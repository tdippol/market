package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CheckCompratoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;

public class CheckCompratoriEntityBuilder {
    private Long id;
    private PromozioneTestataEntity promozione;
    private CompratoreEntity compratore;

    public CheckCompratoriEntityBuilder(Long id) {
        this.id = id;
    }

    public CheckCompratoriEntityBuilder withPromozione(PromozioneTestataEntity promozione) {
        this.promozione = promozione;
        return this;
    }

    public CheckCompratoriEntityBuilder withCompratore(CompratoreEntity compratore) {
        this.compratore = compratore;
        return this;
    }

    public CheckCompratoriEntity build() {
        CheckCompratoriEntity entity = new CheckCompratoriEntity();
        entity.setId(id);
        entity.setPromozioneTestataEntity(promozione);
        entity.setCompratoreEntity(compratore);
        return entity;
    }
}
