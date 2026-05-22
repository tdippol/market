package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaBuoniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;
import com.axiante.mui.webapp.utils.security.CumulabilitaSecurity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class CumulabilitaUtil {

    @Inject
    private Instance<CumulabilitaSecurity> cumulabilitaSecurityInstance;

    private DateTimeUtils dateTimeUtils = new DateTimeUtils();

    public String createRowData(@NonNull final List<CumulabilitaEsclusivitaEntity> entities) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        String json = "";
        try {
            final Comparator<CumulabilitaEsclusivitaEntity> sorted = Comparator
                    .comparing(CumulabilitaEsclusivitaEntity::getDataPubblicazione, Comparator.nullsFirst(Comparator.naturalOrder()))
                    .thenComparing(CumulabilitaEsclusivitaEntity::getId);
            entities.stream().sorted(sorted).forEach(e -> arrayNode.add(createRowNode(e)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error getting rowData for Cumulabilita", ex);
        }
        return json;
    }

    public String createRowDataBuoni(@NonNull final CumulabilitaEsclusivitaEntity cumulabilita) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        String json = "";
        try {
            cumulabilita.getCumulabilitaBuoniEntities().stream()
                    .sorted(
                            Comparator.comparing(CumulabilitaBuoniEntity::getCodicePromozione)
                                    .thenComparing(CumulabilitaBuoniEntity::getPrefissoBS))
                    .forEach(p -> arrayNode.add(createRowNodeBuono(cumulabilita, p)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error getting rowData for Buoni Cumulabilita", ex);
        }
        return json;
    }

    public String createRowDataBuoniAvailable(@NonNull final List<MuiReportBSEntity> pianificazioni, final String idCumulabilita) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        String json = "";
        try {
            pianificazioni.forEach(p -> arrayNode.add(createRowNodeBuono(p,idCumulabilita)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error getting rowData for Buoni Cumulabilita Disponibili", ex);
        }
        return json;
    }

    private ObjectNode createRowNode(CumulabilitaEsclusivitaEntity entity) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        final CumulabilitaSecurity cumulabilitaSecurity = cumulabilitaSecurityInstance.get();
        final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();
        final String cellTypeDate = DBPromoCellTypeEnum.DATE.getType();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("ID").editable(false).type(cellTypeString)
                .value(String.valueOf(entity.getId())).build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder().name("Prefisso").editable(false).type(cellTypeString)
                .value(entity.getPrefissoBS()).build();

        map.put("prefisso", cell);
        cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(cellTypeDate)
                .value(entity.getDataInizio() == null ? "" : dateTimeUtils.toExcelDate(entity.getDataInizio()))
                .build();
        map.put("dataInizio", cell);

        cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(cellTypeDate)
                .value(entity.getDataFine() == null ? "" : dateTimeUtils.toExcelDate(entity.getDataFine()))
                .build();
        map.put("dataFine", cell);

        cell = DBPromoAgCell.builder().name("Descrizione").editable(false).type(cellTypeString)
                .value(entity.getDescrizione()).build();
        map.put("descrizione", cell);


        cell = DBPromoAgCell.builder().name("Data Pubblicazione").editable(false).type(cellTypeDate)
                .value(entity.getDataPubblicazione() == null ? "" : dateTimeUtils.toExcelDate(entity.getDataPubblicazione()))
                .build();
        map.put("dataPubblicazione", cell);

        cell = DBPromoAgCell.builder().name("Data Annullamento").editable(false).type(cellTypeDate)
                .value(entity.getDataAnnullamento() == null ? "" : dateTimeUtils.toExcelDate(entity.getDataAnnullamento()))
                .build();
        map.put("dataAnnullamento", cell);

        cell = DBPromoAgCell.builder().name("publishEnabled")
                .value(String.valueOf(cumulabilitaSecurity.canPublishCumulabilita(entity)))
                .build();
        map.put("publishEnabled", cell);

        cell = DBPromoAgCell.builder().name("cancelEnabled")
                .value(String.valueOf(cumulabilitaSecurity.canCancelCumulabilita(entity))).build();
        map.put("cancelEnabled", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createRowNodeBuono(@NonNull MuiReportBSEntity cumulabilitaBuoniEntity, final String idCumulabilita) {
        return JsonUtils.getMapper().valueToTree(createMapBuono(cumulabilitaBuoniEntity));
    }

    private ObjectNode createRowNodeBuono(@NonNull CumulabilitaEsclusivitaEntity cumulabilita,
                                          @NonNull CumulabilitaBuoniEntity cumulabilitaBuoniEntity) {
        final Map<String, DBPromoAgCell> map = createMapBuono(cumulabilitaBuoniEntity);

        DBPromoAgCell cell = DBPromoAgCell.builder().name("ID CUMULABILITA").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(String.valueOf(cumulabilita.getId())).build();
        map.put("idCumulabilita", cell);

        boolean deleteable = true;
        if (cumulabilitaBuoniEntity.getBuonoDefault()){
            deleteable = false;
        } else {
            deleteable = cumulabilitaSecurityInstance.get().canDeleteBuono(cumulabilita);
        }
        cell = DBPromoAgCell.builder().name("deleteEnabled")
                .value(String.valueOf(deleteable)).build();
        map.put("deleteEnabled", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    private Map<String, DBPromoAgCell> createMapBuono(@NonNull MuiReportBSEntity reportBSEntity) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();
        final String cellTypeDate = DBPromoCellTypeEnum.DATE.getType();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("ID").editable(false).type(cellTypeString)
                .value(String.valueOf(reportBSEntity.getChiave())).build();
        map.put("id", cell);
        cell = DBPromoAgCell.builder().name("Canale").editable(false).type(cellTypeString)
                .value(reportBSEntity.getDescrizioneCanale()).build();
        map.put("canale", cell);

        cell = DBPromoAgCell.builder().name("Cod.Promo").editable(false).type(cellTypeString)
                .value(reportBSEntity.getId().getCodicePromozione()).build();
        map.put("codicePromo", cell);

        cell = DBPromoAgCell.builder().name("Promozione").editable(false).type(cellTypeString)
                .value(reportBSEntity.getDescrizionePromozione()).build();
        map.put("promozione", cell);

        cell = DBPromoAgCell.builder().name("Prefisso Cumulabile").editable(false).type(cellTypeString)
                .value(reportBSEntity.getId().getPrefissoBS()).build();
        map.put("prefissoCumulabile", cell);

        cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(cellTypeDate)
                .value(nullableValueAsString(reportBSEntity.getDataInizio())).build();
        map.put("dataInizio", cell);

        cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(cellTypeDate)
                .value(nullableValueAsString(reportBSEntity.getDataFine())).build();
        map.put("dataFine", cell);

        return map;
    }

    private Map<String, DBPromoAgCell> createMapBuono(@NonNull CumulabilitaBuoniEntity cumulabilitaBuoniEntity) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();
        final String cellTypeDate = DBPromoCellTypeEnum.DATE.getType();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("ID").editable(false).type(cellTypeString)
                .value(String.valueOf(cumulabilitaBuoniEntity.getId())).build();
        map.put("id", cell);
        cell = DBPromoAgCell.builder().name("Canale").editable(false).type(cellTypeString)
                .value(cumulabilitaBuoniEntity.getCodiceCanale()).build();
        map.put("canale", cell);

        cell = DBPromoAgCell.builder().name("Cod.Promo").editable(false).type(cellTypeString)
                .value(cumulabilitaBuoniEntity.getCodicePromozione()).build();
        map.put("codicePromo", cell);

        cell = DBPromoAgCell.builder().name("Promozione").editable(false).type(cellTypeString)
                .value(cumulabilitaBuoniEntity.getDescrizionePromozione()).build();
        map.put("promozione", cell);

        cell = DBPromoAgCell.builder().name("Prefisso Cumulabile").editable(false).type(cellTypeString)
                .value(cumulabilitaBuoniEntity.getPrefissoBS()).build();
        map.put("prefissoCumulabile", cell);

        cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(cellTypeDate)
                .value(nullableValueAsString(cumulabilitaBuoniEntity.getDataInizio())).build();
        map.put("dataInizio", cell);

        cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(cellTypeDate)
                .value(nullableValueAsString(cumulabilitaBuoniEntity.getDataFine())).build();
        map.put("dataFine", cell);

        return map;
    }

    private String nullableValueAsString(Integer value) {
        return value == null ? "" : String.valueOf(value);
    }

    private String nullableValueAsString(Date value) {
        return value == null ? "" : dateTimeUtils.toExcelDate(value);
    }
}
