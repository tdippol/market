--Remove Meccaniche set complesse
SET DEFINE OFF;
DELETE FROM MUIPROMO.MENU_ROLES WHERE ID_MENU = 7;
DELETE FROM MUIPROMO.MENU WHERE ID_MENU = 7;

UPDATE MUIPROMO.MENU SET BEAN = 'creaPromozione' WHERE ID_MENU = 4;
UPDATE MUIPROMO.MENU SET BEAN = 'modificaPromozione' WHERE ID_MENU = 3;
UPDATE MUIPROMO.MENU SET URL = null, BEAN = null WHERE ID_MENU = 9;
UPDATE MUIPROMO.MENU SET BEAN = 'agGrid' WHERE ID_MENU = 39;
UPDATE MUIPROMO.MENU SET BEAN = 'creaSezioniTematiche' WHERE ID_MENU = 40;
UPDATE MUIPROMO.MENU SET BEAN = 'gabbia' WHERE ID_MENU = 16;
UPDATE MUIPROMO.MENU SET BEAN = 'macroSpazi' WHERE ID_MENU = 42;

UPDATE MUIPROMO.MENU SET EXTERNAL_LINK = 1, URL = 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.1%20Piano%20Annuale/Controllo%20Negozi.xlsx&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo' WHERE ID_MENU = 12;
SET DEFINE ON;


UPDATE MUIPROMO.MENU SET BEAN = 'spaziCampagneReparto' WHERE ID_MENU = 29;
UPDATE MUIPROMO.MENU SET BEAN = 'targetRepartoPromo' WHERE ID_MENU = 20;
UPDATE MUIPROMO.MENU SET BEAN = 'targetRepartoData' WHERE ID_MENU = 21;

DELETE FROM MUIPROMO.MENU_ROLES WHERE ID_MENU = 10;
DELETE FROM MUIPROMO.MENU WHERE ID_MENU = 10;

