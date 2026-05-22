package com.axiante.mui.webapp.webservice.dto;

import lombok.Getter;

public enum EnabledDisabledFlag {
    ENABLED("Enabled"), DISABLED("Disabled");

    @Getter
    private final String value;

    EnabledDisabledFlag(String value) {
        this.value = value;
    }
}
