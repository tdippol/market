package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import java.util.List;

public interface TipoElementoService extends DbPromoService<CfgTipoElementoEntity> {
    List<String> findTipoElemento(Long meccanicaId, Long setPianificazioneId);
}
