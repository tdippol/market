package com.axiante.mui.webapp.webservice;

import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromozioneFlagEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneDefaultEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoPromoRifatturazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoFatturazioneDefaultService;
import com.axiante.mui.dbpromo.persistence.service.PromoFatturazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.TipoPromoRifatturazioneService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.RifatturazioneUtil;
import com.axiante.mui.webapp.views.content.dbpromo.dtos.CompratoreFornitoreTipologiaDTO;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.PromoFatturazioneEntityHelper;
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
import java.util.stream.Collectors;

@Path("/rifatturazione")
@Slf4j
@Dependent
public class RifatturazioneResource extends SessionEnabledResource {
    @Inject
    private Instance<PromoFatturazioneDefaultService> promoFattDefaultServiceInstance;

    @Inject
    private Instance<PromoFatturazioneService> promoFatturazioneServiceInstance;

    @Inject
    private Instance<RifatturazioneUtil> rifatturazioneUtilInstance;

    @Inject
    private Instance<TipoPromoRifatturazioneService> tipoPromoRifatturazioneServiceInstance;

    @Inject
    private Instance<PromoService> promoServiceInstance;

    @Inject
    private Instance<PromoFatturazioneEntityHelper> promoFatturazioneEntityHelperInstance;

    @Inject
    private Instance<CanalePromozioneService> canalePromozioneServiceInstance;

    @GET
    @Path("/initialload/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInitialLoadColumnDef(@QueryParam("contesto") String contesto) {
        return loadColumnDefFromFile("columnDefRifatturazioneInitialLoad.json",
                "db_grid_rifatturazione_initialload", contesto);
    }

    @GET
    @Path("/initialload/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInitialLoadRowData(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        try {
            List<String> codiciGruppi = getApplicationUser(contesto).getGruppi();
            List<Long> idsCompratori = rifatturazioneUtilInstance.get().compratoriWritableByGruppi(codiciGruppi).stream()
                    .map(CompratoreEntity::getId)
                    .collect(Collectors.toList());
            final List<PromoFatturazioneDefaultEntity> fatturazioniDefaults = promoFattDefaultServiceInstance.get()
                    .findAllByIdsCompratori(idsCompratori);
            return Response.ok(rifatturazioneUtilInstance.get()
                    .createFatturazioniDefaultsRowData(fatturazioniDefaults)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /rifatturazione/initialload/rowData with contesto '%s'", contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @GET
    @Path("/initialload/missingDefaults/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInitialLoadMissingDefaultsColumnDef(@QueryParam("contesto") String contesto) {
        return loadColumnDefFromFile("columnDefRifatturazioneMissingDefaults.json",
                "db_grid_rifatturazione_missingdefaults", contesto);
    }

    @GET
    @Path("/initialload/missingDefaults/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInitialLoadMissingDefaultsRowData(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        try {
            List<String> codiciGruppi = getApplicationUser(contesto).getGruppi();
            List<TipoPromoRifatturazioneEntity> tipologie = tipoPromoRifatturazioneServiceInstance.get().findAll();
            List<CompratoreFornitoreTipologiaDTO> matrix = rifatturazioneUtilInstance.get()
                    .createMatrixForMissingDefaults(codiciGruppi, tipologie);
            return Response.ok(rifatturazioneUtilInstance.get()
                    .createFatturazioniDefaultsMatrixRowData(matrix)).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /rifatturazione/initialload/missingDefaults/rowData with contesto '%s'", contesto), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @GET
    @Path("/singola-attivita/{idPromozione}/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingolaAttivitaColumnDef(@PathParam("idPromozione") Long idPromozione,
                                                @QueryParam("contesto") String contesto,
                                                @Context HttpServletRequest request) {
        PromozioneTestataEntity promozione = promoServiceInstance.get().findById(idPromozione);
        if (promozione == null || promozione.getCanalePromozioneEntity() == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message(String.format("Promozione con id %s non trovata", idPromozione)).asJson()).build();
        }
        Long idCanale = promozione.getCanalePromozioneEntity().getId();
        String columnDef = canalePromozioneServiceInstance.get().countByIdWithTipologiaInitialLoad(idCanale) > 0
                ? "columnDefRifatturazioneSingolaAttivitaInitialLoad.json"
                : "columnDefRifatturazioneSingolaAttivitaNoInitialLoad.json";
        return loadColumnDefFromFile(columnDef, "db_grid_rifatturazione", contesto);
    }

    @GET
    @Path("/singola-attivita/{idPromozione}/compratore/{idCompratore}/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingolaAttivitaRowData(@PathParam("idPromozione") Long idPromozione,
                                              @PathParam("idCompratore") Long idCompratore,
                                              @QueryParam("contesto") String contesto,
                                              @Context HttpServletRequest request) {
        try {
            PromozioneTestataEntity promozione = promoServiceInstance.get().findById(idPromozione);
            if (promozione == null || promozione.getCanalePromozioneEntity() == null) {
                log.error(String.format("Promozione con id %s non trovata o nessun canale associato", idPromozione));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message(String.format("Promozione con id %s non trovata", idPromozione)).asJson()).build();
            }
            Long idCanale = promozione.getCanalePromozioneEntity().getId();
            List<PromoFatturazioneEntity> fatturazioni = promoFatturazioneServiceInstance.get()
                    .findAllByIdCompratoreAndIdPromozione(idCompratore, idPromozione);
            return Response.ok(rifatturazioneUtilInstance.get()
                            .createFatturazioniRowData(fatturazioni, idCanale, promozione.getDataFine(),
                                    retrieveFlagRifatturazioneAttivita(promozione)))
                    .build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /rifatturazione/singola-attivita/%s/compratore/%s/rowData",
                    idPromozione, idCompratore), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero rowData").asJson()).build();
        }
    }

