/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayLoadingIndicator from '@clayui/loading-indicator';
import {openToast} from 'frontend-js-components-web';
import React, {useEffect, useMemo, useState} from 'react';

import CMSDefaultPermissionService from '../../common/services/CMSDefaultPermissionService';
import DefaultPermissionForm from '../default_permission/DefaultPermissionForm';
import {
	Action,
	AssetRoleSelectedActions,
	DefaultAssetTypes,
	Role,
} from '../default_permission/DefaultPermissionTypes';
import toRoleSelectedActions from './toRoleSelectedActions';

const DESCRIPTIONS: Record<DefaultAssetTypes, string> = {
	[DefaultAssetTypes.L_CONTENTS]: Liferay.Language.get(
		'new-contents-created-inside-this-folder-start-with-these-permissions'
	),
	[DefaultAssetTypes.L_FILES]: Liferay.Language.get(
		'new-files-created-inside-this-folder-start-with-these-permissions'
	),
	[DefaultAssetTypes.OBJECT_ENTRY_FOLDERS]: Liferay.Language.get(
		'new-subfolders-created-inside-this-folder-start-with-these-permissions'
	),
};

interface FolderDefaultsPermissionsProps {
	actions: Action[];
	assetType: DefaultAssetTypes;
	classExternalReferenceCode: string;
	className: string;
	onChange: (values: AssetRoleSelectedActions) => void;
	roles: Role[];
	values: AssetRoleSelectedActions | null;
}

export default function FolderDefaultsPermissions({
	actions,
	assetType,
	classExternalReferenceCode,
	className,
	onChange,
	roles,
	values,
}: FolderDefaultsPermissionsProps) {
	const [loadFailed, setLoadFailed] = useState(false);

	const defaultsRoles = useMemo(
		() => roles.map(({actions: _actions, ...role}) => role),
		[roles]
	);

	useEffect(() => {
		if (values) {
			return;
		}

		CMSDefaultPermissionService.getObjectEntry({
			classExternalReferenceCode,
			className,
		})
			.then(({defaultPermissions}) =>
				onChange(JSON.parse(defaultPermissions))
			)
			.catch(() => {
				openToast({
					message: Liferay.Language.get(
						'an-unexpected-system-error-occurred'
					),
					type: 'danger',
				});

				setLoadFailed(true);
			});
	}, [classExternalReferenceCode, className, onChange, values]);

	if (loadFailed) {
		return null;
	}

	if (!values) {
		return <ClayLoadingIndicator />;
	}

	return (
		<>
			<p className="text-secondary">{DESCRIPTIONS[assetType]}</p>

			<DefaultPermissionForm
				actions={actions}
				key={assetType}
				onChange={(checkedRoleActions) =>
					onChange({
						...values,
						[assetType]: toRoleSelectedActions(checkedRoleActions),
					})
				}
				roles={defaultsRoles}
				values={values[assetType]}
			/>
		</>
	);
}
