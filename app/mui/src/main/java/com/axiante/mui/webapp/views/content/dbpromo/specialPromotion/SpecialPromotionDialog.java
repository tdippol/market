package com.axiante.mui.webapp.views.content.dbpromo.specialPromotion;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpTestataEntity;
import com.axiante.mui.webapp.views.util.UiUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.SelectEvent;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Dependent
@Getter
@Setter
@Slf4j
public class SpecialPromotionDialog implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int REMINDER_MIN_DAYS_FROM_START = 12;

    private MuiSpTestataEntity bean;

    public void initForCreate() {
        bean = new MuiSpTestataEntity();
        bean.setAttiva(1);
    }

    public void onDataInizioSelect(SelectEvent event) {
        try {
            Date date = (Date) event.getObject();
            changeDataInizio(date);
        } catch (Exception e) {
            log.error("Error in onDataInizioSelect", e);
            addGlobalError("Errore applicativo", "Impossibile impostare la data di inizio.");
        }
    }

    public void onDataInizioChange(AjaxBehaviorEvent event) {
        try {
            Date date = UiUtils.getEventDateValue(event);
            changeDataInizio(date);
        } catch (Exception e) {
            log.error("Error in onDataInizioChange", e);
            addGlobalError("Errore applicativo", "Impossibile impostare la data di inizio.");
        }
    }

    public void onDataFineSelect(SelectEvent event) {
        try {
            Date date = (Date) event.getObject();
            changeDataFine(date);
        } catch (Exception e) {
            log.error("Error in onDataFineSelect", e);
            addGlobalError("Errore applicativo", "Impossibile impostare la data di fine.");
        }
    }

    public void onDataFineChange(AjaxBehaviorEvent event) {
        try {
            Date date = UiUtils.getEventDateValue(event);
            changeDataFine(date);
        } catch (Exception e) {
            log.error("Error in onDataFineChange", e);
            addGlobalError("Errore applicativo", "Impossibile impostare la data di fine.");
        }
    }

    protected void changeDataInizio(Date date) {
        if (bean == null) {
            return;
        }

        bean.setDataInizio(date);

        if (date == null) {
            bean.setDataReminderInsParam(null);
            return;
        }

        bean.setDataReminderInsParam(addDays(date, REMINDER_MIN_DAYS_FROM_START));

        if (bean.getDataFine() != null && date.after(bean.getDataFine())) {
            addGlobalError("Attenzione", "La data fine non può essere precedente alla data inizio.");
        }
    }

    protected void changeDataFine(Date date) {
        if (bean == null) {
            return;
        }

        bean.setDataFine(date);

        if (date != null && bean.getDataInizio() != null && date.before(bean.getDataInizio())) {
            addGlobalError("Attenzione", "La data fine non può essere precedente alla data inizio.");
        }

        if (date != null && bean.getDataReminderInsParam() != null && bean.getDataReminderInsParam().after(date)) {
            addGlobalError("Attenzione", "La data reminder non può essere successiva alla data fine.");
        }
    }

    public boolean validateForSave() {
        log.info("SP dialog - ENTRO in validateForSave");

        boolean valid = true;

        if (bean == null) {
            addGlobalError("Attenzione", "Dati promozione non inizializzati.");
            markValidationFailed();
            return false;
        }

        if (isBlank(bean.getDescrizione())) {
            addGlobalError("Attenzione", "La descrizione è obbligatoria.");
            valid = false;
        }

        if (bean.getDataInizio() == null) {
            addGlobalError("Attenzione", "La data inizio è obbligatoria.");
            valid = false;
        }

        if (bean.getDataFine() == null) {
            addGlobalError("Attenzione", "La data fine è obbligatoria.");
            valid = false;
        }

        if (bean.getDataReminderInsParam() == null) {
            addGlobalError("Attenzione", "La data reminder è obbligatoria.");
            valid = false;
        }

        if (bean.getDataInizio() != null && bean.getDataFine() != null
                && bean.getDataFine().before(bean.getDataInizio())) {
            addGlobalError("Attenzione", "La data fine non può essere precedente alla data inizio.");
            valid = false;
        }

        if (bean.getDataInizio() != null && bean.getDataReminderInsParam() != null) {
            Date minReminderDate = addDays(bean.getDataInizio(), REMINDER_MIN_DAYS_FROM_START);
            if (bean.getDataReminderInsParam().before(minReminderDate)) {
                addGlobalError("Attenzione", "La data reminder deve essere successiva di 12 giorni alla data inizio.");
                valid = false;
            }
        }

        if (bean.getDataFine() != null && bean.getDataReminderInsParam() != null
                && bean.getDataReminderInsParam().after(bean.getDataFine())) {
            addGlobalError("Attenzione", "La data reminder non può essere successiva alla data fine.");
            valid = false;
        }

        if (!valid) {
            markValidationFailed();
        }

        return valid;
    }

    private void addGlobalError(String summary, String message) {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, message)
        );
    }

    private void markValidationFailed() {
        FacesContext.getCurrentInstance().validationFailed();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }


    public Date getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}