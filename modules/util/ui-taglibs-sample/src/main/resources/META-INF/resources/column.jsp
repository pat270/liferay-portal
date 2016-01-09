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

	<span class="badge badge-primary">aui:layout</span>

	<span class="badge badge-primary">aui:column</span>
</div>

<aui:column columnWidth="<%= 20 %>" first="<%= true %>">
	Column width set at 20 with "first" attribute set to true.
	Column width set at 30 with "first" attribute set to true.
	Column width set at 30 with "first" attribute set to true.
	Column width set at 30 with "first" attribute set to true.
	Column width set at 30 with "first" attribute set to true.
</aui:column>

<aui:column columnWidth="<%= 30 %>">
	Column width set at 30.
	Column width set at 30.
	Column width set at 30.
	Column width set at 30.
	Column width set at 30.
</aui:column>

<aui:column columnWidth="<%= 50 %>" last="<%= true %>">
	Column width set at 50 with "last" attribute set to true.
	Column width set at 70 with "last" attribute set to true.
	Column width set at 70 with "last" attribute set to true.
	Column width set at 70 with "last" attribute set to true.
	Column width set at 70 with "last" attribute set to true.
</aui:column>