create table MUIPROMO.APPLICATION_PROPERTIES
(
    ID_APPLICATION_PROPERTIES INTEGER not null
        constraint APPLICATION_PROPERTIES_PK
            primary key,
    AP_KEY VARCHAR(50) not null,
    AP_VALUE VARCHAR(250) not null
);

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
    AUTH_TYPE VARCHAR(50) not null
);
