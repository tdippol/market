package com.axiante.mui.webapp.views.content.dbpromo.macrospaziMedia;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.FornitoriRetailEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiEventoRetailEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;
import com.axiante.mui.webapp.utils.EventoRetailUtils;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.views.util.UiUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.SelectEvent;

import javax.faces.event.AjaxBehaviorEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
public class EventiDialog {
    private static final String ADD_EVENTO_TITLE = "Aggiungi Evento";
    private static final String EDIT_EVENTO_TITLE = "Modifica Evento";

    @Getter
    protected MuiEventoRetailEntity bean;

    protected final FacesContextAware context;
    protected final List<FornitoriRetailEntity> fornitoriRetail;

    @Getter
    protected boolean editing;

    @Getter
    private String labelDataEliminazioneFornitore;

    @Getter
    private boolean btnSalvaEventoDisabled = true;

    private final EventoRetailUtils eventoRetailUtils = new EventoRetailUtils();

    public EventiDialog(FacesContextAware context, List<FornitoriRetailEntity> fornitoriRetail) {
        this.context = context;
        this.fornitoriRetail = fornitoriRetail;
        this.editing = false;
    }

    public void setMacrospazio(@NonNull MuiMacrospazioMediaEntity macrospazio) {
        if (bean != null) {
            bean.setMacrospazio(macrospazio);
            if (macrospazio.getListino() != null) {
                bean.setValoreContributo(BigDecimal.valueOf(macrospazio.getListino()));
            }
        }
    }

    public String getTitle() {
        return editing ? EDIT_EVENTO_TITLE : ADD_EVENTO_TITLE;
    }

    public boolean isDataInizioDisabled() {
        if (this.bean == null || (this.bean.getId() == null && editing)) {
            return true;
        }
        if (!editing) {
            return false;
        }
        return eventoRetailUtils.hasBegun(bean);
    }

    public boolean isDataFineDisabled() {
        if (this.bean == null || (this.bean.getId() == null && editing)) {
            return true;
        }
        if (!editing) {
            return false;
        }
        return !eventoRetailUtils.isActive(bean) && !eventoRetailUtils.isFuture(bean);
    }

    public boolean getFornitoreDisabled() {
        if (this.bean == null || (this.bean.getId() == null && editing)) {
            return true;
        }
        if (!editing) {
            return false;
        }
        return eventoRetailUtils.hasBegun(bean);
    }

    public boolean getCausaleDisabled() {
        // In editing disabilitare se in corso o passato
        return editing && (eventoRetailUtils.hasBegun(bean) || eventoRetailUtils.hasEnded(bean));
    }

    public boolean getContributoDisabled() {
        // In editing disabilitare se passato
        return editing && eventoRetailUtils.hasEnded(bean);
    }

    public void onDataInizioSelect(SelectEvent event) {
        try {
            Date date = (Date) event.getObject();
            changeDataInizio(date);
        } catch (Exception e) {
            log.error("Error in onDataInizioSelect", e);
            context.addErrorMessage("Errore applicativo",
                    "Impossibile impostare la data di inizio, contattare l'assistenza");
        }
    }

    public void onDataInizioChange(AjaxBehaviorEvent event) {
        try{
            Date date = UiUtils.getEventDateValue(event);
            changeDataInizio(date);
        } catch (Exception e) {
            log.error("Error getting data inizio macrospazio from event", e);
            context.addErrorMessage("Errore applicativo",
                    "Impossibile impostare la data di inizio macrospazio, contattare l'assistenza");
        }
    }

    public void onDataFineSelect(SelectEvent event) {
        try {
            Date date = (Date) event.getObject();
            changeDataFine(date);
        } catch (Exception e) {
            log.error("Error in onDataFineSelect", e);
            context.addErrorMessage("Errore applicativo",
                    "Impossibile impostare la data di fine, contattare l'assistenza");
        }
    }

    public void onDataFineChange(AjaxBehaviorEvent event) {
        try {
            Date date = UiUtils.getEventDateValue(event);
            changeDataFine(date);
        } catch (Exception e) {
            log.error("Error getting data fine macrospazio from event", e);
            context.addErrorMessage("Errore applicativo",
                    "Impossibile impostare la data di fine macrospazio, contattare l'assistenza");
        }
    }

    public void onFornitoreChange(AjaxBehaviorEvent event) {
        try {
            String codiceFornitore = UiUtils.getEventStringValue(event);
            if (codiceFornitore != null && !codiceFornitore.trim().isEmpty()) {
                updateLabelDataEliminazioneFornitore(codiceFornitore);
                if (bean != null) {
                    bean.setCodiceFornitore(codiceFornitore);
                    handleDatesOnFornitore(codiceFornitore);
                    btnSalvaEventoDisabled = btnSalvaEventoDisabled || btnSalvaEventoDisabledOnRequiredFields();
                }
            } else {
                labelDataEliminazioneFornitore = null;
                btnSalvaEventoDisabled = true;
            }
        } catch (Exception e) {
            labelDataEliminazioneFornitore = null;
            btnSalvaEventoDisabled = true;
            log.error("Error getting fornitore from event", e);
            context.addErrorMessage("Errore applicativo",
                    "Impossibile impostare il fornitore, contattare l'assistenza");
        }
    }

