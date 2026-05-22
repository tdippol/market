package com.axiante.mui.webapp.views.content.dbpromo.specialPromotion;

import com.axiante.mui.webapp.views.FacesContextAware;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class ModificaSpecialPromotionBackingBean implements Serializable, FacesContextAware {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private boolean locked;

    @Getter
    @Setter
    private boolean btnLockedDisabled;


    @Getter
    @Setter
    private Date reminderDate;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Date startDate;

    @Getter
    @Setter
    private Date endDate;

    public ModificaSpecialPromotionBackingBean() {
        this.locked = true;
        this.btnLockedDisabled = false;
    }
}