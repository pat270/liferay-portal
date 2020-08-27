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

package com.liferay.polls.search.test;

import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.util.test.PollsTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.util.GroupTestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Igor Fabiano Nazar
 * @author Luan Maoski
 */
public class PollsQuestionFixture {

	public PollsQuestionFixture(Group group) {
		_group = group;
	}

	public PollsQuestion createPollsQuestion() throws Exception {
		PollsQuestion pollsQuestion = PollsTestUtil.addQuestion(
			_group.getGroupId());

		_pollsQuestions.add(pollsQuestion);

		return pollsQuestion;
	}

	public PollsQuestion createPollsQuestion(
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap)
		throws Exception {

		PollsQuestion pollsQuestion = PollsTestUtil.addQuestion(
			_group.getGroupId(), titleMap, descriptionMap);

		_pollsQuestions.add(pollsQuestion);

		return pollsQuestion;
	}

	public List<PollsQuestion> getPollsQuestions() {
		return _pollsQuestions;
	}

	public void updateDisplaySettings(Locale locale) throws Exception {
		Group group = GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), null, locale);

		_group.setModelAttributes(group.getModelAttributes());
	}

	private final Group _group;
	private final List<PollsQuestion> _pollsQuestions = new ArrayList<>();

}