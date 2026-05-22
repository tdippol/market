-- New Menu configuration
alter table MUIPROMO.CONFIGURATION add "ID_MENU" NUMBER(38,0); 
alter table MUIPROMO.CONFIGURATION add "LAST_REVISION_DATE" TIMESTAMP (6); 
alter table MUIPROMO.CONFIGURATION add "LAST_EDITOR" VARCHAR2(255);

-- In grid Filters
alter table MUIPROMO.users add "INGRID_FILTERS" CLOB default '{}';
alter table MUIPROMO.USERS_BACKUP add "INGRID_FILTERS" CLOB default '{}';

-- Bug #1390: SQL190827184636951 not droppped in 1.0.41
drop index "MUIPROMO"."SQL190827184636951";