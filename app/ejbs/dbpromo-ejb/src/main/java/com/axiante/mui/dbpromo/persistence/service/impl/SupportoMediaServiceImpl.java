package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.SupportoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.SupportoMediaService;
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
public class SupportoMediaServiceImpl extends AbstractDbPromoService<SupportoMediaEntity>
        implements SupportoMediaService {
    private static final long serialVersionUID = -3651695504726975393L;

    @Inject
    @Getter
    private SupportoMediaDAO dao;

    @Override
    public boolean isCodeUsed(@NonNull String codice) {
        try {
            final SupportoMediaEntity entity = dao.findByCode(codice);
            return entity != null;
        } catch (Exception ex) {
            log.error(String.format("Error checking for used 'codiceMedia' %s", codice), ex);
            return true;
        }
    }

    @Override
    public List<SupportoMediaEntity> findAllAttivi() {
        return dao.findAllAttivi();
    }
}
