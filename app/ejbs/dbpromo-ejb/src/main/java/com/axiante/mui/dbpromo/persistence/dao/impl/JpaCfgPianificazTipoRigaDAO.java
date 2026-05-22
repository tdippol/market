package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazTipoRigaDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import javax.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaCfgPianificazTipoRigaDAO extends JpaDbPromoDAO<CfgPianificazTipoRigaEntity>
	implements CfgPianificazTipoRigaDAO {

	private static final long serialVersionUID = -1097727878285833977L;

	@Override
	public CfgPianificazTipoRigaEntity findByDescription(String description) {
		try {
			return getEm().createNamedQuery("MuiCfgPianificazTipoRiga.findByDescription", CfgPianificazTipoRigaEntity.class)
					.setParameter("description", description)
					.getSingleResult();
		} catch (NoResultException e) {
			log.info("No CfgPianificazTipoRigaEntity found", e);
			return null;
		}
	}

	@Override
	public CfgPianificazTipoRigaEntity findByCodiceTipo(String codiceTipo) {
		try {
			return getEm().createNamedQuery("MuiCfgPianificazTipoRiga.findByCodiceTipo", CfgPianificazTipoRigaEntity.class)
					.setParameter("codiceTipo", codiceTipo)
					.getSingleResult();
		} catch (NoResultException e) {
			log.info("No CfgPianificazTipoRigaEntity found", e);
			return null;
		}
	}

}
