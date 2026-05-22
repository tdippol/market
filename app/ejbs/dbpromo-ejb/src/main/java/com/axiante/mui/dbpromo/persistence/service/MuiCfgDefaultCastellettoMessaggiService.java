package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;

import java.util.List;

public interface MuiCfgDefaultCastellettoMessaggiService extends DbPromoService<MuiCfgDefaultCastellettoMessaggiEntity> {
    List<MuiCfgDefaultCastellettoMessaggiEntity> findByCodiceCanaleAndCodiceMeccanica(Long codiceCanale, String codiceMeccanica);

    List<MuiCfgDefaultCastellettoMessaggiEntity> findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo);

    Short findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo);

    Long countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo);

    void addMessageAbove(MuiCfgDefaultCastellettoMessaggiEntity entity, Long id, String codiceUtente);
}
