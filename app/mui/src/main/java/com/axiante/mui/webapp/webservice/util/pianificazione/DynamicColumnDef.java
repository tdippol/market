package com.axiante.mui.webapp.webservice.util.pianificazione;

import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;

public interface DynamicColumnDef {
	default String generateColumnDefByPromoConfiguration(PromozioneTestataEntity promozioneTestataEntity, String hiddenColumns,
                                                 String grid, String contesto) {
		return generateColumnDefByPromoConfiguration(promozioneTestataEntity, hiddenColumns, grid, contesto, false);
	}

	String generateColumnDefByPromoConfiguration(PromozioneTestataEntity promozioneTestataEntity, String hiddenColumns,
                                                 String grid, String contesto, boolean contestoRequired);

	default String generateRowDataByPromoConfiguration(String buyerId, String operationMessage) {
		return generateRowDataByPromoConfiguration(buyerId, null, null, operationMessage);
	}

	String generateRowDataByPromoConfiguration(String buyerId, Boolean isUserAdmin, List<String> codiciGruppo,
											   String operationMessage);

	ObjectNode generateRowDataByPromoPianificazioneMaster(PromozionePianificazioneEntity pianificazione);

	ObjectNode generateRowDataByPromoConfiguration(PromozioneTestataEntity testata, Long idMeccanica,
			PlanningArticleMultiFilterParam params, Boolean isUserAdmin, List<String> codiciGruppo);

	String generateRowDataFilteredByPromoConfiguration(String promoId, String radioChecked);

	default ObjectNode generateRowDataByPromoElementMechanic(PromozioneTestataEntity testata, Long idMeccanica, String buyerId,
			String operationMessage) {
		return generateRowDataByPromoElementMechanic(testata, idMeccanica, buyerId, null, null, operationMessage);
	}

	ObjectNode generateRowDataByPromoElementMechanic(PromozioneTestataEntity testata, Long idMeccanica, String buyerId,
			Boolean isUserAdmin, List<String> codiciGruppo, String operationMessage);
}
