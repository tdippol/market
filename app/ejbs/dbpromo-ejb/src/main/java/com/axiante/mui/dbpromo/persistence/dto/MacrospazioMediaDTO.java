package com.axiante.mui.dbpromo.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MacrospazioMediaDTO {

  private Long id;
  private String codice;
  private String descrizione;
  private Date dataInizio;
  private Date dataFine;
  private Date dataInserimento;
  private String codiceUtenteInserimento;
  private Date dataAggiornamento;
  private String codiceUtenteAggiornamento;
}
