package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ReportVendutoDAO;
import com.axiante.mui.dbpromo.persistence.entities.ReportVendutoEntity;
import com.axiante.mui.dbpromo.persistence.service.ReportVendutoService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class ReportVendutoServiceImpl implements ReportVendutoService, Serializable {
    private static final long serialVersionUID = 1945623772130776563L;

    @Inject
    @Getter
    private ReportVendutoDAO dao;

    @Override
    public List<ReportVendutoEntity> findAllByIdPromozione(@NonNull Long idPromozione) {
        return dao.findAllByIdPromozione(idPromozione);
    }
}
