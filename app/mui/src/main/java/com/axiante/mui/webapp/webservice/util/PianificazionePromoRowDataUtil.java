package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.CheckCompratoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CheckPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.NegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniNegoziEntity;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.webapp.webservice.pojo.ItemHierarchy;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
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
public class PianificazionePromoRowDataUtil {

    @Inject
    private Instance<ItemService> itemServiceInstance;

    private DateTimeUtils dateTimeUtils = new DateTimeUtils();

    public String createCompratoriRowData(@NonNull List<CompratoreEntity> compratori) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        try {
            compratori.stream().sorted(Comparator.comparing(CompratoreEntity::getCodiceCompratore))
                    .forEach(c -> arrayNode.add(createCompratoreRowNode(c)));
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available compratori", ex);
            return "";
        }
    }

    public String createCheckCompratoriRowData(@NonNull Collection<CheckCompratoriEntity> compratori) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        try {
            compratori.stream().sorted(byDate).forEach(c -> arrayNode.add(createCompratoreCheckRowNode(c)));
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available compratori", ex);
            return "";
        }
    }

    public String createCheckPianificazioneRowData(@NonNull List<CheckPianificazioneEntity> checks) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        try {
            checks.forEach(c -> arrayNode.add(createCheckPianificazioneRowNode(c)));
            final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for 'Controlli Pianificazione'", ex);
            return "";
        }
    }

    public String createPromoRiferimentoArticoliRowData(@NonNull List<MuiPromoArticoliDbPromoEntity> items) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        try {
            items.forEach(i -> arrayNode.add(createPromoRiferimentoArticoloRowNode(i)));
            final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.warn("Error creating JSON rowData for 'Articoli da Promo Riferimento'", ex);
            return "";
        }
    }
    
    public String createPlanoRiferimentoArticoliRowData(@NonNull List<MuiPlanoArticoliDbPromoEntity> items) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        try {
            items.forEach(i -> arrayNode.add(createPlanoRiferimentoArticoloRowNode(i)));
            final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.warn("Error creating JSON rowData for 'Articoli da Plano Riferimento'", ex);
            return "";
        }
    }


    public String createReportSovrapposizioniRowData(@NonNull List<ReportSovrapposizioniEntity> entities) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        try {
            entities.forEach(e -> arrayNode.add(createReportSovrapposizioniRowNode(e)));
            final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.warn("Error creating JSON rowData for 'Report Sovrapposizioni Pianificazione'", ex);
            return "";
        }
    }

    Comparator<CheckCompratoriEntity> byDate = (o1, o2) -> {
        try {
            if (o1.getDataAggiornamento() == null && o2.getDataAggiornamento() != null) {
                return o1.getDataAggiornamento().compareTo(o2.getDataInserimento());
            }
            if (o1.getDataAggiornamento() != null && o2.getDataAggiornamento() == null) {
                return o1.getDataInserimento().compareTo(o2.getDataAggiornamento());
            }
            if (o1.getDataAggiornamento() == null && o2.getDataAggiornamento() == null) {
                return o1.getDataInserimento().compareTo(o2.getDataInserimento());
            }
            return o1.getDataAggiornamento().compareTo(o2.getDataAggiornamento());
        } catch (Exception ex) {
            log.error("Error comparing", ex);
        }
        return 0;
    };

    private ObjectNode createCompratoreRowNode(CompratoreEntity c) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("ID").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(String.valueOf(c.getId())).build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(String.format("[%s] %s", c.getCodiceCompratore(), c.getDescrizione())).build();
        map.put("compratore", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createCompratoreCheckRowNode(CheckCompratoriEntity checkCompratore) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(String.format("[%s] %s", checkCompratore.getCompratoreEntity().getCodiceCompratore(),
                        checkCompratore.getCompratoreEntity().getDescrizione())).build();
        map.put("compratore", cell);

        final DataTypeParams dateParams = new DataTypeParams();
        dateParams.setDateFormat("DDD dd/mm/yyyy HH:MM.ss");
        cell = DBPromoAgCell.builder().name("Data").editable(false).type(DBPromoCellTypeEnum.DATE.getType())
                .dataTypeParams(dateParams)
                .value(checkCompratore.getDataAggiornamento() != null
                        ? dateTimeUtils.toExcelDate(checkCompratore.getDataAggiornamento())
                        : dateTimeUtils.toExcelDate(checkCompratore.getDataInserimento())).build();
        map.put("data", cell);

        cell = DBPromoAgCell.builder().name("Utente Ultimo Aggiornamento").editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(checkCompratore.getCodiceUtenteAggiornamento() == null
                        ? checkCompratore.getCodiceUtenteInserimento()
                        : checkCompratore.getCodiceUtenteAggiornamento()).build();
        map.put("lastEditor", cell);

        cell = DBPromoAgCell.builder().name("Esito").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(checkCompratore.getEsito()).build();
        map.put("esito", cell);

        cell = DBPromoAgCell.builder().name("Modificato").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(checkCompratore.getModificato() ? "SI" : "NO").build();
        map.put("modificato", cell);

        final boolean isKo = "KO".equalsIgnoreCase(checkCompratore.getEsito());
        final boolean isWarn = !isKo && checkCompratore.getModificato();

        if (isKo || isWarn) {
            map.values().forEach(c -> {
                c.setMandatory(isKo);
                c.setWarning(isWarn);
            });
        }

        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createCheckPianificazioneRowNode(CheckPianificazioneEntity check) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        final String stringCell = DBPromoCellTypeEnum.STRING.getType();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Tipo Controllo").editable(false).type(stringCell)
                .value(nullableValueAsString(check.getTipoControllo())).build();
        map.put("tipoControllo", cell);

        cell = DBPromoAgCell.builder().name("Descrizione").editable(false).type(stringCell)
                .value(nullableValueAsString(check.getDescrizioneControllo())).build();
        map.put("descrizione", cell);

        cell = DBPromoAgCell.builder().name("Codice Compratore").editable(false).type(stringCell)
                .value(check.getCompratore().getCodiceCompratore()).build();
        map.put("codiceCompratore", cell);

        cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(stringCell)
                .value(check.getCompratore().getDescrizione()).build();
        map.put("compratore", cell);

        cell = DBPromoAgCell.builder().name("Severità").editable(false).type(stringCell)
                .value(nullableValueAsString(check.getSeverita())).build();
        map.put("severita", cell);

        cell = DBPromoAgCell.builder().name("Codice Articolo").editable(false).type(stringCell)
                .value(getCodiceArticolo(check.getCodiceElemento())).build();
        map.put("codiceArticolo", cell);

        cell = DBPromoAgCell.builder().name("Descrizione Articolo").editable(false).type(stringCell)
                .value(nullableValueAsString(check.getElemento())).build();
        map.put("descrizioneArticolo", cell);

        cell = DBPromoAgCell.builder().name("Utente").editable(false).type(stringCell)
                .value(nullableValueAsString(check.getCodiceUtenteInserimento())).build();
        map.put("utente", cell);

        if ("Errore".equalsIgnoreCase(check.getSeverita())) {
            map.values().forEach(c -> c.setMandatory(true));
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createReportSovrapposizioniRowNode(ReportSovrapposizioniEntity entity) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        final String stringCell = DBPromoCellTypeEnum.STRING.getType();
        final String dateCell = DBPromoCellTypeEnum.DATE.getType();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Id Report").editable(false).type(stringCell)
                .value(String.valueOf(entity.getId())).build();
        map.put("idReport", cell);

        cell = DBPromoAgCell.builder().name("Codice Articolo").editable(false).type(stringCell)
                .value(entity.getItem().getCodiceItem()).build();
        map.put("codiceArticolo", cell);

        cell = DBPromoAgCell.builder().name("Descrizione Articolo").editable(false).type(stringCell)
                .value(nullableValueAsString(entity.getItem().getDescrizione())).build();
        map.put("descrizioneArticolo", cell);

        cell = DBPromoAgCell.builder().name("Meccanica").editable(false).type(stringCell)
                .value(prepareMeccanica(entity.getMeccanica())).build();
        map.put("meccanica", cell);

        cell = DBPromoAgCell.builder().name("Condizione").editable(false).type(stringCell)
                .value(nullableValueAsString(entity.getValore())).build();
        map.put("condizione", cell);

        cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(dateCell).dataTypeParams(getDateTypeParams())
                .value(dateTimeUtils.toExcelDate(entity.getDataInizioArticolo())).build();
        map.put("dataInizio", cell);

        cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(dateCell).dataTypeParams(getDateTypeParams())
                .value(dateTimeUtils.toExcelDate(entity.getDataFineArticolo())).build();
        map.put("dataFine", cell);

        cell = DBPromoAgCell.builder().name("Codice Promo Sovr").editable(false).type(stringCell)
                .value(entity.getCodicePromoSovrapposta()).build();
        map.put("codicePromoSovr", cell);

        cell = DBPromoAgCell.builder().name("Descrizione Promo Sovr").editable(false).type(stringCell)
                .value(nullableValueAsString(entity.getDescrizionePromoSovrapposta())).build();
        map.put("descrizionePromoSovr", cell);

        cell = DBPromoAgCell.builder().name("Meccanica Sovr").editable(false).type(stringCell)
                .value(prepareMeccanica(entity.getMeccanicaSovrapposta())).build();
        map.put("meccanicaSovr", cell);

        cell = DBPromoAgCell.builder().name("Condizione Sovr").editable(false).type(stringCell)
                .value(nullableValueAsString(entity.getValoreSovrapposizione())).build();
        map.put("condizioneSovr", cell);

        cell = DBPromoAgCell.builder().name("Data Inizio Sovr").editable(false).type(dateCell).dataTypeParams(getDateTypeParams())
                .value(dateTimeUtils.toExcelDate(entity.getDataInizioArticoloSovrapposto())).build();
        map.put("dataInizioSovr", cell);

        cell = DBPromoAgCell.builder().name("Data Fine Sovr").editable(false).type(dateCell).dataTypeParams(getDateTypeParams())
                .value(dateTimeUtils.toExcelDate(entity.getDataFineArticoloSovrapposto())).build();
        map.put("dataFineSovr", cell);

        cell = DBPromoAgCell.builder().name("Numero Negozi").editable(false).type(stringCell)
                .value(nullableValueAsString(entity.getNumNegozi())).build();
        map.put("numeroNegozi", cell);

        cell = DBPromoAgCell.builder().name("Severita").editable(false).type(stringCell)
                .value(entity.getSeverita()).build();
        map.put("severita", cell);

        if ("Errore".equalsIgnoreCase(entity.getSeverita())) {
            map.values().forEach(c -> c.setMandatory(true));
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private String getCodiceArticolo(String codiceElemento) {
        if (StringUtils.isBlank(codiceElemento)) {
            return "";
        }
        try {
            return itemServiceInstance.get().findCodiceById(Long.parseLong(codiceElemento));
        } catch (Exception ex) {
            log.error(String.format("Error parsing codiceElemento '%s' as Long", codiceElemento), ex);
            return "";
        }
    }

    private ObjectNode createPromoRiferimentoArticoloRowNode(MuiPromoArticoliDbPromoEntity item) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Codice Promozione").editable(false).type(cellStringType)
                .value(item.getId().getCodicePromozione()).build();
        map.put("codicePromozione", cell);

        cell = DBPromoAgCell.builder().name("Codice Item").editable(false).type(cellStringType)
                .value(nullableValueAsString(item.getId().getCodiceItem())).build();
        map.put("codiceItem", cell);

        cell = DBPromoAgCell.builder().name("Tipo Item").editable(false).type(cellStringType)
                .value(item.getId().getTipoItem()).build();
        map.put("tipoItem", cell);

        cell = DBPromoAgCell.builder().name("Meccanica").editable(false).type(cellStringType)
                .value(prepareMeccanica(item.getCodiceMeccanica(), item.getDescrizioneMeccanica())).build();
        map.put("meccanica", cell);

        cell = DBPromoAgCell.builder().value("Offerta").editable(false).type(cellStringType)
                .value(nullableValueAsString(item.getValore())).build();
        map.put("offerta", cell);

        cell = DBPromoAgCell.builder().name("Differenziazione").editable(false).type(cellStringType)
                .value(nullableValueAsString(item.getTipoOfferta())).build();
        map.put("differenziazione", cell);

        String codiceArticolo = "";
        String descrizioneArticolo = "";
        String codiceCompratore = "";
        String descrizioneCompratore = "";
        final ItemEntity itemEntity = item.getItem();
        if (itemEntity != null) {
            codiceArticolo = nullableValueAsString(itemEntity.getCodiceItem());
            descrizioneArticolo = nullableValueAsString(itemEntity.getDescrizione());
            final CompratoreEntity compratore = itemEntity.getCompratoreEntity();
            if (compratore != null) {
                codiceCompratore = nullableValueAsString(compratore.getCodiceCompratore());
                descrizioneCompratore = nullableValueAsString(compratore.getDescrizione());
            } else {
                if (log.isDebugEnabled()) {
                    log.warn(String.format("Compratore non presente su articolo id %d", itemEntity.getId()));
                }
            }
        } else {
            if (log.isDebugEnabled()) {
                log.warn(String.format("Item %d su promo %s non trovato",
                        item.getId().getCodiceItem(), item.getId().getCodicePromozione()));
            }
        }
        cell = DBPromoAgCell.builder().name("Codice Articolo").editable(false).type(cellStringType)
                .value(codiceArticolo).build();
        map.put("codiceArticolo", cell);

        cell = DBPromoAgCell.builder().name("Descrizione Articolo").editable(false).type(cellStringType)
                .value(descrizioneArticolo).build();
        map.put("descrizioneArticolo", cell);

        cell = DBPromoAgCell.builder().name("Codice Compratore").editable(false).type(cellStringType)
                .value(codiceCompratore).build();
        map.put("codiceCompratore", cell);

        cell = DBPromoAgCell.builder().name("Descrizione Compratore").editable(false).type(cellStringType)
                .value(descrizioneCompratore).build();
        map.put("descrizioneCompratore", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createPlanoRiferimentoArticoloRowNode(MuiPlanoArticoliDbPromoEntity item) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Codice Plano").editable(false).type(cellStringType)
                .value(item.getId().getIdPlano()).build();
        map.put("codicePlano", cell);

        cell = DBPromoAgCell.builder().name("Codice Item").editable(false).type(cellStringType)
                .value(String.valueOf(item.getId().getCodiceItem())).build();
        map.put("codiceItem", cell);

        cell = DBPromoAgCell.builder().name("Tipo Item").editable(false).type(cellStringType)
                .value(item.getId().getTipoItem()).build();
        map.put("tipoItem", cell);

        cell = DBPromoAgCell.builder().name("Planogramma").editable(false).type(cellStringType)
                .value(nullableValueAsString(item.getDescrizionePlanogramma())).build();
        map.put("planogramma", cell);

        final ItemEntity itemEntity = item.getItem();

        if ( itemEntity != null ) {
            cell = DBPromoAgCell.builder().name("Codice Articolo").editable(false).type(cellStringType)
                    .value(nullableValueAsString(itemEntity.getCodiceItem())).build();
            map.put("codiceArticolo", cell);

            cell = DBPromoAgCell.builder().name("Descrizione Articolo").editable(false).type(cellStringType)
                    .value(nullableValueAsString(itemEntity.getDescrizione())).build();
            map.put("descrizioneArticolo", cell);

            cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(cellStringType)
                    .value(itemEntity.getCompratoreEntity().getCodiceCompratore() == null ? ""
                            : String.format("[%s] %s", itemEntity.getCompratoreEntity().getCodiceCompratore(),
                            itemEntity.getCompratoreEntity().getDescrizione() != null
                                    ? itemEntity.getCompratoreEntity().getDescrizione().toUpperCase()
                                    : ""))
                    .build();
            map.put("compratore", cell);

            cell = DBPromoAgCell.builder().name("In Out").editable(false).type(cellStringType)
                    .value(String.valueOf(itemEntity.getInOut())).build();
            map.put("inOut", cell);

            final ItemHierarchy itemHierarchy = ItemHierarchy.build(itemEntity);

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
        } else {
            log.warn("Item " + item.getId() + " non trovato");
            cell = DBPromoAgCell.builder().name("Codice Articolo").editable(false).type(cellStringType).value("").build();
            map.put("codiceArticolo", cell);
            cell = DBPromoAgCell.builder().name("Descrizione Articolo").editable(false).type(cellStringType).value("").build();
            map.put("descrizioneArticolo", cell);
            cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(cellStringType).value("").build();
            map.put("compratore", cell);
            cell = DBPromoAgCell.builder().name("In Out").editable(false).type(cellStringType).value("").build();
            map.put("inOut", cell);
            cell = DBPromoAgCell.builder().name("Reparto").editable(false).type(cellStringType).value("").build();
            map.put("reparto", cell);
            cell = DBPromoAgCell.builder().name("Categoria").editable(false).type(cellStringType).value("").build();
            map.put("categoria", cell);
            cell = DBPromoAgCell.builder().name("Grm").editable(false).type(cellStringType).value("").build();
            map.put("grm", cell);
            cell = DBPromoAgCell.builder().name("Sub Grm").editable(false).type(cellStringType).value("").build();
            map.put("subGrm", cell);
        }

        return JsonUtils.getMapper().valueToTree(map);
    }

    private String prepareMeccanica(String codiceMeccanica, String descrizioneMeccanica) {
        if (StringUtils.isBlank(codiceMeccanica)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(codiceMeccanica.trim());
        if (!StringUtils.isBlank(descrizioneMeccanica)) {
            sb.append(" - ").append(descrizioneMeccanica.trim());
        }
        return sb.toString();
    }

    private String prepareMeccanica(MeccanicheEntity meccanica) {
        return prepareMeccanica(meccanica.getCodiceMeccanica(), meccanica.getDescrizione());
    }

    private String nullableValueAsString(final String value) {
        return value == null ? "" : value;
    }

    private String nullableValueAsString(BigDecimal value) {
        return value == null ? "" : nullableValueAsString(String.valueOf(value));
    }

    private String nullableValueAsString(Integer value) {
        return value == null ? "" : nullableValueAsString(String.valueOf(value));
    }

    private String nullableValueAsString(Long value) {
        return value == null ? "" : nullableValueAsString(String.valueOf(value));
    }

    private DataTypeParams getDateTypeParams() {
        final DataTypeParams dateParams = new DataTypeParams();
        dateParams.setDateFormat("DDD dd/mm/yyyy");
        return dateParams;
    }

    public String createDettaglioSovrapposizioniRowData(@NonNull List<ReportSovrapposizioniNegoziEntity> checks) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        try {
            checks.forEach(c -> arrayNode.add(createDettaglioSovrapposizioniRowNode(c)));
            final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for 'Controlli Pianificazione'", ex);
            return "";
        }
    }

    private ObjectNode createDettaglioSovrapposizioniRowNode(@NonNull ReportSovrapposizioniNegoziEntity dettaglioSovrapposizione) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        final NegozioEntity negozio = dettaglioSovrapposizione.getNegozio();
        if ( negozio != null ) {
            final String stringCell = DBPromoCellTypeEnum.STRING.getType();
            DBPromoAgCell cell = DBPromoAgCell.builder().name("Codice Negozio").editable(false).type(stringCell)
                    .value(nullableValueAsString(negozio.getCodiceNegozio())).build();
            map.put("codiceNegozio", cell);

            cell = DBPromoAgCell.builder().name("Negozio").editable(false).type(stringCell)
                    .value(nullableValueAsString(negozio.getDescrizione())).build();
            map.put("descrizione", cell);

            cell = DBPromoAgCell.builder().name("Codice Società").editable(false).type(stringCell)
                    .value(nullableValueAsString(negozio.getSocieta())).build();
            map.put("societa", cell);
            
            cell = DBPromoAgCell.builder().name("Società").editable(false).type(stringCell)
                    .value(nullableValueAsString(negozio.getDescrizioneSocieta())).build();
            map.put("descrizioneSocieta", cell);

            cell = DBPromoAgCell.builder().name("Codice Zona").editable(false).type(stringCell)
                    .value(nullableValueAsString(negozio.getCodiceZona())).build();
            map.put("codiceZona", cell);

            cell = DBPromoAgCell.builder().name("Zona").editable(false).type(stringCell)
                    .value(nullableValueAsString(negozio.getDescrizioneZona())).build();
            map.put("descrizioneZona", cell);
        	
        }
        return JsonUtils.getMapper().valueToTree(map);
    }
}
