package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import java.util.List;
import java.util.Set;

public interface PianificazionePianoMediaService extends DbPromoService<PianificazionePianoMediaEntity> {
    default void remove(Set<PianificazionePianoMediaEntity> set) {
        remove(set, 100);
    }

    void remove(Set<PianificazionePianoMediaEntity> set, int batchSize);

    List<PianificazionePianoMediaEntity> findByPianoMedia(PianoMediaEntity pianoMedia);

    List<PianificazionePianoMediaEntity> findAttiviByPianoMedia(PianoMediaEntity pianoMedia);
}
