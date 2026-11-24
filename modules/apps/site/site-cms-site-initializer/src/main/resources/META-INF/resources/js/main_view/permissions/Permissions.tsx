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
import {
	Action,
	ActionsMap,
	AssetRoleSelectedActions,
	DefaultAssetTypes,
	Role,
	RoleSelectedActions,
} from '../default_permission/DefaultPermissionTypes';
import FolderDefaultsPermissions from './FolderDefaultsPermissions';
import ThisFolderPermissions from './ThisFolderPermissions';

type DefaultsSection = 'contents' | 'files' | 'subfolders';

interface PermissionsProps {
	actions: Action[];
	backURL: string;
	classExternalReferenceCode: string | null;
	className: string;
	classPK: number;
	defaultsActions: ActionsMap;
	defaultsSections: DefaultsSection[];
	roles: Role[];
}

const DEFAULTS_SECTION_ASSET_TYPES: Record<DefaultsSection, DefaultAssetTypes> =
	{
		contents: DefaultAssetTypes.L_CONTENTS,
		files: DefaultAssetTypes.L_FILES,
		subfolders: DefaultAssetTypes.OBJECT_ENTRY_FOLDERS,
	};

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
	actions,
	backURL,
	classExternalReferenceCode,
	className,
	classPK,
	defaultsActions,
	defaultsSections,
	roles,
}: PermissionsProps) {
	const [activeSection, setActiveSection] = useState<string>(THIS_FOLDER);
	const [defaultsValues, setDefaultsValues] =
		useState<AssetRoleSelectedActions | null>(null);

	const assetType =
		DEFAULTS_SECTION_ASSET_TYPES[activeSection as DefaultsSection];
	const [thisFolderValues, setThisFolderValues] =
		useState<RoleSelectedActions | null>(null);

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
					<ClayLayout.ContentRow className="min-vh-100">
						<ClayLayout.ContentCol
							className="cms-sidebar-nav sidebar-layout"
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
						</ClayLayout.ContentCol>

						<ClayLayout.ContentCol expand>
							<h2 className="text-5">
								{_getSectionLabel(activeSection)}
							</h2>

							{activeSection === THIS_FOLDER ? (
								<ThisFolderPermissions
									actions={actions}
									classPK={classPK}
									onChange={setThisFolderValues}
									roles={roles}
									values={thisFolderValues}
								/>
							) : (
								<FolderDefaultsPermissions
									actions={defaultsActions[assetType]}
									assetType={assetType}
									classExternalReferenceCode={
										classExternalReferenceCode as string
									}
									className={className}
									onChange={setDefaultsValues}
									roles={roles}
									values={defaultsValues}
								/>
							)}
						</ClayLayout.ContentCol>
					</ClayLayout.ContentRow>
				) : (
					<div className="min-vh-100 py-4" />
				)}
			</ClayLayout.ContainerFluid>
		</>
	);
}
