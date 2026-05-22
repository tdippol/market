package com.axiante.mui.webapp.webservice;

import com.axiante.mui.dbpromo.persistence.entities.SottoscrizioneEntity;
import com.axiante.mui.dbpromo.persistence.service.SottoscrizioneService;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.SottoscrizioniRowDataUtil;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/sottoscrizioni")
@Slf4j
@Dependent
public class SottoscrizioniResource extends SessionEnabledResource {
    @Inject
    private Instance<SottoscrizioneService> sottoscrizioneServiceInstance;

    @Inject
    private Instance<SottoscrizioniRowDataUtil> sottoscrizioniRowDataUtilInstance;

    @GET
    @Path("/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSottoscrizioniColumnDef(@QueryParam("contesto") String contesto) {
        return loadColumnDefFromFile("columnDefSottoscrizioni.json", "db_sottoscrizioni", contesto);
    }

    @GET
    @Path("/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSottoscrizioniRowData(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        try {
            final List<SottoscrizioneEntity> sottoscrizioni = sottoscrizioneServiceInstance.get().findAll();
            return Response.ok(sottoscrizioniRowDataUtilInstance.get()
                    .createSottoscrizioniRowData(sottoscrizioni)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /sottoscrizioni/rowData with contesto '%s'", contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSottoscrizione(@PathParam("id") Long idSottoscrizione, @Context HttpServletRequest request) {
        try {
            final SottoscrizioneEntity sottoscrizione = sottoscrizioneServiceInstance.get().findById(idSottoscrizione);
            if (sottoscrizione == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new Message(String.format("Sottoscrizione con id %s non trovata", idSottoscrizione)).asJson())
                        .build();
            }
            if (sottoscrizione.getPromozioni() != null && !sottoscrizione.getPromozioni().isEmpty()) {
                log.error("Sottoscrizione con promozioni associate non eliminabile; idSottoscrizione=" + idSottoscrizione);
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Sottoscrizione '%s' con promozioni associate non eliminabile",
                                sottoscrizione.getCodice()).asJson())
                        .build();
            }
            sottoscrizioneServiceInstance.get().remove(sottoscrizione);
            final List<SottoscrizioneEntity> sottoscrizioni = sottoscrizioneServiceInstance.get().findAll();
            return Response.ok(sottoscrizioniRowDataUtilInstance.get()
                    .createSottoscrizioniRowData(sottoscrizioni)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /sottoscrizioni/%d", idSottoscrizione), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message(String.format("Errore rimozione sottoscrizione con id %s", idSottoscrizione)).asJson())
                    .build();
        }
    }
}
