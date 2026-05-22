package com.axiante.mui.webapp.webservice;

import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.CumulabilitaType;
import com.axiante.mui.dbpromo.business.enumeration.OperazioneCumulabilita;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaBuoniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;
import com.axiante.mui.dbpromo.persistence.service.CumulabilitaEsclusivitaService;
import com.axiante.mui.dbpromo.persistence.service.ReportBSService;
import com.axiante.mui.webapp.utils.security.CumulabilitaSecurity;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.CumulabilitaUtil;
import com.fasterxml.jackson.databind.JsonNode;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/grid")
@Slf4j
@Dependent
public class CumulabilitaServiceResource extends SessionEnabledResource {

    @Inject
    transient private Instance<CumulabilitaUtil> cumulabilitaUtilInstance;

    @Inject
    transient private Instance<CumulabilitaEsclusivitaService> cumulabilitaServiceInstance;

    @Inject
    transient private Instance<CumulabilitaSecurity> cumulabilitaSecurityInstance;

    @Inject
    transient private Instance<ReportBSService> reportBSServiceInstance;

    @GET
    @Path("/cumulabilita/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefCumulabilita(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefCumulabilita.json", "db_cumulabilita", context);
    }

    @GET
    @Path("/cumulabilita/{type}/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataCumulabilita(@PathParam("type") String typeValue, @Context HttpServletRequest request) {
        CumulabilitaType type = CumulabilitaType.fromValue(typeValue);
        try {
            final String rowData = cumulabilitaUtilInstance.get()
                    .createRowData(cumulabilitaServiceInstance.get().findByType(type));
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error("Error getting rowData for <CUMULABILITA>", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/cumulabilita/buoni/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefCumulabilitaBuoni(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefCumulabilitaBuoni.json", "db_buoni", context);
    }

    @GET
    @Path("/cumulabilita/{id}/buoni/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataCumulabilitaBuoni(@PathParam("id") String id, @Context HttpServletRequest request) {
        try {
            final CumulabilitaEsclusivitaEntity entity = cumulabilitaServiceInstance.get().findById(Long.parseLong(id));
            if (entity == null) {
                log.error(String.format("Cumulabilita with id %s not found", id));
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }
            final String rowData = cumulabilitaUtilInstance.get().createRowDataBuoni(entity);
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error("Error getting rowData for <BUONI CUMULABILITA>", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/cumulabilita/buoni/available/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefCumulabilitaBuoniAvailable(@QueryParam("contesto") String context,
                                                           @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefCumulabilitaBuoniAvailable.json", "db_buoni", context);
    }

    @GET
    @Path("/cumulabilita/{id}/buoni/available")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataCumulabilitaBuoniAvailable(@PathParam("id") String id, @Context HttpServletRequest request) {
        // 1. devo cercare tutti i buoni che siano attivi e non gia' inseriti.
        // 2. da 1 devo togliere tutti i buoni che sono, nello stesso periodo, configurati in esclusivita'
        // 3. da 2 devo togliere tutti i buoni che sono, nello stesso periodo, configurati in altre cumulabilita'
        try {
            final CumulabilitaEsclusivitaEntity cumulabilita = cumulabilitaServiceInstance.get().findById(Long.parseLong(id));
            if (cumulabilita == null) {
                log.error(String.format("Cumulabilita with id %s not found", id));
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }
            // 1. mi leggo tutti i buoni disponibili tranne quelli che stanno nella cumulabilita
            final List<String> chiavi = cumulabilita.getCumulabilitaBuoniEntities().stream().map(CumulabilitaBuoniEntity::getChiave).collect(Collectors.toList());
            // 2 + 3. mi leggo tutti i buoni che overlappano
            chiavi.addAll(
                    cumulabilitaServiceInstance.get().findOverlapping(cumulabilita).stream()
                            .map(CumulabilitaEsclusivitaEntity::getCumulabilitaBuoniEntities)
                            .flatMap(Set::stream).map(CumulabilitaBuoniEntity::getPrefissoBS)
                            .collect(Collectors.toList())
            );
            final HashSet<String> s = new HashSet<>(chiavi); // rimuovo i duplicati
            final List<MuiReportBSEntity> buoni = reportBSServiceInstance.get().findAllNotUsedInBetween(
                    new ArrayList<>(s),
                    cumulabilita.getDataInizio(),
                    cumulabilita.getDataFine()
            );

            if (buoni != null) {
                final String rowData = cumulabilitaUtilInstance.get().createRowDataBuoniAvailable(buoni, id);
                return Response.ok(rowData).build();
            } else {
                log.error("Error getting rowData for <BUONI CUMULABILITA>: buoni is null");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception ex) {
            log.error("Error getting rowData for <BUONI CUMULABILITA>", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/cumulabilita/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response publishCumulabilita(@PathParam("id") String id, @Context HttpServletRequest request) {
        try {
            final CumulabilitaEsclusivitaEntity cumulabilita = cumulabilitaServiceInstance.get().findById(Long.parseLong(id));
            if (cumulabilita == null || !cumulabilitaSecurityInstance.get().canPublishCumulabilita(cumulabilita)) {
                log.error(String.format("Cannot publish cumulabilita with id %s", id));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message(String.format("Impossibile pubblicare cumulabilita id %s", id)).asJson())
                        .build();
            }
            cumulabilita.setDataPubblicazione(new Date());
            cumulabilitaServiceInstance.get().persistWithAuditLog(cumulabilita, getCurrentUser().getName());
            cumulabilitaServiceInstance.get().exportCumulabilita(cumulabilita.getId(), getCurrentUser().getName());
            String rowData = cumulabilitaUtilInstance.get().createRowData(cumulabilitaServiceInstance.get()
                    .findByType(cumulabilita.getTipo()));
            String type = cumulabilita.getTipo().getTitle();
            rowData = addCallbackMessage(rowData, String.format("%s %s pubblicata correttamente",
                    type, cumulabilita.getDescrizione()));
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error("Error exporting cumulabilita' with id " + id, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/cumulabilita/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelCumulabilita(@PathParam("id") String id, @Context HttpServletRequest request) {
        try {
            final CumulabilitaEsclusivitaEntity cumulabilita = cumulabilitaServiceInstance.get().findById(Long.parseLong(id));
            if (cumulabilita == null || !cumulabilitaSecurityInstance.get().canCancelCumulabilita(cumulabilita)) {
                log.error(String.format("Cannot cancel cumulabilita with id %s", id));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message(String.format("Impossibile cancellare cumulabilita id %s", id)).asJson())
                        .build();
            }
            cumulabilitaServiceInstance.get().updateCumulabilita(cumulabilita.getId(), OperazioneCumulabilita.DEL_TES,
                    getCurrentUser().getName());
            cumulabilita.setDataAnnullamento(new Date());
            cumulabilitaServiceInstance.get().persistWithAuditLog(cumulabilita, getCurrentUser().getName());
            cumulabilitaServiceInstance.get().exportCumulabilita(cumulabilita.getId(), getCurrentUser().getName());
            String rowData = cumulabilitaUtilInstance.get().createRowData(cumulabilitaServiceInstance.get()
                    .findByType(cumulabilita.getTipo()));
            String type = cumulabilita.getTipo().getTitle();
            rowData = addCallbackMessage(rowData, String.format("%s %s annullata correttamente",
                    type, cumulabilita.getDescrizione()));
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error("Error deleting cumulabilita' with id " + id, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/cumulabilita/{id}/buoni/{idBuono}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCumulabilitaBuono(@PathParam("id") String id, @PathParam("idBuono") String idBuono,
                                            @Context HttpServletRequest request) {
        try {
            Long idCumulabilita = Long.parseLong(id);
            Long idCumulbilitaBuono = Long.parseLong(idBuono);
            CumulabilitaEsclusivitaEntity cumulabilitaEsclusivitaEntity = cumulabilitaServiceInstance.get().findById(idCumulabilita);
            if (cumulabilitaEsclusivitaEntity != null) {
                CumulabilitaBuoniEntity entity = cumulabilitaEsclusivitaEntity.getCumulabilitaBuoniEntities()
                        .stream().filter(e -> e.getId().equals(idCumulbilitaBuono)).findAny().orElse(null);
                if (entity == null) {
                    log.error("Buono with id {} not found in db; maybe it was deleted by some other operator ?", idBuono);
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
                cumulabilitaServiceInstance.get().updateCumulabilita(idCumulbilitaBuono, OperazioneCumulabilita.DEL_DET,
                        getCurrentUser().getName());
                cumulabilitaEsclusivitaEntity.removeCumulabilitaBuoniEntity(entity);
                cumulabilitaServiceInstance.get().persistWithAuditLog(cumulabilitaEsclusivitaEntity, getCurrentUser().getName());
                String rowData = cumulabilitaUtilInstance.get().createRowDataBuoni(cumulabilitaEsclusivitaEntity);
                String type = cumulabilitaEsclusivitaEntity.getTipo().getTitle();
                rowData = addCallbackMessage(rowData, String.format("Buono cancellato correttamente dalla %s %s",
                        type, cumulabilitaEsclusivitaEntity.getDescrizione()));
                return Response.ok(rowData).build();
            } else {
                log.error("Cumulabilita with id {} not found in db; maybe it was deleted by some other operator ?", idCumulabilita);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (NumberFormatException nfe) {
            log.error("Error deleting buono with id {} from cumulabilita' with id {} : one of these values is not a number", idBuono, id);
            log.error("", nfe);
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception ex) {
            log.error("Error deleting buono with id " + idBuono + " from cumulabilita' with id " + id, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String addCallbackMessage(String rowData, String message) {
        try {
            final JsonNode jsonNode = JsonUtils.getMapper().readTree(rowData);
            DbPromoAgCellUtils.putValue(jsonNode, "callbackMessage", message, false);
            return jsonNode.toString();
        } catch (IOException ex) {
            log.error("Error adding message to rowData", ex);
            return rowData;
        }
    }
}
