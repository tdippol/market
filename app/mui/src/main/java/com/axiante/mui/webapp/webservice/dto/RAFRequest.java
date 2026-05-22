package com.axiante.mui.webapp.webservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class RAFRequest {
    private static final String TIMEZONE = "Europe/Rome";

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = TIMEZONE)
    private Date dataInizio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = TIMEZONE)
    private Date dataFine;
    private Integer decimali;
    private Long idIniziativa;
    private RafContent[] figli;
    private RafContent parent;
    @JsonIgnore
    private String method = "POST";
}
