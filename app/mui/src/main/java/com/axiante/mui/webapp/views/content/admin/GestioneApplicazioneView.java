package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.backing.ConfigurationCatalog;
import com.axiante.mui.backing.ConnectionCatalog;
import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.filter.ConfigurationFilterCatalog;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.entity.AuditLogEntity;
import com.axiante.mui.persistence.entity.ConnectionSetupEntity;
import com.axiante.mui.persistence.service.ApplicationPropertiesService;
import com.axiante.mui.persistence.service.AuditLogService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.ImageProducer;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.tm1.mdx.objects.Query;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;

@Slf4j
@MuiViewModel("gestioneApplicazione")
@Named("gestioneApplicazione")
@SessionScoped
public class GestioneApplicazioneView extends AbstractAdminView {
    private static final long serialVersionUID = -7845751138146058766L;
    @Inject
    @Getter
    transient private ApplicationFilterCatalogProducer catalogProducer;
    @Inject
    @Getter
    transient private CatalogReducer catalogReducer;
    @Inject
    transient private ApplicationProperties applicationProperties;
    @Inject
    transient private ConnectionCatalog connectionCatalog;
    @Inject
    transient private ConfigurationCatalog configurationCatalog;
    @Inject
    transient private ConfigurationFilterCatalog configurationFilterCatalog;
    @Inject
    transient private MuiService muiService;
    @Inject
    transient private ApplicationPropertiesService applicationPropertyService;

    @Getter
    @Setter
    private ApplicationPropertiesEntity selectedApplicationProperty;
    @Getter
    @Setter
    private List<ApplicationPropertiesEntity> applicationPropertiesEntityList;
    @Getter
    @Setter
    private ConnectionSetupEntity selectedConnectionSetup;
    @Getter
    @Setter
    private List<ConnectionSetupEntity> connectionSetupEntityList;
    @Getter
    private AdminModeBackingBean adminBean;

    @Getter
    private AdminModeOverrideBackingBean adminModeOverrideBean;

    @Getter
    private LazyDataModel<AuditLogEntity> auditLogDataModel;
    @Getter
    DBPromoAuditLogBackingBean auditLog;
    @Inject
    private AuditLogService auditLogService;

    @Inject
    private ImageProducer imageProducer;

