/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class SerializationException extends PortalException {

	public SerializationException() {
	}

	public SerializationException(String msg) {
		super(msg);
	}

	public SerializationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public SerializationException(Throwable throwable) {
		super(throwable);
	}

}