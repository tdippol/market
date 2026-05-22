package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import java.util.List;

public interface PianoMediaPianificazioneDettaglioService extends DbPromoService<PianoMediaPianificazioneDettaglioEntity>{
    List<PianoMediaPianificazioneDettaglioEntity> findByPianoMedia(PianoMediaEntity pianoMedia);
}
