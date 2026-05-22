package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgCastellettoMessaggiComponentEntity;
import java.util.List;

public interface MuiCfgCastellettoMessaggiComponentDAO extends DbPromoDAO<MuiCfgCastellettoMessaggiComponentEntity> {
    List<MuiCfgCastellettoMessaggiComponentEntity> findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo);

    MuiCfgCastellettoMessaggiComponentEntity findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(Long codiceCanale, String codiceMeccanica, String codiceDispositivo, MessaggiComponentsEnum component);

    Long countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(Long codiceCanale, String codiceMeccanica, String codiceDispositivo, MessaggiComponentsEnum component);
}
