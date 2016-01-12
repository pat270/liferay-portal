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

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "A");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("tabs1", tabs1);

String tabNames = "A,Assets,Breadcrumb,Button,Captcha,Categorization Filter,Column,Container,Custom Attribute,DDM,Diff,Discussion,Display Style,Drop Here Info,Email Notification Settings,Error,Error Header,Flags,Form Navigator,Frontend,Header,Icon Menu,Icons,Input,Language,Layout,Logo Selector,Membership Policy Error,Navbar,Navbar Search,Navigation,Page Iterator,Panel,Preview,Quick Access Entry,Ratings,Row,RSS,Search,Search Paginator,Search Toggle,Sites Directory,Social,Staging,Success,Tabs,Toggle,Translation Manager,Trash,User Display,User Portrait,Workflow Status";
%>

<c:if test='<%= tabs1.equals("Categorization Filter") %>'>

	<%
	List<AssetCategory> assetCategoryList = AssetCategoryLocalServiceUtil.getCategories();
	List<AssetTag> assetTagList = AssetTagLocalServiceUtil.getTags();

	if (!assetCategoryList.isEmpty() && !assetTagList.isEmpty()) {
		Long categoryId = assetCategoryList.get(0).getCategoryId();

		portletURL.setParameter("categoryId", String.valueOf(categoryId));
		portletURL.setParameter("tag", assetTagList.get(0).getName());
	}
	%>

</c:if>

<div class="container-fluid">
	<div class="row">
		<div class="col-xs-3 test-ui-taglib-nav">
			<liferay-ui:tabs
				names="<%= tabNames %>"
				url="<%= portletURL.toString() %>"
			/>
		</div>
		<div class="col-xs-9">
			<h1 class="test-ui-taglib-title">
				<%= ParamUtil.getString(request, "tabs1", "A") %>
			</h1>

			<c:choose>
				<c:when test='<%= tabs1.equals("A") %>'>
					<%@ include file="/a.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Assets") %>'>
					<%@ include file="/assets.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Breadcrumb") %>'>
					<%@ include file="/breadcrumb.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Button") %>'>
					<%@ include file="/button.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Captcha") %>'>
					<%@ include file="/captcha.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Categorization Filter") %>'>
					<%@ include file="/categorization_filter.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Column") %>'>
					<%@ include file="/column.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Container") %>'>
					<%@ include file="/container.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Custom Attribute") %>'>
					<%@ include file="/custom_attribute.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("DDM") %>'>
					<%@ include file="/ddm.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Diff") %>'>
					<%@ include file="/diff.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Discussion") %>'>
					<%@ include file="/discussion.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Display Style") %>'>
					<%@ include file="/display_style.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Drop Here Info") %>'>
					<%@ include file="/drop_here_info.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Email Notification Settings") %>'>
					<%@ include file="/email_notification_settings.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Error") %>'>
					<%@ include file="/error.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Error Header") %>'>
					<%@ include file="/error_header.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Flags") %>'>
					<%@ include file="/flags.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Frontend") %>'>
					<%@ include file="/frontend.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Form Navigator") %>'>
					<%@ include file="/form_navigator.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Header") %>'>
					<%@ include file="/header.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Icons") %>'>
					<%@ include file="/icons.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Icon Menu") %>'>
					<%@ include file="/icon_menu.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Input") %>'>
					<%@ include file="/input.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Language") %>'>
					<%@ include file="/language.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Layout") %>'>
					<%@ include file="/layout.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Logo Selector") %>'>
					<%@ include file="/logo_selector.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Membership Policy Error") %>'>
					<%@ include file="/membership_policy_error.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Navbar") %>'>
					<%@ include file="/navbar.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Navbar Search") %>'>
					<%@ include file="/navbar_search.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Navigation") %>'>
					<%@ include file="/navigation.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Page Iterator") %>'>
					<%@ include file="/page_iterator.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Panel") %>'>
					<%@ include file="/panel.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Preview") %>'>
					<%@ include file="/preview.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Quick Access Entry") %>'>
					<%@ include file="/quick_access_entry.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Ratings") %>'>
					<%@ include file="/ratings.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Row") %>'>
					<%@ include file="/row.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("RSS") %>'>
					<%@ include file="/rss.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Search") %>'>
					<%@ include file="/search.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Search Paginator") %>'>
					<%@ include file="/search_paginator.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Search Toggle") %>'>
					<%@ include file="/search_toggle.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Sites Directory") %>'>
					<%@ include file="/sites_directory.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Social") %>'>
					<%@ include file="/social.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Staging") %>'>
					<%@ include file="/staging.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Success") %>'>
					<%@ include file="/success.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Tabs") %>'>
					<%@ include file="/tabs.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Toggle") %>'>
					<%@ include file="/toggle.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Translation Manager") %>'>
					<%@ include file="/translation_manager.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Trash") %>'>
					<%@ include file="/trash.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("User Display") %>'>
					<%@ include file="/user_display.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("User Portrait") %>'>
					<%@ include file="/user_portrait.jsp" %>
				</c:when>
				<c:when test='<%= tabs1.equals("Workflow Status") %>'>
					<%@ include file="/workflow_status.jsp" %>
				</c:when>
			</c:choose>
		</div>
	</div>
</div>