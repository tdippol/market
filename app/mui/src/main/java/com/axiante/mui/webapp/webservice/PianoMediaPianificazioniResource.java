package com.axiante.mui.webapp.webservice;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.promo.grid.DBPromoColumnlDef;
import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneAnagraficaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneAnagraficaPianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.PianificazionePianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPianificazioneDettaglioService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoArticoliDbPromoService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.PianificazioneMediaWriteLevelEnum;
import com.axiante.mui.webapp.business.PianoMediaSecurityUtils;
import com.axiante.mui.webapp.business.data.PianoMediaDettaglioDTO;
import com.axiante.mui.webapp.business.service.PianoMediaDettaglioFactory;
import com.axiante.mui.webapp.webservice.dto.PianoMediaDettaglioArticoliDto;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.PianoMediaPianificazioneUtil;
import com.axiante.mui.webapp.webservice.util.PianoMediaPromoRowDataUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Path("/pianomediapianificazioni")
@Dependent
@Slf4j
public class PianoMediaPianificazioniResource extends SessionEnabledResource {

    @Inject
    private Instance<PianoMediaService> pianoMediaServiceInstance;

    @Inject
    private Instance<PianoMediaSecurityUtils> securityUtilsInstance;

    @Inject
    private Instance<PianoMediaDettaglioFactory> pianoMediaDettaglioFactoryInstance;

    @Inject
    private Instance<PianoMediaPromoRowDataUtil> pianoMediaPromoRowDataUtilInstance;

    @Inject
    private Instance<PianoMediaPianificazioneUtil> pianoMediaPianificazioneUtilInstance;

    @Inject
    private Instance<MuiService> muiServiceInstance;

    @Inject
    private ApplicationProperties applicationProperties;

    @Inject
    private Instance<PianoMediaPianificazioneDettaglioService> pianoMediaPianificazioneDettaglioService;

    @Inject
    Instance<PianoMediaPromoArticoliDbPromoService> pianoMediaPromoArticoliDbPromoService;

    @Inject
    Instance<PianificazionePianoMediaService> pianificazionePianoMediaService;

    @Inject
    Instance<PianificazioneAnagraficaPianoMediaService> pianificazioneAnagraficaPianoMediaService;
    public static final String DYNAMIC_COL_RADIX = "media_";

