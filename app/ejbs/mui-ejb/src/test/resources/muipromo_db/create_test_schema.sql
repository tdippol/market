--roles table
CREATE TABLE MUIPROMO.roles
(
  id INTEGER NOT NULL,
  name VARCHAR(50) NOT NULL,
  description varchar(250),
  help_filename varchar(250),
  help_data blob,
  uuid VARCHAR(32),
  override_admin NUMERIC(1) not null default 0,
  primary key (id),
  unique (name)
) ;
CREATE TABLE MUIPROMO.GRUPPO (
	id INTEGER NOT NULL,
	descrizione VARCHAR(30),
	codice_gruppo VARCHAR(10) not null,
	TIPO_ACCESSO_TOTALE VARCHAR(1),
    uuid VARCHAR(32),
	primary key(id)
);
CREATE SEQUENCE MUIPROMO.GRUPPO_ID_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE MUIPROMO.ROLES_SEQ
  MINVALUE 1
  MAXVALUE 2147483647
  START WITH 1
  INCREMENT BY 1;
--users table
CREATE TABLE MUIPROMO.users
(
  id INTEGER NOT NULL ,
  name VARCHAR(50) NOT NULL,
  filters CLOB default '{}',
    hidden_cols CLOB default '[]',
  cam_login integer default 0,
  namespace_login integer default 0,
  admin_filters CLOB default '{}',
  description VARCHAR(50),
  ingrid_filters CLOB default '{}',
  db_filters CLOB default '{}',
  last_access timestamp,
  uuid VARCHAR(32),  
  primary key (id),
  unique (name)
) ;

CREATE SEQUENCE MUIPROMO.USERS_SEQ
  MINVALUE 1
  MAXVALUE 2147483647
  START WITH 1
  INCREMENT BY 1;

  CREATE TABLE MUIPROMO.USERS_ROLES(
	ID_USER INTEGER  NOT NULL,
	ID_ROLE INTEGER  NOT NULL
);

CREATE UNIQUE INDEX USERS_ROLES_U_IDX ON MUIPROMO.USERS_ROLES(ID_USER, ID_ROLE);

CREATE TABLE MUIPROMO.CANALE_PROMOZIONE (
	ID INTEGER NOT NULL ,
	CODICE_CANALE integer NOT NULL,
	DESCRIZIONE VARCHAR(30),
	SIGLA_CANALE VARCHAR(10),
	PRIMARY KEY(ID)
);

CREATE SEQUENCE MUIPROMO.CANALE_PROMOZIONE_ID_SEQ START WITH 1;

CREATE TABLE MUIPROMO.GRUPPO_CANALE(
	ID_GRUPPO BIGINT NOT NULL ,
	ID_CANALE BIGINT NOT NULL ,
	FLAG_CREATE NUMERIC(1) default 1,
	FLAG_OWNER NUMERIC(1) default 1,
	PRIMARY KEY (ID_GRUPPO, ID_CANALE)
);
alter table MUIPROMO.GRUPPO_CANALE add constraint FK_CANALE_GROUP foreign key (ID_GRUPPO) references MUIPROMO.GRUPPO;
alter table MUIPROMO.GRUPPO_CANALE add constraint FK_GROUP_CANALE foreign key (ID_CANALE) references MUIPROMO.CANALE_PROMOZIONE;

--acl table, actual core of the db
create table MUIPROMO.acl(
                      id integer not null ,
                      role_id integer not null references MUIPROMO.roles(id),
                      component_class varchar(50) not null,
                      component varchar(50) not null,
                      visible integer default 0  not null,
                      editable integer default 0  not null,
                      enabled integer default 0  not null,
    				  uuid VARCHAR(32),
    				  primary key (id),
                      unique (role_id,component)
);
CREATE SEQUENCE MUIPROMO.ACL_SEQ
  MINVALUE 1
  MAXVALUE 2147483647
  START WITH 1
  INCREMENT BY 1;
--menu table
create table MUIPROMO.menu
(
    ID_MENU integer not null constraint menu_pk  primary key,
    PARENT_ID integer,
    LABEL VARCHAR(50) not null,
    URL varchar(250),
    BEAN VARCHAR(50),
    ORDER_ID integer default 0 not null,
    EXTERNAL_LINK integer default 0 not null,
    TEMPLATE integer,
    uuid VARCHAR(32)    
);
CREATE SEQUENCE MUIPROMO.MENU_SEQ
  MINVALUE 1
  MAXVALUE 2147483647
  START WITH 1
  INCREMENT BY 1;

CREATE SEQUENCE MUIPROMO.CONNECTION_SETUP_SEQ
    MINVALUE 1
    MAXVALUE 2147483647
    START WITH 1
    INCREMENT BY 1;
--MENU_ROLES table
create table MUIPROMO.MENU_ROLES
(
	ID_MENU int not null,
	ID_ROLES int not null,
	ADMIN_MODE_VISIBLE NUMERIC(1) not null default 1
);
alter table MUIPROMO.MENU_ROLES add constraint FK_MENU foreign key (ID_MENU) references MUIPROMO.menu;
alter table MUIPROMO.MENU_ROLES add constraint FK_ROLES foreign key (ID_ROLES) references MUIPROMO.roles;

