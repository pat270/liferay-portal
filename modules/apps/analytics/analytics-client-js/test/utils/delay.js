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

import {LIMIT_FAILED_ATTEMPTS} from '../../src/utils/constants';
import {getRetryDelay} from '../../src/utils/delay';

describe('getRetryDelay', () => {
	it('returns a delay as a function of attempt number with a maximum delay', () => {
		expect(getRetryDelay(1, LIMIT_FAILED_ATTEMPTS)).toEqual(1000);
		expect(getRetryDelay(2, LIMIT_FAILED_ATTEMPTS)).toEqual(2000);
		expect(getRetryDelay(3, LIMIT_FAILED_ATTEMPTS)).toEqual(3000);
		expect(getRetryDelay(4, LIMIT_FAILED_ATTEMPTS)).toEqual(5000);
		expect(getRetryDelay(5, LIMIT_FAILED_ATTEMPTS)).toEqual(8000);
		expect(getRetryDelay(6, LIMIT_FAILED_ATTEMPTS)).toEqual(13000);
		expect(getRetryDelay(7, LIMIT_FAILED_ATTEMPTS)).toEqual(21000);
		expect(getRetryDelay(30, LIMIT_FAILED_ATTEMPTS)).toEqual(21000);
	});
});
