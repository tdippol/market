package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.common.utility.StringUtils;
import com.axiante.mui.common.utility.ftp.FtpUploader;
import com.axiante.mui.common.utility.ftp.FtpUploaderException;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgCastellettoMessaggiComponentEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgCastellettoMessaggiComponentService;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgDefaultCastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.MuiFontEntitiesService;
import com.axiante.mui.webapp.listener.MessaggiUpdatedListener;
import com.axiante.mui.webapp.utils.FileUploadUtils;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.views.content.dbpromo.data.logoMessaggi.FormValueChangeListener;
import com.axiante.mui.webapp.views.content.dbpromo.data.logoMessaggi.LogoUploadBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.logoMessaggi.MessaggioFormBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.logoMessaggi.VisibilityStateBean;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity.CODICE_F;
import static com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity.TIPO_1;

@Slf4j
public class MessaggiBean implements Serializable, FacesContextAware, FormValueChangeListener {
    @Getter
    private final VisibilityStateBean visibilityState = new VisibilityStateBean();

    @Getter
    private final MessaggioFormBean formBean = new MessaggioFormBean();

    @Getter
    private final LogoUploadBean logoUploadBean = new LogoUploadBean();

    private static final long serialVersionUID = -1461584330005789814L;
    private final CastellettoMessaggiService messaggiService;
    private final ApplicationProperties properties;
    private final MuiCfgCastellettoMessaggiComponentService defaultService;
    private MuiCfgDefaultCastellettoMessaggiService cfgMessaggiService;
    private final CfgCanaleDispositivoEntity dispositivo;
    private final boolean editable;
    private final MuiFontEntitiesService fontEntitiesService;
    private static final String LOGO_FILENAME_PREFIX = "{{imgPath}}/";
    private static final String ASCII128_REGEX = "\\A\\p{ASCII}*\\z";
    private static final Integer LOGO_MAX_SIZE_LIMIT = 65536;
    private final StringUtils stringUtils = new StringUtils();

    @Getter
    @Setter
    private List<MessaggiAllineamentoEnum> allineamenti;

    @Getter
    @Setter
    private List<MessaggiFontEnum> fonts;

    @Setter
    @Getter
    private List<MuiFontEntities> fontEntities;
    private PromozionePianificazioneEntity pianificazione;

    @Setter
    private String codicePromo;

    @Setter
    private CastellettoMessaggiEntity messaggioSelected;

    @Getter
    private Long idMessageToBeDeleted;

    @Getter
    private boolean resetDisabled = true;

    @Getter
    private boolean pendingChanges = false;

    private static final String ERRORE = "Errore";

    Set<MessaggiUpdatedListener> listeners = new HashSet<>();
    @Getter(AccessLevel.PROTECTED)
    protected Map<MessaggiComponentsEnum, MuiCfgCastellettoMessaggiComponentEntity> componentMap =
            new HashMap<>();

    public MessaggiBean(PromozionePianificazioneEntity pianificazione, boolean editable, CfgCanaleDispositivoEntity dispositivo,
                        CastellettoMessaggiService messaggiService, MuiCfgDefaultCastellettoMessaggiService cfgMessaggiService,
                        ApplicationProperties properties, MuiCfgCastellettoMessaggiComponentService defaultService,
                        MuiFontEntitiesService fontEntitiesService) {
        this.dispositivo = dispositivo;
        this.messaggiService = messaggiService;
        this.properties = properties;
        this.defaultService = defaultService;
        this.cfgMessaggiService = cfgMessaggiService;
        this.pianificazione = pianificazione;
        this.editable = editable;
        this.fontEntitiesService = fontEntitiesService;
        init();
    }

    public void init() {
        loadFonts();
        loadAlignments();
        loadFonEntities();
        preloadDefaults();
        determineVisibleComponents();
        determineResetEnabled();
        readComponentConfigurations();
        getFormBean().addFormValueChangeListener(this);
    }

    public void onFieldChange() {
        pendingChanges = messaggioSelected == null || getFormBean().messaggioSelectedChanged(messaggioSelected);
    }

    private void loadFonEntities() {
        fontEntities = fontEntitiesService.findAll();
    }

    public void clearForm() {
        preloadDefaults();
        if (messaggioSelected != null) {
            loadSelectedMessage(messaggioSelected.getId());
        }
    }

