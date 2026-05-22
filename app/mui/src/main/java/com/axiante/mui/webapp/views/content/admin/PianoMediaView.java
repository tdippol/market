package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.entity.PianoMediaSecurityEntity;
import com.axiante.mui.persistence.service.PianoMediaSecurityService;
import com.axiante.mui.webapp.utils.CanaleUtils;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.tm1.mdx.objects.Query;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@MuiViewModel("gestionePianoMedia")
@Named("gestionePianoMedia")
@SessionScoped
public class PianoMediaView extends AbstractAdminView {
    private static final long serialVersionUID = 3947613700502323576L;

    @Inject
    @Getter
    transient private CatalogReducer catalogReducer;

    @Inject
    @Getter
    transient private ApplicationFilterCatalogProducer catalogProducer;

    @Inject
    @Getter
    private SicurezzaAccessiBean accessiBean;

    @Getter
    List<CanalePromozioneEntity> canali;

    @Getter
    List<PianoMediaSecurityEntity> sicurezzaAccessi;

    @Getter
    @Setter
    PianoMediaSecurityEntity selectedSecurity;

    @Inject
    private ApplicationProperties applicationProperties;

    @Inject
    private CanalePromozioneService canaleService;

    @Inject
    private PianoMediaSecurityService securityService;

    @Getter
    private Long idCanaleSelezionato;

    @Inject
    CanaleUtils canaleUtils;

    @PostConstruct
    public void init() {
        log.info("PianoMediaView init");
        try {
            readCanali();
            final CanalePromozioneEntity c = getCanaleFromCodice(applicationProperties.getProperty(ApplicationProperties.CANALE_PIANO_MEDIA, (Long) null));
            if (c != null) {
                idCanaleSelezionato = c.getId();
            } else {
                idCanaleSelezionato = null;
            }
        } catch (Exception e) {
            log.error("Error reading canali", e);
            canali = new ArrayList<>();
        }
        try {
            readSicurezzaAccessi();
        } catch (Exception ex) {
            log.error("Error reading sicurezza accessi", ex);
            sicurezzaAccessi = new ArrayList<>();
        }
    }

    private void readCanali() {
        canali = canaleService.findAll();
        canali.sort(canaleUtils.getComparatorByGruppo().thenComparing(canaleUtils.getComparatorByCanale()));
    }

    private void readSicurezzaAccessi() {
        sicurezzaAccessi = securityService.readAll().stream()
                .sorted(Comparator.comparing(s -> s.getGroup().getDescrizione()))
                .collect(Collectors.toList());
    }

    public void toggleAccessoScheda(PianoMediaSecurityEntity security) {
        updateSecurity(security, "Accesso Scheda", security.getAccessoScheda().getSecurity(),
                security.getGroup().getDescrizione());
    }

    public void toggleAccessoPianificazione(PianoMediaSecurityEntity security) {
        updateSecurity(security, "Accesso Pianificazione", security.getAccessoPianificazione().getSecurity(),
                security.getGroup().getDescrizione());
    }

    public void togglePianificazioneCompratore(PianoMediaSecurityEntity security) {
        updateSecurity(security, "Pianificazione Compratore", security.getPianificazioneCompratore().toString(),
                security.getGroup().getDescrizione());
    }

    public void toggleFiltroArticoli(PianoMediaSecurityEntity security) {
        updateSecurity(security, "Filtro Articolo", security.getFiltroArticoli().toString(),
                security.getGroup().getDescrizione());
    }

