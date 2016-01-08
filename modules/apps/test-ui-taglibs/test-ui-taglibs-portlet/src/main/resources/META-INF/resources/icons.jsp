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

	<span class="badge badge-primary">aui:icon</span>

	<span class="badge badge-primary">liferay-ui:icon</span>
</div>

<h3>aui:icon</h3>

<aui:icon
	image="edit"
/>

<br />

<h3>aui:icon with label</h3>

<aui:icon
	image="heart"
	label="Heart Icon"
/>

<br />

<h3>liferay-ui:icon</h3>

<liferay-ui:icon
	iconCssClass="icon-refresh"
/>

<br />

<h3>liferay-ui:icon with tooltip</h3>

<liferay-ui:icon
	iconCssClass="icon-circle-arrow-left"
	message="Search Icon"
/>

<br />

<h3>liferay-ui:icon with label</h3>

<liferay-ui:icon
	iconCssClass="icon-search"
	label="<%= true %>"
	message="Search Icon"
/>