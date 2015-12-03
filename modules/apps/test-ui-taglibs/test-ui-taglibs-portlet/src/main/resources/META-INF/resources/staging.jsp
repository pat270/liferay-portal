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

<%-- <%
long exportImportConfigurationId = 0;

ExportImportConfiguration exportImportConfiguration = null;
Map<String, Serializable> exportImportConfigurationSettingsMap = Collections.emptyMap();
Map<String, String[]> parameterMap = Collections.emptyMap();
long[] selectedLayoutIds = null;

if (SessionMessages.contains(liferayPortletRequest, portletDisplay.getId() + "exportImportConfigurationId")) {
	exportImportConfigurationId = (Long)SessionMessages.get(liferayPortletRequest, portletDisplay.getId() + "exportImportConfigurationId");

	if (exportImportConfigurationId > 0) {
		exportImportConfiguration = ExportImportConfigurationLocalServiceUtil.getExportImportConfiguration(exportImportConfigurationId);
	}

	exportImportConfigurationSettingsMap = (Map<String, Serializable>)SessionMessages.get(liferayPortletRequest, portletDisplay.getId() + "settingsMap");

	parameterMap = (Map<String, String[]>)exportImportConfigurationSettingsMap.get("parameterMap");
	selectedLayoutIds = GetterUtil.getLongValues(exportImportConfigurationSettingsMap.get("layoutIds"));
}
else {
	exportImportConfigurationId = ParamUtil.getLong(request, "exportImportConfigurationId");

	if (exportImportConfigurationId > 0) {
		exportImportConfiguration = ExportImportConfigurationLocalServiceUtil.getExportImportConfiguration(exportImportConfigurationId);

		exportImportConfigurationSettingsMap = exportImportConfiguration.getSettingsMap();

		parameterMap = (Map<String, String[]>)exportImportConfigurationSettingsMap.get("parameterMap");
		selectedLayoutIds = GetterUtil.getLongValues(exportImportConfigurationSettingsMap.get("layoutIds"));
	}
}
%> --%>

<div class="alert alert-default">
	<strong class="lead">Taglibs used: </strong>

	<span class="badge badge-primary">staging:menu</span>

	<span class="badge badge-primary">staging:configuration-header</span>

	<span class="badge badge-primary">staging:content</span>

	<span class="badge badge-primary">staging:deletions</span>

	<span class="badge badge-primary">staging:portlet-list</span>
</div>

<h3>liferay-staging:menu</h3>

<liferay-staging:menu cssClass="publish-link" extended="<%= false %>" onlyActions="<%= true %>" />

<liferay-staging:menu />

<br />

<h3>liferay-staging:configuration-header</h3>

<%-- <liferay-staging:configuration-header
	exportImportConfiguration=""
	label="configuration header"
/> --%>

<br />

<h3>liferay-staging:content</h3>

<%-- <liferay-staging:content disableInputs="<%= true %>" parameterMap="<%= parameterMap %>" type="<%= cmd %>" /> --%>

<br />

<h3>liferay-staging:deletions</h3>

<%-- <liferay-staging:deletions /> --%>

<br />

<h3>liferay-staging:portlet-list</h3>

<%-- <liferay-staging:portlet-list /> --%>

<%-- <liferay-staging:portlet-list dateRange="" disableInputs="" parameterMap="<%= parameterMap %>" portlets="<%= dataSiteLevelPortlets %>" type="<%= type %>" /> --%>
