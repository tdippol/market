package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgIdMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgIdMessaggiEntity;
import java.util.List;
import javax.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaCfgIdMessaggiDAOImpl extends JpaDbPromoDAO<CfgIdMessaggiEntity>
    implements CfgIdMessaggiDAO {

  private static final long serialVersionUID = -1539400636284682525L;

  @Override
  public CfgIdMessaggiEntity findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio(
      Long codiceCanale, String codiceMeccanica, String codiceDispositivo, Integer idMessaggio) {
    try{
    return getEm()
        .createNamedQuery(
            "CfgIdMessaggiEntity.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio",
            CfgIdMessaggiEntity.class)
        .setParameter("codiceCanale", codiceCanale)
        .setParameter("codiceMeccanica", codiceMeccanica)
        .setParameter("codiceDispositivo", codiceDispositivo)
        .setParameter("idMessaggio", idMessaggio)
        .getSingleResult();
    } catch (NoResultException e){
      log.warn(String.format("No description for codiceCanale: %s, codiceMeccanica: %s, codiceDispositivo: %s, idMessaggio: %s",
          codiceCanale.toString(), codiceMeccanica, codiceDispositivo, idMessaggio.toString()));
        return null;
    }
  }

  @Override
  public List<CfgIdMessaggiEntity> findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
      Long codiceCanale, String codiceMeccanica, String codiceDispositivo) {
    return getEm()
        .createNamedQuery(
            "CfgIdMessaggiEntity.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo",
            CfgIdMessaggiEntity.class)
        .setParameter("codiceCanale", codiceCanale)
        .setParameter("codiceMeccanica", codiceMeccanica)
        .setParameter("codiceDispositivo", codiceDispositivo)
        .getResultList();
  }
}
