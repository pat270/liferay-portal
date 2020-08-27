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

package com.liferay.external.reference.service.impl;

import com.liferay.account.model.AccountEntry;
import com.liferay.external.reference.service.base.ERAccountEntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;

/**
 * @author Drew Brokke
 */
@Component(
	property = "model.class.name=com.liferay.external.reference.model.ERAccountEntry",
	service = AopService.class
)
public class ERAccountEntryLocalServiceImpl
	extends ERAccountEntryLocalServiceBaseImpl {

	@Override
	public AccountEntry addOrUpdateAccountEntry(
			String externalReferenceCode, long userId,
			long parentAccountEntryId, String name, String description,
			boolean deleteLogo, String[] domains, byte[] logoBytes,
			String taxId, String type, int status,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		AccountEntry accountEntry =
			accountEntryLocalService.fetchAccountEntryByReferenceCode(
				user.getCompanyId(), externalReferenceCode);

		if (accountEntry == null) {
			accountEntry = accountEntryLocalService.addAccountEntry(
				userId, parentAccountEntryId, name, description, domains,
				logoBytes, taxId, type, status, serviceContext);

			accountEntry.setExternalReferenceCode(externalReferenceCode);

			accountEntry = accountEntryLocalService.updateAccountEntry(
				accountEntry);
		}
		else {
			accountEntry = accountEntryLocalService.updateAccountEntry(
				accountEntry.getAccountEntryId(), parentAccountEntryId, name,
				description, deleteLogo, domains, logoBytes, taxId, status,
				serviceContext);
		}

		return accountEntry;
	}

}