package com.axiante.mui.webapp.webservice;

import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;
import com.axiante.mui.dbpromo.persistence.service.ReportBSService;
import com.axiante.mui.webapp.business.data.BuonoInPromoEnum;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.ReportBuonoInPromoRowDataUtil;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/reportBuonoInPromo")
@Slf4j
@Dependent
public class ReportBuonoInPromoResource extends SessionEnabledResource {
    @Inject
    private Instance<ReportBuonoInPromoRowDataUtil> rowDataUtilInstance;

    @Inject
    private Instance<ReportBSService> reportBSServiceInstance;

    @GET
    @Path("/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDef(@Context HttpServletRequest request) throws Exception {
        return loadColumnDefFromFile("columnDefBuonoInPromo.json", "db_grid_buonoInPromo", null);
    }

    @GET
    @Path("/rowData/{codiceBuono}/{searchType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowData(@PathParam("codiceBuono") String codiceBuono, @PathParam("searchType") String searchType,
                               @Context HttpServletRequest request) throws Exception {
        try {
            final List<MuiReportBSEntity> entities = new ArrayList<>();
            switch (BuonoInPromoEnum.valueOf(searchType)) {
                case IN_CORSO:
                    entities.addAll(reportBSServiceInstance.get().findAllInProgressByPrefissoBS(codiceBuono));
                    break;
                case COMPLETATI:
                    entities.addAll(reportBSServiceInstance.get().findAllCompletedByPrefissoBS(codiceBuono));
                    break;
                case FUTURI:
                    entities.addAll(reportBSServiceInstance.get().findAllFuturesByPrefissoBS(codiceBuono));
                    break;
                default:
                    log.error(String.format("Cannot convert %s to a valid search parameter", searchType));
                    break;
            }
            return Response.ok(rowDataUtilInstance.get().createBuonoInPromoRowData(entities)).build();
        } catch (Exception ex) {
            log.error(String.format("Error loading rowData for buono in promo; searchType: %s, idItem: %s",
                    searchType, codiceBuono), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Errore caricamento 'Buono in Promo'").asJson()).build();
        }
    }
}
