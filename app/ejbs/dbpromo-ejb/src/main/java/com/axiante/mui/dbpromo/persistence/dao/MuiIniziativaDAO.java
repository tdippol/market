package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiIniziativaEntity;
import java.util.Date;
import java.util.List;

public interface MuiIniziativaDAO {
    List<MuiIniziativaEntity> findAllByDataInizioAndDataFine(Date dataInizio, Date dataFine);
}
