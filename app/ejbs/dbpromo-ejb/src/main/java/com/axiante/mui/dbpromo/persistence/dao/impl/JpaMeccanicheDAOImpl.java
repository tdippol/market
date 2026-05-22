package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MeccanicheDAO;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DbPromoJpaDao
public class JpaMeccanicheDAOImpl extends JpaDbPromoDAO<MeccanicheEntity> implements MeccanicheDAO {
    private static final long serialVersionUID = 8615705516225256399L;

    @Override
    public MeccanicheEntity findByCodice(String codice) {
        Long count = getEm().createNamedQuery("MeccanicheEntity.countByCodice", Long.class)
                .setParameter("codiceMeccanica", codice)
                .getSingleResult();
        if (count == 1) {
            return getEm().createNamedQuery("MeccanicheEntity.findByCodice", MeccanicheEntity.class)
                    .setParameter("codiceMeccanica", codice)
                    .getSingleResult();
        }
        if (count == 0) {
            log.warn("Nessuna meccanica trovata con codice {}", codice);
        } else {
            log.error("Trovate {} meccaniche con codice {}", count, codice);
        }
        return null;
    }
}
