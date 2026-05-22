package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgSetPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaCfgSetPianificazioneDAOImpl extends JpaDbPromoDAO<CfgSetPianificazioneEntity>
		implements CfgSetPianificazioneDAO {

	private static final long serialVersionUID = 8980987982376265622L;

	@Override
	public CfgSetPianificazioneEntity findByLockedAndDataInizio(Date dataInizio) throws Exception {
		try {
			List<CfgSetPianificazioneEntity> list = getEm()
					.createNamedQuery("MuiCfgSetPianificazione.findByLockedAndDataInizio",
							CfgSetPianificazioneEntity.class)
					.setParameter("dataInizio", dataInizio).getResultList();
			if (list.size() > 1) {
				throw new RuntimeException("More than one set satisfies the requested parameters");
			} else if(list.size()==1) {
				return list.get(0);
			} else {
				log.info("No MuiCfgSetPianificazione found dataInizio " + dataInizio);
				return null;
			}
		} catch (NoResultException e) {
			log.info("No MuiCfgSetPianificazione found dataInizio " + dataInizio, e);
			return null;
		}
	}

	@Override
	public CfgSetPianificazioneEntity findOpenSet() throws RuntimeException {
		try {
			return getEm()
					.createNamedQuery("MuiCfgSetPianificazione.findOpenSet", CfgSetPianificazioneEntity.class)
					.getSingleResult();
		} catch (NonUniqueResultException ex) {
			String msg = "More than one set satisfies the requested parameters";
			log.error(msg, ex);
			throw new RuntimeException(msg, ex);
		} catch (NoResultException ex) {
			log.info("No MuiCfgSetPianificazione found which is open", ex);
			return null;
		}
	}

	@Override
	public List<CfgSetPianificazioneEntity> findAllEnabled() {
		return getEm().createNamedQuery("MuiCfgSetPianificazione.findAllEnabled", CfgSetPianificazioneEntity.class)
				.getResultList();
	}
}
