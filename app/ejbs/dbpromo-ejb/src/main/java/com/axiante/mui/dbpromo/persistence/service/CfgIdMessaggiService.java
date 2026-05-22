package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgIdMessaggiEntity;
import java.util.List;

public interface CfgIdMessaggiService extends DbPromoService<CfgIdMessaggiEntity> {
  CfgIdMessaggiEntity findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio(
      Long codiceCanale, String codiceMeccanica, String codiceDispositivo, Integer idMessaggio);

  List<CfgIdMessaggiEntity> findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
      Long codiceCanale, String codiceMeccanica, String codiceDispositivo);
}
