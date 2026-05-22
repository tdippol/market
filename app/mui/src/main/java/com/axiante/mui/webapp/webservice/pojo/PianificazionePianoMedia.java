package com.axiante.mui.webapp.webservice.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
public class PianificazionePianoMedia {
    @Setter
    private Long id;

    @Setter
    private Long resource;

    @Setter
    private String start;

    @Setter
    private String end;

    @Setter
    private String text;

    @Setter
    private String barColor;

    @Setter
    private Boolean deleteDisabled = Boolean.FALSE;

    @Setter
    private Boolean moveDisabled = Boolean.FALSE;

    @Setter
    private Boolean resizeDisabled = Boolean.FALSE;

    private Boolean moveVDisabled = Boolean.TRUE;
}
