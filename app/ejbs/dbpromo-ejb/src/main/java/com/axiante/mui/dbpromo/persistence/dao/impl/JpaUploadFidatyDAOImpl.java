package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.UploadFidatyDAO;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaUploadFidatyDAOImpl extends JpaDbPromoDAO<UploadFidayEntity> implements UploadFidatyDAO {

	private static final long serialVersionUID = -1087914574186224602L;

	@Override
	public List<UploadFidayEntity> findByPromozione(Long idPromozione) {
		return getEm().createNamedQuery("UploadFidayEntity.findByPromozione", UploadFidayEntity.class)
				.setParameter("idPromozione", idPromozione)
				.getResultList();
	}

	@Override
	public List<UploadFidayEntity> findValidByPromozione(Long idPromozione) {
		return getEm().createNamedQuery("UploadFidayEntity.findValidByPromozione", UploadFidayEntity.class)
				.setParameter("idPromozione", idPromozione)
				.getResultList();
	}

	@Override
	public List<UploadFidayEntity> findByPianificazione(Long idPianificazione) {
		return getEm().createNamedQuery("UploadFidayEntity.findByPianificazione", UploadFidayEntity.class)
				.setParameter("idPianificazione", idPianificazione)
				.getResultList();
	}

	@Override
	public UploadFidayEntity findByNomeDestinazioneAndPianificazione(@NonNull final String nomeFile,
			@NonNull final Long idPianificazione) {
		List<UploadFidayEntity> list = getEm().createNamedQuery("UploadFidayEntity.findByNomeDestinazioneAndPianificazione", UploadFidayEntity.class)
				.setParameter("idPianificazione", idPianificazione)
				.setParameter("nomeFile", nomeFile)
				.getResultList();
		if (list.size() == 0) {
			return null;
		} else {
			return list.stream()
					.reduce((a, b) -> a.getDataPubblicazione().compareTo(b.getDataPubblicazione()) < 0 ? b : a).get();
		}
	}
}
