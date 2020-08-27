create table DEDataDefinitionFieldLink (
	uuid_ VARCHAR(75) null,
	deDataDefinitionFieldLinkId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	ddmStructureId LONG,
	fieldName VARCHAR(75) null,
	lastPublishDate DATE null
);

create table DEDataListView (
	uuid_ VARCHAR(75) null,
	deDataListViewId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	appliedFilters TEXT null,
	ddmStructureId LONG,
	fieldNames TEXT null,
	name STRING null,
	sortField VARCHAR(75) null
);