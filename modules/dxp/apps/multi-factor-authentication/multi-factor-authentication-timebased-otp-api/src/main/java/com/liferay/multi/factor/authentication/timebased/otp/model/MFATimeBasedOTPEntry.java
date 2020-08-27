/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.multi.factor.authentication.timebased.otp.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the MFATimeBasedOTPEntry service. Represents a row in the &quot;MFATimeBasedOTPEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Arthur Chan
 * @see MFATimeBasedOTPEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.multi.factor.authentication.timebased.otp.model.impl.MFATimeBasedOTPEntryImpl"
)
@ProviderType
public interface MFATimeBasedOTPEntry
	extends MFATimeBasedOTPEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.multi.factor.authentication.timebased.otp.model.impl.MFATimeBasedOTPEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MFATimeBasedOTPEntry, Long>
		MFA_TIME_BASED_OTP_ENTRY_ID_ACCESSOR =
			new Accessor<MFATimeBasedOTPEntry, Long>() {

				@Override
				public Long get(MFATimeBasedOTPEntry mfaTimeBasedOTPEntry) {
					return mfaTimeBasedOTPEntry.getMfaTimeBasedOTPEntryId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<MFATimeBasedOTPEntry> getTypeClass() {
					return MFATimeBasedOTPEntry.class;
				}

			};

}