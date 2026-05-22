package com.axiante.mui.webapp.views.content.dbpromo.specialPromotion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class GestioneStatoSpecialPromotionBackingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String promozione;
    private String statoCorrente;
    private Long idStatoTransizioneCorrente;
}