package com.axiante.mui.webapp.views.content.dbpromo.data;


import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CastellettoMessaggiService;
import com.axiante.mui.webapp.views.FacesContextAware;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class CastellettiBean implements Serializable, FacesContextAware {
    private static final long serialVersionUID = 4932308577464351812L;
    private static final int TESTO_MAX_LENGTH = 42;

    @Getter
    private boolean buttonInizializzaDisabled = false;

    @Getter
    private boolean buttonEliminaDisabled = true;
    private List<CastellettoMessaggiEntity> castellettoMessaggi;

    @Inject
    Instance<CastellettoMessaggiService> service;

    //campi del form
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Integer sequenza = null;

    @Getter
    @Setter
    private MessaggiSezioneEnum sezione = MessaggiSezioneEnum.RIGA;

    @Getter
    private String testo = null;

    @Getter
    private final MessaggiSezioneEnum[] sezioni = {MessaggiSezioneEnum.RIGA, MessaggiSezioneEnum.TITOLO};

    private PromozionePianificazioneEntity pianificazione;

    @Getter
    private Long idCastellettoToBeDeleted;

    public Integer getTestoMaxLength() {
        return TESTO_MAX_LENGTH;
    }

    public void setTesto(String testo) {
        if (testo != null && testo.length() > TESTO_MAX_LENGTH) {
            log.warn(String.format("Testo troppo lungo viene troncato a %d caratteri", TESTO_MAX_LENGTH));
            testo = testo.substring(0, TESTO_MAX_LENGTH);
        }
        this.testo = testo;
    }

    private void calcolaStatoBottoni() {
        if (castellettoMessaggi != null) {
            buttonInizializzaDisabled = true;
            buttonEliminaDisabled = false;
        } else {
            buttonInizializzaDisabled = false;
            buttonEliminaDisabled = true;
        }
    }

    /**
     * inizializza il castelletto con 5 valori standard pre-popolati
     */
    public void inizializzaCastelletti() {
        if (castellettoMessaggi != null && !castellettoMessaggi.isEmpty()) {
            throw new IllegalStateException("Castelletto già inizializzato");
        }

        castellettoMessaggi = new ArrayList<>();
        castellettoMessaggi.add(newCastelletto(MessaggiSezioneEnum.TITOLO, 1, pianificazione,
                "--------CASHBACK MULTITRANSAZIONE---------"));
        castellettoMessaggi.add(newCastelletto(MessaggiSezioneEnum.RIGA, 2, pianificazione,
                "SALDO ACCUMULATO AD OGGI"));
        castellettoMessaggi.add(newCastelletto(MessaggiSezioneEnum.RIGA, 3, pianificazione,
                "QUOTA ACCUMULATA"));
        castellettoMessaggi.add(newCastelletto(MessaggiSezioneEnum.RIGA, 4, pianificazione,
                "TORNASCONTO RITIRATO"));
        castellettoMessaggi.add(newCastelletto(MessaggiSezioneEnum.RIGA, 5, pianificazione,
                "NUOVO SALDO"));
        try {
            for (CastellettoMessaggiEntity castellettoMessaggiEntity : castellettoMessaggi) {
                service.get().persistWithAuditLog(castellettoMessaggiEntity, getCurrentUser().getName());
            }
            addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Castelletto inizializzato")
            );
        } catch (Exception e) {
            log.error("Errore durante l'inizializzazione del castelletto", e);
            addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore", "Errore durante l'inizializzazione del castelletto")
            );
        } finally {
            calcolaStatoBottoni();
        }
    }

    /**
     * Elimina tutti i valori di castelletto messaggi
     */
    public void eliminaCastelletti() {
        // devo rileggere i castelletti ... sia mai che qualcuno li abbia modificati
        inizializza(pianificazione.getId());
        if (castellettoMessaggi == null || castellettoMessaggi.isEmpty()) {
            throw new IllegalStateException("Castelletto non inizializzato");
        }
        try {
            service.get().remove(castellettoMessaggi.stream().map(CastellettoMessaggiEntity::getId).collect(Collectors.toList()));
            castellettoMessaggi = null;
            addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Castelletti eliminati"));
        } catch (Exception e) {
            log.error("Errore durante l'eliminazione dei castelletti", e);
            addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore", "Errore durante l'eliminazione dei castelletti"));
        } finally {
            calcolaStatoBottoni();
        }
    }

    public void aggiorna() {
        CastellettoMessaggiEntity e;
        if (this.id != null) {
            e = service.get().findById(this.id);
        } else {
            e = new CastellettoMessaggiEntity();
            int max = castellettoMessaggi.stream().max(Comparator.comparing(CastellettoMessaggiEntity::getSeqStampa)).get().getSeqStampa();
            e.setSeqStampa(max + 1);
            castellettoMessaggi.add(e);
            e.setPianificazione(pianificazione);
        }
        e.setSezione(this.sezione);
        e.setTesto(this.testo);
        service.get().persistWithAuditLog(e, getCurrentUser().getName());
        reset();
    }

    private CastellettoMessaggiEntity newCastelletto(MessaggiSezioneEnum sezione, int seqStampa,
                                                     PromozionePianificazioneEntity pianificazione,
                                                     String testo) {
        CastellettoMessaggiEntity castellettoMessaggiEntity = new CastellettoMessaggiEntity();
        castellettoMessaggiEntity.setSezione(sezione);
        castellettoMessaggiEntity.setSeqStampa(seqStampa);
        castellettoMessaggiEntity.setPianificazione(pianificazione);
        castellettoMessaggiEntity.setTesto(testo);
        return castellettoMessaggiEntity;
    }

    public void setPianificazione(PromozionePianificazioneEntity pianificazione) {
        this.pianificazione = pianificazione;
        inizializza(pianificazione.getId());
        reset();
    }

    private void inizializza(Long idPianificazione) {
        castellettoMessaggi = service.get().findCastellettiByIdPianificazione(idPianificazione);
        if (castellettoMessaggi.isEmpty()) {
            castellettoMessaggi = null;
        } else {
            // controllo la situazione a db.
            // MG 2024-05-22: il controllo viene lasciato su 4 "righe castelletto",
            // anche se la CR #5236 indica di impostare le righe pre-popolate a 5
            // questo per NON invalidare i castelletti gia' creati e rompere il caricamento della pagina
            if (castellettoMessaggi.size() < 4) {
                addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore", "Castelletto non inizializzato correttamente"));
                throw new IllegalStateException("Castelletto non inizializzato correttamente");
            }
        }
        calcolaStatoBottoni();
    }

    public void reset() {
        this.sequenza = null;
        this.sezione = MessaggiSezioneEnum.RIGA;
        this.testo = null;
        this.id = null;
    }

    public void selezionaCastelletto() {
        try {
            final String messaggioIdParam = getRequestParameterMap().get("castellettoSelected");
            final Long idCastellettoMessaggi = JsonUtils.getMapper().readTree(messaggioIdParam).asLong();
            prepareUpsert(idCastellettoMessaggi);
        } catch (Exception ex) {
            log.error("Error reading selected row", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore selezione castelletto; contattare l'assistenza"));
        }
    }

    private void prepareUpsert(Long idCastellettoMessaggi) {
        reset();
        boolean _new = (idCastellettoMessaggi == null);
        if (!_new) {
            CastellettoMessaggiEntity e = service.get().findById(idCastellettoMessaggi);
            if (e != null) {
                this.id = e.getId();
                this.testo = e.getTesto();
                this.sezione = e.getSezione();
                this.sequenza = e.getSeqStampa();
            } else {
                throw new IllegalStateException("Castelletto non trovato");
            }
        }
    }

    public void prepareDeleteCastellettoDialog(){
        try {
            final String idCastelletto = getRequestParameterMap().get("id");
            if (idCastelletto != null) {
                idCastellettoToBeDeleted = Long.parseLong(idCastelletto);
                executeScript("PF('deleteCastellettoConfirmDialog').show();");
            } else {
                log.error("impossibile valorizzare l'id del castelletto da eliminare");
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore eliminazione castelletto; contattare l'assistenza"));
            }
        } catch (Exception ex) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore eliminazione castelletto; contattare l'assistenza"));
        }
    }

}