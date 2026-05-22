package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.List;

public interface PianoMediaStatiTransizioneDAO extends DbPromoDAO<PianoMediaStatiTransizioneEntity> {

    List<PianoMediaStatiTransizioneEntity> findByIdAndStatus(Long idPiano, Long statusID);

    List<PianoMediaStatiTransizioneEntity> findAllNotAutomaticByIdAndStatus(Long idPiano, Long statusID);

    List<PianoMediaStatiTransizioneEntity> findAllAutomaticByIdAndStatus(Long idPiano, Long statusID);

    PianoMediaStatiTransizioneEntity findByPianoAndStatuses(PianoMediaEntity pianoMedia,
                                                            StatoPromozioneEntity fromStatus,
                                                            StatoPromozioneEntity toStatus);

    PianoMediaStatiTransizioneEntity findByPianoAndStatusesAndFlagAutomatico(PianoMediaEntity pianoMedia,
                                                                             StatoPromozioneEntity fromStatus,
                                                                             StatoPromozioneEntity toStatus,
                                                                             Boolean flagAutomatico);
}
