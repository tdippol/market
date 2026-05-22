package com.axiante.mui.dbpromo.business.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PromoStatusFields {
    private boolean description;
    private boolean startDate;
    private boolean endDate;
    private boolean noteMarketing;
    private boolean isDeletable;
}
