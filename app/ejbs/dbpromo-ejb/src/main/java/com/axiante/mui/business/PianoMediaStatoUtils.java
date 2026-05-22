package com.axiante.mui.business;

import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Dependent
@Slf4j
public class PianoMediaStatoUtils {
    private final List<String> APPROVE_ENABLE_STATUS_LIST = Arrays.asList("300", "310", "320");

    public StatoPromozioneEntity getStato(PianoMediaEntity pianoMediaEntity) {
        PianoMediaStatoEntity e = pianoMediaEntity.getCurrentStatus();
        switch (e.getStato().getCodiceStato()) {
            case "00":
            case "500":
            case "400":
            case "410":
                return e.getStato();
        }
        return calcolaStato(pianoMediaEntity.getDettagliPianificazione());
    }

    /**
     * Calcola lo stato del piano media in base allo stato della testata e delle pianificazioni associate.
     * Questo metodo garantisce, a differenza dei metodi che prendono una lista o un set di dettaglio pianificazione,
     * di ritornare uno stato: se lo stato della testata e' uno stato che non implica lo "stato minimo" dei dettagli allora
     * ritorna lo stato della testata, altrimenti ritorna lo stato minimo dei dettagli.
     *
     * @param e
     * @return
     */
    public StatoPromozioneEntity calcolaStato(PianoMediaEntity e) {
        PianoMediaStatoEntity s = e.getCurrentStatus();
        switch (s.getStato().getCodiceStato()) {
            case "00":
            case "30":
            case "500":
            case "400":
            case "410":
                return s.getStato();
        }
        return calcolaStato(e.getDettagliPianificazione());
    }

    public StatoPromozioneEntity calcolaStato(Set<PianoMediaPianificazioneDettaglioEntity> list) {
        return calcolaStato(list.stream());
    }

    public StatoPromozioneEntity calcolaStato(List<PianoMediaPianificazioneDettaglioEntity> list) {
        return calcolaStato(list.stream());
    }

    private StatoPromozioneEntity calcolaStato(Stream<PianoMediaPianificazioneDettaglioEntity> list) {
        List<StatoPromozioneEntity> stati = list.map(
                // ci sono i dati vecchi che potrebbero non avere lo stato a livello di pianificazione
                d -> {
                    if (d.getStato() == null) {
                        return d.getPianoMedia().getCurrentStatus().getStato();
                    }
                    return d.getStato();
                }
        ).distinct().collect(Collectors.toList());
        List<String> codici = stati.stream().map(StatoPromozioneEntity::getCodiceStato).collect(Collectors.toList());
        if (codici.contains("320")) {
            return stati.stream().filter(s -> s.getCodiceStato().equals("320")).findFirst().orElse(null);
        } else if (codici.contains("300")) {
            return stati.stream().filter(s -> s.getCodiceStato().equals("300")).findFirst().orElse(null);
        } else {
            return stati.stream().filter(s -> s.getCodiceStato().equals("310")).findFirst().orElse(null);
        }
    }

    public PianoMediaEntity cambiaStato(@NonNull PianoMediaEntity pianoMedia, @NonNull StatoPromozioneEntity nuovoStato, @NonNull String user) {
        return cambiaStato(pianoMedia, nuovoStato, user, true);
    }

    /**
     * Cambia lo stato al piano media; se il flag propagate e' true, cambia lo stato anche alle pianificazioni associate
     *
     * @param pianoMedia piano media al quale cambiare lo stato
     * @param stato      il nuovo stato da impostare
     * @param propagate  true propaga il nuovo stato alle pianificazioni associate, false no
     * @return il piano media con lo stato aggiornato
     */
    public PianoMediaEntity cambiaStato(@NonNull PianoMediaEntity pianoMedia, @NonNull StatoPromozioneEntity stato, @NonNull String user, boolean propagate) {
        PianoMediaStatoEntity statoCorrente = pianoMedia.getCurrentStatus();
        if (!statoCorrente.getStato().getCodiceStato().equals(stato.getCodiceStato())) {
            PianoMediaStatoEntity nuovoStato = (PianoMediaStatoEntity) AuditLogFiller.fillAuditLogFields(new PianoMediaStatoEntity(), user);
            nuovoStato.setStato(stato);
            nuovoStato.setDataInizioStato(new Date());
            statoCorrente = pianoMedia.removePianoMediaStato(statoCorrente);
            statoCorrente.setDataFineStato(nuovoStato.getDataInizioStato());
            statoCorrente = (PianoMediaStatoEntity) AuditLogFiller.fillAuditLogFields(statoCorrente, user);
            pianoMedia.addPianoMediaStato(statoCorrente);
            pianoMedia.addPianoMediaStato(nuovoStato);
            if (propagate) {
                pianoMedia.getDettagliPianificazione().forEach(d -> d.setStato(stato));
            }
        }
        return pianoMedia;

    }

    /**
     * Controlla se lo stato corrente del piano media permette l'abilitazione delle azioni di approvazione / non approvazione
     *
     * @param pianoMedia piano media da controllare
     * @return true se le azioni di approvazione / non approvazione sono permesse, false altrimenti
     */
    public boolean isApproveEnabled(@NonNull PianoMediaEntity pianoMedia) {
        //TODO: questo non e' vero. Lo stato 320/300 potrebbe non coinvolgere tutti i dettagli
        final StatoPromozioneEntity statoCorrente = getStato(pianoMedia);
        if (statoCorrente == null) {
            return false;
        }
        return APPROVE_ENABLE_STATUS_LIST.contains(statoCorrente.getCodiceStato());
    }
}
