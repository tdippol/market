package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiSpPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpPianificazioneEntity;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.List;
@Slf4j
@DbPromoJpaDao
public class JpaMuiSpPianificazioneDAOImpl extends JpaDbPromoDAO<MuiSpPianificazioneEntity>
        implements MuiSpPianificazioneDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public List<MuiSpPianificazioneEntity> findByPromozioneAndTipo(Long idPromozione, String tipo) {
        return getEm()
                .createNamedQuery("MuiSpPianificazioneEntity.findByPromozioneAndTipo", MuiSpPianificazioneEntity.class)
                .setParameter("idPromozione", idPromozione)
                .setParameter("tipo", tipo)
                .getResultList();
    }

    @Override
    public void updatePremioFinale(Long id, BigDecimal premioFinale) {
        log.info("updatePremioFinale - id: " + id + " premioFinale: " + premioFinale);

        int updated = getEm()
                .createQuery("UPDATE MuiSpPianificazioneEntity e SET e.premioFinale = :premioFinale WHERE e.id = :id")
                .setParameter("premioFinale", premioFinale)
                .setParameter("id", id)
                .executeUpdate();

        log.info("updatePremioFinale - righe aggiornate: " + updated);
    }

    @Override
    public MuiSpPianificazioneEntity findByUniqueKey(Long idPromozione,
                                                     Long idItem,
                                                     Long idFornitore,
                                                     String tipo) {
        try {
            return getEm()
                    .createNamedQuery("MuiSpPianificazioneEntity.findByUniqueKey", MuiSpPianificazioneEntity.class)
                    .setParameter("idPromozione", idPromozione)
                    .setParameter("idItem", idItem)
                    .setParameter("idFornitore", idFornitore)
                    .setParameter("tipo", tipo)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}