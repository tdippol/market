package com.axiante.mui.webapp.webservice;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.dto.CastellettoMessaggiDTO;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.DescCategoriaBuoniEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.DescCategoriaBuoniService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.PianificazioneEnhancedMessaggiRowDataUtil;
import com.axiante.mui.webapp.webservice.util.PianificazioneMessaggiRowDataUtil;
import lombok.extern.slf4j.Slf4j;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/pianificazionepromo")
@Slf4j
@Dependent
public class PianificazioneMessaggiServiceResource extends SessionEnabledResource {

    @Inject
    private Instance<CastellettoMessaggiService> messaggiServiceInstance;

    @Inject
    private Instance<PianificazioneMessaggiRowDataUtil> messaggiRowDataUtilInstance;

    @Inject
    private Instance<PianificazioneEnhancedMessaggiRowDataUtil> enhancedMessaggiRowDataUtils;

    @Inject
    private Instance<PianificazioneService> pianificazioneServiceInstance;

    @Inject
    private Instance<DescCategoriaBuoniService> categoriaBuoniServiceInstance;

    @GET
    @Path("/messaggi/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessaggiColumnDef(
            @QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        return loadColumnDefFromFile(
                "pianificazione_messaggi_columnDef.json", "pianificazione_messaggi_grid", contesto);
    }

