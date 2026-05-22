package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.DBPromoComplementariConstants;
import com.axiante.mui.common.utility.AxFileUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.UploadFidatyService;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Dependent
@Slf4j
public class UploadComplementari implements FacesContextAware {
    @Inject
    UploadFidatyService service;

    @Inject
    PianificazioneService pianificazioneService;

    @Getter
    PromozionePianificazioneEntity pianificazione;

    @Inject
    ApplicationProperties applicationProperties;

    @Inject
    PianificazionePromoUtil pianificazionePromoUtil;

    @Setter
    @Getter
    UploadedFile uploadedFile;

    File temporaryFile;

    String originalFileName;

    @Setter
    @Getter
    String descrizione;

    @Setter
    @Getter
    Date dialogDataInizioPubblicazione;

    @Setter
    @Getter
    Date dialogDataFinePubblicazione;

    @Setter
    @Getter
    Date minDateInizio;

    @Setter
    @Getter
    Date maxDateInizio;

    @Setter
    @Getter
    Date minDateFine;

    @Setter
    @Getter
    Date maxDateFine;

    @Setter
    String user;

    @Getter
    String title;

    @Getter
    String uploadedFileName;

    @Getter
    @Setter
    boolean validDates = true;

    @Getter
    @Setter
    boolean selectedDataInizioPublicazione = false;

    @Getter
    @Setter
    boolean selectedDataFinePublicazione = false;

    @Getter
    Boolean legacyNamingConvention = null;

    private static final String CATEGORIA_NAMING_CONVENTION = "%s-%s-%s-%s-%s%s-%s.txt";
    private static final String CATEGORIA_NAMING_CONVENTION_LEGACY = "%s-%s-%s-%s%s.txt";
    private static final String PRECARICATI_NAMING_CONVENTION = "%s-%s-%s-%s-%s-%s.txt";
    private static final String PRECARICATI_NAMING_CONVENTION_LEGACY = "%s-%s-%s-%s.txt";
    private static final SimpleDateFormat DATEFORMATTER = new SimpleDateFormat("ddMMyyyy");

    public void init() {
    }

    public void setPianificazione(PromozionePianificazioneEntity pianificazione) {
        if (pianificazione != null) {
            this.pianificazione = pianificazione;
            this.title = pianificazione.getPromozioneTestataEntity().getDescrizioneEstesa();
            calcolaNamingConvention();
        } else {
            this.title = "";
        }
        // Reset nome file da caricare al caricamento della dialog
        uploadedFileName = "";
    }

    public Date getMinDateInizio() {
        if (pianificazione != null) {
            return pianificazione.getDataInizio();
        }
        return null;
    }

    public Date getMaxDateInizio() {
        if (pianificazione != null) {
            return pianificazione.getDataFine();
        }
        return null;
    }

    public Date getMinDateFine() {
        if (pianificazione != null) {
            return pianificazione.getDataInizio();
        }
        return null;
    }

    public Date getMaxDateFine() {
        if (pianificazione != null) {
            return pianificazione.getDataFine();
        }
        return null;
    }

    public Date getDialogDataInizioPubblicazione() {
        if (!selectedDataInizioPublicazione) {
            if (pianificazione != null) {
                setDialogDataInizioPubblicazione(pianificazione.getDataInizio());
                return pianificazione.getDataInizio();
            }
        } else {
            return dialogDataInizioPubblicazione;
        }

        return null;
    }

    public Date getDialogDataFinePubblicazione() {
        if (!selectedDataFinePublicazione) {
            if (pianificazione != null) {
                setDialogDataFinePubblicazione(pianificazione.getDataFine());
                return pianificazione.getDataFine();
            }
        } else {
            return dialogDataFinePubblicazione;
        }

        return null;
    }

    public String getTipoElemento() {
        if (pianificazione != null) {
            PromozionePianificazioneEntity child = pianificazioneService.findChildByParent(pianificazione);
            if (child != null && child.getTipoElemento() != null) {
                return child.getTipoElemento();
            }
            return pianificazione.getTipoElemento();
        }
        return "";
    }

    public String getCodiceMeccanica() {
        return pianificazione != null ? pianificazione.getMeccanicaEntity().getCodiceMeccanica() : "";
    }

