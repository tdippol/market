package com.axiante.mui.webapp.webservice.util.pianificazione;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.grid.DBPromoColTypeEnum;
import com.axiante.mui.common.promo.grid.DBPromoColumnlDef;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.common.promo.params.WebserviceParams;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.common.utility.PicklistUtils;
import com.axiante.mui.common.utility.StringUtils;
import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.business.utils.ComboBoxFactory;
import com.axiante.mui.dbpromo.business.utils.LookupUtils;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import com.axiante.mui.dbpromo.persistence.service.ClusterClienteService;
import com.axiante.mui.dbpromo.persistence.service.FormaPagamentoService;
import com.axiante.mui.dbpromo.persistence.service.MuiIniziativaService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTestataService;
import com.axiante.mui.dbpromo.persistence.service.TipoRigaService;
import com.axiante.mui.dbpromo.persistence.service.UploadFidatyService;
import com.axiante.mui.webapp.utils.PianificazioneSecurityUtil;
import com.axiante.mui.webapp.utils.SottoclasseUtil;
import com.axiante.mui.webapp.webservice.util.ColumnDefUtils;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import com.axiante.mui.webapp.webservice.util.configuration.CfgPianificazioneEntityHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PromoConfigurationHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.VisualizzaPianificazioneHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PlanningCommons;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Dependent
public class PlanningColumnDef implements DynamicColumnDef {

    @Inject
    private PianificazioneService pianificazioneService;

    @Inject
    private PromoService promoService;

    @Inject
    private PromoConfigurationHelper promoConfigurationHelper;

    @Inject
    private VisualizzaPianificazioneHelper visualizzaPianificazioneHelper;

    @Inject
    private PlanningCommons planningCommons;

    @Inject
    private PianificazionePromoUtil pianificazionePromoUtil;

    @Inject
    UploadFidatyService uploadFidaty;

    @Inject
    TipoRigaService tipoRigaService;

    @Inject
    CfgPianificazioneEntityHelper pianificazioneEntityHelper;

    @Inject
    private Instance<PianificazioneSecurityUtil> securityUtilInstance;

    @Inject
    private Instance<ComboBoxFactory> comboBoxFactoryInstance;

    final PicklistUtils picklistUtils = new PicklistUtils();

    @Inject
    private Instance<SottoclasseUtil> sottoclasseUtilInstance;

    @Inject
    private Instance<FormaPagamentoService> formaPagamentoServiceInstance;

    @Inject
    private Instance<ClusterClienteService> clusterClienteServiceInstance;

    @Inject
    private Instance<MuiIniziativaService> muiIniziativaServiceInstance;

    @Inject
    private PromozioneTestataService promozioneTestataService;

    @Inject
    private LookupUtils lookupUtils;

    /**
     * Al momento il contesto non viene considerato nella creazione del JSON del
     * columnDef, in quanto le colonne sono completamente dinamiche in base ai campi
     * configurati per le meccaniche presenti in testata e non dipendenti da un file
     * JSON hard-coded.
     *
     * @param testata
     * @param hiddenColumns
     * @param grid
     * @param contesto
     * @param contestoRequired
     * @return
     */
    @Override
    public String generateColumnDefByPromoConfiguration(PromozioneTestataEntity testata, String hiddenColumns,
                                                        String grid, String contesto, boolean contestoRequired) {
        String json = "";

        final List<CfgPianificazioneEntity> pianificazioni = promoConfigurationHelper
                .getConfigurazioniFiltrate(testata);

        final List<DBPromoColumnlDef> columnDefList = new ArrayList<>();
        columnDefList.add(DBPromoColumnlDef.builder()
                .field("selected").width(45).columnType(DBPromoColTypeEnum.CHECKBOX.getType())
                .checkboxSelection(true).headerCheckboxSelection(true).headerCheckboxSelectionFilteredOnly(true)
                .pinned("right")
                .build());
        columnDefList.addAll(pianificazioni.stream()
                .sorted(Comparator.comparing(CfgPianificazioneEntity::getOrdinamento,
                        Comparator.nullsLast(Comparator.naturalOrder())))
                .map(pianificazione -> createDynamicColumnDef(testata, pianificazione))
                .collect(Collectors.toList()));

        try {
            json = JsonUtils.getMapper().writeValueAsString(columnDefList);
            json = String.format("{ \"%s\" :  %s }", DbPromoConstants.COLUMN_DEF, json);
            // adesso applico le hidden columns
            final JsonNode node = new ColumnDefUtils().applyHiddenColumns(json, hiddenColumns, grid);
            if (node != null) {
                json = node.toString();
            }
        } catch (final JsonProcessingException e) {
            log.error("error converting column def", e);
        }

        return json;
    }

    @Override
    public String generateRowDataByPromoConfiguration(@NonNull final String promoId, Boolean isUserAdmin,
                                                      List<String> codiciGruppo, String operationMessage) {

        long startTime = System.currentTimeMillis();
        long endTime = startTime;
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";

        try {
            final PromozioneTestataEntity testata = promozioneTestataService.findByIdFullEagerFetch(new Long(promoId));
            if (testata != null) {
                final String statoPromozione = PromoAcl.getCodiceStatoPromozione(testata);
                final boolean editabile = planningCommons.isPlanningEditableCellAndRow(testata);
                final List<PromozionePianificazioneEntity> righeMaster =
                testata.getPromozionePianificazioneEntities().stream()
                        .filter(p -> p.getParent() == null)
                        .sorted(Comparator.comparingLong(PromozionePianificazioneEntity::getId))
                        .collect(Collectors.toList());
                final List<CfgPianificazioneEntity> configurazioni = promoConfigurationHelper
                        .getConfigurazioniSorted(testata);
                // fai il parse delle configurazioni e se ci sono delle lookup allora fai la prefect in una singola query
                // e costruisci la mappa
                lookupUtils.initializeLookups(testata, configurazioni);
                final int uploadEnabled = testata.getCanalePromozioneEntity().getAbilitaUpload();
                final Boolean logoMessaggioEnabled = testata.getCanalePromozioneEntity().getFlLogoMessaggio();
                Stream<PromozionePianificazioneEntity> stream = null;
                if (isUserAdmin != null && codiciGruppo != null) {
                    stream = righeMaster.stream()
                            .sorted(Comparator.comparing(PromozionePianificazioneEntity::getElemento, Comparator.nullsLast(Comparator.naturalOrder())))
                            .filter(p -> isVisible(p, isUserAdmin, codiciGruppo));
                } else {
                    stream = righeMaster.stream()
                            .sorted(Comparator.comparing(PromozionePianificazioneEntity::getElemento, Comparator.nullsLast(Comparator.naturalOrder())));
                }
                stream.forEach(riga -> insertPianificazioneInArrayNode(riga, arrayNode, editabile, uploadEnabled,
                        logoMessaggioEnabled, configurazioni, statoPromozione, null, isUserAdmin, codiciGruppo, lookupUtils));
            }

        } catch (final Exception e) {
            log.error("Error map PromozionePianificazioneEntity into JSON object", e);
            if (operationMessage == null) {
                operationMessage = "";
            }
            operationMessage += "\n Errore durante il recupero del dettaglio della pianificazione";
        } finally {
            final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            if (operationMessage != null) {
                objectNode.put("operationMessage", operationMessage);
            }
            if ( lookupUtils != null ) lookupUtils.releaseResources();
            try {
                json = JsonUtils.getMapper().writeValueAsString(objectNode);
                endTime = System.currentTimeMillis();
                log.info("Computed grid in {} s", (endTime - startTime)/1000);

            } catch (final JsonProcessingException e) {
                log.error("Error processing pianificazione JSON row data", e);
            }
        }

        return json;
    }

    /**
     * Controllo se riga di pianificazione e' visibile - tipoRiga SET ||
     * RAGGRUPPAMENTO --> sempre - tipoRiga ELEMENTO - isUserAdmin --> sempre -
     * controllo tramite security
     *
     * @param pianificazione
     * @param isUserAdmin
     * @param codiciGruppo
     * @return
     */
    private boolean isVisible(PromozionePianificazioneEntity pianificazione, boolean isUserAdmin,
                              List<String> codiciGruppo) {
        try {
            final PianificazioneRowTypeEnum rowType = PianificazioneRowTypeEnum
                    .fromCode(pianificazione.getTipoRiga().getCodiceTipo());
            if (PianificazioneRowTypeEnum.SET.equals(rowType)
                    || PianificazioneRowTypeEnum.RAGGRUPPAMENTO.equals(rowType)) {
                if (pianificazione.getCodiceGruppo() != null && pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getFlSicurezzaSet()) {
                    return isUserAdmin ||
                            codiciGruppo.contains(pianificazione.getCodiceGruppo());
                } else {
                    return true;
                }
            }
            if (PianificazioneRowTypeEnum.ELEMENTO.equals(rowType)) {
                return isUserAdmin || securityUtilInstance.get().isReadable(pianificazione, codiciGruppo);
            }
            log.error(String.format("Cannot set visibility for pianificazione id %d", pianificazione.getId()));
            return false;
        } catch (Exception ex) {
            log.error(String.format("Cannot set visibility for pianificazione id %d", pianificazione.getId()), ex);
            return false;
        }
    }

