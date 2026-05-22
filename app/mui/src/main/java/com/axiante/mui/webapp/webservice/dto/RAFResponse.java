package com.axiante.mui.webapp.webservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

public class RAFResponse extends RAFRequest {
    @Getter
    @Setter
    private Integer stato;

    @Getter
    @Setter
    @JsonIgnore
    private String statusLine;

    public Boolean isEmpty() {
        return getDataInizio() == null &&
                getDataFine() == null &&
                getDecimali() == null &&
                getIdIniziativa() == null &&
                getFigli() == null &&
                getParent() == null &&
                getStato() == null;

    }
}
