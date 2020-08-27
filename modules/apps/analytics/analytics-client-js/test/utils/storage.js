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

import {
	getItem,
	getStorageSizeInKb,
	setItem,
	verifyStorageLimitForKey,
} from '../../src/utils/storage';

const STORAGE_KEY = 'some-key';

describe('Storage Utils', () => {
	beforeEach(() => {
		localStorage.removeItem(STORAGE_KEY);
	});

	describe('getItem', () => {
		it('Retrieves an item from localStorage', () => {
			const expected = {name: 'foo'};

			localStorage.setItem(STORAGE_KEY, JSON.stringify(expected));

			expect(getItem(STORAGE_KEY)).toEqual(expected);
		});
	});

	describe('getStorageSizeInKb', () => {
		it('Calculates the kilobyte size of a string', () => {
			const expected = 0.0234375;
			expect(getStorageSizeInKb('0123456789')).toEqual(expected);
		});
	});

	describe('setItem', () => {
		it('Sets an item in localStorage', () => {
			const expected = {name: 'foo'};

			setItem(STORAGE_KEY, expected);

			expect(JSON.parse(localStorage.getItem(STORAGE_KEY))).toEqual(
				expected
			);
		});
	});

	describe('verifyStorageLimitForKey', () => {
		it('Removes items in a localStorage queue if the storage limit is exceeded', async () => {
			const queue = [{name: 'foo'}, {name: 'bar'}, {name: 'baz'}];
			const mockStorageLimit = 0.05;

			setItem(STORAGE_KEY, queue);

			await verifyStorageLimitForKey(STORAGE_KEY, mockStorageLimit);

			expect(getItem(STORAGE_KEY)).toEqual(
				expect.arrayContaining(queue.slice(1))
			);
		});

		it('Does not change items in a localStorage queue if the storage limit is not exceeded', async () => {
			const queue = [{name: 'foo'}, {name: 'bar'}, {name: 'baz'}];
			const mockStorageLimit = 100;

			setItem(STORAGE_KEY, queue);

			await verifyStorageLimitForKey(STORAGE_KEY, mockStorageLimit);

			expect(getItem(STORAGE_KEY)).toEqual(expect.arrayContaining(queue));
		});
	});
});
