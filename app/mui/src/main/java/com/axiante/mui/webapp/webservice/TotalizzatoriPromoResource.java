package com.axiante.mui.webapp.webservice;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTestataService;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.PromozioneTotalizzatoreRowDataUtil;
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

@Path("/totalizzatoriPromo")
@Slf4j
@Dependent
public class TotalizzatoriPromoResource extends SessionEnabledResource{

    @Inject
    private Instance<PromozioneTestataService> promozioneTestataService;

    @Inject
    private Instance<PromozioneTotalizzatoreRowDataUtil> rowDataUtils;
    @GET
    @Path("/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromozioneTotalizzatoriColumnDef(@QueryParam("contesto") String contesto) {
        return loadColumnDefFromFile("columnDefPromozioneTotalizzatori.json", "db_promozione_totalizzatori", contesto);
    }

    @GET
    @Path("/rowData/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromozioneTotalizzatoreRowData(@PathParam("id") int idPianificazione, @QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        try {
            PromozioneTestataEntity testata = promozioneTestataService.get().findById((long) idPianificazione);
            if ( testata != null ){
                return Response.ok(rowDataUtils.get().createTotaliRowData(testata.getTotalizzatori(), null)).build();
            } else {
                return Response.ok().build();
            }
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /pianomedia/supporti/rowData with contesto '%s'", contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

}