    /**
     * Controllo se riga di pianificazione e' editabile - tipoRiga SET ||
     * RAGGRUPPAMENTO --> sempre - tipoRiga ELEMENTO - isUserAdmin --> sempre -
     * controllo tramite security
     *
     * @param pianificazione
     * @param isUserAdmin
     * @param codiciGruppo
     * @return
     */
    private boolean isEditable(PromozionePianificazioneEntity pianificazione, boolean isUserAdmin,
                               List<String> codiciGruppo) {
        try {
            final PianificazioneRowTypeEnum rowType = PianificazioneRowTypeEnum
                    .fromCode(pianificazione.getTipoRiga().getCodiceTipo());
            if (PianificazioneRowTypeEnum.SET.equals(rowType)
                    || PianificazioneRowTypeEnum.RAGGRUPPAMENTO.equals(rowType)) {
                return true;
            }
            if (PianificazioneRowTypeEnum.ELEMENTO.equals(rowType)) {
                return isUserAdmin || securityUtilInstance.get().isWriteable(pianificazione, codiciGruppo);
            }
            log.error(String.format("Cannot set visibility for pianificazione id %d", pianificazione.getId()));
            return false;
        } catch (Exception ex) {
            log.error(String.format("Cannot set editability for pianificazione id %d", pianificazione.getId()), ex);
            return false;
        }
    }

    @Override
    public ObjectNode generateRowDataByPromoPianificazioneMaster(PromozionePianificazioneEntity pianificazione) {
        return null;
    }

    @Override
    public ObjectNode generateRowDataByPromoConfiguration(PromozioneTestataEntity testata, Long idMeccanica,
                                                          PlanningArticleMultiFilterParam params, Boolean isUserAdmin, List<String> codiciGruppo) {
        return null;
    }

    @Override
    public String generateRowDataFilteredByPromoConfiguration(String promoId, String radioChecked) {

        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";

        try {
            final PromozioneTestataEntity testata = promoService.findById(new Long(promoId));
            final boolean editabile = planningCommons.isPlanningEditableCellAndRow(testata);

            if (testata != null) {
                final List<PromozionePianificazioneEntity> righeMaster = pianificazioneService
                        .findAllParentPromozionePianificazioneEntityByPromozioneTestata(testata);

                final List<CfgPianificazioneEntity> configurazioni = promoConfigurationHelper
                        .getConfigurazioniSorted(testata);
                final String statoPromozione = PromoAcl.getCodiceStatoPromozione(testata);

                final int uploadEnabled = testata.getCanalePromozioneEntity().getAbilitaUpload();
                final Boolean logoMessaggioEnabled = testata.getCanalePromozioneEntity().getFlLogoMessaggio();
                lookupUtils.initializeLookups(testata, configurazioni);
                righeMaster.stream().sorted(Comparator.comparing(PromozionePianificazioneEntity::getId))
                        .forEach(riga -> insertPianificazioneInArrayNode(riga, arrayNode, editabile, uploadEnabled,
                                logoMessaggioEnabled, configurazioni, statoPromozione, radioChecked, null, null, lookupUtils));
            }

        } catch (final Exception e) {
            log.error("Error map PromozionePianificazioneEntity into JSON object", e);
        } finally {
            final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            if ( lookupUtils != null ) lookupUtils.releaseResources();
            try {
                json = JsonUtils.getMapper().writeValueAsString(objectNode);
            } catch (final JsonProcessingException e) {
                log.error("Error processing pianificazione JSON row data", e);
            }
        }

        return json;

    }

    @Override
    public ObjectNode generateRowDataByPromoElementMechanic(PromozioneTestataEntity testata, Long idMeccanica,
                                                            String buyerId, Boolean isUserAdmin, List<String> codiciGruppo, String operationMessage) {
        return null;
    }

    private void insertPianificazioneInArrayNode(PromozioneTestataEntity testata, final PromozionePianificazioneEntity pianificazione,
                                                 final ArrayNode arrayNode, boolean editabile, int uploadEnabled,
                                                 boolean logoMessaggioEnabled,
                                                 List<CfgPianificazioneEntity> configurazioni, String statoPromozione, String filter,
                                                 Boolean isUserAdmin, List<String> codiciGruppo, LookupUtils lookupUtils) {

        // mappo la riga
        if (testata == null) {
            testata = pianificazione.getPromozioneTestataEntity();
        }

        // controllo se in configurazione ci sono liste condizionali e se si creo la mappa dei servizi
        Map<String, String> webservices = new HashMap<>();
        // questa e' paranoia ...
        if (testata.getMuiCfgSetPianificazione() != null && testata.getMuiCfgSetPianificazione().getMuiCfgConfHeaders() != null && pianificazione.getMeccanicaEntity() != null && pianificazione.getMeccanicaEntity().getCodiceMeccanica() != null) {
            CfgConfHeaderEntity header = testata.getMuiCfgSetPianificazione().getMuiCfgConfHeaders().stream()
                    .filter(h -> h.getMeccanicaEntity() != null && h.getMeccanicaEntity().getCodiceMeccanica() != null)
                    .filter(h -> h.getMeccanicaEntity().getCodiceMeccanica().equals(pianificazione.getMeccanicaEntity().getCodiceMeccanica()))
                    .findFirst().orElse(null);
            if (header != null) {
                List<CfgPianificazioneEntity> configurazioniConChiave = null;
                if (header.getMuiCfgPianificaziones() != null) {
                    configurazioniConChiave = pianificazionePromoUtil.getConfigurazioniWithChiave(
                            new ArrayList<>(header.getMuiCfgPianificaziones())
                    );
                }
                if (configurazioniConChiave != null && configurazioniConChiave.size() > 0) {
                    //devo creare una mappa con
                    // chiave: configurazione.getMuiCfgPianificazioneCampi().getCampo()
                    // valore: webservice aggiornato
                    configurazioniConChiave.forEach(c -> {
                        webservices.put(c.getMuiCfgPianificazioneCampi().getCampo(),
                                setServiceValues(c.getTipoLista(), pianificazione.getPromozioneTestataEntity().getId(), pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getId(),
                                        pianificazione.getMeccanicaEntity().getId(), pianificazione.getId(), String.valueOf(visualizzaPianificazioneHelper.invokeGetterEntity(c.getChiave(), pianificazione))));
                    });
                }
            }
        }
        arrayNode.add(mapPianificazioneToObjectNode(
                pianificazione.getPromozioneTestataEntity(),
                pianificazione,
                editabile,
                uploadEnabled,
                logoMessaggioEnabled,
                configurazioni,
                statoPromozione,
                isUserAdmin,
                codiciGruppo,
                webservices,
                pianificazionePromoUtil.isPianificazioneLockedOnDataInizio(pianificazione),
                pianificazionePromoUtil.isPianificazioneLockedOnDataFine(pianificazione),
                pianificazionePromoUtil.isPianificazioneDeleteEnabledAfterDataFine(pianificazione)
        ));
        final  CfgConfHeaderEntity conf = configurazioni.stream().map(CfgPianificazioneEntity::getMuiCfgConfHeader).filter(c->c.getMeccanicaEntity().getCodiceMeccanica().equals(pianificazione.getMeccanicaEntity().getCodiceMeccanica())).findFirst().orElse(null);
        if ( conf == null || conf.getLivelloPianificazione() == null || "ELEMENTO".equals(conf.getLivelloPianificazione().getCodice()) ){
            log.debug("saved a lookup !");
        } else {
            if ((pianificazione.getChildren() != null) && (pianificazione.getChildren().size() > 0)) {
                Comparator<PromozionePianificazioneEntity> orderBy = PianificazioneRowTypeEnum.SET.getTypeCode()
                        .equals(pianificazione.getTipoRiga().getCodiceTipo())
                        ? byNumRaggr
                        : Comparator.comparing(PromozionePianificazioneEntity::getElemento, Comparator.nullsLast(Comparator.naturalOrder()));
                Stream<PromozionePianificazioneEntity> stream = null;
                if (isUserAdmin != null && codiciGruppo != null) {
                    stream = pianificazione.getChildren().stream().sorted(orderBy)
                            .filter(p -> isVisible(p, isUserAdmin, codiciGruppo));
                } else {
                    stream = pianificazione.getChildren().stream().sorted(orderBy);
                }
                stream.forEach(p -> insertPianificazioneInArrayNode(p, arrayNode, editabile, uploadEnabled, logoMessaggioEnabled,
                        configurazioni, statoPromozione, filter, isUserAdmin, codiciGruppo, lookupUtils));
            }
        }
    }

