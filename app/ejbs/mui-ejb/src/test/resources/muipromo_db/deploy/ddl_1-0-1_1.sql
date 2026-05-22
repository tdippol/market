--roles table
CREATE TABLE MUIPROMO.roles
(
  id INTEGER NOT NULL,
  name VARCHAR(50) NOT NULL,
  description varchar(250),
  primary key (id),
  unique (name)
) ;

CREATE SEQUENCE MUIPROMO.ROLES_SEQ
  MINVALUE 1
  MAXVALUE 2147483647
  START WITH 1
  INCREMENT BY 1;

--users table
CREATE TABLE MUIPROMO.users
(
  id INTEGER NOT NULL,
  name VARCHAR(50) NOT NULL,
  filters CLOB default '{}',
  role_id integer not null  references MUIPROMO.roles(id),
  primary key (id),
  unique (name)
) ;

CREATE SEQUENCE MUIPROMO.USERS_SEQ
  MINVALUE 1
  MAXVALUE 2147483647
  START WITH 1
  INCREMENT BY 1;

--ui table
create table MUIPROMO.ui(
                     id integer NOT NULL,
                     name varchar(50),
                     description varchar(250),
                     primary key (id),
                     unique (name)
);

CREATE SEQUENCE MUIPROMO.UI_SEQ
  MINVALUE 1
  MAXVALUE 2147483647
  START WITH 1
  INCREMENT BY 1;

--acl table, actual core of the db
create table MUIPROMO.acl(
                      id integer NOT NULL,
                      role_id integer not null references MUIPROMO.roles(id),
                      ui_id integer not null references MUIPROMO.ui(id),
                      component_class varchar(50) not null,
                      component varchar(50) not null,
                      visible  integer default 0 not null,
                      editable  integer default 0 not null,
                      enabled  integer default 0 not null,
                      primary key (id),
                      unique (role_id,ui_id,component)
);

CREATE SEQUENCE MUIPROMO.ACL_SEQ
  MINVALUE 1
  MAXVALUE 2147483647
  START WITH 1
  INCREMENT BY 1;

--menu table
create table MUIPROMO.menu
(
	ID_MENU INTEGER not null
		constraint MENU_PK
			primary key,
	PARENT_ID INTEGER,
	LABEL VARCHAR(50) not null,
	URL VARCHAR(250),
	BEAN VARCHAR(50),
	ORDER_ID INTEGER default 0 not null,
	EXTERNAL_LINK  integer default 0 not null
);

CREATE SEQUENCE MUIPROMO.MENU_SEQ
  MINVALUE 1
  MAXVALUE 2147483647
  START WITH 1
  INCREMENT BY 1;