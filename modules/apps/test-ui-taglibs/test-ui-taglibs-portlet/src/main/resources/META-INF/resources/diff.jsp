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

<div class="alert alert-default">
	<strong class="lead">Taglibs used: </strong>

	<span class="badge badge-primary">liferay-ui:diff</span>

	<span class="badge badge-primary">liferay-ui:diff-html</span>

	<span class="badge badge-primary">liferay-ui:diff-version-comparator</span>
</div>

<h3>ui:diff</h3>

<%-- <%
String sourceName = (String)renderRequest.getAttribute("SOURCE_NAME");
String targetName = (String)renderRequest.getAttribute("TARGET_NAME");
List[] diffResults = (List[])renderRequest.getAttribute("DIFF_RESULTS");
%>

<c:catch var="catchException">
	<liferay-ui:diff
		diffResults="<%= diffResults %>"
		sourceName="test"
		targetName="test"
	/>
</c:catch>

<c:if test = "${catchException != null}">
	<div class="alert alert-danger">
		${catchException}
	</div>
</c:if> --%>

<br />

<h3>ui:diff-html</h3>

<%
String diffHtmlResults = (String)request.getAttribute(WebKeys.DIFF_HTML_RESULTS);
double diffVersion = GetterUtil.getDouble(request.getAttribute(WebKeys.DIFF_VERSION));

String infoMessage = StringPool.BLANK;

if (diffVersion > 0) {
	infoMessage = LanguageUtil.format(request, "unable-to-render-version-x", diffVersion);
}
%>

<liferay-ui:diff-html
	diffHtmlResults="<%= diffHtmlResults %>"
	infoMessage="<%= infoMessage %>"
/>

<br />

<h3>ui:diff-version-comparator</h3>

<%-- <%
String redirect = ParamUtil.getString(request, "redirect");
long groupId = ParamUtil.getLong(request, "groupId");
String articleId = ParamUtil.getString(request, "articleId");

Set<Locale> availableLocales = (Set<Locale>)request.getAttribute(WebKeys.AVAILABLE_LOCALES);
String languageId = (String)request.getAttribute(WebKeys.LANGUAGE_ID);
double sourceVersion = (Double)request.getAttribute(WebKeys.SOURCE_VERSION);
double targetVersion = (Double)request.getAttribute(WebKeys.TARGET_VERSION);
%>

<liferay-portlet:resourceURL id="compareVersions" varImpl="resourceURL">
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
	<portlet:param name="articleId" value="<%= articleId %>" />
</liferay-portlet:resourceURL>

<liferay-ui:diff-version-comparator
	availableLocales="<%= availableLocales %>"
	diffHtmlResults="<%= diffHtmlResults %>"
	languageId="<%= languageId %>"
	portletURL="<%= portletURL %>"
	resourceURL="<%= resourceURL %>"
	sourceVersion="<%= sourceVersion %>"
	targetVersion="<%= targetVersion %>"
/> --%>


<%--
<%
String redirect = ParamUtil.getString(request, "redirect");

long groupId = ParamUtil.getLong(request, "groupId");
String articleId = ParamUtil.getString(request, "articleId");

ActionUtil.compareVersions(renderRequest, renderResponse);

Set<Locale> availableLocales = (Set<Locale>)request.getAttribute(WebKeys.AVAILABLE_LOCALES);
String diffHtmlResults = (String)request.getAttribute(WebKeys.DIFF_HTML_RESULTS);
String languageId = (String)request.getAttribute(WebKeys.LANGUAGE_ID);
double sourceVersion = (Double)request.getAttribute(WebKeys.SOURCE_VERSION);
double targetVersion = (Double)request.getAttribute(WebKeys.TARGET_VERSION);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "compare-versions"));
%>

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="mvcPath" value="/compare_versions.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
	<portlet:param name="articleId" value="<%= articleId %>" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL id="compareVersions" varImpl="resourceURL">
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
	<portlet:param name="articleId" value="<%= articleId %>" />
</liferay-portlet:resourceURL>

<div class="container-fluid-1280">
	<liferay-ui:diff-version-comparator
		availableLocales="<%= availableLocales %>"
		diffHtmlResults="<%= diffHtmlResults %>"
		languageId="<%= languageId %>"
		portletURL="<%= portletURL %>"
		resourceURL="<%= resourceURL %>"
		sourceVersion="<%= sourceVersion %>"
		targetVersion="<%= targetVersion %>"
	/>
</div> --%>
