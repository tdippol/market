package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.MuiPromoDbPromoService;
import com.axiante.mui.webapp.business.OwnershipService;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Comparator;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;

@Slf4j
@Dependent
public class ViewPromoUtil {

	@Inject
	private Instance<OwnershipService> ownershipServiceInstance;

	@Inject
	private Instance<MuiPromoDbPromoService> selezionaPromoServiceInstance;

	private DateTimeUtils dateTimeUtils = new DateTimeUtils();

	@Inject
	private Instance<ItemService> itemServiceInstance;
	/**
	 * Crea rowData per la pagina 'Visualizza Promozioni'
	 *
	 * @param testate lista delle testate
	 * @param gruppi  lista dei codici gruppo dell'utente
	 * @param isAdmin true se utente ADMIN, false altrimenti
	 * @return
	 */
	public String createRowData(List<PromozioneTestataEntity> testate, List<String> gruppi, boolean isAdmin) {
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		String json = "";
		try {
			testate.forEach(t -> arrayNode.add(createRowNode(t,
					isAdmin || ownershipServiceInstance.get().hasOwnership(t, gruppi))));
			final ObjectNode node = JsonUtils.getMapper().createObjectNode();
			node.set(DbPromoConstants.ROW_DATA, arrayNode);
			json = JsonUtils.getMapper().writeValueAsString(node);
		} catch (JsonProcessingException ex) {
			log.error("Error processing 'Visualizza Promozione' JSON row data", ex);
		}
		return json;
	}

	/**
	 * Crea singolo nodo JSON per la testata
	 *
	 * @param testata      singola testata
	 * @param hasOwnership true se utente ha ownership sulla testata, false altrimenti
	 * @return
	 */
	private ObjectNode createRowNode(PromozioneTestataEntity testata, boolean hasOwnership) {
		final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();

		DBPromoAgCell cell = DBPromoAgCell.builder().name("Id").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(testata.getId()))
				.build();
		map.put("id", cell);

		cell = DBPromoAgCell.builder().name("Anno").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
				.value(testata.getAnno() == null ? "" : testata.getAnno()).build();
		map.put("anno", cell);

		String descrizioneCanale = testata.getMuiCanalePromozione() != null
					&& testata.getMuiCanalePromozione().getDescrizione() != null
				? testata.getMuiCanalePromozione().getDescrizione()
				: "";
		cell = DBPromoAgCell.builder().name("Canale").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType()).value(descrizioneCanale)
				.build();
		map.put("canale", cell);

		cell = DBPromoAgCell.builder().name("Promozione").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(testata.getDescrizioneEstesa() == null ? "" : testata.getDescrizioneEstesa())
				.build();
		map.put("descrizioneEstesa", cell);

		cell = DBPromoAgCell.builder().name("Data Inizio").editable(false)
				.type(DBPromoCellTypeEnum.DATE.getType()).value(testata.getDataInizio() == null ? ""
						: dateTimeUtils.toExcelDate(testata.getDataInizio()))
				.build();
		map.put("dataInizio", cell);

		cell = DBPromoAgCell.builder().name("Data Fine").editable(false)
				.type(DBPromoCellTypeEnum.DATE.getType()).value(testata.getDataFine() == null ? ""
						: dateTimeUtils.toExcelDate(testata.getDataFine()))
				.build();
		map.put("dataFine", cell);

		cell = DBPromoAgCell.builder().name("Ora Inizio").editable(false).type(DBPromoCellTypeEnum.TIME.getType())
				.value(testata.getOraInizio() == null ? "" : DateTimeUtils.toTime(testata.getOraInizio()))
				.build();
		map.put("oraInizio", cell);

		cell = DBPromoAgCell.builder().name("Ora Fine").editable(false).type(DBPromoCellTypeEnum.TIME.getType())
				.value(testata.getOraFine() == null ? "" : DateTimeUtils.toTime(testata.getOraFine()))
				.build();
		map.put("oraFine", cell);

		final StatoPromozioneEntity statoPromozioneEntity = PromoAcl.getStatoCorrente(testata);
		cell = DBPromoAgCell.builder().name("Stato Promozione").editable(false)
				.type(DBPromoCellTypeEnum.STRING.getType())
				.value(statoPromozioneEntity == null
							|| statoPromozioneEntity.getCodiceStato() == null
							|| statoPromozioneEntity.getLabel() == null
						? "" : String.format("%s - %s", statoPromozioneEntity.getCodiceStato(), statoPromozioneEntity.getLabel()))
				.build();
		map.put("stato", cell);

		cell = DBPromoAgCell.builder().name("Note").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
				.value(testata.getNoteMarketing() == null ? "" : testata.getNoteMarketing())
				.build();
		map.put("noteMarketing", cell);

		cell = DBPromoAgCell.builder().name("codicePromozione").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
				.value(testata.getCodicePromozione() == null ? "" : testata.getCodicePromozione())
				.build();
		map.put("codicePromozione", cell);

		cell = DBPromoAgCell.builder().name("Owner").editable(false).type(DBPromoCellTypeEnum.CHECKBOX.getType())
				.value(String.valueOf(hasOwnership)).build();
		map.put("owner", cell);

		String riferimento = "";
		if ( testata.getCanalePromozioneEntity() != null && testata.getCanalePromozioneEntity().getCodiceCanale() != null ) {
			switch(testata.getCanalePromozioneEntity().getCodiceCanale().intValue()) {
			case 25:
				// Smaltimenti fine promo
				if ( testata.getCodicePromoRiferimento() != null ) {
					final MuiPromoDbPromoEntity e = selezionaPromoServiceInstance.get().findByCodicePromozione(testata.getCodicePromoRiferimento());
					if ( e!= null && e.getDescrizioneEstesa() != null )
						riferimento = e.getDescrizioneEstesa();
				}
				break;
			}
		}
		cell = DBPromoAgCell.builder().name("Riferimento").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
				.value(riferimento).build();
		map.put("riferimento", cell);

		if ( testata.getCanalePromozioneEntity() != null &&  testata.getCanalePromozioneEntity().getFlCalcolaReparto() ){
			cell = DBPromoAgCell.builder().name("Reparto").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
					.value(calcolaReparto(testata)).build();
			map.put("reparto", cell);
		}

		return JsonUtils.getMapper().valueToTree(map);
	}

	public String calcolaReparto(PromozioneTestataEntity testata){
		PromozionePianificazioneEntity pianificazione = testata.getPromozionePianificazioneEntities().stream()
                .filter(p -> PromoPianificazioneEnum.ARTICOLO.getTipoElemento().equals(p.getTipoElemento()))
				.min(Comparator.comparing(PromozionePianificazioneEntity::getId)).orElse(null);
		if ( pianificazione != null ){
			ItemEntity item = itemServiceInstance.get().findById(Long.valueOf(pianificazione.getCodiceElemento()));
			if( item != null ){
				try {
					return item.getSubGrmEntity().getGrmEntity().getMuiCategoria().getRepartoEntity().getDescrizione();
				} catch (Exception e){
					log.error("Errore nel calcolo del reparto", e);
				}
			}
		}
		return "";
	}
}
