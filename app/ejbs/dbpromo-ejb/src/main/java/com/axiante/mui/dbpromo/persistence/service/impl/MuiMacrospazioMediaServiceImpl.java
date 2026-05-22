package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiMacrospazioMediaDAO;
import com.axiante.mui.dbpromo.persistence.dto.MacrospazioWithEventsDTO;
import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiMacrospazioMediaService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
@Transactional
@Slf4j
public class MuiMacrospazioMediaServiceImpl extends AbstractDbPromoService<MuiMacrospazioMediaEntity>
        implements MuiMacrospazioMediaService {
    private static final long serialVersionUID = -904815945579205770L;
    @Inject @Getter MuiMacrospazioMediaDAO dao;

    @Override
    public boolean existsByCodiceMacrospazio(String codiceMacrospazio) {
        if (codiceMacrospazio == null) {
            return false;
        }
        Long count = dao.countByCodiceMacrospazio(codiceMacrospazio);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByCodiceMacrospazio(String codiceMacrospazio, List<Long> excludedIds) {
        if (excludedIds == null || excludedIds.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return existsByCodiceMacrospazio(codiceMacrospazio);
        }
        if (codiceMacrospazio == null) {
            return false;
        }
        Long count = dao.countByCodiceMacrospazio(codiceMacrospazio, excludedIds);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByDescrizioneMacrospazio(String descrizioneMacrospazio) {
        if (descrizioneMacrospazio == null) {
            return false;
        }
        Long count = dao.countByDescrizioneMacrospazio(descrizioneMacrospazio);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByDescrizioneMacrospazio(String descrizioneMacrospazio, List<Long> excludedIds) {
        if (excludedIds == null || excludedIds.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return existsByDescrizioneMacrospazio(descrizioneMacrospazio);
        }
        if (descrizioneMacrospazio == null) {
            return false;
        }
        Long count = dao.countByDescrizioneMacrospazio(descrizioneMacrospazio, excludedIds);
        return count != null && count > 0;
    }

    @Override
    public List<MacrospazioWithEventsDTO> findAllWithHasEvents() {
        return dao.findAllWithHasEvents();
    }

    @Override
    public void confermaMacrospazi(String codiceUtente){
        if (codiceUtente == null) {
            throw new IllegalArgumentException("codiceUtente can not be null");
        }
        getDao().confermaMacrospazi(codiceUtente);
    }
}
