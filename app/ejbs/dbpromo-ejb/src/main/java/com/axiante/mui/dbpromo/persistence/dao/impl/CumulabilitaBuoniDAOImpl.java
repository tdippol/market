package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.CumulabilitaBuoniDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaBuoniEntity;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DbPromoJpaDao
public class CumulabilitaBuoniDAOImpl implements CumulabilitaBuoniDAO {
    @Inject
    @DbPromo
    @Getter
    private EntityManager em;

    public CumulabilitaBuoniEntity findByCodicePromozioneAndCodiceCanaleAndPrefissoBs(String codicePromozione, String codiceCanale, String prefissoBS) {
        try {
            return getEm().createNamedQuery("CumulabilitaBuoniEntity.findByCodicePromozioneAndCodiceCanaleAndPrefissoBs",CumulabilitaBuoniEntity.class)
                    .setParameter("codicePromozione", codicePromozione)
                    .setParameter("codiceCanale", codiceCanale)
                    .setParameter("prefissoBS", prefissoBS)
                    .getSingleResult();
        } catch (NonUniqueResultException e) {
            log.error("NonUniqueResultException: {}", e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            return null;
        }
    }
}
