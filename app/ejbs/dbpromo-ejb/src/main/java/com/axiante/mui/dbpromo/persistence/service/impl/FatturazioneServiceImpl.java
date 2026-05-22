package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ContributiArticoliDAO;
import com.axiante.mui.dbpromo.persistence.dao.ContributiPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoArticoloEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.FatturazioneService;
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
public class FatturazioneServiceImpl extends AbstractDbPromoService<ContributiPromoEntity> implements FatturazioneService {
    private static final long serialVersionUID = 4399419840664550350L;

    @Inject
    @Getter
    ContributiPromoDAO dao;

    @Inject
    ContributiArticoliDAO articoliDAO;

    @Override
    public List<ContributiPromoEntity> findAllByPromozioni(List<PromozioneTestataEntity> promozioni) {
        if (promozioni == null || promozioni.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return dao.findAllByPromozioni(promozioni);
    }

    @Override
    public List<ContributiPromoEntity> findAllByPromozione(@NonNull PromozioneTestataEntity promozione) {
        return dao.findAllByPromozione(promozione);
    }

    @Override
    public Long countByPromozioneAndCompratoreAndFornitore(@NonNull PromozioneTestataEntity promozione,
                                                           @NonNull CompratoreEntity compratore,
                                                           @NonNull FornitoreEntity fornitore) {
        return dao.countByPromozioneAndCompratoreAndFornitore(promozione, compratore, fornitore);
    }

    @Override
    public List<Long> findAllItemsIdByPromozione(@NonNull PromozioneTestataEntity promozione) {
        return dao.findAllItemsIdByPromozione(promozione);
    }

    @Override
    public Integer nextProgressivoByPromozioneAndCompratoreAndFornitore(@NonNull PromozioneTestataEntity promozione,
                                                                        @NonNull CompratoreEntity compratore,
                                                                        @NonNull FornitoreEntity fornitore) {
        return dao.nextProgressivo(promozione, compratore, fornitore);
    }

    @Override
    public List<ContributiPromoArticoloEntity> findAllContributiArticoliByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return articoliDAO.findAllByIds(ids);
    }

    @Override
    public List<ContributiPromoArticoloEntity> findAllContributiArticoliByIdRata(@NonNull Long idRata) {
        return articoliDAO.findAllByIdRata(idRata);
    }
}
