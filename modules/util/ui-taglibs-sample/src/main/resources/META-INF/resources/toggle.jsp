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

	<span class="badge badge-primary">liferay-ui:toggle</span>

	<span class="badge badge-primary">liferay-ui:toggle-area</span>
</div>

<h3>liferay-ui:toggle default</h3>

<liferay-ui:toggle id="testContent" />

<div id="testContent">
	Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
</div>

<br />

<h3>liferay-ui:toggle with hide and show messages</h3>

<liferay-ui:toggle hideMessage="Hide Message" id="testContent2" showMessage="Show Message" />

<div id="testContent2">
	Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
</div>

<br />

<h3>liferay-ui:toggle-area default</h3>

<liferay-ui:toggle-area id="testContent3">
	<div id="testContent3">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</div>
</liferay-ui:toggle-area>

<br />

<h3>liferay-ui:toggle-area with hide and show messages</h3>

<liferay-ui:toggle-area hideMessage="Hide Message" id="testContent4" showMessage="Show Message">
	<div id="testContent4">
		Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. Liferay Portal started out as a personal development project in 2000 and was open sourced in 2001.
	</div>
</liferay-ui:toggle-area>