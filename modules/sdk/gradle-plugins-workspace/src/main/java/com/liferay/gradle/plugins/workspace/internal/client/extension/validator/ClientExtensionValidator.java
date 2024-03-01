/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.gradle.plugins.workspace.internal.client.extension.validator;

import com.liferay.gradle.plugins.workspace.internal.client.extension.ClientExtension;
import com.liferay.gradle.plugins.workspace.internal.util.StringUtil;
import com.liferay.gradle.util.ArrayUtil;
import com.liferay.petra.string.StringPool;

import groovy.json.JsonException;
import groovy.json.JsonSlurper;

import java.io.File;

import java.util.Map;
import java.util.Objects;

import org.gradle.api.GradleException;
import org.gradle.api.Project;

/**
 * @author Anderson Luiz
 */
public class ClientExtensionValidator {

	public void validate(ClientExtension clientExtension, Project project) {
		if (Objects.equals(clientExtension.type, "batch")) {
			_validateRequiredDirectory(clientExtension, project, "batch");
			_validateRequiredTypeSettingsKeys(
				clientExtension, "oAuthApplicationHeadlessServer");
		}

		if (Objects.equals(clientExtension.type, "instanceSettings")) {
			_validateRequiredTypeSettingsKeys(clientExtension, "pid");
		}

		if (Objects.equals(clientExtension.type, "siteInitializer")) {
			_validateRequiredDirectory(
				clientExtension, project, "site-initializer");
			_validateRequiredTypeSettingsKeys(
				clientExtension, "oAuthApplicationHeadlessServer",
				"siteExternalReferenceCode", "siteName");
			_validateTypeSettingsValues(
				clientExtension, "builtInTemplateType", "site-initializer",
				"site-template");
			_validateTypeSettingsValues(
				clientExtension, "membershipType", "open", "private",
				"restricted");
		}

		if (Objects.equals(clientExtension.type, "themeCSS")) {
			_validateFrontendTokenDefinitionFile(clientExtension, project);
		}
	}

	private void _validateFrontendTokenDefinitionFile(
		ClientExtension clientExtension, Project project) {

		String frontendTokenDefinitionFilePath = null;

		try {
			Map<String, Object> typeSettings = clientExtension.typeSettings;

			if (typeSettings.containsKey(_FRONTEND_TOKEN_DEFINITION_JSON_KEY)) {
				frontendTokenDefinitionFilePath = String.valueOf(
					typeSettings.get(_FRONTEND_TOKEN_DEFINITION_JSON_KEY));

				File file = project.file(frontendTokenDefinitionFilePath);

				if (!file.exists() || !file.isFile()) {
					throw new GradleException(
						String.format(
							"Unable to find file %s",
							StringUtil.quote(frontendTokenDefinitionFilePath)));
				}

				JsonSlurper jsonSlurper = new JsonSlurper();

				jsonSlurper.parse(file);
			}
		}
		catch (JsonException jsonException) {
			throw new GradleException(
				String.format(
					"Unable to parse file %s",
					StringUtil.quote(frontendTokenDefinitionFilePath)));
		}
	}

	private void _validateRequiredDirectory(
			ClientExtension clientExtension, Project project,
			String requiredDirectoryName)
		throws GradleException {

		File file = project.file(requiredDirectoryName);

		if (file.isDirectory()) {
			return;
		}

		throw new GradleException(
			String.format(
				"A %s directory is required for client extension %s with " +
					"type %s",
				StringUtil.quote(requiredDirectoryName), clientExtension.id,
				clientExtension.type));
	}

	private void _validateRequiredTypeSettingsKeys(
			ClientExtension clientExtension, String... requiredTypeSettingsKeys)
		throws GradleException {

		for (String requiredTypeSettingsKey : requiredTypeSettingsKeys) {
			if (clientExtension.typeSettings.containsKey(
					requiredTypeSettingsKey)) {

				continue;
			}

			throw new GradleException(
				String.format(
					"Client extension %s with type %s must define the " +
						"property %s",
					clientExtension.id, clientExtension.type,
					StringUtil.quote(requiredTypeSettingsKey)));
		}
	}

	private void _validateTypeSettingsValues(
			ClientExtension clientExtension, String typeSettingsKey,
			String... validValues)
		throws GradleException {

		Object typeSettingsValue = clientExtension.typeSettings.get(
			typeSettingsKey);

		if ((typeSettingsValue == null) ||
			ArrayUtil.contains(validValues, typeSettingsValue)) {

			return;
		}

		throw new GradleException(
			String.format(
				"Client extension %s has an invalid value %s for the " +
					"property %s. Valid values are: %s.",
				clientExtension.id, StringUtil.quote(typeSettingsValue),
				StringUtil.quote(typeSettingsKey),
				com.liferay.petra.string.StringUtil.merge(
					validValues, StringPool.COMMA_AND_SPACE)));
	}

	private static final String _FRONTEND_TOKEN_DEFINITION_JSON_KEY =
		"frontendTokenDefinitionJSON";

}