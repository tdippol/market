package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiIniziativaDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiIniziativaEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiIniziativaService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class MuiIniziativaServiceImpl implements MuiIniziativaService, Serializable {
    private static final long serialVersionUID = -8078185176823032847L;

    @Inject
    @Getter
    private MuiIniziativaDAO dao;

    @Override
    public List<MuiIniziativaEntity> findAllByDataInizioAndDataFine(@NonNull Date dataInizio, @NonNull Date dataFine) {
        return dao.findAllByDataInizioAndDataFine(dataInizio, dataFine);
    }
}
