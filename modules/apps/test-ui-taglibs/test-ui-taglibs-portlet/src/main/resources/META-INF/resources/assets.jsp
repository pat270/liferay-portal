<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%
PortletRequest portletRequest = (PortletRequest)request.getAttribute("javax.portlet.request");

ServiceContext serviceContext = new ServiceContext();

// Create exceptions to make error messages appear.

AssetVocabulary assetVocabTest = AssetVocabularyLocalServiceUtil.createAssetVocabulary(12356362623L);

AssetCategoryException testCategoryException = new AssetCategoryException(
	assetVocabTest, AssetCategoryException.TOO_MANY_CATEGORIES);

AssetTagException testTagException = new AssetTagException(AssetTagException.AT_LEAST_ONE_TAG);

SessionErrors.add(portletRequest, testCategoryException.getClass(), testCategoryException);
SessionErrors.add(portletRequest, testTagException.getClass(), testTagException);

SessionMessages.add(portletRequest, PortalUtil.getPortletId(portletRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

// Add asset entries, categories, vocabularies, and tags.

AssetTagLocalServiceUtil tagUtil = new AssetTagLocalServiceUtil();
AssetEntryLocalServiceUtil entryUtil = new AssetEntryLocalServiceUtil();
AssetCategoryLocalServiceUtil categoryUtil = new AssetCategoryLocalServiceUtil();
AssetVocabularyLocalServiceUtil vocabularyUtil = new AssetVocabularyLocalServiceUtil();

try {
	entryUtil.deleteAssetEntry(98765);
	categoryUtil.deleteAssetCategory(56789);
	tagUtil.deleteAssetTag(23456);
	vocabularyUtil.deleteAssetVocabulary(8);
}
catch (Exception e) {
	out.println("Exception caught while deleting assets.\n");
}

try {
	AssetEntry testAssetEntry = entryUtil.createAssetEntry(98765);
	entryUtil.addAssetEntry(testAssetEntry);

	AssetCategory testAssetCategory = categoryUtil.createAssetCategory(56789);
	categoryUtil.addAssetCategory(testAssetCategory);

	AssetTag testAssetTag = tagUtil.createAssetTag(23456);
	tagUtil.addAssetTag(testAssetTag);

	AssetVocabulary testAssetVocabulary = vocabularyUtil.createAssetVocabulary(8);
	vocabularyUtil.addAssetVocabulary(testAssetVocabulary);
}
catch (Exception e) {
	out.println("Exception caught while adding assets.\n");
}

try {
	// vocabularyUtil.addVocabulary(user.getUserId(), user.getGroupId(), "Test Vocab Title", serviceContext);
	// tagUtil.addTag(user.getUserId(), user.getGroupId(), "test tag name", serviceContext);
	categoryUtil.addAssetEntryAssetCategory(98765, 56789);
	tagUtil.addAssetEntryAssetTag(98765, 23456);
}
catch (Exception e) {
	out.println("Failed somewhere in the add block.\n");
	out.println(e.getMessage());
}

// Throws Error:
// ERROR [http-bio-8080-exec-16][render_portlet_jsp:131] null
// java.lang.RuntimeException: Unable to get class name from id 0
// AssetTagLocalServiceUtil.updateTag(user.getUserId(), 23456, "Tag Name Testing", serviceContext);

// Create new blog post.
//
// Calendar rCal = SocialDriverUtil.getCal();
//
// ServiceContext serviceContext = new ServiceContext();
// serviceContext.setCreateDate(rCal.getTime());
// serviceContext.setModifiedDate(rCal.getTime());
// String cid = contentContainer.getRandomId();
// String title = contentContainer.getContentTitle(cid);
// String content = contentContainer.getContentBody(cid);
// String[] tags = contentContainer.getContentTags(cid);
// serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
// serviceContext.setAddGroupPermissions(true);
// serviceContext.setAddGuestPermissions(true);
//
// serviceContext.setAssetTagNames(tags);
// serviceContext.setCompanyId(companyId);
// serviceContext.setScopeGroupId(groupId);
//
// BlogsEntry newEntry = BlogsEntryLocalServiceUtil.addEntry
//     (SocialDriverUtil.getUserId(companyId, themeId, profileFlag),
//         title, "",
//     content,
//     rCal.get(Calendar.MONTH), rCal.get(Calendar.DAY_OF_MONTH),
//     rCal.get(Calendar.YEAR),
//     rCal.get(Calendar.HOUR_OF_DAY), rCal.get(Calendar.MINUTE), false,
//         false, null, false, null,
//     null, null, serviceContext);

// BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);
// BlogsEntry entry = BlogsEntryServiceUtil.getEntry(22519);
//
// if (!AssetTagLocalServiceUtil.hasTag(entry.getGroupId(), "tagTest"))
// 	AssetTagLocalServiceUtil.addTag(entry.getUserId(), entry.getGroupId(), "tagTest", serviceContext);
%>

<div class="alert alert-default">
	<strong class="lead">Taglibs used: </strong>

	<span class="badge badge-primary">liferay-ui:asset-categories-error</span>

	<span class="badge badge-primary">liferay-ui:asset-categories-navigation</span>

	<span class="badge badge-primary">liferay-ui:asset-categories-selector</span>

	<span class="badge badge-primary">liferay-ui:asset-categories-summary</span>

	<span class="badge badge-primary">liferay-ui:asset-links</span>

	<span class="badge badge-primary">liferay-ui:asset-tags-error</span>

	<span class="badge badge-primary">liferay-ui:asset-tags-navigation</span>

	<span class="badge badge-primary">liferay-ui:asset-tags-selector</span>

	<span class="badge badge-primary">liferay-ui:asset-tags-summary</span>

	<span class="badge badge-primary">liferay-ui:categorization-filter</span>

	<span class="badge badge-primary">liferay-ui:input-asset-links</span>
</div>

<h3>ui:asset-categories-error</h3>

<liferay-ui:asset-categories-error />

<h3>ui:asset-categories-navigation</h3>

<liferay-ui:asset-categories-navigation hidePortletWhenEmpty="<%= false %>"/>

<h3>ui:asset-categories-selector</h3>

<liferay-ui:asset-categories-selector />

<h3>ui:asset-categories-summary</h3>

<liferay-ui:asset-categories-summary
	className="<%= AssetEntryLocalServiceUtil.getAssetEntry(98765).getClassName() %>"
	classPK="<%= 98765 %>"
	portletURL="<%= portletURL %>"
/>

<h3>ui:asset-links</h3>

<liferay-ui:asset-links assetEntryId="22519" />

<h3>ui:asset-tags-error</h3>

<liferay-ui:asset-tags-error />

<h3>ui:asset-tags-navigation</h3>

<%
long classNameId = PrefsParamUtil.getLong(portletPreferences, request, "classNameId");
String displayStyle = PrefsParamUtil.getString(portletPreferences, request, "displayStyle", "cloud");
long displayStyleGroupId = PrefsParamUtil.getLong(portletPreferences, request, "displayStyleGroupId", themeDisplay.getScopeGroupId());
int maxAssetTags = PrefsParamUtil.getInteger(portletPreferences, request, "maxAssetTags", 10);
boolean showAssetCount = PrefsParamUtil.getBoolean(portletPreferences, request, "showAssetCount");
boolean showZeroAssetCount = PrefsParamUtil.getBoolean(portletPreferences, request, "showZeroAssetCount");
%>

<liferay-ui:asset-tags-navigation
	classNameId="<%= classNameId %>"
	displayStyle="<%= displayStyle %>"
	hidePortletWhenEmpty="<%= false %>"
	maxAssetTags="<%= maxAssetTags %>"
	showAssetCount="<%= showAssetCount %>"
	showZeroAssetCount="<%= showZeroAssetCount %>"
/>

<h3>ui:asset-tags-selector</h3>

<liferay-ui:asset-tags-selector />

<h3>ui:asset-tags-summary</h3>

<%
// long assetEntryId = ParamUtil.getLong(request, "assetEntryId");
// AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByType(type);
//
// AssetEntry assetEntry = assetRendererFactory.getAssetEntry(assetEntryId);
%>

<%-- <liferay-ui:asset-tags-summary
	className="<%= testAssetEntry.getClassName() %>"
	classPK="<%= testAssetEntry.getEntryId() %>"
	portletURL="<%= portletURL %>"
/> --%>

<h3>ui:categorization-filter</h3>

<liferay-ui:categorization-filter
	assetType="content"
	portletURL="<%= portletURL %>"
/>

<h3>ui:input-asset-links</h3>

<liferay-ui:input-asset-links />
