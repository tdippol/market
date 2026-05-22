package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.business.enumeration.IniziativaStatusEnum;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.IniziativeService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.axiante.mui.persistence.entity.AuditLogEntity;
import com.axiante.mui.persistence.service.AuditLogService;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.data.IniziativeMessageEnum;
import com.axiante.mui.webapp.webservice.dto.RAFRequest;
import com.axiante.mui.webapp.webservice.dto.RAFResponse;
import com.axiante.mui.webapp.webservice.util.TotalizzatoreHelper;
import java.util.Calendar;
import java.util.Date;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.MenuItem;

@MuiViewModel("iniziative")
@Dependent
@Slf4j
public class IniziativeView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = 4099053515069362378L;

    @Getter
    private final Integer descrizioneMaxLength = 30;

    @Getter
    private final Integer oggettoMaxLength = 24;

    @Inject
    private Instance<IniziativeService> iniziativeServiceInstance;

    @Inject
    private Instance<StatoPromoService> statoPromoServiceInstance;

    @Getter
    private String descrizioneIniziativa;

    @Getter
    private String oggettoIniziativa;

    @Getter
    @Setter
    private boolean flagDecimali;

    @Getter
    private Date dataInizioIniziativa;

    @Getter
    private Date dataFineIniziativa;

    @Getter
    private Date minDataInizioIniziativa;

    @Getter
    private Date minDataFineIniziativa;

    @Getter
    @Setter
    private String noteIniziativa;

    @Getter
    private boolean btnConfirmAddIniziativaDisabled = true;

    @Inject
    Instance<TotalizzatoreHelper> totalizzatoreHelperInstance;

    @Inject
    Instance<AuditLogService> auditLogServiceInstance;
    private DateTimeUtils dtUtils = new DateTimeUtils();

    public void init() {
        minDataInizioIniziativa = calculateMinDataInizioIniziativa();
        minDataFineIniziativa = calculateMinDataFineIniziativa();
    }

    public void resetDialogAddIniziativa() {
        descrizioneIniziativa = null;
        dataInizioIniziativa = null;
        dataFineIniziativa = null;
        noteIniziativa = null;
        minDataInizioIniziativa = calculateMinDataInizioIniziativa();
        minDataFineIniziativa = null;
        oggettoIniziativa=null;
        flagDecimali=false;
        updateBtnConfirmAddIniziativa();
    }

    public void prepareDialogAddIniziativa() {
        resetDialogAddIniziativa();
    }

    public void confirmDialogAddIniziativa() {
        if (validate()) {
            try {
                IniziativeMessageEnum message = saveIniziativa();
                if (message == null)
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                            "Nuova iniziativa creata correttamente"));
                else
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
                            message.getDescrizione()));
            } catch (Exception ex) {
                log.error("Error saving IniziativaEntity", ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Impossibile creare nuova rata di fatturazione"));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile creare nuova iniziativa"));
        }
        resetDialogAddIniziativa();
    }

    public void setDescrizioneIniziativa(String descrizioneIniziativa) {
        this.descrizioneIniziativa = descrizioneIniziativa != null ? descrizioneIniziativa.toUpperCase() : null;
        updateBtnConfirmAddIniziativa();
    }

    public void  setOggettoIniziativa(String oggetto){
        this.oggettoIniziativa = oggetto != null ? oggetto.toUpperCase(): null;
        updateBtnConfirmAddIniziativa();
    }

    public void setDataInizioIniziativa(Date dataInizioIniziativa) {
        this.dataInizioIniziativa = dataInizioIniziativa;
        updateBtnConfirmAddIniziativa();
        if (dataInizioIniziativa != null) {
            minDataFineIniziativa = dataInizioIniziativa;
        }
    }

    public void setDataFineIniziativa(Date dataFineIniziativa) {
        this.dataFineIniziativa = dataFineIniziativa;
        updateBtnConfirmAddIniziativa();
    }

    private void updateBtnConfirmAddIniziativa() {
        btnConfirmAddIniziativaDisabled = !validate();
    }

    private boolean validateDates() {
        if (dataInizioIniziativa == null || dataFineIniziativa == null) {
            return false;
        }
        final Date today = dtUtils.getDateWithoutTime(new Date());
        return dtUtils.isAfter(dataInizioIniziativa, today, false)
                && dtUtils.isAfter(dataFineIniziativa, dataInizioIniziativa, true);
    }

    private boolean validateDescrizione() {
        return !StringUtils.isBlank(descrizioneIniziativa) && descrizioneIniziativa.length() <= descrizioneMaxLength;
    }

    private boolean validateOggetto() {
        return !StringUtils.isBlank(oggettoIniziativa) && oggettoIniziativa.length() <= oggettoMaxLength;
    }

    private boolean validate() {
        return validateDescrizione() && validateOggetto() && validateDates();
    }

    private IniziativeMessageEnum saveIniziativa() {
        IniziativaEntity iniziativa = null;
        try {
            iniziativa = new IniziativaEntity();
            iniziativa.setDescrizione(descrizioneIniziativa.trim().toUpperCase());
            iniziativa.setDataInizio(dataInizioIniziativa);
            iniziativa.setDataFine(dataFineIniziativa);
            if (!StringUtils.isBlank(noteIniziativa)) {
                iniziativa.setNote(noteIniziativa.trim());
            }
            iniziativa.setFlagDecimale(flagDecimali);
            iniziativa.setOggetto(oggettoIniziativa);
            // Crea stato iniziale (10)
            final StatoPromozioneEntity stato = statoPromoServiceInstance.get()
                    .findByCode(IniziativaStatusEnum.NON_PUBBLICATA.getCodice());
            if (stato == null) {
                throw new Exception(String.format("Cannot find stato with code %s",
                        IniziativaStatusEnum.NON_PUBBLICATA.getCodice()));
            }
            final String username = getUserDto().getUsermame();
            IniziativaStatoEntity iniziativaStato = (IniziativaStatoEntity)
                    AuditLogFiller.fillAuditLogFields(new IniziativaStatoEntity(), username);
            iniziativaStato.setStatoPromo(stato);
            iniziativaStato.setDataInizioStato(new Date());
            iniziativa.addStato(iniziativaStato);
            iniziativa = (IniziativaEntity) iniziativeServiceInstance.get().persistWithAuditLog(iniziativa, username);
        } catch ( Exception e){
            log.error("Error saving iniziativa", e);
            return IniziativeMessageEnum.SAVE_INIZIATIVA_ERRROR;
        }
        // totalizzatori
        try {
            TotalizzatoreHelper helper = totalizzatoreHelperInstance.get();
            RAFRequest request = helper.generate(iniziativa);
            auditLogServiceInstance.get().logAction(AuditLogEntity.EXTERNAL_RESOURCE, helper.getAuditPath(request), getCurrentUser().getName());
            RAFResponse response = helper.parse(helper.queryRaf(request), true);
            response.setMethod(request.getMethod());
            if (IniziativeMessageEnum.asMessageEnum(response) == null) {
                if (response.getStato() == 1) {
                    iniziativa = helper.update(iniziativa, response);
                    try {
                        iniziativeServiceInstance.get().persist(iniziativa);
                    } catch (Exception totalizzatoreException) {
                        log.error("Error saving totalizzatori ", totalizzatoreException);
                        return IniziativeMessageEnum.SAVE_TOTALIZZATORE_ERROR;
                    }
                }
            } else {
                return IniziativeMessageEnum.asMessageEnum(response);
            }
        } catch(Exception e){
            log.error("Errore durante la generazione dei totalizzatori", e);
            return IniziativeMessageEnum.GEN_ERRROR;
        }
        return null;
    }

    private Date calculateMinDataInizioIniziativa() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dtUtils.getDateWithoutTime(new Date()));
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    private Date calculateMinDataFineIniziativa() {
        if (dataInizioIniziativa != null) {
            return dataInizioIniziativa;
        }
        return calculateMinDataInizioIniziativa();
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
