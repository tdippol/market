package com.axiante.mui.webapp.views.content.admin.pojos;

import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import lombok.Getter;
import lombok.Setter;

public class StatoCanalePojo {
    @Getter
    @Setter
    private StatoPromozioneEntity stato;

    @Getter
    @Setter
    private boolean available;

    public StatoCanalePojo() {
    }

    public StatoCanalePojo(StatoPromozioneEntity stato, boolean available) {
        this.stato = stato;
        this.available = available;
    }
}
