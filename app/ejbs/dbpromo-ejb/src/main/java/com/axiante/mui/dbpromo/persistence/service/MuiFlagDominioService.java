package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiFlagDominioEntity;
import java.util.List;

public interface MuiFlagDominioService extends DbPromoService<MuiFlagDominioEntity> {
    List<MuiFlagDominioEntity> findAllAttiviByFlag(Long idFlag);
    List<MuiFlagDominioEntity> findAllAttiviAndDefaultByFlag(Long idFlag);
}
