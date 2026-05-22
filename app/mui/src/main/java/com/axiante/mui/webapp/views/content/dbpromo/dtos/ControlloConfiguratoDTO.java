package com.axiante.mui.webapp.views.content.dbpromo.dtos;

import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.webapp.business.supportimedia.SupportoMediaCheckEnum;
import lombok.Getter;
import lombok.Setter;

public class ControlloConfiguratoDTO {
    @Getter
    private SupportoMediaCheckEnum checkEnum;

    @Getter
    @Setter
    private SupportoMediaEntity supportoMedia;

    @Getter
    @Setter
    private boolean abilitato = false;

    public ControlloConfiguratoDTO(SupportoMediaCheckEnum checkEnum) {
        this.checkEnum = checkEnum;
    }

    public void reset() {
        this.supportoMedia = null;
        this.abilitato = false;
    }
}
