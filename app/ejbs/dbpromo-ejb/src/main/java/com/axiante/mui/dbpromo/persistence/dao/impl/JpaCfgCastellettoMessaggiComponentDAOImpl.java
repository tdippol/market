package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiCfgDefaultCastellettoMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;

import java.util.Arrays;
import java.util.List;

public class JpaCfgCastellettoMessaggiComponentDAOImpl extends JpaDbPromoDAO<MuiCfgDefaultCastellettoMessaggiEntity>
        implements MuiCfgDefaultCastellettoMessaggiDAO {
    private static final long serialVersionUID = -2915489409781944300L;

    public List<MuiCfgDefaultCastellettoMessaggiEntity> findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo) {
        final String[] dispositivi = codiceDispositivo.split("\\|\\|");
        if ( dispositivi.length > 1){
            return getEm()
                    .createNamedQuery("MuiCfgDefaultCastellettoMessaggiEntity.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoIn"
                            , MuiCfgDefaultCastellettoMessaggiEntity.class)
                    .setParameter("codiceCanale", codiceCanale)
                    .setParameter("codiceMeccanica", codiceMeccanica)
                    .setParameter("codiceDispositivo", Arrays.asList(dispositivi))
                    .getResultList();
        } else {
            return getEm()
                    .createNamedQuery("MuiCfgDefaultCastellettoMessaggiEntity.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo"
                            , MuiCfgDefaultCastellettoMessaggiEntity.class)
                    .setParameter("codiceCanale", codiceCanale)
                    .setParameter("codiceMeccanica", codiceMeccanica)
                    .setParameter("codiceDispositivo", codiceDispositivo)
                    .getResultList();
        }
    }
    public List<MuiCfgDefaultCastellettoMessaggiEntity> findByCodiceCanaleAndCodiceMeccanica(Long codiceCanale, String codiceMeccanica) {
        return getEm()
                .createNamedQuery("MuiCfgDefaultCastellettoMessaggiEntity.findByCodiceCanaleAndCodiceMeccanica"
                        , MuiCfgDefaultCastellettoMessaggiEntity.class)
                .setParameter("codiceCanale", codiceCanale)
                .setParameter("codiceMeccanica", codiceMeccanica)
                .getResultList();
    }
    @Override
    public Short findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo){
        Long i = getEm().createNamedQuery("MuiCfgDefaultCastellettoMessaggiEntity.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo", Long.class)
                .setParameter("codiceCanale", codiceCanale)
                .setParameter("codiceMeccanica", codiceMeccanica)
                .setParameter("codiceDispositivo", codiceDispositivo)
                .getSingleResult();
        if ( i!= null && i > 0){
            return getEm().createNamedQuery("MuiCfgDefaultCastellettoMessaggiEntity.findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo", Short.class)
                    .setParameter("codiceCanale", codiceCanale)
                    .setParameter("codiceMeccanica", codiceMeccanica)
                    .setParameter("codiceDispositivo", codiceDispositivo)
                    .getSingleResult();
        }
        return 0;
    }

    public Long countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(Long codiceCanale, String codiceMeccanica, String codiceDispositivo){
        return getEm().createNamedQuery("MuiCfgDefaultCastellettoMessaggiEntity.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo", Long.class)
                .setParameter("codiceCanale", codiceCanale)
                .setParameter("codiceMeccanica", codiceMeccanica)
                .setParameter("codiceDispositivo", codiceDispositivo)
                .getSingleResult();
    }

    @Override
    public List<MuiCfgDefaultCastellettoMessaggiEntity> findAllByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndSequenzaGreaterEqualThan(Long codiceCanale, String codiceMeccanica, String codiceDispositivo, Short seqStampa) {
        return getEm().createNamedQuery("MuiCfgDefaultCastellettoMessaggiEntity.findAllByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndSequenzaGreaterEqualThan", MuiCfgDefaultCastellettoMessaggiEntity.class)
                .setParameter("codiceCanale", codiceCanale)
                .setParameter("codiceMeccanica", codiceMeccanica)
                .setParameter("codiceDispositivo", codiceDispositivo)
                .setParameter("seqStampa", seqStampa)
                .getResultList();
    }
}
