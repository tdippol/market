package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PromozioneTestataEntityBuilder {
    private Long id;
    private String anno;
    private String codice;
    private Date dataInizio;
    private Date dataFine;
    private CanalePromozioneEntity canale;
    private CfgSetPianificazioneEntity cfgSetPianificazione;
    private Set<PromozioneStatoEntity> promozioneStati;
    private Set<PromozionePianificazioneEntity> pianificazioni;

    public PromozioneTestataEntityBuilder(Long id) {
        this.id = id;
    }

    public PromozioneTestataEntityBuilder withAnno(String anno) {
        this.anno = anno;
        return this;
    }

    public PromozioneTestataEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public PromozioneTestataEntityBuilder withDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
        return this;
    }

    public PromozioneTestataEntityBuilder withDataFine(Date dataFine) {
        this.dataFine = dataFine;
        return this;
    }

    public PromozioneTestataEntityBuilder withCanale(CanalePromozioneEntity canale) {
        this.canale = canale;
        return this;
    }

    public PromozioneTestataEntityBuilder withCfgSetPianificazione(CfgSetPianificazioneEntity cfgSetPianificazione) {
        this.cfgSetPianificazione = cfgSetPianificazione;
        return this;
    }

    public PromozioneTestataEntityBuilder withPromozioneStato(PromozioneStatoEntity promozioneStato) {
        if (this.promozioneStati == null) {
            this.promozioneStati = new HashSet<>();
        }
        promozioneStati.add(promozioneStato);
        return this;
    }

    public PromozioneTestataEntityBuilder withPromozioneStati(Set<PromozioneStatoEntity> promozioneStati) {
        this.promozioneStati = promozioneStati;
        return this;
    }

    public PromozioneTestataEntityBuilder withPianificazione(PromozionePianificazioneEntity pianificazione) {
        if (pianificazioni == null) {
            pianificazioni = new HashSet<>();
        }
        pianificazioni.add(pianificazione);
        return this;
    }

    public PromozioneTestataEntity build() {
        final PromozioneTestataEntity entity = new PromozioneTestataEntity();
        entity.setId(id);
        entity.setAnno(anno);
        entity.setCodicePromozione(codice);
        entity.setDataInizio(dataInizio);
        entity.setDataFine(dataFine);
        entity.setCanalePromozioneEntity(canale);
        entity.setMuiCfgSetPianificazione(cfgSetPianificazione);
        entity.setPromozioneStatoEntities(promozioneStati);
        entity.setPromozionePianificazioneEntities(pianificazioni);
        return entity;
    }
}
