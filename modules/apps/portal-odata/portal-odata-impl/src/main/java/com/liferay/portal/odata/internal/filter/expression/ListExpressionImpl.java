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

package com.liferay.portal.odata.internal.filter.expression;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.odata.filter.expression.Expression;
import com.liferay.portal.odata.filter.expression.ExpressionVisitException;
import com.liferay.portal.odata.filter.expression.ExpressionVisitor;
import com.liferay.portal.odata.filter.expression.ListExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cristina González
 */
public class ListExpressionImpl implements ListExpression {

	public ListExpressionImpl(
		Expression leftOperationExpression, Operation operation,
		List<Expression> rightOperationExpressions) {

		_leftOperationExpression = leftOperationExpression;
		_operation = operation;
		_rightOperationExpressions = rightOperationExpressions;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor)
		throws ExpressionVisitException {

		List<T> visitedRightOperationExpressions = new ArrayList<>();

		for (Expression rightOperationExpression : _rightOperationExpressions) {
			visitedRightOperationExpressions.add(
				rightOperationExpression.accept(expressionVisitor));
		}

		return expressionVisitor.visitListExpressionOperation(
			_operation, _leftOperationExpression.accept(expressionVisitor),
			visitedRightOperationExpressions);
	}

	@Override
	public Expression getLeftOperationExpression() {
		return _leftOperationExpression;
	}

	@Override
	public Operation getOperation() {
		return _operation;
	}

	@Override
	public List<Expression> getRightOperationExpressions() {
		return _rightOperationExpressions;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{", _leftOperationExpression, " ", _operation.name(), " ",
			_rightOperationExpressions, '}');
	}

	private final Expression _leftOperationExpression;
	private final Operation _operation;
	private final List<Expression> _rightOperationExpressions;

}