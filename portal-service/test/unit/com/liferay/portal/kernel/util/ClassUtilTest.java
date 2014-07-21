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

package com.liferay.portal.kernel.util;

import java.io.StringReader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class ClassUtilTest {

	@Test
	public void testGetClassesFromAnnotationsWithArrayParameter()
		throws Exception {

		testGetClassesFromAnnotationWithArrayParameter("A");
		testGetClassesFromAnnotationWithArrayParameter("A", "B");
		testGetClassesFromAnnotationWithArrayParameter("A", "B", "C");
	}

	protected void testGetClassesFromAnnotationWithArrayParameter(
			String... classNames)
		throws Exception {

		StringBundler sb = new StringBundler(classNames.length * 3 + 1);

		sb.append("@AnnotationWithArrayParameter({");

		for (int i = 0; i < classNames.length; i++) {
			sb.append(classNames[i]);
			sb.append(".class");

			if (i < (classNames.length - 1)) {
				sb.append(StringPool.COMMA);
			}
		}

		sb.append("})");

		Set<String> actualClassNames = ClassUtil.getClasses(
			new StringReader(sb.toString()), null);

		Set<String> expectedClassNames = new HashSet<String>();

		expectedClassNames.add("AnnotationWithArrayParameter");
		expectedClassNames.addAll(Arrays.asList(classNames));

		Assert.assertEquals(expectedClassNames, actualClassNames);
	}

}