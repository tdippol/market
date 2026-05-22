package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import java.util.Date;
import java.util.List;
import lombok.NonNull;

public interface PianoMediaService extends DbPromoService<PianoMediaEntity> {

    List<PianoMediaEntity> findByDataInizioAndDataFine(Date dataInizio, Date dataFine);
    List<PianoMediaEntity> findNonPubblicatiByDataInizioAndDataFine(Date dataInizio, Date dataFine);

    List<PianoMediaEntity> findOpenPianiMedia();

    List<PianoMediaEntity> findNonPubblicatiPianiMedia();

    List<PianoMediaEntity> findNotCancelled();

    List<Integer> findOpenAvailableYears();

    List<PianoMediaEntity> findPubblicatiByDataInizio(@NonNull Date dataInizio);
    List<PianoMediaEntity> findOpenByDataFine(@NonNull Date dataFine);

    default List<PianoMediaEntity> findPubblicatiByDataInizio(){
        return findPubblicatiByDataInizio(new Date());
    }
    default List<PianoMediaEntity> findOpenByDataFine(){return findOpenByDataFine(new Date());}

    List<PianoMediaEntity> findPianiMediaAccessibiliInPianificazione();
}
