package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.webapp.views.FacesContextAware;
import java.util.Map;
import javax.faces.event.ActionEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlanoRiferimentoBackingBean implements FacesContextAware {

    @Getter
    private String sourcePlanoSelected = null;

    @Getter
    private String dlgConfirmMessage;

    public void preparePlanoDialog(ActionEvent e) {
        sourcePlanoSelected = null;
    }

    public void selezionaPlano(ActionEvent e ) {
        Map<String, String[]> paramValues = getExternalContext().getRequestParameterValuesMap();
        String[] ss = paramValues.get("sourcePlanoSelected");
        if ( (ss != null) && (ss.length > 0)) {
            sourcePlanoSelected = ss[0];
        } else {
            sourcePlanoSelected = null;
            dlgConfirmMessage = "Nessuna promozione selezionata";
        }
    }

    public void confirmCaricaDaPlano(ActionEvent e) {
        if ( sourcePlanoSelected != null ) {
            dlgConfirmMessage = String.format("Sei sicuro di voler caricare il planogramma di riferimento %s ?",
                    sourcePlanoSelected);
        }
    }
}