    private void insertPianificazioneInArrayNode(final PromozionePianificazioneEntity pianificazione,
                                                 ArrayNode arrayNode, boolean editabile, int uploadEnabled,
                                                 boolean logoMessaggioEnabled, List<CfgPianificazioneEntity> configurazioni,
                                                 String statoPromozione, String filter, Boolean isUserAdmin, List<String> codiciGruppo, LookupUtils lookupUtils) {
        insertPianificazioneInArrayNode(null, pianificazione, arrayNode, editabile, uploadEnabled,
                logoMessaggioEnabled, configurazioni, statoPromozione, filter, isUserAdmin == null ? false : isUserAdmin,
                codiciGruppo, lookupUtils);
    }

    public String setServiceValues(final String service, Long idPromozione, Long idCanale, Long idMeccanica,
                                   Long idPianificazione, String chiave) {

        String result = service;
        boolean correct = true;
        try {
            if (idPromozione != null) {
                result = result.replaceAll("\\$\\{promozione}", idPromozione.toString());
            }
            if (idCanale != null) {
                result = result.replaceAll("\\$\\{canale}", idCanale.toString());
            }
            if (idMeccanica != null) {
                result = result.replaceAll("\\$\\{meccanica}", idMeccanica.toString());
            }
            if (idPianificazione != null) {
                result = result.replaceAll("\\$\\{pianificazione}", idPianificazione.toString());
            }
            if (chiave != null && !("null".equals(chiave))) {
                result = result.replaceAll("\\$\\{chiaveCondizionale}", chiave);
            }
        } catch (Exception e) {
            log.error("Errore nel settaggio dei valori per il servizio: " + service, e);
        } finally {
            correct = !(result.contains("${promozione}") || result.contains("${canale}") || result.contains("${meccanica}")
                    || result.contains("${pianificazione}") || result.contains("${chiaveCondizionale}"));
            if (!correct) {
                log.error(String.format("La configurazione della pianificazione con id %d differisce dai valori registrati nel database. Controllare la configurazione.", idPianificazione));
            }
        }
        return result;
    }

    public ObjectNode mapPianificazioneToObjectNode(@NonNull final PromozioneTestataEntity testata,
                                                    @NonNull final PromozionePianificazioneEntity pianificazione,
                                                    boolean rigaEditabile, int uploadEnabled, boolean logoMessaggioEnabled,
                                                    List<CfgPianificazioneEntity> configurazioni, String statoPromozione,
                                                    Boolean isUserAdmin, List<String> codiciGruppo,
                                                    @NonNull Map<String, String> webservices, Boolean rowLockedOnDataInizio,
                                                    Boolean rowLockedOnDataFine) {
        return mapPianificazioneToObjectNode(testata, pianificazione, rigaEditabile, uploadEnabled, logoMessaggioEnabled,
                configurazioni, statoPromozione, isUserAdmin, codiciGruppo, webservices, rowLockedOnDataInizio,
                rowLockedOnDataFine, null);
    }

