package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniEntity;
import java.util.List;

public interface ReportSovrapposizioniService  extends DbPromoService<ReportSovrapposizioniEntity> {
    List<ReportSovrapposizioniEntity> findAllByPromozioneAndCodiciCompratore(PromozioneTestataEntity testata,
                                                                             List<String> codiciCompratore);
}
