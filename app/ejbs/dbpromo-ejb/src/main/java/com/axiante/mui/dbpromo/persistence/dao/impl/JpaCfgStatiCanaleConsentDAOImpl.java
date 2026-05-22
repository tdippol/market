package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgStatiCanaleConsentDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiCanaleConsentEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.ArrayList;
import java.util.List;

@DbPromoJpaDao
public class JpaCfgStatiCanaleConsentDAOImpl extends JpaDbPromoDAO<CfgStatiCanaleConsentEntity>
		implements CfgStatiCanaleConsentDAO {
	private static final long serialVersionUID = 6407335155520130891L;

	@Override
	public List<CfgStatiCanaleConsentEntity> findAllPromoAllowedStateByChannel(CanalePromozioneEntity canalePromozioneEntity) {
		if (canalePromozioneEntity != null) {
			return getEm().createNamedQuery("MuiCfgStatiCanaleConsent.findAllByIdCanale", CfgStatiCanaleConsentEntity.class)
					.setParameter("muiCanalePromozione", canalePromozioneEntity)
					.getResultList();
		}
		return new ArrayList<>();
	}

	@Override
	public CfgStatiCanaleConsentEntity findByCanaleAndStato(CanalePromozioneEntity canale, StatoPromozioneEntity stato) {
		return getEm().createNamedQuery("MuiCfgStatiCanaleConsent.findByCanaleAndStato", CfgStatiCanaleConsentEntity.class)
				.setParameter("canale", canale)
				.setParameter("stato", stato)
				.getSingleResult();
	}

	@Override
	public List<CfgAzioniEntity> findAzioniConsentByCanale(CanalePromozioneEntity canale) {
		return getEm().createNamedQuery("MuiCfgStatiCanaleConsent.findAzioniConsentByCanale",CfgAzioniEntity.class)
				.setParameter("canale", canale)
				.getResultList();
	}

	@Override
	public List<CfgAzioniEntity> findAzioniConsentByCanaleAndStato(CanalePromozioneEntity canale, StatoPromozioneEntity stato) {
		return getEm().createNamedQuery("MuiCfgStatiCanaleConsent.findAzioniConsentByCanaleAndStato",CfgAzioniEntity.class)
				.setParameter("canale", canale)
				.setParameter("stato", stato)
				.getResultList();
	}

    @Override
    public List<String> findCodiciAzioniConsentiteByCanaleAndStato(CanalePromozioneEntity canale, StatoPromozioneEntity stato) {
        return getEm().createNamedQuery("MuiCfgStatiCanaleConsent.findCodiciAzioniConsentiteByCanaleAndStato", String.class)
				.setParameter("canale", canale)
				.setParameter("stato", stato)
				.getResultList();
    }
}
