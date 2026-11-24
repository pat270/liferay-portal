/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayLoadingIndicator from '@clayui/loading-indicator';
import {openToast} from 'frontend-js-components-web';
import React, {useEffect, useState} from 'react';

import FolderService from '../../common/services/FolderService';
import DefaultPermissionForm from '../default_permission/DefaultPermissionForm';
import {
	Action,
	Role,
	RoleSelectedActions,
} from '../default_permission/DefaultPermissionTypes';
import toRoleSelectedActions from './toRoleSelectedActions';

interface ThisFolderPermissionsProps {
	actions: Action[];
	classPK: number;
	onChange: (values: RoleSelectedActions) => void;
	roles: Role[];
	values: RoleSelectedActions | null;
}

export default function ThisFolderPermissions({
	actions,
	classPK,
	onChange,
	roles,
	values,
}: ThisFolderPermissionsProps) {
	const [loadFailed, setLoadFailed] = useState(false);

	useEffect(() => {
		if (values) {
			return;
		}

		FolderService.getFolderPermissions(classPK).then(({data, error}) => {
			if (error) {
				openToast({message: error, type: 'danger'});

				setLoadFailed(true);

				return;
			}

			const roleSelectedActions: RoleSelectedActions = {};

			for (const {actionIds, roleName} of data?.items || []) {
				roleSelectedActions[roleName] = actionIds;
			}

			onChange(roleSelectedActions);
		});
	}, [classPK, onChange, values]);

	if (loadFailed) {
		return null;
	}

	if (!values) {
		return <ClayLoadingIndicator />;
	}

	return (
		<DefaultPermissionForm
			actions={actions}
			onChange={(checkedRoleActions) =>
				onChange(toRoleSelectedActions(checkedRoleActions))
			}
			roles={roles}
			values={values}
		/>
	);
}
