package com.axiante.mui.webapp.webservice;
import java.math.BigDecimal;
import java.util.ArrayList;
import com.axiante.mui.common.promo.UserFilterUtils;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpContribCompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpParametroEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.*;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.webservice.util.ViewSpecialPromotionUtil;
import lombok.extern.slf4j.Slf4j;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpPianificazioneEntity;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.axiante.mui.dbpromo.persistence.service.MuiSpPianificazioneService;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpContribCompratoreHistEntity;
@Path("/specialpromotion")
@Slf4j
@Dependent
public class GridServiceSPResource extends SessionEnabledResource {

    @Inject
    private MuiSpParametroService muiSpParametroService;

    @Inject
    private transient MuiSpPianificazioneService muiSpPianificazioneService;

    @Inject
    private transient MuiSpContribCompratoreService muiSpContribCompratoreService;

    @Inject
    private transient MuiSpTestataService muiSpTestataService;

    @Inject
    private transient MuiSpStatoService muiSpStatoService;

    @Inject
    private transient MuiSpContribCompratoreHistService muiSpContribCompratoreHistService;

    @Inject
    private transient MuiService muiService;

    @Inject
    private transient ViewSpecialPromotionUtil viewSpecialPromotionUtil;

    private transient UserFilterUtils userFilterUtils = new UserFilterUtils();


