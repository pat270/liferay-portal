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

package com.liferay.dispatch.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DispatchTriggerService}.
 *
 * @author Alessio Antonio Rendina
 * @see DispatchTriggerService
 * @generated
 */
public class DispatchTriggerServiceWrapper
	implements DispatchTriggerService, ServiceWrapper<DispatchTriggerService> {

	public DispatchTriggerServiceWrapper(
		DispatchTriggerService dispatchTriggerService) {

		_dispatchTriggerService = dispatchTriggerService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _dispatchTriggerService.getOSGiServiceIdentifier();
	}

	@Override
	public DispatchTriggerService getWrappedService() {
		return _dispatchTriggerService;
	}

	@Override
	public void setWrappedService(
		DispatchTriggerService dispatchTriggerService) {

		_dispatchTriggerService = dispatchTriggerService;
	}

	private DispatchTriggerService _dispatchTriggerService;

}