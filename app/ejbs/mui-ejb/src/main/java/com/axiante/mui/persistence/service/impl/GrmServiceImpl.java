package com.axiante.mui.persistence.service.impl;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.GruppoGrmDAO;
import com.axiante.mui.persistence.service.GrmService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Slf4j
@Dependent
public class GrmServiceImpl implements GrmService {

    private static final long serialVersionUID = -7948758290017181310L;

    @Inject
    private Instance<GruppoGrmDAO> gruppoGrmDAOInstance;

    @Override
    public boolean hasAssociationWithIdGrmAndCodiciGruppo(@NonNull Integer idGrm, @NonNull List<String> codiciGruppo,
                                                          @NonNull PianificazioneSecurityEnum security) {
        if (codiciGruppo.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return false;
        }
        return PianificazioneSecurityEnum.WRITE.equals(security)
                ? gruppoGrmDAOInstance.get().countWritableByGrmIdAndCodiciGruppo(idGrm, codiciGruppo) > 0
                : gruppoGrmDAOInstance.get().countByGrmIdAndCodiciGruppo(idGrm, codiciGruppo) > 0;
    }

    @Override
    public List<String> findAllWritableCodiciGrmByCodiciGruppo(@NonNull List<String> codiciGruppo) {
        if (codiciGruppo.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return gruppoGrmDAOInstance.get().findAllByCodiciGruppoAndTipoAccesso(codiciGruppo, PianificazioneSecurityEnum.WRITE);
    }
}
