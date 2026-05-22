package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import java.util.List;

public interface UploadFidatyDAO extends DbPromoDAO<UploadFidayEntity> {

	List<UploadFidayEntity> findByPromozione(Long idPromozione);

	List<UploadFidayEntity> findByPianificazione(Long idPianificazione);

	List<UploadFidayEntity> findValidByPromozione(Long idPromozione) ;

	UploadFidayEntity findByNomeDestinazioneAndPianificazione(String nomeFile, Long idPianificazione) ;
}
