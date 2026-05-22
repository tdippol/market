package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.enumeration.TipoInclusioneEsclusione;
import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiInclusioneEsclusioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiInclusioneEsclusioneEntity;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.ParameterMode;
import java.util.List;

@DbPromoJpaDao
@Slf4j
public class JpaMuiInclusioneEsclusioneDAOImpl extends JpaDbPromoDAO<MuiInclusioneEsclusioneEntity>
        implements MuiInclusioneEsclusioneDAO {
    private static final long serialVersionUID = 284900619848771338L;

    @Override
    public List<MuiInclusioneEsclusioneEntity> findByPromozione(@NonNull Long idPromozione) {
        return getEm().createNamedQuery("MuiInclusioneEsclusioneEntity.findByPromozione", MuiInclusioneEsclusioneEntity.class)
                .setParameter("idPromozione", idPromozione).getResultList();
    }

    @Override
    public List<MuiInclusioneEsclusioneEntity> findByPromozioneAndTipo(@NonNull Long idPromozione, @NonNull TipoInclusioneEsclusione tipo) {
        return getEm().createNamedQuery("MuiInclusioneEsclusioneEntity.findByPromozioneAndTipo", MuiInclusioneEsclusioneEntity.class)
                .setParameter("idPromozione", idPromozione)
                .setParameter("tipo", tipo)
                .getResultList();
    }

    @Override
    public Long countByPromozioneAndMeccanicaAndTipo(@NonNull Long idPromozione, @NonNull Long idMeccanica, @NonNull TipoInclusioneEsclusione tipo) {
        return getEm().createNamedQuery("MuiInclusioneEsclusioneEntity.countByPromozioneAndMeccanicaAndTipo", Long.class)
                .setParameter("idPromozione", idPromozione)
                .setParameter("idMeccanica", idMeccanica)
                .setParameter("tipo", tipo)
                .getSingleResult();
    }

    @Override
    public Long countByPromozioneAndMeccanicaAndTipoAndTipoElemento(Long idPromozione, Long idMeccanica, TipoInclusioneEsclusione tipo, ElementType tipoElemento) {
        return getEm().createNamedQuery("MuiInclusioneEsclusioneEntity.countByPromozioneAndMeccanicaAndTipoAndTipoElemento", Long.class)
                .setParameter("idPromozione", idPromozione)
                .setParameter("idMeccanica", idMeccanica)
                .setParameter("tipo", tipo)
                .setParameter("tipoElemento", tipoElemento)
                .getSingleResult();
    }

    @Override
    public boolean runFunctionExportInclusioniEsclusioni(@NonNull Long idPromozione, @NonNull String username) throws Exception {
        getEm().createStoredProcedureQuery(Constants.P_MUI_EXPORT_INCL_ESCL)
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, idPromozione)
                .setParameter(2, username)
                .executeUpdate();
        return true;
    }
}
