package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;

public class CfgAbilitaMeccCanaleEntityBuilder {

    private Long id;
    private CanalePromozioneEntity canale;
    private MeccanicheEntity meccanica;

    public CfgAbilitaMeccCanaleEntityBuilder() {
    }

    public CfgAbilitaMeccCanaleEntityBuilder(Long id) {
        this.id = id;
    }

    public CfgAbilitaMeccCanaleEntityBuilder withCanale(CanalePromozioneEntity canale) {
        this.canale = canale;
        return this;
    }

    public CfgAbilitaMeccCanaleEntityBuilder withMeccanica(MeccanicheEntity meccanica) {
        this.meccanica = meccanica;
        return this;
    }

    public CfgAbilitaMeccCanaleEntity build() {
        final CfgAbilitaMeccCanaleEntity entity = new CfgAbilitaMeccCanaleEntity();
        entity.setId(id);
        entity.setCanalePromozioneEntity(canale);
        entity.setMeccanicheEntity(meccanica);
        return entity;
    }
}
