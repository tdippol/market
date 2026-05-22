package com.axiante.mui.webapp.webservice;

import com.axiante.mui.dbpromo.persistence.entities.PianificazioneAnagraficaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaControlliEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazionePianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaControlliService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaService;
import com.axiante.mui.webapp.business.PianoMediaSecurityUtils;
import com.axiante.mui.webapp.business.service.PianoMediaHelperService;
import com.axiante.mui.webapp.webservice.dto.CreatePianificazioneDto;
import com.axiante.mui.webapp.webservice.dto.PianificazioniPianoDto;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.PianificazionePianoFactory;
import com.axiante.mui.webapp.webservice.util.PianoMediaRowDataUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
import lombok.extern.slf4j.Slf4j;

@Path("/pianomedia")
@Dependent
@Slf4j
public class PianificazionePianoMediaResource extends SessionEnabledResource {

    @Inject
    private Instance<PianoMediaService> pianoMediaServiceInstance;

    @Inject
    private Instance<PianificazionePianoMediaService> pianificazionePianoMediaServiceInstance;

    @Inject
    private Instance<PianificazionePianoFactory> pianificazionePianoFactoryInstance;

    @Inject
    private Instance<PianoMediaHelperService> pianoMediaHelperServiceInstance;

    @Inject
    private Instance<PianoMediaControlliService> pianoMediaControlliServiceInstance;

    @Inject
    private Instance<PianoMediaRowDataUtil> pianoMediaRowDataUtilInstance;

    @Inject
    private Instance<PianoMediaSecurityUtils> securityUtilsInstance;

