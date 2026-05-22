package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneMarchioPrivatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMarchioPrivatoEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozioneMarchioPrivatoService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class PromozioneMarchioPrivatoServiceImpl extends AbstractDbPromoService<PromozioneMarchioPrivatoEntity>
        implements PromozioneMarchioPrivatoService {
    private static final long serialVersionUID = 6705037059457220870L;

    @Inject
    @Getter
    PromozioneMarchioPrivatoDAO dao;

    @Override
    public List<PromozioneMarchioPrivatoEntity> findByIdPromozione(Long idPromozione) {
        if ( idPromozione == null ) throw new IllegalArgumentException("idPromozione cannot be null");
        return getDao().findByIdPromozione(idPromozione);
    }
}
