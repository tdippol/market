package com.axiante.mui.webapp.webservice.util.pianificazione;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import java.util.function.Predicate;

public class PianificazioneEntityPredicates {

    public Predicate<PromozionePianificazioneEntity> byTipoRiga(PianificazioneRowTypeEnum rowtype) {
        return pianificazione -> rowtype.getTypeCode().equals(pianificazione.getTipoRiga().getCodiceTipo());
    }

    public Predicate<PromozionePianificazioneEntity> byMeccanica(MeccanicheEntity meccanica) {
        return pianificazione -> meccanica.equals(pianificazione.getMeccanicaEntity());
    }
}
