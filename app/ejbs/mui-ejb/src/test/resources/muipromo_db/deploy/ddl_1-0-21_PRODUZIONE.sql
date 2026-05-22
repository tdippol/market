create table MUIPROMO.CONFIGURATION
(
    ID_CONFIGURATION int
        constraint CONFIGURATION_pk
            primary key,
    PATH varchar(150) not null,
    JSON CLOB not null,
    TYPE VARCHAR(10) not null
);

create unique index SQL190827184636951
    on MUIPROMO.CONFIGURATION (PATH, TYPE);