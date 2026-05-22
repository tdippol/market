package com.axiante.mui.webapp.webservice;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgPianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.CreaPianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.SupportoMediaService;
import com.axiante.mui.validator.service.PianoMediaValidatorService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.business.service.PianoMediaHelperService;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.PianoMediaFactory;
import com.axiante.mui.webapp.webservice.util.PianoMediaPromoRowDataUtil;
import com.axiante.mui.webapp.webservice.util.PianoMediaRowDataUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Path("/pianomedia")
@Dependent
@Slf4j
public class PianoMediaResource extends SessionEnabledResource {

    @Inject
    private Instance<SupportoMediaService> supportoMediaServiceInstance;

    @Inject
    private Instance<CfgPianoMediaService> cfgPianoMediaServiceInstance;

    @Inject
    private Instance<PianoMediaRowDataUtil> pianoMediaRowDataUtilInstance;

    @Inject
    private Instance<CreaPianoMediaService> creaPianoMediaServiceInstance;

    @Inject
    private Instance<CreatePromotionService> createPromotionServiceInstance;

    @Inject
    private Instance<PianoMediaValidatorService> pianoMediaValidatorServiceInstance;

    @Inject
    private Instance<PianoMediaPromoDbpromoService> pianoMediaPromoServiceInstance;

    @Inject
    private Instance<PianoMediaFactory> pianoMediaFactoryInstance;

    @Inject
    private Instance<PianoMediaService> pianoMediaServiceInstance;


    @Inject
    private Instance<PianoMediaHelperService> pianoMediaHelperServiceInstance;

    @Inject
    private Instance<PianoMediaPromoRowDataUtil> pianoMediaPromoRowDataUtilInstance;

    @GET
    @Path("/supporti/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSupportiColumnDef(@QueryParam("contesto") String contesto) {
        return loadColumnDefFromFile("columnDefPianoMediaSupporti.json", "db_pianoMediaSupporti", contesto);
    }

    @GET
    @Path("/inizializzazione/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInizializzazioneColumnDef(@QueryParam("contesto") String contesto) throws Exception {
        return loadColumnDefFromFile("columnDefPianoMediaInizializzazione.json", "db_pianoMediaInizializzazione", contesto);
    }

