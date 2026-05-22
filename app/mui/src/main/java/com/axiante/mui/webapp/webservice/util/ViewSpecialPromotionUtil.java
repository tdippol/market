package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.dao.CompratoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.FornitoreDAO;
import com.axiante.mui.dbpromo.persistence.entities.*;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpStatiConsentitiService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpStatiTransizioneService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpTestataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import com.axiante.mui.common.utility.AxUUID;
import javax.inject.Inject;

import java.math.BigDecimal;


import java.util.*;
import javax.enterprise.context.Dependent;

@Slf4j
@Dependent
public class ViewSpecialPromotionUtil {

    @Inject
    private MuiSpTestataService muiSpTestataService;

    @Inject
    private CreatePromotionService createPromotionService;

    @Inject
    private MuiSpStatiConsentitiService muiSpStatiConsentitiService;

    @Inject
    private MuiSpStatiTransizioneService muiSpStatiTransizioneService;

    private DateTimeUtils dateTimeUtils = new DateTimeUtils();

    public String createRowData(List<MuiSpTestataEntity> testate) {

        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";

        try {
            testate.forEach(t -> arrayNode.add(createRowNode(t)));

            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(node);

        } catch (JsonProcessingException ex) {
            log.error("Error processing 'Visualizza Special Promotion' JSON row data", ex);
        }

        return json;
    }