    public void onCausaleChange(AjaxBehaviorEvent event) {
        try {
            String causale = UiUtils.getEventStringValue(event);
            if (bean != null) {
                bean.setCodiceCausale(causale);
                btnSalvaEventoDisabled = btnSalvaEventoDisabledOnFornitore() || btnSalvaEventoDisabledOnRequiredFields();
            }
        } catch (Exception e) {
            btnSalvaEventoDisabled = true;
            log.error("Error getting causale from event", e);
            context.addErrorMessage("Errore applicativo",
                    "Impossibile impostare la causale, contattare l'assistenza");
        }
    }

    public void onValoreChange(AjaxBehaviorEvent event) {
        try {
            BigDecimal valore = UiUtils.getEventBigDecimalValue(event);
            if (bean != null) {
                bean.setValoreContributo(valore);
                btnSalvaEventoDisabled = btnSalvaEventoDisabledOnFornitore() || btnSalvaEventoDisabledOnRequiredFields();
            }
        } catch (Exception e) {
            btnSalvaEventoDisabled = true;
            log.error("Error getting valore contributo from event", e);
            context.addErrorMessage("Errore applicativo",
                    "Impossibile impostare il valore contributo, contattare l'assistenza");
        }
    }

    public void onNoteChange(AjaxBehaviorEvent event) {
        try {
            String note = UiUtils.getEventStringValue(event);
            if (bean != null) {
                bean.setNote(note);
            }
        } catch (Exception e) {
            log.error("Error getting note from event", e);
            context.addErrorMessage("Errore applicativo",
                    "Impossibile impostare le note, contattare l'assistenza");
        }
    }

    protected void changeDataInizio(Date date) {
        if (bean != null) {
            bean.setDataInizio(date);
            if (date != null && inThePast(date)) {
                context.addErrorMessage("Attenzione", "Data inizio non può essere nel passato");
                btnSalvaEventoDisabled = true;
                return;
            }
            if (date != null && isAfterFineMacrospazio(date)) {
                context.addErrorMessage("Attenzione",
                        "Data inizio non può essere successiva alla data fine del macrospazio.");
                btnSalvaEventoDisabled = true;
                if (bean.getDataFine() != null) {
                    bean.setDataFine(null);
                }
                return;
            }
            if (date != null && isAfterEliminazioneFornitore(date)) {
                context.addErrorMessage("Attenzione",
                        "Data inizio non può essere successiva alla data di eliminazione del fornitore selezionato.");
                btnSalvaEventoDisabled = true;
                return;
            }
            if (date != null && isAfterDataFine(date)) {
                bean.setDataFine(null);
            }
            btnSalvaEventoDisabled = btnSalvaEventoDisabledOnRequiredFields() || btnSalvaEventoDisabledOnFornitore();
        } else {
            btnSalvaEventoDisabled = true;
        }
    }

    protected void changeDataFine(Date date) {
        if (bean != null) {
            bean.setDataFine(date);
            if (date != null && inThePast(date)) {
                context.addErrorMessage("Attenzione", "Data fine non può essere nel passato");
                btnSalvaEventoDisabled = true;
                return;
            }
            if (date != null && isAfterFineMacrospazio(date)) {
                context.addErrorMessage("Attenzione",
                        "Data fine non può essere successiva alla data fine del macrospazio.");
                btnSalvaEventoDisabled = true;
                return;
            }
            if (date != null && isAfterEliminazioneFornitore(date)) {
                context.addErrorMessage("Attenzione",
                        "Data fine non può essere successiva alla data di eliminazione del fornitore selezionato.");
                btnSalvaEventoDisabled = true;
                return;
            }
            if (date != null && isBeforeDataInizio(date)) {
                context.addErrorMessage("Attenzione", "Data fine non può essere precedente alla data inizio");
                btnSalvaEventoDisabled = true;
                return;
            }
            btnSalvaEventoDisabled = btnSalvaEventoDisabledOnRequiredFields() || btnSalvaEventoDisabledOnFornitore();
        } else {
            btnSalvaEventoDisabled = true;
        }
    }

    public void setBean(MuiEventoRetailEntity bean) {
        this.bean = bean;
        if (bean != null && bean.getCodiceFornitore() != null) {
            updateLabelDataEliminazioneFornitore(bean.getCodiceFornitore());
        } else {
            labelDataEliminazioneFornitore = null;
        }
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
        btnSalvaEventoDisabled = !editing;
    }

