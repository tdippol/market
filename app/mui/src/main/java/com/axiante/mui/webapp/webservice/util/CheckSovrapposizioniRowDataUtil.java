package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class CheckSovrapposizioniRowDataUtil extends AbstractRowDataUtil {
    @Inject
    private Instance<ItemService> itemServiceInstance;

    public String createRowData(@NonNull Set<PromozionePianificazioneEntity> pianificazioni, boolean editable)
            throws Exception {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        pianificazioni.stream().sorted(Comparator.comparing(PromozionePianificazioneEntity::getId))
                .forEach(p -> arrayNode.add(createRowNode(p, editable)));
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
        return mapper.writeValueAsString(objectNode);
    }

    public String createSingleRow(@NonNull PromozionePianificazioneEntity pianificazione, boolean editable,
                                  String message) throws Exception {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ObjectNode objectNode = mapper.createObjectNode();
        objectNode.set(DbPromoConstants.ROW_DATA, createRowNode(pianificazione, editable));
        DbPromoAgCellUtils.putValue(objectNode, "callbackMessage", message, false);
        return mapper.writeValueAsString(objectNode);
    }

    private ObjectNode createRowNode(PromozionePianificazioneEntity pianificazione, boolean editable) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Id").editable(false).type(cellStringType)
                .value(String.valueOf(pianificazione.getId())).build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder().name("Meccanica").editable(false).type(cellStringType)
                .value(getMeccanica(pianificazione.getMeccanicaEntity())).build();
        map.put("meccanica", cell);

        cell = DBPromoAgCell.builder().name("Articolo").editable(false).type(cellStringType)
                .value(getArticolo(pianificazione.getElemento())).build();
        map.put("articolo", cell);

        cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(cellStringType)
                .value(getCompratore(pianificazione.getCodiceElemento(), pianificazione.getTipoElemento())).build();
        map.put("compratore", cell);

        cell = DBPromoAgCell.builder().name("Escludi Check").editable(true).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(String.valueOf(pianificazione.getEscludiCheckSovr())).build();
        map.put("escludiCheckSovr", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    private String getMeccanica(MeccanicheEntity meccanica) {
        return meccanica != null
                ? String.format("%s - %s", meccanica.getCodiceMeccanica(), meccanica.getDescrizione())
                : "";
    }

    private String getArticolo(String articolo) {
        return articolo != null ? articolo : "";
    }

    private String getCompratore(String codiceElemento, String tipoElemento) {
        if (!"ARTICOLO".equalsIgnoreCase(tipoElemento)) {
            return "";
        }
        Long idItem = Long.parseLong(codiceElemento);
        final ItemEntity item = itemServiceInstance.get().findById(idItem);
        return item != null && item.getCompratoreEntity() != null
                ? item.getCompratoreEntity().getFullDescription()
                : "";
    }
}
