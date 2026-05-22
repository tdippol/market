package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.business.enumeration.IniziativaStatusEnum;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.business.utils.IniziativaAcl;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.IniziativeService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.axiante.mui.persistence.entity.AuditLogEntity;
import com.axiante.mui.persistence.service.AuditLogService;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.webservice.dto.RAFRequest;
import com.axiante.mui.webapp.webservice.dto.RAFResponse;
import com.axiante.mui.webapp.webservice.util.TotalizzatoreHelper;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class EditIniziativaBackingBean implements FacesContextAware {

    @Getter
    private boolean locked = true;

    @Getter
    private boolean dataInizioDisabled = true;

    @Getter
    private boolean btnUnlockDisabled = true;

    @Getter
    private boolean btnDeleteDisabled = true;

    @Getter
    private boolean btnSaveDisabled = true;

    @Getter
    private boolean btnCancelDisabled = true;

    @Getter
    private String descrizione;

    @Getter
    private Date dataInizio;

    @Getter
    @Setter
    private Date dataFine;

    @Getter
    @Setter
    private String note;

    @Getter
    @Setter
    private String oggetto;

    @Getter
    @Setter
    private Boolean flagDecimale;

    @Getter
    private Date minDataInizio;

    @Getter
    private Date minDataFine;

    @Getter
    private IniziativaEntity iniziativa;

    private IniziativeService iniziativeService;
    private StatoPromoService statoPromoService;
    private DateTimeUtils dtUtils = new DateTimeUtils();

    private TotalizzatoreHelper helper;

    private AuditLogService auditLogService;
    public EditIniziativaBackingBean(IniziativeService iniziativeService, StatoPromoService statoPromoService, TotalizzatoreHelper helper, AuditLogService auditLogService) {
        this.iniziativeService = iniziativeService;
        this.statoPromoService = statoPromoService;
        this.helper = helper;
        this.auditLogService = auditLogService;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = StringUtils.isBlank(descrizione) ? "" : descrizione.trim().toUpperCase();
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
        if (dataInizio != null) {
            minDataFine = calculateMinData(dataInizio);
        }
    }

    public boolean canBeUnlocked(@NonNull IniziativaEntity iniziativa) {
        final Date today = dtUtils.getDateWithoutTime(new Date());
        return dtUtils.isAfter(iniziativa.getDataFine(), today, false)
                && !IniziativaAcl.isDeleted(iniziativa)
                && !IniziativaAcl.isPublished(iniziativa)
                && Boolean.TRUE.equals(iniziativa.getTotalizzatoreAllineato());
    }

    public void unlockEditIniziativa() {
        if (iniziativa == null || !canBeUnlocked(iniziativa)) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile sbloccare iniziativa selezionata"));
            return;
        }
        locked = false;
        btnUnlockDisabled = true;
        final Date today = dtUtils.getDateWithoutTime(new Date());
        dataInizioDisabled = dataInizio != null && dtUtils.isBefore(dataInizio, today, false);
        minDataInizio = today;
        minDataFine = today;
        btnDeleteDisabled = dtUtils.isBefore(iniziativa.getDataInizio(), today, true)  || hasPromozioniAttive();
        btnSaveDisabled = false;
        btnCancelDisabled = false;
    }

    boolean hasPromozioniAttive(){
        return this.iniziativa.getPromozioni() != null &&
               this.iniziativa.getPromozioni().stream()
                       .flatMap(p->p.getPromozioneStatoEntities().stream())
                       .filter(s->s.getDataFineStato()==null)
                       .filter(s->Integer.valueOf(s.getStatoPromozioneEntity().getCodiceStato())>10)
                       .findAny().isPresent();
    }

    public void loadIniziativa(IniziativaEntity iniziativa) {
        this.iniziativa = iniziativa;
        if (iniziativa != null) {
            descrizione = iniziativa.getDescrizione();
            dataInizio = iniziativa.getDataInizio();
            dataFine = iniziativa.getDataFine();
            note = iniziativa.getNote();
            oggetto = iniziativa.getOggetto();
            flagDecimale=iniziativa.getFlagDecimale();
            btnUnlockDisabled = !canBeUnlocked(iniziativa);
        } else {
            descrizione = null;
            dataInizio = null;
            dataFine = null;
            note = null;
            btnUnlockDisabled = true;
            oggetto = null;
            flagDecimale=null;
        }
        locked = true;
        dataInizioDisabled = true;
        minDataInizio = null;
        minDataFine = null;
        btnDeleteDisabled = true;
        btnSaveDisabled = true;
        btnCancelDisabled = true;
    }

    public boolean deleteIniziativa() {
        boolean deleted = false;
        final Date today = dtUtils.getDateWithoutTime(new Date());
        if (iniziativa != null && dtUtils.isAfter(iniziativa.getDataInizio(), today, false)) {
            try {
                iniziativa = saveCancelledIniziativa(iniziativa);
                deleted = true;
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        String.format("Iniziativa %s eliminata con successo", iniziativa.getDescrizione())));
            } catch (Exception ex) {
                log.error(String.format("Error deleting iniziativa with id %d", iniziativa.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore eliminaazione iniziativa selezionata"));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile eliminare iniziativa selezionata"));
        }
        return deleted;
    }

    public void discardEditIniziativa() {
        loadIniziativa(iniziativa);
    }

    public boolean saveEditIniziativa() {
        boolean saved = false;
        if (iniziativa != null) {
            try {
                if (validate()) {
                    iniziativa.setDescrizione(descrizione.trim().toUpperCase());
                    iniziativa.setDataInizio(dataInizio);
                    iniziativa.setDataFine(dataFine);
                    iniziativa.setNote(note);
                    iniziativa.setOggetto(oggetto);
                    iniziativa.setFlagDecimale(flagDecimale);
                    iniziativa.setTotalizzatoreAllineato(Boolean.FALSE);
                    iniziativa = (IniziativaEntity) iniziativeService.persistWithAuditLog(iniziativa, getCurrentUser().getName());

                    FacesMessage responseMessage = null;

                    RAFRequest rafRequest = helper.generate(iniziativa);
                    auditLogService.logAction(AuditLogEntity.EXTERNAL_RESOURCE, helper.getAuditPath(rafRequest), getCurrentUser().getName());
                    RAFResponse response = helper.parse(helper.queryRaf(rafRequest),true);
                    response.setMethod(rafRequest.getMethod());
                    if ( IniziativeMessageEnum.asMessageEnum(response) == null ){
                        switch (response.getStato()){
                            case 1:
                                if ( "POST".equals(rafRequest.getMethod())){
                                    //ok;
                                    iniziativa = iniziativeService.persist(helper.update(iniziativa,response));
                                    responseMessage =new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                                            "Iniziativa aggiornata con successo");
                                }
                                break;
                            case 2:
                            case 3:
                                if ( "PUT".equals(rafRequest.getMethod())){
                                    //ok;
                                    iniziativa = iniziativeService.persist(helper.update(iniziativa,response));
                                    responseMessage =new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                                            "Iniziativa aggiornata con successo");
                                }
                                break;
                            default:
                                // errore
                                responseMessage =new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore nei totalizzatori",
                                        response.getStatusLine());

                        }
                    } else {
                        responseMessage =new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore nei totalizzatori",
                                IniziativeMessageEnum.asMessageEnum(response).getDescrizione());
                    }
                    addMessage(null, responseMessage);
                    loadIniziativa(iniziativa);
                    saved = true;
                } else {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                            "Impossibile salvare iniziativa selezionata; controllare i valori impostati"));
                }
            } catch (Exception ex) {
                log.error(String.format("Error saving iniziativa with id %d", iniziativa.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore nel salvataggio iniziativa selezionata"));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile salvare iniziativa selezionata"));
        }
        return saved;
    }

    private boolean validate() {
        return !StringUtils.isBlank(descrizione) && dataInizio != null && dataFine != null
                && dtUtils.isAfter(dataFine, dataInizio, false);
    }

    private Date calculateMinData(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    private IniziativaEntity saveCancelledIniziativa(@NonNull IniziativaEntity iniziativa) throws Exception {
        final IniziativaStatoEntity lastStatus = IniziativaAcl.getLastStatus(iniziativa);
        if (lastStatus == null) {
            throw new Exception(String.format("Iniziativa with id %d does not have a valid last status",
                    iniziativa.getId()));
        }
        final StatoPromozioneEntity stato = statoPromoService.findByCode(IniziativaStatusEnum.CANCELLATA_00.getCodice());
        if (stato == null) {
            throw new Exception(String.format("Cannot find stato with code %s",
                    IniziativaStatusEnum.CANCELLATA_00.getCodice()));
        }
        final String usermame = getCurrentUser().getName();
        final Date now = new Date();
        final Date today = dtUtils.getDateWithoutTime(now);
        lastStatus.setDataFineStato(today);
        lastStatus.setDataAggiornamento(now);
        lastStatus.setCodiceUtenteAggiornamento(usermame);
        IniziativaStatoEntity iniziativaStato = (IniziativaStatoEntity)
                AuditLogFiller.fillAuditLogFields(new IniziativaStatoEntity(), usermame);
        iniziativaStato.setStatoPromo(stato);
        iniziativaStato.setDataInizioStato(today);
        iniziativa.addStato(iniziativaStato);
        iniziativeService.persistWithAuditLog(iniziativa, usermame);
        return iniziativa;
    }
}
