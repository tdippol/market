package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPromoDbpromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
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
public class PianoMediaPromoDbpromoServiceImpl implements PianoMediaPromoDbpromoService, Serializable {
    private static final long serialVersionUID = 2846261171173522735L;

    @Inject
    @Getter
    private PianoMediaPromoDbpromoDAO dao;

    @Override
    public List<PianoMediaPromoDbpromoEntity> findByDataFineGreaterThanAndCanali(@NonNull Date date,
                                                                                 List<CanalePromozioneEntity> canali) {
        if (canali == null || canali.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return dao.findByDataFineGreaterThanAndCanali(date, canali);
    }

    @Override
    public PianoMediaPromoDbpromoEntity findByCodicePromo(@NonNull String codicePromo) {
        return dao.findByCodicePromo(codicePromo);
    }

    @Override
    public List<PianoMediaPromoDbpromoEntity> findAllByCodiciPromo(List<String> codiciPromo) {
        if (codiciPromo == null || codiciPromo.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return dao.findAllByCodiciPromo(codiciPromo);
    }

    @Override
    public List<PianoMediaPromoDbpromoEntity> findByDataFineGreaterThan(@NonNull Date date) {
        return dao.findByDataFineGreaterThan(date);
    }

}
