/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	CheckedRoleActions,
	RoleSelectedActions,
} from '../default_permission/DefaultPermissionTypes';

export default function toRoleSelectedActions(
	checkedRoleActions: CheckedRoleActions
): RoleSelectedActions {
	const roleSelectedActions: RoleSelectedActions = {};

	for (const [key, checked] of Object.entries(checkedRoleActions)) {
		if (!checked) {
			continue;
		}

		const index = key.lastIndexOf('#');

		const roleName = key.slice(0, index);

		roleSelectedActions[roleName] = [
			...(roleSelectedActions[roleName] || []),
			key.slice(index + 1),
		];
	}

	return roleSelectedActions;
}
