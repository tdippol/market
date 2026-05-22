package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import com.axiante.mui.dbpromo.persistence.dao.MuiCfgCastellettoMessaggiComponentDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgCastellettoMessaggiComponentEntity;

import java.util.List;

public class JpaCfgDefaultCastellettoMessaggiDAOImpl extends JpaDbPromoDAO<MuiCfgCastellettoMessaggiComponentEntity>
        implements MuiCfgCastellettoMessaggiComponentDAO {
    private static final long serialVersionUID = -2915489409781944300L;

    /**
     *
     * @param codiceCanale
     * @param codiceMeccanica
     * @param codiceDispositivo
     * @return
     */
    public List<MuiCfgCastellettoMessaggiComponentEntity> findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
            Long codiceCanale, String codiceMeccanica, String codiceDispositivo) {
        return getEm()
                .createNamedQuery("MuiCfgCastellettoMessaggiComponentEntity.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo"
                        , MuiCfgCastellettoMessaggiComponentEntity.class)
                .setParameter("codiceCanale", codiceCanale)
                .setParameter("codiceMeccanica", codiceMeccanica)
                .setParameter("codiceDispositivo", codiceDispositivo)
                .getResultList();
    }

    /**
     * @param codiceCanale 
     * @param codiceMeccanica
     * @param codiceDispositivo
     * @param component
     * @return
     */
    @Override
    public MuiCfgCastellettoMessaggiComponentEntity findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
            Long codiceCanale, String codiceMeccanica, String codiceDispositivo, MessaggiComponentsEnum component) {
        return getEm()
                .createNamedQuery("MuiCfgCastellettoMessaggiComponentEntity.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent"
                        , MuiCfgCastellettoMessaggiComponentEntity.class)
                .setParameter("codiceCanale", codiceCanale)
                .setParameter("codiceMeccanica", codiceMeccanica)
                .setParameter("codiceDispositivo", codiceDispositivo)
                .setParameter("component", component)
                .getSingleResult();
    }

    /**
     * @param codiceCanale 
     * @param codiceMeccanica
     * @param codiceDispositivo
     * @param component
     * @return
     */
    @Override
    public Long countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
            Long codiceCanale, String codiceMeccanica, String codiceDispositivo, MessaggiComponentsEnum component) {
        return getEm()
                .createNamedQuery("MuiCfgCastellettoMessaggiComponentEntity.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent"
                        , Long.class)
                .setParameter("codiceCanale", codiceCanale)
                .setParameter("codiceMeccanica", codiceMeccanica)
                .setParameter("codiceDispositivo", codiceDispositivo)
                .setParameter("component", component)
                .getSingleResult();
    }
}
