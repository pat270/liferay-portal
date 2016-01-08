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

// Create exceptions to make error messages appear.

AssetVocabulary assetVocabTest = AssetVocabularyLocalServiceUtil.createAssetVocabulary(12356362623L);

AssetCategoryException testCategoryException = new AssetCategoryException(
	assetVocabTest, AssetCategoryException.TOO_MANY_CATEGORIES);

AssetTagException testTagException = new AssetTagException(AssetTagException.AT_LEAST_ONE_TAG);

SessionErrors.add(portletRequest, testCategoryException.getClass(), testCategoryException);
SessionErrors.add(portletRequest, testTagException.getClass(), testTagException);

SessionMessages.add(portletRequest, PortalUtil.getPortletId(portletRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

// Set blog entry variable for ui:asset-categories-summary and ui:asset-tags-summary.

AssetEntry assetEntry = null;
List<AssetCategory> categoryList = null;
List<AssetTag> tagList = null;

BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);

int blogCount = BlogsEntryLocalServiceUtil.getBlogsEntriesCount();
int categoryCount = AssetCategoryLocalServiceUtil.getAssetCategoriesCount();
int tagCount = AssetTagLocalServiceUtil.getAssetTagsCount();

if (blogCount > 0) {
	List<BlogsEntry> blogEntryList = BlogsEntryLocalServiceUtil.getBlogsEntries(0, 1);

	entry = blogEntryList.get(0);

	// Get asset entry from blog entry for ui:asset-links.

	assetEntry = AssetEntryLocalServiceUtil.getEntry(
		BlogsEntry.class.getName(), entry.getEntryId());

	categoryList = AssetCategoryLocalServiceUtil.getCategories(
		BlogsEntry.class.getName(), entry.getEntryId());

	tagList = AssetTagLocalServiceUtil.getTags(
		BlogsEntry.class.getName(), entry.getEntryId());
}
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

<br />

<h3>ui:asset-categories-navigation</h3>

<c:if test="<%= categoryCount == 0 %>">
	<div class="alert alert-warning">
		Add at least 1 category to see this taglib.
	</div>
</c:if>

<liferay-asset:asset-categories-navigation hidePortletWhenEmpty="<%= false %>" />

<br />

<h3>ui:asset-categories-selector</h3>

<liferay-ui:asset-categories-selector />

<br />

<h3>ui:asset-categories-summary for Blog Post

<c:choose>
	<c:when test="<%= BlogsEntryLocalServiceUtil.getBlogsEntriesCount() != 0 && !categoryList.isEmpty() %>">
		: "<%= entry.getTitle() %>"</h3>

		<liferay-ui:asset-categories-summary
			className="<%= BlogsEntry.class.getName() %>"
			classPK="<%= (entry != null) ? entry.getEntryId() : 0 %>"
			message="Test message"
			portletURL="<%= portletURL %>"
		/>
	</c:when>

	<c:otherwise>
		</h3>

		<div class="alert alert-warning">
			Add at least 1 category to the most recent blog entry to see this taglib.
		</div>
	</c:otherwise>
</c:choose>

<br /><br />

<h3>ui:asset-links</h3>

<c:choose>
	<c:when test="<%= assetEntry != null %>">
		<liferay-ui:asset-links
			assetEntryId="<%= (assetEntry != null) ? assetEntry.getEntryId() : 0 %>"
			className="<%= BlogsEntry.class.getName() %>"
			classPK="<%= entry.getEntryId() %>"
		/>
	</c:when>

	<c:otherwise>
		<div class="alert alert-warning">
			Add at least 1 direct link on the most recent blog entry to see this taglib.
		</div>
	</c:otherwise>
</c:choose>

<br />

<h3>ui:asset-tags-error</h3>

<liferay-ui:asset-tags-error />

<br />

<h3>ui:asset-tags-navigation</h3>

<c:choose>
	<c:when test="<%= tagCount != 0 %>">
		<liferay-ui:asset-tags-navigation />
	</c:when>

	<c:otherwise>
		<div class="alert alert-warning">
			Add at least 1 tag to see this taglib.
		</div>
	</c:otherwise>
</c:choose>

<br />

<h3>ui:asset-tags-selector</h3>

<liferay-ui:asset-tags-selector />

<br />

<h3>ui:asset-tags-summary for Blog Post

<c:choose>
	<c:when test="<%= BlogsEntryLocalServiceUtil.getBlogsEntriesCount() != 0 && !tagList.isEmpty() %>">
		: "<%= entry.getTitle() %>"</h3>

		<liferay-ui:asset-tags-summary
			className="<%= BlogsEntry.class.getName() %>"
			classPK="<%= (entry != null) ? entry.getEntryId() : 0 %>"
			portletURL="<%= portletURL %>"
		/>
	</c:when>
	<c:otherwise>
		</h3>

		<div class="alert alert-warning">
			Add at least 1 tag to the most recent blog entry to see this taglib.
		</div>
	</c:otherwise>
</c:choose>

<br /><br />

<h3>ui:input-asset-links</h3>

<liferay-ui:input-asset-links />