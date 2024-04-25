<#assign
	segmentsEntries = dataFactory.newSegmentsEntries(guestGroupModel.groupId)
/>

<#list segmentsEntries as segmentsEntry>
	${dataFactory.toInsertSQL(segmentsEntry)}
</#list>

<#list dataFactory.getSequence(dataFactory.maxSegmentsEntrySegmentsExperienceCount) as i>
	<#assign
		layoutModel = dataFactory.newLayoutModel(guestGroupModel.groupId, "segments_experience_layout_" + i, "", "")
	/>

	${dataFactory.toInsertSQL(layoutModel)}

	<#list segmentsEntries as segmentsEntry>
		${dataFactory.toInsertSQL(dataFactory.newSegmentsExperience(guestGroupModel.groupId, layoutModel.plid, segmentsEntry.segmentsEntryId))}
	</#list>
</#list>