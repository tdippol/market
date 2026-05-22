SET DEFINE OFF;

DELETE FROM MUIPROMO.MENU_ROLES;

DELETE FROM MUIPROMO.MENU;

Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('207','74','Sintesi',null,null,'24','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('209','207','Sintesi Campagna - Volantino','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Sintesi%20Campagna%20-%20Volantino&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','1','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('208','84','Margine Rettificato S38','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Pianificazione%20Campagna%20(Mrg%20rettificato%20S38)&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','19','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('210','207','Sintesi Campagna Zona (Marketing)','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Sintesi%20Campagna%20Zona(Marketing)&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','3','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('232','74','Conferma Prezzi',null,null,'19','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('212','207','Sintesi Dati Panieri e Sconto più Facile','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Sintesi%20Dati%20Panieri%20e%20Sconto%20più%20Facile.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','4','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('213','207','Sintesi Campagna (GAS)','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Sintesi%20Campagna%20(GAS).xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','5','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('233','232','Conferma Prezzi','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Conferma%20Prezzi.xlsx &AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','1','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('241','49','Progressivo Pigne','layouts/1-0-0.xhtml','agGrid','10','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('234','232','Conferma Prezzi MKT','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Conferma%20Prezzi%20MKTG.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','2','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('217','207','Sintesi Campagna Zona','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Sintesi%20Campagna%20Zona.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','7','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('219','207','Sintesi Campagna','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Sintesi%20Campagna.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','8','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('220','74','Report',null,null,'23','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('224','220','Report Spazi Articoli','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Report%20Spazi%20Articoli%20v2&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','3','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('222','220','Report Contributi','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Report%20Contributi_v2.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','1','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('223','220','Report Delta spazi','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Report%20Delta%20Spazi&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','2','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('225','220','Report Spazi Fornitore Marketing','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Report%20Spazi%20Fornitore%20MKTG&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','4','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('226','220','Report Spazi Fornitore','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Report%20Spazi%20Fornitore&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','5','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('227','99','Controlli Campagna Buyer','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/x)%20Controlli/Controlli%20Campagna%20Buyer.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','2','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('228','99','Sintesi Campagna','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/x)%20Controlli/Sintesi%20Campagna_2.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','3','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('229','220','Storico Articoli Pianificati','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Storico%20articoli%20pianificati&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','6','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('230','220','Riepilogo Piano Promozioni','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Riepilogo%20Piano%20Campagne.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','7','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('231','220','Impegno Fornitore (ridotto)','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Impegno%20Fornitore%20(Ridotto).xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','8','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('235','232','Conferma Prezzi Category','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Conferma%20Prezzi%20(Category).xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','3','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('236','207','Sintesi Campagna - Volantino (gest, diff.)','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Sintesi%20Campagna%20-%20Volantino%20(gestione%20differenziati).xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','2','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('239','63','Crea Paniere Proximity','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.9%20Proximity/Crea%20Paniere%20Proximity.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','1','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('238','207','Sintesi Campagna Zona (GAS - PAD)','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Sintesi%20Campagna%20Zona%20(GAS-PAD).xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','6','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('1',null,'Piano Annuale',null,null,'1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('2','1','Visualizza Piano','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('3','1','Modifica Promozione','layouts/1-0-0.xhtml','agGrid','3','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('4','1','Crea Promozione','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('5','1','Timing','layouts/1-0-0.xhtml','agGrid','4','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('6','1','Meccaniche','layouts/1-0-0.xhtml','agGrid','5','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('8','1','Zone Promo','layouts/1-0-0.xhtml','agGrid','7','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('9','1','Sezioni Tematiche',null,null,'8','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('11','1','Negozi Promo','layouts/1-0-0.xhtml','agGrid','10','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('12','1','Controllo Negozi','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.1%20Piano%20Annuale/Controllo%20Negozi.xslx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','11','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('13','1','Foto','layouts/2-1-0.xhtml','agGrid','12','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('14','1','Gestione Contributi','layouts/1-0-0.xhtml','agGrid','13','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('15','1','Gestione Iniziative','layouts/1-0-0.xhtml','agGrid','14','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('16','1','Gabbia','layouts/2-0-0.xhtml','agGrid','15','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('17',null,'Timone',null,null,'2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('18','17','Associazione Promo','layouts/2-1-1.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('19','17','Target Reparto',null,null,'2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('20','19','Promo','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('21','19','Data','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('22','17','Target Categoria',null,null,'3','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('23','22','Promo','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('24','22','Data','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('25','17','Foto Speciali',null,null,'4','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('26','25','Target','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('27','25','Riepilogo','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('28','17','Spazi Campagna',null,null,'5','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('29','28','Target Reparto','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('30','28','Target Categoria','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('31',null,'Admin',null,null,'999','0',null);
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('32','31','Gestione Utenti','admin/gestioneUtenti.xhtml','gestioneUtenti','1','0',null);
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('33','31','Gestione Ruoli','admin/gestioneRuoli.xhtml','gestioneRuoli','2','0',null);
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('34','31','Gestione UI','admin/gestioneUI.xhtml','gestioneUI','3','0',null);
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('35','31','Gestione ACL','admin/gestioneACL.xhtml','gestioneACL','4','0',null);
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('39','9','Sezioni Tematiche','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('40','9','Crea Sezioni Tematiche','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('41','1','Spazi',null,null,'16','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('42','41','Macrospazi','layouts/1-1-1.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('43','41','Macrospazi Negozi','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('44','41','Macrospazi Negozi Promo','layouts/1-0-0.xhtml','agGrid','3','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('45','41','Macrospazi Listino Promo','layouts/1-0-0.xhtml','agGrid','4','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('46','41','Spazi Condivisi','layouts/2-1-0.xhtml','agGrid','5','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('47','31','Gestione Menu','admin/gestioneMenu.xhtml','gestioneMenu','5','0',null);
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('48','31','Gestione Configurazioni','admin/gestioneConfigurazioni.xhtml','gestioneConfigurazioni','6','0',null);
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('49',null,'Reporting',null,null,'5','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('50','49','Analisi Budget-Venduto',null,null,'1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('51','50','Categoria','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('52','50','Articolo','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('53','49','Storico articolo per zona (ACQ)','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('54','49','Copia in Pianificazione','layouts/1-0-0.xhtml','agGrid','3','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('55','49','Sintesi Campagna','layouts/1-0-0.xhtml','agGrid','4','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('56','49','Dettaglio Campagna','layouts/1-0-0.xhtml','agGrid','5','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('57','49','Dettaglio Zona','layouts/1-0-0.xhtml','agGrid','6','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('58','49','Timone Reparto','layouts/1-0-0.xhtml','agGrid','7','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('59','49','Storico articolo per zona (MKT)','layouts/1-0-0.xhtml','agGrid','8','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('60',null,'Fatturazione',null,null,'4','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('61','60','Contribuzione Campagna','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('62','60','Associazioni Articoli','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('63',null,'Proximity',null,'agGrid','6','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('64','63','Timing','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('65','63','Negozi Promo','layouts/1-0-0.xhtml','agGrid','3','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('66','63','Proiezioni','layouts/1-0-0.xhtml','agGrid','4','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('67','63','Articoli per Negozio','layouts/1-0-0.xhtml','agGrid','5','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('68','63','Set per Negozio','layouts/1-0-0.xhtml','agGrid','6','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('74',null,'Pianificazione',null,null,'3','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('75','74','Selezione Promo','layouts/1-1-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('76','74','Selezione Articoli Contributi','layouts/1-1-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('77','74','Articoli Fittizi','layouts/1-0-0.xhtml','agGrid','4','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('78','74','Proiezioni','layouts/1-1-1.xhtml','agGrid','5','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('79','74','Differenziazione Zone','layouts/1-0-0.xhtml','agGrid','6','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('80','74','Meccaniche Set','layouts/2-0-0.xhtml','agGrid','7','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('81','74','Configurazione Spazi Negozi','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Report%20Configurazione%20Spazi%20Negozi&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','8','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('82','74','Report Note','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Report%20Note%20Spazi&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','9','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('83','74','Controlli e Pubblicazione','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/x)%20Controlli/Controlli%20Campagna%20Buyer%20CDD.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','10','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('84','74','Margine Rettificato',null,null,'11','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('85','84','Margine Rettificato','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Pianificazione%20Campagna (Mrg%20rettificato)&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','12','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('86','84','Margine Rettificato V2','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Pianificazione%20Campagna (Mrg%20rettificato)%20v.2.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','13','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('87','84','Margine Rettificato Multicampagna','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Pianificazione%20Fornitore%20-%20Multi Campagna%20(Mrg%20rettificato)&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','14','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('90','74','Selezione Famiglie Contributi','layouts/1-1-0.xhtml','agGrid','3','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('91','60','Contribuzione Promozioni','layouts/1-0-0.xhtml','agGrid','3','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('92','31','Gestione Log','/tracker/',null,'10','1',null);
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('93','31','Gestione Applicazione','admin/gestioneApplicazione.xhtml','gestioneApplicazione','9','0',null);
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('95','1','Gestione Date','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.1%20Piano%20Annuale/Gestione%20date.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','17','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('96','1','Riepilogo Piano Annuale','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.1%20Piano%20Annuale/Riepilogo%20Piano%20Campagne.xlsx&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','18','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('97','17','Gestione Timone','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.2%20Timone/Gestione%20Timone&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','7','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('98','17','Gestione Timone Capo Reparto','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.2%20Timone/Gestione%20Timone%20Capo%20Reparto&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','8','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('99',null,'Controlli',null,null,'7','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('100','99','Controlli Campagna MKTG','http://tmoneprod.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/x)%20Controlli/Controlli%20Campagna%20MKTG&AdminHost=tmoneprod.mil.esselunga.net&TM1Server=Promo','agGrid','1','1','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('101','25','Target (CAT)','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('102','25','Target (ACQ)','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('103','28','Target Reparto (CAT)','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('104','28','Target Reparto (ACQ)','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('105','28','Target Categoria (CAT)','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('106','28','Target Categoria (ACQ)','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('107','22','Promo (CAT)','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('108','22','Promo (ACQ)','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('109','22','Data (CAT)','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('110','22','Data (ACQ)','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('111','19','Promo (CAT)','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('112','19','Promo (ACQ)','layouts/1-0-0.xhtml','agGrid','1','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('113','19','Data (CAT)','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('114','19','Data (ACQ)','layouts/1-0-0.xhtml','agGrid','2','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('115','49','Storico Articolo Zona Promo','layouts/1-0-0.xhtml','agGrid','9','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('94','60','Associazione Articoli Promozione','layouts/1-0-0.xhtml','agGrid','4','0','1');
Insert into MUIPROMO.MENU (ID_MENU,PARENT_ID,LABEL,URL,BEAN,ORDER_ID,EXTERNAL_LINK,TEMPLATE) values ('202','74','Informazioni Aggiuntive (MKT)','layouts/1-0-0.xhtml','agGrid','18','0','1');

Insert into MUIPROMO.ROLES (ID,NAME,DESCRIPTION,HELP_FILENAME) values ('10','NO_MENU','Nessuna Voce di Menu',null);

Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('62','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('94','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('229','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('231','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('51','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('49','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('87','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('213','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('52','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('217','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('74','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('86','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('50','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('90','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('226','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('99','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('220','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('67','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('61','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('60','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('81','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('207','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('80','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('53','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('91','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('54','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('68','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('227','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('63','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('230','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('76','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('83','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('222','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('82','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('233','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('78','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('84','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('208','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('238','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('232','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('77','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('66','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('219','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('224','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('79','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('75','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('115','6');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('77','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('224','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('209','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('113','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('23','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('50','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('47','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('95','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('78','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('98','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('99','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('3','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('92','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('104','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('64','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('80','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('53','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('9','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('241','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('94','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('21','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('76','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('219','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('60','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('27','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('228','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('52','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('67','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('84','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('232','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('110','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('109','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('82','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('103','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('107','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('101','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('42','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('111','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('223','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('202','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('65','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('25','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('2','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('79','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('8','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('1','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('61','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('236','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('227','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('81','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('239','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('102','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('208','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('231','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('115','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('63','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('210','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('13','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('55','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('46','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('112','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('59','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('30','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('68','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('213','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('35','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('48','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('96','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('33','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('100','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('225','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('22','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('12','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('24','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('93','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('233','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('114','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('16','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('6','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('83','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('26','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('34','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('15','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('62','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('87','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('90','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('108','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('4','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('212','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('238','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('226','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('17','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('40','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('86','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('28','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('57','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('106','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('74','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('11','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('43','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('19','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('32','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('56','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('18','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('41','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('97','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('39','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('229','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('49','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('230','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('44','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('51','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('66','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('220','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('45','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('235','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('5','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('29','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('91','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('75','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('54','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('217','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('31','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('105','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('207','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('85','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('234','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('14','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('20','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('224','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('20','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('112','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('25','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('239','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('46','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('43','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('87','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('97','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('223','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('52','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('235','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('54','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('113','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('110','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('109','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('213','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('95','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('41','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('30','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('220','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('75','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('78','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('233','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('22','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('207','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('202','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('58','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('79','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('50','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('222','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('83','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('229','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('29','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('12','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('21','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('68','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('82','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('230','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('236','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('23','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('53','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('85','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('104','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('100','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('40','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('107','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('208','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('226','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('209','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('99','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('13','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('80','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('103','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('74','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('105','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('225','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('77','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('56','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('60','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('219','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('59','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('86','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('94','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('220','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('105','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('78','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('74','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('235','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('17','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('22','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('230','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('90','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('81','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('80','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('61','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('25','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('82','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('79','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('91','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('75','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('62','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('83','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('107','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('76','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('28','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('229','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('84','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('101','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('232','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('77','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('60','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('87','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('109','4');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('3','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('41','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('30','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('90','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('14','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('227','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('66','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('16','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('21','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('4','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('39','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('61','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('57','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('232','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('84','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('217','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('17','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('62','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('6','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('14','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('63','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('57','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('64','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('49','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('91','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('3','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('11','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('96','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('19','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('115','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('239','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('2','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('1','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('26','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('102','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('228','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('24','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('238','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('90','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('65','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('5','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('27','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('24','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('111','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('51','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('11','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('114','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('28','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('106','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('67','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('8','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('81','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('241','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('1','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('86','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('234','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('15','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('76','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('42','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('108','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('228','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('45','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('98','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('2','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('44','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('210','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('4','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('212','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('55','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('9','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('94','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('18','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('101','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('231','3');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('58','1');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('87','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('28','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('81','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('114','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('98','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('22','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('17','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('76','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('56','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('112','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('25','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('110','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('57','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('241','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('77','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('82','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('75','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('79','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('80','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('78','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('84','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('86','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('49','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('74','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('102','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('106','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('55','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('104','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('83','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('108','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('90','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('19','5');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('84','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('12','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('236','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('234','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('28','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('63','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('86','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('42','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('40','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('82','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('39','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('50','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('56','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('25','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('225','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('5','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('54','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('229','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('76','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('65','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('210','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('20','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('45','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('213','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('8','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('222','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('115','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('81','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('67','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('64','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('99','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('26','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('230','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('96','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('223','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('77','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('232','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('202','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('61','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('87','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('22','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('231','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('44','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('58','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('94','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('91','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('27','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('43','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('220','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('209','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('19','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('9','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('68','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('13','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('100','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('79','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('59','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('55','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('6','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('66','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('46','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('95','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('212','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('17','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('53','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('74','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('49','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('207','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('75','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('23','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('60','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('52','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('224','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('16','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('97','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('29','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('62','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('83','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('51','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('15','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('80','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('78','2');
Insert into MUIPROMO.MENU_ROLES (ID_MENU,ID_ROLES) values ('18','2');

DELETE FROM MUIPROMO.ACL;

DELETE FROM MUIPROMO.UI;

Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('45','Proximity->Negozi Promo ',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('21','Pianificazione ->Articoli Fittizzi',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('22','Pianificazione ->Differenziazione Zone',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('23','Pianificazione ->Proiezioni',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('24','Reporting -> Copia in Pianificazione',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('1','Timone ->Associazione Promo',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('2','Piano Annuale -> Modifica Promozione',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('3','Piano Annuale -> Crea Sezioni Tematiche',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('4','Piano Annuale -> Foto',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('5','Piano Annuale -> Gabbia',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('6','Piano Annuale -> Macrospazi',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('7','Piano Annuale -> Spazi Condivisi',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('8','Timone -> Associazione Promo',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('9','Timone -> Target Reparto Promo',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('10','Timone -> Target Reparto Data',null);
Insert into MUIPROMO.UI (ID,NAME,DESCRIPTION) values ('11','Timone -> Spazi Campagna Reparto',null);

Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('209','2','1','BUTTON','tim_ap_aggiorna_associazioni','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('219','2','45','BUTTON','refresh_assort_negozi_promo_prox','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('229','2','23','BUTTON','pnf_pro_abilita','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('239','2','23','BUTTON','pnf_pro_clear','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('249','2','23','BUTTON','pnf_pro_copia','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('259','2','23','BUTTON','pnf_pro_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('64','5','11','BUTTON','tim_scr_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('65','6','11','BUTTON','tim_scr_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('199','3','1','BUTTON','tim_ap_aggiorna_associazioni','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('269','2','23','BUTTON','pnf_pro_sposta','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('279','2','21','BUTTON','pnf_aft_esegui','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('289','2','21','BUTTON','	pnf_aft_crea','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('299','2','24','BUTTON','rep_cpi_copest','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('309','3','24','BUTTON','rep_cpi_copest','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('319','2','24','BUTTON','rep_cpi_copia','0','0','0');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('329','3','24','BUTTON','rep_cpi_copia','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('200','3','21','BUTTON','pnf_aft_crea','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('201','3','21','BUTTON','pnf_aft_esegui','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('202','3','22','BUTTON','pnf_dif_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('203','3','23','BUTTON','pnf_pro_abilita','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('204','3','23','BUTTON','pnf_pro_clear','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('205','3','23','BUTTON','pnf_pro_copia','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('206','3','23','BUTTON','pnf_pro_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('207','3','23','BUTTON','pnf_pro_sposta','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('66','6','21','BUTTON','pnf_aft_crea','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('67','4','21','BUTTON','pnf_aft_crea','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('72','5','21','BUTTON','pnf_aft_esegui','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('69','5','21','BUTTON','pnf_aft_crea','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('70','6','21','BUTTON','pnf_aft_esegui','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('71','4','21','BUTTON','pnf_aft_esegui','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('73','6','22','BUTTON','pnf_dif_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('74','4','22','BUTTON','pnf_dif_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('75','5','22','BUTTON','pnf_dif_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('76','6','23','BUTTON','pnf_pro_sposta','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('77','4','23','BUTTON','pnf_pro_sposta','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('78','5','23','BUTTON','pnf_pro_sposta','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('79','6','23','BUTTON','pnf_pro_copia','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('80','4','23','BUTTON','pnf_pro_copia','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('81','5','23','BUTTON','pnf_pro_copia','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('82','6','23','BUTTON','pnf_pro_clear','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('83','4','23','BUTTON','pnf_pro_clear','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('84','5','23','BUTTON','pnf_pro_clear','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('85','6','23','BUTTON','	pnf_pro_abilita','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('86','4','23','BUTTON','	pnf_pro_abilita','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('87','5','23','BUTTON','pnf_pro_abilita','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('88','6','23','BUTTON','pnf_pro_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('89','4','23','BUTTON','pnf_pro_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('90','5','23','BUTTON','pnf_pro_inizializza','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('91','6','24','BUTTON','rep_cpi_copia','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('92','4','24','BUTTON','rep_cpi_copia','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('93','5','24','BUTTON','rep_cpi_copia','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('94','6','24','BUTTON','rep_cpi_copest','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('95','4','24','BUTTON','rep_cpi_copest','1','0','1');
Insert into MUIPROMO.ACL (ID,ROLE_ID,UI_ID,COMPONENT_CLASS,COMPONENT,VISIBLE,EDITABLE,ENABLED) values ('96','5','24','BUTTON','rep_cpi_copest','1','0','1');
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

Insert into MUIPROMO.APPLICATION_PROPERTIES (ID_APPLICATION_PROPERTIES,AP_KEY,AP_VALUE) values ('5','TM1_OPERATION_TIMEOUT','120');
Insert into MUIPROMO.APPLICATION_PROPERTIES (ID_APPLICATION_PROPERTIES,AP_KEY,AP_VALUE) values ('6','SOCKET_TIMEOUT','5');
Insert into MUIPROMO.APPLICATION_PROPERTIES (ID_APPLICATION_PROPERTIES,AP_KEY,AP_VALUE) values ('7','CONNECTION_MANAGER_TIMEOUT','10');
Insert into MUIPROMO.APPLICATION_PROPERTIES (ID_APPLICATION_PROPERTIES,AP_KEY,AP_VALUE) values ('8','GRID_HEIGHT','65');

DELETE FROM MUIPROMO.CONFIGURATION;
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('95','/Piano_Annuale/Spazi/Spazi_Condivisi',' {
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "spazio":["descrizione","compratore","macroSpazio", "reparto","macroSpazioDescrizione"],
    "compratore":["descrizione" , "repartodcodesc" , "categorymanager" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('102','/Reporting/Timone_Reparto','{
  "rep_promozione" : [ "anno", "canale", "tipo", "descrizione", "canaleanno" ],
  "rep_misuraTimone" : [ "descrizione" ],
  "rep_scenario" : [ "descrizione" ],
  "rep_reparto" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('105','/Timone/Foto_Speciali/Riepilogo',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('106','/Timone/Foto_Speciali/Target',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('107','/Timone/Spazi_Campagna/Target_Categoria','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager","repartodcodesc" , "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "repartodesc", "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('108','/Timone/Spazi_Campagna/Target_Reparto',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('109','/Timone/Target_Categoria/Data','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager","repartodcodesc" , "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "repartodesc", "descrizione" ],
  "scenario" : [ "descrizione" ],
  "misuraTimone" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('110','/Timone/Target_Categoria/Promo','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager","repartodcodesc" , "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "repartodesc", "descrizione" ],
  "scenario" : [ "descrizione" ],
  "misuraTimone" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('111','/Timone/Target_Reparto/Data',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('112','/Timone/Target_Reparto/Promo','{
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('113','/Timone/Associazione_Promo','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "promozioneriferimento":["anno","rif_canale","semestre","gruppo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('87','/Piano_Annuale/Visualizza_Piano','{
    "promozione":["anno", "canale","tipo","descrizione","riferimento","semestre","proximity"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('58','/welcome',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","proximity"],
    "rep_promozione":["anno","canale","riferimento","semestre", "descrizione","canaleanno"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('89','/Piano_Annuale/Sezioni_Tematiche/Sezioni_Tematiche','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "pubblicita" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('91','/Piano_Annuale/Spazi/Macrospazi_Listino_Promo',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "macrospazio":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('78','/Piano_Annuale/Crea_Promozione','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('79','/Piano_Annuale/Foto','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "descrizione", "repartodcodesc", "categorymanager" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('80','/Piano_Annuale/Gabbia','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('81','/Piano_Annuale/Gestione_Contributi','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "tipoPromozione" : [ "descrizione", "gruppo" ],
  "contratto" : [ "descrizione" ],
  "prestazione" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('83','/Piano_Annuale/Meccaniche','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('84','/Piano_Annuale/Modifica_Promozione','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('85','/Piano_Annuale/Negozi_Promo','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "negozio" : [ "descrizione", "zonaPromo" ],
  "canale" : [ "descrizione" ],
  "misuraCanale" : [ "descrizione" ],
  "sezioneTematica" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('88','/Piano_Annuale/Zone_Promo',' {
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "zonaPromo":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('67','/Fatturazione/Associazioni_Articoli',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"fornitore":["descrizione"],
	"compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "spazioProgressivo":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('70','/Pianificazione/Articoli_Fittizi',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore": ["descrizione"],
	"articoloFittizio":["descrizione","compratore"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('71','/Pianificazione/Differenziazione_Zone',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('74','/Pianificazione/Selezione_Articoli_Contributi','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager", "repartodcodesc", "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "descrizione" ],
  "fornitore" : [ "descrizione" ],
  "articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('75','/Pianificazione/Selezione_Famiglie_Contributi','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager", "repartodcodesc", "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "descrizione" ],
  "fornitore" : [ "descrizione" ],
  "articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('96','/Reporting/Copia_in_Pianificazione','{
  "rep_promozione" : [ "anno", "canale", "tipo", "descrizione", "canaleanno" ],
  "rep_compratore" : [ "descrizione" ],
  "rep_scenario" : [ "descrizione" ],
  "rep_articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc" ],
  "rep_fornitore" : [ "descrizione" ],
  "rep_sezioneTematica" : [ "descrizione" ],
  "rep_meccanicaSemplice" : [ "descrizione" ],
  "rep_avolantino" : [ "descrizione" ],
  "rep_MisuraReportingArticolo" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('97','/Reporting/Dettaglio_Campagna','{
  "rep_promozione" : [ "anno", "canale", "tipo", "descrizione", "canaleanno" ],
  "rep_compratore" : [ "descrizione" ],
  "rep_scenario" : [ "descrizione" ],
  "rep_articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc" ],
  "rep_fornitore" : [ "descrizione" ],
  "rep_sezioneTematica" : [ "descrizione" ],
  "rep_avolantino" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('114','/Reporting/Analisi_Budget-Venduto/Categoria','{
  "rep_promozione" : [ "anno", "canale", "tipo", "descrizione", "canaleanno" ],
  "rep_compratore" : [ "descrizione" ],
  "rep_categoria" : [ "descrizione" ],
  "rep_misuraTimone" : [ "descrizione" ],
  "rep_scenario" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('115','/Reporting/Analisi_Budget-Venduto/Categoria',TO_CLOB('{
  "connection" : "reporting",
  "show-version" : true,
  "configurations" : [ {
    "name" : "gc_AnalisiBudgetVenduto_Categoria",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 2000000,
    "title" : "Analisi Budget-Venduto - Categoria",
    "compulsoryFilters" : [ "REP - Promozione" ],
    "compulsoryFiltersMessage" : "Per utilizzare questo form è necessario applicare almeno un filtro sulla dimensione Promozione",
    "height" : 60,
    "css" : ')
    || TO_CLOB('".headerCons{background-color: #defefe!important;}",
    "REP - Misura Timone" : "{[REP - Misura Timone].[MUI_SUB_Analisi_Budget_Venduto_Categoria]}",
    "REP - Misura Timone - OLD" : "{[REP - Misura Timone].[N_ART_PROMO]},{[REP - Misura Timone].[TOT_FOTO]},{[REP - Misura Timone].[N_FOTO]},{[REP - Misura Timone].[N_FOTO_SCAFFALE]},{[REP - Misura Timone].[N_FOTO_SPEC]},{[REP - Misura Timone].[N_FOTO_SCAFFALE_SPEC]},{[REP - Misura Timone].[N_FOTO_ULT]},{[REP - Misura Timone].[SPZ_CAMP]},{FILTER')
    || TO_CLOB('( {TM1SUBSETALL( [REP - Misura Timone] )}, [REP - Misura Timone].[Tipo] = ''Spazi'')}   ",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Compratore" : "{[REP - Compratore].[Compratori]}",
          "REP - Categoria" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)} , ASC)}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Promozione" : "{TM1SORT(  {TM1')
    || TO_CLOB('FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          "REP - Scenario" : "{[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[TGT_ACQ],[REP - Scenario].[BDG],[REP - Scenario].[CONS] }",
          "REP - Misura Timone" : "{ [REP - Misura Timone].[MUI_SUB_Analisi_Budget_Venduto_Categoria] }"
        }
      },
      "FROM" : "[Timone Reporting]",
      "WHERE" : {
        "REP - Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attr')
    || TO_CLOB('ibutes/MUI_DescrizioneData", "Attributes/MUI_Direzione", "Attributes/MUI_Reparto", "Attributes/Descrizione", "Attributes/MUI_TOTS", "Attributes/MUI_TOT", "Attributes/MUI_DIR", "Attributes/MUI_REP", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildre')
    || TO_CLOB('n" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        },
        "BDG" : {
          "headerClass" : "hea')
    || TO_CLOB('derBdg",
          "openByDefault" : true
        },
        "CONS" : {
          "headerClass" : "headerCons",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : false,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "D_VEND/RIF')
    || TO_CLOB('_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "REP_minus_Categoria.Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Totale Compratore",
      "field" : "REP_minus_Compratore.MUI_TOTS",
')
    || TO_CLOB('
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Direzione",
      "field" : "REP_minus_Categoria.MUI_Direzione",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "REP_minus_Categoria.MUI_Reparto",
      "width" : 100,
      "hide" : false,')
    || TO_CLOB('
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Categoria",
      "field" : "REP_minus_Categoria.Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "REP_minus_Compratore.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editab')
    || TO_CLOB('le" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "preSelections" : [ {
      "dimension" : "REP - Compratore",
      "dimCode" : "rep_compratore",
      "dimColumnName" : "REP - Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_AnalisiBudgetVenduto_Categoria" ]')
    || TO_CLOB('
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('116','/Reporting/Analisi_Budget-Venduto/Articolo',TO_CLOB('{
  "rep_promozione" : [ "anno", "canale", "tipo", "descrizione", "canaleanno" ],
  "rep_compratore" : [ "descrizione" ],
  "rep_articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc" ],
  "rep_fornitore" : [ "descrizione" ],
  "rep_zonaPromo" : [ "descrizione" ],
  "rep_sezioneTematica" : [ "descrizione" ],
  "rep_meccanicaSemplice" : [ "descrizione" ],
  "rep_avolantino" : [ "descrizione" ],
  "rep_MisuraReportingArticoloZona" : [ "descrizione" ],
  "rep_scenario" : [ "descrizione" ]')
    || TO_CLOB('
}'),'FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('117','/Reporting/Analisi_Budget-Venduto/Articolo',TO_CLOB('{
  "connection" : "reporting",
  "show-version" : true,
  "configurations" : [ {
    "name" : "gc_AnalisiBudgetVenduto_Articolo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 2000000,
    "compulsoryFilters" : [ "REP - Promozione" ],
    "compulsoryFiltersMessage" : "Per utilizzare questo form è necessario applicare almeno un filtro sulla dimensione Promozione",
    "title" : "Analisi Budget-Venduto - Articolo",
    "height" : 60,
    "css" : ".')
    || TO_CLOB('headerCons{background-color: #defefe!important;}",
    "REP - Fornitore" : "{[REP - Fornitore].[Fornitori]}",
    "REP - Compratore" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
    "REP - Zona Promo" : "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
    "REP - Sezione Tematica" : "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
    "REP - Meccanica Semplice" : "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
    "REP - AVolantino" : "{')
    || TO_CLOB('[REP - AVolantino].[AVolantino -BDGVend]}",
    "REP - Articolo" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Fornitore" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}",
          "REP - Compratore" : "[REP - Compratore].[Compratori]",
          "REP - Zona Promo" : "{TM1SUBSETALL( [REP - Zona Promo] )}",
          ')
    || TO_CLOB('"REP - Sezione Tematica" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
          "REP - Meccanica Semplice" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
          "REP - AVolantino" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}",
          "REP - Articolo" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
        }
      },
      ')
    || TO_CLOB('"COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Promozione" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          "REP - Scenario" : "{[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[BDG],[REP - Scenario].[CONS]}",
          "REP - Misura Reporting Articolo Zona" : "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -BDGVend]}"
        }
      },
      "FROM" : "[Reporting Articolo Zona]",
   ')
    || TO_CLOB('   "WHERE" : {
        "REP - Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/DescrizioneArticolo", "Attributes/MUI_Category", "Attributes/MUI_Reparto", "Attributes/MUI_TOT", "Attributes/RepartoDesc", "Attributes/CategoriaDesc", "Attributes/GRMDesc", "Attributes/MUI_SubGrm", "Attributes/MUI_DescrizioneData", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
 ')
    || TO_CLOB('   "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "op')
    || TO_CLOB('enByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        },
        "BDG" : {
          "headerClass" : "headerBdg",
          "openByDefault" : true
        },
        "CONS" : {
          "headerClass" : "headerCons",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
 ')
    || TO_CLOB('       "columnGroupShow" : "always",
        "editable" : false,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "MARGINE_LORDO_%_P" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
        },
        "DELTA_%_SC" : {
          "type" : [ "TM1DataColumnIntegerPercentage", "numericColumn" ]
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : ')
    || TO_CLOB('true
      },
      "field" : "REP_minus_Articolo.DescrizioneArticolo",
      "headerName" : "Articolo",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Articolo",
      "field" : "REP_minus_Articolo.DescrizioneArticolo",
      "width" : 300,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "fie')
    || TO_CLOB('ld" : "REP_minus_Articolo.MUI_Reparto",
      "width" : 80,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Categoria",
      "field" : "REP_minus_Articolo.CategoriaDesc",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Grm",
      "field" : "REP_minus_Articolo.GRMDesc",
      "width')
    || TO_CLOB('" : 130,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Sub Grm",
      "field" : "REP_minus_Articolo.MUI_SubGrm",
      "width" : 130,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitori",
      "field" : "REP_minus_Fornitore.Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGro')
    || TO_CLOB('up" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Category",
      "field" : "REP_minus_Compratore.MUI_Category",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "REP_minus_Compratore.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
 ')
    || TO_CLOB('     "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Zona Promo",
      "field" : "REP_minus_ZonaPromo.Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Sezione Tematica",
      "field" : "REP_minus_SezioneTematica.Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]')
    || TO_CLOB('
    }, {
      "headerName" : "Meccanica",
      "field" : "REP_minus_MeccanicaSemplice.Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "A Volantino",
      "field" : "REP_minus_AVolantino.Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowSuppressionEn')
    || TO_CLOB('abled" : false,
    "colSuppressionEnabled" : false,
    "preSelections" : [ {
      "dimension" : "REP - Compratore",
      "dimCode" : "rep_compratore",
      "dimColumnName" : "REP - Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_AnalisiBudgetVenduto_Articolo" ]
    }, {
      "dimension" : "REP - Zona Promo",
      "dimCode" : "rep_zon')
    || TO_CLOB('aPromo",
      "dimColumnName" : "REP - Zona Promo",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_AnalisiBudgetVenduto_Articolo" ]
    } ]
  } ]
}'),'GRID');
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
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('73','/Pianificazione/Proiezioni','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager", "repartodcodesc", "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "descrizione" ],
  "fornitore" : [ "descrizione" ],
  "articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc" ]
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('9','/Fatturazione/Associazione_Articoli_Promozione',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_AssociazioneArticoliPromo_bottom",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Associazione Articoli Promozione",
    "maxCells" : 100000,
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_In')
    || TO_CLOB('izio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
          "Fornitore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
          "Rata" : "{ [Rata].[(I) Fatturazione] }",
          "ArticoloNoSec" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ArticoloNoSec] )}, 0)}, DESC)}}",
  ')
    || TO_CLOB('        "Spazio Progressivo" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio Progressivo] )}, 0)}, DESC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Fatturazione" : "{[Misura Fatturazione].[ASS_RATA],[Misura Fatturazione].[OK]}"
        }
      },
      "FROM" : "[Fatturazione Articolo]",
      "WHERE" : {
        "Scenario" : "[BDG]",
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      ')
    || TO_CLOB('"Members" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Canale", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/Descrizione", "Attributes/Riferimento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_')
    || TO_CLOB('MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 90,
        "hide" : false,
        "rowGroup" : false,')
    || TO_CLOB('
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText" ]
      },
      "childrenCustomTypes" : {
        "OK" : {
          "width" : 100,
          "hide" : true
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "ArticoloNoSec.Descrizione",
      "headerName" : "Articolo",
      "width" : 600,
      "pinned" ')
    || TO_CLOB(': "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Promozione",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Elem')
    || TO_CLOB('ent" ]
    }, {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI')
    || TO_CLOB('_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
   ')
    || TO_CLOB('   "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitore",
      "field" : "Fornitore.Descrizione",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Rata",
      "field" : "Rata.Descrizione",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Elem')
    || TO_CLOB('ent" ]
    }, {
      "headerName" : "Articolo",
      "field" : "ArticoloNoSec.Descrizione",
      "width" : 300,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Spazio",
      "field" : "SpazioProgressivo.Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('10','/Fatturazione/Associazioni_Articoli',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_AssociazioneArticoliPromo_bottom",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Associazione Articoli",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
          "Fornitore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [')
    || TO_CLOB('Fornitore] )}, 0)}, DESC)}}",
          "Rata" : "{ [Rata].[(I) Fatturazione] }",
          "ArticoloNoSec" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ArticoloNoSec] )}, 0)}, DESC)}}",
          "Spazio Progressivo" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio Progressivo] )}, 0)}, DESC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETA')
    || TO_CLOB('LL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Misura Fatturazione" : "{[Misura Fatturazione].[ASS_RATA],[Misura Fatturazione].[OK]}"
        }
      },
      "FROM" : "[Fatturazione Articolo]",
      "WHERE" : {
        "Scenario" : "[BDG]",
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI')
    || TO_CLOB('_DescrizioneData", "Attributes/Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClas')
    || TO_CLOB('s" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 300,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
     ')
    || TO_CLOB('   "type" : [ "TM1DataColumnText" ]
      },
      "childrenCustomTypes" : {
        "OK" : {
          "width" : 100,
          "hide" : true
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "ArticoloNoSec.Descrizione",
      "headerName" : "Articolo",
      "width" : 500,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Comp')
    || TO_CLOB('ratore",
      "field" : "Compratore.Descrizione",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitore",
      "field" : "Fornitore.Descrizione",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Rata",
      "field" : "Rata.Descrizione",
      "width" : 200,
 ')
    || TO_CLOB('     "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Articolo",
      "field" : "ArticoloNoSec.Descrizione",
      "width" : 300,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Spazio",
      "field" : "SpazioProgressivo.Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "e')
    || TO_CLOB('ditable" : false,
      "type" : [ "TM1Element" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('13','/Pianificazione/Articoli_Fittizi',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_articoliFittizi",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "title" : "Articoli Fittizi",
    "height" : 58,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Articolo Fittizio" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo Fittizio] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_E')
    || TO_CLOB('MPTY" : false,
        "DIMENSIONS" : {
          "Misura Articolo Fittizio" : "{[Misura Articolo Fittizio].[(I) Aggiornamento Articoli Fittizi]}"
        }
      },
      "FROM" : "[Articoli Fittizi Aggiornamento]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_TOT", "Attributes/Compratore", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "Dy')
    || TO_CLOB('namicColumnsSettings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : { },
      "childrendefaults" : {
        "cellClass" : "cellClass-left-text",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText", "numericColumn" ]
      },
      "childrenCustomTypes" : {
     ')
    || TO_CLOB('   "VALORE" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PEZZATURA" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "IVA" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "DataInizio" : {
          "cellClass" : "dateFormat",
          "type" : [ "TM1DataColumnDate" ]
       ')
    || TO_CLOB(' },
        "DataFine" : {
          "cellClass" : "dateFormat",
          "type" : [ "TM1DataColumnDate" ]
        },
        "PRZ_ATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
')
    || TO_CLOB('      "field" : "ArticoloFittizio.Descrizione",
      "headerName" : "Articolo Fittizio",
      "cellClass" : "cellClass-left-text",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Totale Fittizio",
      "field" : "ArticoloFittizio.MUI_TOT",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Comp')
    || TO_CLOB('ratore",
      "field" : "ArticoloFittizio.Compratore",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPia')
    || TO_CLOB('nificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_articoliFittizi" ]
    } ],
    "styleRules" : { },
    "actions" : [ {
      "componentId" : "pnf_aft_crea",
      "componentLabel" : "Crea Articolo Fittizio in Promo",
      "process" : "MUI_DUMMY_DIM.Articolo Fittizio.Aggiornamento New iN Promo",
      "timeout" : -1,
      "refresh" : [ "gc_articoliFittizi" ],
      "params" : [ {
        "dimension" : "Compratore",
        "attribute" : "Descrizione",
       ')
    || TO_CLOB(' "paramName" : "inCompratore",
        "label" : "Compratore",
        "hasPicklist" : true
      } ]
    }, {
      "componentId" : "pnf_aft_esegui",
      "componentLabel" : "Esegui Azione Su Articoli Fittizzi",
      "process" : "MUI_DUMMY_CUB.Articolo Fittizio.SpostaSuArtEff_new",
      "timeout" : -1,
      "refresh" : [ "gc_articoliFittizi" ],
      "params" : [ {
        "dimension" : "Compratore",
        "attribute" : "Descrizione",
        "paramName" : "inCompratore",
  ')
    || TO_CLOB('      "label" : "Compratore",
        "hasPicklist" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('14','/Pianificazione/Differenziazione_Zone',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_differenziazionePromo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "title" : "Pianificazione promozione per zona",
    "height" : 58,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.')
    || TO_CLOB('], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
          "Articolo" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}",
          "Zona Promo" : "{ EXCEPT({{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Zona Promo] )}, 0)}, ASC)}}, { [Zona Promo].[NA],[Zona Promo].[SOCIETA_1],[Zona Promo].[SO')
    || TO_CLOB('CIETA_2] })}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Promozione Pianificazione" : "{[Misura Promozione Pianificazione Zone].[MUI_PianificazioneZone]}"
        }
      },
      "FROM" : "[Promozione Pianificazione - Zone]",
      "WHERE" : {
        "Scenario" : "[BDG]",
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Descrizio')
    || TO_CLOB('ne", "Attributes/Fornitore", "Attributes/RepartoDesc", "Attributes/CategoriaDesc", "Attributes/GRMDesc", "Attributes/SubGrmDesc", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : { },
      "childrendefaults" : {
        "width" : 100')
    || TO_CLOB(',
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText" ]
      },
      "childrenCustomTypes" : {
        "PRZ_ATT_ANN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_MIN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
  ')
    || TO_CLOB('      "PRZ_MAX" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_ATT_USR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "%_SC" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "VAL_SC" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-t')
    || TO_CLOB('ext"
        },
        "PRZ_PROMO" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_AN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_USR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CONTR_%_IN_FATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "ce')
    || TO_CLOB('llClass" : "cellClass-right-text"
        },
        "CST_C_IVA" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_PROMO_C_IVA" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "N_PEZZI" : {
          "type" : [ "TM1DataColumnDecimal3" ],
          "cellClass" : "cellClass-right-text"
        },
        "COLLI" : {
          "type" : [ "TM1DataC')
    || TO_CLOB('olumnDecimal3" ],
          "cellClass" : "cellClass-right-text"
        },
        "TOT_dot_VEND" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "F_FATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "IVA" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cellClass" : "cellClass-right-text"
        },
        "RIFATT_BS" : {
  ')
    || TO_CLOB('        "type" : [ "TM1DataColumnText" ],
          "cellClass" : "cellClass-left-text"
        },
        "LIM_UTIL" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "ALTRO" : {
          "hide" : true
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "ZonaPromo.Descrizione",
      "headerName" : "Zona Promo",
      "cell')
    || TO_CLOB('Class" : "cellClass-left-text",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Promozione",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "width" : 100,
      "hide" : true,
      "r')
    || TO_CLOB('owGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitore",
      "field" : "Articolo.Fornitore",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Articolo",
      "field" : "Articolo.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Ele')
    || TO_CLOB('ment" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Articolo.RepartoDesc",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Categoria",
      "field" : "Articolo.CategoriaDesc",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Grm",
      "field" : "A')
    || TO_CLOB('rticolo.GRMDesc",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Sub Grm",
      "field" : "Articolo.SubGrmDesc",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promoz')
    || TO_CLOB('ione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_differenziazionePromo" ]
    }, {
      "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",')
    || TO_CLOB('
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_differenziazionePromo" ]
    } ],
    "styleRules" : { },
    "actions" : [ {
      "componentId" : "pnf_dif_inizializza",
      "componentLabel" : "Inizializza",
      "process" : "MUI_DUMMY_CUB.Promozione Pianificazione Zone (Inizializza)",
      "timeout" : -1,
      "refresh" : [ "gc_differenziazionePromo" ],
      "params" : [ {
        "dimension" : "Compratore",
        "attribute" : "Descrizione",
     ')
    || TO_CLOB('   "paramName" : "inCompratore",
        "label" : "Compratore",
        "hasPicklist" : false,
        "disabled" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('15','/Pianificazione/Meccaniche_Set',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_meccanicheSet_creazione",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "title" : "Creazione e modifica set",
    "height" : 58,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDES')
    || TO_CLOB('C)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
          "ID Set" : "{[ID Set].[(I) Lista set]}",
          "ID RaggrSet" : "{[ID RaggrSet].[(I) Configura set]}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Configurazione Promozione - Set" : "{[Misura Configura')
    || TO_CLOB('zione Promozione - Set].[(I) Definizione set]}"
        }
      },
      "FROM" : "[Configurazione Promozione - Set Parametri]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : ')
    || TO_CLOB('[ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "childrendefaults" : {
        "width" : 100,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText" ]
      },
      "headerCustomTypes" : { },
      "childrenCustomTypes" : {
        "VAL_SC" : {
          "type" : [ "TM1DataColumnNumber" ]
        },
        "LI')
    || TO_CLOB('M_UTIL" : {
          "type" : [ "TM1DataColumnInteger" ]
        },
        "NrOcc" : {
          "type" : [ "TM1DataColumnInteger" ]
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "IDRaggrSet.Descrizione",
      "headerName" : "ID Raggr Set",
      "cellClass" : "cellClass-left-text",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDef')
    || TO_CLOB('s" : [ {
      "headerName" : "Promozione",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "ID Set",
      "field" : ')
    || TO_CLOB('"IDSet.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "ID RaggrSet",
      "field" : "IDRaggrSet.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" ')
    || TO_CLOB(': "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_meccanicheSet_creazione", "gc_meccanicheSet_associazione" ]
    }, {
      "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizion')
    || TO_CLOB('e",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_meccanicheSet_creazione", "gc_meccanicheSet_associazione" ]
    } ],
    "styleRules" : { }
  }, {
    "name" : "gc_meccanicheSet_associazione",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "title" : "Associazione articoli a set",
    "height" : 58,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "D')
    || TO_CLOB('IMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "{{ EXCEPT( {TM1SUBSETALL( [Compratore] )}, { [Compratore].[NA], [Compratore].[S000] }) }}",
          "ID Set" : "{[ID Set].[(I) Lista set]}",
          "Articolo" : "{{TM1SORT( {TM1FILTERB')
    || TO_CLOB('YLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "ID RaggrSet" : "{[ID RaggrSet].[(I) Configura set articoli]}",
          "Misura Configurazione Promozione - Set" : "{[Misura Configurazione Promozione - Set].[(I) Set Articoli]}"
        }
      },
      "FROM" : "[Configurazione Promozione - Set]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {')
    || TO_CLOB('
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : { },
      "childrendefaults" : {
        "width" : 100,
        "hide" : false,
 ')
    || TO_CLOB('       "rowGroup" : false,
        "aggFunc" : "",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText" ]
      },
      "childrenCustomTypes" : {
        "DataInizio" : {
          "cellClass" : "dateFormat",
          "type" : [ "TM1DataColumnDate", "numericColumn" ],
          "aggFunc" : "",
          "columnGroupShow" : "always"
        },
        "DataFine" : {
          "cellClass" : "dateFormat",
          "type" : [ "TM1D')
    || TO_CLOB('ataColumnDate", "numericColumn" ],
          "aggFunc" : "",
          "columnGroupShow" : "always"
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Articolo.Descrizione",
      "headerName" : "Articolo",
      "cellClass" : "cellClass-left-text",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Promozione"')
    || TO_CLOB(',
      "field" : "Promozione.MUI_Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "ID Set",
      "field" : "IDSet.Descrizione",
      "width" : 100,
')
    || TO_CLOB('
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Articolo",
      "field" : "Articolo.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizion')
    || TO_CLOB('e",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_meccanicheSet_creazione", "gc_meccanicheSet_associazione" ]
    }, {
      "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
  ')
    || TO_CLOB('    "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_meccanicheSet_creazione", "gc_meccanicheSet_associazione" ]
    } ],
    "styleRules" : { }
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('16','/Pianificazione/Proiezioni',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_proiezioni_1",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "title" : "Target per Categoria",
    "height" : 29,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozio')
    || TO_CLOB('ne].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')} ",
          "Compratore" : "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]}) }",
          "Categoria" : "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Scenario" : "{[Scen')
    || TO_CLOB('ario].[(I) Scenario Timone Pianificazione]}",
          "Misura Timone." : "{[Misura Timone.].[MUI_Proiezione_Timone_Categoria],[Misura Timone.].[MUI_AVG]}"
        }
      },
      "FROM" : "[Timone Categoria II]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_RepartoDesc", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consoli')
    || TO_CLOB('dated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.Descrizione",
      "headerName" : "Categoria",
      "cellClass" : "cellClass-left-text",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione" ],
      "headerdefaults" : {
     ')
    || TO_CLOB('   "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        },
        "BDG" : {
          "hea')
    || TO_CLOB('derClass" : "headerBdg",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 100,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : false,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "N_FOTO_SPEC" : {
          "type" : [ "TM1DataColumnInteger" ],
          "hide" : "false"
       ')
    || TO_CLOB(' },
        "N_FOTO_ULT" : {
          "aggFunc" : ""
        },
        "N_FOTO_SCAFFALE" : {
          "hide" : "false"
        },
        "N_FOTO_SCAFFALE_SPEC" : {
          "hide" : "false"
        },
        "CONTR" : {
          "hide" : "false"
        },
        "EXTRA_CONTR" : {
          "hide" : "false"
        },
        "MUI_AVG" : {
          "hide" : "true"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "c')
    || TO_CLOB('ellClass" : "cellClass-right-text",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "D_VEND/RIF_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ],
          "columnGroupShow" : "always",
          "aggFunc" : "customAvg",
          "aggFuncParam" : "$MUI_AVG"
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "Promozione",
      "field" : "Promozione.Descrizione",
      "width" : 70,')
    || TO_CLOB('
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Categoria.MUI_RepartoDesc",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "edita')
    || TO_CLOB('ble" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_proiezioni_1", "gc_proiezioni_2", "gc_proiezioni_3" ]
    }, {
    ')
    || TO_CLOB('  "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_proiezioni_1", "gc_proiezioni_2", "gc_proiezioni_3" ]
    } ],
    "styleRules" : { },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "true",
      "nodeGroupFalse" : "true"
    }
  }, {
    "name" : "')
    || TO_CLOB('gc_proiezioni_2",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "title" : "Proiezioni",
    "height" : 29,
    "Misura Promozione Pianificazione 2" : "{([Misura Promozione Pianificazione].[Configurazione Subset Pianificazione 1])}",
    "Misura Promozione Pianificazione" : "({FILTER(TM1SUBSETALL([Misura Promozione Pianificazione]),[}ElementAttributes_Misura Promozione Pianificazione].([}ElementAttributes_Misura Promozione Pianificazione].[O')
    || TO_CLOB('rdinamento])>0 )})",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')} ",
          "Compratore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
          "Articolo')
    || TO_CLOB('" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Promozione Pianificazione" : "{([Misura Promozione Pianificazione].[MUI_Proiezioni])}"
        }
      },
      "FROM" : "[Promozione Pianificazione]",
      "WHERE" : {
        "Scenario" : "[BDG]",
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attr')
    || TO_CLOB('ibutes/Attivo", "Attributes/MUI_Descrizione", "Attributes/Riferimento", "Attributes/Descrizione", "Attributes/DescrizioneCODICE", "Attributes/Fornitore", "Attributes/RepartoDesc", "Attributes/CategoriaDesc", "Attributes/GRMDesc", "Attributes/SubGrmDesc", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
  ')
    || TO_CLOB('      "marryChildren" : true
      },
      "childrendefaults" : {
        "width" : 100,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText" ],
        "cellClass" : "cellClass-left-text"
      },
      "childrenCustomTypes" : {
        "PRZ_ATT_ANN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
       ')
    || TO_CLOB(' },
        "PRZ_MIN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_MAX" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_ATT_USR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "VAL_SC" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellCla')
    || TO_CLOB('ss-right-text"
        },
        "PRZ_PROMO" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_AN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_USR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CONTR__perc__IN_FATT" : {
          "type" : [ "TM1DataColumnPercentage')
    || TO_CLOB('" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_C_IVA" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_PROMO_C_IVA" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "N_PEZZI" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "COLLI" : {
          "t')
    || TO_CLOB('ype" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "TOT.VEND" : {
          "hide" : true
        },
        "F_FATT" : {
          "hide" : true,
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "IVA" : {
          "type" : [ "TM1DataColumnIntegerPercentage" ],
          "cellClass" : "cellClass-right-text"
        },
        "RIFATT_BS" : {
          "cellClass" : "')
    || TO_CLOB('cellClass-right-text"
        },
        "LIM_UTIL" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "%_SC" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "CONTR_%_IN_FATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PEZZ" : {
          "type" : [ "TM1DataColumnInteger" ],
')
    || TO_CLOB('
          "cellClass" : "cellClass-right-text"
        },
        "VEND_PROMO_S_IVA" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_PROMO" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "ML_I_UNI" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "ML" : {
          "type" ')
    || TO_CLOB(': [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "%_ML" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cellClass" : "cellClass-right-text"
        },
        "CONTR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "EXTRA_CONTR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "NumeroNe')
    || TO_CLOB('gozi" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "PUNTI" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "ESP_PZ" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "ALTRO" : {
          "hide" : true
        },
        "MAX_UTIL" : {
          "type" : [ "TM1DataColumnInteg')
    || TO_CLOB('er" ],
          "cellClass" : "cellClass-right-text"
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Articolo.Descrizione",
      "headerName" : "Articolo",
      "cellClass" : "cellClass-left-text",
      "width" : 400,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
')
    || TO_CLOB('      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Promozione",
      "field" : "Promozione.MUI_Descrizione",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Articolo(Codice)",
     ')
    || TO_CLOB(' "field" : "Articolo.DescrizioneCODICE",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Attivo",
      "field" : "Articolo.Attivo",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" ')
    || TO_CLOB(': "Compratore",
      "field" : "Compratore.Descrizione",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitore",
      "field" : "Articolo.Fornitore",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }')
    || TO_CLOB(', {
      "headerName" : "Reparto",
      "field" : "Articolo.RepartoDesc",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Categoria",
      "field" : "Articolo.CategoriaDesc",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" :')
    || TO_CLOB(' [ "TM1Element" ]
    }, {
      "headerName" : "Grm",
      "field" : "Articolo.GRMDesc",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Sub Grm",
      "field" : "Articolo.SubGrmDesc",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
   ')
    || TO_CLOB('   "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_proiezioni_1", "gc_proiezioni_2", "gc_proiezioni_3" ]
    }, {
      "dimension" : "C')
    || TO_CLOB('ompratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_proiezioni_1", "gc_proiezioni_2", "gc_proiezioni_3" ]
    } ],
    "styleRules" : { }
  }, {
    "name" : "gc_proiezioni_3",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "ti')
    || TO_CLOB('tle" : "Target Foto Speciali",
    "height" : 29,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')} ",
          "Compratore" : "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore]')
    || TO_CLOB(' )}, 0)}, ASC)}} , {[Compratore].[S000]}) }",
          "Categoria" : "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Scenario" : "{[Scenario].[(II) TCS I]}",
          "Sezione Tematica" : "{{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, { [Sezione Tematica].[ST_0000] }) }, { [Se')
    || TO_CLOB('zione Tematica].[ST_001] }) }, { [Sezione Tematica].[ST_042] }) }}",
          "Misura Timone." : "{[Misura Timone.].[(II)Timone Categoria Sez]}"
        }
      },
      "FROM" : "[Timone Categoria Sezione I]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/RepartoDesc", "Attributes/MUI_Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", ')
    || TO_CLOB('"HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.Descrizione",
      "headerName" : "Categoria",
      "cellClass" : "cellClass-left-text",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione", "Descrizione" ],
      "headerdefaults" : {')
    || TO_CLOB('
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        },
        "BDG" : {
       ')
    || TO_CLOB('   "headerClass" : "headerBdg",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : false,
        "type" : [ "TM1DataColumnInteger" ],
        "cellClass" : "cellClass-right-text"
      },
      "childrenCustomTypes" : { }
    },
    "columnDefs" : [ {
      "headerName" : "Promozione",
   ')
    || TO_CLOB('   "field" : "Promozione.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Categoria.RepartoDesc",
      "width" : 70,
     ')
    || TO_CLOB(' "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "styleRules" : { },
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refres')
    || TO_CLOB('h" : [ "gc_proiezioni_1", "gc_proiezioni_2", "gc_proiezioni_3" ]
    }, {
      "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_proiezioni_1", "gc_proiezioni_2", "gc_proiezioni_3" ]
    } ],
    "actions" : [ {
      "componentId" : "pnf_pro_sposta",
      ')
    || TO_CLOB('"componentLabel" : "Sposta Articoli",
      "process" : "MUI_DUMMY_COPY.Promozione.Articolo+del",
      "timeout" : -1,
      "refresh" : [ "gc_proiezioni_1", "gc_proiezioni_2", "gc_proiezioni_3" ],
      "params" : [ {
        "dimension" : "Compratore",
        "attribute" : "Descrizione",
        "paramName" : "inCompratore",
        "label" : "Compratore",
        "hasPicklist" : false,
        "disabled" : true
      }, {
        "dimension" : "Promozione",
        "attribute" ')
    || TO_CLOB(': "MUI_Descrizione",
        "paramName" : "toPromo",
        "label" : "Promozione di destinazione",
        "hasPicklist" : true
      } ]
    }, {
      "componentId" : "pnf_pro_copia",
      "componentLabel" : "Copia Articoli",
      "process" : "MUI_DUMMY_COPY.Promozione.Articolo",
      "timeout" : -1,
      "refresh" : [ "gc_proiezioni_2" ],
      "params" : [ {
        "dimension" : "Compratore",
        "attribute" : "Descrizione",
        "paramName" : "inCompratore",
  ')
    || TO_CLOB('      "label" : "Compratore",
        "hasPicklist" : false,
        "disabled" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "toPromo",
        "label" : "Promozione di destinazione",
        "hasPicklist" : true
      } ]
    }, {
      "componentId" : "pnf_pro_clear",
      "componentLabel" : "Clear",
      "process" : "MUI_DUMMY_CUB.Promozione Pianificazione Pulizia Articoli",
      "timeout" : -1,
      "r')
    || TO_CLOB('efresh" : [ "gc_proiezioni_2" ],
      "params" : [ {
        "dimension" : "Compratore",
        "attribute" : "Descrizione",
        "paramName" : "pCompratore",
        "label" : "Compratore",
        "hasPicklist" : false,
        "disabled" : true
      } ]
    }, {
      "componentId" : "pnf_pro_abilita",
      "componentLabel" : "Abilita Famiglia",
      "process" : "MUI_DUMMY_CUB.Famiglia.Programmazione Fornitore-Articolo.Caller",
      "timeout" : -1,
      "refresh" : [ "')
    || TO_CLOB('gc_proiezioni_2" ],
      "params" : [ {
        "dimension" : "Compratore",
        "attribute" : "Descrizione",
        "paramName" : "inCompratore",
        "label" : "Compratore",
        "hasPicklist" : false,
        "disabled" : true
      } ]
    }, {
      "componentId" : "pnf_pro_inizializza",
      "componentLabel" : "Inizializza",
      "process" : "MUI_DUMMY_CUB.Pianificazione Panieri.SPF.Caller",
      "timeout" : -1,
      "refresh" : [ "gc_proiezioni_2" ],
      "p')
    || TO_CLOB('arams" : [ {
        "dimension" : "Compratore",
        "attribute" : "Descrizione",
        "paramName" : "inCompratore",
        "label" : "Compratore",
        "hasPicklist" : false,
        "disabled" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('17','/Pianificazione/Selezione_Articoli_Contributi',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_selezioneArticoliContributi_targetCategoria",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "title" : "Target per categoria",
    "height" : 29,
    "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]}')
    || TO_CLOB(',{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],')
    || TO_CLOB('BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]}) }",
          "Categoria" : "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Scenario" : "{[Scenario].[(I) Scenario Timone Pianificazione]}",
     ')
    || TO_CLOB('     "Misura Timone." : "{{[Misura Timone.].[MUI_SUB_Selezione_Articoli_Contributi_Timone]},{[Misura Timone.].[MUI_AVG]}}"
        }
      },
      "FROM" : "[Timone Categoria II]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_RepartoDesc", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
  ')
    || TO_CLOB('    "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.Descrizione",
      "headerName" : "Categoria",
      "cellClass" : "cellClass-left-text",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : ')
    || TO_CLOB('{
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        },
        "BDG" : {
          "headerClass" : "headerBdg",
          "openByDefault" : true
     ')
    || TO_CLOB('   }
      },
      "childrendefaults" : {
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : false,
        "type" : [ "TM1DataColumnInteger" ],
        "cellClass" : "cellClass-right-text"
      },
      "childrenCustomTypes" : {
        "N_FOTO_SPEC" : {
          "type" : [ "TM1DataColumnInteger" ],
          "hide" : "false"
        },
        "N_FOTO_ULT" : {
     ')
    || TO_CLOB('     "hide" : "false"
        },
        "N_FOTO_SCAFFALE" : {
          "hide" : "false"
        },
        "SPZ_CAMP" : {
          "hide" : "true"
        },
        "MUI_AVG" : {
          "hide" : "true"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cellClass" : "cellClass-right-text",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "D_VEND/RIF_%" : {
         ')
    || TO_CLOB(' "type" : [ "TM1DataColumnPercentage", "numericColumn" ],
          "columnGroupShow" : "always",
          "aggFunc" : "customAvg",
          "aggFuncParam" : "$MUI_AVG"
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "Promozione",
      "field" : "Promozione.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "hea')
    || TO_CLOB('derName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Reparto",
      "field" : "Categoria.MUI_RepartoDesc",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-te')
    || TO_CLOB('xt"
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_selezioneArticoliContributi_targetCategoria", "gc_selezioneArticoliContributi_promozione" ]
    }, {
      "di')
    || TO_CLOB('mension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_selezioneArticoliContributi_targetCategoria", "gc_selezioneArticoliContributi_promozione" ]
    } ],
    "styleRules" : { },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : true,
      "nodeGroupFalse" : true
')
    || TO_CLOB('    }
  }, {
    "name" : "gc_selezioneArticoliContributi_promozione",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "title" : "Programmazione articoli in promozione-Definizione contributi E.C. e in fattura",
    "height" : 29,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozion')
    || TO_CLOB('e].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
          "Fornitore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
          "Articolo" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY"')
    || TO_CLOB(' : false,
        "DIMENSIONS" : {
          "Misura Programmazione Fornitore" : "{[Misura Programmazione Fornitore].[(I) Programmazione Fornitore Articolo (Dinamico)]}"
        }
      },
      "FROM" : "[Programmazione Fornitore - Articolo]",
      "WHERE" : {
        "Scenario" : "[BDG]",
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Attivo", "Attributes/Riferimento", "Attributes/MUI_Descrizione",')
    || TO_CLOB(' "Attributes/DescrizioneCODICE", "Attributes/RepartoDesc", "Attributes/CategoriaDesc", "Attributes/GRMDesc", "Attributes/SubGrmDesc", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault')
    || TO_CLOB('" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "",
        "')
    || TO_CLOB('columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText" ],
        "cellClass" : "cellClass-left-text"
      },
      "childrenCustomTypes" : {
        "D_Promo1" : {
          "type" : [ "TM1DataColumnNumber" ],
          "aggFunc" : "",
          "cellClass" : "cellClass-right-text"
        },
        "D_Promo2" : {
          "type" : [ "TM1DataColumnNumber" ],
          "aggFunc" : "",
          "cellClass" : "cellClass-right-text"
       ')
    || TO_CLOB(' },
        "CONTR_%_IN_FATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "aggFunc" : "",
          "cellClass" : "cellClass-right-text"
        },
        "CONTR" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "EXTRA_CONTR" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "CONTR_SP" : {
          "type" : [ "TM1DataColumnInteger')
    || TO_CLOB('" ],
          "cellClass" : "cellClass-right-text"
        },
        "EXTRA_CONTR_SP" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "FF_C_%" : {
          "type" : [ "TM1DataColumnNumber" ],
          "aggFunc" : "",
          "cellClass" : "cellClass-right-text"
        },
        "EC_%_ABB_FATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
    ')
    || TO_CLOB('    "FF_EC_NC/ND_NO_Abbatt(%)" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "FF_EC_NC/ND_NO_Abbatt (€/pz)" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "E_PZ_SELL_IN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "FF_EC_%" : {
          "type" : [ "TM1DataColumnNumber" ],
 ')
    || TO_CLOB('         "aggFunc" : "",
          "cellClass" : "cellClass-right-text"
        },
        "E_PZ_SELL_OUT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "ASSORT" : {
          "hide" : true
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Articolo.Descrizione",
      "headerName" : "Articolo",
      "cellClass" : "cel')
    || TO_CLOB('lClass-left-text",
      "width" : 400,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Attivo",
      "field" : "Articolo.Attivo",
      "cellClass" : "cellClass-le')
    || TO_CLOB('ft-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Promozione",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
 ')
    || TO_CLOB('     "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Fornitore",
      "field" : "Fornitore.Descrizione",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Articolo(Codice)",
      "fiel')
    || TO_CLOB('d" : "Articolo.DescrizioneCODICE",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Reparto",
      "field" : "Articolo.RepartoDesc",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" :')
    || TO_CLOB(' "Categoria",
      "field" : "Articolo.CategoriaDesc",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Grm",
      "field" : "Articolo.GRMDesc",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
    ')
    || TO_CLOB('  "headerName" : "Sub Grm",
      "field" : "Articolo.SubGrmDesc",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Articolo",
      "field" : "Articolo.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-te')
    || TO_CLOB('xt"
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_selezioneArticoliContributi_targetCategoria", "gc_selezioneArticoliContributi_promozione" ]
    }, {
      "di')
    || TO_CLOB('mension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_selezioneArticoliContributi_targetCategoria", "gc_selezioneArticoliContributi_promozione" ]
    } ],
    "styleRules" : { },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : true,
      "nodeGroupFalse" : true
')
    || TO_CLOB('    }
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('18','/Pianificazione/Selezione_Famiglie_Contributi',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_selezioneFamiglieContributi_targetCategoria",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "title" : "Target per categoria",
    "height" : 29,
    "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]}')
    || TO_CLOB(',{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],')
    || TO_CLOB('BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]}) }",
          "Categoria" : "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Scenario" : "{[Scenario].[(I) Scenario Timone Pianificazione]}",
     ')
    || TO_CLOB('     "Misura Timone." : "{[Misura Timone.].[MUI_SUB_Selezione_Articoli_Contributi_Timone],[Misura Timone.].[MUI_AVG]}"
        }
      },
      "FROM" : "[Timone Categoria II]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_RepartoDesc", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      ')
    || TO_CLOB('"cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.Descrizione",
      "headerName" : "Categoria",
      "cellClass" : "cellClass-left-text",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
 ')
    || TO_CLOB('       "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        },
        "BDG" : {
          "headerClass" : "headerBdg",
          "openByDefault" : true
        }')
    || TO_CLOB('
      },
      "childrendefaults" : {
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : false,
        "type" : [ "TM1DataColumnInteger" ],
        "cellClass" : "cellClass-right-text"
      },
      "childrenCustomTypes" : {
        "N_FOTO_SPEC" : {
          "type" : [ "TM1DataColumnInteger" ],
          "hide" : "false"
        },
        "N_FOTO_ULT" : {
         ')
    || TO_CLOB(' "hide" : "true"
        },
        "N_FOTO_SCAFFALE" : {
          "hide" : "false"
        },
        "N_FOTO_SCAFFALE_SPEC" : {
          "hide" : "false"
        },
        "CONTR" : {
          "hide" : "false"
        },
        "EXTRA_CONTR" : {
          "hide" : "false"
        },
        "D_ART_slash_TGT" : {
          "hide" : "true"
        },
        "D_FOTO_slash_TGT" : {
          "hide" : "true"
        },
        "MUI_AVG" : {
          "hide" : "true"
    ')
    || TO_CLOB('    },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cellClass" : "cellClass-right-text",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "D_VEND/RIF_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ],
          "columnGroupShow" : "always",
          "aggFunc" : "customAvg",
          "aggFuncParam" : "$MUI_AVG"
        },
        "SEL" : {
          "type" ')
    || TO_CLOB(': [ "TM1DataColumnText" ],
          "cellClass" : "cellClass-left-text"
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "Promozione",
      "field" : "Promozione.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
     ')
    || TO_CLOB(' "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Categoria.MUI_RepartoDesc",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrC')
    || TO_CLOB('olumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_selezioneFamiglieContributi_targetCategoria", "gc_selezioneFamiglieContributi_promozione" ]
    }, {
      "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
')
    || TO_CLOB('
      "paramName" : "",
      "refresh" : [ "gc_selezioneFamiglieContributi_targetCategoria", "gc_selezioneFamiglieContributi_promozione" ]
    } ],
    "styleRules" : { },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "true",
      "nodeGroupFalse" : "true"
    }
  }, {
    "name" : "gc_selezioneFamiglieContributi_promozione",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "title" : "Programmazione Famiglie in promozione - defin')
    || TO_CLOB('izione contributi",
    "height" : 29,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
 ')
    || TO_CLOB('         "Fornitore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
          "Articolo" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Programmazione Fornitore" : "{[Misura Programmazione Fornitore].[(I) Programmazione Fornitore Articolo (Dinamico)]}"
        }
      },
      "FROM" : "[Programmazione Fornitore - Artico')
    || TO_CLOB('lo]",
      "WHERE" : {
        "Scenario" : "[BDG]",
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Attivo", "Attributes/Riferimento", "Attributes/MUI_Famiglia", "Attributes/MUI_Descrizione", "Attributes/DescrizioneCODICE", "Attributes/RepartoDesc", "Attributes/CategoriaDesc", "Attributes/GRMDesc", "Attributes/SubGrmDesc", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated"')
    || TO_CLOB(', "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : { },
      "childrendefaults" : {
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText" ],
        "cellClass')
    || TO_CLOB('" : "cellClass-left-text"
      },
      "childrenCustomTypes" : {
        "D_Promo1" : {
          "type" : [ "TM1DataColumnNumber" ],
          "aggFunc" : "",
          "cellClass" : "cellClass-right-text"
        },
        "D_Promo2" : {
          "type" : [ "TM1DataColumnNumber" ],
          "aggFunc" : "",
          "cellClass" : "cellClass-right-text"
        },
        "CONTR_%_IN_FATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "aggFunc" : "",
         ')
    || TO_CLOB(' "cellClass" : "cellClass-right-text"
        },
        "CONTR" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "EXTRA_CONTR" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "CONTR_SP" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "EXTRA_CONTR_SP" : {
          "type" : [ "T')
    || TO_CLOB('M1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "FF_C_%" : {
          "type" : [ "TM1DataColumnNumber" ],
          "aggFunc" : "",
          "cellClass" : "cellClass-right-text"
        },
        "EC_%_ABB_FATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "FF_EC_NC/ND_NO_Abbatt(%)" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-r')
    || TO_CLOB('ight-text"
        },
        "FF_EC_NC/ND_NO_Abbatt (€/pz)" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "E_PZ_SELL_IN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "FF_EC_%" : {
          "type" : [ "TM1DataColumnNumber" ],
          "aggFunc" : "",
          "cellClass" : "cellClass-right-text"
        },
        "E_PZ_SELL_OUT" : {
    ')
    || TO_CLOB('      "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "ASSORT" : {
          "hide" : true
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Articolo.Descrizione",
      "headerName" : "Articolo",
      "width" : 400,
      "pinned" : "left",
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    },
    "colum')
    || TO_CLOB('nDefs" : [ {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Attivo",
      "field" : "Articolo.Attivo",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "ty')
    || TO_CLOB('pe" : [ "TM1Element" ]
    }, {
      "headerName" : "Promozione",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1E')
    || TO_CLOB('lement" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Fornitore",
      "field" : "Fornitore.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Famiglia",
      "field" : "Articolo.MUI_Famiglia",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
 ')
    || TO_CLOB('     "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Articolo (Codice)",
      "field" : "Articolo.DescrizioneCODICE",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Reparto",
      "field" : "Articolo.RepartoDesc",
      "width" : 100,
      "hide" : false,
      "rowGroup" : ')
    || TO_CLOB('false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Categoria",
      "field" : "Articolo.CategoriaDesc",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Grm",
      "field" : "Articolo.GRMDesc",
      "width" : 100,
      "hide" : false,
   ')
    || TO_CLOB('   "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Sub Grm",
      "field" : "Articolo.SubGrmDesc",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    }, {
      "headerName" : "Articolo",
      "field" : "Articolo.Descrizione",
      "width" : 100,
      ')
    || TO_CLOB('"hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ],
      "cellClass" : "cellClass-left-text"
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPro')
    || TO_CLOB('mo",
      "refresh" : [ "gc_selezioneFamiglieContributi_targetCategoria", "gc_selezioneFamiglieContributi_promozione" ]
    }, {
      "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_selezioneFamiglieContributi_targetCategoria", "gc_selezioneFamiglieContribut')
    || TO_CLOB('i_promozione" ]
    } ],
    "styleRules" : { },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : true,
      "nodeGroupFalse" : true
    }
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('19','/Pianificazione/Selezione_Promo',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_listinoContributi",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "title" : "Listino Contributi",
    "height" : 29,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Tipo Promozione" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}",
          "Promozione" : "{FILTER( { { ORDER( ORDER( {')
    || TO_CLOB('{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Prestazione" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Misura Prestazioni" : "{[Misura Prestazioni].[listino] }",
          "Contrat')
    || TO_CLOB('to" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Contratto] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[Promozione Pianificazione Listino(I)]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field')
    || TO_CLOB('" : "Prestazione.Descrizione",
      "headerName" : "Prestazione",
      "width" : 500,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Tipo Promozione",
      "field" : "TipoPromozione.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Promozione",
      "field" : "Promozione.MUI_Descrizione",
      "')
    || TO_CLOB('width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Listino",
      "marryChildren" : true,
      "children" : [ {
        "headerName" : "Extra Contratto",
        "field" : "MisuraPrestazioni$listino$$Contratto$ExtraContratto",
        "width" : 100,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "type" : [ "TM1DataColumnInteger" ],
        "c')
    || TO_CLOB('ellClass" : "cellClass-right-text"
      }, {
        "headerName" : "Contratto",
        "field" : "MisuraPrestazioni$listino$$Contratto$Contratto_1",
        "width" : 100,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "type" : [ "TM1DataColumnInteger" ],
        "cellClass" : "cellClass-right-text"
      } ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promo')
    || TO_CLOB('zione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_listinoContributi", "gc_timing" ]
    } ],
    "styleRules" : { },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : true,
      "nodeGroupFalse" : true
    }
  }, {
    "name" : "gc_timing",
    "logMemory" : true,
    "logTime" : true,
  ')
    || TO_CLOB('  "skip" : true,
    "maxCells" : 750000,
    "title" : "Timing",
    "height" : 29,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Versione" : "{[Versione].[UFF]}",
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}"
        }
')
    || TO_CLOB('
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "MisuraPromozioneProprietà" : "{[Misura Promozione Proprietà].[Misura 1]}"
        }
      },
      "FROM" : "[Configurazione Promozione - Proprietà]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Canale", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/Riferimento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", ')
    || TO_CLOB('"Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Promozione.MUI_Descrizione",
      "headerName" : "Promozione",
      "cellClass" : "cellClass-left-text",
      "width" : 400,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
 ')
    || TO_CLOB('     "rowGroup" : true,
      "editable" : false,
      "cellClass" : "cellClass-left-text",
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "cellClass" : "cellClass-left-text",
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" ')
    || TO_CLOB(': true,
      "rowGroup" : true,
      "editable" : false,
      "cellClass" : "cellClass-left-text",
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 150,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "cellClass" : "cellClass-left-text",
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizio')
    || TO_CLOB('ne",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Pianificazione TM1/GPR",
      "field" : "MisuraPromozionePropriet_agrave_$resp_mktg1",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Volantino/Piano Media",
      "field" : "MisuraPromozionePropriet_agra')
    || TO_CLOB('ve_$resp_mktg2",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Raccolta Proiezioni",
      "field" : "MisuraPromozionePropriet_agrave_$1_pubblicazione",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Pre-Presen')
    || TO_CLOB('tazione CS",
      "field" : "MisuraPromozionePropriet_agrave_$data_riunione_commerciale",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Scadenza Conferma Prezzi",
      "field" : "MisuraPromozionePropriet_agrave_$data_scadenza_conferma_prezzi",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editab')
    || TO_CLOB('le" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data abb. prezzi",
      "field" : "MisuraPromozionePropriet_agrave_$Data_abb_prezzi",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Inizio Istituzionale",
      "field" : "MisuraPromozionePropriet_agrave_$Data_In')
    || TO_CLOB('izio_ist_dot_",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Fine Istituzionale",
      "field" : "MisuraPromozionePropriet_agrave_$Data_Fine_ist_dot_",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    },')
    || TO_CLOB(' {
      "headerName" : "Data Inizio DRO/GEM",
      "field" : "MisuraPromozionePropriet_agrave_$Data_inizio_DRO_slash_GEM",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Fine DRO/GEM",
      "field" : "MisuraPromozionePropriet_agrave_$Data_fine_DRO_slash_GEM",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false')
    || TO_CLOB(',
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Inizio Las",
      "field" : "MisuraPromozionePropriet_agrave_$Data_Inizio_Las",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Fine Las",
      "field" : "MisuraPromozionePropriet_agrave_$Data')
    || TO_CLOB('_Fine_Las",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Inizio Freschi",
      "field" : "MisuraPromozionePropriet_agrave_$Data_Inizio_Freschi",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
   ')
    || TO_CLOB('   "headerName" : "Data Fine Freschi",
      "field" : "MisuraPromozionePropriet_agrave_$Data_Fine_Freschi",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Inizio",
      "field" : "MisuraPromozionePropriet_agrave_$DataInizio",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : true,
      "c')
    || TO_CLOB('ellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Fine",
      "field" : "MisuraPromozionePropriet_agrave_$DataFine",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Valore Punto Fragola",
      "field" : "MisuraPromozionePropriet_agrave_$ValorePuntoFragola",
      "width" : 100,
    ')
    || TO_CLOB('  "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnDecimal3" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_l')
    || TO_CLOB('istinoContributi", "gc_timing" ]
    } ],
    "styleRules" : { },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : true,
      "nodeGroupFalse" : true
    }
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('20','/Piano_Annuale/Controllo_Negozi',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_ControlloNegozi",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
          "Reparto" : "{{TM1SORT( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMP')
    || TO_CLOB('TY" : false,
        "DIMENSIONS" : {
          "Scenario" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
          "Misura Timone." : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Ca')
    || TO_CLOB('nale", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/MUI_Descrizione", "Attributes/Riferimento", "Attributes/TipoElemento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist", "PicklistValues" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type')
    || TO_CLOB('" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre"')
    || TO_CLOB(',
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
  ')
    || TO_CLOB('    "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Descrizione",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,')
    || TO_CLOB('
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Tipo Elemento",
      "field" : "Reparto.TipoElemento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento Data",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
    ')
    || TO_CLOB('    "width" : 70,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 70,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "e')
    || TO_CLOB('ditable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "S')
    || TO_CLOB('cenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
     ')
    || TO_CLOB('   "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnIntege')
    || TO_CLOB('r", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Venduto Promo (s/iva)",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$')
    || TO_CLOB('VENDUTO_PROMO_NETTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "ML %",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "colum')
    || TO_CLOB('nGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target MKT",
      "headerClass" : "headerMkt",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 70,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" :')
    || TO_CLOB(' "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 70,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
    ')
    || TO_CLOB('    "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
   ')
    || TO_CLOB('     "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericC')
    || TO_CLOB('olumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT"')
    || TO_CLOB(',
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target REP",
      "headerClass" : "headerRep",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
        "widt')
    || TO_CLOB('h" : 70,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 70,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : t')
    || TO_CLOB('rue,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_REP')
    || TO_CLOB('$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",')
    || TO_CLOB('
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
 ')
    || TO_CLOB('     }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
        "widt')
    || TO_CLOB('h" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "op')
    || TO_CLOB('en",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target Acq.",
      "headerClass" : "headerAcq",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 70,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        ')
    || TO_CLOB('"editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 70,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenar')
    || TO_CLOB('io$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",')
    || TO_CLOB('
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
  ')
    || TO_CLOB('      "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
')
    || TO_CLOB('
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable')
    || TO_CLOB('" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('21','/Piano_Annuale/Crea_Promozione',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_CreaPromozione",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Crea Nuove Promozioni",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "}Clients" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}Clients] )}, 0)}, ASC)}}",
          "Anno" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Anno] )}, 0)}, DESC)}}",
 ')
    || TO_CLOB('         "ID" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "MisuraPianoOperativoCommerciale" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Piano Operativo Commerciale] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[Promozione Creazione]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Name", "Attributes/MUI_Client", "UniqueNa')
    || TO_CLOB('me" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist", "PicklistValues" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "ID.Name",
      "headerName" : "ID",
      "width" : 200,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Anno",
      "field" : "Anno.Name",
      "width" : 100,
      "hide" : true,
    ')
    || TO_CLOB('  "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Client",
      "field" : "_cbrace_Clients.MUI_Client",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "ID",
      "field" : "ID.Name",
      "width" : 80,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
  ')
    || TO_CLOB('  }, {
      "headerName" : "Ordine Timone",
      "field" : "MisuraPianoOperativoCommerciale$Ordinamento",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Desc",
      "field" : "MisuraPianoOperativoCommerciale$Desc",
      "width" : 500,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      ')
    || TO_CLOB('"headerName" : "Gruppo",
      "field" : "MisuraPianoOperativoCommerciale$TipoPromozione",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Sottogruppo",
      "field" : "MisuraPianoOperativoCommerciale$Canale",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerNa')
    || TO_CLOB('me" : "Data Inizio",
      "field" : "MisuraPianoOperativoCommerciale$DataInizio",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Fine",
      "field" : "MisuraPianoOperativoCommerciale$DataFine",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "')
    || TO_CLOB('dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Inizio Ist.",
      "field" : "MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Fine Ist.",
      "field" : "MisuraPianoOperativoCommerciale$Data_Fine_ist_d')
    || TO_CLOB('ot_",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Operazione",
      "field" : "MisuraPianoOperativoCommerciale$Operazione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    } ],
    "actions" : [ {
      "componentId" : "pi')
    || TO_CLOB('a_cp_crea",
      "componentLabel" : "Crea Promozione",
      "process" : "MUI_DUMMY_DIM.Promozione.NewPromo",
      "timeout" : -1,
      "refresh" : [ "gc_CreaPromozione" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('22','/Piano_Annuale/Foto',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_Foto_foto",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "suppressRowClickSelection" : false,
    "maxCells" : 2000000,
    "title" : "Foto",
    "height" : 22,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Raggruppamento Foto" : "{{TM1SORTBYINDEX({{TM1FILTERBYLEVE')
    || TO_CLOB('L( {TM1SUBSETALL( [Raggruppamento Foto] )}, 0)}}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "}ElementAttributes_Raggruppamento Foto" : "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Raggruppamento Foto] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[}ElementAttributes_Raggruppamento Foto]",
      "WHERE" : { }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "UniqueName", "Attrib')
    || TO_CLOB('utes/MUI_TOTS", "Attributes/MUI_Descrizione", "Attributes/MUI_Compratore", "Attributes/MUI_Reparto" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Reparto",
      "field" : "RaggruppamentoFoto.MUI_Reparto",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" :')
    || TO_CLOB(' "Compratore",
      "field" : "RaggruppamentoFoto.MUI_Compratore",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Raggruppamento Foto",
      "field" : "RaggruppamentoFoto.MUI_TOTS",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Foto",
      "field" : "Raggru')
    || TO_CLOB('ppamentoFoto.MUI_Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "selections" : [ {
      "source" : {
        "table" : "gc_Foto_foto",
        "dimension" : "RaggruppamentoFoto",
        "attribute" : "MUI_Descrizione"
      },
      "destination" : [ {
        "table" : "gc_Foto_foto",
        "dimension" : "RaggruppamentoFoto",
        "attribute" : "MUI_Descrizione"
   ')
    || TO_CLOB('   } ]
    } ]
  }, {
    "name" : "gc_Foto_compratore",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "suppressRowClickSelection" : false,
    "maxCells" : 2000000,
    "title" : "Compratore",
    "height" : 22,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Comprato')
    || TO_CLOB('re] ) }   , 0 ) } , { {[Compratore].[S000] }, {[Compratore].[NA] }})} , [Compratore].[MUI_Sort] , BASC )}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "}ElementAttributes_Compratore" : "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Compratore] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[}ElementAttributes_Compratore]",
      "WHERE" : { }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", ')
    || TO_CLOB('"Attributes/CategoryManager", "Attributes/MUI_Reparto", "Attributes/MUI_Descrizione", "Attributes/Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Category Manager",
      "field" : "Compratore.CategoryManager",
      "width" : 150,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
')
    || TO_CLOB('    }, {
      "headerName" : "Reparto",
      "field" : "Compratore.MUI_Reparto",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "selections" : [ {
      "source" : {
       ')
    || TO_CLOB(' "table" : "gc_Foto_compratore",
        "dimension" : "Compratore",
        "attribute" : "Descrizione"
      },
      "destination" : [ {
        "table" : "gc_Foto_compratore",
        "dimension" : "Compratore",
        "attribute" : "Descrizione"
      } ]
    } ]
  }, {
    "name" : "gc_Foto_associazione",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "maxCellsMessage" : "Aggiungere il filtro sulla Promozione.",
    "rowSupp')
    || TO_CLOB('ressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Condivisione foto",
    "height" : 28,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Raggruppamento Foto" : "{{TM1SORTBYINDEX({FILTER( {{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Raggruppamento Foto] )}, 0)}}, [Raggruppamento Foto].[MUI_NRFOTO] <= 30.000000)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          ')
    || TO_CLOB('"Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')} ",
          "Compratore" : "{{ [Compratore].[TOT_COMP], [Compratore].[S22]},{ EXCEPT( {TM1SUBSETALL( [Compratore] )}, { [Compratore].[NA], [Compratore].[S000], [Compratore].[TOT_COMP], [Compratore].[S22]}) }}"
        }
      },
      "FROM"')
    || TO_CLOB(' : "[Configurazione RaggrFoto]",
      "WHERE" : {
        "Misura Config RaggrFoto" : "[OK]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/MUI_TOT", "Attributes/MUI_TOTS", "Attributes/MUI_Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI')
    || TO_CLOB('_DescrizioneData", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
         ')
    || TO_CLOB(' "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : { }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "cellClass" : "cellClass-l')
    || TO_CLOB('eft-text",
      "field" : "RaggruppamentoFoto.Descrizione",
      "headerName" : "Raggruppamento Foto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Tot Foto",
      "field" : "RaggruppamentoFoto.MUI_TOT",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Tot Foto by",
      "field" : "Raggr')
    || TO_CLOB('uppamentoFoto.MUI_TOTS",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_Foto_associaz')
    || TO_CLOB('ione" ]
    } ],
    "actions" : [ {
      "componentId" : "pia_fo_condividi",
      "componentLabel" : "Condividi",
      "process" : "MUI_DUMMY_CUB.ConfigurazioneRaggrFoto",
      "timeout" : -1,
      "refresh" : [ "gc_Foto_associazione" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
        "label" : "Promozione",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : ')
    || TO_CLOB('true
      }, {
        "dimension" : "RaggruppamentoFoto",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inFoto",
        "label" : "Foto",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : true
      }, {
        "dimension" : "Compratore",
        "attribute" : "Descrizione",
        "paramName" : "inCompratore",
        "label" : "Compratore",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : true
    ')
    || TO_CLOB('  } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('23','/Piano_Annuale/Gabbia',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_Gabbia",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Gabbia",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promoz')
    || TO_CLOB('ione],BASC)}}, [Promozione].[Proximity] <> ''Y'')} "
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Piano Operativo Commerciale" : "{[Misura Piano Operativo Commerciale].[Costo_punto_Fragola]}"
        }
      },
      "FROM" : "[Promozione Aggiornamento]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Canale", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/Descriz')
    || TO_CLOB('ione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Promozione.MUI_Descrizione",
      "headerName" : "Promozione",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width"')
    || TO_CLOB(' : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : fa')
    || TO_CLOB('lse,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 150,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {')
    || TO_CLOB('
      "headerName" : "Valore Punto Fragola",
      "field" : "MisuraPianoOperativoCommerciale$Costo_punto_Fragola",
      "width" : 150,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnDecimal3", "numericColumn" ]
    } ]
  }, {
    "name" : "gc_GabbiaPunti",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Gabbia Punti",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" ')
    || TO_CLOB(': true,
        "DIMENSIONS" : {
          "ID" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Gabbia Punti Fragola" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Gabbia Punti Fragola] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[Gabbia Punti Fragola]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Name", "UniqueNa')
    || TO_CLOB('me" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "ID",
      "field" : "ID.Name",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Prezzo Minimo",
      "field" : "MisuraGabbiaPuntiFragola$PrezzoMinimo",
      "width" : 100,
      "hide" : false,
  ')
    || TO_CLOB('    "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnNumber", "numericColumn" ]
    }, {
      "headerName" : "Prezzo Massimo",
      "field" : "MisuraGabbiaPuntiFragola$PrezzoMassimo",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnNumber", "numericColumn" ]
    }, {
      "headerName" : "Punti Fragola",
      "field" : "MisuraGabbiaPuntiFragola$PuntiFragola",
      "width" : 1')
    || TO_CLOB('00,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnNumber", "numericColumn" ]
    } ],
    "actions" : [ {
      "componentId" : "pia_ga_modifica",
      "componentLabel" : "Modifica Valore Punti Fragola",
      "process" : "MUI_DUMMY_DIM.Cambio valore Costo Punti Fragola",
      "timeout" : -1,
      "refresh" : [ "gc_Gabbia", "gc_GabbiaPunti" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : ')
    || TO_CLOB('"Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
        "label" : "Descrizione",
        "hasPicklist" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('24','/Piano_Annuale/Gestione_Contributi',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_GestioneContributi",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Listino Contributi",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[P')
    || TO_CLOB('romozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')} ",
          "Tipo Promozione" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Contratto" : "{[Contratto].[Extra Contratto],[Contratto].[Contratto_1] }",
          "Prestazione" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}",
          "Misura Prestazi')
    || TO_CLOB('oni" : "[Misura Prestazioni].[Listino]"
        }
      },
      "FROM" : "[Promozione Modifica - Listino]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Caption_Default", "Attributes/Canale", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
   ')
    || TO_CLOB('   "headerconf" : [ "Descrizione", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerC')
    || TO_CLOB('lass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "avg",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "DataInizio" : {
          "cellClass" : "dateFormat",
          "type" : [ "TM1DataColumnDate", "numeric')
    || TO_CLOB('Column" ],
          "aggFunc" : "",
          "columnGroupShow" : "always"
        },
        "DataFine" : {
          "cellClass" : "dateFormat",
          "type" : [ "TM1DataColumnDate", "numericColumn" ],
          "aggFunc" : "",
          "columnGroupShow" : "always"
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Promozione.MUI_Descrizione",
      "headerName" : "Promozione",
  ')
    || TO_CLOB('    "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Tipo Promozione",
      "field" : "TipoPromozione.Caption_Default",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" ')
    || TO_CLOB(': false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Des')
    || TO_CLOB('crizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nod')
    || TO_CLOB('eGroupFalse" : "false"
    }
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('25','/Piano_Annuale/Gestione_Iniziative',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_GestioneIniziative",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Configurazione Iniziative",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], ')
    || TO_CLOB('BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Iniziativa" : "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Iniziativa] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Configurazione Iniziative" : "{[Misura Configurazione Iniziative].[OK] }"
        }
      },
      "FROM" : "[Configurazione Promozione - Iniziative]",
      "WHERE" : {
        "Scenario" :')
    || TO_CLOB(' "[BDG]",
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Canale", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/MUI_Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Iniziativa.Descrizione",
      "heade')
    || TO_CLOB('rName" : "Iniziativa",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : ')
    || TO_CLOB('false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "head')
    || TO_CLOB('erName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 150,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "OK",
      "field" : "MisuraConfigurazioneIniziative$OK",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnInteger", "numericColumn" ]
    } ],
    "groupRowAggNodes" : {
   ')
    || TO_CLOB('   "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "false"
    }
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('26','/Piano_Annuale/Meccaniche',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_Meccaniche",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Configurazione Meccaniche Promozionali",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Configurazione Promozione" : "{[Misura Configurazione Promozione].[SEL]}",
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1')
    || TO_CLOB('SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Meccanica Complesse" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Meccanica Complesse] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[Configurazione Promozione - Meccaniche]"
    },
    "ExecuteMDX"')
    || TO_CLOB(' : {
      "Members" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Canale", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Promozione.MUI_Descrizione",
      "headerName" : "Promozione",
      "width" : 500,
      "pinned" : "left",')
    || TO_CLOB('
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 60,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "cellClass" : "cellClass-center-text",
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 60,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "cellClass')
    || TO_CLOB('" : "cellClass-center-text",
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "cellClass" : "cellClass-center-text",
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editab')
    || TO_CLOB('le" : false,
      "cellClass" : "cellClass-center-text",
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 150,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "cellClass" : "cellClass-center-text",
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Meccaniche Semplici",
      "openByDefault" : true,
      "children" : [ {
        "headerName')
    || TO_CLOB('" : "Sconto %",
        "field" : "MeccanicaComplesse$M002",
        "width" : 70,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Prezzo Corto",
        "field" : "MeccanicaComplesse$M003",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-cente')
    || TO_CLOB('r-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "P. Fragola",
        "field" : "MeccanicaComplesse$M004",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Sc % Fidaty",
        "field" : "MeccanicaComplesse$M005",
        "width" : 110,
        "hide" : false,
        "r')
    || TO_CLOB('owGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Prezzo Corto & P.Fragola",
        "field" : "MeccanicaComplesse$M006",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Sc% Fidaty  &  P. Fra')
    || TO_CLOB('gola",
        "field" : "MeccanicaComplesse$M007",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "3x2",
        "field" : "MeccanicaComplesse$M008",
        "width" : 50,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        ')
    || TO_CLOB('"type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "1 + 1",
        "field" : "MeccanicaComplesse$M009",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Il 2 costa la metà ",
        "field" : "MeccanicaComplesse$M010",
        "width" : 110,
        "hide" : false,
        "rowGroup" : fals')
    || TO_CLOB('e,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Sconto % + Punti Fragola",
        "field" : "MeccanicaComplesse$M012",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Sc + Facile val",
        "field" :')
    || TO_CLOB(' "MeccanicaComplesse$M014",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Oggetti articolo",
        "field" : "MeccanicaComplesse$M015",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ ')
    || TO_CLOB('"TM1DataColumnText" ]
      }, {
        "headerName" : "Buono sc.Fidaty (F11)",
        "field" : "MeccanicaComplesse$M018",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Sc Val Fidaty",
        "field" : "MeccanicaComplesse$M023",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false')
    || TO_CLOB(',
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Sconto fidaty per classe cliente (%)",
        "field" : "MeccanicaComplesse$M024",
        "width" : 170,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "PC + Triplica Punti",
  ')
    || TO_CLOB('      "field" : "MeccanicaComplesse$M025",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Punti Fragola su insieme",
        "field" : "MeccanicaComplesse$M104",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text')
    || TO_CLOB('",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Triplica i punti",
        "field" : "MeccanicaComplesse$M111",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Punti Fragola Set per Triplica Punti",
        "field" : "MeccanicaComplesse$M164",
        "width" : 170,
        "h')
    || TO_CLOB('ide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Buono Sconto Categoria - sconto a valore",
        "field" : "MeccanicaComplesse$M031",
        "width" : 170,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
    ')
    || TO_CLOB('    "headerName" : "Buono Sconto Categoria - punti fragola",
        "field" : "MeccanicaComplesse$M034",
        "width" : 170,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Buono Sconto Categoria - sconto percentuale",
        "field" : "MeccanicaComplesse$M035",
        "width" : 170,
        "hide" : false,
        "rowGroup"')
    || TO_CLOB(' : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Buono Sconto Categoria - sc val - Set semplice",
        "field" : "MeccanicaComplesse$M131",
        "width" : 170,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Buono S')
    || TO_CLOB('conto Categoria - PF - Set semplice",
        "field" : "MeccanicaComplesse$M134",
        "width" : 170,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Buono Sconto Categoria - sc % - Set semplice",
        "field" : "MeccanicaComplesse$M135",
        "width" : 170,
        "hide" : false,
        "rowGroup" : false,
        "ed')
    || TO_CLOB('itable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Set Semplice per Triplica Oggetti",
        "field" : "MeccanicaComplesse$M165",
        "width" : 170,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "PAGINA PUBBLICITARIA",
        "field" : ')
    || TO_CLOB('"MeccanicaComplesse$M000",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Meccanica NO Promo con Spazio",
        "field" : "MeccanicaComplesse$M090",
        "width" : 170,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
       ')
    || TO_CLOB(' "type" : [ "TM1DataColumnText" ]
      } ]
    }, {
      "headerName" : "Meccaniche Complesse",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "SSc + Facile Set",
        "field" : "MeccanicaComplesse$M114",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Oggetti Set",
      ')
    || TO_CLOB('  "field" : "MeccanicaComplesse$M115",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Sconto SET a valore",
        "field" : "MeccanicaComplesse$M201",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
     ')
    || TO_CLOB('   "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Sconto SET %",
        "field" : "MeccanicaComplesse$M205",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Sconto Set Esteso Valore",
        "field" : "MeccanicaComplesse$M301",
        "width" : 110,
        "hide" : false,
        "r')
    || TO_CLOB('owGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Set Esteso - prezzo corto",
        "field" : "MeccanicaComplesse$M303",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Set Esteso Punti Fra')
    || TO_CLOB('gola",
        "field" : "MeccanicaComplesse$M304",
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellClass-center-text",
        "type" : [ "TM1DataColumnText" ]
      }, {
        "headerName" : "Sconto Percentuale Set Esteso",
        "field" : "MeccanicaComplesse$M305",
        "width" : 170,
        "hide" : false,
        "rowGroup" : false,
        "editable" : true,
        "cellClass" : "cellCl')
    || TO_CLOB('ass-center-text",
        "type" : [ "TM1DataColumnText" ]
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('27','/Piano_Annuale/Modifica_Promozione',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_ModificaPromozione",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Modifica Promozione",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[')
    || TO_CLOB('Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "MisuraPianoOperativoCommerciale" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Piano Operativo Commerciale] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[Promozione Aggiornamento]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Anno", "Attribute')
    || TO_CLOB('s/Canale", "Attributes/Descrizione", "Attributes/Riferimento", "Attributes/MUI_Semestre", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Promozione.MUI_Descrizione",
      "headerName" : "Promozione",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
')
    || TO_CLOB('      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizion')
    || TO_CLOB('e",
      "width" : 150,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 90,
      "hide" : false,
      "row')
    || TO_CLOB('Group" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 85,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Operazione",
      "field" : "MisuraPianoOperativoCommerciale$Operazione",
      "width" : 90,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,')
    || TO_CLOB('
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "MisuraPianoOperativoCommerciale$Descrizione",
      "width" : 300,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Descrizione.",
      "field" : "MisuraPianoOperativoCommerciale$Desc_Agg",
      "width" : 300,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
  ')
    || TO_CLOB('    "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Data Inizio",
      "field" : "MisuraPianoOperativoCommerciale$DataInizio",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Inizio.",
      "field" : "MisuraPianoOperativoCommerciale$DataInizio_Agg",
      "width" : 110,
      "hide" : false,
  ')
    || TO_CLOB('    "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Fine",
      "field" : "MisuraPianoOperativoCommerciale$DataFine",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Fine.",
      "field" :')
    || TO_CLOB(' "MisuraPianoOperativoCommerciale$DataFine_Agg",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Inizio Ist.",
      "field" : "MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
  ')
    || TO_CLOB('    "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Inizio Ist",
      "field" : "MisuraPianoOperativoCommerciale$DataInizioIst_Agg",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Fine Ist.",
      "field" : "MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_",
      "width"')
    || TO_CLOB(' : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Fine Ist",
      "field" : "MisuraPianoOperativoCommerciale$DataFineIst_Agg",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      ')
    || TO_CLOB('"headerName" : "Data Abbatt Prezzi",
      "field" : "MisuraPianoOperativoCommerciale$DATA_ABB_PRZ",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Abbatt Prezzi_",
      "field" : "MisuraPianoOperativoCommerciale$DATA_ABB_PRZ_AGG",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "edit')
    || TO_CLOB('able" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Ordine Timone",
      "field" : "MisuraPianoOperativoCommerciale$Ordinamento",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Ordine Timone.",
      "field" : "MisuraPianoOperativoCommerciale$Ordinamento_Agg",
      "width" : 200,
  ')
    || TO_CLOB('    "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "No Stampa %",
      "field" : "MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "No Stampa %_",
      "field" : "MisuraPianoOperativoCommerciale$ETICH_SENZA__perc__AGG",
      "')
    || TO_CLOB('width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "No Stampa Etic",
      "field" : "MisuraPianoOperativoCommerciale$NO_STMP_ETICH",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "CanalePromozionale",
      "field" : "MisuraPianoOperativoCommerciale$Canale",
  ')
    || TO_CLOB('    "width" : 150,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "StatoPromozione",
      "field" : "MisuraPianoOperativoCommerciale$StatoPromo",
      "width" : 150,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "TipoPromozione",
      "field" : "MisuraPianoOperativoCommerciale$TipoPromozion')
    || TO_CLOB('e",
      "width" : 150,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Note",
      "field" : "MisuraPianoOperativoCommerciale$Note",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Note Marketing",
      "field" : "MisuraPianoOperativoCommerciale$NoteMarketing",
      ')
    || TO_CLOB('"width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    } ],
    "actions" : [ {
      "componentId" : "pia_mp_aggiorna",
      "componentLabel" : "Aggiorna Promozione",
      "process" : "MUI_DUMMY_DIM.Promozione.UpdatePromo (I)",
      "timeout" : -1,
      "refresh" : [ "gc_ModificaPromozione" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : ')
    || TO_CLOB('"inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
        "label" : "Promozione",
        "hasPicklist" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('28','/Piano_Annuale/Negozi_Promo',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_NegoziPromo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Definizione Negozi Promozione (Modifica attiva per promozioni in pianificazione)",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Negozio" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
 ')
    || TO_CLOB('       "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Sezione Tematica" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, ASC)}}",
          "Canale" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETAL')
    || TO_CLOB('L( [Canale] )}, 0)}, ASC)}}",
          "Misura Canale" : "{ [Misura Canale].[Tipo_Neg_Calc], [Misura Canale].[Tipo_Neg_Input], [Misura Canale].[Canale], [Misura Canale].[DataInizio], [Misura Canale].[DataFine],[Misura Canale].[Delta] }"
        }
      },
      "FROM" : "[Canale Neg SezTematica]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Canale", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/MUI_Descrizione", "Attributes/MUI')
    || TO_CLOB('_ZonaPromo", "Attributes/MUI_DescrizioneData", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT')
    || TO_CLOB('_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
 ')
    || TO_CLOB('       "editable" : true,
        "type" : [ "TM1DataColumnText" ]
      },
      "childrenCustomTypes" : {
        "DataInizio" : {
          "cellClass" : "dateFormat",
          "type" : [ "TM1DataColumnDate", "numericColumn" ],
          "aggFunc" : "",
          "columnGroupShow" : "always"
        },
        "DataFine" : {
          "cellClass" : "dateFormat",
          "type" : [ "TM1DataColumnDate", "numericColumn" ],
          "aggFunc" : "",
          "columnGroupShow" : ')
    || TO_CLOB('"always"
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Negozio.Descrizione",
      "headerName" : "Negozio",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
     ')
    || TO_CLOB(' "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 80,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data')
    || TO_CLOB('",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Zona Promo",
      "field" : "Negozio.MUI_ZonaPromo",
      "width" : 150,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Negozio",
      "field" : "Negozio.Descrizione",
      "width" : 15')
    || TO_CLOB('0,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('29','/Piano_Annuale/Timing',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_Timing",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Timing",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Versione" : "{[Versione].[UFF]}",
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozi')
    || TO_CLOB('one].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "MisuraPromozioneProprietà " : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Promozione Proprietà] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[Configurazione Promozione - Proprietà]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Canale", "Attributes/MUI')
    || TO_CLOB('_Descrizione", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/Riferimento", "Attributes/MUI_Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Promozione.MUI_Descrizione",
      "headerName" : "Promozione",
      "width" : 500,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    }')
    || TO_CLOB(',
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 60,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozio')
    || TO_CLOB('ne.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 110,
      "')
    || TO_CLOB('hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Data Inizio Las",
      "field" : "MisuraPromozionePropriet_agrave_$Data_Inizio_Las",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Fine Las",
      "field" : "MisuraPromozionePropriet_agrave_$Data_F')
    || TO_CLOB('ine_Las",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Inizio Freschi",
      "field" : "MisuraPromozionePropriet_agrave_$Data_Inizio_Freschi",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
     ')
    || TO_CLOB(' "headerName" : "Data Fine Freschi",
      "field" : "MisuraPromozionePropriet_agrave_$Data_Fine_Freschi",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Inizio DRO/GEM",
      "field" : "MisuraPromozionePropriet_agrave_$Data_inizio_DRO_slash_GEM",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "edita')
    || TO_CLOB('ble" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Fine DRO/GEM",
      "field" : "MisuraPromozionePropriet_agrave_$Data_fine_DRO_slash_GEM",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Raccolta Proiezioni",
      "field" : "MisuraPromozionePropriet_agrave_$1_p')
    || TO_CLOB('ubblicazione",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Pre-Presentazione CS",
      "field" : "MisuraPromozionePropriet_agrave_$data_riunione_commerciale",
      "width" : 150,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]')
    || TO_CLOB('
    }, {
      "headerName" : "Data Scadenza Conferma Prezzi",
      "field" : "MisuraPromozionePropriet_agrave_$data_scadenza_conferma_prezzi",
      "width" : 150,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Pianificazione TM1/GPR",
      "field" : "MisuraPromozionePropriet_agrave_$resp_mktg1",
      "width" : 100,
      "hide" : false,
      "row')
    || TO_CLOB('Group" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Volantino/Piano Media",
      "field" : "MisuraPromozionePropriet_agrave_$resp_mktg2",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Data Inizio",
      "field" : "MisuraPromozionePropriet_agrave_$DataInizio",
      "width" : 110,
      "hide" : false,
  ')
    || TO_CLOB('    "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Fine",
      "field" : "MisuraPromozionePropriet_agrave_$DataFine",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Inizio Istituzionale",
      "field" : "MisuraPromozione')
    || TO_CLOB('Propriet_agrave_$Data_Inizio_ist_dot_",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Fine Istituzionale",
      "field" : "MisuraPromozionePropriet_agrave_$Data_Fine_ist_dot_",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1D')
    || TO_CLOB('ataColumnDate" ]
    }, {
      "headerName" : "Data abb. prezzi",
      "field" : "MisuraPromozionePropriet_agrave_$Data_abb_prezzi",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Valore Punto Fragola",
      "field" : "MisuraPromozionePropriet_agrave_$ValorePuntoFragola",
      "width" : 100,
      "hide" : false,
      "rowGroup')
    || TO_CLOB('" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnDecimal3" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('30','/Piano_Annuale/Visualizza_Piano',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_VisualizzaPiano",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "pagination" : false,
    "title" : "Riepilogo Promozioni",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Prom')
    || TO_CLOB('ozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}   "
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "MisuraPianoOperativoCommerciale" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Piano Operativo Commerciale] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[Promozione Aggiornamento]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_Descrizione", "')
    || TO_CLOB('Attributes/Anno", "Attributes/Canale", "Attributes/Descrizione", "Attributes/Riferimento", "Attributes/MUI_Semestre", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Promozione.MUI_Descrizione",
      "headerName" : "Promozione",
      "width" : 500,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    ')
    || TO_CLOB('},
    "columnDefs" : [ {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 80,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 80,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" ')
    || TO_CLOB(': "Promozione.MUI_Descrizione",
      "width" : 110,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 90,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 90,
     ')
    || TO_CLOB(' "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Data Inizio",
      "field" : "MisuraPianoOperativoCommerciale$DataInizio",
      "width" : 120,
      "hide" : false,
      "rowGroup" : fal')
    || TO_CLOB('se,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Fine",
      "field" : "MisuraPianoOperativoCommerciale$DataFine",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Inizio Ist",
      "field" : "MisuraPianoOp')
    || TO_CLOB('erativoCommerciale$Data_Inizio_ist_dot_",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Fine Ist",
      "field" : "MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" :')
    || TO_CLOB(' [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Data Abbatt.Prezzi",
      "field" : "MisuraPianoOperativoCommerciale$DATA_ABB_PRZ",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate", "numericColumn" ]
    }, {
      "headerName" : "Ordine Timone",
      "field" : "MisuraPianoOperativoCommerciale$Ordinamento",
      "width" : 80,
      "hide" :')
    || TO_CLOB(' false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "No Stampa %",
      "field" : "MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "No Stampa Etic",
      "field" : "MisuraPianoOperativoCommerciale$NO_STMP_ETICH",
      "width" : 110,
    ')
    || TO_CLOB('  "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Canale Promozionale",
      "field" : "MisuraPianoOperativoCommerciale$Canale",
      "width" : 150,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Stato Promozione",
      "field" : "MisuraPianoOperativoCommerciale$StatoPromo",
      "width" : 170,
')
    || TO_CLOB('
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Note Marketing",
      "field" : "MisuraPianoOperativoCommerciale$NoteMarketing",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('31','/Piano_Annuale/Zone_Promo',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_ZonePromo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Abilitazione delle zone promozionali",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Configurazione Promozione" : "{[Misura Configurazione Promozione].[SEL]}",
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUB')
    || TO_CLOB('SETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Meccanica Complesse" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Zona Promo] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[Configurazione Promozione - Zone]"
    },
    "ExecuteMDX" : {
      "Membe')
    || TO_CLOB('rs" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/Anno", "Attributes/Canale", "Attributes/MUI_Semestre", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Promozione.MUI_Descrizione",
      "headerName" : "Promozione",
      "width" : 300,
      "pinned" : "left",
      "type" : [')
    || TO_CLOB(' "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
  ')
    || TO_CLOB('    "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "')
    || TO_CLOB('width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Zona BDG",
      "field" : "ZonaPromo$ZONAPROMO_ALL",
      "width" : 90,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "[1_01] MILANO",
      "field" : "ZonaPromo$ZONAPROMO_1_01",
      "width" : 125,
      ')
    || TO_CLOB('"hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "[1_02] EMILIA",
      "field" : "ZonaPromo$ZONAPROMO_1_02",
      "width" : 125,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "[1_03] PIEMONTE",
      "field" : "ZonaPromo$ZONAPROMO_1_03",
    ')
    || TO_CLOB('  "width" : 125,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "[1_04] VERONA",
      "field" : "ZonaPromo$ZONAPROMO_1_04",
      "width" : 125,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "[2_01] FIRENZE",
      "field" : "ZonaPromo')
    || TO_CLOB('$ZONAPROMO_2_01",
      "width" : 125,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "[2_02] LAZIO",
      "field" : "ZonaPromo$ZONAPROMO_2_02",
      "width" : 125,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('32','/Piano_Annuale/Sezioni_Tematiche/Sezioni_Tematiche',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SezioniTematiche_SezioniTematiche",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Associazione sezione tematica e campagna",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDES')
    || TO_CLOB('C)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Pubblicità" : "{[Pubblicità].[(I) Volantino]}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Zona Promo" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Zona Promo] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[Configurazione Promozione - Sezione Tematica]"
    },
    "ExecuteMDX" : {
 ')
    || TO_CLOB('     "Members" : [ "Name", "Attributes/Canale", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/MUI_Descrizione", "Attributes/Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Pubblicit_agrave_.Descrizione",
      "headerName" : "",
      "width" : 300,
      "pinned" : "left",
      "type"')
    || TO_CLOB(' : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 60,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 50,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
')
    || TO_CLOB('
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
     ')
    || TO_CLOB(' "width" : 90,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione Sezione Tematica",
      "field" : "MisuraZonaPromo$Descrizione",
      "width" : 170,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Canale",
      "field" : "MisuraZonaPromo$Canale",
      "width" : 70,
      "hide" : fals')
    || TO_CLOB('e,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Agg_canale",
      "field" : "MisuraZonaPromo$Agg_canale",
      "width" : 90,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "DataInizio",
      "field" : "MisuraZonaPromo$DataInizio",
      "width" : 80,
      "hide" : false,
      "rowGroup" : false,
      "ed')
    || TO_CLOB('itable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "DataFine",
      "field" : "MisuraZonaPromo$DataFine",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Zona BDG",
      "field" : "MisuraZonaPromo$ZONAPROMO_ALL",
      "width" : 70,
      "hide" : false,
      "ro')
    || TO_CLOB('wGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "[1_01] MILANO",
      "field" : "MisuraZonaPromo$ZONAPROMO_1_01",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "[1_02] EMILIA",
      "field" : "MisuraZonaPromo$ZONAPROMO_1_02",
      "width" : 70,
')
    || TO_CLOB('      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "[1_03] PIEMONTE",
      "field" : "MisuraZonaPromo$ZONAPROMO_1_03",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "[1_04] VERONA",
      "field" : "MisuraZonaPromo$ZONAP')
    || TO_CLOB('ROMO_1_04",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "[2_01] FIRENZE",
      "field" : "MisuraZonaPromo$ZONAPROMO_2_01",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "[2_02] LAZIO",
      ')
    || TO_CLOB('"field" : "MisuraZonaPromo$ZONAPROMO_2_02",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "aggFunc" : "sum",
      "type" : [ "TM1DataColumnInteger" ]
    }, {
      "headerName" : "Check",
      "field" : "MisuraZonaPromo$Check",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('33','/Piano_Annuale/Sezioni_Tematiche/Crea_Sezioni_Tematiche',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SezioniTematiche_CreaSezioniTematiche",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Definizione Sezioni Tematiche",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "ID Sezione Tematica" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID Sezione Tematica] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
  ')
    || TO_CLOB('      "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Configurazione Sezione Tematica" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Configurazione Sezione Tematica] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[Configurazione Sezione Tematica]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Name", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {')
    || TO_CLOB(' },
    "columnDefs" : [ {
      "headerName" : "ID Sezione Tematica",
      "field" : "IDSezioneTematica.Name",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "MisuraConfigurazioneSezioneTematica$Descrizione",
      "width" : 400,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]')
    || TO_CLOB('
    }, {
      "headerName" : "Tipo",
      "field" : "MisuraConfigurazioneSezioneTematica$TIPO",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Note",
      "field" : "MisuraConfigurazioneSezioneTematica$Note",
      "width" : 170,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "hea')
    || TO_CLOB('derName" : "Operazione",
      "field" : "MisuraConfigurazioneSezioneTematica$Operazione",
      "width" : 400,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    } ],
    "actions" : [ {
      "componentId" : "pia_st_aggiorna",
      "componentLabel" : "Aggiorna Anagrafica Sezioni Tematiche",
      "process" : "MUI_DUMMY_DIM.Sezione Tematica",
      "timeout" : -1,
      "refresh" : [ "gc_SezioniTematiche_CreaSezioniT')
    || TO_CLOB('ematiche" ],
      "params" : [ {
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : false
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('34','/Piano_Annuale/Spazi/Macrospazi_Listino_Promo',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_Spazi_MacrospaziListinoPromo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Configurazione Macrospazi Listino Promo",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "MacroSpazio" : "{[MacroSpazio].[MacroSpazio_liv0]}"
        }
      },
')
    || TO_CLOB('      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Misura_Configurazione_Macrospazi_Listino" : "{[Misura_Configurazione_Macrospazi_Listino].[CC],[Misura_Configurazione_Macrospazi_Listino].[EC]}"
   ')
    || TO_CLOB('     }
      },
      "FROM" : "[Configurazione Macrospazio - Listino - Promo]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_DescrizioneData", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione" ],
      "headerdefaults" : {
   ')
    || TO_CLOB('     "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefault')
    || TO_CLOB('s" : {
        "width" : 260,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "closed",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VALIDO_DAL" : {
          "type" : [ "TM1DataColumnText" ],
          "aggFunc" : "",
          "columnGroupShow" : "always"
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendere')
    || TO_CLOB('rParams" : {
        "suppressCount" : true
      },
      "field" : "Promozione.MUI_Descrizione",
      "headerName" : "Promozione",
      "width" : 400,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Macro Spazio",
      "field" : "MacroSpazio.MUI_Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('35','/Piano_Annuale/Spazi/Macrospazi_Negozi_Promo',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_Spazi_MacrospaziNegoziPromo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Configurazione Macrospazi Negozi Promo",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Negozio" : "{EXCEPT({{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] ')
    || TO_CLOB(')}, 0)}, ASC)}},{[Negozio].[NA] })}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}   ",
          "MacroSpazio" : "{[MacroSpazio].[MacroSpazio_liv0]}",
          "Misura_Configurazione_')
    || TO_CLOB('Macrospazi_Neg_Promo" : "{[Misura_Configurazione_Macrospazi_Neg_Promo].[DEFAULT],[Misura_Configurazione_Macrospazi_Neg_Promo].[SPAZIO]}"
        }
      },
      "FROM" : "[Configurazione Macrospazio - Negozio - Promo]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_DescrizioneData", "Attributes/Descrizione", "Attributes/MUI_ZonaPromo", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns"')
    || TO_CLOB(' : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true')
    || TO_CLOB('
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VALIDO_DAL" : {
          "type" : [ "TM1DataColum')
    || TO_CLOB('nText" ],
          "aggFunc" : "",
          "columnGroupShow" : "always"
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Negozio.Descrizione",
      "headerName" : "Negozio",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Zona Promo",
      "field" : "Negozio.MUI_ZonaPromo",
      "width" : 100,
    ')
    || TO_CLOB('  "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Negozio",
      "field" : "Negozio.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('36','/Piano_Annuale/Spazi/Macrospazi_Negozi',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_Spazi_MacrospaziNegozi",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Configurazione Macrospazi Negozio",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Negozio" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
       ')
    || TO_CLOB(' "DIMENSIONS" : {
          "MacroSpazio" : "{[MacroSpazio].[MacroSpazio_liv0]}",
          "Misura_Configurazione_Macrospazi_Neg" : "{[Misura_Configurazione_Macrospazi_Neg].[(I) Configurazione Macro Neg]}"
        }
      },
      "FROM" : "[Configurazione Macrospazio - Negozio]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_TOT", "Attributes/MUI_ZonaPromo", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolid')
    || TO_CLOB('ated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
   ')
    || TO_CLOB('       "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "closed",
        "editable" : true,
        "type" : [ "TM1DataColumnText" ]
      },
      "childrenCustomTypes" : {
        "VALIDO_DAL" : {
          "columnGroupSho')
    || TO_CLOB('w" : "always"
        },
        "DEFAULT" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ]
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Negozio.Descrizione",
      "headerName" : "Negozio",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Tot. Negozio",
      "field" : "Negozio.MUI_T')
    || TO_CLOB('OT",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Zona Promo",
      "field" : "Negozio.MUI_ZonaPromo",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Negozio",
      "field" : "Negozio.Descrizione",
      "width" : 200,
      "hide" : true,
      "rowGroup" : ')
    || TO_CLOB('false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Negozio.Name == ''NA'' )"
    }
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('37','/Piano_Annuale/Spazi/Macrospazi',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_Spazi_MacroSpazi",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Anagrafica Macrospazi",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "MacroSpazio" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIO')
    || TO_CLOB('NS" : {
          "Misura_Macrospazi" : "{[Misura_Macrospazi].[(I) Macro Spazi]}"
        }
      },
      "FROM" : "[Configurazione Macrospazio]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaul')
    || TO_CLOB('ts" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "chi')
    || TO_CLOB('ldrendefaults" : {
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "NOTE" : {
          "type" : [ "TM1DataColumnText" ],
          "columnGroupShow" : "always"
        },
        "GG_COM_DEFAULT" : {
          "type" : [ "TM1DataColumnText" ],
          "columnGroupShow" : "always"')
    || TO_CLOB('
        },
        "GRP_SPZ" : {
          "type" : [ "TM1DataColumnText" ],
          "columnGroupShow" : "always"
        }
      }
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Macro Spazio",
      "field" : "MacroSpazio.MUI_Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ]
  }, {
    "name" : "gc_Spazi_MacroSpazi_Listino",
    "logMemor')
    || TO_CLOB('y" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Listino Macrospazi",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "MacroSpazio" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura_Configurazione_Macrospazi_Listino" : "{[Misura_Configurazione_Macrospazi_Listino].')
    || TO_CLOB('[(I) Configurazione Macro Listino]}"
        }
      },
      "FROM" : "[Configurazione Macrospazio - Listino]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : t')
    || TO_CLOB('rue
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" ')
    || TO_CLOB(': 150,
        "hide" : false,
        "rowGroup" : false,
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "TIPO_CONTR_SP" : {
          "type" : [ "TM1DataColumnText" ],
          "columnGroupShow" : "always"
        },
        "TIPO_EXTRA_CONTR_SP" : {
          "type" : [ "TM1DataColumnText" ],
          "columnGroupShow" : "always"
        },
        ')
    || TO_CLOB('"DATA_CAMBIO" : {
          "type" : [ "TM1DataColumnText" ],
          "columnGroupShow" : "always"
        },
        "TIPO_CONTR_SP_FUT" : {
          "type" : [ "TM1DataColumnText" ],
          "columnGroupShow" : "always"
        },
        "TIPO_EXTRA_CONTR_SP_FUT" : {
          "type" : [ "TM1DataColumnText" ],
          "columnGroupShow" : "always"
        }
      }
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Macro Spazio",
      "')
    || TO_CLOB('field" : "MacroSpazio.MUI_Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ]
  }, {
    "name" : "gc_Spazi_MacroSpazi_Aggiorna",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Aggiorna Macrospazi",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "MacroSpazio" : "{{TM1SORT( {TM1FILTERB')
    || TO_CLOB('YLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura_Macrospazi" : "{[Misura_Macrospazi].[(I) Macro Spazi Agg.]}"
        }
      },
      "FROM" : "[Configurazione Macrospazio]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "Ha')
    || TO_CLOB('sPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : ')
    || TO_CLOB('true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "GG_COM_NEW" : {
          "type" : [ "TM1DataColumnText" ],
          "co')
    || TO_CLOB('lumnGroupShow" : "always"
        },
        "OPERAZIONE" : {
          "type" : [ "TM1DataColumnText" ],
          "columnGroupShow" : "always"
        },
        "GRP_SPZ" : {
          "type" : [ "TM1DataColumnText" ],
          "columnGroupShow" : "always"
        }
      }
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Macro Spazio",
      "field" : "MacroSpazio.MUI_Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGrou')
    || TO_CLOB('p" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "actions" : [ {
      "componentId" : "pia_ms_crea",
      "componentLabel" : "Creazione Macrospazio",
      "process" : "MUI_DUMMY_DIM.MacroSpazioInsertElement",
      "timeout" : -1,
      "refresh" : [ "gc_Spazi_MacroSpazi", "gc_Spazi_MacroSpazi_Listino", "gc_Spazi_MacroSpazi_Aggiorna" ],
      "params" : [ {
        "paramName" : "inCodice",
        "label" : "Codice Macrospazi",
        "hasPickl')
    || TO_CLOB('ist" : false
      }, {
        "paramName" : "inDescrizione",
        "label" : "Descrizione Macrospazio",
        "hasPicklist" : false
      }, {
        "paramName" : "inDescTimone",
        "label" : "Descrizione Timone",
        "hasPicklist" : false
      }, {
        "paramName" : "inGruppo",
        "label" : "Gruppo Macrospazi",
        "defaultValue" : "Spazi Campagna",
        "hasPicklist" : false
      } ]
    }, {
      "componentId" : "pia_ms_aggiorna",
      "co')
    || TO_CLOB('mponentLabel" : "Aggiorna Macrospazio",
      "process" : "MUI_DUMMY_CUB.ConfigurazioneMacrospazi_Aggiornamento",
      "timeout" : -1,
      "refresh" : [ "gc_Spazi_MacroSpazi", "gc_Spazi_MacroSpazi_Listino", "gc_Spazi_MacroSpazi_Aggiorna" ],
      "params" : [ {
        "label" : "Aggiorna:",
        "defaultValue" : "Sei Sicuro Di Voler Aggiornare?",
        "hasPicklist" : false,
        "disabled" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('38','/Piano_Annuale/Spazi/Spazi_Condivisi',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_Spazi_SpaziCondivisi_spazio",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "suppressRowClickSelection" : false,
    "maxCells" : 2000000,
    "title" : "Spazio",
    "height" : 22,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Spazio" : "{{ORDER( {TM1FILTERBYLEVEL( ')
    || TO_CLOB('{TM1SUBSETALL( [Spazio] )}, 0)},     [Spazio].[MUI_Sort]  ,  ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "}ElementAttributes_Spazio" : "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Spazio] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[}ElementAttributes_Spazio]",
      "WHERE" : { }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "UniqueName", "Attributes/MacroSpazioDescrizione",')
    || TO_CLOB(' "Attributes/Descrizione", "Attributes/MUI_Compratore", "Attributes/MUI_Reparto" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Reparto",
      "field" : "Spazio.MUI_Reparto",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" :')
    || TO_CLOB(' "Spazio.MUI_Compratore",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Macro Spazio",
      "field" : "Spazio.MacroSpazioDescrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Spazio",
      "field" : "Spazio.Descrizione",
      "width" : 200,
      "h')
    || TO_CLOB('ide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "selections" : [ {
      "source" : {
        "table" : "gc_Spazi_SpaziCondivisi_spazio",
        "dimension" : "Spazio",
        "attribute" : "Descrizione"
      },
      "destination" : [ {
        "table" : "gc_Spazi_SpaziCondivisi_spazio",
        "dimension" : "Spazio",
        "attribute" : "Descrizione"
      } ]
    } ]
  }, {
    "name" : "gc_Spazi_SpaziCondi')
    || TO_CLOB('visi_compratore",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "suppressRowClickSelection" : false,
    "maxCells" : 2000000,
    "title" : "Compratore",
    "height" : 22,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , { {[Compratore].[S000]')
    || TO_CLOB(' }, {[Compratore].[NA] }})} , [Compratore].[MUI_Sort] , BASC )}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "}ElementAttributes_Compratore" : "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Compratore] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[}ElementAttributes_Compratore]",
      "WHERE" : { }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "UniqueName", "Attributes/CategoryManager"')
    || TO_CLOB(', "Attributes/MUI_Reparto", "Attributes/MUI_Descrizione" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Category Manager",
      "field" : "Compratore.CategoryManager",
      "width" : 150,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Comprato')
    || TO_CLOB('re.MUI_Reparto",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "selections" : [ {
      "source" : {
        "table" : "gc_Spazi_SpaziCondivisi_compratore",
        "dimen')
    || TO_CLOB('sion" : "Compratore",
        "attribute" : "MUI_Descrizione"
      },
      "destination" : [ {
        "table" : "gc_Spazi_SpaziCondivisi_compratore",
        "dimension" : "Compratore",
        "attribute" : "MUI_Descrizione"
      } ]
    } ]
  }, {
    "name" : "gc_Spazi_SpaziCondivisi_associazione",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "maxCells" : 2000000,
    "title"')
    || TO_CLOB(' : "Condivisione Spazi",
    "height" : 28,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Spazio" : "{{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 0)},     [Spazio].[MUI_Sort]  ,  ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BD')
    || TO_CLOB('ESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "{{ [Compratore].[TOT_COMP], [Compratore].[S22]},{ EXCEPT( {TM1SUBSETALL( [Compratore] )}, { [Compratore].[NA], [Compratore].[S000], [Compratore].[TOT_COMP], [Compratore].[S22]}) }}"
        }
      },
      "FROM" : "[Configurazione RaggrSpazi]",
      "WHERE" : {
        "Misura Config RaggrFoto" : "[OK]"
      }
    },
    "ExecuteMDX" : {
      ')
    || TO_CLOB('"Members" : [ "Name", "UniqueName", "Attributes/Canale", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/Descrizione", "Attributes/Compratore", "Attributes/MacroSpazioDescrizione", "Attributes/MUI_TOT", "Attributes/MUI_DescrizioneData" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione" ],
      "headerdefaults" : {
 ')
    || TO_CLOB('       "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefau')
    || TO_CLOB('lts" : {
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : { }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "cellClass" : "cellClass-left-text",
      "field" : "Spazio.Descrizione",
      "headerNa')
    || TO_CLOB('me" : "Spazio",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Totale",
      "field" : "Spazio.MUI_TOT",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Spazio.Compratore",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : ')
    || TO_CLOB('false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Macro Spazio",
      "field" : "Spazio.MacroSpazioDescrizione",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColum')
    || TO_CLOB('nName" : "MUI_Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_Spazi_SpaziCondivisi_associazione" ]
    } ],
    "actions" : [ {
      "componentId" : "pia_sc_condividi",
      "componentLabel" : "Condividi",
      "process" : "MUI_DUMMY_CUB.ConfigurazioneRaggrSpazi",
      "timeout" : -1,
      "refresh" : [ "gc_Spazi_SpaziCondivisi_associazione" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
')
    || TO_CLOB('        "paramName" : "inPromo",
        "label" : "Promozione",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : true
      }, {
        "dimension" : "Spazio",
        "attribute" : "Descrizione",
        "paramName" : "inSpazio",
        "label" : "Spazio",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : true
      }, {
        "dimension" : "Compratore",
        "attribute" : "MUI_Descrizione",
        "paramName" : "')
    || TO_CLOB('inCompratore",
        "label" : "Compratore",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('39','/Reporting/Copia_in_Pianificazione',TO_CLOB('{
  "connection" : "reporting",
  "show-version" : true,
  "configurations" : [ {
    "name" : "gc_CopiaInPianificazione",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "title" : "Copia in Pianificazione",
    "compulsoryFilters" : [ "REP - Promozione" ],
    "compulsoryFiltersMessage" : "Per utilizzare questo form è necessario applicare almeno un filtro sulla dimensione Promozione",
    "height" : 60,
    "css" : ".headerCons{backgrou')
    || TO_CLOB('nd-color: #defefe!important;}",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Scenario" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)} , ASC)}",
          "REP - Compratore" : "{ [REP - Compratore].[Compratori]}",
          "REP - Promozione" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          "REP - Fornitore" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - ')
    || TO_CLOB('Fornitore] )}, 0)} , ASC)}",
          "REP - Articolo" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}",
          "REP - Sezione Tematica" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
          "REP - Meccanica Semplice" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
          "REP - AVolantino" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0')
    || TO_CLOB(')} , ASC)}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "REP - Misura Reporting Articolo" : "{[REP - Misura Reporting Articolo].[Misura Reporting Articolo - Reporting]}"
        }
      },
      "FROM" : "[Reporting Articolo]",
      "WHERE" : {
        "REP - Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/Riferimento", "A')
    || TO_CLOB('ttributes/DescrizioneArticolo", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openBy')
    || TO_CLOB('Default" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        },
        "BDG" : {
          "headerClass" : "headerBdg",
          "openByDefault" : true
        },
        "CONS" : {
          "headerClass" : "headerCons",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
  ')
    || TO_CLOB('      "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "MISURA" : {
          "type" : [ "TM1DataColumnText" ],
          "cellClass" : "cellClass-left-text"
        },
        "MARGINE_LORDO_%_P" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cel')
    || TO_CLOB('lClass" : "cellClass-right-text"
        },
        "PRZ_PROMO" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "DELTA_%_SC" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cellClass" : "cellClass-right-text"
        },
        "%_SC" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cellClass" : "cellClass-right-text"
        },
        "Z1" : {
          "type" : [ "TM1DataColumnT')
    || TO_CLOB('ext" ],
          "cellClass" : "cellClass-left-text"
        },
        "Z2" : {
          "type" : [ "TM1DataColumnText" ],
          "cellClass" : "cellClass-left-text"
        },
        "Z3" : {
          "type" : [ "TM1DataColumnText" ],
          "cellClass" : "cellClass-left-text"
        },
        "Z4" : {
          "type" : [ "TM1DataColumnText" ],
          "cellClass" : "cellClass-left-text"
        },
        "Z5" : {
          "type" : [ "TM1DataColumnText" ],
   ')
    || TO_CLOB('       "cellClass" : "cellClass-left-text"
        },
        "Z6" : {
          "type" : [ "TM1DataColumnText" ],
          "cellClass" : "cellClass-left-text"
        },
        "Z7" : {
          "type" : [ "TM1DataColumnText" ],
          "cellClass" : "cellClass-left-text"
        },
        "Z8" : {
          "type" : [ "TM1DataColumnText" ],
          "cellClass" : "cellClass-left-text"
        },
        "AGGR_FOTO" : {
          "type" : [ "TM1DataColumnText" ],
        ')
    || TO_CLOB('  "cellClass" : "cellClass-left-text"
        },
        "ESP" : {
          "type" : [ "TM1DataColumnText" ],
          "cellClass" : "cellClass-left-text"
        },
        "ESP_DIM" : {
          "type" : [ "TM1DataColumnText" ],
          "cellClass" : "cellClass-left-text"
        },
        "SPAZIO" : {
          "type" : [ "TM1DataColumnText" ],
          "cellClass" : "cellClass-left-text"
        },
        "SPAZIO_ESTESO" : {
          "type" : [ "TM1DataColumnText" ],
')
    || TO_CLOB('
          "cellClass" : "cellClass-left-text"
        }
      }
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Scenario",
      "field" : "REP_minus_Scenario.Descrizione",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "REP_minus_Compratore.Descrizione",
      "width" : 150,
      "hide" : true,
      "')
    || TO_CLOB('rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Promozione",
      "field" : "REP_minus_Promozione.MUI_Descrizione",
      "width" : 350,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "REP_minus_Promozione.Riferimento",
      "width" : 350,
      "hide" : false,
      "rowGroup" : false,
      "editab')
    || TO_CLOB('le" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitori",
      "field" : "REP_minus_Fornitore.Descrizione",
      "width" : 350,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Articolo",
      "field" : "REP_minus_Articolo.DescrizioneArticolo",
      "width" : 350,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1El')
    || TO_CLOB('ement" ]
    }, {
      "headerName" : "Meccanica",
      "field" : "REP_minus_MeccanicaSemplice.Descrizione",
      "width" : 150,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Sezione Tematica",
      "field" : "REP_minus_SezioneTematica.Descrizione",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
  ')
    || TO_CLOB('    "headerName" : "A Volantino",
      "field" : "REP_minus_AVolantino.Descrizione",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "actions" : [ {
      "componentId" : "rep_cpi_copia",
      "componentLabel" : "Copia pezzi e colli",
      "process" : "MUI_DUMMY_EXP.CUB.Reporting Articolo - FlagCopia",
      "timeou')
    || TO_CLOB('t" : -1,
      "refresh" : [ "gc_CopiaInPianificazione" ],
      "params" : [ {
        "dimension" : "REP - Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "pPromozione",
        "label" : "Promozione",
        "hasPicklist" : true,
        "disabled" : false,
        "visible" : true
      }, {
        "dimension" : "REP - Compratore",
        "attribute" : "Descrizione",
        "paramName" : "pCompratore",
        "label" : "Compratore",
        "ha')
    || TO_CLOB('sPicklist" : true,
        "disabled" : false,
        "visible" : true
      } ]
    }, {
      "componentId" : "rep_cpi_copest",
      "componentLabel" : "Copia estesa",
      "process" : "MUI_DUMMY_EXP.CUB.Reporting Articolo - FlagCopiaEstesa",
      "timeout" : -1,
      "refresh" : [ "gc_CopiaInPianificazione" ],
      "params" : [ {
        "dimension" : "REP - Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "pPromozione",
        "label" : "Promoz')
    || TO_CLOB('ione",
        "hasPicklist" : true,
        "disabled" : false,
        "visible" : true
      }, {
        "dimension" : "REP - Compratore",
        "attribute" : "Descrizione",
        "paramName" : "pCompratore",
        "label" : "Compratore",
        "hasPicklist" : true,
        "disabled" : false,
        "visible" : true
      } ]
    } ],
    "preSelections" : [ {
      "dimension" : "REP - Compratore",
      "dimCode" : "rep_compratore",
      "dimColumnName" : "REP -')
    || TO_CLOB(' Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_CopiaInPianificazione" ]
    }, {
      "dimension" : "REP - Scenario",
      "dimCode" : "rep_scenario",
      "dimColumnName" : "REP - Scenario",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramN')
    || TO_CLOB('ame" : "",
      "refresh" : [ "gc_CopiaInPianificazione" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('40','/Reporting/Dettaglio_Campagna',TO_CLOB('{
  "connection" : "reporting",
  "show-version" : true,
  "configurations" : [ {
    "name" : "gc_DettaglioCampagna",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "__forceSuppression" : true,
    "maxCells" : 1000000,
    "compulsoryFilters" : [ "REP - Promozione" ],
    "compulsoryFiltersMessage" : "Per utilizzare questo form è necessario applicare almeno un filtro sulla dimensione Promozione",
    "title" : "Dettaglio Campagna",
    "height" : 60,
    "cs')
    || TO_CLOB('s" : ".headerCons{background-color: #defefe!important;}",
    "REP - Fornitore" : "{[REP - Fornitore].[Fornitori]}",
    "REP - Compratore" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
    "REP - Zona Promo" : "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
    "REP - Sezione Tematica" : "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
    "REP - Meccanica Semplice" : "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
    "REP - AVolantin')
    || TO_CLOB('o" : "{[REP - AVolantino].[AVolantino -BDGVend]}",
    "REP - Articolo" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Compratore" : "[REP - Compratore].[Compratori]",
          "REP - Fornitore" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}",
          "REP - Articolo" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( ')
    || TO_CLOB('[REP - Articolo] )}, 0)} , ASC)}",
          "REP - Sezione Tematica" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
          "REP - Meccanica Semplice" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
          "REP - AVolantino" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {')
    || TO_CLOB('
          "REP - Promozione" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          "REP - Scenario" : "{[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[BDG],[REP - Scenario].[CONS]}",
          "REP - Misura Reporting Articolo" : "{ [REP - Misura Reporting Articolo].[Reporting ACQ]  } "
        }
      },
      "FROM" : "[Reporting Articolo]",
      "WHERE" : {
        "REP - Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Memb')
    || TO_CLOB('ers" : [ "Name", "Attributes/MUI_DescrizioneData", "Attributes/Descrizione", "Attributes/DescrizioneArticolo", "Attributes/MUI_Category", "Attributes/MUI_Reparto", "Attributes/MUI_TOT", "Attributes/RepartoDesc", "Attributes/CategoriaDesc", "Attributes/GRMDesc", "Attributes/MUI_SubGrm", "Attributes/MUI_DescrizioneData", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
     ')
    || TO_CLOB(' "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "h')
    || TO_CLOB('eaderClass" : "headerRep",
          "openByDefault" : true
        },
        "BDG" : {
          "headerClass" : "headerBdg",
          "openByDefault" : true
        },
        "CONS" : {
          "headerClass" : "headerCons",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
  ')
    || TO_CLOB('      "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "MARGINE_LORDO_%_P" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
        },
        "DELTA_%_SC" : {
          "type" : [ "TM1DataColumnIntegerPercentage", "numericColumn" ]
        }
      }
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Articolo",
      "field" : "REP_minus_Articolo.DescrizioneArticolo",
      "')
    || TO_CLOB('width" : 300,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "REP_minus_Articolo.MUI_Reparto",
      "width" : 80,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Categoria",
      "field" : "REP_minus_Articolo.CategoriaDesc",
      "width" : 100,
      "hide" : false,
      "')
    || TO_CLOB('rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Grm",
      "field" : "REP_minus_Articolo.GRMDesc",
      "width" : 130,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Sub Grm",
      "field" : "REP_minus_Articolo.MUI_SubGrm",
      "width" : 130,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "typ')
    || TO_CLOB('e" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitori",
      "field" : "REP_minus_Fornitore.Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Category",
      "field" : "REP_minus_Compratore.MUI_Category",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "he')
    || TO_CLOB('aderName" : "Compratore",
      "field" : "REP_minus_Compratore.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Sezione Tematica",
      "field" : "REP_minus_SezioneTematica.Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Meccanica Semplic')
    || TO_CLOB('e",
      "field" : "REP_minus_MeccanicaSemplice.Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "A Volantino",
      "field" : "REP_minus_AVolantino.Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowSuppressionEnabled" : false,
    "colSuppressionEnable')
    || TO_CLOB('d" : false,
    "preSelections" : [ {
      "dimension" : "REP - Compratore",
      "dimCode" : "rep_compratore",
      "dimColumnName" : "REP - Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_DettaglioCampagna" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('41','/Reporting/Dettaglio_Zona',TO_CLOB('{
  "connection" : "reporting",
  "show-version" : true,
  "configurations" : [ {
    "name" : "gc_DettaglioZona",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "compulsoryFilters" : [ "REP - Promozione" ],
    "compulsoryFiltersMessage" : "Per utilizzare questo form è necessario applicare almeno un filtro sulla dimensione Promozione",
    "title" : "Dettaglio Zona",
    "height" : 60,
    "css" : ".headerCons{background-color: #defef')
    || TO_CLOB('e!important;}",
    "REP - Fornitore" : "{[REP - Fornitore].[Fornitori]}",
    "REP - Compratore" : "{[REP - Compratore].[Compratori]}",
    "REP - Zona Promo" : "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
    "REP - Sezione Tematica" : "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
    "REP - Meccanica Semplice" : "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
    "REP - AVolantino" : "{[REP - AVolantino].[AVolantino -BDGVend]}",
    "REP - Articolo" : "{TM1SORT(  {')
    || TO_CLOB('TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Fornitore" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}",
          "REP - Compratore" : "{[REP - Compratore].[Compratori]}",
          "REP - Sezione Tematica" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
          "REP - Meccanica Semplic')
    || TO_CLOB('e" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
          "REP - AVolantino" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}",
          "REP - Articolo" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Promozione" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - ')
    || TO_CLOB('Promozione] )}, 0)} , ASC)}",
          "REP - Scenario" : "{[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[BDG],[REP - Scenario].[CONS]}",
          "REP - Zona Promo" : "{{[REP - Zona Promo].[TOT_ZONA_PROMO],[REP - Zona Promo].[TOT_ZONE] },{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}}",
          "REP - Misura Reporting Articolo Zona" : "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -BDGVend]}"
        }
      },
      "FROM" ')
    || TO_CLOB(': "[Reporting Articolo Zona]",
      "WHERE" : {
        "REP - Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_DescrizioneData", "Attributes/Descrizione", "Attributes/DescrizioneArticolo", "Attributes/MUI_Category", "Attributes/MUI_Reparto", "Attributes/MUI_TOT", "Attributes/RepartoDesc", "Attributes/CategoriaDesc", "Attributes/GRMDesc", "Attributes/MUI_SubGrm", "Attributes/MUI_DescrizioneData", "UniqueName" ],
      "Cells" : [ "Ordina')
    || TO_CLOB('l", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
    ')
    || TO_CLOB('    },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        },
        "BDG" : {
          "headerClass" : "headerBdg",
          "openByDefault" : true
        },
        "CONS" : {
          "headerClass" : "headerCons",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
')
    || TO_CLOB('
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "MARGINE_LORDO_%_P" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
        },
        "DELTA_%_SC" : {
          "type" : [ "TM1DataColumnIntegerPercentage", "numericColumn" ]
        }
      }
    },
    "')
    || TO_CLOB('autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "REP_minus_Articolo.DescrizioneArticolo",
      "headerName" : "Articolo",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Articolo",
      "field" : "REP_minus_Articolo.DescrizioneArticolo",
      "width" : 300,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
   ')
    || TO_CLOB('   "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "REP_minus_Articolo.MUI_Reparto",
      "width" : 80,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Categoria",
      "field" : "REP_minus_Articolo.CategoriaDesc",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
     ')
    || TO_CLOB(' "headerName" : "Grm",
      "field" : "REP_minus_Articolo.GRMDesc",
      "width" : 130,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Sub Grm",
      "field" : "REP_minus_Articolo.MUI_SubGrm",
      "width" : 130,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitori",
      "field" : "REP_minus_')
    || TO_CLOB('Fornitore.Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Category",
      "field" : "REP_minus_Compratore.MUI_Category",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "REP_minus_Compratore.Descrizione",
      "width" :')
    || TO_CLOB(' 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Sezione Tematica",
      "field" : "REP_minus_SezioneTematica.Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Meccanica Semplice",
      "field" : "REP_minus_MeccanicaSemplice.Descrizione",
      "width" : 70,
      "hi')
    || TO_CLOB('de" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "A Volantino",
      "field" : "REP_minus_AVolantino.Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "preSelections" : [ {
      "dimension" : "REP - Scenario",
      "dimCode" ')
    || TO_CLOB(': "rep_scenario",
      "dimColumnName" : "REP - Scenario",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_DettaglioZona" ]
    }, {
      "dimension" : "REP - Compratore",
      "dimCode" : "rep_compratore",
      "dimColumnName" : "REP - Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descri')
    || TO_CLOB('zione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_DettaglioZona" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('42','/Reporting/Sintesi_Campagna',TO_CLOB('{
  "connection" : "reporting",
  "show-version" : true,
  "configurations" : [ {
    "name" : "gc_SintesiCampagna",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "compulsoryFilters" : [ "REP - Promozione" ],
    "compulsoryFiltersMessage" : "Per utilizzare questo form è necessario applicare almeno un filtro sulla dimensione Promozione",
    "title" : "Sintesi Campagna",
    "forceSuppression" : true,
    "css" : ".headerCons{background-color: #defefe!important')
    || TO_CLOB(';}",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Compratore" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
          "REP - Categoria" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)} , ASC)}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Promozione" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM')
    || TO_CLOB('1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          "REP - Scenario" : "{[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[TGT_ACQ],[REP - Scenario].[BDG],[REP - Scenario].[CONS] }",
          "REP - Misura Timone" : "{ [REP - Misura Timone].[MUI_SUB_Analisi_Budget_Venduto_Categoria] }"
        }
      },
      "FROM" : "[Timone Reporting]",
      "WHERE" : {
        "REP - Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_Descriz')
    || TO_CLOB('ioneData", "Attributes/Descrizione", "Attributes/MUI_Category", "Attributes/MUI_Total", "Attributes/MUI_Direzione", "Attributes/MUI_Reparto", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
    ')
    || TO_CLOB('    "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        },
        "BDG" : {
          "headerClass" : "headerBdg",
          "openByDefault" : true
        },
')
    || TO_CLOB('        "CONS" : {
          "headerClass" : "headerCons",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "D_VEND/RIF_%" : {
          "type" : [ "TM1DataColumnPercentage", ')
    || TO_CLOB('"numericColumn" ]
        }
      }
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Direzione",
      "field" : "REP_minus_Categoria.MUI_Direzione",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "REP_minus_Categoria.MUI_Reparto",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "e')
    || TO_CLOB('ditable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Categoria",
      "field" : "REP_minus_Categoria.Descrizione",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Category",
      "field" : "REP_minus_Compratore.MUI_Category",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1E')
    || TO_CLOB('lement" ]
    }, {
      "headerName" : "Compratore",
      "field" : "REP_minus_Compratore.Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('43','/Reporting/Storico_articolo_per_zona_(ACQ)',TO_CLOB('{
  "connection" : "reporting",
  "show-version" : true,
  "configurations" : [ {
    "name" : "gc_StoricoArticoloPerZonaAcq",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "compulsoryFilters" : [ "REP - Promozione" ],
    "compulsoryFiltersMessage" : "Per utilizzare questo form è necessario applicare almeno un filtro sulla dimensione Promozione",
    "title" : "Storico articolo per zona (ACQ)",
    "css" : ".headerCons{background-colo')
    || TO_CLOB('r: #defefe!important;}",
    "height" : 60,
    "REP - Fornitore" : "{[REP - Fornitore].[Fornitori]}",
    "REP - Compratore" : "{[REP - Compratore].[Compratori]}",
    "REP - Zona Promo" : "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
    "REP - Sezione Tematica" : "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
    "REP - Meccanica Semplice" : "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
    "REP - AVolantino" : "{[REP - AVolantino].[AVolantino -BDGVend]}",
    "RE')
    || TO_CLOB('P - Articolo" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Promozione" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          "REP - Fornitore" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}",
          "REP - Compratore" : "{[REP - Compratore].[Compratori]}",
          "REP - ')
    || TO_CLOB('Sezione Tematica" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
          "REP - Meccanica Semplice" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
          "REP - AVolantino" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}",
          "REP - Articolo" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
        }
      },
      "COLS" ')
    || TO_CLOB(': {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "REP - Scenario" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)} , ASC)}",
          "REP - Zona Promo" : "{{[REP - Zona Promo].[TOT_ZONA_PROMO],[REP - Zona Promo].[TOT_ZONE] },{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}}",
          "REP - Misura Reporting Articolo Zona" : "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -Storico]}"
     ')
    || TO_CLOB('   }
      },
      "FROM" : "[Reporting Articolo Zona]",
      "WHERE" : {
        "REP - Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Descrizione", "Attributes/Riferimento", "Attributes/DescrizioneArticolo", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Des')
    || TO_CLOB('crizione", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
  ')
    || TO_CLOB('        "openByDefault" : true
        },
        "BDG" : {
          "headerClass" : "headerBdg",
          "openByDefault" : true
        },
        "CONS" : {
          "headerClass" : "headerCons",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColum')
    || TO_CLOB('nInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "PRZ_ATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_PROMO" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "DELTA_%_SC" : {
          "type" : [ "TM1DataColumnIntegerPercentage" ],
          "cellClass" : "cellClass-right-text"
        }
      }
    },
    "')
    || TO_CLOB('autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Compratore",
      "field" : "REP_minus_Compratore.Descrizione",
      "width" : 150,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Promozione",
      "field" : "REP_minus_Promozione.MUI_Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Eleme')
    || TO_CLOB('nt" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "REP_minus_Promozione.Riferimento",
      "width" : 350,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitori",
      "field" : "REP_minus_Fornitore.Descrizione",
      "width" : 350,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "')
    || TO_CLOB('Articolo",
      "field" : "REP_minus_Articolo.DescrizioneArticolo",
      "width" : 350,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Meccanica",
      "field" : "REP_minus_MeccanicaSemplice.Descrizione",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Sezione Tematica",
      "f')
    || TO_CLOB('ield" : "REP_minus_SezioneTematica.Descrizione",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "A Volantino",
      "field" : "REP_minus_AVolantino.Descrizione",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
')
    || TO_CLOB('    "preSelections" : [ {
      "dimension" : "REP - Scenario",
      "dimCode" : "rep_scenario",
      "dimColumnName" : "REP - Scenario",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_StoricoArticoloPerZonaAcq" ]
    }, {
      "dimension" : "REP - Compratore",
      "dimCode" : "rep_compratore",
      "dimColumnName" : "REP - Compratore",
      "at')
    || TO_CLOB('tribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_StoricoArticoloPerZonaAcq" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('44','/Reporting/Storico_articolo_per_zona_(MKT)',TO_CLOB('{
  "connection" : "reporting",
  "show-version" : true,
  "configurations" : [ {
    "name" : "gc_StoricoArticoloPerZonaAcqMkt",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "compulsoryFilters" : [ "REP - Promozione" ],
    "compulsoryFiltersMessage" : "Per utilizzare questo form è necessario applicare almeno un filtro sulla dimensione Promozione",
    "title" : "Storico articolo per zona (MKT)",
    "css" : ".headerCons{background-c')
    || TO_CLOB('olor: #defefe!important;}",
    "height" : 60,
    "REP - Fornitore" : "{[REP - Fornitore].[Fornitori]}",
    "REP - Compratore" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
    "REP - Zona Promo" : "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
    "REP - Sezione Tematica" : "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
    "REP - Meccanica Semplice" : "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
    "REP - AVolantino" : "{[RE')
    || TO_CLOB('P - AVolantino].[AVolantino -BDGVend]}",
    "REP - Articolo" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Promozione" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          "REP - Fornitore" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}",
          "REP - Compratore" : "[REP ')
    || TO_CLOB('- Compratore].[Compratori]",
          "REP - Sezione Tematica" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
          "REP - Meccanica Semplice" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
          "REP - AVolantino" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}",
          "REP - Articolo" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0')
    || TO_CLOB(')} , ASC)}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "REP - Scenario" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)} , ASC)}",
          "REP - Zona Promo" : "{{[REP - Zona Promo].[TOT_ZONE] } , { EXCEPT( {TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}, { [REP - Zona Promo].[ZONA_PROMO_ALL] }) } }",
          "REP - Misura Reporting Articolo Zona" : "{[REP - Misura Reporti')
    || TO_CLOB('ng Articolo Zona].[Misura Reporting Articolo Zona -Storico]}"
        }
      },
      "FROM" : "[Reporting Articolo Zona]",
      "WHERE" : {
        "REP - Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Descrizione", "Attributes/Riferimento", "Attributes/DescrizioneArticolo", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : ')
    || TO_CLOB('true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        }')
    || TO_CLOB(',
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        },
        "BDG" : {
          "headerClass" : "headerBdg",
          "openByDefault" : true
        },
        "CONS" : {
          "headerClass" : "headerCons",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "al')
    || TO_CLOB('ways",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "PRZ_ATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_PROMO" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "DELTA_%_SC" : {
          "type" : [ "TM1DataColumnIntegerPercentage" ],
          "ce')
    || TO_CLOB('llClass" : "cellClass-right-text"
        }
      }
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Compratore",
      "field" : "REP_minus_Compratore.Descrizione",
      "width" : 150,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Promozione",
      "field" : "REP_minus_Promozione.MUI_Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGr')
    || TO_CLOB('oup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "REP_minus_Promozione.Riferimento",
      "width" : 350,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitori",
      "field" : "REP_minus_Fornitore.Descrizione",
      "width" : 350,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false')
    || TO_CLOB(',
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Articolo",
      "field" : "REP_minus_Articolo.DescrizioneArticolo",
      "width" : 350,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Meccanica",
      "field" : "REP_minus_MeccanicaSemplice.Descrizione",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Elem')
    || TO_CLOB('ent" ]
    }, {
      "headerName" : "Sezione Tematica",
      "field" : "REP_minus_SezioneTematica.Descrizione",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "A Volantino",
      "field" : "REP_minus_AVolantino.Descrizione",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "row')
    || TO_CLOB('SuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "preSelections" : [ {
      "dimension" : "REP - Scenario",
      "dimCode" : "rep_scenario",
      "dimColumnName" : "REP - Scenario",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_StoricoArticoloPerZonaAcqMkt" ]
    }, {
      "dimension" : "REP - Compratore",
      "dimCode" : "r')
    || TO_CLOB('ep_compratore",
      "dimColumnName" : "REP - Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_StoricoArticoloPerZonaAcqMkt" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('45','/Reporting/Timone_Reparto',TO_CLOB('{
  "connection" : "reporting",
  "show-version" : true,
  "configurations" : [ {
    "name" : "gc_TimoneReparto",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "compulsoryFilters" : [ "REP - Promozione" ],
    "compulsoryFiltersMessage" : "Per utilizzare questo form è necessario applicare almeno un filtro sulla dimensione Promozione",
    "title" : "Timone Reparto",
    "css" : ".headerCons{background-color: #defefe!important;}",
   ')
    || TO_CLOB(' "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Promozione" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          "REP - Reparto" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Reparto] )}, 0)} , ASC)}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Scenario" : "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BD')
    || TO_CLOB('G],[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[TGT_ACQ]}",
          "REP - Misura Timone" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Timone] )}, 0)} , ASC)}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "REP - Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Anno", "Attributes/Descrizione", "Attributes/Canale", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "C')
    || TO_CLOB('onsolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt')
    || TO_CLOB('",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        },
        "CONS" : {
          "headerClass" : "headerCons",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM')
    || TO_CLOB('1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : { }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "REP_minus_Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Anno",
      "field" : "REP_minus_Promozione.Anno",
      "width" : 70,
      "hid')
    || TO_CLOB('e" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Canale",
      "field" : "REP_minus_Promozione.Canale",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Promozione",
      "field" : "REP_minus_Promozione.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable')
    || TO_CLOB('" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "REP_minus_Reparto.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('48','/Timone/Foto_Speciali/Riepilogo',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_FotoSpeciali_Riepilogo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Foto Speciali Riepilogo",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Categoria" : "{{[Categoria].[CAT_0000]},{[Categoria].[MUI_TargetCategoria]}  ,{EXCEPT( {{ {TM1FI')
    || TO_CLOB('LTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}}} , {[Categoria].[CAT_0000]}) }}",
          "Compratore" : "{{[Compratore].[S000]},{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]} )}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} ,')
    || TO_CLOB(' [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]}}",
          "Sezione Tematica" : "{TM1SUBSETALL( [Sezione Tematica] )}",
          "Misura Timone." : "{{[Misura Timone.].[N_FOTO_TOT]},{[Misura Timone.].[N_FOTO_SCAFFALE_TOT]}}"
        }
      },
      "FROM" : "[Timone Categoria Sezione]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
  ')
    || TO_CLOB('  "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/MUI_TOT", "Attributes/MUI_RepartoDesc", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.Descrizione",
  ')
    || TO_CLOB('    "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" ')
    || TO_CLOB(': "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        ')
    || TO_CLOB('"type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnNumberK", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO__perc_" : {
          "type" : [ "TM1DataColumnNumber", "numericColumn" ],
          "columnGroupShow" : "always"
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "Totale Categoria",
      "field" : "')
    || TO_CLOB('Categoria.MUI_TOT",
      "width" : 90,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Categoria.MUI_RepartoDesc",
      "width" : 90,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Categoria",
      "field" : "Categoria.Descrizi')
    || TO_CLOB('one",
      "width" : 90,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizio')
    || TO_CLOB('ne",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
  ')
    || TO_CLOB('    "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000')
    || TO_CLOB(''')  || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].')
    || TO_CLOB('includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',')
    || TO_CLOB('''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-1{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('49','/Timone/Foto_Speciali/Target',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_FotoSpeciali_Target",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Foto Speciali Target",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( ')
    || TO_CLOB('{EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{{[Categoria].[CAT_0000]}, {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria]')
    || TO_CLOB('.[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]}}",
          "Sezione Tematica" : "{{TM1S')
    || TO_CLOB('UBSETALL( [Sezione Tematica] )}}",
          "Misura Timone." : "{[Misura Timone.].[(II) Timone Categoria Sez]}"
        }
      },
      "FROM" : "[Timone Categoria Sezione]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/MUI_Descrizione", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells"')
    || TO_CLOB(' : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "MUI_Descrizione", "D')
    || TO_CLOB('escrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : tr')
    || TO_CLOB('ue
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "ASSORT_P" : {
          "hide" : true,
          "type" : [ "TM1DataColumnText" ]
        },
        "ST_0000" : {
          "hide" : true
        }
    ')
    || TO_CLOB('  }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
     ')
    || TO_CLOB(' "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" :')
    || TO_CLOB(' [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  || (data.Compratore.Name === ''S000'' && data.Categoria.Name ==')
    || TO_CLOB('= ''CAT_0000'')  ",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "r')
    || TO_CLOB('ow_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name')
    || TO_CLOB('.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('50','/Timone/Spazi_Campagna/Target_Categoria',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SpaziCampagna_TargetCategoria",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Target Categoria",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},{EXCE')
    || TO_CLOB('PT( {{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}} , {[Compratore].[S000]} )}  }",
          "Categoria" : "{{[Categoria].[MUI_TargetCategoria]}  ,{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[')
    || TO_CLOB('Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Mis')
    || TO_CLOB('ura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')} , { [Misura Timone.].[ASSORT_P]}  }"
        }
      },
      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/MUI_Descrizione", "Attribut')
    || TO_CLOB('es/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "T')
    || TO_CLOB('GT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
 ')
    || TO_CLOB('       "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "N_ART_PROMO" : {
          "columnGroupShow" : "always"
        },
        "TOT_FOTO" : {
          "columnGroupShow" : "always"
        },
        "SPZ_CAMP" : {
          "columnGroupShow" : "always"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {')
    || TO_CLOB('
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "he')
    || TO_CLOB('aderName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" ')
    || TO_CLOB(': "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(da')
    || TO_CLOB('ta.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(d')
    || TO_CLOB('ata.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "(data.Compratore.Name == ''S000'' && (data.Categoria.Name  == ''_REP_01'' || data.Categoria.Name  == ''_REP_02'' || data.')
    || TO_CLOB('Categoria.Name  == ''_REP_03'' || data.Categoria.Name  == ''_REP_04'' || data.Categoria.Name  == ''_REP_05'' || data.Categoria.Name  == ''_REP_06'' || data.Categoria.Name  == ''_REP_09'' || data.Categoria.Name  == ''_REP_11'' || data.Categoria.Name  == ''_REP_12'' || data.Categoria.Name  == ''_REP_14'' || data.Categoria.Name  == ''_REP_50'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
   ')
    || TO_CLOB(' "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('51','/Timone/Spazi_Campagna/Target_Reparto',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SpaziCampagna_TargetReparto",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Target Reparto",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )')
    || TO_CLOB('}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {[Reparto].[REP_01] , [Reparto].[REP_01_01], [Reparto].[REP_01_02], [Reparto].[REP_01_03], [Reparto].[REP_01_04], [Reparto].[REP_09], [Reparto].[REP_12], [Reparto].[REP_12_01], [Reparto].[REP_12_02], [Reparto].[REP_12_04]  },[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
      "COLS" : {
        "NON_E')
    || TO_CLOB('MPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timon')
    || TO_CLOB('e.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/Canale", "Attributes/Riferimento", "Attributes/TipoElemento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
  ')
    || TO_CLOB('    "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDe')
    || TO_CLOB('fault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
 ')
    || TO_CLOB('       "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "N_ART_PROMO" : {
          "columnGroupShow" : "always"
        },
        "TOT_FOTO" : {
          "columnGroupShow" : "always"
        },
        "SPZ_CAMP" : {
          "columnGroupShow" : "always"
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.')
    || TO_CLOB('Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : t')
    || TO_CLOB('rue,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Descrizione",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "')
    || TO_CLOB('type" : [ "TM1Element" ]
    }, {
      "headerName" : "Tipo Elemento",
      "field" : "Reparto.TipoElemento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2"')
    || TO_CLOB(' : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : ')
    || TO_CLOB('[ {
      "componentId" : "tim_scr_inizializza",
      "componentLabel" : "Inizializza Spazio",
      "process" : "MUI_DUMMY_CUB.Timone Reparto Inizializza Spazi",
      "timeout" : -1,
      "refresh" : [ "gc_SpaziCampagna_TargetReparto" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "')
    || TO_CLOB('MUI_Descrizione",
        "paramName" : "inPromo",
        "label" : "Promozione",
        "hasPicklist" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('52','/Timone/Target_Categoria/Data',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetCategoria_Data",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Categoria (data)",
    "height" : 60,
    "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
    "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano pe')
    || TO_CLOB('r _) ",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{    {[Categoria].[CAT_0000]} , {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categori')
    || TO_CLOB('a].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promo')
    || TO_CLOB('zione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misu')
    || TO_CLOB('ra Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]} }"
        }
      },
      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "')
    || TO_CLOB('Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefault')
    || TO_CLOB('s" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "chil')
    || TO_CLOB('drendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "')
    || TO_CLOB('TM1DataColumnPercentage", "numericColumn" ],
          "columnGroupShow" : "always",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
 ')
    || TO_CLOB('     "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type')
    || TO_CLOB('" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "')
    || TO_CLOB('rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''S000'' && data.Categoria.Name == ''CAT_0000'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compra')
    || TO_CLOB('tore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupF')
    || TO_CLOB('alse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('54','/Timone/Target_Categoria/Promo',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetCategoria_Promo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 500000,
    "title" : "Target Categoria",
    "height" : 60,
    "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
    "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i')
    || TO_CLOB(' reparti iniziano per _) ",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{  {[Categoria].[CAT_0000]} , {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''')
    || TO_CLOB('_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Can')
    || TO_CLOB('ale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGIN')
    || TO_CLOB('E_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]}}"
        }
      },
      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells"')
    || TO_CLOB(' : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
   ')
    || TO_CLOB('   "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
    ')
    || TO_CLOB('  },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO_%" : {
     ')
    || TO_CLOB('     "type" : [ "TM1DataColumnPercentage", "numericColumn" ],
          "columnGroupShow" : "always",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "edit')
    || TO_CLOB('able" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : fa')
    || TO_CLOB('lse,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]')
    || TO_CLOB('
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''S000'' && data.Categoria.Name == ''CAT_0000'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  ",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_styl')
    || TO_CLOB('e_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",')
    || TO_CLOB('
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('55','/Timone/Target_Reparto/Data',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetReparto_Data",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Reparto (data)",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC')
    || TO_CLOB('),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
          "Misura Timone." : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[M')
    || TO_CLOB('isura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]},{[Misura Timone.].[MUI_AVG]}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/DataInizioIst", "Attributes/MUI_Descrizione", "Attributes/TipoElemento", "Attributes/Riferimento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Valu')
    || TO_CLOB('e", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable"')
    || TO_CLOB(' : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Data",
      "field" : "Promozione.DataInizioIst",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerN')
    || TO_CLOB('ame" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Desc')
    || TO_CLOB('rizione",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento Data",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art"')
    || TO_CLOB(',
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : fal')
    || TO_CLOB('se,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericCo')
    || TO_CLOB('lumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
')
    || TO_CLOB('        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupSh')
    || TO_CLOB('ow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "V')
    || TO_CLOB('enduto Promo (s/iva)",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "ML %",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
        "width" : 80,
        "hide"')
    || TO_CLOB(' : false,
        "rowGroup" : false,
        "aggFunc" : "customAvg",
        "aggFuncParam" : "$MUI_AVG",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
      }, {
        "headerName" : "FF C",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$CONTR",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
     ')
    || TO_CLOB('   "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "FF EC",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$EXTRA_CONTR",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target MKT",
      "h')
    || TO_CLOB('eaderClass" : "headerMkt",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_')
    || TO_CLOB('MKT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "colu')
    || TO_CLOB('mnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "header')
    || TO_CLOB('Name" : "N. Foto Speciali",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        ')
    || TO_CLOB('"hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
      ')
    || TO_CLOB('  "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target REP",
      "headerClass" : "headerRep",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ ')
    || TO_CLOB('"TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO"')
    || TO_CLOB(',
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open')
    || TO_CLOB('",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec')
    || TO_CLOB('. Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
       ')
    || TO_CLOB(' "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "')
    || TO_CLOB('TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target Acq.",
      "headerClass" : "')
    || TO_CLOB('headerAcq",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_ACQ$$MisuraTimo')
    || TO_CLOB('ne_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : ')
    || TO_CLOB('"open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Fot')
    || TO_CLOB('o Speciali",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,')
    || TO_CLOB('
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "T')
    || TO_CLOB('M1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Rep",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Rep",
        "field" : "Scenario$TGT_RE')
    || TO_CLOB('P$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    } ],
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : [ {
      "componentId" : "tim_trd_inizializza",')
    || TO_CLOB('
      "componentLabel" : "Inizializza Reference",
      "process" : "MUI_DUMMY_CUB.Timone.Inizializza Reference",
      "timeout" : -1,
      "refresh" : [ "gc_TargetReparto_Data" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
      ')
    || TO_CLOB('  "label" : "Promozione",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "Gruppo",
        "paramName" : "inGruppo",
        "label" : "Gruppo",
        "hasPicklist" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('56','/Timone/Target_Reparto/Promo',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetReparto_Promo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "title" : "Target Reparto ",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Prom')
    || TO_CLOB('ozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
          "Misura Timone." : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone')
    || TO_CLOB('.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[MUI_AVG]}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Canale", "Attributes/MUI_Descrizione", "Attributes/Riferimento", "Attributes/TipoElemento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
')
    || TO_CLOB('    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
')
    || TO_CLOB('      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" :')
    || TO_CLOB(' "Reparto.Descrizione",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Tipo Elemento",
      "field" : "Reparto.TipoElemento",
      "width" : 70,
      "hide" ')
    || TO_CLOB(': true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento Data",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : ')
    || TO_CLOB('[ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_')
    || TO_CLOB('$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupSh')
    || TO_CLOB('ow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" :')
    || TO_CLOB(' "N. Foto Spec. Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hid')
    || TO_CLOB('e" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Venduto Promo (s/iva)",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" ')
    || TO_CLOB(': true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "ML %",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "customAvg",
        "aggFuncParam" : "$MUI_AVG",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
      } ]
    }, {
   ')
    || TO_CLOB('   "headerName" : "Target MKT",
      "headerClass" : "headerMkt",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "T')
    || TO_CLOB('otale",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
')
    || TO_CLOB('        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "nume')
    || TO_CLOB('ricColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFAL')
    || TO_CLOB('E_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "o')
    || TO_CLOB('pen",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target REP",
      "headerClass" : "headerRep",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
      ')
    || TO_CLOB('  "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Sc')
    || TO_CLOB('enario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : ')
    || TO_CLOB('"sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }')
    || TO_CLOB(', {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
        "width')
    || TO_CLOB('" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
       ')
    || TO_CLOB(' "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" ')
    || TO_CLOB(': "Target Acq.",
      "headerClass" : "headerAcq",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
      ')
    || TO_CLOB('  "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFun')
    || TO_CLOB('c" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
 ')
    || TO_CLOB('     }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
     ')
    || TO_CLOB('   "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        ')
    || TO_CLOB('"editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt ')
    || TO_CLOB('Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    } ],
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : [ {
   ')
    || TO_CLOB('   "componentId" : "tim_trp_inizializza",
      "componentLabel" : "Inizializza Reference",
      "process" : "MUI_DUMMY_CUB.Timone.Inizializza Reference",
      "timeout" : -1,
      "refresh" : [ "gc_TargetReparto_Promo" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",')
    || TO_CLOB('
        "paramName" : "inPromo",
        "label" : "Promozione",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "Gruppo",
        "paramName" : "inGruppo",
        "label" : "Gruppo",
        "hasPicklist" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('57','/Timone/Associazione_Promo',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_AssociazionePromo_Promozioni",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "suppressRowClickSelection" : false,
    "title" : "Promozioni",
    "height" : 22,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTER')
    || TO_CLOB('BYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "}ElementAttributes_Promozione" : "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Promozione] )}, 0)}, ASC)}"
        }
      },
      "FROM" : "[}ElementAttributes_Promozione]"
  ')
    || TO_CLOB('  },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Name", "Attributes/MUI_Descrizione", "Attributes/Riferimento", "Attributes/TipoPromozione", "Attributes/Canale", "Attributes/Anno", "Attributes/MUI_Semestre", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Promozione",
      "field" : "Promozione.Name",
      "width" : 70,
      "hide" ')
    || TO_CLOB(': false,
      "editable" : false,
      "rowGroup" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : false,
      "editable" : false,
      "rowGroup" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 400,
      "hide" : false,
      "editable" : false,
      "rowGroup')
    || TO_CLOB('" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Gruppo",
      "field" : "Promozione.TipoPromozione",
      "width" : 100,
      "hide" : false,
      "editable" : false,
      "rowGroup" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "header')
    || TO_CLOB('Name" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 50,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 100,
      "hide" : false,
      "editable" : false,
      "rowGroup" : false,
      "type" : [ "TM1Element" ]
    } ],
    "selections" : [ {
      "source" : {
        "table" : "gc_AssociazionePromo_Pro')
    || TO_CLOB('mozioni",
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione"
      },
      "destination" : [ {
        "table" : "gc_AssociazionePromo_Associazioni",
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione"
      }, {
        "table" : "gc_AssociazionePromo_SezioniTematiche",
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione"
      } ]
    }, {
      "source" : {
        "table" : "gc_AssociazionePromo_Promozion')
    || TO_CLOB('i",
        "dimension" : "Promozione",
        "attribute" : "Anno"
      },
      "destination" : [ {
        "table" : "gc_AssociazionePromo_Promozioni",
        "dimension" : "Promozione",
        "attribute" : "Anno"
      } ]
    } ]
  }, {
    "name" : "gc_AssociazionePromo_Riferimento",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "suppressRowClickSelection" : false,
    "t')
    || TO_CLOB('itle" : "Riferimento",
    "height" : 22,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione Riferimento" : "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}, [Promozione Riferimento].[Data_Inizio_Ist.],DESC )}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "}ElementAttributes_Promozione" : "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttribu')
    || TO_CLOB('tes_Promozione Riferimento] )}, 0)}, ASC)}"
        }
      },
      "FROM" : "[}ElementAttributes_Promozione Riferimento]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Name", "Attributes/MUI_Descrizione", "Attributes/Descrizione", "Attributes/MUI_Canale", "Attributes/Anno", "Attributes/Gruppo", "Attributes/Semestre", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : { },
    "col')
    || TO_CLOB('umnDefs" : [ {
      "headerName" : "Promozione",
      "field" : "PromozioneRiferimento.Name",
      "width" : 100,
      "hide" : false,
      "editable" : false,
      "rowGroup" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "PromozioneRiferimento.MUI_Descrizione",
      "width" : 300,
      "hide" : false,
      "editable" : false,
      "rowGroup" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "')
    || TO_CLOB('Codice Descrizione",
      "field" : "PromozioneRiferimento.Descrizione",
      "width" : 300,
      "hide" : true,
      "editable" : false,
      "rowGroup" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "PromozioneRiferimento.Anno",
      "width" : 50,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Canale",
      "field" : "PromozioneRiferim')
    || TO_CLOB('ento.MUI_Canale",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Gruppo",
      "field" : "PromozioneRiferimento.Gruppo",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "PromozioneRiferimento.Semestre",
      "width" : 100,
      "hi')
    || TO_CLOB('de" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "selections" : [ {
      "source" : {
        "table" : "gc_AssociazionePromo_Riferimento",
        "dimension" : "PromozioneRiferimento",
        "attribute" : "Descrizione"
      },
      "destination" : [ {
        "table" : "gc_AssociazionePromo_Riferimento",
        "dimension" : "PromozioneRiferimento",
        "attribute" : "Descrizione"
      } ]
    } ]
  }, {
')
    || TO_CLOB('    "name" : "gc_AssociazionePromo_Associazioni",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "alertNoDataFound" : false,
    "title" : "Associazioni",
    "height" : 18,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Da')
    || TO_CLOB('ta_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Promozione Riferimento" : "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}, ASC)}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Configurazione Riferimento" : "{[Misura Configurazione Riferimento].[Selezione]}"
        }
      },
      "FROM" : "[Configur')
    || TO_CLOB('azione Riferimento Associazione]"
    },
    "ElementSelector" : {
      "promozione" : {
        "lable" : "Promozione",
        "Dimension" : "Promozione",
        "Show" : "Descrizione",
        "mdx" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}} ",
        "enabled" : false
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Up')
    || TO_CLOB('dateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Promozione",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "PromozioneRiferimento.Descrizione",
      "width" : 400,
      "hide" : false,
      "editable" : false,
   ')
    || TO_CLOB('   "rowGroup" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Associazione",
      "field" : "MisuraConfigurazioneRiferimento$Selezione",
      "width" : 100,
      "hide" : false,
      "editable" : true,
      "rowGroup" : false,
      "type" : [ "TM1DataColumnInteger" ]
    } ],
    "actions" : [ {
      "componentId" : "tim_ap_aggiorna_associazioni",
      "componentLabel" : "Aggiorna Associazioni",
      "process" : "MUI_DUMMY_CUB.Aggiorna Associazioni"')
    || TO_CLOB(',
      "timeout" : -1,
      "refresh" : [ "gc_AssociazionePromo_Associazioni", "gc_AssociazionePromo_SezioniTematiche" ],
      "params" : [ {
        "label" : "Aggiorna Associazioni:",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : true,
        "defaultValue" : "Sei Sicuro Di Voler Aggiornare?"
      } ]
    }, {
      "componentId" : "tim_ap_aggiorna",
      "componentLabel" : "Aggiorna Descrizione Riferimento",
      "process" : "MUI_DUMMY_DIM.P')
    || TO_CLOB('romozione.ConfRiferimento",
      "timeout" : -1,
      "refresh" : [ "gc_AssociazionePromo_Promozioni", "gc_AssociazionePromo_Riferimento", "gc_AssociazionePromo_Associazioni", "gc_AssociazionePromo_SezioniTematiche" ],
      "params" : [ {
        "label" : "Anno",
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "hasPicklist" : true,
        "disabled" : false,
        "visible" : true
      } ]
    }, {
      "componentId"')
    || TO_CLOB(' : "tim_ap_associa",
      "componentLabel" : "Associa",
      "process" : "MUI_DUMMY_CUB.Associa Promozione",
      "timeout" : -1,
      "refresh" : [ "gc_AssociazionePromo_Associazioni", "gc_AssociazionePromo_SezioniTematiche" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
        "label" : "Promozione",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : true
      },')
    || TO_CLOB(' {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : false
      }, {
        "dimension" : "PromozioneRiferimento",
        "attribute" : "Descrizione",
        "paramName" : "inRiferimento",
        "label" : "Riferimento",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : true
      } ]
    } ]
  }')
    || TO_CLOB(', {
    "name" : "gc_AssociazionePromo_SezioniTematiche",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "alertNoDataFound" : false,
    "title" : "Sezioni Tematiche",
    "height" : 35,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[P')
    || TO_CLOB('romozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}  ",
          "Promozione con Sezione Tematica" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione con Sezione Tematica] )}, 0)}, ASC)}} "
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Configurazione Riferimento" : "{[Misura Configurazione Riferimento].[Selezione] , [Misura Co')
    || TO_CLOB('nfigurazione Riferimento].[DataInizio.],[Misura Configurazione Riferimento].[DataFine.],[Misura Configurazione Riferimento].[DataInizio],[Misura Configurazione Riferimento].[DataFine],[Misura Configurazione Riferimento].[COUNT_GIORNI],[Misura Configurazione Riferimento].[VOL],[Misura Configurazione Riferimento].[ESCLUSIONE_CLUSTER],[Misura Configurazione Riferimento].[ESCLUSIONE_COUNTING],[Misura Configurazione Riferimento].[Escludi_Venduto],[Misura Configurazione Riferimento].[DESCRIZIONE_RIF]}')
    || TO_CLOB('"
        }
      },
      "FROM" : "[Configurazione Riferimento]"
    },
    "ElementSelector" : {
      "promozione" : {
        "lable" : "Promozione",
        "Dimension" : "Promozione",
        "Show" : "Descrizione",
        "mdx" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}} ",
        "enabled" : false
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "UniqueName" ],
     ')
    || TO_CLOB(' "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Promozione",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 300,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione Rif.",
      "field" : "MisuraConfigurazioneRiferimento$DESCRIZIONE_RIF",
      "width" : 400,')
    || TO_CLOB('
      "hide" : false,
      "editable" : true,
      "rowGroup" : false,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Sezione Tematica",
      "field" : "PromozioneconSezioneTematica.Descrizione",
      "width" : 300,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Selezione",
      "field" : "MisuraConfigurazioneRiferimento$Selezione",
      "width" : 100,
      "h')
    || TO_CLOB('ide" : false,
      "editable" : true,
      "rowGroup" : false,
      "type" : [ "TM1DataColumnCheckbox" ]
    }, {
      "headerName" : "DataInizio Rif",
      "field" : "MisuraConfigurazioneRiferimento$DataInizio_dot_",
      "width" : 100,
      "hide" : false,
      "editable" : true,
      "rowGroup" : false,
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "DataFine Rif",
      "field" : "MisuraConfigurazioneRiferimento$DataFine_dot_",
      "width" : 10')
    || TO_CLOB('0,
      "hide" : false,
      "editable" : true,
      "rowGroup" : false,
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Inizio",
      "field" : "MisuraConfigurazioneRiferimento$DataInizio",
      "width" : 100,
      "hide" : false,
      "editable" : true,
      "rowGroup" : false,
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Fine",
      "field" : "MisuraConfigurazioneRiferimento$DataFine",
      "width" : 100,
    ')
    || TO_CLOB('  "hide" : false,
      "editable" : true,
      "rowGroup" : false,
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "N. Giorni",
      "field" : "MisuraConfigurazioneRiferimento$COUNT_GIORNI",
      "width" : 100,
      "hide" : false,
      "editable" : true,
      "rowGroup" : false,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Gestione Volantino",
      "field" : "MisuraConfigurazioneRiferimento$VOL",
      "width" : 100,
      "h')
    || TO_CLOB('ide" : false,
      "editable" : true,
      "rowGroup" : false,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Escludi Cluster Formato",
      "field" : "MisuraConfigurazioneRiferimento$ESCLUSIONE_CLUSTER",
      "width" : 100,
      "hide" : false,
      "editable" : true,
      "rowGroup" : false,
      "type" : [ "TM1DataColumnText" ]
    }, {
      "headerName" : "Escludi Conteggio",
      "field" : "MisuraConfigurazioneRiferimento$ESCLUSIONE_COUNTING",
')
    || TO_CLOB('
      "width" : 100,
      "hide" : false,
      "editable" : true,
      "rowGroup" : false,
      "type" : [ "TM1DataColumnCheckbox" ]
    }, {
      "headerName" : "Escludi Venduto",
      "field" : "MisuraConfigurazioneRiferimento$Escludi_Venduto",
      "width" : 100,
      "hide" : false,
      "editable" : true,
      "rowGroup" : false,
      "type" : [ "TM1DataColumnCheckbox" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('0','config_filter.json',TO_CLOB('[ {
  "DIM_code" : "idsezioneTematica",
  "DIM_columnName" : "ID Sezione Tematica",
  "DIM_description" : "ID Sezione Tematica",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID Sezione Tematica] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "Caption_Default",
    "ATTR_columnName" : "Caption_Default",
    "ATTR_desc" : "Caption_Default"
  } ]
}, {
  "DIM_code" : "promozione",
  "DIM_columnName" : "Promozione",
  "D')
    || TO_CLOB('IM_description" : "Promozione",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
  "list_ATTR" : [ {
    "ATTR_code" : "anno",
    "ATTR_columnName" : "Anno",
    "ATTR_desc" : "Anno"
  }, {
    "ATTR_code" : "canale",
    "ATTR_columnName" : "Canale",
    "ATTR_desc" : "Canale"
  }, {
')
    || TO_CLOB('    "ATTR_code" : "tipo",
    "ATTR_columnName" : "MUI_Tipo Promozione Desc",
    "ATTR_desc" : "Gruppo"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "MUI_Descrizione",
    "ATTR_desc" : "Promozione"
  }, {
    "ATTR_code" : "riferimento",
    "ATTR_columnName" : "Riferimento",
    "ATTR_desc" : "Riferimento"
  }, {
    "ATTR_code" : "semestre",
    "ATTR_columnName" : "MUI_Semestre",
    "ATTR_desc" : "Semestre"
  }, {
    "ATTR_code" : "datainizioist",
    "')
    || TO_CLOB('ATTR_columnName" : "DataInizioIst",
    "ATTR_desc" : "Data Inizio Ist."
  }, {
    "ATTR_code" : "datafineist",
    "ATTR_columnName" : "DataFineIst",
    "ATTR_desc" : "Data Fine Ist."
  }, {
    "ATTR_code" : "tipopromozione",
    "ATTR_columnName" : "Tipo Promozione",
    "ATTR_desc" : "Tipo Promozione"
  }, {
    "ATTR_code" : "statodesc",
    "ATTR_columnName" : "StatoDesc",
    "ATTR_desc" : "Stato Desc"
  }, {
    "ATTR_code" : "canaleanno",
    "ATTR_columnName" : "Canal')
    || TO_CLOB('e_Anno",
    "ATTR_desc" : "Canale Anno"
  }, {
    "ATTR_code" : "proximity",
    "ATTR_columnName" : "Proximity",
    "ATTR_desc" : "Proximity"
  } ]
}, {
  "DIM_code" : "promozioneriferimento",
  "DIM_columnName" : "Promozione Riferimento",
  "DIM_description" : "Promozione Riferimento",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)},[Promozione Riferimento].[Data_Inizio_Ist.], BD')
    || TO_CLOB('ESC)}} , [Promozione Riferimento].[Canale], BASC),[Promozione Riferimento].[Gruppo],BASC)}",
  "list_ATTR" : [ {
    "ATTR_code" : "anno",
    "ATTR_columnName" : "Anno",
    "ATTR_desc" : "Anno"
  }, {
    "ATTR_code" : "rif_canale",
    "ATTR_columnName" : "MUI_Canale",
    "ATTR_desc" : "Canale"
  }, {
    "ATTR_code" : "gruppo",
    "ATTR_columnName" : "MUI_Tipo Promozione Desc",
    "ATTR_desc" : "Gruppo"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descri')
    || TO_CLOB('zione + Riferimento",
    "ATTR_desc" : "Promozione"
  }, {
    "ATTR_code" : "riferimento",
    "ATTR_columnName" : "Descrizione + Data",
    "ATTR_desc" : "Promozione"
  }, {
    "ATTR_code" : "semestre",
    "ATTR_columnName" : "Semestre",
    "ATTR_desc" : "Semestre"
  } ]
}, {
  "DIM_code" : "reparto",
  "DIM_columnName" : "Reparto",
  "DIM_description" : "Reparto",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}')
    || TO_CLOB(', 0)},[Reparto].[MUI_Sort],  BASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "compratore",
    "ATTR_columnName" : "Compratore",
    "ATTR_desc" : "Compratore"
  }, {
    "ATTR_code" : "sigla",
    "ATTR_columnName" : "Sigla",
    "ATTR_desc" : "Sigla"
  }, {
    "ATTR_code" : "tipelemento",
    "ATTR_columnName" : "TipoElemento",
    "ATTR_desc" : "Tipo Elemento"
  }, ')
    || TO_CLOB('{
    "ATTR_code" : "codpadre",
    "ATTR_columnName" : "cod_padre",
    "ATTR_desc" : "Cod. Padre"
  }, {
    "ATTR_code" : "reparto",
    "ATTR_columnName" : "Reparto",
    "ATTR_desc" : "Reparto"
  }, {
    "ATTR_code" : "categorymanager",
    "ATTR_columnName" : "CategoryManager",
    "ATTR_desc" : "Category Manager"
  } ]
}, {
  "DIM_code" : "categoria",
  "DIM_columnName" : "Categoria",
  "DIM_description" : "Categoria",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSourc')
    || TO_CLOB('e" : "{{Except ( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_000],[Categoria].[CAT_0000] }  ) } ,{[Categoria].[CAT_0000]}}",
  "list_ATTR" : [ {
    "ATTR_code" : "direzionedesc",
    "ATTR_columnName" : "DirezioneDesc",
    "ATTR_desc" : "Direzione"
  }, {
    "ATTR_code" : "direzione",
    "ATTR_columnName" : "Direzione",
    "ATTR_desc" : "Cod Direzione"
  }, {
    "ATTR_code" : "repartodesc",
    "ATTR_columnName" : "RepartoDesc",
')
    || TO_CLOB('    "ATTR_desc" : "Reparto"
  }, {
    "ATTR_code" : "reparto",
    "ATTR_columnName" : "Reparto",
    "ATTR_desc" : "Cod Reparto"
  }, {
    "ATTR_code" : "estensionecl",
    "ATTR_columnName" : "EstensioneCL",
    "ATTR_desc" : "Estensione CL"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "raggruppamentoFoto",
  "DIM_columnName" : "Raggruppamento Foto",
  "DIM_description" : "Raggruppame')
    || TO_CLOB('nto Foto",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Raggruppamento Foto] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "tot",
    "ATTR_columnName" : "MUI_TOT",
    "ATTR_desc" : "Totali"
  }, {
    "ATTR_code" : "tots",
    "ATTR_columnName" : "MUI_TOTS",
    "ATTR_desc" : "Sub Totali"
  }, {
    "ATTR_code" : "mui_descrizione",
    "ATTR_columnName" : "MUI_Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {')
    || TO_CLOB('
    "ATTR_code" : "mui_compratore",
    "ATTR_columnName" : "MUI_Compratore",
    "ATTR_desc" : "Compratore"
  }, {
    "ATTR_code" : "mui_reparto",
    "ATTR_columnName" : "MUI_Reparto",
    "ATTR_desc" : "Reparto"
  } ]
}, {
  "DIM_code" : "spazio",
  "DIM_columnName" : "Spazio",
  "DIM_description" : "Spazio",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 1)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "')
    || TO_CLOB('compratore",
    "ATTR_columnName" : "MUI_Compratore",
    "ATTR_desc" : "Compratore"
  }, {
    "ATTR_code" : "reparto",
    "ATTR_columnName" : "MUI_Reparto",
    "ATTR_desc" : "Reparto"
  }, {
    "ATTR_code" : "macroSpazioDescrizione",
    "ATTR_columnName" : "MacroSpazioDescrizione",
    "ATTR_desc" : "Macro Spazio"
  } ]
}, {
  "DIM_code" : "compratore",
  "DIM_columnName" : "Compratore",
  "DIM_description" : "Compratore",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSou')
    || TO_CLOB('rce" : "{ EXCEPT( {TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}, { [Compratore].[NA] ,  [Compratore].[S000]  }) } , { [Compratore].[S000]}",
  "list_ATTR" : [ {
    "ATTR_code" : "categorymanager",
    "ATTR_columnName" : "CategoryManager",
    "ATTR_desc" : "Category Manager"
  }, {
    "ATTR_code" : "repartodcodesc",
    "ATTR_columnName" : "MUI_Reparto",
    "ATTR_desc" : "Reparto Cod - Desc"
  }, {
    "ATTR_code" : "repartodesc",
    "ATTR_columnName" : ')
    || TO_CLOB('"MUI_RepartoDesc",
    "ATTR_desc" : "Reparto"
  }, {
    "ATTR_code" : "reparto",
    "ATTR_columnName" : "Reparto",
    "ATTR_desc" : "Cod Reparto"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "anno",
  "DIM_columnName" : "Anno",
  "DIM_description" : "Anno",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Anno] )}, 0)}, DESC)}}",
 ')
    || TO_CLOB(' "list_ATTR" : [ {
    "ATTR_code" : "Name",
    "ATTR_columnName" : "Name",
    "ATTR_desc" : "Anno"
  } ]
}, {
  "DIM_code" : "clients",
  "DIM_columnName" : "}Clients",
  "DIM_description" : "Clients",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}Clients] )}, 0)}, DESC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "TM1_DefaultDisplayValue",
    "ATTR_columnName" : "}TM1_DefaultDisplayValue",
    "ATTR_desc" : "Client"
  } ')
    || TO_CLOB(']
}, {
  "DIM_code" : "zonaPromo",
  "DIM_columnName" : "Zona Promo",
  "DIM_description" : "Zona Promo",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{ EXCEPT({{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Zona Promo] )}, 0)}, ASC)}}, { [Zona Promo].[NA],[Zona Promo].[SOCIETA_1],[Zona Promo].[SOCIETA_2] })}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "pubbli')
    || TO_CLOB('cita",
  "DIM_columnName" : "Pubblicità",
  "DIM_description" : "Pubblicità",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{ EXCEPT({{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Pubblicità] )}, 0)}, ASC)}}, { [Pubblicità].[RADIO] }) }",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "canale",
  "DIM_columnName" : "Canale",
  "DIM_description" : "Canale",
  "ENDPOINT_se')
    || TO_CLOB('rverName" : "promo",
  "MDX_jsonSource" : "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Canale] )}, 0)}, [Canale].[Descrizione],ASC )}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "misuraCanale",
  "DIM_columnName" : "Misura Canale",
  "DIM_description" : "Misura Canale",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura ')
    || TO_CLOB('Canale] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "negozio",
  "DIM_columnName" : "Negozio",
  "DIM_description" : "Negozio",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "zonaPromo",
    "ATTR_columnName" : "MUI_ZonaPromo",
    "ATTR_d')
    || TO_CLOB('esc" : "Zona Promo"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "tipoPuntoVendita",
    "ATTR_columnName" : "Tipo Punto Vendita",
    "ATTR_desc" : "Tipo Punto Vendita"
  }, {
    "ATTR_code" : "repartoProfumeria",
    "ATTR_columnName" : "Reparto Profumeria",
    "ATTR_desc" : "Reparto Profumeria"
  } ]
}, {
  "DIM_code" : "tipoPromozione",
  "DIM_columnName" : "Tipo Promozione",
  "DIM_')
    || TO_CLOB('description" : "Tipo Promozione",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "gruppo",
    "ATTR_columnName" : "GRUPPO",
    "ATTR_desc" : "Gruppo"
  } ]
}, {
  "DIM_code" : "macrospazio",
  "DIM_columnName" : "MacroSpazio",
  "DIM_descripti')
    || TO_CLOB('on" : "Macro Spazio",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "prestazioniEC",
    "ATTR_columnName" : "Prestazioni EC",
    "ATTR_desc" : "Prestazioni EC"
  }, {
    "ATTR_code" : "prestazioniCNT",
    "ATTR_columnName" : "Prestazioni CNT",
 ')
    || TO_CLOB('   "ATTR_desc" : "Prestazioni CNT"
  } ]
}, {
  "DIM_code" : "misuraConfigurazioneMacrospaziNeg",
  "DIM_columnName" : "Misura_Configurazione_Macrospazi_Neg",
  "DIM_description" : "Configurazione Macrospazio Neg",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Neg] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Desc')
    || TO_CLOB('rizione"
  } ]
}, {
  "DIM_code" : "misuraConfigurazioneMacrospaziNegPromo",
  "DIM_columnName" : "Misura_Configurazione_Macrospazi_Neg_Promo",
  "DIM_description" : "Configurazione Macrospazio Neg Promo",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Neg_Promo] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descriz')
    || TO_CLOB('ione"
  } ]
}, {
  "DIM_code" : "misuraConfigurazioneMacrospaziListino",
  "DIM_columnName" : "Misura_Configurazione_Macrospazi_Listino",
  "DIM_description" : "Misura Configurazione Macrospazi Listino",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Listino] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione')
    || TO_CLOB('"
  } ]
}, {
  "DIM_code" : "rep_promozione",
  "DIM_columnName" : "REP - Promozione",
  "DIM_description" : "Rep Promozione",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)},[REP - Promozione].[Data_Inizio_Ist], BDESC)}} , [REP - Promozione].[Canale], BASC),[REP - Promozione].[Tipo Promozione],BASC)}",
  "list_ATTR" : [ {
    "ATTR_code" : "anno",
    "ATTR_columnName" : "Anno",
    "AT')
    || TO_CLOB('TR_desc" : "Anno"
  }, {
    "ATTR_code" : "canale",
    "ATTR_columnName" : "Canale",
    "ATTR_desc" : "Canale"
  }, {
    "ATTR_code" : "tipo",
    "ATTR_columnName" : "Tipo Promozione",
    "ATTR_desc" : "Gruppo"
  }, {
    "ATTR_code" : "riferimento",
    "ATTR_columnName" : "Riferimento",
    "ATTR_desc" : "Riferimento"
  }, {
    "ATTR_code" : "semestre",
    "ATTR_columnName" : "MUI_Semestre",
    "ATTR_desc" : "Semestre"
  }, {
    "ATTR_code" : "descrizione",
    "AT')
    || TO_CLOB('TR_columnName" : "Descrizione + Data",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "datainizioist",
    "ATTR_columnName" : "DataInizioIst",
    "ATTR_desc" : "Data Inizio Ist."
  }, {
    "ATTR_code" : "datafineist",
    "ATTR_columnName" : "DataFineIst",
    "ATTR_desc" : "Data Fine Ist."
  }, {
    "ATTR_code" : "tipopromozione",
    "ATTR_columnName" : "Tipo Promozione",
    "ATTR_desc" : "Tipo Promozione"
  }, {
    "ATTR_code" : "statodesc",
    "ATTR_columnNam')
    || TO_CLOB('e" : "StatoDesc",
    "ATTR_desc" : "Stato Desc"
  }, {
    "ATTR_code" : "canaleanno",
    "ATTR_columnName" : "Canale_Anno",
    "ATTR_desc" : "Canale Anno"
  } ]
}, {
  "DIM_code" : "rep_compratore",
  "DIM_columnName" : "REP - Compratore",
  "DIM_description" : "Rep Compratore",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "[REP - Compratore].[Compratori]",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_des')
    || TO_CLOB('c" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_categoria",
  "DIM_columnName" : "REP - Categoria",
  "DIM_description" : "Rep Categoria",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "reparto",
    "ATTR_columnName" : "MUI_Reparto",
    "ATTR_desc" : "Reparto"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "AT')
    || TO_CLOB('TR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_misuraTimone",
  "DIM_columnName" : "REP - Misura Timone",
  "DIM_description" : "Rep Misura Timone",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Timone] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_scenario",
  "DIM_columnNa')
    || TO_CLOB('me" : "REP - Scenario",
  "DIM_description" : "Rep Scenario",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{ [REP - Scenario].[CONS] ,[REP - Scenario].[BDG]  ,[REP - Scenario].[RIF_MKT_DT]   ,[REP - Scenario].[TGT_ACQ] }",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "categoriadesc",
    "ATTR_columnName" : "Categoria Desc",
    "ATTR_desc" : "Categoria Desc"
  }, {')
    || TO_CLOB('
    "ATTR_code" : "grmdesc",
    "ATTR_columnName" : "GRM Desc",
    "ATTR_desc" : "GRM Desc"
  }, {
    "ATTR_code" : "subgrmdesc",
    "ATTR_columnName" : "SubGrm Desc",
    "ATTR_desc" : "SubGrm Desc"
  } ]
}, {
  "DIM_code" : "rep_articolo",
  "DIM_columnName" : "REP - Articolo",
  "DIM_description" : "Rep Articolo",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 1)}, ASC)}}",
  "list_ATTR" : [ {
')
    || TO_CLOB('
    "ATTR_code" : "categoriadesc",
    "ATTR_columnName" : "Categoria Desc",
    "ATTR_desc" : "Categoria Desc"
  }, {
    "ATTR_code" : "grmdesc",
    "ATTR_columnName" : "GRM Desc",
    "ATTR_desc" : "GRM Desc"
  }, {
    "ATTR_code" : "subgrmdesc",
    "ATTR_columnName" : "SubGrm Desc",
    "ATTR_desc" : "SubGrm Desc"
  } ]
}, {
  "DIM_code" : "rep_fornitore",
  "DIM_columnName" : "REP - Fornitore",
  "DIM_description" : "Rep Fornitore",
  "ENDPOINT_serverName" : "reporting",')
    || TO_CLOB('
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_zonaPromo",
  "DIM_columnName" : "REP - Zona Promo",
  "DIM_description" : "Rep Zona Promo",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{TM1SUBSETALL( [REP - Zona Promo] )}",
  "list_ATTR" : [ {
    "ATTR_code')
    || TO_CLOB('" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_sezioneTematica",
  "DIM_columnName" : "REP - Sezione Tematica",
  "DIM_description" : "Rep Sezione Tematica",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc')
    || TO_CLOB('" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_meccanicaSemplice",
  "DIM_columnName" : "REP - Meccanica Semplice",
  "DIM_description" : "Rep Meccanica Semplice",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_avolantino",
')
    || TO_CLOB('  "DIM_columnName" : "REP - AVolantino",
  "DIM_description" : "Rep AVolantino",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_MisuraReportingArticoloZona",
  "DIM_columnName" : "REP - Misura Reporting Articolo Zona",
  "DIM_description')
    || TO_CLOB('" : "Rep Articolo Zona",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Reporting Articolo Zona] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_MisuraReportingArticolo",
  "DIM_columnName" : "REP - Misura Reporting Articolo",
  "DIM_description" : "Rep Articolo",
  "ENDPOINT_serverName" :')
    || TO_CLOB(' "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Reporting Articolo] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_reparto",
  "DIM_columnName" : "REP - Reparto",
  "DIM_description" : "Rep Reparto",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Re')
    || TO_CLOB('parto] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "fornitore",
  "DIM_columnName" : "Fornitore",
  "DIM_description" : "Fornitore",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    ')
    || TO_CLOB('"ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "articoloFittizio",
  "DIM_columnName" : "Articolo Fittizio",
  "DIM_description" : "Articolo Fittizio",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo Fittizio] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "compratore",
    "ATTR_columnName" : ')
    || TO_CLOB('"Compratore",
    "ATTR_desc" : "Compratore"
  } ]
}, {
  "DIM_code" : "sezioneTematica",
  "DIM_columnName" : "Sezione Tematica",
  "DIM_description" : "Sezione Tematica",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "scenario",
  "DIM_col')
    || TO_CLOB('umnName" : "Scenario",
  "DIM_description" : "Scenario",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "misuraTimone",
  "DIM_columnName" : "Misura Timone.",
  "DIM_description" : "Misura",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM')
    || TO_CLOB('1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "contratto",
  "DIM_columnName" : "Contratto",
  "DIM_description" : "Contratto",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Contratto] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizi')
    || TO_CLOB('one",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "prestazione",
  "DIM_columnName" : "Prestazione",
  "DIM_description" : "Prestazione",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "iniziati')
    || TO_CLOB('va",
  "DIM_columnName" : "Iniziativa",
  "DIM_description" : "Iniziativa",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Iniziativa] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "Descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "articolo",
  "DIM_columnName" : "Articolo",
  "DIM_description" : "Articolo",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonS')
    || TO_CLOB('ource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 1)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "categoriadesc",
    "ATTR_columnName" : "Categoria Desc",
    "ATTR_desc" : "Categoria Desc"
  }, {
    "ATTR_code" : "grmdesc",
    "ATTR_columnName" : "GRM Desc",
    "ATTR_desc" : "GRM Desc"
  }, {
    "ATTR_code" : "subgrmdesc",
    "ATTR_columnName" : "SubGrm Desc",
    "ATTR_desc" : "SubGrm Desc"
  }, {
    "ATTR_code" : "attivo",
    "ATTR_columnName" :')
    || TO_CLOB(' "Attivo",
    "ATTR_desc" : "Attivo"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "misuraProgrammazioneFornitore",
  "DIM_columnName" : "Misura Programmazione Fornitore",
  "DIM_description" : "Misura Programmazione Fornitore",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Programmazione Fornitore] )}, 0)}, ASC)}}",
  "list_ATTR')
    || TO_CLOB('" : [ {
    "ATTR_code" : "visualizzazione",
    "ATTR_columnName" : "Visualizzazione",
    "ATTR_desc" : "Visualizzazione"
  } ]
}, {
  "DIM_code" : "spazioProgressivo",
  "DIM_columnName" : "SpazioProgressivo",
  "DIM_description" : "Spazio Progressivo",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [SpazioProgressivo] )}, 0)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "MUI_Descrizione",
    "ATTR_')
    || TO_CLOB('desc" : "Spazio"
  } ]
} ]'),'GLOBAL');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('59','/Admin/Gestione_ACL',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('60','/Admin/Gestione_Applicazione',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('61','/Admin/Gestione_Configurazioni',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('62','/Admin/Gestione_Menu',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('63','/Admin/Gestione_Ruoli',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('64','/Admin/Gestione_UI',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('65','/Admin/Gestione_Utenti',' {
    "clients":["TM1_DefaultDisplayValue"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('66','/Fatturazione/Associazione_Articoli_Promozione',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"fornitore":["descrizione"],
	"compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "spazioProgressivo":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('76','/Pianificazione/Selezione_Promo',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"tipoPromozione":["descrizione"],
	"prestazione":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('77','/Piano_Annuale/Controllo_Negozi','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('82','/Piano_Annuale/Gestione_Iniziative','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('92','/Piano_Annuale/Spazi/Macrospazi_Negozi_Promo','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "macrospazio":["descrizione"],
    "negozio":["zonaPromo","descrizione"],
    "misuraConfigurazioneMacrospaziNegPromo":["descrizione"]
}
 ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('98','/Reporting/Dettaglio_Zona','{
  "rep_promozione" : [ "anno", "canale", "tipo", "descrizione", "canaleanno" ],
  "rep_articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc" ],
  "rep_fornitore" : [ "descrizione" ],
  "rep_zonaPromo" : [ "descrizione" ],
  "rep_sezioneTematica" : [ "descrizione" ],
  "rep_meccanicaSemplice" : [ "descrizione" ],
  "rep_avolantino" : [ "descrizione" ],
  "rep_MisuraReportingArticoloZona" : [ "descrizione" ],
  "rep_scenario" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('99','/Reporting/Sintesi_Campagna','{
  "rep_promozione" : [ "anno", "canale", "tipo", "descrizione", "canaleanno" ],
  "rep_compratore" : [ "descrizione" ],
  "rep_categoria" : [ "descrizione" ],
  "rep_misuraTimone" : [ "descrizione" ],
  "rep_scenario" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('100','/Reporting/Storico_articolo_per_zona_(ACQ)','{
  "rep_promozione" : [ "anno", "canale", "tipo", "descrizione", "canaleanno" ],
  "rep_articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc" ],
  "rep_fornitore" : [ "descrizione" ],
  "rep_zonaPromo" : [ "descrizione" ],
  "rep_sezioneTematica" : [ "descrizione" ],
  "rep_meccanicaSemplice" : [ "descrizione" ],
  "rep_avolantino" : [ "descrizione" ],
  "rep_MisuraReportingArticoloZona" : [ "descrizione" ],
  "rep_scenario" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('101','/Reporting/Storico_articolo_per_zona_(MKT)','{
  "rep_promozione" : [ "anno", "canale", "tipo", "descrizione", "canaleanno" ],
  "rep_articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc" ],
  "rep_fornitore" : [ "descrizione" ],
  "rep_zonaPromo" : [ "descrizione" ],
  "rep_sezioneTematica" : [ "descrizione" ],
  "rep_meccanicaSemplice" : [ "descrizione" ],
  "rep_avolantino" : [ "descrizione" ],
  "rep_MisuraReportingArticoloZona" : [ "descrizione" ]
}','FILTER');
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
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('90','/Piano_Annuale/Sezioni_Tematiche/Crea_Sezioni_Tematiche',' {
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1020','/Fatturazione/Contribuzione_Campagna',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_AssociazioneArticoliPromo_top",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Contribuzione Campagna",
    "height" : 60,
    "Rata" : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata].[I-Rate Fatturazione Contr])}, 0)}, DESC)}}   }",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM')
    || TO_CLOB('1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
          "Fornitore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
          "Rata" : "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) }, { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] }) }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FI')
    || TO_CLOB('LTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Misura Fatturazione" : "{[Misura Fatturazione].[I-Fatturazione Contributi]}"
        }
      },
      "FROM" : "[Fatturazione]",
      "WHERE" : {
        "Scenario" : "[BDG]",
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attribute')
    || TO_CLOB('s/MUI_DescrizioneData", "Attributes/Descrizione", "Attributes/MUI_TOT", "Attributes/MUI_TOTS", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
      ')
    || TO_CLOB('  },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 100,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupSh')
    || TO_CLOB('ow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "LISTINO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ]
        },
        "APPLICATO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ]
        },
        "PRESTAZIONE" : {
          "width" : 300,
          "type" : [ "TM1DataColumnText" ]
        },
        "CAUS" : {
          "width" : 300,')
    || TO_CLOB('
          "type" : [ "TM1DataColumnText" ]
        },
        "DESC_FATT" : {
          "width" : 300,
          "type" : [ "TM1DataColumnText" ]
        },
        "OK" : {
          "width" : 50,
          "hide" : true,
          "type" : [ "TM1DataColumnText" ]
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Rata.Descrizione",
      "headerName" : "Rata",
      "width" : 400,
 ')
    || TO_CLOB('     "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Totale Compratore",
      "field" : "Compratore.MUI_TOT",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type"')
    || TO_CLOB(' : [ "TM1Element" ]
    }, {
      "headerName" : "Totale Fornitore",
      "field" : "Fornitore.MUI_TOTS",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitore",
      "field" : "Fornitore.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Totale R')
    || TO_CLOB('ata",
      "field" : "Rata.MUI_TOTS",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Rata",
      "field" : "Rata.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1021','/Fatturazione/Contribuzione_Campagna','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "fornitore" : [ "descrizione" ],
  "compratore" : [ "categorymanager", "repartodesc", "reparto", "descrizione" ],
  "spazioProgressivo" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1022','/Fatturazione/Contribuzione_Promozioni',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_AssociazioneArticoliPromo_top",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Contribuzione Promozioni",
    "maxCells" : 100000,
    "height" : 60,
    "Rata" : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata].[I-Rate Fatturazione Contr])}, 0)}, DESC)}}   }",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILT')
    || TO_CLOB('ER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
          "Fornitore" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
          "Rata" : "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILT')
    || TO_CLOB('ERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) }, { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] }) }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Fatturazione" : "{[Misura Fatturazione].[I-Fatturazione Contributi]}"
        }
      },
      "FROM" : "[Fatturazione]",
      "WHERE" : {
        "Scenario" : "[BDG]",
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Mem')
    || TO_CLOB('bers" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Canale", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/Descrizione", "Attributes/Riferimento", "Attributes/MUI_TOT", "Attributes/MUI_TOTS", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
   ')
    || TO_CLOB('   "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 100,
        "h')
    || TO_CLOB('ide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "LISTINO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ]
        },
        "APPLICATO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ]
        },
        "PRESTAZIONE" : {
          "width" : 300,
      ')
    || TO_CLOB('    "type" : [ "TM1DataColumnText" ]
        },
        "CAUS" : {
          "width" : 300,
          "type" : [ "TM1DataColumnText" ]
        },
        "DESC_FATT" : {
          "width" : 300,
          "type" : [ "TM1DataColumnText" ]
        },
        "OK" : {
          "width" : 50,
          "hide" : true,
          "type" : [ "TM1DataColumnText" ]
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      ')
    || TO_CLOB('},
      "field" : "Rata.Descrizione",
      "headerName" : "Rata",
      "width" : 400,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" :')
    || TO_CLOB(' 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : f')
    || TO_CLOB('alse,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "he')
    || TO_CLOB('aderName" : "Totale Compratore",
      "field" : "Compratore.MUI_TOT",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Totale Fornitore",
      "field" : "Fornitore')
    || TO_CLOB('.MUI_TOTS",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitore",
      "field" : "Fornitore.Descrizione",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Totale Rata",
      "field" : "Rata.MUI_TOTS",
      "width" : 70,
      "hide" : true,
      "rowGroup')
    || TO_CLOB('" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Rata",
      "field" : "Rata.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1023','/Fatturazione/Contribuzione_Promozioni','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "fornitore" : [ "descrizione" ],
  "compratore" : [ "categorymanager", "repartodesc", "reparto", "descrizione" ],
  "spazioProgressivo" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('150','/Proximity/Timing','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('151','/Proximity/Negozi_Promo','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "negozio" : [ "descrizione", "zonaPromo" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('152','/Proximity/Proiezioni','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager", "repartodesc", "reparto", "descrizione" ],
  "rep_articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('153','/Proximity/Articoli_per_Negozio','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "negozio" : [ "descrizione", "zonaPromo" ],
  "compratore" : [ "categorymanager", "repartodesc", "reparto", "descrizione" ],
  "rep_articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('154','/Proximity/Set_per_Negozio','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('155','/Proximity/Timing',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_Timing",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "pagination" : false,
    "title" : "Timing",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Versione" : "{[Versione].[UFF]}",
          "Promozione" : " {FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizi')
    || TO_CLOB('o_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] = ''Y'')}   "
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "MisuraPromozioneProprietà" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Promozione Proprietà] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[Configurazione Promozione - Proprietà]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level"')
    || TO_CLOB(', "Attributes", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist", "PicklistValues" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Promozione.MUI_Descrizione",
      "headerName" : "Promozione",
      "width" : 500,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozi')
    || TO_CLOB('one.Canale",
      "width" : 60,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : tr')
    || TO_CLOB('ue,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 110,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" :')
    || TO_CLOB(' [ "TM1Element" ]
    }, {
      "headerName" : "Data Inizio",
      "field" : "MisuraPromozionePropriet_agrave_$DataInizio",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Data Fine",
      "field" : "MisuraPromozionePropriet_agrave_$DataFine",
      "width" : 110,
      "hide" : false,
      "rowGroup" : false,
      "editable" :')
    || TO_CLOB(' true,
      "cellClass" : "dateFormat",
      "type" : [ "TM1DataColumnDate" ]
    }, {
      "headerName" : "Valore Punto Fragola",
      "field" : "MisuraPromozionePropriet_agrave_$ValorePuntoFragola",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : true,
      "type" : [ "TM1DataColumnDecimal3" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('156','/Proximity/Negozi_Promo',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_NegoziPromo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Negozi Promo",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Negozio" : "{[Negozio].[Proximity]}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{TM1SORT( {FILTER( {TM1SUBSETALL( [')
    || TO_CLOB('Promozione] )}, [Promozione].[Proximity] = ''Y'')}, ASC)}"
        }
      },
      "FROM" : "[PROX Configurazione Negozio Promozione]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_DescrizioneData", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist", "PicklistValues" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData" ],
   ')
    || TO_CLOB('   "headerdefaults" : {
        "marryChildren" : true
      },
      "childrendefaults" : {
        "width" : 300,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText" ]
      },
      "childrenCustomTypes" : { }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Negozio.Descri')
    || TO_CLOB('zione",
      "headerName" : "Negozio",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Negozio",
      "field" : "Negozio.Descrizione",
      "width" : 250,
      "hide" : true,
      "rowGroup" : true,
      "editable" : true,
      "type" : [ "TM1DataColumnText" ]
    } ],
    "actions" : [ {
      "componentId" : "refresh_assort_negozi_promo_prox",
      "componentLabel" : "Aggiorna Assortiment')
    || TO_CLOB('o Negozio Promo Proximity",
      "process" : "MUI_DUMMY_Esegui Prox Cube Programmazione Fornitori Articoli Gestione Main",
      "timeout" : -1,
      "refresh" : [ "gc_NegoziPromo" ],
      "params" : [ {
        "paramName" : "pCompratore",
        "label" : "Inserire il Compratore",
        "defaultValue" : "TOT_COMP",
        "hasPicklist" : false
      } ]
    } ],
    "rowSuppressionEnabled" : true,
    "colSuppressionEnabled" : false,
    "preSelections" : [ {
      "dimens')
    || TO_CLOB('ion" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetProximity",
      "paramName" : "inPromo",
      "refresh" : [ "gc_NegoziPromo" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('157','/Proximity/Proiezioni',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_Proiezioni",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 750000,
    "title" : "Proiezioni",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{TM1SORT( {FILTER( {TM1SUBSETALL( [Promozione] )}, [Promozione].[Proximity] = ''Y'')}, ASC)}",
          "Articolo" : "{[Articolo].[(I) Articoli lev.0]}",
 ')
    || TO_CLOB('         "Compratore" : "{[Compratore].[(I) Compratori lev.0]}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "MisuraPromozionePianificazione" : "{TM1SORT( {FILTER( {TM1SUBSETALL( [Misura Promozione Pianificazione] )}, [Misura Promozione Pianificazione].[Proximity Subset] = \"1\")}, ASC)}"
        }
      },
      "FROM" : "[Promozione Pianificazione]",
      "WHERE" : {
        "Scenario" : "[BDG]",
        "Versione" : "[UFF]"
 ')
    || TO_CLOB('     }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist", "PicklistValues" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "childrendefaults" : {
        "width" : 100,
        "hide" : false,
        "rowGroup" : false,
        "')
    || TO_CLOB('aggFunc" : "",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText" ],
        "cellClass" : "cellClass-left-text"
      },
      "childrenCustomTypes" : {
        "PRZ_ATT_ANN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PUNTI" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        }
      }
    },
    "au')
    || TO_CLOB('toGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Articolo.Descrizione",
      "headerName" : "Articolo",
      "cellClass" : "cellClass-left-text",
      "width" : 400,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Promozione",
      "field" : "Promozione.MUI_Descrizione",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : true,
')
    || TO_CLOB('      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Articolo(Codice)",
      "field" : "Articolo.DescrizioneCODICE",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "cellClass" : "cellClass-left-text",
      ')
    || TO_CLOB('"width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetProximity",
      "paramName" : "inPromo",
      "refresh" : [ "')
    || TO_CLOB('gc_Proiezioni" ]
    }, {
      "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_Proiezioni" ]
    } ],
    "styleRules" : { }
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('158','/Proximity/Articoli_per_Negozio',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_ArticoliPerNegozio",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "pagination" : false,
    "title" : "Articoli per Negozio",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Negozio" : "{[Negozio].[Proximity]}",
          "Articolo" : "{[Articolo].[(I) Articoli lev.0]}",
          "Compratore" : "{[Compratore].[(I) Comprat')
    || TO_CLOB('ori lev.0]}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{TM1SORT( {FILTER( {TM1SUBSETALL( [Promozione] )}, [Promozione].[Proximity] = ''Y'')}, ASC)}",
          "PROX Report Promozione Assortimento Negozio" : "{[PROX Report Promozione Assortimento Negozio].[Lev 0]}"
        }
      },
      "FROM" : "[PROX Report Promozione Assortimento Negozio]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "At')
    || TO_CLOB('tributes/Descrizione", "Attributes/DescrizioneArticolo", "Attributes/MUI_DescrizioneData", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist", "PicklistValues" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Articolo.DescrizioneArticolo",
      "headerName" : "Articolo",
      "width" : 600,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "')
    || TO_CLOB('DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true')
    || TO_CLOB('
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 200,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : { }
    },
    "columnDefs" : [ {
      "headerName" : "Negozi')
    || TO_CLOB('o",
      "field" : "Negozio.Descrizione",
      "width" : 250,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetProximity",
   ')
    || TO_CLOB('   "paramName" : "inPromo",
      "refresh" : [ "gc_ArticoliPerNegozio" ]
    }, {
      "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_ArticoliPerNegozio" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('159','/Proximity/Set_per_Negozio',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SetPerNegozio",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "pagination" : false,
    "title" : "Set Per Negozio",
    "height" : 60,
    "PROXReportArticolinoninSET" : "{[PROX Report Articoli non in SET].[No Assortimento],[PROX Report Articoli non in SET].[In Assortimento],[PROX Report Articoli non in SET].[Non In Assortimento]}",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,')
    || TO_CLOB('
        "DIMENSIONS" : {
          "Promozione" : "{TM1SORT( {FILTER( {TM1SUBSETALL( [Promozione] )}, [Promozione].[Proximity] = ''Y'')}, ASC)}",
          "Compratore" : "{ EXCEPT( {TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}, { [Compratore].[NA], [Compratore].[S000] }) }",
          "Negozio" : "{[Negozio].[Proximity]}",
          "FamigliaSet" : "[Famiglia Set].[Lev 0]",
          "Articolo" : "{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}"
        }
 ')
    || TO_CLOB('     },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "PROXReportArticolinoninSET" : "{[PROX Report Articoli non in SET].[In Assortimento],[PROX Report Articoli non in SET].[Non In Assortimento]}"
        }
      },
      "FROM" : "[PROX Report Articoli non in SET]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/DescrizioneArticolo", "Attributes/MUI_DescrizioneData", ')
    || TO_CLOB('"UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist", "PicklistValues" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Articolo.DescrizioneArticolo",
      "headerName" : "Articolo",
      "width" : 600,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_Desc')
    || TO_CLOB('rizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
')
    || TO_CLOB('
        }
      },
      "childrendefaults" : {
        "width" : 200,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : { }
    },
    "columnDefs" : [ {
      "headerName" : "Negozio",
      "field" : "Negozio.Descrizione",
      "width" : 300,
      "hide" : true,
      "rowGroup" : tr')
    || TO_CLOB('ue,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Famiglia Set",
      "field" : "FamigliaSet.Name",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
     ')
    || TO_CLOB(' "attrColumnName" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetProximity",
      "paramName" : "inPromo",
      "refresh" : [ "gc_SetPerNegozio" ]
    }, {
      "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_SetPerNegozio" ]
    }')
    || TO_CLOB(' ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1001','/Pianificazione/Informazioni_Aggiuntive_(MKT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_informazioniAggiuntiveMKT",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : " Informazioni Aggiuntive",
    "height" : 58,
    "maxCells" : 750000,
    "Misura Promozione Pianificazione 2" : "{([Misura Promozione Pianificazione].[Configurazione Subset Pianificazione 1])}",
    "Misura Promozione Pianificazione" : "({FILTER(TM1SUBSETALL([Misura Promozione Pianificazione]),[}ElementAttri')
    || TO_CLOB('butes_Misura Promozione Pianificazione].([}ElementAttributes_Misura Promozione Pianificazione].[Ordinamento])>0 )})",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "')
    || TO_CLOB('{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
          "Articolo" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Promozione Pianificazione" : "{([Misura Promozione Pianificazione].[I - Misura Promozione Pianificazione Media (MKTG)])}"
        }
      },
      "FROM" : "[Promozione Pianificazione]",
      "WHERE" : {
  ')
    || TO_CLOB('      "Scenario" : "[BDG]",
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Descrizione", "Attributes/DescrizioneCODICE", "Attributes/Fornitore", "Attributes/RepartoDesc", "Attributes/CategoriaDesc", "Attributes/GRMDesc", "Attributes/SubGrmDesc", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSe')
    || TO_CLOB('ttings" : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "childrendefaults" : {
        "width" : 100,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText" ],
        "cellClass" : "cellClass-left-text"
      },
      "childrenCustomTypes" : {
        "DataInizio" : {
          "type" : ')
    || TO_CLOB('[ "TM1DataColumnDate" ],
          "cellClass" : "cellClass-right-text"
        },
        "DataFine" : {
          "type" : [ "TM1DataColumnDate" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_ATT_ANN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_MIN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_MAX" : {')
    || TO_CLOB('
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_ATT_USR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "_perc__SC" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "VAL_SC" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        ')
    || TO_CLOB('},
        "PRZ_PROMO" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_AN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_USR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CONTR__perc__IN_FATT" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cellCla')
    || TO_CLOB('ss" : "cellClass-right-text"
        },
        "CST_C_IVA" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_PROMO_C_IVA" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "N_PEZZI" : {
          "type" : [ "TM1DataColumnDecimal3" ],
          "cellClass" : "cellClass-right-text"
        },
        "COLLI" : {
          "type" : [ "TM1DataColumn')
    || TO_CLOB('Decimal3" ],
          "cellClass" : "cellClass-right-text"
        },
        "TOT_dot_VEND" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "F_FATT" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "IVA" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cellClass" : "cellClass-right-text"
        },
        "RIFATT_BS" : {
      ')
    || TO_CLOB('    "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "LIM_UTIL" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "%_SC" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CONTR_%_IN_FATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
   ')
    || TO_CLOB('     "PEZZ" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "VEND_PROMO_S_IVA" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_PROMO" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "ML_I_UNI" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellCl')
    || TO_CLOB('ass-right-text"
        },
        "ML" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "%_ML" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cellClass" : "cellClass-right-text"
        },
        "CONTR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "EXTRA_CONTR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "ce')
    || TO_CLOB('llClass" : "cellClass-right-text"
        },
        "NumeroNegozi" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "PUNTI" : {
          "type" : [ "TM1DataColumnDecimal3" ],
          "cellClass" : "cellClass-right-text"
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Articolo.Descrizione",
      "headerName" : "Arti')
    || TO_CLOB('colo",
      "cellClass" : "cellClass-left-text",
      "width" : 400,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Promozione",
      "field" : "Promozione.MUI_Descrizione",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Articolo(Codice)",
      "field" : "Articolo.')
    || TO_CLOB('DescrizioneCODICE",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornito')
    || TO_CLOB('re",
      "field" : "Articolo.Fornitore",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Articolo.RepartoDesc",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "he')
    || TO_CLOB('aderName" : "Categoria",
      "field" : "Articolo.CategoriaDesc",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Grm",
      "field" : "Articolo.GRMDesc",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
   ')
    || TO_CLOB(' }, {
      "headerName" : "Sub Grm",
      "field" : "Articolo.SubGrmDesc",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI')
    || TO_CLOB('_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_informazioniAggiuntiveMKT" ]
    }, {
      "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_informazioniAggiuntiveMKT" ]
')
    || TO_CLOB('    } ],
    "styleRules" : { }
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1002','/Pianificazione/Informazioni_Aggiuntive_(MKT)','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager", "repartodcodesc", "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "descrizione" ],
  "fornitore" : [ "descrizione" ],
  "articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc", "attivo" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1100','/Pianificazione/Sintesi/Sintesi_Campagna_-_Volantino_(gest,_diff.)','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1101','/Pianificazione/Sintesi/Sintesi_Campagna_-_Volantino_(gest,_diff.)','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1102','/Proximity/Crea_Paniere_Proximity','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1103','/Proximity/Crea_Paniere_Proximity','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1104','/Reporting/Progressivo_Pigne',TO_CLOB('{
  "connection" : "reporting",
  "show-version" : true,
  "configurations" : [ {
    "name" : "gc_ProgressivoPigne",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 2000000,
    "compulsoryFilters" : [ "REP - Promozione" ],
    "compulsoryFiltersMessage" : "Per utilizzare questo form è necessario applicare almeno un filtro sulla dimensione Promozione",
    "title" : "Progressivo Pigne",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "N')
    || TO_CLOB('ON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Promozione" : "{FILTER( { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)},[REP - Promozione].[Data_Inizio_Ist], BDESC)}} , [REP - Promozione].[Canale], BASC),[REP - Promozione].[Tipo Promozione],BASC)}, [REP - Promozione].[Canale] = ''Istituzionale'')}",
          "REP - Compratore" : " { ORDER ( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , [REP - Compratore].[MUI_Category] , ASC )}')
    || TO_CLOB('",
          "REP - Categoria" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)} , ASC)}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Misura Timone" : "{ {[REP - Misura Timone].[SPZ_CAMP]} , {FILTER( {TM1SUBSETALL( [REP - Misura Timone] )}, [REP - Misura Timone].[Tipo] = ''Spazi'')} }"
        }
      },
      "FROM" : "[Timone Reporting DRO]",
      "WHERE" : {
        "REP - Versione" : "[UFF]",
  ')
    || TO_CLOB('      "REP - Scenario" : "[BDG]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_DescrizioneData", "Attributes/Anno", "Attributes/MUI_Descrizione", "Attributes/Riferimento", "Attributes/MUI_Direzione", "Attributes/MUI_Reparto", "Attributes/MUI_Category", "Attributes/Descrizione", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "he')
    || TO_CLOB('aderconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "ope')
    || TO_CLOB('nByDefault" : true
        },
        "BDG" : {
          "headerClass" : "headerBdg",
          "openByDefault" : true
        },
        "CONS" : {
          "headerClass" : "headerCons",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : false,
        "type" : [ "TM1DataColumnInteger", "')
    || TO_CLOB('numericColumn" ]
      },
      "childrenCustomTypes" : {
        "D_VEND/RIF_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
        }
      }
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Anno",
      "field" : "REP_minus_Promozione.Anno",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Promozione",
      ')
    || TO_CLOB('"field" : "REP_minus_Promozione.MUI_Descrizione",
      "width" : 450,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "REP_minus_Promozione.Riferimento",
      "width" : 450,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Category",
      "field" : "REP_minus_Compratore.MUI')
    || TO_CLOB('_Category",
      "width" : 120,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "REP_minus_Compratore.Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Direzione",
      "field" : "REP_minus_Categoria.MUI_Direzione",
      "width" : 100,
   ')
    || TO_CLOB('   "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "REP_minus_Categoria.MUI_Reparto",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Categoria",
      "field" : "REP_minus_Categoria.Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
')
    || TO_CLOB('
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1105','/Reporting/Progressivo_Pigne','{
  "rep_promozione" : [ "anno", "canale", "tipo", "descrizione", "canaleanno" ],
  "rep_compratore" : [ "descrizione" ],
  "rep_categoria" : [ "reparto", "descrizione" ],
  "rep_misuraTimone" : [ "descrizione" ],
  "rep_scenario" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('118','/Timone/Foto_Speciali/Target_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_FotoSpeciali_Target",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "forceSuppression" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Foto Speciali Target",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{[Compratore].[MUI_Timone_Fo')
    || TO_CLOB('toSpec_Target_CAT]}",
          "Categoria" : "{[Categoria].[MUI_Timone_FotoSpec_Target_CAT]}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF')
    || TO_CLOB('_MKT_DT]},{[Scenario].[TGT_MKT]}}",
          "Sezione Tematica" : "{{TM1SUBSETALL( [Sezione Tematica] )}}",
          "Misura Timone." : "{EXCEPT( {[Misura Timone.].[(II) Timone Categoria Sez]} , {[Misura Timone.].[ASSORT_P]} ) }"
        }
      },
      "FROM" : "[Timone Categoria Sezione]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/M')
    || TO_CLOB('UI_Descrizione", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : tru')
    || TO_CLOB('e,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "MUI_Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDe')
    || TO_CLOB('fault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "ASSORT_P" : {
          "type" : [ "TM1')
    || TO_CLOB('DataColumnText" ]
        },
        "ST_0000" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide"')
    || TO_CLOB(' : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
')
    || TO_CLOB('      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' &')
    || TO_CLOB('& data.Categoria.MUI_Level > 0)  || (data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000'')  ",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      ')
    || TO_CLOB('"row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''')
    || TO_CLOB('].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('119','/Timone/Foto_Speciali/Target_(CAT)',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('120','/Timone/Foto_Speciali/Target_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_FotoSpeciali_Target",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "forceSuppression" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Foto Speciali Target",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{ [Compratore].[MUI_Timone_F')
    || TO_CLOB('otoSpec_Target_ACQ] }",
          "Categoria" : "{ [Categoria].[MUI_Timone_FotoSpec_Target_ACQ]  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario]')
    || TO_CLOB('.[RIF_MKT_DT]},{[Scenario].[TGT_MKT]}}",
          "Sezione Tematica" : "{ EXCEPT({{TM1SUBSETALL( [Sezione Tematica] )}}  ,{[Sezione Tematica].[ST_0000]}  )} ",
          "Misura Timone." : "{ EXCEPT({[Misura Timone.].[(II) Timone Categoria Sez]} , {[Misura Timone.].[ASSORT_P]}  ) }"
        }
      },
      "FROM" : "[Timone Categoria Sezione]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizi')
    || TO_CLOB('one", "Attributes/MUI_DescrizioneData", "Attributes/MUI_Descrizione", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" :')
    || TO_CLOB(' [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "MUI_Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
      ')
    || TO_CLOB('    "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" :')
    || TO_CLOB(' {
        "ASSORT_P" : {
          "type" : [ "TM1DataColumnText" ]
        },
        "ST_000" : {
          "hide" : false
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore')
    || TO_CLOB('.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI')
    || TO_CLOB('_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI')
    || TO_CLOB('_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  || (data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000'')  ",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',')
    || TO_CLOB('''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_')
    || TO_CLOB('06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('121','/Timone/Foto_Speciali/Target_(ACQ)',' {
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('122','/Timone/Spazi_Campagna/Target_Reparto_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SpaziCampagna_TargetReparto",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : true,
    "forceSuppression" : true,
    "title" : "Target Reparto",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL')
    || TO_CLOB('( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {[Reparto].[REP_01] , [Reparto].[REP_01_01], [Reparto].[REP_01_02], [Reparto].[REP_01_03], [Reparto].[REP_01_04], [Reparto].[REP_09], [Reparto].[REP_12], [Reparto].[REP_12_01], [Reparto].[REP_12_02], [Reparto].[REP_12_04]  },[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
 ')
    || TO_CLOB('     "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NET')
    || TO_CLOB('TO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/Canale", "Attributes/Riferimento", "Attributes/TipoElemento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "')
    || TO_CLOB('Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
    ')
    || TO_CLOB('  "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hi')
    || TO_CLOB('de" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "N_ART_PROMO" : {
          "columnGroupShow" : "always"
        },
        "TOT_FOTO" : {
          "columnGroupShow" : "always"
        },
        "SPZ_CAMP" : {
          "columnGroupShow" : "always"
        }
      }
    },
    "columnD')
    || TO_CLOB('efs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field"')
    || TO_CLOB(' : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Descrizione",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Tipo Elemento",
      "field" : "Reparto.TipoElemento",
      "width" : 70,
      "hide')
    || TO_CLOB('" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categ')
    || TO_CLOB('oria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : [ {
      "componentId" : "tim_scr_inizializza",
      "componentLabel" : "Inizializza Spazio",
      "process" : "MUI_DUMMY_CUB.Timone Reparto I')
    || TO_CLOB('nizializza Spazi",
      "timeout" : -1,
      "refresh" : [ "gc_SpaziCampagna_TargetReparto" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
        "label" : "Promozione",
        "hasPicklist" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('123','/Timone/Spazi_Campagna/Target_Reparto_(CAT)',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('124','/Timone/Spazi_Campagna/Target_Reparto_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SpaziCampagna_TargetReparto",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Reparto",
    "height" : 60,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "forceSuppression" : true,
    "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timon')
    || TO_CLOB('e.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }",
    "Promozione" : "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
    "MDX" : {
      "ROWS" : {
  ')
    || TO_CLOB('      "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{[Reparto].[MUI_Timone_Spazi_Rep_ACQ]}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario"')
    || TO_CLOB(' : " {[Scenario].[RIF_MKT_DT],[Scenario].[TGT_REP]}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{TM1DRILLDOWNMEMBER( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Misura Timone.] )}, ''TOT_FOTO'')}, ALL, RECURSIVE )},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{TM1DRILLDOWNMEMBER( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Misura Timone.] )}, ''SPZ_CAMP'')}, ALL, RECURSIVE )}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
     ')
    || TO_CLOB(' }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/Canale", "Attributes/Name", "Attributes/Riferimento", "Attributes/TipoElemento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
  ')
    || TO_CLOB('    "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TG')
    || TO_CLOB('T_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "chil')
    || TO_CLOB('drenCustomTypes" : {
        "N_ART_PROMO" : {
          "columnGroupShow" : "always"
        },
        "TOT_FOTO" : {
          "columnGroupShow" : "always"
        },
        "SPZ_CAMP" : {
          "columnGroupShow" : "always"
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, ')
    || TO_CLOB('{
      "headerName" : "Descrizione",
      "field" : "Promozione.Riferimento",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "fiel')
    || TO_CLOB('d" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Descrizione",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Reparto",
      "field" : "Reparto.Name",
      "width" : 120,
      "hide" : tru')
    || TO_CLOB('e,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Tipo Elemento",
      "field" : "Reparto.TipoElemento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_1" : "[''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Repar')
    || TO_CLOB('to.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento ==')
    || TO_CLOB(' ''R''"
    },
    "actions" : [ ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('125','/Timone/Spazi_Campagna/Target_Reparto_(ACQ)',' {
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('126','/Timone/Spazi_Campagna/Target_Categoria_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SpaziCampagna_TargetReparto",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : true,
    "forceSuppression" : true,
    "title" : "Target Reparto",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL')
    || TO_CLOB('( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {[Reparto].[REP_01] , [Reparto].[REP_01_01], [Reparto].[REP_01_02], [Reparto].[REP_01_03], [Reparto].[REP_01_04], [Reparto].[REP_09], [Reparto].[REP_12], [Reparto].[REP_12_01], [Reparto].[REP_12_02], [Reparto].[REP_12_04]  },[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
 ')
    || TO_CLOB('     "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NET')
    || TO_CLOB('TO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/Canale", "Attributes/Riferimento", "Attributes/TipoElemento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "')
    || TO_CLOB('Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
    ')
    || TO_CLOB('  "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hi')
    || TO_CLOB('de" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "N_ART_PROMO" : {
          "columnGroupShow" : "always"
        },
        "TOT_FOTO" : {
          "columnGroupShow" : "always"
        },
        "SPZ_CAMP" : {
          "columnGroupShow" : "always"
        }
      }
    },
    "columnD')
    || TO_CLOB('efs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field"')
    || TO_CLOB(' : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Descrizione",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Tipo Elemento",
      "field" : "Reparto.TipoElemento",
      "width" : 70,
      "hide')
    || TO_CLOB('" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categ')
    || TO_CLOB('oria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : [ {
      "componentId" : "tim_scr_inizializza",
      "componentLabel" : "Inizializza Spazio",
      "process" : "MUI_DUMMY_CUB.Timone Reparto I')
    || TO_CLOB('nizializza Spazi",
      "timeout" : -1,
      "refresh" : [ "gc_SpaziCampagna_TargetReparto" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
        "label" : "Promozione",
        "hasPicklist" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('127','/Timone/Spazi_Campagna/Target_Categoria_(CAT)','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager","repartodcodesc" , "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "repartodesc", "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('128','/Timone/Spazi_Campagna/Target_Categoria_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SpaziCampagna_TargetReparto",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Reparto",
    "height" : 60,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "forceSuppression" : true,
    "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timon')
    || TO_CLOB('e.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }",
    "Promozione" : "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
    "MDX" : {
      "ROWS" : {
  ')
    || TO_CLOB('      "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{[Reparto].[MUI_Timone_Spazi_Rep_ACQ]}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario"')
    || TO_CLOB(' : " {[Scenario].[RIF_MKT_DT],[Scenario].[TGT_REP]}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{TM1DRILLDOWNMEMBER( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Misura Timone.] )}, ''TOT_FOTO'')}, ALL, RECURSIVE )},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{TM1DRILLDOWNMEMBER( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Misura Timone.] )}, ''SPZ_CAMP'')}, ALL, RECURSIVE )}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
     ')
    || TO_CLOB(' }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/Canale", "Attributes/Name", "Attributes/Riferimento", "Attributes/TipoElemento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
  ')
    || TO_CLOB('    "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TG')
    || TO_CLOB('T_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "chil')
    || TO_CLOB('drenCustomTypes" : {
        "N_ART_PROMO" : {
          "columnGroupShow" : "always"
        },
        "TOT_FOTO" : {
          "columnGroupShow" : "always"
        },
        "SPZ_CAMP" : {
          "columnGroupShow" : "always"
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, ')
    || TO_CLOB('{
      "headerName" : "Descrizione",
      "field" : "Promozione.Riferimento",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "fiel')
    || TO_CLOB('d" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Descrizione",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Reparto",
      "field" : "Reparto.Name",
      "width" : 120,
      "hide" : tru')
    || TO_CLOB('e,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Tipo Elemento",
      "field" : "Reparto.TipoElemento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_1" : "[''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Repar')
    || TO_CLOB('to.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento ==')
    || TO_CLOB(' ''R''"
    },
    "actions" : [ ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('129','/Timone/Spazi_Campagna/Target_Categoria_(ACQ)','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager","repartodcodesc" , "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "repartodesc", "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('130','/Timone/Target_Categoria/Promo_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetCategoria_Promo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "forceSuppression" : true,
    "title" : "Target Categoria",
    "height" : 60,
    "MDX_Comment" : "Ã¿ stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) ",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,')
    || TO_CLOB('
        "DIMENSIONS" : {
          "Compratore" : "{{[Compratore].[S000]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{{[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBY')
    || TO_CLOB('PATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[S')
    || TO_CLOB('cenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]}}"
        }
      },
')
    || TO_CLOB('      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
  ')
    || TO_CLOB('      "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDef')
    || TO_CLOB('ault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
  ')
    || TO_CLOB('      "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ],
          "columnGroupShow" : "always",
          "aggFunc" : "weightedAvg",
  ')
    || TO_CLOB('        "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
  ')
    || TO_CLOB('    "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width')
    || TO_CLOB('" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.')
    || TO_CLOB('Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COM')
    || TO_CLOB('P'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compr')
    || TO_CLOB('atore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('131','/Timone/Target_Categoria/Promo_(CAT)','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager","repartodcodesc" , "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "repartodesc", "descrizione" ],
  "scenario" : [ "descrizione" ],
  "misuraTimone" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('132','/Timone/Target_Categoria/Promo_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetCategoria_Promo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 500000,
    "title" : "Target Categoria",
    "forceSuppression" : true,
    "height" : 60,
    "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) ",
    "MDX" : {
      "ROWS" : {
  ')
    || TO_CLOB('      "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{  {[Categoria].[CAT_0000]} , {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT(')
    || TO_CLOB(' {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione]')
    || TO_CLOB('.[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT')
    || TO_CLOB('_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]}}"
        }
      },
      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "C')
    || TO_CLOB('onsolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryCh')
    || TO_CLOB('ildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
     ')
    || TO_CLOB('   "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage"')
    || TO_CLOB(', "numericColumn" ],
          "columnGroupShow" : "always",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Elem')
    || TO_CLOB('ent" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
  ')
    || TO_CLOB('  }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
    ')
    || TO_CLOB('  "row_style_hidden" : "(data.Compratore.Name == ''S000'' && data.Categoria.Name == ''CAT_0000'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  ",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [')
    || TO_CLOB('''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compra')
    || TO_CLOB('tore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('133','/Timone/Target_Categoria/Promo_(ACQ)','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager","repartodcodesc" , "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "repartodesc", "descrizione" ],
  "scenario" : [ "descrizione" ],
  "misuraTimone" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('134','/Timone/Target_Categoria/Data_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetCategoria_Data",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "forceSuppression" : true,
    "title" : "Target Categoria (data)",
    "height" : 60,
    "MDX_Comment" : "Ã¿ stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) ",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" :')
    || TO_CLOB(' true,
        "DIMENSIONS" : {
          "Compratore" : "{{[Compratore].[S000]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{{[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FI')
    || TO_CLOB('LTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT')
    || TO_CLOB(']},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]} }"
        }
   ')
    || TO_CLOB('   },
      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" ')
    || TO_CLOB(': {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "op')
    || TO_CLOB('enByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "su')
    || TO_CLOB('m",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ],
          "columnGroupShow" : "always",
          "aggFunc" : "weightedAv')
    || TO_CLOB('g",
          "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizion')
    || TO_CLOB('e",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
     ')
    || TO_CLOB(' "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) ||')
    || TO_CLOB(' (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''')
    || TO_CLOB('TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (dat')
    || TO_CLOB('a.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('143','/Timone/Target_Reparto/Promo_(CAT)','{
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('144','/Timone/Target_Reparto/Promo_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetReparto_Promo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Reparto",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Pro')
    || TO_CLOB('mozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
          "Misura Timone." : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura ')
    || TO_CLOB('Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]},{[Misura Timone.].[MUI_AVG]}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/DataInizioIst", "Attributes/MUI_Descrizione", "Attributes/TipoElemento", "Attributes/Riferimento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "U')
    || TO_CLOB('pdateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : fal')
    || TO_CLOB('se,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Data",
      "field" : "Promozione.DataInizioIst",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" ')
    || TO_CLOB(': "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Descrizio')
    || TO_CLOB('ne",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento Data",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
  ')
    || TO_CLOB('      "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
')
    || TO_CLOB('        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn"')
    || TO_CLOB(' ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
     ')
    || TO_CLOB('   "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" :')
    || TO_CLOB(' "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Vendut')
    || TO_CLOB('o Promo (s/iva)",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "ML %",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
        "width" : 80,
        "hide" : fa')
    || TO_CLOB('lse,
        "rowGroup" : false,
        "aggFunc" : "customAvg",
        "aggFuncParam" : "$MUI_AVG",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target MKT",
      "headerClass" : "headerMkt",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
        "wi')
    || TO_CLOB('dth" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable"')
    || TO_CLOB(' : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT')
    || TO_CLOB('_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "s')
    || TO_CLOB('um",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ')
    || TO_CLOB(']
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target REP",
      "headerClass" : "headerRep",
      "openByDefault" : true,
      "children" :')
    || TO_CLOB(' [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : f')
    || TO_CLOB('alse,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1Da')
    || TO_CLOB('taColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_REP$$MisuraTimon')
    || TO_CLOB('e_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
   ')
    || TO_CLOB('     "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "')
    || TO_CLOB('headerName" : "D Foto/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
        "wi')
    || TO_CLOB('dth" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target Acq.",
      "headerClass" : "headerAcq",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
  ')
    || TO_CLOB('      "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
     ')
    || TO_CLOB('   "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTim')
    || TO_CLOB('one_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "')
    || TO_CLOB('columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
')
    || TO_CLOB('        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
        "width" : 80,
 ')
    || TO_CLOB('       "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
     ')
    || TO_CLOB('   "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    } ],
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : [ {
      "componentId" : "tim_trd_inizializza",
      "componentLabel" : "Inizializza Target",
      "process" : "MUI_DUMMY_CUB.TimoneRIF.TGT4",
      "timeout" : -1,
      "refresh" : [ "gc_TargetReparto_Promo" ],
      "params" : [ {
   ')
    || TO_CLOB('     "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
        "label" : "Promozione",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "Gruppo",
        "paramName" : "inGruppo",
        "label" : "Gruppo",
        "ha')
    || TO_CLOB('sPicklist" : true
      }, {
        "dimension" : "Reparto",
        "attribute" : "Descrizione",
        "paramName" : "inReparto",
        "label" : "Reparto",
        "hasPicklist" : true
      } ]
    } ],
    "css" : ".ag-row-group.ag-row-level-1{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('145','/Timone/Target_Reparto/Promo_(ACQ)','{
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('147','/Reporting/Storico_Articolo_Zona_Promo','{
  "rep_promozione" : [ "anno", "canale", "tipo", "descrizione", "canaleanno" ],
  "rep_articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc" ],
  "rep_fornitore" : [ "descrizione" ],
  "rep_zonaPromo" : [ "descrizione" ],
  "rep_sezioneTematica" : [ "descrizione" ],
  "rep_meccanicaSemplice" : [ "descrizione" ],
  "rep_avolantino" : [ "descrizione" ],
  "rep_MisuraReportingArticoloZona" : [ "descrizione" ],
  "rep_scenario" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('146','/Reporting/Storico_Articolo_Zona_Promo',TO_CLOB('{
  "connection" : "reporting",
  "show-version" : true,
  "configurations" : [ {
    "name" : "gc_StoricoArticoloZonaPromo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "compulsoryFilters" : [ "REP - Promozione" ],
    "compulsoryFiltersMessage" : "Per utilizzare questo form è necessario applicare almeno un filtro sulla dimensione Promozione",
    "title" : "Storico articolo zona promo",
    "css" : ".headerCons{background-color: #d')
    || TO_CLOB('efefe!important;}",
    "height" : 60,
    "REP - Fornitore" : "{[REP - Fornitore].[Fornitori]}",
    "REP - Compratore" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
    "REP - Zona Promo" : "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
    "REP - Sezione Tematica" : "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
    "REP - Meccanica Semplice" : "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
    "REP - AVolantino" : "{[REP - AVol')
    || TO_CLOB('antino].[AVolantino -BDGVend]}",
    "REP - Articolo" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "REP - Promozione" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          "REP - Fornitore" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}",
          "REP - Compratore" : "{[REP - Compr')
    || TO_CLOB('atore].[Compratori]}",
          "REP - Sezione Tematica" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
          "REP - Meccanica Semplice" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
          "REP - AVolantino" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}",
          "REP - Articolo" : "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , A')
    || TO_CLOB('SC)}",
          "REP - Zona Promo" : "{{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "REP - Scenario" : "{{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)} , ASC)}}",
          "REP - Misura Reporting Articolo Zona" : "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -Storico]}"
        }
      },
      "FRO')
    || TO_CLOB('M" : "[Reporting Articolo Zona]",
      "WHERE" : {
        "REP - Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Descrizione", "Attributes/Riferimento", "Attributes/DescrizioneArticolo", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "Descrizione", "Descrizione" ]')
    || TO_CLOB(',
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }')
    || TO_CLOB(',
        "BDG" : {
          "headerClass" : "headerBdg",
          "openByDefault" : true
        },
        "CONS" : {
          "headerClass" : "headerCons",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
 ')
    || TO_CLOB('     "childrenCustomTypes" : {
        "PRZ_ATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_PROMO" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "DELTA_%_SC" : {
          "type" : [ "TM1DataColumnIntegerPercentage" ],
          "cellClass" : "cellClass-right-text"
        }
      }
    },
    "autoGroupColumnDef" : { },
    "columnDe')
    || TO_CLOB('fs" : [ {
      "headerName" : "Compratore",
      "field" : "REP_minus_Compratore.Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Promozione",
      "field" : "REP_minus_Promozione.MUI_Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Rife')
    || TO_CLOB('rimento",
      "field" : "REP_minus_Promozione.Riferimento",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitori",
      "field" : "REP_minus_Fornitore.Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Articolo",
      "field" : "REP_minus_Artico')
    || TO_CLOB('lo.DescrizioneArticolo",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Meccanica",
      "field" : "REP_minus_MeccanicaSemplice.Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Sezione Tematica",
      "field" : "REP_minus_SezioneTematica.Descrizione"')
    || TO_CLOB(',
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "A Volantino",
      "field" : "REP_minus_AVolantino.Descrizione",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Zona Promo",
      "field" : "REP_minus_ZonaPromo.Descrizione",
      "width" : 70,
      "hide" : f')
    || TO_CLOB('alse,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "preSelections" : [ {
      "dimension" : "REP - Compratore",
      "dimCode" : "rep_compratore",
      "dimColumnName" : "REP - Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "ref')
    || TO_CLOB('resh" : [ "gc_StoricoArticoloZonaPromo" ]
    }, {
      "dimension" : "REP - Scenario",
      "dimCode" : "rep_scenario",
      "dimColumnName" : "REP - Scenario",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_StoricoArticoloZonaPromo" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('135','/Timone/Target_Categoria/Data_(CAT)','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager","repartodcodesc" , "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "repartodesc", "descrizione" ],
  "scenario" : [ "descrizione" ],
  "misuraTimone" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('136','/Timone/Target_Categoria/Data_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetCategoria_Data",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "forceSuppression" : true,
    "title" : "Target Categoria (data)",
    "height" : 60,
    "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) ",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : ')
    || TO_CLOB('true,
        "DIMENSIONS" : {
          "Compratore" : "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{    {[Categoria].[CAT_0000]} , {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL')
    || TO_CLOB('( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y')
    || TO_CLOB(''')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Ti')
    || TO_CLOB('mone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]} }"
        }
      },
      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "Ha')
    || TO_CLOB('sPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
 ')
    || TO_CLOB('     },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,')
    || TO_CLOB('
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn"')
    || TO_CLOB(' ],
          "columnGroupShow" : "always",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
')
    || TO_CLOB('
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "he')
    || TO_CLOB('aderName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidd')
    || TO_CLOB('en" : "(data.Compratore.Name == ''S000'' && data.Categoria.Name == ''CAT_0000'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_')
    || TO_CLOB('01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' &')
    || TO_CLOB('& [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('137','/Timone/Target_Categoria/Data_(ACQ)','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager","repartodcodesc" , "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "repartodesc", "descrizione" ],
  "scenario" : [ "descrizione" ],
  "misuraTimone" : [ "descrizione" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('138','/Timone/Target_Reparto/Data_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetReparto_Data",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Reparto (data)",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC')
    || TO_CLOB('),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
          "Misura Timone." : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[M')
    || TO_CLOB('isura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]},{[Misura Timone.].[MUI_AVG]}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/DataInizioIst", "Attributes/MUI_Descrizione", "Attributes/TipoElemento", "Attributes/Riferimento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Valu')
    || TO_CLOB('e", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable"')
    || TO_CLOB(' : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Data",
      "field" : "Promozione.DataInizioIst",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerN')
    || TO_CLOB('ame" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Desc')
    || TO_CLOB('rizione",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento Data",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art"')
    || TO_CLOB(',
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : fal')
    || TO_CLOB('se,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericCo')
    || TO_CLOB('lumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
')
    || TO_CLOB('        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupSh')
    || TO_CLOB('ow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "V')
    || TO_CLOB('enduto Promo (s/iva)",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "ML %",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
        "width" : 80,
        "hide"')
    || TO_CLOB(' : false,
        "rowGroup" : false,
        "aggFunc" : "customAvg",
        "aggFuncParam" : "$MUI_AVG",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
      }, {
        "headerName" : "FF C",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$CONTR",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
     ')
    || TO_CLOB('   "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "FF EC",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$EXTRA_CONTR",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target MKT",
      "h')
    || TO_CLOB('eaderClass" : "headerMkt",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_')
    || TO_CLOB('MKT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "colu')
    || TO_CLOB('mnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "header')
    || TO_CLOB('Name" : "N. Foto Speciali",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        ')
    || TO_CLOB('"hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
      ')
    || TO_CLOB('  "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target REP",
      "headerClass" : "headerRep",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ ')
    || TO_CLOB('"TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO"')
    || TO_CLOB(',
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open')
    || TO_CLOB('",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec')
    || TO_CLOB('. Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
       ')
    || TO_CLOB(' "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "')
    || TO_CLOB('TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target Acq.",
      "headerClass" : "')
    || TO_CLOB('headerAcq",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_ACQ$$MisuraTimon')
    || TO_CLOB('e_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "op')
    || TO_CLOB('en",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Sp')
    || TO_CLOB('eciali",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : true,
    ')
    || TO_CLOB('    "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataC')
    || TO_CLOB('olumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Rep",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Rep",
        "field" : "Scenario$TGT_REP$$Misur')
    || TO_CLOB('aTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    } ],
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : [ {
      "componentId" : "tim_trd_inizializza",
      "')
    || TO_CLOB('componentLabel" : "Inizializza Reference",
      "process" : "MUI_DUMMY_CUB.Timone.Inizializza Reference",
      "timeout" : -1,
      "refresh" : [ "gc_TargetReparto_Data" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
        "label"')
    || TO_CLOB(' : "Promozione",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "Gruppo",
        "paramName" : "inGruppo",
        "label" : "Gruppo",
        "hasPicklist" : true
      } ]
    } ],
    "css" : ".ag-row-group.ag-row-level-2{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('139','/Timone/Target_Reparto/Data_(CAT)',' {
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('140','/Timone/Target_Reparto/Data_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetReparto_Data",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Reparto (data)",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC')
    || TO_CLOB('),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
          "Misura Timone." : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[M')
    || TO_CLOB('isura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]},{[Misura Timone.].[MUI_AVG]}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/DataInizioIst", "Attributes/MUI_Descrizione", "Attributes/TipoElemento", "Attributes/Riferimento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Valu')
    || TO_CLOB('e", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable"')
    || TO_CLOB(' : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Data",
      "field" : "Promozione.DataInizioIst",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerN')
    || TO_CLOB('ame" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Desc')
    || TO_CLOB('rizione",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento Data",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art"')
    || TO_CLOB(',
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : fal')
    || TO_CLOB('se,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericCo')
    || TO_CLOB('lumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
')
    || TO_CLOB('        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupSh')
    || TO_CLOB('ow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "V')
    || TO_CLOB('enduto Promo (s/iva)",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "ML %",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
        "width" : 80,
        "hide"')
    || TO_CLOB(' : false,
        "rowGroup" : false,
        "aggFunc" : "customAvg",
        "aggFuncParam" : "$MUI_AVG",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target MKT",
      "headerClass" : "headerMkt",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
      ')
    || TO_CLOB('  "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "edit')
    || TO_CLOB('able" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenari')
    || TO_CLOB('o$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc"')
    || TO_CLOB(' : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericCol')
    || TO_CLOB('umn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target REP",
      "headerClass" : "headerRep",
      "openByDefault" : true,
      "childr')
    || TO_CLOB('en" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide')
    || TO_CLOB('" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "')
    || TO_CLOB('TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_REP$$Misura')
    || TO_CLOB('Timone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",')
    || TO_CLOB('
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
    ')
    || TO_CLOB('    "headerName" : "D Foto/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
      ')
    || TO_CLOB('  "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target Acq.",
      "headerClass" : "headerAcq",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150')
    || TO_CLOB(',
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
')
    || TO_CLOB('        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_ACQ$$Misu')
    || TO_CLOB('raTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
    ')
    || TO_CLOB('    "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }')
    || TO_CLOB(', {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
        "width" : 8')
    || TO_CLOB('0,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
')
    || TO_CLOB('        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    } ],
    "rowClassRules" : {
      "row_style_1" : "[''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Reparto.Name)"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : [ {
      "componentId" : "tim_trd_iniziali')
    || TO_CLOB('zza",
      "componentLabel" : "Inizializza Target",
      "process" : "MUI_DUMMY_CUB.TimoneRIF.TGT4",
      "timeout" : -1,
      "refresh" : [ "gc_TargetReparto_Data" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
        "label" : ')
    || TO_CLOB('"Promozione",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "Gruppo",
        "paramName" : "inGruppo",
        "label" : "Gruppo",
        "hasPicklist" : true
      }, {
        "dimension" : "Reparto",
        "attribute" : "Descrizione",
        "paramName" : "inReparto",
        "label" : "Reparto",
        "hasPicklist" : true
      } ]
    } ],
    "css" : ".ag-row-group.ag-row-level-2{background: #28aeff !important;}"
 ')
    || TO_CLOB(' } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('141','/Timone/Target_Reparto/Data_(ACQ)',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('142','/Timone/Target_Reparto/Promo_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetReparto_Promo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "title" : "Target Reparto",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promo')
    || TO_CLOB('zione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
          "Misura Timone." : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.')
    || TO_CLOB('] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[MUI_AVG]}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Canale", "Attributes/TipoElemento", "Attributes/MUI_Descrizione", "Attributes/Riferimento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
 ')
    || TO_CLOB('   "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
 ')
    || TO_CLOB('     "headerName" : "Descrizione",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : ')
    || TO_CLOB('"Reparto.Descrizione",
      "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Tipo Elemento",
      "field" : "Reparto.TipoElemento",
      "width" : 70,
      "hide" :')
    || TO_CLOB(' true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento Data",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [')
    || TO_CLOB(' "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$')
    || TO_CLOB('N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupSho')
    || TO_CLOB('w" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : ')
    || TO_CLOB('"N. Foto Spec. Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide')
    || TO_CLOB('" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Venduto Promo (s/iva)",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" :')
    || TO_CLOB(' true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "ML %",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "customAvg",
        "aggFuncParam" : "$MUI_AVG",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
      } ]
    }, {
    ')
    || TO_CLOB('  "headerName" : "Target MKT",
      "headerClass" : "headerMkt",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "To')
    || TO_CLOB('tale",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
 ')
    || TO_CLOB('       "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numer')
    || TO_CLOB('icColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE')
    || TO_CLOB('_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "op')
    || TO_CLOB('en",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target REP",
      "headerClass" : "headerRep",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
       ')
    || TO_CLOB(' "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Sce')
    || TO_CLOB('nario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "')
    || TO_CLOB('sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },')
    || TO_CLOB(' {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
        "width"')
    || TO_CLOB(' : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        ')
    || TO_CLOB('"editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" :')
    || TO_CLOB(' "Target Acq.",
      "headerClass" : "headerAcq",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        ')
    || TO_CLOB('"field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" :')
    || TO_CLOB(' "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      ')
    || TO_CLOB('}, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "wi')
    || TO_CLOB('dth" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editabl')
    || TO_CLOB('e" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Rep",
  ')
    || TO_CLOB('      "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    } ],
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : [ {
      "compon')
    || TO_CLOB('entId" : "tim_trp_inizializza",
      "componentLabel" : "Inizializza Reference",
      "process" : "MUI_DUMMY_CUB.Timone.Inizializza Reference",
      "timeout" : -1,
      "refresh" : [ "gc_TargetReparto_Promo" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        ')
    || TO_CLOB('"paramName" : "inPromo",
        "label" : "Promozione",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "Gruppo",
        "paramName" : "inGruppo",
        "label" : "Gruppo",
        "hasPicklist" : true
      } ]
    } ],
    "css" : ".ag-row-group.ag-row-level-1{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1079','/Pianificazione/Conferma_Prezzi/Conferma_Prezzi','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1080','/Pianificazione/Conferma_Prezzi/Conferma_Prezzi_MKT','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1040','/Pianificazione/Margine_Rettificato/Margine_Rettificato_S38','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1041','/Pianificazione/Margine_Rettificato/Margine_Rettificato_S38','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1083','/Pianificazione/Conferma_Prezzi/Conferma_Prezzi_Category','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1084','/Pianificazione/Report/Storico_Articoli_Pianificati','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1085','/Pianificazione/Report/Storico_Articoli_Pianificati','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1087','/Pianificazione/Report/Riepilogo_Piano_Promozioni','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1050','/Pianificazione/Report/Report_Contributi','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1051','/Pianificazione/Report/Report_Contributi','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1052','/Pianificazione/Report/Report_Delta_spazi','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1053','/Pianificazione/Report/Report_Delta_spazi','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1054','/Pianificazione/Report/Report_Spazi_Articoli','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1055','/Pianificazione/Report/Report_Spazi_Articoli','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1056','/Pianificazione/Report/Report_Spazi_Fornitore_Marketing','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1057','/Pianificazione/Report/Report_Spazi_Fornitore_Marketing','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1058','/Pianificazione/Report/Report_Spazi_Fornitore','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1059','/Pianificazione/Report/Report_Spazi_Fornitore','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1060','/Pianificazione/Sintesi/Sintesi_Campagna_-_Volantino','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1061','/Pianificazione/Sintesi/Sintesi_Campagna_-_Volantino','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1062','/Pianificazione/Sintesi/Sintesi_Campagna_Zona_(Marketing)','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1063','/Pianificazione/Sintesi/Sintesi_Campagna_Zona_(Marketing)','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1064','/Pianificazione/Sintesi/Sintesi_Dati_Panieri_e_Sconto_più_Facile','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1065','/Pianificazione/Sintesi/Sintesi_Dati_Panieri_e_Sconto_più_Facile','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1066','/Pianificazione/Sintesi/Sintesi_Campagna_(GAS)','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1067','/Pianificazione/Sintesi/Sintesi_Campagna_(GAS)','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1068','/Pianificazione/Sintesi/Sintesi_Campagna_Zona_(GAS_–_PAD)','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1069','/Pianificazione/Sintesi/Sintesi_Campagna_Zona_(GAS_–_PAD)','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1070','/Pianificazione/Sintesi/Sintesi_Campagna_Zona','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1071','/Pianificazione/Sintesi/Sintesi_Campagna_Zona','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1072','/Pianificazione/Sintesi/Sintesi_Campagna','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1073','/Pianificazione/Sintesi/Sintesi_Campagna','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1074','/Controlli/Controlli_Campagna_Buyer','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1075','/Controlli/Controlli_Campagna_Buyer','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1076','/Controlli/Sintesi_Campagna_','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1077','/Controlli/Sintesi_Campagna_','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1078','/Pianificazione/Conferma_Prezzi/Conferma_Prezzi','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1081','/Pianificazione/Conferma_Prezzi/Conferma_Prezzi_MKT','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1082','/Pianificazione/Conferma_Prezzi/Conferma_Prezzi_Category','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1086','/Pianificazione/Report/Riepilogo_Piano_Promozioni','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1088','/Pianificazione/Report/Impegno_Fornitore_(ridotto)','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1089','/Pianificazione/Report/Impegno_Fornitore_(ridotto)','{}','FILTER');


