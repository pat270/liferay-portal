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

	DisplayTerms searchDisplayTerms = new DisplayTerms(portletRequest);
%>

<div class="alert alert-default">
	<strong class="lead">Taglibs used: </strong>

	<span class="badge badge-primary">liferay-ui:search-container</span>

	<span class="badge badge-primary">aui:fieldset</span>

	<span class="badge badge-primary">aui:input</span>
</div>

<c:catch var="catchException">
	<liferay-ui:search-toggle
		buttonLabel="search"
		displayTerms="<%= searchDisplayTerms %>"
		id="toggle_id_asset_search"
	>
		<aui:fieldset>
			<aui:input inlineField="<%= true %>" name="name" size="30" value="value" />

			<aui:input inlineField="<%= true %>" name="name" size="30" value="value" />
		</aui:fieldset>
	</liferay-ui:search-toggle>
</c:catch>

<c:if test = "${catchException != null}">
	<div class="alert alert-danger">
		${catchException}
	</div>
</c:if>

<br />

<h3>Lexicon View</h3>

<c:catch var="catchException">
	<liferay-ui:search-toggle
		buttonLabel="search"
		displayTerms="<%= searchDisplayTerms %>"
		id="toggle_id_asset_search"
		markupView="lexicon"
	>
		<aui:fieldset>
			<aui:input inlineField="<%= true %>" name="name" size="30" value="value" />

			<aui:input inlineField="<%= true %>" name="name" size="30" value="value" />
		</aui:fieldset>
	</liferay-ui:search-toggle>
</c:catch>

<c:if test = "${catchException != null}">
	<div class="alert alert-danger">
		${catchException}
	</div>
</c:if>