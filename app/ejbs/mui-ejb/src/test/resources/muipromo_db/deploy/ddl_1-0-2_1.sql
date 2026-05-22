create table MUIPROMO.menu_roles
(
	ID INTEGER NOT NULL
		constraint MENU_ROLES_pk
			primary key,
	ID_MENU int not null,
	ID_ROLES int not null
);

CREATE SEQUENCE MUIPROMO.MENU_ROLES_SEQ
  MINVALUE 1
  MAXVALUE 2147483647
  START WITH 1
  INCREMENT BY 1;

alter table MUIPROMO.menu_roles
    add constraint FK_MENU
        foreign key (ID_MENU) references MUIPROMO.menu;

alter table MUIPROMO.menu_roles
    add constraint FK_ROLES
        foreign key (ID_ROLES) references MUIPROMO.roles;

alter table MUIPROMO.users add HIDDEN_COLS CLOB default '[]';

alter table MUIPROMO.users
	add CAM_LOGIN INTEGER default 0;

alter table MUIPROMO.users
	add NAMESPACE_LOGIN INTEGER default 0;
