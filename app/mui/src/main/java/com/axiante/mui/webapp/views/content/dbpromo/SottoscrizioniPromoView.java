package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.dbpromo.persistence.entities.SottoscrizioneEntity;
import com.axiante.mui.dbpromo.persistence.service.SottoscrizioneService;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.MenuItem;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@MuiViewModel("sottoscrizioniPromo")
@Dependent
@Slf4j
public class SottoscrizioniPromoView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = 5763222936814700651L;

    @Getter
    @Setter
    private String codice;

    @Getter
    @Setter
    private String descrizione;

    @Inject
    private Instance<SottoscrizioneService> sottoscrizioneServiceInstance;

    private List<String> usedCodes = new ArrayList<>();

    public void initCreaSottoscrizioneDialog() {
        init();
    }

    private void init() {
        loadUsedCodes();
        resetFields();
    }

    public void confirmSottoscrizione() {
        if (validate()) {
            try {
                saveSottoscrizione();
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sottoscrizione salvata",
                        "Sottoscrizione salvata correttamente"));
            } catch (Exception ex) {
                log.error("Error saving Sottoscrizione", ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore salvataggio Sottoscrizione; contattare l'assistenza"));
            }
        } else {
            log.warn(String.format("Sottoscrizione not valid; codice: %s, descrizione: %s", codice, descrizione));
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Dati inseriti non validi"));
        }
    }

    private void loadUsedCodes() {
        usedCodes = sottoscrizioneServiceInstance.get().findAll().stream()
                .map(SottoscrizioneEntity::getCodice)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    private void resetFields() {
        codice = null;
        descrizione = null;
    }

    private boolean validate() {
        return codice != null && !codice.trim().isEmpty() && !usedCodes.contains(codice.toUpperCase())
                && descrizione != null && !descrizione.trim().isEmpty();
    }

    private void saveSottoscrizione() {
        SottoscrizioneEntity sottoscrizione = new SottoscrizioneEntity();
        sottoscrizione.setCodice(codice.trim().toUpperCase());
        sottoscrizione.setDescrizione(descrizione.trim());
        sottoscrizioneServiceInstance.get().persistWithAuditLog(sottoscrizione, getCurrentUser().getName());
    }

    @Override
    public void setNode(MenuItem node) {
        super.setNode(node);
        applyDefaultRules();
        init();
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
