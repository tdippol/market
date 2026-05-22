package com.axiante.mui.webapp.utils;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import java.io.Serializable;
import java.util.Comparator;
import javax.enterprise.context.Dependent;

@Dependent
public class CanaleUtils implements Serializable {
    public static final long serialVersionUID = -4531156692944220314L;
    public Comparator<CanalePromozioneEntity> getComparatorByGruppo() {
        return Comparator.comparing(c -> c.getGruppoPromozioneEntity().getDescrizione());
    }

    public Comparator<CanalePromozioneEntity> getComparatorByCanale(){
        return Comparator.comparing(CanalePromozioneEntity::getDescrizione);
    }

}
