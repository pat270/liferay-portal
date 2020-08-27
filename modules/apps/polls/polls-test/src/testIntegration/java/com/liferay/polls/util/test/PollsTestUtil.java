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

package com.liferay.polls.util.test;

import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.model.PollsVote;
import com.liferay.polls.service.PollsChoiceLocalServiceUtil;
import com.liferay.polls.service.PollsQuestionLocalServiceUtil;
import com.liferay.polls.service.PollsVoteLocalServiceUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;

import java.util.Locale;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class PollsTestUtil {

	public static PollsChoice addChoice(long groupId, long questionId)
		throws Exception {

		return PollsChoiceLocalServiceUtil.addChoice(
			TestPropsValues.getUserId(), questionId,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			ServiceContextTestUtil.getServiceContext(groupId));
	}

	public static PollsQuestion addQuestion(long groupId) throws Exception {
		return addQuestion(
			groupId, RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap());
	}

	public static PollsQuestion addQuestion(
			long groupId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap)
		throws Exception {

		return PollsQuestionLocalServiceUtil.addQuestion(
			TestPropsValues.getUserId(), titleMap, descriptionMap, 0, 0, 0, 0,
			0, true, null, ServiceContextTestUtil.getServiceContext(groupId));
	}

	public static PollsVote addVote(
			long groupId, long questionId, long choiceId)
		throws Exception {

		return PollsVoteLocalServiceUtil.addVote(
			TestPropsValues.getUserId(), questionId, choiceId,
			ServiceContextTestUtil.getServiceContext(groupId));
	}

}