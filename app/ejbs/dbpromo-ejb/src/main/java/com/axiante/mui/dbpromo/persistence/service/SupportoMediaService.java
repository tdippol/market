package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import java.util.List;

public interface SupportoMediaService extends DbPromoService<SupportoMediaEntity> {
    boolean isCodeUsed(String codice);
    List<SupportoMediaEntity> findAllAttivi();
}
