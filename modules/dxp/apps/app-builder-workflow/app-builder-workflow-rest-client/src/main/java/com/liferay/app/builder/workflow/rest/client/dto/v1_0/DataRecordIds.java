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

package com.liferay.app.builder.workflow.rest.client.dto.v1_0;

import com.liferay.app.builder.workflow.rest.client.function.UnsafeSupplier;
import com.liferay.app.builder.workflow.rest.client.serdes.v1_0.DataRecordIdsSerDes;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
public class DataRecordIds implements Cloneable {

	public static DataRecordIds toDTO(String json) {
		return DataRecordIdsSerDes.toDTO(json);
	}

	public Long[] getDataRecordIds() {
		return dataRecordIds;
	}

	public void setDataRecordIds(Long[] dataRecordIds) {
		this.dataRecordIds = dataRecordIds;
	}

	public void setDataRecordIds(
		UnsafeSupplier<Long[], Exception> dataRecordIdsUnsafeSupplier) {

		try {
			dataRecordIds = dataRecordIdsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long[] dataRecordIds;

	@Override
	public DataRecordIds clone() throws CloneNotSupportedException {
		return (DataRecordIds)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DataRecordIds)) {
			return false;
		}

		DataRecordIds dataRecordIds = (DataRecordIds)object;

		return Objects.equals(toString(), dataRecordIds.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return DataRecordIdsSerDes.toJSON(this);
	}

}