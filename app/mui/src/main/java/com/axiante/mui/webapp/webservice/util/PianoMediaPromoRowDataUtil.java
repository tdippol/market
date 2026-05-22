package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.business.PianoMediaStatoUtils;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneAnagraficaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.webapp.business.PianificazioneMediaWriteLevelEnum;
import com.axiante.mui.webapp.business.data.PianoMediaDettaglioDTO;
import com.axiante.mui.webapp.business.data.PianoMediaPromoArticoliDettaglioDbPromoDTO;
import com.axiante.mui.webapp.webservice.PianoMediaPianificazioniResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class PianoMediaPromoRowDataUtil extends AbstractRowDataUtil {

    @Inject
    private Instance<PianoMediaStatoUtils> pianoMediaStatoUtilsInstance;

    public String createPromoRifRowData(List<PianoMediaPromoDbpromoEntity> entities) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            entities.stream().sorted(Comparator.comparing(PianoMediaPromoDbpromoEntity::getCodicePromozione))
                    .forEach(e -> arrayNode.add(createPromoRifRowNode(e)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available 'PianoMediaPromoDbpromoEntity'", ex);
            return "";
        }
    }

    public String createPianificazioneItemsRowData(List<PianoMediaDettaglioDTO> dtoList) {
          final ObjectNode objectNode = createPianificazioneItemsRowDataNode(dtoList);
          if ( objectNode == null ){
              return "";
          }
          try {
              return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for pianificazione items 'PianoMediaDettaglioDTO'", ex);
            return "";
        }
    }

    public ObjectNode createPianificazioneItemsRowDataNode(List<PianoMediaDettaglioDTO> dtoList) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            dtoList.stream().sorted(Comparator.comparing(PianoMediaDettaglioDTO::getCodiceArticolo, Comparator.nullsLast(Comparator.reverseOrder())))
                    .forEach(i -> arrayNode.add(createPianificazioneItemRowNode(i)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return objectNode;
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for pianificazione items 'PianoMediaDettaglioDTO'", ex);
            return null;
        }
    }

    public String createPianificazioniDetailsRowData(List<PianoMediaDettaglioDTO> dtoList, PianificazioneMediaWriteLevelEnum editableLevel) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        final boolean editable =  PianificazioneMediaWriteLevelEnum.ALL.equals(editableLevel);
        final boolean editNotes = PianificazioneMediaWriteLevelEnum.NOTES.equals(editableLevel);
        try {
            dtoList.stream().sorted(Comparator.comparing(PianoMediaDettaglioDTO::getCodiceArticolo, Comparator.nullsLast(Comparator.reverseOrder())))
                    .forEach(i -> arrayNode.add(createPianificazioneDetailsRowNode(i,editable, editNotes)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for pianificazione details 'PianoMediaDettaglioDTO'", ex);
            return "";
        }
    }

    public String createDettaglioArticoloDifferenziatoRowData(List<PianoMediaPromoArticoliDettaglioDbPromoDTO> articoli) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            articoli.stream()
                    .sorted(Comparator.comparing(PianoMediaPromoArticoliDettaglioDbPromoDTO::getZona))
                    .forEach(a -> arrayNode.add(createDettaglioArticoloDiffRowNode(a)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for pianificazione dettaglio articolo differenziato 'PianoMediaDettaglioDTO'", ex);
            return "";
        }
    }

    public String createStatoCompratoriRowData(Map<String, List<PianoMediaPianificazioneDettaglioEntity>> byBuyers) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            byBuyers.forEach((buyer, details) -> arrayNode.add(createStatoCompratoreRowNode(buyer, details)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for 'Stato Compratori'", ex);
            return "";
        }
    }

    private ObjectNode createStatoCompratoreRowNode(String buyer, List<PianoMediaPianificazioneDettaglioEntity> details) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            final StatoPromozioneEntity stato = pianoMediaStatoUtilsInstance.get().calcolaStato(details);
            DBPromoAgCell cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(cellStringType)
                    .value(buyer).build();
            map.put("compratore", cell);

            cell = DBPromoAgCell.builder().name("Stato").editable(false).type(cellStringType)
                    .value(getStato(stato)).build();
            map.put("stato", cell);

            if (stato != null) {
                if ("320".equals(stato.getCodiceStato())) {
                    // Se lo stato e' 320, aggiungo la property mandatory (red-background) ad agCell
                    map.values().forEach(c -> c.setMandatory(true));
                } else if ("310".equals(stato.getCodiceStato())) {
                    // Se lo stato e' 310, aggiungo l'extra class 'ax-success-bg' (green-background)
                    map.values().forEach(c -> c.setExtraClasses(Collections.singletonList("ax-success-bg")));
                }
            }
        } catch (Exception ex) {
            log.error(String.format("Error creating row node for 'Stato Compratore' with codice compratore '%s'", buyer),
                    ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private String getStato(StatoPromozioneEntity stato) {
        if (stato == null) {
            return "NA";
        }
        if ("310".equals(stato.getCodiceStato())) {
            return "APPROVATO";
        }
        if ("320".equals(stato.getCodiceStato())) {
            return "NON APPROVATO";
        }
        return "NA";
    }

    private ObjectNode createDettaglioArticoloDiffRowNode(PianoMediaPromoArticoliDettaglioDbPromoDTO articolo) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final String cellDateType = DBPromoCellTypeEnum.DATE.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("Zona").editable(false).type(cellStringType)
                    .value(articolo.getZona()).build();
            map.put("zona", cell);

            cell = DBPromoAgCell.builder().name("Meccanica").editable(false).type(cellStringType)
                    .value(articolo.getCodiceMeccanica()).build();
            map.put("meccanica", cell);

            cell = DBPromoAgCell.builder().name("Condizione").editable(false).type(cellStringType)
                    .value(articolo.getCodiceCondizione()).build();
            map.put("condizione", cell);

            cell = DBPromoAgCell.builder().name("Valore Off").editable(false).type(cellStringType)
                    .value(articolo.getValore()).build();
            map.put("valoreOff", cell);

            cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(cellDateType)
                    .value(nullableValue(articolo.getDataInizio())).build();
            map.put("dataInizio", cell);

            cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(cellDateType)
                    .value(nullableValue(articolo.getDataFine())).build();
            map.put("dataFine", cell);

            cell = DBPromoAgCell.builder().name("Volantino").editable(false).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                    .value(nullableValue(articolo.getFlVolantino())).build();
            map.put("volantino", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating row node for 'PianoMediaPromoArticoliDettaglioDbPromoDTO' with codice item '%s'",
                    articolo.getCodiceItem()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createPromoRifRowNode(PianoMediaPromoDbpromoEntity e) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final String cellDateType = DBPromoCellTypeEnum.DATE.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("Codice").editable(false).type(cellStringType)
                    .value(nullableValue(e.getCodicePromozione())).build();
            map.put("codice", cell);

            cell = DBPromoAgCell.builder().name("Descrizione").editable(false).type(cellStringType)
                    .value(nullableValue(e.getDescrizioneEstesa())).build();
            map.put("descrizione", cell);

            cell = DBPromoAgCell.builder().name("Gruppo").editable(false).type(cellStringType)
                    .value(nullableValue(e.getDescrizioneGruppo())).build();
            map.put("gruppo", cell);

            cell = DBPromoAgCell.builder().name("Canale").editable(false).type(cellStringType)
                    .value(nullableValue(e.getDescrizioneCanale())).build();
            map.put("canale", cell);

            cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(cellDateType)
                    .value(nullableValue(e.getDataInizio())).build();
            map.put("dataInizio", cell);

            cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(cellDateType)
                    .value(nullableValue(e.getDataFine())).build();
            map.put("dataFine", cell);

            cell = DBPromoAgCell.builder().name("Stato").editable(false).type(cellStringType)
                    .value(nullableValue(e.getDescrizioneStato())).build();
            map.put("stato", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating row node for 'PianoMediaPromoDbpromoEntity' with codice '%s'",
                    e.getCodicePromozione()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createPianificazioneItemRowNode(PianoMediaDettaglioDTO item) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final String cellDateType = DBPromoCellTypeEnum.DATE.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("Codice Item").editable(false).type(cellStringType)
                    .value(item.getCodiceItem()).build();
            map.put("codiceItem", cell);

            cell = DBPromoAgCell.builder().name("Codice Promozione").editable(false).type(cellStringType)
                    .value(item.getCodicePromozione()).build();
            map.put("codicePromo", cell);

            cell = DBPromoAgCell.builder().name("Tipo Item").editable(false).type(cellStringType)
                    .value(item.getTipoItem()).build();
            map.put("tipoItem", cell);

            cell = DBPromoAgCell.builder().name("Codice Articolo").editable(false).type(cellStringType)
                    .value(item.getCodiceArticolo()).build();
            map.put("codiceArticolo", cell);

            cell = DBPromoAgCell.builder().name("Descrizione Articolo").editable(false).type(cellStringType)
                    .value(item.getDescrizioneArticolo()).build();
            map.put("descrizioneArticolo", cell);

            cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(cellStringType)
                    .value(item.getCompratore()).build();
            map.put("compratore", cell);

            cell = DBPromoAgCell.builder().name("Reparto").editable(false).type(cellStringType)
                    .value(item.getReparto()).build();
            map.put("reparto", cell);

            cell = DBPromoAgCell.builder().name("Categoria").editable(false).type(cellStringType)
                    .value(item.getCategoria()).build();
            map.put("categoria", cell);

            cell = DBPromoAgCell.builder().name("Grm").editable(false).type(cellStringType)
                    .value(item.getGrm()).build();
            map.put("grm", cell);

            cell = DBPromoAgCell.builder().name("Sub Grm").editable(false).type(cellStringType)
                    .value(item.getSubGrm()).build();
            map.put("subGrm", cell);

            cell = DBPromoAgCell.builder().name("Volantino").editable(false).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                    .value(nullableValue(item.isFlVolantino())).build();
            map.put("volantino", cell);

            cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(cellDateType)
                    .value(nullableValue(item.getDataInizio())).build();
            map.put("dataInizio", cell);

            cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(cellDateType)
                    .value(nullableValue(item.getDataFine())).build();
            map.put("dataFine", cell);

            cell = DBPromoAgCell.builder().name("Zona").editable(false).type(cellStringType)
                    .value(nullableValue( String.join(",", item.getZone()))).build();
            map.put("zona", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating row node for 'PianoMediaDettaglioDTO' with codice articolo '%s'",
                    item.getCodiceArticolo()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    public ObjectNode createPianificazioneDetailsRowNode(PianoMediaDettaglioDTO item,boolean editable, boolean editableNotes) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final String cellNumericType = DBPromoCellTypeEnum.NUMERIC.getType();
        final String cellDateType = DBPromoCellTypeEnum.DATE.getType();
        final String cellCheckboxType = DBPromoCellTypeEnum.CHECKBOX.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            final boolean itemRemoved = item.articoloRemoved();
            DBPromoAgCell cell = DBPromoAgCell.builder().name("ID").editable(false).type(cellStringType)
                    .value(nullableValue(item.getId())).build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("Codice Item").editable(false).type(cellStringType)
                    .value(item.getCodiceItem()).build();
            map.put("codiceItem", cell);

            cell = DBPromoAgCell.builder().name("Codice Promozione").editable(false).type(cellStringType)
                    .value(item.getCodicePromozione()).build();
            map.put("codicePromo", cell);

            cell = DBPromoAgCell.builder().name("Tipo Item").editable(false).type(cellStringType)
                    .value(item.getTipoItem()).build();
            map.put("tipoItem", cell);

            cell = DBPromoAgCell.builder().name("Civetta").editable(!itemRemoved && editable).type(cellCheckboxType)
                    .value(nullableValue(item.isCivetta())).build();
            map.put("civetta", cell);

            cell = DBPromoAgCell.builder().name("Articolo").editable(false).type(cellStringType)
                    .value(item.getArticolo()).build();
            map.put("articolo", cell);

            cell = DBPromoAgCell.builder().name("Reparto").editable(false).type(cellStringType)
                    .value(item.getReparto()).build();
            map.put("reparto", cell);

            cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(cellStringType)
                    .value(item.getCompratore()).build();
            map.put("compratore", cell);

            cell = DBPromoAgCell.builder().name("Pezzi").editable(false).type(cellNumericType)
                    .dataTypeParams(DataTypeParams.builder().precision(0).build())
                    .value(nullableValue(item.getPezzi())).build();
            map.put("pezzi", cell);

            cell = DBPromoAgCell.builder().name("Rank Pezzi").editable(!itemRemoved && editable).type(cellNumericType)
                    .dataTypeParams(DataTypeParams.builder().precision(0).build())
                    .value(nullableValue(item.getPezzoRank())).build();
            map.put("rankPezzi", cell);

            cell = DBPromoAgCell.builder().name("Fatturato").editable(false).type(cellNumericType)
                    .value(nullableValue(item.getFatturato())).build();
            map.put("fatturato", cell);

            cell = DBPromoAgCell.builder().name("Rank Fatturato").editable(!itemRemoved && editable).type(cellNumericType)
                    .dataTypeParams(DataTypeParams.builder().precision(0).build())
                    .value(nullableValue(item.getFatturatoRank())).build();
            map.put("rankFatturato", cell);

            if (item.isDifferenziato()) {
                cell = DBPromoAgCell.builder().name("Zone Diff").editable(false)
                        .type(DBPromoCellTypeEnum.POPUP_DIALOG.getType()).nullable(Boolean.TRUE).value("").build();
                map.put("zoneDiff", cell);

                cell = DBPromoAgCell.builder().name("Meccanica").editable(false).type(cellStringType)
                        .value("").build();
                map.put("codiceMeccanica", cell);

                cell = DBPromoAgCell.builder().name("Condizione").editable(false).type(cellStringType)
                        .value("").build();
                map.put("codiceCondizione", cell);

                cell = DBPromoAgCell.builder().name("Valore Off").editable(false).type(cellStringType)
                        .value("").build();
                map.put("valoreOff", cell);

                cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(cellDateType)
                        .value("").build();
                map.put("dataInizio", cell);

                cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(cellDateType)
                        .value("").build();
                map.put("dataFine", cell);

                cell = DBPromoAgCell.builder().name("Volantino").editable(false).type(cellStringType)
                        .value("").build();
                map.put("volantino", cell);
            } else {
                cell = DBPromoAgCell.builder().name("Zone Diff").editable(false).type(cellStringType)
                        .value("").build();
                map.put("zoneDiff", cell);

                cell = DBPromoAgCell.builder().name("Meccanica").editable(false).type(cellStringType)
                        .value(nullableValue(item.getCodiceMeccanica())).build();
                map.put("codiceMeccanica", cell);

                cell = DBPromoAgCell.builder().name("Condizione").editable(false).type(cellStringType)
                        .value(nullableValue(item.getCodiceCondizione())).build();
                map.put("codiceCondizione", cell);

                cell = DBPromoAgCell.builder().name("Valore Off").editable(false).type(cellStringType)
                        .value(nullableValue(item.getValoreOfferta())).build();
                map.put("valoreOff", cell);

                cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(cellDateType)
                        .value(nullableValue(item.getDataInizio())).build();
                map.put("dataInizio", cell);

                cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(cellDateType)
                        .value(nullableValue(item.getDataFine())).build();
                map.put("dataFine", cell);

                cell = DBPromoAgCell.builder().name("Volantino").editable(false).type(cellCheckboxType)
                        .value(nullableValue(item.isFlVolantino())).build();
                map.put("volantino", cell);
            }

            for(PianificazioneAnagraficaPianoMediaEntity anagrafica: item.getDettaglioPianificazione().getPianificazioniAnagrafiche()) {
                cell = DBPromoAgCell.builder()
                        .editable(editable)
                        .type(cellCheckboxType)
                        .value(nullableValue(anagrafica.getAttivo()))
                        .build();
                map.put(String.format("%s%d", PianoMediaPianificazioniResource.DYNAMIC_COL_RADIX, anagrafica.getPianificazioneMedia().getId()), cell);
            }


            cell = DBPromoAgCell.builder().name("Commento").editable(!itemRemoved && editableNotes).type(cellStringType)
                    .value(nullableValue(item.getNoteCompratore())).build();
            map.put("commento", cell);

            cell = DBPromoAgCell.builder().name("deleteEnabled").value(String.valueOf(editable)).build();
            map.put("deleteEnabled", cell);

            cell = DBPromoAgCell.builder().name("columnToBeUpdated").editable(Boolean.FALSE)
                    .type(DBPromoCellTypeEnum.STRING.getType()).value("").build();
            map.put("columnToBeUpdated", cell);

            cell = DBPromoAgCell.builder().name("valueToBeUpdated").editable(Boolean.FALSE)
                    .type(DBPromoCellTypeEnum.STRING.getType()).value("").build();
            map.put("valueToBeUpdated", cell);

            cell = DBPromoAgCell.builder().name("columnHeaderToBeUpdated").editable(Boolean.FALSE)
                    .type(DBPromoCellTypeEnum.STRING.getType()).value("").build();
            map.put("columnHeaderToBeUpdated", cell);

            if (itemRemoved) {
                map.values().forEach(c -> c.setMandatory(true));
            }
        } catch (Exception ex) {
            log.error(String.format("Error creating row node for 'PianoMediaDettaglioDTO' with codice articolo '%s'",
                    item.getCodiceArticolo()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }
}
