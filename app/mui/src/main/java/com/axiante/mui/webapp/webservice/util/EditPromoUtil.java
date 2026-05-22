package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.actions.ActionEnum;
import com.axiante.mui.dbpromo.actions.ActionTypeEnum;
import com.axiante.mui.dbpromo.actions.ElementFieldEnum;
import com.axiante.mui.dbpromo.actions.FormEnum;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.webapp.business.ActionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@Dependent
public class EditPromoUtil {

	@Inject
	private Instance<ActionService> actionServiceInstance;

	private final DateTimeUtils dateTimeUtils = new DateTimeUtils();

	public String createDescrizioneEstesa(PromozioneTestataEntity promoEntity) {
		final LocalDateTime from = new java.sql.Timestamp(promoEntity.getDataInizio().getTime()).toLocalDateTime();
		final LocalDateTime to = new java.sql.Timestamp(promoEntity.getDataFine().getTime()).toLocalDateTime();
		return dateTimeUtils.calculateExtendedDescription(promoEntity.getCodicePromozione(),
				Date.from(from.atZone(ZoneId.systemDefault()).toInstant()),
				Date.from(to.atZone(ZoneId.systemDefault()).toInstant()), promoEntity.getDescrizione());
	}

	public String createRowData(List<PromozioneTestataEntity> promozioneTestataEntities, List<String> gruppi, boolean isAdmin) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";
		promozioneTestataEntities.stream().forEach(t -> arrayNode.add(createRowNode(t, gruppi, isAdmin)));
		try {
			final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
			objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(objectNode);
		} catch (final JsonProcessingException e) {
			log.error("Error processing modifica promozione JSON row data", e);
		}
		return json;
	}

	private ObjectNode createRowNode(PromozioneTestataEntity promozioneTestataEntity, List<String> gruppi, boolean isAdmin) {
		final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();
		final StatoPromozioneEntity stato = PromoAcl.getStatoCorrente(promozioneTestataEntity);
		final CanalePromozioneEntity canale = promozioneTestataEntity.getMuiCanalePromozione();
		String descrizioneGruppo = null;
		String descrizioneCanale = null;
		if (canale != null) {
			final GruppoPromozioneEntity gruppoPromozioneEntity = canale.getGruppoPromozioneEntity();
			descrizioneGruppo = gruppoPromozioneEntity != null ? gruppoPromozioneEntity.getDescrizione() : "";
			descrizioneCanale = canale.getGruppoPromozioneEntity() != null ? canale.getDescrizione() : "";
		}
		final ActionService actionService = actionServiceInstance.get();
		final boolean isNuovaDescrizioneEditable = isAdmin || actionService.applyRule(ActionEnum.EDIT_DESCRIZIONE,
				canale, stato, ActionTypeEnum.ACTIVE, FormEnum.MODIFICA_PROMO, ElementFieldEnum.FIELD_NUOVA_DESCRIZIONE);
		final boolean isNuovaDataInizioEditable = isAdmin || actionService.applyRule(ActionEnum.EDIT_DATA_INIZIO,
				canale, stato, ActionTypeEnum.ACTIVE, FormEnum.MODIFICA_PROMO, ElementFieldEnum.FIELD_NUOVA_DATA_INIZIO);
		final boolean isNuovaDataFineEditable = isAdmin || actionService.applyRule(ActionEnum.EDIT_DATA_FINE,
				canale, stato, ActionTypeEnum.ACTIVE, FormEnum.MODIFICA_PROMO, ElementFieldEnum.FIELD_NUOVA_DATA_FINE);
		final boolean isNuovaOraInizioEditable = isAdmin || actionService.applyRule(ActionEnum.EDIT_FASCIA_ORARIA,
				canale, stato, ActionTypeEnum.ACTIVE, FormEnum.MODIFICA_PROMO, ElementFieldEnum.FIELD_NUOVA_ORA_INIZIO);
		final boolean isNuovaOraFineEditable = isAdmin || actionService.applyRule(ActionEnum.EDIT_FASCIA_ORARIA,
				canale, stato, ActionTypeEnum.ACTIVE, FormEnum.MODIFICA_PROMO, ElementFieldEnum.FIELD_NUOVA_ORA_FINE);
		final boolean isNuoveNoteEditable = isAdmin || actionService.applyRule(ActionEnum.EDIT_NOTE,
				canale, stato, ActionTypeEnum.ACTIVE, FormEnum.MODIFICA_PROMO, ElementFieldEnum.FIELD_NOTE);
		final boolean isDeletable = isAdmin || actionService.applyRule(ActionEnum.BUTTON_ELIMINA,
				canale, stato, ActionTypeEnum.ACTIVE, FormEnum.MODIFICA_PROMO, ElementFieldEnum.BUTTON_ELIMINA);

		DBPromoAgCell cell = DBPromoAgCell.builder().name("Id").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(promozioneTestataEntity.getId()))
				.build();
		map.put("id", cell);

		cell = DBPromoAgCell.builder().name("Codice").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(promozioneTestataEntity.getCodicePromozione() == null ? ""
						: promozioneTestataEntity.getCodicePromozione())
				.build();
		map.put("codice", cell);

		cell = DBPromoAgCell.builder().name("Anno").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
				.value(promozioneTestataEntity.getAnno() == null ? "" : promozioneTestataEntity.getAnno()).build();
		map.put("anno", cell);

		cell = DBPromoAgCell.builder().name("Descrizione").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(promozioneTestataEntity.getDescrizioneEstesa() == null ? ""
						: promozioneTestataEntity.getDescrizioneEstesa())
				.build();
		map.put("descrizione", cell);

		cell = DBPromoAgCell.builder().name("Copia Automatica").editable(true)
				.type(DBPromoCellTypeEnum.CHECKBOX.getType())
				.value(promozioneTestataEntity.getFlCopiaAutomatica() != null
						? Boolean.toString(promozioneTestataEntity.getFlCopiaAutomatica())
						: "false")
				.build();
		map.put("copiaAutomatica", cell);

		final DataTypeParams dataTypeParamsPojo = new DataTypeParams();
		dataTypeParamsPojo.setLength(40);
		cell = DBPromoAgCell.builder().name("Nuova Descrizione").editable(isNuovaDescrizioneEditable)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(promozioneTestataEntity.getNewDescrizione() == null ? ""
						: promozioneTestataEntity.getNewDescrizione())
				.dataTypeParams(dataTypeParamsPojo).build();
		map.put("newDescrizione", cell);

		cell = DBPromoAgCell.builder().name("Gruppo").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(descrizioneGruppo == null ? "" : descrizioneGruppo)
				.build();
		map.put("gruppo", cell);

		cell = DBPromoAgCell.builder().name("Canale").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(descrizioneCanale == null ? "" : descrizioneCanale)
				.build();
		map.put("canale", cell);

		cell = DBPromoAgCell.builder().name("Data Inizio").editable(false)
				.type(DBPromoCellTypeEnum.DATE.getType()).value(promozioneTestataEntity.getDataInizio() == null ? ""
						: dateTimeUtils.toExcelDate(promozioneTestataEntity.getDataInizio()))
				.build();
		map.put("dataInizio", cell);

		cell = DBPromoAgCell.builder().name("Nuova Data Inizio").editable(isNuovaDataInizioEditable)
				.type(DBPromoCellTypeEnum.DATE.getType()).value(promozioneTestataEntity.getNewDataInizio() == null ? ""
						: dateTimeUtils.toExcelDate(promozioneTestataEntity.getNewDataInizio()))
				.build();
		map.put("newDataInizio", cell);

		cell = DBPromoAgCell.builder().name("Data Fine").editable(false)
				.type(DBPromoCellTypeEnum.DATE.getType()).value(promozioneTestataEntity.getDataFine() == null ? ""
						: dateTimeUtils.toExcelDate(promozioneTestataEntity.getDataFine()))
				.build();
		map.put("dataFine", cell);

		cell = DBPromoAgCell.builder().name("Nuova Data Fine").editable(isNuovaDataFineEditable)
				.type(DBPromoCellTypeEnum.DATE.getType()).value(promozioneTestataEntity.getNewDataFine() == null ? ""
						: dateTimeUtils.toExcelDate(promozioneTestataEntity.getNewDataFine()))
				.build();
		map.put("newDataFine", cell);

		cell = DBPromoAgCell.builder().name("Ora Inizio").editable(false)
				.type(DBPromoCellTypeEnum.TIME.getType()).value(promozioneTestataEntity.getOraInizio() == null
						? "" : DateTimeUtils.toTime(promozioneTestataEntity.getOraInizio()))
				.build();
		map.put("oraInizio", cell);

		cell = DBPromoAgCell.builder().name("Nuova Ora Inizio").editable(isNuovaOraInizioEditable)
				.type(DBPromoCellTypeEnum.TIME.getType()).value(promozioneTestataEntity.getNewOraInizio() == null
						? "" : DateTimeUtils.toTime(promozioneTestataEntity.getNewOraInizio()))
				.build();
		map.put("newOraInizio", cell);

		cell = DBPromoAgCell.builder().name("Ora Fine").editable(false)
				.type(DBPromoCellTypeEnum.TIME.getType()).value(promozioneTestataEntity.getOraFine() == null
						? "" : DateTimeUtils.toTime(promozioneTestataEntity.getOraFine()))
				.build();
		map.put("oraFine", cell);

		cell = DBPromoAgCell.builder().name("Nuova Ora Fine").editable(isNuovaOraFineEditable)
				.type(DBPromoCellTypeEnum.TIME.getType()).value(promozioneTestataEntity.getNewOraFine() == null
						? "" : DateTimeUtils.toTime(promozioneTestataEntity.getNewOraFine()))
				.build();
		map.put("newOraFine", cell);

		cell = DBPromoAgCell.builder().name("Stato").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
				.value((stato != null)
						&& ((stato.getCodiceStato() == null) || (stato.getLabel() == null))
						? ""
								: String.format("%s - %s", stato.getCodiceStato(),
										stato.getLabel()))
				.build();
		map.put("stato", cell);

		cell = DBPromoAgCell.builder().name("Note").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(promozioneTestataEntity.getNoteMarketing() == null ? ""
						: promozioneTestataEntity.getNoteMarketing())
				.build();
		map.put("noteMarketing", cell);

		cell = DBPromoAgCell.builder().name("Nuove Note").editable(isNuoveNoteEditable)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(promozioneTestataEntity.getNewNoteMarketing() == null ? ""
						: promozioneTestataEntity.getNewNoteMarketing())
				.build();
		map.put("newNoteMarketing", cell);

		cell = DBPromoAgCell.builder().name("Messaggio").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value("").build();
		map.put("messaggio", cell);

		cell = DBPromoAgCell.builder().name("codicePromozione").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(promozioneTestataEntity.getCodicePromozione() == null ? ""
						: promozioneTestataEntity.getCodicePromozione())
				.build();
		map.put("codicePromozione", cell);

		cell = DBPromoAgCell.builder().name("codiceStato").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value((stato != null) && (stato.getCodiceStato() == null) ? ""
						: stato.getCodiceStato())
				.build();
		map.put("codiceStato", cell);

		cell = DBPromoAgCell.builder().name("isDeletable").editable(false)
				.type(DBPromoCellTypeEnum.BOOLEAN.getType())
				.value(String.valueOf(isDeletable)).build();
		map.put("isDeletable", cell);

		return JsonUtils.getMapper().valueToTree(map);
	}

}
