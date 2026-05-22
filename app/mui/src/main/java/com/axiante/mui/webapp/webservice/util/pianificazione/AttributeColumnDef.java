package com.axiante.mui.webapp.webservice.util.pianificazione;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.AttributoArticoloEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.AttributoArticoloService;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class AttributeColumnDef extends AbstractColumnDef implements DynamicColumnDef {

    @Inject
    private AttributoArticoloService attributoService;

    @Override
    public String generateColumnDefByPromoConfiguration(PromozioneTestataEntity promozioneTestataEntity,
                                                        String hiddenColumns, String grid, String contesto,
                                                        boolean contestoRequired) {
        return loadColumnDefFromFile("pianificazione_selezione_attributo_columnDef.json", hiddenColumns, grid,
                contesto, contestoRequired);
    }

    @Override
    public String generateRowDataByPromoConfiguration(String buyerId, Boolean isUserAdmin, List<String> codiciGruppo,
                                                      String operationMessage) {
        throw new UnsupportedOperationException("This functionality is not supported for this ColumnDef");
    }

    @Override
    @Deprecated
    public ObjectNode generateRowDataByPromoPianificazioneMaster(PromozionePianificazioneEntity pianificazione) {
        throw new UnsupportedOperationException("This functionality is not supported for this ColumnDef");
    }

    @Override
    public ObjectNode generateRowDataByPromoConfiguration(PromozioneTestataEntity testata, Long idMeccanica,
                                                          PlanningArticleMultiFilterParam params, Boolean isUserAdmin,
                                                          List<String> codiciGruppo) {
        throw new UnsupportedOperationException("This functionality is not supported for this ColumnDef");
    }

    @Override
    public String generateRowDataFilteredByPromoConfiguration(String promoId, String radioChecked) {
        throw new UnsupportedOperationException("This functionality is not supported for this ColumnDef");
    }

    @Override
    public ObjectNode generateRowDataByPromoElementMechanic(PromozioneTestataEntity testata, Long idMeccanica,
                                                            String buyerId, Boolean isUserAdmin, List<String> codiciGruppo,
                                                            String operationMessage) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        attributoService.findAllActive().stream().sorted(Comparator.comparing(AttributoArticoloEntity::getCodiceAttributo))
                .forEach(a -> arrayNode.add(mapEntityToJson(a)));
        ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
        objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
        return objectNode;
    }

    private ObjectNode mapEntityToJson(AttributoArticoloEntity attributo) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("id").editable(false).type(cellTypeString)
                .value(String.valueOf(attributo.getId())).build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder().name("Codice Attributo").editable(Boolean.FALSE).type(cellTypeString)
                .value(getNullableValue(attributo.getCodiceAttributo())).build();
        map.put("codiceAttributo", cell);

        cell = DBPromoAgCell.builder().name("Descrizione Attributo").editable(Boolean.FALSE).type(cellTypeString)
                .value(getNullableValue(attributo.getDescrizione())).build();
        map.put("descrizioneAttributo", cell);

        cell = DBPromoAgCell.builder().name("elemento").editable(false).type(cellTypeString)
                .value(getFullDescription(attributo.getCodiceAttributo(), attributo.getDescrizione())).build();
        map.put("elemento", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }
}
