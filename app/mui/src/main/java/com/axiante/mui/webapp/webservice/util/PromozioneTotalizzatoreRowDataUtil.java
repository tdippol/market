package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.ComboBoxValues;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.PianoMediaTipoData;
import com.axiante.mui.dbpromo.business.utils.ComboBoxFactory;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneTotalizzatoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaControlliEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import com.axiante.mui.webapp.business.supportimedia.SupportoMediaCheckEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

@Dependent
@Slf4j
public class PromozioneTotalizzatoreRowDataUtil extends AbstractRowDataUtil {

    @Inject
    private Instance<PianoMediaPromoDbpromoService> pianoMediaPromoServiceInstance;

    @Inject
    private Instance<ComboBoxFactory> comboBoxFactoryInstance;

    transient DateTimeUtils dateTimeUtils = new DateTimeUtils();

    public String createTotaliRowData(@NonNull Set<PianificazioneTotalizzatoriEntity> totalizzatori, String messaggio) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            totalizzatori.stream().sorted(Comparator.comparing(PianificazioneTotalizzatoriEntity::getId))
                    .forEach(s -> arrayNode.add(createPianificazioneTotalizzatoreRowNode(s)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            if (messaggio != null) {
                DbPromoAgCellUtils.putValue(objectNode, "updateMessage", messaggio, false);
            }
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available 'PianificazioneTotalizzatoreEntity'", ex);
            return "";
        }
    }

    private ObjectNode createPianificazioneTotalizzatoreRowNode(PianificazioneTotalizzatoriEntity totalizzatore) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("Id Totalizzatore").editable(false).type(cellStringType)
                    .value(String.valueOf(totalizzatore.getId())).build();
            map.put("idTotalizzatore", cell);

            cell = DBPromoAgCell.builder().name("Descrizione").editable(false).type(cellStringType)
                    .value(totalizzatore.getDescrizione()).build();
            map.put("descrizione", cell);

            cell = DBPromoAgCell.builder().name("Action Type").editable(false).type(cellStringType)
                    .value(totalizzatore.getActionType()==null?"":String.valueOf(totalizzatore.getActionType())).build();
            map.put("actionType", cell);
            
            cell = DBPromoAgCell.builder().name("Segno").editable(false).type(cellStringType)
                    .value(String.valueOf(totalizzatore.getSegno())).build();
            map.put("segno", cell);

