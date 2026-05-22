package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgCastellettoMessaggiComponentEntity;
import java.util.List;

public interface MuiCfgCastellettoMessaggiComponentService extends DbPromoService<MuiCfgCastellettoMessaggiComponentEntity> {
    List<MuiCfgCastellettoMessaggiComponentEntity> findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo);
    boolean removeByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(Long codiceCanale, String codiceMeccanica, String codiceDispositivo, MessaggiComponentsEnum name);
    MuiCfgCastellettoMessaggiComponentEntity findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(Long codiceCanale, String codiceMeccanica, String codiceDispositivo, MessaggiComponentsEnum name);

}
