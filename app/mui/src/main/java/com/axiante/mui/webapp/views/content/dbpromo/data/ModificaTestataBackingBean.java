package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.webapp.views.FacesContextAware;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class ModificaTestataBackingBean implements FacesContextAware {

    @Getter
    @Setter
    private boolean locked = true;

    @Getter
    @Setter
    private boolean btnLockedDisabled = false;

    @Getter
    @Setter
    private boolean btnDeleteDisabled = true;

    @Getter
    @Setter
    private boolean descriptionDisabled = true;

    @Getter
    @Setter
    private boolean startDateDisabled = true;

    @Getter
    @Setter
    private boolean endDateDisabled = true;

    @Getter
    @Setter
    private boolean startHourDisabled = true;

    @Getter
    @Setter
    private boolean endHourDisabled = true;

    @Getter
    @Setter
    private boolean notesDisabled = true;

    @Getter
    @Setter
    private boolean valorePuntoDisabled = true;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Date startDate;

    @Getter
    @Setter
    private Date endDate;

    @Getter
    @Setter
    private Date startHour;

    @Getter
    @Setter
    private Date endHour;

    @Getter
    @Setter
    private String notes;

    @Getter
    @Setter
    private BigDecimal valorePunto;

    @Getter
    private BigDecimal valorePuntoMaxValue;

    public ModificaTestataBackingBean(BigDecimal valorePuntoMaxValue) {
        this.valorePuntoMaxValue = valorePuntoMaxValue;
    }
}
