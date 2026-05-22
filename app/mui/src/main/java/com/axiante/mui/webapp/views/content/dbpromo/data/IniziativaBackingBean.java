package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaStatoEntity;
import com.axiante.mui.dbpromo.persistence.service.IniziativeService;
import com.axiante.mui.webapp.views.FacesContextAware;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.event.ActionEvent;
import java.util.Map;

@Slf4j
public class IniziativaBackingBean implements FacesContextAware {

    @Getter
    private IniziativaEntity iniziativa;

    @Getter
    private String statoIniziativa;

    @Getter
    @Setter
    private IniziativaEntity iniziativaSelected;

    @Getter
    @Setter
    private String dataInizioPromo;

    @Getter
    @Setter
    private String dataFinePromo;

    @Getter
    private String dlgConfirmMessage;

    private String dlgRemoveRifMessage;

    private IniziativeService iniziativeService;

    public IniziativaBackingBean(IniziativeService iniziativeService) {
        this.iniziativeService = iniziativeService;
    }

    public void prepareIniziativaDialog() {
        iniziativaSelected = null;
    }

    public void selezionaIniziativa(ActionEvent e) {
        Map<String, String[]> paramValues = getExternalContext().getRequestParameterValuesMap();
        String[] ss = paramValues.get("iniziativaSelected");
        if ( (ss != null) && (ss.length > 0)) {
            try {
                iniziativaSelected = iniziativeService.findById(Long.parseLong(ss[0]));
            } catch (Exception ex) {
                log.error(String.format("Error getting iniziativa with id %s", ss[0]), ex);
                iniziativaSelected = null;
            }
        } else {
            iniziativaSelected = null;
            dlgConfirmMessage = "Nessuna promozione selezionata";
        }
    }

    public void confirmScegliIniziativa(ActionEvent e) {
        if (iniziativaSelected != null) {
            dlgConfirmMessage = String.format("Sei sicuro di voler caricare l'iniziativa %s ?",
                    iniziativaSelected.getDescrizione());
        }
    }

    public void setIniziativa(IniziativaEntity iniziativa) {
        this.iniziativa = iniziativa;
        if (iniziativa != null) {
            iniziativa.getStati().stream()
                    .filter(s -> s.getDataFineStato() == null).findFirst()
                    .map(IniziativaStatoEntity::getStatoPromo)
                    .ifPresent(s -> statoIniziativa = String.format("%s - %s", s.getCodiceStato(), s.getDescrizione()));
        }
    }

    public String getDlgRemoveRifMessage() {
        prepareRemoveRifMessage();
        return dlgRemoveRifMessage;
    }

    public void prepareRemoveRifMessage() {
        if (iniziativa != null) {
            dlgRemoveRifMessage = String.format(
                    "Sei sicuro di rimuovere l'associazione con l'iniziativa '%s' ?",
                    iniziativa.getDescrizione());
        } else {
            dlgRemoveRifMessage = "Errore di caricamento";
        }
    }

    public String getIniziativaFullDescription() {
        return iniziativa != null ? String.format("%d - %s", iniziativa.getId(), iniziativa.getDescrizione()) : "";
    }
}
