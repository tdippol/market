package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.persistence.entities.MuiPromoDbPromoEntity;
import com.axiante.mui.webapp.views.FacesContextAware;
import java.util.Map;
import javax.faces.event.ActionEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PromozioneRiferimentoBackingBean implements FacesContextAware {

    @Getter
    private String sourcePromoSelected = null;

    @Getter
    private String dlgConfirmMessage;

	private String dlgRemoveRifMessage;

    @Getter
    @Setter
    private MuiPromoDbPromoEntity promoRiferimento;

    public void preparePromoDialog(ActionEvent e) {
        sourcePromoSelected = null;
    }

    public void selezionaPromo(ActionEvent e ) {
        Map<String, String[]> paramValues = getExternalContext().getRequestParameterValuesMap();
        String[] ss = paramValues.get("sourcePromoSelected");
        if ( (ss != null) && (ss.length > 0)) {
            sourcePromoSelected = ss[0];
        } else {
            sourcePromoSelected = null;
            dlgConfirmMessage = "Nessuna promozione selezionata";
        }
    }

    public void confirmCaricaDaPromo(ActionEvent e) {
        if ( sourcePromoSelected != null ) {
            dlgConfirmMessage = String.format("Sei sicuro di voler caricare la promozione di riferimento %s ?",
                    sourcePromoSelected);
        }
    }

	public void prepareRemoveRifMessage() {
		if (promoRiferimento != null) {
			dlgRemoveRifMessage = String.format(
					"Sei sicuro di rimuovere l'associazione con la promozione di riferimento '%s' ?",
					promoRiferimento.getDescrizioneEstesa());
		} else {
			dlgRemoveRifMessage = "Errore di caricamento";

		}
	}

	public String getDlgRemoveRifMessage() {
		prepareRemoveRifMessage();
		return dlgRemoveRifMessage;
	}
}