    @GET
    @Path("/supporti/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSupportiRowData(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        try {
            final List<SupportoMediaEntity> entities = supportoMediaServiceInstance.get().findAll();
            return Response.ok(pianoMediaRowDataUtilInstance.get().createSupportiRowData(entities, null)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /pianomedia/supporti/rowData with contesto '%s'", contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @GET
    @Path("/inizializzazione/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInizializzazioneRowData(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) throws Exception {
        try {
            final List<CfgPianoMediaEntity> entities = cfgPianoMediaServiceInstance.get().findAllAttivi();
            return Response.ok(pianoMediaRowDataUtilInstance.get().createInizializzazioneRowData(entities, null)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /pianomedia/supporti/rowData with contesto '%s'", contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @POST
    @Path("/supporti/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSupporto(@PathParam("id") String id, @QueryParam("contesto") String contesto,
                                   String payload, @Context HttpServletRequest request) {
        try {
            SupportoMediaEntity supporto = supportoMediaServiceInstance.get().findById(Long.parseLong(id));
            if (supporto == null) {
                log.error(String.format("Cannot find SupportoMedia with id '%s'", id));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Errore modifica Supporto Media").asJson()).build();
            }
            supporto = pianoMediaRowDataUtilInstance.get().update(supporto, payload, getCurrentUser().getName());

            supporto = supportoMediaServiceInstance.get().persist(supporto);
            return Response.ok(pianoMediaRowDataUtilInstance.get()
                            .createSupportiRowData(Collections.singletonList(supporto), "Supporto Media aggiornato"))
                    .build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws POST /pianomedia/supporti/rowData; contesto:'%s'; id:'%s'; payload:'%s'",
                    contesto, id, payload), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore modifica Supporto Media").asJson()).build();
        }
    }

    @GET
    @Path("/creaPianoMedia/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreaPianoMediaColumnDef(@QueryParam("contesto") String contesto) {
        return loadColumnDefFromFile("columnDefPianoMediaCrea.json", "db_grid_creaPianoMedia", contesto);
    }

    @GET
    @Path("/creaPianoMedia/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreaPianoMediaRowData(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        try {
            final UserDTO userDTO = getApplicationUser(contesto);
            final String slots = createPromotionServiceInstance.get().findSlotCreaPromoValue();
            final List<CreaPianoMediaEntity> entities = creaPianoMediaServiceInstance.get()
                    .findByUserId(userDTO.getUsermame());
            final String rowData = pianoMediaRowDataUtilInstance.get()
                    .createCreaPianoMediaRowData(slots, entities, userDTO.getUsermame());
            if (rowData == null) {
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message("Spazio di lavoro esaurito"))
                        .build();
            }
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /pianomedia/supporti/rowData with contesto '%s'", contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/creaPianoMedia/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCreaPianoMedia(@QueryParam("contesto") String contesto, String payload,
                                         @Context HttpServletRequest request) {
        log.debug(String.format("Calling ws /pianomedia/creaPianoMedia/update with payload %s", payload));
        try {
            CreaPianoMediaEntity entity = pianoMediaValidatorServiceInstance.get().validateCreaPianoMedia(payload);
            if (entity == null) {
                log.warn(String.format("Cannot validate entity from payload '%s'", payload));
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }
            // Se tutto ok, persisto
            entity = (CreaPianoMediaEntity) creaPianoMediaServiceInstance.get()
                    .persistWithAuditLog(entity, getApplicationUser(contesto).getUsermame());
            // Serialize entity into JSON
            // Preparo valori picklist anni (corrente e successivo)
            final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            final String[] anni = {String.format("%s", currentYear), String.format("%s", currentYear + 1), ""};
            final ObjectNode rowNode = pianoMediaRowDataUtilInstance.get()
                    .createCreaPianoMediaRowNode(entity, anni);
            return Response.ok(JsonUtils.getMapper().writeValueAsString(rowNode)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /pianomedia/creaPianoMedia/update with contesto '%s' and payload '%s'",
                    contesto, payload), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/creaPianoMedia/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCreaPianoMedia(@QueryParam("contesto") String contesto, String payload,
                                       @Context HttpServletRequest request) {
        log.debug(String.format("saveCreaPianoMedia; payload:\n%s", payload));
        try {
            JsonNode node = JsonUtils.getMapper().readTree(payload);
            List<String> messages = createPianoMedia(node, getApplicationUser(contesto), false);
            if (!messages.isEmpty()) {
                ((ObjectNode) node).put("message", String.join("\n", messages));
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(JsonUtils.writeValueAsString(node))
                        .build();
            }
            return Response.ok(JsonUtils.writeValueAsString(node)).build();
        } catch (Exception ex) {
            log.error("Error reading payload for saving single Piano Media", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/creaPianoMedia/saveAll")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveAllCreaPianoMedia(@QueryParam("contesto") String contesto, String payload,
                                          @Context HttpServletRequest request) {
        log.debug(String.format("saveAllCreaPianoMedia; payload\n%s", payload));
        final ArrayList<JsonNode> savedJsonNodeList = new ArrayList<>();
        final ArrayList<JsonNode> tmpJsonNodeList = new ArrayList<>();
        final AtomicReference<Boolean> isPianoMediaSaved = new AtomicReference<>(false);
        JsonNode jsonNodeArray;
        try {
            jsonNodeArray = JsonUtils.getMapper().readTree(payload);
        } catch (final IOException ex) {
            log.error(String.format("Error read JsonNode array from request %s", payload), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        if (!jsonNodeArray.isArray()) {
            log.error("Request does not contain a valid JsonNode Array");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        final UserDTO userDto = getApplicationUser(contesto);
        final List<String> messages = new ArrayList<>();
        jsonNodeArray.forEach(node -> {
            try {
                List<String> msg = createPianoMedia(node, userDto, true);
                if (msg.isEmpty()) {
                    savedJsonNodeList.add(node);
                    isPianoMediaSaved.set(true);
                } else {
                    messages.addAll(msg);
                }
            } catch (Exception ex) {
                log.error(String.format("Error processing object\n%s", node.toString()), ex);
            }
        });
        try {
            final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            if (savedJsonNodeList.size() > 0) {
                objectNode.put("pianiMediaSaved", JsonUtils.writeValueAsString(savedJsonNodeList));
            }
            objectNode.put("isPianoMediaSaved", isPianoMediaSaved.get());
            objectNode.put("pianiMediaError", JsonUtils.writeValueAsString(tmpJsonNodeList));
            if (!messages.isEmpty()) {
                objectNode.put("message", messages.stream().distinct().collect(Collectors.joining("\n")));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(JsonUtils.writeValueAsString(objectNode)).build();
            }
            return Response.ok(JsonUtils.writeValueAsString(objectNode)).build();
        } catch (Exception ex) {
            log.error("Error serializing list of CreaPianoMediaEntity", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/creaPianoMedia/promoRif/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreaPianoMediaPromoRifColumnDef(@QueryParam("contesto") String contesto) {
        return loadColumnDefFromFile("columnDefPianoMediaCreaPromoRif.json", "db_grid_pianoMediaPromoRif", contesto);
    }

    @GET
    @Path("/creaPianoMedia/promoRif/rowData/{slotId}/{promoRif}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreaPianoMediaPromoRifRowData(@PathParam("slotId") String slotId,
                                                     @PathParam("promoRif") String promoRif,
                                                     @QueryParam("contesto") String contesto,
                                                     @Context HttpServletRequest request) {
        try {
            // Tutte le possibili
            List<PianoMediaPromoDbpromoEntity> entities = pianoMediaPromoServiceInstance.get()
                    .findByDataFineGreaterThan(new Date());
            // Recupero CreaPianoMediaEntity da slotId a userId
            final CreaPianoMediaEntity creaPianoMedia = creaPianoMediaServiceInstance.get()
                    .findByUserIdAndSlotId(getCurrentUser().getName(), slotId);
            // Se ho la entity e non sto interrogando per la promoMaster, devo recuperare i codici da "scartare"
            if (creaPianoMedia != null && !"PROMORIF".equalsIgnoreCase(promoRif)) {
                final ArrayList<String> used = new ArrayList<>();
                if (!StringUtils.isBlank(creaPianoMedia.getPromoMaster())) {
                    used.add(creaPianoMedia.getPromoMaster());
                }
                final String promoRifCode = promoRif.toUpperCase();
                switch (promoRifCode) {
                    case "PROMOSECA":
                        if (!StringUtils.isBlank(creaPianoMedia.getPromoSecondaryB())) {
                            used.add(creaPianoMedia.getPromoSecondaryB());
                        }
                        if (!StringUtils.isBlank(creaPianoMedia.getPromoSecondaryC())) {
                            used.add(creaPianoMedia.getPromoSecondaryC());
                        }
                        break;
                    case "PROMOSECB":
                        if (!StringUtils.isBlank(creaPianoMedia.getPromoSecondaryA())) {
                            used.add(creaPianoMedia.getPromoSecondaryA());
                        }
                        if (!StringUtils.isBlank(creaPianoMedia.getPromoSecondaryC())) {
                            used.add(creaPianoMedia.getPromoSecondaryC());
                        }
                        break;
                    case "PROMOSECC":
                        if (!StringUtils.isBlank(creaPianoMedia.getPromoSecondaryA())) {
                            used.add(creaPianoMedia.getPromoSecondaryA());
                        }
                        if (!StringUtils.isBlank(creaPianoMedia.getPromoSecondaryB())) {
                            used.add(creaPianoMedia.getPromoSecondaryB());
                        }
                        break;
                    default:
                        log.error(String.format("Codice riferimento promo '%s' non gestito", promoRif));
                        break;
                }
                entities = entities.stream()
                        .filter(e -> !used.contains(e.getCodicePromozione()))
                        .collect(Collectors.toList());
            }
            return Response.ok(pianoMediaPromoRowDataUtilInstance.get().createPromoRifRowData(entities)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /pianomedia/creaPianoMedia/promoRif/rowData '%s'", contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    private List<String> createPianoMedia(JsonNode node, UserDTO userDTO, boolean massive) {
        final List<String> messages = new ArrayList<>();
        try {
            final CreaPianoMediaEntity creaPianoMedia = pianoMediaValidatorServiceInstance.get()
                    .validateCreaPianoMedia(node);
            if (creaPianoMedia != null) {
                PianoMediaEntity pianoMedia = pianoMediaFactoryInstance.get().build(creaPianoMedia, userDTO.getUsermame());
                if (pianoMedia == null) {
                    log.error("Error creating PianoMedia from CreaPianoMedia");
                    messages.add("Errore creazione Piano Media");
                } else {
                    pianoMedia = (PianoMediaEntity) pianoMediaServiceInstance.get()
                            .persistWithAuditLog(pianoMedia, userDTO.getUsermame());

                    pianoMedia.setConfigurazioniPianoMedia(pianoMediaFactoryInstance.get().createDefaults(pianoMedia, userDTO.getUsermame()));

                    pianoMedia = (PianoMediaEntity) pianoMediaServiceInstance.get().persist(pianoMedia);

                    creaPianoMediaServiceInstance.get().remove(creaPianoMedia);

                    pianoMedia.getConfigurazioniPianoMedia()
                            .forEach(p -> pianoMediaHelperServiceInstance.get().executeChecks(p, userDTO.getUsermame()));
                    if (!massive) {
                        ((ObjectNode) node).put("idPianoMedia", pianoMedia.getId());
                    }
                }
            } else {
                log.error("Cannot validate Piano Media");
                messages.add("Errore validazione Piano Media");
            }
        } catch (Exception ex) {
            log.error("Error creating Piano Media", ex);
            messages.add(ex.getMessage());
        }
        return messages;
    }

    @POST
    @Path("/inizializzazione/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInizializzazione(@PathParam("id") String id, @QueryParam("contesto") String contesto,
                                   String payload, @Context HttpServletRequest request) throws Exception {
        try {
            CfgPianoMediaEntity supporto = cfgPianoMediaServiceInstance.get().findById(Long.parseLong(id));
            if (supporto == null) {
                log.error(String.format("Cannot find initialization param with id '%s'", id));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Errore modifica Inizializzazione Media").asJson()).build();
            }
            supporto = pianoMediaRowDataUtilInstance.get().update(supporto, payload);
            supporto = cfgPianoMediaServiceInstance.get().persist(supporto);
            return Response.ok(pianoMediaRowDataUtilInstance.get()
                            .createInizializzazioneRowData(Collections.singletonList(supporto), "Inizializzazione Media aggiornato"))
                    .build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws POST /pianomedia/inizializzazione/{id}; contesto:'%s'; id:'%s'; payload:'%s'",
                    contesto, id, payload), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore modifica Inizializzazione Media").asJson()).build();
        }
    }

    @GET
    @Path("/visualizza/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVisualizzaColumnDef(@QueryParam("contesto") String contesto) {
        return loadColumnDefFromFile("columnDefPianoMediaVisualizza.json", "db_grid_visualizzaPianoMedia", contesto);
    }

    @GET
    @Path("/visualizza/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVisualizzaRowData(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        try {
            //TODO: implementare filtri utente: dobbiamo creare un tab dei filtri separati per i piani media ? oppure usare i filtri di default ?
            final List<PianoMediaEntity> entities = pianoMediaServiceInstance.get().findNotCancelled();
            return Response.ok(pianoMediaRowDataUtilInstance.get().visualizzaPianoRowData(entities)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /pianomedia/visualizza/rowData with contesto '%s'", contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }


    @GET
    @Path("/stati/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatiPianoMediaColumDef(@QueryParam("contesto") String contesto) {
        //il columnDef è lo stesso della scheda promo in questo caso cambia solo il contenuto
        return loadColumnDefFromFile("columnDefStati.json", "db_grid_visualizzaPianoMedia", contesto);
    }

    @GET
    @Path("/stati/rowData/{pianoMediaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatiPianoMediaRowData(@PathParam("pianoMediaId") String pianoMediaId,
                                              @Context HttpServletRequest request) {
        Long idPianoMedia=-1L;
        try {
            idPianoMedia = Long.parseLong(pianoMediaId);
        } catch (final Exception e) {
            log.error("error converting promozione id to long " + idPianoMedia, e);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione inesistente").asJson()).build();
        }
        final PianoMediaEntity pianoMedia = pianoMediaServiceInstance.get().findById(idPianoMedia);
        if (pianoMedia == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Piano media non trovato").asJson()).build();
        }

        //TODO: accesso dipende dalla promo master ?
//        if (!promoSecurity.isAccessible(testata, getApplicationUser(null))) {
//            return Response.status(Response.Status.PRECONDITION_FAILED)
//                    .entity(new Message("Promozione non abilitata").asJson()).build();
//        }

        try {
            return Response.ok(pianoMediaRowDataUtilInstance.get().createStatiRowData(pianoMedia.getPianoMediaStati())).build();
        } catch (final Exception e) {
            log.error("Error getting rowData stati scheda piano per piano media con id " + pianoMediaId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