create table MUIPROMO.APPLICATION_PROPERTIES(
    ID_APPLICATION_PROPERTIES INTEGER not null
        constraint APPLICATION_PROPERTIES_PK
            primary key,
    AP_KEY VARCHAR(50) not null,
    AP_VALUE VARCHAR(250) not null,
    uuid VARCHAR(32)    
);
CREATE SEQUENCE MUIPROMO.APPLICATION_PROPERTIES_ID_SEQ
  MINVALUE 1
  MAXVALUE 2147483647
  START WITH 1
  INCREMENT BY 1;


create table MUIPROMO.CONNECTION_SETUP
(
    ID_CONNECTION_SETUP int
        constraint CONNECTION_SETUP_pk
            primary key,
    CONNECTION_NAME varchar(50) not null,
    USERNAME VARCHAR(50),
    PASSWORD VARCHAR(50),
    DOMAIN VARCHAR(50),
    HOST VARCHAR(100) not null,
    PORT int not null,
    PATH VARCHAR(50) not null,
    VALIDATE_SSL int default 0 not null,
    AUTH_TYPE VARCHAR(50) not null,
    uuid VARCHAR(32)    
);

--configurations

create table MUIPROMO.CONFIGURATION
(
    ID_CONFIGURATION int
        constraint CONFIGURATION_pk
            primary key,
    PATH varchar(150) not null,
    JSON CLOB not null,
    TYPE VARCHAR(10) not null,
    ID_MENU integer, 
    LAST_REVISION_DATE timestamp,
    LAST_EDITOR varchar(255),
    uuid VARCHAR(32)    
);

CREATE SEQUENCE MUIPROMO.CONFIGURATION_SEQ
  MINVALUE 1
  MAXVALUE 2147483647
  START WITH 1
  INCREMENT BY 1;

create unique index SQL190827184636951 on MUIPROMO.CONFIGURATION (PATH, TYPE);

CREATE TABLE MUIPROMO.AUDIT_LOG(
    LOG_DATE TIMESTAMP NOT NULL,
    USERNAME VARCHAR(50) NOT NULL, 
    ACTION VARCHAR(50) NOT NULL, 
    PATH VARCHAR(1000)
);

CREATE SEQUENCE MUIPROMO.UPLOAD_DOCUMENT_ID_SEQ START WITH 1;
CREATE TABLE MUIPROMO.UPLOAD_DOCUMENT(
    ID                              BIGINT          NOT NULL PRIMARY KEY,
    NAME                            VARCHAR(4000)   NOT NULL,
    CODICE_UTENTE_INSERIMENTO       VARCHAR(50),
    CODICE_UTENTE_AGGIORNAMENTO     VARCHAR(50),
    DATA_INSERIMENTO                TIMESTAMP,
    DATA_AGGIORNAMENTO              TIMESTAMP
);

CREATE TABLE MUIPROMO.DOCUMENT_ROLE(
    ID_ROLE         BIGINT  NOT NULL    REFERENCES MUIPROMO.ROLES(ID),
    ID_DOCUMENT     BIGINT  NOT NULL    REFERENCES MUIPROMO.UPLOAD_DOCUMENT(ID)
);
CREATE UNIQUE INDEX DOCUMENT_ROLE_U_IDX ON MUIPROMO.DOCUMENT_ROLE(ID_ROLE, ID_DOCUMENT);

CREATE TABLE MUIPROMO.CONTEXTS (
	ID BIGINT NOT NULL PRIMARY KEY,
	CODE VARCHAR(15) NOT NULL,
	DESCRIPTION VARCHAR(255)
	);
CREATE SEQUENCE MUIPROMO.CONTEXT_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE MUIPROMO.CONTEXT_CANALE(
	ID_CONTEXT BIGINT NOT NULL REFERENCES MUIPROMO.CONTEXTS(ID),
	ID_CANALE BIGINT NOT NULL REFERENCES MUIPROMO.CANALE_PROMOZIONE(ID)
);

CREATE UNIQUE INDEX CONTEXT_CANALE_UNIQUE_IDX ON MUIPROMO.CONTEXT_CANALE(ID_CONTEXT, ID_CANALE);

CREATE TABLE MUIPROMO.V_REPARTO(
	ID NUMERIC(16,0) NOT NULL PRIMARY KEY,
	CODICE_REPARTO VARCHAR(2) NOT NULL,
	CODICE_REPARTO_PADRE VARCHAR(2),
	DESCRIZIONE VARCHAR(30)
);

CREATE SEQUENCE  MUIPROMO.REPARTO_ID_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE MUIPROMO.GRUPPO_REPARTO(
	ID_GRUPPO NUMERIC(16,0) NOT NULL REFERENCES MUIPROMO.GRUPPO(ID),
	ID_REPARTO NUMERIC(16,0) NOT NULL REFERENCES MUIPROMO.V_REPARTO(ID),
	TIPO_ACCESSO VARCHAR(1)
);

CREATE UNIQUE INDEX IDX_GRUPPO_REPARTO ON MUIPROMO.GRUPPO_REPARTO (ID_GRUPPO, ID_REPARTO);