    @GET
    @Path("/pianificazioni/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPianificazioniColumnDef(@QueryParam("contesto") String contesto,
                                               @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefPianoMediaPianificazioni.json",
                "db_pianificazionepianomedia_pianificazione", contesto);
    }

    @GET
    @Path("/{idPianoMedia}/pianificazioni/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDynamicPianificazioniColumnDef(@QueryParam("contesto") String contesto,
                                                      @PathParam("idPianoMedia") String pianoMediaId,
                                                      @Context HttpServletRequest request) {
        Response r = loadColumnDefFromFile("columnDefPianoMediaPianificazioni.json",
                "db_pianificazionepianomedia_pianificazione", contesto);
        try {
            JsonNode staticColumnDef = JsonUtils.getMapper().readTree(r.getEntity().toString());
            if (staticColumnDef != null && staticColumnDef.has("columnDef")) {
                ArrayNode dynamicColumnDef = (ArrayNode) staticColumnDef.get("columnDef");
                final PianoMediaEntity pianoMedia = pianoMediaServiceInstance.get().findById(Long.parseLong(pianoMediaId));
                final Map<Long, List<PianificazionePianoMediaEntity>> grouped = pianoMedia.getConfigurazioniPianoMedia().stream()
                        .collect(Collectors.groupingBy(e -> e.getSupportoMedia().getId()));
                grouped.forEach((idMedia, gantt) -> {
                    // Ordino lista
                    gantt.sort(Comparator.comparing(PianificazionePianoMediaEntity::getDataInizio));
                    // Itero lista per costruire 'text' e aggiungere a 'pianificazionePiani'
                    for (int i = 0; i < gantt.size(); i++) {
                        DBPromoColumnlDef column = DBPromoColumnlDef.builder()
                                .columnType("string")
                                .editable(true)
                                .width(150)
                                .field(String.format("%s%d", DYNAMIC_COL_RADIX, gantt.get(i).getId()))
                                .headerName(String.format("%s #%d", gantt.get(i).getSupportoMedia().getDescrizione(), i + 1))
                                .build();
                        dynamicColumnDef.add(JsonUtils.getMapper().valueToTree(column));
                    }
                });
                final JsonNode newColdef = JsonUtils.getMapper().createObjectNode().set("columnDef", dynamicColumnDef);
                r = Response.ok(newColdef.toString()).build();
            }
        } catch (IOException e) {
            log.error("Errore in decodifica columDef statico", e);
        } catch (NumberFormatException e) {
            log.error("Errore in decodifica idPianoMedia", e);
        }

        return r;
    }

    @GET
    @Path("/{idPianoMedia}/pianificazioni/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPianificazioniRowData(@PathParam("idPianoMedia") String pianoMediaId,
                                             @QueryParam("contesto") String contesto,
                                             @Context HttpServletRequest request) {
        Long idPianoMedia = -1L;
        try {
            idPianoMedia = Long.parseLong(pianoMediaId);
        } catch (Exception ex) {
            log.error(String.format("Error converting pianoMediaId %s to long", pianoMediaId), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Piano media inesistente").asJson()).build();
        }
        try {
            final PianoMediaEntity pianoMedia = pianoMediaServiceInstance.get().findById(idPianoMedia);
            if (pianoMedia == null) {
                final String msg = String.format("Piano media con id %s not esiste", pianoMediaId);
                log.warn(msg);
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message(msg).asJson()).build();
            }
            List<PianoMediaDettaglioDTO> dtoList = pianoMediaDettaglioFactoryInstance.get()
                    .createPianoMediaDettaglioDTO(pianoMedia);
            // Security on view TUTTI | PROPRI
            if (!getCurrentUser().isAdmin() && securityUtilsInstance.get().canViewOwnItemsOnly(getCurrentUser())) {
                final List<String> gruppi = getApplicationUser(contesto).getGruppi();
                if (gruppi == null || gruppi.isEmpty()) {
                    final String msg = String.format("Nessun gruppo associato all'utente '%s'; impossibile determinare compratori e di conseguenza articoli",
                            getCurrentUser().getName());
                    log.error(msg);
                    return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
                }
                final List<String> buyers = muiServiceInstance.get().findAllCodiciCompratoreByCodiciGruppo(gruppi);
                // Tengo solo gli articoli dei compratori associati ai gruppi dell'utente
                dtoList = dtoList.stream()
                        .filter(i -> buyers.contains(i.getCodiceCompratore()))
                        .collect(Collectors.toList());
            }
            final PianificazioneMediaWriteLevelEnum editableLevel =
                    securityUtilsInstance.get().calculateWriteLevel(
                            pianoMedia.getCurrentStatus().getStato(), applicationProperties.getProperty(ApplicationProperties.MEDIA_MODALITA_COMPRATORE,
                                    ApplicationProperties.DEFAULT_MEDIA_MODALITA_COMPRATORE),getCurrentUser());

            final String rowData = pianoMediaPromoRowDataUtilInstance.get()
                    .createPianificazioniDetailsRowData(dtoList, editableLevel);
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws GET /pianomediapianificazioni/%s/pianificazioni/rowData", pianoMediaId), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero pianificazioni piano media").asJson()).build();
        }
    }

    @GET
    @Path("/items/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPianificazioniItemsColumnDef(@QueryParam("contesto") String contesto,
                                                    @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefPianoMediaPianificazioniItems.json",
                "db_add_items_dialog_grid", contesto);
    }

    @GET
    @Path("/{idPianoMedia}/items/{promoRif}/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPianificazioniItemsRowData(@PathParam("idPianoMedia") String pianoMediaId,
                                                  @PathParam("promoRif") String promoRif,
                                                  @QueryParam("contesto") String contesto,
                                                  @QueryParam("subGrm") String subGrm,
                                                  @QueryParam("grm") String grm,
                                                  @QueryParam("categoria") String categoria,
                                                  @QueryParam("reparto") String reparto,
                                                  @QueryParam("buyer") String buyer,
                                                  @QueryParam("dataInizio") String dataInizioString,
                                                  @QueryParam("dataFine") String dataFineString,
                                                  @QueryParam("volantino") String volantino,
                                                  @QueryParam("zona") String zona,
                                                  @Context HttpServletRequest request) {
        Long idPianoMedia = -1L;
        try {
            idPianoMedia = Long.parseLong(pianoMediaId);
        } catch (Exception ex) {
            log.error(String.format("Error converting pianoMediaId %s to long", pianoMediaId), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Piano media inesistente").asJson()).build();
        }
        try {
            final PianoMediaEntity pianoMedia = pianoMediaServiceInstance.get().findById(idPianoMedia);
            if (pianoMedia == null) {
                final String msg = String.format("Piano media con id %s not esiste", pianoMediaId);
                log.warn(msg);
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message(msg).asJson()).build();
            }
            final PianoMediaDettaglioArticoliDto availableItemsDto = pianoMediaPianificazioneUtilInstance.get()
                    .getAvailableItems(pianoMedia, promoRif, getApplicationUser(contesto));
            if (availableItemsDto.isError()) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message(availableItemsDto.getValidationMessage()))
                        .build();
            }
            // Apply filters
            final Date dataInizio = dataInizioString == null
                    ? null
                    : new SimpleDateFormat(DateTimeUtils.ITALIAN_DATE_PATTERN).parse(dataInizioString);
            final Date dataFine = dataFineString == null
                    ? null
                    : new SimpleDateFormat(DateTimeUtils.ITALIAN_DATE_PATTERN).parse(dataFineString);
            final List<PianoMediaDettaglioDTO> dettagli = pianoMediaPianificazioneUtilInstance.get()
                    .appyFilters(availableItemsDto.getDettagli(), subGrm, grm, categoria, reparto, buyer,
                            dataInizio, dataFine, volantino,zona);
            return Response.ok(pianoMediaPromoRowDataUtilInstance.get()
                    .createPianificazioneItemsRowData(dettagli)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws GET /pianomediapianificazioni/%s/items/%s/rowData", pianoMediaId, promoRif), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero pianificazione articoli piano media").asJson()).build();
        }
    }

    @POST
    @Path("/items/{id}/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePianificazione(@PathParam("id") Long id, @QueryParam("contesto") String contesto,
                                         String payload, @Context HttpServletRequest request) {
        /*
        con l'id mi prendo la riga di pianificazione
         */
        Response.Status status = Response.Status.OK;
        String message = "";
        boolean modalitaCompratore = applicationProperties.getProperty(ApplicationProperties.MEDIA_MODALITA_COMPRATORE, ApplicationProperties.DEFAULT_MEDIA_MODALITA_COMPRATORE);
        PianificazioneMediaWriteLevelEnum level = securityUtilsInstance.get().getPianificazioneMediaWriteLevel(getCurrentUser(), modalitaCompratore);
        PianoMediaPianificazioneDettaglioEntity pianificazione = pianoMediaPianificazioneDettaglioService.get().findById(id);
//        PianificazioneMediaWriteLevelEnum level = PianificazioneMediaWriteLevelEnum.NONE;
        if (pianificazione != null) {
            level = getUserWriteModeOnRow(pianificazione, contesto);
            if (level.equals(PianificazioneMediaWriteLevelEnum.NONE)) {
                message = "Livello di accesso utente insufficiente";
                status = Response.Status.PRECONDITION_FAILED;
                log.error(message);
            } else {
                try {
                    final JsonNode node = JsonUtils.getMapper().readTree(payload);
                    final String columnToBeUpdated = DbPromoAgCellUtils.getValue(node.get("columnToBeUpdated"));
                    final String valueToBeUpdated = DbPromoAgCellUtils.getValue(node.get("valueToBeUpdated"));
                    if (columnToBeUpdated == null) {
                        message = "Errore nel payload: campo da aggiornare non presente";
                        status = Response.Status.PRECONDITION_FAILED;
                        log.error(message);
                    } else {
                        if (level.equals(PianificazioneMediaWriteLevelEnum.NOTES)) {
                            switch (columnToBeUpdated) {
                                case "commento":
                                    pianificazione.setNoteCompratore(valueToBeUpdated);
                                    break;
                                case "civetta":
                                case "rankPezzi":
                                case "rankFatturato":
                                    message = String.format("Livello di accesso utente insufficiente al campo %s", columnToBeUpdated);
                                    status = Response.Status.PRECONDITION_FAILED;
                                    log.error(message);
                                    break;
                                default:
                                    message = String.format("Errore nel payload: campo %s non supportato", columnToBeUpdated);
                                    status = Response.Status.PRECONDITION_FAILED;
                                    log.error(message);
                            }
                        } else {
                            switch (columnToBeUpdated) {
                                case "commento":
                                    pianificazione.setNoteCompratore(valueToBeUpdated);
                                    break;
                                case "civetta":
                                    pianificazione.setCivetta(Boolean.valueOf(valueToBeUpdated));
                                    break;
                                case "rankPezzi":
                                    pianificazione.setPezzoRank(nullableIntegerValue(valueToBeUpdated));
                                    break;
                                case "rankFatturato":
                                    pianificazione.setFatturatoRank(nullableIntegerValue(valueToBeUpdated));
                                    break;
                                default:
                                    if (columnToBeUpdated.startsWith(DYNAMIC_COL_RADIX)) {
                                        String codicePianificazioneMedia = columnToBeUpdated.substring(DYNAMIC_COL_RADIX.length());
                                        try {
                                            Long idPianficazioneMedia = Long.parseLong(codicePianificazioneMedia);
                                            PianificazionePianoMediaEntity pianificazionePiano = pianificazionePianoMediaService.get().findById(idPianficazioneMedia);
                                            if (pianificazionePiano != null) {
                                                PianificazioneAnagraficaPianoMediaEntity anagraficaPianoMediaEntity = pianificazioneAnagraficaPianoMediaService.get().findByPianificazioneDettaglioAndPianificazioneMedia(pianificazione, pianificazionePiano);
                                                anagraficaPianoMediaEntity.setAttivo(Boolean.valueOf(valueToBeUpdated));
                                                pianificazioneAnagraficaPianoMediaService.get().persist(anagraficaPianoMediaEntity);
                                            } else {
                                                message = String.format("Pianificazione media non trovata %s", columnToBeUpdated);
                                                status = Response.Status.PRECONDITION_FAILED;
                                                log.error(message);
                                            }
                                        } catch (NumberFormatException e) {
                                            log.error("Errore in decodifica idPianificazioneMedia", e);
                                            message = String.format("Errore nel payload: campo %s non supportato", columnToBeUpdated);
                                            status = Response.Status.PRECONDITION_FAILED;
                                        } catch (IllegalArgumentException e) {
                                            message = String.format("Errore database: sono presenti piu' entries di configurazione anagrafica per il piano media %d", pianificazione.getPianoMedia().getId());
                                            log.error(message, e);
                                            status = Response.Status.PRECONDITION_FAILED;
                                        }
                                    } else {
                                        message = String.format("Errore nel payload: campo %s non supportato", columnToBeUpdated);
                                        status = Response.Status.PRECONDITION_FAILED;
                                        log.error(message);
                                    }
                            }
                        }
                    }
                } catch (Exception e) {
                    message = "Errore nella lettura del payload";
//                    errore = true;
                    log.error(message, e);
                }
            }
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero pianificazione articoli piano media: contattare l'assistenza").asJson()).build();
        }

        if (Response.Status.OK.equals(status)) {
            try {
                pianificazione = pianoMediaPianificazioneDettaglioService.get().persist(pianificazione);
                message = "Pianificazione articolo aggiornata correttamente";
            } catch (Exception e) {
                log.error(message, e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(new Message("Errore nell'aggiornamento della pianificazione, contattare l'assistenza").asJson())
                        .build();
            }
        } else {
            final List<PianoMediaDettaglioDTO> dtoList = pianoMediaDettaglioFactoryInstance.get()
                    .createPianoMediaDettaglioDTO(pianificazione.getPianoMedia(), pianificazione);
            final ObjectNode responseNode = JsonUtils.getMapper().createObjectNode();
            final boolean editable =  PianificazioneMediaWriteLevelEnum.ALL.equals(level);
            final boolean editNotes = PianificazioneMediaWriteLevelEnum.NOTES.equals(level);

            DbPromoAgCellUtils.putValue(responseNode, "message", message, false);
            responseNode.set("originalRow", pianoMediaPromoRowDataUtilInstance.get()
                    .createPianificazioneDetailsRowNode(dtoList.get(0), editable, editNotes));
            return Response.status(status).entity(responseNode.toString()).build();
        }
        return Response.status(status).entity(new Message(message).asJson()).build();
    }

    @DELETE
    @Path("/items/{id}/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePianificazione(@PathParam("id") Long id, @QueryParam("contesto") String contesto,
                                         @Context HttpServletRequest request) {
        /*
        con l'id mi prendo la riga di pianificazione
         */
        boolean errore = false;
        String message = "";
        final PianoMediaPianificazioneDettaglioEntity pianificazione = pianoMediaPianificazioneDettaglioService.get().findById(id);
        Response.Status status = Response.Status.OK;
        if (pianificazione != null) {
            final PianificazioneMediaWriteLevelEnum writeModeOnRow = getUserWriteModeOnRow(pianificazione, contesto);
            if (writeModeOnRow.equals(PianificazioneMediaWriteLevelEnum.NONE) || writeModeOnRow.equals(PianificazioneMediaWriteLevelEnum.NOTES)) {
                status = Response.Status.PRECONDITION_FAILED;
                message = "Utente non autorizzato ad eliminare la pianificazione";
            } else {
                try {
                    pianoMediaPianificazioneDettaglioService.get().remove(pianificazione);
                    List<PianoMediaDettaglioDTO> dtoList = pianoMediaDettaglioFactoryInstance.get()
                            .createPianoMediaDettaglioDTO(pianificazione.getPianoMedia());
                    // Security on view TUTTI | PROPRI
                    if (!getCurrentUser().isAdmin() && securityUtilsInstance.get().canViewOwnItemsOnly(getCurrentUser())) {
                        final List<String> gruppi = getApplicationUser(contesto).getGruppi();
                        if (gruppi == null || gruppi.isEmpty()) {
                            final String msg = String.format("Nessun gruppo associato all'utente '%s'; impossibile determinare compratori e di conseguenza articoli",
                                    getCurrentUser().getName());
                            log.error(msg);
                            return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
                        }
                        final List<String> buyers = muiServiceInstance.get().findAllCodiciCompratoreByCodiciGruppo(gruppi);
                        // Tengo solo gli articoli dei compratori associati ai gruppi dell'utente
                        dtoList = dtoList.stream()
                                .filter(i -> buyers.contains(i.getCodiceCompratore()))
                                .collect(Collectors.toList());
                    }
                    // Recupero modalita compratore
                    boolean modalitaCompratore = applicationProperties.getProperty(ApplicationProperties.MEDIA_MODALITA_COMPRATORE,
                            ApplicationProperties.DEFAULT_MEDIA_MODALITA_COMPRATORE);
                    // Reload rowData
                    final PianificazioneMediaWriteLevelEnum editableLevel =
                            securityUtilsInstance.get().calculateWriteLevel(
                                    pianificazione.getPianoMedia().getCurrentStatus().getStato(), applicationProperties.getProperty(ApplicationProperties.MEDIA_MODALITA_COMPRATORE,
                                            ApplicationProperties.DEFAULT_MEDIA_MODALITA_COMPRATORE),getCurrentUser());


                    final String rowData = pianoMediaPromoRowDataUtilInstance.get()
                            .createPianificazioniDetailsRowData(dtoList, editableLevel);
                    return Response.ok(rowData).build();
                } catch (Exception e) {
                    message = "Errore nella cancellazione della pianificazione";
                    status = Response.Status.PRECONDITION_FAILED;
                    log.error(message, e);
                }
            }
            return Response.status(status).entity(new Message(message).asJson()).build();
        } else {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message("Pianificazione non trovata").asJson()).build();
        }
    }

    @GET
    @Path("/pianificazioni/zoneDiff/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPianificazioneZoneDiffColumnDef(@QueryParam("contesto") String contesto,
                                                       @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefPianificazioniZoneDiff.json", "db_zoneDiff", contesto);
    }

    @GET
    @Path("/pianificazioni/{id}/zoneDiff/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPianificazioneZoneDiffRowData(@PathParam("id") Long id,
                                                     @QueryParam("contesto") String contesto,
                                                     @Context HttpServletRequest request) {
        // Recupero riga pianificazione dato id
        final PianoMediaPianificazioneDettaglioEntity pianificazione = pianoMediaPianificazioneDettaglioService.get()
                .findById(id);
        if (pianificazione == null) {
            final String msg = String.format("Pianificazione con id '%d' non esiste", id);
            log.error(msg);
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
        }
        // Recupero dettaglio da pianificazione
        final PianoMediaPromoArticoliDbPromoEntity articolo = pianoMediaPromoArticoliDbPromoService.get()
                .findByCodiceItemAndCodicePromoAndTipoItem(pianificazione.getCodiceItem(),
                        pianificazione.getCodicePromozione(), pianificazione.getTipoItem());
        if (articolo == null) {
            final String msg = String.format("Dettaglio pianificazione con id '%d' non esiste", id);
            log.error(msg);
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
        }
        final PianoMediaDettaglioDTO dto = pianoMediaDettaglioFactoryInstance.get()
                .createPianoMediaDettaglioDTO(articolo);
        return Response.ok(pianoMediaPromoRowDataUtilInstance.get()
                        .createDettaglioArticoloDifferenziatoRowData(dto.getDettaglioArticolo()))
                .build();
    }

    @GET
    @Path("/{idPianoMedia}/statocompratori/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatoCompratoriColumnDef(@QueryParam("contesto") String contesto,
                                                @PathParam("idPianoMedia") String pianoMediaId,
                                                @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefPianoMediaPianificazioniStatoCompratori.json",
                "db_pianificazionepianomedia_statocompratori", contesto);
    }

    @GET
    @Path("/{idPianoMedia}/statocompratori/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatoCompratoriRowData(@PathParam("idPianoMedia") String pianoMediaId,
                                              @QueryParam("contesto") String contesto,
                                              @Context HttpServletRequest request) {
        // Controllo accesso
        final boolean modalitaCompratore = applicationProperties.getProperty(ApplicationProperties.MEDIA_MODALITA_COMPRATORE,
                ApplicationProperties.DEFAULT_MEDIA_MODALITA_COMPRATORE);
        if (!getCurrentUser().isAdmin() && !securityUtilsInstance.get().canAccessCheckBuyers(getCurrentUser(), modalitaCompratore)) {
            log.error(String.format("Error accessing 'Stato Compratori' for user %s", getCurrentUser().getName()));
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("L'utente non ha il permesso di accedere allo stato compratori").asJson())
                    .build();
        }
        Long idPianoMedia = -1L;
        try {
            idPianoMedia = Long.parseLong(pianoMediaId);
        } catch (Exception ex) {
            log.error(String.format("Error converting pianoMediaId %s to long", pianoMediaId), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Piano media inesistente").asJson()).build();
        }
        try {
            final PianoMediaEntity pianoMedia = pianoMediaServiceInstance.get().findById(idPianoMedia);
            if (pianoMedia == null) {
                final String msg = String.format("Piano media con id %s not esiste", pianoMediaId);
                log.warn(msg);
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message(msg).asJson()).build();
            }
            List<PianoMediaDettaglioDTO> dtoList = pianoMediaDettaglioFactoryInstance.get()
                    .createPianoMediaDettaglioDTO(pianoMedia);
            // Security on view TUTTI | PROPRI
            if (!getCurrentUser().isAdmin() && securityUtilsInstance.get().canViewOwnItemsOnly(getCurrentUser())) {
                final List<String> gruppi = getApplicationUser(contesto).getGruppi();
                if (gruppi == null || gruppi.isEmpty()) {
                    final String msg = String.format("Nessun gruppo associato all'utente '%s'; impossibile determinare compratori e di conseguenza articoli",
                            getCurrentUser().getName());
                    log.error(msg);
                    return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
                }
                final List<String> buyers = muiServiceInstance.get().findAllCodiciCompratoreByCodiciGruppo(gruppi);
                // Tengo solo gli articoli dei compratori associati ai gruppi dell'utente
                dtoList = dtoList.stream()
                        .filter(i -> buyers.contains(i.getCodiceCompratore()))
                        .collect(Collectors.toList());
            }
            final Map<String, List<PianoMediaPianificazioneDettaglioEntity>> byBuyers = dtoList.stream()
                    .filter(p->p.getCodiceCompratore() != null)
                    .collect(Collectors.groupingBy(PianoMediaDettaglioDTO::getCodiceCompratore,
                            Collectors.mapping(PianoMediaDettaglioDTO::getDettaglioPianificazione, Collectors.toList())));
            final String rowData = pianoMediaPromoRowDataUtilInstance.get().createStatoCompratoriRowData(byBuyers);
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws GET /pianomediapianificazioni/%s/statocompratori/rowData",
                    pianoMediaId), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero stato compratori").asJson()).build();
        }
    }

    /**
     * Dato l'utente corrente e la riga di pianificazione, restituisce il livello di edit
     *
     * @param e        riga di pianificazione
     * @param contesto contesto di esecuzione
     * @return livello di edit
     */
    private PianificazioneMediaWriteLevelEnum getUserWriteModeOnRow(@NonNull PianoMediaPianificazioneDettaglioEntity e,
                                                                    @NonNull String contesto) {
        if (getCurrentUser().isAdmin()) {
            return PianificazioneMediaWriteLevelEnum.ALL;
        }
        final boolean modalitaCompratore = applicationProperties.getProperty(ApplicationProperties.MEDIA_MODALITA_COMPRATORE, ApplicationProperties.DEFAULT_MEDIA_MODALITA_COMPRATORE);
        PianificazioneMediaWriteLevelEnum level = securityUtilsInstance.get().getPianificazioneMediaWriteLevel(getCurrentUser(), modalitaCompratore);
        if (level.equals(PianificazioneMediaWriteLevelEnum.NONE)) {
            return PianificazioneMediaWriteLevelEnum.NONE;
        }

        if (securityUtilsInstance.get().canViewOwnItemsOnly(getCurrentUser())) {
            // puo' vedere solo i propri articoli
//      1. prendi i gruppi dall'utente
            final List<String> gruppi = getApplicationUser(contesto).getGruppi();
            if (gruppi == null || gruppi.isEmpty()) {
                return PianificazioneMediaWriteLevelEnum.NONE;
            }
//      2. prendi i compratori associati ai gruppi
            final List<String> buyers = muiServiceInstance.get().findAllCodiciCompratoreByCodiciGruppo(gruppi);
            if (buyers == null || buyers.isEmpty()) {
                return PianificazioneMediaWriteLevelEnum.NONE;
            }
//      3. prendi l'articolo
            final PianoMediaPromoArticoliDbPromoEntity articolo = pianoMediaPromoArticoliDbPromoService.get()
                    .findByCodiceItemAndCodicePromoAndTipoItem(e.getCodiceItem(), e.getCodicePromozione(), e.getTipoItem());
//        4. controlla che il gruppo abbia accesso al compratore
            if (articolo != null && buyers.contains(articolo.getCodiceCompratore())) {
                return level;
            }
            return PianificazioneMediaWriteLevelEnum.NONE;
        }
        // puo' vedere tutto.
        return level;

    }

    private Integer nullableIntegerValue(String value) {
        return value != null ? Integer.valueOf(value) : null;
    }


}
