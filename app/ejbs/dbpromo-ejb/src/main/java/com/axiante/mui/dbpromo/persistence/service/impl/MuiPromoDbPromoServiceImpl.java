package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiPromoDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiPromoDbPromoService;
import lombok.Getter;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Dependent
@Transactional
public class MuiPromoDbPromoServiceImpl implements MuiPromoDbPromoService, Serializable {
    private static final long serialVersionUID = -6375381255061209996L;

    @Inject
    @Getter
    MuiPromoDbPromoDAO dao;

    @Override
    public List<MuiPromoDbPromoEntity> findAll() {
        return getDao().findAll();
    }

    @Override
    public List<MuiPromoDbPromoEntity> findByGiorniSelezione(Integer giorniSelezione) {
        if ((giorniSelezione != null) && (giorniSelezione > 0)) {
            // calcolo la data minima da prendere=oggi - giorniSelezione
            LocalDate date = LocalDate.now().minusDays(giorniSelezione);
            return getDao().findByDataFineGreaterThan(
                    new java.sql.Date(
                            Date.from(
                                            date.atStartOfDay(ZoneId.systemDefault()).toInstant() // instant
                                    ) //java.util.date
                                    .getTime() //long
                    )//java.sql.date
            );
        } else {
            // non ho giorni selezione: ritorno tutto
            return findAll();
        }
    }

    @Override
    public MuiPromoDbPromoEntity findByCodicePromozione(@NonNull String codicePromozione) {
        return dao.findByCodicePromozione(codicePromozione);
    }
}
