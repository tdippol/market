package com.axiante.mui.backing;

import com.axiante.mui.business.PianoMediaStatoUtils;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Singleton
@Slf4j
public class PianoMediaScheduler implements Serializable {
    @Inject
    PianoMediaService pianoMediaService;

    @Inject
    PianoMediaStatoUtils pianoMediaStatoUtils;

    @Inject
    private StatoPromoService statoPromoService;

    @Getter(AccessLevel.PROTECTED)
    private StatoPromozioneEntity statoInCorso = null;

    @Getter(AccessLevel.PROTECTED)
    private StatoPromozioneEntity statoChiuso = null;

    private static final long serialVersionUID = -5603472987746797382L;
    private static final Object semaphore = new Object();

    public static final String USER = "SCHEDULER";

    @PostConstruct
    public void init() {
        try {
            synchronized (semaphore) {
                statoInCorso = statoPromoService.findByCode("400");
            }
        } catch (Exception e) {
            log.error("Errore di inizializzazione, impossibile attivare l'apertura automatica dei piani media", e);
        }
        try {
            synchronized (semaphore) {
                statoChiuso = statoPromoService.findByCode("500");
            }
        } catch (Exception e) {
            log.error("Errore di inizializzazione, impossibile attivare la chiusura automatica dei piani media", e);
        }
    }

    /**
     * This method is called every day at 6:30:42 AM
     */
    @Schedule(second = "42", minute = "30", hour = "6", persistent = false)
    public void forceOpen() {
        if (!canRunForceOpen()) {
            log.error("Impossibile aprire automaticamente i piani media: stato in corso non trovato");
            return;
        }
        List<PianoMediaEntity> piani = pianoMediaService.findPubblicatiByDataInizio();
        cambiaStato(piani, statoInCorso);
    }

    /**
     * This method is called every day at 0:00:36 AM
     */
    @Schedule(second = "36", minute = "00", hour = "0", persistent = false)
    public void forceClose() {
        if (!canRunForceClose()) {
            log.error("Impossibile chiudere automaticamente i piani media: stato chiuso non trovato");
            return;
        }
        List<PianoMediaEntity> piani = pianoMediaService.findOpenByDataFine();
        cambiaStato(piani, statoChiuso);
    }

    protected void cambiaStato(List<PianoMediaEntity> piani, @NonNull StatoPromozioneEntity stato) {
        final String exceptionMessage = String.format("Errore nella %s automatica del piano media {}", stato.getCodiceStato().equals("400") ? "apertura" : "chiusura");
        final String debugMessage = String.format("Trovati {} piani media da %s", stato.getCodiceStato().equals("400") ? "aprire" : "chiudere");
        if (piani != null && !piani.isEmpty()) {
            log.debug(debugMessage, piani.size());
            for (PianoMediaEntity piano : piani) {
                try {
                    piano = pianoMediaStatoUtils.cambiaStato(piano, stato, USER, false);
                    pianoMediaService.persistWithAuditLog(piano, USER);
                    log.debug("Piano media {} in stato {} ", piano.getId(), stato.getDescrizione());
                } catch (Exception e) {
                    log.error(exceptionMessage, piano.getId(), e);
                }
            }
        }
    }

    protected boolean canRunForceOpen() {
        boolean result = getStatoInCorso() != null;
        if (!result) {
            init();
            result = getStatoInCorso() != null;
        }
        return result;
    }

    protected boolean canRunForceClose() {
        boolean result = getStatoChiuso() != null;
        if (!result) {
            init();
            result = getStatoChiuso() != null;
        }
        return result;
    }
}
