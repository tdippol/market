package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgIdMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgCastellettoMessaggiComponentEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaleDispositivoService;
import com.axiante.mui.dbpromo.persistence.service.CfgIdMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.MeccanicaService;
import com.axiante.mui.dbpromo.persistence.service.MuiBottoneService;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgCastellettoMessaggiComponentService;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgDefaultCastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.MuiFontEntitiesService;
import com.axiante.mui.dbpromo.persistence.service.MuiFontStileService;
import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.admin.dto.CfgDescrizioneMessaggiDTO;
import com.axiante.mui.webapp.views.content.admin.dto.CfgLoghiMessaggiComponentDTO;
import com.axiante.mui.webapp.views.content.admin.dto.CfgLoghiMessaggiConfigurationDTO;
import com.axiante.tm1.mdx.objects.Query;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@MuiViewModel("configurazioneLoghiMessaggi")
@Named("configurazioneLoghiMessaggi")
@SessionScoped
public class ConfigurazioneLoghiMessaggiView extends AbstractAdminView {
    private static final long serialVersionUID = -3256424561617392612L;

    @Getter
    @Setter
    List<CanalePromozioneEntity> canali;

    @Getter
    @Setter
    List<MeccanicheEntity> meccaniche;

    CanalePromozioneEntity canaleSelected;
    MeccanicheEntity meccanicaSelected;

    @Inject
    CanalePromozioneService canaleService;

    @Inject
    MeccanicaService meccanicaService;

    @Inject
    MuiCfgDefaultCastellettoMessaggiService defaultMessaggiService;

    @Inject
    CfgCanaleDispositivoService dispositivoService;

    @Inject
    CfgIdMessaggiService idMessaggiService;

    @Inject
    MuiCfgCastellettoMessaggiComponentService componentService;

    @Inject
    MuiFontStileService fontStileService;

    @Inject
    MuiBottoneService bottoneService;

    @Inject
    MuiFontEntitiesService fontEntitiesService;

    @Getter
    List<MuiCfgDefaultCastellettoMessaggiEntity> configurazioneDiDefault;

    @Getter
    List<CfgIdMessaggiEntity> descrizioniMessaggi;

    @Getter
    List<CfgLoghiMessaggiComponentDTO> componentiAttivi;

    @Setter
    @Getter
    Integer activeTab;

    @Getter
    CfgLoghiMessaggiConfigurationDTO confBean;

    @Getter
    CfgDescrizioneMessaggiDTO descrizioneMessaggiBean = new CfgDescrizioneMessaggiDTO();

    @Setter
    @Getter
    MuiCfgDefaultCastellettoMessaggiEntity selectedCfg;

    @Getter
    List<MuiFontStileEntity> stili;

    @Getter
    List<CfgCanaleDispositivoEntity> dispositivi;

    @Getter
    String dispositivo;

    @Getter
    private List<String> availableFonts;

    @Getter
    private List<String> availableAllineamento;

    @Getter
    private List<String> availableSezione;

    @Getter
    private List<MuiFontEntities> availableFontEntities;

    @Getter
    private List<MuiBottoneEntity> availableButtons;

    @Getter
    @Setter
    private boolean btnConfirmAddConfigurationDisabled = false;

    private Long addBeforeThisId = null;

    @Override
    @PostConstruct
    public void init() {
        readCanali();
        // leggo i canali-meccanica
        if (!canali.isEmpty()) {
            canaleSelected = canali.get(0);
        } else {
            canaleSelected = null;
        }
        readMeccaniche();
        if (!meccaniche.isEmpty()) {
            meccanicaSelected = meccaniche.get(0);
        }
        readDispositivi();
        if (!dispositivi.isEmpty()) {
            dispositivo = dispositivi.get(0).getCodice();
        }

        readStili();
        readMuiBottoni();
        readFontEntities();
        resetAddConfiguration();
        updateFilters();

        prepareEnumerations();
    }

