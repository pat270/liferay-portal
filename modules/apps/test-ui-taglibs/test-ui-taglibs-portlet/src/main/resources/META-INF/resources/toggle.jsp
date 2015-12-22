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
	Bacon venison meatball, biltong pork capicola jerky fatback leberkas salami alcatra meatloaf. Tongue ham landjaeger sirloin. Turkey jowl flank andouille meatball frankfurter. Rump tail pork loin, pork shoulder venison bresaola leberkas salami. Pastrami strip steak pork loin filet mignon sirloin pork chop ball tip flank t-bone.
</div>

<br />

<h3>liferay-ui:toggle with hide and show messages</h3>

<liferay-ui:toggle hideMessage="Hide Message" id="testContent2" showMessage="Show Message" />

<div id="testContent2">
	Bacon venison meatball, biltong pork capicola jerky fatback leberkas salami alcatra meatloaf. Tongue ham landjaeger sirloin. Turkey jowl flank andouille meatball frankfurter. Rump tail pork loin, pork shoulder venison bresaola leberkas salami. Pastrami strip steak pork loin filet mignon sirloin pork chop ball tip flank t-bone.
</div>

<br />

<h3>liferay-ui:toggle-area default</h3>

<liferay-ui:toggle-area id="testContent3">
	<div id="testContent3">
		Bacon venison meatball, biltong pork capicola jerky fatback leberkas salami alcatra meatloaf. Tongue ham landjaeger sirloin. Turkey jowl flank andouille meatball frankfurter. Rump tail pork loin, pork shoulder venison bresaola leberkas salami. Pastrami strip steak pork loin filet mignon sirloin pork chop ball tip flank t-bone tongue.
	</div>
</liferay-ui:toggle-area>

<br />

<h3>liferay-ui:toggle-area with hide and show messages</h3>

<liferay-ui:toggle-area hideMessage="Hide Message" id="testContent4" showMessage="Show Message">
	<div id="testContent4">
		Bacon venison meatball, biltong pork capicola jerky fatback leberkas salami alcatra meatloaf. Tongue ham landjaeger sirloin. Turkey jowl flank andouille meatball frankfurter. Rump tail pork loin, pork shoulder venison bresaola leberkas salami. Pastrami strip steak pork loin filet mignon sirloin pork chop ball tip flank t-bone tongue.
	</div>
</liferay-ui:toggle-area>
