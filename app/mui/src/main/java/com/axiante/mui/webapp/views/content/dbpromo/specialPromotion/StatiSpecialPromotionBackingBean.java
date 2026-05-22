package com.axiante.mui.webapp.views.content.dbpromo.specialPromotion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class StatiSpecialPromotionBackingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String stato;
    private Date dataInizioStato;
    private Date dataFineStato;
    private String utente;
}