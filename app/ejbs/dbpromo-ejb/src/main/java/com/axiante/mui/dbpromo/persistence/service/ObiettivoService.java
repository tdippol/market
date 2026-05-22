package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.ObiettivoEntity;

import java.util.List;

public interface ObiettivoService extends DbPromoService<ObiettivoEntity> {
    List<ObiettivoEntity> findAllByPromozione(Long idPromozione);
}
