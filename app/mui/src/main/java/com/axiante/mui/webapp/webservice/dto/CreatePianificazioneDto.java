package com.axiante.mui.webapp.webservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePianificazioneDto {
    private Long resource;
    private String start;
    private String end;
}
