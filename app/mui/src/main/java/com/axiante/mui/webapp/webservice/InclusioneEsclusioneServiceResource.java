package com.axiante.mui.webapp.webservice;

import com.axiante.mui.dbpromo.business.enumeration.TipoInclusioneEsclusione;
import com.axiante.mui.dbpromo.persistence.entities.MuiInclusioneEsclusioneEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiInclusioneEsclusioneService;
import com.axiante.mui.webapp.webservice.util.InclusioneEsclusioneRowDataUtil;
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
import java.util.List;

@Path("/inclusioneesclusione")
@Dependent
@Slf4j
public class InclusioneEsclusioneServiceResource extends SessionEnabledResource {
    @Inject
    Instance<MuiInclusioneEsclusioneService> serviceInstance;
    @Inject
    Instance<InclusioneEsclusioneRowDataUtil> rowDataUtilInstance;

    @GET
    @Path("/columnDef/inclusione")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInclusioneColumnDef(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        return getColumnDef(contesto, TipoInclusioneEsclusione.INCLUSIONE);
    }
    @GET
    @Path("/columnDef/esclusione")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEsclusioneColumnDef(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        return getColumnDef(contesto, TipoInclusioneEsclusione.ESCLUSIONE);
    }

    @GET
    @Path("/rowData/{idPromozione}/inclusione")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInclusioneRowData(@PathParam("idPromozione") String promozione, @QueryParam("contesto") String contesto,
                                         @Context HttpServletRequest request) {
        if (promozione == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Parametro idPromozione mancante").build();
        }
        try{
            Long idPromozione = Long.parseLong(promozione);
            return getRowData(idPromozione, TipoInclusioneEsclusione.INCLUSIONE);
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Parametro idPromozione non valido").build();
        }
    }
    @GET
    @Path("/rowData/{idPromozione}/esclusione")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEsclusioneRowData(@PathParam("idPromozione") String promozione, @QueryParam("contesto") String contesto,
                                         @Context HttpServletRequest request) {
        if (promozione == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Parametro idPromozione mancante").build();
        }
        try{
            Long idPromozione = Long.parseLong(promozione);
            return getRowData(idPromozione, TipoInclusioneEsclusione.ESCLUSIONE);
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Parametro idPromozione non valido").build();
        }
    }

    private Response getColumnDef(String contesto, TipoInclusioneEsclusione tipo) {
        String gridName = "db_inclusione";
        if (tipo != null && tipo.equals(TipoInclusioneEsclusione.ESCLUSIONE)) {
            gridName = "db_esclusione";
        }
        return loadColumnDefFromFile("columnDefInclusioneEsclusione.json", gridName, contesto);
    }

    private Response getRowData(Long idPromozione, TipoInclusioneEsclusione tipo) {
        try {
            List<MuiInclusioneEsclusioneEntity> data = serviceInstance.get().findByPromozioneAndTipo(idPromozione, tipo);
            return Response.ok(rowDataUtilInstance.get().createRowData(data)).build();
        } catch (Exception e) {
            log.error("Error getting row data for type {}", tipo.name(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
