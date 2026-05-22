package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaService;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class PianoMediaServiceImpl extends AbstractDbPromoService<PianoMediaEntity> implements PianoMediaService {
    private static final long serialVersionUID = -5603472987746797382L;

    @Inject
    @Getter
    private PianoMediaDAO dao;

    @Override
    public List<PianoMediaEntity> findByDataInizioAndDataFine(@NonNull Date dataInizio, @NonNull Date dataFine) {
        return getDao().findByDataInizioAndDataFine(dataInizio, dataFine);
    }
    @Override
    public List<PianoMediaEntity> findNonPubblicatiByDataInizioAndDataFine(@NonNull Date dataInizio, @NonNull Date dataFine) {
        return getDao().findNonPubblicatiByDataInizioAndDataFine(dataInizio, dataFine);
    }

    @Override
    public List<PianoMediaEntity> findOpenPianiMedia() {
        return getDao().findOpenPianiMedia();
    }

    @Override
    public List<PianoMediaEntity> findNonPubblicatiPianiMedia() {
        return getDao().findNonPubblicatiPianiMedia();
    }

    @Override
    public List<PianoMediaEntity> findNotCancelled() {
        return getDao().findNotCancelled();
    }

    @Override
    public List<Integer> findOpenAvailableYears() {
        return getDao().findOpenAvailableYears();
    }

    @Override
    public List<PianoMediaEntity> findPubblicatiByDataInizio(@NonNull Date dataInizio){
        return getDao().findPubblicatiByDataInizio(dataInizio);
    }

    @Override
    public List<PianoMediaEntity> findOpenByDataFine(@NonNull Date dataFine){
        return getDao().findOpenByDataFine(dataFine);
    }

    @Override
    public List<PianoMediaEntity> findPianiMediaAccessibiliInPianificazione(){
        return getDao().findPianiMediaAccessibiliInPianificazione();
    }
}
