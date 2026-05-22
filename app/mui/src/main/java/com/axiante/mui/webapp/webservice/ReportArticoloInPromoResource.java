package com.axiante.mui.webapp.webservice;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportArticoloEntity;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.ReportArticoloService;
import com.axiante.mui.persistence.service.ArticoloService;
import com.axiante.mui.webapp.business.data.ArticoloInPromoEnum;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.ReportArticoloInPromoRowDataUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.ArticleColumnDef;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/reportArticoloInPromo")
@Slf4j
@Dependent
public class ReportArticoloInPromoResource extends SessionEnabledResource {

    @Inject
    private Instance<ReportArticoloInPromoRowDataUtil> rowDataUtilInstance;

    @Inject
    private Instance<ItemService> itemServiceInstance;

    @Inject
    private Instance<ArticoloService> articoloServiceInstance;

    @Inject
    private Instance<ReportArticoloService> reportArticoloServiceInstance;

    @GET
    @Path("/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDef(@Context HttpServletRequest request) throws Exception {
        return loadColumnDefFromFile("columnDefArticoloInPromo.json", "db_grid_articoloInPromo", null);
    }

    @GET
    @Path("/rowData/{idItem}/{searchType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowData(@PathParam("idItem") String idItem, @PathParam("searchType") String searchType,
                               @Context HttpServletRequest request) throws Exception {
        try {
            Long idArticolo = Long.parseLong(idItem);
            final List<MuiReportArticoloEntity> entities = new ArrayList<>();
            switch (ArticoloInPromoEnum.valueOf(searchType)) {
                case IN_CORSO_FUTURI:
                    entities.addAll(reportArticoloServiceInstance.get().findAllInProgressFutureByIdItem(idArticolo));
                    break;
                case COMPLETATI:
                    entities.addAll(reportArticoloServiceInstance.get().findAllCompletedByIdItem(idArticolo));
                    break;
                default:
                    log.error(String.format("Cannot convert %s to a valid search parameter", searchType));
                    break;
            }
            return Response.ok(rowDataUtilInstance.get().createArticoloInPromoRowData(entities, new Date())).build();
        } catch (Exception ex) {
            log.error(String.format("Error loading rowData for articoli in promo; searchType: %s, idItem: %s",
                    searchType, idItem), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Errore caricamento 'Articoli in Promo'").asJson()).build();
        }
    }

    @GET
    @Path("/selezioneTab/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSelectionColumnDef(@Context HttpServletRequest request) throws Exception {
        return Response
                .ok(new ArticleColumnDef().generateColumnDefByPromoConfiguration(null,
                        getCurrentUser().getHiddenCols(), "db_cerca_articolo_dialog_selezione", null))
                .build();
    }

    @POST
    @Path("/selezioneTab/rowData")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilteredSelectionRowData(PlanningArticleMultiFilterParam params, @Context HttpServletRequest request)
            throws Exception {
        validateFilteredItemsParams(params);
        return Response.ok(rowDataUtilInstance.get().createSelectionRowData(params)).build();
    }

    @GET
    @Path("/copiaIncollaTab/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCopyPasteColumnDef(@Context HttpServletRequest request) throws Exception {
        return loadColumnDefFromFile("columnDefReportArticoloPromoCopiaIncolla.json",
                "db_cercaArticoloDialogCopiaIncollaTab");
    }

    @GET
    @Path("/copiaIncollaTab/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCopyPasteRowData(@Context HttpServletRequest request) throws Exception {
        return Response.ok(rowDataUtilInstance.get().createCopyPasteRowData()).build();
    }

    @GET
    @Path("/copiaIncollaTab/validate/{codiceItem}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCopyPasteValidate(@PathParam("codiceItem") String codiceItem, @Context HttpServletRequest request)
            throws Exception {
        return Response.ok().entity(validateItem(codiceItem).toString()).build();
    }

    private ObjectNode validateItem(String codiceItem) {
        final ItemEntity item = itemServiceInstance.get().findByCode(codiceItem);
        ObjectNode node = JsonUtils.getMapper().createObjectNode();
        node.put("codice", codiceItem);
        if (item == null || !articoloServiceInstance.get().hasAssociationWithIdArticoloAndCodiciGruppo(item.getId(),
                getApplicationUser(null).getGruppi(), PianificazioneSecurityEnum.READ)) {
            if (item == null) {
                log.error(String.format("Cannot find item with code '%s'", codiceItem));
            } else {
                log.warn(String.format("Item with code '%s' not visible for current user '%s'",
                        codiceItem, getApplicationUser(null).getUsermame()));
            }
            node.put("descrizione", item == null ? "elemento non esiste" : "elemento non visibile");
            node.put("isValid", false);
        } else {
            node.put("idItem", item.getId());
            node.put("descrizione", item.getLabel());
            node.put("isValid", true);
        }
        return node;
    }

    private void validateFilteredItemsParams(PlanningArticleMultiFilterParam params) {
        if (params.getIdCompratoreSelected() != null && params.getIdCompratoreSelected().equals(0L)) {
            params.setIdCompratoreSelected(null);
        }
        if (params.getIdFornitoreSelected() != null && params.getIdFornitoreSelected().equals(0L)) {
            params.setIdFornitoreSelected(null);
        }
        if (params.getIdRepartoSelected() != null && params.getIdRepartoSelected().equals(0L)) {
            params.setIdRepartoSelected(null);
        }
        if (params.getIdCategoriaSelected() != null && params.getIdCategoriaSelected().equals(0L)) {
            params.setIdCategoriaSelected(null);
        }
        if (params.getIdGrmSelected() != null && params.getIdGrmSelected().equals(0L)) {
            params.setIdGrmSelected(null);
        }
    }
}
