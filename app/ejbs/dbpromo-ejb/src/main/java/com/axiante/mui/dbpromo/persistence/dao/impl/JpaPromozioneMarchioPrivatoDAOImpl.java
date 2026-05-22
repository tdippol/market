package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneMarchioPrivatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMarchioPrivatoEntity;
import java.util.List;

@DbPromoJpaDao
public class JpaPromozioneMarchioPrivatoDAOImpl extends JpaDbPromoDAO<PromozioneMarchioPrivatoEntity> implements PromozioneMarchioPrivatoDAO {
    private static final long serialVersionUID = 1880727911496121015L;

    @Override
    public List<PromozioneMarchioPrivatoEntity> findByIdPromozione(Long idPromozione) {
        return getEm().createNamedQuery("PromozioneMarchioPrivatoEntity.findByIdPromozione", PromozioneMarchioPrivatoEntity.class)
                .setParameter("promozione", idPromozione)
                .getResultList();
    }
}
