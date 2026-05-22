package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaControlliDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaControlliEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaControlliService;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
@Transactional
public class PianoMediaControlliServiceImpl extends AbstractDbPromoService<PianoMediaControlliEntity>
        implements PianoMediaControlliService {
    private static final long serialVersionUID = 3424302762432047873L;

    @Inject
    @Getter
    private PianoMediaControlliDAO dao;

    @Override
    public List<PianoMediaControlliEntity> findByPianificazioneMedia(@NonNull PianificazionePianoMediaEntity pianificazioneMedia) {
        return dao.findByPianificazioneMedia(pianificazioneMedia);
    }

    @Override
    public List<PianoMediaControlliEntity> findByIdPianificazioniMedia(@NonNull List<Long> idPianificazioniMedia) {
        if (idPianificazioniMedia.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return dao.findByIdPianificazioniMedia(idPianificazioniMedia);
    }

    @Override
    public List<PianoMediaControlliEntity> findByPianoMedia(@NonNull PianoMediaEntity pianoMedia) {
        return dao.findByPianoMedia(pianoMedia);
    }

    @Override
    public List<PianoMediaControlliEntity> findByCodiceSupportoConfigurato(@NonNull String codiceControllo){
        return getDao().findByCodiceSupportoConfigurato(codiceControllo);
    }

    @Override
    public long countByCodiceSupportoConfigurato(@NonNull String codiceControllo){
        return getDao().countByCodiceSupportoConfigurato(codiceControllo);
    }

}
