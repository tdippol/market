package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleDispositivoDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DbPromoJpaDao
public class JpaCfgCanaleDispositivoDAOImpl extends JpaDbPromoDAO<CfgCanaleDispositivoEntity> implements CfgCanaleDispositivoDAO {
    private static final long serialVersionUID = -2405656375328375579L;

    @Override
    public CfgCanaleDispositivoEntity findByCodice(@NonNull String codice) {
        Long count = getEm().createNamedQuery("CfgCanaleDispositivoEntity.countByCodice", Long.class)
                .setParameter("codice", codice)
                .getSingleResult();
        if ((count != null) && (count == 1)) {
            return getEm().createNamedQuery("CfgCanaleDispositivoEntity.findByCodice", CfgCanaleDispositivoEntity.class)
                    .setParameter("codice", codice)
                    .getSingleResult();
        } else {
            String message = null;
            if (count == null || count == 0) {
                message = String.format("Nessun canale con codice %s trovato", codice);
                log.error(message);
                throw new NoResultException(message);
            } else {
                message = String.format("Trovati %d canali con codice %s ", count, codice);
                throw new NonUniqueResultException(message);
            }
        }
    }
}
