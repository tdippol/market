package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianificazioneAnagraficaPianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneAnagraficaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneAnagraficaPianoMediaService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class JpaPianificazioneAnagraficaPianoMediaServiceImpl extends AbstractDbPromoService<PianificazioneAnagraficaPianoMediaEntity>
        implements PianificazioneAnagraficaPianoMediaService {
    private static final long serialVersionUID = -3236682770821747485L;

    @Inject
    @Getter
    private PianificazioneAnagraficaPianoMediaDAO dao;

    @Override
    public PianificazioneAnagraficaPianoMediaEntity findByPianificazioneDettaglioAndPianificazioneMedia(
            PianoMediaPianificazioneDettaglioEntity pianificazioneDettaglio, PianificazionePianoMediaEntity pianificazioneMedia) {
        return getDao().findByPianificazioneDettaglioAndPianificazioneMedia(pianificazioneDettaglio, pianificazioneMedia);
    }
}
