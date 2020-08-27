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

package com.liferay.petra.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Jorge Ferrer
 */
public class GenericUtil {

	public static Class<?> getGenericClass(Class<?> clazz) {
		Type[] genericInterfaceTypes = clazz.getGenericInterfaces();

		for (Type genericInterfaceType : genericInterfaceTypes) {
			if (genericInterfaceType instanceof ParameterizedType) {
				ParameterizedType parameterizedType =
					(ParameterizedType)genericInterfaceType;

				return (Class<?>)parameterizedType.getActualTypeArguments()[0];
			}
		}

		Class<?> superClass = clazz.getSuperclass();

		if (superClass != null) {
			return getGenericClass(superClass);
		}

		return Object.class;
	}

	public static Class<?> getGenericClass(Object object) {
		Class<?> clazz = object.getClass();

		return getGenericClass(clazz);
	}

	public static String getGenericClassName(Object object) {
		Class<?> clazz = getGenericClass(object);

		return clazz.getName();
	}

}