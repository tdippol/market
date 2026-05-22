DELETE FROM MUIPROMO.MENU WHERE LABEL LIKE 'Local Test' and URL = 'gridcontentStreamed.xhtml';
DELETE FROM MUIPROMO.MENU WHERE LABEL LIKE 'Google.com';
DELETE FROM MUIPROMO.MENU WHERE LABEL LIKE 'Local Test';
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES (94, 60, 'Associazione Articoli Promozione', 'fatturazione/associazioneArticoliPromozione.xhtml', 'agGrid', 4, 0);
