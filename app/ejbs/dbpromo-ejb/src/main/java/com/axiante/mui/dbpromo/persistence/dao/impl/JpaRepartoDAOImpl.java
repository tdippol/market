package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.RepartoDAO;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import java.util.List;
import javax.persistence.NoResultException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DbPromoJpaDao
public class JpaRepartoDAOImpl extends JpaDbPromoDAO<RepartoEntity> implements RepartoDAO {

    private static final long serialVersionUID = 2954448407918004205L;

    @Override
    public RepartoEntity findByCode(String codiceReparto) {
        try {
            return getEm().createNamedQuery("RepartoEntity.findByCode", RepartoEntity.class)
                    .setParameter("codiceReparto", codiceReparto).getSingleResult();
        } catch (NoResultException ex) {
            log.info("Reparto code [" + codiceReparto + "] not found", ex);
            return null;
        }
    }

    @Override
    public List<RepartoEntity> findAllOrderedBy() {
        return getEm().createNamedQuery("RepartoEntity.findAll", RepartoEntity.class).getResultList();
    }

    @Override
    public List<RepartoEntity> findAllByCodiciCompratore(@NonNull List<String> codiciCompratore) {
        if (codiciCompratore.isEmpty()) {
            throw new IllegalArgumentException("codiciCompratore cannot be null or empty");
        }
        return getEm().createNamedQuery("RepartoEntity.findAllByCodiciCompratore", RepartoEntity.class)
                .setParameter("codiciCompratore", codiciCompratore)
                .getResultList();
    }
}
