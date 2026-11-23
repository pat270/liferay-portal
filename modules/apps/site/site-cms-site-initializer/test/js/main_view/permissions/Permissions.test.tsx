/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '@testing-library/jest-dom';
import {render, screen} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import {navigate} from 'frontend-js-web';
import React from 'react';

import Permissions from '../../../../src/main/resources/META-INF/resources/js/main_view/permissions/Permissions';

const BACK_URL = '/cms/contents';

describe('Permissions', () => {
	it('shows the folder sections in the sidebar', async () => {
		render(
			<Permissions
				backURL={BACK_URL}
				className="com.liferay.object.model.ObjectEntryFolder"
				classPK={1}
				defaultsSections={['subfolders', 'contents']}
			/>
		);

		expect(
			screen.getByRole('heading', {name: 'permissions'})
		).toBeInTheDocument();
		expect(
			screen.getByRole('heading', {name: 'this-folder'})
		).toBeInTheDocument();
		expect(screen.getByText('subfolders')).toBeInTheDocument();
		expect(screen.queryByText('files')).not.toBeInTheDocument();

		await userEvent.click(screen.getByText('contents'));

		expect(
			screen.getByRole('heading', {name: 'contents'})
		).toBeInTheDocument();
	});

	it('shows the files section for a folder that holds files', () => {
		render(
			<Permissions
				backURL={BACK_URL}
				className="com.liferay.object.model.ObjectEntryFolder"
				classPK={1}
				defaultsSections={['subfolders', 'files']}
			/>
		);

		expect(screen.getByText('files')).toBeInTheDocument();
		expect(screen.queryByText('contents')).not.toBeInTheDocument();
	});

	it('does not show a sidebar for an item', () => {
		render(
			<Permissions
				backURL={BACK_URL}
				className="com.liferay.object.model.ObjectDefinition#Z7P5"
				classPK={1}
				defaultsSections={[]}
			/>
		);

		expect(
			screen.getByRole('heading', {name: 'permissions'})
		).toBeInTheDocument();
		expect(screen.queryByText('this-folder')).not.toBeInTheDocument();
	});

	it('returns to the previous view on cancel', async () => {
		render(
			<Permissions
				backURL={BACK_URL}
				className="com.liferay.object.model.ObjectEntryFolder"
				classPK={1}
				defaultsSections={['subfolders', 'contents']}
			/>
		);

		await userEvent.click(screen.getByRole('button', {name: 'cancel'}));

		expect(navigate).toHaveBeenCalledWith(BACK_URL);
	});

	it('links the back button to the previous view', () => {
		render(
			<Permissions
				backURL={BACK_URL}
				className="com.liferay.object.model.ObjectEntryFolder"
				classPK={1}
				defaultsSections={['subfolders', 'contents']}
			/>
		);

		expect(screen.getByRole('button', {name: 'back'})).toHaveAttribute(
			'href',
			BACK_URL
		);
	});
});
