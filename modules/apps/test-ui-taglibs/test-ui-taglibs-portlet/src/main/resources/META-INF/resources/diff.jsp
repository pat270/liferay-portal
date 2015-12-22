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

<%
DiffResult dr1 = new DiffResult(2, "Test 1 changed line");
DiffResult dr2 = new DiffResult(2, "Test 2 changed line");

List<DiffResult> list1 = new ArrayList<DiffResult>();
List<DiffResult> list2 = new ArrayList<DiffResult>();

List<DiffResult>[] diffResults = (List<DiffResult>[])new List<?>[2];

list1.add(dr1);
list2.add(dr2);

diffResults[0] = list1;
diffResults[1] = list2;
%>

<liferay-ui:diff
	diffResults="<%= diffResults %>"
	sourceName="Source Name Test"
	targetName="Target Name Test"
/>

<br />

<h3>ui:diff-html</h3>

<%
String diffHtmlResults = "Test content.&nbsp;Test<span class=\"diff-html-removed\" previous=\"first-diff\" changeid=\"removed-diff-0\" next=\"removed-diff-1\"> </span><span class=\"diff-html-removed\" id=\"removed-diff-0\" previous=\"first-diff\" changeid=\"removed-diff-0\" next=\"removed-diff-1\">content</span>.&nbsp;Test<span class=\"diff-html-removed\" previous=\"removed-diff-0\" changeid=\"removed-diff-1\" next=\"removed-diff-2\"> </span><span class=\"diff-html-removed\" id=\"removed-diff-1\" previous=\"removed-diff-0\" changeid=\"removed-diff-1\" next=\"removed-diff-2\">content</span>.&nbsp;Test <span class=\"diff-html-removed\" id=\"removed-diff-2\" previous=\"removed-diff-1\" changeid=\"removed-diff-2\" next=\"added-diff-0\">content</span><span class=\"diff-html-added\" id=\"added-diff-0\" previous=\"removed-diff-2\" changeid=\"added-diff-0\" next=\"last-diff\">test</span>.&nbsp;Test <span class=\"diff-html-changed\" id=\"changed-diff-0\" changes=\"<b>Strong</b> style added.\" previous=\"first-diff\" changeid=\"changed-diff-0\" next=\"last-diff\">content</span>.&nbsp;Test content.&nbsp;Test content.&nbsp;Test content.&nbsp;";

String infoMessage = "Test info message";
%>

<liferay-ui:diff-html
	diffHtmlResults="<%= diffHtmlResults %>"
	infoMessage="<%= infoMessage %>"
/>

<br />

<h3>ui:diff-version-comparator</h3>

<%
Date date = new Date();

DiffVersion diffVersion = new DiffVersion(user.getUserId(), 2, date);

List<DiffVersion> diffVersionList = new ArrayList<DiffVersion>();
diffVersionList.add(diffVersion);

DiffVersionsInfo diffVersionsInfo = new DiffVersionsInfo(diffVersionList, 3, 1);
%>

<liferay-frontend:diff-version-comparator
	diffHtmlResults="<%= diffHtmlResults %>"
	diffVersionsInfo="<%= diffVersionsInfo %>"
	portletURL="<%= portletURL %>"
	resourceURL="<%= portletURL %>"
	sourceVersion="<%= 2 %>"
	targetVersion="<%= 3 %>"
/>
