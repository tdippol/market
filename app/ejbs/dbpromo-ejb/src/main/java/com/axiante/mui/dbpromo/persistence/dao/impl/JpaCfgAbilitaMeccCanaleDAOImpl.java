package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgAbilitaMeccCanaleDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaCfgAbilitaMeccCanaleDAOImpl extends JpaDbPromoDAO<CfgAbilitaMeccCanaleEntity>
		implements CfgAbilitaMeccCanaleDAO {

	private static final long serialVersionUID = -4899548809758380607L;

	@Override
	public List<CfgAbilitaMeccCanaleEntity> findAllByIdCanale(
			@NonNull final CanalePromozioneEntity canalePromozioneEntity) throws Exception {
		return getEm().createNamedQuery("MuiCfgAbilitaMeccCanale.findAllByIdCanale", CfgAbilitaMeccCanaleEntity.class)
				.setParameter("canalePromozioneEntity", canalePromozioneEntity).getResultList();
	}

	@Override
	public CfgAbilitaMeccCanaleEntity findByMeccanicaAndCanale(@NonNull Long idMeccanica,@NonNull Long idCanale) {
		return getEm().createNamedQuery("MuiCfgAbilitaMeccCanale.findByMeccanicaAndCanale",CfgAbilitaMeccCanaleEntity.class)
				.setParameter("idCanale", idCanale).setParameter("idMeccanica", idMeccanica)
				.getSingleResult();
	}
}
