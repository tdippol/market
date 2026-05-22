package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPromoDbpromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaPianoMediaPromoDbpromoDAOImpl implements PianoMediaPromoDbpromoDAO {

    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public List<PianoMediaPromoDbpromoEntity> findByDataFineGreaterThanAndCanali(@NonNull Date date,
                                                                                 List<CanalePromozioneEntity> canali) {
        if (canali == null || canali.isEmpty()) {
            throw new IllegalArgumentException("canali cannot be null or empty");
        }
        final List<Long> codiciCanale = canali.stream().map(CanalePromozioneEntity::getCodiceCanale).collect(Collectors.toList());
        return getEm().createNamedQuery("PianoMediaPromoDbpromoEntity.findByDataFineGreaterThanAndCanali", PianoMediaPromoDbpromoEntity.class)
                .setParameter("date", date)
                .setParameter("codiciCanale", codiciCanale)
                .getResultList();
    }
    @Override
    public List<PianoMediaPromoDbpromoEntity> findByDataFineGreaterThan(@NonNull Date date) {
        return getEm().createNamedQuery("PianoMediaPromoDbpromoEntity.findByDataFineGreaterThan", PianoMediaPromoDbpromoEntity.class)
                .setParameter("date", date)
                .getResultList();
    }
    @Override
    public PianoMediaPromoDbpromoEntity findByCodicePromo(@NonNull String codicePromo) {
        final Long count = getEm().createNamedQuery("PianoMediaPromoDbpromoEntity.countByCodicePromo", Long.class)
                .setParameter("codicePromo", codicePromo)
                .getSingleResult();
        if (count == 0) {
            log.warn(String.format("Cannot found entity with codicePromo '%s'", codicePromo));
            return null;
        }
        if (count > 1) {
            log.error(String.format("Found %d entities with codicePromo '%s'", count, codicePromo));
            return null;
        }
        return getEm().createNamedQuery("PianoMediaPromoDbpromoEntity.findByCodicePromo", PianoMediaPromoDbpromoEntity.class)
                .setParameter("codicePromo", codicePromo)
                .getSingleResult();
    }

    @Override
    public List<PianoMediaPromoDbpromoEntity> findAllByCodiciPromo(List<String> codiciPromo) {
        if (codiciPromo == null || codiciPromo.isEmpty()) {
            throw new IllegalArgumentException("codiciPromo cannot be null or empty");
        }
        return getEm().createNamedQuery("PianoMediaPromoDbpromoEntity.findAllByCodiciPromo", PianoMediaPromoDbpromoEntity.class)
                .setParameter("codiciPromo", codiciPromo)
                .getResultList();
    }
}
