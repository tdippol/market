package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianificazioneTotalizzatoriDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneTotalizzatoriEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneTotalizzatoriService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class PianificazioneTotalizzatoriServiceImpl extends AbstractDbPromoService<PianificazioneTotalizzatoriEntity>
        implements PianificazioneTotalizzatoriService {
    private static final long serialVersionUID = 2925239802547449850L;

    @Inject
    @Getter
    private PianificazioneTotalizzatoriDAO dao;

    @Override
    public List<PianificazioneTotalizzatoriEntity> findAllWithParentByIdIniziativa(Long idIniziativa) {
        return dao.findAllWithParentByIdPianificazione(idIniziativa);
    }
}
