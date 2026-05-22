package com.axiante.mui.webapp.webservice;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.common.promo.grid.ComboBoxValues;
import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.common.utility.PicklistUtils;
import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.business.enumeration.PlanningRadioButtonEnum;
import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.business.utils.ComboBoxFactory;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CheckCompratoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CheckPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSottoclasseEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniNegoziEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import com.axiante.mui.dbpromo.persistence.service.CheckPianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.ClusterClienteService;
import com.axiante.mui.dbpromo.persistence.service.ConfigurationService;
import com.axiante.mui.dbpromo.persistence.service.FormaPagamentoService;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.MuiIniziativaService;
import com.axiante.mui.dbpromo.persistence.service.MuiPlanoArticoliDbPromoService;
import com.axiante.mui.dbpromo.persistence.service.MuiPromoArticoliDbPromoService;
import com.axiante.mui.dbpromo.persistence.service.MuiSottoclasseService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTestataService;
import com.axiante.mui.dbpromo.persistence.service.ReportSovrapposizioniNegoziService;
import com.axiante.mui.dbpromo.persistence.service.ReportSovrapposizioniService;
import com.axiante.mui.dbpromo.persistence.service.UploadFidatyService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.MeccanicheUtil;
import com.axiante.mui.webapp.utils.PianificazioneSecurityUtil;
import com.axiante.mui.webapp.utils.SottoclasseUtil;
import com.axiante.mui.webapp.webservice.dto.PromozioneCheckDto;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoRowDataUtil;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.ComplementaryColumnDef;
import com.axiante.mui.webapp.webservice.util.pianificazione.DynamicColumnDef;
import com.axiante.mui.webapp.webservice.util.pianificazione.DynamicColumnDefFactory;
import com.axiante.mui.webapp.webservice.util.pianificazione.PlanningColumnDef;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.LinkHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.LookupUtils;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.NumSetUtils;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PianificazioneValidatorUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PromoConfigurationHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PromozionePianificazioneEntityHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.VisualizzaPianificazioneHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.DynamicColumnEnum;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PlanningCommons;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
import java.io.File;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/pianificazionepromo")
@Slf4j
@Dependent
public class GridPianificazioneServiceResource extends SessionEnabledResource {

    @Inject
    transient private DynamicColumnDefFactory dynamicColumnDefFactory;

    @Inject
    transient private PianificazioneService pianificazioneService;

    @Inject
    transient private Instance<MuiSottoclasseService> sottoclasseServiceInstance;

    @Inject
    transient private Instance<FormaPagamentoService> formaPagamentoServiceInstance;

    @Inject
    transient private PianificazionePromoUtil pianificazionePromoUtil;

    @Inject
    transient private PianificazionePromoRowDataUtil pianificazionePromoRowDataUtil;

    @Inject
    transient private PromoConfigurationHelper promoConfigurationHelper;

    transient private final DateTimeUtils dateTimeUtils = new DateTimeUtils();

    @Inject
    transient private CfgPianificazioneService cfgPianificazioneService;

    @Inject
    transient private UploadFidatyService uploadFidatyService;

    @Inject
    transient private ComplementaryColumnDef complementariUtils;

    @Inject
    transient private PromozionePianificazioneEntityHelper promoPianificazioneEntityHelper;

    @Inject
    transient private PlanningCommons planningCommons;

    @Inject
    transient private ConfigurationService configurationService;

    @Inject
    transient private PianificazioneValidatorUtil validatorUtil;

    @Inject
    transient private Instance<MuiService> muiServiceInstance;

    @Inject
    transient private Instance<PianificazioneSecurityUtil> securityUtilInstance;

    @Inject
    transient private Instance<MuiPromoArticoliDbPromoService> muiPromoArticoliDbPromoServiceInstance;

    @Inject
    transient private Instance<MuiPlanoArticoliDbPromoService> muiPlanoArticoliDbPromoServiceInstance;

    @Inject
    transient private Instance<CheckPianificazioneService> checkPianificazioneServiceInstance;

    @Inject
    transient private Instance<ReportSovrapposizioniNegoziService> sovrapposizioneNegoziServiceInstance;

    @Inject
    transient private Instance<ReportSovrapposizioniService> reportSovrapposizioniServiceInstance;

    @Inject
    transient private Instance<PromozioneTestataService> promozioneTestataService;

    @Inject
    @Getter(AccessLevel.PACKAGE)
    transient private Instance<ItemService> itemService;

    @Inject
    transient private Instance<MeccanicheUtil> meccanicheUtilInstance;

    @Inject
    transient private Instance<ComboBoxFactory> comboBoxFactoryInstance;

    @Inject
    transient private Instance<LookupUtils> lookupUtilsInstance;

    @Inject
    private VisualizzaPianificazioneHelper visualizzaPianificazioneHelper;

    @Inject
    private Instance<ClusterClienteService> clusterClienteServiceInstance;

    @Inject
    private Instance<MuiIniziativaService> muiIniziativaServiceInstance;

    @Inject
    private Instance<LinkHelper> linkHelperInstance;

    @Inject
    Instance<NumSetUtils> numSetUtilsInstance;

    @Inject
    private Instance<SottoclasseUtil> sottoclasseUtilInstance;

    @GET
    @Path("/selezioneTab/columnDef/{elementType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDef(@PathParam("elementType") String elementType, @QueryParam("contesto") String context,
                                 @Context HttpServletRequest request) throws Exception {

        DynamicColumnDef dynamicColumnDef = null;
        if (elementType != null) {
            if (DynamicColumnEnum.TOTALE.equals(DynamicColumnEnum.from(elementType))) {
                return Response.ok().build();
            }
            if (!elementType.isEmpty()) {
                dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef(elementType);
            }
        }
        if (dynamicColumnDef == null) {
            log.error("Error converting elementType " + elementType);
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(JsonUtils.getMapper()
                            .writeValueAsString(new Message(String.format("Tipo Elemento non supportato %s", elementType))))
                    .build();
        }

        return Response
                .ok(dynamicColumnDef.generateColumnDefByPromoConfiguration(null, getCurrentUser().getHiddenCols(),
                        dynamicColumnDefFactory.getDynamicColumnDefGridName(elementType), context))
                .build();
    }

    @GET
    @Path("/selezioneTab/rowData/{idPromozioneCorrente}/{meccanicaId}/{elementType}/{buyerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowData(@PathParam("idPromozioneCorrente") String promozioneId,
                               @PathParam("meccanicaId") String meccanicaId, @PathParam("elementType") String elementType,
                               @PathParam("buyerId") String buyerId, @QueryParam("contesto") String contesto,
                               @Context HttpServletRequest request) throws Exception {
        return getRowData(promozioneId, meccanicaId, elementType, buyerId, null, contesto, request);
    }

