package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CheckTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoPubblicazioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.SottoscrizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoTerminaleCassaEntity;
import com.axiante.mui.webapp.business.OwnershipService;
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Dependent
public class SchedaPromoUtil {

	@Inject
	private Instance<OwnershipService> ownershipServiceInstance;

	final DateTimeUtils dateTimeUtils = new DateTimeUtils();

	public String createPlanogrammiRowData(Set<MuiPlanoDbPromoEntity> planogrammi, boolean deletable) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		try {
			final Comparator<MuiPlanoDbPromoEntity> sorted = Comparator.nullsFirst(
							Comparator.comparing(MuiPlanoDbPromoEntity::getCategoriaEspositiva))
					.thenComparing(Comparator.nullsFirst(Comparator.comparing(MuiPlanoDbPromoEntity::getDescrizioneCategoria)))
					.thenComparing(Comparator.nullsFirst(Comparator.comparing(MuiPlanoDbPromoEntity::getDimensione)))
					.thenComparing(Comparator.nullsFirst(Comparator.comparing(MuiPlanoDbPromoEntity::getTipologia)));
			planogrammi.stream().sorted(sorted).forEach(p -> arrayNode.add(createPlanogrammaRowNode(p, deletable)));
			final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			return JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException ex) {
			log.error("Error getting rowData for 'SchedaPromo - Planogrammi'", ex);
			return "";
		}
	}

	private ObjectNode createPlanogrammaRowNode(MuiPlanoDbPromoEntity plano, boolean deletable) {
		final Map<String, DBPromoAgCell> map = new HashMap<>();
		final String stringType = DBPromoCellTypeEnum.STRING.getType();

		DBPromoAgCell cell = DBPromoAgCell.builder().name("Codice Categoria").editable(false).type(stringType)
				.value(nullableValue(plano.getCategoriaEspositiva())).build();
		map.put("categoriaEspositiva", cell);

		cell = DBPromoAgCell.builder().name("Descrizione Categoria").editable(false).type(stringType)
				.value(nullableValue(plano.getDescrizioneCategoria())).build();
		map.put("descrizioneCategoria", cell);

		cell = DBPromoAgCell.builder().name("Dimensione").editable(false).type(stringType)
				.value(nullableValue(plano.getDimensione())).build();
		map.put("dimensione", cell);

		cell = DBPromoAgCell.builder().name("Tipologia").editable(false).type(stringType)
				.value(nullableValue(plano.getTipologia())).build();
		map.put("tipologia", cell);

		cell = DBPromoAgCell.builder().name("Codice CICS").editable(false).type(stringType)
				.value(nullableValue(plano.getCodiceCics())).build();
		map.put("codiceCics", cell);

		cell = DBPromoAgCell.builder().name("Planogramma").editable(false).type(stringType)
				.value(nullableValue(plano.getCodicePlano())).build();
		map.put("codicePlano", cell);

		cell = DBPromoAgCell.builder().name("ID Planogram").editable(false).type(stringType)
				.value(plano.getIdPlano()).build();
		map.put("idPlano", cell);

		cell = DBPromoAgCell.builder().name("RemoveEnabled").editable(false).type(stringType)
				.value(String.valueOf(deletable)).build();
		map.put("removeEnabled", cell);

		return JsonUtils.getMapper().valueToTree(map);
	}

	public String createStatiRowData(Set<PromozioneStatoEntity> promozioneStatoEntities) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";
		promozioneStatoEntities.stream()
		.sorted(Comparator.comparing(PromozioneStatoEntity::getDataInizioStato).reversed())
		.forEach(promozioneStatoEntity -> arrayNode.add(createStatiRowNode(promozioneStatoEntity)));
		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing scheda promozione tab stati JSON row data", e);
		}
		return json;
	}

	private ObjectNode createStatiRowNode(PromozioneStatoEntity promozioneStatoEntity) {
		final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();
		DBPromoAgCell dbPromoAgCell = DBPromoAgCell.builder().name("Stato").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value((promozioneStatoEntity.getStatoPromozioneEntity() == null)
						|| (promozioneStatoEntity.getStatoPromozioneEntity().getCodiceStato() == null)
						|| (promozioneStatoEntity.getStatoPromozioneEntity().getLabel() == null)
						? ""
								: String.format("%s - %s",
										promozioneStatoEntity.getStatoPromozioneEntity().getCodiceStato(),
										promozioneStatoEntity.getStatoPromozioneEntity().getLabel()))
				.build();
		map.put("codice", dbPromoAgCell);

		DataTypeParams dataTypeParamsPojo = new DataTypeParams();
		dataTypeParamsPojo.setDateFormat(DbPromoConstants.DBPROMO_STRING_DATE_FORMAT);

		dbPromoAgCell = DBPromoAgCell.builder().name("Data Inizio").editable(false)
				.type(DBPromoCellTypeEnum.DATE.getType())
				.value(promozioneStatoEntity.getDataInizioStato() == null ? ""
						: dateTimeUtils.toExcelDate(promozioneStatoEntity.getDataInizioStato()))
				.dataTypeParams(dataTypeParamsPojo).build();
		map.put("dataInizio", dbPromoAgCell);

		dbPromoAgCell = DBPromoAgCell.builder().name("Data Fine").editable(false)
				.type(DBPromoCellTypeEnum.DATE.getType())
				.value(promozioneStatoEntity.getDataFineStato() == null ? ""
						: dateTimeUtils.toExcelDate(promozioneStatoEntity.getDataFineStato()))
				.dataTypeParams(dataTypeParamsPojo).build();

		map.put("dataFine", dbPromoAgCell);

		dbPromoAgCell = DBPromoAgCell.builder().name("Utente").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(promozioneStatoEntity.getCodiceUtenteInserimento()).build();

		map.put("utente", dbPromoAgCell);

		return JsonUtils.getMapper().valueToTree(map);
	}

	public String createMeccanicheRowData(PromozioneTestataEntity testata, List<String> gruppi, boolean isAdmin) {

		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";

		final StatoPromozioneEntity lastStatus = PromoAcl.getStatoCorrente(testata);
		final boolean lastMeccanica = !((testata.getPromozioneMeccanicheEntities() != null)
				&& (testata.getPromozioneMeccanicheEntities().size() > 1));

		boolean removeEnabled = (isAdmin || ownershipServiceInstance.get().hasOwnership(testata, gruppi)) // abbiamo ownership sulla testata
				&& (lastStatus != null // abbiamo lo stato
		)
				&& !(PromoStatusEnum.PROMOZIONIE_CONCLUSA.getKey().equals(lastStatus.getCodiceStato())
				|| PromoStatusEnum.PIANIFICAZIONE_FINALIZZATA.getKey().equals(lastStatus.getCodiceStato())
				|| PromoStatusEnum.PROMOZIONE_IN_ESECUZIONE.getKey().equals(lastStatus.getCodiceStato())
				|| PromoStatusEnum.SBLOCCO_PROMO_IN_ESECUZIONE_PER_CORREZIONI.getKey()
				.equals(lastStatus.getCodiceStato()))
				&& !lastMeccanica // e non c'e' una sola meccanica
				&& !Boolean.TRUE.equals(testata.getMuiCanalePromozione().getFlMeccanicaSingola())    // il canale ha la meccanica singola
				;

		// controlla #1968
		// Le meccaniche devono essere ordinate con ordine Crescente per codice
		testata.getPromozioneMeccanicheEntities().stream()
		.sorted(Comparator.comparing(
				promozioneMeccanica -> promozioneMeccanica.getMeccanicheEntity().getCodiceMeccanica()))
		.forEach(promozioneMeccanicheEntity -> arrayNode
				.add(createMeccanicheRowNode(promozioneMeccanicheEntity, removeEnabled, testata)));

		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing scheda promozione tab meccaniche JSON row data", e);
		}

		return json;
	}

	public String createSottoscrizioniRowData(Set<SottoscrizioneEntity> sottoscrizioni) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";
		sottoscrizioni.stream()
				.sorted(Comparator.comparing(SottoscrizioneEntity::getCodice))
				.forEach(s -> arrayNode.add(createSottoscrizioneRowNode(s)));
		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing scheda promozione tab reparto JSON row data", e);
		}
		return json;
	}

	private ObjectNode createSottoscrizioneRowNode(SottoscrizioneEntity sottoscrizione) {
		final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();
		final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();

		DBPromoAgCell cell = DBPromoAgCell.builder().name("Id").editable(false).type(cellTypeString)
				.value(nullableValue(sottoscrizione.getId())).build();
		map.put("id", cell);

		cell = DBPromoAgCell.builder().name("Codice").editable(false).type(cellTypeString)
				.value(nullableValue(sottoscrizione.getCodice())).build();
		map.put("codice", cell);

		cell = DBPromoAgCell.builder().name("Descrizione").editable(false).type(cellTypeString)
				.value(nullableValue(sottoscrizione.getDescrizione())).build();
		map.put("descrizione", cell);

		cell = DBPromoAgCell.builder().name("RemoveEnabled").editable(false).type(cellTypeString)
				.value(String.valueOf(true)).build();
		map.put("removeEnabled", cell);

		return JsonUtils.getMapper().valueToTree(map);
	}

	public String createRepartiRowData(PromozioneTestataEntity testata, List<String> gruppi, boolean isAdmin) {

		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";

		final StatoPromozioneEntity lastStatus = PromoAcl.getStatoCorrente(testata);

		boolean removeEnabled = (isAdmin || ownershipServiceInstance.get().hasOwnership(testata, gruppi)) // abbiamo ownership sulla testata
				&& (lastStatus != null // abbiamo lo stato
						)
				&& !(PromoStatusEnum.PROMOZIONIE_CONCLUSA.getKey().equals(lastStatus.getCodiceStato())
						|| PromoStatusEnum.PIANIFICAZIONE_FINALIZZATA.getKey().equals(lastStatus.getCodiceStato())
						|| PromoStatusEnum.PROMOZIONE_IN_ESECUZIONE.getKey().equals(lastStatus.getCodiceStato())
						|| PromoStatusEnum.SBLOCCO_PROMO_IN_ESECUZIONE_PER_CORREZIONI.getKey()
						.equals(lastStatus.getCodiceStato()));

		// I reparti devono essere ordinate con ordine Crescente per codice
		testata.getReparti().stream()
		.sorted(Comparator.comparing(
				RepartoEntity::getCodiceReparto))
		.forEach(reparto -> arrayNode
				.add(createRepartoRowNode(reparto, removeEnabled, testata)));

		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing scheda promozione tab reparto JSON row data", e);
		}

		return json;
	}


	public String createTipoCassaRowData(@NonNull PromozioneTestataEntity testata, List<String> gruppi, boolean isAdmin) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";
		boolean removeEnabled = (isAdmin || ownershipServiceInstance.get().hasOwnership(testata, gruppi))
				&& isTipoCassaDeletable(testata);
		testata.getPromozioneTipiTerminaleCassa().stream()
		.sorted(Comparator.comparing(e -> e.getTipoTerminaleCassaEntity().getDescrizione()))
		.forEach(cassaEntity -> arrayNode.add(createTipoCassaRowNode(cassaEntity.getTipoTerminaleCassaEntity(), removeEnabled)));
		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException ex) {
			String msg = "Error processing scheda promozione tab tipo cassa JSON row data; " +
					"PromozioneTestataEntity id=" + testata.getId();
			log.error(msg, ex);
		}
		return json;
	}

	/**
	 * Il tipo cassa e' eliminabile dalla promozione corrente se lo stato corrente e' uno tra i seguenti
	 * - 10: non pubblicata
	 * - 30: in pubblicazione
	 * - 311: sblocco pubblicazione
	 * - 410: sblocco esecuzione
	 * @param testata promozione corrente
	 * @return true se il tipo cassa associato e' cancellabile, false altrimenti
	 */
	private boolean isTipoCassaDeletable(final PromozioneTestataEntity testata) {
		final StatoPromozioneEntity lastStatusEntity = PromoAcl.getStatoCorrente(testata);
		final List<String> validStates = Arrays.asList(
				PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey(),
				PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey(),
				PromoStatusEnum.SBLOCCO_PIANIFICAZIONE_PER_CORREZIONI.getKey(),
				PromoStatusEnum.SBLOCCO_PROMO_IN_ESECUZIONE_PER_CORREZIONI.getKey());
		return (lastStatusEntity != null) && validStates.contains(lastStatusEntity.getCodiceStato());
	}

	private ObjectNode createTipoCassaRowNode(TipoTerminaleCassaEntity cassaEntity, boolean removeEnabled) {
		final HashedMap<String, DBPromoAgCell> agCellRowMap = new HashedMap<>();

		DBPromoAgCell cell = DBPromoAgCell.builder().name("Tipo").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(cassaEntity.getTipo())
				.build();
		agCellRowMap.put("tipo", cell);

		cell = DBPromoAgCell.builder().name("Descrizione").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(cassaEntity.getDescrizione())
				.build();
		agCellRowMap.put("descrizione", cell);

		cell = DBPromoAgCell.builder().name("TipoTerminale").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(String.valueOf(cassaEntity.getTipoTerminale()))
				.build();
		agCellRowMap.put("tipoTerminale", cell);

		cell = DBPromoAgCell.builder().name("IdTipoCassa").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(String.valueOf(cassaEntity.getId()))
				.build();
		agCellRowMap.put("idTipoCassa", cell);

		cell = DBPromoAgCell.builder().name("RemoveEnabled").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(String.valueOf(removeEnabled))
				.build();
		agCellRowMap.put("removeEnabled", cell);

		return JsonUtils.getMapper().valueToTree(agCellRowMap);
	}

	private ObjectNode createMeccanicheRowNode(PromozioneMeccanicheEntity promozioneMeccaniche, boolean removeEnabled,
			PromozioneTestataEntity testata) {

		final HashedMap<String, DBPromoAgCell> dbPromoAgCellRowMap = new HashedMap<>();

		DBPromoAgCell dbPromoAgCell = DBPromoAgCell.builder().name("Meccanica").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value((promozioneMeccaniche.getMeccanicheEntity() == null)
						|| (promozioneMeccaniche.getMeccanicheEntity().getCodiceMeccanica() == null)
						|| (promozioneMeccaniche.getMeccanicheEntity().getDescrizione() == null)
						? ""
								: String.format("%s - %s",
										promozioneMeccaniche.getMeccanicheEntity().getCodiceMeccanica(),
										promozioneMeccaniche.getMeccanicheEntity().getDescrizione()))
				.build();
		dbPromoAgCellRowMap.put("descrizioneEsteso", dbPromoAgCell);

		dbPromoAgCell = DBPromoAgCell.builder().name("Descrizione").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value((promozioneMeccaniche.getMeccanicheEntity() == null)
						|| (promozioneMeccaniche.getMeccanicheEntity().getDescrizione() == null) ? ""
								: promozioneMeccaniche.getMeccanicheEntity().getDescrizione())
				.build();
		dbPromoAgCellRowMap.put("descrizione", dbPromoAgCell);

		dbPromoAgCell = DBPromoAgCell.builder().name("CodiceMeccanica").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value((promozioneMeccaniche.getMeccanicheEntity() == null)
						|| (promozioneMeccaniche.getMeccanicheEntity().getCodiceMeccanica() == null) ? ""
								: promozioneMeccaniche.getMeccanicheEntity().getCodiceMeccanica())
				.build();
		dbPromoAgCellRowMap.put("codiceMeccanica", dbPromoAgCell);

		dbPromoAgCell = DBPromoAgCell.builder().name("IdPromozioneMeccaniche").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(promozioneMeccaniche.getId())).build();
		dbPromoAgCellRowMap.put("idPromozioneMeccaniche", dbPromoAgCell);

		dbPromoAgCell = DBPromoAgCell.builder().name("RemoveEnabled").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(removeEnabled && PromoAcl
						.isMeccanicaCancellabile(promozioneMeccaniche.getMeccanicheEntity(), testata)))
				.build();
		dbPromoAgCellRowMap.put("removeEnabled", dbPromoAgCell);
		return JsonUtils.getMapper().valueToTree(dbPromoAgCellRowMap);
	}

	private ObjectNode createRepartoRowNode(RepartoEntity reparto, boolean removeEnabled,
			PromozioneTestataEntity testata) {

		final HashedMap<String, DBPromoAgCell> dbPromoAgCellRowMap = new HashedMap<>();

		DBPromoAgCell dbPromoAgCell = DBPromoAgCell.builder().name("Reparto").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value((reparto.getCodiceReparto() == null) || (reparto.getDescrizione() == null)
						? ""
								: reparto.getLabel())
				.build();
		dbPromoAgCellRowMap.put("descrizioneEstesa", dbPromoAgCell);

		dbPromoAgCell = DBPromoAgCell.builder().name("Descrizione").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value((reparto.getDescrizione() == null) ? ""
						: reparto.getDescrizione())
				.build();
		dbPromoAgCellRowMap.put("descrizione", dbPromoAgCell);

		dbPromoAgCell = DBPromoAgCell.builder().name("CodiceReparto").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value((reparto.getCodiceReparto() == null) ? ""
						: reparto.getCodiceReparto())
				.build();
		dbPromoAgCellRowMap.put("codiceReparto", dbPromoAgCell);

		dbPromoAgCell = DBPromoAgCell.builder().name("RemoveEnabled").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(removeEnabled && PromoAcl
						.isRepartoCancellabile(reparto, testata)))
				.build();
		dbPromoAgCellRowMap.put("removeEnabled", dbPromoAgCell);

		dbPromoAgCell = DBPromoAgCell.builder().name("idReparto").editable(false)
				.type(DBPromoCellTypeEnum.NUMERIC.getType()).value(reparto.getId().toString()).build();
		dbPromoAgCellRowMap.put("idReparto", dbPromoAgCell);

		return JsonUtils.getMapper().valueToTree(dbPromoAgCellRowMap);

	}


	public String createPubblicazioniRowData(List<PromoPubblicazioneTestataEntity> promoPubblicazioneTestataEntities) {

		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";

		promoPubblicazioneTestataEntities.stream()
		.sorted(Comparator.comparing(PromoPubblicazioneTestataEntity::getDataPubblicazione).reversed())
		.forEach(pubblicazioneTestataEntity -> arrayNode
				.add(createPubblicazioniRowNode(pubblicazioneTestataEntity)));

		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing scheda promozione tab pubblicazioni JSON row data", e);
		}

		return json;
	}

	public String createControlliRowData(@NonNull PromozioneTestataEntity testata) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		try {
			testata.getChecks().stream().sorted(Comparator.comparing(CheckTestataEntity::getId).reversed())
					.forEach(c -> arrayNode.add(createControlliRowNode(c)));
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			return JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (Exception ex) {
			log.error(String.format("Error creating JSON rowData for Testata with id %d", testata.getId()), ex);
			return "";
		}
	}

	private ObjectNode createControlliRowNode(CheckTestataEntity check) {
		final Map<String, DBPromoAgCell> map = new HashMap<>();

		DBPromoAgCell cell = DBPromoAgCell.builder().name("Tipo Controllo").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(check.getTipoControllo()).build();
		map.put("tipoControllo", cell);

		cell = DBPromoAgCell.builder().name("Descrizione").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(check.getDescrizioneControllo()).build();
		map.put("descrizione", cell);

		cell = DBPromoAgCell.builder().name("Compratore").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(check.getDescrizioneCompratore() != null ? check.getDescrizioneCompratore() : "").build();
		map.put("compratore", cell);

		cell = DBPromoAgCell.builder().name("Severita").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(check.getSeverita() != null ? check.getSeverita() : "").build();
		map.put("severita", cell);

		if ("ERRORE".equalsIgnoreCase(check.getSeverita())) {
			map.values().forEach(c -> c.setMandatory(true));
		}

		return JsonUtils.getMapper().valueToTree(map);
	}

	public String createOwnerRowData(@NonNull PromozioneTestataEntity testata) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		try {
			testata.getOwners().stream().sorted(Comparator.comparing(CompratoreEntity::getCodiceCompratore))
					.forEach(c -> arrayNode.add(createOwnerRowNode(c)));
			final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			return JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (Exception ex) {
			log.error(String.format("Error creating JSON rowData for Testata with id %d", testata.getId()), ex);
			return "";
		}
	}

	private ObjectNode createOwnerRowNode(CompratoreEntity buyer) {
		final Map<String, DBPromoAgCell> map = new HashMap<>();

		DBPromoAgCell cell = DBPromoAgCell.builder().name("Owner").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(String.format("%s - %s", buyer.getCodiceCompratore(), buyer.getDescrizione())).build();
		map.put("owner", cell);

		return JsonUtils.getMapper().valueToTree(map);
	}

	private ObjectNode createPubblicazioniRowNode(PromoPubblicazioneTestataEntity entity) {

		final HashedMap<String, DBPromoAgCell> dbPromoAgCellRowMap = new HashedMap<>();

		DataTypeParams dataTypeParamsPojo = new DataTypeParams();
		dataTypeParamsPojo.setDateFormat(DbPromoConstants.DBPROMO_STRING_DATE_FORMAT);

		DBPromoAgCell dbPromoAgCell = DBPromoAgCell.builder().name("Data Pubblicazione").editable(false)
				.type(DBPromoCellTypeEnum.DATE.getType())
				.value(entity.getDataPubblicazione() == null ? ""
						: dateTimeUtils.toExcelDate(entity.getDataPubblicazione()))
				.dataTypeParams(dataTypeParamsPojo).build();
		dbPromoAgCellRowMap.put("dataPubblicazione", dbPromoAgCell);

		dbPromoAgCell = DBPromoAgCell.builder().name("Stato").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
				.value((entity.getStatoPromozioneEntity().getCodiceStato() == null)
						|| (entity.getStatoPromozioneEntity().getLabel() == null) ? ""
								: String.format("%s - %s", entity.getStatoPromozioneEntity().getCodiceStato(),
										entity.getStatoPromozioneEntity().getLabel()))
				.build();
		dbPromoAgCellRowMap.put("stato", dbPromoAgCell);

		dbPromoAgCell = DBPromoAgCell.builder().name("Esito").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
				.value(DbPromoConstants.convertEsito(entity.getFlagEsito())).build();
		dbPromoAgCellRowMap.put("esito", dbPromoAgCell);
		
		dbPromoAgCell = DBPromoAgCell.builder().name("Descrizione").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
				.value(entity.getDescrizione()).build();
		dbPromoAgCellRowMap.put("descrizione", dbPromoAgCell);
		

		dbPromoAgCell = DBPromoAgCell.builder().name("ExportId").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(entity.getExportId()).build();
		dbPromoAgCellRowMap.put("exportId", dbPromoAgCell);

		return JsonUtils.getMapper().valueToTree(dbPromoAgCellRowMap);
	}

	private String nullableValue(String value) {
		return StringUtils.isBlank(value) ? "" : value;
	}

	private String nullableValue(Long value) {
		return value != null ? String.valueOf(value) : "";
	}
}