    @GET
    @Path("{pianoMediaId}/pianificazioni")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPianificazioni(@PathParam("pianoMediaId") String pianoMediaId, @Context HttpServletRequest request) {
        Long idPianoMedia = -1L;
        try {
            idPianoMedia = Long.parseLong(pianoMediaId);
        } catch (Exception ex) {
            log.error(String.format("Error converting pianoMediaId %s to long", pianoMediaId), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Piano media inesistente").asJson()).build();
        }
        try {
            final PianoMediaEntity pianoMedia = pianoMediaServiceInstance.get().findById(idPianoMedia);
            if (pianoMedia == null) {
                final String msg = String.format("Piano media con id %s not esiste", pianoMediaId);
                log.warn(msg);
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message(msg).asJson()).build();
            }
            final boolean editable = (getCurrentUser().isAdmin() || securityUtilsInstance.get().canWriteScheda(getCurrentUser())) && (securityUtilsInstance.get().isGanntEditable(pianoMedia));
            return Response.ok(pianificazionePianoFactoryInstance.get().build(pianoMedia, editable).asJson())
                    .build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws GET /pianomedia/%s/pianificazioni", pianoMediaId), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero pianificazioni piano media").asJson()).build();
        }
    }

    @POST
    @Path("{pianoMediaId}/pianificazioni")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPianificazione(@PathParam("pianoMediaId") String pianoMediaId, CreatePianificazioneDto payload,
                                         @Context HttpServletRequest request) {
        final boolean editable = getCurrentUser().isAdmin() || securityUtilsInstance.get().canWriteScheda(getCurrentUser());
        if (!editable) {
            log.error(String.format("User '%s' cannot create Pianificazione for Piano Media with id '%s'",
                    getCurrentUser().getName(), pianoMediaId));
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Creazione Pianificazione Piano Media non permessa; contattare l'assistenza").asJson())
                    .build();
        }
        Long idPianoMedia = -1L;
        try {
            idPianoMedia = Long.parseLong(pianoMediaId);
        } catch (Exception ex) {
            log.error(String.format("Error converting pianoMediaId %s to long", pianoMediaId), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Piano media inesistente").asJson()).build();
        }
        if (payload == null) {
            final String msg = String.format("Payload creazione pianificazione per piano media con id %s not impostato", pianoMediaId);
            log.error(msg);
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
        }
        try {
            PianoMediaEntity pianoMedia = pianoMediaServiceInstance.get().findById(idPianoMedia);
            if (pianoMedia == null) {
                final String msg = String.format("Piano media con id %s not esiste", pianoMediaId);
                log.error(msg);
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
            }
            PianificazionePianoMediaEntity ppm = pianificazionePianoFactoryInstance.get()
                    .buildPianificazionePianoMedia(pianoMedia, payload, getCurrentUser().getName());
            if (ppm == null) {
                final String msg = String.format("Errore creazione pianificazione per piano media '%s'", pianoMedia.getDescrizione());
                log.error(msg);
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
            }
            // Se la testata ha gia' pianificazioni di dettaglio per ogni dettaglio aggiungo l'anagrafica media
            if(pianoMedia.getDettagliPianificazione() != null && !pianoMedia.getDettagliPianificazione().isEmpty()){
                for (PianoMediaPianificazioneDettaglioEntity dettaglio: pianoMedia.getDettagliPianificazione()){
                    PianificazioneAnagraficaPianoMediaEntity anagrafica = new PianificazioneAnagraficaPianoMediaEntity();
                    anagrafica.setAttivo(false);
                    dettaglio.addPianificazioneAnagrafica(anagrafica);
                    ppm.addPianificazioneAnagrafica(anagrafica);
                }
            }
            // Persisto, aggiungo a PianoMedia, eseguo i controlli e ritorno il dto aggiornato
            ppm = pianificazionePianoMediaServiceInstance.get().persist(ppm);
            pianoMedia.getConfigurazioniPianoMedia().add(ppm);
            int failedChecks = pianoMediaHelperServiceInstance.get().executeChecks(ppm, getCurrentUser().getName());
            final PianificazioniPianoDto dto = pianificazionePianoFactoryInstance.get().build(pianoMedia, editable);
            if (failedChecks > 0) {
                dto.setMessage(String.format("Controlli falliti: %d", failedChecks));
            }
            return Response.ok(dto.asJson()).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws POST /pianomedia/%s/pianificazioni", pianoMediaId), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore creazione pianificazione piano media").asJson()).build();
        }
    }

    @DELETE
    @Path("{pianoMediaId}/pianificazioni/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePianificazione(@PathParam("pianoMediaId") String pianoMediaId, @PathParam("id") String id,
                                         @Context HttpServletRequest request) {
        final boolean editable = getCurrentUser().isAdmin() || securityUtilsInstance.get().canWriteScheda(getCurrentUser());
        if (!editable) {
            log.error(String.format("User '%s' cannot delete Pianificazione Piano Media with id '%s'",
                    getCurrentUser().getName(), id));
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Cancellazione Pianificazione Piano Media non permessa; contattare l'assistenza").asJson())
                    .build();
        }
        Long idPianoMedia = -1L;
        Long idPianificazione = -1L;
        try {
            idPianoMedia = Long.parseLong(pianoMediaId);
        } catch (Exception ex) {
            log.error(String.format("Error converting pianoMediaId %s to long", pianoMediaId), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Piano media inesistente").asJson()).build();
        }
        try {
            idPianificazione = Long.parseLong(id);
        } catch (Exception ex) {
            log.error(String.format("Error converting pianificazioneId %s to long", id), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Pianificazione piano media inesistente").asJson()).build();
        }
        try {
            PianoMediaEntity pianoMedia = pianoMediaServiceInstance.get().findById(idPianoMedia);
            if (pianoMedia == null) {
                final String msg = String.format("Piano media con id %s not esiste", pianoMediaId);
                log.error(msg);
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
            }
            Long finalIdPianificazione = idPianificazione;
            if (!pianoMedia.getConfigurazioniPianoMedia().removeIf(p -> finalIdPianificazione.equals(p.getId()))) {
                final String msg = String.format("Errore eliminazione pianificazione piano media con id %s", id);
                log.error(msg);
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message(msg).asJson()).build();
            }
            final PianificazionePianoMediaEntity ppm = pianificazionePianoMediaServiceInstance.get()
                    .findById(finalIdPianificazione);
            if (ppm == null) {
                final String msg = String.format("Pianificazione piano media con id %s non esiste", id);
                log.error(msg);
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message(msg).asJson()).build();
            }
            // Elimino i controlli dalla PianificazionePianoMedia
            pianoMediaHelperServiceInstance.get().removeExistingChecks(ppm);
            // Persisto PianoMedia e ritorno il dto aggiornato
            pianoMedia = (PianoMediaEntity) pianoMediaServiceInstance.get()
                    .persistWithAuditLog(pianoMedia, getCurrentUser().getName());
            return Response.ok(pianificazionePianoFactoryInstance.get().build(pianoMedia, editable).asJson()).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws DELETE /pianomedia/%s/pianificazioni/%s", pianoMediaId, id), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore eliminazione pianificazione piano media").asJson()).build();
        }
    }

    @POST
    @Path("{pianoMediaId}/pianificazioni/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePianificazione(@PathParam("pianoMediaId") String pianoMediaId, @PathParam("id") String id,
                                         CreatePianificazioneDto payload, @Context HttpServletRequest request) {
        final boolean editable = getCurrentUser().isAdmin() || securityUtilsInstance.get().canWriteScheda(getCurrentUser());
        if (!editable) {
            log.error(String.format("User '%s' cannot edit Pianificazione Piano Media with id '%s'",
                    getCurrentUser().getName(), id));
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Modifica Pianificazione Piano Media non permessa; contattare l'assistenza").asJson())
                    .build();
        }
        Long idPianoMedia = -1L;
        Long idPianificazione = -1L;
        try {
            idPianoMedia = Long.parseLong(pianoMediaId);
        } catch (Exception ex) {
            log.error(String.format("Error converting pianoMediaId %s to long", pianoMediaId), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Piano media inesistente").asJson()).build();
        }
        try {
            idPianificazione = Long.parseLong(id);
        } catch (Exception ex) {
            log.error(String.format("Error converting pianificazioneId %s to long", id), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Pianificazione piano media inesistente").asJson()).build();
        }
        try {
            PianoMediaEntity pianoMedia = pianoMediaServiceInstance.get().findById(idPianoMedia);
            if (pianoMedia == null) {
                final String msg = String.format("Piano media con id %s not esiste", pianoMediaId);
                log.error(msg);
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
            }
            PianificazionePianoMediaEntity ppm = pianificazionePianoMediaServiceInstance.get().findById(idPianificazione);
            if (ppm == null) {
                final String msg = String.format("Pianificazione piano media con id %s not esiste", id);
                log.error(msg);
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
            }
            final boolean update = pianificazionePianoFactoryInstance.get().update(ppm, payload);
            if (!update) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Errore aggiornamento pianificazione piano media %d", id).asJson()).build();
            }
            // Entity aggiornata, persisto
            ppm = (PianificazionePianoMediaEntity) pianificazionePianoMediaServiceInstance.get()
                    .persistWithAuditLog(ppm, getCurrentUser().getName());
            if (ppm == null) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Errore aggiornamento pianificazione piano media %d", id).asJson()).build();
            }
            // Tutto ok, eseguo controlli e ritorno dto aggiornato
            pianoMediaHelperServiceInstance.get().executeChecks(ppm, getCurrentUser().getName());
            return Response.ok(pianificazionePianoFactoryInstance.get().build(pianoMedia, editable).asJson()).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws POST /pianomedia/%s/pianificazioni/%s", pianoMediaId, id), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore aggiornamento pianificazione piano media").asJson()).build();
        }
    }

    @GET
    @Path("controlli/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controlliColumnDef(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {
        return loadColumnDefFromFile("columnDefPianoMediaControlli.json", "db_pianoMedia_controlli", contesto);
    }

    @GET
    @Path("{pianoMediaId}/controlli/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controlliRowData(@PathParam("pianoMediaId") String pianoMediaId,
                                     @QueryParam("contesto") String contesto,
                                     @Context HttpServletRequest request) {
        Long idPianoMedia = -1L;
        try {
            idPianoMedia = Long.parseLong(pianoMediaId);
        } catch (Exception ex) {
            log.error(String.format("Error parsing pianoMediaId %s as long", pianoMediaId), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Errore recupero controlli per Piano Media selezionato").asJson())
                    .build();
        }
        final PianoMediaEntity pianoMedia = pianoMediaServiceInstance.get().findById(idPianoMedia);
        if (pianoMedia == null) {
            log.error(String.format("Cannot find Piano Media with id %d", idPianoMedia));
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Errore recupero controlli per Piano Media selezionato").asJson())
                    .build();
        }
        try {
            final List<Long> ppmIds = pianoMedia.getConfigurazioniPianoMedia().stream()
                    .map(PianificazionePianoMediaEntity::getId)
                    .collect(Collectors.toList());
            final List<PianoMediaControlliEntity> controlli = pianoMediaControlliServiceInstance.get()
                    .findByIdPianificazioniMedia(ppmIds);
            final String rowData = pianoMediaRowDataUtilInstance.get().createControlliRowData(controlli);
            return Response.ok(rowData).build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws GET /pianomedia/%s/controlli/rowData", pianoMediaId), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero controlli pianificazioni piano media").asJson()).build();
        }
    }

    @GET
    @Path("/overview/{anno}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOverviewPianificazioni(@PathParam("anno") String anno, @Context HttpServletRequest request) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(anno));
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date dataInizio = cal.getTime();
            cal.set(Calendar.MONTH, Calendar.DECEMBER);
            cal.set(Calendar.DAY_OF_MONTH, 31);
            Date dataFine = cal.getTime();
            final List<PianoMediaEntity> pianificazioni = pianoMediaServiceInstance.get()
                    .findByDataInizioAndDataFine(dataInizio, dataFine);
            return Response.ok(pianificazionePianoFactoryInstance.get()
                            .build(pianificazioni, dataInizio, dataFine, false).asJson())
                    .build();
        } catch (Exception ex) {
            log.error(String.format("Error calling ws GET /pianomedia/overview/%s", anno), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Errore recupero overview pianificazioni piano media").asJson()).build();
        }
    }

}
