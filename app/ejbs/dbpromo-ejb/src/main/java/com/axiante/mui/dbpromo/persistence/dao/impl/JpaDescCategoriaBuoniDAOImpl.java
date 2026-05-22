package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.DescCategoriaBuoniDAO;
import com.axiante.mui.dbpromo.persistence.entities.DescCategoriaBuoniEntity;
import lombok.NonNull;

import javax.persistence.NonUniqueResultException;

@DbPromoJpaDao
public class JpaDescCategoriaBuoniDAOImpl extends JpaDbPromoDAO<DescCategoriaBuoniEntity>
        implements DescCategoriaBuoniDAO {
    private static final long serialVersionUID = -4819533275730759887L;

    @Override
    public DescCategoriaBuoniEntity findByIdPromozione(@NonNull Long idPromozione) {
        Long count = getEm().createNamedQuery("DescCategoriaBuoniEntity.countByIdPromozione", Long.class)
                .setParameter("idPromozione", idPromozione)
                .getSingleResult();
        if (count == 1) {
            return getEm().createNamedQuery("DescCategoriaBuoniEntity.findByIdPromozione", DescCategoriaBuoniEntity.class)
                    .setParameter("idPromozione", idPromozione)
                    .getSingleResult();
        }
        if (count == 0) {
            return null;
        }
        throw new NonUniqueResultException(String.format("Trovati %d valori per idPromozione=%d", count.intValue(),
                idPromozione));
    }
}
