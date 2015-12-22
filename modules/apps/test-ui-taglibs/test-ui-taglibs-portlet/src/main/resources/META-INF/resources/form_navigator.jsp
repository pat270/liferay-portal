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

	<span class="badge badge-primary">liferay-ui:form-navigator</span>
</div>

<c:catch var="catchException">
	<aui:layout>
		<aui:form name="formName">
			<aui:input name="Text1" type="hidden" />

			<aui:input name="Text2" type="hidden" />

			<aui:input name="Text3" type="hidden" />

			<liferay-ui:form-navigator formName="formName" id="FormNavigatorId" />
		</aui:form>
	</aui:layout>
</c:catch>

<c:if test = "${catchException != null}">
	<div class="alert alert-danger">
		${catchException}

		<br>

		<c:forEach items="${catchException.stackTrace}" var="stackTrace">
			<c:out value="${stackTrace}"/><br/>
		</c:forEach>
	</div>
</c:if>