    @GET
    @Path("/visualizza/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefVisualizzaSpecialPromotion(@QueryParam("contesto") String contesto,
                                                           @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefVisualizzaSpecialPromotion.json",
                "db_grid_visualizzaSpecialPromotion", contesto);
    }

    @GET
    @Path("/visualizza/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataVisualizzaSpecialPromotion(@QueryParam("contesto") String context,
                                                         @Context HttpServletRequest request) {
        try {
            final UserDTO userDto = getApplicationUser(context);

            final List<MuiSpTestataEntity> testate = muiSpTestataService
                    .findAllForVisualizza().stream()
                    .sorted(Comparator.comparing(MuiSpTestataEntity::getDataInizio))
                    .collect(Collectors.toList());

            return Response.ok(viewSpecialPromotionUtil.createRowData(testate)).build();

        } catch (final Exception ex) {
            log.error("Error getting rowData for VisualizzaSpecialPromotion", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/stati/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefStati(@QueryParam("contesto") String contesto,
                                      @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefStatiSpecialPromotion.json",
                "db_schedaspecialpromotion_stati", contesto);
    }

    @GET
    @Path("/stati/rowData/{idPromozione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataStati(@PathParam("idPromozione") Long idPromozione,
                                    @QueryParam("contesto") String contesto,
                                    @Context HttpServletRequest request) {
        try {
            final UserDTO userDto = getApplicationUser(contesto);

            final List<MuiSpStatoEntity> stati =
                    muiSpStatoService.findAllByPromozioneIdOrderByDataInizio(idPromozione);

            return Response.ok(viewSpecialPromotionUtil.createRowDataStati(stati)).build();

        } catch (Exception ex) {
            log.error("Error getting rowData for Stati SpecialPromotion", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/pubblicazioni/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefPubblicazioni(@QueryParam("contesto") String contesto,
                                              @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefPubblicazioniSpecialPromotion.json",
                "db_schedaspecialpromotion_pubblicazioni", contesto);
    }

    @GET
    @Path("/pubblicazioni/rowData/{idPromozione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataPubblicazioni(@PathParam("idPromozione") Long idPromozione,
                                            @QueryParam("contesto") String contesto,
                                            @Context HttpServletRequest request) {
        try {
            final UserDTO userDto = getApplicationUser(contesto);

            final MuiSpTestataEntity testataHeader = muiSpTestataService.findByIdForHeader(idPromozione);

            final MuiSpStatoEntity statoCorrente = testataHeader == null || testataHeader.getSpStatoEntities() == null
                    ? null
                    : testataHeader.getSpStatoEntities().stream()
                    .filter(st -> st.getDataFineStato() == null)
                    .findFirst()
                    .orElse(null);

            if (testataHeader == null
                    || statoCorrente == null
                    || statoCorrente.getStatoPromozioneEntity() == null
                    || "10".equals(statoCorrente.getStatoPromozioneEntity().getCodiceStato())) {

                return Response.ok(viewSpecialPromotionUtil.createRowDataPubblicazioni(
                        Collections.<MuiSpContribCompratoreEntity>emptyList(),
                        "",
                        "")).build();
            }

            final String descrizioneGruppo =
                    testataHeader.getDescrizioneEstesa() != null && !testataHeader.getDescrizioneEstesa().trim().isEmpty()
                            ? testataHeader.getDescrizioneEstesa()
                            : (testataHeader.getDescrizione() != null ? testataHeader.getDescrizione() : "");

            final String statoGruppo =
                    statoCorrente.getStatoPromozioneEntity().getCodiceStato() + " - "
                            + statoCorrente.getStatoPromozioneEntity().getDescrizione();

            final List<MuiSpContribCompratoreEntity> contributi =
                    muiSpContribCompratoreService.findByPromozioneId(idPromozione);

            return Response.ok(viewSpecialPromotionUtil.createRowDataPubblicazioni(
                    contributi,
                    descrizioneGruppo,
                    statoGruppo)).build();

        } catch (Exception ex) {
            log.error("Error getting rowData for Pubblicazioni SpecialPromotion", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/pubblicazioni/toggle")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response toggleContribuzioneCompratore(@FormParam("idPromozione") Long idPromozione,
                                                  @FormParam("idCompratore") Long idCompratore,
                                                  @QueryParam("contesto") String contesto,
                                                  @Context HttpServletRequest request) {
        try {
            final UserDTO userDto = getApplicationUser(contesto);

            if (idPromozione == null || idCompratore == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Parametri mancanti").build();
            }

            final Integer nuovoValore = muiSpContribCompratoreService.toggleContribuzione(
                    idPromozione,
                    idCompratore,
                    userDto.getUsermame()
            );

            final Map<String, Object> response = new LinkedHashMap<String, Object>();
            response.put("success", true);
            response.put("idPromozione", idPromozione);
            response.put("idCompratore", idCompratore);
            response.put("flContribuzione", nuovoValore);

            return Response.ok(response).build();

        } catch (Exception ex) {
            log.error("Error toggling contribuzione compratore for promozione " + idPromozione + " e compratore " + idCompratore, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/parametri/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefParametri(@QueryParam("contesto") String contesto,
                                          @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefParametriSpecialPromotion.json",
                "db_schedaspecialpromotion_parametri", contesto);
    }


    @POST
    @Path("/pianificazione/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response savePianificazione(String jsonEntity,
                                       @QueryParam("contesto") String contesto,
                                       @Context HttpServletRequest request) {
        try {
            final UserDTO userDto = getApplicationUser(contesto);

            final com.fasterxml.jackson.databind.JsonNode node =
                    com.axiante.mui.common.utility.JsonUtils.getMapper().readTree(jsonEntity);

            final MuiSpPianificazioneEntity entity =
                    com.axiante.mui.common.utility.JsonUtils.getMapper()
                            .treeToValue(node, MuiSpPianificazioneEntity.class);

            muiSpPianificazioneService.saveOrUpdate(entity, userDto.getUsermame());

            com.axiante.mui.common.promo.grid.DbPromoAgCellUtils.putValue(
                    node,
                    "updateMessage",
                    "Aggiornamento effettuato",
                    false
            );

            return Response.ok(node.toString()).build();

        } catch (Exception ex) {
            log.error("Errore durante il salvataggio pianificazione Special Promotion", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Path("/parametri/rowData/{idPromozione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataParametri(@PathParam("idPromozione") Long idPromozione,
                                        @QueryParam("contesto") String contesto,
                                        @Context HttpServletRequest request) {
        try {
            final UserDTO userDto = getApplicationUser(contesto);

            log.info("getRowDataParametri - idPromozione: " + idPromozione);

            final List<MuiSpParametroEntity> parametri =
                    muiSpParametroService.findByPromozioneId(idPromozione);

            final Map<Long, Integer> contribuzioneByCompratore =
                    muiSpContribCompratoreService.findContribuzioneByCompratore(idPromozione);

            final List<MuiSpParametroEntity> parametriFiltrati =
                    filterParametriByUser(parametri, userDto);

            final String json = viewSpecialPromotionUtil.createRowDataParametri(parametriFiltrati, contribuzioneByCompratore);

            log.info("getRowDataParametri - parametri trovati: " + (parametri == null ? 0 : parametri.size()));
            log.info("getRowDataParametri - parametri filtrati: " + (parametriFiltrati == null ? 0 : parametriFiltrati.size()));

            return Response.ok(json).build();

        } catch (Exception ex) {
            log.error("Error getting rowData for Parametri SpecialPromotion", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    @Path("/parametri/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveParametro(String jsonEntity,
                                  @QueryParam("contesto") String contesto,
                                  @Context HttpServletRequest request) {
        try {
            final UserDTO userDto = getApplicationUser(contesto);

            final com.fasterxml.jackson.databind.JsonNode node =
                    com.axiante.mui.common.utility.JsonUtils.getMapper().readTree(jsonEntity);

            if (node instanceof com.fasterxml.jackson.databind.node.ObjectNode) {
                ((com.fasterxml.jackson.databind.node.ObjectNode) node).remove("premioFinale");
            }

            final MuiSpParametroEntity parametro =
                    com.axiante.mui.common.utility.JsonUtils.getMapper()
                            .treeToValue(node, MuiSpParametroEntity.class);

            muiSpParametroService.saveOrUpdateParametro(parametro, userDto.getUsermame());

            com.axiante.mui.common.promo.grid.DbPromoAgCellUtils.putValue(
                    node,
                    "updateMessage",
                    "Aggiornamento effettuato",
                    false
            );

            return Response.ok(node.toString()).build();

        } catch (Exception ex) {
            log.error("Errore durante il salvataggio parametro Special Promotion", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GET
    @Path("/pianificazione/columnDef/v")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefValidazioneVenduto(@QueryParam("contesto") String contesto,
                                                   @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefValidazioneArticoloVenduto.json",
                "db_schedaspecialpromotion_validazione_venduto", contesto);
    }

    @GET
    @Path("/pianificazione/rowData/v/{idPromozione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataValidazioneVenduto(@PathParam("idPromozione") Long idPromozione,
                                                 @QueryParam("contesto") String contesto,
                                                 @Context HttpServletRequest request) {
        try {
            log.info("ENTRATO ROWDATA VENDUTO - idPromozione: " + idPromozione);
            final UserDTO userDto = getApplicationUser(contesto);

            final List<MuiSpPianificazioneEntity> righe =
                    muiSpPianificazioneService.findByPromozioneAndTipo(idPromozione, "V");

            log.info("ROWDATA VENDUTO - righe trovate: " + (righe == null ? 0 : righe.size()));
            final List<MuiSpPianificazioneEntity> righeFiltrate =
                    filterPianificazioneByUser(righe, userDto);

            final Map<Long, Integer> contribuzioneByCompratore =
                    muiSpContribCompratoreService.findContribuzioneByCompratore(idPromozione);

            final String json =
                    viewSpecialPromotionUtil.createRowDataValidazioneArticolo(righeFiltrate,contribuzioneByCompratore);
            return Response.ok(json).build();

        } catch (Exception ex) {
            log.error("Error getting rowData validazione articolo venduto", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Path("/pianificazione/columnDef/c")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefValidazioneConsegnato(@QueryParam("contesto") String contesto,
                                                      @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefValidazioneArticoloConsegnato.json",
                "db_schedaspecialpromotion_validazione_consegnato", contesto);
    }

    @GET
    @Path("/pianificazione/rowData/c/{idPromozione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataValidazioneConsegnato(@PathParam("idPromozione") Long idPromozione,
                                                    @QueryParam("contesto") String contesto,
                                                    @Context HttpServletRequest request) {
        try {
            log.info("ENTRATO ROWDATA CONSEGNATO - idPromozione: " + idPromozione);
            final UserDTO userDto = getApplicationUser(contesto);

            final List<MuiSpPianificazioneEntity> righe =
                    muiSpPianificazioneService.findByPromozioneAndTipo(idPromozione, "C");

            log.info("ROWDATA CONSEGNATO - righe trovate: " + (righe == null ? 0 : righe.size()));
            final List<MuiSpPianificazioneEntity> righeFiltrate =
                    filterPianificazioneByUser(righe, userDto);

            final Map<Long, Integer> contribuzioneByCompratore =
                    muiSpContribCompratoreService.findContribuzioneByCompratore(idPromozione);

            final String json =
                    viewSpecialPromotionUtil.createRowDataValidazioneArticolo(righeFiltrate,contribuzioneByCompratore);
            return Response.ok(json).build();

        } catch (Exception ex) {
            log.error("Error getting rowData validazione articolo consegnato", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

//metodi per il multi-utenza
    private List<String> findCodiciCompratoreVisibili(UserDTO userDto) {
        if (userDto == null) {
            return Collections.emptyList();
        }

        if (userDto.isAdmin()) {
            return null;
        }

        List<String> codiciGruppo = userDto.getGruppi();

        if (codiciGruppo == null || codiciGruppo.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> codiciCompratore =
                muiService.findAllCodiciCompratoreByCodiciGruppo(codiciGruppo);

        return codiciCompratore == null ? Collections.<String>emptyList() : codiciCompratore;
    }

    private List<MuiSpParametroEntity> filterParametriByUser(List<MuiSpParametroEntity> parametri,
                                                             UserDTO userDto) {
        List<String> codiciCompratore = findCodiciCompratoreVisibili(userDto);

        if (codiciCompratore == null) {
            return parametri;
        }

        if (parametri == null || codiciCompratore.isEmpty()) {
            return Collections.emptyList();
        }

        return parametri.stream()
                .filter(p -> p.getCodiceCompratore() != null
                        && codiciCompratore.contains(p.getCodiceCompratore()))
                .collect(Collectors.toList());
    }

    private List<MuiSpPianificazioneEntity> filterPianificazioneByUser(List<MuiSpPianificazioneEntity> righe,
                                                                       UserDTO userDto) {
        List<String> codiciCompratore = findCodiciCompratoreVisibili(userDto);

        if (codiciCompratore == null) {
            return righe;
        }

        if (righe == null || codiciCompratore.isEmpty()) {
            return Collections.emptyList();
        }

        return righe.stream()
                .filter(r -> r.getCodiceCompratore() != null
                        && codiciCompratore.contains(r.getCodiceCompratore()))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/cifra-fissa/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefContributiFornitoriCifraFissa(@QueryParam("contesto") String contesto,
                                                              @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefContributiFornitoriCifraFissa.json",
                "db_grid_contributiFornitoriCifraFissa", contesto);
    }

    @GET
    @Path("/cifra-fissa/rowData/{idPromozione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataContributiFornitoriCifraFissa(@PathParam("idPromozione") Long idPromozione,
                                                            @QueryParam("contesto") String contesto,
                                                            @Context HttpServletRequest request) {
        try {
            final UserDTO userDto = getApplicationUser(contesto);

            final List<MuiSpParametroEntity> parametri =
                    muiSpParametroService.findByPromozioneId(idPromozione);

            final List<MuiSpParametroEntity> parametriFiltrati =
                    filterParametriByUser(parametri, userDto).stream()
                            .filter(p -> "Cifra fissa".equalsIgnoreCase(p.getTipoPremio() == null ? "" : p.getTipoPremio().trim()))
                            .collect(Collectors.toList());

            final String json =
                    viewSpecialPromotionUtil.createRowDataContributiFornitoriCifraFissa(parametriFiltrati);

            return Response.ok(json).build();

        } catch (Exception ex) {
            log.error("Error getting rowData for Contributi Fornitori Cifra Fissa", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Path("/reportRiassuntivo/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefReportRiassuntivoSpecialPromotion(@QueryParam("contesto") String contesto,
                                                                  @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefReportRiassuntivoSpecialPromotion.json",
                "db_grid_reportRiassuntivo", contesto);
    }

    @GET
    @Path("/reportRiassuntivo/rowData/{idPromozione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataReportRiassuntivoSpecialPromotion(@PathParam("idPromozione") Long idPromozione,
                                                                @QueryParam("idCompratore") Long idCompratore,
                                                                @QueryParam("idFornitore") Long idFornitore) {
        try {
            final List<MuiSpParametroEntity> parametri =
                    muiSpParametroService.findByPromozioneId(idPromozione);

            final List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

            BigDecimal totalePremioDaConsegnato = BigDecimal.ZERO;
            BigDecimal totalePremioDaVenduto = BigDecimal.ZERO;
            BigDecimal totaleCifraFissa = BigDecimal.ZERO;
            BigDecimal totalePremioTotale = BigDecimal.ZERO;

            LinkedHashMap<Long, Map<String, Object>> rowsByFornitore =
                    new LinkedHashMap<Long, Map<String, Object>>();

            if (parametri != null) {
                for (MuiSpParametroEntity parametro : parametri) {

                    if (idCompratore != null && !idCompratore.equals(parametro.getIdCompratore())) {
                        continue;
                    }

                    if (idFornitore != null && !idFornitore.equals(parametro.getIdFornitore())) {
                        continue;
                    }

                    if (parametro.getIdFornitore() == null) {
                        continue;
                    }

                    BigDecimal premioDaVenduto = parametro.getPremioFinaleVend() == null
                            ? BigDecimal.ZERO
                            : parametro.getPremioFinaleVend();

                    BigDecimal premioDaConsegnato = parametro.getPremioFinaleCons() == null
                            ? BigDecimal.ZERO
                            : parametro.getPremioFinaleCons();

                    BigDecimal cifraFissa = parametro.getPremioCf() == null
                            ? BigDecimal.ZERO
                            : parametro.getPremioCf();

                    BigDecimal premioTotale = premioDaConsegnato
                            .add(premioDaVenduto)
                            .add(cifraFissa);

                    Map<String, Object> row = rowsByFornitore.get(parametro.getIdFornitore());

                    if (row == null) {
                        row = new LinkedHashMap<String, Object>();
                        row.put("fornitore", buildCell(parametro.getDescrizioneFornitore()));
                        row.put("premioDaConsegnato", buildCell(BigDecimal.ZERO));
                        row.put("premioDaVenduto", buildCell(BigDecimal.ZERO));
                        row.put("cifraFissa", buildCell(BigDecimal.ZERO));
                        row.put("premioTotale", buildCell(BigDecimal.ZERO));
                        row.put("editable", false);

                        rowsByFornitore.put(parametro.getIdFornitore(), row);
                    }

                    BigDecimal currentPremioDaConsegnato =
                            (BigDecimal) ((Map<String, Object>) row.get("premioDaConsegnato")).get("value");

                    BigDecimal currentPremioDaVenduto =
                            (BigDecimal) ((Map<String, Object>) row.get("premioDaVenduto")).get("value");

                    BigDecimal currentCifraFissa =
                            (BigDecimal) ((Map<String, Object>) row.get("cifraFissa")).get("value");

                    BigDecimal currentPremioTotale =
                            (BigDecimal) ((Map<String, Object>) row.get("premioTotale")).get("value");

                    row.put("premioDaConsegnato", buildCell(currentPremioDaConsegnato.add(premioDaConsegnato)));
                    row.put("premioDaVenduto", buildCell(currentPremioDaVenduto.add(premioDaVenduto)));
                    row.put("cifraFissa", buildCell(currentCifraFissa.add(cifraFissa)));
                    row.put("premioTotale", buildCell(currentPremioTotale.add(premioTotale)));

                    totalePremioDaConsegnato = totalePremioDaConsegnato.add(premioDaConsegnato);
                    totalePremioDaVenduto = totalePremioDaVenduto.add(premioDaVenduto);
                    totaleCifraFissa = totaleCifraFissa.add(cifraFissa);
                    totalePremioTotale = totalePremioTotale.add(premioTotale);
                }
            }

            Map<String, Object> totale = new LinkedHashMap<String, Object>();
            totale.put("fornitore", buildCell("Totale Fornitori"));
            totale.put("premioDaConsegnato", buildCell(totalePremioDaConsegnato));
            totale.put("premioDaVenduto", buildCell(totalePremioDaVenduto));
            totale.put("cifraFissa", buildCell(totaleCifraFissa));
            totale.put("premioTotale", buildCell(totalePremioTotale));
            totale.put("editable", false);

            rows.add(totale);
            rows.addAll(rowsByFornitore.values());

            return Response.ok(rows).build();

        } catch (Exception e) {
            log.error("Errore caricamento rowData report riassuntivo Special Promotion", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Map<String, Object> buildCell(Object value) {
        Map<String, Object> cell = new LinkedHashMap<String, Object>();
        cell.put("value", value);
        return cell;
    }


    @GET
    @Path("/pubblicazione/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefPubblicazione(@QueryParam("contesto") String contesto,
                                              @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefPubblicazioneSpecialPromotion.json",
                "db_schedaspecialpromotion_pubblicazione", contesto);
    }

    @GET
    @Path("/pubblicazione/rowData/{idPromozione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataPubblicazione(@PathParam("idPromozione") Long idPromozione,
                                            @QueryParam("contesto") String contesto,
                                            @Context HttpServletRequest request) {
        try {

            final Map<Long, Integer> contribuzioneCorrenteByCompratore =
                    muiSpContribCompratoreService.findContribuzioneByCompratore(idPromozione);

            final UserDTO userDto = getApplicationUser(contesto);

            final List<MuiSpContribCompratoreHistEntity> storico =
                    muiSpContribCompratoreHistService.findByPromozioneId(idPromozione);

            storico.sort((a, b) -> {

                if (a.getDataInserimento() == null
                        || b.getDataInserimento() == null) {
                    return 0;
                }

                return b.getDataInserimento().compareTo(a.getDataInserimento());
            });

            final Map<Long, Date> ultimaDataByCompratore =
                    new HashMap<Long, Date>();

            for (MuiSpContribCompratoreHistEntity row : storico) {

                if (row.getCompratoreEntity() == null
                        || row.getCompratoreEntity().getId() == null
                        || row.getDataInserimento() == null) {
                    continue;
                }

                Long idCompratore = row.getCompratoreEntity().getId();

                Date currentDate = ultimaDataByCompratore.get(idCompratore);

                if (currentDate == null
                        || row.getDataInserimento().after(currentDate)) {

                    ultimaDataByCompratore.put(
                            idCompratore,
                            row.getDataInserimento()
                    );
                }
            }

            return Response.ok(
                    viewSpecialPromotionUtil.createRowDataPubblicazione(
                            storico,
                            contribuzioneCorrenteByCompratore,
                            ultimaDataByCompratore
                    )
            ).build();

        } catch (Exception ex) {
            log.error("Error getting rowData for Pubblicazione SpecialPromotion", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}