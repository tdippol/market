package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.SottoscrizioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.SottoscrizioneService;
import com.axiante.mui.webapp.views.FacesContextAware;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SottoscrizioniBackingBean implements FacesContextAware {
    @Getter(value = AccessLevel.PROTECTED)
    private String user;

    @Getter
    @Setter
    PromozioneTestataEntity promozioneCorrente;

    @Getter
    @Setter
    SottoscrizioneEntity selectedSottoscrizione;

    @Getter
    List<SottoscrizioneEntity> sottoscrizioniDisponibili;

    private PromoService promoService;
    private SottoscrizioneService sottoscrizioneService;

    public SottoscrizioniBackingBean(PromoService promoService, SottoscrizioneService sottoscrizioneService, String user) {
        this.promoService = promoService;
        this.sottoscrizioneService = sottoscrizioneService;
        this.user = user;
    }

    public void prepareDialog() {
        loadSottoscrizioni();
        this.selectedSottoscrizione = null;
    }

    public void aggiungiSottoscrizione() {
        if (selectedSottoscrizione != null && promoService != null && promozioneCorrente != null) {
            if (promozioneCorrente.getSottoscrizioni().contains(selectedSottoscrizione)) {
                final String message = String.format("Sottoscrizione con codice '%s' già associata alla promozione corrente",
                        selectedSottoscrizione.getCodice());
                log.error(message);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore", message));
                return;
            }
            promozioneCorrente.addSottoscrizione(selectedSottoscrizione);
            try {
                promozioneCorrente = promoService.persist(promozioneCorrente, user);
                executeScript("updateSottoscrizioniRowData(" + promozioneCorrente.getId() + ");");
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
                        "Sottoscrizione inserita correttamente"));
            } catch (Exception ex) {
                log.error("Error adding Sottoscrizione", ex);
                promozioneCorrente.removeSottoscrizione(selectedSottoscrizione);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore",
                        "Impossibile aggiungere la Sottoscrizione selezionata"));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore",
                    "Errore aggiunta sottoscrizione; contattare l'assistenza"));
        }
    }

    private void loadSottoscrizioni() {
        if (promozioneCorrente != null) {
            this.sottoscrizioniDisponibili = sottoscrizioneService.findAll().stream()
                    .filter(s -> !promozioneCorrente.getSottoscrizioni().contains(s))
                    .sorted(Comparator.comparing(SottoscrizioneEntity::getCodice))
                    .collect(Collectors.toList());
        } else {
            this.sottoscrizioniDisponibili = sottoscrizioneService.findAll().stream()
                    .sorted(Comparator.comparing(SottoscrizioneEntity::getCodice))
                    .collect(Collectors.toList());
        }
    }
}
