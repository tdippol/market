package com.axiante.mui.webapp.webservice;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.service.IniziativeService;
import com.axiante.mui.dbpromo.persistence.service.TotalizzatoriService;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.IniziativeRowDataUtil;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("/iniziative")
@Dependent
@Slf4j
public class IniziativeServiceResource extends SessionEnabledResource {

    @Inject
    private Instance<IniziativeService> iniziativeServiceInstance;

    @Inject
    private Instance<IniziativeRowDataUtil> iniziativeRowDataUtilInstance;

    @Inject
    private Instance<TotalizzatoriService> totalizzatoriServiceInstance;

    @GET
    @Path("/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIniziativeColumnDef(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {

        return loadColumnDefFromFile("columnDefIniziative.json", "db_grid_iniziative", contesto);
    }

    @GET
    @Path("/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIniziativeRowData(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {

        try {
            final List<IniziativaEntity> iniziative = iniziativeServiceInstance.get().findAllNotCancelled();
            return Response.ok(iniziativeRowDataUtilInstance.get().createIniziativeRowData(iniziative)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /iniziative/rowData with contesto '%s'", contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @GET
    @Path("/columnDef/selezionaIniziativa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScegliIniziativaColumnDef(@QueryParam("contesto") String contesto,
                                                 @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefScegliIniziativa.json", "db_grid_selezionaIniziativa", contesto);
    }

    @GET
    @Path("/rowData/selezionaIniziativa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScegliIniziativaRowData(@QueryParam("dataInizio") String dataInizioStr,
                                               @QueryParam("dataFine") String dataFineStr,
                                               @QueryParam("contesto") String contesto,
                                               @Context HttpServletRequest request) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            final DateTimeUtils dateTimeUtils = new DateTimeUtils();
            Date dataInizio = dateTimeUtils.toDate(dataInizioStr, sdf);
            Date dataFine = dateTimeUtils.toDate(dataFineStr, sdf);
            if (dataInizio == null || dataFine == null) {
                final String msg = "Data inizio e data fine non sono impostate";
                log.error(msg);
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
            }
            final List<IniziativaEntity> iniziative = iniziativeServiceInstance.get()
                    .findAllPublishedAndInProgressAndValidDates(dataInizio, dataFine);
            return Response.ok(iniziativeRowDataUtilInstance.get().createIniziativeRowData(iniziative)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /iniziative/selezionaIniziativa/rowData with dataInizio %s and dataFine %s and contesto '%s'",
                    dataInizioStr, dataFineStr, contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @GET
    @Path("/stati/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIniziativaStatiColumnDef(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {

        return loadColumnDefFromFile("columnDefIniziativaStati.json", "db_schedainiziativa_stati", contesto);
    }

    @GET
    @Path("/{idIniziativa}/stati/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIniziativaStatiRowData(@PathParam("idIniziativa") String idIniziativa,
                                              @QueryParam("contesto") String contesto, @Context HttpServletRequest request) {

        try {
            final IniziativaEntity iniziativa = iniziativeServiceInstance.get().findById(Long.parseLong(idIniziativa));
            if (iniziativa == null) {
                log.error(String.format("Unable to find iniziativa with id %s", idIniziativa));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Iniziativa con id %s non trovata", idIniziativa).asJson()).build();
            }
            return Response.ok(iniziativeRowDataUtilInstance.get().createIniziativaStatiRowData(iniziativa.getStati()))
                    .build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /iniziative/%s/stati/rowData with contesto '%s'",
                    idIniziativa, contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @GET
    @Path("/totalizzatori/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIniziativaTotalizzatoriColumnDef(@QueryParam("contesto") String contesto,
                                                        @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefIniziativaTotalizzatori.json",
                "db_schedainiziativa_totalizzatori", contesto);
    }

    @GET
    @Path("/{idIniziativa}/totalizzatori/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIniziativaTotalizzatoriRowData(@PathParam("idIniziativa") String idIniziativa,
                                                      @QueryParam("contesto") String contesto,
                                                      @Context HttpServletRequest request) {
        try {
            final IniziativaEntity iniziativa = iniziativeServiceInstance.get().findById(Long.parseLong(idIniziativa));
            if (iniziativa == null) {
                log.error(String.format("Unable to find iniziativa with id %s", idIniziativa));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Iniziativa con id %s non trovata", idIniziativa).asJson()).build();
            }
            return Response.ok(iniziativeRowDataUtilInstance.get()
                            .createIniziativaTotalizzatoriRowData(totalizzatoriServiceInstance.get()
                                    .findAllWithParentByIdIniziativa(iniziativa.getId())))
                    .build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /iniziative/%s/totalizzatori/rowData with contesto '%s'",
                    idIniziativa, contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }
}
