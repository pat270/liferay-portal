/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.cms.site.initializer.internal.fragment.renderer;

import com.liferay.depot.constants.DepotRolesConstants;
import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.entry.folder.util.ObjectEntryFolderUtil;
import com.liferay.object.model.ObjectEntryFolder;
import com.liferay.object.service.ObjectEntryFolderLocalService;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionRegistryUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Patrick Yeo
 */
@Component(service = FragmentRenderer.class)
public class PermissionsComponentSectionFragmentRenderer
	extends BaseComponentSectionFragmentRenderer {

	@Override
	public String getCollectionKey() {
		return "sections";
	}

	@Override
	public void render(
			FragmentRendererContext fragmentRendererContext,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (!FeatureFlagManagerUtil.isEnabled(
				themeDisplay.getCompanyId(), "LPD-114063")) {

			return;
		}

		super.render(
			fragmentRendererContext, httpServletRequest, httpServletResponse);
	}

	@Override
	protected String getComponentName() {
		return "Permissions";
	}

	@Override
	protected String getLabelKey() {
		return "permissions";
	}

	@Override
	protected Map<String, Object> getProps(
			FragmentRendererContext fragmentRendererContext,
			HttpServletRequest httpServletRequest)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		String className = ParamUtil.getString(
			httpServletRequest, "className");
		long classPK = ParamUtil.getLong(httpServletRequest, "classPK");

		ModelResourcePermission<?> modelResourcePermission =
			ModelResourcePermissionRegistryUtil.getModelResourcePermission(
				className);

		if (modelResourcePermission == null) {
			throw new PrincipalException();
		}

		modelResourcePermission.check(
			themeDisplay.getPermissionChecker(), classPK,
			ActionKeys.PERMISSIONS);

		return HashMapBuilder.<String, Object>put(
			"actions", _getActions(className, httpServletRequest)
		).put(
			"backURL", _getBackURL(httpServletRequest, themeDisplay)
		).put(
			"className", className
		).put(
			"classPK", classPK
		).put(
			"defaultsSections", _getDefaultsSections(className, classPK)
		).put(
			"roles", _getRoles(className, classPK, themeDisplay)
		).build();
	}

	private List<Map<String, Object>> _getActions(
		String className, HttpServletRequest httpServletRequest) {

		if (!Objects.equals(className, ObjectEntryFolder.class.getName())) {
			return Collections.emptyList();
		}

		List<String> guestUnsupportedActions =
			ResourceActionsUtil.getResourceGuestUnsupportedActions(
				null, className);

		return TransformUtil.transform(
			ResourceActionsUtil.getResourceActions(className),
			resourceAction -> HashMapBuilder.<String, Object>put(
				"guestUnsupported",
				guestUnsupportedActions.contains(resourceAction)
			).put(
				"key", resourceAction
			).put(
				"label",
				ResourceActionsUtil.getAction(
					httpServletRequest, resourceAction)
			).build());
	}

	private String _getBackURL(
		HttpServletRequest httpServletRequest, ThemeDisplay themeDisplay) {

		String backURL = PortalUtil.escapeRedirect(
			ParamUtil.getString(httpServletRequest, "backURL"));

		if (Validator.isNotNull(backURL)) {
			return backURL;
		}

		return themeDisplay.getPathFriendlyURLPublic() +
			GroupConstants.CMS_FRIENDLY_URL;
	}

	private List<String> _getDefaultsSections(String className, long classPK) {
		if (!Objects.equals(className, ObjectEntryFolder.class.getName())) {
			return Collections.emptyList();
		}

		ObjectEntryFolder rootObjectEntryFolder =
			_objectEntryFolderLocalService.fetchObjectEntryFolder(
				ObjectEntryFolderUtil.getRootObjectEntryFolderId(classPK));

		if (rootObjectEntryFolder == null) {
			return ListUtil.fromArray("subfolders");
		}

		if (Objects.equals(
				rootObjectEntryFolder.getExternalReferenceCode(),
				ObjectEntryFolderConstants.EXTERNAL_REFERENCE_CODE_CONTENTS)) {

			return ListUtil.fromArray("subfolders", "contents");
		}

		if (Objects.equals(
				rootObjectEntryFolder.getExternalReferenceCode(),
				ObjectEntryFolderConstants.EXTERNAL_REFERENCE_CODE_FILES)) {

			return ListUtil.fromArray("subfolders", "files");
		}

		return ListUtil.fromArray("subfolders");
	}

	private List<Map<String, Object>> _getRoles(
			String className, long classPK, ThemeDisplay themeDisplay)
		throws Exception {

		if (!Objects.equals(className, ObjectEntryFolder.class.getName())) {
			return Collections.emptyList();
		}

		ObjectEntryFolder objectEntryFolder =
			_objectEntryFolderLocalService.getObjectEntryFolder(classPK);

		List<String> resourceActions = ResourceActionsUtil.getResourceActions(
			className);

		Map<Long, Set<String>> companyActionIds =
			_resourcePermissionLocalService.
				getAvailableResourcePermissionActionIds(
					themeDisplay.getCompanyId(), className,
					ResourceConstants.SCOPE_COMPANY,
					String.valueOf(themeDisplay.getCompanyId()),
					resourceActions);
		Map<Long, Set<String>> groupActionIds =
			_resourcePermissionLocalService.
				getAvailableResourcePermissionActionIds(
					themeDisplay.getCompanyId(), className,
					ResourceConstants.SCOPE_GROUP,
					String.valueOf(objectEntryFolder.getGroupId()),
					resourceActions);

		return TransformUtil.transform(
			_roleLocalService.getGroupRolesAndTeamRoles(
				themeDisplay.getCompanyId(), null,
				Arrays.asList(
					RoleConstants.ADMINISTRATOR,
					DepotRolesConstants.ASSET_LIBRARY_OWNER),
				null, null,
				new int[] {
					RoleConstants.TYPE_REGULAR, RoleConstants.TYPE_DEPOT
				},
				DepotRolesConstants.SUBTYPE_SPACE, 0, 0, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS),
			role -> {
				Set<String> actionIds = new HashSet<>(
					companyActionIds.getOrDefault(
						role.getRoleId(), Collections.emptySet()));

				actionIds.addAll(
					groupActionIds.getOrDefault(
						role.getRoleId(), Collections.emptySet()));

				return HashMapBuilder.<String, Object>put(
					"actions", ArrayUtil.toStringArray(actionIds)
				).put(
					"key", role.getName()
				).put(
					"name", role.getTitle(themeDisplay.getLocale())
				).put(
					"type", String.valueOf(role.getType())
				).build();
			});
	}

	@Reference
	private ObjectEntryFolderLocalService _objectEntryFolderLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}
