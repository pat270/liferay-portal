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

package com.liferay.multi.factor.authentication.email.otp.web.internal.constants;

/**
 * @author Arthur Chan
 */
public interface MFAEmailOTPEventTypes {

	public static final String MFA_EMAIL_OTP_NOT_VERIFIED =
		"MFA_EMAIL_OTP_NOT_VERIFIED";

	public static final String MFA_EMAIL_OTP_VERIFICATION_FAILURE =
		"MFA_EMAIL_OTP_VERIFICATION_FAILURE";

	public static final String MFA_EMAIL_OTP_VERIFICATION_SUCCESS =
		"MFA_EMAIL_OTP_VERIFICATION_SUCCESS";

	public static final String MFA_EMAIL_OTP_VERIFIED =
		"MFA_EMAIL_OTP_VERIFIED";

}