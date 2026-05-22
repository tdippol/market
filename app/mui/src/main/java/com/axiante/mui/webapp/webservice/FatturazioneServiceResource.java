package com.axiante.mui.webapp.webservice;

import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoArticoloEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.service.FatturazioneService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.FatturazioneUtil;
import com.axiante.mui.webapp.views.content.enumeration.TipologiaArticoliEnum;
import com.axiante.mui.webapp.webservice.dto.PromozioneCheckDto;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.FatturazioneRowDataUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

@Path("/fatturazione")
@Dependent
@Slf4j
public class FatturazioneServiceResource extends SessionEnabledResource {

    @Inject
    transient private Instance<FatturazioneRowDataUtil> fatturazioneRowDataUtilInstance;

    @Inject
    transient private Instance<FatturazioneUtil> fatturazioneUtilInstance;

    @Inject
    transient private Instance<FatturazioneService> fatturazioneServiceInstance;

    @GET
    @Path("/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFatturazioniColumnDef(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        
        return loadColumnDefFromFile("columnDefFatturazioni.json", "db_grid_fatturazione", contesto);
    }

    @GET
    @Path("/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFatturazioniRowData(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        
        try {
            final List<ContributiPromoEntity> fatturazioni = fatturazioneUtilInstance.get()
                    .readAvailableFatturazioni(getApplicationUser(contesto));
            return Response.ok(fatturazioneRowDataUtilInstance.get().createContributiRowData(fatturazioni)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /fatturazione/rowData with contesto '%s'", contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @GET
    @Path("/articoli/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFatturazioneArticoliColumnDef(@QueryParam("contesto") String contesto,
                                                     @Context HttpServletRequest request) {
        
        return loadColumnDefFromFile("columnDefFatturazioneArticoli.json",
                "db_grid_fatturazione_articoli", contesto);
    }

    @GET
    @Path("/articoli/add/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFatturazioneAddArticoliColumnDef(@QueryParam("contesto") String contesto,
                                                        @Context HttpServletRequest request) {
        
        return loadColumnDefFromFile("columnDefFatturazioneArticoli.json",
                "db_grid_fatturazione_add_articoli", contesto);
    }

    @GET
    @Path("/articoli/rowData/promozione/{idPromozione}/compratore/{codiceCompratore}/fornitore/{codiceFornitore}/{tipologia}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFatturazioneArticoliRowData(@PathParam("idPromozione") String idPromozione,
                                                   @PathParam("codiceCompratore") String codiceCompratore,
                                                   @PathParam("codiceFornitore") String codiceFornitore,
                                                   @PathParam("tipologia") String tipologia,
                                                   @QueryParam("contesto") String contesto,
                                                   @Context HttpServletRequest request) {
        
        try {
            // Validate input
            final TipologiaArticoliEnum tipologiaArticoliEnum = TipologiaArticoliEnum.fromValue(tipologia);
            final PromozioneCheckDto promozioneCheckDto = validateFatturazioneArticoliRequest(idPromozione,
                    codiceCompratore, codiceFornitore, tipologia, tipologiaArticoliEnum, contesto);
            if (promozioneCheckDto.isError()) {
                return Response.status(promozioneCheckDto.getResponseStatus())
                        .entity(new Message(promozioneCheckDto.getErrorMessage()).asJson()).build();
            }

            final List<ItemEntity> items = new ArrayList<>();
            if (TipologiaArticoliEnum.OWN.equals(tipologiaArticoliEnum)) {
                // Solo articoli di propria competenza, filtrati su compratore e fornitore
                items.addAll(fatturazioneUtilInstance.get()
                        .getBillableItems(promozioneCheckDto.getTestata(), codiceCompratore, codiceFornitore));
            } else if (TipologiaArticoliEnum.ALL.equals(tipologiaArticoliEnum)) {
                // Competenza del fornitore, filtrati su compratori in WRITE
                final FatturazioneUtil fatturazioneUtil = fatturazioneUtilInstance.get();
                final List<String> codiciCompratori = fatturazioneUtil
                        .readCompratoriWithWritableItems(promozioneCheckDto.getTestata(), getApplicationUser(contesto)).stream()
                        .map(CompratoreEntity::getCodiceCompratore).collect(Collectors.toList());
                items.addAll(fatturazioneUtil.getBillableItems(promozioneCheckDto.getTestata(), codiciCompratori, codiceFornitore));
            }

            // Escludo articoli già presenti in qualsiasi rata della promozione in oggetto
            final List<Long> billedItemsId = fatturazioneServiceInstance.get()
                    .findAllItemsIdByPromozione(promozioneCheckDto.getTestata());
            items.removeIf(i -> billedItemsId.contains(i.getId()));

            return Response.ok(fatturazioneRowDataUtilInstance.get().createBillableItemsRowData(items)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /fatturazione/articoli/rowData with idPromozione '%s', codiceCompratore '%s', codiceFornitore '%s', tipologia '%s' and contesto '%s'",
                    idPromozione, codiceCompratore, codiceFornitore, tipologia, contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @GET
    @Path("/articoli/rowData/promozione/{idPromozione}/rata/{idRata}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFatturazioneDettaglioArticoliRowData(@PathParam("idPromozione") String idPromozione,
                                                            @PathParam("idRata") String idRata,
                                                            @QueryParam("contesto") String contesto,
                                                            @Context HttpServletRequest request) {
        
        final PromozioneCheckDto promozioneCheckDto = validatePromozioneFromRequest(idPromozione, contesto);
        if (promozioneCheckDto.isError()) {
            return Response.status(promozioneCheckDto.getResponseStatus())
                    .entity(new Message(promozioneCheckDto.getErrorMessage()).asJson()).build();
        }
        try {
            final List<ContributiPromoArticoloEntity> articoli = fatturazioneServiceInstance.get()
                    .findAllContributiArticoliByIdRata(Long.parseLong(idRata));
            return Response.ok(fatturazioneRowDataUtilInstance.get().createFatturazioneArticoliRowData(articoli)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /fatturazione/articoli/rowData/promozione/rata with idPromozione '%s', idRata '%s' and contesto '%s'",
                    idPromozione, idRata, contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    private PromozioneCheckDto validateFatturazioneArticoliRequest(String idPromozione, String codiceCompratore,
                                                                   String codiceFornitore, String tipologia,
                                                                   TipologiaArticoliEnum tipologiaArticoliEnum,
                                                                   String contesto) {
        final PromozioneCheckDto promozioneCheckDto = validatePromozioneFromRequest(idPromozione, contesto);
        if (promozioneCheckDto.isError()) {
            return promozioneCheckDto;
        }
        if (tipologiaArticoliEnum == null) {
            promozioneCheckDto.setError(String.format("Tipologia articoli '%s' non valida", tipologia),
                    Response.Status.PRECONDITION_FAILED);
            return promozioneCheckDto;
        }
        final UserDTO userDto = getApplicationUser(contesto);
        final List<String> compratori = fatturazioneUtilInstance.get()
                .readCompratoriWithWritableItems(promozioneCheckDto.getTestata(), userDto).stream()
                .map(CompratoreEntity::getCodiceCompratore)
                .collect(Collectors.toList());
        if (!compratori.contains(codiceCompratore)) {
            promozioneCheckDto.setError(String.format("Compratore '%s' non compare tra i possibili compratori della promozione",
                    codiceCompratore), Response.Status.PRECONDITION_FAILED);
            return promozioneCheckDto;
        }
        final List<String> fornitori = fatturazioneUtilInstance.get()
                .readFornitoriWithPlannedItems(promozioneCheckDto.getTestata(), codiceCompratore).stream()
                .map(FornitoreEntity::getCodiceFornitore)
                .collect(Collectors.toList());
        if (!fornitori.contains(codiceFornitore)) {
            promozioneCheckDto.setError(String.format("Fornitore '%s' non compare tra i possibili fornitori della promozione",
                    codiceFornitore), Response.Status.PRECONDITION_FAILED);
            return promozioneCheckDto;
        }
        return promozioneCheckDto;
    }
}