    public void updateMessaggio() {
        if (pianificazione == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRORE, "Pianificazione non valorizzata"));
            return;
        }
        if (getFormBean().getIdMessaggio() == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRORE, "Il campo id messaggio e' obbligatorio"));
            return;
        }
        // MG#5407: validazione campo su NUMERO_CARATTERI e LIMITA_CARATTERI impostati a livello di
        // dispositivo
        if (getFormBean().getTesto() != null && !getFormBean().getTesto().isEmpty()) {
            String errors = validateTestoMessaggio(getFormBean().getTesto());
            if (errors != null && !errors.isEmpty()) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRORE, errors));
                return;
            }
        }
        if (getFormBean().getSeqStampa() == null) {
            // crea la sequenza di stampa
            getFormBean().setSeqStampa(calcolaSeqStampa());
        }
        try {
            if (messaggioSelected == null) {
                messaggioSelected = new CastellettoMessaggiEntity();
                messaggioSelected.setPianificazione(pianificazione);
                messaggioSelected.setSezione(MessaggiSezioneEnum.MESSAGGI);
            }
            messaggioSelected.setSeqStampa(getFormBean().getSeqStampa());
            messaggioSelected.setTaglioCarta(getFormBean().getTaglioCarta());
            messaggioSelected.setBarcode(getFormBean().getBarcode());
            messaggioSelected.setBold(getFormBean().getBold());
            messaggioSelected.setFont(getFormBean().getFont());
            messaggioSelected.setAllineamento(getFormBean().getAllineamento());
            messaggioSelected.setTesto(getFormBean().getTesto());
            if (getFormBean().getLogoFilename() != null) {
                String filenamePrefix = CODICE_F.equalsIgnoreCase(dispositivo.getCodice())
                        ? "" : LOGO_FILENAME_PREFIX;
                String filename = getFormBean().getLogoFilename().replace(LOGO_FILENAME_PREFIX, "");
                filename = FtpUploader.sanitizeFileName(filename);
                messaggioSelected.setLogo(String.format("%s%s", filenamePrefix, filename));
                if (getLogoUploadBean().getTempLogoFile() != null) {
                    // Salvo file nella share condivisa
                    landFile(getLogoUploadBean().getTempLogoFile(), filename);
                }
            } else {
                messaggioSelected.setLogo(null);
            }
            // nuovi campi
            messaggioSelected.setFontStile(getFormBean().getFontStile());
            messaggioSelected.setBottone(getFormBean().getBottone());
            messaggioSelected.setCodice(getFormBean().getCodice());
            messaggioSelected.setRegolamento(getFormBean().getRegolamento());
            messaggioSelected.setBarra(getFormBean().getBarra());
            messaggioSelected.setIdMessaggio(getFormBean().getIdMessaggio());
            messaggioSelected.setCodiceCanaleDispositivo(dispositivo.getCodice());
            if (messaggioSelected.getSezione() == null) {
                messaggioSelected.setSezione(MessaggiSezioneEnum.MESSAGGI);
            }
            messaggioSelected.setVariabile(getFormBean().getVariabile());
            messaggioSelected.setFontEntity(getFormBean().getFontEntity());
            // Persisto messaggio
            messaggiService.persistWithAuditLog(messaggioSelected, getCurrentUser().getName());
            // Pulisco form caricando i default
            preloadDefaults();
            // Pulisco messaggio selected
            messaggioSelected = null;
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Messaggio salvato", null));
        } catch (Exception ex) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRORE,
                    "Errore salvataggio Messaggio; contattare l'assistenza"));
            log.warn(String.format("Error saving CastellettoMessaggi for Pianificazione with id: %d",
                    pianificazione.getId()), ex);
        } finally {
            listeners.forEach(MessaggiUpdatedListener::onMessaggiUpdated);
        }
    }

    protected Integer calcolaSeqStampa() {
        Integer result = null;
        List<CastellettoMessaggiEntity> list =
                messaggiService.findMessaggiByIdPianificazioneAndCodiceDispositivo(
                        pianificazione.getId(), dispositivo.getCodice());

        if (list == null || list.isEmpty()) {
            result = 1;
        } else {
            list.sort((o1, o2) -> o1.getSeqStampa().compareTo(o2.getSeqStampa()));
            result = list.get(list.size() - 1).getSeqStampa() + 1;
        }
        return result;
    }

    protected String validateTestoMessaggio(String testo) {
        List<String> errors = new ArrayList<>();
        try {
            if (dispositivo.getNumeroCaratteri() != null
                    && testo.length() > dispositivo.getNumeroCaratteri()) {
                errors.add(
                        String.format(
                                "Superata la dimensione massima del testo impostata a %d caratteri",
                                dispositivo.getNumeroCaratteri()));
            }
            if (dispositivo.getLimitaCaratteri() != null) {
                String validatedLimitaCaratteri =
                        validateLimitaCaratteri(testo, dispositivo.getLimitaCaratteri());
                if (validatedLimitaCaratteri != null) {
                    errors.add(validatedLimitaCaratteri);
                }
            }
        } catch (Exception ex) {
            final String msg =
                    String.format(
                            "Errore durante la validazione del testo %s per il dispositivo %s",
                            testo, dispositivo.getDescrizione());
            log.error(msg);
            errors.add(msg);
        }
        return errors.isEmpty() ? null : String.join(", ", errors);
    }

    // MG#5420 - al momento gestita solo casistica ASCII-128
    private String validateLimitaCaratteri(String testo, String limitaCaratteri) {
        if ("ASCII-128".equalsIgnoreCase(limitaCaratteri) && !testo.matches(ASCII128_REGEX)) {
            return "Il campo testo contiene caratteri non consentiti; inserire solo caratteri ASCII";
        }
        if (!"ASCII-128".equalsIgnoreCase(limitaCaratteri)) {
            log.warn(String.format("Parametro limitaCaratteri '%s' non valido", limitaCaratteri));
        }
        return null;
    }

    public void handleLogoUpload(final FileUploadEvent event) {
        String oldLogoFilename = getFormBean().getLogoFilename();
        if (getFormBean().getIdMessaggio() == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attenzione",
                    "Id Messaggio necessario per l'upload del logo"));
            return;
        }
        if (getFormBean().getSeqStampa() == null) {
            getFormBean().setSeqStampa(calcolaSeqStampa());
        }
        try {
            UploadedFile file = event.getFile();
            final String fileName = file.getFileName();
            if (dispositivo.getFormatter() == null || TIPO_1.equalsIgnoreCase(dispositivo.getFormatter())) {
                if (!isPngOrJpeg(fileName) && !isBmp(fileName)) {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                            "Formato file non valido; sono consentiti solo file BMP, JPG, JPEG, PNG"));
                    return;
                }
            } else {
                if (!isBmp(fileName)) {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                            "Formato file non valido; sono consentiti solo file BMP"));
                    return;
                }
                if (file.getSize() > LOGO_MAX_SIZE_LIMIT) {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                            String.format("Dimensione file non valida; dimensione massima consentita %d KB",
                                    (LOGO_MAX_SIZE_LIMIT / 1024))));
                    return;
                }
            }
            getLogoUploadBean().setUploadedLogo(file);
            getFormBean()
                    .setLogoFilename(
                            FileUploadUtils.createFileName(
                                    getLogoUploadBean().getUploadedLogo().getFileName(),
                                    codicePromo,
                                    getFormBean().getIdMessaggio(),
                                    getFormBean().getSeqStampa(),
                                    dispositivo));
            if (logoUsedInPromo(getFormBean().getLogoFilename())) {
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_WARN,
                                "Attenzione",
                                String.format(
                                        "Un logo con nome %s è già presente nelle promozione '%s'; rinominare il file",
                                        getFormBean().getLogoFilename(), codicePromo)));
                if (getLogoUploadBean().getTempLogoFile() != null) {
                    if (!getLogoUploadBean().getTempLogoFile().delete()) {
                        log.warn(
                                "Unable to delete temporary logo file: {}",
                                getLogoUploadBean().getTempLogoFile().getAbsolutePath());
                    }
                    getLogoUploadBean().setTempLogoFile(null);
                }
                getLogoUploadBean().setUploadedLogo(null);
                getFormBean().setLogoFilename(null);
            } else {
                getLogoUploadBean()
                        .setTempLogoFile(File.createTempFile("temp_" + System.currentTimeMillis(), ".tmp"));
                IOUtils.write(
                        getLogoUploadBean().getUploadedLogo().getContents(),
                        Files.newOutputStream(getLogoUploadBean().getTempLogoFile().toPath()));
            }
        } catch (Exception ex) {
            log.error("Error uploading logo", ex);
            if (getLogoUploadBean().getTempLogoFile() != null) {
                if (!getLogoUploadBean().getTempLogoFile().delete()) {
                    log.warn(
                            "Unable to delete temporary logo file: {}",
                            getLogoUploadBean().getTempLogoFile().getAbsolutePath());
                }
                getLogoUploadBean().setTempLogoFile(null);
                getLogoUploadBean().setUploadedLogo(null);
                getFormBean().setLogoFilename(null);
            }
        } finally {
            if (getLogoUploadBean().getUploadedLogo() != null) {
                getFormBean().setBarcode(Boolean.FALSE);
                getFormBean().setTaglioCarta(Boolean.FALSE);
            }
        }
        if (!Objects.equals(oldLogoFilename, getFormBean().getLogoFilename())) {
            pendingChanges = true;
        }
        // Notifica il cambiamento del logo
        onFormValueChange(MessaggiComponentsEnum.LOGO, oldLogoFilename, getFormBean().getLogoFilename());
    }

    private boolean logoUsedInPromo(String logoFilename) {
        return messaggiService.isLogoUsedInPromo(
                logoFilename, pianificazione.getPromozioneTestataEntity().getId());
    }

    public void selezionaMessaggio() {
        if (pendingChanges) {
            handlePendingChanges();
            return;
        }
        try {
            final String messaggioIdParam = getRequestParameterMap().get("messaggioSelected");
            final Long idMessaggio = JsonUtils.getMapper().readTree(messaggioIdParam).asLong();
            loadSelectedMessage(idMessaggio);
        } catch (Exception ex) {
            log.error("Error reading selected row", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRORE,
                    "Errore selezione messaggio; contattare l'assistenza"));
        }
    }

    private void handlePendingChanges() {
        executeScript("clearSelection(); PF('dlgPendingChanges').show();");
    }

    public void prepareDeleteMessageDialog() {
        try {
            final String messaggioIdParam = getRequestParameterMap().get("id");
            if (messaggioIdParam != null) {
                idMessageToBeDeleted = Long.parseLong(messaggioIdParam);
                executeScript("PF('deleteMessaggeConfirmDialog').show();");
            } else {
                log.error("Unable to setting idMessageToBeDeleted from request parameter");
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                ERRORE,
                                "Errore eliminazione messaggio; contattare l'assistenza"));
            }
        } catch (Exception ex) {
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            ERRORE,
                            "Errore eliminazione messaggio; contattare l'assistenza"));
        }
    }

    public void addMessageAbove() {
        try {
            final String messaggioIdParam = getRequestParameterMap().get("id");
            if (messaggioIdParam != null) {
                Long idMessaggio = Long.parseLong(messaggioIdParam);
                messaggiService.addMessageAbove(idMessaggio, getCurrentUser().getName());
            } else {
                log.error("Unable to set message id from request parameter");
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRORE,
                        "Errore inserimento messaggio; contattare l'assistenza"));
            }
        } catch (Exception ex) {
            log.error("Error inserting row above selected row", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRORE,
                    "Errore inserimento messaggio; contattare l'assistenza"));
        }
    }

    public void resetMessage() {
        idMessageToBeDeleted = null;
        preloadDefaults();
    }

    private void loadSelectedMessage(Long idMessaggio) {
        messaggioSelected = messaggiService.findById(idMessaggio);
        if (messaggioSelected == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRORE,
                    "Errore selezione messaggio; contattare l'assistenza"));
            return;
        }
        getFormBean().loadSelectedMessage(messaggioSelected);
        getLogoUploadBean().messaggioChanged();
        boolean logoDisabled = false;
        if (getFormBean().getTaglioCarta() || getFormBean().getBarcode()) {
            logoDisabled = true;
        } else {
            logoDisabled = getFormBean().getLogoFilename() != null;
        }
        getVisibilityState().setLogoDisabled(logoDisabled);
        getVisibilityState().setTaglioCartaDisabled(false);
    }

    private void loadFonts() {
        fonts = Arrays.asList(MessaggiFontEnum.values());
    }

    private void loadAlignments() {
        allineamenti = Arrays.asList(MessaggiAllineamentoEnum.values());
    }

    private void preloadDefaults() {
        getLogoUploadBean().preloadDefaults();
        getFormBean().preloadDefaults(dispositivo);
        getVisibilityState().preloadDefaults();
        pendingChanges = false;
    }

    private void landFile(File tempFile, String filename) throws IOException {
        if (dispositivo == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRORE,
                    "Nessun dispositivo selezionato"));
            return;
        }
        if (CODICE_F.equalsIgnoreCase(dispositivo.getCodice())) {
            String dataPath = properties.getProperty(ApplicationProperties.MESSAGGI_LOGO_DATA_PATH,
                    ApplicationProperties.MESSAGGI_LOGO_DATA_PATH_DEFAULT);
            if (dispositivo.getDirectory() != null && !dispositivo.getDirectory().isEmpty()) {
                dataPath += File.separator + dispositivo.getDirectory();
                dataPath = dataPath.replaceAll(File.separator + File.separator, File.separator);
            }
            final File file = new File(String.format("%s%s%s", dataPath, File.separator, filename));
            FileUtils.copyFile(tempFile, file);
        } else if (dispositivo.getAbilitaFtp()) {
            if (isValidFtpConfiguration()) {
                FtpUploader.FtpConfig config = FtpUploader.FtpConfig.builder().server(dispositivo.getFtpServer())
                        .port(Integer.parseInt(dispositivo.getFtpPort()))
                        .username(dispositivo.getFtpUser()) // Replace with your username
                        .password(dispositivo.getFtpPassword()) // Replace with your password
                        .remotePath(dispositivo.getFtpPath()) // Directory to upload to
                        .timeout(30000) // 30 seconds timeout
                        .build();
                try {
                    FtpUploader.uploadFile(config, filename, tempFile);
                } catch (FtpUploaderException e) {
                    log.error("FTP upload failed", e);
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRORE, e.getItalianMessage()));
                }
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRORE,
                        "Dispositivo abilitato al caricamento FTP, ma configurazione FTP non valida"));
            }
        }
        // duplica su directory locale
        duplicaSuDirectoryLocale(tempFile, filename);
    }

    private File moveFile(String dataPath, File tempFile, String filename) throws IOException {
        if (dispositivo != null
                && dispositivo.getDirectory() != null
                && !dispositivo.getDirectory().isEmpty()) {
            dataPath += File.separator + dispositivo.getDirectory();
            dataPath = dataPath.replaceAll(File.separator + File.separator, File.separator);
        }
        final File file = new File(String.format("%s%s%s", dataPath, File.separator, filename));
        FileUtils.copyFile(tempFile, file);
        return file;
    }

    private void duplicaSuDirectoryLocale(File file, String filename) {
        String localImgPath =
                properties.getProperty(
                        ApplicationProperties.MESSAGGI_LOCAL_IMG_PATH);
        if ( localImgPath == null || localImgPath.isEmpty() ) {
            log.error("Local image path for messaggi is not configured, skipping local duplication");
        } else {
            try {
                moveFile(localImgPath, file, filename);
            } catch (Exception e) {
                log.error("Error duplicating logo file to local image path", e);
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_WARN, ERRORE, "Errore duplicazione locale del logo; contattare l'assistenza");
                addMessage(null, message);
            }
        }
    }
    private boolean isValidFtpConfiguration() {
        return !StringUtils.isEmpty(dispositivo.getFtpServer())
                && !StringUtils.isEmpty(dispositivo.getFtpUser())
                && !StringUtils.isEmpty(dispositivo.getFtpPassword())
                && !StringUtils.isEmpty(dispositivo.getFtpPath())
                && !StringUtils.isEmpty(dispositivo.getFtpPort())
                && stringUtils.isInteger(dispositivo.getFtpPort());
    }

    private void determineVisibleComponents() {
        // data la pianificazione e il device
        // 1. recupera la lista dei componenti visibili dei componenti
        // 2. aggiorna la lista delle visibilita
        if (pianificazione != null) {
            defaultService
                    .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                            pianificazione
                                    .getPromozioneTestataEntity()
                                    .getCanalePromozioneEntity()
                                    .getCodiceCanale(),
                            pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                            dispositivo.getCodice())
                    .stream()
                    .filter(MuiCfgCastellettoMessaggiComponentEntity::getEnabled)
                    .map(MuiCfgCastellettoMessaggiComponentEntity::getComponent)
                    .forEach(getVisibilityState()::enableVisible);
        } else {
            log.error("no object of type pianificazione set for this bean");
        }
    }

    private void readComponentConfigurations() {
        if (pianificazione != null && dispositivo != null) {
            componentMap =
                    defaultService
                            .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                                    pianificazione
                                            .getPromozioneTestataEntity()
                                            .getCanalePromozioneEntity()
                                            .getCodiceCanale(),
                                    pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                                    dispositivo.getCodice())
                            .stream()
                            .collect(
                                    Collectors.toMap(
                                            MuiCfgCastellettoMessaggiComponentEntity::getComponent,
                                            e -> e,
                                            (e1, e2) -> e1, // handle duplicate keys if any
                                            HashMap::new));
        } else {
            log.error("Unable to read component configurations; pianificazione or dispositivo is null");
        }
    }

    public void addMessaggiUpdateListener(@NonNull MessaggiUpdatedListener listener) {
        listeners.add(listener);
    }

    public void resetDefaultMessaggi() {
        if (pianificazione != null && dispositivo != null) {
            messaggiService.resetMessaggi(pianificazione, dispositivo, getCurrentUser().getName());
            messaggioSelected = null;
            preloadDefaults();
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reset completato",
                    String.format("Messaggi resettati con successo per dispositivo %s", dispositivo.getDescrizione())));
        } else {
            log.error("Unable to reset default messaggi; pianificazione or dispositivo is null");
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRORE,
                    "Errore reset messaggi; contattare l'assistenza"));
        }
    }

    public String getNomeDispositivo() {
        if (dispositivo != null) {
            return dispositivo.getDescrizione();
        }
        return null;
    }

    protected void determineResetEnabled() {
        resetDisabled =
                cfgMessaggiService.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        pianificazione
                                .getPromozioneTestataEntity()
                                .getCanalePromozioneEntity()
                                .getCodiceCanale(),
                        pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                        dispositivo.getCodice())
                        <= 0;
    }

    public void onFormValueChange(MessaggiComponentsEnum component, Object oldValue, Object newValue) {
        if (getComponentMap().get(component) == null || !getComponentMap().get(component).getEnabled()) {
            log.warn("Component {} is not enabled or not configured", component);
            return;
        }
        boolean valueChanged = isValueChanged(oldValue, newValue);
        if (!valueChanged) {
            // se il valore non e' cambiato, non fare nulla
            return;
        }
        if (getComponentMap().get(component).getUnicaInRiga()) {
            // se il valore e' vuoto (e quindi e' diverso da prima perche' sono qui)
            // o e' falso, abilita tutti i componenti
            // altrimenti, disabilita gli altri componenti
            if (newValue == null || (newValue instanceof Boolean && !(Boolean) newValue)) {
                for (MessaggiComponentsEnum comp : MessaggiComponentsEnum.values()) {
                    if (getComponentMap().get(comp) != null) {
                        getVisibilityState().enableComponent(comp);
                    }
                }
                // resetto il testo
                getFormBean().setTesto(null);
            } else {
                getFormBean().reloadDefaultsBut(dispositivo, component);
                for (MessaggiComponentsEnum comp : MessaggiComponentsEnum.values()) {
                    if (comp != component && getComponentMap().get(comp) != null) {
                        getVisibilityState().disableComponent(comp);
                    }
                }
                // adesso rimetti a posto id_messaggio e seqStampa
                getVisibilityState().setIdMessaggioDisabled(false);
                getVisibilityState()
                        .setSeqStampaDisabled(
                                messaggioSelected == null || messaggioSelected.getIdMessaggio() == null);
                if (getComponentMap().get(component).getTesto() == null) {
                    log.warn("Component {} has no testo defined, setting default", component);
                    getFormBean().setTesto(component.getValue());
                } else {
                    getFormBean().setTesto(getComponentMap().get(component).getTesto());
                }
                getLogoUploadBean().removeLogo();
            }
        }
        // se non e' unica in riga siamo a posto cosi'
    }

    private boolean isValueChanged(Object oldValue, Object newValue) {
        boolean valueChanged = false;
    /*
    se newValue e' boolean allora controlla che newValue sia diverso da oldValue
    altrimenti se newValue e' stringa controlla che non sia vuota
    se newValue non e' vuoto allora controlla che non sia una sottostringa di oldValue
     */
        if (newValue instanceof Boolean) {
            valueChanged = oldValue == null || !oldValue.equals(newValue);
        } else {
            String sNewValue = (String) newValue;
            String sOldValue = (String) oldValue;
            if (newValue == null || sNewValue.isEmpty()) {
                valueChanged = sOldValue != null && !sOldValue.isEmpty();
            } else {
                valueChanged = sOldValue == null || !sOldValue.contains(sNewValue);
            }
        }
        return valueChanged;
    }

    public void removeLogo() {
        getLogoUploadBean().removeLogo();
        getFormBean().setLogoFilename(null);
        getVisibilityState().setLogoDisabled(false);
        pendingChanges = true;
    }

    private boolean isBmp(String filename) {
        return filename.toLowerCase().endsWith(".bmp");
    }

    private boolean isPngOrJpeg(String filename) {
        return filename.toLowerCase().endsWith(".png")
                || filename.toLowerCase().endsWith(".jpg")
                || filename.toLowerCase().endsWith(".jpeg");
    }

    public void fetchHtml() {
        String dataPath = properties.getProperty(ApplicationProperties.MESSAGGI_LOCAL_IMG_PATH,
                ApplicationProperties.DEFAULT_MESSAGGI_LOCAL_IMG_PATH);
        String html = messaggiService.getHtmlFromDb(pianificazione.getId(), dispositivo.getCodice(), dataPath);
        log.info("Retrieved HTML message: {}", html);
        html = embedImagesInHtml(html);
        log.info("HTML with embedded images: {}", html);
        getAjax().addCallbackParam("html", html);
    }

    private String embedImagesInHtml(String html) {
        // Regex spiegata:
        // (<img\\s+[^>]*?src\\s*=\\s*) -> Gruppo 1: Cattura l'apertura del tag fino a 'src='
        // ([\"'])                      -> Gruppo 2: Cattura l'apice di apertura (doppio o singolo)
        // ([^\"']+)                    -> Gruppo 3: IL PATH DELL'IMMAGINE
        // ([\"'])                      -> Gruppo 4: Cattura l'apice di chiusura
        String regex = "(<img\\s+[^>]*?src\\s*=\\s*)([\"'])([^\"']+)([\"'])";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(html);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String prefix = m.group(1);
            String quote = m.group(2);
            String imagePath = m.group(3);
            try {
                Path path = Paths.get(imagePath);
                if (Files.exists(path)) {
                    byte[] imageBytes = Files.readAllBytes(path);
                    String extension = getFileExtension(imagePath);
                    String mimeType = getMimeType(extension);
                    String base64Content = Base64.getEncoder().encodeToString(imageBytes);
                    String newSrc = "data:" + mimeType + ";base64," + base64Content;
                    m.appendReplacement(sb, Matcher.quoteReplacement(prefix + quote + newSrc + quote));
                } else {
                    log.error(String.format("Image %s not found", imagePath));
                    m.appendReplacement(sb, Matcher.quoteReplacement(m.group(0)));
                }
            } catch (IOException ex) {
                log.error(String.format("Error converting image %s to base64", imagePath), ex);
                m.appendReplacement(sb, Matcher.quoteReplacement(m.group(0)));
            }
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private String getFileExtension(String imagePath) {
        int lastDotIndex = imagePath.lastIndexOf('.');
        return lastDotIndex > 0
                ? imagePath.substring(lastDotIndex + 1).toLowerCase()
                : "png";
    }

    private String getMimeType(String extension) {
        if (extension != null) {
            switch (extension.toLowerCase()) {
                case "png":
                    return "image/png";
                case "jpg":
                case "jpeg":
                    return "image/jpeg";
                case "gif":
                    return "image/gif";
                case "bmp":
                    return "image/bmp";
                case "svg":
                    return "image/svg+xml";
                default:
                    return "image/png";
            }
        } else {
            return "image/png";
        }
    }
}
