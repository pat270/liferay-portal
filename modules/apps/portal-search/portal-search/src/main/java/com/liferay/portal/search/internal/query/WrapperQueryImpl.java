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

package com.liferay.portal.search.internal.query;

import com.liferay.portal.search.query.QueryVisitor;
import com.liferay.portal.search.query.WrapperQuery;

import java.nio.charset.StandardCharsets;

/**
 * @author Michael C. Han
 */
public class WrapperQueryImpl extends BaseQueryImpl implements WrapperQuery {

	public WrapperQueryImpl(byte[] source) {
		_source = source;
	}

	public WrapperQueryImpl(String source) {
		_source = source.getBytes(StandardCharsets.UTF_8);
	}

	@Override
	public <T> T accept(QueryVisitor<T> queryVisitor) {
		return queryVisitor.visit(this);
	}

	@Override
	public byte[] getSource() {
		return _source;
	}

	public void setSource(byte[] source) {
		_source = source;
	}

	@Override
	public String toString() {
		return new String(_source);
	}

	private static final long serialVersionUID = 1L;

	private byte[] _source;

}