CREATE TABLE MUIPROMO.V_COMPRATORE (
	ID NUMERIC(16,0) NOT NULL PRIMARY KEY,
	CODICE_COMPRATORE VARCHAR(3) NOT NULL,
	DESCRIZIONE VARCHAR(20),
	FLAG_ATTIVO DECIMAL(1,0) NOT NULL,
	ID_RESPONSABILE NUMERIC(16,0)  
);
CREATE INDEX COMPRATORE_ID ON MUIPROMO.V_COMPRATORE (ID_RESPONSABILE);
CREATE SEQUENCE MUIPROMO.COMPRATORE_ID_SEQ START WITH 1 INCREMENT BY 1;
ALTER TABLE MUIPROMO.V_COMPRATORE ADD FOREIGN KEY (ID_RESPONSABILE) REFERENCES MUIPROMO.V_COMPRATORE(ID);

CREATE TABLE MUIPROMO.GRUPPO_COMPRATORE(
	ID_GRUPPO INTEGER REFERENCES MUIPROMO.GRUPPO(ID),
	ID_COMPRATORE NUMERIC (16,0) REFERENCES MUIPROMO.V_COMPRATORE(ID),
	TIPO_ACCESSO VARCHAR(1)
);
CREATE UNIQUE INDEX  IDX_GRUPPO_COMPRATORE ON MUIPROMO.GRUPPO_COMPRATORE (ID_GRUPPO, ID_COMPRATORE);


CREATE TABLE MUIPROMO.V_GRM (
	ID NUMERIC(16,0) NOT NULL PRIMARY KEY,
	CODICE_GRM VARCHAR(3) NOT NULL,
	DESCRIZIONE VARCHAR(30)
);

CREATE SEQUENCE MUIPROMO.GRM_ID_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE MUIPROMO.GRUPPO_GRM(
	ID_GRUPPO BIGINT NOT NULL REFERENCES MUIPROMO.GRUPPO(ID),
	ID_GRM NUMERIC(16,0)  NOT NULL REFERENCES MUIPROMO.V_GRM(ID),
	TIPO_ACCESSO VARCHAR(1)
);


CREATE UNIQUE INDEX GRM_IDX ON MUIPROMO.GRUPPO_GRM(ID_GRUPPO, ID_GRM);

CREATE TABLE MUIPROMO.USER_GROUP(
	ID_USER NUMERIC(16,0) NOT NULL REFERENCES USERS(ID),
	ID_GRUPPO NUMERIC(16,0) NOT NULL REFERENCES GRUPPO(ID) 
);

CREATE UNIQUE INDEX USER_GROUP_UNIQUE_IDX ON MUIPROMO.USER_GROUP (ID_USER, ID_GRUPPO);

INSERT INTO MUIPROMO.roles (ID, NAME, DESCRIPTION) VALUES (1, 'ROLE_TEST_1', 'ROLE_TEST_1');
INSERT INTO MUIPROMO.roles (ID, NAME, DESCRIPTION) VALUES (2,'ROLE_TEST_2', 'ROLE_TEST_2');
INSERT INTO MUIPROMO.roles (ID, NAME, DESCRIPTION) VALUES (3,'ROLE_TEST_3', 'ROLE_TEST_3');

INSERT INTO MUIPROMO.users (ID, NAME, ROLE_ID) VALUES ( 1, 'USER_TEST_1', 1);
INSERT INTO MUIPROMO.users (ID, NAME, ROLE_ID) VALUES (2,'USER_TEST_2', 2);

INSERT INTO MUIPROMO.ACL(ID,role_id,component_class,component) values (1,1,'component_class_test_1','component_test_1');
INSERT INTO MUIPROMO.ACL(ID,role_id,component_class,component) values (2,2,'component_class_test_2','component_test_2');

INSERT into MUIPROMO.menu(ID_MENU,PARENT_ID,LABEL) values (1,null,'menu_test_1');
INSERT into MUIPROMO.menu(ID_MENU,PARENT_ID,LABEL) values (2,1,'menu_test_2');
INSERT into MUIPROMO.menu(ID_MENU,PARENT_ID,LABEL) values (-3,1,'menu_test_2');


INSERT INTO MUIPROMO.APPLICATION_PROPERTIES (ID_APPLICATION_PROPERTIES, AP_KEY, AP_VALUE) VALUES (1, 'TOKEN_SPAWN', '120');
INSERT INTO MUIPROMO.APPLICATION_PROPERTIES (ID_APPLICATION_PROPERTIES, AP_KEY, AP_VALUE) VALUES (2, 'AUTH_SERVER', 'http://tmonetest.mil.esselunga.net');
INSERT INTO MUIPROMO.APPLICATION_PROPERTIES (ID_APPLICATION_PROPERTIES, AP_KEY, AP_VALUE) VALUES (3, 'MAX_CELLS', '100000');

