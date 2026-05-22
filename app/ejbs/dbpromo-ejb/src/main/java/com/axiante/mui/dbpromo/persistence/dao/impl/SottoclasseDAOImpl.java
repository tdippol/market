package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.dao.SottoclasseDAO;
import com.axiante.mui.dbpromo.persistence.entities.SottoclasseEntity;
import lombok.NonNull;

import javax.persistence.ParameterMode;
import java.util.List;

public class SottoclasseDAOImpl extends JpaDbPromoDAO<SottoclasseEntity> implements SottoclasseDAO {
    private static final long serialVersionUID = -8070259223641224470L;

    @Override
    public List<SottoclasseEntity> findAll() {
        return getEm().createNamedQuery("SottoclasseEntity.findAll", SottoclasseEntity.class).getResultList();
    }

    @Override
    public SottoclasseEntity findById(@NonNull Long id) {
        return getEm().find(SottoclasseEntity.class, id);
    }

    @Override
    public boolean existsByCodeOrDescription(@NonNull String code, @NonNull String description) {
        Long count = getEm().createNamedQuery("SottoclasseEntity.countByCodeOrDescription", Long.class)
                .setParameter("codice", code)
                .setParameter("descrizione", description)
                .getSingleResult();
        return count != null && count > 0;
    }

    @Override
    public boolean runFunctionPubblicaSottoclassi(@NonNull String username) throws Exception {
        getEm().createStoredProcedureQuery(Constants.P_MUI_EXPORT_SOTTOCLASSE)
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .setParameter(1, username)
                .executeUpdate();
        return true;
    }

    @Override
    public Long countSottoclassiNonScaricate() {
        return getEm().createNamedQuery("SottoclasseEntity.countSottoclassiNonScaricate", Long.class)
                .getSingleResult();
    }
}
