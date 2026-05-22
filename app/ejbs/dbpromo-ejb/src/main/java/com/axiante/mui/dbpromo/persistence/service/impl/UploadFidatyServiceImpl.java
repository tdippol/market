package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.UploadFidatyDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import com.axiante.mui.dbpromo.persistence.service.UploadFidatyService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class UploadFidatyServiceImpl extends AbstractDbPromoService<UploadFidayEntity> implements UploadFidatyService {

	private static final long serialVersionUID = -4849591964907470516L;
	@Inject
	@Getter
	UploadFidatyDAO dao;

	@Override
	public List<UploadFidayEntity> findByPromozione(PromozioneTestataEntity e) {
		return findByPromozione(e.getId());
	}

	@Override
	public UploadFidayEntity delete(UploadFidayEntity entity) {
		dao.remove(entity);
		entity.setId(null);
		return entity;
	}

	@Override
	public List<UploadFidayEntity> findByPromozione(long idPromozione) {
		return dao.findByPromozione(idPromozione);
	}

	@Override
	public List<UploadFidayEntity> findValidByPromozione(long idPromozione) {
		return dao.findValidByPromozione(idPromozione);
	}

	@Override
	public List<UploadFidayEntity> findByPianificazione(@NonNull PromozionePianificazioneEntity pianificazione) {
		return dao.findByPianificazione(pianificazione.getId());
	}

	@Override
	public List<UploadFidayEntity> findByPianificazione(long idPianificazione) {
		return dao.findByPianificazione(idPianificazione);
	}

	@Override
	public UploadFidayEntity findByNomeDestinazioneAndPianificazione(@NonNull final String nomeFileDestinazione,
			@NonNull final Long idPianificazione) {
		return dao.findByNomeDestinazioneAndPianificazione(nomeFileDestinazione, idPianificazione);
	}
}
