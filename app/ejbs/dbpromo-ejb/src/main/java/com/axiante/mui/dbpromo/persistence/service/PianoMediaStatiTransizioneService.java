package com.axiante.mui.dbpromo.persistence.service;


import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.io.Serializable;
import java.util.List;

public interface PianoMediaStatiTransizioneService extends DbPromoService<PianoMediaStatiTransizioneEntity>, Serializable {

    List<PianoMediaStatiTransizioneEntity> findByIdAndStatus(Long idPiano, Long statusID);

    List<PianoMediaStatiTransizioneEntity> findAllNotAutomaticByIdAndStatus(Long idPiano, Long statusID);

    List<PianoMediaStatiTransizioneEntity> findAllAutomaticByIdAndStatus(Long idPiano, Long statusID);

    List<PianoMediaStatiTransizioneEntity> findByPianoMedia(PianoMediaEntity pianoMedia);

    PianoMediaStatiTransizioneEntity findByPianoAndStatuses(PianoMediaEntity pianoMedia,
                                                       StatoPromozioneEntity fromStatus,
                                                       StatoPromozioneEntity toStatus);

    PianoMediaStatiTransizioneEntity findManualiByPianoAndStatuses(PianoMediaEntity pianoMedia,
                                                                   StatoPromozioneEntity fromStatus,
                                                                   StatoPromozioneEntity toStatus,
                                                                   Boolean flagAutomatico);
}