INSERT INTO MUIPROMO.CONNECTION_SETUP (ID_CONNECTION_SETUP, CONNECTION_NAME, USERNAME, PASSWORD, DOMAIN, HOST, PORT, PATH, VALIDATE_SSL, AUTH_TYPE) VALUES (1, 'promo', null, null, null, 'https://TMONETEST', 8882, '/api/v1/', 0, 'PASSPORT');
INSERT INTO MUIPROMO.CONNECTION_SETUP (ID_CONNECTION_SETUP, CONNECTION_NAME, USERNAME, PASSWORD, DOMAIN, HOST, PORT, PATH, VALIDATE_SSL, AUTH_TYPE) VALUES (2, 'reporting', null, null, null, 'https://TMONETEST', 8883, '/api/v1/', 0, 'PASSPORT');


Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values (59,'/Admin/Gestione_ACL',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values (60,'/Admin/Gestione_Applicazione',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values (61,'/Admin/Gestione_Configurazioni',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values (62,'/Admin/Gestione_Menu',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values (63,'/Admin/Gestione_Ruoli',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values (64,'/Admin/Gestione_UI',' ','FILTER');

Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE, id_menu) values (2,'/Admin/Gestione_ACL',' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gridContainer",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}','GRID', 2);
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE, id_menu) values (3,'/Admin/Gestione_Applicazione',' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gridContainer",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}','GRID', -3);
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values (6,'/Admin/Gestione_Ruoli',' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gridContainer",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}','GRID');

Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values (1,'config_filter.json',('[
  		{
          "DIM_code": "idsezioneTematica",
          "DIM_columnName": "ID Sezione Tematica",
          "DIM_description": "ID Sezione Tematica",
          "ENDPOINT_serverName": "promo",
          "MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID Sezione Tematica] )}, 0)}, ASC)}}",
          "list_ATTR": [
              {
                  "ATTR_code": "Caption_Default",
                  "ATTR_columnName": "Caption_Default",
                  "ATTR_desc": "Caption_De')
    || ('fault"
              }
          ]
      },
      {
		"DIM_code": "promozione",
		"DIM_columnName": "Promozione",
		"DIM_description": "Promozione",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
		"list_ATTR": [
			{
				"ATTR_code": "anno",
				"ATTR_columnName": "Anno",
				"ATTR_desc": "Anno"
			},
			{
				"ATTR_code": "canale",
				"ATTR_columnName": "Canale",
				"ATTR_desc')
    || ('": "Canale"
			},
			{
				"ATTR_code": "tipo",
				"ATTR_columnName": "Tipo Promozione",
				"ATTR_desc": "Gruppo"
			},
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione + Data",
				"ATTR_desc": "Promozione"
			},
			{
				"ATTR_code": "riferimento",
				"ATTR_columnName": "Riferimento",
				"ATTR_desc": "Riferimento"
			},
			{
				"ATTR_code": "semestre",
				"ATTR_columnName": "MUI_Semestre",
				"ATTR_desc": "Semestre"
			},
			{
				"ATTR_code": "datainizioist",
		')
    || ('		"ATTR_columnName": "DataInizioIst",
				"ATTR_desc": "Data Inizio Ist."
			},
			{
				"ATTR_code": "datafineist",
				"ATTR_columnName": "DataFineIst",
				"ATTR_desc": "Data Fine Ist."
			},
			{
				"ATTR_code": "tipopromozione",
				"ATTR_columnName": "Tipo Promozione",
				"ATTR_desc": "Tipo Promozione"
			},
			{
				"ATTR_code": "statodesc",
				"ATTR_columnName": "StatoDesc",
				"ATTR_desc": "Stato Desc"
			},
			{
				"ATTR_code": "canaleanno",
				"ATTR_columnName": "Canale_Anno",
	')
    || ('			"ATTR_desc": "Canale Anno"
			}
		]
	},
	{
		"DIM_code": "promozioneriferimento",
		"DIM_columnName": "Promozione Riferimento",
		"DIM_description": "Promozione Riferimento",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}, [Promozione Riferimento].[Data_Inizio_Ist.],DESC )}",
		"list_ATTR": [
			{
				"ATTR_code": "anno",
				"ATTR_columnName": "Anno",
				"ATTR_desc": "Anno"
			},
			{
				"ATTR_code": "rif_ca')
    || ('nale",
				"ATTR_columnName": "MUI_Canale",
				"ATTR_desc": "Canale"
			},
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione + Riferimento",
				"ATTR_desc": "Promozione"
			},
			{
				"ATTR_code": "riferimento",
				"ATTR_columnName": "Descrizione + Data",
				"ATTR_desc": "Riferimento"
			},
			{
				"ATTR_code": "gruppo",
				"ATTR_columnName": "Gruppo",
				"ATTR_desc": "Gruppo"
			},
			{
				"ATTR_code": "semestre",
				"ATTR_columnName": "Semestre",
				"ATTR_desc')
    || ('": "Semestre"
			}
		]
	},
	{
		"DIM_code": "reparto",
		"DIM_columnName": "Reparto",
		"DIM_description": "Reparto",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Reparto] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "compratore",
				"ATTR_columnName": "Compratore",
				"ATTR_desc": "Compratore"
			},
			{
				"ATTR_c')
    || ('ode": "sigla",
				"ATTR_columnName": "Sigla",
				"ATTR_desc": "Sigla"
			},
			{
				"ATTR_code": "tipelemento",
				"ATTR_columnName": "TipoElemento",
				"ATTR_desc": "Tipo Elemento"
			},
			{
				"ATTR_code": "codpadre",
				"ATTR_columnName": "cod_padre",
				"ATTR_desc": "Cod. Padre"
			},
			{
				"ATTR_code": "reparto",
				"ATTR_columnName": "Reparto",
				"ATTR_desc": "Reparto"
			},
			{
				"ATTR_code": "categorymanager",
				"ATTR_columnName": "CategoryManager",
				"ATTR_desc": "')
    || ('Category Manager"
			}
		]
	},
	{
		"DIM_code": "categoria",
		"DIM_columnName": "Categoria",
		"DIM_description": "Categoria",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "direzionedesc",
				"ATTR_columnName": "DirezioneDesc",
				"ATTR_desc": "Direzione"
			},
			{
				"ATTR_code": "direzione",
				"ATTR_columnName": "Direzione",
				"ATTR_desc": "Cod Direzione"
			},
	')
    || ('		{
				"ATTR_code": "repartodesc",
				"ATTR_columnName": "RepartoDesc",
				"ATTR_desc": "Reparto"
			},
			{
				"ATTR_code": "reparto",
				"ATTR_columnName": "Reparto",
				"ATTR_desc": "Cod Reparto"
			},
			{
				"ATTR_code": "estensionecl",
				"ATTR_columnName": "EstensioneCL",
				"ATTR_desc": "Estensione CL"
			},
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "compratore",
		"DIM_columnName": "Co')
    || ('mpratore",
		"DIM_description": "Compratore",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ EXCEPT( {TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}, { [Compratore].[NA], [Compratore].[S000] }) }",
		"list_ATTR": [
			{
				"ATTR_code": "categorymanager",
				"ATTR_columnName": "CategoryManager",
				"ATTR_desc": "Category Manager"
			},
			{
				"ATTR_code": "repartodcodesc",
				"ATTR_columnName": "MUI_Reparto",
				"ATTR_desc": "RepartoCodDesc"
			},
			{
			')
    || ('	"ATTR_code": "repartodesc",
				"ATTR_columnName": "RepartoDesc",
				"ATTR_desc": "Reparto"
			},
			{
				"ATTR_code": "reparto",
				"ATTR_columnName": "Reparto",
				"ATTR_desc": "Cod Reparto"
			},
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "anno",
		"DIM_columnName": "Anno",
		"DIM_description": "Anno",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSET')
    || ('ALL( [Anno] )}, 0)}, DESC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "Name",
				"ATTR_columnName": "Name",
				"ATTR_desc": "Anno"
			}
		]
	},
	{
		"DIM_code": "clients",
		"DIM_columnName": "}Clients",
		"DIM_description": "Clients",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}Clients] )}, 0)}, DESC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "TM1_DefaultDisplayValue",
				"ATTR_columnName": "}TM1_DefaultDisplayValue",
				"ATTR_desc"')
    || (': "Client"
			}
		]
	},
	{
		"DIM_code": "zonaPromo",
		"DIM_columnName": "Zona Promo",
		"DIM_description": "Zona Promo",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ EXCEPT({{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Zona Promo] )}, 0)}, ASC)}}, { [Zona Promo].[NA],[Zona Promo].[SOCIETA_1],[Zona Promo].[SOCIETA_2] })}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "p')
    || ('ubblicita",
		"DIM_columnName": "Pubblicità",
		"DIM_description": "Pubblicità",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ EXCEPT({{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Pubblicità] )}, 0)}, ASC)}}, { [Pubblicità].[RADIO] }) }",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "canale",
		"DIM_columnName": "Canale",
		"DIM_description": "Canale",
		"ENDPOINT_serverName": "')
    || ('promo",
		"MDX_jsonSource": "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Canale] )}, 0)}, [Canale].[Descrizione],ASC )}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "misuraCanale",
		"DIM_columnName": "Misura Canale",
		"DIM_description": "Misura Canale",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Canale] )}, 0)}, ASC)}}",
	')
    || ('	"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "negozio",
		"DIM_columnName": "Negozio",
		"DIM_description": "Negozio",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "zonaPromo",
				"ATTR_columnName": "MUI_ZonaPromo",
				"ATTR_desc": "Zona Promo"
			},
			{
				"ATTR_cod')
    || ('e": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "tipoPuntoVendita",
				"ATTR_columnName": "Tipo Punto Vendita",
				"ATTR_desc": "Tipo Punto Vendita"
			},
			{
				"ATTR_code": "repartoProfumeria",
				"ATTR_columnName": "Reparto Profumeria",
				"ATTR_desc": "Reparto Profumeria"
			}
		]
	},
	{
		"DIM_code": "raggruppamentoFoto",
		"DIM_columnName": "Raggruppamento Foto",
		"DIM_description": "Raggruppamento Foto",
		"ENDPOIN')
    || ('T_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Raggruppamento Foto] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "mui_descrizione",
				"ATTR_columnName": "MUI_Descrizione",
				"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "tot",
				"ATTR_columnName": "MUI_TOT",
				"ATTR_desc": "Totali"
			},
			{
				"ATTR_code": "tots",
				"ATTR_columnName": "MUI_TOTS",
				"ATTR_desc": "Sub Totali"
			}
		]
	},
	{
		"DIM_code": "tipoPromozio')
    || ('ne",
		"DIM_columnName": "Tipo Promozione",
		"DIM_description": "Tipo Promozione",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "gruppo",
				"ATTR_columnName": "GRUPPO",
				"ATTR_desc": "Gruppo"
			}
		]
	},
	{
		"DIM_code": "macrospazio",
		"DIM_columnNa')
    || ('me": "MacroSpazio",
		"DIM_description": "Macro Spazio",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "prestazioniEC",
				"ATTR_columnName": "Prestazioni EC",
				"ATTR_desc": "Prestazioni EC"
			},
			{
				"ATTR_code": "prestazioniCNT",
				"ATTR_columnName":')
    || (' "Prestazioni CNT",
				"ATTR_desc": "Prestazioni CNT"
			}
		]
	},
	{
		"DIM_code": "misuraConfigurazioneMacrospaziNeg",
		"DIM_columnName": "Misura_Configurazione_Macrospazi_Neg",
		"DIM_description": "Configurazione Macrospazio Neg",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Neg] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc')
    || ('": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "misuraConfigurazioneMacrospaziNegPromo",
		"DIM_columnName": "Misura_Configurazione_Macrospazi_Neg_Promo",
		"DIM_description": "Configurazione Macrospazio Neg Promo",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Neg_Promo] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione')
    || ('"
			}
		]
	},
	{
		"DIM_code": "misuraConfigurazioneMacrospaziListino",
		"DIM_columnName": "Misura_Configurazione_Macrospazi_Listino",
		"DIM_description": "Misura Configurazione Macrospazi Listino",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Listino] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	')
    || ('{
		"DIM_code": "spazio",
		"DIM_columnName": "Spazio",
		"DIM_description": "Spazio",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 1)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "compratore",
				"ATTR_columnName": "Compratore",
				"ATTR_desc": "Compratore"
			},
			{
				"ATTR_code": "macroSpazio",
				"ATTR_columnName": "MacroSpazio",
				"ATTR_desc": "Macro Spazio"
			},
			{
				"ATTR_code": "macroSpazioDescrizione",')
    || ('
				"ATTR_columnName": "MacroSpazioDescrizione",
				"ATTR_desc": "Macro Spazio Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_promozione",
		"DIM_columnName": "REP - Promozione",
		"DIM_description": "Rep Promozione",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)}, [REP - Promozione].[Data_Inizio_Ist],DESC )} ",
		"list_ATTR": [
			{
				"ATTR_code": "anno",
				"ATTR_columnName": "Anno",
				"ATTR_desc": "Anno"
')
    || ('			},
			{
				"ATTR_code": "canale",
				"ATTR_columnName": "Canale",
				"ATTR_desc": "Canale"
			},
			{
				"ATTR_code": "tipo",
				"ATTR_columnName": "Tipo Promozione",
				"ATTR_desc": "Gruppo"
			},
			{
				"ATTR_code": "riferimento",
				"ATTR_columnName": "Riferimento",
				"ATTR_desc": "Riferimento"
			},
			{
				"ATTR_code": "semestre",
				"ATTR_columnName": "MUI_Semestre",
				"ATTR_desc": "Semestre"
			},
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione +')
    || (' Data",
				"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "datainizioist",
				"ATTR_columnName": "DataInizioIst",
				"ATTR_desc": "Data Inizio Ist."
			},
			{
				"ATTR_code": "datafineist",
				"ATTR_columnName": "DataFineIst",
				"ATTR_desc": "Data Fine Ist."
			},
			{
				"ATTR_code": "tipopromozione",
				"ATTR_columnName": "Tipo Promozione",
				"ATTR_desc": "Tipo Promozione"
			},
			{
				"ATTR_code": "statodesc",
				"ATTR_columnName": "StatoDesc",
				"ATTR_desc": "Stato D')
    || ('esc"
			},
			{
				"ATTR_code": "canaleanno",
				"ATTR_columnName": "Canale_Anno",
				"ATTR_desc": "Canale Anno"
			}
		]
	},
	{
		"DIM_code": "rep_compratore",
		"DIM_columnName": "REP - Compratore",
		"DIM_description": "Rep Compratore",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descri')
    || ('zione"
			}
		]
	},
	{
		"DIM_code": "rep_categoria",
		"DIM_columnName": "REP - Categoria",
		"DIM_description": "Rep Categoria",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_misuraTimone",
		"DIM_columnName": "REP - Misura Timone",
		"DIM_descri')
    || ('ption": "Rep Misura Timone",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Timone] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_scenario",
		"DIM_columnName": "REP - Scenario",
		"DIM_description": "Rep Scenario",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYL')
    || ('EVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "categoriadesc",
				"ATTR_columnName": "Categoria Desc",
				"ATTR_desc": "Categoria Desc"
			},
			{
				"ATTR_code": "grmdesc",
				"ATTR_columnName": "GRM Desc",
				"ATTR_desc": "GRM Desc"
			},
			{
				"ATTR_code": "subgrmdesc",
				"ATTR_columnName": "SubGrm Desc",
				"ATTR_desc": "SubGrm Desc"
			}
		]
	},
	{
		"DIM_code": "rep_articolo",
		"DIM_columnName": "REP - Articolo",
		"DIM_descripti')
    || ('on": "Rep Articolo",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 1)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "categoriadesc",
				"ATTR_columnName": "Categoria Desc",
				"ATTR_desc": "Categoria Desc"
			},
			{
				"ATTR_code": "grmdesc",
				"ATTR_columnName": "GRM Desc",
				"ATTR_desc": "GRM Desc"
			},
			{
				"ATTR_code": "subgrmdesc",
				"ATTR_columnName": "SubGrm Desc",
				"ATTR_desc": "SubGrm De')
    || ('sc"
			}
		]
	},
	{
		"DIM_code": "rep_fornitore",
		"DIM_columnName": "REP - Fornitore",
		"DIM_description": "Rep Fornitore",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_zonaPromo",
		"DIM_columnName": "REP - Zona Promo",
		"DIM_description": "')
    || ('Rep Zona Promo",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_sezioneTematica",
		"DIM_columnName": "REP - Sezione Tematica",
		"DIM_description": "Rep Sezione Tematica",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1F')
    || ('ILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_meccanicaSemplice",
		"DIM_columnName": "REP - Meccanica Semplice",
		"DIM_description": "Rep Meccanica Semplice",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)}, ASC)}}",
		"lis')
    || ('t_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_avolantino",
		"DIM_columnName": "REP - AVolantino",
		"DIM_description": "Rep AVolantino",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descr')
    || ('izione"
			}
		]
	},
	{
		"DIM_code": "rep_MisuraReportingArticoloZona",
		"DIM_columnName": "REP - Misura Reporting Articolo Zona",
		"DIM_description": "Rep Articolo Zona",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Reporting Articolo Zona] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_Misura')
    || ('ReportingArticolo",
		"DIM_columnName": "REP - Misura Reporting Articolo",
		"DIM_description": "Rep Articolo",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Reporting Articolo] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_reparto",
		"DIM_columnName": "REP - Reparto",
		"DIM_description": "Rep R')
    || ('eparto",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Reparto] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "fornitore",
		"DIM_columnName": "Fornitore",
		"DIM_description": "Fornitore",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)},')
    || (' ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "articoloFittizio",
		"DIM_columnName": "Articolo Fittizio",
		"DIM_description": "Articolo Fittizio",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo Fittizio] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				')
    || ('"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "compratore",
				"ATTR_columnName": "Compratore",
				"ATTR_desc": "Compratore"
			}
		]
	},
	{
		"DIM_code": "sezioneTematica",
		"DIM_columnName": "Sezione Tematica",
		"DIM_description": "Sezione Tematica",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"')
    || ('ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "scenario",
		"DIM_columnName": "Scenario",
		"DIM_description": "Scenario",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "misuraTimone",
		"DIM_columnName": "Misura Timone.",
		"DIM_description": "Misura",
')
    || ('		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},




	{
          "DIM_code": "storeSalesPlan",
          "DIM_columnName": "Store Sales Plan",
          "DIM_description": "Store Sales Plan",
          "ENDPOINT_serverName": "local",
          "MDX_jsonSource": "{{TM1SORT(')
    || (' {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Store Sales Plan] )}, 0)}, ASC)}}",
          "list_ATTR": [
              {
                  "ATTR_code": "Account",
                  "ATTR_columnName": "Account",
                  "ATTR_desc": "Account"
              }
          ]
      } ,
     {
          "DIM_code": "contratto",
          "DIM_columnName": "Contratto",
          "DIM_description": "Contratto",
          "ENDPOINT_serverName": "promo",
          "MDX_jsonSource": "{{TM1SORT( {TM1FILTER')
    || ('BYLEVEL( {TM1SUBSETALL( [Contratto] )}, 0)}, ASC)}}",
          "list_ATTR": [
              {
                  "ATTR_code": "descrizione",
                  "ATTR_columnName": "Descrizione",
                  "ATTR_desc": "Descrizione"
              }
          ]
      },
      {
              "DIM_code": "prestazione",
              "DIM_columnName": "Prestazione",
              "DIM_description": "Prestazione",
              "ENDPOINT_serverName": "promo",
              "MDX_jsonSource": "{')
    || ('{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}",
              "list_ATTR": [
                  {
                      "ATTR_code": "descrizione",
                      "ATTR_columnName": "Descrizione",
                      "ATTR_desc": "Descrizione"
                  }
              ]
      },
      {
              "DIM_code": "iniziativa",
              "DIM_columnName": "Iniziativa",
              "DIM_description": "Iniziativa",
              "ENDPOINT_serverNam')
    || ('e": "promo",
              "MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Iniziativa] )}, 0)}, ASC)}}",
              "list_ATTR": [
                  {
                      "ATTR_code": "Descrizione",
                      "ATTR_columnName": "Descrizione",
                      "ATTR_desc": "Descrizione"
                  }
              ]
      },
	{
		"DIM_code": "articolo",
		"DIM_columnName": "Articolo",
		"DIM_description": "Articolo",
		"ENDPOINT_serverName": "promo",
	')
    || ('	"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 1)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "categoriadesc",
				"ATTR_columnName": "Categoria Desc",
				"ATTR_desc": "Categoria Desc"
			},
			{
				"ATTR_code": "grmdesc",
				"ATTR_columnName": "GRM Desc",
				"ATTR_desc": "GRM Desc"
			},
			{
				"ATTR_code": "subgrmdesc",
				"ATTR_columnName": "SubGrm Desc",
				"ATTR_desc": "SubGrm Desc"
			},
			{
				"ATTR_code": "attivo",
				"ATTR_columnName": "')
    || ('Attivo",
				"ATTR_desc": "Attivo"
			}
		]
	},
	{
		"DIM_code": "misuraProgrammazioneFornitore",
		"DIM_columnName": "Misura Programmazione Fornitore",
		"DIM_description": "Misura Programmazione Fornitore",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Programmazione Fornitore] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "visualizzazione",
				"ATTR_columnName": "Visualizzazione",
				"ATTR_desc": "Visualizzazione"
			}')
    || ('
		]
	}
]
'),'GLOBAL');

insert into MUIPROMO.MENU_ROLES(ID_MENU,ID_ROLES) values (1,1);
insert into MUIPROMO.MENU_ROLES(ID_MENU,ID_ROLES) values (1,2);
insert into MUIPROMO.MENU_ROLES(ID_MENU,ID_ROLES) values (2,1);
insert into MUIPROMO.MENU_ROLES(ID_MENU,ID_ROLES) values (2,2);
-- ingrid filters

update MUIPROMO.users set ingrid_filters = '{"griglia_1": [{"dimension": "dimensione_1","attribute": "attributo_1","selected_values": ["valore1", "valore2", "valore3"]},{"dimension": "dimensione_n","attribute": "attributon","selected_values": ["valore1", "valore2", "valore3"]}],"griglia_m": [{"dimension": "dimensione_1","attribute": "attributo_1","selected_values": ["valore1", "valore2", "valore3"]},{"dimension": "dimensione_n","attribute": "attributon","selected_values": ["valore1", "valore2", "valore3"]}]}' where id = 1;
update MUIPROMO.users set ingrid_filters = '{"griglia_1": [{"dimension": "dimensione_1","attribute": "attributo_1","selected_values": ["valore1", "valore2", "valore3"]},{"dimension": "dimensione_n","attribute": "attributon","selected_values": ["valore1", "valore2", "valore3"]}],"griglia_m": [{"dimension": "dimensione_1","attribute": "attributo_1","selected_values": ["valore1", "valore2", "valore3"]},{"dimension": "dimensione_n","attribute": "attributon","selected_values": ["valore1", "valore2", "valore3"]}]}' where id = 2;

-- COPIA DELLA LISTA DEI CANALI

INSERT INTO MUIPROMO.CANALE_PROMOZIONE(ID,CODICE_CANALE,DESCRIZIONE,SIGLA_CANALE) VALUES(NEXT VALUE FOR MUIPROMO.CANALE_PROMOZIONE_ID_SEQ,14,'Precaricati su Carta','SPR');
INSERT INTO MUIPROMO.CANALE_PROMOZIONE(ID,CODICE_CANALE,DESCRIZIONE,SIGLA_CANALE) VALUES(NEXT VALUE FOR MUIPROMO.CANALE_PROMOZIONE_ID_SEQ,15,'Sconti per Classe Carta','SCC' );
INSERT INTO MUIPROMO.CANALE_PROMOZIONE(ID,CODICE_CANALE,DESCRIZIONE,SIGLA_CANALE) VALUES(NEXT VALUE FOR MUIPROMO.CANALE_PROMOZIONE_ID_SEQ,12,'Buoni Potenziamento','BP'      );
INSERT INTO MUIPROMO.CANALE_PROMOZIONE(ID,CODICE_CANALE,DESCRIZIONE,SIGLA_CANALE) VALUES(NEXT VALUE FOR MUIPROMO.CANALE_PROMOZIONE_ID_SEQ,13,'Buoni Categoria','BC'          );

-- CREAZIONE DI UN GRUPPO DI TEST
INSERT INTO MUIPROMO.GRUPPO (ID, codice_gruppo, DESCRIZIONE) VALUES (NEXT VALUE FOR MUIPROMO.GRUPPO_ID_SEQ,'DEV', 'DEVELOPMENT');
--CREAZIONE DI UNA ASSOCIAZIONE DI TEST : MANCA IL CANALE 3 VOLUTAMENTE
INSERT INTO MUIPROMO.GRUPPO_CANALE(ID_GRUPPO, ID_CANALE) VALUES (1, 1);
INSERT INTO MUIPROMO.GRUPPO_CANALE(ID_GRUPPO, ID_CANALE) VALUES (1, 2);
INSERT INTO MUIPROMO.GRUPPO_CANALE(ID_GRUPPO, ID_CANALE) VALUES (1, 4);
