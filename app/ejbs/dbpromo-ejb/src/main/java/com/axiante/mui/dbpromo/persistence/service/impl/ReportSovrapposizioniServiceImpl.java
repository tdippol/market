package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ReportSovrapposizioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniEntity;
import com.axiante.mui.dbpromo.persistence.service.ReportSovrapposizioniService;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Transactional
@Slf4j
public class ReportSovrapposizioniServiceImpl extends AbstractDbPromoService<ReportSovrapposizioniEntity>
        implements ReportSovrapposizioniService {
    private static final long serialVersionUID = 6866638615212235016L;

    @Inject
    @Getter
    private ReportSovrapposizioniDAO dao;

    @Override
    public List<ReportSovrapposizioniEntity> findAllByPromozioneAndCodiciCompratore(@NonNull PromozioneTestataEntity testata,
                                                                                    List<String> codiciCompratore) {
        if (codiciCompratore == null || codiciCompratore.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return dao.findAllByPromozioneAndCodiciCompratore(testata, codiciCompratore);
    }
}
