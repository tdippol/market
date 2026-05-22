package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgIdMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgIdMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgIdMessaggiService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Dependent
@Transactional
@Slf4j
public class CfgIdMessaggiServiceImpl extends AbstractDbPromoService<CfgIdMessaggiEntity>
        implements CfgIdMessaggiService {
    private static final long serialVersionUID = -6308805544679845201L;

    @Inject
    @Getter
    CfgIdMessaggiDAO dao;

    @Override
    public CfgIdMessaggiEntity findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio(
            Long codiceCanale, String codiceMeccanica, String codiceDispositivo, Integer idMessaggio) {
        return getDao()
                .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio(
                        codiceCanale, codiceMeccanica, codiceDispositivo, idMessaggio);
    }

    @Override
    public List<CfgIdMessaggiEntity> findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
            Long codiceCanale, String codiceMeccanica, String codiceDispositivo) {
        if (codiceCanale == null || codiceMeccanica == null || codiceDispositivo == null) {
            return Collections.emptyList();
        }
        return getDao()
                .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        codiceCanale, codiceMeccanica, codiceDispositivo);
    }
}
