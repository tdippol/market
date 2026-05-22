package com.axiante.mui.webapp.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;

public class CfgPianificazioneEntityBuilder {
    private Long id;
    private String abilitaPerTipoElemento;
    private String descrizione;
    private String hide;
    private String lista;
    private String mandatory;
    private Integer ordinamento;
    private String sicurezza;
    private String tipoLista;
    private String uniquePeriodoMeccanica;
    private String uniquePromo;
    private String warn;
    private String defValue;
    private Short multiselect;
    private Integer length;
    private CfgConfHeaderEntity muiCfgConfHeader;
    private CfgPianificazTipoRigaEntity muiCfgPianificazTipoRiga;


    public CfgPianificazioneEntityBuilder(Long id) {
        this.id = id;
    }

    public CfgPianificazioneEntityBuilder withAbilitaPerTipoElemento(String abilitaPerTipoElemento) {
        this.abilitaPerTipoElemento = abilitaPerTipoElemento;
        return this;
    }

    public CfgPianificazioneEntityBuilder withDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public CfgPianificazioneEntityBuilder withHide(String hide) {
        this.hide = hide;
        return this;
    }

    public CfgPianificazioneEntityBuilder withLista(String lista) {
        this.lista = lista;
        return this;
    }

    public CfgPianificazioneEntityBuilder withMandatory(String mandatory) {
        this.mandatory = mandatory;
        return this;
    }

    public CfgPianificazioneEntityBuilder withOrdinamento(Integer ordinamento) {
        this.ordinamento = ordinamento;
        return this;
    }

    public CfgPianificazioneEntityBuilder withSicurezza(String sicurezza) {
        this.sicurezza = sicurezza;
        return this;
    }

    public CfgPianificazioneEntityBuilder withTipoLista(String tipoLista) {
        this.tipoLista = tipoLista;
        return this;
    }

    public CfgPianificazioneEntityBuilder withUniquePeriodoMeccanica(String uniquePeriodoMeccanica) {
        this.uniquePeriodoMeccanica = uniquePeriodoMeccanica;
        return this;
    }

    public CfgPianificazioneEntityBuilder withUniquePromo(String uniquePromo) {
        this.uniquePromo = uniquePromo;
        return this;
    }

    public CfgPianificazioneEntityBuilder withWarn(String warn) {
        this.warn = warn;
        return this;
    }

    public CfgPianificazioneEntityBuilder withDefValue(String defValue) {
        this.defValue = defValue;
        return this;
    }

    public CfgPianificazioneEntityBuilder withMultiselect(Short multiselect) {
        this.multiselect = multiselect;
        return this;
    }

    public CfgPianificazioneEntityBuilder withLength(Integer length) {
        this.length = length;
        return this;
    }
    
    public CfgPianificazioneEntityBuilder withMuiCfgConfHeader(CfgConfHeaderEntity muiCfgConfHeader) {    	
    	this.muiCfgConfHeader = muiCfgConfHeader;    	
		return this;    	
    }
    
    public CfgPianificazioneEntityBuilder withMuiCfgPianificazTipoRiga(CfgPianificazTipoRigaEntity muiCfgPianificazTipoRiga) {
    	this.muiCfgPianificazTipoRiga = muiCfgPianificazTipoRiga;
    	return this;
    }

    public CfgPianificazioneEntity build() {
        final CfgPianificazioneEntity entity = new CfgPianificazioneEntity();
        entity.setId(id);
        entity.setAbilitaPerTipoElemento(abilitaPerTipoElemento);
        entity.setDescrizione(descrizione);
        entity.setHide(hide);
        entity.setLista(lista);
        entity.setMandatory(mandatory);
        entity.setOrdinamento(ordinamento);
        entity.setSicurezza(sicurezza);
        entity.setTipoLista(tipoLista);
        entity.setUniquePeriodoMeccanica(uniquePeriodoMeccanica);
        entity.setUniquePromo(uniquePromo);
        entity.setWarn(warn);
        entity.setDefValue(defValue);
        entity.setMultiselect(multiselect);
        entity.setLength(length);
        entity.setMuiCfgConfHeader(muiCfgConfHeader);
        entity.setMuiCfgPianificazTipoRiga(muiCfgPianificazTipoRiga);
        return entity;
    }
}
