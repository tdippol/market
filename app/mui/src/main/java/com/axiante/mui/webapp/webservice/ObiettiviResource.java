package com.axiante.mui.webapp.webservice;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.ObiettivoEntity;
import com.axiante.mui.dbpromo.persistence.service.ObiettivoService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTestataService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.ObiettivoEntityHelper;
import com.axiante.mui.webapp.webservice.util.PromozioneObiettiviUtil;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
import java.util.List;

@Path("/obiettivi")
@Slf4j
@Dependent
public class ObiettiviResource extends SessionEnabledResource {
    @Inject
    private Instance<PromozioneTestataService> promozioneTestataServiceInstance;

    @Inject
    private Instance<ObiettivoService> obiettivoService;

    @Inject
    private Instance<PromozioneObiettiviUtil> promozioneObiettiviUtilInstance;

    @Inject
    private Instance<ObiettivoEntityHelper> helperInstance;

    @GET
    @Path("/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObiettiviColumnDef(@QueryParam("contesto") String contesto) {
        return loadColumnDefFromFile("columnDefPromozioneObiettivi.json", "db_promozione_obiettivi", contesto);
    }

    @GET
    @Path("/rowData/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObiettiviRowData(@PathParam("id") Long idPromozione, @QueryParam("contesto") String contesto,
                                        @Context HttpServletRequest request) {
        try {
            final List<ObiettivoEntity> obiettivi = obiettivoService.get().findAllByPromozione(idPromozione);
            return Response.ok(promozioneObiettiviUtilInstance.get().createObiettiviRowData(obiettivi)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /obiettivi/rowData with idPromozione '%d' and contesto '%s'",
                    idPromozione, contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateObiettivo(@PathParam("id") Long idObiettivo, @QueryParam("contesto") String contesto,
                                    String payload, @Context HttpServletRequest request) {
        final UserDTO userDTO = getApplicationUser(contesto);
        if (userDTO == null) {
            log.error(String.format("Cannot find user with contesto '%s'", contesto));
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Impossibile recuperare utente").asJson()).build();
        }
        final ObiettivoEntity obiettivo = obiettivoService.get().findById(idObiettivo);
        if (obiettivo == null) {
            log.error(String.format("Cannot find obiettivo with id '%d'", idObiettivo));
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Impossibile recuperare obiettivo").asJson()).build();
        }
        try {
            helperInstance.get().invokeSetterEntity(obiettivo, JsonUtils.getMapper().readTree(payload));
            obiettivoService.get().persistWithAuditLog(obiettivo, userDTO.getUsermame());
            return Response.ok(promozioneObiettiviUtilInstance.get()
                    .createObiettiviRowData(Collections.singletonList(obiettivo))).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /obiettivi/%d", idObiettivo), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message(String.format("Errore aggiornamento obiettivo con id %s", idObiettivo)).asJson())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteObiettivo(@PathParam("id") Long idObiettivo, @Context HttpServletRequest request) {
        try {
            final ObiettivoEntity obiettivo = obiettivoService.get().findById(idObiettivo);
            if (obiettivo == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new Message(String.format("Obiettivo con id %s non trovato", idObiettivo)).asJson())
                        .build();
            }
            Long idPromozione = obiettivo.getTotalizzatore().getTestata().getId();
            obiettivoService.get().remove(obiettivo);
            final List<ObiettivoEntity> obiettivi = obiettivoService.get().findAllByPromozione(idPromozione);
            return Response.ok(promozioneObiettiviUtilInstance.get().createObiettiviRowData(obiettivi)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /obiettivi/%d", idObiettivo), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message(String.format("Errore rimozione obiettivo con id %s", idObiettivo)).asJson())
                    .build();
        }
    }
}
