package com.axiante.mui.webapp.webservice.util.pianificazione;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.DBPromoComplementariConstants;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import com.axiante.mui.dbpromo.persistence.service.UploadFidatyService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class ComplementaryColumnDef extends AbstractColumnDef implements DynamicColumnDef {
	@Inject
	UploadFidatyService service;
	@Inject
	ApplicationProperties applicationProperties;

	@Override
	public String generateColumnDefByPromoConfiguration(final PromozioneTestataEntity promozioneTestataEntity,
			String hiddenColumns, String grid, String contesto, boolean contestoRequired) {
		return loadColumnDefFromFile("pianificazione_complementari_columnDef.json", hiddenColumns, grid,
				contesto, contestoRequired);
	}

	@Override
	public String generateRowDataByPromoConfiguration(String idPromozione, Boolean isUserAdmin, List<String> codiciGruppo, String operationMessage) {
		try {
			final ArrayNode nodes = JsonUtils.getMapper().createArrayNode();
			service.findValidByPromozione(new Long(idPromozione)).stream().map(u -> map(u)).map(row -> {
				// trasformo la lista in una hashmap
				return row.stream().collect(Collectors.toMap(DBPromoAgCell::getName, Function.identity()));
			}).map(row -> {
				// la map in in object node
				return JsonUtils.getMapper().valueToTree(row);
			}).forEach(node -> nodes.add((JsonNode) node));
			final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, nodes);
			return JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (Exception e) {
			log.error("Error reading list of uploaded data", e);
		}
		return "";
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

	private List<DBPromoAgCell> map(@NonNull final UploadFidayEntity entity) {
		PromozionePianificazioneEntity pianificazione = entity.getPromozionePianificazioneEntity();
		DateTimeUtils utils = new DateTimeUtils();
		List<DBPromoAgCell> list = new ArrayList<>();
		list.add(DBPromoAgCell.builder().name("ID_UPLOAD").value("" + entity.getId()).build());
		list.add(DBPromoAgCell.builder().name("TIPO_ELEMENTO").value(pianificazione.getTipoElemento()).build());
		list.add(DBPromoAgCell.builder().name("ID_MECCANICA")
				.value(pianificazione.getMeccanicaEntity().getCodiceMeccanica()).build());
		list.add(DBPromoAgCell.builder().name("NUM_SET").value(pianificazione.getNumSet()).build());
		list.add(DBPromoAgCell.builder().name("BUONO_SCONTO_RADICE").value(
				pianificazione.getBuonoScontoRadice() != null ? pianificazione.getBuonoScontoRadice().toString() : null)
				.build());
		list.add(DBPromoAgCell.builder().name("BUONO_SCONTO_PROGRESSIVO")
				.value(pianificazione.getBuonoScontoProgressivo() != null
						? pianificazione.getBuonoScontoProgressivo().toString()
						: null)
				.build());
		list.add(DBPromoAgCell.builder().name("DATA_PUBBLICAZIONE")
				.value(utils.toExcelDate(entity.getDataPubblicazione())).type(DBPromoCellTypeEnum.DATE.getType())
				.build());
		list.add(DBPromoAgCell.builder().name("USER").value(entity.getUserUpload()).build());
		list.add(DBPromoAgCell.builder().name("NOME_FILE_ORIGINALE").value(entity.getOriginalFileName()).build());
		list.add(DBPromoAgCell.builder().name("NOME_FILE_SUL_SERVER").value(entity.getUploadedFileName()).build());
		list.add(DBPromoAgCell.builder().name("CHECKSUM_FILE").value(entity.getChecksumFile()).build());
		list.add(DBPromoAgCell.builder().name("FILE_SCARTI").value("" + checkScarto(entity.getUploadedFileName()))
				.build());
		list.add(DBPromoAgCell.builder().name("DATA_INIZIO_PUBBLICAZIONE")
				.value(utils.toExcelDate(entity.getDataInizioPublicazione())).type(DBPromoCellTypeEnum.DATE.getType())
				.build());
		list.add(DBPromoAgCell.builder().name("DATA_FINE_PUBBLICAZIONE")
				.value(utils.toExcelDate(entity.getDataFinePublicazione())).type(DBPromoCellTypeEnum.DATE.getType())
				.build());
		list.add(DBPromoAgCell.builder().name("DESCRIZIONE").value(entity.getDescription()).build());
		return list;
	}

	public Boolean checkScarto(@NonNull final String fileName) {
		String checkZone = applicationProperties
				.getProperty(DBPromoComplementariConstants.PERCORSO_SCARTI_COMPLEMENTARI);
		if (checkZone == null) {
			log.error("missing configuration property " + DBPromoComplementariConstants.PERCORSO_SCARTI_COMPLEMENTARI);
			return false;
		}
		final String path = createPathScarto(fileName);
		try {
			File file = new File(path);
			if (file.exists()) {
				return true;
			} else {
				log.debug("file di scarto per " + fileName + " non trovato");
			}
		} catch (Exception e) {
			log.error("Errore durante il controllo dei file di scarto", e);
		}
		return false;
	}

	private String createPathScarto(@NonNull final String fileName) {
		final String checkZone = applicationProperties
				.getProperty(DBPromoComplementariConstants.PERCORSO_SCARTI_COMPLEMENTARI);
		if (checkZone != null) {
			return checkZone + File.separator + "SCARTI_" + fileName;
		}
		return null;
	}

	public File getScarto(@NonNull Long idUpload) {
		UploadFidayEntity upload = service.findById(idUpload);
		if (upload != null) {
			if (checkScarto(upload.getUploadedFileName())) {
				return new File(createPathScarto(upload.getUploadedFileName()));
			}
		}
		return null;
	}
}
