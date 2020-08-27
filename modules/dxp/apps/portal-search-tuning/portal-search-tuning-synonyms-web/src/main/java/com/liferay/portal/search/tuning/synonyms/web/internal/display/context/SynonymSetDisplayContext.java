/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.search.tuning.synonyms.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;

import java.util.List;

/**
 * @author Filipe Oshiro
 */
public class SynonymSetDisplayContext {

	public String getDisplayedSynonymSet() {
		return _displayedSynonymSet;
	}

	public List<DropdownItem> getDropdownItems() {
		return _dropDownItems;
	}

	public String getEditRenderURL() {
		return _editRenderURL;
	}

	public String getSynonymSet() {
		return _synonymSet;
	}

	public String getSynonymSetId() {
		return _synonymSetId;
	}

	public void setDisplayedSynonymSet(String displayedSynonymSet) {
		_displayedSynonymSet = displayedSynonymSet;
	}

	public void setDropDownItems(List<DropdownItem> dropDownItems) {
		_dropDownItems = dropDownItems;
	}

	public void setEditRenderURL(String editRenderURL) {
		_editRenderURL = editRenderURL;
	}

	public void setSynonyms(String synonyms) {
		_synonymSet = synonyms;
	}

	public void setSynonymSetId(String synonymSetId) {
		_synonymSetId = synonymSetId;
	}

	private String _displayedSynonymSet;
	private List<DropdownItem> _dropDownItems;
	private String _editRenderURL;
	private String _synonymSet;
	private String _synonymSetId;

}