package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import java.util.List;

public interface PianoMediaPianificazioneDettaglioDAO extends DbPromoDAO<PianoMediaPianificazioneDettaglioEntity> {
    List<PianoMediaPianificazioneDettaglioEntity> findByPianoMedia(PianoMediaEntity pianoMedia);
}
