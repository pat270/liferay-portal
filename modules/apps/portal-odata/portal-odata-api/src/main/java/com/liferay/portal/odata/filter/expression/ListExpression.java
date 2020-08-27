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

package com.liferay.portal.odata.filter.expression;

import java.util.List;

/**
 * Represents a list expression node in the expression tree.
 *
 * @author Cristina González
 * @review
 */
public interface ListExpression extends Expression {

	/**
	 * Returns an {@link Expression} subtree of the left operation.
	 *
	 * @return Expression sub tree of the left operation
	 * @review
	 */
	public Expression getLeftOperationExpression();

	/**
	 * Returns the list expression's operation.
	 *
	 * @return the operation of the list Expression
	 * @review
	 */
	public Operation getOperation();

	/**
	 * Returns a list of the right {@link Expression} operations.
	 *
	 * @return the expression list
	 * @review
	 */
	public List<Expression> getRightOperationExpressions();

	public static enum Operation {

		IN

	}

}