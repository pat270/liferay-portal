/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.cms.site.initializer.internal.fragment.renderer.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.depot.constants.DepotConstants;
import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.service.DepotEntryLocalService;
import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectEntryFolder;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryFolderLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.test.util.ObjectEntryFolderTestUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.TestInfo;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Patrick Yeo
 */
@RunWith(Arquillian.class)
public class PermissionsComponentSectionFragmentRendererTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_depotEntry = _depotEntryLocalService.addDepotEntry(
			Collections.singletonMap(
				LocaleUtil.getDefault(), RandomTestUtil.randomString()),
			null, DepotConstants.TYPE_SPACE,
			new ServiceContext() {
				{
					setCompanyId(TestPropsValues.getCompanyId());
					setUserId(TestPropsValues.getUserId());
				}
			});
	}

	@Test
	@TestInfo("LPD-116791")
	public void testGetProps() throws Exception {

		// Folder inside Contents

		ObjectEntryFolder contentsObjectEntryFolder =
			ObjectEntryFolderTestUtil.addObjectEntryFolder(
				_depotEntry.getGroupId(),
				_getRootObjectEntryFolderId(
					ObjectEntryFolderConstants.
						EXTERNAL_REFERENCE_CODE_CONTENTS));

		String backURL = RandomTestUtil.randomString();

		Map<String, Object> props = _getProps(
			backURL, ObjectEntryFolder.class.getName(),
			contentsObjectEntryFolder.getObjectEntryFolderId());

		Assert.assertEquals(backURL, props.get("backURL"));
		Assert.assertEquals(
			ObjectEntryFolder.class.getName(), props.get("className"));
		Assert.assertEquals(
			contentsObjectEntryFolder.getObjectEntryFolderId(),
			props.get("classPK"));
		Assert.assertEquals(
			Arrays.asList("subfolders", "contents"),
			props.get("defaultsSections"));

		// Folder inside Files

		ObjectEntryFolder filesObjectEntryFolder =
			ObjectEntryFolderTestUtil.addObjectEntryFolder(
				_depotEntry.getGroupId(),
				_getRootObjectEntryFolderId(
					ObjectEntryFolderConstants.EXTERNAL_REFERENCE_CODE_FILES));

		props = _getProps(
			backURL, ObjectEntryFolder.class.getName(),
			filesObjectEntryFolder.getObjectEntryFolderId());

		Assert.assertEquals(
			Arrays.asList("subfolders", "files"),
			props.get("defaultsSections"));

		// Object entry

		ObjectEntry objectEntry = _addObjectEntry(
			contentsObjectEntryFolder.getObjectEntryFolderId());

		props = _getProps(
			backURL, objectEntry.getModelClassName(),
			objectEntry.getObjectEntryId());

		Assert.assertEquals(
			Collections.emptyList(), props.get("defaultsSections"));

		// Unsafe back URL

		props = _getProps(
			"javascript:alert(document.cookie)",
			ObjectEntryFolder.class.getName(),
			contentsObjectEntryFolder.getObjectEntryFolderId());

		Assert.assertEquals(
			_portal.getPathFriendlyURLPublic() +
				GroupConstants.CMS_FRIENDLY_URL,
			props.get("backURL"));
	}

	@Test
	@TestInfo("LPD-116791")
	public void testGetPropsWithoutPermissionsPermission() throws Exception {
		ObjectEntryFolder objectEntryFolder =
			ObjectEntryFolderTestUtil.addObjectEntryFolder(
				_depotEntry.getGroupId(),
				_getRootObjectEntryFolderId(
					ObjectEntryFolderConstants.
						EXTERNAL_REFERENCE_CODE_CONTENTS));

		_user = UserTestUtil.addUser();

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(_user));

		Assert.assertThrows(
			PrincipalException.class,
			() -> _getProps(
				RandomTestUtil.randomString(),
				ObjectEntryFolder.class.getName(),
				objectEntryFolder.getObjectEntryFolderId()));
	}

	private ObjectEntry _addObjectEntry(long objectEntryFolderId)
		throws Exception {

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.
				getObjectDefinitionByExternalReferenceCode(
					"L_CMS_BASIC_WEB_CONTENT", TestPropsValues.getCompanyId());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		serviceContext.setAttribute(
			"friendlyUrlMap", new HashMap<String, String>());

		return _objectEntryLocalService.addObjectEntry(
			_depotEntry.getGroupId(), TestPropsValues.getUserId(),
			objectDefinition.getObjectDefinitionId(), objectEntryFolderId,
			"en_US",
			HashMapBuilder.<String, Serializable>put(
				"content_i18n",
				HashMapBuilder.put(
					"en_US", RandomTestUtil.randomString()
				).build()
			).put(
				"title_i18n",
				HashMapBuilder.put(
					"en_US", RandomTestUtil.randomString()
				).build()
			).build(),
			serviceContext);
	}

	private Map<String, Object> _getProps(
			String backURL, String className, long classPK)
		throws Exception {

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(
			_companyLocalService.getCompany(TestPropsValues.getCompanyId()));
		themeDisplay.setLocale(LocaleUtil.getDefault());
		themeDisplay.setPathFriendlyURLPublic(
			_portal.getPathFriendlyURLPublic());
		themeDisplay.setPermissionChecker(
			PermissionThreadLocal.getPermissionChecker());

		mockHttpServletRequest.setAttribute(
			WebKeys.THEME_DISPLAY, themeDisplay);

		mockHttpServletRequest.setParameter("backURL", backURL);
		mockHttpServletRequest.setParameter("className", className);
		mockHttpServletRequest.setParameter("classPK", String.valueOf(classPK));

		return ReflectionTestUtil.invoke(
			_fragmentRenderer, "getProps",
			new Class<?>[] {
				FragmentRendererContext.class, HttpServletRequest.class
			},
			null, mockHttpServletRequest);
	}

	private long _getRootObjectEntryFolderId(String externalReferenceCode)
		throws Exception {

		ObjectEntryFolder objectEntryFolder =
			_objectEntryFolderLocalService.
				getObjectEntryFolderByExternalReferenceCode(
					externalReferenceCode, _depotEntry.getGroupId(),
					_depotEntry.getCompanyId());

		return objectEntryFolder.getObjectEntryFolderId();
	}

	@Inject
	private CompanyLocalService _companyLocalService;

	@DeleteAfterTestRun
	private DepotEntry _depotEntry;

	@Inject
	private DepotEntryLocalService _depotEntryLocalService;

	@Inject(
		filter = "component.name=com.liferay.site.cms.site.initializer.internal.fragment.renderer.PermissionsComponentSectionFragmentRenderer"
	)
	private FragmentRenderer _fragmentRenderer;

	@Inject
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Inject
	private ObjectEntryFolderLocalService _objectEntryFolderLocalService;

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	@Inject
	private Portal _portal;

	@DeleteAfterTestRun
	private User _user;

}
