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
if (!user.getExpandoBridge().hasAttribute("Custom User Field 1")) {
	user.getExpandoBridge().addAttribute("Custom User Field 1");
	user.getExpandoBridge().setAttribute("Custom User Field 1", "Value 1");
}

if (!user.getExpandoBridge().hasAttribute("Custom User Field 2")) {
	user.getExpandoBridge().addAttribute("Custom User Field 2");
	user.getExpandoBridge().setAttribute("Custom User Field 2", "Value 2");
}

if (!user.getExpandoBridge().hasAttribute("Custom User Field 3")) {
	user.getExpandoBridge().addAttribute("Custom User Field 3");
	user.getExpandoBridge().setAttribute("Custom User Field 3", "Value 3");
}
%>

<div class="alert alert-default">
	<strong class="lead">Taglibs used: </strong>

	<span class="badge badge-primary">liferay-ui:custom-attribute-list</span>
</div>

<h3>Custom Attributes for User</h3>

<%
if (!themeDisplay.isSignedIn()) {
	out.println("<div class=\"alert alert-warning\">"
		+ "Must be signed in to view custom attributes."
		+ "</div>");
}
%>

<liferay-ui:custom-attribute-list
	className="<%= User.class.getName() %>"
	classPK="<%= user.getUserId() %>"
	editable="<%= false %>"
	label="<%= true %>"
/>

<br />

<h3>Editable Custom Attributes for User</h3>

<%
if (!themeDisplay.isSignedIn()) {
	out.println("<div class=\"alert alert-warning\">"
		+ "Must be signed in to view editable custom attributes."
		+ "</div>");
}
%>

<liferay-ui:custom-attribute-list
	className="<%= User.class.getName() %>"
	classPK="<%= user.getUserId() %>"
	editable="<%= true %>"
	label="<%= true %>"
/>