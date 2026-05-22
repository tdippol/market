package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.HashSet;
import java.util.Set;

public class PromozionePianificazioneEntityBuilder {
    private Long id;
    private String codiceElemento;
    private String elemento;
    private String numRaggruppamento;
    private String numSet;
    private String tipoElemento;
    private CfgPianificazTipoRigaEntity tipoRiga;
    private MeccanicheEntity meccanica;
    private PromozioneTestataEntity promozioneTestata;
    private Set<PromozionePianificazioneEntity> details;

    public PromozionePianificazioneEntityBuilder(Long id) {
        this.id = id;
    }

    public PromozionePianificazioneEntityBuilder withCodiceElemento(String codiceElemento) {
        this.codiceElemento = codiceElemento;
        return this;
    }

    public PromozionePianificazioneEntityBuilder withElemento(String elemento) {
        this.elemento = elemento;
        return this;
    }

    public PromozionePianificazioneEntityBuilder withNumRaggruppamento(String numRaggruppamento) {
        this.numRaggruppamento = numRaggruppamento;
        return this;
    }

    public PromozionePianificazioneEntityBuilder withNumSet(String numSet) {
        this.numSet = numSet;
        return this;
    }

    public PromozionePianificazioneEntityBuilder withTipoElemento(String tipoElemento) {
        this.tipoElemento = tipoElemento;
        return this;
    }

    public PromozionePianificazioneEntityBuilder withTipoRiga(CfgPianificazTipoRigaEntity tipoRiga) {
        this.tipoRiga = tipoRiga;
        return this;
    }
    public PromozionePianificazioneEntityBuilder withTipoRiga(PlanningLevelEnum level) {
        this.tipoRiga = new CfgPianificazTipoRigaEntity();
        this.tipoRiga.setDescrizione(level.getDescription());
        this.tipoRiga.setCodiceTipo(level.getCode());
        return this;
    }
    public PromozionePianificazioneEntityBuilder withTipoRiga(PianificazioneRowTypeEnum level) {
        this.tipoRiga = new CfgPianificazTipoRigaEntity();
        this.tipoRiga.setDescrizione(level.getDescription());
        this.tipoRiga.setCodiceTipo(level.getTypeCode());
        return this;
    }    
     
    
    public PromozionePianificazioneEntityBuilder withMeccanica(MeccanicheEntity meccanica) {
        this.meccanica = meccanica;
        return this;
    }

    public PromozionePianificazioneEntityBuilder withPromozioneTestata(PromozioneTestataEntity promozioneTestata) {
        this.promozioneTestata = promozioneTestata;
        return this;
    }

    public PromozionePianificazioneEntityBuilder withDetail(PromozionePianificazioneEntity promozionePianificazione) {
        if (details == null) {
            details = new HashSet<>();
        }
        details.add(promozionePianificazione);
        return this;
    }

    public PromozionePianificazioneEntity build() {
        final PromozionePianificazioneEntity entity = new PromozionePianificazioneEntity();
        entity.setId(id);
        entity.setCodiceElemento(codiceElemento);
        entity.setElemento(elemento);
        entity.setNumRaggruppamento(numRaggruppamento);
        entity.setNumSet(numSet);
        entity.setTipoElemento(tipoElemento);
        entity.setTipoRiga(tipoRiga);
        entity.setMeccanicaEntity(meccanica);
        entity.setPromozioneTestataEntity(promozioneTestata);
        entity.setChildren(details);
        return entity;
    }
}
