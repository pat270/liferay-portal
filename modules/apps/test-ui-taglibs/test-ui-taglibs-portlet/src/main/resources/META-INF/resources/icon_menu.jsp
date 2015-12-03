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

	<span class="badge badge-primary">liferay-ui:icon-menu</span>

	<span class="badge badge-primary">liferay-ui:icon</span>
</div>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="Button">
	<liferay-ui:icon
		iconCssClass="icon-external-link"
		message="shortcut"
		url="link"
	/>
	<liferay-ui:icon
		iconCssClass="icon-remove"
		message="cancel-checkout[document]"
		url="link"
	/>
	<liferay-ui:icon
		iconCssClass="icon-lock"
		message="checkin"
		url="link"
	/>
	<liferay-ui:icon
		iconCssClass="icon-unlock"
		message="checkout[document]"
		url="link"
	/>
	<liferay-ui:icon
		iconCssClass="icon-move"
		message="move"
		url="link"
	/>
</liferay-ui:icon-menu>
