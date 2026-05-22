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
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaControlliEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import com.axiante.mui.webapp.business.supportimedia.SupportoMediaCheckEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

@Dependent
@Slf4j
public class PianoMediaRowDataUtil extends AbstractRowDataUtil {

    @Inject
    private Instance<PianoMediaPromoDbpromoService> pianoMediaPromoServiceInstance;

    @Inject
    private Instance<ComboBoxFactory> comboBoxFactoryInstance;

    transient DateTimeUtils dateTimeUtils = new DateTimeUtils();

    public String createSupportiRowData(@NonNull List<SupportoMediaEntity> supporti, String messaggio) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            supporti.stream().sorted(Comparator.comparing(SupportoMediaEntity::getCodiceMedia))
                    .forEach(s -> arrayNode.add(createSupportoRowNode(s)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            if (messaggio != null) {
                DbPromoAgCellUtils.putValue(objectNode, "updateMessage", messaggio, false);
            }
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available 'SupportoMediaEntity'", ex);
            return "";
        }
    }

    public String createInizializzazioneRowData(@NonNull List<CfgPianoMediaEntity> list, String messaggio){
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            list.stream().sorted(Comparator.comparing(p->p.getSupportoMedia().getCodiceMedia()))
                    .forEach(s -> arrayNode.add(createInizializzazioneRowNode(s)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            if (messaggio != null) {
                DbPromoAgCellUtils.putValue(objectNode, "updateMessage", messaggio, false);
            }
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available 'CfgPianoMediaEntity'", ex);
            return "";
        }
    }

    public SupportoMediaEntity update(SupportoMediaEntity supporto, String payload, String username) {
        try {
            final JsonNode node = JsonUtils.getMapper().readTree(payload);
            final String descrizione = node.get("descrizione").asText();
            if (!StringUtils.isBlank(descrizione) && !descrizione.trim().toUpperCase().equals(supporto.getDescrizione())) {
                supporto.setDescrizione(descrizione.trim().toUpperCase());
            }
            final String attivo = node.get("attivo").asText();
            supporto.setDataAggiornamento(Calendar.getInstance().getTime());
            supporto.setCodiceUtenteAggiornamento(username);
            try {
                supporto.setAttivo(Boolean.parseBoolean(attivo));
            } catch (Exception e){
                log.error(String.format("Error converting boolean value %s", attivo), e);
            }
            String color = node.get("colore").asText();
            if (StringUtils.isBlank(color)) {
                return supporto;
            }
            if (color.startsWith("#")) {
                color = color.substring(1).trim().toUpperCase();
            }
            if (!color.equals(supporto.getColore())) {
                supporto.setColore(color);
            }
        } catch (Exception ex) {
            log.error(String.format("Error updating 'SupportoMediaEntity'; id:'%d'; payload:'%s'",
                    supporto.getId(), payload), ex);
        }
        return supporto;
    }

    public String createCreaPianoMediaRowData(String slots, List<CreaPianoMediaEntity> entities, String userId) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            final int rowDataSlots = StringUtils.isBlank(slots) ? 0 : Integer.parseInt(slots);
            final Function<CreaPianoMediaEntity, Integer> toSlotId = e -> {
                Integer ret = null;
                if (e.getSlotId() != null) {
                    try {
                        ret = Integer.parseInt(e.getSlotId());
                    } catch (Exception ex) {
                        log.warn(String.format("Error converting slotId '%s' to integer value", e.getSlotId()), ex);
                        ret = 0;
                    }
                }
                return ret;
            };
            // Preparo valori picklist anni (corrente e successivo)
            final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            final String[] anni = {String.format("%s", currentYear), String.format("%s", currentYear + 1), ""};
            // I dati vanno ordinati per slotId
            final List<CreaPianoMediaEntity> list = entities.stream()
                    .sorted(Comparator.comparing(CreaPianoMediaEntity::getSlotId))
                    .collect(Collectors.toList());
            IntStream.rangeClosed(1, rowDataSlots).forEach(i -> {
                final CreaPianoMediaEntity entity = list.stream().filter(e -> i == toSlotId.apply(e))
                        .findFirst().orElse(null);
                if (entity != null) {
                    // Serializzo entity in una riga
                    arrayNode.add(createCreaPianoMediaRowNode(entity, anni));
                } else {
                    // Riga vuota
                    arrayNode.add(createCreaPianoMediaEmptyRowNode(userId, String.format("%03d", i), anni));
                }
            });
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for 'CreaPianoMediaEntity'", ex);
            return "";
        }
    }

    public String createControlliRowData(@NonNull List<PianoMediaControlliEntity> controlli) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            controlli.stream()
                    .sorted(Comparator.comparing(c -> c.getPianificazioneMedia().getId()))
                    .forEach(c -> arrayNode.add(createControlloRowNode(c)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available 'PianoMediaControlliEntity'", ex);
            return "";
        }
    }

    private ObjectNode createCreaPianoMediaEmptyRowNode(String userId, String slotId, String[] anni) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final String cellDateType = DBPromoCellTypeEnum.DATE.getType();
        final String cellPicklistType = DBPromoCellTypeEnum.PICKLIST.getType();
        final String cellPopupDialogType = DBPromoCellTypeEnum.POPUP_DIALOG.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("userId").editable(false).type(cellStringType)
                    .value(userId).build();
            map.put("userId", cell);

            cell = DBPromoAgCell.builder().name("slotId").editable(false).type(cellStringType)
                    .value(slotId).build();
            map.put("slotId", cell);

            cell = DBPromoAgCell.builder().name("Anno").editable(true).type(cellPicklistType)
                    .nullable(Boolean.TRUE).value("").picklistValues(anni).build();
            map.put("anno", cell);

            cell = DBPromoAgCell.builder().name("Promozione Riferimento").editable(true).type(cellPopupDialogType)
                    .nullable(Boolean.TRUE).value("").build();
            map.put("promoRif", cell);

            cell = DBPromoAgCell.builder().name("Descrizione").editable(true).type(cellStringType)
                    .nullable(Boolean.TRUE).value("").build();
            map.put("descrizione", cell);

            cell = DBPromoAgCell.builder().name("Data Inizio").editable(true).type(cellDateType)
                    .nullable(Boolean.TRUE).value("").build();
            map.put("dataInizio", cell);

            cell = DBPromoAgCell.builder().name("Data Fine").editable(true).type(cellDateType)
                    .nullable(Boolean.TRUE).value("").build();
            map.put("dataFine", cell);

            cell = DBPromoAgCell.builder().name("Promozione Secondaria A").editable(true).type(cellPopupDialogType)
                    .nullable(Boolean.TRUE).value("").build();
            map.put("promoSecA", cell);

            cell = DBPromoAgCell.builder().name("Promozione Secondaria B").editable(true).type(cellPopupDialogType)
                    .nullable(Boolean.TRUE).value("").build();
            map.put("promoSecB", cell);

            cell = DBPromoAgCell.builder().name("Promozione Secondaria C").editable(true).type(cellPopupDialogType)
                    .nullable(Boolean.TRUE).value("").build();
            map.put("promoSecC", cell);

            cell = DBPromoAgCell.builder().name("createEnabled").editable(false).value(String.valueOf(false)).build();
            map.put("createEnabled", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating empty row node for 'CreaPianoMediaEntity' with slotId '%s' and userId '%s'",
                    slotId, userId), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createSupportoRowNode(SupportoMediaEntity supporto) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("ID").editable(false).type(cellStringType)
                    .value(String.valueOf(supporto.getId())).build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("Codice").editable(false).type(cellStringType)
                    .value(supporto.getCodiceMedia()).build();
            map.put("codice", cell);

            cell = DBPromoAgCell.builder().name("Descrizione").editable(true).type(cellStringType)
                    .value(supporto.getDescrizione()).build();
            map.put("descrizione", cell);
            
            cell = DBPromoAgCell.builder().name("Attivo").editable(true).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                    .value(String.valueOf(supporto.getAttivo())).build();
            map.put("attivo", cell);

            cell = DBPromoAgCell.builder().name("Colore").editable(true).type(DBPromoCellTypeEnum.PICKCOLOR.getType())
                    .value(supporto.getColore()).build();
            map.put("colore", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating row node for 'SupportoMediaEntity' with id '%d'", supporto.getId()), ex);
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
