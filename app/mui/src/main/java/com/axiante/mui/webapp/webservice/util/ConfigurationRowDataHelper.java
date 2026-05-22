package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.ComboBoxValues;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.utils.ComboBoxFactory;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.CfgLivelloPianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneCampiService;
import com.axiante.mui.webapp.webservice.util.configuration.CfgPianificazioneEntityHelper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
@Slf4j
public class ConfigurationRowDataHelper {

    @Inject
    private CfgLivelloPianificazioneService livelloPianificazioneService;

    @Inject
    private PianificazioneCampiService pianificazioneCampiService;

    @Inject
    private CfgConfHeaderService cfgConfHeaderService;

    @Inject
    private ComboBoxFactory comboBoxFactory;

    @Inject
    private CfgPianificazioneEntityHelper pianificazioneEntityHelper;

    /**
     * Creazione rowData per griglia Configurazione Pianificazione Header
     * @param setPianificazioneEntity set pianificazione
     * @return JSON rowData griglia
     */
    public String createRowDataPlanningHeader(@NonNull CfgSetPianificazioneEntity setPianificazioneEntity) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";
        try {
            final List<ComboBoxValues> levels = comboBoxFactory.from(livelloPianificazioneService.findAll(), false);
            final List<CfgConfHeaderEntity> headers = setPianificazioneEntity.getMuiCfgConfHeaders().stream()
                    .sorted(byCodiceMeccanica)
                    .collect(Collectors.toList());
            final boolean editable = PromoAcl.isCfgPianificazioneEditable(setPianificazioneEntity);
            headers.forEach(h -> arrayNode.add(createRowNode(h, setPianificazioneEntity.getId(), levels, editable)));
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error processing rowData for setPianificazione " + setPianificazioneEntity.getId(), ex);
        }
        return json;
    }

    /**
     * Creazione rowData per griglia Configurazione Pianificazione Tipo Elemento
     * @param confHeaderEntity header di configurazione
     * @return JSON rowData griglia
     */
    public String createRowDataPlanningTipoElemento(@NonNull CfgConfHeaderEntity confHeaderEntity) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";
        try {
            final List<CfgTipoElementoEntity> tipiElemento = confHeaderEntity.getTipiElemento().stream()
                    .sorted(Comparator.comparing(CfgTipoElementoEntity::getId))
                    .collect(Collectors.toList());
            final boolean editable = PromoAcl.isCfgPianificazioneEditable(confHeaderEntity.getMuiCfgSetPianificazione());
            tipiElemento.forEach(e -> arrayNode.add(createRowNode(e, confHeaderEntity.getId(), editable)));
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error processing rowData for confHeader " + confHeaderEntity.getId(), ex);
        }
        return json;
    }

    /**
     * Creazione rowData per griglia Configurazione Pianificazione Regola
     * @param confHeaderEntity header di configurazione
     * @return JSON rowData griglia
     */
    public String createRowDataPlanningPianificazione(@NonNull CfgConfHeaderEntity confHeaderEntity) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";
        try {
            final List<CfgPianificazioneEntity> pianificazioni = confHeaderEntity.getMuiCfgPianificaziones().stream()
                    .sorted(byTipoRiga.thenComparing(byCampo))
                    .collect(Collectors.toList());
            final boolean editable = PromoAcl.isCfgPianificazioneEditable(confHeaderEntity.getMuiCfgSetPianificazione());
            pianificazioni.forEach(p -> arrayNode.add(createRowNode(p, confHeaderEntity.getId(), editable)));
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error processing rowData for pianificazione with idHeader " + confHeaderEntity.getId(), ex);
        }
        return json;
    }

    public String createRowDataPlanningCampi() {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";
        try {
            pianificazioneCampiService.findAll().forEach(f -> arrayNode.add(createRowNode(f)));
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error processing rowData for pianificazione campi", ex);
        }
        return  json;
    }

    /**
     * Creazione singola riga di pianificazione per la griglia 'Regole'
     * @param pianificazioneEntity riga di pianificazione
     * @param idHeader id header
     * @return il nodo JSON relativo alla riga di pianificazione
     */
    public ObjectNode createRowNode(CfgPianificazioneEntity pianificazioneEntity, Long idHeader) {
        return createRowNode(pianificazioneEntity, idHeader, true);
    }

    /**
     * Creazione singola riga di pianificazione per la griglia 'Regole'
     * @param pianificazioneEntity riga di pianificazione
     * @param idHeader id header
     * @param editable true se le celle sono modificabili, false altrimenti
     * @return il nodo JSON relativo alla riga di pianificazione
     */
    private ObjectNode createRowNode(CfgPianificazioneEntity pianificazioneEntity, Long idHeader, boolean editable) {
        final HashedMap<String, DBPromoAgCell> rowData = new HashedMap<>();
        final boolean isEntityLookup = pianificazioneEntityHelper.isEntityLookup(pianificazioneEntity);
        final boolean isFormulaEnabled = pianificazioneEntityHelper.isFormulaEnabled(pianificazioneEntity);

        DBPromoAgCell cell = DBPromoAgCell.builder().name("idCfgPianificazione").editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(pianificazioneEntity.getId())).build();
        rowData.put("idCfgPianificazione", cell);

        cell = DBPromoAgCell.builder().name("idCfgConfHeader").editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(idHeader)).build();
        rowData.put("idCfgConfHeader", cell);

        cell = DBPromoAgCell.builder().name("Tipo Riga").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(pianificazioneEntity.getMuiCfgPianificazTipoRiga() == null
                        ? ""
                        : pianificazioneEntity.getMuiCfgPianificazTipoRiga().getDescrizione())
                .build();
        rowData.put("tipoRiga", cell);

        cell = DBPromoAgCell.builder().name("Campo").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(pianificazioneEntity.getMuiCfgPianificazioneCampi() == null
                        ? ""
                        : pianificazioneEntity.getMuiCfgPianificazioneCampi().getDescrizione())
                .build();
        rowData.put("campo", cell);

        cell = DBPromoAgCell.builder().name("Codice Campo").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(pianificazioneEntity.getMuiCfgPianificazioneCampi() == null
                        ? ""
                        : pianificazioneEntity.getMuiCfgPianificazioneCampi().getCodiceCampo())
                .build();
        rowData.put("codiceCampo", cell);

        cell = DBPromoAgCell.builder().name("Descrizione").editable(true).type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(pianificazioneEntity.getDescrizione())).build();
        rowData.put("descrizione", cell);
        
        cell = DBPromoAgCell.builder().name("Nome Campo").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(pianificazioneEntity.getMuiCfgPianificazioneCampi() == null
                        ? ""
                        : nullAsEmptyString(pianificazioneEntity.getMuiCfgPianificazioneCampi().getCampo()))
                .build();
        rowData.put("nomeCampo", cell);
        
        cell = DBPromoAgCell.builder().name("Hide").editable(editable || isEntityLookup || isFormulaEnabled)
                .type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(pianificazioneEntity.getHide())).build();
        rowData.put("hide", cell);

        cell = DBPromoAgCell.builder().name("Mandatory").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(isEntityLookup
                        ? String.valueOf(false)
                        : boolFlagAsString(pianificazioneEntity.getMandatory())).build();
        rowData.put("mandatory", cell);

        cell = DBPromoAgCell.builder().name("Lista").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(pianificazioneEntity.getLista())).build();
        rowData.put("lista", cell);

        cell = DBPromoAgCell.builder().name("Tipo Lista").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(pianificazioneEntity.getTipoLista())).build();
        rowData.put("tipo_lista", cell);

        cell = DBPromoAgCell.builder().name("Render As ComboBox").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(pianificazioneEntity.getRenderAsCombo())).build();
        rowData.put("renderAsCombo", cell);

        cell = DBPromoAgCell.builder().name("Ordine").editable(editable || isEntityLookup || isFormulaEnabled)
                .type(DBPromoCellTypeEnum.NUMERIC.getType())
                .value(pianificazioneEntity.getOrdinamento() == null
                        ? ""
                        : pianificazioneEntity.getOrdinamento().toString())
                .dataTypeParams(DataTypeParams.builder().precision(0).build())
                .build();
        rowData.put("ordinamento", cell);

        cell = DBPromoAgCell.builder().name("Lunghezza").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.NUMERIC.getType())
                .value(pianificazioneEntity.getLength() == null
                        ? ""
                        : pianificazioneEntity.getLength().toString())
                .dataTypeParams(DataTypeParams.builder().precision(0).build())
                .build();
        rowData.put("length", cell);

        cell = DBPromoAgCell.builder().name("Sicurezza R/W").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.PICKLIST.getType())
                .picklistValues(new String[] { "R", "W" })
                .value(isEntityLookup
                        ? "R"
                        : nullAsEmptyString(pianificazioneEntity.getSicurezza())).build();
        rowData.put("sicurezza", cell);

        cell = DBPromoAgCell.builder().name("Multiselect").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(pianificazioneEntity.getMultiselect())).build();
        rowData.put("flag_multiselect", cell);

        cell = DBPromoAgCell.builder().name("Default Value").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(pianificazioneEntity.getDefValue())).build();
        rowData.put("def_value", cell);

        cell = DBPromoAgCell.builder().name("listaCondizionale").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(pianificazioneEntity.getListaCondizionale())).build();
        rowData.put("listaCondizionale", cell);

        cell = DBPromoAgCell.builder().name("chiave").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(pianificazioneEntity.getChiave())).build();
        rowData.put("chiave", cell);

        cell = DBPromoAgCell.builder().name("Warning").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(isEntityLookup
                        ? String.valueOf(false)
                        : boolFlagAsString(pianificazioneEntity.getWarn())).build();
        rowData.put("warn", cell);

        boolean editableValidoSeRaggruppamento=false;
        if(pianificazioneEntity.getMuiCfgConfHeader() != null && 
        	pianificazioneEntity.getMuiCfgConfHeader().getLivelloPianificazione() !=null &&
        	pianificazioneEntity.getMuiCfgConfHeader().getLivelloPianificazione().getCodice().equalsIgnoreCase("SET") &&
        	pianificazioneEntity.getMuiCfgPianificazTipoRiga() != null &&        	
        	pianificazioneEntity.getMuiCfgPianificazTipoRiga().getCodiceTipo().equalsIgnoreCase("R")) {
        	editableValidoSeRaggruppamento=true;
        }
        cell = DBPromoAgCell.builder().name("Valido Se Raggruppamento")
                .editable(editableValidoSeRaggruppamento && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.NUMERIC.getType())
                .value(pianificazioneEntity.getValidoSeRaggruppamento() == null
                        ? ""
                        : pianificazioneEntity.getValidoSeRaggruppamento().toString())
                .dataTypeParams(DataTypeParams.builder().precision(0).build())
                .build();
        rowData.put("VALIDO_SE_RAGGRUPPAMENTO", cell);

        cell = DBPromoAgCell.builder().name("Allow Zero").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(pianificazioneEntity.getAllowZero())).build();
        rowData.put("ALLOW_ZERO", cell);

        cell = DBPromoAgCell.builder().name("Min Length").editable(editable && !isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.NUMERIC.getType())
                .value(pianificazioneEntity.getMinLength() == null
                        ? ""
                        : pianificazioneEntity.getMinLength().toString())
                .dataTypeParams(DataTypeParams.builder().precision(0).build())
                .build();
        rowData.put("MIN_LENGTH", cell);

        cell = DBPromoAgCell.builder().name("Lookup Attribute").editable(isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(pianificazioneEntity.getLookupAttribute())).build();
        rowData.put("LOOKUP_ATTRIBUTE", cell);
        
        cell = DBPromoAgCell.builder().name("Lookup Value").editable(isEntityLookup && !isFormulaEnabled)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(pianificazioneEntity.getValue())).build();
        rowData.put("VALUE", cell);

        cell = DBPromoAgCell.builder().name("Value Type").editable(isEntityLookup || isFormulaEnabled)
                .type(DBPromoCellTypeEnum.PICKLIST.getType())
                .value(nullAsEmptyString(pianificazioneEntity.getValueType())).build();
        if (isEntityLookup) {
            cell.setPicklistValues(new String[] { "String", "Integer", "Long", "Double" });
        }
        rowData.put("VALUE_TYPE", cell);

        cell = DBPromoAgCell.builder().name("Abilita Formula").editable(true).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(pianificazioneEntity.getFormulaEnabled())).build();
        rowData.put("FORMULA_ENABLED", cell);

        cell = DBPromoAgCell.builder().name("Formula").editable(isFormulaEnabled).type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(pianificazioneEntity.getFormula()))
                .dataTypeParams(DataTypeParams.builder().length(4000).build())
                .build();
        rowData.put("FORMULA", cell);

        cell = DBPromoAgCell.builder().name("columnToBeUpdated").editable(Boolean.FALSE)
                .type(DBPromoCellTypeEnum.STRING.getType()).value("").build();
        rowData.put("columnToBeUpdated", cell);

        cell = DBPromoAgCell.builder().name("valueToBeUpdated").editable(Boolean.FALSE)
                .type(DBPromoCellTypeEnum.STRING.getType()).value("").build();
        rowData.put("valueToBeUpdated", cell);

        cell = DBPromoAgCell.builder().name("columnHeaderToBeUpdated").editable(Boolean.FALSE)
                .type(DBPromoCellTypeEnum.STRING.getType()).value("").build();
        rowData.put("columnHeaderToBeUpdated", cell);


        cell = DBPromoAgCell.builder().name("deleteEnabled").value(String.valueOf(editable)).build();
        rowData.put("deleteEnabled", cell);

        cell = DBPromoAgCell.builder().name("propaga").value(boolFlagAsString(pianificazioneEntity.getPropaga()))
                .type(DBPromoCellTypeEnum.CHECKBOX.getType()).editable(Boolean.TRUE).build();
        rowData.put("propaga", cell);

        cell = DBPromoAgCell.builder().name("invisible").value(boolFlagAsString(pianificazioneEntity.getInvisible()))
                .type(DBPromoCellTypeEnum.CHECKBOX.getType()).editable(Boolean.TRUE).build();
        rowData.put("invisible", cell);

        return JsonUtils.getMapper().valueToTree(rowData);
    }

    /**
     * Creazione singola riga tipo elemento per la griglia 'Tipo Elemento'
     * @param tipoElemento riga tipo elemento
     * @param idHeader id del header corrispondente
     * @param editable true se le celle sono modificabili, false altrimenti
     * @return il nodo JSON relativo alla riga tipo elemento
     */
    private ObjectNode createRowNode(CfgTipoElementoEntity tipoElemento, Long idHeader, boolean editable) {
        final HashedMap<String, DBPromoAgCell> rowData = new HashedMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("id").editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(tipoElemento.getId())).build();
        rowData.put("id", cell);

        cell = DBPromoAgCell.builder().name("idCfgConfHeader").editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(idHeader)).build();
        rowData.put("idCfgConfHeader", cell);

        cell = DBPromoAgCell.builder().name("raggruppamento").editable(editable).nullable(false)
                .type(DBPromoCellTypeEnum.NUMERIC.getType()).value(String.valueOf(tipoElemento.getRaggruppamento()))
                .dataTypeParams(DataTypeParams.builder().precision(0).build())
                .build();
        rowData.put("raggruppamento", cell);

        cell = DBPromoAgCell.builder().name("totale").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(tipoElemento.getTotale())).build();
        rowData.put("totale", cell);

        cell = DBPromoAgCell.builder().name("reparto").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(tipoElemento.getReparto())).build();
        rowData.put("reparto", cell);

        cell = DBPromoAgCell.builder().name("grm").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(tipoElemento.getGrm())).build();
        rowData.put("grm", cell);

        cell = DBPromoAgCell.builder().name("articolo").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(tipoElemento.getArticolo())).build();
        rowData.put("articolo", cell);

        cell = DBPromoAgCell.builder().name("attributo").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(tipoElemento.getAttributo())).build();
        rowData.put("attributo", cell);

        cell = DBPromoAgCell.builder().name("omogeneo").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(tipoElemento.getOmogeneo())).build();
        rowData.put("omogeneo", cell);

        cell = DBPromoAgCell.builder().name("deleteEnabled").value(String.valueOf(editable)).build();
        rowData.put("deleteEnabled", cell);

        return JsonUtils.getMapper().valueToTree(rowData);
    }

    /**
     * Creazione singola riga header per la griglia 'Header'
     * @param header riga header
     * @param idSetPianificazione id del set di pianificazione corrispondente
     * @param levels lista dei possibili livello di pianificazione assegnabili
     * @param editable true se le celle sono modificabili, false altrimenti
     * @return il nodo JSON relativo alla riga header
     */
    private ObjectNode createRowNode(CfgConfHeaderEntity header, Long idSetPianificazione, List<ComboBoxValues> levels,
                                     boolean editable) {
        final HashedMap<String, DBPromoAgCell> rowData = new HashedMap<>();
        final Boolean fullyConfigured = cfgConfHeaderService.isFieldMeccanicaFullyConfigured(header.getId());

        DBPromoAgCell cell = DBPromoAgCell.builder().name("id").editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(header.getId()))
                .mandatory(!fullyConfigured).build();
        rowData.put("id", cell);

        cell = DBPromoAgCell.builder().name("idCfgSetPianificazione").editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(idSetPianificazione))
                .mandatory(!fullyConfigured).build();
        rowData.put("idCfgSetPianificazione", cell);

        cell = DBPromoAgCell.builder().name("meccanica").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(String.format("%s - %s", header.getMeccanicaEntity().getCodiceMeccanica(),
                        header.getMeccanicaEntity().getDescrizione()))
                .nullable(false).mandatory(!fullyConfigured).build();
        rowData.put("meccanica", cell);

        cell = DBPromoAgCell.builder().name("livello_pianificazione").editable(editable)
                .type(DBPromoCellTypeEnum.COMBOBOX.getType()).value(String.valueOf(header.getLivelloPianificazione().getId()))
                .comboBoxValues(levels).mandatory(!fullyConfigured).build();
        rowData.put("livello_pianificazione", cell);

        cell = DBPromoAgCell.builder().name("min_set").editable(editable).type(DBPromoCellTypeEnum.NUMERIC.getType())
                .dataTypeParams(DataTypeParams.builder().precision(0).build())
                .value(nullIntegerAsEmptyString(header.getMinSet())).nullable(true).mandatory(!fullyConfigured).build();
        rowData.put("min_set", cell);

        cell = DBPromoAgCell.builder().name("max_set").editable(editable).type(DBPromoCellTypeEnum.NUMERIC.getType())
                .dataTypeParams(DataTypeParams.builder().precision(0).build())
                .value(nullIntegerAsEmptyString(header.getMaxSet())).nullable(true).mandatory(!fullyConfigured).build();
        rowData.put("max_set", cell);

        cell = DBPromoAgCell.builder().name("min_raggr").editable(editable).type(DBPromoCellTypeEnum.NUMERIC.getType())
                .dataTypeParams(DataTypeParams.builder().precision(0).build())
                .value(nullIntegerAsEmptyString(header.getMinRaggruppamento())).nullable(true)
                .mandatory(!fullyConfigured).build();
        rowData.put("min_raggr", cell);

        cell = DBPromoAgCell.builder().name("max_raggr").editable(editable).type(DBPromoCellTypeEnum.NUMERIC.getType())
                .dataTypeParams(DataTypeParams.builder().precision(0).build())
                .value(nullIntegerAsEmptyString(header.getMaxRaggruppamento())).nullable(true)
                .mandatory(!fullyConfigured).build();
        rowData.put("max_raggr", cell);

        cell = DBPromoAgCell.builder().name("unica_in_promo").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(header.getUnicaInPromo())).mandatory(!fullyConfigured).build();
        rowData.put("unica_in_promo", cell);

        cell = DBPromoAgCell.builder().name("duplica_articolo").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(header.getDuplicaArticolo())).mandatory(!fullyConfigured).build();
        rowData.put("duplica_articolo", cell);

        cell = DBPromoAgCell.builder().name("duplica_reparto").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(header.getDuplicaReparto())).mandatory(!fullyConfigured).build();
        rowData.put("duplica_reparto", cell);

        cell = DBPromoAgCell.builder().name("duplica_grm").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(header.getDuplicaGrm())).mandatory(!fullyConfigured).build();
        rowData.put("duplica_grm", cell);

        cell = DBPromoAgCell.builder().name("duplica_totale").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(header.getDuplicaTotale())).mandatory(!fullyConfigured).build();
        rowData.put("duplica_totale", cell);

        cell = DBPromoAgCell.builder().name("logo_messaggi").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(header.getLogoMessaggi())).mandatory(!fullyConfigured).build();
        rowData.put("logo_messaggi", cell);


        cell = DBPromoAgCell.builder().name("castelletti").editable(editable).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(boolFlagAsString(header.getCastelletti())).mandatory(!fullyConfigured).build();
        rowData.put("castelletti", cell);

        cell = DBPromoAgCell.builder().name("deleteEnabled").value(String.valueOf(editable)).build();
        rowData.put("deleteEnabled", cell);

        return JsonUtils.getMapper().valueToTree(rowData);
    }

    private ObjectNode createRowNode(CfgPianificazioneCampiEntity entity) {
        final HashedMap<String, DBPromoAgCell> rowData = new HashedMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("id").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(String.valueOf(entity.getId())).build();
        rowData.put("id", cell);

        cell = DBPromoAgCell.builder().name("codiceCampo").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(entity.getCodiceCampo())).build();
        rowData.put("codiceCampo", cell);

        cell = DBPromoAgCell.builder().name("descrizione").editable(true).nullable(true)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(entity.getDescrizione())).build();
        rowData.put("descrizione", cell);

        cell = DBPromoAgCell.builder().name("descrizioneEstesa").editable(true).nullable(true)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(entity.getDescrizioneEstesa())).build();
        rowData.put("descrizioneEstesa", cell);

        cell = DBPromoAgCell.builder().name("raggruppamento").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(entity.getRaggruppamento())).build();
        rowData.put("raggruppamento", cell);

        cell = DBPromoAgCell.builder().name("tipo").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(entity.getTipo())).build();
        rowData.put("tipo", cell);

        cell = DBPromoAgCell.builder().name("campo").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(entity.getCampo())).build();
        rowData.put("campo", cell);

        cell = DBPromoAgCell.builder().name("entityLookup").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(entity.getEntityLookup())).build();
        rowData.put("entityLookup", cell);

        cell = DBPromoAgCell.builder().name("entityAttribute").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(nullAsEmptyString(entity.getEntityAttribute())).build();
        rowData.put("entityAttribute", cell);

        cell = DBPromoAgCell.builder().name("columnWidth").editable(true).nullable(true)
                .type(DBPromoCellTypeEnum.NUMERIC.getType())
                .dataTypeParams(DataTypeParams.builder().precision(0).build())
                .value(nullIntegerAsEmptyString(entity.getColumnWidth())).build();
        rowData.put("columnWidth", cell);
        
        cell = DBPromoAgCell.builder().name("valueToBeUpdated").editable(Boolean.FALSE)
                .type(DBPromoCellTypeEnum.STRING.getType()).value("").build();
        rowData.put("valueToBeUpdated", cell);
        
        cell = DBPromoAgCell.builder().name("columnHeaderToBeUpdated").editable(Boolean.FALSE)
                .type(DBPromoCellTypeEnum.STRING.getType()).value("").build();
        rowData.put("columnHeaderToBeUpdated", cell);
        
        cell = DBPromoAgCell.builder().name("columnToBeUpdated").editable(Boolean.FALSE)
                .type(DBPromoCellTypeEnum.STRING.getType()).value("").build();
        rowData.put("columnToBeUpdated", cell);
        
        return JsonUtils.getMapper().valueToTree(rowData);
    }

    private String boolFlagAsString(Integer value) {
        return value == null || value != 1 ? String.valueOf(false) : String.valueOf(true);
    }

    private String boolFlagAsString(Short value) {
        return value == null || value != 1 ? String.valueOf(false) : String.valueOf(true);
    }

    private String boolFlagAsString(String value) {
        return String.valueOf("1".equals(value));
    }

    private String boolFlagAsString(Boolean value) {
        return String.valueOf(value);
    }

    private String nullAsEmptyString(String value) {
        return value == null ? "" : value;
    }

    private String nullIntegerAsEmptyString(Integer value) {
        return value == null ? "" : String.valueOf(value);
    }

    Comparator<CfgConfHeaderEntity> byCodiceMeccanica = (o1, o2) -> {
        try {
            return o1.getMeccanicaEntity().getCodiceMeccanica()
                    .compareTo(o2.getMeccanicaEntity().getCodiceMeccanica());
        } catch (Exception ex) {
            log.error("Error comparing header " + o1.getId() + " with " + o2.getId() + " by CodiceMeccanica", ex);
        }
        return 0;
    };

    Comparator<CfgPianificazioneEntity> byTipoRiga = (o1, o2) -> {
        try {
            return o1.getMuiCfgPianificazTipoRiga().getDescrizione()
                    .compareTo(o2.getMuiCfgPianificazTipoRiga().getDescrizione());
        } catch (Exception ex) {
            log.error("Error comparing pianificazione id " + o1.getId() + " with id " + o2.getId(), ex);
        }
        return 0;
    };

    Comparator<CfgPianificazioneEntity> byCampo = (o1, o2) -> {
        try {
            if (o1.getMuiCfgPianificazioneCampi() != null && o1.getMuiCfgPianificazioneCampi().getCampo() != null
                    && o2.getMuiCfgPianificazioneCampi() != null && o2.getMuiCfgPianificazioneCampi().getCampo() != null) {
                return o1.getMuiCfgPianificazioneCampi().getCampo()
                        .compareTo(o2.getMuiCfgPianificazioneCampi().getCampo());
            }
            return 0;
        } catch (Exception ex) {
            log.error("Error comparing pianificazione id " + o1.getId() + " with id " + o2.getId(), ex);
        }
        return 0;
    };
}
