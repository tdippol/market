package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiSpStatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;

import com.axiante.mui.dbpromo.business.utils.Constants;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.List;

@Slf4j
@DbPromoJpaDao
public class JpaMuiSpStatoDAOImpl extends JpaDbPromoDAO<MuiSpStatoEntity> implements MuiSpStatoDAO {
    private static final long serialVersionUID = 1L;

    @Override
    public void save(MuiSpStatoEntity entity) {
        getEm().persist(entity);
    }



    @Override
    public MuiSpStatoEntity findCurrentByPromozioneId(Long idPromozione) {
        return getEm().createQuery(
                        "SELECT s FROM MuiSpStatoEntity s " +
                                "WHERE s.spTestataEntity.id = :idPromozione " +
                                "AND s.dataFineStato IS NULL",
                        MuiSpStatoEntity.class)
                .setParameter("idPromozione", idPromozione)
                .getSingleResult();
    }

    @Override
    public MuiSpStatoEntity update(MuiSpStatoEntity entity) {
        return getEm().merge(entity);
    }


    @Override
    public StatoPromozioneEntity findStatoPromozioneById(Long id) {
        return getEm().find(StatoPromozioneEntity.class, id);
    }

    @Override
    public List<MuiSpStatoEntity> findAllByPromozioneIdOrderByDataInizio(Long idPromozione) {
        return getEm().createNamedQuery(
                        "MuiSpStatoEntity.findAllByPromozioneIdOrderByDataInizio",
                        MuiSpStatoEntity.class)
                .setParameter("idPromozione", idPromozione)
                .getResultList();
    }

    @Override
    public void runProcedureAssociaCompratori(Long idPromozione, String username) {
        try {
            Connection conn = getEm().unwrap(Connection.class);
            try (java.sql.CallableStatement cs = conn.prepareCall(
                    String.format("{ CALL DBPROMO.%s(?, ?) }", Constants.P_MUI_SP_ASSOCIA_COMPRATORI))) {
                cs.setLong(1, idPromozione);
                cs.setString(2, username);
                cs.execute();
            }
        } catch (Exception ex) {
            log.error(String.format("Error calling procedure %s for promozione id %d",
                    Constants.P_MUI_SP_ASSOCIA_COMPRATORI, idPromozione), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public StatoPromozioneEntity findStatoPromozioneByCode(String code) {
            return getEm()
                    .createNamedQuery("StatoPromozioneEntity.findByCode", StatoPromozioneEntity.class)
                    .setParameter("code", code)
                    .getSingleResult();
    }


}