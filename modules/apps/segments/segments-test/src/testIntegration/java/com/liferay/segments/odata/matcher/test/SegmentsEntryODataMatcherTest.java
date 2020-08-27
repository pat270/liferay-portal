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

package com.liferay.segments.odata.matcher.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.segments.odata.matcher.ODataMatcher;

import java.util.Collections;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eduardo García
 */
@RunWith(Arquillian.class)
public class SegmentsEntryODataMatcherTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testMatchesIdEquals() throws Exception {
		long segmentsEntryId = RandomTestUtil.nextLong();

		Map<String, String> segmentsEntryIds = Collections.singletonMap(
			"segmentsEntryIds",
			StringUtil.merge(
				new long[] {segmentsEntryId, RandomTestUtil.nextLong()}, ","));

		Assert.assertTrue(
			_contextODataMatcher.matches(
				StringBundler.concat(
					"(segmentsEntryIds eq '", segmentsEntryId, "')"),
				segmentsEntryIds));
		Assert.assertFalse(
			_contextODataMatcher.matches(
				StringBundler.concat(
					"(segmentsEntryIds eq '", RandomTestUtil.nextLong(), "')"),
				segmentsEntryIds));
	}

	@Test
	public void testMatchesIdIn() throws Exception {
		long segmentsEntryId = RandomTestUtil.nextLong();

		Map<String, String> segmentsEntryIds = Collections.singletonMap(
			"segmentsEntryIds",
			StringUtil.merge(
				new long[] {segmentsEntryId, RandomTestUtil.nextLong()}, ","));

		Assert.assertTrue(
			_contextODataMatcher.matches(
				StringBundler.concat(
					"(segmentsEntryIds in ('", segmentsEntryId, "'))"),
				segmentsEntryIds));
		Assert.assertFalse(
			_contextODataMatcher.matches(
				StringBundler.concat(
					"(segmentsEntryIds in ('", RandomTestUtil.nextLong(),
					"'))"),
				segmentsEntryIds));
	}

	@Test
	public void testMatchesStringNotContains() throws Exception {
		long segmentsEntryId = RandomTestUtil.nextLong();

		Map<String, String> segmentsEntryIds = Collections.singletonMap(
			"segmentsEntryIds",
			StringUtil.merge(
				new long[] {segmentsEntryId, RandomTestUtil.nextLong()}, ","));

		Assert.assertFalse(
			_contextODataMatcher.matches(
				StringBundler.concat(
					"not (segmentsEntryIds eq '", segmentsEntryId, "')"),
				segmentsEntryIds));
		Assert.assertTrue(
			_contextODataMatcher.matches(
				StringBundler.concat(
					"not (segmentsEntryIds eq '", RandomTestUtil.nextLong(),
					"')"),
				segmentsEntryIds));
	}

	@Inject(
		filter = "target.class.name=com.liferay.segments.model.SegmentsEntry",
		type = ODataMatcher.class
	)
	private ODataMatcher<Map<?, ?>> _contextODataMatcher;

}