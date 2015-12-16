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

	<span class="badge badge-primary">staging:menu</span>

	<span class="badge badge-primary">staging:configuration-header</span>

	<span class="badge badge-primary">staging:content</span>

	<span class="badge badge-primary">staging:deletions</span>

	<span class="badge badge-primary">staging:portlet-list</span>
</div>

<h3>liferay-staging:menu</h3>

<%
List<Group> groupList = GroupLocalServiceUtil.getGroups(0, 1);
Group aGroup = groupList.get(0);

renderRequest.setAttribute("stagingGroup", aGroup);
%>

<liferay-staging:menu />

<br />

<h3>liferay-staging:configuration-header</h3>

<c:choose>
	<c:when test="<%= ExportImportConfigurationLocalServiceUtil.getExportImportConfigurationsCount() != 0 %>">
		<%
			List<ExportImportConfiguration> exportImportConfigList = ExportImportConfigurationLocalServiceUtil.getExportImportConfigurations(0,1);

			ExportImportConfiguration exportImportConfiguration = exportImportConfigList.get(0);
		%>

		<liferay-staging:configuration-header
			exportImportConfiguration="<%= exportImportConfiguration %>"
			label="configuration header"
		/>
	</c:when>
	<c:otherwise>
		<div class="alert alert-warning">
			Add at least 1 export import configuration to see this taglib.
		</div>
	</c:otherwise>
</c:choose>

<br />

<h3>liferay-staging:content</h3>

<%
Map<String, String[]> parameterMap = new HashMap<>();

String[] testArrayString = new String[2];
testArrayString[0] = "test";

parameterMap.put("param", testArrayString);
%>

<liferay-staging:content parameterMap="<%= parameterMap %>" type="<%= Constants.EXPORT %>" />

<br />

<h3>liferay-staging:deletions</h3>

<liferay-staging:deletions cmd="<%= Constants.PUBLISH %>" />

<br />

<h3>liferay-staging:portlet-list</h3>

<%
List<Portlet> dataSiteLevelPortlets = ExportImportHelperUtil.getDataSiteLevelPortlets(company.getCompanyId(), false);
%>

<liferay-staging:portlet-list
	parameterMap="<%= parameterMap %>"
	portlets="<%= dataSiteLevelPortlets %>"
	type="<%= Constants.EXPORT %>"
/>
