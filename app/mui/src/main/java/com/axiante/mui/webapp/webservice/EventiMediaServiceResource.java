package com.axiante.mui.webapp.webservice;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.dto.EventoRetailDTO;
import com.axiante.mui.dbpromo.persistence.entities.MuiEventoRetailEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiEventoRetailService;
import com.axiante.mui.webapp.webservice.util.EventiMediaRowdataUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/eventimedia")
@Slf4j
@Dependent
public class EventiMediaServiceResource extends SessionEnabledResource {
    @Inject
    Instance<MuiEventoRetailService> serviceInstance;

    @Inject
    EventiMediaRowdataUtils rowdataUtils;

    @GET
    @Path("/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventiMediaColumnDef(@QueryParam("contesto") String contesto) {
        return loadColumnDefFromFile(
                "columnDefEventiMedia.json", "db_grid_eventi", contesto);
    }

    @GET
    @Path("/{idMacrospazio}/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventiMediaRowdata(@PathParam("idMacrospazio") Long idMacrospazio,
                                          @QueryParam("contesto") String contesto)
            throws JsonProcessingException {
        List<EventoRetailDTO> eventi = idMacrospazio == -1L
                ? serviceInstance.get().getAll()
                : serviceInstance.get().findAllByIdMacrospazio(idMacrospazio);
        return Response.ok().entity(rowdataUtils.createRowData(eventi)).build();
    }

    @DELETE
    @Path("/{idEvento}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEventoMedia(@PathParam("idEvento") Long idEvento) {
        String message;
        try {
            MuiEventoRetailEntity evento = serviceInstance.get().findById(idEvento);
            if (evento != null) {
                serviceInstance.get().remove(evento);
                message = "Evento rimosso con successo.";
                return Response.ok(toJson(message, true)).build();
            } else {
                message = "Evento non trovato.";
                log.warn("Evento Media with id " + idEvento + " not found for deletion.");
                return Response.status(Response.Status.NOT_FOUND).entity(toJson(message, false)).build();
            }
        } catch (Exception e) {
            log.error("Error deleting Evento Media with id " + idEvento, e);
            message = "Errore durante la rimozione dell'evento.";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(toJson(message, false)).build();
        }
    }

    protected String toJson(String string, boolean success) {
        String json = "";
        try {
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("success", success);
            node.put("message", string != null ? string : "");
            json = JsonUtils.getMapper().writeValueAsString(node);
        } catch (JsonProcessingException ex) {
            log.error("Error processing JSON", ex);
            json = "{\"success\":false,\"message\":\"Error processing JSON\"}";
        }
        return json;
    }

}