    public void addConnection() {
        try {
            if (validate()) {
                muiService.persistConnectionSetup(selectedConnectionSetup);
                readConnectionSetups();
                PrimeFaces.current().executeScript("PF('dlgAddConnectionWV').hide();");
                initSelectedConnection();
                updateDependetObjects();
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Connessione Aggiunta!", ""));
            }
        } catch (final Exception e) {
            log.error("Error adding connection", e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
        }
    }

    public void deleteConnection() {
        try {
            if (selectedConnectionSetup != null) {
                muiService.removeConnectionSetup(selectedConnectionSetup);
                readConnectionSetups();
            }
            selectedConnectionSetup = null;
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Connessione Eliminata!", ""));

        } catch (final Exception e) {
            log.error("error deleting connection setup", e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
        }
    }

    public void editApplicationProperty(final RowEditEvent event) {
        try {
            setSelectedApplicationProperty((ApplicationPropertiesEntity) event.getObject());
            muiService.persistApplicationProperty(selectedApplicationProperty);
            applicationProperties.update();
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proprietà Modificata!", ""));
        } catch (final Exception e) {
            log.error("Error editing application property", e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
        }
    }

    public void editConnectionSetup(final RowEditEvent event) {
        try {
            setSelectedConnectionSetup((ConnectionSetupEntity) event.getObject());
            muiService.persistConnectionSetup(selectedConnectionSetup);
            //			connectionCatalog.update();
            //			configurationCatalog.update();
            //			configurationFilterCatalog.update();
            updateDependetObjects();
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Configurazione Modificata!", ""));
        } catch (final Exception e) {
            log.error("Error editing connection setup", e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
        }
    }

    private void updateDependetObjects() {
        connectionCatalog.update();
        configurationCatalog.update();
        configurationFilterCatalog.update();
    }

    @PostConstruct
    public void init() {
        initializeFields();
        readApplicationProperties();
        readConnectionSetups();
        adminBean = new AdminModeBackingBean(applicationPropertyService, applicationProperties);
        adminModeOverrideBean = new AdminModeOverrideBackingBean(muiService);
        this.auditLog = new DBPromoAuditLogBackingBean(auditLogService);
        this.auditLogDataModel = new LazyAuditLogDataModel(auditLogService, auditLog);
    }

    public void initSelectedConnection() {
        selectedConnectionSetup = new ConnectionSetupEntity();
    }

    public void openAddConnectionDialog() {
        initSelectedConnection();
    }

    @Override
    public Query prepareFilteredQuery(final String grid) {
        return null;
    }

    private void initializeFields() {
        Class<ApplicationProperties> clazz = ApplicationProperties.class;
        Field[] fields = clazz.getFields();
        final String radix = "DEFAULT_";
        final String exclude = "ADMIN_MODE";
        Arrays.stream(fields)
                .filter(f -> java.lang.reflect.Modifier.isStatic(f.getModifiers())) // campi statici
                .filter(f -> f.getName().startsWith(radix)) // che si chiamano DEFAULT_qualcosa
                .filter(f -> !f.getName().contains(exclude)) // che non si occupano di ADMIN_MODE
                .forEach(f -> {
                    String field = f.getName().substring(radix.length()); // nome della proprieta'
                    Class<?> t = f.getType();
                    try {
                        if (t == Integer.class) {
                            applicationPropertyService.getOrCreateProperty(field, "" + f.get(null));
                        } else if (t == Double.class) {
                            applicationPropertyService.getOrCreateProperty(field, "" + f.get(null));
                        } else if (t == Boolean.class) {
                            applicationPropertyService.getOrCreateProperty(field, "" + f.get(null));
                        } else {
                            applicationPropertyService.getOrCreateProperty(field, "" + f.get(null));
                        }
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        log.error(String.format("Error setting default value for variable %s", field), e);
                    }
                });
    }


    private void readApplicationProperties() {
        try {
            createDocumentDataPath();
            applicationPropertiesEntityList = muiService.readApplicationProperties().stream()
                    .filter(p -> !p.getKey().startsWith(ApplicationProperties.ADMIN_MODE))
                    .filter(p -> !p.getKey().startsWith(ApplicationProperties.MEDIA_MODALITA_COMPRATORE))
                    .filter(p -> !p.getKey().startsWith(ApplicationProperties.CANALE_PIANO_MEDIA))
                    .sorted(Comparator.comparing(ApplicationPropertiesEntity::getKey))
                    .collect(Collectors.toList());
        } catch (final Exception e) {
            log.error("error reading property list", e);
        }
    }

    private void createDocumentDataPath() {
        // #3284: Create DOCUMENT_DATA_PATH entry in DB if not exists
        if (applicationProperties.getProperty(ApplicationProperties.DOCUMENT_DATA_PATH) == null) {
            createProperty(ApplicationProperties.DOCUMENT_DATA_PATH, ApplicationProperties.DOCUMENT_DATA_PATH_DEFAULT);
        }
    }

    private void createProperty(final String key, final String value) {
        try {
            final ApplicationPropertiesEntity entity = new ApplicationPropertiesEntity();
            entity.setKey(key);
            entity.setValue(value);
            entity.getUuid();
            muiService.persistApplicationProperty(entity);
        } catch (Exception ex) {
            log.error(String.format("Error creating property '%s' with default value '%s'", key, value), ex);
        }
    }

    private void readConnectionSetups() {
        try {
            connectionSetupEntityList = muiService.readConnectionSetups();
        } catch (final Exception e) {
            log.error("Error reading connection setups", e);
        }
    }

    @Override
    public void updateView() {
        // Do nothing
    }

    @Override
    public void updateView(final String grid) {
        updateView();
    }

    public boolean validate() throws Exception {
        if (selectedConnectionSetup == null) {
            throw new Exception("Errore di inizializzazione oggetto 'ConnectionSetup'.");
        }
        if ((selectedConnectionSetup.getConnectionName() == null)
                || selectedConnectionSetup.getConnectionName().equals("")) {
            throw new Exception("Campo 'Nome' obbligatorio.");
        }
        if ((selectedConnectionSetup.getHost() == null) || selectedConnectionSetup.getHost().equals("")) {
            throw new Exception("Campo 'Host' obbligatorio.");
        }
        if (selectedConnectionSetup.getPort() == null) {
            throw new Exception("Campo 'Port' obbligatorio.");
        }
        if ((selectedConnectionSetup.getPath() == null) || selectedConnectionSetup.getPath().equals("")) {
            throw new Exception("Campo 'Path' obbligatorio.");
        }
        return true;
    }

    public void uploadLoginImage(final FileUploadEvent event) {
        uploadImage(event, ApplicationProperties.LOGIN_IMAGE);
    }

    public void uploadTopBarImage(final FileUploadEvent event) {
        uploadImage(event,ApplicationProperties.TOP_IMAGE);
    }

    public void uploadFaviconImage(final FileUploadEvent event) {
        uploadImage(event, ApplicationProperties.FAVICON_NAME);
    }

    private void uploadImage(final FileUploadEvent event, final String fileName) {
        if ((event != null) && (event.getFile() != null)) {
            File file = imageProducer.getFile(fileName);
            if (file != null) {
                if (file.canWrite()) {
                    try {
                        try (InputStream input = event.getFile().getInputstream()) {
                            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            TimeUnit.SECONDS.sleep(1);
                            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Immagine Caricata!", ""));
                        } catch (final InterruptedException e) {
                            log.error("uploadImage: can't sleep.", e);
                        }
                    } catch (final IOException e) {
                        log.error("Error uploading " + fileName, e);
                        addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento", e.getMessage()));
                    }
                } else {
                    log.error(String.format("Mancano i permessi di scrittura per il file %s", file.getAbsolutePath()));
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento", "Permesso negato"));
                }
            } else {
                log.error(String.format("Error getting destination file %s", fileName));
                addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento", "Impossibile scrivere il file"));
            }
        }
    }
}