    public ObjectNode mapPianificazioneToObjectNode(@NonNull final PromozioneTestataEntity testata,
                                                    @NonNull final PromozionePianificazioneEntity pianificazione,
                                                    boolean rigaEditabile, int uploadEnabled, boolean logoMessaggioEnabled,
                                                    List<CfgPianificazioneEntity> configurazioni, String statoPromozione,
                                                    Boolean isUserAdmin, List<String> codiciGruppo,
                                                    @NonNull Map<String, String> webservices,
                                                    Boolean rowLockedOnDataInizio, Boolean rowLockedOnDataFine,
                                                    Boolean deleteEnabled) {
        final Long idRowMeccanica = pianificazione.getMeccanicaEntity().getId();
        CfgPianificazTipoRigaEntity temp = pianificazione.getTipoRiga();
        if (temp == null) {
            // FIXME: Il tipo riga 'MASTER' non è più utilizzato!
            log.warn(String.format(
                    "la pianificazione con id %d non ha il tipo riga corretto %s; utilizzo il default MASTER",
                    pianificazione.getId(), pianificazione.getTipoRiga()));
            temp = tipoRigaService.findByDescription("MASTER");
        }
        final HashMap<String, DBPromoAgCell> map = new HashMap<>();

        final AtomicInteger values = new AtomicInteger(0);

        final List<CfgPianificazioneEntity> cfgList = configurazioni.stream()
                .filter(configurazione -> configurazione.getMuiCfgConfHeader().getMeccanicaEntity().getId()
                        .equals(idRowMeccanica))
                // TODO: modificare entity per mappare la relazione con la tabella dei tipi riga
                .filter(c -> c.getMuiCfgPianificazTipoRiga().getId().equals(pianificazione.getTipoRiga().getId()))
                .filter(Objects::nonNull).collect(Collectors.toList());

// Scrivibile se non e' sicurizzata (isUserAdmin OR codiciGruppo <null>), se
// user e' <admin>,
// se riga e' <set> OR <raggruppamento>, se soddisfa la sicurezza
        final boolean isWritable = isUserAdmin == null || codiciGruppo == null
                || isEditable(pianificazione, isUserAdmin, codiciGruppo);
// Cancellabile se non e' securizzata (isUserAdmin OR codiciGruppo <null>), se
// user e' <admin>,
// se soddisfa la sicurezza
        final boolean isDeletable = isUserAdmin == null || codiciGruppo == null || isUserAdmin
                || securityUtilInstance.get().isDeletable(pianificazione, codiciGruppo);

        lookupUtils.initializeLookups(testata, cfgList);

        cfgList.stream().filter(c -> !pianificazioneEntityHelper.isEntityLookup(c)).forEach(configurazione -> {
            values.incrementAndGet();
            log.debug(String.format("Recupero valori per campo %s meccanica %d id pianificazione %d",
                    configurazione.getMuiCfgPianificazioneCampi().getDescrizione(), idRowMeccanica,
                    pianificazione.getId()));
            DBPromoAgCell cell;

            String cellType = null;
            if (PianificazioneConstants.CAMPO_PIANIFICAZIONE_MECCANICA
                    .equals(configurazione.getMuiCfgPianificazioneCampi().getCampo())) {
                String meccanica = "";
                final MeccanicheEntity meccanicaEntity = pianificazione.getMeccanicaEntity();
                if (meccanicaEntity != null) {
                    cellType = planningCommons.defineCellType(configurazione.getTipoLista(),
                            picklistUtils.defineListaCellEditor(configurazione.getLista()),
                            configurazione.getMuiCfgPianificazioneCampi().getCampo(),
                            configurazione.getMuiCfgPianificazioneCampi().getTipo(),
                            configurazione.getRenderAsCombo());
                    if ("combobox".equalsIgnoreCase(cellType)) {
                        meccanica = String.valueOf(meccanicaEntity.getId());
                    } else {
                        meccanica = String.format("%s - %s", meccanicaEntity.getCodiceMeccanica(),
                                meccanicaEntity.getDescrizione());
                    }
                } else {
                    log.warn(String.format("MeccanicaEntity null for PromozionePianificazioneEntity with id %s",
                            pianificazione.getId()));
                }
                cell = mapDataToDBPromoAgCell(testata, configurazione, meccanica,
                        pianificazione.getTipoRiga().getDescrizione(), map, rigaEditabile, isWritable);
                if ("combobox".equalsIgnoreCase(cellType)) {
                    cell.setComboBoxValues(Collections.singletonList(comboBoxFactoryInstance.get().from(meccanicaEntity)));
                }
            } else {
                cell = mapDataToDBPromoAgCell(testata, configurazione,
                        visualizzaPianificazioneHelper.invokeGetterEntity(
                                configurazione.getMuiCfgPianificazioneCampi().getCampo(), pianificazione),
                        pianificazione.getTipoRiga().getDescrizione(), map, rigaEditabile, isWritable);
                if (planningCommons.overrideConfiguration(configurazione, pianificazione)) {
                    cell.setEditable(false);
                }
                if (PianificazioneConstants.CAMPO_CLUSTER_CLIENTE.equals(configurazione.getMuiCfgPianificazioneCampi().getCampo())) {
                    handleFieldClusterCliente(pianificazione, configurazione, cell);
                }
                if (PianificazioneConstants.CAMPO_CODICE_INIZIATIVA.equals(configurazione.getMuiCfgPianificazioneCampi().getCampo())) {
                    handleFieldCodiceIniziativa(pianificazione, configurazione, cell);
                }
            }
            if (pianificazioneEntityHelper.isFormulaEnabled(configurazione)) {
                cell.getDataTypeParams().setFormula(true);
                cell.setFormula(configurazione.getFormula());
            }

            cell.setDefValue(configurazione.getDefValue());

            if (cell.getPicklistService() != null) {
                // set values
                if (webservices.containsKey(configurazione.getMuiCfgPianificazioneCampi().getCampo())) {
                    cell.getPicklistService().setUrl(webservices.get(configurazione.getMuiCfgPianificazioneCampi().getCampo()));
                } else {
                    cell.getPicklistService().setUrl(setServiceValues(cell.getPicklistService().getUrl(), testata.getId(),
                            testata.getCanalePromozioneEntity().getId(), testata.getId(), pianificazione.getId(), null));
                }
            } else if (cell.getComboBoxService() != null) {
                if (webservices.containsKey(configurazione.getMuiCfgPianificazioneCampi().getCampo())) {
                    cell.getComboBoxService().setUrl(webservices.get(configurazione.getMuiCfgPianificazioneCampi().getCampo()));
                } else {
                    cell.getComboBoxService().setUrl(setServiceValues(cell.getComboBoxService().getUrl(), testata.getId(),
                            testata.getCanalePromozioneEntity().getId(), testata.getId(), pianificazione.getId(), null));
                }
            }
            log.debug(String.format("ho note ? %b", map.get("NOTE") != null));
            if ((configurazione.getMuiCfgPianificazioneCampi().getCampo() != null)
                    && configurazione.getMuiCfgPianificazioneCampi().getCampo().contentEquals("NOTE")) {
                log.debug("inserisco nota");
            }
            if (cell.getDataTypeParams() == null) {
                cell.setDataTypeParams(DataTypeParams.builder().build());
            }
            cell.getDataTypeParams()
                    .setAllowZero((configurazione.getAllowZero() != null) && configurazione.getAllowZero().equals(1));
            cell.getDataTypeParams().setLength(configurazione.getLength());
            cell.getDataTypeParams().setMinLength(configurazione.getMinLength());
            //gestione campo invisible
            if (configurazione.getInvisible()) {
                // la cella non e' editabile
                cell.setInvisible(Boolean.TRUE);
                cell.setEditable(Boolean.FALSE);
            }
            //4764
            // se la configurazione del canale dice che bisogna controllare ... e non siamo admin
            if (!isUserAdmin) {
                if (rowLockedOnDataFine) {
                    // blocca tutto
                    cell.setEditable(Boolean.FALSE);
                } else {
                    if (
                            !(
                                    configurazione.getMuiCfgPianificazioneCampi().getCampo().equalsIgnoreCase("DATA_FINE") ||
                                            configurazione.getMuiCfgPianificazioneCampi().getCampo().equalsIgnoreCase("VALORE")
                            )
                                    && rowLockedOnDataInizio
                    ) {
                        // blocca tutto tranne data fine
                        cell.setEditable(Boolean.FALSE);
                    }
                }
            }
            map.put(configurazione.getMuiCfgPianificazioneCampi().getCampo(), cell);
        });

        cfgList.stream().filter(cfg -> pianificazioneEntityHelper.isEntityLookup(cfg))
                .sorted(Comparator.comparing(CfgPianificazioneEntity::getOrdinamento))
                .forEach(cfg -> {
                    log.debug(String.format(
                            "Recupero valore per campo '%s' tramite EntityLookup; "
                                    + "[EntityLookup='%s'; EntityAttribute='%s'; LookupAttribute='%s'; LookupValue='%s']",
                            cfg.getMuiCfgPianificazioneCampi().getCampo(), cfg.getMuiCfgPianificazioneCampi().getEntityLookup(),
                            cfg.getMuiCfgPianificazioneCampi().getEntityAttribute(), cfg.getLookupAttribute(), cfg.getValue()));
                    Object value = lookupUtils.lookupValue(cfg, pianificazione);
                    DBPromoAgCell cell = mapDataToDBPromoAgCell(testata, cfg, value,
                            pianificazione.getTipoRiga().getDescrizione(), map, rigaEditabile, false);
                    map.put(cfg.getMuiCfgPianificazioneCampi().getCampo(), cell);
                });
        map.put("idPromoPianificazione",
                DBPromoAgCell.builder().name("idPromoPianificazione").type(DBPromoCellTypeEnum.NUMERIC.getType())
                        .value("" + pianificazione.getId()).editable(Boolean.FALSE).build());
        map.put("tipoElemento", DBPromoAgCell.builder().name("tipoElemento").value(pianificazione.getTipoElemento())
                .editable(Boolean.FALSE).build());
        map.put("descrizioneMeccanica", DBPromoAgCell.builder().name("descrizioneMeccanica")
                .value(pianificazione.getMeccanicaEntity().getDescrizione()).editable(Boolean.FALSE).build());
        map.put("idMeccanica", DBPromoAgCell.builder().type(DBPromoCellTypeEnum.NUMERIC.getType())
                .name("idPromoPianificazione").value("" + idRowMeccanica).editable(Boolean.FALSE).build());
        map.put("planningEditable", DBPromoAgCell.builder().name("idPromoPianificazione").value("" + rigaEditabile)
                .editable(Boolean.FALSE).build());
        map.put("numRaggruppamento", DBPromoAgCell.builder().name("numRaggruppamento")
                .value(pianificazione.getNumRaggruppamento()).editable(Boolean.FALSE).build());
        final CfgConfHeaderEntity configHeader = promoConfigurationHelper.getHeaderFromTestataAndMeccanica(
                pianificazione.getPromozioneTestataEntity(), pianificazione.getMeccanicaEntity());
        if (configHeader.getMinRaggruppamento() != null) {
            map.put("minRaggruppamento",
                    DBPromoAgCell.builder().type(DBPromoCellTypeEnum.NUMERIC.getType()).name("minRaggruppamento")
                            .value(configHeader.getMinRaggruppamento().toString()).editable(Boolean.FALSE).build());
        } else {
            map.put("minRaggruppamento", DBPromoAgCell.builder().type(DBPromoCellTypeEnum.NUMERIC.getType())
                    .name("minRaggruppamento").value("0").editable(Boolean.FALSE).build());
        }

        if ((pianificazione.getParent() != null) && (pianificazione.getParent().getParent() != null)
                && (pianificazione.getParent().getParent().getChildren() != null)) {
            map.put("fratelliDelMaster",
                    DBPromoAgCell.builder().type(DBPromoCellTypeEnum.NUMERIC.getType()).name("fratelliDelMaster")
                            .value("" + pianificazione.getParent().getParent().getChildren().size())
                            .editable(Boolean.FALSE).build());
        } else {
            map.put("fratelliDelMaster", DBPromoAgCell.builder().type(DBPromoCellTypeEnum.NUMERIC.getType())
                    .name("fratelliDelMaster").value("0").editable(Boolean.FALSE).build());
        }

        map.put("columnToBeUpdated",
                DBPromoAgCell.builder().name("columnToBeUpdated").value("").editable(Boolean.FALSE).build());
        map.put("valueToBeUpdated",
                DBPromoAgCell.builder().name("valueToBeUpdated").value("").editable(Boolean.FALSE).build());
        map.put("columnHeaderToBeUpdated",
                DBPromoAgCell.builder().name("columnHeaderToBeUpdated").value("").editable(Boolean.FALSE).build());

        // #2908
        map.put("tipoRiga", DBPromoAgCell.builder().name("tipoRiga")
                .value(pianificazione.getTipoRiga().getDescrizione()).editable(Boolean.FALSE).build());
        // 2909
        map.put("hierarchyTree", DBPromoAgCell.builder().name("hierarchyTree").value(null).editable(Boolean.FALSE)
                .hierarchy(getHierchyTree(pianificazione)).build());

        map.put("isTotale", DBPromoAgCell.builder().name("isTotale").value(Boolean.FALSE.toString())
                .editable(Boolean.FALSE).build());
        if ((pianificazione.getParent() != null) && (pianificazione.getParent().getChildren() != null)) {
            map.put("masterDetails",
                    DBPromoAgCell.builder().name("masterDetails")
                            .value("" + pianificazione.getParent().getChildren().size())
                            .type(DBPromoCellTypeEnum.NUMERIC.getType()).editable(Boolean.FALSE).build());
        } else {
            map.put("masterDetails", DBPromoAgCell.builder().name("masterDetails").value("0")
                    .type(DBPromoCellTypeEnum.NUMERIC.getType()).editable(Boolean.FALSE).build());
        }
        if (pianificazione.getChildren() != null) {
            map.put("master", DBPromoAgCell.builder().name("master").value("true")
                    .type(DBPromoCellTypeEnum.BOOLEAN.getType()).editable(Boolean.FALSE).build());
        } else {
            map.put("master", DBPromoAgCell.builder().name("master").value("false")
                    .type(DBPromoCellTypeEnum.BOOLEAN.getType()).editable(Boolean.FALSE).build());
        }
        map.put("nomeElemento", DBPromoAgCell.builder().name("nomeElemento").value(pianificazione.getElemento())
                .editable(Boolean.FALSE).build());
        // #4759: disattivazione bottoni.
        //se l'utente non e' admin e c'e' un blocco sulla data inizio o fine allora nessun bottone e' abilitato
        boolean attivaBottoni = rigaEditabile;
        if (!isUserAdmin) {
            if (rowLockedOnDataInizio || rowLockedOnDataFine) {
                // #4983: Possibilità di re-inserimento articolo in pianificazione x giorni dopo la data fine
                if (deleteEnabled != null && deleteEnabled) {
                    attivaBottoni = true;
                } else {
                    attivaBottoni = false;
                }
            }
        }
        // #3803: implementazione sicurezza sulla pianificazione
        map.put("addEnabled", DBPromoAgCell.builder().name("addEnabled")
                .value("" + addEnabled(pianificazione, attivaBottoni, temp)).editable(Boolean.FALSE).build());
        map.put("deleteEnabled",
                DBPromoAgCell.builder().name("deleteEnabled")
                        .value(String.valueOf(isDeletable && deleteEnabled(pianificazione, attivaBottoni, temp)))
                        .editable(Boolean.FALSE).build());
        map.put("emptyAllEnabled",
                DBPromoAgCell.builder().name("emptyAllEnabled")
                        .value("" + emptyAllEnabled(pianificazione, attivaBottoni, temp, isUserAdmin, codiciGruppo))
                        .editable(Boolean.FALSE).build());
        if (attivaBottoni) {
            if ((pianificazione.getMeccanicaEntity() != null)
                    && (pianificazione.getMeccanicaEntity().getCodiceMeccanica() != null)) {
                switch (pianificazione.getMeccanicaEntity().getCodiceMeccanica()) {
                    case "M018":
                        map.put("barcodeEnabled",
                                DBPromoAgCell.builder().name("barcodeEnabled").value("true").editable(Boolean.FALSE).build());
                        break;
                    case "M933":
                    case "M932":
// Barcode enabled only for riga 'SET'
                        final boolean barcodeEnabled = PianificazioneRowTypeEnum.SET.getDescription()
                                .equals(pianificazione.getTipoRiga().getDescrizione());
                        map.put("barcodeEnabled", DBPromoAgCell.builder().name("barcodeEnabled")
                                .value(String.valueOf(barcodeEnabled)).editable(Boolean.FALSE).build());
                        break;
                    default:
                        map.put("barcodeEnabled",
                                DBPromoAgCell.builder().name("barcodeEnabled").value("false").editable(Boolean.FALSE).build());
                }
            }
        }

        if (attivaBottoni) {
            final List<UploadFidayEntity> fileList = uploadFidaty.findByPianificazione(pianificazione.getId());
            final int numCaricati = fileList != null ? fileList.size() : 0;
            map.put("uploadEnabled",
                    DBPromoAgCell.builder().name("uploadEnabled")
                            .value("" + uploadEnabled(numCaricati, uploadEnabled, pianificazione, rigaEditabile, temp))
                            .build());
            //#5325: abilitazione upload logo messaggio indipendentemente dallo stato della pianificazione
//            map.put("uploadLogoMessaggioEnabled", DBPromoAgCell.builder().name("uploadLogoMessaggioEnabled")
//                    .value(String.valueOf(logoMessaggioEnabled(logoMessaggioEnabled, pianificazione))).build());
        }
        //#5325: abilitazione upload logo messaggio indipendentemente dallo stato della pianificazione
        // ma solo dalla configurazione
        map.put("uploadLogoMessaggioEnabled", DBPromoAgCell.builder().name("uploadLogoMessaggioEnabled")
                .value(String.valueOf(logoMessaggioEnabled(logoMessaggioEnabled, pianificazione))).build());

        if (PianificazioneConstants.PRECARICATI_SU_CARTA
                .equals(pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getDescrizione())
                && rigaEditabile) {

            updateRowDataByNumeroRaggruppamento(map);
        }

        if (pianificazione.getChecks() != null && !pianificazione.getChecks().isEmpty()) {
            if (pianificazione.getChecks() != null && pianificazione.getChecks().stream().anyMatch(c -> "Errore".equalsIgnoreCase(c.getSeverita()))) {
                map.values().forEach(c -> c.setTxtMandatory(true));
            } else {
                map.values().forEach(c -> c.setTxtWarning(true));
            }
        }

        if (webservices != null && webservices.size() > 0) {
            Iterator<Entry<String, DBPromoAgCell>> i = map.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry<String, DBPromoAgCell> entry = i.next();
                if (webservices.get(entry.getKey()) != null) {
                    entry.getValue().setPicklistService(WebserviceParams.builder().url(preparePickListService(webservices.get(entry.getKey()))).build());
                }
            }
        }
        // adesso controllo se ho una lista condizionale
        return JsonUtils.getMapper().valueToTree(map);
    }

    private void handleFieldClusterCliente(PromozionePianificazioneEntity pianificazione, CfgPianificazioneEntity configurazione,
                                           DBPromoAgCell cell) {
        String cellType = planningCommons.defineCellType(configurazione.getTipoLista(),
                picklistUtils.defineListaCellEditor(configurazione.getLista()),
                configurazione.getMuiCfgPianificazioneCampi().getCampo(),
                configurazione.getMuiCfgPianificazioneCampi().getTipo(),
                configurazione.getRenderAsCombo());
        if ("combobox".equalsIgnoreCase(cellType)) {
            // piantato dentro a mano: scoppia in faccia alla prima modifica
            // ma non mi frega un cazzo, ve l'ho detto in tutte le lingue
            // che non avete idea di come si fa una analisi di impatto
            cell.setComboBoxValues(
                    comboBoxFactoryInstance.get().from(
                            clusterClienteServiceInstance.get().findByDataInizioAndDataFine(pianificazione.getDataInizio(), pianificazione.getDataFine())
                    )
            );
        }
        // MG: 5616 campo non editabile per pianificazioni su meccaniche cashback multitransazione
        if (pianificazionePromoUtil.isClusterClienteDisabled(pianificazione)) {
            cell.setEditable(false);
        }
    }

    private void handleFieldCodiceIniziativa(PromozionePianificazioneEntity pianificazione, CfgPianificazioneEntity configurazione,
                                             DBPromoAgCell cell) {
        String cellType = planningCommons.defineCellType(configurazione.getTipoLista(),
                picklistUtils.defineListaCellEditor(configurazione.getLista()),
                configurazione.getMuiCfgPianificazioneCampi().getCampo(),
                configurazione.getMuiCfgPianificazioneCampi().getTipo(),
                configurazione.getRenderAsCombo());
        if ("combobox".equalsIgnoreCase(cellType)) {
            Date dataInizio = pianificazione.getDataInizio() != null
                    ? pianificazione.getDataInizio()
                    : pianificazione.getPromozioneTestataEntity().getDataInizio();
            Date dataFine = pianificazione.getDataFine() != null
                    ? pianificazione.getDataFine()
                    : pianificazione.getPromozioneTestataEntity().getDataFine();
            cell.setComboBoxValues(comboBoxFactoryInstance.get().from(
                    muiIniziativaServiceInstance.get().findAllByDataInizioAndDataFine(dataInizio, dataFine)));
        }
    }

    private Object getLookupValue(String value, String valueType) {
        try {
            switch (valueType.toUpperCase()) {
                case "STRING":
                    // as-is
                    return value;
                case "INTEGER":
                    return Integer.parseInt(value);
                case "LONG":
                    return Long.parseLong(value);
                case "DOUBLE":
                    return Double.parseDouble(value);
                default:
                    log.error(String.format("Value type '%s' is not valid; fallback to 'String'", valueType));
                    return value;
            }
        } catch (Exception ex) {
            log.error(String.format("Error converting value '%s' to type '%s'; fallback to 'String'", value, valueType),
                    ex);
            return value;
        }
    }

    boolean canUploadFiles(int uploaded, int uploadEnabled) {
        return (uploadEnabled > 0) && (uploaded < uploadEnabled);
    }

    boolean uploadEnabled(int uploaded, int uploadEnabled, final PromozionePianificazioneEntity pianificazione,
                          final Boolean rigaEditabile, final CfgPianificazTipoRigaEntity tipoRiga) {
        // se :
        // 1. la riga e' editabile
        // 2. l'upload per il canale e' attivo
        // 3. ci sono meno files del numero massimo configurato
        // 4. la riga e' una riga al livello di pianificazione
        // configurato in header per la meccanica corrente
        // allora puo' avere l'upload
        final boolean meccanicaConfigured = pianificazione.getPromozioneTestataEntity().getMuiCfgSetPianificazione()
                .getMuiCfgConfHeaders().stream()
                .filter(h -> h.getMeccanicaEntity().getId()
                        .equals(pianificazione.getMeccanicaEntity().getId()))
                .anyMatch(h -> h.getLivelloPianificazione().getDescrizione()
                        .equalsIgnoreCase(pianificazione.getTipoRiga().getDescrizione()));
        return rigaEditabile && canUploadFiles(uploaded, uploadEnabled) && meccanicaConfigured;
    }

    private boolean logoMessaggioEnabled(final boolean logoMessaggioEnabled, final PromozionePianificazioneEntity pianificazione) {
        //#5318
        return logoMessaggioEnabled && isLogoMessaggiEnabled(pianificazione);
    }

    private boolean isLogoMessaggiEnabled(final PromozionePianificazioneEntity pianificazione) {
        /*
         data la pianificazione:
         1. prendi il set di configurazione dalla testata
         2. prendi la configurazione per la meccanica con il set di testata
         3. ritorna il valore di logo_messaggi
         */
        Long idSetPianificazione = pianificazione.getPromozioneTestataEntity().getMuiCfgSetPianificazione().getId();
        Optional<CfgConfHeaderEntity> config =
                pianificazione.getMeccanicaEntity().getMuiCfgConfHeaders().stream().filter(c -> c.getMuiCfgSetPianificazione().getId().equals(idSetPianificazione)).findAny();
        boolean enabled = false;
        boolean canEnable = (config.isPresent() && config.get().getLogoMessaggi());

        if (canEnable) {
            // se e' una meccanica a livello set o raggruppamento
            boolean checkParent = isMeccanicaOnLevel(pianificazione.getMeccanicaEntity(), pianificazione.getPromozioneTestataEntity().getMuiCfgSetPianificazione().getId(), PlanningLevelEnum.SET) ||
                    isMeccanicaOnLevel(pianificazione.getMeccanicaEntity(), pianificazione.getPromozioneTestataEntity().getMuiCfgSetPianificazione().getId(), PlanningLevelEnum.RAGGRUPPAMENTO);
            if (checkParent) {
                // abilito solo se non ha parent.
                enabled = pianificazione.getParent() == null;
            } else {
                // altrimenti e' una meccanica ad elemento o totale e quindi abilito
                enabled = true;
            }

        }
        return enabled;
    }

    private boolean isMeccanicaOnLevel(MeccanicheEntity entity, Long idSetPianificazione, PlanningLevelEnum level) {
        return entity.getMuiCfgConfHeaders().stream()
                .anyMatch(h -> level.name().equals(h.getLivelloPianificazione().getCodice())
                        && idSetPianificazione.equals(h.getMuiCfgSetPianificazione().getId()));
    }

    boolean deleteEnabled(final PromozionePianificazioneEntity pianificazione, final Boolean rigaEditabile,
                          final CfgPianificazTipoRigaEntity tipoRiga) {
        if (rigaEditabile) {
            final PlanningLevelEnum level = PlanningLevelEnum.valueOf(tipoRiga.getDescrizione().toUpperCase());
            switch (level) {
                case SET:
                    return true;
                case RAGGRUPPAMENTO:
                    if (pianificazione.getParent() != null) {

                        final CfgConfHeaderEntity header = pianificazione.getPromozioneTestataEntity()
                                .getMuiCfgSetPianificazione().getMuiCfgConfHeaders().stream()
                                .filter(h -> h.getLivelloPianificazione().getDescrizione()
                                        .equalsIgnoreCase(PlanningLevelEnum.SET.getDescription()))
                                .filter(h -> h.getMeccanicaEntity().getId()
                                        .equals(pianificazione.getParent().getMeccanicaEntity().getId()))
                                .findFirst().orElse(null);
                        if (header == null) {
                            log.error("Impossibile recuperare l'header per la pianificazione id "
                                    + pianificazione.getParent().getId() + " con livello "
                                    + PlanningLevelEnum.SET.getDescription());
                            return false;
                        }

                        if (header.getMinRaggruppamento() != null) {
                            return pianificazione.getParent().getChildren().stream()
                                    .filter(h -> h.getTipoRiga().getCodiceTipo()
                                            .equalsIgnoreCase(PlanningLevelEnum.RAGGRUPPAMENTO.getCode()))
                                    .count() > header.getMinRaggruppamento();
                        }
                        // non ci sono numero minimo di raggruppamento: sempre cancellabile
                        return true;
                    }
                    // non ha padre
                    return true;
                case ELEMENTO:
                    return true;
                default:
                    break;
            }
        }
        return false;
    }

    boolean emptyAllEnabled(final PromozionePianificazioneEntity pianificazione, final Boolean rigaEditabile,
                            final CfgPianificazTipoRigaEntity tipoRiga, Boolean isUserAdmin, List<String> codiciGruppo) {
        if (rigaEditabile) {
            final PlanningLevelEnum level = PlanningLevelEnum.valueOf(tipoRiga.getDescrizione().toUpperCase());
            switch (level) {
                case SET:
                case ELEMENTO:
                    return false;
                case RAGGRUPPAMENTO:
                    // #3803: implementazione sicurezza sulla pianificazione
                    return isUserAdmin != null && codiciGruppo != null
                            ? pianificazione.getChildren().size() > 0 && pianificazione.getChildren().stream()
                            .anyMatch(p -> isEditable(p, isUserAdmin, codiciGruppo))
                            : pianificazione.getChildren().size() > 0;
                default:
                    break;
            }
        }
        return false;
    }

    boolean addEnabled(final PromozionePianificazioneEntity pianificazione, final Boolean rigaEditabile,
                       final CfgPianificazTipoRigaEntity tipoRiga) {
        if (rigaEditabile) {
            final PlanningLevelEnum level = PlanningLevelEnum.valueOf(tipoRiga.getDescrizione().toUpperCase());
            switch (level) {
                case SET:
                    final CfgConfHeaderEntity header = pianificazione.getPromozioneTestataEntity()
                            .getMuiCfgSetPianificazione().getMuiCfgConfHeaders().stream()
                            .filter(h -> h.getLivelloPianificazione().getDescrizione()
                                    .equalsIgnoreCase(level.getDescription()))
                            .filter(h -> h.getMeccanicaEntity().getId().equals(pianificazione.getMeccanicaEntity().getId()))
                            .findFirst().orElse(null);
                    if (header == null) {
                        log.error("Impossibile recuperare l'header per la pianificazione id " + pianificazione.getId()
                                + " con livello " + level.getDescription());
                        return false;
                    }

                    if (header.getMaxRaggruppamento() != null) {
                        if (pianificazione.getChildren() == null) {
                            // posso aggiungere raggruppamenti ...
                            return true;
                        } else {
                            return pianificazione.getChildren().stream()
                                    .filter(h -> h.getTipoRiga().getCodiceTipo()
                                            .equalsIgnoreCase(PlanningLevelEnum.RAGGRUPPAMENTO.getCode()))
                                    .count() < header.getMaxRaggruppamento();
                        }
                    }
                    // non esiste limite per il max set
                    return true;
                case RAGGRUPPAMENTO:
                    return true;
                case ELEMENTO:
                    return false;
                default:
                    break;
            }
        }
        return false;
    }

    List<Long> getHierchyTree(@NonNull final PromozionePianificazioneEntity e) {
        final List<Long> path = new ArrayList<>();
        if (e.getParent() != null) {
            path.addAll(0, getHierchyTree(e.getParent()));
        }
        path.add(e.getId());

        return path;
    }

    /**
     * Questo metodo aggiorna il rowData in funzione della seguente regola: I
     * seguenti campi sono editabili solo se Numero Raggruppamento = 1: -
     * Descrizione Sconto - Numero Utilizzi - Data fine - Data inizio - Stampa
     * Offerta Scontrino Se il numero raggruppamento è <> da 1 devono essere
     * valorizzati a null e non editabili
     * <p>
     * NOTA: essendo valori persistiti e recuperati -> solo l'editabilità in
     * visualizzazione deve essere modificata secondo questa regola
     *
     * @param dbPromoAgCellRowMap
     */
    private void updateRowDataByNumeroRaggruppamento(HashMap<String, DBPromoAgCell> dbPromoAgCellRowMap) {
        final DBPromoAgCell numeroRaggruppamentoAgCell = dbPromoAgCellRowMap
                .get(PianificazioneConstants.REFERENCE_NUMERO_RAGGRUPPAMENTO_COLUMN);

        if (numeroRaggruppamentoAgCell != null) {
            final List<String> updateableColumns = Arrays.asList(
                    PianificazioneConstants.REFERENCE_DESCRIZIONE_SCONTO_COLUMN,
                    PianificazioneConstants.REFERENCE_NUMERO_UTILIZZI_COLUMN,
                    PianificazioneConstants.REFERENCE_DATA_FINE_COLUMN,
                    PianificazioneConstants.REFERENCE_DATA_INIZIO_COLUMN,
                    PianificazioneConstants.REFERENCE_STAMPA_OFFERTA_SCONTRINO_COLUMN,
                    PianificazioneConstants.REFERENCE_VALIDITA_PERIODO_COLUMN,
                    PianificazioneConstants.REFERENCE_NUMERO_STAMPE_COLUMN,
                    PianificazioneConstants.REFERENCE_MAGGIOR_VANTAGGIO_COLUMN,
                    PianificazioneConstants.REFERENCE_INCLUDI_ESSELUNGA,
                    PianificazioneConstants.REFERENCE_INCLUDI_ESSERBELLA,
                    PianificazioneConstants.REFERENCE_INCLUDI_ATLANTIC,
                    PianificazioneConstants.REFERENCE_INCLUDI_LAESSE);

            updateableColumns.forEach(column -> {
                final DBPromoAgCell agCell = dbPromoAgCellRowMap.get(column);
                if (agCell != null) {
                    agCell.setEditable(PianificazioneConstants.PRECARICATI_SU_CARTA_NUMERO_RAGGRUPPAMENTO_EDITABLE
                            .equals(numeroRaggruppamentoAgCell.getValue()));
                }
            });
        }
    }

    /**
     * Questo metodo crea la picklist dinamica per le colonne in cui le picklist non
     * devono includere valori già usati nello stesso periodo della promozione e da
     * ogni relativa meccanica
     *
     * @param promozionePianificazioneEntity
     * @param dbPromoAgCellRowMap
     * @param columnField
     */
    public List<String> createDynamicPickList(PromozionePianificazioneEntity promozionePianificazioneEntity,
                                              Map<String, DBPromoAgCell> dbPromoAgCellRowMap, String columnField, List<CanalePromozioneEntity> canali) {

        final String[] picklistValues = pianificazionePromoUtil.createColumnValueArray(
                promozionePianificazioneEntity.getPromozioneTestataEntity(),
                dbPromoAgCellRowMap.get(columnField).getPicklistValues(), columnField,
                promozionePianificazioneEntity.getMeccanicaEntity(), canali);
        List<String> pickListUpdated = Arrays.stream(picklistValues).collect(Collectors.toList());
        final String currentValue = dbPromoAgCellRowMap.get(columnField).getValue();
        // Se esiste un valore già persistito lo aggiungo alla picklist in modo che sia
        // selezionabile e la picklist non risulti vuota
        if ((currentValue != null) && !currentValue.isEmpty()) {
            final StringUtils utils = new StringUtils();
            pickListUpdated.add(currentValue);
            if (utils.isInteger(currentValue)) {
                pickListUpdated = pickListUpdated.stream().sorted(Comparator.comparingInt(Integer::parseInt))
                        .collect(Collectors.toList());
            } else if (utils.isNumber(currentValue)) {
                pickListUpdated = pickListUpdated.stream().sorted(Comparator.comparingDouble(Double::parseDouble))
                        .collect(Collectors.toList());
            } else {
                pickListUpdated = pickListUpdated.stream().sorted(Comparator.comparing(String::toString))
                        .collect(Collectors.toList());
            }
        }

        dbPromoAgCellRowMap.get(columnField).setPicklistValues(pickListUpdated.toArray(new String[0]));
        return pickListUpdated;
    }

    private DBPromoAgCell mapDataToDBPromoAgCell(PromozioneTestataEntity testata,
                                                 CfgPianificazioneEntity configurazione, Object value, String tipoRiga, HashMap<String, DBPromoAgCell> righe,
                                                 boolean promoEditable, boolean writable) {
// TODO fix toString del value dopo VisualizzaPianificazioneHelper
        final StringUtils utils = new StringUtils();

// I nomi delle colonne sono da recuperare nel campo "Descrizione" dell'entity
// CfgPianificazioneCampiEntity
        final String header = configurazione.getMuiCfgPianificazioneCampi().getDescrizione();
        final String field = configurazione.getMuiCfgPianificazioneCampi().getCampo();
        final String type = configurazione.getMuiCfgPianificazioneCampi().getTipo();
// Il codice tipo ha valore "M" per le righe master e "D" per le righe detail
// nella tabella MUI_CFG_PIANIFICAZ_TIPO_RIGA
        final String rowType = planningCommons.getRowType(configurazione.getMuiCfgPianificazTipoRiga().getCodiceTipo());
// il campo di configurazione "lista" puo' assumere i seguenti valori:
// null : nessuna lista
// "" : nessuna lista
// "[valore1;valore2;...;valoren]": il renderer del campo deve essere una lista
// con i valori : valore1,valore2, ..., valoren
// "[valorex..valorey]" : il renderer del campo deve essere una lista con i
// valori interi da valorex a valorey
        final List<String> lista = picklistUtils.defineListaCellEditor(configurazione.getLista());

        String picklistService = null;
        String comboBoxService = null;

        final String cellType = planningCommons.defineCellType(configurazione.getTipoLista(), lista, field, type, configurazione.getRenderAsCombo());
        if (lista != null && cellType != null) {
            switch (DBPromoCellTypeEnum.asEnum(cellType)) {
                case PICKLIST:
                    picklistService = configurazione.getTipoLista();
                    break;
                case COMBOBOX:
                    comboBoxService = configurazione.getTipoLista();
                    break;
                default:
                    break;
            }
        }
// La valorizzazione e l'editabilità dipendono dal TIPORIGA e dal valore di
// SICUREZZA in MUI_CFG_PIANIFICAZIONE
// #3282: i campi di tipo 'Formula' non sono MAI editabili
// #3803: introduzione sicurezza sulla pianificazione
        final boolean editable = writable && !configurazione.getFormulaEnabled() && promoEditable
                && planningCommons.isCellEditable(field, rowType, tipoRiga, righe)
                ? planningCommons.checkColumnEditable(testata, configurazione.getSicurezza(), field)
                : false;
        String val = (planningCommons.checkSpecialHeader(field) || rowType.equals(tipoRiga)) && (value != null)
                ? planningCommons.toValue(value, type)
                : null;
        final DBPromoAgCell dbPromoAgCell = DBPromoAgCell.builder().name(header)
                .type(cellType)
                // Il valore di una cella può essere null solo se il campo non è mandatory
                // (MUI_CFG_PIANIFICAZIONE.MANDATORY = "1")
                .nullable(!PianificazioneConstants.PLANNING_UPDATE_CELL_MANDATORY.equals(configurazione.getMandatory()))
                .editable(editable)
                .value(val)
                .dataTypeParams(DataTypeParams.builder()
                        .multiselect(new Short((short) 1).equals(configurazione.getMultiselect()))
                        // #2255
                        .length(configurazione.getLength()).build())
                .picklistService(
                        picklistService != null ? WebserviceParams.builder().url(picklistService).build() : null)
                .comboBoxService(
                        comboBoxService != null ? WebserviceParams.builder().url(comboBoxService).build() : null)
                .build();

//        if (dbPromoAgCell.getEditable() && ((dbPromoAgCell.getValue() == null) || dbPromoAgCell.getValue().isEmpty())) {
//            // Se solo warn il background è quello del warn
//            if (PianificazioneConstants.PLANNING_UPDATE_CELL_WARN.equals(configurazione.getWarn())) {
//                final StyleParams styleParams = new StyleParams();
//                styleParams.setBackgroundColor(PianificazioneConstants.BACKGROUND_COLOR_WARN);
//                dbPromoAgCell.setStyleParams(styleParams);
//            }
//            // Se solo mandatory o sia warn sia mandatory il background è quello del
//            // mandatory
//            if (PianificazioneConstants.PLANNING_UPDATE_CELL_MANDATORY.equals(configurazione.getMandatory())) {
//                final StyleParams styleParams = new StyleParams();
//                styleParams.setBackgroundColor(PianificazioneConstants.BACKGROUND_COLOR_MANDATORY);
//                dbPromoAgCell.setStyleParams(styleParams);
//            }
//        }

        // la lista dei valori e' sempre > 1 perche' il primo elemento
        // e' il valore vuoto
        if (DBPromoCellTypeEnum.PICKLIST.getType().equals(dbPromoAgCell.getType())) {
            if (picklistService != null) {
                dbPromoAgCell.setPicklistService(
                        WebserviceParams.builder().url(preparePickListService(picklistService)).build());
            } else {
                dbPromoAgCell.setPicklistValues(lista.toArray(new String[lista.size()]));
            }
        } else if (DBPromoCellTypeEnum.COMBOBOX.getType().equals(dbPromoAgCell.getType())) {
            if (comboBoxService != null) {
                dbPromoAgCell.setComboBoxService(
                        WebserviceParams.builder().url(preparePickListService(comboBoxService)).build());
            }
            // TODO: combo box values
            // la cosa piu' veloce e' fare uno switch e considerare
            // le colonne e creare una singola voce
            if (val != null && PianificazioneConstants.CAMPO_PIANIFICAZIONE_SOTTOCLASSE
                    .equals(configurazione.getMuiCfgPianificazioneCampi().getCampo())) {
                try {
                    final String codiceStatoPromozione = PromoAcl.getCodiceStatoPromozione(testata);
                    final ComboBoxCapable sottoclasse = sottoclasseUtilInstance.get()
                            .getSottoclasseByCodiceAndStatoPromozione(val, codiceStatoPromozione);
                    if (sottoclasse != null) {
                        dbPromoAgCell.setComboBoxValues(Collections.singletonList(comboBoxFactoryInstance.get()
                                .from(sottoclasse)));
                    } else {
                        log.error(String.format("Nessuna sottoclasse disponibile con codice '%s' nello stato '%s'",
                                val, codiceStatoPromozione));
                        val = null;
                    }
                } catch (Exception ex) {
                    log.error(String.format("Errore durante il recupero della sottoclasse con codice '%s'", val), ex);
                    val = null;
                }
            }
            if (PianificazioneConstants.CAMPO_PIANIFICAZIONE_FORMA_PAGAMENTO
                    .equalsIgnoreCase(configurazione.getMuiCfgPianificazioneCampi().getCampo())) {
                try {
                    ComboBoxCapable combo = formaPagamentoServiceInstance.get().findByCode(val);
                    if (combo != null) {
                        dbPromoAgCell.setComboBoxValues(Collections.singletonList(comboBoxFactoryInstance.get().from(combo)));
                    } else {
                        log.error(String.format("Nessuna forma pagamento esistente con codice '%s'", val));
                        val = null;
                    }
                } catch (Exception ex) {
                    log.error(String.format("Errore durante il recupero della forma pagamento con codice '%s'", val), ex);
                    val = null;
                }
            }
        }
        if (utils.isIntegerType(type)) {
            DataTypeParams dataTypeParams = dbPromoAgCell.getDataTypeParams();
            if (dataTypeParams == null) {
                dataTypeParams = new DataTypeParams();
            }
            dataTypeParams.setPrecision(0);
            dbPromoAgCell.setDataTypeParams(dataTypeParams);
        }

        // 3144: override del campo REFERENCE_NUMERO_RAGGRUPPAMENTO_COLUMN
        if (configurazione.getMuiCfgPianificazioneCampi().getCampo()
                .equals(PianificazioneConstants.REFERENCE_NUMERO_RAGGRUPPAMENTO_COLUMN)) {
            dbPromoAgCell.setEditable(false);
        }

        // MG 2025-09-23: spostato impostazione mandatory / warning al termine di tutti i controlli
        dbPromoAgCell.setMandatory((val == null || val.isEmpty())
                && PianificazioneConstants.PLANNING_UPDATE_CELL_MANDATORY.equals(configurazione.getMandatory()));
        dbPromoAgCell.setWarning((val == null || val.isEmpty())
                && PianificazioneConstants.PLANNING_UPDATE_CELL_WARN.equals(configurazione.getWarn()));

        return dbPromoAgCell;
    }

    private String preparePickListService(String serviceString) {
        if (serviceString.indexOf("ws://") >= 0) {
            return serviceString.substring(serviceString.indexOf("ws://") + "ws://".length());
        }
        return serviceString;
    }

    private DBPromoColumnlDef createDynamicColumnDef(PromozioneTestataEntity testata,
                                                     CfgPianificazioneEntity pianificazione) {

        DBPromoColumnlDef dbPromoCelDef;

        if (pianificazione.getMuiCfgPianificazioneCampi().getTipo() == null) {
            log.warn(String.format(
                    "DEF_VALUE is null for MUI_PROMOZIONE_PIANIFICAZIONE.CAMPO %s and MUI_CFG_PIANIFICAZIONE_CAMPI.DESCRIZIONE %s",
                    pianificazione.getMuiCfgPianificazioneCampi().getCampo(),
                    pianificazione.getMuiCfgPianificazioneCampi().getDescrizione()));
        }

        final boolean isColumnEditable = planningCommons.checkColumnEditable(testata, pianificazione.getSicurezza());
        final boolean isColumnHidden = planningCommons.getBooleanHideValue(pianificazione.getHide());
// #3264: Se campo "Descrizione" di CfgPianificazioneEntity e' valorizzato (!=
// null && !empty),
// viene utilizzato come header, altrimenti viene utilizzato campo "Descrizione"
// di CfgPianificazioneCampiEntity
        final String headerName = org.apache.commons.lang3.StringUtils.isNotBlank(pianificazione.getDescrizione())
                ? pianificazione.getDescrizione()
                : pianificazione.getMuiCfgPianificazioneCampi().getDescrizione();

        Integer defaultWidth = pianificazione.getMuiCfgPianificazioneCampi().getColumnWidth();
        if (!((defaultWidth != null) && (defaultWidth > 0))) {
            // in caso il campo non sia valorizzato , overo sia null o 0 , prevedere una
            // dimensione di default
            if (PianificazioneConstants.REFERENCE_DATA_INIZIO_COLUMN
                    .equals(pianificazione.getMuiCfgPianificazioneCampi().getCampo())
                    || PianificazioneConstants.REFERENCE_DATA_FINE_COLUMN
                    .equals(pianificazione.getMuiCfgPianificazioneCampi().getCampo())) {
                defaultWidth = 100;
            } else {
                defaultWidth = 200;
            }
        }
        if (PianificazioneConstants.REFERENCE_DATA_INIZIO_COLUMN
                .equals(pianificazione.getMuiCfgPianificazioneCampi().getCampo())
                || PianificazioneConstants.REFERENCE_DATA_FINE_COLUMN
                .equals(pianificazione.getMuiCfgPianificazioneCampi().getCampo())) {

            dbPromoCelDef = DBPromoColumnlDef.builder().headerName(headerName).editable(isColumnEditable)
                    .width(defaultWidth).hide(isColumnHidden).columnType(DBPromoColTypeEnum.DATE.getType())
                    .field(pianificazione.getMuiCfgPianificazioneCampi().getCampo()).build();
        } else {
            dbPromoCelDef = DBPromoColumnlDef.builder().headerName(headerName).editable(isColumnEditable)
                    .width(defaultWidth).field(pianificazione.getMuiCfgPianificazioneCampi().getCampo())
                    .hide(isColumnHidden)
                    .columnType(planningCommons.defineDBPromoColumnType(
                            picklistUtils.defineListaCellEditor(pianificazione.getLista()),
                            pianificazione.getMuiCfgPianificazioneCampi().getCampo(),
                            pianificazione.getMuiCfgPianificazioneCampi().getTipo()))
                    .build();
        }

        return dbPromoCelDef;
    }

    Comparator<PromozionePianificazioneEntity> byNumRaggr = (o1, o2) -> {
        try {

            final int raggr1 = Float.valueOf(o1.getNumRaggruppamento()).intValue();//Integer.parseInt(o1.getNumRaggruppamento());
            final int raggr2 = Float.valueOf(o2.getNumRaggruppamento()).intValue();//Integer.parseInt(o2.getNumRaggruppamento());
            return Integer.compare(raggr1, raggr2);
        } catch (NumberFormatException ex) {
            log.error(String.format("Unable to compare 'NumRaggr' %s with %s", o1.getNumRaggruppamento(),
                    o2.getNumRaggruppamento()), ex);
        }
        return 0;
    };
}