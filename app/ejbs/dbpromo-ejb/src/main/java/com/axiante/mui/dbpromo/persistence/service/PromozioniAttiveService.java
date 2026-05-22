package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneAttiva;

import java.util.List;

public interface PromozioniAttiveService {
    List<PromozioneAttiva> findAll();

    boolean runDeletePromozione(String codicePromozione, String codiceUtente);
}
