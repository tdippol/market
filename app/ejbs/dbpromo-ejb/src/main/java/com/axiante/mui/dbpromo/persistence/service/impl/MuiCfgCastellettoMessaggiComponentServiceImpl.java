package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import com.axiante.mui.dbpromo.persistence.dao.MuiCfgCastellettoMessaggiComponentDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgCastellettoMessaggiComponentEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgCastellettoMessaggiComponentService;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Default
@Transactional
@Slf4j
public class MuiCfgCastellettoMessaggiComponentServiceImpl extends AbstractDbPromoService<MuiCfgCastellettoMessaggiComponentEntity> implements MuiCfgCastellettoMessaggiComponentService {

    private static final long serialVersionUID = 438125213687329368L;
    @Getter
    @Inject
    MuiCfgCastellettoMessaggiComponentDAO dao;

    @Override
    public List<MuiCfgCastellettoMessaggiComponentEntity> findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(@NonNull Long codiceCanale,
                                                                                                                   @NonNull String codiceMeccanica,
                                                                                                                   @NonNull String codiceDispositivo) {
        return getDao().findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(codiceCanale, codiceMeccanica, codiceDispositivo);
    }

    /**
     * @param codiceCanale
     * @param codiceMeccanica
     * @param codiceDispositivo
     * @param name
     * @return
     */
    @Override
    public boolean removeByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(Long codiceCanale, String codiceMeccanica, String codiceDispositivo, MessaggiComponentsEnum name) {
        Long count = getDao().countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(codiceCanale, codiceMeccanica, codiceDispositivo, name);
        if (count == 0)
            return false;
        if (count > 1) {
            log.error(
                    String.format("More than one component found with name %s for canale %d meccanica %s and dispositivo %s", name, codiceCanale, codiceMeccanica, codiceDispositivo)
            );
            return false;
        }
        MuiCfgCastellettoMessaggiComponentEntity e = getDao().findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(codiceCanale, codiceMeccanica, codiceDispositivo, name);
        try {
            getDao().remove(e);
        } catch (Exception ex) {
            log.error(String.format("Error removing component id %d ", e.getId()), e);
            return false;
        }
        return true;
    }

    @Override
    public MuiCfgCastellettoMessaggiComponentEntity findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(Long codiceCanale, String codiceMeccanica, String codiceDispositivo, MessaggiComponentsEnum name) {
        return getDao().findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(codiceCanale, codiceMeccanica, codiceDispositivo, name);
    }

}
