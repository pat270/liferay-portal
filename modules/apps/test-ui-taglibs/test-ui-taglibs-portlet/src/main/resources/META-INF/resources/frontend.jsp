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

	<span class="badge badge-primary">liferay-frontend:add-menu</span>
	<span class="badge badge-primary">liferay-frontend:add-menu-item</span>
	<span class="badge badge-primary">liferay-frontend:horizontal-card</span>
	<span class="badge badge-primary">liferay-frontend:icon-vertical-card</span>
	<span class="badge badge-primary">liferay-frontend:management-bar</span>
	<span class="badge badge-primary">liferay-frontend:management-bar-buttons</span>
	<span class="badge badge-primary">liferay-frontend:management-bar-display-buttons</span>
	<span class="badge badge-primary">liferay-frontend:management-bar-filters</span>
	<span class="badge badge-primary">liferay-frontend:management-bar-navigation</span>
	<span class="badge badge-primary">liferay-frontend:management-bar-sort</span>
	<span class="badge badge-primary">liferay-frontend:user-vertical-card</span>
	<span class="badge badge-primary">liferay-frontend:vertical-card-footer</span>
	<span class="badge badge-primary">liferay-frontend:vertical-card</span>
	<span class="badge badge-primary">liferay-frontend:vertical-card-sticker-bottom</span>
	<span class="badge badge-primary">liferay-frontend:vertical-card-header</span>
	<span class="badge badge-primary">liferay-ui:user-portrait</span>
</div>

<h3>liferay-frontend:add-menu</h3>

<p>On bottom right-hand corner.</p>

<liferay-frontend:add-menu>
	<liferay-frontend:add-menu-item title='Add Menu Item' url="#" />
</liferay-frontend:add-menu>

<br />

<h3>liferay-frontend:horizontal-card</h3>

<liferay-frontend:horizontal-card
	icon="icon-folder-close-alt"
	imageCSSClass="icon-monospaced"
	text="Just testing some text here"
	url="#"
/>

<br />

<h3>liferay-frontend:icon-vertical-card with icon</h3>

<liferay-frontend:icon-vertical-card
	icon="users"
	subtitle="Subtitle Test"
	title="Title Test"
>
	<liferay-frontend:vertical-card-footer>
		Vertical card footer content. Vertical card footer content. Vertical card footer content. Vertical card footer content. Vertical card footer content. Vertical card footer content. Vertical card footer content.
	</liferay-frontend:vertical-card-footer>
</liferay-frontend:icon-vertical-card>

<br />

<h3>liferay-frontend:icon-vertical-card with user id</h3>

<liferay-frontend:user-vertical-card
	subtitle="Subtitle Test"
	title="Title Test"
	userId="<%= user.getUserId() %>"
>
	<liferay-frontend:vertical-card-footer>
		Testing some content for the vertical card footer.
	</liferay-frontend:vertical-card-footer>
</liferay-frontend:user-vertical-card>

<br />

<h3>frontend:vertical-card</h3>

<liferay-frontend:vertical-card
	cssClass="entry-display-style"
	imageUrl='/image/company_logo'
	title="Title"
	url="#"
>
	<liferay-frontend:vertical-card-sticker-bottom>
		<liferay-ui:user-portrait
			userId="<%= user.getUserId() %>"
		/>
	</liferay-frontend:vertical-card-sticker-bottom>

	<liferay-frontend:vertical-card-footer>
		<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="1" />
	</liferay-frontend:vertical-card-footer>

	<liferay-frontend:vertical-card-header>
		Testing header message.
	</liferay-frontend:vertical-card-header>
</liferay-frontend:vertical-card>

<br />

<h3>frontend:vertical-card-small-image</h3>

<br/>

<br/>

<div style="position: relative;">
	<liferay-frontend:vertical-card-small-image src="/image/company_logo" />
</div>

<br/>

<h3>liferay-frontend:management-bar</h3>

<liferay-frontend:management-bar
	searchContainerId="layoutSetPrototypeSearchContainer"
	includeCheckBox="<%= true %>"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all", "active", "inactive"} %>'
			portletURL="<%= renderResponse.createRenderURL() %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="Date"
			orderByType="Date"
			orderColumns='<%= new String[] {"create-date"} %>'
			portletURL="<%= renderResponse.createRenderURL() %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= portletURL %>"
			selectedDisplayStyle="List"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-action-buttons>
		<liferay-frontend:management-bar-button href="javascript:;" iconCssClass="icon-trash" id="managementBarButtonTest" label="Label Test" />
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>
