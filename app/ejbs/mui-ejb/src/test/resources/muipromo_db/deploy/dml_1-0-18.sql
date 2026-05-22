UPDATE MUIPROMO.MENU SET LABEL = 'Pianificazione' WHERE LABEL = 'Proiezione Promo';
UPDATE MUIPROMO.MENU SET LABEL = 'Articoli Fittizi',URL = 'proiezionePromo/articoliFittizi.xhtml' WHERE LABEL = 'Articoli Fittizzi';
UPDATE MUIPROMO.MENU SET LABEL = 'Differenziazione Zone',URL = 'proiezionePromo/differenziazioneZone.xhtml' WHERE LABEL = 'Differenziazione Promo';

UPDATE MUIPROMO.MENU SET URL = 'pianificazione/selezionePromo.xhtml' WHERE LABEL = 'Selezione Promo';
UPDATE MUIPROMO.MENU SET URL = 'pianificazione/selezioneArticoliContributi.xhtml' WHERE LABEL = 'Selezione Articoli Contributi';
UPDATE MUIPROMO.MENU SET URL = 'pianificazione/articoliFittizi.xhtml' WHERE LABEL = 'Articoli Fittizi';
UPDATE MUIPROMO.MENU SET URL = 'pianificazione/proiezioni.xhtml' WHERE LABEL = 'Proiezioni';
UPDATE MUIPROMO.MENU SET URL = 'pianificazione/meccanicheSet.xhtml' WHERE LABEL = 'Meccaniche Set';
UPDATE MUIPROMO.MENU SET URL = 'pianificazione/selezioneFamiglieContributi.xhtml' WHERE LABEL = 'Selezione Famiglie Contributi';