SET DEFINE OFF;
UPDATE MUIPROMO.MENU SET ORDER_ID = 4 WHERE ID_MENU = 60;
UPDATE MUIPROMO.MENU SET ORDER_ID = 5 WHERE ID_MENU = 49;
UPDATE MUIPROMO.MENU SET ORDER_ID = 6 WHERE ID_MENU = 63;
UPDATE MUIPROMO.MENU SET ORDER_ID = 7 WHERE ID_MENU = 31;

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

