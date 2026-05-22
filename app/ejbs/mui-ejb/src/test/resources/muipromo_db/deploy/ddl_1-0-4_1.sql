alter table MUIPROMO.MENU
MODIFY
(
url VARCHAR2(350)
);

alter table MUIPROMO.MENU_ROLES drop primary key;

alter table MUIPROMO.MENU_ROLES drop column ID;