package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromoRepartoMarchioPrivatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoRepartoMarchioPrivato;
import java.util.List;

@DbPromoJpaDao
public class JpaPromoRepartoMarchioPrivatoDAOImpl extends JpaDbPromoDAO<PromoRepartoMarchioPrivato>
        implements PromoRepartoMarchioPrivatoDAO {
    private static final long serialVersionUID = 5647283442217214119L;

    public List<PromoRepartoMarchioPrivato> findByIdPromozione(Long idPromozione) {
        return getEm().createNamedQuery("PromoRepartoMarchioPrivato.findByIdPromozione", PromoRepartoMarchioPrivato.class)
                .setParameter("promozione", idPromozione)
                .getResultList();
    }

}
