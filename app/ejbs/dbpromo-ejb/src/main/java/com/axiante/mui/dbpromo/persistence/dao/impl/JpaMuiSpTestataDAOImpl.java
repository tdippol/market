package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiSpTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpTestataEntity;
import com.axiante.mui.dbpromo.business.utils.Constants;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.ParameterMode;
import java.sql.Connection;
import java.util.List;

@Slf4j
@DbPromoJpaDao
public class JpaMuiSpTestataDAOImpl extends JpaDbPromoDAO<MuiSpTestataEntity> implements MuiSpTestataDAO {
    private static final long serialVersionUID = 1L;

    @Override
    public List<MuiSpTestataEntity> findAllForVisualizza() {
        return getEm()
                .createNamedQuery("MuiSpTestataEntity.findAllForVisualizza", MuiSpTestataEntity.class)
                .getResultList();
    }

    @Override
    public void deactivateOtherActive(Long id) {
        getEm()
                .createNamedQuery("MuiSpTestataEntity.deactivateOtherActive")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public MuiSpTestataEntity findActive() {
        List<MuiSpTestataEntity> result = getEm()
                .createNamedQuery("MuiSpTestataEntity.findActive", MuiSpTestataEntity.class)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void save(MuiSpTestataEntity entity) {
        getEm().persist(entity);
    }

    @Override
    public void deleteById(Long id) {
        MuiSpTestataEntity entity = findById(id);
        if (entity != null) {
            getEm().remove(getEm().contains(entity) ? entity : getEm().merge(entity));
        }
    }

    @Override
    public MuiSpTestataEntity findById(Long id) {
        return getEm().find(MuiSpTestataEntity.class, id);
    }

    @Override
    public MuiSpTestataEntity update(MuiSpTestataEntity entity) {
        return getEm().merge(entity);
    }



    @Override
    public MuiSpTestataEntity findActiveForHeader() {
        List<MuiSpTestataEntity> result = getEm()
                .createNamedQuery("MuiSpTestataEntity.findActiveWithDetails", MuiSpTestataEntity.class)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public MuiSpTestataEntity findByIdForHeader(Long id) {
        List<MuiSpTestataEntity> result = getEm()
                .createNamedQuery("MuiSpTestataEntity.findByIdWithDetails", MuiSpTestataEntity.class)
                .setParameter("id", id)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void flush() {
        getEm().flush();
    }

    @Override
    public void runProcedureAssociaReparti(Long idPromozione) {
        try {
            Connection conn = getEm().unwrap(Connection.class);
            try (java.sql.CallableStatement cs = conn.prepareCall(
                    String.format("{ CALL DBPROMO.%s(?) }", Constants.P_MUI_SP_ASSOCIA_REPARTI))) {
                cs.setLong(1, idPromozione);
                cs.execute();
            }
        } catch (Exception ex) {
            log.error(String.format("Error calling procedure %s for promozione id %d",
                    Constants.P_MUI_SP_ASSOCIA_REPARTI, idPromozione), ex);
            throw new RuntimeException(ex);
        }
    }
}