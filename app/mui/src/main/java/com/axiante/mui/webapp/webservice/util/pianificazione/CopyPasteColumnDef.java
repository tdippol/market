package com.axiante.mui.webapp.webservice.util.pianificazione;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class CopyPasteColumnDef extends AbstractColumnDef implements DynamicColumnDef {

	@Override
	public String generateColumnDefByPromoConfiguration(final PromozioneTestataEntity promozioneTestataEntity,
			String hiddenColumns, String grid, String contesto, boolean contestoRequired) {
		return loadColumnDefFromFile("pianificazione_copia_incolla_columnDef.json", hiddenColumns, grid,
				contesto, contestoRequired);
	}

	@Override
	public String generateRowDataByPromoConfiguration(String buyerId, Boolean isUserAdmin, List<String> codiciGruppo, String operationMessage) {

		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";

		for (int i = 0; i < PianificazioneConstants.COPY_PASTE_ROWS; i++) {
			arrayNode.add(createObjectNode());
		}

		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing copiaincolla dialog selezione grm JSON row data", e);
		}

		return json;
	}

	@Override
	public ObjectNode generateRowDataByPromoPianificazioneMaster(PromozionePianificazioneEntity pianificazione) {
		return null;
	}

	@Override
	public ObjectNode generateRowDataByPromoConfiguration(PromozioneTestataEntity testata, Long idMeccanica,
			PlanningArticleMultiFilterParam params, Boolean isUserAdmin, List<String> codiciGruppo) {
		return null;
	}

	@Override
	public String generateRowDataFilteredByPromoConfiguration(String promoId, String radioChecked) {
		return null;
	}

	@Override
	public ObjectNode generateRowDataByPromoElementMechanic(PromozioneTestataEntity testata, Long idMeccanica,
			String buyerId, Boolean isUserAdmin, List<String> codiciGruppo, String operationMessage) {
		return null;
	}

	private ObjectNode createObjectNode() {

		final Map<String, DBPromoAgCell> dbPromoAgCellRowMap = new HashMap<>();

		dbPromoAgCellRowMap.put("codice", DBPromoAgCell.builder().name("Codice").editable(true)
				.type(DBPromoCellTypeEnum.STRING.getType()).value("").build());

		dbPromoAgCellRowMap.put("descrizione", DBPromoAgCell.builder().name("Descrizione").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value("").build());

		ObjectNode objectNode = JsonUtils.getMapper().valueToTree(dbPromoAgCellRowMap);

		return objectNode;
	}
}
