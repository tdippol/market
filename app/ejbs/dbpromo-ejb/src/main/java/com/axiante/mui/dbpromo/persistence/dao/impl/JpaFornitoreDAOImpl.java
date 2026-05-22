package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.FornitoreDAO;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaFornitoreDAOImpl extends JpaDbPromoDAO<FornitoreEntity> implements FornitoreDAO {

    private static final long serialVersionUID = -6249355335093684830L;

    @Override
    public List<FornitoreEntity> findAllByCodiciCompratore(@NonNull List<String> codiciCompratore) {
        if (codiciCompratore.isEmpty()) {
            throw new IllegalArgumentException("codiciCompratore cannot be null or empty");
        }
        return getEm().createNamedQuery("FornitoreEntity.findAllByCodiciCompratore", FornitoreEntity.class)
                .setParameter("codiciCompratore", codiciCompratore)
                .getResultList();
    }

    @Override
    public List<FornitoreEntity> findAllFornitoriAttiviByCodiceCompratore(@NonNull String codiceCompratore) {
        return getEm().createNamedQuery("FornitoreEntity.findAllFornitoriAttiviByCodiceCompratore", FornitoreEntity.class)
                .setParameter("codiceCompratore", codiceCompratore)
                .getResultList();
    }
}
