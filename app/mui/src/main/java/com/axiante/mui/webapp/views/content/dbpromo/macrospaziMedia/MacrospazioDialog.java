package com.axiante.mui.webapp.views.content.dbpromo.macrospaziMedia;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.dto.EventoRetailDTO;
import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiEventoRetailService;
import com.axiante.mui.webapp.utils.MacrospaziMediaUtils;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.views.util.UiUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.SelectEvent;

import javax.faces.event.AjaxBehaviorEvent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class MacrospazioDialog {
    protected final MuiEventoRetailService eventoService;
    protected final FacesContextAware context;
    protected final MacrospaziMediaUtils utils;
    private static final String ADD_MACROSPAZIO_TITLE = "Nuovo Macrospazio Media";
    private static final String EDIT_MACROSPAZIO_TITLE = "Modifica Macrospazio Media";

    @Getter
    protected MuiMacrospazioMediaEntity bean;

    @Getter
    private boolean used = true;

    @Getter
    private boolean editMode = false;

    @Getter
    private String title = ADD_MACROSPAZIO_TITLE;

    public MacrospazioDialog(MuiMacrospazioMediaEntity bean, FacesContextAware context,
                             MuiEventoRetailService eventoService, MacrospaziMediaUtils utils, boolean editMode) {
        this.bean = bean;
        this.context = context;
        this.eventoService = eventoService;
        this.utils = utils;
        this.editMode = editMode;
        this.title = editMode ? EDIT_MACROSPAZIO_TITLE : ADD_MACROSPAZIO_TITLE;
        checkMacrospazioUsed();
    }

    protected void checkMacrospazioUsed() {
        if (bean == null || bean.getId() == null) {
            this.used = false;
        } else {
            this.used = eventoService.countByIdMacrospazio(bean.getId()) > 0;
        }
    }

    public void clearDataInizio() {
        if (bean != null) {
            bean.setDataInizio(null);
        }
    }

    public void clearDataFine() {
        if (bean != null) {
            bean.setDataFine(null);
        }
    }

    public boolean getAddMacrospazioBtnDisabled() {
        return bean == null || !utils.isValid(bean, editMode)
                || (editMode && bean.getDataInizio() != null && (inThePast(bean.getDataInizio()) || dataInizioAfterEventsDates(bean.getDataInizio())))
                || (editMode && bean.getDataFine() != null && dataFineBeforeEventsDates(bean.getDataFine()));
    }

    public boolean getDataInizioDisabled() {
        if (!editMode) {
            return false;
        }
        return bean == null || hasStartedEvents(bean);
    }

    private boolean hasStartedEvents(MuiMacrospazioMediaEntity macrospazio) {
        return eventoService.findAllByIdMacrospazio(macrospazio.getId()).stream()
                .anyMatch(e -> e.getDataInizio() != null && e.getDataInizio().before(new Date()));
    }

    private boolean dataFineBeforeEventsDates(Date dataFine) {
        Date maxDataFine = eventoService.findAllByIdMacrospazio(bean.getId()).stream()
                .map(EventoRetailDTO::getDataFine)
                .filter(Objects::nonNull)
                .max(Date::compareTo).orElse(null);
        return maxDataFine != null && (dataFine == null || dataFine.before(maxDataFine));
    }

    private boolean dataInizioAfterEventsDates(Date dataInizio) {
        Date minDataInizio = eventoService.findAllByIdMacrospazio(bean.getId()).stream()
                .map(EventoRetailDTO::getDataInizio)
                .filter(Objects::nonNull)
                .min(Date::compareTo).orElse(null);
        return minDataInizio != null && (dataInizio == null || dataInizio.after(minDataInizio));
    }

    public void onCodiceChange(AjaxBehaviorEvent event) {
        try {
            String codice = UiUtils.getEventStringValue(event);
            if (bean != null && codice != null) {
                bean.setCodice(codice.trim().toUpperCase());
            }
        } catch (Exception e) {
            log.error("Error getting codice macrospazio from event", e);
            context.addErrorMessage("Errre applicativo",
                    "Impossibile impostare il codice macrospazio, contattare l'assistenza");
        }
    }

    public void onDescrizioneChange(AjaxBehaviorEvent event) {
        try {
            String descrizione = UiUtils.getEventStringValue(event);
            if (bean != null && descrizione != null) {
                bean.setDescrizione(descrizione.trim().toUpperCase());
            }
        } catch (Exception e) {
            log.error("Error getting descrizione macrospazio from event", e);
            context.addErrorMessage("Errre applicativo",
                    "Impossibile impostare la descrizione macrospazio, contattare l'assistenza");
        }
    }

    public void onListinoChange(AjaxBehaviorEvent event) {
        try {
            BigDecimal value = UiUtils.getEventBigDecimalValue(event);
            if (bean != null && value != null) {
                bean.setListino(value);
            }
        } catch (ClassCastException e) {
            log.error("Invalid listino macrospazio value", e);
            context.addErrorMessage("Valore non valido", "Il listino deve essere un valore numerico");
        } catch (Exception e) {
            log.error("Error getting listino macrospazio from event", e);
            context.addErrorMessage("Errre applicativo",
                    "Impossibile impostare il listino macrospazio, contattare l'assistenza");
        }
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
        try {
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
        try{
            Date date = UiUtils.getEventDateValue(event);
            changeDataFine(date);
        } catch (Exception e) {
            log.error("Error getting data fine macrospazio from event", e);
            context.addErrorMessage("Errore applicativo",
                    "Impossibile impostare la data di fine macrospazio, contattare l'assistenza");
        }
    }

    protected void changeDataFine(Date date) {
        if (bean != null) {
            bean.setDataFine(date);
            DateTimeUtils dateTimeUtils = new DateTimeUtils();
            Date today = dateTimeUtils.getDateWithoutTime(new Date());
            if (date != null && dateTimeUtils.isBefore(date, today, false)) {
                context.addErrorMessage("Attenzione",
                        "Data fine non può essere nel passato");
            }
            if (date != null && bean.getDataInizio() != null && date.before(bean.getDataInizio())) {
                context.addErrorMessage("Attenzione",
                        "Data fine non può essere minore della data inizio");
            }
            if (editMode && date != null && dataFineBeforeEventsDates(date)) {
                context.addErrorMessage("Attenzione",
                        "Data fine non può essere minore della massima data fine impostata sugli eventi associati");
            }
        }
    }

    protected void changeDataInizio(Date date) {
        if (bean != null) {
            bean.setDataInizio(date);
            if (date != null) {
                if (bean.getDataFine() != null && date.after(bean.getDataFine())) {
                    context.addErrorMessage("Attenzione",
                            "Data inizio non può essere maggiore della data fine");
                }
                DateTimeUtils dateTimeUtils = new DateTimeUtils();
                Date today = dateTimeUtils.getDateWithoutTime(new Date());
                if (dateTimeUtils.isBefore(date, today, false)) {
                    context.addErrorMessage("Attenzione",
                            "Data inizio non può essere nel passato");
                }
                if (dataInizioAfterEventsDates(date)) {
                    context.addErrorMessage("Attenzione",
                            "Data inizio non può essere maggiore della minima data inizio impostata sugli eventi associati");
                }
            }
        }
    }

    private boolean inThePast(Date date) {
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        Date today = dateTimeUtils.getDateWithoutTime(new Date());
        return dateTimeUtils.isBefore(date, today, true);
    }
}
