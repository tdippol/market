package com.axiante.mui.webapp.webservice;

import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.CheckSovrapposizioniRowDataUtil;
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
import lombok.extern.slf4j.Slf4j;

@Path("/pianificazionepromo/checksovr")
@Slf4j
@Dependent
public class CheckSovrapposizioniResource extends SessionEnabledResource {

    @Inject
    private Instance<PianificazioneService> pianificazioneServiceInstance;

    @Inject
    private Instance<CheckSovrapposizioniRowDataUtil> checkSovrRowDataUtilInstance;

    @GET
    @Path("/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEscludiCheckSovrColumnDef(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefCheckSovrapposizioni.json", "db_checkSovr", contesto);
    }

    @GET
    @Path("/rowData/{idPromozione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEscludiCheckSovrRowData(@PathParam("idPromozione") String idPromozione,
                                               @QueryParam("contesto") String contesto,
                                               @Context HttpServletRequest request) {
        Long idPromo = null;
        try {
            idPromo = Long.parseLong(idPromozione);
        } catch (Exception ex) {
            log.error("Error converting idPromozione to long " + idPromozione, ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione inesistente").asJson()).build();
        }
        final PromozioneTestataEntity promozione = promoService.findById(idPromo);
        if (!security.isAccessible(promozione, getApplicationUser(contesto))) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione non accessibile").asJson()).build();
        }
        if (promozione == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione non accessibile").asJson()).build();
        }
        try {
            return Response.ok(checkSovrRowDataUtilInstance.get()
                            .createRowData(promozione.getPromozionePianificazioneEntities(), isEditable(promozione)))
                    .build();
        } catch (Exception ex) {
            log.error("Error getting articoli for idPromozione: " + idPromozione, ex);
            return Response.serverError().entity(new Message("Errore recupero articoli!").asJson()).build();
        }
    }

    @POST
    @Path("{idPianificazione}/toggle")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response toggleEscludiCheckSovr(@PathParam("idPianificazione") String idPianificazione,
                                           @QueryParam("contesto") String contesto,
                                           @Context HttpServletRequest request) {
        Long idPianif = null;
        try {
            idPianif = Long.parseLong(idPianificazione);
        } catch (Exception ex) {
            log.error("Error converting idPianificazione to long " + idPianificazione, ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Pianificazione inesistente").asJson()).build();
        }
        final PromozionePianificazioneEntity pianificazione = pianificazioneServiceInstance.get().findById(idPianif);
        if (pianificazione == null
                || !security.isAccessible(pianificazione.getPromozioneTestataEntity(), getApplicationUser(contesto))) {
            log.error(String.format("Cannot find pianificazione with id %d", idPianif));
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Pianificazione non accessibile").asJson()).build();
        }
        try {
            final Boolean oldValue = pianificazione.getEscludiCheckSovr();
            pianificazione.setEscludiCheckSovr(!oldValue);
            final PromozionePianificazioneEntity updatedPianificazione = pianificazioneServiceInstance.get()
                    .persist(pianificazione);
            final String message = String.format("Check sovrapposizioni %s", oldValue ? "disabilitato" : "abilitato");
            final String rowData = checkSovrRowDataUtilInstance.get()
                    .createSingleRow(updatedPianificazione, isEditable(pianificazione.getPromozioneTestataEntity()), message);
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error(String.format("Error toggle 'Escludi Check Sovr' for pianificazione with id: %s", idPianificazione), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore modifica 'Escludi Check Sovrapposizioni'").asJson()).build();
        }
    }

    private boolean isEditable(PromozioneTestataEntity promozione) {
        if (promozione == null) {
            return false;
        }
        final String codiceStatoPromozione = PromoAcl.getCodiceStatoPromozione(promozione);
        return codiceStatoPromozione != null
                && !codiceStatoPromozione.equals(PromoStatusEnum.CANCELLATA_00.getKey())
                && !codiceStatoPromozione.equals(PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey())
                && !codiceStatoPromozione.equals(PromoStatusEnum.PROMOZIONIE_CONCLUSA.getKey());
    }
}
