package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MarchioPrivatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportArticoloEntity;
import com.axiante.mui.dbpromo.persistence.service.MarchioPrivatoService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.webapp.webservice.pojo.ItemHierarchy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Dependent
@Slf4j
public class ReportArticoloInPromoRowDataUtil extends AbstractRowDataUtil {

    @Inject
    private Instance<PianificazioneService> pianificazioneServiceInstance;

    @Inject
    private Instance<MarchioPrivatoService> marchioPrivatoServiceInstance;

    private DateTimeUtils dateTimeUtils = new DateTimeUtils();

    public String createArticoloInPromoRowData(@NonNull List<MuiReportArticoloEntity> entities, @NonNull Date date) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
        entities.forEach(entity -> arrayNode.add(createArticoloInPromoObjectNode(entity, date)));
        objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
        try {
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (JsonProcessingException ex) {
            log.error("Error processing rowData for 'Articolo in Promo'", ex);
        }
        return "";
    }

    public String createSelectionRowData(@NonNull PlanningArticleMultiFilterParam param) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        pianificazioneServiceInstance.get().findAllItemsByDynamicFilters(param.getIdCompratoreSelected(),
                        param.getIdFornitoreSelected(), param.getIdRepartoSelected(), param.getIdCategoriaSelected(),
                        param.getIdGrmSelected(), param.getCodiceMarchioPrivSelected())
                .forEach(item -> mapItemEntityByFornitore(arrayNode, item));
        final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
        objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
        try {
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (JsonProcessingException ex) {
            log.error("Error processing rowData for 'Selezione' tab on 'Report Articoli in Promo' dialog", ex);
        }
        return "";
    }

    public String createCopyPasteRowData() {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        arrayNode.add(createCopyPasteObjectNode());
        ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
        objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
        try {
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (JsonProcessingException ex) {
            log.error("Error processing rowData for 'Copia / Incolla' tab on 'Report Articoli in Promo' dialog", ex);
        }
        return "";
    }

    private ObjectNode createArticoloInPromoObjectNode(MuiReportArticoloEntity entity, Date date) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();
        final String cellTypeDate = DBPromoCellTypeEnum.DATE.getType();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Anno").editable(false).type(cellTypeString)
                .value(entity.getAnno()).build();
        map.put("anno", cell);

        cell = DBPromoAgCell.builder().name("Canale").editable(false).type(cellTypeString)
                .value(entity.getDescrizioneCanale()).build();
        map.put("canale", cell);

        cell = DBPromoAgCell.builder().name("Promozione").editable(false).type(cellTypeString)
                .value(String.format("[%s] %s", entity.getId().getCodicePromozione(), entity.getDescrizionePromozione()))
                .build();
        map.put("descrizioneEstesa", cell);

        cell = DBPromoAgCell.builder().name("Articolo").editable(false).type(cellTypeString)
                .value(String.format("%s - %s", entity.getCodiceArticolo(), entity.getDescrizioneArticolo())).build();
        map.put("articolo", cell);

        cell = DBPromoAgCell.builder().name("Data Inizio Articolo").editable(false).type(cellTypeDate)
                .value(entity.getDataInizioArticolo() == null ? "" : dateTimeUtils.toExcelDate(entity.getDataInizioArticolo())).build();
        map.put("dataInizioArticolo", cell);

        cell = DBPromoAgCell.builder().name("Data Fine Articolo").editable(false).type(cellTypeDate)
                .value(entity.getDataFineArticolo() == null ? "" : dateTimeUtils.toExcelDate(entity.getDataFineArticolo())).build();
        map.put("dataFineArticolo", cell);

        cell = DBPromoAgCell.builder().name("Data Inizio Testata").editable(false).type(cellTypeDate)
                .value(entity.getDataInizioTestata() == null ? "" : dateTimeUtils.toExcelDate(entity.getDataInizioTestata())).build();
        map.put("dataInizioTestata", cell);

        cell = DBPromoAgCell.builder().name("Data Fine Testata").editable(false).type(cellTypeDate)
                .value(entity.getDataFineTestata() == null ? "" : dateTimeUtils.toExcelDate(entity.getDataFineTestata())).build();
        map.put("dataFineTestata", cell);

        cell = DBPromoAgCell.builder().name("Zona").editable(false).type(cellTypeString)
                .value(entity.getId().getCodiceZona()).build();
        map.put("zona", cell);

        cell = DBPromoAgCell.builder().name("Gruppo Promozionale").editable(false).type(cellTypeString)
                .value(String.format("%s - %s", entity.getCodiceGruppo(), entity.getDescrizioneGruppo())).build();
        map.put("gruppo", cell);

        cell = DBPromoAgCell.builder().name("Meccanica").editable(false).type(cellTypeString)
                .value(entity.getId().getCodiceMeccanica()).build();
        map.put("meccanica", cell);

        cell = DBPromoAgCell.builder().name("Condizione").editable(false).type(cellTypeString)
                .value(entity.getId().getCodiceCondizione()).build();
        map.put("condizione", cell);

        cell = DBPromoAgCell.builder().name("Offerta").editable(false).type(cellTypeString)
                .value(entity.getValore()).build();
        map.put("offerta", cell);

        cell = DBPromoAgCell.builder().name("Stato Promozione").editable(false).type(cellTypeString)
                .value(String.format("%s - %s", entity.getCodiceStato(), entity.getDescrizioneStato())).build();
        map.put("stato", cell);

        cell = DBPromoAgCell.builder().name("Estendi LaEsse").editable(false).type(cellTypeString)
                .value(nullableValue(entity.getFlEstendiLaesse())).build();
        map.put("estendiLaEsse", cell);

        cell = DBPromoAgCell.builder().name("Estensione Cluster").editable(false).type(cellTypeString)
                .value(nullableValue(entity.getFlEstensioneCluster())).build();
        map.put("estensioneCluster", cell);

        if (isInCorso(entity, date)) {
            map.values().forEach(c -> c.setExtraClasses(Collections.singletonList("ax-white-bg")));
        }

        return JsonUtils.getMapper().valueToTree(map);
    }

    private boolean isInCorso(MuiReportArticoloEntity entity, Date date) {
        // entity.dataInizio <= :today and entity.dataFine > :today
        return dateTimeUtils.isBefore(entity.getDataInizio(), date, true)
                && dateTimeUtils.isAfter(entity.getDataFine(), date, false);
    }

    private ObjectNode createCopyPasteObjectNode() {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        map.put("codice", DBPromoAgCell.builder().name("Codice").editable(true)
                .type(DBPromoCellTypeEnum.STRING.getType()).value("").build());
        map.put("descrizione", DBPromoAgCell.builder().name("Descrizione").editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType()).value("").build());
        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createSelezioneObjectNode(ItemEntity item, FornitoreEntity fornitore) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
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

        return JsonUtils.getMapper().valueToTree(map);
    }

    private void mapItemEntityByFornitore(ArrayNode arrayNode, ItemEntity item) {
        if (item.getMuiAssortimentoFornitores() != null) {
            item.getMuiAssortimentoFornitores().stream()
                    .filter(f -> f.getFornitoreEntity() != null)
                    .forEach(f -> arrayNode.add(createSelezioneObjectNode(item, f.getFornitoreEntity())));
        }
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
}
