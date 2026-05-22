INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('115', '49', 'Storico Articolo Zona Promo', 'reporting/storicoArticoloZonaPromo.xhtml', 'agGrid', '9', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('116', '74', 'Aggiungi Articoli', 'pianificazione/aggiungiArticoli.xhtml', 'agGrid', '17', '0');

UPDATE MUIPROMO.MENU SET LABEL = 'Pianificazione' WHERE LABEL = 'Proiezione Promo';
UPDATE MUIPROMO.MENU SET LABEL = 'Articoli Fittizi',URL = 'proiezionePromo/articoliFittizi.xhtml' WHERE LABEL = 'Articoli Fittizzi';
UPDATE MUIPROMO.MENU SET LABEL = 'Differenziazione Zone',URL = 'proiezionePromo/differenziazioneZone.xhtml' WHERE LABEL = 'Differenziazione Promo';

UPDATE MUIPROMO.MENU SET URL = 'pianificazione/selezionePromo.xhtml' WHERE LABEL = 'Selezione Promo';
UPDATE MUIPROMO.MENU SET URL = 'pianificazione/selezioneArticoliContributi.xhtml' WHERE LABEL = 'Selezione Articoli Contributi';
UPDATE MUIPROMO.MENU SET URL = 'pianificazione/articoliFittizi.xhtml' WHERE LABEL = 'Articoli Fittizi';
UPDATE MUIPROMO.MENU SET URL = 'pianificazione/proiezioni.xhtml' WHERE LABEL = 'Proiezioni';
UPDATE MUIPROMO.MENU SET URL = 'pianificazione/meccanicheSet.xhtml' WHERE LABEL = 'Meccaniche Set';
UPDATE MUIPROMO.MENU SET URL = 'pianificazione/selezioneFamiglieContributi.xhtml' WHERE LABEL = 'Selezione Famiglie Contributi';

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (95, 1, 'Gestione Date', '', null, 17, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (96, 1, 'Riepilogo Piano Annuale', '', null, 18, 1);

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (97, 17, 'Gestione Timone', '', null, 7, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (98, 17, 'Gestione Timone Capo Reparto', '', null, 8, 1);

UPDATE MUIPROMO.MENU SET ORDER_ID = 8 WHERE LABEL LIKE 'Admin';
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (99, null, 'Controlli', null, null, 7, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (100, 99, 'Controlli Campagna MKTG', '', null, 1, 1);

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('101', '25', 'Target (CAT)', 'timone/fotoSpeciali/targetCat.xhtml', 'agGrid', '1', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('102', '25', 'Target (ACQ)', 'timone/fotoSpeciali/targetAcq.xhtml', 'agGrid', '1', '0');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('103', '28', 'Target Reparto (CAT)', 'timone/spaziCampagna/targetRepartoCat.xhtml', 'agGrid', '1', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('104', '28', 'Target Reparto (ACQ)', 'timone/spaziCampagna/targetRepartoAcq.xhtml', 'agGrid', '1', '0');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('105', '28', 'Target Categoria (CAT)', 'timone/spaziCampagna/targetCategoriaCat.xhtml', 'agGrid', '2', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('106', '28', 'Target Categoria (ACQ)', 'timone/spaziCampagna/targetCategoriaAcq.xhtml', 'agGrid', '2', '0');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('107', '22', 'Promo (CAT)', 'timone/targetCategoria/promoCat.xhtml', 'agGrid', '1', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('108', '22', 'Promo (ACQ)', 'timone/targetCategoria/promoAcq.xhtml', 'agGrid', '1', '0');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('109', '22', 'Data (CAT)', 'timone/targetCategoria/dataCat.xhtml', 'agGrid', '2', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('110', '22', 'Data (ACQ)', 'timone/targetCategoria/dataAcq.xhtml', 'agGrid', '2', '0');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('111', '19', 'Promo (CAT)', 'timone/targetReparto/promoCat.xhtml', 'agGrid', '1', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('112', '19', 'Promo (ACQ)', 'timone/targetReparto/promoAcq.xhtml', 'agGrid', '1', '0');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('113', '19', 'Data (CAT)', 'timone/targetReparto/dataCat.xhtml', 'agGrid', '2', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('114', '19', 'Data (ACQ)', 'timone/targetReparto/dataAcq.xhtml', 'agGrid', '2', '0');

INSERT INTO MUIPROMO.UI (ID, NAME, DESCRIPTION) VALUES (1, 'Piano Annuale -> Crea Promozione', '');
INSERT INTO MUIPROMO.UI (ID, NAME, DESCRIPTION) VALUES (2, 'Piano Annuale -> Modifica Promozione', '');
INSERT INTO MUIPROMO.UI (ID, NAME, DESCRIPTION) VALUES (3, 'Piano Annuale -> Crea Sezioni Tematiche', '');
INSERT INTO MUIPROMO.UI (ID, NAME, DESCRIPTION) VALUES (4, 'Piano Annuale -> Foto', '');
INSERT INTO MUIPROMO.UI (ID, NAME, DESCRIPTION) VALUES (5, 'Piano Annuale -> Gabbia', '');
INSERT INTO MUIPROMO.UI (ID, NAME, DESCRIPTION) VALUES (6, 'Piano Annuale -> Macrospazi', '');
INSERT INTO MUIPROMO.UI (ID, NAME, DESCRIPTION) VALUES (7, 'Piano Annuale -> Spazi Condivisi', '');
INSERT INTO MUIPROMO.UI (ID, NAME, DESCRIPTION) VALUES (8, 'Timone -> Associazione Promo', '');
INSERT INTO MUIPROMO.UI (ID, NAME, DESCRIPTION) VALUES (9, 'Timone -> Target Reparto Promo', '');
INSERT INTO MUIPROMO.UI (ID, NAME, DESCRIPTION) VALUES (10, 'Timone -> Target Reparto Data', '');
INSERT INTO MUIPROMO.UI (ID, NAME, DESCRIPTION) VALUES (11, 'Timone -> Spazi Campagna Reparto', '');

SET DEFINE OFF;
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('1','2','1','BUTTON','pia_cp_crea','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('2','3','1','BUTTON','pia_cp_crea','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('3','4','1','BUTTON','pia_cp_crea','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('4','5','1','BUTTON','pia_cp_crea','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('5','6','1','BUTTON','pia_cp_crea','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('6','2','2','BUTTON','pia_mp_aggiorna','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('7','3','2','BUTTON','pia_mp_aggiorna','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('8','4','2','BUTTON','pia_mp_aggiorna','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('9','5','2','BUTTON','pia_mp_aggiorna','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('10','6','2','BUTTON','pia_mp_aggiorna','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('11','2','3','BUTTON','pia_st_aggiorna','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('12','3','3','BUTTON','pia_st_aggiorna','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('13','4','3','BUTTON','pia_st_aggiorna','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('14','5','3','BUTTON','pia_st_aggiorna','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('15','6','3','BUTTON','pia_st_aggiorna','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('16','2','4','BUTTON','pia_fo_condividi','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('17','3','4','BUTTON','pia_fo_condividi','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('18','4','4','BUTTON','pia_fo_condividi','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('19','5','4','BUTTON','pia_fo_condividi','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('20','6','4','BUTTON','pia_fo_condividi','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('21','2','5','BUTTON','pia_ga_modifica','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('22','3','5','BUTTON','pia_ga_modifica','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('23','4','5','BUTTON','pia_ga_modifica','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('24','5','5','BUTTON','pia_ga_modifica','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('25','6','5','BUTTON','pia_ga_modifica','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('26','2','6','BUTTON','pia_ms_crea','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('27','3','6','BUTTON','pia_ms_crea','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('28','4','6','BUTTON','pia_ms_crea','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('29','5','6','BUTTON','pia_ms_crea','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('30','6','6','BUTTON','pia_ms_crea','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('31','2','6','BUTTON','pia_ms_aggiorna','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('32','3','6','BUTTON','pia_ms_aggiorna','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('33','4','6','BUTTON','pia_ms_aggiorna','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('34','5','6','BUTTON','pia_ms_aggiorna','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('35','6','6','BUTTON','pia_ms_aggiorna','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('36','2','7','BUTTON','pia_sc_condividi','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('37','3','7','BUTTON','pia_sc_condividi','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('38','4','7','BUTTON','pia_sc_condividi','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('39','5','7','BUTTON','pia_sc_condividi','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('40','6','7','BUTTON','pia_sc_condividi','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('41','2','8','BUTTON','tim_ap_associa','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('42','3','8','BUTTON','tim_ap_associa','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('43','4','8','BUTTON','tim_ap_associa','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('44','5','8','BUTTON','tim_ap_associa','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('45','6','8','BUTTON','tim_ap_associa','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('46','2','8','BUTTON','tim_ap_aggiorna','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('47','3','8','BUTTON','tim_ap_aggiorna','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('48','4','8','BUTTON','tim_ap_aggiorna','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('49','5','8','BUTTON','tim_ap_aggiorna','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('50','6','8','BUTTON','tim_ap_aggiorna','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('51','2','9','BUTTON','tim_trp_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('52','3','9','BUTTON','tim_trp_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('53','4','9','BUTTON','tim_trp_inizializza','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('54','5','9','BUTTON','tim_trp_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('55','6','9','BUTTON','tim_trp_inizializza','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('56','2','10','BUTTON','tim_trd_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('57','3','10','BUTTON','tim_trd_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('58','4','10','BUTTON','tim_trd_inizializza','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('59','5','10','BUTTON','tim_trd_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('60','6','10','BUTTON','tim_trd_inizializza','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('61','2','11','BUTTON','tim_scr_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('62','3','11','BUTTON','tim_scr_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('63','4','11','BUTTON','tim_scr_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('64','5','11','BUTTON','tim_scr_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('65','6','11','BUTTON','tim_scr_inizializza','1','0','1');


TRUNCATE TABLE MUIPROMO.CONFIGURATION;
SET DEFINE OFF;
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('95','/Piano_Annuale/Spazi/Spazi_Condivisi',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "spazio":["descrizione","compratore","macroSpazio", "reparto","macroSpazioDescrizione"],
    "compratore":["descrizione" , "repartodcodesc" , "categorymanager" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('102','/Reporting/Timone_Reparto',' {
	"rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
	"rep_misuraTimone":["descrizione"],
	"rep_scenario":["descrizione"],
	"rep_reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('105','/Timone/Foto_Speciali/Riepilogo',' {
    "promozione":["anno","canale","tipo","riferimento","semestre","descrizione","datainizioist"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('106','/Timone/Foto_Speciali/Target',' {
    "promozione":["anno","canale","tipo","riferimento","semestre","descrizione","datainizioist"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('107','/Timone/Spazi_Campagna/Target_Categoria',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
     "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('108','/Timone/Spazi_Campagna/Target_Reparto',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('109','/Timone/Target_Categoria/Data',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('110','/Timone/Target_Categoria/Promo',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('111','/Timone/Target_Reparto/Data',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('112','/Timone/Target_Reparto/Promo','{
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('113','/Timone/Associazione_Promo','{
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "promozioneriferimento":["anno","rif_canale","semestre","gruppo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('87','/Piano_Annuale/Visualizza_Piano','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('58','/welcome',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('89','/Piano_Annuale/Sezioni_Tematiche/Sezioni_Tematiche','{
     "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "pubblicita":["descrizione"]
} ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('91','/Piano_Annuale/Spazi/Macrospazi_Listino_Promo',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "macrospazio":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('78','/Piano_Annuale/Crea_Promozione',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('79','/Piano_Annuale/Foto',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "raggruppamentoFoto":["mui_descrizione","tot", "tots","mui_compratore" , "mui_reparto"],
    "compratore":["descrizione" , "repartodcodesc" , "categorymanager"  ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('80','/Piano_Annuale/Gabbia',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('81','/Piano_Annuale/Gestione_Contributi',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "tipoPromozione":["descrizione","gruppo"],
     "contratto":["descrizione"],
       "prestazione":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('83','/Piano_Annuale/Meccaniche',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('84','/Piano_Annuale/Modifica_Promozione',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('85','/Piano_Annuale/Negozi_Promo',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "negozio":["descrizione","zonaPromo" ],
    "canale":["descrizione"],
    "misuraCanale":["descrizione"],
    "sezioneTematica":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('88','/Piano_Annuale/Zone_Promo',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "zonaPromo":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('67','/Fatturazione/Associazioni_Articoli',' {
	"promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
	"fornitore":["descrizione"],
	"compratore":["categorymanager","repartodesc","reparto","descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('68','/Fatturazione/Contribuzione_Campagna',' {
	"promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
	"fornitore":["descrizione"],
	"compratore":["categorymanager","repartodesc","reparto","descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('69','/Fatturazione/Contribuzione_Promozioni',' {
	"promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
	"fornitore":["descrizione"],
	"compratore":["categorymanager","repartodesc","reparto","descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('70','/Pianificazione/Articoli_Fittizi',' {
	"promozione": ["anno","canale","tipo","riferimento","semestre","descrizione"],
    "compratore": ["descrizione"],
	"articoloFittizio":["descrizione","compratore"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('71','/Pianificazione/Differenziazione_Zone',' {
	"promozione": ["anno","canale","tipo","riferimento","semestre","descrizione"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"],
	"zonaPromo":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('4','/Admin/Gestione_Configurazioni','  {
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('5','/Admin/Gestione_Menu',' {
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('7','/Admin/Gestione_UI','  {
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('74','/Pianificazione/Selezione_Articoli_Contributi',' {
	"promozione": ["anno","canale","tipo","riferimento","semestre","descrizione"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"categoria": ["descrizione"],
	"fornitore": ["descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('75','/Pianificazione/Selezione_Famiglie_Contributi',' {
	"promozione": ["anno","canale","tipo","riferimento","semestre","descrizione"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"categoria": ["descrizione"],
	"fornitore": ["descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('96','/Reporting/Copia_in_Pianificazione',' {
	"rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
	"rep_compratore":["descrizione"],
	"rep_scenario":["descrizione"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
	"rep_fornitore":["descrizione"],
	"rep_sezioneTematica":["descrizione"],
	"rep_meccanicaSemplice":["descrizione"],
	"rep_avolantino":["descrizione"],
	"rep_MisuraReportingArticolo":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('97','/Reporting/Dettaglio_Campagna',' {
	"rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
	"rep_compratore":["descrizione"],
	"rep_scenario":["descrizione"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
	"rep_fornitore":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('114','/Reporting/Analisi_Budget-Venduto/Categoria','{
    "rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "rep_compratore":["descrizione"],
    "rep_categoria":["descrizione"],
    "rep_misuraTimone":["descrizione"],
    "rep_scenario":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('115','/Reporting/Analisi_Budget-Venduto/Categoria',TO_CLOB('{
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_AnalisiBudgetVenduto_Categoria",
			"logMemory": true,
			"logTime": true,
			"skip": true,
            "maxCells":1000000 ,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}" ,
						"REP - Categoria": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)} , ASC)}"
		')
    || TO_CLOB('			}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[TGT_ACQ]}",
						"REP - Misura Timone": "{[REP - Misura Timone].[Misura Timone - Timone]}"
					}
				},
				"FROM": "[Timone Reporting]",
				"WHERE": {
					"REP - Versione": "[UFF]')
    || TO_CLOB('"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"TGT_ACQ":{
')
    || TO_CLOB('						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericCol')
    || TO_CLOB('umn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "REP_minus_Categoria.Descrizione",
				"headerName": "Categoria",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
                {
					"headerName": "Totale Compratore",
					"field": "REP_minus_Compratore.MUI_TOTS",
					"width": 70,
					"hide": true,
					"rowG')
    || TO_CLOB('roup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
               {
					"headerName": "Caregory",
					"field": "REP_minus_Compratore.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},

              {
					"headerName": "Totale Categoria",
					"field": "REP_minus_Categoria.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": true,
')
    || TO_CLOB('					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Direzione",
					"field": "REP_minus_Categoria.MUI_DIR",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Reparto",
					"field": "REP_minus_Categoria.MUI_REP",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"')
    || TO_CLOB('type": [
						"TM1Element"
					]
				} ,
				{
					"headerName": "Categoria",
					"field": "REP_minus_Categoria.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Compratore",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Elemen')
    || TO_CLOB('t"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('116','/Reporting/Analisi_Budget-Venduto/Articolo','{
    "rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
    "rep_fornitore":["descrizione"],
    "rep_zonaPromo":["descrizione"],
    "rep_sezioneTematica":["descrizione"],
    "rep_meccanicaSemplice":["descrizione"],
    "rep_avolantino":["descrizione"],
    "rep_MisuraReportingArticoloZona":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('117','/Reporting/Analisi_Budget-Venduto/Articolo',TO_CLOB('{
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_AnalisiBudgetVenduto_Articolo",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":1000000 ,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tema')
    || TO_CLOB('tica": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
          						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL(')
    || TO_CLOB(' {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Zona Promo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1')
    || TO_CLOB('FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}')
    || TO_CLOB(', 0)} , ASC)}",
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT]}",
						"REP - Misura Reporting Articolo Zona": "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -BDGVend]}"
					}
				},
				"FROM": "[Reporting Articolo Zona]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
"UniqueName"
				],
				"Cells": [
					"Ordin')
    || TO_CLOB('al",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"')
    || TO_CLOB('TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": ')
    || TO_CLOB('true
				},
				"field": "REP_minus_Articolo.DescrizioneArticolo",
				"headerName": "Articolo",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
			    {
					"headerName": "Totale Articolo",
					"field": "REP_minus_Articolo.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				} ,
                {
					"headerName": "Reparto",
			')
    || TO_CLOB('		"field": "REP_minus_Articolo.RepartoDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				 } ,
                 {
					"headerName": "Categoria",
					"field": "REP_minus_Articolo.CategoriDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				  } ,
                 {
					"headerName": "GRM",
					"field": "RE')
    || TO_CLOB('P_minus_Articolo.GRMDesc",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				  } ,
				{
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.SubGrmDesc",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArt')
    || TO_CLOB('icolo",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
                {
					"headerName": "Fornitori",
					"field": "REP_minus_Fornitore.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Compratori",
					"field": "REP_minus_Compratore.Descrizione",
					')
    || TO_CLOB('"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Zona Promo",
					"field": "REP_minus_ZonaPromo.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
               {
					"headerName": "Sezione Tematica",
					"field": "REP_minus_SezioneTematica.Descrizione",
					"')
    || TO_CLOB('width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Meccanica",
					"field": "REP_minus_MeccanicaSemplice.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "A Volantino",
					"field": "REP_minus_AVolantino.Descrizione",
					"width')
    || TO_CLOB('": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('8','/Admin/Gestione_Utenti','  {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_GestioneUtenti",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1','/welcome',' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_Welcome",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}
','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('72','/Pianificazione/Meccaniche_Set',' {
	"promozione": ["anno","canale","tipo","riferimento","semestre","descrizione"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('73','/Pianificazione/Proiezioni',' {
	"promozione": ["anno","canale","tipo","riferimento","semestre","descrizione"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"categoria": ["descrizione"],
	"fornitore": ["descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('2','/Admin/Gestione_ACL',' {
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('3','/Admin/Gestione_Applicazione',' {
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('6','/Admin/Gestione_Ruoli',' {
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('9','/Fatturazione/Associazione_Articoli_Promozione',TO_CLOB('  {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_AssociazioneArticoliPromo_top",
			"logMemory": true,
			"logTime": true,
			"skip": true,
			"Rata": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata].[I-Rate Fatturazione Contr])}, 0)}, DESC)}}   }",
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, DESC)}}",
						"Compratore": "{{TM1SORT( {TM1FILTERB')
    || TO_CLOB('YLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
						"Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
						"Rata": "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) }, { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] }) }"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Misura Fatturazione": "{[Misura Fatturazione].[I-Fatturazione Contributi]}"
')
    || TO_CLOB('

					}
				},
				"FROM": "[Fatturazione]",
				"WHERE": {
					"Scenario": "[BDG]",
					"Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},
			"DynamicColumns": true,
			"DynamicColumnsSettings": {
				"headerconf": [
					"Descrizione"
				],
				"header')
    || TO_CLOB('defaults": {
					"marryChildren": true
				},
				"headerCustomTypes": {
					"RIF_MKT_DT": {
						"openByDefault": true
					},
					"TGT_ACQ": {
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"TGT_MKT": {
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP": {
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults": {
					"width": 100,
					"hide": false,
					"rowGr')
    || TO_CLOB('oup": false,
					"aggFunc": "sum",
					"columnGroupShow": "always",
					"editable": true,
					"type": [
						"TM1DataColumnText",
						"numericColumn"
					]
				},
				"childrenCustomTypes": {
					"LISTINO": {
						"type": [
							"TM1DataColumnNumber",
							"numericColumn"
						]
					},
					"APPLICATO": {
						"type": [
							"TM1DataColumnNumber",
							"numericColumn"
						]
					},
					"PRESTAZIONE": {
						"width": 300,
						"type": [
							"T')
    || TO_CLOB('M1DataColumnText"
						]
					},
					"CAUS": {
						"width": 300,
						"type": [
							"TM1DataColumnText"
						]
					},
					"DESC_FATT": {
						"width": 300,
						"type": [
							"TM1DataColumnText"
						]
					},
					"OK": {
						"width": 50,
						"type": [
							"TM1DataColumnText"
						]
					}
				}
			},
			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "Rata.Descrizione",
				"headerName": "Ra')
    || TO_CLOB('ta",
				"width": 400,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
				{
					"headerName": "Descrizione + Data",
					"field": "Promozione.Descrizione_plus_Data",
					"width": 400,
					"hide": false,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},{
					"headerName": "Canale",
					"field": "Promozione.Canale",
					"width": 100,
					"hide": true,
					"rowGroup": false,
					"edit')
    || TO_CLOB('able": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Anno",
					"field": "Promozione.Anno",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Semestre",
					"field": "Promozione.MUI_Semestre",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
	')
    || TO_CLOB('			{

					"headerName": "Descrizione",
					"field": "Promozione.Descrizione",
					"width": 400,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Riferimento",
					"field": "Promozione.Riferimento",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Totale Compratore",
		')
    || TO_CLOB('			"field": "Compratore.MUI_TOT",
					"width": 200,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Compratore",
					"field": "Compratore.Descrizione",
					"width": 200,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Totale Fornitore",
					"field": "Fornitore.MUI_TOTS",
					"width": 100')
    || TO_CLOB(',
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Fornitore",
					"field": "Fornitore.Descrizione",
					"width": 100,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Totale Rata",
					"field": "Rata.MUI_TOTS",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": ')
    || TO_CLOB('false,

					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Rata",
					"field": "Rata.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}
			]
		},
		{
			"name": "gc_AssociazioneArticoliPromo_bottom",
			"logMemory": true,
			"logTime": true,
			"skip": true,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{{')
    || TO_CLOB('TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, DESC)}}",
						"Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
						"Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
						"Rata": "{ [Rata].[(I) Fatturazione] }",
						"ArticoloNoSec": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ArticoloNoSec] )}, 0)}, DESC)}}",
						"Spazio Progressivo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [')
    || TO_CLOB('Spazio Progressivo] )}, 0)}, DESC)}}"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Misura Fatturazione": "{[Misura Fatturazione].[ASS_RATA],[Misura Fatturazione].[OK]}"
					}
				},
				"FROM": "[Fatturazione Articolo]",
				"WHERE": {
					"Scenario": "[BDG]",
					"Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
		')
    || TO_CLOB('			"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},
			"DynamicColumns": true,
			"DynamicColumnsSettings": {
				"headerconf": [
					"Descrizione"
				],
				"headerdefaults": {
					"marryChildren": true
				},
				"headerCustomTypes": {
					"RIF_MKT_DT": {
						"openByDefault": true
					},
					"TGT_ACQ": {
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"TGT_MKT": {
						"headerClass": "headerMkt",')
    || TO_CLOB('
						"openByDefault": true
					},
					"TGT_REP": {
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults": {
					"width": 200,
					"hide": false,
					"rowGroup": false,
					"aggFunc": "sum",
					"columnGroupShow": "always",
					"editable": true,
					"type": [
						"TM1DataColumnText"
					]
				},
				"childrenCustomTypes": {

				}
			},
			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": t')
    || TO_CLOB('rue

				},
				"field": "ArticoloNoSec.Descrizione",
				"headerName": "Articolo",
				"width": 400,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
				{
					"headerName": "Descrizione + Data",
					"field": "Promozione.Descrizione_plus_Data",
					"width": 400,
					"hide": false,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},{
					"headerName": "Compratore",
					"field": "Comprator')
    || TO_CLOB('e.Descrizione",
					"width": 200,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Canale",
					"field": "Promozione.Canale",
					"width": 100,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Anno",
					"field": "Promozione.Anno",
					"width": 70,
					"hide": true,
					"rowGroup": fa')
    || TO_CLOB('lse,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Semestre",
					"field": "Promozione.MUI_Semestre",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Descrizione",
					"field": "Promozione.Descrizione",
					"width": 400,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						')
    || TO_CLOB('"TM1Element"
					]
				},
				{
					"headerName": "Riferimento",
					"field": "Promozione.Riferimento",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Fornitore",
					"field": "Fornitore.Descrizione",
					"width": 200,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName":')
    || TO_CLOB(' "Rata",

					"field": "Rata.Descrizione",
					"width": 200,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Articolo",
					"field": "ArticoloNoSec.Descrizione",
					"width": 300,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Spazio",
					"field": "SpazioProgressivo.Descrizione",
		')
    || TO_CLOB('			"width": 200,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}
			]
		}
	]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('10','/Fatturazione/Associazioni_Articoli',TO_CLOB('  {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_AssociazioneArticoliPromo_top",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "Rata": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata].[I-Rate Fatturazione Contr])}, 0)}, DESC)}}   }",
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
 ')
    || TO_CLOB('           "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
            "Rata": "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) }, { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] }) }"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)')
    || TO_CLOB('},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Misura Fatturazione": "{[Misura Fatturazione].[I-Fatturazione Contributi]}"
          }
        },
        "FROM": "[Fatturazione]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
     ')
    || TO_CLOB('   "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "MUI_DescrizioneData",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDe')
    || TO_CLOB('fault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
       ')
    || TO_CLOB('   "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
            "TM1DataColumnText",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "LISTINO": {
            "type": [
              "TM1DataColumnNumber",
              "numericColumn"
            ]
          },
          "APPLICATO": {
            "type": [
              "TM1DataColumnNumber",
              "numericColumn"
   ')
    || TO_CLOB('         ]
          },
          "PRESTAZIONE": {
            "width": 300,
            "type": [
              "TM1DataColumnText"
            ]
          },
          "CAUS": {
            "width": 300,
            "type": [
              "TM1DataColumnText"
            ]
          },
          "DESC_FATT": {
            "width": 300,
            "type": [
              "TM1DataColumnText"
            ]
          },
          "OK": {
            "width": 50,
            ')
    || TO_CLOB('"type": [
              "TM1DataColumnText"
            ]
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Rata.Descrizione",
        "headerName": "Rata",
        "width": 400,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Totale Compratore",
          "field": "Compratore.MU')
    || TO_CLOB('I_TOT",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName"')
    || TO_CLOB(': "Totale Fornitore",
          "field": "Fornitore.MUI_TOTS",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Fornitore",
          "field": "Fornitore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
   ')
    || TO_CLOB('       ]
        },
        {
          "headerName": "Totale Rata",
          "field": "Rata.MUI_TOTS",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Rata",
          "field": "Rata.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type":')
    || TO_CLOB(' [
            "TM1Element"
          ]
        }
      ]
    },
    {
      "name": "gc_AssociazioneArticoliPromo_bottom",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}"')
    || TO_CLOB(',
            "Rata": "{ [Rata].[(I) Fatturazione] }",
            "ArticoloNoSec": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ArticoloNoSec] )}, 0)}, DESC)}}",
            "Spazio Progressivo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio Progressivo] )}, 0)}, DESC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Pr')
    || TO_CLOB('omozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Misura Fatturazione": "{[Misura Fatturazione].[ASS_RATA],[Misura Fatturazione].[OK]}"
          }
        },
        "FROM": "[Fatturazione Articolo]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
   ')
    || TO_CLOB('     ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "MUI_DescrizioneData",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
       ')
    || TO_CLOB('     "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 200,
          "hide": false,
          "rowGroup": f')
    || TO_CLOB('alse,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
            "TM1DataColumnText"
          ]
        },
        "childrenCustomTypes": {}
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "ArticoloNoSec.Descrizione",
        "headerName": "Articolo",
        "width": 400,
        "pinned": "left",
        "type": [
          "')
    || TO_CLOB('TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Fornitore",
          "field": "Fornitore.Descrizione",
          "width": 200,
          "hide": true,
          "rowGroup": ')
    || TO_CLOB('true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Rata",
          "field": "Rata.Descrizione",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo",
          "field": "ArticoloNoSec.Descrizione",
          "width": 300,
    ')
    || TO_CLOB('      "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Spazio",
          "field": "SpazioProgressivo.Descrizione",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('11','/Fatturazione/Contribuzione_Campagna',TO_CLOB(' {
	"connection": "promo",

	"configurations": [
		{
			"name": "gc_ContribuzioneCampagna",
			"logMemory": true,
			"logTime": true,
			"skip": true,
			"Rata": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata].[I-Rate Fatturazione Contr])}, 0)}, DESC)}}   }"  ,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
						"Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL')
    || TO_CLOB('( [Fornitore] )}, 0)}, DESC)}}",
						"Rata": "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) }, { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] }) }"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, DESC)}}",
						"Misura Fatturazione": "{[Misura Fatturazione].[I-Fatturazione Contributi]}"
					}
				},
				"FROM": "[')
    || TO_CLOB('Fatturazione]",
				"WHERE": {
					"Scenario": "[BDG]",
					"Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},


			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : [ "MUI_DescrizioneData" , "Descrizione"]  ,
				"headerdefaults":  {"marryChildren" : true}  ')
    || TO_CLOB(',
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":100,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1D')
    || TO_CLOB('ataColumnText"] },
				"childrenCustomTypes"  : { "LISTINO":{"type":[ "TM1DataColumnNumber" ,"numericColumn"]} ,
					"APPLICATO":{"type":[ "TM1DataColumnNumber" ,"numericColumn"]},
					"PRESTAZIONE":{"width":300 , "type":[ "TM1DataColumnText" ]},
					"CAUS":{"width":300 , "type":[ "TM1DataColumnText" ]},
					"DESC_FATT":{"width":300 , "type":[ "TM1DataColumnText" ]},
					"OK":{"width":50 , "type":[ "TM1DataColumnText" ]}
				}
			} ,


			"autoGroupColumnDef": {
				"cellRendererParams": {
')
    || TO_CLOB('					"suppressCount": true
				},
				"field": "Rata.Descrizione",
				"headerName": "Rata",
				"width": 400,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
				{
					"headerName": "Totale Compratore",
					"field": "Compratore.MUI_TOT",
					"width": 100,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Compratore",
					"field": "Compratore.Descrizione",
					"widt')
    || TO_CLOB('h": 100,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Totale Fornitore",
					"field": "Fornitore.MUI_TOTS",
					"width": 100,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Fornitore",
					"field": "Fornitore.Descrizione",
					"width": 100,
					"hide": true,
					"rowGroup": true,
					"editable": false,
')
    || TO_CLOB('					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Totale Rata",
					"field": "Rata.MUI_TOTS",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Rata",
					"field": "Rata.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}
			]
		}
	]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('12','/Fatturazione/Contribuzione_Promozioni',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_ContribuzionePromozioni",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "Rata": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata].[I-Rate Fatturazione Contr])}, 0)}, DESC)}}   }",
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_')
    || TO_CLOB('Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
            "Rata": "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) }, { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] }) }"
          ')
    || TO_CLOB('}
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Fatturazione": "{[Misura Fatturazione].[I-Fatturazione Contributi]}"
          }
        },
        "FROM": "[Fatturazione]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
         ')
    || TO_CLOB(' "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
        ')
    || TO_CLOB('    "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 110,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
        ')
    || TO_CLOB('    "TM1DataColumnText",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "LISTINO": {
            "type": [
              "TM1DataColumnNumber",
              "numericColumn"
            ]
          },
          "APPLICATO": {
            "type": [
              "TM1DataColumnNumber",
              "numericColumn"
            ]
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true')
    || TO_CLOB('
        },
        "field": "Rata.Descrizione",
        "headerName": "Rata",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
     ')
    || TO_CLOB('     "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Totale Fornitore",
          "field": "Fornitore.MUI_TOTS",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
   ')
    || TO_CLOB('       ]
        },
        {
          "headerName": "Fornitore",
          "field": "Fornitore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Totale Rata",
          "field": "Rata.MUI_TOTS",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
       ')
    || TO_CLOB('     "TM1Element"
          ]
        },
        {
          "headerName": "Rata",
          "field": "Rata.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('13','/Pianificazione/Articoli_Fittizi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_articoliFittizi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Articolo Fittizio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo Fittizio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura ')
    || TO_CLOB('Articolo Fittizio": "{[Misura Articolo Fittizio].[(I) Aggiornamento Articoli Fittizi]}"
          }
        },
        "FROM": "[Articoli Fittizi Aggiornamento]"
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "Dyn')
    || TO_CLOB('amicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {},
        "childrendefaults": {
          "cellClass": "cellClass-left-text",
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
')
    || TO_CLOB('

            "TM1DataColumnText",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "DataInizio": {
            "cellClass": "dateFormat",
            "type": [
              "TM1DataColumnDate",
              "numericColumn"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "DataFine": {
            "cellClass": "dateFormat",
            "type": [
              "TM1DataColumnDat')
    || TO_CLOB('e",
              "numericColumn"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "PRZ_ATT": {
            "type": [
              "TM1DataColumnNumber"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "CST": {
            "type": [
              "TM1DataColumnNumber"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          }
    ')
    || TO_CLOB('    }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "ArticoloFittizio.Descrizione",
        "headerName": "Articolo Fittizio",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Totale Fittizio",
          "field": "ArticoloFittizio.MUI_TO')
    || TO_CLOB('T",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "ArticoloFittizio.Compratore",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
       "preSelections": ')
    || TO_CLOB('[
        {
          "dimension": "Promozione",
          "attribute": "Descrizione + Data",
          "process": "",
          "paramName": "inPromo",
          "refresh": ["gc_articoliFittizi"],
          "configuration": {
            "configurations": [
              {
                "name": "Promozione",
                "logMemory": true,
                "logTime": true,
                "skip": true,
                "MDX": {
                  "ROWS": {
                    ')
    || TO_CLOB('"NON_EMPTY": true,
                    "DIMENSIONS": {
                      "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
                    }
                  },
                  "COLS": {
                    "NON_EMPTY": false,
                    "DIMENSIONS": {
                      "}ElementAttributes_Promozione": "{[}El')
    || TO_CLOB('ementAttributes_Promozione].[Descrizione + Data] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Promozione]"
                },
                "ExecuteMDX": {
                  "Members": [
                    "Name",
                    "Attributes",
                    "UniqueName"
                  ],
                  "Cells": [
                    "Ordinal",
                    "Value",
                    "Updateable",
         ')
    || TO_CLOB('           "Consolidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
              }
            ]
          }
        }
      ],
      "styleRules": {},
      "actions": [
        {
          "componentId": "btnCreaArticoloFittizioId",
          "componentLabel": "Crea Articolo Fittizio in Promo",
          "process": "MUI_DUMMY_DIM.Articolo Fittizio.Aggiornamento New iN Promo",
          "timeout": -1,
       ')
    || TO_CLOB('   "refresh": [
            "gc_articoliFittizi"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId": "btnEseguiAzioneArticoliFittiziId",
          "componentLabel": "Esegui Azione Su Articoli Fittizzi",
          "proc')
    || TO_CLOB('ess": "MUI_DUMMY_CUB.Articolo Fittizio.SpostaSuArtEff_new",
          "timeout": -1,
          "refresh": [
            "gc_articoliFittizi"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('14','/Pianificazione/Differenziazione_Zone',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_differenziazionePromo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "')
    || TO_CLOB('Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}",
            "Zona Promo": "{ EXCEPT({{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Zona Promo] )}, 0)}, ASC)}}, { [Zona Promo].[NA],[Zona Promo].[SOCIETA_1],[Zona Promo].[SOCIETA_2] })}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "')
    || TO_CLOB('Misura Promozione Pianificazione": "{[Misura Promozione Pianificazione Zone].[(I) Pianif promozione (zone)]}"
          }
        },
        "FROM": "[Promozione Pianificazione - Zone]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updatea')
    || TO_CLOB('ble",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {},
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "",
          "column')
    || TO_CLOB('GroupShow": "always",
          "editable": true,
          "type": [
            "TM1DataColumnText"
          ]
        },
        "childrenCustomTypes": {
          "PRZ_ATT_ANN": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_MIN": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_MAX": {
            "type": [
              "TM1DataColumnNumber"
            ]
      ')
    || TO_CLOB('    },
          "PRZ_ATT_USR": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "_perc__SC": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "VAL_SC": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_PROMO": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CST_AN": {
          ')
    || TO_CLOB('  "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CST_USR": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CONTR__perc__IN_FATT": {
            "type": [
              "TM1DataColumnPercentage"
            ]
          },
          "CST_C_IVA": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CST_PROMO_C_IVA": {
            "type": [
        ')
    || TO_CLOB('      "TM1DataColumnNumber"
            ]
          },
          "N_PEZZI": {
            "type": [
              "TM1DataColumnDecimal3"
            ]
          },
          "COLLI": {
            "type": [
              "TM1DataColumnDecimal3"
            ]
          },
          "TOT_dot_VEND": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "F_FATT": {
            "type": [
              "TM1DataColumnNumber"
            ]')
    || TO_CLOB('
          },
          "IVA": {
            "type": [
              "TM1DataColumnPercentage"
            ]
          },
          "RIFATT_BS": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "LIM_UTIL": {
            "type": [
              "TM1DataColumnNumber"
            ]
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "fiel')
    || TO_CLOB('d": "ZonaPromo.Descrizione",
        "headerName": "Zona Promo",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM')
    || TO_CLOB('1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Fornitore",
          "field": "Articolo.Fornitore",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "edit')
    || TO_CLOB('able": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo",
          "field": "Articolo.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Articolo.RepartoDesc",
          "width": 100,
          "hide": true,
')
    || TO_CLOB('
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Categoria",
          "field": "Articolo.CategoriaDesc",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Grm",
          "field": "Articolo.GRMDesc",
       ')
    || TO_CLOB('   "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Sub Grm",
          "field": "Articolo.SubGrmDesc",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "preSelections": [
        {
       ')
    || TO_CLOB('   "dimension": "Promozione",
          "attribute": "Descrizione + Data",
          "process": "",
          "paramName": "inPromo",
          "refresh": ["gc_differenziazionePromo"],
          "configuration": {
            "configurations": [
              {
                "name": "Promozione",
                "logMemory": true,
                "logTime": true,
                "skip": true,
                "MDX": {
                  "ROWS": {
                    "NON_EMPTY": tr')
    || TO_CLOB('ue,
                    "DIMENSIONS": {
                      "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
                    }
                  },
                  "COLS": {
                    "NON_EMPTY": false,
                    "DIMENSIONS": {
                      "}ElementAttributes_Promozione": "{[}ElementAttributes')
    || TO_CLOB('_Promozione].[Descrizione + Data] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Promozione]"
                },
                "ExecuteMDX": {
                  "Members": [
                    "Name",
                    "Attributes",
                    "UniqueName"
                  ],
                  "Cells": [
                    "Ordinal",
                    "Value",
                    "Updateable",
                    "Con')
    || TO_CLOB('solidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
              }
            ]
          }
        },
          {
          "dimension": "Compratore",
          "attribute": "Descrizione",
          "process": "",
          "paramName": "inCompratore",
          "refresh": ["gc_differenziazionePromo"],
          "configuration": {
            "configurations": [
              {
                "name": "Com')
    || TO_CLOB('pratore",
                "logMemory": true,
                "logTime": true,
                "skip": true,
                "MDX": {
                  "ROWS": {
                    "NON_EMPTY": true,
                    "DIMENSIONS": {
                      "Compratore": "{{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}}"
                    }
                  },
                  "COLS": {
                    "NON_EMPTY": false,
                    "DIMENSIONS": {
      ')
    || TO_CLOB('                "}ElementAttributes_Compratore": "{[}ElementAttributes_Compratore].[Descrizione] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Compratore]"
                },
                "ExecuteMDX": {
                  "Members": [
                    "Name",
                    "Attributes",
                    "UniqueName"
                  ],
                  "Cells": [
                    "Ordinal",
                    "Value')
    || TO_CLOB('",
                    "Updateable",
                    "Consolidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
              }
            ]
          }
        }
      ],
      "styleRules": {},
      "actions": [
        {
          "componentId": "btnInizializzaDifferenziazionePromoId",
          "componentLabel": "Inizializza",
          "process": "MUI_DUMMY_CUB.Promozione Pianificazione Zone (Inizializ')
    || TO_CLOB('za)",
          "timeout": -1,
          "refresh": [
            "gc_differenziazionePromo"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('15','/Pianificazione/Meccaniche_Set',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_meccanicheSet_creazione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
          ')
    || TO_CLOB('  "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "ID Set": "{[ID Set].[(I) Lista set]}",
            "ID RaggrSet": "{[ID RaggrSet].[(I) Configura set]}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Configurazione Promozione - Set": "{[Misura Configurazione Promozione - Set].[(I) Definizione set]}"
          }
        },
        "FROM": "[Configurazione Promo')
    || TO_CLOB('zione - Set Parametri]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": ')
    || TO_CLOB('[
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
            "TM1DataColumnText"
          ]
        },
        "headerCustomTypes": {},
        "childrenCustomTypes": {
          "VAL_SC": {
         ')
    || TO_CLOB('  "type": [
              "TM1DataColumnNumber"
            ]
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "IDRaggrSet.Descrizione",
        "headerName": "ID Raggr Set",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "heade')
    || TO_CLOB('rName": "Promozione",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "T')
    || TO_CLOB('M1Element"
          ]
        },
        {
          "headerName": "ID Set",
          "field": "IDSet.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "ID RaggrSet",
          "field": "IDRaggrSet.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editabl')
    || TO_CLOB('e": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "attribute": "Descrizione + Data",
          "process": "",
          "paramName": "inPromo",
          "refresh": ["gc_meccanicheSet_creazione","gc_meccanicheSet_associazione"],
          "configuration": {
            "configurations": [
              {
                "name": "Promozione",
                "log')
    || TO_CLOB('Memory": true,
                "logTime": true,
                "skip": true,
                "MDX": {
                  "ROWS": {
                    "NON_EMPTY": true,
                    "DIMENSIONS": {
                      "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
                    }
                  },
            ')
    || TO_CLOB('      "COLS": {
                    "NON_EMPTY": false,
                    "DIMENSIONS": {
                      "}ElementAttributes_Promozione": "{[}ElementAttributes_Promozione].[Descrizione + Data] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Promozione]"
                },
                "ExecuteMDX": {
                  "Members": [
                    "Name",
                    "Attributes",
                    "UniqueName"
  ')
    || TO_CLOB('                ],
                  "Cells": [
                    "Ordinal",
                    "Value",
                    "Updateable",
                    "Consolidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
              }
            ]
          }
        },
          {
          "dimension": "Compratore",
          "attribute": "Descrizione",
          "process": "",
          "paramName": "inCom')
    || TO_CLOB('pratore",
          "refresh": ["gc_meccanicheSet_creazione","gc_meccanicheSet_associazione"],
          "configuration": {
            "configurations": [
              {
                "name": "Compratore",
                "logMemory": true,
                "logTime": true,
                "skip": true,
                "MDX": {
                  "ROWS": {
                    "NON_EMPTY": true,
                    "DIMENSIONS": {
                      "Compratore": "{{{TM1FILTERBY')
    || TO_CLOB('LEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}}"
                    }
                  },
                  "COLS": {
                    "NON_EMPTY": false,
                    "DIMENSIONS": {
                      "}ElementAttributes_Compratore": "{[}ElementAttributes_Compratore].[Descrizione] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Compratore]"
                },
                "ExecuteMDX": {
                  "Members": [
   ')
    || TO_CLOB('                 "Name",
                    "Attributes",
                    "UniqueName"
                  ],
                  "Cells": [
                    "Ordinal",
                    "Value",
                    "Updateable",
                    "Consolidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
              }
            ]
          }
        }
      ],
      "styleRules": {}
    },
    {
')
    || TO_CLOB('      "name": "gc_meccanicheSet_associazione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{ EXCEPT( {TM1SUBSETALL( [Compratore] )}')
    || TO_CLOB(', { [Compratore].[NA], [Compratore].[S000] }) }}",
            "ID Set": "{[ID Set].[(I) Lista set]}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "ID RaggrSet": "{[ID RaggrSet].[(I) Configura set articoli]}",
            "Misura Configurazione Promozione - Set": "{[Misura Configurazione Promozione - Set].[(I) Set Articoli]}"
')
    || TO_CLOB('
          }
        },
        "FROM": "[Configurazione Promozione - Set]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
   ')
    || TO_CLOB('   "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {},
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
            "TM1DataColumnText"
          ]')
    || TO_CLOB('
        },
        "childrenCustomTypes": {
          "DataInizio": {
            "cellClass": "dateFormat",
            "type": [
              "TM1DataColumnDate",
              "numericColumn"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "DataFine": {
            "cellClass": "dateFormat",

            "type": [
              "TM1DataColumnDate",
              "numericColumn"
            ],
            "aggFunc": ')
    || TO_CLOB('"",
            "columnGroupShow": "always"
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Articolo.Descrizione",
        "headerName": "Articolo",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
  ')
    || TO_CLOB('        "field": "Promozione.Descrizione_plus_Data",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
')
    || TO_CLOB('        },
        {
          "headerName": "ID Set",
          "field": "IDSet.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo",
          "field": "Articolo.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": ')
    || TO_CLOB('[
            "TM1Element"
          ]
        }
      ],
      "styleRules": {}
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('16','/Pianificazione/Proiezioni',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_proiezioni_1",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{[Promozione].[(I) Promozione]}",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Categoria].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SO')
    || TO_CLOB('RT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(I) Scenario Timone Pianificazione]}",
            "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAF')
    || TO_CLOB('FALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }"
          }
        },
        "FROM": "[Timone Categoria II]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordi')
    || TO_CLOB('nal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Categoria.Descrizione",
        "headerName": "Categoria",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
')
    || TO_CLOB('
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass"')
    || TO_CLOB(': "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },

          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editab')
    || TO_CLOB('le": false,
          "type": [
            "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "N_FOTO_SPEC": {
            "type": [
              "TM1DataColumnText"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_ULT": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "tr')
    || TO_CLOB('ue",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_SCAFFALE": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_SCAFFALE_SPEC": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "column')
    || TO_CLOB('GroupShow": "always"
          },
          "CONTR": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "EXTRA_CONTR": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "D_ART_slash_TGT": {
    ')
    || TO_CLOB('        "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "D_FOTO_slash_TGT": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          }
        }
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
')
    || TO_CLOB('          "field": "Promozione.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        ')
    || TO_CLOB('},
        {
          "headerName": "Reparto",
          "field": "Categoria.MUI_RepartoDesc",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "attribute": "Descrizione + Data",
          "process": "",
          "paramName": "inPromo",
          "refresh": ["gc_pro')
    || TO_CLOB('iezioni_1","gc_proiezioni_2","gc_proiezioni_3"],
          "configuration": {
            "configurations": [
              {
                "name": "Promozione",
                "logMemory": true,
                "logTime": true,
                "skip": true,
                "MDX": {
                  "ROWS": {
                    "NON_EMPTY": true,
                    "DIMENSIONS": {
                      "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [P')
    || TO_CLOB('romozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
                    }
                  },
                  "COLS": {
                    "NON_EMPTY": false,
                    "DIMENSIONS": {
                      "}ElementAttributes_Promozione": "{[}ElementAttributes_Promozione].[Descrizione + Data] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Promozione')
    || TO_CLOB(']"
                },
                "ExecuteMDX": {
                  "Members": [
                    "Name",
                    "Attributes",
                    "UniqueName"
                  ],
                  "Cells": [
                    "Ordinal",
                    "Value",
                    "Updateable",
                    "Consolidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
             ')
    || TO_CLOB(' }
            ]
          }
        },
          {
          "dimension": "Compratore",
          "attribute": "Descrizione",
          "process": "",
          "paramName": "inCompratore",
          "refresh": ["gc_proiezioni_1","gc_proiezioni_2","gc_proiezioni_3"],
          "configuration": {
            "configurations": [
              {
                "name": "Compratore",
                "logMemory": true,
                "logTime": true,
                "skip": true,
 ')
    || TO_CLOB('               "MDX": {
                  "ROWS": {
                    "NON_EMPTY": true,
                    "DIMENSIONS": {
                      "Compratore": "{{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}}"
                    }
                  },
                  "COLS": {
                    "NON_EMPTY": false,
                    "DIMENSIONS": {
                      "}ElementAttributes_Compratore": "{[}ElementAttributes_Compratore].[Descrizione] }"
            ')
    || TO_CLOB('        }
                  },
                  "FROM": "[}ElementAttributes_Compratore]"
                },
                "ExecuteMDX": {
                  "Members": [
                    "Name",
                    "Attributes",
                    "UniqueName"
                  ],
                  "Cells": [
                    "Ordinal",
                    "Value",
                    "Updateable",
                    "Consolidated",
                    "HasPicklist",
 ')
    || TO_CLOB('                   "PicklistValues"
                  ]
                }
              }
            ]
          }
        }
      ],
      "styleRules": {}
    },
    {
      "name": "gc_proiezioni_2",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[')
    || TO_CLOB('Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Promozione Pianificazione": "({FILTER(TM1SUBSETALL([Misura Promozione Piani')
    || TO_CLOB('ficazione]),[}ElementAttributes_Misura Promozione Pianificazione].([}ElementAttributes_Misura Promozione Pianificazione].[Ordinamento])>0 )})"
          }
        },
        "FROM": "[Promozione Pianificazione]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "V')
    || TO_CLOB('alue",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroup')
    || TO_CLOB('Show": "always",
          "editable": true,
          "type": [
            "TM1DataColumnText"
          ]
        },
        "childrenCustomTypes": {
          "PRZ_ATT_ANN": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_MIN": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_MAX": {
            "type": [
              "TM1DataColumnNumber"
            ]
          }')
    || TO_CLOB(',
          "PRZ_ATT_USR": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "_perc__SC": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "VAL_SC": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_PROMO": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CST_AN": {
            "ty')
    || TO_CLOB('pe": [
              "TM1DataColumnNumber"
            ]
          },
          "CST_USR": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CONTR__perc__IN_FATT": {
            "type": [
              "TM1DataColumnPercentage"
            ]
          },
          "CST_C_IVA": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CST_PROMO_C_IVA": {
            "type": [
             ')
    || TO_CLOB(' "TM1DataColumnNumber"
            ]
          },
          "N_PEZZI": {
            "type": [
              "TM1DataColumnDecimal3"
            ]
          },
          "COLLI": {
            "type": [
              "TM1DataColumnDecimal3"
            ]
          },
          "TOT_dot_VEND": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "F_FATT": {
            "type": [
              "TM1DataColumnNumber"
            ]
   ')
    || TO_CLOB('       },
          "IVA": {
            "type": [
              "TM1DataColumnPercentage"
            ]
          },
          "RIFATT_BS": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "LIM_UTIL": {
            "type": [
              "TM1DataColumnNumber"
            ]
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "')
    || TO_CLOB('Articolo.Descrizione",
        "headerName": "Articolo",
        "cellClass": "cellClass-left-text",
        "width": 400,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element')
    || TO_CLOB('"
          ]
        },
        {
          "headerName": "Articolo(Codice)",
          "field": "Articolo.DescrizioneCODICE",
          "cellClass": "cellClass-left-text",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 100,
          "hide')
    || TO_CLOB('": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Fornitore",
          "field": "Articolo.Fornitore",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Articolo.Reparto')
    || TO_CLOB('Desc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Categoria",
          "field": "Articolo.CategoriaDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerNam')
    || TO_CLOB('e": "Grm",
          "field": "Articolo.GRMDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Sub Grm",
          "field": "Articolo.SubGrmDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
    ')
    || TO_CLOB('    }
      ],
      "styleRules": {}
    },
    {
      "name": "gc_proiezioni_3",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": ')
    || TO_CLOB('"{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Categoria].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(II) TCS I]}",
            "Sezione Tematica": "{{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSE')
    || TO_CLOB('TALL( [Sezione Tematica] )}, 0)}, { [Sezione Tematica].[ST_0000] }) }, { [Sezione Tematica].[ST_001] }) }, { [Sezione Tematica].[ST_042] }) }}",
            "Misura Timone.": "{[Misura Timone.].[(II)Timone Categoria Sez]}"
          }
        },
        "FROM": "[Timone Categoria Sezione I]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
    ')
    || TO_CLOB('    "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Categoria.Descrizione",
        "headerName": "Categoria",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM')
    || TO_CLOB('1Element"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
      ')
    || TO_CLOB('    },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
  ')
    || TO_CLOB('        "columnGroupShow": "always",
          "editable": false,
          "type": [
            "TM1DataColumnInteger"
          ]
        },
        "childrenCustomTypes": {
        }
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
       ')
    || TO_CLOB(' },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Categoria.RepartoDesc",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": ')
    || TO_CLOB('[
            "TM1Element"
          ]
        }
      ],
      "styleRules": {},
      "actions": [
        {
          "componentId": "btnSpostaArticoliId",
          "componentLabel": "Sposta Articoli",
          "process": "MUI_DUMMY_COPY.Promozione.Articolo+del",
          "timeout": -1,
          "refresh": [
            "gc_proiezioni_1"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
  ')
    || TO_CLOB('            "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId": "btnCopiaArticoliId",
          "componentLabel": "Copia Articoli",
          "process": "MUI_DUMMY_COPY.Promozione.Articolo",
          "timeout": -1,
          "refresh": [
            "gc_proiezioni_1"
          ],
          "params": [
            {
              "dimension": "Compratore",
    ')
    || TO_CLOB('          "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId": "btnClearId",
          "componentLabel": "Clear",
          "process": "MUI_DUMMY_CUB.Promozione Pianificazione Pulizia Articoli",
          "timeout": -1,
          "refresh": [
            "gc_proiezioni_1"
          ],
          "params": [
            {
')
    || TO_CLOB('              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "pCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId": "btnAbilitaFamigliaId",
          "componentLabel": "Abilita Famiglia",
          "process": "MUI_DUMMY_CUB.Famiglia.Programmazione Fornitore-Articolo.Caller",
          "timeout": -1,
          "refresh": [
          ')
    || TO_CLOB('  "gc_proiezioni"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId": "btnInizializzaId",
          "componentLabel": "Inizializza",
          "process": "MUI_DUMMY_CUB.Pianificazione Panieri.SPF.Caller",
          "ti')
    || TO_CLOB('meout": -1,
          "refresh": [
            "gc_proiezioni_1"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('17','/Pianificazione/Selezione_Articoli_Contributi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_selezioneArticoliContributi_targetCategoria",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{[Promozione].[(I) Promozione]}",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Categoria].[S000]}) }",
           ')
    || TO_CLOB(' "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(I) Scenario Timone Pianificazione]}",
            "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]}')
    || TO_CLOB(',{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }"
          }
        },
        "FROM": "[Timone Categoria II]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
    ')
    || TO_CLOB('    "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Categoria.Descrizione",
        "headerName": "Categoria",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM')
    || TO_CLOB('1Element"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT')
    || TO_CLOB('": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 80,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": ')
    || TO_CLOB('"always",
          "editable": false,
          "type": [
            "TM1DataColumnInteger"
          ]
        },
        "childrenCustomTypes": {
          "N_FOTO_SPEC": {
            "type": [
              "TM1DataColumnText"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_ULT": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true')
    || TO_CLOB('",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_SCAFFALE": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "SPZ_CAMP": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "a')
    || TO_CLOB('lways"
          },
          "MARGINE_LORDO__perc_": {
            "type": [
              "TM1DataColumnPercentage"
            ]
          }
        }
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "header')
    || TO_CLOB('Name": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Categoria.MUI_RepartoDesc",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
')
    || TO_CLOB('          ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "attribute": "Descrizione + Data",
          "process": "",
          "paramName": "inPromo",
          "refresh": ["gc_selezioneArticoliContributi_targetCategoria","gc_selezioneArticoliContributi_promozione"],
          "configuration": {
            "configurations": [
              {
                "name": "Promozione",
                "logMemory": true,
           ')
    || TO_CLOB('     "logTime": true,
                "skip": true,
                "MDX": {
                  "ROWS": {
                    "NON_EMPTY": true,
                    "DIMENSIONS": {
                      "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
                    }
                  },
                  "COLS": {
          ')
    || TO_CLOB('          "NON_EMPTY": false,
                    "DIMENSIONS": {
                      "}ElementAttributes_Promozione": "{[}ElementAttributes_Promozione].[Descrizione + Data] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Promozione]"
                },
                "ExecuteMDX": {
                  "Members": [
                    "Name",
                    "Attributes",
                    "UniqueName"
                  ],
       ')
    || TO_CLOB('           "Cells": [
                    "Ordinal",
                    "Value",
                    "Updateable",
                    "Consolidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
              }
            ]
          }
        },
          {
          "dimension": "Compratore",
          "attribute": "Descrizione",
          "process": "",
          "paramName": "inCompratore",
          "refre')
    || TO_CLOB('sh": ["gc_selezioneArticoliContributi_targetCategoria","gc_selezioneArticoliContributi_promozione"],
          "configuration": {
            "configurations": [
              {
                "name": "Compratore",
                "logMemory": true,
                "logTime": true,
                "skip": true,
                "MDX": {
                  "ROWS": {
                    "NON_EMPTY": true,
                    "DIMENSIONS": {
                      "Compratore": "{{{TM1FIL')
    || TO_CLOB('TERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}}"
                    }
                  },
                  "COLS": {
                    "NON_EMPTY": false,
                    "DIMENSIONS": {
                      "}ElementAttributes_Compratore": "{[}ElementAttributes_Compratore].[Descrizione] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Compratore]"
                },
                "ExecuteMDX": {
                  "Members": [')
    || TO_CLOB('
                    "Name",
                    "Attributes",
                    "UniqueName"
                  ],
                  "Cells": [
                    "Ordinal",
                    "Value",
                    "Updateable",
                    "Consolidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
              }
            ]
          }
        }
      ],
      "styleRules": {}
    },
  ')
    || TO_CLOB('  {
      "name": "gc_selezioneArticoliContributi_promozione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{[Promozione].[(I) Promozione]}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
    ')
    || TO_CLOB('        "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Programmazione Fornitore": "{[Misura Programmazione Fornitore].[(I) Programmazione Fornitore Articolo (Dinamico)]}"
          }
        },
        "FROM": "[Programmazione Fornitore - Articolo]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
')
    || TO_CLOB('        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_Descrizione",
          "Attributes/DescrizioneCODICE",
          "Attributes/RepartoDesc",
          "Attributes/CategoriaDesc",
          "Attributes/GRMDesc",
          "Attributes/SubGrmDesc",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
')
    || TO_CLOB('
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
')
    || TO_CLOB('          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
            "TM1DataColumnText')
    || TO_CLOB('"
          ]
        },
        "childrenCustomTypes": {
          "Selezione": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "Famiglia": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "IF (%)": {
            "type": [
              "TM1DataColum')
    || TO_CLOB('nNumber"
            ],
            "aggFunc": "avg",
            "columnGroupShow": "always"
          },
          "Tech_Contributi_A_M": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "Misura CICS": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          ')
    || TO_CLOB('"Memo Compratore": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "NOTE 1": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "NOTE 2": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "col')
    || TO_CLOB('umnGroupShow": "always"
          },
          "NOTE 3": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "Raggr. No Foto": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "Raggr. FF EC": {
            "type": [
              "TM1DataColumnText"
      ')
    || TO_CLOB('      ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "Raggr. Foto": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "_dot_T": {
            "type": [
              "TM1DataColumnText"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          }
        }
   ')
    || TO_CLOB('   },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Articolo.Descrizione",
        "headerName": "Articolo",
        "cellClass": "cellClass-left-text",
        "width": 400,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.MUI_Descrizione",
          "width": 100,
')
    || TO_CLOB('          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Fornitore",
          "field"')
    || TO_CLOB(': "Fornitore.Descrizione",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo(Codice)",
          "field": "Articolo.DescrizioneCODICE",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
')
    || TO_CLOB('        {
          "headerName": "Reparto",
          "field": "Articolo.RepartoDesc",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Categoria",
          "field": "Articolo.CategoriaDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
 ')
    || TO_CLOB('           "TM1Element"
          ]
        },
        {
          "headerName": "Grm",
          "field": "Articolo.GRMDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Sub Grm",
          "field": "Articolo.SubGrmDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "ed')
    || TO_CLOB('itable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo",
          "field": "Articolo.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "styleRules": {}
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('18','/Pianificazione/Selezione_Famiglie_Contributi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_selezioneFamiglieContributi_targetCategoria",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{[Promozione].[(I) Promozione]}",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Categoria].[S000]}) }",
           ')
    || TO_CLOB(' "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(I) Scenario Timone Pianificazione]}",
            "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]}')
    || TO_CLOB(',{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }"
          }
        },
        "FROM": "[Timone Categoria II]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
    ')
    || TO_CLOB('    "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Categoria.Descrizione",
        "headerName": "Categoria",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM')
    || TO_CLOB('1Element"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT')
    || TO_CLOB('": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 80,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": ')
    || TO_CLOB('"always",
          "editable": false,
          "type": [
            "TM1DataColumnInteger"
          ]
        },
        "childrenCustomTypes": {
          "N_FOTO_SPEC": {
            "type": [
              "TM1DataColumnText"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_ULT": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true')
    || TO_CLOB('",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_SCAFFALE": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_SCAFFALE_SPEC": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGr')
    || TO_CLOB('oupShow": "always"
          },
          "CONTR": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "EXTRA_CONTR": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "D_ART_slash_TGT": {
      ')
    || TO_CLOB('      "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "D_FOTO_slash_TGT": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "MARGINE_LORDO__perc_": {
            "type": [
              "TM1DataColumnPercent')
    || TO_CLOB('age"
            ]
          }
        }
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 70,
          "hide": true,
')
    || TO_CLOB('          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Categoria.MUI_RepartoDesc",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
        ')
    || TO_CLOB('  "attribute": "Descrizione + Data",
          "process": "",
          "paramName": "inPromo",
          "refresh": ["gc_selezioneFamiglieContributi_targetCategoria","gc_selezioneFamiglieContributi_promozione"],
          "configuration": {
            "configurations": [
              {
                "name": "Promozione",
                "logMemory": true,
                "logTime": true,
                "skip": true,
                "MDX": {
                  "ROWS": {
        ')
    || TO_CLOB('            "NON_EMPTY": true,
                    "DIMENSIONS": {
                      "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
                    }
                  },
                  "COLS": {
                    "NON_EMPTY": false,
                    "DIMENSIONS": {
                      "}ElementAttributes_Promozi')
    || TO_CLOB('one": "{[}ElementAttributes_Promozione].[Descrizione + Data] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Promozione]"
                },
                "ExecuteMDX": {
                  "Members": [
                    "Name",
                    "Attributes",
                    "UniqueName"
                  ],
                  "Cells": [
                    "Ordinal",
                    "Value",
                    "Updateable"')
    || TO_CLOB(',
                    "Consolidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
              }
            ]
          }
        },
          {
          "dimension": "Compratore",
          "attribute": "Descrizione",
          "process": "",
          "paramName": "inCompratore",
          "refresh": ["gc_selezioneFamiglieContributi_targetCategoria","gc_selezioneFamiglieContributi_promozione"],
          "conf')
    || TO_CLOB('iguration": {
            "configurations": [
              {
                "name": "Compratore",
                "logMemory": true,
                "logTime": true,
                "skip": true,
                "MDX": {
                  "ROWS": {
                    "NON_EMPTY": true,
                    "DIMENSIONS": {
                      "Compratore": "{{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}}"
                    }
                  },
                  "C')
    || TO_CLOB('OLS": {
                    "NON_EMPTY": false,
                    "DIMENSIONS": {
                      "}ElementAttributes_Compratore": "{[}ElementAttributes_Compratore].[Descrizione] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Compratore]"
                },
                "ExecuteMDX": {
                  "Members": [
                    "Name",
                    "Attributes",
                    "UniqueName"
                 ')
    || TO_CLOB(' ],
                  "Cells": [
                    "Ordinal",
                    "Value",
                    "Updateable",
                    "Consolidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
              }
            ]
          }
        }
      ],
      "styleRules": {}
    },
    {
      "name": "gc_selezioneFamiglieContributi_promozione",
      "logMemory": true,
      "logTime": true,
  ')
    || TO_CLOB('    "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{[Promozione].[(I) Promozione]}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        }')
    || TO_CLOB(',
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Programmazione Fornitore": "{[Misura Programmazione Fornitore].[(I) Programmazione Fornitore Articolo (Dinamico)]}"
          }
        },
        "FROM": "[Programmazione Fornitore - Articolo]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizion')
    || TO_CLOB('e",
          "Attributes/MUI_Famiglia",
          "Attributes/MUI_Descrizione",
          "Attributes/DescrizioneCODICE",
          "Attributes/RepartoDesc",
          "Attributes/CategoriaDesc",
          "Attributes/GRMDesc",
          "Attributes/SubGrmDesc",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
   ')
    || TO_CLOB('   "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
        },
        "childrendefaults": {
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
            "TM1DataColumn')
    || TO_CLOB('Text"
          ]
        },
        "childrenCustomTypes": {
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Articolo.Descrizione",
        "headerName": "Articolo",
        "cellClass": "cellClass-left-text",
        "width": 400,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozion')
    || TO_CLOB('e",
          "field": "Promozione.MUI_Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]')
    || TO_CLOB('
        },
        {
          "headerName": "Fornitore",
          "field": "Fornitore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Famiglia",
          "field": "Articolo.MUI_Famiglia",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
         ')
    || TO_CLOB(' "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo (Codice)",
          "field": "Articolo.DescrizioneCODICE",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Articolo.RepartoDesc",
          "width": 100,
          "hide": false,
     ')
    || TO_CLOB('     "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Categoria",
          "field": "Articolo.CategoriaDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Grm",
          "field": "Articolo.GRMDesc",
          "w')
    || TO_CLOB('idth": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Sub Grm",
          "field": "Articolo.SubGrmDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo",
        ')
    || TO_CLOB('  "field": "Articolo.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "styleRules": {}
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('19','/Pianificazione/Selezione_Promo',TO_CLOB('  {
  "connection" : "promo" ,
  "configurations": [
    {
      "name": "gc_listinoContributi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Tipo Promozione" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}",
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].')
    || TO_CLOB('[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Prestazione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false ,
          "DIMENSIONS": {
            "Misura Prestazioni": "{[Misura Prestazioni].[listino] }",
            "Contratto": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Contratto] )}, 0)}, ASC)}}"
          }
     ')
    || TO_CLOB('   },
        "FROM": "[Promozione Pianificazione Listino(I)]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "')
    || TO_CLOB('Prestazione.Descrizione"
      , "headerName": "Prestazione"
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Tipo Promozione","field":"TipoPromozione.Descrizione","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Promozione","field":"Promozione.Descrizione_plus_Data","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},')
    || TO_CLOB('

        {"headerName":"Listino",
          "marryChildren":true,
          "children" :
          [
            {"headerName":"Extra Contratto","field":"MisuraPrestazioni$listino$$Contratto$ExtraContratto","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnInteger"]} ,
            {"headerName":"Contratto","field":"MisuraPrestazioni$listino$$Contratto$Contratto_1","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnInteger')
    || TO_CLOB('"]}
          ]
        }


      ]
    ,"preSelections": [
        {
          "dimension": "Promozione",
          "attribute": "Descrizione + Data",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_listinoContributi","gc_timing"],
          "configuration": {
            "configurations": [
              {
                "name": "Promozione",
                "logMemory": true,
                "l')
    || TO_CLOB('ogTime": true,
                "skip": true,
                "MDX": {
                  "ROWS": {
                    "NON_EMPTY": true,
                    "DIMENSIONS": {
                      "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
                    }
                  },
                  "COLS": {
                 ')
    || TO_CLOB('   "NON_EMPTY": false,
                    "DIMENSIONS": {
                      "}ElementAttributes_Promozione": "{[}ElementAttributes_Promozione].[Descrizione + Data] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Promozione]"
                },
                "ExecuteMDX": {
                  "Members": [
                    "Name",
                    "Attributes",
                    "UniqueName"
                  ],
              ')
    || TO_CLOB('    "Cells": [
                    "Ordinal",
                    "Value",
                    "Updateable",
                    "Consolidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
              }
            ]
          }
        }
      ],
      "styleRules":{		}
    },

    {
      "name": "gc_timing",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS')
    || TO_CLOB('": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Versione": "{[Versione].[UFF]}" ,
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MisuraPromozioneProprietà": "{[Misura Promozione Prop')
    || TO_CLOB('rietà].[Misura 1]}"
          }
        },
        "FROM": "[Configurazione Promozione - Proprietà]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "')
    || TO_CLOB('suppressCount": true }
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName": "Promozione"
      , "cellClass": "cellClass-left-text"
      , "width":400
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.An')
    || TO_CLOB('no","width":70,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":150,"hide":false,"rowGroup": false , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]}')
    || TO_CLOB(',
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio Las","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Las","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine Las","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Las","width":')
    || TO_CLOB('100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Inizio Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Freschi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Freschi","width":100,"hide":false,"rowGroup": false , "editable')
    || TO_CLOB('": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Inizio DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_inizio_DRO_slash_GEM","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_fine_DRO_slash_GEM","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat",')
    || TO_CLOB(' "type":[ "TM1DataColumnDate"]},
        {"headerName":"Raccolta Proiezioni","field":"MisuraPromozionePropriet_agrave_$1_pubblicazione","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Pre-Presentazione CS","field":"MisuraPromozionePropriet_agrave_$data_riunione_commerciale","width":100,"hide":false,"rowGroup": false , "editable": false,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
')
    || TO_CLOB('
        {"headerName":"Data Scadenza Conferma Prezzi","field":"MisuraPromozionePropriet_agrave_$data_scadenza_conferma_prezzi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Pianificazione TM1/GPR","field":"MisuraPromozionePropriet_agrave_$resp_mktg1","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Volantino/Piano Media","field":"')
    || TO_CLOB('MisuraPromozionePropriet_agrave_$resp_mktg2","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Data Inizio","field":"MisuraPromozionePropriet_agrave_$DataInizio","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine","field":"MisuraPromozionePropriet_agrave_$DataFine","width":100,"hide":false,"rowGroup": false , "editable": true,"')
    || TO_CLOB('cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Inizio Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_ist_dot_","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Fine_ist_dot_","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type"')
    || TO_CLOB(':[ "TM1DataColumnDate"]},
        {"headerName":"Data abb. prezzi","field":"MisuraPromozionePropriet_agrave_$Data_abb_prezzi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Valore Punto Fragola","field":"MisuraPromozionePropriet_agrave_$ValorePuntoFragola","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnDecimal3"]}

      ]
    ,
      "styleRules":{		}
    ')
    || TO_CLOB('}
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('20','/Piano_Annuale/Controllo_Negozi',TO_CLOB(' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_ControlloNegozi",
			"logMemory": true,
			"logTime": true,
			"skip": true,
      "MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
						"Reparto": "{{TM1SORT( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)}, ASC)}}"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Scenario": "{{TM')
    || TO_CLOB('1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
						"Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]}}"
					}
				},
				"FROM": "[Timone Reparto]",
				"WHERE": {
					"Versione": "[Ufficiale]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPic')
    || TO_CLOB('klist",
					"PicklistValues"
				]
			},
			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "Reparto.Descrizione",
				"headerName": "Reparto",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
				{
					"headerName": "Canale",
					"field": "Promozione.Canale",
					"width": 100,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}')
    || TO_CLOB(',
				{
					"headerName": "Anno",
					"field": "Promozione.Anno",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Semestre",
					"field": "Promozione.MUI_Semestre",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Descrizione",
					"field": "Promozione.Descrizione",
					"widt')
    || TO_CLOB('h": 400,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Descrizione + Data",
					"field": "Promozione.Descrizione_plus_Data",
					"width": 400,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Reparto",
					"field": "Reparto.Descrizione",
					"width": 120,
					"hide": true,
					"rowGroup": false,
					"edita')
    || TO_CLOB('ble": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Riferimento",
					"field": "Promozione.Riferimento",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Tipo Elemento",
					"field": "Reparto.TipoElemento",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"h')
    || TO_CLOB('eaderName": "Riferimento Data",
					"openByDefault": true,
					"children": [
						{
							"headerName": "N. Art",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
							"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "Totale",
							"field": "Scenario$RI')
    || TO_CLOB('F_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
							"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGrou')
    || TO_CLOB('pShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Banco",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
					')
    || TO_CLOB('	{
							"headerName": "N. Foto Speciali",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Spec. Banco",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
							"widt')
    || TO_CLOB('h": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Ultima",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
	')
    || TO_CLOB('						"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "Venduto Promo (s/iva)",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "ML %"')
    || TO_CLOB(',
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnPercentage",
								"numericColumn"
							]
						}
					]
				},
				{
					"headerName": "Target MKT",
					"headerClass": "headerMkt",
					"openByDefault": true,
					"children": [
						{
							"headerName": "N. Art",
		')
    || TO_CLOB('					"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
							"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "Totale",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
							"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "')
    || TO_CLOB('sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						')
    || TO_CLOB('},
						{
							"headerName": "N. Foto Banco",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Speciali",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
							"width": 80,
	')
    || TO_CLOB('						"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Spec. Banco",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true')
    || TO_CLOB(',
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Ultima",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						}
					]
				},
				{
					"headerName": "Target REP",')
    || TO_CLOB('
					"headerClass": "headerRep",
					"openByDefault": true,
					"children": [
						{
							"headerName": "N. Art",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
							"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "Totale",
							"field": "Scenario$TGT')
    || TO_CLOB('_REP$$MisuraTimone_dot_$TOT_FOTO",
							"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow":')
    || TO_CLOB(' "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Banco",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							')
    || TO_CLOB('"headerName": "N. Foto Speciali",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Spec. Banco",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
							"width": 80,
							"')
    || TO_CLOB('hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Ultima",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
			')
    || TO_CLOB('					"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "D Foto/tgt Mkt",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "D Foto Banco/tgt Mkt",
							"fi')
    || TO_CLOB('eld": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						}
					]
				},
				{
					"headerName": "Target Acq.",
					"headerClass": "headerAcq",
					"openByDefault": true,
					"children": [
						{
							"headerName": "N. Art",
							"field')
    || TO_CLOB('": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
							"width": 70,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "Totale",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
							"width": 70,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							')
    || TO_CLOB('"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
							"width": 80,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
			')
    || TO_CLOB('				"headerName": "N. Foto Banco",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
							"width": 80,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Speciali",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
							"width": 80,
							"hide": t')
    || TO_CLOB('rue,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Spec. Banco",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
							"width": 80,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": ')
    || TO_CLOB('[
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Ultima",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
							"width": 80,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "D Foto/tgt Rep",
							"field": "Scena')
    || TO_CLOB('rio$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
							"width": 80,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "D Foto Banco/tgt Rep",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
							"width": 80,
							"hide": true,
							"rowGroup": false,
		')
    || TO_CLOB('					"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						}
					]
				}
			]
		}
	]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('21','/Piano_Annuale/Crea_Promozione',TO_CLOB(' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_CreaPromozione",
			"logMemory": true,
			"logTime": true,
			"skip": true,
      "MDX": {
              "ROWS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "}Clients": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}Clients] )}, 0)}, ASC)}}",
                      "Anno": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Anno] )}, 0)}, DESC)}}",
                     ')
    || TO_CLOB(' "ID": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID] )}, 0)}, ASC)}}"
                  }
              },
              "COLS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "MisuraPianoOperativoCommerciale": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Piano Operativo Commerciale] )}, 0)}, ASC)}}"
                  }
              },
              "FROM": "[Promozione Creazione]"

          },
        "ExecuteMDX": {
')
    || TO_CLOB('                     "Members": [
                        "Name",
                        "Attributes",
                       "UniqueName"
                              ] ,
                      "Cells": [
                        "Ordinal",
                        "Value",
                        "Updateable",
                        "Consolidated" ,
                        "HasPicklist",
                        "PicklistValues"
                      ]
                    },

   ')
    || TO_CLOB('     "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                                 , "field": "ID.Name"
                                 , "headerName": "ID"
                                 , "width":200
                                 , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
              {"headerName":"Anno","field":"Anno.Name","widt')
    || TO_CLOB('h":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
              {"headerName":"Client","field":"_cbrace_Clients._cbrace_TM1_DefaultDisplayValue","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"ID","field":"ID.Name","width":80,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","w')
    || TO_CLOB('idth":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Desc","field":"MisuraPianoOperativoCommerciale$Desc","width":500,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]} ,
              {"headerName":"Gruppo","field":"MisuraPianoOperativoCommerciale$TipoPromozione","width":120,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]} ,
              {"headerName":"Sottogr')
    || TO_CLOB('uppo","field":"MisuraPianoOperativoCommerciale$Canale","width":120,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]} ,
              {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]} ,
              {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":120,"hide":fa')
    || TO_CLOB('lse,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio Ist.","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]} ,
              {"headerName":"Data Fine Ist.","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":120,"hide":false')
    || TO_CLOB(',"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Operazione","field":"MisuraPianoOperativoCommerciale$Operazione","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}
              ],
          "actions": [{
            "componentId": "pia_cp_crea",
            "componentLabel": "Crea Promozione",
            "process": "MUI_DUMMY_DIM.Promozione.NewPromo",')
    || TO_CLOB('
            "timeout":-1,
            "refresh": ["gc_CreaPromozione"],
            "params":[{
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            }]
          }]
      }
     ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('22','/Piano_Annuale/Foto',TO_CLOB('{
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Foto_foto",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": false,
      "suppressRowClickSelection": false,
      "maxCells": 2000000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Raggruppamento Foto": "{{TM1SORTBYINDEX({{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Raggrupp')
    || TO_CLOB('amento Foto] )}, 0)}}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "}ElementAttributes_Raggruppamento Foto": "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Raggruppamento Foto] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[}ElementAttributes_Raggruppamento Foto]",
        "WHERE": {
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
         ')
    || TO_CLOB(' "UniqueName",
          "Attributes/MUI_TOTS",
          "Attributes/MUI_Descrizione",
          "Attributes/MUI_Compratore",
          "Attributes/MUI_Reparto"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {},
      "columnDefs": [
        {
          "headerName": "Reparto",
          "field": "Raggruppa')
    || TO_CLOB('mentoFoto.MUI_Reparto",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "RaggruppamentoFoto.MUI_Compratore",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
  ')
    || TO_CLOB('      {
          "headerName": "Raggruppamento Foto",
          "field": "RaggruppamentoFoto.MUI_TOTS",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Foto",
          "field": "RaggruppamentoFoto.MUI_Descrizione",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": fals')
    || TO_CLOB('e,
          "type": [
            "TM1Element"
          ]
        }

      ],
      "selections": [
        {
          "source": {
            "table": "gc_Foto_foto",
            "dimension": "RaggruppamentoFoto",
            "attribute": "MUI_Descrizione"
          },
          "destination": [
            {
              "table": "gc_Foto_foto",
              "dimension": "RaggruppamentoFoto",
              "attribute": "MUI_Descrizione"
            }
          ')
    || TO_CLOB(']
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "attribute": "Descrizione + Data",
          "process": "",
          "paramName": "inPromo",
          "refresh": ["gc_Foto_associazione"],
          "configuration": {
            "configurations": [
              {
                "name": "Promozione",
                "logMemory": true,
                "logTime": true,
                "skip": true,
                "MDX": {
')
    || TO_CLOB('                  "ROWS": {
                    "NON_EMPTY": true,
                    "DIMENSIONS": {
                      "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
                    }
                  },
                  "COLS": {
                    "NON_EMPTY": false,
                    "DIMENSIONS": {
            ')
    || TO_CLOB('          "}ElementAttributes_Promozione": "{[}ElementAttributes_Promozione].[Descrizione + Data] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Promozione]"
                },
                "ExecuteMDX": {
                  "Members": [
                    "Name",
                    "Attributes",
                    "UniqueName"
                  ],
                  "Cells": [
                    "Ordinal",
                    "Valu')
    || TO_CLOB('e",
                    "Updateable",
                    "Consolidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
              }
            ]
          }
        }
      ]
    },
    {
      "name": "gc_Foto_compratore",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": false,
      "suppressRowClickSelection": false,
')
    || TO_CLOB('      "maxCells": 2000000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Compratore": "{ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , { {[Compratore].[S000] }, {[Compratore].[NA] }})} , [Compratore].[MUI_Sort] , BASC )}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "}ElementAttributes_Compratore": "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL')
    || TO_CLOB('( {TM1SUBSETALL( [}ElementAttributes_Compratore] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[}ElementAttributes_Compratore]",
        "WHERE": {
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "UniqueName",
          "Attributes"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
 ')
    || TO_CLOB('     "autoGroupColumnDef": {},
      "columnDefs": [
        {
          "headerName": "Category Manager",
          "field": "Compratore.CategoryManager",
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Compratore.MUI_Reparto",
          "width": 100,
          "hide": false,
          "r')
    || TO_CLOB('owGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.MUI_Descrizione",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "selections": [
        {
          "source": {
            "table": "gc_Foto_c')
    || TO_CLOB('ompratore",
            "dimension": "Compratore",
            "attribute": "Descrizione"
          },
          "destination": [
            {
              "table": "gc_Foto_compratore",
              "dimension": "Compratore",
              "attribute": "Descrizione"
            }
          ]
        }
      ]
    },
    {
      "name": "gc_Foto_associazione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 1000000,
      "maxCellsMess')
    || TO_CLOB('age": "Aggiungere il filtro sulla Promozione.",
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": false,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Raggruppamento Foto": "{{TM1SORTBYINDEX({FILTER( {{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Raggruppamento Foto] )}, 0)}}, [Raggruppamento Foto].[MUI_NRFOTO] <= 30.000000)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSI')
    || TO_CLOB('ONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{ [Compratore].[TOT_COMP], [Compratore].[S22]},{ EXCEPT( {TM1SUBSETALL( [Compratore] )}, { [Compratore].[NA], [Compratore].[S000], [Compratore].[TOT_COMP], [Compratore].[S22]}) }}"
          }
        },
        "FROM": "[Configurazione Ra')
    || TO_CLOB('ggrFoto]",
        "WHERE": {
          "Misura Config RaggrFoto": "[OK]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": ')
    || TO_CLOB('[
          "MUI_DescrizioneData",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_R')
    || TO_CLOB('EP": {
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
            "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
        }
      },
      "autoGro')
    || TO_CLOB('upColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "cellClass": "cellClass-left-text",
        "field": "RaggruppamentoFoto.Descrizione",
        "headerName": "Raggruppamento Foto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Tot Foto",
          "field": "RaggruppamentoFoto.MUI_TOT",
          "width": 70,
   ')
    || TO_CLOB('       "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Tot Foto by",
          "field": "RaggruppamentoFoto.MUI_TOTS",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "actions": [
        {
          "componentId": ')
    || TO_CLOB('"pia_fo_condividi",
          "componentLabel": "Condividi",
          "process": "MUI_DUMMY_CUB.ConfigurazioneRaggFoto",
          "timeout": -1,
          "refresh": [
            "gc_Foto_associazione"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribute": "Descrizione + Data",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": false,
              "disabled": true,')
    || TO_CLOB('
              "visible": true
            },
            {
              "dimension": "RaggruppamentoFoto",
              "attribute": "MUI_Descrizione",
              "paramName": "inFoto",
              "label": "Foto",
              "hasPicklist": false,
              "disabled": true,
              "visible": true
            },
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
        ')
    || TO_CLOB('      "label": "Compratore",
              "hasPicklist": false,
              "disabled": true,
              "visible": true
            }
          ]
        }
      ]
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('23','/Piano_Annuale/Gabbia',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Gabbia",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
          }
        },
   ')
    || TO_CLOB('     "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Piano Operativo Commerciale": "{[Misura Piano Operativo Commerciale].[Costo_punto_Fragola]}"
          }
        },
        "FROM": "[Promozione Aggiornamento]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Conso')
    || TO_CLOB('lidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName": "Promozione"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"')
    || TO_CLOB('type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descr')
    || TO_CLOB('izione + Data","field":"Promozione.Descrizione_plus_Data","width":150,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Valore Punto Fragola","field":"MisuraPianoOperativoCommerciale$Costo_punto_Fragola","width":150,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnDecimal3" ,"numericColumn"]}
      ]
    },
    {
      "name": "gc_GabbiaPunti",
      "logMemory": true,
      "logTime": true,
      "skip": true,
     ')
    || TO_CLOB(' "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "ID": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID] )}, 0)}, ASC)}}"

          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Gabbia Punti Fragola": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Gabbia Punti Fragola] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Gabbia Punti Fragola]"

      },
      ')
    || TO_CLOB('"ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
"UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },



      "autoGroupColumnDef" :  {

      } ,
      "columnDefs": [
        {"headerName":"ID","field":"ID.Name","width":100,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Eleme')
    || TO_CLOB('nt"]},
        {"headerName":"Prezzo Minimo","field":"MisuraGabbiaPuntiFragola$PrezzoMinimo","width":100,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnNumber" ,"numericColumn"]},
        {"headerName":"Prezzo Massimo","field":"MisuraGabbiaPuntiFragola$PrezzoMassimo","width":100,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnNumber" ,"numericColumn"]},
        {"headerName":"Punti Fragola","field":"MisuraGabbiaPuntiFragola$PuntiFragola","wid')
    || TO_CLOB('th":100,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnNumber" ,"numericColumn"]}
      ],
      "actions": [{
            "componentId": "pia_ga_modifica",
            "componentLabel": "Modifica Valore Punti Fragola",
            "process": "MUI_DUMMY_DIM.Cambio valore Costo Punti Fragola",
            "timeout":-1,
            "refresh": ["gc_Gabbia","gc_GabbiaPunti"],
            "params":[{
              "dimension": "Promozione",
              "attribute')
    || TO_CLOB('": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "inPromo",
              "label": "Descrizione",
              "hasPicklist": true
            }]
          }]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('24','/Piano_Annuale/Gestione_Contributi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_GestioneContributi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Tipo Promozione"')
    || TO_CLOB(': "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}"

          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Contratto": "{[Contratto].[Extra Contratto],[Contratto].[Contratto_1] }",
            "Prestazione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}",
            "Misura Prestazioni": "[Misura Prestazioni].[Listino]"
          }
        },
        "FROM": "[Promozione Modifica ')
    || TO_CLOB('- Listino]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione" ,"Descrizione" ,"Descrizione"   ]  ,
        "headerdefaults":  {"marryC')
    || TO_CLOB('hildren" : true}  ,
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT":{
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150')
    || TO_CLOB(',"hide":false,"rowGroup": false ,"aggFunc": "avg", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "DataInizio":{"cellClass": "dateFormat","type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"},
          "DataFine":{"cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"}
        }
      } ,

      "autoGr')
    || TO_CLOB('oupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName": "Promozione"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Tipo Promozione","field":"TipoPromozione.Caption_Default","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width"')
    || TO_CLOB(':100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":400,"hide":true,"rowGroup": false , "editable": false,"')
    || TO_CLOB('type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]}
      ],
      "groupRowAggNodes": {
        "nodeGroupTrue" : "false",
        "nodeGroupFalse" : "false"
      }
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('25','/Piano_Annuale/Gestione_Iniziative',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_GestioneIniziative",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Iniziativa": "{')
    || TO_CLOB('{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Iniziativa] )}, 0)}, ASC)}}"

          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Configurazione Iniziative": "{[Misura Configurazione Iniziative].[OK] }"
          }
        },
        "FROM": "[Configurazione Promozione - Iniziative]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }

      },
      "ExecuteMDX": {
        "Members": [
  ')
    || TO_CLOB('        "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },



      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Iniziativa.Descrizione"
      , "headerName": "Iniziativa"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
  ')
    || TO_CLOB('    } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","')
    || TO_CLOB('field":"Promozione.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":150,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"OK","field":"MisuraConfigurazioneIniziative$OK","width":70,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnInteger" ,"numericColumn"]}
      ],
      "groupRowAggN')
    || TO_CLOB('odes": {
        "nodeGroupTrue" : "false",
        "nodeGroupFalse" : "false"
      }

    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('26','/Piano_Annuale/Meccaniche',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Meccaniche",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Configurazione Promozione": "{[Misura Configurazione Promozione].[SEL]}" ,
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}}')
    || TO_CLOB(' , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Meccanica Complesse": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Meccanica Complesse] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Configurazione Promozione - Meccaniche]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueNa')
    || TO_CLOB('me"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName": "Promozione"
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
')
    || TO_CLOB('        {"headerName":"Canale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-center-text", "type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":60,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"cellClass": "')
    || TO_CLOB('cellClass-center-text","type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":150,"hide":true,"rowGroup": false , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},

        {
        "headerName": "Meccaniche Se')
    || TO_CLOB('mplici",
        "openByDefault": true,
        "children": [

        {"headerName":"Sconto %","field":"MeccanicaComplesse$M002","width":70,"hide":false,"rowGroup": false , "editable": true,"cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Prezzo Corto","field":"MeccanicaComplesse$M003","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"P. Fragola')
    || TO_CLOB('","field":"MeccanicaComplesse$M004","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc % Fidaty","field":"MeccanicaComplesse$M005","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Prezzo Corto & P.Fragola","field":"MeccanicaComplesse$M006","width":110,"hide":false,"rowGroup": false , "e')
    || TO_CLOB('ditable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc% Fidaty  &  P. Fragola","field":"MeccanicaComplesse$M007","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"3x2","field":"MeccanicaComplesse$M008","width":50,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
      ')
    || TO_CLOB('  {"headerName":"1 + 1","field":"MeccanicaComplesse$M009","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Il 2 costa la metà","field":"MeccanicaComplesse$M010","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto % + Punti Fragola","field":"MeccanicaComplesse$M012","width":110,"hide"')
    || TO_CLOB(':false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc + Facile val","field":"MeccanicaComplesse$M014","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Oggetti articolo","field":"MeccanicaComplesse$M015","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type')
    || TO_CLOB('":["TM1DataColumnText"]},
        {"headerName":"Buono sc.Fidaty (F11)","field":"MeccanicaComplesse$M018","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc Val Fidaty","field":"MeccanicaComplesse$M023","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto fidaty per classe cliente (')
    || TO_CLOB('%)","field":"MeccanicaComplesse$M024","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"PC + Triplica Punti","field":"MeccanicaComplesse$M025","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Punti Fragola su insieme","field":"MeccanicaComplesse$M104","width":110,"hide":false,"rowGroup": ')
    || TO_CLOB('false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Triplica i punti","field":"MeccanicaComplesse$M111","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Punti Fragola Set per Triplica Punti","field":"MeccanicaComplesse$M164","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","ty')
    || TO_CLOB('pe":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sconto a valore","field":"MeccanicaComplesse$M031","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - punti fragola","field":"MeccanicaComplesse$M034","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"h')
    || TO_CLOB('eaderName":"Buono Sconto Categoria - sconto percentuale","field":"MeccanicaComplesse$M035","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sc val - Set semplice","field":"MeccanicaComplesse$M131","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Cate')
    || TO_CLOB('goria - PF - Set semplice","field":"MeccanicaComplesse$M134","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sc % - Set semplice","field":"MeccanicaComplesse$M135","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Set Semplice per Triplica Oggetti","field":"Mecca')
    || TO_CLOB('nicaComplesse$M165","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]} ,
        {"headerName":"PAGINA PUBBLICITARIA","field":"MeccanicaComplesse$M000","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Meccanica NO Promo con Spazio","field":"MeccanicaComplesse$M090","width":170,"hide":false,"rowGroup": false , "ed')
    || TO_CLOB('itable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]}

        ]} ,

        {
        "headerName": "Meccaniche Complesse",
        "openByDefault": true,
        "children": [

        {"headerName":"SSc + Facile Set","field":"MeccanicaComplesse$M114","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Oggetti Set","field":"MeccanicaComplesse$M115","width"')
    || TO_CLOB(':110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto SET a valore","field":"MeccanicaComplesse$M201","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto SET %","field":"MeccanicaComplesse$M205","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-')
    || TO_CLOB('text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto Set Esteso Valore","field":"MeccanicaComplesse$M301","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Set Esteso - prezzo corto","field":"MeccanicaComplesse$M303","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Set Este')
    || TO_CLOB('so Punti Fragola","field":"MeccanicaComplesse$M304","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto Percentuale Set Esteso","field":"MeccanicaComplesse$M305","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]}

         ]}
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('27','/Piano_Annuale/Modifica_Promozione',TO_CLOB(' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_ModificaPromozione",
			"logMemory": true,
			"logTime": true,
			"skip": true,
      "MDX": {
              "ROWS": {
                  "NON_EMPTY": true,
                  "DIMENSIONS": {
                    "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
   ')
    || TO_CLOB('               }
              },
              "COLS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "MisuraPianoOperativoCommerciale": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Piano Operativo Commerciale] )}, 0)}, ASC)}}"
                  }
              },
              "FROM": "[Promozione Aggiornamento]"
          },
        "ExecuteMDX": {
                     "Members": [
                        "Name",
         ')
    || TO_CLOB('               "Attributes",
                        "UniqueName"
                              ] ,
                      "Cells": [
                        "Ordinal",
                        "Value",
                        "Updateable",
                        "Consolidated" ,
                        "HasPicklist",
                        "PicklistValues"
                      ]
                    } ,
        "autoGroupColumnDef" :  {
                                   "cellRende')
    || TO_CLOB('rerParams":{ "suppressCount": true }
                                 , "field": "Promozione.Descrizione_plus_Data"
                                 , "headerName": "Promozione"
                                 , "width":300
                                 , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
              {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "')
    || TO_CLOB('editable": false,"type":["TM1Element"]},
              {"headerName":"Canale","field":"Promozione.Canale","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]} ,
              {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":150,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Descrizione","field":"Promozione.Descrizione","width":70,"hide":true,"rowGroup": false , "editable"')
    || TO_CLOB(': false,"type":["TM1Element"]},
              {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Element"]},
              {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":85,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Operazione","field":"MisuraPianoOperativoCommerciale$Operazione","width":90,"hide":false,"rowGroup": false , "editab')
    || TO_CLOB('le": true, "type":[ "TM1DataColumnText" ]},
              {"headerName":"Descrizione","field":"MisuraPianoOperativoCommerciale$Descrizione","width":300,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText" ]},
              {"headerName":"Descrizione.","field":"MisuraPianoOperativoCommerciale$Desc_Agg","width":300,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText" ]},
              {"headerName":"Data Inizio","field":"MisuraPianoOperativo')
    || TO_CLOB('Commerciale$DataInizio","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio.","field":"MisuraPianoOperativoCommerciale$DataInizio_Agg","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","widt')
    || TO_CLOB('h":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine.","field":"MisuraPianoOperativoCommerciale$DataFine_Agg","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio Ist.","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":110,"hide":')
    || TO_CLOB('false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio Ist","field":"MisuraPianoOperativoCommerciale$DataInizioIst_Agg","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine Ist.","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":110,"hide":false,"r')
    || TO_CLOB('owGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine Ist","field":"MisuraPianoOperativoCommerciale$DataFineIst_Agg","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Abbatt Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":200,"hide":false,"rowGroup": fals')
    || TO_CLOB('e , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Abbatt Prezzi_","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ_AGG","width":200,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":200,"hide":false,"rowGroup": false , "editable')
    || TO_CLOB('": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Ordine Timone.","field":"MisuraPianoOperativoCommerciale$Ordinamento_Agg","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"No Stampa %_","field":"MisuraPi')
    || TO_CLOB('anoOperativoCommerciale$ETICH_SENZA__perc__AGG","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"No Stampa Etic","field":"MisuraPianoOperativoCommerciale$NO_STMP_ETICH","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"CanalePromozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":150,"hide":false,"rowGroup": false , "editable": true')
    || TO_CLOB(', "type":[ "TM1DataColumnText"]},
              {"headerName":"StatoPromozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"TipoPromozione","field":"MisuraPianoOperativoCommerciale$TipoPromozione","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Note","field":"MisuraPianoOperativoCommerci')
    || TO_CLOB('ale$Note","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Note Marketing","field":"MisuraPianoOperativoCommerciale$NoteMarketing","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}

            ],
            "actions": [{
            "componentId": "pia_mp_aggiorna",
            "componentLabel": "Aggiorna Promozione",
            "process": "MUI_DUMMY_DIM.Promozione.Upd')
    || TO_CLOB('atePromo (I)",
            "timeout":-1,
            "refresh": ["gc_ModificaPromozione"],
            "params":[{
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
     ')
    || TO_CLOB('         "hasPicklist": true
            }]
          }]
        }
     ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('28','/Piano_Annuale/Negozi_Promo',TO_CLOB(' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_NegoziPromo",
			"logMemory": true,
			"logTime": true,
			"skip": true,
			 "MDX": {
              "ROWS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "Negozio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}}"
                  }
              },
              "COLS": {
                  "NON_EMPTY": true,
                  "DIMENSIONS": {
             ')
    || TO_CLOB('       "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
                    "Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, ASC)}}" ,
                    "Canale": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Canale] )}, 0)}, ASC)}}",
                    "Misura Canale": "{ [Misura Canale].[T')
    || TO_CLOB('ipo_Neg_Calc], [Misura Canale].[Tipo_Neg_Input], [Misura Canale].[Canale], [Misura Canale].[DataInizio], [Misura Canale].[DataFine],[Misura Canale].[Delta] }"



                  }
              },
              "FROM": "[Canale Neg SezTematica]"

          },
        "ExecuteMDX": {
                     "Members": [
                        "Name",
                        "Attributes",
                        "UniqueName"
                              ] ,
                      "Cells": [
      ')
    || TO_CLOB('                  "Ordinal",
                        "Value",
                        "Updateable",
                        "Consolidated" ,
                        "HasPicklist",
                        "PicklistValues"
                      ]
                    },

          "DynamicColumns" : true ,
          "DynamicColumnsSettings" :{
              "headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" , "Descrizione"]  ,
              "headerdefaults":  {"marryChildren" : t')
    || TO_CLOB('rue}  ,
            "headerCustomTypes":{
              "RIF_MKT_DT":{
                "openByDefault": true
              },
              "TGT_ACQ":{
                "headerClass": "headerAcq",
                "openByDefault": true
              },
              "TGT_MKT":{
                "headerClass": "headerMkt",
                "openByDefault": true
              },
              "TGT_REP":{
                "headerClass": "headerRep",
                "openByDefault": true
              }
')
    || TO_CLOB('            },
              "childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnText"] },
              "childrenCustomTypes"  : {
                                   "DataInizio":{"cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"},
                                   "DataFine":{"cellClass": "dateFormat", "type":[ "TM1DataColumnDa')
    || TO_CLOB('te" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"}
                               }
              } ,

        "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                                 , "field": "Negozio.Descrizione"
                                 , "headerName": "Negozio"
                                 , "width":300
                                 , "pinned": "left"
                                 , "type":[')
    || TO_CLOB('"TM1Element"]
                          } ,
        "columnDefs": [
          {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Elem')
    || TO_CLOB('ent"]},
          {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":200,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]},
          {"headerName":"Zona Promo","field":"Negozio.MUI_ZonaPromo","width":150,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Negozio","field":"Negozio.Descrizione","width":150,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]}



              ')
    || TO_CLOB(']
            ,
			"rowSuppressionEnabled": true,
			"colSuppressionEnabled": false
		}
	]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('29','/Piano_Annuale/Timing',TO_CLOB('{
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Timing",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Versione": "{[Versione].[UFF]}" ,
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Pr')
    || TO_CLOB('omozione],BASC)}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MisuraPromozioneProprietà": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Promozione Proprietà] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Configurazione Promozione - Proprietà]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
  ')
    || TO_CLOB('        "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

        "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                                 , "field": "Promozione.Descrizione_plus_Data"
                                 , "headerName": "Promozione"
                                 , "width":500
                     ')
    || TO_CLOB('            , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
          {"headerName":"Canale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Semestre","field":"Promozione.MUI_Semestr')
    || TO_CLOB('e","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Riferimento","field":"Promozione.Riferimento","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Data Inizio Las","field":"MisuraPromozionePropriet_ag')
    || TO_CLOB('rave_$Data_Inizio_Las","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine Las","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Las","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Inizio Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Freschi","width":110,"hide":false,"r')
    || TO_CLOB('owGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Freschi","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Inizio DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_inizio_DRO_slash_GEM","width":110,"hide":false,"rowGroup": false , "editable": true,"')
    || TO_CLOB('cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_fine_DRO_slash_GEM","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Raccolta Proiezioni","field":"MisuraPromozionePropriet_agrave_$1_pubblicazione","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1D')
    || TO_CLOB('ataColumnDate"]},
          {"headerName":"Data Pre-Presentazione CS","field":"MisuraPromozionePropriet_agrave_$data_riunione_commerciale","width":150,"hide":false,"rowGroup": false , "editable": false,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Scadenza Conferma Prezzi","field":"MisuraPromozionePropriet_agrave_$data_scadenza_conferma_prezzi","width":150,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataCo')
    || TO_CLOB('lumnDate"]},
          {"headerName":"Pianificazione TM1/GPR","field":"MisuraPromozionePropriet_agrave_$resp_mktg1","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Volantino/Piano Media","field":"MisuraPromozionePropriet_agrave_$resp_mktg2","width":120,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Data Inizio","field":"MisuraPromozionePropriet_agrave_$DataInizio","')
    || TO_CLOB('width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine","field":"MisuraPromozionePropriet_agrave_$DataFine","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Inizio Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_ist_dot_","width":110,"hide":false,"rowGroup": false , "editabl')
    || TO_CLOB('e": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Fine_ist_dot_","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data abb. prezzi","field":"MisuraPromozionePropriet_agrave_$Data_abb_prezzi","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type"')
    || TO_CLOB(':[ "TM1DataColumnDate"]},
          {"headerName":"Valore Punto Fragola","field":"MisuraPromozionePropriet_agrave_$ValorePuntoFragola","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnDecimal3"]}

      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('30','/Piano_Annuale/Visualizza_Piano',TO_CLOB(' {
  "connection": "promo",

  "configurations": [
    {
      "name": "gc_VisualizzaPiano",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "pagination": false ,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione')
    || TO_CLOB('],BASC)}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MisuraPianoOperativoCommerciale": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Piano Operativo Commerciale] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Promozione Aggiornamento]"
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "O')
    || TO_CLOB('rdinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      } ,
      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName": "Promozione"
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Anno","field":"Promozione.An')
    || TO_CLOB('no","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]} ,
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true,"')
    || TO_CLOB('rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":120,"hide":false,"rowGroup": fal')
    || TO_CLOB('se , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Inizio Ist","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellCl')
    || TO_CLOB('ass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine Ist","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Abbatt.Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "t')
    || TO_CLOB('ype":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":80,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa Etic","field":"MisuraPianoOperativoComm')
    || TO_CLOB('erciale$NO_STMP_ETICH","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Canale Promozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Stato Promozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":170,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
  ')
    || TO_CLOB('      {"headerName":"Note Marketing","field":"MisuraPianoOperativoCommerciale$NoteMarketing","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('31','/Piano_Annuale/Zone_Promo',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_ZonePromo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Configurazione Promozione": "{[Misura Configurazione Promozione].[SEL]}" ,
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione')
    || TO_CLOB('].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Meccanica Complesse": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Zona Promo] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Configurazione Promozione - Zone]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          ')
    || TO_CLOB('"Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName": "Promozione"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width"')
    || TO_CLOB(':100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":400,"hide":true,"rowGroup": false , "editable": false,"')
    || TO_CLOB('type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]} ,
        {"headerName":"Zona BDG","field":"ZonaPromo$ZONAPROMO_ALL","width":90,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_01] MILANO","field":"ZonaPromo$ZONAPROMO_1_01","width":125,"hide":false,"rowGroup": false , "editab')
    || TO_CLOB('le": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_02] EMILIA","field":"ZonaPromo$ZONAPROMO_1_02","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_03] PIEMONTE","field":"ZonaPromo$ZONAPROMO_1_03","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_04] VERONA","field":"ZonaPromo$ZONAPROM')
    || TO_CLOB('O_1_04","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_01] FIRENZE","field":"ZonaPromo$ZONAPROMO_2_01","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_02] LAZIO","field":"ZonaPromo$ZONAPROMO_2_02","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]}

      ]')
    || TO_CLOB('
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('32','/Piano_Annuale/Sezioni_Tematiche/Sezioni_Tematiche',TO_CLOB('{
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_SezioniTematiche_SezioniTematiche",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
 ')
    || TO_CLOB('           "Pubblicità": "{[Pubblicità].[(I) Volantino]}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Zona Promo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Zona Promo] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Configurazione Promozione - Sezione Tematica]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
      ')
    || TO_CLOB('  ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Pubblicit_agrave_.Descrizione"
      , "headerName": ""
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"C')
    || TO_CLOB('anale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":50,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","w')
    || TO_CLOB('idth":100,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione Sezione Tematica","field":"MisuraZonaPromo$Descrizione","width":170,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Canale","field":"MisuraZonaPromo$Canale","width":70,"h')
    || TO_CLOB('ide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Agg_canale","field":"MisuraZonaPromo$Agg_canale","width":90,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"DataInizio","field":"MisuraZonaPromo$DataInizio","width":80,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"DataFine","field":"MisuraZonaPromo$Data')
    || TO_CLOB('Fine","width":70,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Zona BDG","field":"MisuraZonaPromo$ZONAPROMO_ALL","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_01] MILANO","field":"MisuraZonaPromo$ZONAPROMO_1_01","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"')
    || TO_CLOB(']},
        {"headerName":"[1_02] EMILIA","field":"MisuraZonaPromo$ZONAPROMO_1_02","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_03] PIEMONTE","field":"MisuraZonaPromo$ZONAPROMO_1_03","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_04] VERONA","field":"MisuraZonaPromo$ZONAPROMO_1_04","width":70,"hide":false,"rowGr')
    || TO_CLOB('oup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_01] FIRENZE","field":"MisuraZonaPromo$ZONAPROMO_2_01","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_02] LAZIO","field":"MisuraZonaPromo$ZONAPROMO_2_02","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"Check","fi')
    || TO_CLOB('eld":"MisuraZonaPromo$Check","width":70,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]}

      ]
    }
  ]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('33','/Piano_Annuale/Sezioni_Tematiche/Crea_Sezioni_Tematiche',TO_CLOB(' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_SezioniTematiche_CreaSezioniTematiche",
			"logMemory": true,
			"logTime": true,
			"skip": true,
      "MDX": {
              "ROWS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "ID Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID Sezione Tematica] )}, 0)}, ASC)}}"
                  }
              },
              "COLS": {
                ')
    || TO_CLOB('  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "Misura Configurazione Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Configurazione Sezione Tematica] )}, 0)}, ASC)}}"
                  }
              },
              "FROM": "[Configurazione Sezione Tematica]"

          },
        "ExecuteMDX": {
                     "Members": [
                        "Name",
                        "Attributes",
						"UniqueName"
       ')
    || TO_CLOB('                       ] ,
                      "Cells": [
                        "Ordinal",
                        "Value",
                        "Updateable",
                        "Consolidated" ,
                        "HasPicklist",
                        "PicklistValues"
                      ]
                    },

        "autoGroupColumnDef" :  {   } ,
        "columnDefs": [
          {"headerName":"ID Sezione Tematica","field":"IDSezioneTematica.Name","width":1')
    || TO_CLOB('00,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Descrizione","field":"MisuraConfigurazioneSezioneTematica$Descrizione","width":400,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Tipo","field":"MisuraConfigurazioneSezioneTematica$TIPO","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Note","field":"MisuraConfigura')
    || TO_CLOB('zioneSezioneTematica$Note","width":170,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Operazione","field":"MisuraConfigurazioneSezioneTematica$Operazione","width":400,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]}



              ],
          "actions": [{
            "componentId": "pia_st_aggiorna",
            "componentLabel": "Aggiorna Anagrafica Sezioni Tematiche",
            "process": "')
    || TO_CLOB('MUI_DUMMY_DIM.Sezione Tematica",
            "timeout":-1,
            "refresh": ["gc_SezioniTematiche_CreaSezioniTematiche"],
            "params":[{
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": false
            }]
          }]
        }
     ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('34','/Piano_Annuale/Spazi/Macrospazi_Listino_Promo',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Spazi_MacrospaziListinoPromo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": true,
      "colSuppressionEnabled": false,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "MacroSpazio": "{[MacroSpazio].[MacroSpazio_liv0]}"
          }
        },
        "COLS": {
          "NON_EMPTY": false ,
          "DIMENSIONS": {
  ')
    || TO_CLOB('          "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Misura_Configurazione_Macrospazi_Listino": "{[Misura_Configurazione_Macrospazi_Listino].[CC],[Misura_Configurazione_Macrospazi_Listino].[EC]}"
          }
        },
        "FROM": "[Configurazione Macrospazio - Listino - Promo]"

      },
      "ExecuteMDX": {
      ')
    || TO_CLOB('  "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["MUI_DescrizioneData" , "Descrizione" ]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
        ')
    || TO_CLOB('  "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT":{
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":260,"hide":false,"rowGroup": false ,"aggFunc": "sum", "column')
    || TO_CLOB('GroupShow":"closed",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "VALIDO_DAL":{"type":[ "TM1DataColumnText"] ,"aggFunc": "","columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName":  "Promozione"
      , "width":400
      , "pinned": "left"
      , "type":["TM1Element"]
    ')
    || TO_CLOB('  } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.Descrizione_plus_Codice","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('35','/Piano_Annuale/Spazi/Macrospazi_Negozi_Promo',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Spazi_MacrospaziNegoziPromo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {

            "Negozio": "{EXCEPT({{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}},{[Negozio].[NA] })}"
          }
        },
        "COLS": ')
    || TO_CLOB('{
          "NON_EMPTY": true ,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "MacroSpazio": "{[MacroSpazio].[MacroSpazio_liv0]}" ,
            "Misura_Configurazione_Macrospazi_Neg_Promo": "{[Misura_Configurazione_Macrospazi_Neg_Promo].[DEFAULT],[Misura_Configurazione_Macrospazi_Neg_Pro')
    || TO_CLOB('mo].[SPAZIO]}"
          }
        },
        "FROM": "[Configurazione Macrospazio - Negozio - Promo]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf')
    || TO_CLOB('" : ["MUI_DescrizioneData","Descrizione" , "Descrizione" ]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT":{
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
 ')
    || TO_CLOB('           "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "VALIDO_DAL":{"type":[ "TM1DataColumnText"] ,"aggFunc": "","columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "fie')
    || TO_CLOB('ld": "Negozio.Descrizione"
      , "headerName": "Negozio"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [

        {"headerName":"Zona Promo","field":"Negozio.MUI_ZonaPromo","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Negozio","field":"Negozio.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]}
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('36','/Piano_Annuale/Spazi/Macrospazi_Negozi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Spazi_MacrospaziNegozi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Negozio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false ,
          "DIMENSIONS": {
            "MacroSpazio": "{[MacroSpazio].[MacroS')
    || TO_CLOB('pazio_liv0]}" ,
            "Misura_Configurazione_Macrospazi_Neg": "{[Misura_Configurazione_Macrospazi_Neg].[(I) Configurazione Macro Neg]}"
          }
        },
        "FROM": "[Configurazione Macrospazio - Negozio]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "Pi')
    || TO_CLOB('cklistValues"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione" , "Descrizione" ]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT":{

            "headerClass": "headerMkt",
            ')
    || TO_CLOB('"openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"closed",  "editable": true, "type":[ "TM1DataColumnText"] },
        "childrenCustomTypes"  : {
          "VALIDO_DAL":{"columnGroupShow":"always"},
          "DEFAULT":{"type":[ "TM1DataColumnInteger" ,"numericColumn"]}
        }
    ')
    || TO_CLOB('  } ,

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Negozio.Descrizione"
      , "headerName": "Negozio"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Tot. Negozio","field":"Negozio.MUI_TOT","width":200,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Zona Promo","field":"Negozio.MUI_ZonaPromo","width":200,"')
    || TO_CLOB('hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Negozio","field":"Negozio.Descrizione","width":200,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]}
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('37','/Piano_Annuale/Spazi/Macrospazi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Spazi_MacroSpazi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MacroSpazio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura_Macrospazi"')
    || TO_CLOB(': "{[Misura_Macrospazi].[(I) Macro Spazi]}"
          }
        },
        "FROM": "[Configurazione Macrospazio]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColum')
    || TO_CLOB('nsSettings" :{
        "headerconf" : ["Descrizione"]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT":{
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "he')
    || TO_CLOB('aderRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false , "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "NOTE":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "GG_COM_DEFAULT":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "GRP_SPZ":{"type":[ "TM1DataColumnText"')
    || TO_CLOB('] ,"columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.Descrizione_plus_Codice","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}

      ]

    },
    {
      "name": "gc_Spazi_MacroSpazi_Listino",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": fals')
    || TO_CLOB('e,
          "DIMENSIONS": {
            "MacroSpazio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura_Configurazione_Macrospazi_Listino": "{[Misura_Configurazione_Macrospazi_Listino].[(I) Configurazione Macro Listino]}"
          }
        },
        "FROM": "[Configurazione Macrospazio - Listino]"

      },
      "ExecuteMDX": {
     ')
    || TO_CLOB('   "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione"]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
          "R')
    || TO_CLOB('IF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT":{
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false , "columnGroup')
    || TO_CLOB('Show":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "TIPO_CONTR_SP":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "TIPO_EXTRA_CONTR_SP":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "DATA_CAMBIO":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "TIPO_CONTR_SP_FUT":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "TIPO')
    || TO_CLOB('_EXTRA_CONTR_SP_FUT":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.Descrizione_plus_Codice","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}
      ]

    },
    {
      "name": "gc_Spazi_MacroSpazi_Aggiorna",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": ')
    || TO_CLOB('{
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MacroSpazio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura_Macrospazi": "{[Misura_Macrospazi].[(I) Macro Spazi Agg.]}"
          }
        },
        "FROM": "[Configurazione Macrospazio]"

      },
      "ExecuteMDX": {
        "Members": [
  ')
    || TO_CLOB('        "Name",
          "Attributes",
"UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione"]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "op')
    || TO_CLOB('enByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT":{
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false , "columnGroupShow":"always",  "editable": ')
    || TO_CLOB('true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "GG_COM_NEW":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "OPERAZIONE":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "GRP_SPZ":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.Desc')
    || TO_CLOB('rizione_plus_Codice","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}

      ],
      "actions": [{
            "componentId": "pia_ms_crea",
            "componentLabel": "Creazione Macrospazio",
            "process": "MUI_DUMMY_DIM.MacroSpazioInsertElement",
            "timeout":-1,
            "refresh": ["gc_Spazi_MacroSpazi","gc_Spazi_MacroSpazi_Listino","gc_Spazi_MacroSpazi_Aggiorna"],
            "params":[{
              "paramName": "inCo')
    || TO_CLOB('dice",
              "label": "Codice Macrospazi",
              "hasPicklist": false
            },
            {
              "paramName": "inDescrizione",
              "label": "Descrizione Macrospazio",
              "hasPicklist": false
            },
            {
              "paramName": "inDescTimone",
              "label": "Desrizione Timone",
              "hasPicklist": false
            },
            {
              "paramName": "inGruppo",
              "label"')
    || TO_CLOB(': "Gruppo Macrospazi",
              "defaultValue": "Spazi Campagna",
              "hasPicklist": false
            }]
          },
          {
            "componentId": "pia_ms_aggiorna",
            "componentLabel": "Aggiorna Macrospazio",
            "process": "MUI_DUMMY_CUB.ConfigurazioneMacrospazi_Aggiornamento",
            "timeout":-1,
            "refresh": ["gc_Spazi_MacroSpazi","gc_Spazi_MacroSpazi_Listino","gc_Spazi_MacroSpazi_Aggiorna"],
            "params":[{
    ')
    || TO_CLOB('          "label": "Aggiorna:",
              "defaultValue": "Sei Sicuro Di Voler Aggiornare?",
              "hasPicklist": false,
              "disabled": true
            }]
          }]
    }

  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('38','/Piano_Annuale/Spazi/Spazi_Condivisi',TO_CLOB('{
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Spazi_SpaziCondivisi_spazio",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": false,
      "suppressRowClickSelection": false,
      "maxCells": 2000000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Spazio": "{{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 0')
    || TO_CLOB(')},     [Spazio].[MUI_Sort]  ,  ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "}ElementAttributes_Spazio": "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Spazio] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[}ElementAttributes_Spazio]",
        "WHERE": {
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "UniqueName",
          "Attr')
    || TO_CLOB('ibutes/MacroSpazioDescrizione",
          "Attributes/Descrizione",
          "Attributes/MUI_Compratore",
          "Attributes/MUI_Reparto"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {},
      "columnDefs": [
         {
          "headerName": "Reparto",
          "field": "Spazio.MUI_Reparto",
       ')
    || TO_CLOB('   "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Spazio.MUI_Compratore",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Macro Spaz')
    || TO_CLOB('io",
          "field": "Spazio.MacroSpazioDescrizione",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Spazio",
          "field": "Spazio.Descrizione",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
')
    || TO_CLOB('
        }
      ],
      "selections": [
        {
          "source": {
            "table": "gc_Spazi_SpaziCondivisi_spazio",
            "dimension": "Spazio",
            "attribute": "Descrizione"
          },
          "destination": [
            {
              "table": "gc_Spazi_SpaziCondivisi_spazio",
              "dimension": "Spazio",
              "attribute": "Descrizione"
            }
          ]
        }
      ],
      "preSelections": [
        {
        ')
    || TO_CLOB('  "dimension": "Promozione",
          "attribute": "Descrizione + Data",
          "process": "",
          "paramName": "inPromo",
          "refresh": ["gc_Spazi_SpaziCondivisi_associazione"],
          "configuration": {
            "configurations": [
              {
                "name": "Promozione",
                "logMemory": true,
                "logTime": true,
                "skip": true,
                "MDX": {
                  "ROWS": {
                    "NON')
    || TO_CLOB('_EMPTY": true,
                    "DIMENSIONS": {
                      "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
                    }
                  },
                  "COLS": {
                    "NON_EMPTY": false,
                    "DIMENSIONS": {
                      "}ElementAttributes_Promozione": "{[}Elemen')
    || TO_CLOB('tAttributes_Promozione].[Descrizione + Data] }"
                    }
                  },
                  "FROM": "[}ElementAttributes_Promozione]"
                },
                "ExecuteMDX": {
                  "Members": [
                    "Name",
                    "Attributes",
                    "UniqueName"
                  ],
                  "Cells": [
                    "Ordinal",
                    "Value",
                    "Updateable",
             ')
    || TO_CLOB('       "Consolidated",
                    "HasPicklist",
                    "PicklistValues"
                  ]
                }
              }
            ]
          }
        }
      ]
    },
    {
      "name": "gc_Spazi_SpaziCondivisi_compratore",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": false,
      "suppressRowClickSelection": false,
      "maxCells": 2000000,
      "MD')
    || TO_CLOB('X": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Compratore": "{ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , { {[Compratore].[S000] }, {[Compratore].[NA] }})} , [Compratore].[MUI_Sort] , BASC )}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "}ElementAttributes_Compratore": "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_')
    || TO_CLOB('Compratore] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[}ElementAttributes_Compratore]",
        "WHERE": {
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "UniqueName",
          "Attributes"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {},
     ')
    || TO_CLOB(' "columnDefs": [
        {
          "headerName": "Category Manager",
          "field": "Compratore.CategoryManager",
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Compratore.MUI_Reparto",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable')
    || TO_CLOB('": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.MUI_Descrizione",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "selections": [
        {
          "source": {
            "table": "gc_Spazi_SpaziCondivisi_compratore",
        ')
    || TO_CLOB('    "dimension": "Compratore",
            "attribute": "Descrizione"
          },
          "destination": [
            {
              "table": "gc_Spazi_SpaziCondivisi_compratore",
              "dimension": "Compratore",
              "attribute": "Descrizione"
            }
          ]
        }
      ]
    },
    {
      "name": "gc_Spazi_SpaziCondivisi_associazione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false')
    || TO_CLOB(',
      "colSuppressionEnabled": false,
      "maxCells": 2000000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Spazio": "{{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 0)},     [Spazio].[MUI_Sort]  ,  ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozio')
    || TO_CLOB('ne].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{ [Compratore].[TOT_COMP], [Compratore].[S22]},{ EXCEPT( {TM1SUBSETALL( [Compratore] )}, { [Compratore].[NA], [Compratore].[S000], [Compratore].[TOT_COMP], [Compratore].[S22]}) }}"
          }
        },
        "FROM": "[Configurazione RaggrSpazi]",
        "WHERE": {
          "Misura Config RaggrFoto": "[OK]"
        }
      },
      "ExecuteMDX": {
   ')
    || TO_CLOB('     "Members": [
          "Name",
          "UniqueName",
          "Attributes/Canale",
          "Attributes/Anno",
          "Attributes/MUI_Semestre",
          "Attributes/Descrizione",
          "Attributes/Compratore",
          "Attributes/MacroSpazioDescrizione",
          "Attributes/MUI_TOT",
          "Attributes/MUI_DescrizioneData"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "Ha')
    || TO_CLOB('sPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "MUI_DescrizioneData",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": ')
    || TO_CLOB('true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
          ')
    || TO_CLOB('  "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "cellClass": "cellClass-left-text",
        "field": "Spazio.Descrizione",
        "headerName": "Spazio",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
')
    || TO_CLOB('        {
          "headerName": "Totale",
          "field": "Spazio.MUI_TOT",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Spazio.Compratore",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "T')
    || TO_CLOB('M1Element"
          ]
        },
        {
          "headerName": "Macro Spazio",
          "field": "Spazio.MacroSpazioDescrizione",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "actions": [
        {
          "componentId": "pia_sc_condividi",
          "componentLabel": "Condividi",
          "process": "MUI_DUMMY_CUB.Configurazion')
    || TO_CLOB('eRaggSpazi",
          "timeout": -1,
          "refresh": [
            "gc_Spazi_SpaziCondivisi_associazione"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribute": "Descrizione + Data",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": false,
              "disabled": true,
              "visible": true
            },
            {
              "dimension": "Spa')
    || TO_CLOB('zio",
              "attribute": "Descrizione",
              "paramName": "inSpazio",
              "label": "Spazio",
              "hasPicklist": false,
              "disabled": true,
              "visible": true
            },
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": false,
              "disabled": true,
       ')
    || TO_CLOB('       "visible": true
            }
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('39','/Reporting/Copia_in_Pianificazione',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_CopiaInPianificazione",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":1000000 ,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[REP - Sezi')
    || TO_CLOB('one Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Scenario": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)}')
    || TO_CLOB(' , ASC)}",
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                       	"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}" ,
                        "REP - Sezione ')
    || TO_CLOB('Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}"

					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"REP - Misura Reporting Articolo": "{[REP - Misura')
    || TO_CLOB(' Reporting Articolo].[Misura Reporting Articolo - Reporting]}"
					}
				},
				"FROM": "[Reporting Articolo]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : [ "Descrizione" ]  ,
	')
    || TO_CLOB('			"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupS')
    || TO_CLOB('how":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {},
			"columnDefs": [
                 {
					"headerName": "Scenario",
					"field": "REP_minus_Scenario.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Compratore",
					"field": "REP_minus_Compratore.Descrizione",
')
    || TO_CLOB('					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
               {
					"headerName": "Promozione",
					"field": "REP_minus_Promozione.Descrizione_plus_Data",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Fornitori",
					"field": "REP_minus_Fornitore.Descrizione",
					"width": 70,
					"hide": false,')
    || TO_CLOB('
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Meccanica",
					"field": "REP_minus_MeccanicaSemplice.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true')
    || TO_CLOB(',
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Sezione Tematica",
					"field": "REP_minus_SezioneTematica.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "A Volantino",
					"field": "REP_minus_AVolantino.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
	')
    || TO_CLOB('			}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
			"actions": [{
				"componentId": "btnCopiaPezziColliId",
				"componentLabel": "Copia pezzi e colli",
				"process": "MUI_DUMMY",
				"timeout":-1,
				"refresh": ["gc_CopiaInPianificazione"],
				"params":[{
					"label": "Confermi esecuzione?",
					"hasPicklist": false,
					"disabled": true
				}]
			},
				{
					"componentId": "btnCopiaEstesaId",
					"componentLabel": "Copia estesa",
					"process": "MUI')
    || TO_CLOB('_DUMMY",
					"timeout":-1,
					"refresh": ["gc_CopiaInPianificazione"],
					"params":[{
						"label": "Confermi esecuzione?",
						"hasPicklist": false,
						"disabled": true
					}]
				}]
		}

	]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('40','/Reporting/Dettaglio_Campagna',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_DettaglioCampagna",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":1000000 ,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[REP - Sezione ')
    || TO_CLOB('Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)}')
    || TO_CLOB(' , ASC)}",
						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}" ,
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , A')
    || TO_CLOB('SC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}"

					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                      	"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
                         "REP - Scenario": "{ [REP - Scenario].[Scenario -Storico]}",
						"REP - Misura Reporting Articolo": "{[REP - Misura Reporting Articolo].[Rep')
    || TO_CLOB('orting ACQ]}"
					}
				},
				"FROM": "[Reporting Articolo]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : [ "MUI_DescrizioneData" , "Descrizione" ,  "Descrizione" ]  ,
				"head')
    || TO_CLOB('erdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"al')
    || TO_CLOB('ways",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef":{
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "REP_minus_Compratore.Descrizione",
				"headerName": "Compratore",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
              {
					"headerName": "Category",
					"field": "REP_minus_Compratore.MUI_TOT",
					"width"')
    || TO_CLOB(': 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Compratori",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Reparto",
					"field": "REP_minus_Articolo.RepartoDesc",
					"width": 70,
					"hide": true,
					"rowGroup":')
    || TO_CLOB(' true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				 } ,
              {
					"headerName": "Categoria",
					"field": "REP_minus_Articolo.CategoriDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				  } ,
               {
					"headerName": "GRM",
					"field": "REP_minus_Articolo.GRMDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
	')
    || TO_CLOB('					"TM1Element"
					]
				  } ,
              {
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
				{
					"headerName": "Totale Articolo",
					"field": "REP_minus_Articolo.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				} ,
				{
			')
    || TO_CLOB('		"headerName": "Articolo",
					"field": "REP_minus_Articolo.SubGrmDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				} ,

                {
					"headerName": "Fornitori",
					"field": "REP_minus_Fornitore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
               {
					"headerName": "Sezione Tematica",
	')
    || TO_CLOB('				"field": "REP_minus_SezioneTematica.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
               {
					"headerName": "Meccanica Semplice",
					"field": "REP_minus_MeccanicaSemplice.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
               {
					"headerName": "A Volantino",
					"fiel')
    || TO_CLOB('d": "REP_minus_AVolantino.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}





			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('41','/Reporting/Dettaglio_Zona',TO_CLOB('{
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_DettaglioZona",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":10000 ,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[REP - Sezione Tematic')
    || TO_CLOB('a].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
          			    "REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0')
    || TO_CLOB(')} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP ')
    || TO_CLOB('- AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                      "REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
                       "REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT]}",
						"REP - Zona Pro')
    || TO_CLOB('mo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}",
						"REP - Misura Reporting Articolo Zona": "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -BDGVend]}"
					}
				},
				"FROM": "[Reporting Articolo Zona]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidat')
    || TO_CLOB('ed",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : [ "MUI_DescrizioneData" ,"Descrizione" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByD')
    || TO_CLOB('efault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "REP_minus_Articolo.DescrizioneArticolo",
				"head')
    || TO_CLOB('erName": "Articolo",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
                {
					"headerName": "Totale Articolo",
					"field": "REP_minus_Articolo.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				} ,
                {
					"headerName": "Reparto",
					"field": "REP_minus_Articolo.RepartoDesc",
					"width": 70,
					"hide": true,
					"rowG')
    || TO_CLOB('roup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				 } ,
                 {
					"headerName": "Categoria",
					"field": "REP_minus_Articolo.CategoriDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				  } ,
                 {
					"headerName": "GRM",
					"field": "REP_minus_Articolo.GRMDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					')
    || TO_CLOB('"type": [
						"TM1Element"
					]
				  } ,
				{
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.SubGrmDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
		')
    || TO_CLOB('		{
					"headerName": "Compratori",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
                {
					"headerName": "Fornitori",
					"field": "REP_minus_Fornitore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
               {
					"headerName": "Sezione')
    || TO_CLOB(' Tematica",
					"field": "REP_minus_SezioneTematica.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
               {
					"headerName": "Meccanica Semplice",
					"field": "REP_minus_MeccanicaSemplice.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
               {
					"headerName": "A Volantino')
    || TO_CLOB('",
					"field": "REP_minus_AVolantino.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('42','/Reporting/Sintesi_Campagna',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_SintesiCampagna",
			"logMemory": true,
			"logTime": true,
			"skip": true,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}" ,
						"REP - Categoria": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSI')
    || TO_CLOB('ONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[TGT_ACQ]}",
						"REP - Misura Timone": "{[REP - Misura Timone].[Misura Timone - Timone]}"
					}
				},
				"FROM": "[Timone Reporting]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attrib')
    || TO_CLOB('utes",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"ope')
    || TO_CLOB('nByDefault": true
					},
					"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {
				"cellRendererParams": {
					"s')
    || TO_CLOB('uppressCount": true
				},
				"field": "REP_minus_Categoria.Descrizione",
				"headerName": "Categoria",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
				{
					"headerName": "Totale Compratore",
					"field": "REP_minus_Compratore.MUI_TOTS",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
               {
					"headerName": "Caregory",
					"field": "REP')
    || TO_CLOB('_minus_Compratore.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},

              {
					"headerName": "Totale Categoria",
					"field": "REP_minus_Categoria.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Direzione",
					"field": "REP_minus_Categoria.MUI_DIR",
					"width":')
    || TO_CLOB(' 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Reparto",
					"field": "REP_minus_Categoria.MUI_REP",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
				{
					"headerName": "Categoria",
					"field": "REP_minus_Categoria.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"edita')
    || TO_CLOB('ble": true,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Compratore",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,

			"colSuppressionEnabled": false
		}
	]
}


'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('43','/Reporting/Storico_articolo_per_zona_(ACQ)',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_StoricoArticoloPerZonaAcq",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":1000000 ,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[REP - ')
    || TO_CLOB('Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                        "REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( ')
    || TO_CLOB('[REP - Promozione] )}, 0)} , ASC)}",
          				"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [')
    || TO_CLOB('REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT]}",
                        ')
    || TO_CLOB('"REP - Zona Promo": "{{[REP - Zona Promo].[TOT_ZONA_PROMO],[REP - Zona Promo].[TOT_ZONE] },{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}}",
						"REP - Misura Reporting Articolo Zona": "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -BDGVend]}"
					}
				},
				"FROM": "[Reporting Articolo Zona]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {

				"Members": [
					"Name",
					"Attributes",
"UniqueName"
')
    || TO_CLOB('				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["Descrizione" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"openByDefault": true
					},
		')
    || TO_CLOB('			"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {},
			"columnDefs": [

               {
					"headerName": "Comp')
    || TO_CLOB('ratore",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
               {
					"headerName": "Promozione",
					"field": "REP_minus_Promozione.Descrizione_plus_Data",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Fornitori",
					"field": "REP_minus_')
    || TO_CLOB('Fornitore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Meccanica",
					"field": "REP_minus_MeccanicaSemplice.Descrizione",
					"width": 70,
		')
    || TO_CLOB('			"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Sezione Tematica",
					"field": "REP_minus_SezioneTematica.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "A Volantino",
					"field": "REP_minus_AVolantino.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
')
    || TO_CLOB('					"editable": false,
					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('44','/Reporting/Storico_articolo_per_zona_(MKT)',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_StoricoArticoloPerZonaAcqMkt",
			"logMemory": true,
			"logTime": true,
			"skip": true,
            "maxCells":1000000 ,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[REP ')
    || TO_CLOB('- Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                        "REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL')
    || TO_CLOB('( [REP - Promozione] )}, 0)} , ASC)}",
          				"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL(')
    || TO_CLOB(' [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT]}",
                      ')
    || TO_CLOB('  "REP - Zona Promo": "{{[REP - Zona Promo].[TOT_ZONA_PROMO],[REP - Zona Promo].[TOT_ZONE] },{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}}",
						"REP - Misura Reporting Articolo Zona": "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -Storico]}"
					}
				},
				"FROM": "[Reporting Articolo Zona]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
"UniqueName"')
    || TO_CLOB('
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["Descrizione" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"openByDefault": true
					},
	')
    || TO_CLOB('				"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {},
			"columnDefs": [

               {
					"headerName": "Com')
    || TO_CLOB('pratore",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
               {
					"headerName": "Promozione",
					"field": "REP_minus_Promozione.Descrizione_plus_Data",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Fornitori",
					"field": "REP_minus')
    || TO_CLOB('_Fornitore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Meccanica",
					"field": "REP_minus_MeccanicaSemplice.Descrizione",
					"width": 70,
	')
    || TO_CLOB('				"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Sezione Tematica",
					"field": "REP_minus_SezioneTematica.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [

						"TM1Element"
					]
				},
				{
					"headerName": "A Volantino",
					"field": "REP_minus_AVolantino.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false')
    || TO_CLOB(',
					"editable": false,
					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('45','/Reporting/Timone_Reparto',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_TimoneReparto",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":1000000 ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Reparto": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Reparto] )}, 0)} , ASC)}"

					}
				},
			')
    || TO_CLOB('	"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                        "REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[TGT_ACQ]}",
						"REP - Misura Timone": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Timone] )}, 0)} , ASC)}"
					}
				},
				"FROM": "[Timone Reparto]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
"Uni')
    || TO_CLOB('queName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : [ "Descrizione" , "Descrizione"]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"TGT')
    || TO_CLOB('_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"fi')
    || TO_CLOB('eld": "REP_minus_Reparto.Descrizione",
				"headerName": "Reparto",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
              {
					"headerName": "Anno",
					"field": "REP_minus_Promozione.Anno",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Canale",
					"field": "REP_minus_Promozione.Canale",
					"width": 70,
			')
    || TO_CLOB('		"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Promozione",
					"field": "REP_minus_Promozione.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Reparto",
					"field": "REP_minus_Reparto.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
')
    || TO_CLOB('					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('46','/Analisi_Budget-Venduto/Articolo',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_AnalisiBudgetVenduto_Articolo",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":1000000 ,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[RE')
    || TO_CLOB('P - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
          						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP ')
    || TO_CLOB('- Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Zona Promo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSET')
    || TO_CLOB('ALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Scena')
    || TO_CLOB('rio": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT]}",
						"REP - Misura Reporting Articolo Zona": "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -BDGVend]}"
					}
				},
				"FROM": "[Reporting Articolo Zona]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consoli')
    || TO_CLOB('dated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true')
    || TO_CLOB('
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "REP_minus_Articolo.DescrizioneArticolo",
				"headerName": "Ar')
    || TO_CLOB('ticolo",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
			    {
					"headerName": "Totale Articolo",
					"field": "REP_minus_Articolo.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				} ,
                {
					"headerName": "Reparto",
					"field": "REP_minus_Articolo.RepartoDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"ed')
    || TO_CLOB('itable": true ,
					"type": [
						"TM1Element"
					]
				 } ,
                 {
					"headerName": "Categoria",
					"field": "REP_minus_Articolo.CategoriDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				  } ,
                 {
					"headerName": "GRM",
					"field": "REP_minus_Articolo.GRMDesc",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true ,
					"type": [
						"TM1')
    || TO_CLOB('Element"
					]
				  } ,
				{
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.SubGrmDesc",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
                {
		')
    || TO_CLOB('			"headerName": "Fornitori",
					"field": "REP_minus_Fornitore.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Compratori",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Zona Promo",
					"f')
    || TO_CLOB('ield": "REP_minus_ZonaPromo.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
               {
					"headerName": "Sezione Tematica",
					"field": "REP_minus_SezioneTematica.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Meccanica",
					"field": "REP_minus_Meccani')
    || TO_CLOB('caSemplice.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "A Volantino",
					"field": "REP_minus_AVolantino.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('47','/Analisi_Budget-Venduto/Categoria',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_AnalisiBudgetVenduto_Categoria",
			"logMemory": true,
			"logTime": true,
			"skip": true,
            "maxCells":1000000 ,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}" ,
						"REP - Categoria": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)} , ASC)}"
					}
				},
			')
    || TO_CLOB('	"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[TGT_ACQ]}",
						"REP - Misura Timone": "{[REP - Misura Timone].[Misura Timone - Timone]}"
					}
				},
				"FROM": "[Timone Reporting]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMD')
    || TO_CLOB('X": {
				"Members": [
					"Name",
					"Attributes",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"TGT_ACQ":{

						"headerClass": "headerAcq",
						"openByDefa')
    || TO_CLOB('ult": true
					},
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGrou')
    || TO_CLOB('pColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "REP_minus_Categoria.Descrizione",
				"headerName": "Categoria",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
                {
					"headerName": "Totale Compratore",
					"field": "REP_minus_Compratore.MUI_TOTS",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
    ')
    || TO_CLOB('           {
					"headerName": "Caregory",
					"field": "REP_minus_Compratore.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},

              {
					"headerName": "Totale Categoria",
					"field": "REP_minus_Categoria.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": true,

					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Direzion')
    || TO_CLOB('e",
					"field": "REP_minus_Categoria.MUI_DIR",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Reparto",
					"field": "REP_minus_Categoria.MUI_REP",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
				{
					"headerName": "Categoria",
					"field": "REP_minus_Categoria.Descrizione",
					"wid')
    || TO_CLOB('th": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Compratore",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('48','/Timone/Foto_Speciali/Riepilogo',TO_CLOB(' {
  "connection": "promo",
  "configurations": [{
    "name": "gc_FotoSpeciali_Riepilogo",
    "logMemory": true,
    "logTime": true,
    "skip": true,
    "rowSuppressionEnabled": true,
    "colSuppressionEnabled": true,
    "MDX": {
      "ROWS": {
        "NON_EMPTY": true,
        "DIMENSIONS": {
          "Categoria": "{{[Categoria].[MUI_TargetCategoria]}  ,{EXCEPT( {{ {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}}} , {[Categoria].[CAT_0000]}) }}",
          "Comprat')
    || TO_CLOB('ore": "{{[Compratore].[S000]},{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]} )}}"
        }
      },
      "COLS": {
        "NON_EMPTY": true,
        "DIMENSIONS": {
          "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
          "Scenario": " {{[Scenario].[RIF_MKT_D')
    || TO_CLOB('T]},{[Scenario].[TGT_ACQ]},{[Scenario].[TGT_MKT]}}",
          "Sezione Tematica": "{TM1SUBSETALL( [Sezione Tematica] )}",
          "Misura Timone.": "{{[Misura Timone.].[N_FOTO_TOT]},{[Misura Timone.].[N_FOTO_SCAFFALE_TOT]}}"
        }
      },
      "FROM": "[Timone Categoria Sezione]",
      "WHERE": {
        "Versione": "[UFF]"
      }
    },
    "ExecuteMDX": {
      "Members": [
        "Name",
        "Level",
        "Attributes",
        "UniqueName"
      ],
      "C')
    || TO_CLOB('ells": [
        "Ordinal",
        "Value",
        "Updateable",
        "Consolidated",
        "HasPicklist",
        "PicklistValues"
      ]
    },
    "autoGroupColumnDef": {
      "cellRendererParams": {
        "suppressCount": true
      },
      "field": "Categoria.Descrizione",
      "headerName": "Categoria",
      "width": 300,
      "pinned": "left",
      "type": ["TM1Element"]
    },
    "DynamicColumns": true,
    "DynamicColumnsSettings": {
      "headerco')
    || TO_CLOB('nf": ["MUI_DescrizioneData","Descrizione","Descrizione","Descrizione"],
      "headerdefaults": {
        "marryChildren": true
      },
      "headerCustomTypes":{
        "RIF_MKT_DT":{
          "openByDefault": true
        },
        "TGT_ACQ":{
          "headerClass": "headerAcq",
          "openByDefault": true
        },
        "TGT_MKT":{
          "headerClass": "headerMkt",
          "openByDefault": true
        },
        "TGT_REP":{
          "headerClass": "head')
    || TO_CLOB('erRep",
          "openByDefault": true
        }
      },
      "childrendefaults": {
        "width": 110,
        "hide": false,
        "rowGroup": false,
        "aggFunc": "sum",
        "columnGroupShow": "always",
        "editable": true,
        "type": ["TM1DataColumnInteger", "numericColumn"]
      },
      "childrenCustomTypes": {
        "VENDUTO_PROMO_NETTO": {
          "type": ["TM1DataColumnNumberK", "numericColumn"],
          "columnGroupShow": "always"
     ')
    || TO_CLOB('   },
        "MARGINE_LORDO__perc_": {
          "type": ["TM1DataColumnNumber", "numericColumn"],
          "columnGroupShow": "always"
        }
      }
    },






    "columnDefs": [
      {
        "headerName": "Totale Categoria",
        "field": "Categoria.MUI_TOT",
        "width": 90,
        "hide": true,
        "rowGroup": true,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },
      {
        "headerName": "Repar')
    || TO_CLOB('to",
        "field": "Categoria.MUI_RepartoDesc",
        "width": 90,
        "hide": true,
        "rowGroup": true,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },
      {
        "headerName": "Categoria",
        "field": "Categoria.Descrizione",
        "width": 90,
        "hide": true,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },{
        "headerName')
    || TO_CLOB('": "CategoryManager",
        "field": "Compratore.CategoryManager",
        "width": 70,
        "hide": true,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },
      {
        "headerName": "Compratore",
        "field": "Compratore.Descrizione",
        "width": 70,
        "hide": false,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },')
    || TO_CLOB('
      {
        "headerName": "Cod Compratore",
        "field": "Compratore.Name",
        "width": 70,
        "hide": true,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },
      {
        "headerName": "Livello Categoria",
        "field": "Categoria.MUI_Level",
        "width": 70,
        "hide": true,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["T')
    || TO_CLOB('M1Element"]
      },

      {
        "headerName": "Cod Categoria",
        "field": "Categoria.Name",
        "width": 70,
        "hide": true,
        "rowGroup": false,
        "editable": false,
        "type": ["TM1Element"]
      }

    ],
    "rowClassRules": {
      "row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1": "data.Compratore.Name')
    || TO_CLOB('==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria')
    || TO_CLOB('.MUI_Level == 2"
    },
    "groupRowAggNodes": {
      "nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && da')
    || TO_CLOB('ta.Compratore.Name != ''S000'' ))"
    }
  }]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('49','/Timone/Foto_Speciali/Target',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_FotoSpeciali_Target",
		"logMemory": true,
		"logTime": true,
		"skip": true,
		"maxCells": 1000000 ,
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": false,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Comp')
    || TO_CLOB('ratore].[MUI_Sort] , BASC )}  }",
					"Categoria": "{{[Categoria].[CAT_0000]}, {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"

				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": ')
    || TO_CLOB('{
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]},{[Scenario].[TGT_MKT]}}",
					"Sezione Tematica": "{{TM1SUBSETALL( [Sezione Tematica] )}}",
					"Misura Timone.": "{[Misura Timone.].[(II) Timone Categoria Sez]}"
				}
			},
			"FROM": "[Timone Categoria Sezione]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"L')
    || TO_CLOB('evel",
				"Attributes",
				"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				"Updateable",
				"Consolidated",
				"HasPicklist",
				"PicklistValues"
			]
		},
		"autoGroupColumnDef": {
			"cellRendererParams": {
				"suppressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf":')
    || TO_CLOB(' ["MUI_DescrizioneData","Descrizione","MUI_Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq",
					"openByDefault": true
				},
				"TGT_MKT":{
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TGT_REP":{
					"headerClass": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults')
    || TO_CLOB('": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"ASSORT_P": {
					"type": ["TM1DataColumnText"]
				},
				"ST_0000": {
					"hide": true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field": "Compratore.CategoryManager",
				"width": 70,
				"h')
    || TO_CLOB('ide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left"')
    || TO_CLOB(',
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},

			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false,
				"type": ["TM1Element"]
			}

		],
		"rowCla')
    || TO_CLOB('ssRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  || (data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000'')  ",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S0')
    || TO_CLOB('00'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S')
    || TO_CLOB('000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('50','/Timone/Spazi_Campagna/Target_Categoria',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_SpaziCampagna_TargetCategoria",
		"logMemory": true,
		"logTime": true,
		"skip": true,
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": true,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},{EXCEPT( {{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}} , {[Compratore].[S000]} )}  }" ,
					"Categoria": "{{[Categoria')
    || TO_CLOB('].[MUI_TargetCategoria]}  ,{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }}"
				}
			},
			"COLS": {
				"NON_EMPTY": false,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura T')
    || TO_CLOB('imone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')} , { [Misura Timone.].[ASSORT_P]}  }"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
')
    || TO_CLOB('
			"Members": [
				"Name",
				"Level",
				"Attributes",
"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				"Updateable",
				"Consolidated",
				"HasPicklist",
				"PicklistValues"
			]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": [
				"MUI_DescrizioneData",
				"Descrizione",
				"Descrizione"
			],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault"')
    || TO_CLOB(': true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq",
					"openByDefault": true
				},
				"TGT_MKT":{
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TGT_REP":{
					"headerClass": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editable": true,
				"type": [
					"TM1DataColumnInteger",
					')
    || TO_CLOB('"numericColumn"
				]
			},
			"childrenCustomTypes": {
				"N_ART_PROMO":  {
					"columnGroupShow":"always"
				},
				"TOT_FOTO":  {
					"columnGroupShow":"always"
				},
				"SPZ_CAMP":  {
					"columnGroupShow":"always"
				}
              ,
				"ASSORT_P":  {
					"hide": true
				}
			}
		},

		"autoGroupColumnDef" :  {  "cellRendererParams":{ "suppressCount": true }
		, "field": "Categoria.MUI_Descrizione"
		, "headerName": "Categoria"
		, "width":300
		, "pinned')
    || TO_CLOB('": "left"
		, "type":["TM1Element"]} ,

		"columnDefs": [
			{"headerName":"CategoryManager","field":"Compratore.CategoryManager","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Compratore","field":"Compratore.MUI_Descrizione","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Cod Compratore","field":"Compratore.Name","width":70,"hide":true,"rowGroup": ')
    || TO_CLOB('false, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Livello Categoria","field":"Categoria.MUI_Level","width":70,"hide":true,"rowGroup": false, "pinned": "left", "editable": false,"type":["TM1Element"]},{"headerName":"Cod Categoria","field":"Categoria.Name","width":70,"hide":true,"rowGroup": false,  "editable": false,"type":["TM1Element"]}
		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) ||')
    || TO_CLOB(' (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3" : "data.Compratore.Name==''TOT_COMP''')
    || TO_CLOB(' && data.Categoria.MUI_Level == 1",
			"row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse" : "(data.Compratore.Name == ''S000'' && (data.Categoria.Name  == ''_REP_01'' || data.Categoria.Name  == ''_REP_02'' || data.Categoria.Name  == ''_REP_03'' || data.Categoria.Name  == ''_REP_04'' || data.Categoria.Name  ==')
    || TO_CLOB(' ''_REP_05'' || data.Categoria.Name  == ''_REP_06'' || data.Categoria.Name  == ''_REP_09'' || data.Categoria.Name  == ''_REP_11'' || data.Categoria.Name  == ''_REP_12'' || data.Categoria.Name  == ''_REP_14'' || data.Categoria.Name  == ''_REP_50'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('51','/Timone/Spazi_Campagna/Target_Reparto',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_SpaziCampagna_TargetReparto",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
		"colSuppressionEnabled": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Pr')
    || TO_CLOB('omozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Reparto": "{{ORDER( {[Reparto].[REP_01] , [Reparto].[REP_01_01], [Reparto].[REP_01_02], [Reparto].[REP_01_03], [Reparto].[REP_01_04], [Reparto].[REP_09], [Reparto].[REP_12], [Reparto].[REP_12_01], [Reparto].[REP_12_02], [Reparto].[REP_12_04]  },[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": " {{[Scenario].[')
    || TO_CLOB('RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
            "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spaz')
    || TO_CLOB('i'')}  }"
          }
        },
        "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
"UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRe')
    || TO_CLOB('ndererParams": {
          "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCusto')
    || TO_CLOB('mTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT":{
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 110,
        ')
    || TO_CLOB('  "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "open",
          "editable": true,
          "type": [
            "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "N_ART_PROMO":  {
            "columnGroupShow":"always"
          },
          "TOT_FOTO":  {
            "columnGroupShow":"always"
          },
          "SPZ_CAMP":  {
            "columnGr')
    || TO_CLOB('oupShow":"always"
          }
        }
      },
      "columnDefs": [
        {
          "headerName": "Canale",
          "field": "Promozione.Canale",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
         ')
    || TO_CLOB(' "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 400,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descriz')
    || TO_CLOB('ione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Tipo Elemento",
          "field": "Reparto.TipoElemento",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "rowClassRules": ')
    || TO_CLOB('{
        "row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
        "row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
        "row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
        "row_style_4"')
    || TO_CLOB(': "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
      },
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [{
            "componentId": "tim_scr_inizializza",
            "componentLabel": "Inizializza Spazio",
            "process": "MUI_DUMMY_CUB.Timone Reparto Inizializza Spazi",
            "timeout":-1,
            "refresh": ["gc_SpaziCampagna_TargetReparto"]')
    || TO_CLOB(',
            "params":[{
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            }]
          }]
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('52','/Timone/Target_Categoria/Data',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_TargetCategoria_Data",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) " ,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ')
    || TO_CLOB('( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
                    "Categoria": "{{[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
			')
    || TO_CLOB('	}
			},
			"COLS": {
				"NON_EMPTY": false,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura')
    || TO_CLOB(' Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO__perc_]},{[Misura Timone.].[D_FOTO_slash_TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF_slash_TGT_REP]},{[Misura Timone.].[ASSORT_P]} }"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
				"Attributes",
"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				"Updateable",
		')
    || TO_CLOB('		"Consolidated",
				"HasPicklist",
				"PicklistValues"
			]
		},
		"autoGroupColumnDef": {
			"cellRendererParams": {
				"suppressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
			},
			"h')
    || TO_CLOB('eaderCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq",
					"openByDefault": true
				},
				"TGT_MKT":{
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TGT_REP":{
					"headerClass": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editabl')
    || TO_CLOB('e": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"VENDUTO_PROMO_NETTO": {
					"type": ["TM1DataColumnInteger", "numericColumn"],
					"columnGroupShow": "always"
				},
				"MARGINE_LORDO__perc_": {
					"type": ["TM1DataColumnNumber", "numericColumn"],
					"columnGroupShow": "always"
				},
				"ASSORT_P": {
					"hide": true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"fie')
    || TO_CLOB('ld": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
			')
    || TO_CLOB('	"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": f')
    || TO_CLOB('alse,

				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_0')
    || TO_CLOB('1'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01''')
    || TO_CLOB(',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('54','/Timone/Target_Categoria/Promo',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_TargetCategoria_Promo",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) " ,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT')
    || TO_CLOB(' ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
                    "Categoria": "{{[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
  ')
    || TO_CLOB('
				}
			},
			"COLS": {
				"NON_EMPTY": false,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCA')
    || TO_CLOB('FFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO__perc_]},{[Misura Timone.].[D_FOTO_slash_TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF_slash_TGT_REP]},{[Misura Timone.].[ASSORT_P]}}"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
				"Attributes",
"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
	')
    || TO_CLOB('			"Updateable",
				"Consolidated",
				"HasPicklist",
				"PicklistValues"
			]
		},
		"autoGroupColumnDef": {
			"cellRendererParams": {
				"suppressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren"')
    || TO_CLOB(': true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq",
					"openByDefault": true
				},
				"TGT_MKT":{
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TGT_REP":{
					"headerClass": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",

				"columnGroupShow":')
    || TO_CLOB(' "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"VENDUTO_PROMO_NETTO": {
					"type": ["TM1DataColumnInteger", "numericColumn"],
					"columnGroupShow": "always"
				},
				"MARGINE_LORDO__perc_": {
					"type": ["TM1DataColumnNumber", "numericColumn"],
					"columnGroupShow": "always"
				},
				"ASSORT_P": {
					"hide":true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "Catego')
    || TO_CLOB('ryManager",
				"field": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
')
    || TO_CLOB('				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": fals')
    || TO_CLOB('e,
				"editable": false,
				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S')
    || TO_CLOB('000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''')
    || TO_CLOB('S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('55','/Timone/Target_Reparto/Data',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Data",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Rep')
    || TO_CLOB('arto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]}}"
          }
        },
  ')
    || TO_CLOB('      "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
         ')
    || TO_CLOB(' "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Anno",
          "field": "Promozione.Anno",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
 ')
    || TO_CLOB('       },
        {

          "headerName": "Semestre",
          "field": "Promozione.MUI_Semestre",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Data",
          "field": "Promozione.DataInizioIst",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "')
    || TO_CLOB('type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 400,
          "hide": tru')
    || TO_CLOB('e,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descrizione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento",
          "field": "Promozione.Riferimento')
    || TO_CLOB('",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento Data",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
         ')
    || TO_CLOB('     "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
           ')
    || TO_CLOB('   "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "c')
    || TO_CLOB('olumnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
')
    || TO_CLOB('
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable":')
    || TO_CLOB(' true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
      ')
    || TO_CLOB('        "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
          ')
    || TO_CLOB('      "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Venduto Promo (s/iva)",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1Dat')
    || TO_CLOB('aColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "ML %",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "weightedAvg",
              "aggFuncParam": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "columnGroupShow": "open",
         ')
    || TO_CLOB('     "editable": true,

              "type": [
                "TM1DataColumnPercentage",
                "numericColumn"
              ]
            },
            {
              "headerName": "FF C",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$CONTR",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "')
    || TO_CLOB('type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "FF EC",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$EXTRA_CONTR",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataCol')
    || TO_CLOB('umnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target MKT",
          "headerClass": "headerMkt",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "')
    || TO_CLOB('sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow":')
    || TO_CLOB(' "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,')
    || TO_CLOB('
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
 ')
    || TO_CLOB('               "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumn')
    || TO_CLOB('Integer",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
      ')
    || TO_CLOB('          "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
     ')
    || TO_CLOB('         ]
            }
          ]
        },
        {
          "headerName": "Target REP",
          "headerClass": "headerRep",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
 ')
    || TO_CLOB('             "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
       ')
    || TO_CLOB('       "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataC')
    || TO_CLOB('olumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
            ')
    || TO_CLOB('    "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
        ')
    || TO_CLOB('      ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
        ')
    || TO_CLOB('    },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
         ')
    || TO_CLOB('     "headerName": "D Foto/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D ')
    || TO_CLOB('Foto Banco/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName')
    || TO_CLOB('": "Target Acq.",
          "headerClass": "headerAcq",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataCol')
    || TO_CLOB('umnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericCo')
    || TO_CLOB('lumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
    ')
    || TO_CLOB('        {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "hea')
    || TO_CLOB('derName": "N. Foto Speciali",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco')
    || TO_CLOB('",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "fiel')
    || TO_CLOB('d": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Rep",
              "field": "Scenario$TGT_REP$$MisuraTimo')
    || TO_CLOB('ne_dot_$D_FOTO_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Rep",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SC')
    || TO_CLOB('AFF_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        }
      ],
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElement')
    || TO_CLOB('o == ''R''"
      },
      "actions": [
        {
          "componentId": "tim_trd_inizializza",
          "componentLabel": "Inizializza Reference",
          "process": "MUI_DUMMY_CUB.Timone.Inizializza Reference",
          "timeout": -1,
          "refresh": [
            "gc_TargetReparto_Data"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label":')
    || TO_CLOB(' "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Gruppo",
              "paramName": "inGruppo",
              "label": "Gruppo",
              "hasPicklist": true
       ')
    || TO_CLOB('     }
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('56','/Timone/Target_Reparto/Promo',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Promo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 1000000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozio')
    || TO_CLOB('ne],BASC)}",
            "Reparto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]}}"
          }
        },
')
    || TO_CLOB('        "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
       ')
    || TO_CLOB('   "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Canale",
          "field": "Promozione.Canale",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
       ')
    || TO_CLOB('   ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 400,
          "hide": true,
          "rowGroup": true,
          ')
    || TO_CLOB('"editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descrizione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento",
          "field": "Promozione.Riferimento",
          "width": 70,
          "hide')
    || TO_CLOB('": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Tipo Elemento",
          "field": "Reparto.TipoElemento",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento Data",
          "openByDefault')
    || TO_CLOB('": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },')
    || TO_CLOB('
            {
              "headerName": "Totale",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "head')
    || TO_CLOB('erName": "N. Foto",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
             ')
    || TO_CLOB(' "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$RI')
    || TO_CLOB('F_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_')
    || TO_CLOB('dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
')
    || TO_CLOB('              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Venduto Promo (s/iva)",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
             ')
    || TO_CLOB(' "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "ML %",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
              "width": 80,
              ')
    || TO_CLOB('"hide": false,
              "rowGroup": false,
              "aggFunc": "weightedAvg",
              "aggFuncParam": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnPercentage",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target MKT",
          "headerClass": "header')
    || TO_CLOB('Mkt",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
   ')
    || TO_CLOB('           ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
         ')
    || TO_CLOB('   {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto ')
    || TO_CLOB('Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "f')
    || TO_CLOB('ield": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_MKT$$')
    || TO_CLOB('MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FO')
    || TO_CLOB('TO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target REP",
          "headerClass": "headerRep",
          "openByDefault": true,
          "')
    || TO_CLOB('children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
       ')
    || TO_CLOB('       "headerName": "Totale",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
      ')
    || TO_CLOB('        "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_REP$$M')
    || TO_CLOB('isuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_S')
    || TO_CLOB('PEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
          ')
    || TO_CLOB('    "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              ')
    || TO_CLOB('"hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
              "width": 80,
              "hide": false,
      ')
    || TO_CLOB('        "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "r')
    || TO_CLOB('owGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target Acq.",
          "headerClass": "headerAcq",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "')
    || TO_CLOB('field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_ACQ$$MisuraTimo')
    || TO_CLOB('ne_dot_$TOT_FOTO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
              "width"')
    || TO_CLOB(': 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": ')
    || TO_CLOB('true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup')
    || TO_CLOB('": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
       ')
    || TO_CLOB('       "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
       ')
    || TO_CLOB('       "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Rep",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow')
    || TO_CLOB('": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Rep",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
')
    || TO_CLOB('
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        }
      ],
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [
        {
          "componentId": "tim_trp_inizializza",
          "componentLabel": "Inizializza Reference",
          "process": "MUI_DUMMY')
    || TO_CLOB('_CUB.Timone.Inizializza Reference",
          "timeout": -1,
          "refresh": [
            "gc_TargetReparto_Promo"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName')
    || TO_CLOB('": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Gruppo",
              "paramName": "inGruppo",
              "label": "Gruppo",
              "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('57','/Timone/Associazione_Promo',TO_CLOB(' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_AssociazionePromo_Promozioni",
			"logMemory": true,
			"logTime": true,
			"skip": true,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
			"suppressRowClickSelection": false,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, [Promozione].[Data_Inizio_Ist.],DESC )}"
					}
				},
				"')
    || TO_CLOB('COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"}ElementAttributes_Promozione": "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Promozione] )}, 0)}, ASC)}"
					}
				},
				"FROM": "[}ElementAttributes_Promozione]"
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
')
    || TO_CLOB('

			},
			"autoGroupColumnDef": {

			},
			"columnDefs": [
				{
					"headerName": "Promozione",
					"field": "Promozione.Name",
					"width": 70,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Descrizione",
					"field": "Promozione.Descrizione_plus_Data",
					"width": 400,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Elemen')
    || TO_CLOB('t"
					]
				},
				{
					"headerName": "Riferimento",
					"field": "Promozione.Riferimento",
					"width": 400,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Gruppo",
					"field": "Promozione.TipoPromozione",
					"width": 100,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Can')
    || TO_CLOB('ale",
					"field": "Promozione.Canale",
					"width": 100,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Anno",
					"field": "Promozione.Anno",
					"width": 50,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Semestre",
					"field": "Promozione.MUI_Semestre",
					"width": 100,
			')
    || TO_CLOB('		"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				}
			],
			"selections": [
				{
					"source": {
						"table": "gc_AssociazionePromo_Promozioni",
						"dimension": "Promozione",
						"attribute": "Descrizione"
					},
					"destination": [
						{
							"table": "gc_AssociazionePromo_Associazioni",
							"dimension": "Promozione",
							"attribute": "Descrizione"
						},
						{
							"table": "gc_Associ')
    || TO_CLOB('azionePromo_SezioniTematiche",
							"dimension": "Promozione",
							"attribute": "Descrizione"
						}
					]
				},
				{
					"source": {
						"table": "gc_AssociazionePromo_Promozioni",
						"dimension": "Promozione",
						"attribute": "Anno"
					},
					"destination": [
						{
							"table": "gc_AssociazionePromo_Promozioni",
							"dimension": "Promozione",
							"attribute": "Anno"
						}
					]
				}
			]
		},
		{
			"name": "gc_AssociazionePromo_Riferi')
    || TO_CLOB('mento",
			"logMemory": true,
			"logTime": true,
			"skip": true,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
			"suppressRowClickSelection": false,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione Riferimento": "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}, [Promozione Riferimento].[Data_Inizio_Ist.],DESC )}"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
')
    || TO_CLOB('						"}ElementAttributes_Promozione": "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Promozione Riferimento] )}, 0)}, ASC)}"
					}
				},
				"FROM": "[}ElementAttributes_Promozione Riferimento]"
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},
			"autoGroupColumnDef": {')
    || TO_CLOB('

			},
			"columnDefs": [
				{
					"headerName": "Promozione",
					"field": "PromozioneRiferimento.Name",
					"width": 100,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Descrizione",
					"field": "PromozioneRiferimento.Descrizione_plus_Data",
					"width": 300,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
	')
    || TO_CLOB('			},
				{
					"headerName": "Codice Descrizione",
					"field": "PromozioneRiferimento.Descrizione",
					"width": 300,
					"hide": true,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Anno",
					"field": "PromozioneRiferimento.Anno",
					"width": 50,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Ca')
    || TO_CLOB('nale",
					"field": "PromozioneRiferimento.MUI_Canale",
					"width": 100,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Gruppo",
					"field": "PromozioneRiferimento.Gruppo",
					"width": 100,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Semestre",
					"field": "PromozioneRiferim')
    || TO_CLOB('ento.Semestre",
					"width": 100,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}
			],
			"selections": [
				{
					"source": {
						"table": "gc_AssociazionePromo_Riferimento",
						"dimension": "PromozioneRiferimento",
						"attribute": "Descrizione"
					},
					"destination": [
						{
							"table": "gc_AssociazionePromo_Riferimento",
							"dimension": "PromozioneRiferimento",
							"attribute"')
    || TO_CLOB(': "Descrizione"
						}
					]
				}
			]
		},
		{
			"name": "gc_AssociazionePromo_Associazioni",
			"logMemory": true,
			"logTime": true,
			"skip": true,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
			"alertNoDataFound": false,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}",
						"Promozione Riferimento": "{TM1SORT( {TM1FILTERBYLE')
    || TO_CLOB('VEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}, ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Misura Configurazione Riferimento": "{[Misura Configurazione Riferimento].[Selezione]}"
					}
				},
				"FROM": "[Configurazione Riferimento Associazione]"
			},
			"ElementSelector": {
				"promozione": {
					"lable": "Promozione",
					"Dimension": "Promozione",
					"Show": "Descrizione",
					"mdx": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM')
    || TO_CLOB('1SUBSETALL( [Promozione] )}, 0)}, ASC)}} ",
					"enabled": false
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},
			"autoGroupColumnDef": {

			},
			"columnDefs": [
				{
					"headerName": "Promozione",
					"field": "Promozione.Descrizione",
					"width": 200,
					"hide":')
    || TO_CLOB(' false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Riferimento",
					"field": "PromozioneRiferimento.Descrizione",
					"width": 300,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				}
			],
			"actions": [
              {
					"componentId": "tim_ap_aggiorna",
					"componentLabel": "Aggiorna Descrizione Riferimento",
					"proces')
    || TO_CLOB('s": "MUI_DUMMY_DIM.Promozione.ConfRiferimento",

					"timeout": -1,
					"refresh": [
						"gc_AssociazionePromo_Promozioni","gc_AssociazionePromo_Riferimento","gc_AssociazionePromo_Associazioni","gc_AssociazionePromo_SezioniTematiche"
					],
					"params": [
						{
							"dimension": "Promozione",
							"attribute": "Anno",
							"paramName": "inAnno",
							"label": "Anno",
							"hasPicklist": false,
							"disabled": false,
							"visible": true
						}
					]
			')
    || TO_CLOB('	},
              {
					"componentId": "tim_ap_associa",
					"componentLabel": "Associa",
					"process": "MUI_DUMMY_CUB.Associa Promozione",

					"timeout": -1,
					"refresh": [
						"gc_AssociazionePromo_Associazioni","gc_AssociazionePromo_SezioniTematiche"
					],
					"params": [
						{
							"dimension": "Promozione",
							"attribute": "Descrizione",
							"paramName": "inPromo",
							"label": "Promozione",
							"hasPicklist": false,
							"disabled": true,
')
    || TO_CLOB('							"visible": true
						},
						{
							"dimension": "Promozione",
							"attribute": "Anno",
							"paramName": "inAnno",
							"label": "Anno",
							"hasPicklist": false,
							"disabled": true,
							"visible": false
						},
						{
							"dimension": "PromozioneRiferimento",
							"attribute": "Descrizione",
							"paramName": "inRiferimento",
							"label": "Riferimento",
							"hasPicklist": false,
							"disabled": true,
							"visible": true
					')
    || TO_CLOB('	}
					]
				}
			]
		},
		{
			"name": "gc_AssociazionePromo_SezioniTematiche",
			"logMemory": true,
			"logTime": true,
			"skip": true,

			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
			"alertNoDataFound": false,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}} ",
						"Promozione con Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL')
    || TO_CLOB('( {TM1SUBSETALL( [Promozione con Sezione Tematica] )}, 0)}, ASC)}} "
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Misura Configurazione Riferimento": "{[Misura Configurazione Riferimento].[Selezione] , [Misura Configurazione Riferimento].[DataInizio.],[Misura Configurazione Riferimento].[DataFine.],[Misura Configurazione Riferimento].[DataInizio],[Misura Configurazione Riferimento].[DataFine],[Misura Configurazione Riferimento].[COUNT_GIORNI],[Misura Con')
    || TO_CLOB('figurazione Riferimento].[VOL],[Misura Configurazione Riferimento].[ESCLUSIONE_CLUSTER],[Misura Configurazione Riferimento].[ESCLUSIONE_COUNTING],[Misura Configurazione Riferimento].[Escludi_Venduto],[Misura Configurazione Riferimento].[DESCRIZIONE_RIF]}"
					}
				},
				"FROM": "[Configurazione Riferimento]"
			},
			"ElementSelector": {
				"promozione": {
					"lable": "Promozione",
					"Dimension": "Promozione",
					"Show": "Descrizione",
					"mdx": "{{TM1SORT( {TM1FILTERBYLEV')
    || TO_CLOB('EL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}} ",
					"enabled": false
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},
			"autoGroupColumnDef": {

			},
			"columnDefs": [
				{
					"headerName": "Promozione",
					"field": "Promozione.Descrizione_plus_Data",
					"width": 3')
    || TO_CLOB('00,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Descrizione Rif.",
					"field": "MisuraConfigurazioneRiferimento$DESCRIZIONE_RIF",
					"width": 400,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnText"
					]
				},
				{
					"headerName": "Sezione Tematica",
					"field": "PromozioneconSezioneTematica.Descrizione",
				')
    || TO_CLOB('	"width": 300,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Selezione",
					"field": "MisuraConfigurazioneRiferimento$Selezione",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnCheckbox"
					]
				},
				{
					"headerName": "DataInizio Rif",
					"field": "MisuraConfigurazioneRiferimento$DataInizio_dot_"')
    || TO_CLOB(',
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnDate"
					]
				},
				{
					"headerName": "DataFine Rif",
					"field": "MisuraConfigurazioneRiferimento$DataFine_dot_",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnDate"
					]
				},
				{
					"headerName": "Data Inizio",
					"field": "MisuraConfigurazioneRiferimento$Dat')
    || TO_CLOB('aInizio",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnDate"
					]
				},
				{
					"headerName": "Data Fine",
					"field": "MisuraConfigurazioneRiferimento$DataFine",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnDate"
					]
				},
				{
					"headerName": "N. Giorni",
					"field": "MisuraConfigurazioneRiferimento$COUNT')
    || TO_CLOB('_GIORNI",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnText"
					]
				},
				{
					"headerName": "Gestione Volantino",
					"field": "MisuraConfigurazioneRiferimento$VOL",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnText"
					]
				},
				{
					"headerName": "Escludi Cluster Formato",
					"field": "MisuraConfigurazion')
    || TO_CLOB('eRiferimento$ESCLUSIONE_CLUSTER",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnText"
					]
				},
				{
					"headerName": "Escludi Conteggio",
					"field": "MisuraConfigurazioneRiferimento$ESCLUSIONE_COUNTING",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnCheckbox"
					]
				},
				{
					"headerName": "Escludi Venduto",
')
    || TO_CLOB('
					"field": "MisuraConfigurazioneRiferimento$Escludi_Venduto",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnCheckbox"
					]
				}
			]
		}
	]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('0','config_filter.json',TO_CLOB('[
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
                  "ATTR_desc": "')
    || TO_CLOB('Caption_Default"
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
	')
    || TO_CLOB('			"ATTR_desc": "Anno"
			},
			{
				"ATTR_code": "canale",
				"ATTR_columnName": "Canale",
				"ATTR_desc": "Canale"
			},
			{
				"ATTR_code": "tipo",
				"ATTR_columnName": "MUI_Tipo Promozione Desc",
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
			')
    || TO_CLOB('	"ATTR_code": "semestre",
				"ATTR_columnName": "MUI_Semestre",
				"ATTR_desc": "Semestre"
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
				"ATTR_code": ')
    || TO_CLOB('"statodesc",
				"ATTR_columnName": "StatoDesc",
				"ATTR_desc": "Stato Desc"
			},
			{
				"ATTR_code": "canaleanno",
				"ATTR_columnName": "Canale_Anno",
				"ATTR_desc": "Canale Anno"
			}
		]
	},
	{
		"DIM_code": "promozioneriferimento",
		"DIM_columnName": "Promozione Riferimento",
		"DIM_description": "Promozione Riferimento",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}')
    || TO_CLOB(',[Promozione Riferimento].[Data_Inizio_Ist.], BDESC)}} , [Promozione Riferimento].[Canale], BASC),[Promozione Riferimento].[Gruppo],BASC)}",
		"list_ATTR": [
			{
				"ATTR_code": "anno",
				"ATTR_columnName": "Anno",
				"ATTR_desc": "Anno"
			},
			{
				"ATTR_code": "rif_canale",
				"ATTR_columnName": "MUI_Canale",
				"ATTR_desc": "Canale"
			},
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione + Riferimento",
				"ATTR_desc": "Promozione"
			},
			{')
    || TO_CLOB('
				"ATTR_code": "riferimento",
				"ATTR_columnName": "Descrizione + Data",
				"ATTR_desc": "Riferimento"
			},
			{
				"ATTR_code": "gruppo",
				"ATTR_columnName": "MUI_Tipo Promozione Desc",
				"ATTR_desc": "Gruppo"
			},
			{
				"ATTR_code": "semestre",
				"ATTR_columnName": "Semestre",
				"ATTR_desc": "Semestre"
			}
		]
	},
	{
		"DIM_code": "reparto",
		"DIM_columnName": "Reparto",
		"DIM_description": "Reparto",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSo')
    || TO_CLOB('urce": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}",
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
				"ATTR_code": "sigla",
				"ATTR_columnName": "Sigla",
				"ATTR_desc": "Sigla"
			},
			{
				"ATTR_code": "tipelemento",
				"ATTR_co')
    || TO_CLOB('lumnName": "TipoElemento",
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
				"ATTR_desc": "Category Manager"
			}
		]
	},
	{
		"DIM_code": "categoria",
		"DIM_columnName": "Categoria",
		"DIM_des')
    || TO_CLOB('cription": "Categoria",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{Except ( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_000],[Categoria].[CAT_0000] }  ) }",
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
			{
				"ATTR_code": "re')
    || TO_CLOB('partodesc",
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
		"DIM_code": "raggruppamentoFoto",
		"DIM_columnN')
    || TO_CLOB('ame": "Raggruppamento Foto",
		"DIM_description": "Raggruppamento Foto",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Raggruppamento Foto] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "tot",
				"ATTR_columnName": "MUI_TOT",
				"ATTR_desc": "Totali"
			},
			{
				"ATTR_code": "tots",
				"ATTR_columnName": "MUI_TOTS",
				"ATTR_desc": "Sub Totali"
			},
            {
				"ATTR_code": "mui_descrizione",
				"AT')
    || TO_CLOB('TR_columnName": "MUI_Descrizione",
				"ATTR_desc": "Descrizione"
			},
            {
				"ATTR_code": "mui_compratore",
				"ATTR_columnName": "MUI_Compratore",
				"ATTR_desc": "Compratore"
			},
          {
				"ATTR_code": "mui_reparto",
				"ATTR_columnName": "MUI_Reparto",
				"ATTR_desc": "Reparto"
			}
		]
	},
  {
		"DIM_code": "spazio",
		"DIM_columnName": "Spazio",
		"DIM_description": "Spazio",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1F')
    || TO_CLOB('ILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 1)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "compratore",
				"ATTR_columnName": "MUI_Compratore",
				"ATTR_desc": "Compratore"
			},
            {
				"ATTR_code": "reparto",
				"ATTR_columnName": "MUI_Reparto",
				"ATTR_desc": "Reparto"
			},
			{
				"ATTR_code": "macroSpazioDescrizione",
				"ATTR_columnName": "MacroSpazioDescrizione",
				"ATTR_desc": "Macro Spazio"
			}
		]
	},
	{
		"DIM_code": "compratore",
		"DIM')
    || TO_CLOB('_columnName": "Compratore",
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
				"ATTR_desc": "')
    || TO_CLOB('RepartoCodDesc"
			},
			{
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
		"MDX_')
    || TO_CLOB('jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Anno] )}, 0)}, DESC)}}",
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
				"ATTR_code": "TM1_DefaultDisplayVal')
    || TO_CLOB('ue",
				"ATTR_columnName": "}TM1_DefaultDisplayValue",
				"ATTR_desc": "Client"
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
				"ATTR_colum')
    || TO_CLOB('nName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "pubblicita",
		"DIM_columnName": "Pubblicità ",
		"DIM_description": "Pubblicità ",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ EXCEPT({{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Pubblicità ] )}, 0)}, ASC)}}, { [Pubblicità ].[RADIO] }) }",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
	')
    || TO_CLOB('	"DIM_code": "canale",
		"DIM_columnName": "Canale",
		"DIM_description": "Canale",
		"ENDPOINT_serverName": "promo",
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
		"ENDPO')
    || TO_CLOB('INT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Canale] )}, 0)}, ASC)}}",
		"list_ATTR": [
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
		"')
    || TO_CLOB('list_ATTR": [
			{
				"ATTR_code": "zonaPromo",
				"ATTR_columnName": "MUI_ZonaPromo",
				"ATTR_desc": "Zona Promo"
			},
			{
				"ATTR_code": "descrizione",
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
				"ATTR_desc": "Reparto')
    || TO_CLOB(' Profumeria"
			}
		]
	},
	{
		"DIM_code": "tipoPromozione",
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
				"ATTR_d')
    || TO_CLOB('esc": "Gruppo"
			}
		]
	},
	{
		"DIM_code": "macrospazio",
		"DIM_columnName": "MacroSpazio",
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
				"ATT')
    || TO_CLOB('R_desc": "Prestazioni EC"
			},
			{
				"ATTR_code": "prestazioniCNT",
				"ATTR_columnName": "Prestazioni CNT",
				"ATTR_desc": "Prestazioni CNT"
			}
		]
	},
	{
		"DIM_code": "misuraConfigurazioneMacrospaziNeg",
		"DIM_columnName": "Misura_Configurazione_Macrospazi_Neg",
		"DIM_description": "Configurazione Macrospazio Neg",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Neg] )}, 0)}, ASC)}}"')
    || TO_CLOB(',
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "misuraConfigurazioneMacrospaziNegPromo",
		"DIM_columnName": "Misura_Configurazione_Macrospazi_Neg_Promo",
		"DIM_description": "Configurazione Macrospazio Neg Promo",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Neg_Promo] )}, 0)}, ASC)}}",')
    || TO_CLOB('
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "misuraConfigurazioneMacrospaziListino",
		"DIM_columnName": "Misura_Configurazione_Macrospazi_Listino",
		"DIM_description": "Misura Configurazione Macrospazi Listino",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Listino] )}, 0)}, ASC)}}",
')
    || TO_CLOB('		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
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
				"ATTR')
    || TO_CLOB('_columnName": "Anno",
				"ATTR_desc": "Anno"
			},
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
')
    || TO_CLOB('
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione + Data",
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
	')
    || TO_CLOB('			"ATTR_code": "statodesc",
				"ATTR_columnName": "StatoDesc",
				"ATTR_desc": "Stato Desc"
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
		"list_A')
    || TO_CLOB('TTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
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
				"ATTR_de')
    || TO_CLOB('sc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_misuraTimone",
		"DIM_columnName": "REP - Misura Timone",
		"DIM_description": "Rep Misura Timone",
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
		"DIM_column')
    || TO_CLOB('Name": "REP - Scenario",
		"DIM_description": "Rep Scenario",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)}, ASC)}}",
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
				"AT')
    || TO_CLOB('TR_columnName": "SubGrm Desc",
				"ATTR_desc": "SubGrm Desc"
			}
		]
	},
	{
		"DIM_code": "rep_articolo",
		"DIM_columnName": "REP - Articolo",
		"DIM_description": "Rep Articolo",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 1)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "categoriadesc",
				"ATTR_columnName": "Categoria Desc",
				"ATTR_desc": "Categoria Desc"
			},
			{
				"ATTR_code": "')
    || TO_CLOB('grmdesc",
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
		"DIM_code": "rep_fornitore",
		"DIM_columnName": "REP - Fornitore",
		"DIM_description": "Rep Fornitore",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_c')
    || TO_CLOB('ode": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_zonaPromo",
		"DIM_columnName": "REP - Zona Promo",
		"DIM_description": "Rep Zona Promo",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			')
    || TO_CLOB('}
		]
	},
	{
		"DIM_code": "rep_sezioneTematica",
		"DIM_columnName": "REP - Sezione Tematica",
		"DIM_description": "Rep Sezione Tematica",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)}, ASC)}}",
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
		"DIM_columnNa')
    || TO_CLOB('me": "REP - Meccanica Semplice",
		"DIM_description": "Rep Meccanica Semplice",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)}, ASC)}}",
		"list_ATTR": [
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
		"EN')
    || TO_CLOB('DPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_MisuraReportingArticoloZona",
		"DIM_columnName": "REP - Misura Reporting Articolo Zona",
		"DIM_description": "Rep Articolo Zona",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "')
    || TO_CLOB('{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Reporting Articolo Zona] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_MisuraReportingArticolo",
		"DIM_columnName": "REP - Misura Reporting Articolo",
		"DIM_description": "Rep Articolo",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP')
    || TO_CLOB(' - Misura Reporting Articolo] )}, 0)}, ASC)}}",
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
		"DIM_description": "Rep Reparto",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Reparto] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
	')
    || TO_CLOB('			"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "fornitore",
		"DIM_columnName": "Fornitore",
		"DIM_description": "Fornitore",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "articoloFittizio",')
    || TO_CLOB('
		"DIM_columnName": "Articolo Fittizio",
		"DIM_description": "Articolo Fittizio",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo Fittizio] )}, 0)}, ASC)}}",
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
			}
		]
	},
	{
		"DIM_code')
    || TO_CLOB('": "sezioneTematica",
		"DIM_columnName": "Sezione Tematica",
		"DIM_description": "Sezione Tematica",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "scenario",
		"DIM_columnName": "Scenario",
		"DIM_description": "Scenario",
		"ENDPOINT_s')
    || TO_CLOB('erverName": "promo",
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
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}')
    || TO_CLOB('}",
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
          "MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Store Sales Plan] )}, 0)}, ASC)}}",
          "list_ATTR": [
              {
 ')
    || TO_CLOB('                 "ATTR_code": "Account",
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
          "MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Contratto] )}, 0)}, ASC)}}",
          "list_ATTR": [
              {
      ')
    || TO_CLOB('            "ATTR_code": "descrizione",
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
              "MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}",
              ')
    || TO_CLOB('"list_ATTR": [
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
              "ENDPOINT_serverName": "promo",
              "MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {T')
    || TO_CLOB('M1SUBSETALL( [Iniziativa] )}, 0)}, ASC)}}",
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
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL(')
    || TO_CLOB(' [Articolo] )}, 1)}, ASC)}}",
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
				"ATTR_columnName": "Attivo",
				"ATTR_desc": "Attivo"
			}
		')
    || TO_CLOB(']
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
			}
		]
	}
]
'),'GLOBAL');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('59','/Admin/Gestione_ACL',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('60','/Admin/Gestione_Applicazione',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('61','/Admin/Gestione_Configurazioni',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('62','/Admin/Gestione_Menu',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('63','/Admin/Gestione_Ruoli',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('64','/Admin/Gestione_UI',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('65','/Admin/Gestione_Utenti',' {
    "clients":["TM1_DefaultDisplayValue"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('66','/Fatturazione/Associazione_Articoli_Promozione',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('76','/Pianificazione/Selezione_Promo',' {
	"promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"tipoPromozione":["descrizione"],
	"prestazione":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('77','/Piano_Annuale/Controllo_Negozi',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('82','/Piano_Annuale/Gestione_Iniziative',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
     "iniziativa":["Descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('92','/Piano_Annuale/Spazi/Macrospazi_Negozi_Promo','{
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "macrospazio":["descrizione"],
    "negozio":["zonaPromo","descrizione"],
    "misuraConfigurazioneMacrospaziNegPromo":["descrizione"]
}
 ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('98','/Reporting/Dettaglio_Zona',' {
	"rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
	"rep_fornitore":["descrizione"],
	"rep_zonaPromo":["descrizione"],
	"rep_sezioneTematica":["descrizione"],
	"rep_meccanicaSemplice":["descrizione"],
	"rep_avolantino":["descrizione"],
	"rep_MisuraReportingArticoloZona":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('99','/Reporting/Sintesi_Campagna',' {
	"rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
	"rep_compratore":["descrizione"],
	"rep_categoria":["descrizione"],
	"rep_misuraTimone":["descrizione"],
	"rep_scenario":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('100','/Reporting/Storico_articolo_per_zona_(ACQ)',' {
	"rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
	"rep_fornitore":["descrizione"],
	"rep_zonaPromo":["descrizione"],
	"rep_sezioneTematica":["descrizione"],
	"rep_meccanicaSemplice":["descrizione"],
	"rep_avolantino":["descrizione"],
	"rep_MisuraReportingArticoloZona":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('101','/Reporting/Storico_articolo_per_zona_(MKT)',' {
	"rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
	"rep_fornitore":["descrizione"],
	"rep_zonaPromo":["descrizione"],
	"rep_sezioneTematica":["descrizione"],
	"rep_meccanicaSemplice":["descrizione"],
	"rep_avolantino":["descrizione"],
	"rep_MisuraReportingArticoloZona":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('103','/Analisi_Budget-Venduto/Articolo',' {
    "rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
    "rep_fornitore":["descrizione"],
    "rep_zonaPromo":["descrizione"],
    "rep_sezioneTematica":["descrizione"],
    "rep_meccanicaSemplice":["descrizione"],
    "rep_avolantino":["descrizione"],
    "rep_MisuraReportingArticoloZona":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('104','/Analisi_Budget-Venduto/Categoria',' {
    "rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "rep_compratore":["descrizione"],
    "rep_categoria":["descrizione"],
    "rep_misuraTimone":["descrizione"],
    "rep_scenario":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('93','/Piano_Annuale/Spazi/Macrospazi_Negozi',' {
    "macrospazio":["descrizione" ],
    "negozio":["zonaPromo","descrizione" ],
    "misuraConfigurazioneMacrospaziNeg":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('94','/Piano_Annuale/Spazi/Macrospazi',' {
    "macrospazio":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('86','/Piano_Annuale/Timing',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('90','/Piano_Annuale/Sezioni_Tematiche/Crea_Sezioni_Tematiche',' {
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('118','/Timone/Foto_Speciali/Target_(CAT)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_FotoSpeciali_Target",
		"logMemory": true,
		"logTime": true,
		"skip": true,
		"maxCells": 1000000 ,
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": false,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
					"Categor')
    || TO_CLOB('ia": "{{[Categoria].[CAT_0000]}, {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"

				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLE')
    || TO_CLOB('VEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]}}",
					"Sezione Tematica": "{{TM1SUBSETALL( [Sezione Tematica] )}}",
					"Misura Timone.": "{EXCEPT( {[Misura Timone.].[(II) Timone Categoria Sez]} , {[Misura Timone.].[ASSORT_P]} ) }"
				}
			},
			"FROM": "[Timone Categoria Sezione]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
				"Attributes",
')
    || TO_CLOB('
				"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				"Updateable",
				"Consolidated",
				"HasPicklist",
				"PicklistValues"
			]
		},
		"autoGroupColumnDef": {
			"cellRendererParams": {
				"suppressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","D')
    || TO_CLOB('escrizione","MUI_Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq",
					"openByDefault": true
				},
				"TGT_MKT":{
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TGT_REP":{
					"headerClass": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 110,
	')
    || TO_CLOB('			"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"ASSORT_P": {
					"type": ["TM1DataColumnText"]
				},
				"ST_0000": {
					"hide": true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup')
    || TO_CLOB('": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
')
    || TO_CLOB('
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},

			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false,
				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style')
    || TO_CLOB('_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  || (data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000'')  ",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP')
    || TO_CLOB('_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_0')
    || TO_CLOB('2'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('119','/Timone/Foto_Speciali/Target_(CAT)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre","descrizione","datainizioist"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('120','/Timone/Foto_Speciali/Target_(ACQ)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_FotoSpeciali_Target",
		"logMemory": true,
		"logTime": true,
		"skip": true,
		"maxCells": 1000000 ,
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": false,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Comp')
    || TO_CLOB('ratore].[MUI_Sort] , BASC )}  }",
					"Categoria": "{{[Categoria].[CAT_0000]}, {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"

				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": ')
    || TO_CLOB('{
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]}}",
					"Sezione Tematica": "{ EXCEPT({{TM1SUBSETALL( [Sezione Tematica] )}}  ,{[Sezione Tematica].[ST_000]}  )} ",
					"Misura Timone.": "{ EXCEPT({[Misura Timone.].[(II) Timone Categoria Sez]} , {[Misura Timone.].[ASSORT_P]}  ) }"
				}
			},
			"FROM": "[Timone Categoria Sezione]",
			"WHERE": {
				"Versione": "[UFF]"')
    || TO_CLOB('
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
				"Attributes",
				"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				"Updateable",
				"Consolidated",
				"HasPicklist",
				"PicklistValues"
			]
		},
		"autoGroupColumnDef": {
			"cellRendererParams": {
				"suppressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"Dy')
    || TO_CLOB('namicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","MUI_Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq",
					"openByDefault": true
				},
				"TGT_MKT":{
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TGT_REP":{
					"headerClass": "header')
    || TO_CLOB('Rep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"ASSORT_P": {
					"type": ["TM1DataColumnText"]
				},
				"ST_0000": {
					"hide": true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
')
    || TO_CLOB('
				"field": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width":')
    || TO_CLOB(' 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},

			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"e')
    || TO_CLOB('ditable": false,
				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  || (data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000'')  ",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes')
    || TO_CLOB('(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Comprato')
    || TO_CLOB('re.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('121','/Timone/Foto_Speciali/Target_(ACQ)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre","descrizione","datainizioist"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('122','/Timone/Spazi_Campagna/Target_Reparto_(CAT)',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_SpaziCampagna_TargetReparto",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
		"colSuppressionEnabled": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Pr')
    || TO_CLOB('omozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Reparto": "{{ORDER( {[Reparto].[REP_01] , [Reparto].[REP_01_01], [Reparto].[REP_01_02], [Reparto].[REP_01_03], [Reparto].[REP_01_04], [Reparto].[REP_09], [Reparto].[REP_12], [Reparto].[REP_12_01], [Reparto].[REP_12_02], [Reparto].[REP_12_04]  },[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": " {{[Scenario].[')
    || TO_CLOB('RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
            "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spaz')
    || TO_CLOB('i'')}  }"
          }
        },
        "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
"UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRe')
    || TO_CLOB('ndererParams": {
          "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCusto')
    || TO_CLOB('mTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT":{
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 110,
        ')
    || TO_CLOB('  "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "open",
          "editable": true,
          "type": [
            "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "N_ART_PROMO":  {
            "columnGroupShow":"always"
          },
          "TOT_FOTO":  {
            "columnGroupShow":"always"
          },
          "SPZ_CAMP":  {
            "columnGr')
    || TO_CLOB('oupShow":"always"
          }
        }
      },
      "columnDefs": [
        {
          "headerName": "Canale",
          "field": "Promozione.Canale",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
         ')
    || TO_CLOB(' "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 400,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descriz')
    || TO_CLOB('ione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Tipo Elemento",
          "field": "Reparto.TipoElemento",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "rowClassRules": ')
    || TO_CLOB('{
        "row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
        "row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
        "row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
        "row_style_4"')
    || TO_CLOB(': "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
      },
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [{
            "componentId": "tim_scr_inizializza",
            "componentLabel": "Inizializza Spazio",
            "process": "MUI_DUMMY_CUB.Timone Reparto Inizializza Spazi",
            "timeout":-1,
            "refresh": ["gc_SpaziCampagna_TargetReparto"]')
    || TO_CLOB(',
            "params":[{
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            }]
          }]
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('123','/Timone/Spazi_Campagna/Target_Reparto_(CAT)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('124','/Timone/Spazi_Campagna/Target_Reparto_(ACQ)',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_SpaziCampagna_TargetReparto",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
		"colSuppressionEnabled": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Pr')
    || TO_CLOB('omozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Reparto": "{ {[Reparto].[_REP_01],[Reparto].[_REP_02],[Reparto].[_REP_03],[Reparto].[_REP_04],[Reparto].[_REP_05],[Reparto].[_REP_06],[Reparto].[_REP_09],[Reparto].[_REP_11],[Reparto].[_REP_12],[Reparto].[_REP_14],[Reparto].[_REP_50]}  ,  {ORDER( {[Reparto].[REP_01] , [Reparto].[REP_01_01], [Reparto].[REP_01_02], [Reparto].[REP_01_03], [Reparto].[REP_01_04], [Reparto].[REP_09], [Reparto].[REP_12], [Reparto].[REP_12_0')
    || TO_CLOB('1], [Reparto].[REP_12_02], [Reparto].[REP_12_04]  },[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_REP]}}",
            "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]}')
    || TO_CLOB(',{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }"
          }
        },
        "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
"UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Va')
    || TO_CLOB('lue",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
  ')
    || TO_CLOB('      "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT":{
            "headerClass": "headerMkt",
            "openByDefault": true
          },
        ')
    || TO_CLOB('  "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 110,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "open",
          "editable": true,
          "type": [
            "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "N_ART_PROMO":  {
 ')
    || TO_CLOB('           "columnGroupShow":"always"
          },
          "TOT_FOTO":  {
            "columnGroupShow":"always"
          },
          "SPZ_CAMP":  {
            "columnGroupShow":"always"
          }
        }
      },
      "columnDefs": [
        {
          "headerName": "Canale",
          "field": "Promozione.Canale",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
 ')
    || TO_CLOB('         ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 400,
          "hide": true,
          "rowGroup": true,
    ')
    || TO_CLOB('      "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descrizione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Cod Reparto",
          "field": "Reparto.Name",
          "width": 120,
          "hide": ')
    || TO_CLOB('true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Tipo Elemento",
          "field": "Reparto.TipoElemento",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "rowClassRules": {
        "row_style_1": "[''_REP_01'',''_REP_02'',''_R')
    || TO_CLOB('EP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Reparto.Name)",
        "row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
        "row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
        "row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
      },
    ')
    || TO_CLOB('  "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": []
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('125','/Timone/Spazi_Campagna/Target_Reparto_(ACQ)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('126','/Timone/Spazi_Campagna/Target_Categoria_(CAT)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_SpaziCampagna_TargetCategoria",
		"logMemory": true,
		"logTime": true,
		"skip": true,
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": true,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},{EXCEPT( {{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}} , {[Compratore].[S000]} )}  }" ,
					"Categoria": "{{[Categoria')
    || TO_CLOB('].[MUI_TargetCategoria]}  ,{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }}"
				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]}')
    || TO_CLOB(',{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')} , { [Misura Timone.].[ASSORT_P]}  }"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
		')
    || TO_CLOB('		"Attributes",
"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				"Updateable",
				"Consolidated",
				"HasPicklist",
				"PicklistValues"
			]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": [
				"MUI_DescrizioneData",
				"Descrizione",
				"Descrizione"
			],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"headerCl')
    || TO_CLOB('ass": "headerAcq",
					"openByDefault": true
				},
				"TGT_MKT":{
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TGT_REP":{
					"headerClass": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editable": true,
				"type": [
					"TM1DataColumnInteger",
					"numericColumn"
				]
			},
			"childrenCust')
    || TO_CLOB('omTypes": {
				"N_ART_PROMO":  {
					"columnGroupShow":"always"
				},
				"TOT_FOTO":  {
					"columnGroupShow":"always"
				},
				"SPZ_CAMP":  {
					"columnGroupShow":"always"
				}
              ,
				"ASSORT_P":  {
					"hide": true
				}
			}
		},

		"autoGroupColumnDef" :  {  "cellRendererParams":{ "suppressCount": true }
		, "field": "Categoria.MUI_Descrizione"
		, "headerName": "Categoria"
		, "width":300
		, "pinned": "left"
		, "type":["TM1Element"]} ,

		"c')
    || TO_CLOB('olumnDefs": [
			{"headerName":"CategoryManager","field":"Compratore.CategoryManager","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Compratore","field":"Compratore.MUI_Descrizione","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Cod Compratore","field":"Compratore.Name","width":70,"hide":true,"rowGroup": false, "pinned": "left", "editable": false,"typ')
    || TO_CLOB('e":["TM1Element"]},
			{"headerName":"Livello Categoria","field":"Categoria.MUI_Level","width":70,"hide":true,"rowGroup": false, "pinned": "left", "editable": false,"type":["TM1Element"]},{"headerName":"Cod Categoria","field":"Categoria.Name","width":70,"hide":true,"rowGroup": false,  "editable": false,"type":["TM1Element"]}
		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Ca')
    || TO_CLOB('tegoria.MUI_Level > 0)",
			"row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_st')
    || TO_CLOB('yle_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse" : "(data.Compratore.Name == ''S000'' && (data.Categoria.Name  == ''_REP_01'' || data.Categoria.Name  == ''_REP_02'' || data.Categoria.Name  == ''_REP_03'' || data.Categoria.Name  == ''_REP_04'' || data.Categoria.Name  == ''_REP_05'' || data.Categoria.Name  == ''_REP_06''')
    || TO_CLOB(' || data.Categoria.Name  == ''_REP_09'' || data.Categoria.Name  == ''_REP_11'' || data.Categoria.Name  == ''_REP_12'' || data.Categoria.Name  == ''_REP_14'' || data.Categoria.Name  == ''_REP_50'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('127','/Timone/Spazi_Campagna/Target_Categoria_(CAT)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
     "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('128','/Timone/Spazi_Campagna/Target_Categoria_(ACQ)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_SpaziCampagna_TargetCategoria",
		"logMemory": true,
		"logTime": true,
		"skip": true,
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": false,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},{EXCEPT( {{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}} , {[Compratore].[S000]} )}  }" ,
					"Categoria": "{{[Categori')
    || TO_CLOB('a].[MUI_TargetCategoria]}  ,{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }}"
				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[M')
    || TO_CLOB('isura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')} , { [Misura Timone.].[ASSORT_P]}  }"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
				"Attributes",
')
    || TO_CLOB('"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				"Updateable",
				"Consolidated",
				"HasPicklist",
				"PicklistValues"
			]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": [
				"MUI_DescrizioneData",
				"Descrizione",
				"Descrizione"
			],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq"')
    || TO_CLOB(',
					"openByDefault": true
				},
				"TGT_MKT":{
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TGT_REP":{
					"headerClass": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editable": true,
				"type": [
					"TM1DataColumnInteger",
					"numericColumn"
				]
			},
			"childrenCustomTypes": {
				')
    || TO_CLOB('"N_ART_PROMO":  {
					"columnGroupShow":"always"
				},
				"TOT_FOTO":  {
					"columnGroupShow":"always"
				},
				"SPZ_CAMP":  {
					"columnGroupShow":"always"
				}
              ,
				"ASSORT_P":  {
					"hide": true
				}
			}
		},

		"autoGroupColumnDef" :  {  "cellRendererParams":{ "suppressCount": true }
		, "field": "Categoria.MUI_Descrizione"
		, "headerName": "Categoria"
		, "width":300
		, "pinned": "left"
		, "type":["TM1Element"]} ,

		"columnDefs": [
		')
    || TO_CLOB('	{"headerName":"CategoryManager","field":"Compratore.CategoryManager","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Compratore","field":"Compratore.MUI_Descrizione","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Cod Compratore","field":"Compratore.Name","width":70,"hide":true,"rowGroup": false, "pinned": "left", "editable": false,"type":["TM1Element"]')
    || TO_CLOB('},
			{"headerName":"Livello Categoria","field":"Categoria.MUI_Level","width":70,"hide":true,"rowGroup": false, "pinned": "left", "editable": false,"type":["TM1Element"]},{"headerName":"Cod Categoria","field":"Categoria.Name","width":70,"hide":true,"rowGroup": false,  "editable": false,"type":["TM1Element"]}
		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level')
    || TO_CLOB(' > 0)",
			"row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4" : "data.Co')
    || TO_CLOB('mpratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse" : "(data.Compratore.Name == ''S000'' && (data.Categoria.Name  == ''_REP_01'' || data.Categoria.Name  == ''_REP_02'' || data.Categoria.Name  == ''_REP_03'' || data.Categoria.Name  == ''_REP_04'' || data.Categoria.Name  == ''_REP_05'' || data.Categoria.Name  == ''_REP_06'' || data.Categori')
    || TO_CLOB('a.Name  == ''_REP_09'' || data.Categoria.Name  == ''_REP_11'' || data.Categoria.Name  == ''_REP_12'' || data.Categoria.Name  == ''_REP_14'' || data.Categoria.Name  == ''_REP_50'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('129','/Timone/Spazi_Campagna/Target_Categoria_(ACQ)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
     "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('130','/Timone/Target_Categoria/Promo_(CAT)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_TargetCategoria_Promo",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) " ,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM')
    || TO_CLOB('1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
                    "Categoria": "{{[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"

				}
	')
    || TO_CLOB('		},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Mis')
    || TO_CLOB('ura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO__perc_]},{[Misura Timone.].[D_FOTO_slash_TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF_slash_TGT_REP]},{[Misura Timone.].[ASSORT_P]}}"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
				"Attributes",
"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				"Updateable",
				"Consolidated",
				"HasPic')
    || TO_CLOB('klist",
				"PicklistValues"
			]
		},
		"autoGroupColumnDef": {
			"cellRendererParams": {
				"suppressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_')
    || TO_CLOB('MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq",
					"openByDefault": true
				},
				"TGT_MKT":{
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TGT_REP":{
					"headerClass": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",

				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1')
    || TO_CLOB('DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"VENDUTO_PROMO_NETTO": {
					"type": ["TM1DataColumnInteger", "numericColumn"],
					"columnGroupShow": "always"
				},
				"MARGINE_LORDO__perc_": {
					"type": ["TM1DataColumnNumber", "numericColumn"],
					"columnGroupShow": "always"
				},
				"ASSORT_P": {
					"hide":true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field": "Compratore.CategoryMana')
    || TO_CLOB('ger",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hide": true,
				"rowGroup')
    || TO_CLOB('": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false,
				"type": ["TM1Eleme')
    || TO_CLOB('nt"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_')
    || TO_CLOB('REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',')
    || TO_CLOB('''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('131','/Timone/Target_Categoria/Promo_(CAT)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('132','/Timone/Target_Categoria/Promo_(ACQ)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_TargetCategoria_Promo",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) " ,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT')
    || TO_CLOB(' ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
                    "Categoria": "{{[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
  ')
    || TO_CLOB('
				}
			},
			"COLS": {
				"NON_EMPTY": false,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCA')
    || TO_CLOB('FFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO__perc_]},{[Misura Timone.].[D_FOTO_slash_TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF_slash_TGT_REP]},{[Misura Timone.].[ASSORT_P]}}"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
				"Attributes",
"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
	')
    || TO_CLOB('			"Updateable",
				"Consolidated",
				"HasPicklist",
				"PicklistValues"
			]
		},
		"autoGroupColumnDef": {
			"cellRendererParams": {
				"suppressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren"')
    || TO_CLOB(': true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq",
					"openByDefault": true
				},
				"TGT_MKT":{
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TGT_REP":{
					"headerClass": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",

				"columnGroupShow":')
    || TO_CLOB(' "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"VENDUTO_PROMO_NETTO": {
					"type": ["TM1DataColumnInteger", "numericColumn"],
					"columnGroupShow": "always"
				},
				"MARGINE_LORDO__perc_": {
					"type": ["TM1DataColumnNumber", "numericColumn"],
					"columnGroupShow": "always"
				},
				"ASSORT_P": {
					"hide":true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "Catego')
    || TO_CLOB('ryManager",
				"field": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
')
    || TO_CLOB('				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": fals')
    || TO_CLOB('e,
				"editable": false,
				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S')
    || TO_CLOB('000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''')
    || TO_CLOB('S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('133','/Timone/Target_Categoria/Promo_(ACQ)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('134','/Timone/Target_Categoria/Data_(CAT)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_TargetCategoria_Data",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) " ,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1')
    || TO_CLOB('SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
                    "Categoria": "{{[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
				}
			},
			"COLS": {
	')
    || TO_CLOB('			"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO')
    || TO_CLOB('_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO__perc_]},{[Misura Timone.].[D_FOTO_slash_TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF_slash_TGT_REP]},{[Misura Timone.].[ASSORT_P]} }"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
				"Attributes",
"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				"Updateable",
				"Consolidated",
				"HasPicklist",
				"Pickli')
    || TO_CLOB('stValues"
			]
		},
		"autoGroupColumnDef": {
			"cellRendererParams": {
				"suppressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"ope')
    || TO_CLOB('nByDefault": true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq",
					"openByDefault": true
				},
				"TGT_MKT":{
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TGT_REP":{
					"headerClass": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "n')
    || TO_CLOB('umericColumn"]
			},
			"childrenCustomTypes": {
				"VENDUTO_PROMO_NETTO": {
					"type": ["TM1DataColumnInteger", "numericColumn"],
					"columnGroupShow": "always"
				},
				"MARGINE_LORDO__perc_": {
					"type": ["TM1DataColumnNumber", "numericColumn"],
					"columnGroupShow": "always"
				},
				"ASSORT_P": {
					"hide": true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field": "Compratore.CategoryManager",
				"width": 7')
    || TO_CLOB('0,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinne')
    || TO_CLOB('d": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false,

				"type": ["TM1Element"]
			}

		],
')
    || TO_CLOB('
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12')
    || TO_CLOB('_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06''')
    || TO_CLOB(',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('143','/Timone/Target_Reparto/Promo_(CAT)','{
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('144','/Timone/Target_Reparto/Promo_(ACQ)',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Promo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Re')
    || TO_CLOB('parto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]}}"
          }
        },
 ')
    || TO_CLOB('       "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
        ')
    || TO_CLOB('  "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Anno",
          "field": "Promozione.Anno",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
')
    || TO_CLOB('        },
        {

          "headerName": "Semestre",
          "field": "Promozione.MUI_Semestre",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Data",
          "field": "Promozione.DataInizioIst",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
         ')
    || TO_CLOB(' "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 400,
          "hide": t')
    || TO_CLOB('rue,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descrizione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento",
          "field": "Promozione.Riferimen')
    || TO_CLOB('to",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento Data",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
       ')
    || TO_CLOB('       "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
         ')
    || TO_CLOB('     "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              ')
    || TO_CLOB('"columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open"')
    || TO_CLOB(',
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable')
    || TO_CLOB('": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
    ')
    || TO_CLOB('          "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
        ')
    || TO_CLOB('        "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Venduto Promo (s/iva)",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1D')
    || TO_CLOB('ataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "ML %",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "weightedAvg",
              "aggFuncParam": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "columnGroupShow": "open",
       ')
    || TO_CLOB('       "editable": true,

              "type": [
                "TM1DataColumnPercentage",
                "numericColumn"
              ]
            },
            {
              "headerName": "FF C",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$CONTR",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
             ')
    || TO_CLOB(' "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "FF EC",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$EXTRA_CONTR",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataC')
    || TO_CLOB('olumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target MKT",
          "headerClass": "headerMkt",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc":')
    || TO_CLOB(' "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow')
    || TO_CLOB('": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": tru')
    || TO_CLOB('e,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
')
    || TO_CLOB('
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColu')
    || TO_CLOB('mnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
    ')
    || TO_CLOB('            "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
   ')
    || TO_CLOB('           ]
            }
          ]
        },
        {
          "headerName": "Target REP",
          "headerClass": "headerRep",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
')
    || TO_CLOB('
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
     ')
    || TO_CLOB('         "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1Dat')
    || TO_CLOB('aColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
          ')
    || TO_CLOB('      "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
      ')
    || TO_CLOB('        ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
      ')
    || TO_CLOB('      },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
       ')
    || TO_CLOB('       "headerName": "D Foto/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "')
    || TO_CLOB('D Foto Banco/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerNa')
    || TO_CLOB('me": "Target Acq.",
          "headerClass": "headerAcq",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataC')
    || TO_CLOB('olumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numeric')
    || TO_CLOB('Column"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
  ')
    || TO_CLOB('          {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "h')
    || TO_CLOB('eaderName": "N. Foto Speciali",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Ban')
    || TO_CLOB('co",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "fi')
    || TO_CLOB('eld": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Rep",
              "field": "Scenario$TGT_REP$$MisuraTi')
    || TO_CLOB('mone_dot_$D_FOTO_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Rep",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_')
    || TO_CLOB('SCAFF_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        }
      ],
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoEleme')
    || TO_CLOB('nto == ''R''"
      },
      "actions": [
        {
          "componentId": "tim_trd_inizializza",
          "componentLabel": "Inizializza Target",
          "process": "MUI_DUMMY_CUB.TimoneRIF.TGT4",
          "timeout": -1,
          "refresh": [
            "gc_TargetReparto_Promo"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
    ')
    || TO_CLOB('          "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Gruppo",
              "paramName": "inGruppo",
              "label": "Gruppo",
              "hasPicklist": true
            },
     ')
    || TO_CLOB('       {
              "dimension": "Reparto",
              "attribute": "Descrizione",
              "paramName": "inReparto",
              "label": "Reparto",
              "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('145','/Timone/Target_Reparto/Promo_(ACQ)','{
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('147','/Reporting/Storico_Articolo_Zona_Promo','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('146','/Reporting/Storico_Articolo_Zona_Promo','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('148','/Pianificazione/Aggiungi_Articoli','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('149','/Pianificazione/Aggiungi_Articoli','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('135','/Timone/Target_Categoria/Data_(CAT)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('136','/Timone/Target_Categoria/Data_(ACQ)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_TargetCategoria_Data",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) " ,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ')
    || TO_CLOB('( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
                    "Categoria": "{{[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
			')
    || TO_CLOB('	}
			},
			"COLS": {
				"NON_EMPTY": false,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura')
    || TO_CLOB(' Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO__perc_]},{[Misura Timone.].[D_FOTO_slash_TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF_slash_TGT_REP]},{[Misura Timone.].[ASSORT_P]} }"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
				"Attributes",
"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				"Updateable",
		')
    || TO_CLOB('		"Consolidated",
				"HasPicklist",
				"PicklistValues"
			]
		},
		"autoGroupColumnDef": {
			"cellRendererParams": {
				"suppressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
			},
			"h')
    || TO_CLOB('eaderCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq",
					"openByDefault": true
				},
				"TGT_MKT":{
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TGT_REP":{
					"headerClass": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editabl')
    || TO_CLOB('e": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"VENDUTO_PROMO_NETTO": {
					"type": ["TM1DataColumnInteger", "numericColumn"],
					"columnGroupShow": "always"
				},
				"MARGINE_LORDO__perc_": {
					"type": ["TM1DataColumnNumber", "numericColumn"],
					"columnGroupShow": "always"
				},
				"ASSORT_P": {
					"hide": true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"fie')
    || TO_CLOB('ld": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
			')
    || TO_CLOB('	"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": f')
    || TO_CLOB('alse,

				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_0')
    || TO_CLOB('1'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01''')
    || TO_CLOB(',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('137','/Timone/Target_Categoria/Data_(ACQ)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('138','/Timone/Target_Reparto/Data_(CAT)',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Data",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Rep')
    || TO_CLOB('arto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]}}"
          }
        },
  ')
    || TO_CLOB('      "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
         ')
    || TO_CLOB(' "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Anno",
          "field": "Promozione.Anno",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
 ')
    || TO_CLOB('       },
        {

          "headerName": "Semestre",
          "field": "Promozione.MUI_Semestre",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Data",
          "field": "Promozione.DataInizioIst",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "')
    || TO_CLOB('type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 400,
          "hide": tru')
    || TO_CLOB('e,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descrizione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento",
          "field": "Promozione.Riferimento')
    || TO_CLOB('",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento Data",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
         ')
    || TO_CLOB('     "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
           ')
    || TO_CLOB('   "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "c')
    || TO_CLOB('olumnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
')
    || TO_CLOB('
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable":')
    || TO_CLOB(' true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
      ')
    || TO_CLOB('        "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
          ')
    || TO_CLOB('      "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Venduto Promo (s/iva)",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1Dat')
    || TO_CLOB('aColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "ML %",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "weightedAvg",
              "aggFuncParam": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "columnGroupShow": "open",
         ')
    || TO_CLOB('     "editable": true,

              "type": [
                "TM1DataColumnPercentage",
                "numericColumn"
              ]
            },
            {
              "headerName": "FF C",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$CONTR",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "')
    || TO_CLOB('type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "FF EC",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$EXTRA_CONTR",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataCol')
    || TO_CLOB('umnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target MKT",
          "headerClass": "headerMkt",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "')
    || TO_CLOB('sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow":')
    || TO_CLOB(' "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,')
    || TO_CLOB('
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
 ')
    || TO_CLOB('               "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumn')
    || TO_CLOB('Integer",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
      ')
    || TO_CLOB('          "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
     ')
    || TO_CLOB('         ]
            }
          ]
        },
        {
          "headerName": "Target REP",
          "headerClass": "headerRep",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
 ')
    || TO_CLOB('             "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
       ')
    || TO_CLOB('       "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataC')
    || TO_CLOB('olumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
            ')
    || TO_CLOB('    "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
        ')
    || TO_CLOB('      ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
        ')
    || TO_CLOB('    },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
         ')
    || TO_CLOB('     "headerName": "D Foto/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D ')
    || TO_CLOB('Foto Banco/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName')
    || TO_CLOB('": "Target Acq.",
          "headerClass": "headerAcq",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataCol')
    || TO_CLOB('umnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericCo')
    || TO_CLOB('lumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
    ')
    || TO_CLOB('        {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "hea')
    || TO_CLOB('derName": "N. Foto Speciali",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco')
    || TO_CLOB('",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "fiel')
    || TO_CLOB('d": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Rep",
              "field": "Scenario$TGT_REP$$MisuraTimo')
    || TO_CLOB('ne_dot_$D_FOTO_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Rep",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SC')
    || TO_CLOB('AFF_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        }
      ],
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElement')
    || TO_CLOB('o == ''R''"
      },
      "actions": [
        {
          "componentId": "tim_trd_inizializza",
          "componentLabel": "Inizializza Reference",
          "process": "MUI_DUMMY_CUB.Timone.Inizializza Reference",
          "timeout": -1,
          "refresh": [
            "gc_TargetReparto_Data"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label":')
    || TO_CLOB(' "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Gruppo",
              "paramName": "inGruppo",
              "label": "Gruppo",
              "hasPicklist": true
       ')
    || TO_CLOB('     }
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('139','/Timone/Target_Reparto/Data_(CAT)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('140','/Timone/Target_Reparto/Data_(ACQ)',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Data",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Rep')
    || TO_CLOB('arto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]}}"
          }
        },
  ')
    || TO_CLOB('      "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
         ')
    || TO_CLOB(' "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Anno",
          "field": "Promozione.Anno",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
 ')
    || TO_CLOB('       },
        {

          "headerName": "Semestre",
          "field": "Promozione.MUI_Semestre",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Data",
          "field": "Promozione.DataInizioIst",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "')
    || TO_CLOB('type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 400,
          "hide": tru')
    || TO_CLOB('e,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descrizione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento",
          "field": "Promozione.Riferimento')
    || TO_CLOB('",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento Data",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
         ')
    || TO_CLOB('     "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
           ')
    || TO_CLOB('   "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "c')
    || TO_CLOB('olumnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
')
    || TO_CLOB('
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable":')
    || TO_CLOB(' true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
      ')
    || TO_CLOB('        "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
          ')
    || TO_CLOB('      "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Venduto Promo (s/iva)",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1Dat')
    || TO_CLOB('aColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "ML %",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "weightedAvg",
              "aggFuncParam": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "columnGroupShow": "open",
         ')
    || TO_CLOB('     "editable": true,

              "type": [
                "TM1DataColumnPercentage",
                "numericColumn"
              ]
            },
            {
              "headerName": "FF C",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$CONTR",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "')
    || TO_CLOB('type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "FF EC",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$EXTRA_CONTR",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataCol')
    || TO_CLOB('umnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target MKT",
          "headerClass": "headerMkt",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "')
    || TO_CLOB('sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow":')
    || TO_CLOB(' "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,')
    || TO_CLOB('
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
 ')
    || TO_CLOB('               "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumn')
    || TO_CLOB('Integer",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
      ')
    || TO_CLOB('          "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
     ')
    || TO_CLOB('         ]
            }
          ]
        },
        {
          "headerName": "Target REP",
          "headerClass": "headerRep",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
 ')
    || TO_CLOB('             "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
       ')
    || TO_CLOB('       "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataC')
    || TO_CLOB('olumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
            ')
    || TO_CLOB('    "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
        ')
    || TO_CLOB('      ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
        ')
    || TO_CLOB('    },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
         ')
    || TO_CLOB('     "headerName": "D Foto/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D ')
    || TO_CLOB('Foto Banco/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName')
    || TO_CLOB('": "Target Acq.",
          "headerClass": "headerAcq",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataCol')
    || TO_CLOB('umnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericCo')
    || TO_CLOB('lumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
    ')
    || TO_CLOB('        {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "hea')
    || TO_CLOB('derName": "N. Foto Speciali",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco')
    || TO_CLOB('",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "fiel')
    || TO_CLOB('d": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Rep",
              "field": "Scenario$TGT_REP$$MisuraTimo')
    || TO_CLOB('ne_dot_$D_FOTO_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Rep",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SC')
    || TO_CLOB('AFF_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        }
      ],
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElement')
    || TO_CLOB('o == ''R''"
      },
      "actions": [
        {
          "componentId": "tim_trd_inizializza",
          "componentLabel": "Inizializza Target",
          "process": "MUI_DUMMY_CUB.TimoneRIF.TGT4",
          "timeout": -1,
          "refresh": [
            "gc_TargetReparto_Data"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
       ')
    || TO_CLOB('       "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Gruppo",
              "paramName": "inGruppo",
              "label": "Gruppo",
              "hasPicklist": true
            },
        ')
    || TO_CLOB('    {
              "dimension": "Reparto",
              "attribute": "Descrizione",
              "paramName": "inReparto",
              "label": "Reparto",
              "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('141','/Timone/Target_Reparto/Data_(ACQ)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('142','/Timone/Target_Reparto/Promo_(CAT)',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Promo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 1000000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozio')
    || TO_CLOB('ne],BASC)}",
            "Reparto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]}}"
          }
        },
')
    || TO_CLOB('        "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
       ')
    || TO_CLOB('   "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Canale",
          "field": "Promozione.Canale",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
       ')
    || TO_CLOB('   ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 400,
          "hide": true,
          "rowGroup": true,
          ')
    || TO_CLOB('"editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descrizione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento",
          "field": "Promozione.Riferimento",
          "width": 70,
          "hide')
    || TO_CLOB('": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Tipo Elemento",
          "field": "Reparto.TipoElemento",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento Data",
          "openByDefault')
    || TO_CLOB('": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },')
    || TO_CLOB('
            {
              "headerName": "Totale",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "head')
    || TO_CLOB('erName": "N. Foto",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
             ')
    || TO_CLOB(' "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$RI')
    || TO_CLOB('F_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_')
    || TO_CLOB('dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
')
    || TO_CLOB('              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Venduto Promo (s/iva)",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
             ')
    || TO_CLOB(' "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "ML %",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
              "width": 80,
              ')
    || TO_CLOB('"hide": false,
              "rowGroup": false,
              "aggFunc": "weightedAvg",
              "aggFuncParam": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnPercentage",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target MKT",
          "headerClass": "header')
    || TO_CLOB('Mkt",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
   ')
    || TO_CLOB('           ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
         ')
    || TO_CLOB('   {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto ')
    || TO_CLOB('Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "f')
    || TO_CLOB('ield": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_MKT$$')
    || TO_CLOB('MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FO')
    || TO_CLOB('TO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target REP",
          "headerClass": "headerRep",
          "openByDefault": true,
          "')
    || TO_CLOB('children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
       ')
    || TO_CLOB('       "headerName": "Totale",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
      ')
    || TO_CLOB('        "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_REP$$M')
    || TO_CLOB('isuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_S')
    || TO_CLOB('PEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
          ')
    || TO_CLOB('    "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              ')
    || TO_CLOB('"hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
              "width": 80,
              "hide": false,
      ')
    || TO_CLOB('        "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "r')
    || TO_CLOB('owGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target Acq.",
          "headerClass": "headerAcq",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "')
    || TO_CLOB('field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_ACQ$$MisuraTimo')
    || TO_CLOB('ne_dot_$TOT_FOTO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
              "width"')
    || TO_CLOB(': 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": ')
    || TO_CLOB('true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup')
    || TO_CLOB('": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
       ')
    || TO_CLOB('       "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
       ')
    || TO_CLOB('       "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Rep",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow')
    || TO_CLOB('": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Rep",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
')
    || TO_CLOB('
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        }
      ],
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [
        {
          "componentId": "tim_trp_inizializza",
          "componentLabel": "Inizializza Reference",
          "process": "MUI_DUMMY')
    || TO_CLOB('_CUB.Timone.Inizializza Reference",
          "timeout": -1,
          "refresh": [
            "gc_TargetReparto_Promo"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName')
    || TO_CLOB('": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Gruppo",
              "paramName": "inGruppo",
              "label": "Gruppo",
              "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}
'),'GRID');