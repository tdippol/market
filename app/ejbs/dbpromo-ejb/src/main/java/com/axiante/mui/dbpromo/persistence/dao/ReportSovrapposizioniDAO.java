package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniEntity;
import java.util.List;

public interface ReportSovrapposizioniDAO extends DbPromoDAO<ReportSovrapposizioniEntity> {
    List<ReportSovrapposizioniEntity> findAllByPromozioneAndCodiciCompratore(PromozioneTestataEntity testata,
                                                                             List<String> codiciCompratore);
}
