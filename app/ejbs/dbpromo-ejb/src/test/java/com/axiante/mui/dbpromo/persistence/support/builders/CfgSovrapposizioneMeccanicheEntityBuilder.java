package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSovrapposizioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;

public class CfgSovrapposizioneMeccanicheEntityBuilder {

    private CfgAbilitaMeccCanaleEntity meccanicaCanaleAbilitata;
    private MeccanicheEntity meccanicaSovrapposta;

    public CfgSovrapposizioneMeccanicheEntityBuilder withMeccanicaCanaleAbilitata(CfgAbilitaMeccCanaleEntity meccanicaCanaleAbilitata) {
        this.meccanicaCanaleAbilitata = meccanicaCanaleAbilitata;
        return this;
    }

    public CfgSovrapposizioneMeccanicheEntityBuilder withMeccanicaSovrapposta(MeccanicheEntity meccanicaSovrapposta) {
        this.meccanicaSovrapposta = meccanicaSovrapposta;
        return this;
    }

    public CfgSovrapposizioneMeccanicheEntity build() {
        final CfgSovrapposizioneMeccanicheEntity entity = new CfgSovrapposizioneMeccanicheEntity();
        entity.setMeccanicaCanaleAbilitata(meccanicaCanaleAbilitata);
        entity.setMeccanicaSovrapposta(meccanicaSovrapposta);
        return entity;
    }
}
