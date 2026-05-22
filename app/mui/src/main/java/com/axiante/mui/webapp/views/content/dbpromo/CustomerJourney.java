package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.entities.SottoclasseEntity;
import com.axiante.mui.dbpromo.persistence.service.SottoclasseService;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@MuiViewModel("customerJourney")
@Slf4j
public class CustomerJourney extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = -6132159742653998967L;

    @Inject
    private Instance<SottoclasseService> sottoclasseServiceInstance;

    @Getter
    @Setter
    private String codice;

    @Getter
    @Setter
    private String descrizione;

    @Getter
    @Setter
    private Integer priorita;

    @Getter
    @Setter
    private Integer delay;

    @Getter
    @Setter
    private Boolean attiva;

    @Getter
    private Boolean btnPubblicaSottoclassiDisabled = Boolean.FALSE;

    @PostConstruct
    public void init() {
        initDialog();
        this.btnPubblicaSottoclassiDisabled = handleBtnPubblicaSottoclassi();
    }

    public void initDialog() {
        resetForms();
    }

    public void confermaCreazione() {
        if (!validateForm() || getUserDto().getUsermame() == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Errore", "Tutti i campi devono essere compilati"));
            return;
        }
        String cleanedCodice = codice.trim().toUpperCase();
        String cleanedDescrizione = descrizione.trim().toUpperCase();
        if (sottoclasseServiceInstance.get().existsByCodeOrDescription(cleanedCodice, cleanedDescrizione)) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    String.format("Esiste già una sottoclasse con codice '%s' o descrizione '%s'",
                            cleanedCodice, cleanedDescrizione)));
            return;
        }
        SottoclasseEntity sottoclasse = new SottoclasseEntity();
        sottoclasse.setCodice(cleanedCodice);
        sottoclasse.setDescrizione(cleanedDescrizione);
        sottoclasse.setPriorita(priorita);
        sottoclasse.setDelay(delay);
        sottoclasse.setAttiva(attiva);
        try {
            sottoclasseServiceInstance.get().persistWithAuditLog(sottoclasse, getUserDto().getUsermame());
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sottoclasse creata",
                    String.format("Sottoclasse '%s' creata correttamente", cleanedDescrizione)));
        } catch (Exception ex) {
            log.error("Error creating sottoclasse", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Errore", "Errore creazione sottoclasse"));
        }
        executeScript("PF('addSottoclasseDialog').hide()");
    }

    public Boolean btnConfermaCreazioneDisabled() {
        return !validateForm();
    }

    public void pubblicaSottoclassi() {
        final String username = getCurrentUser().getName();
        boolean spResult = executeStoredProcedure(username);
        if (spResult) {
            try {
                updateDataScarico(username);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sottoclassi pubblicate",
                        "Sottoclassi pubblicate correttamente"));
                this.btnPubblicaSottoclassiDisabled = handleBtnPubblicaSottoclassi();
            } catch (Exception ex) {
                log.error("Error updating data scarico", ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore pubblicazione sottoclassi"));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore pubblicazione sottoclassi"));
        }
        executeScript("PF('pubblicaSottoclassiDialog').hide()");
    }

    public boolean handleBtnPubblicaSottoclassi() {
        return sottoclasseServiceInstance.get().countSottoclassiNonScaricate() == 0;
    }

    private void updateDataScarico(String username) {
        final Date now = new Date();
        final List<SottoclasseEntity> sottoclassi = sottoclasseServiceInstance.get().findAll().stream()
                .map(s -> {
                    s.setDataScarico(now);
                    return (SottoclasseEntity) AuditLogFiller.fillAuditLogFields(s, username);
                }).collect(Collectors.toList());
        sottoclasseServiceInstance.get().persistInTransaction(sottoclassi);
    }

    private boolean executeStoredProcedure(String username) {
        boolean result = true;
        try {
            result = sottoclasseServiceInstance.get().runFunctionPubblicaSottoclassi(username);
        } catch (Exception ex) {
            log.error(String.format("Stored procedure %s terminata con errore", Constants.P_MUI_EXPORT_SOTTOCLASSE), ex);
            result = false;
        }
        return result;
    }

    private boolean validateForm() {
        return validateNotBlankField(codice) && validateNotBlankField(descrizione)
                && validateIntegerField(priorita, 1, 999)
                && validateIntegerField(delay, 0, 999)
                && attiva != null;
    }

    private void resetForms() {
        this.codice = null;
        this.descrizione = null;
        this.priorita = null;
        this.delay = null;
        this.attiva = true;
    }

    private boolean validateNotBlankField(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private boolean validateIntegerField(Integer value, Integer minValue, Integer maxValue) {
        return value != null && value >= minValue && value <= maxValue;
    }

    @Override
    public void applyRules() {
        // noop
    }

    @Override
    public void applyDefaultRules() {
        // noop
    }
}
