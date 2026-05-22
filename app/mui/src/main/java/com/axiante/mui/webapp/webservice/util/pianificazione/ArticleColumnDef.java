package com.axiante.mui.webapp.webservice.util.pianificazione;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.persistence.entities.AssortimentoFornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MarchioPrivatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.MarchioPrivatoService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.webapp.utils.PianificazioneSecurityUtil;
import com.axiante.mui.webapp.webservice.pojo.ItemHierarchy;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PianificazioneHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Dependent
public class ArticleColumnDef extends AbstractColumnDef implements DynamicColumnDef {

	@Inject
	private PianificazioneService pianificazioneService;

	@Inject
	private Instance<CfgConfHeaderService> confHeaderServiceInstance;

	@Inject
	private Instance<PianificazioneHelper> pianificazioneHelperInstance;

	@Inject
	private Instance<PianificazioneSecurityUtil> securityUtilInstance;

	@Inject
	private Instance<MarchioPrivatoService> marchioPrivatoServiceInstance;

	@Override
	public String generateColumnDefByPromoConfiguration(final PromozioneTestataEntity promozioneTestataEntity,
														String hiddenColumns, String grid, String contesto, boolean contestoRequired) {
		return loadColumnDefFromFile("pianificazione_selezione_articolo_columnDef.json", hiddenColumns, grid,
				contesto, contestoRequired);
	}

	@Override
	public String generateRowDataByPromoConfiguration(final @NonNull String buyerId, Boolean isUserAdmin,
													  List<String> codiciGruppo, final String operationMessage) {

		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";

		CompratoreEntity compratoreEntity = null;
		Long compratoreid = null;

		if (buyerId != null && !buyerId.isEmpty() && StringUtils.isNumeric(buyerId)) {
			compratoreid = Long.parseLong(buyerId);
		}

		if (compratoreid != null) {
			compratoreEntity = pianificazioneService.findCompratoreById(compratoreid);
		}

		// nel caso di ARTICOLO: il parametro Compratore e' obbligatorio, se non
		// presente il rowData e' vuoto;
		// altrimenti ritorna la lista degli articoli (MUI_ITEM) associati al compratore
		// selezionato(MUI_COMPRATORE);
		if (compratoreEntity != null) {
			List<ItemEntity> itemEntities = pianificazioneService.getAllItemsByCompratore(compratoreEntity);
			itemEntities.stream().forEach(itemEntity -> mapItemEntityByFornitore(itemEntity, arrayNode));
		}

		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing pianificazione dialog selezione articolo JSON row data", e);
		}

