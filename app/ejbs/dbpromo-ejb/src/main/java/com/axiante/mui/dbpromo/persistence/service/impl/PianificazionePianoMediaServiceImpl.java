package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianificazionePianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazionePianoMediaService;
import lombok.Getter;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Dependent
@Transactional
public class PianificazionePianoMediaServiceImpl extends AbstractDbPromoService<PianificazionePianoMediaEntity>
        implements PianificazionePianoMediaService {
    private static final long serialVersionUID = -1122679854149254380L;

    @Inject
    @Getter
    PianificazionePianoMediaDAO dao;

    @Override
    public void remove(Set<PianificazionePianoMediaEntity> set, int batchSize) {
        getDao().remove(set, batchSize);
    }

    @Override
    public List<PianificazionePianoMediaEntity> findByPianoMedia(@NonNull PianoMediaEntity pianoMedia) {
        return dao.findByPianoMedia(pianoMedia);
    }

    @Override
    public List<PianificazionePianoMediaEntity> findAttiviByPianoMedia(@NonNull PianoMediaEntity pianoMedia) {
        return dao.findAttiviByPianoMedia(pianoMedia);
    }
}
