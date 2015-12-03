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

	<span class="badge badge-primary">liferay-ui:search-container</span>

	<span class="badge badge-primary">liferay-ui:search-container-results</span>

	<span class="badge badge-primary">liferay-ui:search-container-row</span>

	<span class="badge badge-primary">liferay-ui:search-container-column-text</span>

	<span class="badge badge-primary">liferay-ui:search-iterator</span>
</div>

<liferay-ui:search-container
	delta="1"
	headerNames="email-address,screen-name"
	iteratorURL="<%= portletURL %>"
	total="<%= UserLocalServiceUtil.getUsersCount() %>"
>

	<liferay-ui:search-container-results
		results="<%= UserLocalServiceUtil.getUsers(searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.User"
		escapedModel="<%= true %>"
		keyProperty="userId"
		modelVar="curUser"
	>

		<liferay-ui:search-container-column-text
			name="email-address"
			property="emailAddress"
		/>

		<liferay-ui:search-container-column-text
			name="screen-name"
			property="screenName"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<br />

<h3>Lexicon View</h3>

<liferay-ui:search-container
	delta="1"
	headerNames="email-address,screen-name"
	iteratorURL="<%= portletURL %>"
	total="<%= UserLocalServiceUtil.getUsersCount() %>"
>

	<liferay-ui:search-container-results
		results="<%= UserLocalServiceUtil.getUsers(searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.User"
		escapedModel="<%= true %>"
		keyProperty="userId"
		modelVar="curUser"
	>

		<liferay-ui:search-container-column-text
			name="email-address"
			property="emailAddress"
		/>

		<liferay-ui:search-container-column-text
			name="screen-name"
			property="screenName"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator markupView="lexicon" />
</liferay-ui:search-container>
