package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.dbpromo.persistence.entities.*;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.VisualizzaCfgPianificazioneCampiHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PlanningCommons;
import com.fasterxml.jackson.databind.JsonNode;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class PianificazioneCampiUtil implements FacesContextAware {

	@Inject
	private VisualizzaCfgPianificazioneCampiHelper visualizzaCfgPianificazioneCampiHelper;
	
	@Inject
	private PlanningCommons planningCommons;
	/**
	 * Questo metodo ripristina il valore persistito in caso di validazione fallita
	 *
	 * @param node
	 * @param column
	 * @param pianificazionePromozione
	 */
	@SuppressWarnings("null")
	public void resetPersistedValue(JsonNode node, String column,
			CfgPianificazioneCampiEntity pianificazioneCampo ,Object valueToBeUpdated ) {
		Object value = visualizzaCfgPianificazioneCampiHelper.invokeGetterEntity(column, pianificazioneCampo);
		
		final String cellType =getCellTypeForField(pianificazioneCampo, column);
		final String valueAsString = planningCommons.toValue(value, cellType);
		DbPromoAgCellUtils.putValue(node, column, valueAsString, true);
	}
	
	private String getCellTypeForField(@NonNull CfgPianificazioneCampiEntity pianificazioneCampo, @NonNull String column) {
	 String type = null;
		switch (column) {
		case "descrizione":
			type = "String";
		case "descrizioneEstesa":
			type =  "String";
		case "columnWidth":
			type =  "Integer";
		}
		return type;
	}

	
}
