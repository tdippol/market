package com.axiante.mui.webapp.webservice;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.dto.SottoclasseCountDto;
import com.axiante.mui.dbpromo.persistence.entities.SottoclasseEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.SottoclasseService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.CustomerJourneyRowDataUtil;
import com.axiante.mui.webapp.webservice.util.SottoclasseEntityHelper;
import lombok.extern.slf4j.Slf4j;

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
import java.util.Collections;
import java.util.List;

@Path("/customerJourney")
@Dependent
@Slf4j
public class CustomerJourneyResource extends SessionEnabledResource {
    @Inject
    private Instance<SottoclasseService> sottoclasseServiceInstance;

    @Inject
    private Instance<PianificazioneService> pianificazioneServiceInstance;

    @Inject
    private Instance<CustomerJourneyRowDataUtil> rowDataUtilInstance;

    @Inject
    private Instance<SottoclasseEntityHelper> helperInstance;

    @GET
    @Path("/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDef(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefCustomerJourney.json", "db_grid_customerJourney", contesto);
    }

    @GET
    @Path("/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowData(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        try {
            final List<SottoclasseEntity> entities = sottoclasseServiceInstance.get().findAll();
            final List<SottoclasseCountDto> sottoclassiUsed = pianificazioneServiceInstance.get()
                    .countSottoclassiUsedInPromoInProgress();
            return Response.ok(rowDataUtilInstance.get().createSottoclassiRowData(entities, sottoclassiUsed)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /customerJourney/rowData with contesto '%s'", contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @POST
    @Path("/sottoclasse/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSottoclasse(@PathParam("id") String id, @QueryParam("contesto") String contesto,
                                      String payload, @Context HttpServletRequest request) {
        final UserDTO userDTO = getApplicationUser(contesto);
        if (userDTO == null) {
            log.error(String.format("Cannot find user with contesto '%s'", contesto));
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Impossibile recuperare utente").asJson()).build();
        }
        Long idSottoclasse = null;
        try {
            idSottoclasse = Long.parseLong(id);
        } catch (NumberFormatException ex) {
            log.error(String.format("Illegal idSottoclasse '%s'", id));
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Impossibile recuperare sottoclasse").asJson()).build();
        }
        SottoclasseEntity entity = sottoclasseServiceInstance.get().findById(idSottoclasse);
        if (entity == null) {
            log.error(String.format("Cannot find sottoclasse with id '%s'", id));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore aggiornamento sottoclasse").asJson()).build();
        }
        try {
            helperInstance.get().invokeSetterEntity(entity, JsonUtils.getMapper().readTree(payload));
            entity.setDataScarico(null);
            sottoclasseServiceInstance.get().persistWithAuditLog(entity, userDTO.getUsermame());
            final List<SottoclasseCountDto> sottoclassiUsed = pianificazioneServiceInstance.get()
                    .countSottoclassiUsedInPromoInProgress();
            return Response.ok(rowDataUtilInstance.get().createSottoclassiRowData(Collections.singletonList(entity),
                    sottoclassiUsed)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /customerJourney/sottoclasse/%s with contesto '%s'", id, contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore aggiornamento sottoclasse").asJson()).build();
        }
    }
}
