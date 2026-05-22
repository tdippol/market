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
