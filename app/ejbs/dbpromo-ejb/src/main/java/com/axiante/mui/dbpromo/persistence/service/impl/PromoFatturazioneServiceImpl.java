package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromoFatturazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoFatturazioneService;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Dependent
@Transactional
@Slf4j
public class PromoFatturazioneServiceImpl extends AbstractDbPromoService<PromoFatturazioneEntity>
        implements PromoFatturazioneService {
    private static final long serialVersionUID = -4003663748597338450L;

    @Inject
    @Getter
    private PromoFatturazioneDAO dao;

    @Override
    public List<PromoFatturazioneEntity> findAllByIdsCompratori(List<Long> idsCompratori) {
        if (idsCompratori == null || idsCompratori.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return dao.findAllByIdsCompratori(idsCompratori);
    }

    @Override
    public List<PromoFatturazioneEntity> findAllByCodiciCompratori(List<String> idsCompratori) {
        if (idsCompratori == null || idsCompratori.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return dao.findAllByCodiciCompratori(idsCompratori);
    }

    @Override
    public List<PromoFatturazioneEntity> findAllByIdCompratoreAndIdPromozione(@NonNull Long idCompratore,
                                                                              @NonNull Long idPromozione) {
        return dao.findAllByIdCompratoreAndIdPromozione(idCompratore, idPromozione);
    }
}
