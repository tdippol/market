package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import java.util.List;
import lombok.NonNull;

public interface UploadFidatyService extends DbPromoService<UploadFidayEntity> {
	List<UploadFidayEntity> findByPromozione(long idPromozione);

	List<UploadFidayEntity> findByPromozione(PromozioneTestataEntity e);

	UploadFidayEntity delete(UploadFidayEntity entity);

	List<UploadFidayEntity> findByPianificazione(PromozionePianificazioneEntity pianificazione);

	List<UploadFidayEntity> findByPianificazione(long idPianificazione);

	List<UploadFidayEntity> findValidByPromozione(long idPromozione);

	UploadFidayEntity findByNomeDestinazioneAndPianificazione(@NonNull String nomeFileDestinazione,
			@NonNull Long idPromozione);
}
