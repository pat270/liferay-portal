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

	<span class="badge badge-primary">liferay-ui:tabs</span>

	<span class="badge badge-primary">liferay-ui:section</span>
</div>

<liferay-ui:tabs
	names="Tab 1,Tab 2,Tab 3"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		Content of 1st section.
		Content of 1st section.
		Content of 1st section.
		Content of 1st section.
		Content of 1st section.
	</liferay-ui:section>

	<liferay-ui:section>
		Content of 2nd section.
		Content of 2nd section.
		Content of 2nd section.
		Content of 2nd section.
		Content of 2nd section.
	</liferay-ui:section>

	<liferay-ui:section>
		Content of 3rd section.
		Content of 3rd section.
		Content of 3rd section.
		Content of 3rd section.
		Content of 3rd section.
	</liferay-ui:section>
</liferay-ui:tabs>