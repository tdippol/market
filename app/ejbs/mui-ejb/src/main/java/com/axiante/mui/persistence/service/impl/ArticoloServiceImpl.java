package com.axiante.mui.persistence.service.impl;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.ArticoloDAO;
import com.axiante.mui.persistence.service.ArticoloService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Slf4j
@Dependent
public class ArticoloServiceImpl implements ArticoloService {

    private static final long serialVersionUID = 5936830988144341144L;

    @Inject
    private Instance<ArticoloDAO> articoloDAOInstance;

    @Override
    public boolean hasAssociationWithIdArticoloAndCodiciGruppo(@NonNull Long idArticolo,
                                                               @NonNull List<String> codiciGruppo,
                                                               @NonNull PianificazioneSecurityEnum security) {
        if (codiciGruppo.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return false;
        }
        return PianificazioneSecurityEnum.WRITE.equals(security)
                ? articoloDAOInstance.get().countWritableByArticoloIdAndCodiciGruppo(idArticolo, codiciGruppo) > 0
                : articoloDAOInstance.get().countByArticoloIdAndCodiciGruppo(idArticolo, codiciGruppo) > 0;
    }

    @Override
    public List<String> findCompratoriByIdArticoliAndCodiciGruppo(List<Long> idArticoli, List<String> codiciGruppo,
                                                                  @NonNull PianificazioneSecurityEnum security) {
        if (codiciGruppo.isEmpty() || idArticoli.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return PianificazioneSecurityEnum.WRITE.equals(security)
                ? articoloDAOInstance.get().findWritableCompratoriByIdArticoliAndCodiciGruppo(idArticoli, codiciGruppo)
                : articoloDAOInstance.get().findCompratoriByIdArticoliAndCodiciGruppo(idArticoli, codiciGruppo);
    }
}
