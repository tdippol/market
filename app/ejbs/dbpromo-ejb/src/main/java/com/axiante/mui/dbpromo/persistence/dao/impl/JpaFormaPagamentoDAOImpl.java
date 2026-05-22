package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.FormaPagamentoDAO;
import com.axiante.mui.dbpromo.persistence.entities.FormaPagamentoEntity;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import lombok.AccessLevel;
import lombok.Getter;

@DbPromoJpaDao
public class JpaFormaPagamentoDAOImpl extends JpaDbPromoDAO<FormaPagamentoEntity> implements FormaPagamentoDAO {
    private static final long serialVersionUID = -6482150569738262250L;

    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public List<FormaPagamentoEntity> findAllActive() {
        return getEm().createNamedQuery("FormaPagamentoEntity.findAllAttivo", FormaPagamentoEntity.class)
                .getResultList();
    }

    @Override
    public FormaPagamentoEntity findByCode(String codice) {
        Long count = getEm().createNamedQuery("FormaPagamentoEntity.countByCodice", Long.class)
                .setParameter("codice", codice).getSingleResult();
        if (count != null && count == 1) {
            return getEm().createNamedQuery("FormaPagamentoEntity.findByCodice", FormaPagamentoEntity.class)
                    .setParameter("codice", codice).getSingleResult();
        } else {
            String message = null;
            if (count == null || count == 0) {
                message = String.format("Nessuna forma pagamento con codice '%s' trovata", codice);
                throw new NoResultException(message);
            } else {
                message = String.format("Trovate %d forme pagamento con codice '%s'", count, codice);
                throw new NonUniqueResultException(message);
            }
        }
    }
}
