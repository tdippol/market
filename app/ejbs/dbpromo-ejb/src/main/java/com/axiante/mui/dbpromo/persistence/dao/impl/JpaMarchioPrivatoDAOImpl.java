package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MarchioPrivatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MarchioPrivatoEntity;
import java.util.List;

@DbPromoJpaDao
public class JpaMarchioPrivatoDAOImpl extends JpaDbPromoDAO<MarchioPrivatoEntity> implements MarchioPrivatoDAO {
    private static final long serialVersionUID = -4068339861807322487L;

    public List<MarchioPrivatoEntity> findAll() {
        return getEm().createNamedQuery("MarchioPrivatoEntity.findAll", MarchioPrivatoEntity.class)
                .getResultList();
    }

    public MarchioPrivatoEntity findByCodice(String codice) {
        return getEm().createNamedQuery("MarchioPrivatoEntity.findByCodice", MarchioPrivatoEntity.class)
                .setParameter("codice", codice)
                .getSingleResult();
    }

    @Override
    public List<MarchioPrivatoEntity> findByCodici(List<String> codici) {
        if (codici == null || codici.isEmpty()) {
            throw new IllegalArgumentException("codici cannot be null or empty");
        }
        return getEm().createNamedQuery("MarchioPrivatoEntity.findByCodici", MarchioPrivatoEntity.class)
                .setParameter("codici", codici)
                .getResultList();
    }
}
