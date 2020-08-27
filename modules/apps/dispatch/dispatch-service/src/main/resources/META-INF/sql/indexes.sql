create index IX_36F4EB5F on DispatchLog (dispatchTriggerId, status);

create unique index IX_D86DCE63 on DispatchTrigger (companyId, name[$COLUMN_LENGTH:75$]);
create index IX_9BD0BFB1 on DispatchTrigger (companyId, type_[$COLUMN_LENGTH:75$]);