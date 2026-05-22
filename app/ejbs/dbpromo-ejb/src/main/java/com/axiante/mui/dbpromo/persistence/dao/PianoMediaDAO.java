package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import java.util.Date;
import java.util.List;
import lombok.NonNull;

public interface PianoMediaDAO extends DbPromoDAO<PianoMediaEntity> {
    List<PianoMediaEntity> findByDataInizioAndDataFine(Date dataInizio, Date dataFine);
    List<PianoMediaEntity> findNonPubblicatiByDataInizioAndDataFine(Date dataInizio, Date dataFine);

    List<PianoMediaEntity> findOpenPianiMedia();
    List<PianoMediaEntity> findNonPubblicatiPianiMedia();

    List<PianoMediaEntity> findNotCancelled();

    List<Integer> findOpenAvailableYears();

    List<PianoMediaEntity> findPubblicatiByDataInizio(Date dataInizio);

    List<PianoMediaEntity> findOpenByDataFine(@NonNull Date dataFine);
    List<PianoMediaEntity> findPianiMediaAccessibiliInPianificazione();
}
