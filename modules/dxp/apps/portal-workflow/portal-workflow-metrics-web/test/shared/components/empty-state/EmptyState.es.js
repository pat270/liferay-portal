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

import '@testing-library/jest-dom/extend-expect';
import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import EmptyState from '../../../../src/main/resources/META-INF/resources/js/shared/components/empty-state/EmptyState.es';

describe('The EmptyState component should', () => {
	afterEach(cleanup);

	test('Be render with message only', () => {
		const {getByTestId} = render(<EmptyState filtered hideAnimation />);

		const emptyState = getByTestId('emptyState');
		const message = getByTestId('emptyStateMsg');

		expect(emptyState.children.length).toEqual(1);
		expect(message).toHaveTextContent('no-results-were-found');
	});

	test('Be render with message and animation', () => {
		const {getByTestId} = render(<EmptyState filtered />);

		const animation = getByTestId('emptyStateAnimation');
		const emptyState = getByTestId('emptyState');
		const message = getByTestId('emptyStateMsg');

		expect(animation.className).toBe(
			'taglib-empty-search-result-message-header'
		);
		expect(emptyState.children.length).toEqual(2);
		expect(message).toHaveTextContent('no-results-were-found');
	});

	test('Be render with message, title and animation', () => {
		const {getByTestId} = render(<EmptyState title="No data" />);

		const animation = getByTestId('emptyStateAnimation');
		const emptyState = getByTestId('emptyState');
		const message = getByTestId('emptyStateMsg');
		const title = getByTestId('emptyStateTitle');

		expect(animation.className).toBe('taglib-empty-result-message-header');
		expect(emptyState.children.length).toEqual(3);
		expect(message).toHaveTextContent('there-is-no-data-at-the-moment');
		expect(title).toHaveTextContent('No data');
	});

	test('Be render with message and action button', () => {
		const mockClick = jest.fn();

		const {getByTestId} = render(
			<EmptyState
				actionButton={<button onClick={mockClick}>Reload</button>}
				hideAnimation
				message="Failed to retrieve data, click on 'Reload' to retrying."
			/>
		);

		const emptyState = getByTestId('emptyState');
		const message = getByTestId('emptyStateMsg');
		const button = emptyState.children[0].children[1];

		expect(message).toHaveTextContent(
			"Failed to retrieve data, click on 'Reload' to retrying."
		);
		expect(button).toHaveTextContent('Reload');

		fireEvent.click(button);

		expect(mockClick).toHaveBeenCalled();
	});
});
