package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;

import java.util.List;

public interface MuiCfgDefaultCastellettoMessaggiDAO extends DbPromoDAO<MuiCfgDefaultCastellettoMessaggiEntity> {
    List<MuiCfgDefaultCastellettoMessaggiEntity> findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo);

    List<MuiCfgDefaultCastellettoMessaggiEntity> findByCodiceCanaleAndCodiceMeccanica(Long codiceCanale, String codiceMeccanica);

    Short findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo);

    Long countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo);

    List<MuiCfgDefaultCastellettoMessaggiEntity> findAllByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndSequenzaGreaterEqualThan(Long codiceCanale, String codiceMeccanica, String codiceDispositivo, Short seqStampa);
}
