package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiCfgDefaultCastellettoMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgDefaultCastellettoMessaggiService;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Default
@Transactional
@Slf4j
public class MuiCfgDefaultCastellettoMessaggiServiceImpl extends AbstractDbPromoService<MuiCfgDefaultCastellettoMessaggiEntity>
        implements MuiCfgDefaultCastellettoMessaggiService {
    private static final long serialVersionUID = 438125213687329368L;

    @Getter
    @Inject
    MuiCfgDefaultCastellettoMessaggiDAO dao;

    @Override
    public List<MuiCfgDefaultCastellettoMessaggiEntity> findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
            @NonNull Long codiceCanale, @NonNull String codiceMeccanica, @NonNull String codiceDispositivo) {
        return getDao().findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(codiceCanale, codiceMeccanica, codiceDispositivo);
    }

    @Override
    public List<MuiCfgDefaultCastellettoMessaggiEntity> findByCodiceCanaleAndCodiceMeccanica(
            @NonNull Long codiceCanale, @NonNull String codiceMeccanica) {
        return getDao().findByCodiceCanaleAndCodiceMeccanica(codiceCanale, codiceMeccanica);
    }

    @Override
    public Short findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo) {
        return getDao().findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(codiceCanale, codiceMeccanica, codiceDispositivo);
    }

    @Override
    public Long countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo){
        return getDao().countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(codiceCanale, codiceMeccanica, codiceDispositivo);
    }

    @Override
    public void addMessageAbove(MuiCfgDefaultCastellettoMessaggiEntity entity, Long id, String codiceUtente) {
        MuiCfgDefaultCastellettoMessaggiEntity cfgDefault = getDao().findById(id);
        if (cfgDefault == null) {
            log.error(String.format("CfgDefaultCastellettoMessaggi with id %d not found", id));
            return;
        }
        getDao().findAllByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndSequenzaGreaterEqualThan(
                        cfgDefault.getCodiceCanale(), cfgDefault.getCodiceMeccanica(), cfgDefault.getCodiceDispositivo(), cfgDefault.getSeqStampa())
                .forEach(e -> {
                    e.setSeqStampa((short) (e.getSeqStampa() + 1));
                    persistWithAuditLog(e, codiceUtente);
                });
        persistWithAuditLog(entity, codiceUtente);
    }
}
