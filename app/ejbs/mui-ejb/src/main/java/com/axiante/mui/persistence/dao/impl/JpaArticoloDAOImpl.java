package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.ArticoloDAO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class JpaArticoloDAOImpl implements ArticoloDAO {

    @Inject
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public Long countByArticoloIdAndCodiciGruppo(@NonNull Long idArticolo, @NonNull List<String> codiciGruppo) {
        if (codiciGruppo.isEmpty()) {
            throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
        }
        return getEm().createNamedQuery("ArticoloEntity.countByArticoloIdAndCodiciGruppo", Long.class)
                .setParameter("idArticolo", idArticolo)
                .setParameter("codiciGruppo", codiciGruppo)
                .getSingleResult();
    }

    @Override
    public Long countWritableByArticoloIdAndCodiciGruppo(@NonNull Long idArticolo, @NonNull List<String> codiciGruppo) {
        if (codiciGruppo.isEmpty()) {
            throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
        }
        return getEm().createNamedQuery("ArticoloEntity.countWritableByArticoloIdAndCodiciGruppo", Long.class)
                .setParameter("idArticolo", idArticolo)
                .setParameter("tipoAccesso", PianificazioneSecurityEnum.WRITE)
                .setParameter("codiciGruppo", codiciGruppo)
                .getSingleResult();
    }

    @Override
    public List<String> findCompratoriByIdArticoliAndCodiciGruppo(@NonNull List<Long> idArticoli,
                                                                  @NonNull List<String> codiciGruppo) {
        validateParameters(idArticoli, codiciGruppo);
        return getEm().createNamedQuery("ArticoloEntity.findCompratoriByIdArticoliAndCodiciGruppo", String.class)
                .setParameter("idArticoli", idArticoli)
                .setParameter("codiciGruppo", codiciGruppo)
                .getResultList();
    }

    @Override
    public List<String> findWritableCompratoriByIdArticoliAndCodiciGruppo(@NonNull List<Long> idArticoli,
                                                                          @NonNull List<String> codiciGruppo) {
        validateParameters(idArticoli, codiciGruppo);
        return getEm().createNamedQuery("ArticoloEntity.findWritableCompratoriByIdArticoliAndCodiciGruppo", String.class)
                .setParameter("idArticoli", idArticoli)
                .setParameter("tipoAccesso", PianificazioneSecurityEnum.WRITE)
                .setParameter("codiciGruppo", codiciGruppo)
                .getResultList();
    }

    private void validateParameters(@NonNull List<Long> idArticoli, @NonNull List<String> codiciGruppo) {
        if (idArticoli.isEmpty()) {
            throw new IllegalArgumentException("idArticoli cannot be null or empty");
        }
        if (codiciGruppo.isEmpty()) {
            throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
        }
    }
}
