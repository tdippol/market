package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;

import java.util.List;

public interface CastellettoMessaggiDAO extends DbPromoDAO<CastellettoMessaggiEntity> {
    List<CastellettoMessaggiEntity> findByIdPianificazione(Long idPianificazione);

    List<CastellettoMessaggiEntity> findMessaggiByIdPianificazione(Long idPianificazione);

    List<CastellettoMessaggiEntity> findMessaggiByIdPianificazioneAndCodiceDispositivo(Long idPianificazione, String codiceDispositivo);

    List<CastellettoMessaggiEntity> findCastellettiByIdPianificazione(Long idPianificazione);

    List<CastellettoMessaggiEntity> findMessaggiByIdPianificazioneAndCodiceDispositivoAndSeqStampaGreaterThan(Long idPianificazione, String codiceDispositivo, Integer seqStampa);

    void remove(List<Long> idCastelletti);

    String getHtmlFromDb(Long idPianificazione, String codiceDispositivo, String imgPath);
}
