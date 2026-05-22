package com.axiante.mui.webapp.webservice;

import com.axiante.mui.dbpromo.persistence.service.ReportBSService;
import com.axiante.mui.webapp.webservice.util.PrefissoBSUtils;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/grid")
@Slf4j
@Dependent
public class PrefissoBsServiceResource extends SessionEnabledResource {

    @Inject
    transient Instance<ReportBSService> reportBSService;

    @Inject
    private Instance<PrefissoBSUtils> rowDataUtilInstance;

    @GET
    @Path("/prefissobs/columnDef")
    @Produces("application/json")
    public Response getPrefissoBsColumnDef(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
        log.info("getPrefissoBsColumnDef");
        return loadColumnDefFromFile("columnDefPrefissoBs.json", "db_prefissobs", context);
    }

    @GET
    @Path("/prefissobs/rowdata")
    @Produces("application/json")
    public Response getPrefissoBsRowData(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
        log.info("getPrefissoBsRowData");
        return Response.ok(rowDataUtilInstance.get().createBuonoInPromoRowData(reportBSService.get().findInProgress(new Date()))).build();
    }
}
