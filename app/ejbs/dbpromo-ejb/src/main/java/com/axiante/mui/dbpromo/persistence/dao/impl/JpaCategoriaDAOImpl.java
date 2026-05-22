package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CategoriaDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaCategoriaDAOImpl extends JpaDbPromoDAO<CategoriaEntity> implements CategoriaDAO {
    private static final long serialVersionUID = -8377109426157186722L;

    @Override
    public List<CategoriaEntity> findAllByCodiciCompratore(@NonNull List<String> codiciCompratore) {
        if (codiciCompratore.isEmpty()) {
            throw new IllegalArgumentException("codiciCompratore cannot be null or empty");
        }
        return getEm().createNamedQuery("CategoriaEntity.findAllByCodiciCompratore", CategoriaEntity.class)
                .setParameter("codiciCompratore", codiciCompratore)
                .getResultList();
    }
}
