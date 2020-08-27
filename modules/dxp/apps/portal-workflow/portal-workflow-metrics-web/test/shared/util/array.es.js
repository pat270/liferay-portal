/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {compareArrays} from '../../../src/main/resources/META-INF/resources/js/shared/util/array.es';

const array = [1, 2, 3];
test('Compare the same array', () => {
	const result = compareArrays(array, array);

	expect(result).toBeTruthy();
});

test('Compare differents arrays', () => {
	const result = compareArrays(array, [3, 2, 1]);

	expect(result).toBeFalsy();
});

test('Compare an empty array and a valid array', () => {
	const result = compareArrays(array, []);

	expect(result).toBeFalsy();
});
