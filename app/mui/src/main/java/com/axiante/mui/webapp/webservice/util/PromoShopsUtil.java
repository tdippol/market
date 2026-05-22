package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.promo.ShopUpdateTypeEnum;
import com.axiante.mui.common.promo.UserFilterUtils;
import com.axiante.mui.common.promo.grid.ComboBoxValues;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.common.promo.params.PromoShopBulkParam;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.actions.ActionEnum;
import com.axiante.mui.dbpromo.business.enumeration.PromoShopRadioButtonEnum;
import com.axiante.mui.dbpromo.business.enumeration.PromoShopsFlagEnum;
import com.axiante.mui.dbpromo.business.service.UploadExcelService;
import com.axiante.mui.dbpromo.business.service.impl.data.ShopItemUpload;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.business.utils.pojo.PromoShopUpdateDto;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.dto.ZonaDto;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.NegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoNegozioEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgStatiCanaleConsentitiService;
import com.axiante.mui.dbpromo.persistence.service.NegoziPromoService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.webapp.business.OwnershipService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.core.StreamingOutput;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class PromoShopsUtil {
	public static final String[] CODICI_MECCANICA_DIFFERENZIAZIONE = new String[]{"", "M800", "M801", "M810"};

	@Inject
	PromoService promoService;

	private DateTimeUtils dateTimeUtils = new DateTimeUtils();

	@Inject
	UploadExcelService uploadExcelService;

	@Inject
	NegoziPromoService negoziPromoService;

	@Inject
	private Instance<OwnershipService> ownershipServiceInstance;

	@Inject ApplicationProperties applicationProperties;
	UserFilterUtils userFilterUtils = new UserFilterUtils();

	@Inject
	private Instance<CfgStatiCanaleConsentitiService> cfgStatiCanaleConsentitiService;
	private static final List<ComboBoxValues> flagsAsCombo = Arrays.stream(PromoShopsFlagEnum.values())
			.map(f -> ComboBoxValues.builder().key(f.getKey()).label(f.getLabel()).build())
			.sorted((c1, c2) -> Long.compare(Long.parseLong(c2.getKey()), Long.parseLong(c1.getKey())))
			.collect(Collectors.toList());

	private final Comparator<PromozioneNegozioEntity> negoziSorterByCodice = Comparator.comparing(Function.identity(),
			Comparator.comparing((PromozioneNegozioEntity o) -> o.getNegozioEntity().getCodiceNegozio()));

	final byte arrayStart = (byte) '[';
	final byte arrayEnd = (byte) ']';
	final byte quote = (byte) '"';
	final byte comma = (byte) ',';
	final byte curlyOpen = (byte) '{';
	final byte curlyClosed = (byte) '}';
	final byte colon = (byte) ':';

	public StreamingOutput streamRowData(String promoSelected, String dbFiltersJson, List<CanalePromozioneEntity> canali,
			List<String> gruppi, boolean isAdmin) {
		return output -> {
			try {

				final Map<String, String> userFiltersMap = userFilterUtils
						.createUserFiltersMapToQueryString(dbFiltersJson);
				// cominciamo a scrivere l'array ...
				output.write(curlyOpen);
				output.write(quote);
				output.write(DbPromoConstants.ROW_DATA.getBytes());
				output.write(quote);
				output.write(colon);
				output.write(arrayStart);

				if ((promoSelected != null) && !promoSelected.isEmpty()) {
					PromozioneTestataEntity testata1 = promoService.findById(new Long(promoSelected));
					streamJsonRowDataGivenPromozioneTestataEntity(testata1, output, gruppi, isAdmin);
				} else {
					List<PromozioneTestataEntity> testate = promoService.findAllNotCancelled(userFiltersMap, canali)
							.stream().filter(Objects::nonNull)
							.filter(t -> !t.getPromozioneNegozioEntities().isEmpty())
							.sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio))
							.collect(Collectors.toList());
					final int max = testate.size();
					final AtomicInteger cursor = new AtomicInteger(0);
					testate.forEach(testata2 -> {
						streamJsonRowDataGivenPromozioneTestataEntity(testata2, output, gruppi, isAdmin);
						if (cursor.incrementAndGet() < max) {
							try {
								output.write(comma);
							} catch (Exception e1) {
								log.error("error streaming content", e1);
							}
						}
					});
				}

				try {
					output.write(arrayEnd);
					output.write(curlyClosed);
				} catch (Exception e2) {
					log.error("error closing output stream", e2);
				}

			} catch (Exception e3) {
				log.error("Error map PromozioneTestataEntity into JSON object", e3);
			}

		};
	}

	public String populateRowData(String promoSelected, String dbFiltersJson, List<CanalePromozioneEntity> canali,
			List<String> gruppi, boolean isAdmin) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";

		try {

			final Map<String, String> userFiltersMap = userFilterUtils.createUserFiltersMapToQueryString(dbFiltersJson);

			if ((promoSelected != null) && !promoSelected.isEmpty()) {
				PromozioneTestataEntity testata = promoService.findById(new Long(promoSelected));
				createJsonRowDataGivenPromozioneTestataEntity(testata, arrayNode, gruppi, isAdmin);
			} else {
				promoService.findAllNotCancelled(userFiltersMap, canali).stream().filter(Objects::nonNull)
				.sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio))
				.forEach(entity -> createJsonRowDataGivenPromozioneTestataEntity(entity, arrayNode, gruppi, isAdmin));
			}

		} catch (Exception e) {
			log.error("Error map PromozioneTestataEntity into JSON object", e);
		}

		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing scheda promozione tab meccaniche JSON row data", e);
		}

		return json;
	}

	/**
	 * dalla testata promo estrae i negozi e li mappa in righe json
	 *
	 * @param testata
	 * @param arrayNode
	 */
	private void createJsonRowDataGivenPromozioneTestataEntity(@NonNull PromozioneTestataEntity testata,
			ArrayNode arrayNode, List<String> gruppi, boolean isAdmin) {
		// Le righe devono essere ordinate per CODICE NEGOZIO (le zone non sono mostrate
		// a video)
		testata.getPromozioneNegozioEntities().stream().sorted(negoziSorterByCodice)
		.map(n -> mapRowDataIntoObjectNode(n, gruppi, isAdmin))
		.forEach(arrayNode::add);
	}

	private void streamJsonRowDataGivenPromozioneTestataEntity(@NonNull PromozioneTestataEntity testata,
			OutputStream out, List<String> gruppi, boolean isAdmin) {
		// Le righe devono essere ordinate per CODICE NEGOZIO (le zone non sono mostrate
		// a video)
		final int max = testata.getPromozioneNegozioEntities().size();
		AtomicInteger cursor = new AtomicInteger(0);
		AtomicBoolean quit = new AtomicBoolean(false);
		testata.getPromozioneNegozioEntities().stream().sorted(negoziSorterByCodice).forEach(o -> {
			// per ogni negozio:
			// stream del contenuto
			if (!quit.get()) {
				try {
					boolean b = streamRowDataIntoObjectNode(o, out, gruppi, isAdmin);
					if ((cursor.incrementAndGet() < max) && b) {
						// se ho inserito qualcosa e ho altro dopo .... metto la virgola
						out.write((byte) ',');
					}
				} catch (IOException e) {
					log.error("Aborting due to error ", e);
					// client abort exception
					quit.set(true);
				} catch (Exception e) {
					log.error("error streaming content", e);
				}
			}
		});
	}

	private ObjectNode mapRowDataIntoObjectNode(PromozioneNegozioEntity promoNegozio, List<String> gruppi, boolean isAdmin) {
		Map<String, DBPromoAgCell> dbPromoAgCellRowMap = mapDBPromoAgCellsGivenPromozioneNegozioEntity(promoNegozio, gruppi, isAdmin)
				.stream().collect(Collectors.toMap(DBPromoAgCell::getName, Function.identity()));

		return JsonUtils.getMapper().valueToTree(dbPromoAgCellRowMap);
	}

	private boolean streamRowDataIntoObjectNode(PromozioneNegozioEntity promoNegozio, OutputStream out, List<String> gruppi, boolean isAdmin)
			throws Exception {
		Map<String, DBPromoAgCell> dbPromoAgCellRowMap = mapDBPromoAgCellsGivenPromozioneNegozioEntity(promoNegozio, gruppi, isAdmin)
				.stream().collect(Collectors.toMap(DBPromoAgCell::getName, Function.identity()));
		if (!dbPromoAgCellRowMap.isEmpty()) {
			JsonUtils.getNonClosingStreamMapper().writeValue(out, dbPromoAgCellRowMap);
			return true;
		}
		return false;
	}

	private List<DBPromoAgCell> mapDBPromoAgCellsGivenPromozioneNegozioEntity(PromozioneNegozioEntity promoNegozio,
																			  List<String> gruppi, boolean isAdmin) {
		PromozioneTestataEntity testata = promoNegozio.getPromozioneTestataEntity();
		final boolean editable = (isAdmin || ownershipServiceInstance.get().hasOwnership(testata, gruppi))
				&& (PromoAcl.isShopEditable(testata) || checkShopEditWhileExecutionInProgressEnabled(testata));
		final boolean codiceMeccanicaEditable = editable && (PromoAcl.isShopCodiceMeccanicaEditable(testata));
		final String stringCell = DBPromoCellTypeEnum.STRING.getType();
		// MG #5346: codici meccanica disponibili cablati nel codice :(
		List<DBPromoAgCell> list = new ArrayList<>();
		list.add(DBPromoAgCell.builder().name("descrizioneEstesa").value(testata.getDescrizioneEstesa())
				.type(stringCell).editable(false).build());
		list.add(DBPromoAgCell.builder().name("negozio")
				.value(String.format("[%s] %s", promoNegozio.getNegozioEntity().getCodiceNegozio(),
						promoNegozio.getNegozioEntity().getDescrizione()))
				.type(stringCell).editable(false).build());
		list.add(DBPromoAgCell.builder().name("sigla").value(promoNegozio.getNegozioEntity().getSigla())
				.type(stringCell).editable(false).build());
		list.add(DBPromoAgCell.builder().name("cluster")
				.value(promoNegozio.getNegozioEntity().getDescrizioneCluster())
				.type(stringCell).editable(false).build());
		list.add(DBPromoAgCell.builder().name("tipoPuntoVendita")
				.value(promoNegozio.getNegozioEntity().getTipoNegozioEntity().getDescrizione())
				.type(stringCell).editable(false).build());
		list.add(DBPromoAgCell.builder().name("tipoConsegna")
				.value(promoNegozio.getNegozioEntity().getTipoConsegna())
				.type(stringCell).editable(false).build());
		Optional<PromoShopsFlagEnum> defaultFlag = convertFlag(promoNegozio.getDefaultFlag());
		list.add(DBPromoAgCell.builder().name("defaultFlag")
				.value(defaultFlag.isPresent() ? defaultFlag.get().getLabel() : "")
				.type(stringCell).editable(false).build());
		list.add(DBPromoAgCell.builder().name("flag").value(promoNegozio.getSelezioneFlag())
				.type(DBPromoCellTypeEnum.COMBOBOX.getType()).comboBoxValues(flagsAsCombo).editable(editable).build());
		list.add(DBPromoAgCell.builder().name("dataFine")
				.value(dateTimeUtils.toExcelDate(promoNegozio.getDataFine()))
				.type(DBPromoCellTypeEnum.DATE.getType()).editable(editable).build());
		list.add(DBPromoAgCell.builder().name("dataInizio")
				.value(dateTimeUtils.toExcelDate(promoNegozio.getDataInizio()))
				.type(DBPromoCellTypeEnum.DATE.getType()).editable(editable).build());
		list.add(DBPromoAgCell.builder().name("codiceMeccanica").value(promoNegozio.getCodiceMeccanica())
				.type(DBPromoCellTypeEnum.PICKLIST.getType()).picklistValues(CODICI_MECCANICA_DIFFERENZIAZIONE)
				.editable(codiceMeccanicaEditable).build());
		list.add(DBPromoAgCell.builder().name("promoShopId").value("" + promoNegozio.getId()).build());
		list.add(DBPromoAgCell.builder().name("promoHeaderId")
				.value("" + promoNegozio.getPromozioneTestataEntity().getId()).build());
		list.add(DBPromoAgCell.builder().name("zona").value(
						String.format("[%s_%s] %s", promoNegozio.getNegozioEntity().getSocieta(),
								promoNegozio.getNegozioEntity().getCodiceZona(),
								promoNegozio.getNegozioEntity().getDescrizioneZona()))
				.type(stringCell).editable(false).build());
		list.add(DBPromoAgCell.builder().name("cedi").value(promoNegozio.getNegozioEntity().getCedi())
				.type(stringCell).editable(false).build());
		return list;
	}

	/**
	 * Questo metodo ritorna la label associata al defaultFlag espressa da un valore
	 * numerico sul database
	 *
	 * @param defaultFlag
	 * @return
	 */
	private Optional<PromoShopsFlagEnum> convertFlag(String defaultFlag) {
		return Optional.ofNullable(Arrays.stream(PromoShopsFlagEnum.values())
				.filter(flag -> flag.getKey().equals(defaultFlag)).findFirst().orElse(null));
	}

	/**
	 * Questo metodo ritorna la label associata al defaultFlag espressa da un valore
	 * numerico sul database
	 *
	 * @param flag
	 * @return
	 */
	private Optional<PromoShopsFlagEnum> convertFlag(Boolean flag) {
		return convertFlag(flag ? "1" : "0");
	}

	/**
	 * Questo metodo verifica se i valori aggiornati relativi a dataInizio e
	 * dataFine sono conformi con le date della promozione a cui sono associati
	 *
	 * @param node
	 * @return
	 */
	public boolean validateUpdatedRow(@NonNull final JsonNode node) {
		boolean correct = false;
		String string = DbPromoAgCellUtils.getValue(node.get("dataInizio"));
		Date newStartDate = null;
		Date newEndDate = null;
		if (string != null) {
			newStartDate = dateTimeUtils.excelToDate(string);
		}
		string = DbPromoAgCellUtils.getValue(node.get("dataFine"));
		if (string != null) {
			newEndDate = dateTimeUtils.excelToDate(string);
		}
		PromozioneTestataEntity testata = null;
		string = DbPromoAgCellUtils.getValue(node.get("promoHeaderId"));
		Long number = null;
		if (string != null) {
			number = new Long(string);
			try {
				testata = promoService.findById(number);
				correct = (testata != null) && // c'e' la promozione
						// nuova data inizio coerente con nuovadata fine
						dateTimeUtils.isBefore(newStartDate, newEndDate, true) &&
						// nuova data inizio successiva a vecchio inizio
						dateTimeUtils.isAfter(newStartDate, testata.getDataInizio(), true) &&
						// nuova data inizio precedente a vecchia fine
						dateTimeUtils.isBefore(newStartDate, testata.getDataFine(), true) &&
						// nuova data fine successiva a vecchio inizio
						dateTimeUtils.isAfter(newEndDate, testata.getDataInizio(), true) &&
						// nuova data fine precedente a vecchia fine
						dateTimeUtils.isBefore(newEndDate, testata.getDataFine(), true) &&
						// codice meccanica e' tra quelli disponibili
						isCodiceMeccanicaValid(node.get("codiceMeccanica"));
			} catch (Exception e) {
				log.error(String.format("Error find promozione testata con id %s", number), e);
			}
		}

		if (!correct && (testata != null)) {
			DbPromoAgCellUtils.putValue(node, "dataInizio", dateTimeUtils.toExcelDate(testata.getDataInizio()), true);
			DbPromoAgCellUtils.putValue(node, "dataFine", dateTimeUtils.toExcelDate(testata.getDataInizio()), true);
		}

		return correct;
	}

	private boolean isCodiceMeccanicaValid(JsonNode node) {
		if (node == null) {
			return true;
		}
		String codiceMeccanica = DbPromoAgCellUtils.getValue(node);
		return codiceMeccanica == null
				|| Arrays.stream(CODICI_MECCANICA_DIFFERENZIAZIONE).anyMatch(codice -> codice.trim().equals(codiceMeccanica));
	}

	public String updatePromoNegozioSelectedFlag(Long shopTypeId, String selectedFlag, Long promoId,
			@NonNull final String user, List<CanalePromozioneEntity> canali, List<String> gruppi, boolean isAdmin) throws Exception {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";

		TipoNegozioEntity tipoNegozio = promoService.findShopTypeById(shopTypeId);
		if (promoId != null) {
			PromozioneTestataEntity testata = promoService.findById(promoId);
			if ((testata != null)
					&& (PromoAcl.isEditable(testata) || PromoAcl.isSbloccoEsecuzione(testata))) {
				updateSelectedFlag(testata, tipoNegozio, selectedFlag, user);
				promoService.persist(testata, user);
				createJsonRowDataGivenPromozioneTestataEntity(testata, arrayNode, gruppi, isAdmin);
			}
		} else {
			List<PromozioneTestataEntity> testate = promoService.findAllNotCancelled(canali);
			testate.stream().filter(
					testata -> PromoAcl.isEditable(testata) || PromoAcl.isSbloccoEsecuzione(testata))
			.forEach(entity -> updateSelectedFlag(entity, tipoNegozio, selectedFlag, user));
			promoService.persist(testate, user);
			testate.forEach(entity -> createJsonRowDataGivenPromozioneTestataEntity(entity, arrayNode, gruppi, isAdmin));
		}
		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing scheda promozione tab meccaniche JSON row data", e);
		}

		return json;

	}

	/**
	 * se il flag
	 *
	 * <pre>
	 * selectedFlag
	 * </pre>
	 *
	 * ha il valore "isPresent" allora viene settato in tutti i negozi di tipo tipo
	 *
	 * <pre>
	 * tipoNegozioEntity
	 * </pre>
	 *
	 * che sono assegnati alla promozione
	 *
	 * <pre>
	 * promozioneTestataEntity
	 * </pre>
	 *
	 * @param promozioneTestataEntity
	 * @param tipoNegozioEntity
	 * @param selectedFlag
	 */
	private void updateSelectedFlag(@NonNull final PromozioneTestataEntity promozioneTestataEntity,
			@NonNull final TipoNegozioEntity tipoNegozioEntity, @NonNull String selectedFlag,
			@NonNull final String user) {

		final Optional<PromoShopsFlagEnum> promoShopsSelectedFlagEnum = convertFlag(selectedFlag);
		promozioneTestataEntity.getPromozioneNegozioEntities().stream()
		.filter(entity -> tipoNegozioEntity.getId().equals(entity.getNegozioEntity().getTipoNegozioEntity().getId()))
		.forEachOrdered(entity -> {
			AuditLogFiller.fillAuditLogFields(entity, user);
			entity.setSelezioneFlag(
					promoShopsSelectedFlagEnum.isPresent() ? promoShopsSelectedFlagEnum.get().getKey()
							: entity.getSelezioneFlag());
		});
	}

	public List<TipoNegozioEntity> getShopTypes() {
		try {
			return promoService.findAllTipoNegozioEntity();
		} catch (Exception e) {
			log.error("Error find list of all TipoNegozioEntity", e);
			return null;
		}
	}

	public List<String> getTipiConsegna() {
		try {
			return promoService.findAllTipoConsegna();
		} catch (Exception e) {
			log.error("Error find list of all TipoNegozioEntity", e);
			return null;
		}
	}

	public List<ZonaDto> getDistinctZone() {
		try {
			return promoService.findAllDistinctZone();
		} catch (Exception ex) {
			log.error("Error find list of all distinct Zone from NegozioEntity", ex);
			return null;
		}
	}

	public List<String> getDistinctCedi() {
		try {
			return promoService.findAllDistinctCedi();
		} catch (Exception ex) {
			log.error("Error find list of all distinct Zone from NegozioEntity", ex);
			return null;
		}
	}

	/**
	 * setta i valori di default per i negozi della promozione selezionata
	 *
	 * @param promoSelected
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String resetDefaultRowData(final Long promoSelected, @NonNull final String user,
			List<CanalePromozioneEntity> canali, List<String> gruppi, boolean isAdmin) throws Exception {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";
		List<PromozioneTestataEntity> testate = new ArrayList<>();
		if (promoSelected != null) {
			testate.add(promoService.findById(promoSelected));
		} else {
			testate.addAll(promoService.findAllNotCancelled(canali));
		}
		testate.stream().filter(Objects::nonNull).filter(
				testata -> PromoAcl.isEditable(testata) || PromoAcl.isSbloccoEsecuzione(testata))
		.forEach(entity -> setDefaultValueForNegoziPromo(entity, arrayNode, user, gruppi, isAdmin));

		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing scheda promozione tab meccaniche JSON row data", e);
		}

		return json;
	}

	/**
	 * setta i valori di default per i negozi associati alla testata corrente
	 *
	 * @param testata
	 * @param arrayNode
	 * @param user
	 */
	private void setDefaultValueForNegoziPromo(@NonNull final PromozioneTestataEntity testata,
			@NonNull final ArrayNode arrayNode, @NonNull final String user, List<String> gruppi, boolean isAdmin) {
		testata.getPromozioneNegozioEntities().stream().map(entity -> {
			entity.setSelezioneFlag(entity.getDefaultFlag());
			entity.setDataInizio(testata.getDataInizio());
			entity.setDataFine(testata.getDataFine());
			return entity;
		}).sorted(negoziSorterByCodice)
		.map(n -> mapRowDataIntoObjectNode(n, gruppi, isAdmin))
		.forEach(arrayNode::add);
		try {
			promoService.persist(testata, user);
		} catch (Exception e) {
			log.error("Error save default PromozioneTestataEntity", e);
		}
	}

	/**
	 * crea il rowData in base alla promozione selezionata (se in tab view) e ai
	 * flag passati tramite radio button
	 *
	 * @param codiceTestataSelezionato
	 * @param valoreRadioButton
	 * @param dbFiltersJson
	 * @return
	 */
	public String populateModifiedRowData(String codiceTestataSelezionato, String valoreRadioButton,
			String dbFiltersJson, List<CanalePromozioneEntity> canali, List<String> gruppi, boolean isAdmin) {
		final ArrayNode nodi = JsonUtils.getMapper().createArrayNode();
		String json = "";
		try {
			if ((codiceTestataSelezionato != null) && !codiceTestataSelezionato.isEmpty()) {
				final PromozioneTestataEntity testata = promoService.findById(new Long(codiceTestataSelezionato));
				filterOnRadioButton(testata, nodi, valoreRadioButton, gruppi, isAdmin);
			} else {
				final Map<String, String> userFiltersMap = userFilterUtils
						.createUserFiltersMapToQueryString(dbFiltersJson);

				List<PromozioneTestataEntity> testate = promoService.findAllNotCancelled(userFiltersMap, canali);

				testate.stream().sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio))
				.forEach(entity -> filterOnRadioButton(entity, nodi, valoreRadioButton, gruppi, isAdmin));
			}
		} catch (Exception e) {
			log.error("Error map only modified PromozioneTestataEntity into JSON object", e);
		}

		try {
			ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, nodi);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			log.error("Error processing scheda promozione tab meccaniche JSON row data", e);
		}

		return json;
	}

	public StreamingOutput streamModifiedRowData(String codiceTestataSelezionato, String valoreRadioButton,
			String dbFiltersJson, List<CanalePromozioneEntity> canali, List<String> gruppi, boolean isAdmin) {
		return output -> {
			try {
				// cominciamo a scrivere l'array ...
				output.write(curlyOpen);
				output.write(quote);
				output.write(DbPromoConstants.ROW_DATA.getBytes());
				output.write(quote);
				output.write(colon);
				output.write(arrayStart);

				if ((codiceTestataSelezionato != null) && !codiceTestataSelezionato.isEmpty()) {
					final PromozioneTestataEntity testata = promoService
							.findById(new Long(codiceTestataSelezionato));
					writeFilterOnRadioButton(testata, valoreRadioButton, output, false, gruppi, isAdmin);
				} else {
					final Map<String, String> userFiltersMap = userFilterUtils
							.createUserFiltersMapToQueryString(dbFiltersJson);

					List<PromozioneTestataEntity> testate = promoService.findAllNotCancelled(userFiltersMap,
							canali);

					final AtomicInteger counter = new AtomicInteger(0);
					testate.stream().sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio))
					.forEach(t -> {
						try {
							// scrivi i negozi
							if (!writeFilterOnRadioButton(t, valoreRadioButton, output,
									counter.incrementAndGet() > 1, gruppi, isAdmin) && (counter.get() == 1)) {
								// non ho scritto niente. Se sono sulla prima promozione
								// questo genera un json errato, quindi devo considerare
								// la prossima promozione come la prima
								counter.decrementAndGet();
							}
						} catch (IOException e1) {
							log.error("Error streaming rowData", e1);
						} catch (Exception bho) {
							log.error("unexpected exception while streaming ", bho);
						}
					});
				}
				try {
					output.write(arrayEnd);
					output.write(curlyClosed);
				} catch (Exception e2) {
					log.error("error closing output stream", e2);
				}

			} catch (Exception e4) {
				log.error("Error map only modified PromozioneTestataEntity into JSON object", e4);
			}
		};
	}

	/**
	 * filtra i negozi in base al flag passato dalla checkbox
	 *
	 * @param testata
	 * @param arrayNode
	 * @param radioChecked
	 */
	private void filterOnRadioButton(final PromozioneTestataEntity testata, ArrayNode arrayNode, String radioChecked,
			List<String> gruppi, boolean isAdmin) {
		Predicate<PromozioneNegozioEntity> filter = null;
		if (PromoShopRadioButtonEnum.VARIAZIONE_SU_DEFAULT.getValue().equalsIgnoreCase(radioChecked)) {
			filter = negozio -> (!negozio.getDefaultFlag().equals(negozio.getSelezioneFlag())
					|| !testata.getDataInizio().equals(negozio.getDataInizio())
					|| !testata.getDataFine().equals(negozio.getDataFine()));
		} else if (PromoShopRadioButtonEnum.VISUALIZZA_SI.getValue().equalsIgnoreCase(radioChecked)) {
			filter = negozio -> negozio.getSelezioneFlag().equals("1");
		} else {
			filter = negozio -> negozio.getSelezioneFlag().equals("0");
		}

		testata.getPromozioneNegozioEntities().stream().filter(filter.and(Objects::nonNull))
		.sorted(negoziSorterByCodice)
		.map(n -> mapRowDataIntoObjectNode(n, gruppi, isAdmin))
		.forEachOrdered(arrayNode::add);
	}

	/**
	 * filtra i valori in base al radio button e li scrive in out. Ritorna true se
	 * ha scritto almeno un valore
	 *
	 * @param testata
	 * @param radioChecked
	 * @param out
	 * @throws Exception
	 */
	private boolean writeFilterOnRadioButton(final PromozioneTestataEntity testata, String radioChecked,
			final OutputStream out, boolean shouldPrependAddComma, List<String> gruppi, boolean isAdmin) throws Exception {
		log.debug("Writing PromozioneNegozioEntity for PromozioneTestataEntity " + testata.getId());
		Predicate<PromozioneNegozioEntity> filter = null;
		AtomicBoolean ret = new AtomicBoolean(false);
		if (PromoShopRadioButtonEnum.VARIAZIONE_SU_DEFAULT.getValue().equalsIgnoreCase(radioChecked)) {
			filter = negozio -> (!negozio.getDefaultFlag().equals(negozio.getSelezioneFlag())
					|| !testata.getDataInizio().equals(negozio.getDataInizio())
					|| !testata.getDataFine().equals(negozio.getDataFine()));
		} else if (PromoShopRadioButtonEnum.VISUALIZZA_SI.getValue().equalsIgnoreCase(radioChecked)) {
			filter = negozio -> negozio.getSelezioneFlag().equals("1");
		} else {
			filter = negozio -> negozio.getSelezioneFlag().equals("0");
		}
		AtomicInteger counter = new AtomicInteger(0);

		List<PromozioneNegozioEntity> negozi = testata.getPromozioneNegozioEntities().stream()
				.filter(filter.and(Objects::nonNull)).sorted(negoziSorterByCodice).collect(Collectors.toList());
		if ((negozi.size() > 0) && shouldPrependAddComma) {
			out.write((byte) ',');
		}
		final int max = negozi.size();
		negozi.forEach(n -> {
			try {
				boolean b = streamRowDataIntoObjectNode(n, out, gruppi, isAdmin);
				if ((counter.incrementAndGet() < max) && b) {
					log.debug("preparing for next PromozioneNegozioEntity");
					out.write((byte) ',');
				}
			} catch (Exception e) {
				log.error("error serializing data", e);
			} finally {
				ret.set(true);
			}
		});
		log.debug("Done writing PromozioneNegozioEntity for PromozioneTestataEntity " + testata.getId());
		return ret.get();
	}

	/**
	 * Questo metodo legge un file finchè trova una riga vuota cercando dei codici
	 * negozio validi. Se trova dei codici validi li aggiunge alla promozione
	 * corrente (id passato in input) Per ognuno setta il flag di selezione a: - SI
	 * se trova il codice negozio ha una sigla associata in MUI_NEGOZIO - NO se
	 * trova il codice negozio non ha una sigla associata in MUI_NEGOZIO
	 *
	 * @param file
	 * @param promoId
	 * @param username
	 * @return
	 */
	public PromoShopUpdateDto readAndUploadFileUntilEmptyRow(File file, final Long promoId, @NonNull final String username) {
		if (promoId == null) {
			log.warn("PromozioneTestataEntity with id " + promoId);
			return PromoShopUpdateDto.error("Impossibile caricare il file");
		}
		try {
			PromozioneTestataEntity testata = promoService.findById(promoId);
			if (testata != null) {
				final StatoPromozioneEntity statoCorrente = PromoAcl.getStatoCorrente(testata);
				if ((statoCorrente == null) || !PromoAcl.isEditableEndDate(statoCorrente)) {
					return PromoShopUpdateDto.warning(
							String.format("Lo stato della promozione %s non consente il caricamento dei negozi",
									testata.getDescrizione()));
				}
				Integer maxRows = applicationProperties.getProperty(ApplicationProperties.MAX_UPLOAD_RECORDS, ApplicationProperties.DEFAULT_MAX_UPLOAD_RECORDS);
				if ( maxRows <= 0 ){
					return PromoShopUpdateDto.warning("L'upload di record da file e' stato disattivato: contattare l'assistenza");
				}
				final Set<String> promoNegoziSigles = testata.getPromozioneNegozioEntities().stream()
						.map(PromozioneNegozioEntity::getNegozioEntity).map(NegozioEntity::getSigla)
						.collect(Collectors.toCollection(HashSet::new));

				Set<ShopItemUpload> shopItemUploads = uploadExcelService.readFileUntilEmptyRow(file, promoNegoziSigles, maxRows);
				if ( shopItemUploads == null ){
					return PromoShopUpdateDto.error("Numero massimo di record caricabili " + maxRows + " superato");
				}
				if (shopItemUploads.isEmpty()) {
					// non ci sono sigle
					return PromoShopUpdateDto.warning("Il file risulta vuoto");
				}

				List<String> sigleValidated = shopItemUploads.stream().filter(ShopItemUpload::isValid)
						.map(ShopItemUpload::getSigla).collect(Collectors.toList());
				if (sigleValidated.isEmpty()) {
					return PromoShopUpdateDto.warning("Il file non contiene sigle valide");
				}
				testata.getPromozioneNegozioEntities().stream().forEach(promozioneNegozioEntity -> {
					if (sigleValidated.contains(promozioneNegozioEntity.getNegozioEntity().getSigla())) {
						promozioneNegozioEntity.setSelezioneFlag(PromoShopsFlagEnum.OBBLIGATORIO.getKey());
					} else {
						promozioneNegozioEntity.setSelezioneFlag(PromoShopsFlagEnum.FACOLTATIVO.getKey());
					}
				});
				try {
					promoService.persist(testata, username);
					return PromoShopUpdateDto.info("Caricamento lista negozi effettuato");
				} catch (Exception e) {
					log.error("Error save default PromozioneTestataEntity", e);
				}
			} else {
				log.warn("PromozioneTestataEntity with id % " + promoId + "is null");
				return null;
			}
		} catch (IOException e) {
			log.error("Errore di caricamento file", e);
		} catch (Exception e) {
			log.error(String.format("PromozioneTestataEntity with id %s not found", promoId), e);
		}
		return PromoShopUpdateDto.error("Errore caricamento lista negozi, contattare l'assistenza tecnica");
	}

	/**
	 * bulk update dei negozi
	 *
	 * @param param
	 */
	public int bulkUpdate(PromoShopBulkParam param, String user, List<CanalePromozioneEntity> canali) {
		final AtomicInteger result = new AtomicInteger(0);
		List<PromozioneNegozioEntity> negozi = null;
		if (param.getNegozi() != null) {
			// la lista dei negozi e' nell'array
			if (param.getNegozi().length > 0) {
				negozi = negoziPromoService.findById(Arrays.asList(param.getNegozi()));
			} else {
				negozi = new ArrayList<>();
			}
		} else {
			// la lista dei negozi e' nella promozione
			if (param.getIdPromozione() != null) {
				// se c'e'
				try {
					PromozioneTestataEntity testata = promoService.findById(param.getIdPromozione());
					negozi = new ArrayList<>(testata.getPromozioneNegozioEntities());
				} catch (Exception e) {
					log.error("Errore di lettura testata promozionale con id " + param.getIdPromozione(), e);
					result.set(-1);
				}
			} else {
				// li devo leggere tutti
				try {
					negozi = promoService.read(canali).stream()
							.map(PromozioneTestataEntity::getPromozioneNegozioEntities).flatMap(Set::stream)
							.collect(Collectors.toList());
				} catch (Exception e) {
					log.error("Errore nel recupero della lista delle promozioni", e);
					result.set(-1);
				}
			}
		}
		TipoNegozioEntity tipoNegozio = null;
		if (result.get() >= 0) {
			if (param.getIdTipoNegozio() != null) {
				try {
					tipoNegozio = promoService.findShopTypeById(param.getIdTipoNegozio());
				} catch (Exception e) {
					log.error("errore nella conversione del tipo negozio con id " + param.getIdTipoNegozio(), e);
					result.set(-1);
				}
			}
		}

		if ((result.get() >= 0) && (negozi != null) && (negozi.size() > 0)) {
			// posso aggiornare SOLO i negozi la cui promozione lo permette
			Optional<PromoShopsFlagEnum> promoShopsSelectedFlagEnum = null;
			final Long idTipoNegozio = tipoNegozio != null ? tipoNegozio.getId() : null;
			if (!ShopUpdateTypeEnum.RESET.equals(param.getType())) {
				promoShopsSelectedFlagEnum = convertFlag(param.getFlag());
			}
			final PromoShopsFlagEnum flag = ((promoShopsSelectedFlagEnum != null)
					&& promoShopsSelectedFlagEnum.isPresent()) ? promoShopsSelectedFlagEnum.get() : null;
			negozi.stream()
					.filter(negozio -> PromoAcl.isShopEditable(negozio.getPromozioneTestataEntity())
							|| PromoAcl.isSbloccoEsecuzione(negozio.getPromozioneTestataEntity())
							|| checkShopEditWhileExecutionInProgressEnabled(negozio.getPromozioneTestataEntity()) // messo in fondo come ultima ratio perche' accede al db
					)
					.forEach(negozio -> {
						if (updateNegozio(negozio, idTipoNegozio, flag, param.getTipoConsegna(), param.getType(),
								param.getZonaSocieta(), param.getZonaCodice(), param.getCodiceCedi())) {
							result.incrementAndGet();
						}
						negozio.getPromozioneTestataEntity().addPromozioneNegozioEntity(negozio);
					});

			// fatto update, adesso salviamo su db
			final List<PromozioneTestataEntity> testate = negozi.stream()
					.map(PromozioneNegozioEntity::getPromozioneTestataEntity).distinct().collect(Collectors.toList());
			promoService.persist(testate, user);
		}
		return result.get();
	}

	/**
	 * Questo metodo aggiorna un negozio di una promozione in base a parametri di
	 * input passati
	 * @param entity        promoNegozio da aggiornare
	 * @param idTipoNegozio id tipo negozio
	 * @param flag          flag
	 * @param tipoConsegna  tipo consegna
	 * @param type          tipo update da effettuare
	 * @param zonaSocieta   codice societa
	 * @param zonaCodice    codice zona
	 * @param codiceCedi    codice cedi
	 */
	private Boolean updateNegozio(final PromozioneNegozioEntity entity, final Long idTipoNegozio,
								  final PromoShopsFlagEnum flag, final String tipoConsegna,
								  final ShopUpdateTypeEnum type, final String zonaSocieta, final String zonaCodice, final String codiceCedi) {
		boolean isUpdated = false;
		switch (type) {
			case RESET:
				// reset a default
				entity.setSelezioneFlag(entity.getDefaultFlag());
				entity.setDataInizio(entity.getPromozioneTestataEntity().getDataInizio());
				entity.setDataFine(entity.getPromozioneTestataEntity().getDataFine());
				isUpdated = true;
				break;
			case NEGOZIO:
				if (entity.getNegozioEntity() != null
						&& entity.getNegozioEntity().getTipoNegozioEntity().getId().equals(idTipoNegozio)
						&& flag != null) {
					entity.setSelezioneFlag(flag.getKey());
					isUpdated = true;
				}
				break;
			case CONSEGNA:
				if (entity.getNegozioEntity() != null && entity.getNegozioEntity().getTipoConsegna() != null
						&& entity.getNegozioEntity().getTipoConsegna().equalsIgnoreCase(tipoConsegna)
						&& flag != null) {
					entity.setSelezioneFlag(flag.getKey());
					isUpdated = true;
				}
				break;
			case ZONA:
				if (entity.getNegozioEntity() != null && entity.getNegozioEntity().getSocieta().equalsIgnoreCase(zonaSocieta)
						&& entity.getNegozioEntity().getCodiceZona().equalsIgnoreCase(zonaCodice)
						&& flag != null) {
					entity.setSelezioneFlag(flag.getKey());
					isUpdated = true;
				}
				break;
			case CEDI:
				if ( entity.getNegozioEntity() != null && entity.getNegozioEntity().getCedi().equalsIgnoreCase(codiceCedi) ) {
					entity.setSelezioneFlag(flag.getKey());
					isUpdated = true;
				}
				break;
			case ALL:
				if (entity.getNegozioEntity() != null && flag != null) {
					entity.setSelezioneFlag(flag.getKey());
					isUpdated = true;
				}
				break;
			default:
				log.warn(String.format("Update type %s not managed", type));
				break;
		}
		return isUpdated;
	}

	//feature #5153
	public boolean checkShopEditWhileExecutionInProgressEnabled(@NonNull PromozioneTestataEntity t){
		PromozioneStatoEntity s = t.getPromozioneStatoEntities().stream().filter(e->e.getDataFineStato()==null).findFirst().orElse(null);
		if ( s == null ) return false;
		List<CfgAzioniEntity> list = cfgStatiCanaleConsentitiService.get().findAzioniConsentByCanaleAndStato(t.getCanalePromozioneEntity(), s.getStatoPromozioneEntity());

		return list.stream().anyMatch(e->ActionEnum.EDIT_SHOP_WHILE_EXECUTION_IN_PROGRESS.getName().equals(e.getCodice()));
	}
}
