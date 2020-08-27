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

package com.liferay.headless.admin.workflow.internal.dto.v1_0.util;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.headless.admin.workflow.dto.v1_0.ObjectReviewed;
import com.liferay.message.boards.model.MBDiscussion;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * @author Rafael Praxedes
 */
public class ObjectReviewedUtil {

	public static ObjectReviewed toObjectReviewed(
		Locale locale, Map<String, Serializable> optionalAttributes) {

		return new ObjectReviewed() {
			{
				assetTitle = _getAssetTitle(
					GetterUtil.getLong(
						optionalAttributes.get(
							WorkflowConstants.CONTEXT_ENTRY_CLASS_PK)),
					GetterUtil.getString(
						optionalAttributes.get(
							WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME)),
					locale);
				assetType = _getAssetType(
					GetterUtil.getString(
						optionalAttributes.get(
							WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME)),
					locale);
				id = GetterUtil.getLong(
					optionalAttributes.get(
						WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
				resourceType = _toResourceType(
					GetterUtil.getString(
						optionalAttributes.get(
							WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME)));
			}
		};
	}

	private static String _getAssetTitle(
		long classPK, String entryClassName, Locale locale) {

		WorkflowHandler<?> workflowHandler =
			WorkflowHandlerRegistryUtil.getWorkflowHandler(entryClassName);

		return workflowHandler.getTitle(classPK, locale);
	}

	private static String _getAssetType(String entryClassName, Locale locale) {
		WorkflowHandler<?> workflowHandler =
			WorkflowHandlerRegistryUtil.getWorkflowHandler(entryClassName);

		return workflowHandler.getType(locale);
	}

	private static String _toResourceType(String entryClassName) {
		if (Objects.equals(entryClassName, BlogsEntry.class.getName())) {
			return "BlogPosting";
		}

		if (Objects.equals(entryClassName, MBDiscussion.class.getName())) {
			return "Comment";
		}

		return null;
	}

}