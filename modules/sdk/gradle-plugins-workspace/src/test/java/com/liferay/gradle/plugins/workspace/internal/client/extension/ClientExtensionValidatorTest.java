/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.gradle.plugins.workspace.internal.client.extension;

import com.liferay.gradle.plugins.workspace.internal.client.extension.validator.ClientExtensionValidator;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import java.util.HashMap;
import java.util.Map;

import org.gradle.api.GradleException;
import org.gradle.api.Project;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Anderson Luiz
 */
public class ClientExtensionValidatorTest {

	@Test
	public void testShouldValidateSuccessfullyIfNoFrontendTokenDefinitionProvided() {
		ClientExtension clientExtension = _getThemeCSSClientExtension();

		Map<String, Object> typeSettings = clientExtension.typeSettings;

		typeSettings.clear();

		Project project = Mockito.mock(Project.class);

		try {
			_clientExtensionValidator.validate(clientExtension, project);
		}
		catch (GradleException gradleException) {
			Assert.fail();
		}
	}

	@Test
	public void testThrowsUnableToFindFrontendTokenDefinitionFileException() {
		Project project = Mockito.mock(Project.class);

		Mockito.when(
			project.file(Mockito.anyString())
		).thenReturn(
			new File("")
		);

		ClientExtension clientExtension = _getThemeCSSClientExtension();

		try {
			_clientExtensionValidator.validate(clientExtension, project);
			Assert.fail();
		}
		catch (GradleException gradleException) {
			String exceptionMessage = gradleException.getMessage();

			Assert.assertTrue(exceptionMessage.contains("Unable to find file"));
		}
	}

	@Test
	public void testThrowsUnableToParseFrontendTokenDefinitionFileException()
		throws IOException {

		Project project = Mockito.mock(Project.class);

		File file = File.createTempFile("frontend-token-definition", "json");

		file.deleteOnExit();

		String invalidJSON = "{[/][i}";

		Files.write(file.toPath(), invalidJSON.getBytes());

		Mockito.when(
			project.file(Mockito.anyString())
		).thenReturn(
			file
		);

		ClientExtension clientExtension = _getThemeCSSClientExtension();

		try {
			_clientExtensionValidator.validate(clientExtension, project);
			Assert.fail();
		}
		catch (GradleException gradleException) {
			String exceptionMessage = gradleException.getMessage();

			Assert.assertTrue(
				exceptionMessage.contains("Unable to parse file"));
		}
	}

	private ClientExtension _getThemeCSSClientExtension() {
		ClientExtension clientExtension = new ClientExtension();

		clientExtension.type = "themeCSS";
		clientExtension.typeSettings = new HashMap<String, Object>() {
			{
				put("frontendTokenDefinitionJSON", "file.json");
			}
		};

		return clientExtension;
	}

	private final ClientExtensionValidator _clientExtensionValidator =
		new ClientExtensionValidator();

}