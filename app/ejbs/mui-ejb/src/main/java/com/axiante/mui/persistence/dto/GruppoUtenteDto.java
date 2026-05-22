package com.axiante.mui.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GruppoUtenteDto {
    private String codiceGruppo;
    private String descrizioneGruppo;
    private String codiceUtente;
    private String descrizioneUtente;
}
