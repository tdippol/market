package com.axiante.mui.persistence.service.impl;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.GroupDAO;
import com.axiante.mui.persistence.service.GruppoService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.List;

@Slf4j
@Dependent
public class GruppoServiceImpl implements GruppoService {
    private static final long serialVersionUID = -9131246790176027075L;

    @Inject
    private Instance<GroupDAO> groupDAOInstance;

    @Override
    public boolean hasAccessTotaleByCodiciGruppo(@NonNull List<String> codiciGruppo,
                                                 @NonNull PianificazioneSecurityEnum security) {
        if (codiciGruppo.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return false;
        }
        return PianificazioneSecurityEnum.WRITE.equals(security)
                ? groupDAOInstance.get().countWriteableAccessTotaleByCodiciGruppo(codiciGruppo) > 0
                : groupDAOInstance.get().countAccessTotaleByCodiciGruppoAndTipoNotNull(codiciGruppo) > 0;
    }

    @Override
    public boolean hasAccessAttributoByCodiciGruppo(@NonNull List<String> codiciGruppo,
                                                    @NonNull PianificazioneSecurityEnum security) {

        if (codiciGruppo.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return false;
        }
        return PianificazioneSecurityEnum.WRITE.equals(security)
                ? groupDAOInstance.get().countWriteableAccessAttributoByCodiciGruppo(codiciGruppo) > 0
                : groupDAOInstance.get().countAccessAttributoByCodiciGruppoAndTipoNotNull(codiciGruppo) > 0;
    }
}