    private ObjectNode createRowNode(MuiSpTestataEntity testata) {

        final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();
        DBPromoAgCell cell;

        cell = DBPromoAgCell.builder()
                .name("Id")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(String.valueOf(testata.getId()))
                .build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder()
                .name("Anno")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(testata.getAnno() == null ? "" : testata.getAnno())
                .build();
        map.put("anno", cell);

        String descrizioneCanale =
                testata.getCanalePromozioneEntity() != null &&
                        testata.getCanalePromozioneEntity().getDescrizione() != null
                        ? testata.getCanalePromozioneEntity().getDescrizione()
                        : "";

        cell = DBPromoAgCell.builder()
                .name("Canale")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(descrizioneCanale)
                .build();
        map.put("canale", cell);

        cell = DBPromoAgCell.builder()
                .name("Promozione")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(testata.getDescrizioneEstesa() == null ? "" : testata.getDescrizioneEstesa())
                .build();
        map.put("descrizioneEstesa", cell);

        cell = DBPromoAgCell.builder()
                .name("Attiva")
                .editable(false)
                .type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value(String.valueOf(1 == testata.getAttiva()))
                .build();
        map.put("attiva", cell);

        cell = DBPromoAgCell.builder()
                .name("Data Inizio")
                .editable(false)
                .type(DBPromoCellTypeEnum.DATE.getType())
                .value(testata.getDataInizio() == null
                        ? ""
                        : dateTimeUtils.toExcelDate(testata.getDataInizio()))
                .build();
        map.put("dataInizio", cell);

        cell = DBPromoAgCell.builder()
                .name("Data Fine")
                .editable(false)
                .type(DBPromoCellTypeEnum.DATE.getType())
                .value(testata.getDataFine() == null
                        ? ""
                        : dateTimeUtils.toExcelDate(testata.getDataFine()))
                .build();
        map.put("dataFine", cell);

        MuiSpStatoEntity statoCorrente =
                testata.getSpStatoEntities() == null
                        ? null
                        : testata.getSpStatoEntities().stream()
                        .filter(s -> s.getDataFineStato() == null)
                        .min(Comparator.comparing(MuiSpStatoEntity::getId))
                        .orElse(null);

        cell = DBPromoAgCell.builder()
                .name("Stato Promozione")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(statoCorrente == null
                        || statoCorrente.getStatoPromozioneEntity() == null
                        || statoCorrente.getStatoPromozioneEntity().getCodiceStato() == null
                        || statoCorrente.getStatoPromozioneEntity().getDescrizione() == null
                        ? ""
                        : String.format("%s - %s",
                        statoCorrente.getStatoPromozioneEntity().getCodiceStato(),
                        statoCorrente.getStatoPromozioneEntity().getDescrizione()))
                .build();
        map.put("stato", cell);

        cell = DBPromoAgCell.builder()
                .name("Data Reminder")
                .editable(false)
                .type(DBPromoCellTypeEnum.DATE.getType())
                .value(testata.getDataReminderInsParam() == null
                        ? ""
                        : dateTimeUtils.toExcelDate(testata.getDataReminderInsParam()))
                .build();
        map.put("dataReminder", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    public void createSpecialPromotion(MuiSpTestataEntity testata,
                                       String codicePromozione,
                                       CanalePromozioneEntity canalePromozioneEntity,
                                       StatoPromozioneEntity statoIniziale,
                                       String userName){
        if (testata == null) {
            throw new IllegalArgumentException("Testata Special Promotion nulla");
        }

        prepareTestataForCreate(testata, codicePromozione, canalePromozioneEntity, userName);
        buildInitialState(testata, statoIniziale, userName);
        muiSpTestataService.save(testata);


        muiSpTestataService.flush();

        if (testata.getId() == null) {
            throw new IllegalStateException("Id Special Promotion nullo dopo il salvataggio della testata");
        }

        try {
            muiSpTestataService.runProcedureAssociaReparti(testata.getId());
        } catch (Exception e) {
            log.error("Errore durante la chiamata della procedura p_mui_sp_associa_reparti per la promozione {}",
                    testata.getId(), e);
            throw new IllegalStateException("Errore durante l'associazione reparti della Special Promotion", e);
        }

        muiSpTestataService.deactivateOtherActive(testata.getId());
        createSpStatiConsentiti(testata, userName);
        createSpStatiTransizione(testata, userName);

    }

    private void prepareTestataForCreate(MuiSpTestataEntity testata,
                                         String codicePromozione,
                                         CanalePromozioneEntity canalePromozioneEntity,
                                         String userName) {
        testata.setCodicePromozione(codicePromozione);
        testata.setCanalePromozioneEntity(canalePromozioneEntity);

        if (testata.getAttiva() == null) {
            testata.setAttiva(1);
        }

        if (testata.getDataInizio() != null
                && (testata.getAnno() == null || testata.getAnno().trim().isEmpty())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(testata.getDataInizio());
            testata.setAnno(String.valueOf(calendar.get(Calendar.YEAR)));
        }

        if (testata.getDataInizio() != null && testata.getDataReminderInsParam() == null) {
            testata.setDataReminderInsParam(addDays(testata.getDataInizio(), 12));
        }

        if (testata.getDescrizioneEstesa() == null || testata.getDescrizioneEstesa().trim().isEmpty()) {
            testata.setDescrizioneEstesa(testata.getDescrizione());
        }

        testata.setCodiceUtenteInserimento(userName);
        testata.setDataInserimento(new Date());
        testata.setCodiceUtenteAggiornamento(userName);
    }

    private MuiSpStatoEntity buildInitialState(MuiSpTestataEntity testata,
                                               StatoPromozioneEntity statoIniziale,
                                               String userName) {
        MuiSpStatoEntity stato = new MuiSpStatoEntity();

        testata.addSpStatoEntity(stato);
        stato.setDataInizioStato(new Date());
        stato.setDataFineStato(null);
        stato.setDataInserimento(new Date());
        stato.setCodiceUtenteInserimento(userName);
        stato.setUuid(AxUUID.randomUUID().toString());

        applyInitialStatusCode(stato, statoIniziale);

        return stato;
    }

    private void applyInitialStatusCode(MuiSpStatoEntity stato,
                                        StatoPromozioneEntity statoIniziale) {
        stato.setStatoPromozioneEntity(statoIniziale);
    }

    private Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    private void createSpStatiConsentiti(MuiSpTestataEntity testata, String userName) {

        List<CfgStatiCanaleConsentEntity> cfgList =
                createPromotionService.findAllPromoAllowedStateByChannel(
                        testata.getCanalePromozioneEntity());

        for (CfgStatiCanaleConsentEntity cfg : cfgList) {

            MuiSpStatiConsentitiEntity entity = new MuiSpStatiConsentitiEntity();
            entity.setSpTestataEntity(testata);
            entity.setStatoPromozioneEntity(cfg.getStatoPromozioneEntity());

            muiSpStatiConsentitiService.persistWithAuditLog(entity, userName);
        }
    }

    private void createSpStatiTransizione(MuiSpTestataEntity testata, String userName) {

        List<CfgStatiTransizioniEntity> cfgList =
                createPromotionService.findAllPromoTransitionByChannel(
                        testata.getCanalePromozioneEntity());

        for (CfgStatiTransizioniEntity cfg : cfgList) {

            MuiSpStatiTransizioneEntity entity = new MuiSpStatiTransizioneEntity();

            entity.setSpTestataEntity(testata);
            entity.setStatoPromozioneEntity(cfg.getStatoPromozione());
            entity.setStatoTransizione(cfg.getStatoTransizione());
            entity.setDescrizione(cfg.getDescrizione());
            entity.setStatoErrore(cfg.getStatoErrore());

            muiSpStatiTransizioneService.persistWithAuditLog(entity, userName);
        }
    }

    public String createRowDataStati(List<MuiSpStatoEntity> stati) {

        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";

        try {
            stati.forEach(s -> arrayNode.add(createRowNodeStato(s)));

            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(node);

        } catch (JsonProcessingException ex) {
            log.error("Error processing 'Stati Special Promotion' JSON row data", ex);
        }

        return json;
    }

    private ObjectNode createRowNodeStato(MuiSpStatoEntity stato) {

        final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();
        DBPromoAgCell cell;

        cell = DBPromoAgCell.builder()
                .name("Stato")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(stato.getStatoPromozioneEntity() == null
                        || stato.getStatoPromozioneEntity().getCodiceStato() == null
                        || stato.getStatoPromozioneEntity().getDescrizione() == null
                        ? ""
                        : String.format("%s - %s",
                        stato.getStatoPromozioneEntity().getCodiceStato(),
                        stato.getStatoPromozioneEntity().getDescrizione()))
                .build();
        map.put("stato", cell);

        cell = DBPromoAgCell.builder()
                .name("Data Inizio Stato")
                .editable(false)
                .type(DBPromoCellTypeEnum.DATE.getType())
                .value(stato.getDataInizioStato() == null
                        ? ""
                        : dateTimeUtils.toExcelDate(stato.getDataInizioStato()))
                .build();
        map.put("dataInizioStato", cell);

        cell = DBPromoAgCell.builder()
                .name("Data Fine Stato")
                .editable(false)
                .type(DBPromoCellTypeEnum.DATE.getType())
                .value(stato.getDataFineStato() == null
                        ? ""
                        : dateTimeUtils.toExcelDate(stato.getDataFineStato()))
                .build();
        map.put("dataFineStato", cell);

        cell = DBPromoAgCell.builder()
                .name("Utente")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(stato.getCodiceUtenteInserimento() == null
                        ? ""
                        : stato.getCodiceUtenteInserimento())
                .build();
        map.put("utente", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }


    public String createRowDataPubblicazioni(List<MuiSpContribCompratoreEntity> contributi,
                                             String descrizioneGruppo,
                                             String statoGruppo) {

        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";

        try {
            contributi.forEach(c -> arrayNode.add(createRowNodePubblicazioni(c, descrizioneGruppo, statoGruppo)));

            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(node);

        } catch (JsonProcessingException ex) {
            log.error("Error processing 'Pubblicazioni Special Promotion' JSON row data", ex);
        }

        return json;
    }

    private ObjectNode createRowNodePubblicazioni(MuiSpContribCompratoreEntity contributo,
                                                  String descrizioneGruppo,
                                                  String statoGruppo) {

        final HashedMap<String, DBPromoAgCell> map = new HashedMap<String, DBPromoAgCell>();
        DBPromoAgCell cell;

        String codiceCompratore = "";
        String descrizioneCompratore = "";

        if (contributo.getCompratoreEntity() != null) {
            codiceCompratore = contributo.getCompratoreEntity().getCodiceCompratore() == null
                    ? ""
                    : contributo.getCompratoreEntity().getCodiceCompratore();

            descrizioneCompratore = contributo.getCompratoreEntity().getDescrizione() == null
                    ? ""
                    : contributo.getCompratoreEntity().getDescrizione();
        }

        String descrizione = codiceCompratore;
        if (!descrizioneCompratore.isEmpty()) {
            descrizione = descrizione + " - " + descrizioneCompratore;
        }

        cell = DBPromoAgCell.builder()
                .name("Gruppo")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(descrizioneGruppo)
                .build();
        map.put("gruppo", cell);

        cell = DBPromoAgCell.builder()
                .name("StatoGruppo")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(statoGruppo)
                .build();
        map.put("statoGruppo", cell);

        cell = DBPromoAgCell.builder()
                .name("IdCompratore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(contributo.getCompratoreEntity() != null && contributo.getCompratoreEntity().getId() != null
                        ? String.valueOf(contributo.getCompratoreEntity().getId())
                        : "")
                .build();
        map.put("idCompratore", cell);

        cell = DBPromoAgCell.builder()
                .name("Descrizione")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(descrizione)
                .build();
        map.put("descrizione", cell);

        String stato = "🔴";
        if (contributo.getFlContribuzione() != null && contributo.getFlContribuzione().intValue() == 1) {
            stato = "🟢";
        }

        cell = DBPromoAgCell.builder()
                .name("Stato")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(stato)
                .build();
        map.put("stato", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }




    public String createRowDataParametri(List<MuiSpParametroEntity> parametri) {

        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";

        try {
            log.info("createRowDataParametri - righe in ingresso: " + (parametri == null ? 0 : parametri.size()));

            if (parametri != null) {
                parametri.forEach(p -> arrayNode.add(createRowNodeParametro(p)));
            }

            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(node);

            log.info("createRowDataParametri - json finale: " + json);

        } catch (JsonProcessingException ex) {
            log.error("Error processing 'Parametri Special Promotion' JSON row data", ex);
        }

        return json;
    }


    public String createRowDataParametri(List<MuiSpParametroEntity> parametri,
                                         Map<Long, Integer> contribuzioneByCompratore) {

        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";

        try {
            log.info("createRowDataParametri - righe in ingresso: " + (parametri == null ? 0 : parametri.size()));

            if (parametri != null) {
                parametri.forEach(p -> {
                    ObjectNode row = createRowNodeParametro(p);

                    boolean editable = true;

                    if (p.getIdCompratore() != null && contribuzioneByCompratore != null) {
                        Integer flContribuzione = contribuzioneByCompratore.get(p.getIdCompratore());

                        if (flContribuzione != null && flContribuzione.intValue() == 1) {
                            editable = false;
                        }
                    }

                    row.put("editable", editable);
                    arrayNode.add(row);
                });
            }

            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(node);

            log.info("createRowDataParametri - json finale: " + json);

        } catch (JsonProcessingException ex) {
            log.error("Error processing 'Parametri Special Promotion' JSON row data", ex);
        }

        return json;
    }




    private ObjectNode createRowNodeParametro(MuiSpParametroEntity parametro) {

        final HashedMap<String, DBPromoAgCell> map = new HashedMap<String, DBPromoAgCell>();
        DBPromoAgCell cell;

        String descrizioneCompratore = parametro.getDescrizioneCompratore() == null
                ? ""
                : parametro.getDescrizioneCompratore();

        if (parametro.getCodiceCompratore() != null) {
            descrizioneCompratore = descrizioneCompratore
                    .replace("[" + parametro.getCodiceCompratore() + "]", "")
                    .trim();
        }

        cell = DBPromoAgCell.builder()
                .name("Id")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getId() != null ? String.valueOf(parametro.getId()) : "")
                .build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder()
                .name("IdPromozione")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getIdPromozione() != null ? String.valueOf(parametro.getIdPromozione()) : "")
                .build();
        map.put("idPromozione", cell);

        cell = DBPromoAgCell.builder()
                .name("IdCompratore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getIdCompratore() != null ? String.valueOf(parametro.getIdCompratore()) : "")
                .build();
        map.put("idCompratore", cell);

        cell = DBPromoAgCell.builder()
                .name("IdFornitore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getIdFornitore() != null ? String.valueOf(parametro.getIdFornitore()) : "")
                .build();
        map.put("idFornitore", cell);

        cell = DBPromoAgCell.builder()
                .name("Codice Compratore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getCodiceCompratore() == null ? "" : parametro.getCodiceCompratore())
                .build();
        map.put("codiceCompratore", cell);

        cell = DBPromoAgCell.builder()
                .name("Compratore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(descrizioneCompratore)
                .build();
        map.put("compratore", cell);

        cell = DBPromoAgCell.builder()
                .name("Fornitore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getDescrizioneFornitore() == null ? "" : parametro.getDescrizioneFornitore())
                .build();
        map.put("fornitore", cell);

        cell = DBPromoAgCell.builder()
                .name("Tipo Premio")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getTipoPremio() == null ? "" : parametro.getTipoPremio())
                .build();
        map.put("tipoPremio", cell);

        cell = DBPromoAgCell.builder()
                .name("Premio CF")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getPremioCf() == null ? "" : parametro.getPremioCf().toString())
                .build();
        map.put("premioCf", cell);

        cell = DBPromoAgCell.builder()
                .name("Premio %")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getPremioPerc() == null ? "" : parametro.getPremioPerc().toString())
                .build();
        map.put("premioPerc", cell);

        cell = DBPromoAgCell.builder()
                .name("Tipo Base Imponibile")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getTipoBaseImp() == null ? "" : parametro.getTipoBaseImp())
                .build();
        map.put("tipoBaseImp", cell);

        cell = DBPromoAgCell.builder()
                .name("Soglia Max")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getSogliaMax() == null ? "" : parametro.getSogliaMax().toString())
                .build();
        map.put("sogliaMax", cell);

        cell = DBPromoAgCell.builder()
                .name("Modalità Liquidazione")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getModLiq() == null ? "" : parametro.getModLiq())
                .build();
        map.put("modLiq", cell);

        cell = DBPromoAgCell.builder()
                .name("Verifica")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getCheckValue() == null ? "" : parametro.getCheckValue())
                .build();
        map.put("checkValue", cell);

        BigDecimal premioFinale = BigDecimal.ZERO;

        if (parametro.getPremioFinaleVend() != null) {
            premioFinale = premioFinale.add(parametro.getPremioFinaleVend());
        }

        if (parametro.getPremioFinaleCons() != null) {
            premioFinale = premioFinale.add(parametro.getPremioFinaleCons());
        }

        cell = DBPromoAgCell.builder()
                .name("Premio Finale")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(premioFinale.compareTo(BigDecimal.ZERO) == 0
                        ? ""
                        : premioFinale.toString())
                .build();

        map.put("premioFinale", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }



    public String createRowDataValidazioneArticolo(List<MuiSpPianificazioneEntity> righe,
                                                   Map<Long, Integer> contribuzioneByCompratore) {

        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";

        try {


            if (righe != null) {
                Map<String, List<MuiSpPianificazioneEntity>> righeByFornitoreCompratore =
                        new LinkedHashMap<String, List<MuiSpPianificazioneEntity>>();

                for (MuiSpPianificazioneEntity riga : righe) {
                    String key = String.valueOf(riga.getIdFornitore()) + "_" + String.valueOf(riga.getIdCompratore());

                    if (!righeByFornitoreCompratore.containsKey(key)) {
                        righeByFornitoreCompratore.put(key, new ArrayList<MuiSpPianificazioneEntity>());
                    }

                    righeByFornitoreCompratore.get(key).add(riga);
                }

                for (List<MuiSpPianificazioneEntity> gruppo : righeByFornitoreCompratore.values()) {
                    arrayNode.add(createRowNodeTotaleFornitore(gruppo));

                    for (MuiSpPianificazioneEntity riga : gruppo) {
                        ObjectNode row = createRowNodeValidazioneArticolo(riga);

                        boolean editable = isCompratoreEditable(
                                riga.getIdCompratore(),
                                contribuzioneByCompratore
                        );
                        row.put("editable", editable);
                        arrayNode.add(row);
                    }
                }
            }

            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(node);

        } catch (JsonProcessingException ex) {
            log.error("Error processing 'Validazione Articolo' JSON row data", ex);
        }

        return json;
    }

    private boolean isCompratoreEditable(Long idCompratore,
                                         Map<Long, Integer> contribuzioneByCompratore) {
        if (idCompratore == null || contribuzioneByCompratore == null) {
            return true;
        }

        Integer flContribuzione = contribuzioneByCompratore.get(idCompratore);

        return !(flContribuzione != null && flContribuzione.intValue() == 1);
    }

    private ObjectNode createRowNodeValidazioneArticolo(MuiSpPianificazioneEntity riga) {

        final HashedMap<String, DBPromoAgCell> map = new HashedMap<String, DBPromoAgCell>();
        DBPromoAgCell cell;

        cell = DBPromoAgCell.builder()
                .name("Id")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getId() == null ? "" : riga.getId().toString())
                .build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder()
                .name("Id Promozione")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getIdPromozione() == null ? "" : riga.getIdPromozione().toString())
                .build();
        map.put("idPromozione", cell);

