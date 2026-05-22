package com.axiante.mui.webapp.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import java.util.HashSet;
import java.util.Set;

public class CfgConfHeaderEntityBuilder {
    private Long id;
    private Integer minSet;
    private Integer maxSet;
    private Integer minRaggruppamento;
    private Integer maxRaggruppamento;
    private Integer unicaInPromo;
    private CfgSetPianificazioneEntity cfgSetPianificazione;
    private MeccanicheEntity meccanica;
    private CfgLivelloPianificazioneEntity cfgLivelloPianificazione;
    private Set<CfgTipoElementoEntity> tipiElemento;
    private Set<CfgPianificazioneEntity> pianificazioni;

    public CfgConfHeaderEntityBuilder(Long id) {
        this.id = id;
    }

    public CfgConfHeaderEntityBuilder withMinSet(Integer minSet) {
        this.minSet = minSet;
        return this;
    }

    public CfgConfHeaderEntityBuilder withMaxSet(Integer maxSet) {
        this.maxSet = maxSet;
        return this;
    }

    public CfgConfHeaderEntityBuilder withMinRaggruppamento(Integer minRaggruppamento) {
        this.minRaggruppamento = minRaggruppamento;
        return this;
    }

    public CfgConfHeaderEntityBuilder withMaxRaggruppamento(Integer maxRaggruppamento) {
        this.maxRaggruppamento = maxRaggruppamento;
        return this;
    }

    public CfgConfHeaderEntityBuilder withUnicaInPromo(Integer unicaInPromo) {
        this.unicaInPromo = unicaInPromo;
        return this;
    }

    public CfgConfHeaderEntityBuilder withCfgSetPianificazione(CfgSetPianificazioneEntity cfgSetPianificazione) {
        this.cfgSetPianificazione = cfgSetPianificazione;
        return this;
    }

    public CfgConfHeaderEntityBuilder withMeccanica(MeccanicheEntity meccanica) {
        this.meccanica = meccanica;
        return this;
    }

    public CfgConfHeaderEntityBuilder withCfgLivelloPianificazione(CfgLivelloPianificazioneEntity cfgLivelloPianificazione) {
        this.cfgLivelloPianificazione = cfgLivelloPianificazione;
        return this;
    }

    public CfgConfHeaderEntityBuilder withTipoElemento(CfgTipoElementoEntity tipoElemento) {
        if (tipiElemento == null) {
            tipiElemento = new HashSet<>();
        }
        tipiElemento.add(tipoElemento);
        return this;
    }

    public CfgConfHeaderEntityBuilder withPianificazione(CfgPianificazioneEntity pianificazione) {
        if (pianificazioni == null) {
            pianificazioni = new HashSet<>();
        }
        pianificazioni.add(pianificazione);
        return this;
    }

    public CfgConfHeaderEntity build() {
        final CfgConfHeaderEntity entity = new CfgConfHeaderEntity();
        entity.setId(id);
        entity.setMinSet(minSet);
        entity.setMaxSet(maxSet);
        entity.setMinRaggruppamento(minRaggruppamento);
        entity.setMaxRaggruppamento(maxRaggruppamento);
        entity.setUnicaInPromo(unicaInPromo);
        entity.setMuiCfgSetPianificazione(cfgSetPianificazione);
        entity.setMeccanicaEntity(meccanica);
        entity.setLivelloPianificazione(cfgLivelloPianificazione);
        entity.setTipiElemento(tipiElemento);
        entity.setMuiCfgPianificaziones(pianificazioni);
        return entity;
    }
}