    @GET
    @Path("/messaggi/rowData/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessaggiRowData(
            @PathParam("idPianificazione") String pianificazioneId,
            @QueryParam("contesto") String contesto,
            @Context HttpServletRequest request) {
        Long idPianificazione = null;
        try {
            idPianificazione = Long.parseLong(pianificazioneId);
        } catch (NumberFormatException ex) {
            log.error(String.format("Error parsing %s as long", pianificazioneId), ex);
        }
        if (idPianificazione == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Pianificazione con id %s non esiste", pianificazioneId).asJson())
                    .build();
        }
        try {
            final List<CastellettoMessaggiEntity> messaggi =
                    messaggiServiceInstance.get().findMessaggiByIdPianificazione(idPianificazione);
            final String rowData = messaggiRowDataUtilInstance.get().createMessaggiRowData(messaggi);
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error("Error rowData Pianificazione Messaggi", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore messaggi pianificazione").asJson())
                    .build();
        }
    }

    @GET
    @Path("/messaggi/categoriabuoni/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessaggiCategoriaBuoniColumnDef(@QueryParam("contesto") String contesto,
                                                       @Context HttpServletRequest request) {
        return loadColumnDefFromFile("pianificazione_messaggi_categoriabuoni_columnDef.json",
                "db_categoria_buoni", contesto);
    }

    @GET
    @Path("/messaggi/categoriabuoni/rowData/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessaggiCategoriaBuoniRowData(@PathParam("idPianificazione") String pianificazioneId,
                                                     @QueryParam("contesto") String contesto,
                                                     @Context HttpServletRequest request) {
        Long idPianificazione = null;
        try {
            idPianificazione = Long.parseLong(pianificazioneId);
        } catch (NumberFormatException ex) {
            log.error(String.format("Error parsing %s as long", pianificazioneId), ex);
        }
        if (idPianificazione == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Pianificazione con id %s non esiste", pianificazioneId).asJson())
                    .build();
        }
        PromozionePianificazioneEntity pianificazione = pianificazioneServiceInstance.get().findById(idPianificazione);
        if (pianificazione == null || pianificazione.getPromozioneTestataEntity() == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Pianificazione con id %s non esiste", pianificazioneId).asJson())
                    .build();
        }
        try {
            DescCategoriaBuoniEntity categoriaBuoni = categoriaBuoniServiceInstance.get()
                    .findByIdPromozione(pianificazione.getPromozioneTestataEntity().getId());
            List<DescCategoriaBuoniEntity> list = categoriaBuoni != null
                    ? Collections.singletonList(categoriaBuoni)
                    : Collections.emptyList();
            return Response.ok(messaggiRowDataUtilInstance.get().createCategoriaBuoniRowData(list)).build();
        } catch (Exception ex) {
            log.error("Error rowData Categoria Buoni", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore messaggi pianificazione").asJson()).build();
        }
    }

    @POST
    @Path("/messaggi/categoriabuoni/{idPromozione}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMessaggiCategoriaBuoni(@PathParam("idPromozione") String promozioneId,
                                                 @QueryParam("contesto") String contesto,
                                                 String payload, @Context HttpServletRequest request) {
        Long idPromozione = null;
        try {
            idPromozione = Long.parseLong(promozioneId);
        } catch (NumberFormatException ex) {
            log.error(String.format("Error parsing %s as long", promozioneId), ex);
        }
        if (idPromozione == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione con id %s non esiste", promozioneId).asJson())
                    .build();
        }
        try {
            DescCategoriaBuoniEntity categoriaBuoni = categoriaBuoniServiceInstance.get().findByIdPromozione(idPromozione);
            if (categoriaBuoni == null) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Categoria Buoni con idPromozione %s non esiste", promozioneId).asJson())
                        .build();
            }
            boolean updated = messaggiRowDataUtilInstance.get().updateCategoriaBuoniEntity(categoriaBuoni,
                    JsonUtils.getMapper().readTree(payload));
            String rowData = "";
            if (updated) {
                categoriaBuoni = categoriaBuoniServiceInstance.get().persist(categoriaBuoni);
                rowData = messaggiRowDataUtilInstance.get().createCategoriaBuoniRowData(Collections.singletonList(categoriaBuoni));
            } else {
                rowData = messaggiRowDataUtilInstance.get().createCategoriaBuoniRowData(Collections.singletonList(categoriaBuoni),
                        "Errore modifica Categoria Buoni");
            }
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error(String.format("Error update Categoria Buoni with idPromozione %s and payload %s",
                    promozioneId, payload), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore update messaggi pianificazione").asJson()).build();
        }
    }

    @GET
    @Path("/messaggi/rowData/{codiceDispositivo}/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessaggiDispositivoRowData(@PathParam("codiceDispositivo") String codiceDispositivo,
                                                  @PathParam("idPianificazione") String pianificazioneId,
                                                  @QueryParam("contesto") String contesto,
                                                  @Context HttpServletRequest request) {
        Long idPianificazione = null;
        try {
            idPianificazione = Long.parseLong(pianificazioneId);
        } catch (NumberFormatException ex) {
            log.error(String.format("Error parsing %s as long", pianificazioneId), ex);
        }
        if (idPianificazione == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Pianificazione con id %s non esiste", pianificazioneId).asJson())
                    .build();
        }
        try {
            final List<CastellettoMessaggiDTO> messaggi =
                    messaggiServiceInstance
                            .get()
                            .findEnhancedMessaggiByIdPianificazioneAndCodiceDispositivo(
                                    idPianificazione, codiceDispositivo);
            final String rowData = enhancedMessaggiRowDataUtils.get().createMessaggiRowData(messaggi);
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error("Error rowData Pianificazione Messaggi", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore messaggi pianificazione").asJson())
                    .build();
        }
    }

    @DELETE
    @Path("/messaggi/{idMessaggio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMessaggio(
            @PathParam("idMessaggio") String messaggioId,
            @QueryParam("contesto") String contesto,
            @Context HttpServletRequest request) {
        Long idMessaggio = null;
        try {
            idMessaggio = Long.parseLong(messaggioId);
        } catch (NumberFormatException ex) {
            log.error(String.format("Error parsing %s as long", messaggioId), ex);
        }
        if (idMessaggio == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Messaggio con id %s non esiste", messaggioId).asJson())
                    .build();
        }
        try {
            final CastellettoMessaggiEntity messaggio = messaggiServiceInstance.get().findById(idMessaggio);
            final Long idPianificazione = messaggio.getPianificazione().getId();
            final String dispositivo = messaggio.getCodiceCanaleDispositivo();
            messaggiServiceInstance.get().remove(messaggio);
            List<CastellettoMessaggiDTO> messaggi = Collections.emptyList();
            if (idPianificazione != null) {
                if (dispositivo != null) {
                    messaggi = messaggiServiceInstance.get()
                            .findEnhancedMessaggiByIdPianificazioneAndCodiceDispositivo(idPianificazione, dispositivo);
                } else {
                    messaggi = messaggiServiceInstance.get().findMessaggiByIdPianificazione(idPianificazione).stream()
                            .map(CastellettoMessaggiDTO::new)
                            .collect(Collectors.toList());
                    log.warn("Dispositivo in null: all rows will be retrieved");
                }
            }
            final String rowData = enhancedMessaggiRowDataUtils.get().createMessaggiRowData(messaggi);
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error(
                    String.format("Error deleting Pianificazione Messaggi with id %s", messaggioId), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore eliminazione messaggio").asJson())
                    .build();
        }
    }

    @GET
    @Path("/castelletti/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCastellettiColumnDef(
            @QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        return loadColumnDefFromFile(
                "pianificazione_castelletti_columnDef.json", "pianificazione_castelletti_grid", contesto);
    }

    @GET
    @Path("/castelletti/rowData/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCastellettiRowData(
            @PathParam("idPianificazione") String pianificazioneId,
            @QueryParam("contesto") String contesto,
            @Context HttpServletRequest request) {
        Long idPianificazione = null;
        try {
            idPianificazione = Long.parseLong(pianificazioneId);
        } catch (NumberFormatException ex) {
            log.error(String.format("Error parsing %s as long", pianificazioneId), ex);
        }
        if (idPianificazione == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Pianificazione con id %s non esiste", pianificazioneId).asJson())
                    .build();
        }
        try {
            final List<CastellettoMessaggiEntity> castelletti =
                    messaggiServiceInstance.get().findCastellettiByIdPianificazione(idPianificazione);
            final String rowData =
                    messaggiRowDataUtilInstance.get().createCastellettiRowData(castelletti);
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error("Error rowData Pianificazione Castelletti", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore castelletti pianificazione").asJson())
                    .build();
        }
    }

    @DELETE
    @Path("/castelletto/{idCastelletto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCastelletto(
            @PathParam("idCastelletto") String castellettoId,
            @QueryParam("contesto") String contesto,
            @Context HttpServletRequest request) {
        Long id = null;
        try {
            id = Long.parseLong(castellettoId);
        } catch (NumberFormatException ex) {
            log.error(String.format("Error parsing %s as long", castellettoId), ex);
        }
        if (id == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Messaggio con id %s non esiste", castellettoId).asJson())
                    .build();
        }
        try {
            final CastellettoMessaggiEntity castelletto = messaggiServiceInstance.get().findById(id);
            if (castelletto.getSeqStampa() < 4) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(
                                new Message(
                                        "Impossibile eliminare le righe di default del castelletto %s",
                                        castellettoId)
                                        .asJson())
                        .build();
            }
            // Recupero idPianificazione per poi ricaricare i messaggi
            final Long idPianificazione = castelletto.getPianificazione().getId();
            messaggiServiceInstance.get().remove(castelletto);
            List<CastellettoMessaggiEntity> castelletti =
                    idPianificazione == null
                            ? Collections.emptyList()
                            : messaggiServiceInstance.get().findCastellettiByIdPianificazione(idPianificazione);
            castelletti = fixSequenzaStampa(castelletti);
            final String rowData = messaggiRowDataUtilInstance.get().createMessaggiRowData(castelletti);
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error(
                    String.format("Error deleting Pianificazione Messaggi with id %s", castellettoId), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore eliminazione messaggio").asJson())
                    .build();
        }
    }

    private List<CastellettoMessaggiEntity> fixSequenzaStampa(List<CastellettoMessaggiEntity> lista) {
        if (lista != null) {
            // li ordino
            lista.sort(Comparator.comparing(CastellettoMessaggiEntity::getSeqStampa));
            int seqStampa = 1;
            for (CastellettoMessaggiEntity c : lista) {
                // adesso devo riempire i buchi
                if (c.getSeqStampa() != seqStampa) {
                    c.setSeqStampa(seqStampa);
                    messaggiServiceInstance.get().persist(c);
                }
                ++seqStampa;
            }
        }
        return lista;
    }
}
