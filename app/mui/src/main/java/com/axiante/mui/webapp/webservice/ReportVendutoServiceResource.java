package com.axiante.mui.webapp.webservice;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportVendutoEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.ReportVendutoService;
import com.axiante.mui.webapp.utils.PianificazioneSecurityUtil;
import com.axiante.mui.webapp.utils.security.PromoSecurity;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.ReportVendutoRowDataUtil;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Path("/reportVenduto")
@Slf4j
@Dependent
public class ReportVendutoServiceResource extends SessionEnabledResource {

    @Inject
    private Instance<PromoService> promoServiceInstance;

    @Inject
    private Instance<ReportVendutoService> reportVendutoServiceInstance;

    @Inject
    private Instance<PianificazioneSecurityUtil> securityUtilInstance;

    @Inject
    private Instance<PromoSecurity> promoSecurityInstance;

    @Inject
    private Instance<ReportVendutoRowDataUtil> rowDataUtilInstance;

    @GET
    @Path("/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReportVendutoColumnDef(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefReportVenduto.json", "db_grid_reportVenduto", contesto);
    }

    @GET
    @Path("/rowData/{idPromozione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReportVendutoRowData(@PathParam("idPromozione") String idPromozione,
                                            @QueryParam("contesto") String contesto,
                                            @Context HttpServletRequest request) {
        if (idPromozione == null || idPromozione.trim().isEmpty() || !StringUtils.isNumeric(idPromozione)) {
            log.error(String.format("Illegal idPromozione %s", idPromozione));
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Impossibile recuperare promozione").asJson()).build();
        }
        try {
            final PromozioneTestataEntity promozione = promoServiceInstance.get().findById(Long.parseLong(idPromozione));
            if (promozione == null
                    || (!isUserAdmin() && !promoSecurityInstance.get().isAccessible(promozione, getApplicationUser(contesto)))) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Promozione non accessibile").asJson()).build();
            }
            Stream<ReportVendutoEntity> stream = reportVendutoServiceInstance.get()
                    .findAllByIdPromozione(promozione.getId()).stream();
            if (!isUserAdmin()) {
                final List<String> buyerCodes = securityUtilInstance.get()
                        .getReadableCompratori(promozione, getApplicationUser(contesto).getGruppi());
                stream = stream.filter(Objects::nonNull)
                        .filter(e -> e.getId().getCodiceCompratore() != null)
                        .filter(e -> buyerCodes.contains(e.getId().getCodiceCompratore()));
            }
            return Response.ok(rowDataUtilInstance.get().createRowData(stream.collect(Collectors.toList()))).build();
        } catch (Exception ex) {
            return Response.serverError().entity(new Message("Errore recupero report venduto").asJson()).build();
        }
    }
}
