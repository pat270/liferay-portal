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

package com.liferay.poshi.runner.prose;

import com.liferay.poshi.runner.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Peter Yoo
 */
public class PoshiProseMatcher {

	public static PoshiProseMatcher getPoshiProseMatcher(String poshiProse) {
		String key = _toString(poshiProse);

		return poshiProseMatcherMap.get(key);
	}

	public static void storePoshiProseMatcher(
			String poshiProse, String macroNamespacedClassCommandName)
		throws Exception {

		String key = _toString(poshiProse);

		PoshiProseMatcher ppm = poshiProseMatcherMap.get(key);

		if (Validator.isNotNull(ppm) &&
			!macroNamespacedClassCommandName.equals(
				ppm.getMacroNamespacedClassCommandName())) {

			StringBuilder sb = new StringBuilder();

			sb.append("Duplicate prose '");
			sb.append(key);
			sb.append("' already exists for ");

			sb.append(ppm.getMacroNamespacedClassCommandName());

			sb.append("\n in ");
			sb.append(macroNamespacedClassCommandName);

			throw new RuntimeException(sb.toString());
		}

		poshiProseMatcherMap.put(
			key,
			new PoshiProseMatcher(poshiProse, macroNamespacedClassCommandName));
	}

	public String getMacroNamespacedClassCommandName() {
		return _macroNamespacedClassCommandName;
	}

	public String getPoshiProse() {
		return _poshiProse;
	}

	public List<String> getVarNames() {
		return _varNames;
	}

	@Override
	public String toString() {
		return _toString(_poshiProse);
	}

	protected static final Map<String, PoshiProseMatcher> poshiProseMatcherMap =
		new HashMap<>();

	private static String _toString(String matchingString) {
		return matchingString.replaceAll("\\$\\{.+?\\}", "\"\"");
	}

	private PoshiProseMatcher(
		String poshiProse, String macroNamespacedClassCommandName) {

		_poshiProse = poshiProse;
		_macroNamespacedClassCommandName = macroNamespacedClassCommandName;

		Matcher matcher = _poshiProseVarPattern.matcher(_poshiProse);

		while (matcher.find()) {
			_varNames.add(matcher.group(1));
		}
	}

	private static final Pattern _poshiProseVarPattern = Pattern.compile(
		"\\$\\{(.+?)\\}");

	private final String _macroNamespacedClassCommandName;
	private final String _poshiProse;
	private final List<String> _varNames = new ArrayList<>();

}