    private void updateLabelDataEliminazioneFornitore(String codiceFornitore) {
        FornitoriRetailEntity fornitore = getFornitoreFromCodice(codiceFornitore);
        if (fornitore == null) {
            //noinspection ConstantValue
            context.addErrorMessage("Attenzione",
                    String.format("Errore recupero fornitore con codice %s.", codiceFornitore));
            btnSalvaEventoDisabled = true;
        } else if (fornitore.getDataEliminazione() != null) {
            Date dataEliminazioneFornitore = fornitore.getDataEliminazione();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            labelDataEliminazioneFornitore = String.format("Data eliminazione: %s", sdf.format(dataEliminazioneFornitore));
            btnSalvaEventoDisabled = btnSalvaEventoDisabledOnDataEliminazioneFornitore(dataEliminazioneFornitore) || btnSalvaEventoDisabledOnRequiredFields();
        } else {
            labelDataEliminazioneFornitore = null;
            btnSalvaEventoDisabled = btnSalvaEventoDisabledOnRequiredFields();
        }
    }

    private boolean btnSalvaEventoDisabledOnFornitore() {
        if (bean.getCodiceFornitore() == null) {
            return true;
        }
        FornitoriRetailEntity fornitore = getFornitoreFromCodice(bean.getCodiceFornitore());
        //noinspection ConstantValue
        if (fornitore == null) {
            return true;
        }
        Date dataEliminazioneFornitore = fornitore.getDataEliminazione();
        return dataEliminazioneFornitore != null && btnSalvaEventoDisabledOnDataEliminazioneFornitore(dataEliminazioneFornitore);
    }

    private boolean btnSalvaEventoDisabledOnRequiredFields() {
        if (bean == null) {
            return true;
        }
        String codiceFornitore = bean.getCodiceFornitore();
        String causale = bean.getCodiceCausale();
        BigDecimal valoreContributo = bean.getValoreContributo();
        Date dataInizio = bean.getDataInizio();
        Date dataFine = bean.getDataFine();
        boolean result = codiceFornitore == null || codiceFornitore.trim().isEmpty()
                || causale == null || causale.trim().isEmpty()
                || valoreContributo == null
                || dataInizio == null || dataFine == null || dataFine.before(dataInizio);
        return editing
                ? result
                : result || inThePast(dataInizio) || inThePast(dataFine);
    }

    private boolean btnSalvaEventoDisabledOnDataEliminazioneFornitore(Date dataEliminazioneFornitore) {
        if (bean != null && labelDataEliminazioneFornitore != null && dataEliminazioneFornitore != null) {
            Date dataInizioMacrospazio = bean.getMacrospazio().getDataInizio();
            if (dataInizioMacrospazio != null && dataEliminazioneFornitore.before(dataInizioMacrospazio)) {
                context.addErrorMessage("Attenzione",
                        "Il fornitore selezionato ha una data di eliminazione precedente alla data inizio del Macrospazio.");
                return true;
            }
        }
        return false;
    }

    private void handleDatesOnFornitore(String codiceFornitore) {
        if (codiceFornitore == null) {
            return;
        }
        FornitoriRetailEntity fornitore = getFornitoreFromCodice(codiceFornitore);
        //noinspection ConstantValue
        if (fornitore == null || fornitore.getDataEliminazione() == null) {
            return;
        }
        Date dataEliminazioneFornitore = fornitore.getDataEliminazione();
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        if (bean.getDataInizio() != null && dateTimeUtils.isAfter(bean.getDataInizio(), dataEliminazioneFornitore, true)) {
            bean.setDataInizio(null);
        }
        if (bean.getDataFine() != null && dateTimeUtils.isAfter(bean.getDataFine(), dataEliminazioneFornitore, true)) {
            bean.setDataFine(null);
        }
    }

    private boolean inThePast(Date date) {
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        Date today = dateTimeUtils.getDateWithoutTime(new Date());
        return dateTimeUtils.isBefore(date, today, true);
    }

    private boolean isAfterFineMacrospazio(Date date) {
        Date dataFineMacrospazio = bean.getMacrospazio().getDataFine();
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        return dataFineMacrospazio != null && dateTimeUtils.isAfter(date, dataFineMacrospazio, true);
    }

    private boolean isAfterEliminazioneFornitore(Date date) {
        if (bean.getCodiceFornitore() == null) {
            return false;
        }
        FornitoriRetailEntity fornitore = getFornitoreFromCodice(bean.getCodiceFornitore());
        //noinspection ConstantValue
        if (fornitore != null && fornitore.getDataEliminazione() != null) {
            Date dataEliminazioneFornitore = fornitore.getDataEliminazione();
            DateTimeUtils dateTimeUtils = new DateTimeUtils();
            return dateTimeUtils.isAfter(date, dataEliminazioneFornitore, true);
        }
        return false;
    }

    private boolean isAfterDataFine(Date date) {
        if (bean.getDataFine() == null) {
            return false;
        }
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        return dateTimeUtils.isAfter(date, bean.getDataFine(), true);
    }

    private boolean isBeforeDataInizio(Date date) {
        if (bean.getDataInizio() == null) {
            return false;
        }
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        return dateTimeUtils.isBefore(date, bean.getDataInizio(), true);
    }

    private FornitoriRetailEntity getFornitoreFromCodice(String codiceFornitore) {
        if (codiceFornitore == null) {
            return null;
        }
        return fornitoriRetail.stream()
                .filter(f -> codiceFornitore.equalsIgnoreCase(f.getCodice()))
                .findFirst().orElse(null);
    }
}
