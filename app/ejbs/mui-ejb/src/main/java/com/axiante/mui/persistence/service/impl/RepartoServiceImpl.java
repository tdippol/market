package com.axiante.mui.persistence.service.impl;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.GruppoRepartoDAO;
import com.axiante.mui.persistence.service.RepartoService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Slf4j
@Dependent
public class RepartoServiceImpl implements RepartoService {

    private static final long serialVersionUID = 2046400318498749477L;

    @Inject
    private Instance<GruppoRepartoDAO> gruppoRepartoDAOInstance;

    @Override
    public boolean hasAssociationWithIdRepartoAndCodiciGruppo(@NonNull Integer idReparto,
                                                              @NonNull List<String> codiciGruppo,
                                                              @NonNull PianificazioneSecurityEnum security) {
        if (codiciGruppo.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return false;
        }
        return PianificazioneSecurityEnum.WRITE.equals(security)
                ? gruppoRepartoDAOInstance.get().countWriteableByRepartoIdAndCodiciGruppo(idReparto, codiciGruppo) > 0
                : gruppoRepartoDAOInstance.get().countByRepartoIdAndCodiciGruppo(idReparto, codiciGruppo) > 0;
    }

    @Override
    public List<String> findAllWritableCodiciRepartoByCodiciGruppo(@NonNull List<String> codiciGruppo) {
        if (codiciGruppo.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return gruppoRepartoDAOInstance.get().findAllByCodiciGruppoAndTipoAccesso(codiciGruppo, PianificazioneSecurityEnum.WRITE);
    }
}
