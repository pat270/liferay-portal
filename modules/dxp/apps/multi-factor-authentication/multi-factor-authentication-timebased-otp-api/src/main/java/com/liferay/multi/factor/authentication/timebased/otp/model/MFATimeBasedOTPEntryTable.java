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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;MFATimeBasedOTPEntry&quot; database table.
 *
 * @author Arthur Chan
 * @see MFATimeBasedOTPEntry
 * @generated
 */
public class MFATimeBasedOTPEntryTable
	extends BaseTable<MFATimeBasedOTPEntryTable> {

	public static final MFATimeBasedOTPEntryTable INSTANCE =
		new MFATimeBasedOTPEntryTable();

	public final Column<MFATimeBasedOTPEntryTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<MFATimeBasedOTPEntryTable, Long>
		mfaTimeBasedOTPEntryId = createColumn(
			"mfaTimeBasedOTPEntryId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<MFATimeBasedOTPEntryTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MFATimeBasedOTPEntryTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MFATimeBasedOTPEntryTable, String> userName =
		createColumn(
			"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MFATimeBasedOTPEntryTable, Date> createDate =
		createColumn(
			"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MFATimeBasedOTPEntryTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MFATimeBasedOTPEntryTable, Integer> failedAttempts =
		createColumn(
			"failedAttempts", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<MFATimeBasedOTPEntryTable, Date> lastFailDate =
		createColumn(
			"lastFailDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MFATimeBasedOTPEntryTable, String> lastFailIP =
		createColumn(
			"lastFailIP", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MFATimeBasedOTPEntryTable, Date> lastSuccessDate =
		createColumn(
			"lastSuccessDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<MFATimeBasedOTPEntryTable, String> lastSuccessIP =
		createColumn(
			"lastSuccessIP", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MFATimeBasedOTPEntryTable, String> sharedSecret =
		createColumn(
			"sharedSecret", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private MFATimeBasedOTPEntryTable() {
		super("MFATimeBasedOTPEntry", MFATimeBasedOTPEntryTable::new);
	}

}