package com.axiante.mui.webapp.views.content.admin.pojos;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import lombok.Getter;
import lombok.Setter;

public class GruppoCanalePojo {
    @Getter
    @Setter
    private GruppoPromozioneEntity group;

    @Getter
    @Setter
    private CanalePromozioneEntity channel;

    public GruppoCanalePojo() {
    }

    public GruppoCanalePojo(GruppoPromozioneEntity group, CanalePromozioneEntity channel) {
        this.group = group;
        this.channel = channel;
    }
}
