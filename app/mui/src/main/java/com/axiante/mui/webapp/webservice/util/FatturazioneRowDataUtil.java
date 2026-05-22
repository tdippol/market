package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoArticoloEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PrestazioniService;
import com.axiante.mui.dbpromo.persistence.service.StatoContributiService;
import com.axiante.mui.webapp.webservice.pojo.ItemHierarchy;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class FatturazioneRowDataUtil extends AbstractRowDataUtil {

    @Inject
    private Instance<PrestazioniService> prestazioniServiceInstance;

    @Inject
    private Instance<StatoContributiService> contributiServiceInstance;

    public String createContributiRowData(@NonNull List<ContributiPromoEntity> contributi) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        try {
            final Comparator<ContributiPromoEntity> byAnno = Comparator.comparing(c -> c.getPromozione().getAnno());
            final Comparator<ContributiPromoEntity> byCanale = Comparator.comparing(c -> c.getPromozione().getCanalePromozioneEntity().getDescrizione());
            final Comparator<ContributiPromoEntity> byPromo = Comparator.comparing(c -> c.getPromozione().getDescrizione());
            final Comparator<ContributiPromoEntity> byCompratore = Comparator.comparing(c -> c.getCompratore().getCodiceCompratore());
            final Comparator<ContributiPromoEntity> byFornitore = Comparator.comparing(c -> c.getFornitore().getCodiceFornitore());
            final Comparator<ContributiPromoEntity> byProgressivo = Comparator.nullsLast(Comparator.comparing(ContributiPromoEntity::getProgressivo));
            contributi.stream().sorted(byAnno.thenComparing(byCanale).thenComparing(byPromo).thenComparing(byCompratore)
                    .thenComparing(byFornitore).thenComparing(byProgressivo))
                    .forEach(c -> arrayNode.add(createContributoRowNode(c)));
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available contributi", ex);
            return "";
        }
    }

    public String createBillableItemsRowData(@NonNull List<ItemEntity> items) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        try {
            items.stream().sorted(Comparator.comparing(ItemEntity::getCodiceItem))
                    .forEach(i -> arrayNode.add(createBillableItemRowNode(i)));
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for billable items", ex);
            return "";
        }
    }

    public String createFatturazioneArticoliRowData(@NonNull List<ContributiPromoArticoloEntity> articoli) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        try {
            articoli.stream().sorted(Comparator.comparing(a -> a.getItem().getCodiceItem()))
                    .forEach(a -> arrayNode.add(createFatturazioneArticoloRowNode(a)));
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for articoli fatturazione", ex);
            return "";
        }
    }

    private ObjectNode createContributoRowNode(ContributiPromoEntity contributo) {
        final PromozioneTestataEntity testata = contributo.getPromozione();
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Id").editable(false).type(cellStringType)
                .value(String.valueOf(contributo.getId())).build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder().name("Id Promozione").editable(false).type(cellStringType)
                .value(String.valueOf(contributo.getPromozione().getId())).build();
        map.put("idPromozione", cell);

        cell = DBPromoAgCell.builder().name("Anno").editable(false).type(cellStringType)
                .value(testata.getAnno() == null ? "" : testata.getAnno()).build();
        map.put("anno", cell);

        String descrizioneCanale = testata.getMuiCanalePromozione() != null && testata.getMuiCanalePromozione().getDescrizione() != null
                ? testata.getMuiCanalePromozione().getDescrizione()
                : "";
        cell = DBPromoAgCell.builder().name("Canale").editable(false).type(cellStringType)
                .value(descrizioneCanale).build();
        map.put("canale", cell);

        cell = DBPromoAgCell.builder().name("Promozione").editable(false).type(cellStringType)
                .value(testata.getDescrizioneEstesa()).build();
        map.put("promozione", cell);

        cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(cellStringType)
                .value(String.format("[%s] %s", contributo.getCompratore().getCodiceCompratore(),
                        contributo.getCompratore().getDescrizione())).build();
        map.put("compratore", cell);

        final FornitoreEntity fornitore = contributo.getFornitore();
        cell = DBPromoAgCell.builder().name("Fornitore").editable(false).type(cellStringType)
                .value(String.format("[%s] %s - Contributo %s", fornitore.getCodiceFornitore(),
                        fornitore.getDescrizione(), nullableValue(contributo.getProgressivo())))
                .build();
        map.put("fornitore", cell);

        cell = DBPromoAgCell.builder().name("Prestazione").editable(false).type(cellStringType)
                .value(lookupPrestazione(contributo.getCodicePrestazione())).build();
        map.put("prestazione", cell);

        cell = DBPromoAgCell.builder().name("Data Liquidazione").editable(false).type(DBPromoCellTypeEnum.DATE.getType())
                .value(nullableValue(contributo.getDataLiquidazione())).build();
        map.put("dataLiquidazione", cell);

        cell = DBPromoAgCell.builder().name("Applicato").editable(false).type(DBPromoCellTypeEnum.NUMERIC.getType())
                .value(nullableValue(contributo.getValoreApplicato())).build();
        map.put("applicato", cell);

        cell = DBPromoAgCell.builder().name("Stato Rata").editable(false).type(cellStringType)
                .value(nullableValue(lookupStatoContributo(contributo.getCodiceStato()))).build();
        map.put("statoRata", cell);

        cell = DBPromoAgCell.builder().name("Saldo").editable(false).type(DBPromoCellTypeEnum.NUMERIC.getType())
                .value(nullableValue(contributo.getSaldoMovimenti())).build();
        map.put("saldo", cell);

        cell = DBPromoAgCell.builder().name("Note Compratore").editable(false).type(cellStringType)
                .value(nullableValue(contributo.getNotaCompratore())).build();
        map.put("noteCompratore", cell);

        cell = DBPromoAgCell.builder().name("Note Fatturazione").editable(false).type(cellStringType)
                .value(nullableValue(contributo.getNotaFattura())).build();
        map.put("noteFatturazione", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createBillableItemRowNode(ItemEntity item) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Codice Articolo").editable(false).type(cellStringType)
                .value(item.getCodiceItem()).build();
        map.put("codiceArticolo", cell);

        cell = DBPromoAgCell.builder().name("Descrizione").editable(false).type(cellStringType)
                .value(item.getDescrizione()).build();
        map.put("descrizione", cell);

        cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(cellStringType)
                .value(item.getCompratoreEntity().getFullDescription()).build();
        map.put("compratore", cell);

        final ItemHierarchy itemHierarchy = ItemHierarchy.build(item);

        cell = DBPromoAgCell.builder().name("Reparto").editable(false).type(cellStringType)
                .value(itemHierarchy.getReparto()).build();
        map.put("reparto", cell);

        cell = DBPromoAgCell.builder().name("Categoria").editable(false).type(cellStringType)
                .value(itemHierarchy.getCategoria()).build();
        map.put("categoria", cell);

        cell = DBPromoAgCell.builder().name("Grm").editable(false).type(cellStringType)
                .value(itemHierarchy.getGrm()).build();
        map.put("grm", cell);

        cell = DBPromoAgCell.builder().name("Sub Grm").editable(false).type(cellStringType)
                .value(itemHierarchy.getSubGrm()).build();
        map.put("subGrm", cell);

        cell = DBPromoAgCell.builder().name("Id").editable(false).type(cellStringType)
                .value(String.valueOf(item.getId())).build();
        map.put("id", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createFatturazioneArticoloRowNode(ContributiPromoArticoloEntity articoloEntity) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final ItemEntity item = articoloEntity.getItem();
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Codice Articolo").editable(false).type(cellStringType)
                .value(item.getCodiceItem()).build();
        map.put("codiceArticolo", cell);

        cell = DBPromoAgCell.builder().name("Descrizione").editable(false).type(cellStringType)
                .value(item.getDescrizione()).build();
        map.put("descrizione", cell);

        cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(cellStringType)
                .value(item.getCompratoreEntity().getFullDescription()).build();
        map.put("compratore", cell);

        final ItemHierarchy itemHierarchy = ItemHierarchy.build(item);

        cell = DBPromoAgCell.builder().name("Reparto").editable(false).type(cellStringType)
                .value(itemHierarchy.getReparto()).build();
        map.put("reparto", cell);

        cell = DBPromoAgCell.builder().name("Categoria").editable(false).type(cellStringType)
                .value(itemHierarchy.getCategoria()).build();
        map.put("categoria", cell);

        cell = DBPromoAgCell.builder().name("Grm").editable(false).type(cellStringType)
                .value(itemHierarchy.getGrm()).build();
        map.put("grm", cell);

        cell = DBPromoAgCell.builder().name("Sub Grm").editable(false).type(cellStringType)
                .value(itemHierarchy.getSubGrm()).build();
        map.put("subGrm", cell);

        cell = DBPromoAgCell.builder().name("Id").editable(false).type(cellStringType)
                .value(String.valueOf(articoloEntity.getId())).build();
        map.put("id", cell);

        if (Boolean.TRUE.equals(articoloEntity.getWarn())) {
            map.values().forEach(c -> c.setWarning(true));
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private String lookupPrestazione(String codicePrestazione) {
        if (codicePrestazione == null) {
            return "";
        }
        final String desc = prestazioniServiceInstance.get().findDescrizioneByCodice(codicePrestazione);
        return desc == null ? "" : desc;
    }

    private String lookupStatoContributo(String codiceContributo) {
        if (codiceContributo == null) {
            return "";
        }
        final String desc = contributiServiceInstance.get().findDescrizioneByCodice(codiceContributo);
        return desc == null ? "" : desc;
    }
}
