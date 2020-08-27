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

package com.liferay.dynamic.data.mapping.spi.form.builder.settings;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.portal.kernel.json.JSONArray;

import java.util.Locale;

/**
 * @author Gabriel Albuquerque
 */
public interface DDMFormBuilderSettingsRetrieverHelper {

	public String getDDMDataProviderInstanceParameterSettingsURL();

	public String getDDMDataProviderInstancesURL();

	public String getDDMFieldSetDefinitionURL();

	public String getDDMFieldSettingsDDMFormContextURL();

	public String getDDMFormContextProviderURL();

	public String getDDMFunctionsURL();

	public JSONArray getFieldSetsMetadataJSONArray(
		long companyId, long scopeGroupId, long fieldSetClassNameId,
		Locale locale);

	public String getRolesURL();

	public String getSerializedDDMExpressionFunctionsMetadata(Locale locale);

	public String getSerializedDDMFormRules(DDMForm ddmForm);

}