        cell = DBPromoAgCell.builder()
                .name("Id Item")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getIdItem() == null ? "" : riga.getIdItem().toString())
                .build();
        map.put("idItem", cell);

        cell = DBPromoAgCell.builder()
                .name("Id Fornitore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getIdFornitore() == null ? "" : riga.getIdFornitore().toString())
                .build();
        map.put("idFornitore", cell);

        cell = DBPromoAgCell.builder()
                .name("Tipo")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getTipo() == null ? "" : riga.getTipo())
                .build();
        map.put("tipo", cell);

        cell = DBPromoAgCell.builder()
                .name("Fornitore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getDescrizioneFornitore() == null ? "" : riga.getDescrizioneFornitore())
                .build();
        map.put("fornitore", cell);

        cell = DBPromoAgCell.builder()
                .name("Compratore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getDescrizioneCompratore() == null ? "" : riga.getDescrizioneCompratore())
                .build();
        map.put("compratore", cell);

        cell = DBPromoAgCell.builder()
                .name("Articolo")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getDescrizioneItem() == null ? "" : riga.getDescrizioneItem())
                .build();
        map.put("articolo", cell);

        cell = DBPromoAgCell.builder()
                .name("Primo Prezzo")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getPrimoPrezzo() == null ? "" : riga.getPrimoPrezzo().toString())
                .build();
        map.put("primoPrezzo", cell);

        cell = DBPromoAgCell.builder()
                .name("Marchio Privato")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getMarchioPrivato() == null ? "" : riga.getMarchioPrivato())
                .build();
        map.put("marchioPrivato", cell);

        cell = DBPromoAgCell.builder()
                .name("% Premio")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getPremioPerc() == null ? "" : riga.getPremioPerc().toString())
                .build();
        map.put("premioPerc", cell);

        cell = DBPromoAgCell.builder()
                .name("Tipo Base Imponibile")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getTipoBaseImp() == null ? "" : riga.getTipoBaseImp())
                .build();
        map.put("tipoBaseImp", cell);

        cell = DBPromoAgCell.builder()
                .name("Soglia Max")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value("")
                .build();
        map.put("sogliaMax", cell);

        cell = DBPromoAgCell.builder()
                .name("Modalità Liquidazione")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getModLiq() == null ? "" : riga.getModLiq())
                .build();
        map.put("modLiq", cell);

        cell = DBPromoAgCell.builder()
                .name("Escludere art")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getEsc() == null ? "" : riga.getEsc().toString())
                .build();
        map.put("esc", cell);

        cell = DBPromoAgCell.builder()
                .name("Escludere Q.ta Promo")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getEscPezziKg() == null ? "" : riga.getEscPezziKg().toString())
                .build();
        map.put("escPezziKg", cell);

        cell = DBPromoAgCell.builder()
                .name("% Premio Eccezione Articolo")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getPremioPercU() == null ? "" : riga.getPremioPercU().toString())
                .build();
        map.put("premioPercU", cell);

        cell = DBPromoAgCell.builder()
                .name("% Incidenza su Reparto")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getIncidenza() == null ? "" : riga.getIncidenza().toString())
                .build();
        map.put("incidenza", cell);

        cell = DBPromoAgCell.builder()
                .name("Base Imponibile Totale")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getVendTot() == null ? "" : riga.getVendTot().toString())
                .build();
        map.put("baseImponibileTotale", cell);

        cell = DBPromoAgCell.builder()
                .name("Base Imponibile Senza Vendite Promo")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getVendNopromo() == null ? "" : riga.getVendNopromo().toString())
                .build();
        map.put("baseImponibileNoVendPromo", cell);

        cell = DBPromoAgCell.builder()
                .name("Premio finale")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(riga.getPremioFinale() == null ? "" : riga.getPremioFinale().toString())
                .build();
        map.put("premioFinale", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }


    private ObjectNode createRowNodeTotaleFornitore(List<MuiSpPianificazioneEntity> righe) {
        MuiSpPianificazioneEntity first = righe == null || righe.isEmpty() ? null : righe.get(0);

        final HashedMap<String, DBPromoAgCell> map = new HashedMap<String, DBPromoAgCell>();
        DBPromoAgCell cell;

        BigDecimal premio = BigDecimal.ZERO;

        if (righe != null) {
            for (MuiSpPianificazioneEntity riga : righe) {
                if (riga.getPremioFinale() != null) {
                    premio = premio.add(riga.getPremioFinale());
                }
            }
        }

        BigDecimal soglia = first == null ? null : first.getSogliaMax();
        BigDecimal premioTotale = premio;

        if (soglia != null
                && soglia.compareTo(BigDecimal.ZERO) != 0
                && premio.compareTo(BigDecimal.ZERO) != 0
                && premio.compareTo(soglia) > 0) {
            premioTotale = soglia;
        }

        cell = DBPromoAgCell.builder().name("Fornitore").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(first == null || first.getDescrizioneFornitore() == null ? "" : first.getDescrizioneFornitore()).build();
        map.put("fornitore", cell);

        cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(first == null || first.getDescrizioneCompratore() == null ? "" : first.getDescrizioneCompratore()).build();
        map.put("compratore", cell);

        cell = DBPromoAgCell.builder().name("Articolo").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value("TOTALE FORNITORE").build();
        map.put("articolo", cell);

        cell = DBPromoAgCell.builder().name("Soglia Max").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(soglia == null ? "" : soglia.toString()).build();
        map.put("sogliaMax", cell);

        cell = DBPromoAgCell.builder().name("Premio finale").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(premioTotale.toString()).build();
        map.put("premioFinale", cell);

        ObjectNode node = JsonUtils.getMapper().valueToTree(map);
        node.put("editable", false);

        return node;
    }

    public String createRowDataContributiFornitoriCifraFissa(List<MuiSpParametroEntity> parametri) {

        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";

        try {
            if (parametri != null) {
                for (MuiSpParametroEntity parametro : parametri) {
                    arrayNode.add(createRowNodeContributiFornitoriCifraFissa(parametro));
                }
            }

            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(node);

        } catch (JsonProcessingException ex) {
            log.error("Error processing 'Contributi Fornitori Cifra Fissa' JSON row data", ex);
        }

        return json;
    }

    private ObjectNode createRowNodeContributiFornitoriCifraFissa(MuiSpParametroEntity parametro) {

        final HashedMap<String, DBPromoAgCell> map = new HashedMap<String, DBPromoAgCell>();
        DBPromoAgCell cell;

        String fornitore = parametro.getDescrizioneFornitore() == null
                ? ""
                : parametro.getDescrizioneFornitore();

        if (fornitore.startsWith("[") && fornitore.indexOf("]") > -1) {
            String codiceFornitore = fornitore.substring(1, fornitore.indexOf("]")).trim();
            String descrizioneFornitore = fornitore.substring(fornitore.indexOf("]") + 1).trim();
            fornitore = codiceFornitore + " - " + descrizioneFornitore;
        }

        String descrizioneCompratore = parametro.getDescrizioneCompratore() == null
                ? ""
                : parametro.getDescrizioneCompratore();

        if (parametro.getCodiceCompratore() != null) {
            descrizioneCompratore = descrizioneCompratore
                    .replace("[" + parametro.getCodiceCompratore() + "]", "")
                    .trim();
        }

        String compratore = parametro.getCodiceCompratore() == null
                ? descrizioneCompratore
                : parametro.getCodiceCompratore() + " - " + descrizioneCompratore;

        cell = DBPromoAgCell.builder()
                .name("Fornitore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(fornitore)
                .build();
        map.put("fornitore", cell);

        cell = DBPromoAgCell.builder()
                .name("Compratore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(compratore)
                .build();
        map.put("compratore", cell);

        cell = DBPromoAgCell.builder()
                .name("Cifra Fissa")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(parametro.getPremioCf() == null ? "" : parametro.getPremioCf().toString())
                .build();
        map.put("cifraFissa", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    public String createRowDataPubblicazione(
            List<MuiSpContribCompratoreHistEntity> storico,
            Map<Long, Integer> contribuzioneCorrenteByCompratore,
            Map<Long, Date> ultimaDataByCompratore) {

        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";

        try {

            if (storico != null) {

                for (MuiSpContribCompratoreHistEntity row : storico) {

                    arrayNode.add(
                            createRowNodePubblicazione(
                                    row,
                                    contribuzioneCorrenteByCompratore,
                                    ultimaDataByCompratore
                            )
                    );
                }
            }

            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.set(DbPromoConstants.ROW_DATA, arrayNode);

            json = JsonUtils.getMapper().writeValueAsString(node);

        } catch (JsonProcessingException ex) {
            log.error("Error processing 'Pubblicazione Special Promotion' JSON row data", ex);
        }

        return json;
    }


    private ObjectNode createRowNodePubblicazione(
            MuiSpContribCompratoreHistEntity storico,
            Map<Long, Integer> contribuzioneCorrenteByCompratore,
            Map<Long, Date> ultimaDataByCompratore) {

        final HashedMap<String, DBPromoAgCell> map =
                new HashedMap<String, DBPromoAgCell>();

        DBPromoAgCell cell;

        String codiceCompratore = "";
        String descrizioneCompratore = "";

        if (storico.getCompratoreEntity() != null) {

            codiceCompratore =
                    storico.getCompratoreEntity().getCodiceCompratore() == null
                            ? ""
                            : storico.getCompratoreEntity().getCodiceCompratore();

            descrizioneCompratore =
                    storico.getCompratoreEntity().getDescrizione() == null
                            ? ""
                            : storico.getCompratoreEntity().getDescrizione();
        }

        cell = DBPromoAgCell.builder()
                .name("Id Compratore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(storico.getCompratoreEntity() != null
                        && storico.getCompratoreEntity().getId() != null
                        ? storico.getCompratoreEntity().getId().toString()
                        : "")
                .build();

        map.put("idCompratore", cell);

        boolean disabled = true;

        if (storico.getCompratoreEntity() != null
                && storico.getCompratoreEntity().getId() != null
                && contribuzioneCorrenteByCompratore != null
                && ultimaDataByCompratore != null
                && storico.getDataInserimento() != null) {

            Long idCompratore =
                    storico.getCompratoreEntity().getId();

            Integer statoCorrente =
                    contribuzioneCorrenteByCompratore.get(idCompratore);

            Date ultimaData =
                    ultimaDataByCompratore.get(idCompratore);

            boolean isUltimaRiga =
                    ultimaData != null
                            && ultimaData.equals(storico.getDataInserimento());

            if (statoCorrente != null
                    && statoCorrente.intValue() == 0
                    && isUltimaRiga) {

                disabled = false;
            }
        }

        cell = DBPromoAgCell.builder()
                .name("Selected")
                .editable(!disabled)
                .type(DBPromoCellTypeEnum.CHECKBOX.getType())
                .value("false")
                .build();

        map.put("selected", cell);

        cell = DBPromoAgCell.builder()
                .name("Codice Compratore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(codiceCompratore)
                .build();

        map.put("codiceCompratore", cell);

        cell = DBPromoAgCell.builder()
                .name("Descrizione Compratore")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(descrizioneCompratore)
                .build();

        map.put("descrizioneCompratore", cell);

        String esito = "Da verde a rosso";

        if (storico.getFlContribuzione() != null
                && storico.getFlContribuzione().intValue() == 1) {

            esito = "Da rosso a verde";
        }

        cell = DBPromoAgCell.builder()
                .name("Esito")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(esito)
                .build();

        map.put("esito", cell);

        cell = DBPromoAgCell.builder()
                .name("Data Inserimento")
                .editable(false)
                .type(DBPromoCellTypeEnum.DATE.getType())
                .value(storico.getDataInserimento() == null
                        ? ""
                        : dateTimeUtils.toExcelDate(storico.getDataInserimento()))
                .build();

        map.put("dataInserimento", cell);

        cell = DBPromoAgCell.builder()
                .name("Utente")
                .editable(false)
                .type(DBPromoCellTypeEnum.STRING.getType())
                .value(storico.getCodiceUtenteInserimento() == null
                        ? ""
                        : storico.getCodiceUtenteInserimento())
                .build();

        map.put("utente", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }
}