package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.webapp.views.FacesContextAware;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Slf4j
public class ClonaPromozioneBean implements FacesContextAware {
    @Getter
    @Setter
    private Date dataInizio;

    @Getter
    @Setter
    private Date dataFine;

    @Getter
    @Setter
    private boolean confirmBtnDisabled = false;

    private DateTimeUtils dtUtils = new DateTimeUtils();
    private PromoService promoService;
    private String user;
    private Long currentIdMenu;
    private Long idPromozioneSorgente;

    public ClonaPromozioneBean(PromoService promoService, @NonNull final String user, Long currentIdMenu) {
        this.promoService = promoService;
        this.user = user;
        this.currentIdMenu = currentIdMenu;
    }

    public void prepare(PromozioneTestataEntity promozioneSorgente) {
        if (promozioneSorgente.getDataFine() == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Promozione sorgente non ha data fine impostata; impossibile proseguire"));
            return;
        }
        idPromozioneSorgente = promozioneSorgente.getId();
        int yearSorgente = promozioneSorgente.getDataFine().toInstant().atZone(ZoneId.systemDefault()).getYear();
        dataInizio = new GregorianCalendar(yearSorgente + 1, Calendar.JANUARY, 1).getTime();
        dataFine = new GregorianCalendar(yearSorgente + 1, Calendar.DECEMBER, 31).getTime();
        handleConfirmButton();
    }

    public void onDataInizioSelect(SelectEvent event) {
        if (dataFine != null && dtUtils.isBefore(dataFine, dataInizio, false)) {
            dataFine = null;
        }
        handleConfirmButton();
    }

    public void onDataFineSelect(SelectEvent event) {
        if (dataInizio != null && dtUtils.isAfter(dataInizio, dataFine, false)) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione",
                    "La data fine non puo' essere antecedente alla data inizio"));
        }
        handleConfirmButton();
    }

    public void confirm() {
        if (!isValid()) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile avviare la procedura di copia; controllare i valori inseriti"));
            return;
        }
        try {
            Long idPromoClonata = promoService.copiaPromozione("APP", user, idPromozioneSorgente, dataInizio, dataFine);
            if (idPromoClonata == null) {
                throw new Exception("Errore durante la copia della promozione");
            }
            if (currentIdMenu != null && currentIdMenu > 0) {
                String js = String.format("goToCopiedPromo([{name: 'menuId', value: '%s'}, {name: 'currentPromoId', value: '%d'}]);",
                        currentIdMenu, idPromoClonata);
                PrimeFaces.current().executeScript(js);
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attenzione",
                        "Impossibile eseguire redirect alla Promozione copiata"));
            }
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
                    "Promozione copiata con successo"));
        } catch (Exception ex) {
            log.error("Error cloning promotion", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore durante la copia della promozione; contattare l'assistenza tecnica"));
        }
    }

    private void handleConfirmButton() {
        confirmBtnDisabled = !isValid();
    }

    private boolean isValid() {
        return idPromozioneSorgente != null && dataInizio != null && dataFine != null
                && dtUtils.isAfter(dataFine, dataInizio, false);
    }
}