    @GET
    @Path("/selezioneTab/rowData/{idPromozioneCorrente}/{meccanicaId}/{elementType}/{buyerId}/{codiceGruppo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowData(@PathParam("idPromozioneCorrente") String promozioneId,
                               @PathParam("meccanicaId") String meccanicaId, @PathParam("elementType") String elementType,
                               @PathParam("buyerId") String buyerId, @PathParam("codiceGruppo") String codiceGruppo,
                               @QueryParam("contesto") String contesto, @Context HttpServletRequest request) throws Exception {
        try {
            if ("null".equalsIgnoreCase(codiceGruppo)) {
                codiceGruppo = null;
            }
            final PromozioneCheckDto promozioneCheckDto = validatePromozioneFromRequest(promozioneId, meccanicaId,
                    contesto, codiceGruppo);
            if (promozioneCheckDto.isError()) {
                return Response.status(promozioneCheckDto.getResponseStatus())
                        .entity(new Message(promozioneCheckDto.getErrorMessage()).asJson()).build();
            }
            if (elementType == null) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Tipo Elemento obbligatorio").asJson()).build();
            }
            if (DynamicColumnEnum.TOTALE.equals(DynamicColumnEnum.from(elementType))) {
                return Response.ok().build();
            }
            final DynamicColumnDef dynamicColumnDef = elementType.isEmpty() ? null
                    : dynamicColumnDefFactory.getDynamicColumnDef(elementType);
            if (dynamicColumnDef == null) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Tipo Elemento non supportato '%s'", elementType).asJson()).build();
            }
            return Response.ok()
                    .entity(dynamicColumnDef.generateRowDataByPromoElementMechanic(promozioneCheckDto.getTestata(),
                            promozioneCheckDto.getIdMeccanica(), buyerId, isUserAdmin(),
                            promozioneCheckDto.getCodiciGruppo(), null).toString())
                    .build();
        } catch (Exception ex) {
            log.error(String.format("Error getting JSON rowData for 'Pianificazione Promozione' with id %s",
                    promozioneId), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @GET
    @Path("/planning/columnDef/{elementType}/{promoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanningColumnDef(@PathParam("elementType") String elementType,
                                         @PathParam("promoId") String promoId, @QueryParam("contesto") String context,
                                         @Context HttpServletRequest request) throws Exception {
        long idPromozione;
        try {
            idPromozione = Long.parseLong(promoId);
        } catch (final Exception e) {
            log.error("error converting promozione id to long " + promoId, e);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione inesistente").asJson()).build();
        }

        final PromozioneTestataEntity testata = getPromozioneTestataEntity(idPromozione);
        if ((testata == null) || !security.isAccessible(testata, getApplicationUser(context))) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione non accessibile").asJson()).build();
        }

        final DynamicColumnDef dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef(elementType);
        if (dynamicColumnDef == null) {
            log.error("Error converting elementType " + elementType);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Tipo Elemento non supportato '%s'", elementType).asJson()).build();
        }

        return Response
                .ok(dynamicColumnDef.generateColumnDefByPromoConfiguration(testata, getCurrentUser().getHiddenCols(),
                        dynamicColumnDefFactory.getDynamicColumnDefGridName(elementType), context))
                .build();
    }

    @GET
    @Path("/planning/rowData/{elementType}/{promoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanningRowData(@PathParam("elementType") String elementType, @PathParam("promoId") String promoId,
                                       @QueryParam("contesto") String context, @Context HttpServletRequest request) throws Exception {
        long idPromozione;
        try {
            idPromozione = Long.parseLong(promoId);
        } catch (final Exception e) {
            log.error("error converting promozione id to long " + promoId, e);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione inesistente").asJson()).build();
        }
        if (!security.isAccessible(promoService.findById(idPromozione), getApplicationUser(context))) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione non accessibile").asJson()).build();
        }

        DynamicColumnDef dynamicColumnDef = null;

        if ((promoId != null) && !promoId.isEmpty() && (elementType != null)) {
            final DynamicColumnEnum type = DynamicColumnEnum.from(elementType);
            if (DynamicColumnEnum.PIANIFICAZIONE.getField().equals(type.getField())
                    || DynamicColumnEnum.COMPLEMENTARI.getField().equals(type.getField())) {
                dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef(elementType);
            }
        }

        if (dynamicColumnDef == null) {
            log.error("Error converting elementType " + elementType);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Tipo Elemento non supportato '%s'", elementType).asJson()).build();
        }

        return Response.ok(dynamicColumnDef.generateRowDataByPromoConfiguration(String.valueOf(idPromozione), null)).build();
    }

    @GET
    @Path("/planning/compratori/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanningCompratoriColumnDef(@QueryParam("contesto") String context,
                                                   @Context HttpServletRequest request) throws Exception {

        return loadColumnDefFromFile("pianificazione_compratori_columnDef.json", "db_pianificazione_compratori",
                context);
    }

    @GET
    @Path("/planning/compratori/rowData/{promoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanningCompratoriRowData(@PathParam("promoId") String promoId,
                                                 @QueryParam("contesto") String context,
                                                 @Context HttpServletRequest request) throws Exception {

        long idPromozione;
        try {
            idPromozione = Long.parseLong(promoId);
        } catch (final Exception e) {
            log.error("error converting promozione id to long " + promoId, e);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione inesistente").asJson()).build();
        }

        try {
            final PromozioneTestataEntity testataEntity = getPromozioneTestataEntity(idPromozione);
            if ((testataEntity == null) || !security.isAccessible(testataEntity, getApplicationUser(context))) {
                log.error("Promozione " + idPromozione + " non accessibile");
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Promozione non accessibile").asJson()).build();
            }
            Stream<CheckCompratoriEntity> stream = testataEntity.getCheckCompratori().stream();
            if (!isUserAdmin()) {
                final List<String> buyerCodes = securityUtilInstance.get()
                        .getReadableCompratori(testataEntity, getApplicationUser(context).getGruppi());
                stream = stream.filter(Objects::nonNull)
                        .filter(c -> c.getCompratoreEntity() != null
                                && buyerCodes.contains(c.getCompratoreEntity().getCodiceCompratore()));
            }
            return Response
                    .ok(pianificazionePromoRowDataUtil.createCheckCompratoriRowData(stream.collect(Collectors.toList())))
                    .build();

        } catch (Exception ex) {
            log.error("Error getting rowData for check compratori", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/planning/controlli/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanningControlliColumnDef(@QueryParam("contesto") String context,
                                                  @Context HttpServletRequest request) throws Exception {

        return loadColumnDefFromFile("pianificazione_controlli_columnDef.json", "db_pianificazione_controlli", context);
    }

    @GET
    @Path("/planning/controlli/rowData/{promoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanningControlliRowData(@PathParam("promoId") String promoId,
                                                @QueryParam("contesto") String context, @Context HttpServletRequest request) throws Exception {

        try {
            final PromozioneCheckDto promozioneCheckDto = validatePromozioneFromRequest(promoId, context);
            if (promozioneCheckDto.isError()) {
                return Response.status(promozioneCheckDto.getResponseStatus())
                        .entity(new Message(promozioneCheckDto.getErrorMessage()).asJson()).build();
            }
            final PromozioneTestataEntity testata = promozioneCheckDto.getTestata();
            List<CheckPianificazioneEntity> checks = new ArrayList<>();
            if (isUserAdmin()) {
                checks.addAll(testata.getCheckPianificazioni());
            } else {
                // Get readable codiciCompratori from security
                final List<String> codiciCompratore = securityUtilInstance.get().getReadableCompratori(testata,
                        promozioneCheckDto.getCodiciGruppo());
                checks.addAll(checkPianificazioneServiceInstance.get().findAllByPromozioneAndCodiciCompratore(testata,
                        codiciCompratore));
            }
            return Response.ok(pianificazionePromoRowDataUtil.createCheckPianificazioneRowData(checks)).build();
        } catch (Exception ex) {
            log.error(String.format("Error getting JSON rowData for 'Controlli Pianificazione' from promozione %s",
                    promoId), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @GET
    @Path("/planning/sovrapposizioni/master/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanningOverlapColumnDef(@QueryParam("contesto") String context,
                                                @Context HttpServletRequest request) throws Exception {

        return loadColumnDefFromFile("pianificazione_overlap_columnDef.json", "db_pianificazione_sovrapposizioni",
                context);
    }

    @GET
    @Path("/planning/sovrapposizioni/master/rowData/{promoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanningOverlapRowData(@PathParam("promoId") String promoId,
                                              @QueryParam("contesto") String context, @Context HttpServletRequest request) throws Exception {

        try {
            final PromozioneCheckDto promozioneCheckDto = validatePromozioneFromRequest(promoId, context);
            if (promozioneCheckDto.isError()) {
                return Response.status(promozioneCheckDto.getResponseStatus())
                        .entity(new Message(promozioneCheckDto.getErrorMessage()).asJson()).build();
            }
            // Get readable codiciCompratori from security
            final PromozioneTestataEntity testata = promozioneCheckDto.getTestata();
            final List<String> codiciCompratore = securityUtilInstance.get().getReadableCompratori(testata,
                    promozioneCheckDto.getCodiciGruppo());
            final List<ReportSovrapposizioniEntity> entities = reportSovrapposizioniServiceInstance.get()
                    .findAllByPromozioneAndCodiciCompratore(testata, codiciCompratore);
            return Response.ok(pianificazionePromoRowDataUtil.createReportSovrapposizioniRowData(entities)).build();
        } catch (Exception ex) {
            log.error(String.format(
                    "Error getting JSON rowData for 'Sovrapposizioni Pianificazione' from promozione %s", promoId), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("/planning/{promoId}/delete/{promoPianificazioneId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removePlanningRow(@PathParam("promoId") String promoId,
                                      @PathParam("promoPianificazioneId") String promoPianificazioneId, @QueryParam("contesto") String context,
                                      @Context HttpServletRequest request) throws Exception {

        try {
            final Long idPromozione = Long.parseLong(promoId);

            if (!security.isAccessible(promoService.findById(idPromozione), getApplicationUser(context))) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Promozione non accessibile").asJson()).build();
            }
        } catch (final Exception e) {
            log.error("error converting promozione id to long " + promoId, e);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione inesistente").asJson()).build();
        }

        String planningRemovedMessage = null;
        Long idPromoPianificazione;
        try {
            idPromoPianificazione = Long.parseLong(promoPianificazioneId);
        } catch (final Exception e) {
            log.error("error converting promozione pianificazione id to long " + promoPianificazioneId, e);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione inesistente").asJson()).build();
        }

        final UserDTO userDTO = getApplicationUser(context);
        final PromozionePianificazioneEntity pianificazione = pianificazioneService
                .getPromoPianificazoneById(idPromoPianificazione);
        if (pianificazione != null) {
            final boolean deletable = isUserAdmin()
                    || securityUtilInstance.get().isDeletable(pianificazione, userDTO.getGruppi());
            if (!deletable) {
                log.error(String.format("Set security does not permit deletion of pianificazione id %d",
                        idPromoPianificazione));
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(
                                new Message("La sicurezza impostata non permette la cancellazione della pianificazione"))
                        .build();
            }
            planningRemovedMessage = pianificazionePromoUtil.delete(pianificazione, userDTO.getUser().getName());
        }

        if ((planningRemovedMessage != null) && (pianificazione != null)) {
            final DynamicColumnDef dynamicColumnDef = dynamicColumnDefFactory
                    .getDynamicColumnDef(DynamicColumnEnum.PIANIFICAZIONE.getField());
            return Response.ok(dynamicColumnDef.generateRowDataByPromoConfiguration(promoId, isUserAdmin(),
                    userDTO.getGruppi(), planningRemovedMessage)).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/planning/{promoId}/empty/{promoPianificazioneId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response emptyRaggruppamento(@PathParam("promoId") String promoId,
                                        @PathParam("promoPianificazioneId") String promoPianificazioneId, @QueryParam("contesto") String context,
                                        @Context HttpServletRequest request) throws Exception {

        try {
            final Long idPromozione = Long.parseLong(promoId);
            if (!security.isAccessible(promoService.findById(idPromozione), getApplicationUser(context))) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Promozione non accessibile").asJson()).build();
            }
        } catch (final Exception e) {
            log.error("error converting promozione id to long " + promoId, e);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione inesistente").asJson()).build();
        }

        String planningEmptyRaggruppamentoMessage = null;
        Long idPromoPianificazione;
        try {
            idPromoPianificazione = Long.parseLong(promoPianificazioneId);
        } catch (final Exception e) {
            log.error("error converting promozione pianificazione id to long " + promoPianificazioneId, e);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione inesistente").asJson()).build();
        }

        final UserDTO userDTO = getApplicationUser(context);
        final PromozionePianificazioneEntity pianificazione = pianificazioneService
                .getPromoPianificazoneById(idPromoPianificazione);
        if (pianificazione != null) {
            final boolean writeable = isUserAdmin() || isEmptiable(pianificazione, userDTO.getGruppi());
            if (!writeable) {
                log.error(String.format("Setted security cannot permit to emptying pianificazione id %d",
                        idPromoPianificazione));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("La sicurezza impostata non permette lo svuotamento del raggruppamento"))
                        .build();
            }
            planningEmptyRaggruppamentoMessage = pianificazionePromoUtil.emptyRaggruppamento(pianificazione,
                    userDTO.getGruppi());
        }

        if ((planningEmptyRaggruppamentoMessage != null) && (pianificazione != null)) {
            final DynamicColumnDef dynamicColumnDef = dynamicColumnDefFactory
                    .getDynamicColumnDef(DynamicColumnEnum.PIANIFICAZIONE.getField());
            return Response.ok(dynamicColumnDef.generateRowDataByPromoConfiguration(promoId, isUserAdmin(),
                    userDTO.getGruppi(), planningEmptyRaggruppamentoMessage)).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    private boolean isEmptiable(@NonNull PromozionePianificazioneEntity pianificazione, List<String> codiciGruppo) {
        try {
            final String codiceTipoRiga = pianificazione.getTipoRiga().getCodiceTipo();
            // L'azione di svuotamento e' possibile solo su pianificazioni con tipo riga
            // RAGGRUPPAMENTO
            if (!PianificazioneRowTypeEnum.RAGGRUPPAMENTO.getTypeCode().equals(codiceTipoRiga)) {
                log.error(String.format("Cannot check isEmptiable tipoRiga %s for pianificazione %d", codiceTipoRiga,
                        pianificazione.getId()));
                return false;
            }
            final PianificazioneSecurityUtil securityUtil = securityUtilInstance.get();
            return !pianificazione.getChildren().isEmpty()
                    && pianificazione.getChildren().stream().anyMatch(p -> securityUtil.isWriteable(p, codiciGruppo));
        } catch (Exception ex) {
            log.error(String.format("Cannot check isEmptiable for pianificazione %d", pianificazione.getId()), ex);
            return false;
        }
    }

    @GET
    @Path("/copiaIncollaTab/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCopyPasteColumnDef(@QueryParam("contesto") String context, @Context HttpServletRequest request)
            throws Exception {

        try {
            final DynamicColumnDef dynamicColumnDef = dynamicColumnDefFactory
                    .getDynamicColumnDef(DynamicColumnEnum.COPIA_INCOLLA.getField());
            return Response
                    .ok(dynamicColumnDef.generateColumnDefByPromoConfiguration(null, getCurrentUser().getHiddenCols(),
                            dynamicColumnDefFactory
                                    .getDynamicColumnDefGridName(DynamicColumnEnum.COPIA_INCOLLA.getField()),
                            context))
                    .build();
        } catch (final Exception ex) {
            final String msg = "Errore nella generazione del columnDef";
            log.error(msg, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(JsonUtils.getMapper().writeValueAsString(new Message(msg))).build();
        }
    }

    @GET
    @Path("/copiaIncollaTab/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCopyPasteRowData(@Context HttpServletRequest request) throws Exception {

        final DynamicColumnDef dynamicColumnDef = dynamicColumnDefFactory
                .getDynamicColumnDef(DynamicColumnEnum.COPIA_INCOLLA.getField());
        if (dynamicColumnDef == null) {
            final String msg = String.format("Tipo Elemento non supportato '%s'",
                    DynamicColumnEnum.COPIA_INCOLLA.getField());
            log.error(msg);
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(msg).asJson()).build();
        }
        return Response.ok(dynamicColumnDef.generateRowDataByPromoConfiguration(null, null)).build();
    }

    @GET
    @Path("/copiaIncollaTab/validate/{idPromozioneCorrente}/{idMeccanicaCorrente}/{elementType}/{itemCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateCopyPasteItemCode(@PathParam("idPromozioneCorrente") String promozioneId,
                                              @PathParam("idMeccanicaCorrente") String meccanicaId, @PathParam("elementType") String elementType,
                                              @PathParam("itemCode") String itemCode, @QueryParam("contesto") String contesto,
                                              @Context HttpServletRequest request) throws Exception {

        Long idPromozione;
        try {
            idPromozione = Long.parseLong(promozioneId);
        } catch (final Exception e) {
            log.error("error converting promozione id to long " + promozioneId, e);
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message("Promozione inesistente"))
                    .build();
        }

        if (!security.isAccessible(promoService.findById(idPromozione), getApplicationUser(contesto))) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione non accessibile").asJson()).build();
        }

        long idMeccanica;
        try {
            idMeccanica = Long.parseLong(meccanicaId);
        } catch (final Exception e) {
            log.error("error converting meccanica id to long " + meccanicaId, e);
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message("Promozione inesistente"))
                    .build();
        }

        final ElementType elementTypeChecked = ElementType.fromDescription(elementType);
        if (!elementType.isEmpty() && (elementTypeChecked != null)) {
            return Response.ok().entity(promoConfigurationHelper.validate(elementTypeChecked, itemCode, idPromozione,
                    idMeccanica, isUserAdmin(), getApplicationUser(contesto).getGruppi()).toString()).build();
        } else {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Tipo Elemento non supportato '%s'", elementType).asJson()).build();
        }
    }

    @POST
    @Path("/updatePlanning")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlanning(String jsonEntity, @QueryParam("contesto") String context,
                                   @Context HttpServletRequest request) throws Exception {
        log.debug(jsonEntity);

        Response response;
        final JsonNode node = JsonUtils.getMapper().readTree(jsonEntity);
        final ObjectNode responseNode = JsonUtils.getMapper().createObjectNode();
        // Reset messaggi nel nodo
        DbPromoAgCellUtils.putValue(responseNode, "updateMessage", null, false);
        DbPromoAgCellUtils.putValue(responseNode, "errorMessage", null, false);

        if ((node.get("columnToBeUpdated") != null) && (node.get("valueToBeUpdated") != null)) {
            final String idPromoPianificazione = DbPromoAgCellUtils.getValue(node.get("idPromoPianificazione"));
            final String columnToBeUpdated = DbPromoAgCellUtils.getValue(node.get("columnToBeUpdated"));
            String codiceMeccanica = null;
            if (node.get("ID_MECCANICA") != null) {
                final String[] splitted = node.get("ID_MECCANICA").get("value").asText().split(" - ");
                codiceMeccanica = splitted.length > 0 ? splitted[0].trim() : null;
            }

            final JsonNode objectToBeUpdated = node.get(columnToBeUpdated);
            final Object defValue = (objectToBeUpdated != null) && (objectToBeUpdated.get("defValue") != null)
                    ? objectToBeUpdated.get("defValue").toString().replace("\"", "")
                    : null;

            Object valueToBeUpdated = DbPromoAgCellUtils.getValue(node.get("valueToBeUpdated"), Boolean.TRUE);

            if ((valueToBeUpdated != null) && StringUtils.isEmpty(valueToBeUpdated.toString())) {
                valueToBeUpdated = null;
            }

            if (idPromoPianificazione != null) {
                final UserDTO userDTO = getApplicationUser(context);
                final PromozionePianificazioneEntity pianificazione = pianificazioneService
                        .getPromoPianificazoneById(Long.parseLong(idPromoPianificazione));
                if (pianificazione == null
                        || !security.isAccessible(pianificazione.getPromozioneTestataEntity(), userDTO)) {
                    return Response.status(Response.Status.PRECONDITION_FAILED)
                            .entity(new Message("Promozione non accessibile").asJson()).build();
                }
                //controllo se la pianificazione e' bloccata a causa delle date di inizio e fine...
                // a meno che non sia admin
                //feature 4759
                if (!userDTO.isAdmin() && pianificazionePromoUtil.isPianificazioneBoundToDateCheck(pianificazione.getPromozioneTestataEntity())) {
                    // se aggiorno data fine allora data fine deve essere libera
                    if ("DATA_FINE".equalsIgnoreCase(columnToBeUpdated) && pianificazionePromoUtil.isPianificazioneLockedOnDataFine(pianificazione)) {
                        DbPromoAgCellUtils.putValue(responseNode, "errorMessage",
                                "La pianificazione non può essere modificata in quanto la data di fine è inferiore alla data odierna", false);
                        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
                        arrayNode.add(node);
                        responseNode.set("rowData", arrayNode);
                        return Response.status(Response.Status.PRECONDITION_FAILED).entity(responseNode.toString()).build();
                    } else {
                        if (pianificazionePromoUtil.isPianificazioneLockedOnDataInizio(pianificazione) &&
                                !(
                                        "DATA_FINE".equalsIgnoreCase(columnToBeUpdated)
                                                ||
                                                "VALORE".equalsIgnoreCase(columnToBeUpdated)
                                )
                        ) {
                            DbPromoAgCellUtils.putValue(responseNode, "errorMessage",
                                    "La pianificazione non può essere modificata in quanto la data di inizio è inferiore alla data odierna", false);
                            final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
                            arrayNode.add(node);
                            responseNode.set("rowData", arrayNode);
                            return Response.status(Response.Status.PRECONDITION_FAILED).entity(responseNode.toString()).build();
                        }
                    }
                }

                final boolean isEditable = isUserAdmin()
                        || securityUtilInstance.get().isWriteable(pianificazione, userDTO.getGruppi());
                if (!isEditable) {
                    log.error(String.format("Setted security cannot permit to edit pianificazione id %s",
                            idPromoPianificazione));
                    pianificazionePromoUtil.resetPersistedValue(node, columnToBeUpdated, pianificazione,
                            defValue, valueToBeUpdated);
                    final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
                    arrayNode.add(node);
                    DbPromoAgCellUtils.putValue(responseNode, "errorMessage",
                            "La sicurezza impostata non permette la cancellazione della pianificazione", false);
                    responseNode.set("rowData", arrayNode);
                    return Response.status(Response.Status.PRECONDITION_FAILED).entity(responseNode.toString()).build();
                }

                boolean fieldLockedInConfiguration = false;
                if ((columnToBeUpdated != null) && !columnToBeUpdated.isEmpty()) {
                    if (PianificazioneConstants.CAMPO_PIANIFICAZIONE_MECCANICA.equals(columnToBeUpdated)
                            && valueToBeUpdated != null) {
                        try {
                            final boolean success = cambioMeccanica(pianificazione, userDTO.getCanali(),
                                    Long.parseLong(valueToBeUpdated.toString()), userDTO.getUser().getName());
                            if (success) {
                                return Response.status(Response.Status.RESET_CONTENT).build();
                            } else {
                                return resetCambioMeccanica(responseNode, node, pianificazione);
                            }
                        } catch (Exception ex) {
                            log.error(String.format("Error changing meccanica for pianificazione %d to value %s",
                                    pianificazione.getId(), valueToBeUpdated), ex);
                            return resetCambioMeccanica(responseNode, node, pianificazione);
                        }
                    }

                    // prendo la configurazione per il campo
                    PlanningLevelEnum livelloPianificazione = PlanningLevelEnum
                            .fromCode(pianificazione.getTipoRiga().getCodiceTipo());

                    if (PlanningLevelEnum.RAGGRUPPAMENTO.equals(livelloPianificazione)) {
                        // la riga e' di tipo raggruppamento: ha senso controllare valido se
                        // raggruppamento
                        List<CfgPianificazioneEntity> configurazioni = configurationService
                                .findBySetAndMeccanicaAndCampo(
                                        pianificazione.getPromozioneTestataEntity()
                                                .getMuiCfgSetPianificazione(),
                                        pianificazione.getMeccanicaEntity(), columnToBeUpdated);
                        if ((configurazioni != null) && (!configurazioni.isEmpty())) {
                            CfgPianificazioneEntity configurazione = configurazioni.stream().filter(
                                            c -> livelloPianificazione.getDescription().equals(c.getMuiCfgPianificazTipoRiga().getCodiceTipo()))
                                    .findFirst().orElse(null);

                            if (configurazione != null) {
                                // ho la configurazione, adesso controllo
                                if (planningCommons.overrideConfiguration(configurazione, pianificazione)) {
                                    // devo tornare un errore
                                    fieldLockedInConfiguration = true;
                                    log.debug("CAMPO LOCKED !!! -> Il campo " + columnToBeUpdated
                                            + " e' configurato con valido se raggruppamento = "
                                            + configurazione.getValidoSeRaggruppamento()
                                            + " ma il num_raggruppamento per la riga di pianificazione e' "
                                            + pianificazione.getNumRaggruppamento());
                                }
                            }
                        }
                    }

                    if (PianificazioneConstants.REFERENCE_DATA_INIZIO_COLUMN.equals(columnToBeUpdated)
                            || PianificazioneConstants.REFERENCE_DATA_FINE_COLUMN.equals(columnToBeUpdated)) {
                        valueToBeUpdated = dateTimeUtils.excelToDate((String) valueToBeUpdated);
                    }

                    final CanalePromozioneEntity muiCanalePromozione = pianificazione
                            .getPromozioneTestataEntity().getMuiCanalePromozione();
                    final MeccanicheEntity meccanica = pianificazione.getMeccanicaEntity();
                    CfgPianificazTipoRigaEntity tipoRiga = pianificazione.getTipoRiga();
                    /*
                     * la configurazione la prendo con id_meccanica = 48 id_cfg_set_pianificazione =
                     * 1 id_tipo_riga = 4 campo = 'DATA_INIZIO' campo = columnToBeUpdated
                     */
                    CfgPianificazioneEntity cfgPianificazione = cfgPianificazioneService
                            .findPianificazioneByCanaleMeccanicaTipoRigaField(muiCanalePromozione, meccanica, tipoRiga,
                                    columnToBeUpdated, pianificazione.getPromozioneTestataEntity());

                    boolean valueValidated = false;
                    if (cfgPianificazione == null) {
                        log.warn(String.format(
                                "Not found MUI_CFG_PIANIFICAZIONE with MuiCfgPianificazTipoRiga.codiceCampo %s and MuiCfgPianificazioneCampi.campo %s",
                                pianificazione.getTipoRiga().getDescrizione(), columnToBeUpdated));
                    } else {
                        String pickListValues = pianificazionePromoUtil.getLista(cfgPianificazione, pianificazione);
                        valueValidated = (valueToBeUpdated == null) || pianificazionePromoUtil
                                .validateUpdatedPlanningData(cfgPianificazione, valueToBeUpdated, pickListValues);
                        Date parentDataInizio;
                        Date parentDataFine;
                        if (pianificazione.getParent() != null) {
                            parentDataInizio = pianificazione.getParent().getDataInizio();
                            parentDataFine = pianificazione.getParent().getDataFine();
                        } else {
                            parentDataInizio = pianificazione.getPromozioneTestataEntity().getDataInizio();
                            parentDataFine = pianificazione.getPromozioneTestataEntity().getDataFine();
                        }
                        Date today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                        switch (columnToBeUpdated.toUpperCase()) {
                            case PianificazioneConstants.REFERENCE_DATA_INIZIO_COLUMN:
                                valueValidated = (valueToBeUpdated != null) && pianificazionePromoUtil.isDateUpdatedValid(
                                        pianificazione, (Date) valueToBeUpdated,
                                        pianificazione.getDataFine(), parentDataInizio, parentDataFine, true)
                                        && dateTimeUtils.isAfter((Date) valueToBeUpdated, today, false, true);
                                if (!valueValidated) {
                                    DbPromoAgCellUtils.putValue(node, columnToBeUpdated,
                                            dateTimeUtils.toExcelDate(pianificazione.getDataInizio()), true);
                                }
                                break;
                            case PianificazioneConstants.REFERENCE_DATA_FINE_COLUMN:
                                valueValidated = (valueToBeUpdated != null) && pianificazionePromoUtil.isDateUpdatedValid(
                                        pianificazione, pianificazione.getDataInizio(),
                                        (Date) valueToBeUpdated, parentDataInizio, parentDataFine, false)
                                        && dateTimeUtils.isAfter((Date) valueToBeUpdated, today, true, true);
                                if (!valueValidated) {
                                    DbPromoAgCellUtils.putValue(node, columnToBeUpdated,
                                            dateTimeUtils.toExcelDate(pianificazione.getDataFine()), true);
                                }
                                break;
                            case PianificazioneConstants.REFERENCE_ORA_INIZIO_COLUMN:
                                valueValidated = dateTimeUtils.isBefore(
                                        valueToBeUpdated == null ? null : DateTimeUtils.toTime(valueToBeUpdated.toString()),
                                        pianificazione.getOraFine(), false, true);
                                break;
                            case PianificazioneConstants.REFERENCE_ORA_FINE_COLUMN:
                                valueValidated = dateTimeUtils.isBefore(pianificazione.getOraInizio(),
                                        valueToBeUpdated == null ? null : DateTimeUtils.toTime(valueToBeUpdated.toString()),
                                        false, true);
                                break;
                            case PianificazioneConstants.CAMPO_CODICE_ONLINE_COLUMN:
                                // Il codice online deve essere NULL per default.
                                // Se viene inserito manualmente allora deve essere univoco per TUTTE LE
                                // PROMOZIONI
                                // dei CANALI BUONO CATEGORIA e POTENZIAMENTO NELLO STESSO PERIODO (promozioni
                                // che hanno un "overlap")
                                // Univocità può essere ripetuto solo all'interno di una stessa promozione
                                if (PianificazioneConstants.BUONI_CATEGORIA.equals(pianificazione
                                        .getPromozioneTestataEntity().getCanalePromozioneEntity().getDescrizione())
                                        || PianificazioneConstants.BUONI_POTENZIAMENTO
                                        .equals(pianificazione.getPromozioneTestataEntity()
                                                .getCanalePromozioneEntity().getDescrizione())) {
                                    valueValidated = pianificazionePromoUtil.checkCodiceOnLineUniqueness(
                                            pianificazione.getPromozioneTestataEntity(), valueToBeUpdated,
                                            userDTO.getCanali());
                                }
                                break;
                            case PianificazioneConstants.REFERENCE_PAGHI:
                            case PianificazioneConstants.REFERENCE_PORTI_VIA:
                                if ((codiceMeccanica != null) && codiceMeccanica.equalsIgnoreCase("M026")) {
                                    valueValidated = checkPaghiPortiVia(pianificazione,
                                            columnToBeUpdated.toUpperCase(), valueToBeUpdated);
                                    if (!valueValidated) {
                                        pianificazionePromoUtil.resetPersistedValue(node, columnToBeUpdated,
                                                pianificazione, defValue, valueToBeUpdated);
                                        String msg = "Il campo 'Paghi' deve essere inferiore al campo 'Porti Via'";
                                        log.warn(msg + " per la pianificazione numero " + pianificazione.getId());
                                        DbPromoAgCellUtils.putValue(responseNode, "message", msg, false);
                                        responseNode.set("originalRow", node);
                                        return Response.status(Response.Status.PRECONDITION_FAILED).entity(responseNode.toString()).build();
                                    }
                                }
                                break;
                            case PianificazioneConstants.REFERENCE_NUM_SET_COLUMN:
                                valueValidated = checkNumSet(pianificazione, pickListValues, valueToBeUpdated);
                                break;
                            case PianificazioneConstants.REFERENCE_LINK:
                                if ("M018".equalsIgnoreCase(codiceMeccanica)) {
                                    valueValidated = validatorUtil.validateLink(pianificazione, valueToBeUpdated);
                                    if (!valueValidated) {
                                        pianificazionePromoUtil.resetPersistedValue(node, columnToBeUpdated,
                                                pianificazione, defValue, valueToBeUpdated);
                                        String msg = "Il campo 'Link' non può ripetersi nelle promozioni nello stesso range di date";
                                        log.warn(msg + " della pianificazione numero " + pianificazione.getId());
                                        DbPromoAgCellUtils.putValue(responseNode, "message",
                                                msg + " della promozione corrente", false);
                                        responseNode.set("originalRow", node);
                                        return Response.status(Response.Status.PRECONDITION_FAILED).entity(responseNode.toString()).build();
                                    }
                                }
                                break;
                        }
                    }

                    if (valueValidated && !fieldLockedInConfiguration) {
                        Map<String, String> webservices = new HashMap<>();
                        // il valore è validato quindi, se il campo modificato è un campo chiave
                        // per una lista condizionale, allora devo aggiornar il webservice
                        // per recuperare la lista delle chiavi condizionali:
                        // columnToBeUpdated -> qui c'e' la colonna da cambiare
                        final List<CfgPianificazioneEntity> configurazioniConChiave = pianificazionePromoUtil
                                .getConfigurazioniWithChiave(new ArrayList<>(cfgPianificazione.getMuiCfgConfHeader()
                                                .getMuiCfgPianificaziones()),
                                        columnToBeUpdated);
                        if (configurazioniConChiave != null && !configurazioniConChiave.isEmpty()) {
                            // devo creare una mappa con
                            // chiave: configurazione.getMuiCfgPianificazioneCampi().getCampo()
                            // valore: webservice aggiornato
                            webservices = calcolaWebservicesPerCampoChiave(pianificazione, cfgPianificazione,
                                    columnToBeUpdated, valueToBeUpdated);
                        }
                        // in webservices ho in chiave la lista dei campi che devo annullare
                        // perche' dipendono da un valore cambiato
                        if (webservices != null && !webservices.isEmpty()) {
                            final AtomicBoolean result = new AtomicBoolean(true);
                            webservices.keySet().forEach(campo -> result.set(result.get() && promoPianificazioneEntityHelper
                                    .invokeSetterEntity(pianificazione, campo, null)));
                            if (!result.get()) {
                                log.error(
                                        "Errore durante il reset del valore della lista condizionare dopo l'update della chiave");
                            }
                        }

                        final boolean updateSuccess;
                        List<PromozionePianificazioneEntity> pianificazioni = new ArrayList<>();
                        pianificazioni.add(pianificazione);
                        List<PromozionePianificazioneEntity> updated = promoPianificazioneEntityHelper
                                .invokeSetterEntity(pianificazioni, columnToBeUpdated, valueToBeUpdated, cfgPianificazione);
                        // MG #5644 reset campo ClusterCliente quando pianificazione e' multitransazione
                        if (PianificazioneConstants.CAMPO_MULTITRANSAZIONE.equalsIgnoreCase(columnToBeUpdated)
                                && pianificazionePromoUtil.isClusterClienteDisabled(pianificazione)) {
                            pianificazione.setClusterCliente(null);
                        }
                        updateSuccess = (updated != null) && !updated.isEmpty();

                        if (updateSuccess) {
                            try {
                                if (PianificazioneConstants.REFERENCE_DATA_INIZIO_COLUMN.equalsIgnoreCase(columnToBeUpdated)
                                        || PianificazioneConstants.REFERENCE_DATA_FINE_COLUMN.equalsIgnoreCase(columnToBeUpdated)) {
                                    pianificazionePromoUtil.checkCodiceIniziativa(pianificazione);
                                }

                                pianificazionePromoUtil.updateCheckCompratore(pianificazione, true);
                                pianificazioneService.savePromozionePianificazioneEntity(pianificazione,
                                        getCurrentUser().getName());
                                promozioneTestataService.get()
                                        .persist(pianificazione.getPromozioneTestataEntity());
                                // controllare se la cella e' editabile. Se lo e' allora controlla lo stile
                                // altrimenti vince sempre l'editabilita'
                                final PianificazioneSecurityEnum sec = PianificazioneSecurityEnum
                                        .fromString(cfgPianificazione.getSicurezza());
                                boolean editable = (sec != null) && sec.isEditable();
                                final int uploadEnabled = pianificazione.getPromozioneTestataEntity()
                                        .getCanalePromozioneEntity().getAbilitaUpload();
                                final Boolean logoMessaggioEnabled = pianificazione.getPromozioneTestataEntity()
                                        .getCanalePromozioneEntity().getFlLogoMessaggio();
                                final List<CfgPianificazioneEntity> configurazioni = promoConfigurationHelper
                                        .getConfigurazioniSorted(pianificazione.getPromozioneTestataEntity());
                                final String statoPromozione = PromoAcl.getCodiceStatoPromozione(
                                        pianificazione.getPromozioneTestataEntity());
                                // Devo costruire un JSONArray di risposta
                                final PlanningColumnDef dynamicColumnDef = (PlanningColumnDef) dynamicColumnDefFactory
                                        .getDynamicColumnDef(DynamicColumnEnum.PIANIFICAZIONE.getField());

                                final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
                                final boolean isUserAdmin = isUserAdmin();
                                final List<String> codiciGruppo = userDTO.getGruppi();
                                if (cfgPianificazione.getPropaga()) {
                                    // c'era la propagazione, ho aggiornato la/le promo ed i figli
                                    List<PromozionePianificazioneEntity> temp = new ArrayList<>();
                                    updated.forEach(p -> {
                                        temp.add(p);
                                        temp.addAll(p.getChildren());
                                    });
                                    updated = temp;
                                }
                                for (PromozionePianificazioneEntity p : updated) {
                                    final JsonNode objectNode = dynamicColumnDef.mapPianificazioneToObjectNode(
                                            p.getPromozioneTestataEntity(),
                                            p,
                                            editable,
                                            uploadEnabled,
                                            logoMessaggioEnabled,
                                            configurazioni,
                                            statoPromozione,
                                            isUserAdmin,
                                            codiciGruppo,
                                            calcolaWebservicesPerPianificazione(p.getPromozioneTestataEntity(), p),
                                            // devo comunque calcolare, per ogni riga aggiornata, la mappa dei webservices dinamici
                                            pianificazionePromoUtil.isPianificazioneLockedOnDataInizio(p),
                                            pianificazionePromoUtil.isPianificazioneLockedOnDataFine(p)
                                    );
                                    final JsonNode cell = objectNode.get(columnToBeUpdated);
                                    boolean invisible = false;
                                    if (cell != null) {
                                        final JsonNode invisibleValue = cell.get("invisible");
                                        if (invisibleValue != null) {
                                            try {
                                                invisible = invisibleValue.asBoolean();
                                            } catch (Exception e) {
                                                log.warn("invisible node value should be boolean but is " + invisibleValue.asText());
                                            }
                                        }
                                    }
                                    arrayNode.add(objectNode);
                                }
                                DbPromoAgCellUtils.putValue(responseNode, "updateMessage", "Aggiornamento effettuato",
                                        false);
                                responseNode.set("rowData", arrayNode);
                            } catch (final Exception e) {
                                pianificazionePromoUtil.resetPersistedValue(node, columnToBeUpdated,
                                        pianificazione, defValue, valueToBeUpdated);
                                log.error(String.format("Errore update tabella MUI_PROMOZIONE_PIANIFICAZIONE id %s",
                                        pianificazione.getId()), e);
                                DbPromoAgCellUtils.putValue(responseNode, "message",
                                        "Caricamento fallito: contattare l'assistenza", false);
                                responseNode.set("originalRow", node);
                                return Response.status(Response.Status.PRECONDITION_FAILED).entity(responseNode.toString()).build();
                            }
                        } else {
                            if (!valueValidated) {
                                pianificazionePromoUtil.resetPersistedValue(node, columnToBeUpdated,
                                        pianificazione, defValue, valueToBeUpdated);
                                log.warn("Validazione campo errata");
                                DbPromoAgCellUtils.putValue(responseNode, "message",
                                        "Caricamento fallito: validazione campo errata", false);
                            } else {
                                log.warn("Caricamento fallito");
                                DbPromoAgCellUtils.putValue(responseNode, "message",
                                        "Caricamento fallito: contattare l'assistenza", false);
                            }
                            responseNode.set("originalRow", node);
                            return Response.status(Response.Status.PRECONDITION_FAILED).entity(responseNode.toString()).build();
                        }
                    } else {
                        pianificazionePromoUtil.resetPersistedValue(node, columnToBeUpdated, pianificazione,
                                defValue, valueToBeUpdated);
                        log.warn("Validazione campo errata");
                        DbPromoAgCellUtils.putValue(responseNode, "message",
                                "Caricamento fallito: validazione campo errata", false);
                        responseNode.set("originalRow", node);
                        return Response.status(Response.Status.PRECONDITION_FAILED).entity(responseNode.toString()).build();
                    }
                }
            }
        } else {
            log.warn(String.format("Invalid request values for columnToBeUpdated %s and valueToBeUpdated %s",
                    node.get("columnToBeUpdated"), node.get("valueToBeUpdated")));
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }

        response = Response.ok(responseNode.toString()).build();
        return response;
    }

    Map<String, String> calcolaWebservicesPerPianificazione(PromozioneTestataEntity testata,
                                                            PromozionePianificazioneEntity pianificazione) {
        Map<String, String> webservices = new HashMap<>();
        CfgConfHeaderEntity header = testata
                .getMuiCfgSetPianificazione().getMuiCfgConfHeaders().stream().filter(c -> c.getMeccanicaEntity()
                        .getCodiceMeccanica().equals(pianificazione.getMeccanicaEntity().getCodiceMeccanica()))
                .findFirst().orElse(null);
        if (header != null) {
            final List<CfgPianificazioneEntity> configurazioniConChiave = pianificazionePromoUtil
                    .getConfigurazioniWithChiave(
                            new ArrayList<>(header.getMuiCfgPianificaziones()));
            if (configurazioniConChiave != null && !configurazioniConChiave.isEmpty()) {
                // devo creare una mappa con
                // chiave: configurazione.getMuiCfgPianificazioneCampi().getCampo()
                // valore: webservice aggiornato
                final PlanningColumnDef dynamicColumnDef = (PlanningColumnDef) dynamicColumnDefFactory
                        .getDynamicColumnDef(DynamicColumnEnum.PIANIFICAZIONE.getField());
                webservices = configurazioniConChiave.stream()
                        .collect(
                                Collectors.toMap(c -> c.getMuiCfgPianificazioneCampi().getCampo(),
                                        c -> dynamicColumnDef.setServiceValues(c.getTipoLista(), testata.getId(),
                                                testata.getCanalePromozioneEntity().getId(),
                                                pianificazione.getMeccanicaEntity().getId(), pianificazione.getId(),
                                                visualizzaPianificazioneHelper
                                                        .invokeGetterEntity(c.getChiave(), pianificazione).toString()),
                                        (a, b) -> b));
            }
        }
        return webservices;
    }

    Map<String, String> calcolaWebservicesPerCampoChiave(PromozionePianificazioneEntity pianificazione,
                                                         CfgPianificazioneEntity configurazione, String campoChiave, Object valore) {
        Map<String, String> webservices = new HashMap<>();
        final List<CfgPianificazioneEntity> configurazioniConChiave = pianificazionePromoUtil
                .getConfigurazioniWithChiave(new ArrayList<>(configurazione.getMuiCfgConfHeader().getMuiCfgPianificaziones()), campoChiave);
        if (configurazioniConChiave != null && !configurazioniConChiave.isEmpty()) {
            // devo creare una mappa con
            // chiave: configurazione.getMuiCfgPianificazioneCampi().getCampo()
            // valore: webservice aggiornato
            final PlanningColumnDef dynamicColumnDef = (PlanningColumnDef) dynamicColumnDefFactory
                    .getDynamicColumnDef(DynamicColumnEnum.PIANIFICAZIONE.getField());
            webservices = configurazioniConChiave.stream()
                    .collect(Collectors.toMap(c -> c.getMuiCfgPianificazioneCampi().getCampo(),
                            c -> dynamicColumnDef.setServiceValues(c.getTipoLista(),
                                    pianificazione.getPromozioneTestataEntity().getId(), // promo id
                                    pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getId(), // canale
                                    pianificazione.getMeccanicaEntity().getId(), // meccanica
                                    pianificazione.getId(), visualizzaPianificazioneHelper
                                            .invokeGetterEntity(c.getChiave(), pianificazione).toString()),
                            (a, b) -> b));

        }
        return webservices;
    }

    private Response resetCambioMeccanica(ObjectNode responseNode, JsonNode node,
                                          PromozionePianificazioneEntity pianificazione) {
        DbPromoAgCellUtils.putValue(node, PianificazioneConstants.CAMPO_PIANIFICAZIONE_MECCANICA,
                String.valueOf(pianificazione.getMeccanicaEntity().getId()), true);
        DbPromoAgCellUtils.putValue(responseNode, "message",
                "Impossibile cambiare meccanica alla riga di pianificazione", false);
        responseNode.set("originalRow", node);
        return Response.status(Response.Status.PRECONDITION_FAILED).entity(responseNode.toString()).build();
    }

    private boolean cambioMeccanica(PromozionePianificazioneEntity pianificazione, List<CanalePromozioneEntity> canali,
                                    Long idMeccanicaUpdated, String user) {
        // 1 - Check tipo riga tipo elemento
        if (!PianificazioneRowTypeEnum.ELEMENTO.getTypeCode().equals(pianificazione.getTipoRiga().getCodiceTipo())) {
            log.error("Errore cambio meccanica su tipo riga non ELEMENTO");
            return false;
        }

        final PromozioneTestataEntity testata = pianificazione.getPromozioneTestataEntity();

        // 2 - Check campo editabile in configurazione
        List<CfgPianificazioneEntity> configurazioni = configurationService.findBySetAndMeccanicaAndCampoAndTipoRiga(
                testata.getMuiCfgSetPianificazione(), pianificazione.getMeccanicaEntity(),
                PianificazioneConstants.CAMPO_PIANIFICAZIONE_MECCANICA, PianificazioneRowTypeEnum.ELEMENTO);
        if (configurazioni == null || configurazioni.isEmpty()) {
            log.error(String.format("Campo %s non configurato per meccanica %s",
                    PianificazioneConstants.CAMPO_PIANIFICAZIONE_MECCANICA,
                    pianificazione.getMeccanicaEntity().getLabel()));
            return false;
        } else if (configurazioni.size() > 1) {
            log.warn(String.format("Campo %s configurato %d volte per la meccanica %s; prendo il primo",
                    PianificazioneConstants.CAMPO_PIANIFICAZIONE_MECCANICA, configurazioni.size(),
                    pianificazione.getMeccanicaEntity().getLabel()));
        }
        CfgPianificazioneEntity cfg = configurazioni.get(0);
        if (PianificazioneSecurityEnum.READ.getSecurity().equals(cfg.getSicurezza())) {
            log.error(String.format("Campo %s configurato come READ-ONLY, impossibile modificare",
                    PianificazioneConstants.CAMPO_PIANIFICAZIONE_MECCANICA));
            return false;
        }

        // 3 - Valido la nuova meccanica
        final MeccanicheEntity meccanica = meccanicheUtilInstance.get()
                .getMeccanicheDisponibili(testata, canali, PlanningLevelEnum.ELEMENTO,
                        ElementType.TOTALE.getDescription().equals(pianificazione.getTipoElemento()))
                .stream()
                .filter(m -> idMeccanicaUpdated.equals(m.getId())).findFirst().orElse(null);
        if (meccanica == null) {
            log.error(String.format("Meccanica %d non impostabile su nuova pianificazione", idMeccanicaUpdated));
            return false;
        }

        // 4 - Creo nuova pianificazione con meccanica impostata ed elemento della
        // pianificazione corrente
        final PromozionePianificazioneEntity entity = new PromozionePianificazioneEntity();
        entity.setMeccanicaEntity(meccanica);
        entity.setTipoRiga(pianificazione.getTipoRiga());
        entity.setTipoElemento(pianificazione.getTipoElemento());
        entity.setCodiceElemento(pianificazione.getCodiceElemento());
        entity.setElemento(pianificazione.getElemento());
        // Data inizio e data fine li prendo dal padre (se esiste), altrimenti dalla
        // testata
        entity.setDataInizio(
                pianificazione.getDataInizio() != null ? pianificazione.getDataInizio() : testata.getDataInizio());
        entity.setDataFine(pianificazione.getDataFine() != null ? pianificazione.getDataFine() : testata.getDataFine());

        // 5 - Inserisco i default values
        // - tolgo dai default quelli gia' settati
        // - risolvo eventuali default di lookup
        final Map<String, String> defaultValues = pianificazioneService.getDefaultValues(testata.getId(),
                meccanica.getId(), PlanningLevelEnum.ELEMENTO.getCode());
        final Map<String, Field> mappedFields = pianificazioneService.getMappedFields();
        defaultValues.entrySet().removeIf(entry -> {
            final String fieldName = entry.getKey();
            final Object value = visualizzaPianificazioneHelper.invokeGetterEntity(fieldName, entity);
            return value != null;
        });
        lookupUtilsInstance.get().resolveLookupValues(entity, defaultValues);
        validatorUtil.validateDefaults(entity, defaultValues, false);
        promoPianificazioneEntityHelper.invokeSetter(entity, mappedFields, defaultValues);

        // 6 - Aggiungo nuova pianificazione alla testata
        testata.addPromozionePianificazioneEntity(entity);

        try {
            // 7 - Persisto la nuova pianificazione e la testata
            pianificazioneService.savePromozionePianificazioneEntity(entity, user);
            promozioneTestataService.get().persist(testata);

            // 8 - Rimuovo pianificazione corrente
            // - check_pianificazione associati
            // - set flag FL_MODIFICA=1 in MUI_CHECK_COMPRATORE
            pianificazionePromoUtil.delete(pianificazione, user);

            // 9 - Persisto la testata
            promozioneTestataService.get().persist(testata);
        } catch (Exception ex) {
            log.error("Error cambio meccanica", ex);
            return false;
        }
        return true;
    }



    @GET
    @Path("/list/buonoScontoProgressivoCondizionale/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBuonoScontoProgressivoCondizionale(@PathParam("idPianificazione") String idPianificazione,
                                                          @QueryParam("key") String chiaveCondizionale,
                                                          @QueryParam("contesto") String context) {
        if (StringUtils.isEmpty(idPianificazione)) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }

        final Long id = Long.valueOf(idPianificazione);
        final PromozionePianificazioneEntity pianificazione = pianificazioneService.getPromoPianificazoneById(id);
        if (pianificazione == null) {
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("message", " pianificazione inesistente");
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(node.toString()).build();
        }
        if (!security.isAccessible(pianificazione.getPromozioneTestataEntity(), getApplicationUser(context))) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione non accessibile").asJson()).build();
        }

        // devo trovare la configurazione per la pianificazione, campo buono sconto
        // progressivo
        final List<CfgPianificazioneEntity> configurazioni = configurationService.findBySetAndMeccanicaAndCampo(
                pianificazione.getPromozioneTestataEntity().getMuiCfgSetPianificazione(),
                pianificazione.getMeccanicaEntity(), PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN);

        if (!configurazioni.isEmpty()) {
            final CfgPianificazioneEntity configurazione = configurazioni.get(0);
            if (configurazioni.size() > 1) {
                log.warn(String.format("il campo %s e' configurato %d volte per la meccanica %s nel canale %s",
                        PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN, configurazioni.size(),
                        pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                        pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getCodiceCanale()));
            }
            if (configurazione.getListaCondizionale() != null) {
                Map<String, String> listaCondizionale;
                try {
                    //noinspection unchecked
                    listaCondizionale = JsonUtils.getMapper().readValue(configurazione.getListaCondizionale(), Map.class);
                } catch (Exception e) {
                    log.error(String.format("Errore durante il parse della mappa condizionale %s",
                            configurazione.getListaCondizionale()), e);
                    return Response.status(Response.Status.PRECONDITION_FAILED).build();
                }
                String lista = null;
                if (listaCondizionale != null) {
                    lista = listaCondizionale.get(chiaveCondizionale);
                }
                // ordina i valori e mandali su
                List<Integer> values = calcolaListaConSovrapposizioni(pianificazione.getPromozioneTestataEntity(),
                        lista, Integer.valueOf(chiaveCondizionale), pianificazione.getBuonoScontoProgressivo());
                Collections.sort(values);
                final ObjectNode node = JsonUtils.getMapper().createObjectNode();
                try {
                    node.put("remoteValues", JsonUtils.getMapper().writeValueAsString(values));
                    return Response.ok().entity(node.toString()).build();
                } catch (final JsonProcessingException e) {
                    log.error("Error mapping result to JSon", e);
                    return Response.serverError().build();
                }

            } else {
                final ObjectNode node = JsonUtils.getMapper().createObjectNode();
                node.put("message", "Configurazione errata: manca il valore 'LISTA' ");
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(node.toString()).build();
            }

        } else {
            // precondition failed
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("message", PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN + " not configured");
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(node.toString()).build();
        }
    }

    @GET
    @Path("/list/buonoScontoProgressivo/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBuonoScontoProgressivo(@PathParam("idPianificazione") String idPianificazione,
                                              @QueryParam("contesto") String context) {
        if (StringUtils.isEmpty(idPianificazione)) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }

        final Long id = Long.valueOf(idPianificazione);
        final PromozionePianificazioneEntity pianificazione = pianificazioneService.getPromoPianificazoneById(id);
        if (pianificazione == null) {
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("message", " pianificazione inesistente");
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(node.toString()).build();
        }

        if (!security.isAccessible(pianificazione.getPromozioneTestataEntity(), getApplicationUser(context))) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione non accessibile").asJson()).build();
        }

        // devo trovare la configurazione per la pianificazione, campo buono sconto
        // progressivo
        final List<CfgPianificazioneEntity> configurazioni = configurationService.findBySetAndMeccanicaAndCampo(
                pianificazione.getPromozioneTestataEntity().getMuiCfgSetPianificazione(),
                pianificazione.getMeccanicaEntity(), PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN);

        if (!configurazioni.isEmpty()) {
            final CfgPianificazioneEntity configurazione = configurazioni.get(0);
            if (configurazioni.size() > 1) {
                log.warn(String.format("il campo %s e' configurato %d volte per la meccanica %s nel canale %s",
                        PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN, configurazioni.size(),
                        pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                        pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getCodiceCanale()));
            }
            if (configurazione.getLista() != null) {
                // ordina i valori e mandali su
                List<Integer> values = calcolaListaConSovrapposizioni(pianificazione.getPromozioneTestataEntity(),
                        configurazione.getLista(), pianificazione.getBuonoScontoRadice(),
                        pianificazione.getBuonoScontoProgressivo());
                Collections.sort(values);
                final ObjectNode node = JsonUtils.getMapper().createObjectNode();
                try {
                    node.put("remoteValues", JsonUtils.getMapper().writeValueAsString(values));
                    return Response.ok().entity(node.toString()).build();
                } catch (final JsonProcessingException e) {
                    log.error("Error mapping result to JSon", e);
                    return Response.serverError().build();
                }

            } else {
                final ObjectNode node = JsonUtils.getMapper().createObjectNode();
                node.put("message", "Configurazione errata: manca il valore 'LISTA' ");
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(node.toString()).build();
            }

        } else {
            // precondition failed
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("message", PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN + " not configured");
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(node.toString()).build();
        }
    }

    @GET
    @Path("/list/link/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLink(@PathParam("idPianificazione") String idPianificazione, @QueryParam("contesto") String context) {
        if (StringUtils.isEmpty(idPianificazione)) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }

        final Long id = Long.valueOf(idPianificazione);
        final PromozionePianificazioneEntity pianificazione = pianificazioneService.getPromoPianificazoneById(id);
        if (pianificazione == null) {
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("message", " pianificazione inesistente");
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(node.toString()).build();
        }

        if (!security.isAccessible(pianificazione.getPromozioneTestataEntity(), getApplicationUser(context))) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione non accessibile").asJson()).build();
        }

        final List<CfgPianificazioneEntity> configurazioni = configurationService.findBySetAndMeccanicaAndCampo(
                pianificazione.getPromozioneTestataEntity().getMuiCfgSetPianificazione(),
                pianificazione.getMeccanicaEntity(), PianificazioneConstants.REFERENCE_LINK);

        if (!configurazioni.isEmpty()) {
            final CfgPianificazioneEntity configurazione = configurazioni.get(0);
            if (configurazioni.size() > 1) {
                log.warn(String.format("il campo %s e' configurato %d volte per la meccanica %s nel canale %s",
                        PianificazioneConstants.REFERENCE_LINK, configurazioni.size(),
                        pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                        pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getCodiceCanale()));
            }
            if (configurazione.getLista() != null) {
                List<String> values = linkHelperInstance.get().getAvailableValues(configurazione.getLista(), pianificazione);
                final ObjectNode node = JsonUtils.getMapper().createObjectNode();
                try {
                    node.put("remoteValues", JsonUtils.getMapper().writeValueAsString(values));
                    return Response.ok().entity(node.toString()).build();
                } catch (final JsonProcessingException ex) {
                    log.error("Error mapping result to JSON", ex);
                    return Response.serverError().build();
                }
            } else {
                final ObjectNode node = JsonUtils.getMapper().createObjectNode();
                node.put("message", String.format("Configurazione errata: manca il valore 'LISTA' per il campo '%s'",
                        PianificazioneConstants.REFERENCE_LINK));
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(node.toString()).build();
            }
        } else {
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("message", PianificazioneConstants.REFERENCE_LINK + " not configured");
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(node.toString()).build();
        }
    }

    /**
     * Calcola la lista dei progressivi validi dato codice promozione, lista da
     * filtrare, radice
     *
     * @return
     */
    List<Integer> calcolaListaConSovrapposizioni(PromozioneTestataEntity promozione, String lista, Integer radice,
                                                 Integer valoreCorrente) {
        final PicklistUtils picklistUtils = new PicklistUtils();
        List<Integer> values = picklistUtils.defineListaCellEditorAsInts(lista);
        List<String> pickListValues = pianificazionePromoUtil.getCorrectFormatPickListValues(lista);
        values = pianificazionePromoUtil.getAvailableProgressiveDiscountCodesBuoniPotenziamento(promozione, radice,
                pickListValues, values);
        // aggiungi il valore corrente, solo se non gia' presente
        if ((valoreCorrente != null) && !values.contains(valoreCorrente)) {
            values.add(valoreCorrente);
        }
        // ordina i valori e mandali su
        Collections.sort(values);

        return values;
    }

    @POST
    @Path("/selezioneTab/{idPromozioneCorrente}/{meccanicaId}/article/rowData/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getSelezioneArticoloFilteredRowData(@PathParam("idPromozioneCorrente") String promozioneId,
                                                        @PathParam("meccanicaId") String meccanicaId, @QueryParam("contesto") String contesto,
                                                        PlanningArticleMultiFilterParam params, @Context HttpServletRequest request) throws Exception {
        try {
            final PromozioneCheckDto promozioneCheckDto = validatePromozioneFromRequest(promozioneId, meccanicaId,
                    contesto, params.getGruppoSelected());
            if (promozioneCheckDto.isError()) {
                return Response.status(promozioneCheckDto.getResponseStatus())
                        .entity(new Message(promozioneCheckDto.getErrorMessage()).asJson()).build();
            }
            final DynamicColumnDef dynamicColumnDef = dynamicColumnDefFactory
                    .getDynamicColumnDef(DynamicColumnEnum.ARTICOLO.getField());
            if (dynamicColumnDef == null) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Tipo Elemento non supportato '%s'", DynamicColumnEnum.ARTICOLO.getField())
                                .asJson())
                        .build();
            }
            validateFilteredItemsParams(params);
            return Response.ok()
                    .entity(dynamicColumnDef.generateRowDataByPromoConfiguration(promozioneCheckDto.getTestata(),
                            promozioneCheckDto.getIdMeccanica(), params, isUserAdmin(),
                            promozioneCheckDto.getCodiciGruppo()).toString())
                    .build();
        } catch (Exception ex) {
            log.error(String.format("Error getting JSON rowData for 'Pianificazione Promozione' with id %s",
                    promozioneId), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @GET
    @Path("/planning/rowData/{elementType}/{promoId}/{radioChecked}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanningRowDataFiltered(@PathParam("elementType") String elementType,
                                               @PathParam("promoId") String promoId, @PathParam("radioChecked") String radioChecked,
                                               @QueryParam("contesto") String context, @Context HttpServletRequest request) throws Exception {

        long idPromozione;
        try {
            idPromozione = Long.parseLong(promoId);
        } catch (final Exception e) {
            log.error("error converting promozione pianificazione id to long " + promoId, e);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione inesistente").asJson()).build();
        }
        if (!security.isAccessible(promoService.findById(idPromozione), getApplicationUser(context))) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione non accessibile").asJson()).build();
        }

        final DynamicColumnDef dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef(elementType);
        if (dynamicColumnDef == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Tipo Elemento non supportato '%s'", elementType).asJson()).build();
        }

        if (DynamicColumnEnum.PIANIFICAZIONE.getField().equals(elementType)
                && PlanningRadioButtonEnum.MASTER.getValue().equals(radioChecked)) {
            return Response.ok(
                            dynamicColumnDef.generateRowDataFilteredByPromoConfiguration(String.valueOf(idPromozione), radioChecked))
                    .build();
        }


        return Response.ok(dynamicColumnDef.generateRowDataByPromoConfiguration(String.valueOf(idPromozione), isUserAdmin(),
                getApplicationUser(context).getGruppi(), null)).build();
    }

    @GET
    @Path("/list/numeroSet/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumeroSet(@PathParam("idPianificazione") String idPianificazione, @QueryParam("contesto") String context) {
        if (StringUtils.isEmpty(idPianificazione)) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }

        final Long id = Long.valueOf(idPianificazione);
        final PromozionePianificazioneEntity pianificazione = pianificazioneService.getPromoPianificazoneById(id);
        if (pianificazione == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message(" pianificazione inesistente").asJson()).build();
        }

        final PromozioneTestataEntity testataEntity = pianificazione.getPromozioneTestataEntity();
        if (!security.isAccessible(testataEntity, getApplicationUser(context))) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione non accessibile").asJson()).build();
        }

        // devo trovare la configurazione per la pianificazione, campo 'NUM_SET'
        final List<CfgPianificazioneEntity> configurazioni = configurationService.findBySetAndMeccanicaAndCampo(
                pianificazione.getPromozioneTestataEntity().getMuiCfgSetPianificazione(),
                pianificazione.getMeccanicaEntity(), PianificazioneConstants.REFERENCE_NUM_SET_COLUMN);
        if (configurazioni.isEmpty()) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(
                            new Message("%s not configured", PianificazioneConstants.REFERENCE_NUM_SET_COLUMN)
                                    .asJson())
                    .build();
        }
        if (configurazioni.size() > 1) {
            log.warn(String.format("il campo %s e' configurato %d volte per la meccanica %s nel canale %s",
                    PianificazioneConstants.REFERENCE_NUM_SET_COLUMN, configurazioni.size(),
                    pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                    testataEntity.getCanalePromozioneEntity().getCodiceCanale()));
        }
        final CfgPianificazioneEntity configurazione = configurazioni.get(0);
        if (configurazione.getLista() == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Configurazione errata: manca il valore 'LISTA' ").asJson()).build();
        }
        return getNumeroSet(pianificazione, configurazione.getLista(), Arrays.asList("M141", "M142", "M143"));
    }

    @GET
    @Path("/list/numeroSetCondizionale/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumeroSetCondizionale(@PathParam("idPianificazione") String idPianificazione,
                                             @QueryParam("contesto") String context) {
        if (StringUtils.isEmpty(idPianificazione)) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }

        final Long id = Long.valueOf(idPianificazione);
        final PromozionePianificazioneEntity pianificazione = pianificazioneService.getPromoPianificazoneById(id);
        if (pianificazione == null) {
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("message", " pianificazione inesistente");
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(node.toString()).build();
        }
        if (!security.isAccessible(pianificazione.getPromozioneTestataEntity(), getApplicationUser(context))) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione non accessibile").asJson()).build();
        }

        // devo trovare la configurazione per la pianificazione, campo cod set
        // progressivo
        final List<CfgPianificazioneEntity> configurazioni = configurationService.findBySetAndMeccanicaAndCampo(
                pianificazione.getPromozioneTestataEntity().getMuiCfgSetPianificazione(),
                pianificazione.getMeccanicaEntity(), PianificazioneConstants.REFERENCE_NUM_SET_COLUMN);
        if (!configurazioni.isEmpty()) {
            String chiaveCondizionale = pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getCodiceCanale().toString();
            final CfgPianificazioneEntity configurazione = configurazioni.get(0);
            if (configurazioni.size() > 1) {
                log.warn(String.format("il campo %s e' configurato %d volte per la meccanica %s nel canale %s",
                        PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN, configurazioni.size(),
                        pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                        pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getCodiceCanale()));
            }
            if (configurazione.getListaCondizionale() != null) {
                Map<String, String> listaCondizionale;
                try {
                    //noinspection unchecked
                    listaCondizionale = JsonUtils.getMapper().readValue(configurazione.getListaCondizionale(), Map.class);
                } catch (Exception e) {
                    log.error("Errore durante il parse della mappa condizionale {}", configurazione.getListaCondizionale(), e);
                    return Response.status(Response.Status.PRECONDITION_FAILED).build();
                }
                String lista = null;
                if (listaCondizionale != null) {
                    lista = listaCondizionale.get(chiaveCondizionale);
                }
                if ( lista == null || lista.isEmpty()){
                    log.error("Errore durante la lettura della lista condizionale. Il canale {} non ha una lista configurata", chiaveCondizionale);
                    return Response.status(Response.Status.PRECONDITION_FAILED).build();
                }
                return getNumeroSet(pianificazione, lista, Arrays.asList("M141", "M142"));

            } else {
                final ObjectNode node = JsonUtils.getMapper().createObjectNode();
                node.put("message", "Configurazione errata: manca il valore 'LISTA CONDIZIONALE' ");
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(node.toString()).build();
            }
        } else {
            // precondition failed
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("message", PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN + " not configured");
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(node.toString()).build();
        }
    }

    private Response getNumeroSet(PromozionePianificazioneEntity pianificazione, String lista, List<String> meccaniche) {
        try {
            List<Integer> availableNumSet = meccaniche.stream().anyMatch(s->s.equals(pianificazione.getMeccanicaEntity().getCodiceMeccanica()))
                    ? numSetUtilsInstance.get().getAvailablePrecaricate(pianificazione.getPromozioneTestataEntity(), lista)
                    : numSetUtilsInstance.get().getAvailable(pianificazione.getPromozioneTestataEntity(), lista, null);
            if (pianificazione.getNumSet() != null) {
                Integer numSet = Integer.parseInt(pianificazione.getNumSet());
                if (!availableNumSet.contains(numSet)) {
                    availableNumSet.add(numSet);
                }
            }
            Collections.sort(availableNumSet);
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("remoteValues", JsonUtils.getMapper().writeValueAsString(availableNumSet));
            return Response.ok().entity(node.toString()).build();
        } catch (final Exception ex) {
            log.error("Error mapping result to JSON", ex);
            return Response.serverError().build();
        }
    }



    @GET
    @Path("/list/meccaniche/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMeccanicheDisponibili(@PathParam("idPianificazione") String idPianificazione,
                                             @QueryParam("contesto") String context) {
        long pianificazioneId;
        try {
            pianificazioneId = Long.parseLong(idPianificazione);
        } catch (Exception ex) {
            log.error(String.format("Error converting pianificazione id %s to long", idPianificazione), ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Pianificazione inesistente").asJson()).build();
        }
        try {
            final PromozionePianificazioneEntity pianificazione = pianificazioneService
                    .getPromoPianificazoneById(pianificazioneId);
            if (pianificazione == null) {
                log.error(String.format("Error getting pianificazione with id %s", idPianificazione));
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Pianificazione inesistente").asJson()).build();
            }
            final PromozioneTestataEntity testata = pianificazione.getPromozioneTestataEntity();
            final UserDTO userDTO = getApplicationUser(context);
            if (!security.isAccessible(testata, userDTO)) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Promozione non accessibile").asJson()).build();
            }
            final List<MeccanicheEntity> meccaniche = meccanicheUtilInstance.get().getMeccanicheDisponibili(testata,
                    userDTO.getCanali(), PlanningLevelEnum.ELEMENTO,
                    ElementType.TOTALE.getDescription().equals(pianificazione.getTipoElemento()));
            if (!meccaniche.contains(pianificazione.getMeccanicaEntity())) {
                meccaniche.add(pianificazione.getMeccanicaEntity());
            }
            final List<ComboBoxValues> items = comboBoxFactoryInstance.get().from(meccaniche, false, true);
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("remoteValues", JsonUtils.getMapper().writeValueAsString(items));
            return Response.ok().entity(node.toString()).build();
        } catch (Exception ex) {
            log.error(
                    String.format("Error getting available meccaniche for pianificazione with id %s", idPianificazione),
                    ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Impossibile recuperare meccaniche disponibili").asJson()).build();
        }
    }

    // FIXME: Questo ws non viene utilizzato ne' da JS ne' presente nelle righe
    // MUI_CFG_PIANIFICAZIONE.TIPO_LISTA
    // in tal caso si può rimuovere
    @GET
    @Path("/list/numeroRaggruppamento/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumeroRaggruppamento(@PathParam("idPianificazione") String idPianificazione,
                                            @QueryParam("contesto") String context) {
        final PicklistUtils picklistUtils = new PicklistUtils();
        if (StringUtils.isEmpty(idPianificazione)) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }

        final Long id = Long.valueOf(idPianificazione);
        final PromozionePianificazioneEntity pianificazione = pianificazioneService.getPromoPianificazoneById(id);
        if (pianificazione == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message(" pianificazione inesistente").asJson()).build();
        }

        final PromozioneTestataEntity testataEntity = pianificazione.getPromozioneTestataEntity();
        if (!security.isAccessible(testataEntity, getApplicationUser(context))) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione non accessibile").asJson()).build();
        }

        // devo trovare la configurazione per la pianificazione, campo numero
        // Raggruppamento
        final List<CfgPianificazioneEntity> configurazioni = cfgPianificazioneService
                .findPianificazioneByCanaleMeccanicaField(testataEntity.getCanalePromozioneEntity(),
                        pianificazione.getMeccanicaEntity(),
                        PianificazioneConstants.REFERENCE_NUMERO_RAGGRUPPAMENTO_COLUMN);
        if (configurazioni.isEmpty()) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(
                            new Message("%s not configured", PianificazioneConstants.REFERENCE_NUMERO_RAGGRUPPAMENTO_COLUMN)
                                    .asJson())
                    .build();
        }
        if (configurazioni.size() > 1) {
            log.warn(String.format("il campo %s e' configurato %d volte per la meccanica %s nel canale %s",
                    PianificazioneConstants.REFERENCE_NUMERO_RAGGRUPPAMENTO_COLUMN, configurazioni.size(),
                    pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                    testataEntity.getCanalePromozioneEntity().getCodiceCanale()));
        }
        final CfgPianificazioneEntity configurazione = configurazioni.get(0);
        if (configurazione.getLista() == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Configurazione errata: manca il valore 'LISTA' ")).build();
        }

        List<Integer> values = picklistUtils.defineListaCellEditorAsInts(configurazione.getLista());
        if (PianificazioneConstants.PRECARICATI_SU_CARTA
                .equals(testataEntity.getCanalePromozioneEntity().getDescrizione())) {
            // Num set: da 1 a 5 (scelta a tendina da configurazione)
            // Mostra solo i progressivi disponibili e non gia utilizzati a parità del campo
            // NumRaggruppamento
            // controllando la promozione corrente e il valore di NumSet
            // A parità di promozione numero raggruppamento e numero set devono essere
            // univoci
            values = pianificazionePromoUtil.getAvailableNumeroRaggruppamentoSuCarta(testataEntity,
                    pianificazione.getNumSet(),
                    pianificazionePromoUtil.getCorrectFormatPickListValues(configurazione.getLista()), values);
            // Aggiungo il valore persistito alla picklist perchè sia selezionabile
            values.add(Integer.parseInt(pianificazione.getNumRaggruppamento()));
        }
        // ordina i valori e mandali su
        Collections.sort(values);
        try {
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("remoteValues", JsonUtils.getMapper().writeValueAsString(values));
            return Response.ok().entity(node.toString()).build();
        } catch (final JsonProcessingException e) {
            log.error("Error mapping result to JSon", e);
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/list/clustercliente/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClusterCliente(@PathParam("idPianificazione") String idPianificazione,
                                      @QueryParam("contesto") String context) {
        if (StringUtils.isEmpty(idPianificazione)) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }

        final Long id = Long.valueOf(idPianificazione);
        final PromozionePianificazioneEntity pianificazione = pianificazioneService.getPromoPianificazoneById(id);
        if (pianificazione == null) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message(" pianificazione inesistente").asJson()).build();
        }
        final List<ComboBoxValues> items = comboBoxFactoryInstance.get().from(clusterClienteServiceInstance.get()
                .findByDataInizioAndDataFine(pianificazione.getDataInizio(), pianificazione.getDataFine()), true, true);
        final ObjectNode node = JsonUtils.getMapper().createObjectNode();
        try {
            node.put("remoteValues", JsonUtils.getMapper().writeValueAsString(items));
            return Response.ok().entity(node.toString()).build();
        } catch (final JsonProcessingException e) {
            log.error("Error mapping result to JSon", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Impossibile recuperare i cluster cliente disponibili").asJson()).build();

        }

    }

    @GET
    @Path("/list/iniziative/{idPianificazione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCodiciIniziative(@PathParam("idPianificazione") String idPianificazione) {
        if (StringUtils.isEmpty(idPianificazione)) {
            log.error("Missing 'idPianificazione' parameter");
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }
        Long id = null;
        try {
            id = Long.valueOf(idPianificazione);
        } catch (NumberFormatException ignored) {
            log.error(String.format("Invalid idPianificazione '%s'", idPianificazione));
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }
        final PromozionePianificazioneEntity pianificazione = pianificazioneService.getPromoPianificazoneById(id);
        if (pianificazione == null) {
            log.error(String.format("Cannot find pianificazione with id: '%d'", id));
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message(" pianificazione inesistente").asJson()).build();
        }
        final Date dataInizio = pianificazione.getDataInizio() != null
                ? pianificazione.getDataInizio()
                : pianificazione.getPromozioneTestataEntity().getDataInizio();
        final Date dataFine = pianificazione.getDataFine() != null
                ? pianificazione.getDataFine()
                : pianificazione.getPromozioneTestataEntity().getDataFine();
        final List<ComboBoxValues> items = comboBoxFactoryInstance.get().from(muiIniziativaServiceInstance.get()
                .findAllByDataInizioAndDataFine(dataInizio, dataFine), true, true);
        try {
            final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.put("remoteValues", JsonUtils.getMapper().writeValueAsString(items));
            return Response.ok().entity(objectNode.toString()).build();
        } catch (JsonProcessingException ex) {
            log.error("Error mapping result to JSON", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Impossibile recuperare le iniziative disponibili").asJson()).build();
        }
    }

    @POST
    @Path("/scarti/{idUpload}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadScarti(@PathParam("idUpload") String idUpload, @QueryParam("contesto") String context,
                                   @Context HttpServletRequest request) {

        if (StringUtils.isEmpty(idUpload)) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("missing id upload").asJson()).build();
        }

        try {
            final Long id = Long.valueOf(idUpload);
            final UploadFidayEntity upload = uploadFidatyService.findById(id);
            if (upload == null) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("complementari entry was not found").asJson()).build();
            }

            if (!security.isAccessible(upload.getPromozionePianificazioneEntity().getPromozioneTestataEntity(),
                    getApplicationUser(context))) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Promozione non accessibile").asJson()).build();
            }

            final File file = complementariUtils.getScarto(id);
            if ((file == null) || !file.canRead()) {
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("error reading file").asJson()).build();
            }

            return Response.ok(file).header("Content-Disposition", "attachment;filename=" + file.getName()).build();
        } catch (final Exception e) {
            log.error("Error downloading file scarti", e);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("id upload must be an integer").asJson()).build();
        }
    }

    @GET
    @Path("/confirmDialogCompratori/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getColumnDefConfermaPianificazione(@QueryParam("contesto") String context,
                                                       @Context HttpServletRequest request) {

        return loadColumnDefFromFile("columnDefConfermaPianificazione.json", "db_confirm_pianificazione_dialog",
                context);
    }

    @GET
    @Path("/confirmDialogCompratori/rowData/{promoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRowDataConfermaPianificazione(@PathParam("promoId") String promoId,
                                                     @Context HttpServletRequest request) {

        try {
            final PromozioneCheckDto promozioneCheckDto = validatePromozioneFromRequest(promoId, null);
            if (promozioneCheckDto.isError()) {
                return Response.status(promozioneCheckDto.getResponseStatus())
                        .entity(new Message(promozioneCheckDto.getErrorMessage()).asJson()).build();
            }
            final List<String> buyerCodes = new ArrayList<>();
            final PromozioneTestataEntity promo = promoService.findById(Long.parseLong(promoId));
            final List<Long> codiciItems = promo.getPromozionePianificazioneEntities().stream()
                    .filter(p -> "E".equalsIgnoreCase(p.getTipoRiga().getCodiceTipo()))
                    .map(PromozionePianificazioneEntity::getCodiceElemento).filter(Objects::nonNull)
                    .filter(s -> !s.isEmpty()).map(Long::parseLong).collect(Collectors.toList());

            final List<String> compratoriPresenti = new ArrayList<>();

//            final List<String> compratoriPresenti = getItemService().get().findByIds(codiciItems).stream()
//                    .map(ItemEntity::getCompratoreEntity).filter(Objects::nonNull)
//                    .map(CompratoreEntity::getCodiceCompratore).distinct().collect(Collectors.toList());

            int start = 0;
            int size = 1000;
            int rem = codiciItems.size() % size;
            int pass = (codiciItems.size() - rem) / size;

            for (int i = 0; i < pass; ++i) {
                start = i * size;
                compratoriPresenti.addAll(getItemService().get().findByIds(codiciItems.subList(start, start + size)).stream()
                        .map(ItemEntity::getCompratoreEntity).filter(Objects::nonNull)
                        .map(CompratoreEntity::getCodiceCompratore).distinct().collect(Collectors.toList()));
            }
            if (rem > 0) {
                start = pass * size;
                compratoriPresenti.addAll(getItemService().get().findByIds(codiciItems.subList(start, start + rem)).stream()
                        .map(ItemEntity::getCompratoreEntity).filter(Objects::nonNull)
                        .map(CompratoreEntity::getCodiceCompratore).distinct().collect(Collectors.toList()));
            }


            final List<String> checksComporatori = promo.getCheckCompratori().stream()
                    .map(CheckCompratoriEntity::getCompratoreEntity).map(CompratoreEntity::getCodiceCompratore)
                    .distinct().collect(Collectors.toList());
            final List<String> collect = Stream.concat(compratoriPresenti.stream(), checksComporatori.stream())
                    .distinct().collect(Collectors.toList());

            if (isUserAdmin()) {
                // Lista dei codici item
                buyerCodes.addAll(collect);
            } else {
                final List<String> ownedCompratori = muiServiceInstance.get()
                        .findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(promozioneCheckDto.getCodiciGruppo(),
                                PianificazioneSecurityEnum.WRITE);
                buyerCodes.addAll(collect.stream().filter(ownedCompratori::contains).collect(Collectors.toList()));
            }
            if (buyerCodes.isEmpty()) {
                return Response.ok(pianificazionePromoRowDataUtil
                        .createCompratoriRowData(Collections.emptyList())).build();
            } else {
                return Response.ok(pianificazionePromoRowDataUtil
                        .createCompratoriRowData(promoService.findAllBuyersByCodes(buyerCodes))).build();
            }
        } catch (Exception ex) {
            log.error("Error getting rowData for available Compratori", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/promoriferimento/articoli/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromoRiferimentoArticoliColumnDef(@QueryParam("contesto") String context,
                                                         @Context HttpServletRequest request) throws Exception {

        return loadColumnDefFromFile("pianificazione_promoRiferimentoArticoli_columnDef.json",
                "db_promorif_dialog_inserisci_selezione", context);
    }

    @GET
    @Path("/promoriferimento/articoli/rowData/{promoId}/{codicePromozione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromoRiferimentoArticoliRowData(@PathParam("promoId") String promoId,
                                                       @PathParam("codicePromozione") String codicePromozione, @QueryParam("contesto") String contesto,
                                                       @Context HttpServletRequest request) throws Exception {
        return getPromoRiferimentoArticoliRowData(promoId, codicePromozione, null, contesto, request);
    }

    @GET
    @Path("/promoriferimento/articoli/rowData/{promoId}/{codicePromozione}/{codiceGruppoSelezionato}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromoRiferimentoArticoliRowData(@PathParam("promoId") String promoId,
                                                       @PathParam("codicePromozione") String codicePromozione, @PathParam("codiceGruppoSelezionato") String codiceGruppoSelezionato, @QueryParam("contesto") String contesto,
                                                       @Context HttpServletRequest request) throws Exception {

        try {
            final PromozioneCheckDto promozioneCheckDto = validatePromozioneFromRequest(promoId, contesto, codiceGruppoSelezionato);
            if (promozioneCheckDto.isError()) {
                return Response.status(promozioneCheckDto.getResponseStatus())
                        .entity(new Message(promozioneCheckDto.getErrorMessage()).asJson()).build();
            }
            List<MuiPromoArticoliDbPromoEntity> entities = muiPromoArticoliDbPromoServiceInstance.get()
                    .findAllByCodicePromozione(codicePromozione);
            if (!isUserAdmin()) {
                final List<Long> idItems = entities.stream().map(e -> e.getId().getCodiceItem())
                        .collect(Collectors.toList());
                final List<Long> keepedIdItems = securityUtilInstance.get().keepAddableItems(idItems,
                        promozioneCheckDto.getTestata(), promozioneCheckDto.getCodiciGruppo());
                entities = entities.stream().filter(i -> {
                    try {
                        return keepedIdItems.contains(i.getId().getCodiceItem());
                    } catch (Exception ex) {
                        log.error(String.format("Unable to parse codiceItem '%s' to Long", i.getId().getCodiceItem()),
                                ex);
                        return false;
                    }
                }).collect(Collectors.toList());
            }
            return Response.ok(pianificazionePromoRowDataUtil.createPromoRiferimentoArticoliRowData(entities)).build();
        } catch (Exception ex) {
            log.error(String.format("Error getting JSON rowData for 'Articolo Promo Riferimento' %s", codicePromozione),
                    ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @GET
    @Path("/planoriferimento/articoli/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanoRiferimentoArticoliColumnDef(@QueryParam("contesto") String context,
                                                         @Context HttpServletRequest request) throws Exception {

        return loadColumnDefFromFile("pianificazione_planoRiferimentoArticoli_columnDef.json",
                "db_planorif_dialog_inserisci_selezione", context);
    }

    @GET
    @Path("/planoriferimento/articoli/rowData/{promoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanoRiferimentoArticoliRowData(@PathParam("promoId") String promoId,
                                                       @QueryParam("contesto") String contesto, @Context HttpServletRequest request) throws Exception {

        try {
            final PromozioneCheckDto promozioneCheckDto = validatePromozioneFromRequest(promoId, contesto);
            if (promozioneCheckDto.isError()) {
                return Response.status(promozioneCheckDto.getResponseStatus())
                        .entity(new Message(promozioneCheckDto.getErrorMessage()).asJson()).build();
            }

            final PromozioneTestataEntity promo = promozioneTestataService.get().findById(Long.parseLong(promoId));
            List<MuiPlanoArticoliDbPromoEntity> entities = getExtendedArticoliPlano(promo);
            if (!isUserAdmin()) {
                final List<Long> idItems = entities.stream().map(e -> e.getId().getCodiceItem())
                        .collect(Collectors.toList());
                final List<Long> keepedIdItems = securityUtilInstance.get().keepAddableItems(idItems,
                        promozioneCheckDto.getTestata(), promozioneCheckDto.getCodiciGruppo());
                entities = entities.stream().filter(i -> keepedIdItems.contains(i.getId().getCodiceItem()))
                        .collect(Collectors.toList());
            }
            return Response.ok(pianificazionePromoRowDataUtil.createPlanoRiferimentoArticoliRowData(entities)).build();
        } catch (Exception ex) {
            log.error(String.format("Error getting JSON rowData for 'Articolo Plano Riferimento' for promo with id %s",
                    promoId), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    public List<MuiPlanoArticoliDbPromoEntity> getExtendedArticoliPlano(@NonNull final PromozioneTestataEntity promo) {
        /*
         * per ogni planogramma: 1. tira fuori gli articoli 2. aggiungi i dati extra per
         * visualizzazione 2. mettili in un array
         */
        List<MuiPlanoArticoliDbPromoEntity> result = new ArrayList<>();
        if (promo.getPlanogrammi() != null && !promo.getPlanogrammi().isEmpty()) {
            promo.getPlanogrammi().forEach(p -> muiPlanoArticoliDbPromoServiceInstance.get().findAllByIdPlano(p.getIdPlano()).forEach(a -> {
                a.setDescrizionePlanogramma(
                        String.format("%s (%s)", p.getCodicePlano(), p.getCategoriaEspositiva()));
                result.add(a);
            }));
        }
        return result;
    }

    /**
     * Se il valore del singolo filtro e' 0L, lo setta a null, per non includerlo
     * nella query di ricerca
     *
     * @param params parametri filtro ricerca articoli
     */
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

    private boolean checkNumSet(PromozionePianificazioneEntity pianificazione, String lista,
                                Object valueToBeUpdated) {
        final String codiceMeccanica = pianificazione.getMeccanicaEntity().getCodiceMeccanica();
        CfgPianificazioneEntity configurazione = promoConfigurationHelper.getConfigurationForField(
                pianificazione.getPromozioneTestataEntity().getMuiCfgSetPianificazione(),
                pianificazione.getMeccanicaEntity(), PianificazioneConstants.REFERENCE_NUM_SET_COLUMN);
        if (configurazione == null) {
            log.error(String.format("Campo '%s' non configurato per meccanica '%s'",
                    PianificazioneConstants.REFERENCE_NUM_SET_COLUMN, codiceMeccanica));
            return false;
        }
        // #5290: ennesima gestione particolare del numSet per le meccaniche M141, M142 e M143
        List<Integer> availableNumSet = "M141".equals(codiceMeccanica) || "M142".equals(codiceMeccanica) || "M143".equals(codiceMeccanica)
                ? numSetUtilsInstance.get().getAvailablePrecaricate(pianificazione.getPromozioneTestataEntity(), configurazione)
                : numSetUtilsInstance.get().getAvailable(pianificazione.getPromozioneTestataEntity(), lista, null);
        if (availableNumSet == null || availableNumSet.isEmpty()) {
            log.warn(String.format("Nessun numero di set disponibile per meccanica '%s'", codiceMeccanica));
        }
        if (configurazione.getAllowZero() != 1) {
            return valueToBeUpdated != null && availableNumSet.contains(Integer.parseInt(valueToBeUpdated.toString()));
        }
        return valueToBeUpdated == null || availableNumSet.contains(Integer.parseInt(valueToBeUpdated.toString()));
    }

    /**
     * Validazione campi PAGHI e PORTI_VIA, solo per meccanica 'M026 - MxN' - valore
     * per PAGHI < valore per PORTI_VIA
     *
     * @param pianificazioneEntity pianificazione corrente
     * @param fieldName            campo da controllare
     * @param valueToBeUpdated     valore da controllare
     * @return true se il valore è valido, false altrimenti
     */
    private boolean checkPaghiPortiVia(@NonNull final PromozionePianificazioneEntity pianificazioneEntity,
                                       @NonNull final String fieldName, final Object valueToBeUpdated) {
        final int value = Integer.parseInt((String) valueToBeUpdated);
        if (fieldName.equals(PianificazioneConstants.REFERENCE_PAGHI)) {
            return (pianificazioneEntity.getPortiVia() == null) || (value < pianificazioneEntity.getPortiVia());
        }
        if (fieldName.equals(PianificazioneConstants.REFERENCE_PORTI_VIA)) {
            return (pianificazioneEntity.getPaghi() == null) || (value > pianificazioneEntity.getPaghi());
        }
        return true;
    }

    @GET
    @Path("/planning/sovrapposizioni/detail/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSovrapposizioniNegoziColumnDef(@Context HttpServletRequest request,
                                                      @QueryParam("contesto") String context) throws Exception {

        return loadColumnDefFromFile("pianificazione_overlap_negozi_columnDef.json",
                "db_pianificazione_sovrapposizioni", context);
    }

    @GET
    @Path("/planning/sovrapposizioni/detail/rowData/{sovrapposizioneId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSovrapposizioniNegoziRowData(@PathParam("sovrapposizioneId") String sovrapposizioneId,
                                                    @Context HttpServletRequest request, @QueryParam("contesto") String context) throws Exception {
        long idSovrapposizione;
        try {
            idSovrapposizione = Long.parseLong(sovrapposizioneId);
        } catch (final Exception e) {
            log.error("error converting id sovrapposizione to long " + sovrapposizioneId, e);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione inesistente").asJson()).build();
        }
        List<ReportSovrapposizioniNegoziEntity> negozi;
        try {
            ReportSovrapposizioniNegoziService service = sovrapposizioneNegoziServiceInstance.get();
            negozi = service.findByIdSovrapposizione(idSovrapposizione);

            return Response.ok(pianificazionePromoRowDataUtil.createDettaglioSovrapposizioniRowData(negozi)).build();

        } catch (Exception e) {
            log.error("Errore durante il recupero delle righe di dettaglio per la sovrapposizione " + sovrapposizioneId,
                    e);
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/list/sottoclassi/{idPromozione}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSottoclassi(@PathParam("idPromozione") String idPromozione, @Context HttpServletRequest request) {
        try {
            final PromozioneTestataEntity promozione = promoService.findById(Long.parseLong(idPromozione));
            if (promozione == null) {
                log.error("Promozione non esistente con id: " + idPromozione);
                return Response.status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Message("Promozione inesistente").asJson()).build();
            }
            final String codiceStatoPromozione = PromoAcl.getCodiceStatoPromozione(promozione);
            final List<MuiSottoclasseEntity> entities = sottoclasseUtilInstance.get()
                    .getSottoclassiByStatoPromozione(codiceStatoPromozione);
            final List<ComboBoxValues> items = comboBoxFactoryInstance.get().from(entities, false, true);
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("remoteValues", JsonUtils.getMapper().writeValueAsString(items));
            return Response.ok().entity(node.toString()).build();
        } catch (NumberFormatException ex) {
            log.error("Errore conversione idPromozione: " + idPromozione, ex);
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Message("Promozione inesistente").asJson()).build();
        } catch (Exception ex) {
            log.error("Errore durante il recupero delle sottoclassi", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Impossibile recuperare le sottoclassi disponibili").asJson()).build();
        }
    }

    @GET
    @Path("/list/formePagamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFormePagamento() {
        try {
            final List<ComboBoxValues> items = comboBoxFactoryInstance.get()
                    .from(formaPagamentoServiceInstance.get().findAllActive(), false, true);
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.put("remoteValues", JsonUtils.getMapper().writeValueAsString(items));
            return Response.ok().entity(node.toString()).build();
        } catch (Exception ex) {
            log.error("Errore durante il recupero delle forme pagamento", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Message("Impossibile recuperare le 'Forme Pagamento' disponibili").asJson()).build();
        }
    }
}
