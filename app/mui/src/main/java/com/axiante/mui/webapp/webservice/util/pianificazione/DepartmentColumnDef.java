package com.axiante.mui.webapp.webservice.util.pianificazione;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.webapp.utils.PianificazioneSecurityUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PianificazioneHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;

@Slf4j
@Dependent
public class DepartmentColumnDef extends AbstractColumnDef implements DynamicColumnDef {

	@Inject
	private Instance<PianificazioneService> pianificazioneServiceInstance;

	@Inject
	private Instance<CfgConfHeaderService> confHeaderServiceInstance;

	@Inject
	private Instance<PianificazioneHelper> pianificazioneHelperInstance;

	@Inject
	private Instance<PianificazioneSecurityUtil> securityUtilInstance;

	@Override
	public String generateColumnDefByPromoConfiguration(final PromozioneTestataEntity promozioneTestataEntity,
			String hiddenColumns, String grid, String contesto, boolean contestoRequired) {
		return loadColumnDefFromFile("pianificazione_selezione_reparto_columnDef.json", hiddenColumns, grid,
				contesto, contestoRequired);
	}

	@Override
	public String generateRowDataByPromoConfiguration(final String buyerId, Boolean isUserAdmin, List<String> codiciGruppo, final String operationMessage) {

		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";

		// nel caso di REPARTO: se presente, il parametro Compratore viene ignorato e
		// viene ritornata la lista dei Reparti disponibili (MUI_REPARTO);
		List<RepartoEntity> repartoEntities = pianificazioneServiceInstance.get().getAllReparti();

		repartoEntities.stream().forEach(repartoEntity -> arrayNode.add(mapRepartoEntityToObjectNode(repartoEntity)));

		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing pianificazione dialog selezione reparto JSON row data", e);
		}

		return json;
	}

	@Override
	public ObjectNode generateRowDataByPromoPianificazioneMaster(PromozionePianificazioneEntity pianificazione) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();

		// nel caso di REPARTO: se presente, il parametro Compratore è ignorato e
		// è ritornata la lista dei Reparti disponibili (MUI_REPARTO);
		List<RepartoEntity> repartoEntities = pianificazioneServiceInstance.get().getAllReparti();

		// Filtro elimina gli elementi già presenti in visualizzazione per tutte le
		// pianificazioni della promozione
		List<Long> usedItems = pianificazione.getPromozioneTestataEntity().getPromozionePianificazioneEntities()
				.stream()
				.filter(entity -> pianificazione.getMeccanicaEntity().getId().equals(entity.getMeccanicaEntity().getId())
						&& entity.getParent() != null
						&& ElementType.REPARTO.getDescription().equalsIgnoreCase(entity.getTipoElemento()))
				.map(detail -> Long.parseLong(detail.getCodiceElemento())).collect(Collectors.toList());

		repartoEntities.stream().filter(repartoEntity -> usedItems.contains(repartoEntity.getId()))
				.collect(Collectors.toList()).stream().forEach(entity -> repartoEntities.remove(entity));

		repartoEntities.stream().forEach(repartoEntity -> arrayNode.add(mapRepartoEntityToObjectNode(repartoEntity)));

		ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
		objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);

		return objectNode;
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

	private ObjectNode mapRepartoEntityToObjectNode(RepartoEntity reparto) {
		return mapRepartoEntityToObjectNode(reparto, null, null);
	}

	private ObjectNode mapRepartoEntityToObjectNode(RepartoEntity reparto, List<Long> usedItems, Boolean duplicaReparto) {
		final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();

		DBPromoAgCell cell = DBPromoAgCell.builder().name("Reparto").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(reparto.getCodiceReparto() == null ? ""
						: String.format("[%s] %s", reparto.getCodiceReparto(),
								reparto.getDescrizione() != null ? reparto.getDescrizione().toUpperCase()
										: ""))
				.build();
		map.put("reparto", cell);

		cell = DBPromoAgCell.builder().name("id").editable(false)
				.value(String.valueOf(reparto.getId())).build();
		map.put("id", cell);

		cell = DBPromoAgCell.builder().name("elemento").editable(false)
				.value(reparto.getCodiceReparto() == null || reparto.getDescrizione() == null ? ""
						: String.format("%s - %s", reparto.getCodiceReparto(), reparto.getDescrizione()))
				.build();
		map.put("elemento", cell);

		final boolean isUsed = duplicaReparto != null && duplicaReparto
				&& usedItems != null && usedItems.contains(reparto.getId());
		if (isUsed) {
			map.values().forEach(c -> c.setWarning(true));
		}

		return JsonUtils.getMapper().valueToTree(map);
	}

	@Override
	public ObjectNode generateRowDataByPromoElementMechanic(@NonNull final PromozioneTestataEntity testata,
			@NonNull final Long idMeccanica, final String buyerId, Boolean isUserAdmin, List<String> codiciGruppo,
			final String operationMessage) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		final CfgConfHeaderEntity confHeader = confHeaderServiceInstance.get()
				.findByMeccanicaIdAndSetPianificazioneId(idMeccanica, testata.getMuiCfgSetPianificazione().getId());
		if (confHeader != null) {
			Stream<RepartoEntity> stream = pianificazioneServiceInstance.get().getAllReparti().stream();
			// #3938 Keep track of used items to add a yellow background to permitted duplicated items
			final List<Long> usedItems = new ArrayList<>();

			// #4182: gestione flag duplica reparto per canale
			final Boolean duplicaRepartoCanale = testata.getMuiCanalePromozione().getDuplicaReparto();
			if (!Boolean.TRUE.equals(duplicaRepartoCanale)) {
				// Scarto elementi duplicati su tutta la pianificazione
				usedItems.addAll(pianificazioneHelperInstance.get()
						.getUsedItems(testata, PromoPianificazioneEnum.REPARTO.getTipoElemento()));
				stream = stream.filter(i -> !usedItems.contains(i.getId()));
			}

			final Boolean duplicaReparto = confHeader.getDuplicaReparto();
			// #3938 Keep track of used items to add a yellow background to permitted duplicated items
			usedItems.addAll(pianificazioneHelperInstance.get().getUsedItems(testata, idMeccanica,
					confHeader.getLivelloPianificazione().getCodice(), PromoPianificazioneEnum.REPARTO.getTipoElemento()));
			if (duplicaReparto == null || !duplicaReparto) {
				// Scarto elementi duplicati
				stream = stream.filter(r -> !usedItems.contains(r.getId()));
			}
			if (isUserAdmin != null && codiciGruppo != null && !isUserAdmin) {
				final List<String> repartoCodes = securityUtilInstance.get().getWritableReparti(testata, codiciGruppo);
				stream = stream.filter(e -> repartoCodes.contains(e.getCodiceReparto()));
			}
			stream.forEach(r -> arrayNode.add(mapRepartoEntityToObjectNode(r, usedItems, duplicaReparto)));
		} else {
			log.error(String.format("Error getting confHeader from meccanica %d and cfgSetPianificazione %d",
					idMeccanica, testata.getMuiCfgSetPianificazione().getId()));
		}
		ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
		objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
		return objectNode;
	}
}
