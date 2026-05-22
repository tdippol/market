package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneAttiva;

import java.util.List;

public interface PromozioniAttiveDAO {
    List<PromozioneAttiva> findAll();

    boolean runDeletePromozione(String codicePromozione, String codiceUtente);
}
