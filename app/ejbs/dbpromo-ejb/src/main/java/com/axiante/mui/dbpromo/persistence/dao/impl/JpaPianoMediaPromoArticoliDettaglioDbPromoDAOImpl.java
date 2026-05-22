package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPromoArticoliDettaglioDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDettaglioDbPromoEntity;
import java.util.List;

@DbPromoJpaDao
public class JpaPianoMediaPromoArticoliDettaglioDbPromoDAOImpl extends JpaDbPromoDAO<PianoMediaPromoArticoliDettaglioDbPromoEntity>
    implements PianoMediaPromoArticoliDettaglioDbPromoDAO {
    private static final long serialVersionUID = 8931811095710615272L;

    @Override
    public List<PianoMediaPromoArticoliDettaglioDbPromoEntity> findByCodicePromoAndItemAndTipoItem(String codicePromo, String codiceItem, String tipoItem) {
        return getEm().createNamedQuery("PianoMediaPromoArticoliDettaglioDbPromoEntity.findByCodicePromoAndItemAndTipoItem", PianoMediaPromoArticoliDettaglioDbPromoEntity.class)
                .setParameter("codicePromo", codicePromo)
                .setParameter("codiceItem", codiceItem)
                .setParameter("tipoItem", tipoItem)
                .getResultList();

    }
}
