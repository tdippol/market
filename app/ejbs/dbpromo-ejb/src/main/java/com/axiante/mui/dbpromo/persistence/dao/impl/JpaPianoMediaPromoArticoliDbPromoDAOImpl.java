package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPromoArticoliDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDbPromoEntity;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaPianoMediaPromoArticoliDbPromoDAOImpl extends JpaDbPromoDAO<PianoMediaPromoArticoliDbPromoEntity>
        implements PianoMediaPromoArticoliDbPromoDAO {
    private static final long serialVersionUID = 8368841502969517630L;

    @Override
    public List<PianoMediaPromoArticoliDbPromoEntity> findByCodicePromo(String codicePromo) {
        return getEm().createNamedQuery("PianoMediaPromoArticoliDbPromoEntity.findByCodicePromo", PianoMediaPromoArticoliDbPromoEntity.class)
                .setParameter("codicePromo", codicePromo)
                .getResultList();
    }

    @Override
    public PianoMediaPromoArticoliDbPromoEntity findByCodiceItemAndCodicePromoAndTipoItem(String codiceItem,
                                                                                          String codicePromo,
                                                                                          String tipoItem) {
        Long count = getEm().createNamedQuery("PianoMediaPromoArticoliDbPromoEntity.countByCodiceItemAndCodicePromoAndTipoItem", Long.class)
                .setParameter("codiceItem", codiceItem)
                .setParameter("codicePromo", codicePromo)
                .setParameter("tipoItem", tipoItem)
                .getSingleResult();
        if (count == 1) {
            return getEm().createNamedQuery("PianoMediaPromoArticoliDbPromoEntity.findByCodiceItemAndCodicePromoAndTipoItem", PianoMediaPromoArticoliDbPromoEntity.class)
                    .setParameter("codiceItem", codiceItem)
                    .setParameter("codicePromo", codicePromo)
                    .setParameter("tipoItem", tipoItem)
                    .getSingleResult();
        } else {
            if (count == 0) {
                log.error(String.format("nessun valore ritornato per codiceItem '%s', codicePromo '%s' e tipoItem '%s'",
                        codiceItem, codicePromo, tipoItem));
            } else {
                log.error(String.format("%d valori ritornato per codiceItem '%s', codicePromo '%s' e tipoItem '%s'",
                        count, codiceItem, codicePromo, tipoItem));
            }
        }
        return null;
    }
}