    private void updateSecurity(@NonNull PianoMediaSecurityEntity security, String field, String value, String group) {
        try {
            securityService.persist(security);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modifica effettuata",
                    String.format("Modificato '%s' in '%s' per il gruppo '%s'", field, value, group)));
        } catch (Exception ex) {
            log.error(String.format("Error updating '%s' on group '%s'", field, group), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
                    String.format("Errore modifica '%s' sul gruppo '%s'", field, group)));
        }
    }

    public void removeSecurity() {
        if (selectedSecurity != null) {
            try {
                securityService.remove(selectedSecurity);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                        String.format("Rimozione security sul gruppo '%s - %s'",
                                selectedSecurity.getGroup().getCodiceGruppo(),
                                selectedSecurity.getGroup().getDescrizione())));
                setSelectedSecurity(null);
                readSicurezzaAccessi();
            } catch (Exception ex) {
                log.error(String.format("Error deleting security with id '%d'", selectedSecurity.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore rimozione security"));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Nessuna security selezionata"));
        }
    }

    public void openAddSecurityDialog() {
        accessiBean.resetDialog();
        accessiBean.loadGroups(sicurezzaAccessi.stream()
                .map(PianoMediaSecurityEntity::getGroup)
                .collect(Collectors.toList()));
    }

    public void addSecurityDialogConfirm() {
        try {
            if (accessiBean.validate()) {
                final PianoMediaSecurityEntity entity = new PianoMediaSecurityEntity();
                entity.setGroup(accessiBean.getGruppoSelected());
                entity.setAccessoScheda(accessiBean.getAccessoScheda());
                entity.setAccessoPianificazione(accessiBean.getAccessoPianificazione());
                entity.setPianificazioneCompratore(accessiBean.getPianificazioneCompratore());
                entity.setFiltroArticoli(accessiBean.getFiltroArticoli());
                securityService.persist(entity);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                        String.format("Aggiunta security sul gruppo '%s - %s'",
                                accessiBean.getGruppoSelected().getCodiceGruppo(),
                                accessiBean.getGruppoSelected().getDescrizione())));
                readSicurezzaAccessi();
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore validazione security"));
            }
        } catch (Exception ex) {
            log.error("Error adding security", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore aggiunta security"));
        }
    }

    public void setModalitaCompratore(boolean modalita) {
        try {
            ApplicationPropertiesEntity modalitaCompratore = applicationProperties.getMuiService()
                    .findApplicationProperty(ApplicationProperties.MEDIA_MODALITA_COMPRATORE);
            if (modalitaCompratore == null) {
                modalitaCompratore = new ApplicationPropertiesEntity();
                modalitaCompratore.setKey(ApplicationProperties.MEDIA_MODALITA_COMPRATORE);
            }
            modalitaCompratore.setValue(modalita ? "true" : "false");
            updateProperties(modalitaCompratore);
        } catch (Exception e) {
            log.error("Error updating modalita compratore", e);
        }
    }

    public boolean getModalitaCompratore(){
        return applicationProperties.getProperty(ApplicationProperties.MEDIA_MODALITA_COMPRATORE, Boolean.FALSE);
    }

    @Override
    public void updateView() {
        // Do nothing
    }

    @Override
    public void updateView(final String grid) {
        updateView();
    }

    @Override
    public Query prepareFilteredQuery(final String grid) {
        return null;
    }

    public void setIdCanaleSelezionato(Long idCanaleSelezionato) {
        this.idCanaleSelezionato = idCanaleSelezionato;
        try {
            ApplicationPropertiesEntity canale = applicationProperties.getMuiService().findApplicationProperty(ApplicationProperties.CANALE_PIANO_MEDIA);
            if ( canale == null ){
                canale = new ApplicationPropertiesEntity();
                canale.setKey(ApplicationProperties.CANALE_PIANO_MEDIA);
            }
            CanalePromozioneEntity c = getCanaleFromId(idCanaleSelezionato);
            canale.setValue(c==null?null:c.getCodiceCanale().toString());
            updateProperties(canale);
        } catch (Exception e) {
            log.error("Error updating canale piano media", e);
        }
    }

    private CanalePromozioneEntity getCanaleFromId(Long idCanaleSelezionato){
        if ( idCanaleSelezionato != null )
            return canali.stream().filter(c->c.getId().equals(idCanaleSelezionato)).findFirst().orElse(null);
        return null;
    }

    private CanalePromozioneEntity getCanaleFromCodice(Long codiceCanale){
        if ( codiceCanale != null ){
            return canali.stream().filter(c->c.getCodiceCanale().equals(codiceCanale)).findFirst().orElse(null);
        }
        return null;
    }

    private void updateProperties(ApplicationPropertiesEntity entity){
        try {
            if ( entity != null ) {
                if ( entity.getValue() == null ){
                    applicationProperties.getMuiService().deleteApplicationProperty(entity.getKey());
                } else {
                    applicationProperties.getMuiService().persistApplicationProperty(entity);
                }
                applicationProperties.update();
            }
        } catch (Exception e) {
            log.error("Error updating properties", e);
        }
    }
}