    @POST
    @Path("/singola-attivita/{idFatturazione}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSingolaAttivita(@PathParam("idFatturazione") Long idFatturazione,
                                          @QueryParam("contesto") String contesto, String payload,
                                          @Context HttpServletRequest request) {
        try {
            final UserDTO userDTO = getApplicationUser(contesto);
            if (userDTO == null) {
                log.error(String.format("Cannot find user with contesto '%s'", (contesto != null ? contesto : "null")));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Impossibile recuperare utente").asJson()).build();
            }
            PromoFatturazioneEntity fatturazione = promoFatturazioneServiceInstance.get().findById(idFatturazione);
            if (fatturazione == null) {
                log.error(String.format("Cannot find fatturazione with id '%d'", idFatturazione));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message(String.format("Fatturazione con id %s non trovata", idFatturazione)).asJson()).build();
            }
            if (!canEdit(userDTO.getGruppi(), fatturazione.getCompratore())) {
                log.error(String.format("User '%s' cannot edit with fatturazione id '%d'",
                        userDTO.getUsermame(), idFatturazione));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Non hai i permessi per aggiornare questa fatturazione").asJson()).build();
            }
            promoFatturazioneEntityHelperInstance.get().invokeSetter(fatturazione, JsonUtils.getMapper().readTree(payload));
            fatturazione = (PromoFatturazioneEntity) promoFatturazioneServiceInstance.get()
                    .persistWithAuditLog(fatturazione, userDTO.getUsermame());
            return Response.ok(rifatturazioneUtilInstance.get()
                            .createFatturazioniRowData(Collections.singletonList(fatturazione),
                                    fatturazione.getPromozione().getCanalePromozioneEntity().getId(),
                                    fatturazione.getPromozione().getDataFine(),
                                    retrieveFlagRifatturazioneAttivita(fatturazione.getPromozione())))
                    .build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws /rifatturazione/singola-attivita/%s", idFatturazione), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore aggiornamento").asJson()).build();
        }
    }

    private boolean canEdit(List<String> codiciGruppo, CompratoreEntity compratore) {
        return rifatturazioneUtilInstance.get().compratoriWritableByGruppi(codiciGruppo).stream()
                .anyMatch(c -> c.getId().equals(compratore.getId()));
    }

    private String retrieveFlagRifatturazioneAttivita(PromozioneTestataEntity promozione) {
        return promozione.getPromozioneFlags().stream()
                .filter(f -> DbPromoConstants.FLAG_SCONTO_RIFATTURABILE.equalsIgnoreCase(f.getFlag().getCodice()))
                .map(MuiPromozioneFlagEntity::getValore)
                .findFirst()
                .orElse(null);
    }
}
