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

	<span class="badge badge-primary">liferay-ui:panel</span>

	<span class="badge badge-primary">liferay-ui:panel-container</span>
</div>

<liferay-ui:panel title="Default Panel">
	Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
</liferay-ui:panel>

<liferay-ui:panel collapsible="<%= false %>" title="Uncollapsible Panel">
	Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
</liferay-ui:panel>

<br />

<h3>Panel States</h3>

<liferay-ui:panel-container>
	<liferay-ui:panel title="Panel">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-blank" title="Blank Panel">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-default" title="Default Panel">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-primary" title="Primary Panel">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-success" title="Success Panel">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-warning" title="Warning Panel">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-danger" title="Danger Panel">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>
</liferay-ui:panel-container>

<br />

<h3>Accordion Panel Group</h3>

<liferay-ui:panel-container accordion="<%= true %>">
	<liferay-ui:panel state="open" title="Panel #1">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>

	<liferay-ui:panel state="close" title="Panel #2">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>

	<liferay-ui:panel state="close" title="Panel #3">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>
</liferay-ui:panel-container>

<br />

<h3>Lexicon View</h3>

<liferay-ui:panel markupView="lexicon" title="Default Panel">
	Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
</liferay-ui:panel>

<liferay-ui:panel collapsible="<%= false %>" markupView="lexicon" title="Uncollapsible Panel">
	Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
</liferay-ui:panel>

<liferay-ui:panel-container markupView="lexicon">
	<liferay-ui:panel markupView="lexicon" title="Panel">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-primary" markupView="lexicon" title="Primary Panel">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>
</liferay-ui:panel-container>

<liferay-ui:panel-container accordion="<%= true %>" markupView="lexicon">
	<liferay-ui:panel markupView="lexicon" state="open" title="Panel #1">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>

	<liferay-ui:panel markupView="lexicon" state="close" title="Panel #2">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>

	<liferay-ui:panel markupView="lexicon" state="close" title="Panel #3">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</liferay-ui:panel>
</liferay-ui:panel-container>