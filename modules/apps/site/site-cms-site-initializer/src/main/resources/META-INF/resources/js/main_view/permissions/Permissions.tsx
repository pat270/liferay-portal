/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayLayout from '@clayui/layout';
import {ClayVerticalNav} from '@clayui/nav';
import {navigate} from 'frontend-js-web';
import React, {useState} from 'react';

import Toolbar from '../../common/components/Toolbar';

type DefaultsSection = 'contents' | 'files' | 'subfolders';

interface PermissionsProps {
	backURL: string;
	className: string;
	classPK: number;
	defaultsSections: DefaultsSection[];
}

const DEFAULTS_SECTION_LABELS: Record<DefaultsSection, string> = {
	contents: Liferay.Language.get('contents'),
	files: Liferay.Language.get('files'),
	subfolders: Liferay.Language.get('subfolders'),
};

const THIS_FOLDER = 'this-folder';

function _getSectionLabel(section: string) {
	if (section === THIS_FOLDER) {
		return Liferay.Language.get('this-folder');
	}

	return DEFAULTS_SECTION_LABELS[section as DefaultsSection];
}

export default function Permissions({
	backURL,
	defaultsSections,
}: PermissionsProps) {
	const [activeSection, setActiveSection] = useState<string>(THIS_FOLDER);

	return (
		<>
			<Toolbar
				backURL={backURL}
				title={Liferay.Language.get('permissions')}
			>
				<Toolbar.Item>
					<ClayButton
						borderless
						displayType="secondary"
						onClick={() => navigate(backURL)}
						size="sm"
					>
						{Liferay.Language.get('cancel')}
					</ClayButton>
				</Toolbar.Item>
			</Toolbar>

			<ClayLayout.ContainerFluid size={false}>
				{defaultsSections.length ? (
					<ClayLayout.Row className="min-vh-100">
						<ClayLayout.Col
							className="cms-sidebar-nav sidebar-layout"
							md="auto"
							sm={12}
						>
							<div className="px-md-2 py-3 py-md-4">
								<ClayVerticalNav
									items={[
										THIS_FOLDER,
										...defaultsSections,
									].map((section) => ({
										active: activeSection === section,
										label: _getSectionLabel(section),
										onClick: () =>
											setActiveSection(section),
									}))}
								/>
							</div>
						</ClayLayout.Col>

						<ClayLayout.Col className="col-md py-4" sm={12}>
							<h2 className="text-5">
								{_getSectionLabel(activeSection)}
							</h2>
						</ClayLayout.Col>
					</ClayLayout.Row>
				) : (
					<div className="min-vh-100 py-4" />
				)}
			</ClayLayout.ContainerFluid>
		</>
	);
}
