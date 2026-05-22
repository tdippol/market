package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CheckPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CheckPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CheckPianificazioneService;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
@Transactional
public class CheckPianificazioneServiceImpl extends AbstractDbPromoService<CheckPianificazioneEntity>
        implements CheckPianificazioneService {
    private static final long serialVersionUID = 1598441883484036836L;

    @Inject
    @Getter
    private CheckPianificazioneDAO dao;

    @Override
    public List<CheckPianificazioneEntity> findAllByPromozioneAndCodiciCompratore(@NonNull PromozioneTestataEntity testata,
                                                                                  List<String> codiciCompratore) {
        if (codiciCompratore == null || codiciCompratore.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return dao.findAllByPromozioneAndCodiciCompratore(testata, codiciCompratore);
    }
}
