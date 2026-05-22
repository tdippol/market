package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;

import java.util.Date;
import java.util.List;

public interface PromozioneTestataService extends DbPromoService<PromozioneTestataEntity> {
    List<PromozioneTestataEntity> findByCanaleMeccanicheDate(Long idCanale, List<String> codiciMeccaniche,
                                                             Date dataInizio, Date dataFine);

    PromozioneTestataEntity findByIdFullEagerFetch(Long id);
}