UPDATE MUIPROMO.MENU SET ORDER_ID = 4 WHERE ID_MENU = 31;
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (49, null, 'Reporting', null, null, 3, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (50, 49, 'Analisi Budget-Venduto', null, null, 1, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (51, 50, 'Categoria', 'reporting/analisiBudget-Venduto/categoria.xhtml', 'agGrid', 1, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (52, 50, 'Articolo', 'reporting/analisiBudget-Venduto/articolo.xhtml', 'agGrid', 2, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (53, 49, 'Storico articolo per zona (ACQ)', 'reporting/storicoArticoloPerZonaAcq.xhtml', 'agGrid', 2, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (54, 49, 'Copia in Pianificazione', 'reporting/copiaInPianificazione.xhtml', 'copiaInPianificazione', 3, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (55, 49, 'Sintesi Campagna', 'reporting/sintesiCampagna.xhtml', 'agGrid', 4, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (56, 49, 'Dettaglio Campagna', 'reporting/dettaglioCampagna.xhtml', 'agGrid', 5, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (57, 49, 'Dettaglio Zona', 'reporting/dettaglioZona.xhtml', 'agGrid', 6, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (58, 49, 'Timone Reparto', 'reporting/timoneReparto.xhtml', 'agGrid', 7, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (59, 49, 'Storico articolo per zona (MKT)', 'reporting/storicoArticoloPerZonaMkt.xhtml', 'agGrid', 8, 0);

UPDATE MUIPROMO.MENU SET ORDER_ID = 6 WHERE ID_MENU = 31;
UPDATE MUIPROMO.MENU SET ORDER_ID = 4 WHERE ID_MENU = 49;
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (60, null, 'Fatturazione', null, null, 3, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (61, 60, 'Contribuzione Campagna', 'fatturazione/contribuzioneCampagna.xhtml', 'agGrid', 1, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (62, 60, 'Associazioni Articoli', 'fatturazione/associazioneArticoli.xhtml', 'agGrid', 2, 0);

SET DEFINE OFF;
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (63, null, 'Reporting TM1', null, null, 5, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (64, 63, 'Analisi Budget-Venduto', null, null, 1, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (65, 64, 'Categoria', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=CubeViewer&Cube=Timone%20Reporting&View=(I)%20Timone%20Reporting%20-%20Buyer&AccessType=Public&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo_Reporting', null, 1, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (66, 64, 'Articolo', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=CubeViewer&Cube=Reporting%20Articolo%20Zona&View=(I)%20Analisi%20BudgetVendutoArticolo&AccessType=Public&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo_Reporting', null, 2, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (67, 63, 'Storico articolo per zona (ACQ)', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=CubeViewer&Cube=Reporting%20Articolo%20Zona&View=(I)%20Storico%20articolo%20per%20zona&AccessType=Public&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo_Reporting', null, 2, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (68, 63, 'Copia in Pianificazione', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=CubeViewer&Cube=Reporting%20Articolo&View=(I)%20Copia%20in%20pianificazione&AccessType=Public&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo_Reporting', null, 3, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (69, 63, 'Sintesi Campagna', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=CubeViewer&Cube=Timone%20Reporting&View=(I)%20Timone%20Reporting%20-%20Sintesi Campagna&AccessType=Public&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo_Reporting', null, 4, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (70, 63, 'Dettaglio Campagna', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=CubeViewer&Cube=Reporting%20Articolo&View=(I)%20Dettaglio%20Campagna&AccessType=Public&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo_Reporting', null, 5, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (71, 63, 'Dettaglio Zona', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=CubeViewer&Cube=Reporting%20Articolo%20Zona&View=(I)%20Dettaglio%20Zona&AccessType=Public&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo_Reporting', null, 6, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (72, 63, 'Timone Reparto', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=CubeViewer&Cube=Timone%20Reparto&View=(II)%20Timone%20Reparto&AccessType=Public&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo_Reporting', null, 7, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (73, 63, 'Storico articolo per zona (MKT)', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=CubeViewer&Cube=Reporting%20Articolo%20Zona&View=(I)%20Storico%20articolo%20per%20zona%20MKT&AccessType=Public&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo_Reporting', null, 8, 1);
SET DEFINE ON;

UPDATE MUIPROMO.MENU SET ORDER_ID = 4 WHERE ID_MENU = 60;
UPDATE MUIPROMO.MENU SET ORDER_ID = 5 WHERE ID_MENU = 49;
UPDATE MUIPROMO.MENU SET ORDER_ID = 6 WHERE ID_MENU = 63;
UPDATE MUIPROMO.MENU SET ORDER_ID = 7 WHERE ID_MENU = 31;
SET DEFINE OFF;
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (74, null, 'Proiezione Promo', null, null, 3, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (75, 74, 'Selezione Promo', 'proiezionePromo/selezionePromo.xhtml', 'agGrid', 1, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (76, 74, 'Selezione Articoli Contributi', 'proiezionePromo/selezioneArticoliContributi.xhtml', 'agGrid', 2, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (77, 74, 'Articoli Fittizzi', 'proiezionePromo/articoliFittizzi.xhtml', 'agGrid', 3, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (78, 74, 'Proiezioni', 'proiezionePromo/proiezioni.xhtml', 'agGrid', 4, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (79, 74, 'Differenziazione Promo', 'proiezionePromo/differenziazionePromo.xhtml', 'agGrid', 5, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (80, 74, 'Meccaniche Set', 'proiezionePromo/meccanicheSet.xhtml', 'agGrid', 6, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (81, 74, 'Configurazione Spazi Negozi', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Report%20Configurazione%20Spazi%20Negozi&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo', null, 7, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (82, 74, 'Report Note', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Report%20Note%20Spazi&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo', null, 8, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (83, 74, 'Controlli e Pubblicazione', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/x)%20Controlli/Controlli%20Campagna%20Buyer%20CDD.xlsx&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo', null, 9, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (84, 74, 'Margine Rettificato', null, null, 10, 0);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (85, 84, 'Margine Rettificato', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Pianificazione%20Campagna (Mrg%20rettificato)&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo', null, 1, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (86, 84, 'Margine Rettificato V2', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Pianificazione%20Campagna (Mrg%20rettificato)%20v.2&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo', null, 2, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (87, 84, 'Margine Rettificato Multicampagna', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Pianificazione%20Fornitore%20-%20Multi Campagna%20(Mrg%20rettificato)&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo', null, 3, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (88, 74, 'Storico Articoli Pianificati', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Storico%20articoli%20pianificati.xlsx&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo', null, 11, 1);
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (89, 74, 'Riepilogo Piano Promozioni', 'http://tmonetest.mil.esselunga.net:9510/tm1web/UrlApi.jsp#Action=Open&Type=WebSheet&Workbook=Applications/.4%20Proiezione%20Promo/Riepilogo%20Piano%20Promozioni.xlsx&AdminHost=tmonetest.mil.esselunga.net&TM1Server=Promo', null, 12, 1);
SET DEFINE ON;

UPDATE MUIPROMO.MENU SET ORDER_ID = 16 WHERE ID_MENU = 89;
UPDATE MUIPROMO.MENU SET ORDER_ID = 15 WHERE ID_MENU = 88;
UPDATE MUIPROMO.MENU SET ORDER_ID = 14 WHERE ID_MENU = 87;
UPDATE MUIPROMO.MENU SET ORDER_ID = 13 WHERE ID_MENU = 86;
UPDATE MUIPROMO.MENU SET ORDER_ID = 12 WHERE ID_MENU = 85;
UPDATE MUIPROMO.MENU SET ORDER_ID = 11 WHERE ID_MENU = 84;
UPDATE MUIPROMO.MENU SET ORDER_ID = 10 WHERE ID_MENU = 83;
UPDATE MUIPROMO.MENU SET ORDER_ID = 9 WHERE ID_MENU = 82;
UPDATE MUIPROMO.MENU SET ORDER_ID = 8 WHERE ID_MENU = 81;
UPDATE MUIPROMO.MENU SET ORDER_ID = 7 WHERE ID_MENU = 80;
UPDATE MUIPROMO.MENU SET ORDER_ID = 6 WHERE ID_MENU = 79;
UPDATE MUIPROMO.MENU SET ORDER_ID = 5 WHERE ID_MENU = 78;
UPDATE MUIPROMO.MENU SET ORDER_ID = 4 WHERE ID_MENU = 77;

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (90, 74, 'Selezione Famiglie Contributi', 'proiezionePromo/selezioneFamiglieContributi.xhtml', 'agGrid', 3, 0);