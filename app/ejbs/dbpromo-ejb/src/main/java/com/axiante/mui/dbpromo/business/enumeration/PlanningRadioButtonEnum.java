package com.axiante.mui.dbpromo.business.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PlanningRadioButtonEnum {

    TUTTO("ALL"),
    MASTER("MASTER");

    @Getter
    private String value;
}
