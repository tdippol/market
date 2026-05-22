package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.IniziativaStatusEnum;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.IniziativaDAO;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DbPromoJpaDao
public class JpaIniziativaDAOImpl extends JpaDbPromoDAO<IniziativaEntity> implements IniziativaDAO {
    private static final long serialVersionUID = -7887400936083242993L;

    @Override
    public List<IniziativaEntity> findAllNotCancelled() {
        return getEm().createNamedQuery("IniziativaEntity.findAllNotCancelled", IniziativaEntity.class)
                .setParameter("codiceStato", IniziativaStatusEnum.CANCELLATA_00.getCodice())
                .getResultList();
    }

    @Override
    public List<IniziativaEntity> findAllPublishedAndInProgressAndValidDates(Date dataInizio, Date dataFine) {
        List<String> codiciStato = Stream.of(IniziativaStatusEnum.PUBBLICATA.getCodice(),
                IniziativaStatusEnum.IN_ESECUZIONE.getCodice()).collect(Collectors.toList());
        return getEm().createNamedQuery("IniziativaEntity.findAllPublishedAndInProgressAndValidDates", IniziativaEntity.class)
                .setParameter("codiciStato", codiciStato)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getResultList();
    }

    @Override
    public List<StatoPromozioneEntity> findStatiTransizioneConsentiti() {
        return getEm().createNamedQuery("IniziativaEntity.findStatiTransizioneConsentiti", StatoPromozioneEntity.class)
                .getResultList();
    }
}