    public void handleUpload(final FileUploadEvent event) {
        uploadedFile = event.getFile();
        try {
            temporaryFile = File.createTempFile("temp_" + System.currentTimeMillis(), ".tmp");
            IOUtils.write(uploadedFile.getContents(), new FileOutputStream(temporaryFile));
            originalFileName = uploadedFile.getFileName();
        } catch (Exception e) {
            log.error("error saving temporary data", e);
            if (temporaryFile != null) {
                temporaryFile.delete();
                temporaryFile = null;
                originalFileName = null;
            }
        }
        // Nome file da visualizzare dopo caricamento del file
        uploadedFileName = event.getFile().getFileName();
    }

    public void upload() {
        if (this.user == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!",
                    "Impossibile recuperare il codice utente caricamento"));
            return;
        }
        if (pianificazione == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!",
                    "Impossibile recuperare il codice della pianificazione"));
            return;
        }
        if (temporaryFile != null) {
            final Date today = new Date(System.currentTimeMillis());
            String filename = createFileName(today);
            if (filename == null) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!",
                        "Impossibile creare il file secondo la naming convention"));
                return;

            }
            String checksum = null;
            try {
                checksum = AxFileUtils.getChecksum(MessageDigest.getInstance("SHA-256"), temporaryFile);
            } catch (NoSuchAlgorithmException ex) {
                log.warn("Unable to calculate checksum for file " + temporaryFile.getName(), ex);
            }
            final String description = getLegacyNamingConvention()
                    ? pianificazione.getPromozioneTestataEntity().getDescrizione()
                    : descrizione != null
                    ? descrizione
                    : "";
            UploadFidayEntity entity = new UploadFidayEntity();
            entity.setDataPubblicazione(today);
            entity.setOriginalFileName(originalFileName);
            entity.setUploadedFileName(filename);
            entity.setUserUpload(user);
            entity.setPromozionePianificazioneEntity(pianificazione);
            entity.setDataInizioPublicazione(getLegacyNamingConvention() ? today : dialogDataInizioPubblicazione);
            entity.setDataFinePublicazione(getLegacyNamingConvention() ? today : dialogDataFinePubblicazione);
            entity.setDescription(description);
            if (checksum != null) {
                entity.setChecksumFile(checksum);
            }
            try {
                UploadFidayEntity sovrascritta = service.findByNomeDestinazioneAndPianificazione(filename,
                        entity.getPromozionePianificazioneEntity().getId());
                if (sovrascritta != null) {
                    sovrascritta.setDataSovrascrittura(today);
                    service.persist(sovrascritta);
                }
                entity = service.persist(entity);
                if (landFile(entity.getUploadedFileName())) {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
                            "Il file " + filename + " e' stato caricato nella cartella di scambio"));
                } else {
                    // Error copying file to destination path; remove entity from DB
                    service.delete(entity);
                    if (sovrascritta != null) {
                        sovrascritta.setDataSovrascrittura(null);
                        service.persist(sovrascritta);
                    }
                    final String msg = String.format("Impossibile spostare il file '%s' nella cartella di scambio",
                            entity.getOriginalFileName());
                    log.error(msg);
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msg));
                }
            } catch (Exception ex) {
                final String msg = String.format("Errore caricamento file '%s'", entity.getOriginalFileName());
                log.error(msg, ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msg));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Missing!", "Nessun file selezionato!"));
        }

        clear();
    }

    private void calcolaNamingConvention() {
        if (pianificazione != null) {
            legacyNamingConvention = pianificazione.getPromozioneTestataEntity().getMuiCanalePromozione().isLegacyUploadNamingConvention();
        } else {
            legacyNamingConvention = true;
        }
    }

    public void clear() {
        setUploadedFile(null);
        setDescrizione(null);
        setDialogDataInizioPubblicazione(null);
        setDialogDataFinePubblicazione(null);
        setSelectedDataInizioPublicazione(false);
        setSelectedDataFinePublicazione(false);
    }

    protected String createFileName(Date today) {
        PromozioneTestataEntity testata = pianificazione.getPromozioneTestataEntity();
        if ( testata.getMuiCanalePromozione().getCodiceCanale() == null ) return null;
        switch (testata.getMuiCanalePromozione().getCodiceCanale().intValue()) {
            case 12:
                return createFileNameBuoniPotenziamento(testata);
            case 13:
                return createFileNameBuoniCategoria(today, testata);
            case 14:
            case 19:
            case 20:
                return createFileNamePrecaricatiSuCarta(today, testata);
            default:
                return null;
        }
    }

    private String createFileNameBuoniPotenziamento(PromozioneTestataEntity testata) {
        return String.format(CATEGORIA_NAMING_CONVENTION,
                DATEFORMATTER.format(getDialogDataInizioPubblicazione()),
                DATEFORMATTER.format(getDialogDataFinePubblicazione()),
                testata.getCodicePromozione(),
                pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                pianificazione.getBuonoScontoRadice(),
                padBuonoScontoProgressivo(),
                getDescrizione() != null ? getDescrizione() : "");
    }

    private String createFileNameBuoniCategoria(Date today, PromozioneTestataEntity testata) {
        // 13: Buoni Categoria
        // #3504: Namning convention basata su flag in configurazione
        return getLegacyNamingConvention()
                ? String.format(CATEGORIA_NAMING_CONVENTION_LEGACY,
                DATEFORMATTER.format(today),
                testata.getCodicePromozione(),
                pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                pianificazione.getBuonoScontoRadice(),
                String.format("%02d", pianificazione.getBuonoScontoProgressivo()))
                :
                String.format(CATEGORIA_NAMING_CONVENTION,
                        DATEFORMATTER.format(getDialogDataInizioPubblicazione()),
                        DATEFORMATTER.format(getDialogDataFinePubblicazione()),
                        testata.getCodicePromozione(),
                        pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                        pianificazione.getBuonoScontoRadice(),
                        String.format("%02d", pianificazione.getBuonoScontoProgressivo()),
                        getDescrizione() != null ? getDescrizione() : "");
    }
    private String createFileNamePrecaricatiSuCarta(Date today, PromozioneTestataEntity testata) {
        // 14: Precaricati su Carta
        // #3504: Namning convention basata su flag in configurazione
        return getLegacyNamingConvention()
                ? String.format(PRECARICATI_NAMING_CONVENTION_LEGACY,
                DATEFORMATTER.format(today),
                testata.getCodicePromozione(),
                pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                pianificazione.getNumSet())
                : String.format(PRECARICATI_NAMING_CONVENTION,
                DATEFORMATTER.format(getDialogDataInizioPubblicazione()),
                DATEFORMATTER.format(getDialogDataFinePubblicazione()),
                testata.getCodicePromozione(),
                pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                pianificazione.getNumSet(),
                getDescrizione() != null ? getDescrizione() : "");
    }
    private String padBuonoScontoProgressivo() {
        // feature #5155
        if ( pianificazione.getBuonoScontoRadice() != null && pianificazione.getBuonoScontoRadice().toString().length() == 3){
            //Se BUONO_SCONTO_RADICE = 3 cifre E BUONO_SCONTO_PROGRESSIVO = 1 cifra la MUI NON deve paddare uno 0 nel BUONO_SCONTO_PROGRESSIVO
            return pianificazione.getBuonoScontoProgressivo().toString();
        }
        //Se BUONO_SCONTO_RADICE = 2 cifre E BUONO_SCONTO_PROGRESSIVO = 1 cifra la MUI deve paddare uno 0 nel BUONO_SCONTO_PROGRESSIVO
        //Se BUONO_SCONTO_RADICE = 2 cifre E BUONO_SCONTO_PROGRESSIVO = 2 cifra la MUI NON deve paddare uno 0 nel BUONO_SCONTO_PROGRESSIVO
        return String.format("%02d", pianificazione.getBuonoScontoProgressivo());
    }

    public void setCurrentPlanningId() {
        String idPromoPianificazione = getRequestParameterMap().get("idPromoPianificazione");
        PromozionePianificazioneEntity pianificazione = null;
        if (idPromoPianificazione != null) {
            Long id = new Long(idPromoPianificazione);
            pianificazione = pianificazioneService.getPromoPianificazoneById(id);
            setPianificazione(pianificazione);
        } else {
            setPianificazione(null);
        }
//        if (pianificazione == null || pianificazionePromoUtil.isUploadComplementariBlocked(pianificazione)) {
//            executeScript("PF('updateComplementariPromoDisabledDialog').show()");
//        } else {
//            executeScript("PF('updateComplementariPromoDialog').show()");
//        }
        // MG#5649: tolto blocco su upload :(
        executeScript("PF('updateComplementariPromoDialog').show()");
    }

    private boolean landFile(String filename) {
        String landingZone = applicationProperties
                .getProperty(DBPromoComplementariConstants.PERCORSO_FILE_COMPLEMENTARI);
        if (landingZone == null) {
            log.error("missing configuration property " + DBPromoComplementariConstants.PERCORSO_FILE_COMPLEMENTARI);
            return false;
        }
        String path = landingZone + File.separator + filename;
        File file = new File(path);
        boolean success = false;
        try {
            success = (!file.exists() && file.createNewFile()) || file.canWrite();
            if (success) {
                FileUtils.copyFile(temporaryFile, file);
            } else {
                log.error("file " + filename + " already exists or there's an issue with directory permissions");
            }
        } catch (IOException e) {
            log.error("error writing contents to file " + path, e);
            success = false;
        } finally {
            temporaryFile.delete();
            temporaryFile = null;
            originalFileName = null;
        }
        return success;
    }

    public void promozioneDataInizioChanged(SelectEvent event) {
        setSelectedDataInizioPublicazione(true);
        setDialogDataInizioPubblicazione((Date) event.getObject());
        setValidDates(validateDataInizioPubblicazione());
    }

    public void promozioneDataFineChanged(SelectEvent event) {
        setSelectedDataFinePublicazione(true);
        setDialogDataFinePubblicazione((Date) event.getObject());
        setValidDates(validateDataFinePubblicazione());
    }

    public void promozioneDataInizioChanged(AjaxBehaviorEvent event) {
        setSelectedDataInizioPublicazione(true);
        setValidDates(validateDataInizioPubblicazione());
    }

    public void promozioneDataFineChanged(AjaxBehaviorEvent event) {
        setSelectedDataFinePublicazione(true);
        setValidDates(validateDataFinePubblicazione());
    }

    public boolean validateDataInizioPubblicazione() {
        if (dialogDataInizioPubblicazione == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    "Data Inizio Pubblicazione campo obbligatorio"));
            return false;
        }
        if (dialogDataInizioPubblicazione.before(pianificazione.getDataInizio())) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    "Data Inizio Pubblicazione non puo essere minore di Data Inizio Pianificazione"));
            return false;
        }
        if (dialogDataInizioPubblicazione.after(pianificazione.getDataFine())) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    "Data Inizio Pubblicazione non puo essere maggiore di Data Fine Pianificazione"));
            return false;
        }

        if (dialogDataInizioPubblicazione.after(dialogDataFinePubblicazione)) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    "Data Inizio Pubblicazione non puo essere maggiore di Data Fine Pubblicazione"));
            return false;
        }
        return true;
    }

    public boolean validateDataFinePubblicazione() {
        if (dialogDataFinePubblicazione == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    "Data Fine Pubblicazione campo obbligatorio"));
            return false;
        }
        if (dialogDataFinePubblicazione.before(pianificazione.getDataInizio())) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    "Data Fine Pubblicazione non puo essere minore di Data Inizio Pianificazione"));
            return false;
        }
        if (dialogDataFinePubblicazione.after(pianificazione.getDataFine())) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    "Data Fine Pubblicazione non puo essere maggiore di Data Fine Pianificazione"));
            return false;
        }

        if (dialogDataFinePubblicazione.before(dialogDataInizioPubblicazione)) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    "Data Fine Pubblicazione non puo essere miore di Data Inizio Pubblicazione"));
            return false;
        }
        return true;
    }

    public void clear(ActionEvent e) {
        log.debug("action listener called");
        clear();
    }

    public void removeFile() {
        setUploadedFile(null);
        uploadedFileName = "";
    }
}
