package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgIdMessaggiEntity;
import java.util.List;

public interface CfgIdMessaggiDAO extends DbPromoDAO<CfgIdMessaggiEntity> {
  CfgIdMessaggiEntity findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio(
      Long codiceCanale, String codiceMeccanica, String codiceDispositivo, Integer idMessaggio);

  List<CfgIdMessaggiEntity> findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
      Long codiceCanale, String codiceMeccanica, String codiceDispositivo);
}
