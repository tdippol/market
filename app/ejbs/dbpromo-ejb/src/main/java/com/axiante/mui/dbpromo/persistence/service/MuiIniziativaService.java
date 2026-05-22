package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiIniziativaEntity;
import java.util.Date;
import java.util.List;

public interface MuiIniziativaService {
    List<MuiIniziativaEntity> findAllByDataInizioAndDataFine(Date dataInizio, Date dataFine);
}