    public void seqStampaValidator(FacesContext context, UIComponent comp, Object value) {
        boolean isValid = true;
        try {
            Short seqStampa = Short.parseShort(value.toString());
            if (seqStampa <= 0 || !isSeqStampaValid(seqStampa)) {
                isValid = false;
            }
        } catch (Exception ex) {
            log.error("Error during seqStampaValidator", ex);
            isValid = false;
        }
        btnConfirmAddConfigurationDisabled = !isValid;
        if (!isValid) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seq Stampa non valida",
                    "Il valore inserito è già utilizzato");
            throw new ValidatorException(message);
        }
    }

    public void canaleSelected(long idCanale) {
        CanalePromozioneEntity canale = canaleService.findById(idCanale);
        if (canale != null) {
            canaleSelected = canale;
            readMeccaniche();
        } else {
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore durante la selezione",
                    "Il canale selezionato non risulta a database");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, error);
            canaleSelected = null;
        }
    }

    public void meccanicaSelected(long idMeccanica) {
        MeccanicheEntity meccanica = meccanicaService.findById(idMeccanica);
        if (meccanica != null) {
            meccanicaSelected = meccanica;
        } else {
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore durante la selezione",
                    "La meccanica selezionata non risulta a database");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, error);
            meccanicaSelected = null;
        }
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
        updateFilters();
    }

    private void readCanali() {
        canali = canaleService.findAll();
        final Comparator<CanalePromozioneEntity> byGruppo = Comparator.comparing(c -> c.getGruppoPromozioneEntity().getDescrizione());
        final Comparator<CanalePromozioneEntity> byCanale = Comparator.comparing(CanalePromozioneEntity::getDescrizione);
        canali.sort(byGruppo.thenComparing(byCanale));
    }

    private void readMeccaniche() {
        if (canaleSelected != null) {
            meccaniche = canaleSelected.getMuiCfgAbilitaMeccCanales().stream()
                    .map(CfgAbilitaMeccCanaleEntity::getMeccanicheEntity).collect(Collectors.toList());
            final Comparator<MeccanicheEntity> byCodiceMeccanica = Comparator.comparing(MeccanicheEntity::getCodiceMeccanica);
            meccaniche.sort(byCodiceMeccanica);
        } else {
            meccaniche = Collections.emptyList();
        }
    }

    private void readStili() {
        stili = fontStileService.findAll();
    }

    private void readMuiBottoni() {
        availableButtons = bottoneService.findAll();
    }

    private void readFontEntities() {
        availableFontEntities = fontEntitiesService.findAll();
    }

    private void readDispositivi() {
        dispositivi = dispositivoService.findAll().stream().sorted(Comparator.comparing(CfgCanaleDispositivoEntity::getDescrizione)).collect(Collectors.toList());
    }

    public Long getIdCanaleSelezionato() {
        if (canaleSelected != null) {
            return canaleSelected.getId();
        }
        return null;
    }

    public void setIdCanaleSelezionato(Long idCanaleSelezionato) {
        Optional<CanalePromozioneEntity> canal = canali.stream().filter(c -> idCanaleSelezionato.equals(c.getId())).findAny();
        if (canal.isPresent()) {
            canaleSelected = canal.get();
        } else {
            canaleSelected = null;
            final String error = String.format("Canale selezionato %d non presente", idCanaleSelezionato);
            log.error(error);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore durante la selezione", error);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        readMeccaniche();
        boolean reset = true;
        if (meccanicaSelected != null) {
            // try to retain
            if (meccaniche.stream().anyMatch(m -> m.getId().equals(meccanicaSelected.getId()))) {
                reset = false;
            }
        }
        if (reset) {
            meccanicaSelected = meccaniche.isEmpty() ? null : meccaniche.get(0);
        }
        updateFilters();
    }

    public Long getIdMeccanicaSelezionata() {
        if (meccanicaSelected != null) {
            return meccanicaSelected.getId();
        }
        return null;
    }

    public void setIdMeccanicaSelezionata(Long idMeccanicaSelezionata) {
        Optional<MeccanicheEntity> mecc = meccaniche.stream().filter(c -> idMeccanicaSelezionata.equals(c.getId())).findAny();
        if (mecc.isPresent()) {
            meccanicaSelected = mecc.get();
        } else {
            meccanicaSelected = null;
            final String error = String.format("Meccanica selezionato %d non presente", idMeccanicaSelezionata);
            log.error(error);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore durante la selezione", error);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        updateFilters();
    }

    private void updateFilters() {
        readConfigurations();
        readComponents();
        readDescrizioni();
    }

    private void readConfigurations() {
        if (canaleSelected != null && meccanicaSelected != null && dispositivo != null) {
            configurazioneDiDefault = defaultMessaggiService.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                    canaleSelected.getCodiceCanale(), meccanicaSelected.getCodiceMeccanica(), this.dispositivo).stream().sorted(Comparator.comparing(MuiCfgDefaultCastellettoMessaggiEntity::getSeqStampa)).collect(Collectors.toList());
        } else {
            configurazioneDiDefault = Collections.emptyList();
        }
    }

    private void readDescrizioni() {
        if (canaleSelected != null && meccanicaSelected != null && dispositivo != null) {
            descrizioniMessaggi = idMessaggiService.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                    canaleSelected.getCodiceCanale(), meccanicaSelected.getCodiceMeccanica(), dispositivo);
            descrizioniMessaggi.sort(Comparator.comparingInt(CfgIdMessaggiEntity::getIdMessaggio));
        } else {
            descrizioniMessaggi = Collections.emptyList();
        }
    }

    private void readComponents() {
        if (canaleSelected != null && meccanicaSelected != null && dispositivo != null) {
            componentiAttivi = new ArrayList<>();
            List<MuiCfgCastellettoMessaggiComponentEntity> components = componentService.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                    canaleSelected.getCodiceCanale(), meccanicaSelected.getCodiceMeccanica(), dispositivo);
            if (components.isEmpty()) {
                // creo tutto ex novo quindi gli elementi sono giusti
                for (MessaggiComponentsEnum component : MessaggiComponentsEnum.values()) {
                    componentService.persistWithAuditLog(createDefaultComponent(component), getCurrentUser().getName());
                    componentiAttivi.add(CfgLoghiMessaggiComponentDTO.getDefaultComponent(component));
                }
            } else {
                // ho la configurazione: potrei aver inserito o tolto componenti
                componentiAttivi = components.stream().map(c ->
                                new CfgLoghiMessaggiComponentDTO(c.getComponent(), c.getEnabled(), c.getUnicaInRiga(), c.getTesto()))
                        .collect(Collectors.toList());
                verifyNewComponents(componentiAttivi);
            }
        } else {
            componentiAttivi = Collections.emptyList();
        }
        Collections.sort(componentiAttivi, Comparator.comparing(CfgLoghiMessaggiComponentDTO::getName));
    }

    private void verifyNewComponents(final List<CfgLoghiMessaggiComponentDTO> list) {
        // check if something needs adding:
        HashSet<MessaggiComponentsEnum> check = new HashSet<>(Arrays.asList(MessaggiComponentsEnum.values()));
        list.stream().map(CfgLoghiMessaggiComponentDTO::getName).forEach(check::remove);
        if (!check.isEmpty()) {
            log.warn("The following elements are not configured and will be added {}", new ArrayList<>(check));
            check.forEach(c -> {
                componentService.persistWithAuditLog(createDefaultComponent(c), getCurrentUser().getName());
                list.add(CfgLoghiMessaggiComponentDTO.getDefaultComponent(c));
            });
        }
        // check if something needs removing;
        check = list.stream().map(CfgLoghiMessaggiComponentDTO::getName).collect(Collectors.toCollection(HashSet::new));
        Arrays.stream(MessaggiComponentsEnum.values()).forEach(check::remove);
        if (!check.isEmpty()) {
            log.warn("The following elements have been removed from configurable elements and will be removed from database {}",
                    new ArrayList<>(check));
            boolean removeErrors = check.stream().map(c -> componentService.removeByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(canaleSelected.getCodiceCanale(), meccanicaSelected.getCodiceMeccanica(), dispositivo, c)).anyMatch(b -> !b);
            if (removeErrors) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore durante l'allineamento",
                        "Almeno un elemento da rimuovere dalla configurazione corrente presenta problemi. Contattare l'assistenza");
                FacesContext.getCurrentInstance().addMessage("null", message);
            }
        }
    }

    private MuiCfgCastellettoMessaggiComponentEntity createDefaultComponent(MessaggiComponentsEnum component) {
        MuiCfgCastellettoMessaggiComponentEntity e = new MuiCfgCastellettoMessaggiComponentEntity();
        e.setCodiceCanale(canaleSelected.getCodiceCanale());
        e.setCodiceMeccanica(meccanicaSelected.getCodiceMeccanica());
        e.setCodiceDispositivo(dispositivo);
        e.setUnicaInRiga(false);
        e.setComponent(component);
        return e;
    }

    /**
     * Aggiorna il valore a database della riga
     *
     * @param riga
     */
    public void updateStatus(CfgLoghiMessaggiComponentDTO riga) {
        MuiCfgCastellettoMessaggiComponentEntity entity = findMessaggioComponent(riga);
        entity.setEnabled(!entity.getEnabled());
        componentService.persistWithAuditLog(entity, getCurrentUser().getName());
    }

    public void updateUnica(CfgLoghiMessaggiComponentDTO riga) {
        MuiCfgCastellettoMessaggiComponentEntity entity = findMessaggioComponent(riga);
        entity.setUnicaInRiga(!entity.getUnicaInRiga());
        componentService.persistWithAuditLog(entity, getCurrentUser().getName());
    }

    public void updateTesto(ValueChangeEvent event) {
        log.debug("testo cambiato");
        Object o = ((UIInput) event.getSource()).getAttributes().get("elemento");
        CfgLoghiMessaggiComponentDTO riga = (CfgLoghiMessaggiComponentDTO) o;
        MuiCfgCastellettoMessaggiComponentEntity entity = findMessaggioComponent(riga);
        entity.setTesto(event.getNewValue().toString());
        componentService.persistWithAuditLog(entity, getCurrentUser().getName());
    }

    public void updateCfg(MuiCfgCastellettoMessaggiComponentEntity riga) {
        defaultMessaggiService.persistWithAuditLog(riga, getCurrentUser().getName());
    }

    public void addConfAction() {
        resetAddConfiguration();
        Short max = defaultMessaggiService.findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                canaleSelected.getCodiceCanale(), meccanicaSelected.getCodiceMeccanica(), dispositivo);
        Short seqStampa = (short) (max + 1);
        confBean.setSeqStampa(seqStampa);
        selectedCfg = null;
    }

    public void addConfActionBefore(MuiCfgDefaultCastellettoMessaggiEntity conf) {
        resetAddConfiguration();
        Short seqStampa = conf.getSeqStampa();
        confBean.setSeqStampa(seqStampa);
        addBeforeThisId = conf.getId();
        selectedCfg = null;
    }

    public void addDescrizioneAction() {
        descrizioneMessaggiBean.clear();
    }

    public void editConfAction(MuiCfgDefaultCastellettoMessaggiEntity conf) {
        if (conf != null) {
            try {
                selectedCfg = conf;
                confBean = new CfgLoghiMessaggiConfigurationDTO(selectedCfg);
            } catch (Exception e) {
                log.error("error while setting the backing bean", e);
            }
        }
    }

    public void editDescriptionAction(CfgIdMessaggiEntity desc) {
        if (desc != null) {
            try {
                descrizioneMessaggiBean.clear();
                descrizioneMessaggiBean.setDescrizione(desc.getDescrizione());
                descrizioneMessaggiBean.setIdMessaggio(desc.getIdMessaggio());
                descrizioneMessaggiBean.setEdit(true);
            } catch (Exception e) {
                log.error("error while setting the backing bean", e);
            }
        }
    }

    public void resetAddConfiguration() {
        confBean = new CfgLoghiMessaggiConfigurationDTO();
        btnConfirmAddConfigurationDisabled = false;
        addBeforeThisId = null;
    }

    public void confirmAddDescription() {
        FacesMessage message = null;
        if (descrizioneMessaggiBean.validate()) {
            CfgIdMessaggiEntity entity = descrizioniMessaggi.stream().filter(d -> d.getIdMessaggio().equals(descrizioneMessaggiBean.getIdMessaggio())).findAny().orElse(null);
            if (entity == null) {
                entity = new CfgIdMessaggiEntity();
                entity.setCodiceCanale(canaleSelected.getCodiceCanale());
                entity.setCodiceMeccanica(meccanicaSelected.getCodiceMeccanica());
                entity.setCodiceDispositivo(dispositivo);
                entity.setIdMessaggio(descrizioneMessaggiBean.getIdMessaggio());
            } else {
                descrizioniMessaggi.remove(entity);
            }
            entity.setDescrizione(descrizioneMessaggiBean.getDescrizione());
            entity = idMessaggiService.persist(entity);
            descrizioniMessaggi.add(entity);
            descrizioniMessaggi.sort(Comparator.comparingInt(CfgIdMessaggiEntity::getIdMessaggio));
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Configurazione Messaggi", "Configurazione modificata con successo");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Configurazione Messaggi", "Errore durante il salvataggio della configurazione");
        }
        addMessage(null, message);
    }

    public void confirmAddConfiguration() {
        FacesMessage message = null;
        try {
            if (selectedCfg != null) {
                selectedCfg = confBean.update(selectedCfg);
                defaultMessaggiService.persistWithAuditLog(selectedCfg, getCurrentUser().getName());
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Configurazione Messaggi", "Configurazione modificata con successo");
            } else {
                confBean.setCodiceCanale(canaleSelected.getCodiceCanale());
                confBean.setCodiceMeccanica(meccanicaSelected.getCodiceMeccanica());
                confBean.setCodiceDispositivo(dispositivo);
                MuiCfgDefaultCastellettoMessaggiEntity entity = confBean.update(new MuiCfgDefaultCastellettoMessaggiEntity());
                if (addBeforeThisId == null) {
                    defaultMessaggiService.persistWithAuditLog(entity, getCurrentUser().getName());
                } else {
                    defaultMessaggiService.addMessageAbove(entity, addBeforeThisId, getCurrentUser().getName());
                }
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Configurazione Messaggi", "Configurazione aggiunta con successo");
            }
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Configurazione Messaggi", "Errore durante il salvataggio della configurazione");
            log.error("Error saving configuration", e);
        } finally {
            readConfigurations();
            selectedCfg = null;
            addMessage(null, message);
        }
    }

    public void deleteConfAction(MuiCfgDefaultCastellettoMessaggiEntity conf) {
        if (conf != null) {
            defaultMessaggiService.remove(conf);
            readConfigurations();
            selectedCfg = null;
        }
    }

    public void deleteDescriptionAction(CfgIdMessaggiEntity descrizione) {
        if (descrizioniMessaggi != null) {
            idMessaggiService.remove(descrizione);
            readDescrizioni();
        }
    }

    private void prepareEnumerations() {
        availableFonts = Arrays.stream(MessaggiFontEnum.values()).map(MessaggiFontEnum::getValue).collect(Collectors.toList());
        availableAllineamento = Arrays.stream(MessaggiAllineamentoEnum.values()).map(MessaggiAllineamentoEnum::getValue).collect(Collectors.toList());
        availableSezione = Arrays.stream(MessaggiSezioneEnum.values()).map(MessaggiSezioneEnum::name).collect(Collectors.toList());
    }

    private boolean isSeqStampaValid(Short seqStampa) {
        Set<Short> usedValues = configurazioneDiDefault.stream().map(MuiCfgDefaultCastellettoMessaggiEntity::getSeqStampa)
                .filter(Objects::nonNull).collect(Collectors.toSet());
        if (selectedCfg != null && selectedCfg.getSeqStampa() != null) {
            usedValues.remove(selectedCfg.getSeqStampa());
        }
        return !usedValues.contains(seqStampa);
    }

    public void setFontStile(String stile) {
        if (stile == null) {
            confBean.setFontStile(null);
        } else {
            confBean.setFontStile(stili.stream().filter(s -> s.getId().equals(stile)).findAny().orElse(null));
        }
    }

    public String getFontStile() {
        if (confBean != null && confBean.getFontStile() != null) return confBean.getFontStile().getId();
        return null;
    }

    public void setBottone(Long bottone) {
        if (bottone == null) {
            confBean.setBottone(null);
        } else {
            confBean.setBottone(availableButtons.stream()
                    .filter(s -> bottone.equals(s.getId()))
                    .findAny().orElse(null));
        }
    }

    public Long getBottone() {
        if (confBean != null && confBean.getBottone() != null) {
            return confBean.getBottone().getId();
        }
        return null;
    }

    public void setFontEntity(String entity) {
        if (entity == null) {
            confBean.setFontEntity(null);
        } else {
            confBean.setFontEntity(availableFontEntities.stream().filter(s -> s.getId().equals(entity)).findAny().orElse(null));
        }
    }

    public String getFontEntity() {
        if (confBean != null && confBean.getFontEntity() != null) {
            return confBean.getFontEntity().getId();
        }
        return null;
    }

    protected MuiCfgCastellettoMessaggiComponentEntity findMessaggioComponent(@NonNull CfgLoghiMessaggiComponentDTO riga) {
        return componentService.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(canaleSelected.getCodiceCanale(), meccanicaSelected.getCodiceMeccanica(), dispositivo, riga.getName());

    }

    // no-op
    @Override
    public void updateView() {
    }

    // no-op
    @Override
    public void updateView(String grid) {
    }

    @Override
    public Query prepareFilteredQuery(String grid) {
        return null;
    }

    @Override
    protected ApplicationFilterCatalogProducer getCatalogProducer() {
        return null;
    }

    @Override
    protected CatalogReducer getCatalogReducer() {
        return null;
    }
}
