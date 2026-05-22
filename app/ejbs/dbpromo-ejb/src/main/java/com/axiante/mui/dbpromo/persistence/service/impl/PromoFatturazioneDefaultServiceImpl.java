package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromoFatturazioneDefaultDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneDefaultEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoFatturazioneDefaultService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Dependent
@Transactional
@Slf4j
public class PromoFatturazioneDefaultServiceImpl extends AbstractDbPromoService<PromoFatturazioneDefaultEntity>
        implements PromoFatturazioneDefaultService {
    private static final long serialVersionUID = -4547095593019524378L;

    @Inject
    @Getter
    private PromoFatturazioneDefaultDAO dao;

    @Override
    public List<PromoFatturazioneDefaultEntity> findAllByIdsCompratori(List<Long> idsCompratori) {
        if (idsCompratori == null || idsCompratori.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return dao.findAllByIdsCompratori(idsCompratori);
    }
}
