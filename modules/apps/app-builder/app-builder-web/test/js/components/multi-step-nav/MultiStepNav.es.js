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

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import MultiStepNav from '../../../../src/main/resources/META-INF/resources/js/components/multi-step-nav/MultiStepNav.es';

describe('MultiStepNav', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = render(
			<MultiStepNav currentStep={3} steps={['1', '2', '3', '4', '5']} />
		);

		const steps = container.querySelectorAll('.multi-step-item');

		expect(steps.length).toBe(5);

		expect(steps[0].classList).toContain('complete');
		expect(steps[1].classList).toContain('complete');
		expect(steps[2].classList).toContain('complete');
		expect(steps[3].classList).not.toContain('complete');
		expect(steps[4].classList).not.toContain('complete');
		expect(steps[3].textContent).toBe('4');
		expect(steps[4].textContent).toBe('5');
	});
});
