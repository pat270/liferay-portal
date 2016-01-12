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

	<span class="badge badge-primary">aui:button-row</span>

	<span class="badge badge-primary">aui:button</span>
</div>

<h3>Button Types</h3>

<aui:button-row>
	<aui:button name="defaultButton" value="Default" />

	<aui:button name="primaryButton" primary="<%= true %>" value="Primary" />

	<aui:button name="submitButton" type="submit" value="Submit" />

	<aui:button name="cancelButton" type="cancel" value="Cancel" />

	<aui:button name="resetButton" type="reset" value="Reset"  />
</aui:button-row>

<br />

<h3>Default State</h3>

<aui:button-row>
	<aui:button cssClass="btn-default" name="defaultButton" value="Default" />

	<aui:button cssClass="btn-primary"name="primaryButton" value="Primary" />

	<aui:button cssClass="btn-info" name="infoButton" value="Info" />

	<aui:button cssClass="btn-success" name="successButton" value="Success" />

	<aui:button cssClass="btn-warning" name="warningButton" value="Warning" />

	<aui:button cssClass="btn-danger" name="dangerButton" value="Danger" />

	<aui:button cssClass="btn-link" name="linkButton" value="Link" />
</aui:button-row>

<br />

<h3>Active State</h3>

<aui:button-row>
	<aui:button cssClass="active btn-default" name="activeDefaultButton" value="Default" />

	<aui:button cssClass="active btn-primary" name="activePrimaryButton" value="Primary" />

	<aui:button cssClass="active btn-info" name="activeinfoButton" value="Info" />

	<aui:button cssClass="active btn-success" name="activeSuccessButton" value="Success" />

	<aui:button cssClass="active btn-warning" name="activeWarningButton" value="Warning" />

	<aui:button cssClass="active btn-danger" name="activeDangerButton" value="Danger" />

	<aui:button cssClass="active btn-link" name="activeLinkButton" value="Link" />
</aui:button-row>

<br />

<h3>Disabled State</h3>

<aui:button-row>
	<aui:button disabled="<%= true %>" name="disabledButton" value="Default" />

	<aui:button cssClass="btn-primary" disabled="<%= true %>" name="disabledPrimaryButton" value="Primary" />

	<aui:button cssClass="btn-info" disabled="<%= true %>" name="disabledInfoButton" value="Info" />

	<aui:button cssClass="btn-success" disabled="<%= true %>" name="disabledSuccessButton" value="Success" />

	<aui:button cssClass="btn-warning" disabled="<%= true %>" name="disabledWarningButton" value="Warning" />

	<aui:button cssClass="btn-danger" disabled="<%= true %>" name="disabledDangerButton" value="Danger" />

	<aui:button cssClass="btn-link" disabled="<%= true %>" name="disabledLinkButton" value="Link" />
</aui:button-row>

<br />

<h3>Button Sizes</h3>

<aui:button-row>
	<aui:button cssClass="btn-xs"  name="extraSmallButton" value="Extra Small" />

	<aui:button cssClass="btn-sm" name="smallButton" value="Small" />

	<aui:button name="defaultButton" value="Default" />

	<aui:button cssClass="btn-lg" name="largeButton" value="Large" />
</aui:button-row>

<br />

<h3>Block Level Buttons</h3>

<aui:button-row>
	<aui:button cssClass="btn-block btn-xs" name="extraSmallButton" value="Extra Small Block" />

	<aui:button cssClass="btn-block btn-sm" name="smallButton" value="Small Block" />

	<aui:button cssClass="btn-block" name="defaultButton" value="Default Block" />

	<aui:button cssClass="btn-block btn-lg" name="largeButton" value="Large Block" />
</aui:button-row>