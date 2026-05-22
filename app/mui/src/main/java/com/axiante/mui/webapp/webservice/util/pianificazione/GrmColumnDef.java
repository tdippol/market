package com.axiante.mui.webapp.webservice.util.pianificazione;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
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
public class GrmColumnDef extends AbstractColumnDef implements DynamicColumnDef {

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
		return loadColumnDefFromFile("pianificazione_selezione_grm_columnDef.json", hiddenColumns, grid,
				contesto, contestoRequired);
	}

	@Override
	public String generateRowDataByPromoConfiguration(final String buyerId, Boolean isUserAdmin, List<String> codiciGruppo,
													  final String operationMessage) {

		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";

		// nel caso di GRM: se presente, il parametro Compratore viene ignorato e viene
		// ritornata la lista dei GRM disponibili (MUI_GRM);
		List<GrmEntity> grmEntities = pianificazioneServiceInstance.get().getAllGrmEntity();

		grmEntities.stream().forEach(grmEntity -> arrayNode.add(mapGrmEntityToObjectNode(grmEntity)));

		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing pianificazione dialog selezione grm JSON row data", e);
		}

		return json;
	}

	@Override
	public ObjectNode generateRowDataByPromoPianificazioneMaster(PromozionePianificazioneEntity pianificazione) {

		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();

		// nel caso di GRM: se presente, il parametro Compratore viene ignorato e viene
		// ritornata la lista dei GRM disponibili (MUI_GRM);
		List<GrmEntity> grmEntities = pianificazioneServiceInstance.get().getAllGrmEntity();

		// Filtro elimina gli elementi già presenti in visualizzazione per tutte le
		// pianificazioni della promozione
		List<Long> usedItems = pianificazione.getPromozioneTestataEntity().getPromozionePianificazioneEntities()
				.stream()
				.filter(entity -> pianificazione.getMeccanicaEntity().getId().equals(entity.getMeccanicaEntity().getId())
						&& entity.getParent() != null
						&& ElementType.GRM.getDescription().equalsIgnoreCase(entity.getTipoElemento()))
				.map(detail -> Long.parseLong(detail.getCodiceElemento()))
				.collect(Collectors.toList());

		grmEntities.stream().filter(grmEntity -> usedItems.contains(grmEntity.getId())).collect(Collectors.toList())
				.stream().forEach(entity -> grmEntities.remove(entity));

		grmEntities.stream().forEach(grmEntity -> arrayNode.add(mapGrmEntityToObjectNode(grmEntity)));

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

	private ObjectNode mapGrmEntityToObjectNode(GrmEntity grm, List<Long> usedItems, Boolean duplicaGrm) {
		final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();
//		final boolean isUsed = duplicaGrm != null && duplicaGrm
//				&& usedItems != null && usedItems.contains(grm.getId());

		DBPromoAgCell cell = DBPromoAgCell.builder().name("Grm").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(grm.getCodiceGrm() == null ? ""
						: String.format("[GRM_%s] %s", grm.getCodiceGrm(),
						grm.getDescrizione() != null ? grm.getDescrizione().toUpperCase() : ""))
				.build();
		map.put("grm", cell);

		String categoria = "";
		if (grm.getMuiCategoria() != null && grm.getMuiCategoria() != null) {
			categoria = String.format("[%s] %s", grm.getMuiCategoria().getCodiceCategoria(),
					grm.getMuiCategoria().getDescrizione() != null
							? grm.getMuiCategoria().getDescrizione().toUpperCase()
							: "");
		}
		cell = DBPromoAgCell.builder().name("Categoria").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(categoria).build();
		map.put("categoria", cell);

		String reparto = "";
		if (!categoria.isEmpty() && grm.getMuiCategoria().getRepartoEntity() != null) {
			reparto = String.format("[%s] %s", grm.getMuiCategoria().getRepartoEntity().getCodiceReparto(),
					grm.getMuiCategoria().getRepartoEntity().getDescrizione() != null
							? grm.getMuiCategoria().getRepartoEntity().getDescrizione().toUpperCase()
							: "");
		}
		cell = DBPromoAgCell.builder().name("Reparto").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(reparto).build();
		map.put("reparto", cell);

		cell = DBPromoAgCell.builder().name("id").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
				.value(String.valueOf(grm.getId())).build();
		map.put("id", cell);

		cell = DBPromoAgCell.builder().name("elemento").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
				.value(grm.getCodiceGrm() == null || grm.getDescrizione() == null ? ""
						: String.format("%s - %s", grm.getCodiceGrm(), grm.getDescrizione()))
				.build();
		map.put("elemento", cell);

		final boolean isUsed = duplicaGrm != null && duplicaGrm
				&& usedItems != null && usedItems.contains(grm.getId());
		if (isUsed) {
			map.values().forEach(c -> c.setWarning(true));
		}

		return JsonUtils.getMapper().valueToTree(map);
	}

	private ObjectNode mapGrmEntityToObjectNode(GrmEntity grm) {
		return mapGrmEntityToObjectNode(grm, null, null);
	}

	@Override
	public ObjectNode generateRowDataByPromoElementMechanic(@NonNull final PromozioneTestataEntity testata,
			@NonNull final Long idMeccanica, final String buyerId, Boolean isUserAdmin, List<String> codiciGruppo,
			final String operationMessage) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		final CfgConfHeaderEntity confHeader = confHeaderServiceInstance.get()
				.findByMeccanicaIdAndSetPianificazioneId(idMeccanica, testata.getMuiCfgSetPianificazione().getId());
		if (confHeader != null) {
			Stream<GrmEntity> stream = pianificazioneServiceInstance.get().getAllGrmEntity().stream();
			// #3938 Keep track of used items to add a yellow background to permitted duplicated items
			final List<Long> usedItems = new ArrayList<>();

			// #4182: gestione flag duplica grm per canale
			final Boolean duplicaGrmCanale = testata.getMuiCanalePromozione().getDuplicaGrm();
			if (!Boolean.TRUE.equals(duplicaGrmCanale)) {
				// Scarto elementi duplicati su tutta la pianificazione
				usedItems.addAll(pianificazioneHelperInstance.get()
						.getUsedItems(testata, PromoPianificazioneEnum.GRM.getTipoElemento()));
				stream = stream.filter(i -> !usedItems.contains(i.getId()));
			}

			final Boolean duplicaGrm = confHeader.getDuplicaGrm();
			usedItems.addAll(pianificazioneHelperInstance.get().getUsedItems(testata, idMeccanica,
					confHeader.getLivelloPianificazione().getCodice(), PromoPianificazioneEnum.GRM.getTipoElemento()));
			if (duplicaGrm == null || !duplicaGrm) {
				// Scarto elementi duplicati
				stream = stream.filter(g -> !usedItems.contains(g.getId()));
			}
			if (isUserAdmin != null && codiciGruppo != null && !isUserAdmin) {
				final List<String> grmCodes = securityUtilInstance.get().getWritableGrm(testata, codiciGruppo);
				stream = stream.filter(e -> grmCodes.contains(e.getCodiceGrm()));
			}
			stream.forEach(g -> arrayNode.add(mapGrmEntityToObjectNode(g, usedItems, duplicaGrm)));
		} else {
			log.error(String.format("Error getting confHeader from meccanica %d and cfgSetPianificazione %d",
					idMeccanica, testata.getMuiCfgSetPianificazione().getId()));
		}
		ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
		objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
		return objectNode;
	}
}
