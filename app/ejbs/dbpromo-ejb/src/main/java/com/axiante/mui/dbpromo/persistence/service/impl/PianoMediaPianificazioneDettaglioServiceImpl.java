package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPianificazioneDettaglioDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPianificazioneDettaglioService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
@Transactional
public class PianoMediaPianificazioneDettaglioServiceImpl extends AbstractDbPromoService<PianoMediaPianificazioneDettaglioEntity>
        implements PianoMediaPianificazioneDettaglioService {
    private static final long serialVersionUID = -8906356151149631705L;

    @Inject
    @Getter
    private PianoMediaPianificazioneDettaglioDAO dao;

    @Override
    public List<PianoMediaPianificazioneDettaglioEntity> findByPianoMedia(PianoMediaEntity pianoMedia) {
        return getDao().findByPianoMedia(pianoMedia);
    }
}
