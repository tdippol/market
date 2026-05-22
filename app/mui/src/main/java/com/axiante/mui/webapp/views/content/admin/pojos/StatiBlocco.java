package com.axiante.mui.webapp.views.content.admin.pojos;

import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import lombok.Getter;
import lombok.Setter;

public class StatiBlocco {

    @Getter
    private StatoPromozioneEntity entity;

    @Getter
    @Setter
    private boolean available = false;

    public StatiBlocco(StatoPromozioneEntity entity) {
        this.entity = entity;
    }
}
