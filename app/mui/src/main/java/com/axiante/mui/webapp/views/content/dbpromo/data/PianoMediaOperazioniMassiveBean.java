package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazionePianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaService;
import com.axiante.mui.webapp.business.service.PianoMediaHelperService;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.webservice.util.PianoMediaFactory;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class PianoMediaOperazioniMassiveBean implements Serializable, FacesContextAware {
    private static final long serialVersionUID = 1448917947459399239L;

    boolean toggleAll = false;

    @Getter
    @Setter
    Date dataInizio;

    @Getter
    @Setter
    Date dataFine;

    @Inject
    @Getter(AccessLevel.PROTECTED)
    private Instance<PianoMediaFactory> pianoMediaFactoryInstance;

    @Inject
    @Getter(AccessLevel.PROTECTED)
    private Instance<PianificazionePianoMediaService> pianificazionePianoMediaServiceInstance;

    @Inject
    @Getter(AccessLevel.PROTECTED)
    private Instance<PianoMediaService> pianoMediaServiceInstance;

    @Inject
    @Getter(AccessLevel.PROTECTED)
    private Instance<PianoMediaHelperService> pianoMediaHelperServiceInstance;

    @PostConstruct
    public void init() {
        this.toggleAll = true;
    }

    public void reinizializza() {
        try {
            List<PianoMediaEntity> pianiMedia = null;
            if (getToggleAll()) {
                pianiMedia = getPianoMediaServiceInstance().get().findNonPubblicatiPianiMedia();
            } else {
                pianiMedia = getPianoMediaServiceInstance().get().findNonPubblicatiByDataInizioAndDataFine(getDataInizio(), getDataFine());
            }
            final PianoMediaHelperService pianoMediaHelperService = getPianoMediaHelperServiceInstance().get();
            final String username = getCurrentUser().getName();
            int failedChecks = 0;
            for (PianoMediaEntity pianoMedia : pianiMedia) {
                // Rimuovo controlli associati e pianificazioni
                pianoMediaHelperService.removeExistingChecks(pianoMedia);
                getPianificazionePianoMediaServiceInstance().get().remove(pianoMedia.getConfigurazioniPianoMedia());
                // Inizializzo pianificazioni secondo default
                pianoMedia.setConfigurazioniPianoMedia(getPianoMediaFactoryInstance().get().createDefaults(pianoMedia, username));
                pianoMedia = getPianoMediaServiceInstance().get().persist(pianoMedia);
                // Eseguo controlli
                failedChecks += pianoMedia.getConfigurazioniPianoMedia().stream().map(
                        p -> pianoMediaHelperService.executeChecks(p, username)
                        ).collect(Collectors.summingInt(Integer::intValue));
//                        .forEach(p -> pianoMediaHelperService.executeChecks(p, username));
            }
            if ( failedChecks > 0 ){
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                        String.format("Sono stati reinizializzati %d piani media e sono stati rilevati %d errori", pianiMedia.size(), failedChecks)));
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                        String.format("Sono stati reinizializzati %d piani media", pianiMedia.size())));
            }
        } catch (Exception e) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore durante la reinizializzazione dei piani media, contattare l'assisitenza"));
        }
    }

    public void confirmReinizializza() {
        if ( !getToggleAll() ) {
            if (getDataInizio() == null || getDataFine() == null) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Selezionare le date di inizio e fine per la reinizializzazione"));
                return;
            }
        }
        executeScript("PF('confirmDlgResetPianiMedia').show()");
    }

    public String getConfirmMessage(){
        String dates = "";
        if ( !getToggleAll() ){
                if ( getDataInizio() == null || getDataFine() == null ){
                    log.error("getConfirmMessage: daData o aData null");
                    return "Errore durante la creazione del messaggio di conferma, cliccare \"NO\" e contattare l'assistenza";
                }
                dates = String.format("dal %s al %s ", DateTimeUtils.getFormatoItaliano().format(getDataInizio()),
                        DateTimeUtils.getFormatoItaliano().format(getDataFine())
            );
        }
        return String.format(
                "Questa operazione non puo' essere interrotta o annullata!\nSei sicuro di voler reinizializzare tutti i piani media %s?", dates
        );
    }
    public void setToggleAll(boolean toggleAll) {
        this.toggleAll = toggleAll;
    }
    public boolean getToggleAll() {
        return this.toggleAll;
    }

    public boolean getDisplayDates(){
        return !getToggleAll();
    }
}
