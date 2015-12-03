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

	<aui:button name="primaryButton" value="Primary" primary="true" />

	<aui:button name="submitButton" value="Submit" type="submit" />

	<aui:button name="cancelButton" value="Cancel" type="cancel" />

	<aui:button name="resetButton" value="Reset" type="reset" />
</aui:button-row>

<br />

<h3>Default State</h3>

<aui:button-row>
	<aui:button name="defaultButton" value="Default" cssClass="btn-default" />

	<aui:button name="primaryButton" value="Primary" cssClass="btn-primary" />

	<aui:button name="infoButton" value="Info" cssClass="btn-info" />

	<aui:button name="successButton" value="Success" cssClass="btn-success" />

	<aui:button name="warningButton" value="Warning" cssClass="btn-warning" />

	<aui:button name="dangerButton" value="Danger" cssClass="btn-danger" />

	<aui:button name="linkButton" value="Link" cssClass="btn-link" />
</aui:button-row>

<br />

<h3>Active State</h3>

<aui:button-row>
	<aui:button name="activeDefaultButton" value="Default" cssClass="btn-default active" />

	<aui:button name="activePrimaryButton" value="Primary" cssClass="btn-primary active" />

	<aui:button name="activeinfoButton" value="Info" cssClass="btn-info active" />

	<aui:button name="activeSuccessButton" value="Success" cssClass="btn-success active" />

	<aui:button name="activeWarningButton" value="Warning" cssClass="btn-warning active" />

	<aui:button name="activeDangerButton" value="Danger" cssClass="btn-danger active" />

	<aui:button name="activeLinkButton" value="Link" cssClass="btn-link active" />
</aui:button-row>

<br />

<h3>Disabled State</h3>

<aui:button-row>
	<aui:button name="disabledButton" value="Default" disabled="true" />

	<aui:button name="disabledPrimaryButton" value="Primary" cssClass="btn-primary" disabled="true" />

	<aui:button name="disabledInfoButton" value="Info" cssClass="btn-info" disabled="true" />

	<aui:button name="disabledSuccessButton" value="Success" cssClass="btn-success" disabled="true" />

	<aui:button name="disabledWarningButton" value="Warning" cssClass="btn-warning" disabled="true" />

	<aui:button name="disabledDangerButton" value="Danger" cssClass="btn-danger" disabled="true" />

	<aui:button name="disabledLinkButton" value="Link" cssClass="btn-link" disabled="true" />
</aui:button-row>

<br />

<h3>Button Sizes</h3>

<aui:button-row>
	<aui:button name="extraSmallButton" value="Extra Small" cssClass="btn-xs" />

	<aui:button name="smallButton" value="Small" cssClass="btn-sm" />

	<aui:button name="defaultButton" value="Default" />

	<aui:button name="largeButton" value="Large" cssClass="btn-lg" />
</aui:button-row>

<br />

<h3>Block Level Buttons</h3>

<aui:button-row>
	<aui:button cssClass="btn-block btn-xs" name="extraSmallButton" value="Extra Small Block" />

	<aui:button cssClass="btn-block btn-sm" name="smallButton" value="Small Block" />

	<aui:button cssClass="btn-block" name="defaultButton" value="Default Block" />

	<aui:button cssClass="btn-block btn-lg" name="largeButton" value="Large Block" />
</aui:button-row>
