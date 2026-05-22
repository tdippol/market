package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaControlliEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import java.util.List;

public interface PianoMediaControlliService extends DbPromoService<PianoMediaControlliEntity> {
    List<PianoMediaControlliEntity> findByPianificazioneMedia(PianificazionePianoMediaEntity pianificazioneMedia);

    List<PianoMediaControlliEntity> findByIdPianificazioniMedia(List<Long> idPianificazioniMedia);

    List<PianoMediaControlliEntity> findByPianoMedia(PianoMediaEntity pianoMedia);

    List<PianoMediaControlliEntity> findByCodiceSupportoConfigurato(String codiceControllo);
    long  countByCodiceSupportoConfigurato(String codiceControllo);

}