		return json;
	}

	/**
	 * Il rowData per gli articoli è definito in funzione dei filtri selezionati
	 *
	 * @param pianificazione
	 * @return
	 */
	@Override
	public ObjectNode generateRowDataByPromoPianificazioneMaster(PromozionePianificazioneEntity pianificazione) {

		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();

		ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
		objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);

		return objectNode;
	}

	@Override
	public ObjectNode generateRowDataByPromoConfiguration(@NonNull final PromozioneTestataEntity testata,
			@NonNull final Long idMeccanica, @NonNull final PlanningArticleMultiFilterParam params,
		  	Boolean isUserAdmin, List<String> codiciGruppo) {

		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();

		// Se e solo se almeno uno dei filtri è valorizzato allora cerco gli item
		if (params.getIdCompratoreSelected() != null
				|| params.getIdFornitoreSelected() != null
				|| params.getIdRepartoSelected() != null
				|| params.getIdCategoriaSelected() != null
				|| params.getIdGrmSelected() != null
				|| params.getCodiceMarchioPrivSelected() != null
		) {

			List<ItemEntity> itemEntities = pianificazioneService.findAllItemsByDynamicFilters(
					params.getIdCompratoreSelected(),
					params.getIdFornitoreSelected(),
					params.getIdRepartoSelected(),
					params.getIdCategoriaSelected(),
					params.getIdGrmSelected(),
					params.getCodiceMarchioPrivSelected());

			if (isUserAdmin != null && !isUserAdmin) {
				final List<String> buyerCodes = securityUtilInstance.get().getWritableCompratori(testata, codiciGruppo);
				itemEntities = itemEntities.stream()
						.filter(i -> buyerCodes.contains(i.getCompratoreEntity().getCodiceCompratore()))
						.collect(Collectors.toList());
			}

			final CfgConfHeaderEntity confHeader = confHeaderServiceInstance.get()
					.findByMeccanicaIdAndSetPianificazioneId(idMeccanica, testata.getMuiCfgSetPianificazione().getId());
			if (confHeader != null) {
				Stream<ItemEntity> stream = itemEntities.stream();
				// #3938 Keep track of used items to add a yellow background to permitted duplicated items
				final List<Long> usedItems = new ArrayList<>();

				// #4182: gestione flag duplica articolo per canale
				final Boolean duplicaArticoloCanale = testata.getMuiCanalePromozione().getDuplicaArticolo();
				if (!Boolean.TRUE.equals(duplicaArticoloCanale)) {
					// Scarto elementi duplicati su tutta la pianificazione
					usedItems.addAll(pianificazioneHelperInstance.get()
							.getUsedItems(testata, PromoPianificazioneEnum.ARTICOLO.getTipoElemento()));
					stream = stream.filter(i -> !usedItems.contains(i.getId()));
				}

				final Boolean duplicaArticolo = confHeader.getDuplicaArticolo();
				// #3938 Keep track of used items to add a yellow background to permitted duplicated items
				usedItems.addAll(pianificazioneHelperInstance.get().getUsedItems(testata, idMeccanica,
						confHeader.getLivelloPianificazione().getCodice(), PromoPianificazioneEnum.ARTICOLO.getTipoElemento()));
				if (duplicaArticolo == null || !duplicaArticolo) {
					// Scarto elementi duplicati
					stream = stream.filter(i -> !usedItems.contains(i.getId()));
				}
				stream.forEach(i -> mapItemEntityByFornitore(i, arrayNode, usedItems, duplicaArticolo,
						testata.getDataInizio()));
			} else {
				log.error(String.format("Error getting confHeader from meccanica %d and cfgSetPianificazione %d",
						idMeccanica, testata.getMuiCfgSetPianificazione().getId()));
			}
		}

		ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
		objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
		return objectNode;
	}
	@Override
	public String generateRowDataFilteredByPromoConfiguration(String promoId, String radioChecked) {
		return null;
	}

	private void mapItemEntityByFornitore(final ItemEntity item, ArrayNode arrayNode) {
		mapItemEntityByFornitore(item, arrayNode, null, null);
	}

	private void mapItemEntityByFornitore(final ItemEntity item, ArrayNode arrayNode, List<Long> usedItems,
										  Boolean duplicaArticolo) {
		mapItemEntityByFornitore(item, arrayNode, usedItems, duplicaArticolo, null);
	}

	private void mapItemEntityByFornitore(final ItemEntity item, ArrayNode arrayNode, List<Long> usedItems,
										  Boolean duplicaArticolo, Date dataInizioPromozione) {
		if (item.getMuiAssortimentoFornitores() != null) {
			item.getMuiAssortimentoFornitores().stream()
					.filter(af -> hasFornitoreActive(af, dataInizioPromozione))
					.forEach(af -> arrayNode.add(mapItemEntityToObjectNode(item,
							af.getFornitoreEntity(), usedItems, duplicaArticolo)));
		}
	}

	private boolean hasFornitoreActive(AssortimentoFornitoreEntity assortimentoFornitoreEntity, Date dataInizioPromozione) {
		DateTimeUtils dtUtils = new DateTimeUtils();
		return assortimentoFornitoreEntity.getFornitoreEntity() != null
				&& (assortimentoFornitoreEntity.getDataEliminazione() == null
				|| dtUtils.isAfter(assortimentoFornitoreEntity.getDataEliminazione(), dataInizioPromozione, false, true));
	}

	private ObjectNode mapItemEntityToObjectNode(ItemEntity item, FornitoreEntity fornitore, List<Long> usedItems,
												 Boolean duplicaArticolo) {
		final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();
		final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();

		DBPromoAgCell cell = DBPromoAgCell.builder().name("Codice Articolo").editable(Boolean.FALSE).type(cellTypeString)
				.value(item.getCodiceItem() == null ? "" : item.getCodiceItem()).build();
		map.put("codiceArticolo", cell);

		cell = DBPromoAgCell.builder().name("Descrizione Articolo").editable(Boolean.FALSE).type(cellTypeString)
				.value(item.getDescrizione() == null ? "" : item.getDescrizione().toUpperCase()).build();
		map.put("descrizioneArticolo", cell);

		cell = DBPromoAgCell.builder().name("Marchio Privato").editable(Boolean.FALSE).type(cellTypeString)
				.value(getMarchioPrivatoByItem(item.getCodiceMarchioPrivato())).build();
		map.put("marchioPrivato", cell);

		cell = DBPromoAgCell.builder().name("Compratore").editable(Boolean.FALSE).type(cellTypeString)
				.value(item.getCompratoreEntity().getCodiceCompratore() == null ? ""
						: String.format("[%s] %s", item.getCompratoreEntity().getCodiceCompratore(),
						item.getCompratoreEntity().getDescrizione() != null
								? item.getCompratoreEntity().getDescrizione().toUpperCase()
								: ""))
				.build();
		map.put("compratore", cell);

		cell = DBPromoAgCell.builder().name("Fornitore").editable(Boolean.FALSE).type(cellTypeString)
				.value(fornitore.getCodiceFornitore() == null ? ""
						: String.format("[%s] %s", fornitore.getCodiceFornitore(),
						fornitore.getDescrizione() != null
								? fornitore.getDescrizione().toUpperCase()
								: ""))
				.build();
		map.put("fornitore", cell);

		final ItemHierarchy itemHierarchy = ItemHierarchy.build(item);

		cell = DBPromoAgCell.builder().name("Reparto").editable(Boolean.FALSE)
				.type(cellTypeString).value(itemHierarchy.getReparto()).build();
		map.put("reparto", cell);

		cell = DBPromoAgCell.builder().name("Categoria").editable(Boolean.FALSE)
				.type(cellTypeString).value(itemHierarchy.getCategoria()).build();
		map.put("categoria", cell);

		cell = DBPromoAgCell.builder().name("Grm").editable(Boolean.FALSE)
				.type(cellTypeString).value(itemHierarchy.getGrm()).build();
		map.put("grm", cell);

		cell = DBPromoAgCell.builder().name("Sub Grm").editable(Boolean.FALSE)
				.type(cellTypeString).value(itemHierarchy.getSubGrm()).build();
		map.put("subGrm", cell);

		cell = DBPromoAgCell.builder().name("id").editable(Boolean.FALSE)
				.value(String.valueOf(item.getId())).build();
		map.put("id", cell);

		cell = DBPromoAgCell.builder().name("elemento").editable(Boolean.FALSE)
				.value(item.getCodiceItem() == null || item.getDescrizione() == null ? ""
						: String.format("%s - %s", item.getCodiceItem(), item.getDescrizione()))
				.build();
		map.put("elemento", cell);

		final boolean isUsed = duplicaArticolo != null && duplicaArticolo
				&& usedItems != null && usedItems.contains(item.getId());
		if (isUsed) {
			map.values().forEach(c -> c.setWarning(true));
		}

		return JsonUtils.getMapper().valueToTree(map);
	}

	private String getMarchioPrivatoByItem(String codiceMarchioPrivato) {
		if (StringUtils.isBlank(codiceMarchioPrivato)) {
			return "";
		}
		final MarchioPrivatoEntity marchioPrivato = marchioPrivatoServiceInstance.get().findByCodice(codiceMarchioPrivato);
		if (marchioPrivato == null) {
			return "";
		}
		return String.format("[%s] %s", marchioPrivato.getCodice(),
						marchioPrivato.getDescrizione() != null ? marchioPrivato.getDescrizione() : "")
				.trim();
	}

	@Override
	public ObjectNode generateRowDataByPromoElementMechanic(@NonNull final PromozioneTestataEntity testata,
															@NonNull final Long idMeccanica, final String buyerId, Boolean isUserAdmin, List<String> codiciGruppo,
															final String operationMessage) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();

		CompratoreEntity compratoreEntity = null;
		Long compratoreid = null;

		if (buyerId != null && !buyerId.isEmpty() && StringUtils.isNumeric(buyerId)) {
			compratoreid = Long.parseLong(buyerId);
		}

		if (compratoreid != null) {
			compratoreEntity = pianificazioneService.findCompratoreById(compratoreid);
		}

		// nel caso di ARTICOLO: il parametro Compratore e' obbligatorio, se non
		// presente il rowData e' vuoto;
		// altrimenti ritorna la lista degli articoli (MUI_ITEM) associati al compratore
		// selezionato(MUI_COMPRATORE);
		if (compratoreEntity != null) {
			List<ItemEntity> itemEntities = pianificazioneService.getAllItemsByCompratore(compratoreEntity);

			// Filtro elimina gli elementi già presenti in visualizzazione per tutte le
			// pianificazioni della promozione
			List<Long> usedItems = testata.getPromozionePianificazioneEntities().stream()
					.filter(pianificazione -> idMeccanica.equals(pianificazione.getMeccanicaEntity().getId())
							&& pianificazione.getParent() != null
							&& ElementType.ARTICOLO.getDescription().equalsIgnoreCase(pianificazione.getTipoElemento()))
					.map(detail -> Long.parseLong(detail.getCodiceElemento())).collect(Collectors.toList());

			itemEntities.stream().filter(itemEntity -> usedItems.contains(itemEntity.getId()))
					.collect(Collectors.toList()).stream().forEach(entity -> itemEntities.remove(entity));

			itemEntities.stream().forEach(itemEntity -> mapItemEntityByFornitore(itemEntity, arrayNode));
		}
		ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
		objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
		return objectNode;
	}
}
