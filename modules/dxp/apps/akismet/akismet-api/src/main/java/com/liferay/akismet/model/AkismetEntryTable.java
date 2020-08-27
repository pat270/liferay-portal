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

package com.liferay.akismet.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;OSBCommunity_AkismetEntry&quot; database table.
 *
 * @author Jamie Sammons
 * @see AkismetEntry
 * @generated
 */
public class AkismetEntryTable extends BaseTable<AkismetEntryTable> {

	public static final AkismetEntryTable INSTANCE = new AkismetEntryTable();

	public final Column<AkismetEntryTable, Long> akismetEntryId = createColumn(
		"akismetEntryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AkismetEntryTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<AkismetEntryTable, Long> classNameId = createColumn(
		"classNameId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AkismetEntryTable, Long> classPK = createColumn(
		"classPK", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AkismetEntryTable, String> type = createColumn(
		"type_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AkismetEntryTable, String> permalink = createColumn(
		"permalink", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AkismetEntryTable, String> referrer = createColumn(
		"referrer", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AkismetEntryTable, String> userAgent = createColumn(
		"userAgent", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AkismetEntryTable, String> userIP = createColumn(
		"userIP", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AkismetEntryTable, String> userURL = createColumn(
		"userURL", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private AkismetEntryTable() {
		super("OSBCommunity_AkismetEntry", AkismetEntryTable::new);
	}

}