package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.dto.CastellettoMessaggiDTO;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;

import java.util.List;

public interface CastellettoMessaggiService extends DbPromoService<CastellettoMessaggiEntity> {
    List<CastellettoMessaggiEntity> findByIdPianificazione(Long idPianificazione);

    List<CastellettoMessaggiEntity> findMessaggiByIdPianificazione(Long idPianificazione);

    List<CastellettoMessaggiEntity> findMessaggiByIdPianificazioneAndCodiceDispositivo(Long idPianificazione, String codiceDispositivo);

    List<CastellettoMessaggiEntity> findCastellettiByIdPianificazione(Long idPianificazione);

    void remove(List<Long> idCastelletti);

    List<CastellettoMessaggiDTO> findEnhancedMessaggiByIdPianificazioneAndCodiceDispositivo(Long idPianificazione, String codiceDispositivo);

    void addMessageAbove(Long idMessaggio, String codiceUtente);

    void resetMessaggi(PromozionePianificazioneEntity pianificazione, CfgCanaleDispositivoEntity dispositivo, String codiceUtente);

    boolean canResetMessaggi(PromozionePianificazioneEntity pianificazione, CfgCanaleDispositivoEntity dispositivo);

    boolean isLogoUsedInPromo(String logoFilename, Long idPianificazione);

    String getHtmlFromDb(Long idPianificazione, String codiceDispositivo, String imgPath);
}
