package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PianificazioneTotalizzatoriEntity;
import java.util.List;

public interface PianificazioneTotalizzatoriService extends DbPromoService<PianificazioneTotalizzatoriEntity> {
    List<PianificazioneTotalizzatoriEntity> findAllWithParentByIdIniziativa(Long idIniziativa);
}
