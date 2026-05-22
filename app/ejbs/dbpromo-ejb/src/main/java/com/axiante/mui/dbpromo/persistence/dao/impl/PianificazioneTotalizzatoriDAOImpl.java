package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PianificazioneTotalizzatoriDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneTotalizzatoriEntity;
import java.util.List;

@DbPromoJpaDao
public class PianificazioneTotalizzatoriDAOImpl extends JpaDbPromoDAO<PianificazioneTotalizzatoriEntity>
        implements PianificazioneTotalizzatoriDAO {
    private static final long serialVersionUID = -930755854260226044L;

    @Override
    public List<PianificazioneTotalizzatoriEntity> findAllWithParentByIdPianificazione(Long idPianificazione) {
        return getEm().createNamedQuery("PianificazioneTotalizzatoriEntity.findAllWithParentByIdPianificazione", PianificazioneTotalizzatoriEntity.class)
                .setParameter("idPianificazione", idPianificazione)
                .getResultList();
    }
}