            cell = DBPromoAgCell.builder().name("Parent").editable(false).type(cellStringType)
                    .value(totalizzatore.getIdParent()==null?"":String.valueOf(totalizzatore.getIdParent())).build();
            map.put("parent", cell);
            cell = DBPromoAgCell.builder().name("Export VS").editable(false).type(cellStringType)
                    .value(String.valueOf(totalizzatore.getExportVs())).build();
            map.put("exportVs", cell);

        } catch (Exception ex) {
            log.error(String.format("Error creating row node for 'SupportoMediaEntity' with id '%d'", totalizzatore.getId()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    public ObjectNode createCreaPianoMediaRowNode(CreaPianoMediaEntity e, String[] anni) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final String cellDateType = DBPromoCellTypeEnum.DATE.getType();
        final String cellPicklistType = DBPromoCellTypeEnum.PICKLIST.getType();
        final String cellPopupDialogType = DBPromoCellTypeEnum.POPUP_DIALOG.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("userId").editable(false).type(cellStringType)
                    .value(nullableValue(e.getUserId())).build();
            map.put("userId", cell);

            cell = DBPromoAgCell.builder().name("ID").editable(false).type(cellStringType)
                    .value(nullableValue(e.getSlotId())).build();
            map.put("slotId", cell);

            cell = DBPromoAgCell.builder().name("Anno").editable(true).type(cellPicklistType)
                    .nullable(Boolean.TRUE).value(nullableValue(e.getAnno())).picklistValues(anni).build();
            map.put("anno", cell);

            cell = DBPromoAgCell.builder().name("Promozione Riferimento").editable(true).type(cellPopupDialogType)
                    .nullable(Boolean.TRUE).value(nullableValue(e.getPromoMaster()))
                    .comboBoxValues(getPromoRifValues(e.getPromoMaster())).build();
            map.put("promoRif", cell);

            cell = DBPromoAgCell.builder().name("Descrizione").editable(true).type(cellStringType)
                    .nullable(Boolean.TRUE).value(nullableValue(e.getDescrizione())).build();
            map.put("descrizione", cell);

            cell = DBPromoAgCell.builder().name("Data Inizio").editable(true).type(cellDateType)
                    .nullable(Boolean.TRUE).value(nullableValue(e.getDataInizio())).build();
            map.put("dataInizio", cell);

            cell = DBPromoAgCell.builder().name("Data Fine").editable(true).type(cellDateType)
                    .nullable(Boolean.TRUE).value(nullableValue(e.getDataFine())).build();
            map.put("dataFine", cell);

            cell = DBPromoAgCell.builder().name("Promozione Secondaria A").editable(true).type(cellPopupDialogType)
                    .nullable(Boolean.TRUE).value(nullableValue(e.getPromoSecondaryA()))
                    .comboBoxValues(getPromoRifValues(e.getPromoSecondaryA())).build();
            map.put("promoSecA", cell);

            cell = DBPromoAgCell.builder().name("Promozione Secondaria B").editable(true).type(cellPopupDialogType)
                    .nullable(Boolean.TRUE).value(nullableValue(e.getPromoSecondaryB()))
                    .comboBoxValues(getPromoRifValues(e.getPromoSecondaryB())).build();
            map.put("promoSecB", cell);

            cell = DBPromoAgCell.builder().name("Promozione Secondaria C").editable(true).type(cellPopupDialogType)
                    .nullable(Boolean.TRUE).value(nullableValue(e.getPromoSecondaryC()))
                    .comboBoxValues(getPromoRifValues(e.getPromoSecondaryC())).build();
            map.put("promoSecC", cell);

            cell = DBPromoAgCell.builder().name("createEnabled").editable(false)
                    .value(String.valueOf(!StringUtils.isBlank(e.getPromoMaster()))).build();
            map.put("createEnabled", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating row node for 'CreaPianoMediaEntity' with id '%d'", e.getId()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private List<ComboBoxValues> getPromoRifValues(String codicePromoRif) {
        if (codicePromoRif == null) {
            return Collections.emptyList();
        }
        final PianoMediaPromoDbpromoEntity entity = pianoMediaPromoServiceInstance.get().findByCodicePromo(codicePromoRif);
        return entity == null
                ? Collections.emptyList()
                : Collections.singletonList(comboBoxFactoryInstance.get().from(entity));
    }

    private ObjectNode createInizializzazioneRowNode(CfgPianoMediaEntity supporto) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("ID").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                    .value(String.valueOf(supporto.getId())).build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("supporto").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                    .value(supporto.getSupportoMedia().getDescrizione()).build();
            map.put("supporto", cell);

            cell = DBPromoAgCell.builder().name("tipoDataInizio").editable(true).type(DBPromoCellTypeEnum.COMBOBOX.getType())
                    .comboBoxValues(PianoMediaTipoData.getTipoDataInizioValues())
                    .value(supporto.getTipoDataInizio()!=null?supporto.getTipoDataInizio().name():PianoMediaTipoData.CAMPAGNA.name())
                    .build();
            map.put("tipoDataInizio", cell);
            cell = DBPromoAgCell.builder().name("scostamento")
                    .editable(PianoMediaTipoData.GIORNI_PRIMA.equals(supporto.getTipoDataInizio()))
                    .type(DBPromoCellTypeEnum.NUMERIC.getType())
                    .dataTypeParams(DataTypeParams.builder().minLength(-99).precision(0).allowNegative(false).build())
                    .value(supporto.getScostamento()!=null?supporto.getScostamento().toString():"").build();
            map.put("scostamento", cell);
            cell = DBPromoAgCell.builder().name("tipoDataFine").editable(true).type(DBPromoCellTypeEnum.COMBOBOX.getType())
                    .comboBoxValues(PianoMediaTipoData.getTipoDataFineValues())
                    .value(supporto.getTipoDataFine()!=null?supporto.getTipoDataFine().name():PianoMediaTipoData.CAMPAGNA.name())
                    .build();
            map.put("tipoDataFine", cell);
            cell = DBPromoAgCell.builder().name("durata")
                    .editable(!PianoMediaTipoData.CAMPAGNA.equals(supporto.getTipoDataFine()))
                    .type(DBPromoCellTypeEnum.NUMERIC.getType())
                    .dataTypeParams(DataTypeParams.builder().minLength(-99).precision(0).allowNegative(false).build())
                    .value(supporto.getDurata()!=null?supporto.getDurata().toString():"").build();
            map.put("durata", cell);

        } catch (Exception ex) {
            log.error(String.format("Error creating row node for 'SupportoMediaEntity' with id '%d'", supporto.getId()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    public CfgPianoMediaEntity update(CfgPianoMediaEntity supporto, String payload) {
        try {
            final JsonNode node = JsonUtils.getMapper().readTree(payload);
            String tipoDataInizio = null;
            String tipoDataFine = null;
            Integer durata = null;
            Integer scostamento = null;

            if ( node.get("tipoDataInizio") != null && !node.get("tipoDataInizio").isNull())
                tipoDataInizio = node.get("tipoDataInizio").asText(PianoMediaTipoData.CAMPAGNA.name());

            if ( node.get("tipoDataFine") != null && !node.get("tipoDataFine").isNull())
                tipoDataFine = node.get("tipoDataFine").asText(PianoMediaTipoData.CAMPAGNA.name());

            if ( node.get("durata") != null && !node.get("durata").isNull())
                durata = node.get("durata").asInt();

            if ( node.get("scostamento") != null && !node.get("scostamento").isNull())
                scostamento = node.get("scostamento").asInt();

            PianoMediaTipoData tipoDataInizioType = null;
            PianoMediaTipoData tipoDataFineType = null;

            try{
                if (tipoDataInizio != null ){
                    tipoDataInizioType = PianoMediaTipoData.valueOf(tipoDataInizio);
                } else {
                    tipoDataInizioType = PianoMediaTipoData.CAMPAGNA;
                }
            } catch (Exception ex){
                log.error("Attempt to set an unsupported TipoData " +tipoDataInizio);
                tipoDataInizioType = PianoMediaTipoData.CAMPAGNA;
            } finally {
                supporto.setTipoDataInizio(tipoDataInizioType);
            }
            try{
                if (tipoDataFine != null ){
                    tipoDataFineType = PianoMediaTipoData.valueOf(tipoDataFine);
                } else {
                    tipoDataFineType = PianoMediaTipoData.CAMPAGNA;
                }
            } catch (Exception ex){
                log.error("Attempt to set an unsupported TipoDataFine " +tipoDataFine);
                tipoDataFineType = PianoMediaTipoData.CAMPAGNA;
            } finally {
                supporto.setTipoDataFine(tipoDataFineType);
            }

            if(PianoMediaTipoData.CAMPAGNA.equals(tipoDataInizioType)){
                supporto.setScostamento(null);
            } else {
                if ( scostamento != null && !scostamento.equals(supporto.getScostamento())) {
                    supporto.setScostamento(scostamento);
                }
            }
            if(PianoMediaTipoData.CAMPAGNA.equals(tipoDataFineType)){
                supporto.setDurata(null);
            } else {
                if ( durata != null && !durata.equals(supporto.getDurata())) {
                    supporto.setDurata(durata);
                }
            }
        } catch (Exception ex) {
            log.error(String.format("Error updating 'SupportoMediaEntity'; id:'%d'; payload:'%s'",
                    supporto.getId(), payload), ex);
        }
        return supporto;
    }

    public String visualizzaPianoRowData(@NonNull List<PianoMediaEntity> list){
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            list.stream().sorted(Comparator.comparing(PianoMediaEntity::getDataInizio))
                    .forEach(s -> arrayNode.add(createPianoMediaRowNode(s)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available 'SupportoMediaEntity'", ex);
            return "";
        }
    }

    private ObjectNode createPianoMediaRowNode(PianoMediaEntity piano) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("Id").editable(false)
                    .type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(piano.getId()))
                    .build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("anno").editable(false).type(cellStringType)
                    .value(String.valueOf(piano.getAnno())).build();
            map.put("anno", cell);

            cell = DBPromoAgCell.builder().name("descrizione").editable(false).type(cellStringType)
                    .value(String.valueOf(piano.getDescrizione())).build();
            map.put("descrizione", cell);

            cell = DBPromoAgCell.builder().name("dataInizio").editable(false).type(DBPromoCellTypeEnum.DATE.getType())
                    .value(piano.getDataInizio() == null ? "" : dateTimeUtils.toExcelDate(piano.getDataInizio())).build();
            map.put("dataInizio", cell);

            cell = DBPromoAgCell.builder().name("dataFine").editable(false).type(DBPromoCellTypeEnum.DATE.getType())
                    .value(piano.getDataInizio() == null ? "" : dateTimeUtils.toExcelDate(piano.getDataFine())).build();
            map.put("dataFine", cell);

            final PianoMediaStatoEntity stato = piano.getPianoMediaStati().stream().filter(s -> s.getDataFineStato() == null).findFirst().orElse(null);

            cell = DBPromoAgCell.builder().name("stato").editable(false).type(cellStringType)
                    .value(stato == null ? "" : stato.getStato().fullDescription()).build();

            map.put("stato", cell);

        } catch (Exception ex) {
            log.error(String.format("Error creating row node for 'SupportoMediaEntity' with id '%d'", piano.getId()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    public String createStatiRowData(Set<PianoMediaStatoEntity> pianoMediaStatoEntities) {

        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";

        pianoMediaStatoEntities.stream()
                .sorted(Comparator.comparing(PianoMediaStatoEntity::getDataInizioStato).reversed())
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

    private ObjectNode createStatiRowNode(PianoMediaStatoEntity promozioneStatoEntity) {

        final HashedMap<String, DBPromoAgCell> dbPromoAgCellRowMap = new HashedMap<>();

        DBPromoAgCell dbPromoAgCell = DBPromoAgCell.builder().name("Stato").editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value((promozioneStatoEntity.getStato() == null)
                        || (promozioneStatoEntity.getStato().getCodiceStato() == null)
                        || (promozioneStatoEntity.getStato().getLabel() == null)
                        ? ""
                        : String.format("%s - %s",
                        promozioneStatoEntity.getStato().getCodiceStato(),
                        promozioneStatoEntity.getStato().getLabel()))
                .build();
        dbPromoAgCellRowMap.put("codice", dbPromoAgCell);

        DataTypeParams dataTypeParamsPojo = new DataTypeParams();
        dataTypeParamsPojo.setDateFormat(DbPromoConstants.DBPROMO_STRING_DATE_FORMAT);

        dbPromoAgCell = DBPromoAgCell.builder().name("Data Inizio").editable(false)
                .type(DBPromoCellTypeEnum.DATE.getType())
                .value(promozioneStatoEntity.getDataInizioStato() == null ? ""
                        : dateTimeUtils.toExcelDate(promozioneStatoEntity.getDataInizioStato()))
                .dataTypeParams(dataTypeParamsPojo).build();
        dbPromoAgCellRowMap.put("dataInizio", dbPromoAgCell);

        dbPromoAgCell = DBPromoAgCell.builder().name("Data Fine").editable(false)
                .type(DBPromoCellTypeEnum.DATE.getType())
                .value(promozioneStatoEntity.getDataFineStato() == null ? ""
                        : dateTimeUtils.toExcelDate(promozioneStatoEntity.getDataFineStato()))
                .dataTypeParams(dataTypeParamsPojo).build();

        dbPromoAgCellRowMap.put("dataFine", dbPromoAgCell);

        dbPromoAgCell = DBPromoAgCell.builder().name("Utente").editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(promozioneStatoEntity.getCodiceUtenteInserimento()).build();

        dbPromoAgCellRowMap.put("utente", dbPromoAgCell);

        return JsonUtils.getMapper().valueToTree(dbPromoAgCellRowMap);
    }

    private ObjectNode createControlloRowNode(PianoMediaControlliEntity e) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
            final String codiceControllo = e.getSupportoMediaCfgCheck().getCodiceControllo();
            final SupportoMediaCheckEnum mediaCheckEnum = SupportoMediaCheckEnum.fromCodice(codiceControllo);
            String controllo = mediaCheckEnum == null ? codiceControllo : mediaCheckEnum.getDescrizione();
            final String media = String.format("%s #%d",
                    e.getPianificazioneMedia().getSupportoMedia().getDescrizione(),
                    getPianificazioneMediaIndex(e.getPianificazioneMedia()));

            DBPromoAgCell cell = DBPromoAgCell.builder().name("ID").editable(false).type(cellStringType)
                    .value(String.valueOf(e.getId())).build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("Media").editable(false).type(cellStringType)
                    .value(media).build();
            map.put("media", cell);

            cell = DBPromoAgCell.builder().name("Controllo").editable(false).type(cellStringType)
                    .value(controllo).build();
            map.put("controllo", cell);

            // Add mandatory or warning props
            if (e.getSupportoMediaCfgCheck().getBlocco() != null) {
                if (e.getSupportoMediaCfgCheck().getBlocco().equals(Boolean.TRUE)) {
                    map.values().forEach(c -> c.setMandatory(true));
                } else {
                    map.values().forEach(c -> c.setWarning(true));
                }
            }
        } catch (Exception ex) {
            log.error(String.format("Error creating row node for 'PianoMediaControlliEntity' with id '%d'", e.getId()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    /**
     * Questo metodo mi serve per ritornare il progressivo della pianificazioneMedia all'interno di tutte
     * le pianificazioni con lo stesso supporto
     *
     * @param pianificazioneMedia la pianificazione da controllare
     * @return progressivo all'interno delle pianificazione con lo stesso supporto
     */
    private Integer getPianificazioneMediaIndex(PianificazionePianoMediaEntity pianificazioneMedia) {
        final List<PianificazionePianoMediaEntity> all = pianificazioneMedia.getPianoMedia()
                .getConfigurazioniPianoMedia().stream()
                .filter(p -> p.getSupportoMedia().equals(pianificazioneMedia.getSupportoMedia()))
                .sorted(Comparator.comparing(PianificazionePianoMediaEntity::getDataInizio))
                .collect(Collectors.toList());
        if (all.isEmpty()) {
            return 1;
        }
        final int i = all.indexOf(pianificazioneMedia);
        return i == -1 ? 1 : i + 1;
    }
}
