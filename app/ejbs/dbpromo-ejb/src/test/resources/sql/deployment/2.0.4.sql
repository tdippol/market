alter table dbpromo.mui_crea_promozione add id_gruppo NUMBER(16,0);
update dbpromo.mui_crea_promozione a set a.id_gruppo = (select id_gruppo from dbpromo.mui_canale_promozione where id=a.id_canale) where a.id_canale is not null;

alter table dbpromo.MUI_UPLOAD_FILE_FIDATY ADD DATA_SOVRASCRITTURA TIMESTAMP;