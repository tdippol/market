package com.axiante.mui.webapp.webservice;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.common.promo.UserFilterUtils;
import com.axiante.mui.common.promo.grid.ComboBoxValues;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.common.promo.params.PromoShopBulkParam;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.actions.ActionEnum;
import com.axiante.mui.dbpromo.actions.ActionTypeEnum;
import com.axiante.mui.dbpromo.actions.ElementFieldEnum;
import com.axiante.mui.dbpromo.actions.FormEnum;
import com.axiante.mui.dbpromo.business.enumeration.PromoShopRadioButtonEnum;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.business.utils.ComboBoxFactory;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CreaPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromozioneFlagEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoPubblicazioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneAttributiEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTipoTerminaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.SottoscrizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import com.axiante.mui.dbpromo.persistence.service.GruppoPromozioniService;
import com.axiante.mui.dbpromo.persistence.service.MuiPlanoDbPromoService;
import com.axiante.mui.dbpromo.persistence.service.MuiPromoDbPromoService;
import com.axiante.mui.dbpromo.persistence.service.NegoziPromoService;
import com.axiante.mui.dbpromo.persistence.service.PromoPubblicazioneTestataService;
import com.axiante.mui.dbpromo.persistence.service.SottoscrizioneService;
import com.axiante.mui.dbpromo.persistence.service.ValorePuntoService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.validator.model.PromoUpdate;
import com.axiante.mui.validator.service.PromotionValidatorService;
import com.axiante.mui.webapp.business.ActionService;
import com.axiante.mui.webapp.business.OwnershipService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.security.PromoSecurity;
import com.axiante.mui.webapp.webservice.dto.PromozioneCheckDto;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.axiante.mui.webapp.webservice.util.CreaPromozioneFactory;
import com.axiante.mui.webapp.webservice.util.EditPromoUtil;
import com.axiante.mui.webapp.webservice.util.PromoShopsUtil;
import com.axiante.mui.webapp.webservice.util.PromozioneAttributiUtil;
import com.axiante.mui.webapp.webservice.util.PromozioneFlagUtil;
import com.axiante.mui.webapp.webservice.util.PromozioneTestataHelper;
import com.axiante.mui.webapp.webservice.util.SchedaPromoUtil;
import com.axiante.mui.webapp.webservice.util.ViewPromoUtil;
import com.axiante.mui.webapp.webservice.validator.PromozioneTestataValidator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Path("/grid")
@Slf4j
@Dependent
public class GridServiceResource extends SessionEnabledResource {

	@Inject
	transient private PromoSecurity promoSecurity;

	@Inject
	transient private CreatePromotionService createPromotionService;

	@Inject
	transient private PromotionValidatorService promotionValidatorService;

	@Inject
	transient private PromoShopsUtil promoShopsUtil;

	@Inject
	transient private NegoziPromoService negoziPromoService;

	@Inject
	transient private PromoPubblicazioneTestataService promoPubblicazioneTestataService;

	@Inject
	transient private GruppoPromozioniService gruppoPromozioniService;

	@Inject
	transient private ViewPromoUtil viewPromoUtil;

	@Inject
	transient CreaPromozioneFactory creaPromozioneFactory;

	@Inject
	transient ComboBoxFactory comboBoxFactory;

	@Inject
	transient PromozioneTestataHelper promozioneTestataHelper;

	@Inject
	transient private PromozioneTestataValidator promozioneTestataValidator;

	@Inject
	transient private MuiPromoDbPromoService selezionaPromoService;

	@Inject
	transient private MuiPlanoDbPromoService selezionaPlanoService;

	@Inject
	transient private OwnershipService ownershipService;

	@Inject
	transient private EditPromoUtil editPromoUtil;

	@Inject
	transient private ActionService actionService;

	@Inject
	transient private SottoscrizioneService sottoscrizioneService;

	@Inject
	@Getter
	private ApplicationProperties applicationProperties;

	transient DateTimeUtils dateTimeUtils = new DateTimeUtils();

	transient UserFilterUtils userFilterUtils = new UserFilterUtils();

	@Inject
	private transient SchedaPromoUtil schedaPromoUtil;
	@Inject
	private transient MuiService muiService;

	@Inject
	transient private Instance<PromozioneFlagUtil> promoFlagUtilInstance;

	@Inject
	transient private Instance<PromozioneAttributiUtil> promoAttributiUtilInstance;

	@Inject
	transient private Instance<ValorePuntoService> valorePuntoServiceInstance;

	@Inject
	private transient Instance<CanalePromozioneService> canalePromozioneServiceInstance;

	@GET
	@Path("/columnDef/visualizza")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefRiepilogo(@QueryParam("contesto") String contesto, @Context HttpServletRequest request) {

		return loadColumnDefFromFile("columnDefVisualizza.json", "db_grid_visualizzaPromo", contesto);
	}

	@GET
	@Path("/rowData/visualizza")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataRiepilogo(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
		try {
			
			final Map<String, String> userFiltersMap = userFilterUtils
					.createUserFiltersMapToQueryString(getCurrentUser().getDbFilters());
			final UserDTO userDto = getApplicationUser(context);
			final List<PromozioneTestataEntity> testate = promoService
					.findAllNotCancelled(userFiltersMap, userDto.getCanali()).stream()
					.sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio)).collect(Collectors.toList());
			return Response.ok(viewPromoUtil.createRowData(testate, userDto.getGruppi(), isUserAdmin())).build();
		} catch (final Exception ex) {
			log.error("Error getting rowData for VisualizzaPromo", ex);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/columnDef/creaPromo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefCreaPromozione(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
		
		return loadColumnDefFromFile("columnDefCreaPromo.json", "db_grid_creaPromo", context);
	}

	@GET
	@Path("/rowData/creaPromo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataCreaPromozione(@QueryParam("contesto") String context,
			@Context HttpServletRequest request) {
		try {
			final UserDTO userDto = getApplicationUser(context);
			final String username = getCurrentUser().getName();
			final String rowData = creaPromozioneFactory.createRowData(createPromotionService.findSlotCreaPromoValue(),
					createPromotionService.getAllPromoByUserId(username), username, userDto.getCanali(),
					userDto.getCanaliCreatePromo());
			if (rowData == null) {
				return Response.status(Status.PRECONDITION_FAILED).entity(new Message("Spazio di lavoro esaurito"))
						.build();
			}
			return Response.ok(rowData).build();
		} catch (final Exception ex) {
			log.error("Error getting rowData for CreaPromo", ex);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/rowData/schedaNegozi")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowData(@Context HttpServletRequest request) {
		
		final UserDTO applicationUser = getApplicationUser(null);
		return Response.ok(promoShopsUtil.populateRowData(null, getCurrentUser().getDbFilters(),
				applicationUser.getCanali(), applicationUser.getGruppi(), isUserAdmin())).build();
	}

	@GET
	@Path("/rowData/selezionaPromozioni")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSelezionaPromozioniRowData(@Context HttpServletRequest request) {
		
		Integer giorniSelezionePromo = ApplicationProperties.DEFAULT_GIORNI_SELEZIONE_PROMO;
		if (getApplicationProperties() != null) {
			giorniSelezionePromo = getApplicationProperties().getProperty(ApplicationProperties.GIORNI_SELEZIONE_PROMO,
					ApplicationProperties.DEFAULT_GIORNI_SELEZIONE_PROMO);
		}
		List<MuiPromoDbPromoEntity> list = selezionaPromoService.findByGiorniSelezione(giorniSelezionePromo);
		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		list.forEach(l -> {
			final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();
			DBPromoAgCell cell = DBPromoAgCell.builder().name("Codice Gruppo").editable(false)
					.type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(l.getCodiceGruppo())).build();
			map.put("codiceGruppo", cell);
			cell = DBPromoAgCell.builder().name("Gruppo").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
					.value(String.valueOf(l.getDescrizioneGruppo())).build();
			map.put("gruppo", cell);
			cell = DBPromoAgCell.builder().name("Tipo Promozione").editable(false)
					.type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(l.getCodiceCanale())).build();
			map.put("tipoPromozione", cell);
			cell = DBPromoAgCell.builder().name("Canale").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
					.value(String.valueOf(l.getDescrizioneCanale())).build();
			map.put("canale", cell);
			cell = DBPromoAgCell.builder().name("Codice").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
					.value(String.valueOf(l.getCodicePromozione())).build();
			map.put("codice", cell);
			cell = DBPromoAgCell.builder().name("Descrizione").editable(false)
					.type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(l.getDescrizioneEstesa())).build();
			map.put("descrizione", cell);
			cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(DBPromoCellTypeEnum.DATE.getType())
					.value(l.getDataInizio() == null ? "" : dateTimeUtils.toExcelDate(l.getDataInizio())).build();
			map.put("dataInizio", cell);
			cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(DBPromoCellTypeEnum.DATE.getType())
					.value(l.getDataFine() == null ? "" : dateTimeUtils.toExcelDate(l.getDataFine())).build();
			map.put("dataFine", cell);
			cell = DBPromoAgCell.builder().name("Stato").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
					.value(l.getStato()).build();
			map.put("stato", cell);

			arrayNode.add(JsonUtils.getMapper().valueToTree(map));
		});
		final ObjectNode node = JsonUtils.getMapper().createObjectNode();
		node.set(DbPromoConstants.ROW_DATA, arrayNode);
		try {
			return Response.ok(JsonUtils.getMapper().writeValueAsString(node)).build();
		} catch (Exception e) {
			log.error("Error getting rowData for ws selezionaPromozioni", e);
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/rowData/selezionaPlanogrammi/{idPromo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSelezionaPlanoRowData(@PathParam("idPromo") String idPromo, @QueryParam("contesto") String contesto,
											 @Context HttpServletRequest request) {
		
		final PromozioneCheckDto promozioneCheckDto = validatePromozioneFromRequest(idPromo, contesto);
		if (promozioneCheckDto.isError()) {
			log.error(String.format("Promozione with id %s not accessible", idPromo));
			return Response.status(Status.PRECONDITION_FAILED)
					.entity(new Message(promozioneCheckDto.getErrorMessage()).asJson()).build();
		}
		List<MuiPlanoDbPromoEntity> list = selezionaPlanoService.findAll();
		// #4056: discard planogram already associated
		final Set<MuiPlanoDbPromoEntity> planogramAlreadyUsed = promozioneCheckDto.getTestata().getPlanogrammi();
		if (planogramAlreadyUsed != null && !planogramAlreadyUsed.isEmpty()) {
			list.removeIf(planogramAlreadyUsed::contains);
		}

		list.sort(
				Comparator.comparing(MuiPlanoDbPromoEntity::getDescrizioneCategoria)
				.thenComparing(Comparator.comparing(MuiPlanoDbPromoEntity::getDimensione))
				.thenComparing(Comparator.comparing(MuiPlanoDbPromoEntity::getTipologia))
				);

		final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
		list.forEach(l -> {
			final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();
			DBPromoAgCell cell = DBPromoAgCell.builder().name("Codice Categoria").editable(false)
					.type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(l.getCategoriaEspositiva())).build();
			map.put("categoriaEspositiva", cell);
			cell = DBPromoAgCell.builder().name("Descr. Categoria").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
					.value(String.valueOf(l.getDescrizioneCategoria())).build();
			map.put("descrizioneCategoria", cell);
			cell = DBPromoAgCell.builder().name("Dimensione").editable(false)
					.type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(l.getDimensione())).build();
			map.put("dimensione", cell);
			cell = DBPromoAgCell.builder().name("Tipologia").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
					.value(String.valueOf(l.getTipologia())).build();
			map.put("tipologia", cell);
			cell = DBPromoAgCell.builder().name("Codice CICS").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
					.value(String.valueOf(l.getCodiceCics())).build();
			map.put("codiceCics", cell);
			cell = DBPromoAgCell.builder().name("Planogramma").editable(false)
					.type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(l.getCodicePlano())).build();
			map.put("codicePlano", cell);
			cell = DBPromoAgCell.builder().name("id").editable(false)
					.type(DBPromoCellTypeEnum.STRING.getType()).value(String.valueOf(l.getIdPlano())).build();
			map.put("idPlano", cell);

			arrayNode.add(JsonUtils.getMapper().valueToTree(map));
		});
		final ObjectNode node = JsonUtils.getMapper().createObjectNode();
		node.set(DbPromoConstants.ROW_DATA, arrayNode);
		try {
			return Response.ok(JsonUtils.getMapper().writeValueAsString(node)).build();
		} catch (Exception e) {
			log.error(String.format("Error getting rowData for ws selezionaPlanogrammi with idPromo %s", idPromo), e);
			return Response.serverError().build();
		}
	}
	
	@GET
	@Path("/columnDef/selezionaPromozioni")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSelezionaPromozioniColumnDef(@QueryParam("contesto") String context,
													@Context HttpServletRequest request) {
		
		return loadColumnDefFromFile("columnDefSelezionaPromo.json", "db_grid_selezionaPromo", context);
	}

	@GET
	@Path("/columnDef/selezionaPlanogrammi")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSelezionaPlanogrammiColumnDef(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
		
		return loadColumnDefFromFile("columnDefSelezionaPlano.json", "db_grid_selezionaPlano", context);
	}

	@GET
	@Path("/columnDef/planogrammi")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlanogrammiColumnDef(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
		
		return loadColumnDefFromFile("columnDefPlanogrammi.json", "db_schedapromo_planogrammi", context);
	}

	@GET
	@Path("/rowData/planogrammi/{idPromo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlanogrammiRowData(@PathParam("idPromo") String idPromo, @QueryParam("contesto") String contesto,
										  @Context HttpServletRequest request) {
		
		final PromozioneCheckDto promozioneCheckDto = validatePromozioneFromRequest(idPromo, contesto);
		if (promozioneCheckDto.isError()) {
			log.error(String.format("Promozione with id %s not accessible", idPromo));
			return Response.status(Status.PRECONDITION_FAILED)
					.entity(new Message(promozioneCheckDto.getErrorMessage()).asJson()).build();
		}
		try {
			final PromozioneTestataEntity testata = promozioneCheckDto.getTestata();
			final Set<MuiPlanoDbPromoEntity> planogrammi = testata.getPlanogrammi();
			final StatoPromozioneEntity statoPromo = PromoAcl.getStatoCorrente(testata);
			final boolean deletable = (isUserAdmin() || ownershipService.hasOwnership(testata, promozioneCheckDto.getCodiciGruppo()))
					&& actionService.applyRule(ActionEnum.BUTTON_ELIMINA_ASSOCIAZIONE_PLANO,
						testata.getMuiCanalePromozione(), statoPromo, ActionTypeEnum.ACTIVE, FormEnum.SCHEDA_PROMO,
						ElementFieldEnum.BUTTON_ELIMINA_ASSOCIAZIONE_PLANO);
			return Response.ok(schedaPromoUtil.createPlanogrammiRowData(planogrammi, deletable)).build();
		} catch (final Exception ex) {
			log.error(String.format("Error getting rowData for 'SchedaPromo - Planogrammi' for promo with id: %s",
					idPromo), ex);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/rowData/schedaNegozi/changeView/{radioChecked}/{promoSelected}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeView(@PathParam("radioChecked") String radioChecked, @PathParam("promoSelected") String promoSelected,
							   @QueryParam("contesto") String context, @Context HttpServletRequest request) {
		if ((radioChecked == null) || (!PromoShopRadioButtonEnum.TUTTO.getValue().equals(radioChecked)
				&& !PromoShopRadioButtonEnum.VARIAZIONE_SU_DEFAULT.getValue().equals(radioChecked)
				&& !PromoShopRadioButtonEnum.VISUALIZZA_SI.getValue().equals(radioChecked)
				&& !PromoShopRadioButtonEnum.VISUALIZZA_NO.getValue().equals(radioChecked))) {
			log.error(String.format("Error radioChecked %s is invalid ", radioChecked));
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Opzione di visualizzazione non valida").asJson()).build();
		}

		if ("null".equalsIgnoreCase(promoSelected)) {
			promoSelected = null;
		}

		if (promoSelected != null) {
			Long idPromozione;
			try {
				idPromozione = Long.parseLong(promoSelected);
			} catch (final Exception ex) {
				log.error("Error converting promozione id to long " + promoSelected, ex);
				return Response.status(Response.Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione inesistente").asJson()).build();
			}
			final PromozioneTestataEntity testata = getPromozioneTestataEntity(idPromozione);
			if (testata == null) {
				log.error(String.format("Error getting Testata with id %d", idPromozione));
				return Response.status(Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione non esistente").asJson()).build();
			}

			if (!promoSecurity.isAccessible(testata, getApplicationUser(context))) {
				log.error("Promozione non esistente o abilitata");
				return Response.status(Response.Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione non accessibile").asJson()).build();
			}
		}

		try {
			final UserDTO applicationUser = getApplicationUser(context);
			if (PromoShopRadioButtonEnum.VARIAZIONE_SU_DEFAULT.getValue().equals(radioChecked)
					|| PromoShopRadioButtonEnum.VISUALIZZA_SI.getValue().equals(radioChecked)
					|| PromoShopRadioButtonEnum.VISUALIZZA_NO.getValue().equals(radioChecked)) {
				return streamResponse(promoShopsUtil.streamModifiedRowData(promoSelected, radioChecked,
						getCurrentUser().getDbFilters(), applicationUser.getCanali(), applicationUser.getGruppi(), isUserAdmin()));
			}
			return streamResponse(promoShopsUtil.streamRowData(promoSelected, getCurrentUser().getDbFilters(),
					applicationUser.getCanali(), applicationUser.getGruppi(), isUserAdmin()));
		} catch (final Exception ex) {
			log.error("Error caricamento rowData per la promozione con id " + promoSelected, ex);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	private Response streamResponse(StreamingOutput out) {
		ResponseBuilder resp = Response.ok(out);
		// Sending the X-Content-Type-Options response header with the value
		// nosniff will prevent Internet Explorer from MIME-sniffing a response
		// away from the declared content-type.
		resp.header("X-Content-Type-Options", "nosniff");
		return resp.build();

	}

	@GET
	@Path("/rowData/modificaPromo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataEdit(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
		try {
			final Map<String, String> userFiltersMap = userFilterUtils
					.createUserFiltersMapToQueryString(getCurrentUser().getDbFilters());
			final UserDTO userDto = getApplicationUser(context);
			final List<PromozioneTestataEntity> testate = promoService
					.findAllNotCancelled(userFiltersMap, userDto.getCanali()).stream()
					.sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio)).collect(Collectors.toList());
			return Response.ok(editPromoUtil.createRowData(testate, userDto.getGruppi(), isUserAdmin())).build();
		} catch (final Exception e) {
			log.error("Error getting rowData from request " + request.getRequestURI(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/columnDef/modificaPromo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefEdit(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
		return loadColumnDefFromFile("editPromo_columnDef.json", "db_grid_modificaPromo", context);
	}

	@GET
	@Path("/columnDef/schedaNegozi")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDef(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
		return loadColumnDefFromFile("columnDefNegoziPromo_scheda_tab.json",
				"db_schedapromo_negoziPromo", context);
	}

	@GET
	@Path("/schedapromo/stati/rowData/{promoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataSchedaPromoStati(@PathParam("promoId") String promoId,
			@Context HttpServletRequest request) {
		long idPromozione;
		try {
			idPromozione = Long.parseLong(promoId);
		} catch (final Exception e) {
			log.error("error converting promozione id to long " + promoId, e);
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione inesistente").asJson()).build();
		}

		final PromozioneTestataEntity testata = getPromozioneTestataEntity(idPromozione);
		if (testata == null) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non trovata").asJson()).build();
		}

		if (!promoSecurity.isAccessible(testata, getApplicationUser(null))) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non abilitata").asJson()).build();
		}

		try {
			return Response.ok(schedaPromoUtil.createStatiRowData(testata.getPromozioneStatoEntities())).build();
		} catch (final Exception e) {
			log.error("Error getting rowData stati scheda promo per promozione con id " + promoId, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/schedapromo/stati/columnDef")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefSchedaPromoStati(@QueryParam("contesto") String context,
												 @Context HttpServletRequest request) {

		return loadColumnDefFromFile("columnDefStati.json", "db_schedapromo_stati", context);
	}

	@GET
	@Path("/schedapromo/flag/columnDef")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefSchedaPromoFlag(@QueryParam("contesto") String context,
												@Context HttpServletRequest request) {
		return loadColumnDefFromFile("schedaPromo_flag_columnDef.json", "db_schedapromo_flag", context);
	}

	@GET
	@Path("/schedapromo/flag/rowData/{promoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataSchedaPromoFlag(@PathParam("promoId") String promoId,
											  @QueryParam("contesto") String contesto,
											  @Context HttpServletRequest request) {
		long idPromozione;
		try {
			idPromozione = Long.parseLong(promoId);
		} catch (final Exception e) {
			log.error("error converting promozione id to long " + promoId, e);
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione inesistente").asJson()).build();
		}

		final PromozioneTestataEntity testata = getPromozioneTestataEntity(idPromozione);
		if (testata == null) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non trovata").asJson()).build();
		}

		final UserDTO userDTO = getApplicationUser(contesto);
		if (!promoSecurity.isAccessible(testata, userDTO)) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non abilitata").asJson()).build();
		}
		try {
			final StatoPromozioneEntity statoPromo = PromoAcl.getStatoCorrente(testata);
			final boolean editable = isUserAdmin() ||
					(ownershipService.hasOwnership(testata, userDTO.getGruppi())
							&& actionService.applyRule(ActionEnum.EDIT_FLAG_TESTATA, testata.getMuiCanalePromozione(),
							statoPromo, ActionTypeEnum.ACTIVE, FormEnum.SCHEDA_PROMO, ElementFieldEnum.TAB_FLAG));
			return Response.ok(promoFlagUtilInstance.get().createFlagRowData(testata.getPromozioneFlags(), editable)).build();
		} catch (final Exception e) {
			log.error("Error getting rowData flag scheda promo per promozione con id " + promoId, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/schedapromo/flag/update/{promoId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateFlag(@PathParam("promoId") String promoId, String payload, @QueryParam("contesto") String contesto,
							   @Context HttpServletRequest request) throws Exception {
		log.debug(payload);
		long idPromozione;
		Long idFlag;
		long idFlagDominio;
		try {
			idPromozione = Long.parseLong(promoId);
			final JsonNode node = JsonUtils.getMapper().readTree(payload);
			idFlag = Long.parseLong(node.get("idFlag").asText(""));
			idFlagDominio = Long.parseLong(node.get("idFlagDominio").asText(""));
		} catch (NumberFormatException ex) {
			log.error("Error parsing payload data as long", ex);
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione inesistente").asJson()).build();
		}

		final PromozioneTestataEntity promozione = getPromozioneTestataEntity(idPromozione);
		if (promozione == null) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non trovata").asJson()).build();
		}

		final UserDTO userDTO = getApplicationUser(contesto);
		if (!promoSecurity.isAccessible(promozione, userDTO)) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non abilitata").asJson()).build();
		}
		try {
			final StatoPromozioneEntity statoPromo = PromoAcl.getStatoCorrente(promozione);
			final boolean editable = isUserAdmin() ||
					(ownershipService.hasOwnership(promozione, userDTO.getGruppi())
							&& actionService.applyRule(ActionEnum.EDIT_FLAG_TESTATA, promozione.getMuiCanalePromozione(),
							statoPromo, ActionTypeEnum.ACTIVE, FormEnum.SCHEDA_PROMO, ElementFieldEnum.TAB_FLAG));
			// Cerco il flag se e' associato alla promozione
			final MuiPromozioneFlagEntity promoFlag = promozione.getPromozioneFlags().stream()
					.filter(pf -> idFlag.equals(pf.getFlag().getId()))
					.findFirst()
					.orElse(null);
			if (promoFlag == null) {
				log.error(String.format("Flag con id %d non associato alla promozione %d", idFlag, promozione.getId()));
				return Response.status(Status.PRECONDITION_FAILED).entity(new Message("Flag non associato").asJson())
						.build();
			}
			if (!editable) {
				log.error(String.format("Flag not editable for promo %s", promoId));
				return Response.status(Status.PRECONDITION_FAILED)
						.entity(promoFlagUtilInstance.get().createOriginalRow(promoFlag, "Errore aggiornamento flag", editable))
						.build();
			}
			if (promoFlagUtilInstance.get().update(promozione, promoFlag, idFlagDominio, userDTO.getUsermame())) {
				// OK, ritorno rowData aggiornato
				return Response.ok(promoFlagUtilInstance.get().createUpdatedRow(promoFlag, editable))
						.build();
			} else {
				// Errore, reset value
				return Response.status(Status.PRECONDITION_FAILED)
						.entity(promoFlagUtilInstance.get().createOriginalRow(promoFlag, "Errore aggiornamento flag", editable))
						.build();
			}
		} catch (Exception ex) {
			log.error(String.format("Error updating flag for promo %s", promoId), ex);
			return Response.status(Status.PRECONDITION_FAILED).build();
		}
	}

	@GET
	@Path("/schedapromo/attributi/columnDef")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefSchedaPromoAttributi(@QueryParam("contesto") String context,
													 @Context HttpServletRequest request) {
		return loadColumnDefFromFile("schedaPromo_attributi_columnDef.json", "db_schedapromo_attributi", context);
	}

	@GET
	@Path("/schedapromo/attributi/rowData/{promoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataSchedaPromoAttributi(@PathParam("promoId") String promoId,
												   @QueryParam("contesto") String contesto,
												   @Context HttpServletRequest request) {
		long idPromozione;
		try {
			idPromozione = Long.parseLong(promoId);
		} catch (final Exception e) {
			log.error("error converting promozione id to long " + promoId, e);
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione inesistente").asJson()).build();
		}

		final PromozioneTestataEntity promozione = getPromozioneTestataEntity(idPromozione);
		if (promozione == null) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non trovata").asJson()).build();
		}

		final UserDTO userDTO = getApplicationUser(contesto);
		if (!promoSecurity.isAccessible(promozione, userDTO)) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non abilitata").asJson()).build();
		}
		try {
			final StatoPromozioneEntity statoPromo = PromoAcl.getStatoCorrente(promozione);
			final boolean editable = isUserAdmin() ||
					(ownershipService.hasOwnership(promozione, userDTO.getGruppi())
							&& actionService.applyRule(ActionEnum.EDIT_ATTRIBUTI_TESTATA, promozione.getMuiCanalePromozione(),
							statoPromo, ActionTypeEnum.ACTIVE, FormEnum.SCHEDA_PROMO, ElementFieldEnum.TAB_ATTRIBUTI));
			return Response.ok(promoAttributiUtilInstance.get()
					.createAttributiRowData(promozione.getPromozioneAttributiEntity(), promozione.getMuiCanalePromozione().getId(), editable)).build();
		} catch (final Exception ex) {
			log.error(String.format("Error getting rowData flag scheda promo per promozione con id %s", promoId), ex);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/schedapromo/attributi/update/{promoId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAttributo(@PathParam("promoId") String promoId, String payload,
									@QueryParam("contesto") String contesto,
									@Context HttpServletRequest request) throws Exception {
		log.debug(payload);
		long idPromozione;
		Long idAttributo;
		String valoreAttributo;
		try {
			idPromozione = Long.parseLong(promoId);
			final JsonNode node = JsonUtils.getMapper().readTree(payload);
			idAttributo = Long.parseLong(node.get("idAttributo").asText(""));
			valoreAttributo = node.get("valoreAttributo").asText("");
		} catch (NumberFormatException ex) {
			log.error("Error parsing payload data as long", ex);
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione inesistente").asJson()).build();
		}

		final PromozioneTestataEntity promozione = getPromozioneTestataEntity(idPromozione);
		if (promozione == null) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non trovata").asJson()).build();
		}

		final UserDTO userDTO = getApplicationUser(contesto);
		if (!promoSecurity.isAccessible(promozione, userDTO)) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non abilitata").asJson()).build();
		}
		try {
			final StatoPromozioneEntity statoPromo = PromoAcl.getStatoCorrente(promozione);
			final boolean editable = isUserAdmin() ||
					(ownershipService.hasOwnership(promozione, userDTO.getGruppi())
							&& actionService.applyRule(ActionEnum.EDIT_ATTRIBUTI_TESTATA, promozione.getMuiCanalePromozione(),
							statoPromo, ActionTypeEnum.ACTIVE, FormEnum.SCHEDA_PROMO, ElementFieldEnum.TAB_ATTRIBUTI));
			final PromozioneAttributiEntity promoAttributo = promozione.getPromozioneAttributiEntity().stream()
					.filter(a -> idAttributo.equals(a.getAttributo().getId()))
					.findFirst()
					.orElse(null);
			if (promoAttributo == null) {
				log.error(String.format("Attributo con id %d non associato alla promozione %d", idAttributo, promozione.getId()));
				return Response.status(Status.PRECONDITION_FAILED).entity(new Message("Attributo non associato").asJson())
						.build();
			}
			if (!editable) {
				log.error(String.format("Flag not editable for promo %s", promoId));
				return Response.status(Status.PRECONDITION_FAILED)
						.entity(promoAttributiUtilInstance.get().createOriginalRow(
								promoAttributo, "Errore aggiornamento flag",
								promozione.getMuiCanalePromozione().getId(), false))
						.build();
			}
			if (promoAttributiUtilInstance.get().update(promozione, promoAttributo, valoreAttributo, userDTO.getUsermame())) {
				return Response.ok(promoAttributiUtilInstance.get().createUpdatedRow(
								promoAttributo, promozione.getMuiCanalePromozione().getId(), editable))
						.build();
			} else {
				return Response.status(Status.PRECONDITION_FAILED)
						.entity(promoAttributiUtilInstance.get().createOriginalRow(
								promoAttributo, "Errore aggiornamento flag",
								promozione.getMuiCanalePromozione().getId(), editable))
						.build();
			}
		} catch (Exception ex) {
			log.error(String.format("Error updating attributo for promo %s", promoId), ex);
			return Response.status(Status.PRECONDITION_FAILED).build();
		}
	}

	@GET
	@Path("/schedapromo/pubblicazioni/rowData/{promoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataSchedaPromoPubblicazioni(@PathParam("promoId") String promoId,
			@Context HttpServletRequest request) {
		Long idPromozione;
		try {
			idPromozione = Long.parseLong(promoId);
		} catch (final Exception e) {
			log.error("error converting promozione id to long " + promoId, e);
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione inesistente").asJson()).build();
		}
		final PromozioneTestataEntity testata = getPromozioneTestataEntity(idPromozione);
		if (testata == null) {
			log.error(String.format("Error getting Testata with id %d", idPromozione));
			return Response.status(Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non esistente").asJson()).build();
		}

		if (!promoSecurity.isAccessible(testata, getApplicationUser(null))) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non abilitata").asJson()).build();
		}

		try {
			final List<PromoPubblicazioneTestataEntity> pubblicazioni = promoPubblicazioneTestataService
					.findByPromoID(idPromozione);
			return Response.ok(schedaPromoUtil.createPubblicazioniRowData(pubblicazioni)).build();
		} catch (final Exception e) {
			log.error("Error getting rowData pubblicazioni scheda promo per promozione con id " + promoId, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/schedapromo/pubblicazioni/columnDef")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefSchedaPromoPubblicazioni(@QueryParam("contesto") String context,
														 @Context HttpServletRequest request) {
		
		return loadColumnDefFromFile("columnDefPubblicazioni.json",
				"db_schedapromo_pubblicazioni", context);
	}

	@GET
	@Path("/schedapromo/controlli/columnDef")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefSchedaPromoControlli(@QueryParam("contesto") String context,
													 @Context HttpServletRequest request) {
		
		return loadColumnDefFromFile("columnDefControlli.json", "db_schedapromo_controlli", context);
	}

	@GET
	@Path("/schedapromo/controlli/rowData/{promoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataSchedaPromoControlli(@PathParam("promoId") String promoId,
			@Context HttpServletRequest request) {
		Long idPromozione;
		try {
			idPromozione = Long.parseLong(promoId);
		} catch (final Exception e) {
			log.error("error converting promozione id to long " + promoId, e);
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione inesistente").asJson()).build();
		}

		try {
			final PromozioneTestataEntity testata = getPromozioneTestataEntity(idPromozione);
			if (testata == null) {
				log.error(String.format("Error getting Testata with id %d", idPromozione));
				return Response.status(Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione non esistente").asJson()).build();
			}

			if (!promoSecurity.isAccessible(testata, getApplicationUser(null))) {
				return Response.status(Response.Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione non abilitata").asJson()).build();
			}
			return Response.ok(schedaPromoUtil.createControlliRowData(testata)).build();
		} catch (final Exception e) {
			log.error("Error getting rowData controlli scheda promo per promozione con id " + promoId, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/schedapromo/ownership/columnDef")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefSchedaPromoOwnership(@QueryParam("contesto") String context,
													 @Context HttpServletRequest request) {
		
		return loadColumnDefFromFile("columnDefOwnership.json", "db_schedapromo_ownership", context);
	}

	@GET
	@Path("/schedapromo/ownership/rowData/{promoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataSchedaPromoOwnership(@PathParam("promoId") String promoId,
			@Context HttpServletRequest request) {
		Long idPromozione;
		try {
			idPromozione = Long.parseLong(promoId);
		} catch (final Exception e) {
			log.error("error converting promozione id to long " + promoId, e);
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione inesistente").asJson()).build();
		}

		try {
			final PromozioneTestataEntity testata = getPromozioneTestataEntity(idPromozione);
			if (testata == null) {
				log.error(String.format("Error getting Testata with id %d", idPromozione));
				return Response.status(Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione non esistente").asJson()).build();
			}

			if (!promoSecurity.isAccessible(testata, getApplicationUser(null))) {
				return Response.status(Response.Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione non abilitata").asJson()).build();
			}
			return Response.ok(schedaPromoUtil.createOwnerRowData(testata)).build();
		} catch (Exception ex) {
			log.error("Error getting rowData owner scheda promo per promozione con id " + promoId, ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getLocalizedMessage()).asJson())
					.build();
		}
	}

	@POST
	@Path("/promo/update")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePromoTestata(String jsonEntity, @QueryParam("contesto") String context,
									   @Context HttpServletRequest request) {
		log.debug("promoTestata input: " + jsonEntity);
		final ObjectMapper mapper = JsonUtils.getMapper();
		try {
			final JsonNode node = mapper.readTree(jsonEntity);

			Long idPromozione = null;
			try {
				idPromozione = Long.parseLong(DbPromoAgCellUtils.getValue(DbPromoAgCellUtils.decode(node.get("id"))));
				final PromozioneTestataEntity testata = getPromozioneTestataEntity(idPromozione);
				if (testata == null) {
					return Response.status(Response.Status.NO_CONTENT).build();
				}

				if (!promoSecurity.isAccessible(testata, getApplicationUser(null))
						|| (!isUserAdmin() && !ownershipService.hasOwnership(testata, getApplicationUser(null).getGruppi()))) {
					return Response.status(Response.Status.PRECONDITION_FAILED)
							.entity(new Message("Promozione non accessibile").asJson()).build();
				}

				final Date dataInizio = dateTimeUtils.excelToDate(DbPromoAgCellUtils.getValue(node.get("dataInizio")));
				final Date newDataInizio = dateTimeUtils
						.excelToDate(DbPromoAgCellUtils.getValue(node.get("newDataInizio")));
				final Date dataFine = dateTimeUtils.excelToDate(DbPromoAgCellUtils.getValue(node.get("dataFine")));
				final Date newDataFine = dateTimeUtils
						.excelToDate(DbPromoAgCellUtils.getValue(node.get("newDataFine")));
				final String anno = DbPromoAgCellUtils.getValue(DbPromoAgCellUtils.decode(node.get("anno")));
				String newDescrizione = DbPromoAgCellUtils
						.getValue(DbPromoAgCellUtils.decode(node.get("newDescrizione")));
				String newNoteMarketing = DbPromoAgCellUtils
						.getValue(DbPromoAgCellUtils.decode(node.get("newNoteMarketing")));
				newDescrizione = StringUtils.isBlank(newDescrizione) ? null : newDescrizione.trim();
				newNoteMarketing = StringUtils.isBlank(newNoteMarketing) ? null : newNoteMarketing.trim();
				boolean flagCopiaAutomatica = false;
				if (context != null && context.toUpperCase().trim().contains("CUST_ENG")) {
					JsonNode nodeCopiaAutomatica = node.get("copiaAutomatica");
					flagCopiaAutomatica = nodeCopiaAutomatica != null
							&& Boolean.parseBoolean(DbPromoAgCellUtils.getValue(node.get("copiaAutomatica")));
				}

				final String oraInizioString = DbPromoAgCellUtils.getValue(node.get("oraInizio"));
				final String newOraInizioString = DbPromoAgCellUtils.getValue(node.get("newOraInizio"));
				final String oraFineString = DbPromoAgCellUtils.getValue(node.get("oraFine"));
				final String newOraFineString = DbPromoAgCellUtils.getValue(node.get("newOraFine"));
				final Date oraInizio = StringUtils.isBlank(oraInizioString) ? null
						: DateTimeUtils.toTime(oraInizioString.trim());
				final Date newOraInizio = StringUtils.isBlank(newOraInizioString) ? null
						: DateTimeUtils.toTime(newOraInizioString.trim());
				final Date oraFine = StringUtils.isBlank(oraFineString) ? null
						: DateTimeUtils.toTime(oraFineString.trim());
				final Date newOraFine = StringUtils.isBlank(newOraFineString) ? null
						: DateTimeUtils.toTime(newOraFineString.trim());

				final PromoUpdate promo = new PromoUpdate();
				promo.setDataInizio(newDataInizio != null ? newDataInizio : dataInizio);
				promo.setDataFine(newDataFine != null ? newDataFine : dataFine);
				promo.setDescrizione(newDescrizione != null ? newDescrizione.toUpperCase() : testata.getDescrizione());
				promo.setNoteMarketing(newNoteMarketing != null ? newNoteMarketing : testata.getNoteMarketing());
				promo.setOraInizio(newOraInizio != null ? newOraInizio : oraInizio);
				promo.setOraFine(newOraFine != null ? newOraFine : oraFine);
				// Necessario per validare le date
				promo.setAnno(anno != null ? anno : testata.getAnno());

				if (!isTestataEditable(testata, promo)) {
					return Response.status(Status.PRECONDITION_FAILED)
							.entity(new Message("Promozione non modificabile").asJson()).build();
				}

				Set<String> messages = promotionValidatorService.validateEditPromotion(promo, testata);
				final String message = promozioneTestataValidator.validateDates(testata, newDataInizio, newDataFine);
				if (message != null) {
					if (messages == null) {
						messages = new HashSet<>();
					}
					messages.add(message);
				}

				if ((messages != null) && !messages.isEmpty()) {
					((ObjectNode) node.get("messaggio")).put("value",
							messages != null ? String.join(";", messages) : "");

					/*
					 * se DataInizio != entity.datainizio allora entity.datainizio, altrimenti il
					 * valore precedentemente inserito se nuovaDataFine != entity.datainFine allora
					 * entity.datainFine, altrimenti il valore precedentemente inserito se
					 * nuovaDescrizione != entity.Descrizione allora entity.Descrizione, altrimenti
					 * ripristino il valore precedentemente inserito se note!= entity.note allora
					 * entity.note, altrimenti il valore precedentemente inserito
					 */

					if ((newDataInizio != null) && !newDataInizio.equals(testata.getDataInizio())) {
						((ObjectNode) node.get("newDataInizio")).put("value",
								dateTimeUtils.toExcelDate(testata.getDataInizio()));
					} else {
						((ObjectNode) node.get("newDataInizio")).put("value",
								newDataInizio != null ? dateTimeUtils.toExcelDate(newDataInizio) : "");
					}
					if ((newDataFine != null) && !newDataFine.equals(testata.getDataFine())) {
						((ObjectNode) node.get("newDataFine")).put("value",
								dateTimeUtils.toExcelDate(testata.getDataFine()));
					} else {
						((ObjectNode) node.get("newDataFine")).put("value",
								newDataFine != null ? dateTimeUtils.toExcelDate(newDataFine) : "");
					}
					if ((newNoteMarketing != null) && !newNoteMarketing.equals(testata.getNoteMarketing())) {
						((ObjectNode) node.get("newNoteMarketing")).put("value", testata.getNoteMarketing());
					} else {
						((ObjectNode) node.get("newNoteMarketing")).put("value",
								newNoteMarketing != null ? newNoteMarketing : "");
					}
					if ((newDescrizione != null) && !newDescrizione.equals(testata.getDescrizione())) {
						((ObjectNode) node.get("newDescrizione")).put("value",
								testata.getNewDescrizione() != null ? testata.getNewDescrizione().toUpperCase()
										: testata.getNewDescrizione());
					} else {
						((ObjectNode) node.get("newDescrizione")).put("value",
								newDescrizione != null ? newDescrizione.toUpperCase() : "");
					}

					return Response.ok(mapper.writeValueAsString(node)).build();
				}

				// validation fields OK
				promozioneTestataHelper.adjustDates(testata, newDataInizio, newDataFine);

				((ObjectNode) node.get("messaggio")).put("value", "");

				testata.setDescrizione((newDescrizione != null) && !newDescrizione.isEmpty() ? newDescrizione
						: testata.getDescrizione());

				if ((testata.getDataInizio() != null) && !testata.getDataInizio().equals(newDataInizio)) {
					final Date date = newDataInizio != null ? newDataInizio : dataInizio;
					testata.setDataInizio(date);
					((ObjectNode) node.get("newDataInizio")).put("value", dateTimeUtils.toExcelDate(date));
				}

				if ((testata.getDataFine() != null) && !testata.getDataFine().equals(newDataFine)) {
					final Date date = newDataFine != null ? newDataFine : dataFine;
					testata.setDataFine(date);
					((ObjectNode) node.get("newDataFine")).put("value", dateTimeUtils.toExcelDate(date));
				}

				// Ora inizio updated
				final boolean oraInizioUpdated = ((testata.getOraInizio() != null)
						&& !testata.getOraInizio().equals(promo.getOraInizio()))
						|| ((testata.getOraInizio() == null) && (promo.getOraInizio() != null));
				if (oraInizioUpdated) {
					testata.setOraInizio(newOraInizio);
					((ObjectNode) node.get("newOraInizio")).put("value", DateTimeUtils.toTime(newOraInizio));
				}

				// Ora fine updated
				final boolean oraFineUpdated = ((testata.getOraFine() != null)
						&& !testata.getOraFine().equals(promo.getOraFine()))
						|| ((testata.getOraFine() == null) && (promo.getOraFine() != null));
				if (oraFineUpdated) {
					testata.setOraFine(newOraFine);
					((ObjectNode) node.get("newOraFine")).put("value", DateTimeUtils.toTime(newOraFine));
				}

				if ((testata.getNoteMarketing() == null) || !testata.getNoteMarketing().equals(newNoteMarketing)) {
					testata.setNoteMarketing(promo.getNoteMarketing());
					((ObjectNode) node.get("newNoteMarketing")).put("value",
							newNoteMarketing != null ? newNoteMarketing : newNoteMarketing);
				}

				if ((testata.getDataInizio() != null) && (testata.getDataFine() != null)
						&& (testata.getDescrizioneEstesa() != null) && !testata.getDescrizioneEstesa().isEmpty()) {
					testata.setDescrizioneEstesa(
							dateTimeUtils.calculateExtendedDescription(testata.getCodicePromozione(),
									testata.getDataInizio(), testata.getDataFine(), testata.getDescrizione()));
				}
				if (testata.getFlCopiaAutomatica() != flagCopiaAutomatica) {
					testata.setFlCopiaAutomatica(flagCopiaAutomatica);
				}

				promoService.persist(testata, getCurrentUser().getName());
				return Response.ok(mapper.writeValueAsString(node)).build();
			} catch (final Exception e) {
				log.error("error converting promozione id to long "
						+ DbPromoAgCellUtils.getValue(DbPromoAgCellUtils.decode(node.get("id"))), e);
				return Response.status(Response.Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione inesistente").asJson()).build();
			}
		} catch (final Exception e) {
			log.error("Errore durante l'aggiornamento della testata", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/promo/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePromoTestata(String jsonEntity, @Context HttpServletRequest request) {
		try {
			
			final JsonNode node = JsonUtils.getMapper().readTree(jsonEntity);
			Long id = null;
			try {
				id = Long.parseLong(DbPromoAgCellUtils.getValue(node.get("id")));
			} catch (final Exception e) {
				log.error("no id found in node " + JsonUtils.getMapper().writeValueAsString(node), e);
				return Response.status(Status.BAD_REQUEST).build();
			}

			final PromozioneTestataEntity testata = getPromozioneTestataEntity(id);
			if (testata == null) {
				log.warn("Nessuna promozione presente con id " + id);
				return Response.status(Status.NO_CONTENT).build();
			}
			if (!promoSecurity.isAccessible(testata, getApplicationUser(null))
					|| (!isUserAdmin() && !ownershipService.hasOwnership(testata, getApplicationUser(null).getGruppi()))) {
				return Response.status(Response.Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione non accessibile").asJson()).build();
			}
			if (promozioneTestataHelper.delete(testata, getCurrentUser().getName())) {
				DbPromoAgCellUtils.putValue(node, "promoDeletedDescription", testata.getDescrizioneEstesa(), false);
				return Response.ok(node.toString()).build();
			} else {
				log.error("Impossibile eliminare promozione con id " + testata.getId());
				return Response.status(Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione non cancellabile").asJson()).build();
			}
		} catch (final Exception e) {
			log.error("Error delete promozione", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/savePromoHeaders")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePromoHeaders(@QueryParam("contesto") String context, String jsonEntity,
			@Context HttpServletRequest request) {
		
		log.debug(jsonEntity);
		final ArrayList<JsonNode> savedJsonNodeList = new ArrayList<>();
		final ArrayList<JsonNode> tmpJsonNodeList = new ArrayList<>();
		final AtomicReference<Boolean> isPromoSaved = new AtomicReference<>(false);
		JsonNode jsonNodeArray;
		try {
			jsonNodeArray = JsonUtils.getMapper().readTree(jsonEntity);
		} catch (final IOException e) {
			log.error(String.format("Error read JsonNode array from request %s", jsonEntity), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		if (!jsonNodeArray.isArray()) {
			log.error("Request does not contain a valid JsonNode Array");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		final UserDTO userDto = getApplicationUser(context);
		final List<String> messages = new ArrayList<>();
		jsonNodeArray.forEach(node -> {
			try {
				if (!creaPromozioneFactory.isRowNodeEmpty(node) && creaPromozioneFactory.isAllCellsPopulated(node)) {
					List<String> msg = createPromoHeader(node,userDto, true);
					if ((msg != null) && msg.isEmpty() ) {
						savedJsonNodeList.add(node);
						isPromoSaved.set(true);
					} else {
						messages.addAll(msg);
					}
				} else {
					tmpJsonNodeList.add(node);
				}
			} catch (final Exception e) {
				log.error("error deserializing object " + node.toString(), e);
			}
		});
		try {
			final ObjectNode promo = JsonUtils.getMapper().createObjectNode();
			if ( savedJsonNodeList.size() > 0 ) {
				promo.put("promoSaved", JsonUtils.writeValueAsString(savedJsonNodeList));
			}
			promo.put("isPromoSaved", isPromoSaved.get());
			promo.put("promoError", JsonUtils.writeValueAsString(tmpJsonNodeList));

			if ( (messages != null) && !messages.isEmpty()) {
				List<String> msg = messages.stream().distinct().collect(Collectors.toList());
				promo.put("message", String.join("\n", msg));
				return Response.status(Status.PRECONDITION_FAILED).entity(JsonUtils.writeValueAsString(promo)).build();
			} else {
				return Response.ok(JsonUtils.writeValueAsString(promo)).build();
			}
		} catch (final Exception e) {
			log.error("Error saving header promozione", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/savePromoHeader")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePromoHeader(@QueryParam("contesto") String context, String jsonEntity,
			@Context HttpServletRequest request) {
		
		log.debug(jsonEntity);
		JsonNode node;
		try {
			node = JsonUtils.getMapper().readTree(jsonEntity);
		} catch (final IOException e) {
			log.error(String.format("Error read JsonNode from request %s", jsonEntity), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		List<String> messages = createPromoHeader(node, getApplicationUser(context), false);
		try {
			if ((messages != null) && !messages.isEmpty()) {
				((ObjectNode) node).put("message", String.join("\n", messages));
				return Response.status(Status.PRECONDITION_FAILED).entity(JsonUtils.writeValueAsString(node)).build();
			} else {
				return Response.ok(JsonUtils.writeValueAsString(node)).build();
			}
		} catch (Exception e) {
			log.error("Error saving Promo", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	private List<String> createPromoHeader(final JsonNode node, final UserDTO userDto, boolean multiPromo) {
		final List<String> messages = new ArrayList<>();
		PromozioneTestataEntity testata = null;
		try {
			testata = creaPromozioneFactory.build((PromozioneTestataEntity) AuditLogFiller
					.fillAuditLogFields(new PromozioneTestataEntity(), userDto.getUser().getName()), node,
					userDto);
			if (!promoSecurity.isAccessible(testata, userDto)) {
				messages.add("Promozione non abilitata");
			}
			// controllo se posso creare la promozione
			// controllo ho raggiunto il numero massimo di promozioni per il canale
			boolean canCreate=createPromotionService.runFunctionCountTestate(testata.getCanalePromozioneEntity().getId(), testata.getDataInizio(), testata.getDataFine(), testata.getMuiCanalePromozione().getMaxPromo());
			if ( !canCreate){
				messages.add(String.format("Numero massimo di testate disponibili esaurito per il canale %s", testata.getCanalePromozioneEntity().getDescrizione()));
			}
			// controllo se ho raggiunto il numero massimo di promozioni per il reparto
			canCreate=createPromotionService.runFunctionCountTestate(userDto.getUser().getId().longValue(),testata.getCanalePromozioneEntity().getId(), testata.getDataInizio(), testata.getDataFine());
			if ( !canCreate){
				messages.add("Numero massimo di testate disponibili esaurito per i reparti utente");
			}
		} catch (Exception e) {
			// qualcosa non torna
			log.error("Error creating promozione testata", e);
			if (e.getMessage() != null) {
				messages.add(e.getMessage());
			} else {
				messages.add(
						"Impossibile creare la promozione: controllare la configurazione del canale e delle meccaniche");
			}
		}
		if (messages.isEmpty()) {
			final List<String> validationMessages = promotionValidatorService.validateNewPromotion(testata, userDto.getCanali(),
					testata.getCanalePromozioneEntity().getGruppoPromozioneEntity());
			if  ((validationMessages != null) && ! validationMessages.isEmpty()) {
				messages.addAll(validationMessages);
			}
		}
		if (messages.isEmpty()) {
			if ((testata.getCodicePromozione() != null) && (testata.getMuiCfgSetPianificazione() != null)) {
				try {
					promozioneTestataHelper.addPromozioneFlags(testata, userDto.getUser().getName());
					promozioneTestataHelper.addPromozioneAttributi(testata, userDto.getUser().getName());
					promozioneTestataHelper.addPromozioneMarchiPrivati(testata, userDto.getUser().getName());
					promozioneTestataHelper.addPromozioneRepartiMarchiPrivati(testata, userDto.getUser().getName());
					if (Boolean.TRUE.equals(testata.getMuiCanalePromozione().getFlValorePuntoFragola())) {
						final BigDecimal valorePunto = valorePuntoServiceInstance.get()
								.findValorePuntoWhereDate(testata.getDataInizio());
						if (valorePunto != null) {
							testata.setValorePunto(valorePunto);
						}
					}
					testata = promoService.persist(testata, userDto.getUser().getName());
					creaPromozioneFactory.deleteRow(node, userDto.getUser().getName());
					if (!multiPromo) {
						((ObjectNode) node).put("idPromozione", testata.getId());
					}
				} catch (final Exception e) {
					log.error("Errore durante il salvataggio di " + testata.toString(), e);
					messages.add(e.getMessage());
				}
			} else {
				final String msg = testata.getMuiCfgSetPianificazione() != null ? "ultimo progressivo anno raggiunto"
						: "rilevato errore in configurazione set pianificazione";
				log.error(msg);
				messages.add(msg);
			}
		}
		return messages;
	}

	@POST
	@Path("/updateCreaPromo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePromotion(@QueryParam("contesto") String context, String jsonEntity,
			@Context HttpServletRequest request) {
		
		log.debug(jsonEntity);
		final long fieldsToCheck = 6;
		JsonNode node;
		try {
			node = JsonUtils.getMapper().readTree(jsonEntity);
		} catch (final IOException e) {
			log.error(String.format("Error read JsonNode from request %s", jsonEntity), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		final String slotId = node.get("slotId") != null ? DbPromoAgCellUtils.getValue(node.get("slotId")) : null;
		final String userId = node.get("userId") != null ? DbPromoAgCellUtils.getValue(node.get("userId")) : null;
		if ((slotId == null) || (userId == null)) {
			return Response.status(Status.PRECONDITION_FAILED).entity(new Message("Promozione non trovata").asJson())
					.build();
		}

		CreaPromozioneEntity creaPromozioneEntity = creaPromozioneFactory.build(node);
		if (creaPromozioneEntity == null) {
			creaPromozioneEntity = new CreaPromozioneEntity();
		}

		String messaggi = "";
		try {
			if (!creaPromozioneFactory.isRowNodeEmpty(node)) {
				final long emptyFields = creaPromozioneFactory.getEmptyCells(node, true).size();
				List<String> messages = null;

				/*
				 * se tutti i campi sono vuoti (non considero messaggio) allora : 1. messaggio
				 * e' vuoto 2. bottone + e' nascosto
				 *
				 * altrimenti 1. se ci sono campi vuoti allora aggiungi messaggio: tutti i campi
				 * sono obbligatori 2. se un campo non valida, allora aggiungi messaggio
				 * ritornato dalla validazione
				 *
				 * se messaggio non vuoto allora bottone + nascosto
				 */
				final UserDTO userDto = getApplicationUser(context);
				if (emptyFields != fieldsToCheck) {
					// c'e almeno un campo non vuoto
					messages = promotionValidatorService.validateNewPromotion(creaPromozioneEntity, userDto.getCanali(),
							creaPromozioneEntity.getGruppoPromozioneEntity());
				}
				messaggi = messages != null ? String.join(";", messages) : "";
				if ((emptyFields != 0) && (emptyFields < fieldsToCheck)) {
					messaggi = String.format("Tutti i campi sono obbligatori%s",
							messaggi.isEmpty() ? "" : ";" + messaggi);
				}
				creaPromozioneEntity.setMessaggio(messaggi);

				creaPromozioneEntity.setDescrizione(creaPromozioneEntity.getDescrizione() != null
						? creaPromozioneEntity.getDescrizione().toUpperCase()
								: creaPromozioneEntity.getDescrizione());

				// #4039: concurrency on copy/paste
				try {
					CreaPromozioneEntity promoByUserIdAndSlotId = createPromotionService.findByUserIdAndSlotId(userId, slotId);
					if (creaPromozioneEntity.getId() == null || !creaPromozioneEntity.getId().equals(promoByUserIdAndSlotId.getId())) {
						creaPromozioneEntity.setId(promoByUserIdAndSlotId.getId());
					}
				} catch (Exception managed) {
					// noop
				}
				createPromotionService.persist(creaPromozioneEntity, getCurrentUser().getName());

				// Filtro i canali disponibili in base al filtro canali sull'utente
				final List<CanalePromozioneEntity> availableChannels = availableChannelsForPromo(creaPromozioneEntity,
						userDto.getCanali(), userDto.getCanaliCreatePromo());

				if (creaPromozioneEntity.getGruppoPromozioneEntity() != null) {
					final List<ComboBoxValues> list = comboBoxFactory.from(availableChannels);

					if (creaPromozioneEntity.getCanalePromozioneEntity() != null) {
						((ObjectNode) node.get("canale")).set("comboBoxValues",
								JsonUtils.getMapper().valueToTree(list));
					} else {
						// devo proprio creare il canale
						((ObjectNode) node).set("canale",
								JsonUtils.getMapper()
								.valueToTree(DBPromoAgCell.builder().name("Canale").editable(true)
										.type(DBPromoCellTypeEnum.COMBOBOX.getType()).value("")
										.comboBoxValues(list).build()));
					}
				} else {
					// EMPTY channels
					((ObjectNode) node).set("canale", JsonUtils.getMapper().valueToTree(
							DBPromoAgCell.builder().name("Canale").editable(true)
									.type(DBPromoCellTypeEnum.COMBOBOX.getType())
									.value("").comboBoxValues(null).build()
					));
				}

				String value = DbPromoAgCellUtils.getValue(DbPromoAgCellUtils.decode(node.get("dataInizio")));
				if (!StringUtils.isEmpty(value) && isToCovert(value)) {
					((ObjectNode) node.get("dataInizio")).put("value",
							dateTimeUtils.toExcelDate(dateTimeUtils.toDate(value)));
				}
				value = DbPromoAgCellUtils.getValue(DbPromoAgCellUtils.decode(node.get("dataFine")));
				if (!StringUtils.isEmpty(value) && isToCovert(value)) {
					((ObjectNode) node.get("dataFine")).put("value",
							dateTimeUtils.toExcelDate(dateTimeUtils.toDate(value)));
				}
				((ObjectNode) node.get("messaggio")).put("value", messaggi);

				if (!creaPromozioneFactory.isAllCellsPopulated(node)) {
					((ObjectNode) node.get("messaggio")).put("value", messaggi);
				}

			} else {
				if (!StringUtils.isBlank(creaPromozioneEntity.getMessaggio())) {
					creaPromozioneEntity.setMessaggio("");
				}
				if (creaPromozioneEntity.getDescrizione() != null) {
					creaPromozioneEntity.setDescrizione(creaPromozioneEntity.getDescrizione().toUpperCase());
				}
				createPromotionService.persist(creaPromozioneEntity, getCurrentUser().getName());
				((ObjectNode) node.get("messaggio")).put("value", "");
			}

			return Response.ok(JsonUtils.writeValueAsString(node)).build();

		} catch (final Exception e) {
			log.error("Error save " + creaPromozioneEntity.toString(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GET
	@Path("/findChannelsByGroup/{groupId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findChannelsByGroup(@PathParam("groupId") String idGruppo, @Context HttpServletRequest request) {
		
		Long id = null;
		try {
			id = Long.parseLong(idGruppo);
		} catch (final Exception e) {
			log.error("impossibile recuperare la lista dei canali senza il gruppo", e);
		}

		List<ComboBoxCapable> list = new ArrayList<>();
		if (id != null) {
			list = (List<ComboBoxCapable>) (List<?>) new ArrayList(
					gruppoPromozioniService.findById(id).getMuiCanalePromoziones());
		}
		final ObjectMapper mapper = JsonUtils.getMapper();
		final ObjectNode node = mapper.createObjectNode();
		final ArrayNode array = node.putArray("remoteValues");
		comboBoxFactory.from(list).stream().sorted(Comparator.comparing(ComboBoxValues::getLabel))
		.forEach(n -> array.add(mapper.valueToTree(n)));
		return array.size() == 0 ? Response.noContent().build() : Response.ok(node.toString()).build();
	}

	@POST
	@Path("/bulkUpdatePromoShops")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response bulkUpdateFlag(PromoShopBulkParam param, @QueryParam("contesto") String contesto,
								   @Context HttpServletRequest request) throws Exception {
		
		final ObjectNode node = JsonUtils.getMapper().createObjectNode();
		String message = null;
		Status status = null;
		final UserDTO userDTO = getApplicationUser(contesto);
		if (userDTO != null) {
			boolean canDo = true;
			if (param.getIdPromozione() != null) {
				final PromozioneTestataEntity testataPromo = promoService.findById(param.getIdPromozione());
				if (testataPromo == null) {
					canDo = false;
					message = String.format("Testata con id %d non esiste a DB; contattare l'assistenza",
							param.getIdPromozione());
					status = Status.PRECONDITION_FAILED;
				} else {
					// #3462 - update solo per stati 10, 30, 311, 410
					final boolean hasOwnership = ownershipService.hasOwnership(testataPromo, userDTO.getGruppi());
					final boolean shopEditable = PromoAcl.isShopEditable(testataPromo) ||
							promoShopsUtil.checkShopEditWhileExecutionInProgressEnabled(testataPromo) ;
					final boolean editable = (isUserAdmin() || hasOwnership) && shopEditable;
					if (!editable) {
						canDo = false;
						message = !hasOwnership
								? String.format("Non hai ownership sulla promozione %s", testataPromo.getDescrizione())
								: String.format("Promozione %s è in uno stato che non consente la modifica", testataPromo.getDescrizione());
						status = Status.PRECONDITION_FAILED;
					}
				}
			}
			if (canDo) {
				final int result = promoShopsUtil.bulkUpdate(param, getCurrentUser().getName(),
						getApplicationUser(null).getCanali());
				if (result < 0) {
					message = "errore durante l'aggiornamento dei negozi";
					status = Status.INTERNAL_SERVER_ERROR;
				} else {
					message = "numero negozi aggiornati: " + result;
					status = Status.OK;
				}
			}
			node.put("message", message);
		} else {
			message = "utente non autorizzato";
			node.put("message", message);
			status = Status.PRECONDITION_FAILED;
		}
		return Response.status(status).entity(node.toString()).build();
	}

	@POST
	@Path("/updatePromoShop/{radioChecked}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePromoShop(@PathParam("radioChecked") String radioChecked, String jsonEntity,
			@Context HttpServletRequest request) throws Exception {
		JsonNode node = JsonUtils.getMapper().readTree(jsonEntity);
		Response response;

		if (radioChecked == null) {
			log.error("error radioChecked is null ");
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Opzione di visualizzazione non valida").asJson()).build();
		}

		String cellValue = DbPromoAgCellUtils.getValue(node.get("promoShopId"));
		if (cellValue == null) {
			((ObjectNode) node).put("message", "Impossibile recuperare il codice del negozio");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(node.toString()).build();
		}

		final Long number = new Long(cellValue);
		PromozioneNegozioEntity promozioneNegozio = negoziPromoService.findById(number);
		final PromozioneTestataEntity testata = promozioneNegozio.getPromozioneTestataEntity();
		if (testata == null) {
			log.error("Errore durante la lettura della testata promozionale con id " + number);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		if (!promoSecurity.isAccessible(testata, getApplicationUser(null))) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non accessibile").asJson()).build();
		}
		final boolean hasOwnership = ownershipService.hasOwnership(testata, getApplicationUser(null).getGruppi());
		final boolean shopEditable = PromoAcl.isShopEditable(testata)
				|| promoShopsUtil.checkShopEditWhileExecutionInProgressEnabled(testata);
		final boolean editable = (isUserAdmin() || hasOwnership) && shopEditable;
		if (!editable) {
			String  message = !hasOwnership
					? String.format("Non hai ownership sulla promozione %s", testata.getDescrizione())
					: String.format("Promozione %s è in uno stato che non consente la modifica", testata.getDescrizione());
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message(message).asJson()).build();
		}

		if (promoShopsUtil.validateUpdatedRow(node)) {
			if (promozioneNegozio != null) {
				cellValue = DbPromoAgCellUtils.getValue(node.get("dataInizio"));
				if (cellValue != null) {
					promozioneNegozio.setDataInizio(dateTimeUtils.excelToDate(cellValue));
				}
				cellValue = DbPromoAgCellUtils.getValue(node.get("dataFine"));
				if (cellValue != null) {
					promozioneNegozio.setDataFine(dateTimeUtils.excelToDate(cellValue));
				}
				cellValue = DbPromoAgCellUtils.getValue(node.get("flag"));
				if (cellValue != null) {
					promozioneNegozio.setSelezioneFlag(cellValue);
				}
				cellValue = DbPromoAgCellUtils.getValue(node.get("codiceMeccanica"));
				promozioneNegozio.setCodiceMeccanica(cellValue);
				promozioneNegozio = (PromozioneNegozioEntity) AuditLogFiller.fillAuditLogFields(promozioneNegozio,
						getCurrentUser().getName());
				negoziPromoService.savePromozioneNegozioEntity(promozioneNegozio);
				((ObjectNode) node).put("message", "Aggiornamento effettuato");

				// Verifica che la modifica rispetti le condizioni di visualizzazione del
				// radioChecked
				// Se la condizione è negata setta il node a vuoto
				if (
						// Se VARIAZIONE_SU_DEFAULT ci deve essere almeno un valore modificato tra
						// selezioneFlag, dataInizio o dataFine
						(PromoShopRadioButtonEnum.VARIAZIONE_SU_DEFAULT.getValue().equals(radioChecked)
								&& promozioneNegozio.getDataInizio()
								.equals(promozioneNegozio.getPromozioneTestataEntity().getDataInizio())
								&& promozioneNegozio.getDataFine()
								.equals(promozioneNegozio.getPromozioneTestataEntity().getDataFine())
								&& promozioneNegozio.getSelezioneFlag().equals(promozioneNegozio.getDefaultFlag()))
						// Se VISUALIZZA_SI selezioneFlag deve essere uguale a 1
						|| (PromoShopRadioButtonEnum.VISUALIZZA_SI.getValue().equals(radioChecked)
								&& !promozioneNegozio.getSelezioneFlag().equals("1"))
						// Se VISUALIZZA_NO selezioneFlag deve essere uguale a 0
						|| (PromoShopRadioButtonEnum.VISUALIZZA_NO.getValue().equals(radioChecked)
								&& !promozioneNegozio.getSelezioneFlag().equals("0"))) {
					node = ((ObjectNode) node).put("hideRow", true);
				} else {
					node = ((ObjectNode) node).put("hideRow", false);
				}
				response = Response.ok(node.toString()).build();
			} else {
				((ObjectNode) node).put("message", "Le date non sono conformi con quelle della promozione");
				response = Response.status(Status.PRECONDITION_FAILED).entity(node.toString()).build();
			}
		} else {
			// oggetto non valido: ripristinare i valori di
			// data inizio
			// data fine
			// flag
			DbPromoAgCellUtils.putValue(node, "dataInizio",
					dateTimeUtils.toExcelDate(promozioneNegozio.getDataInizio()), true);
			DbPromoAgCellUtils.putValue(node, "dataFine", dateTimeUtils.toExcelDate(promozioneNegozio.getDataFine()),
					true);
			DbPromoAgCellUtils.putValue(node, "flag", promozioneNegozio.getSelezioneFlag(), true);
			node = ((ObjectNode) node).put("hideRow", false);
			// 3440: need original row in response
			ObjectNode error = JsonUtils.getMapper().createObjectNode();
			error.put("message", "Le date non sono conformi con quelle della promozione");
			error.set("originalRow", node);

			response = Response.status(Status.PRECONDITION_FAILED).entity(error.toString()).build();
		}
		return response;
	}

	@GET
	@Path("/schedapromo/meccaniche/rowData/{promoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataSchedaPromoMeccaniche(@PathParam("promoId") String promoId,
			@Context HttpServletRequest request) {
		
		if ((promoId == null) || promoId.isEmpty()) {
			return null;
		}

		final PromozioneTestataEntity testata = getPromozioneTestataEntity(new Long(promoId));
		if (testata == null) {
			log.error("Errore durante la lettura della testata promozionale con id " + promoId);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		if (!promoSecurity.isAccessible(testata, getApplicationUser(null))) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non abilitata").asJson()).build();
		}

		try {
			return Response.status(Response.Status.OK).entity(
							schedaPromoUtil.createMeccanicheRowData(testata, getApplicationUser(null).getGruppi(), isUserAdmin()))
					.build();
		} catch (Exception ex) {
			log.error(String.format("Error creating rowData for ws /schedapromo/meccaniche/rowData/%s", promoId), ex);
			return Response.serverError().entity(new Message("Errore recupero rowData meccaniche").asJson()).build();
		}
	}

	@GET
	@Path("/schedapromo/sottoscrizioni/rowData/{promoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataSchedaPromoSottoscrizioni(@PathParam("promoId") String promoId,
														@Context HttpServletRequest request) {
		if ((promoId == null) || promoId.isEmpty()) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non trovata").asJson()).build();
		}
		final PromozioneTestataEntity testata = getPromozioneTestataEntity(new Long(promoId));
		if (testata == null) {
			log.error("Errore durante la lettura della testata promozionale con id " + promoId);
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non trovata").asJson()).build();
		}
		if (!promoSecurity.isAccessible(testata, getApplicationUser(null))) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non abilitata").asJson()).build();
		}
		try {
			return Response.status(Response.Status.OK)
					.entity(schedaPromoUtil.createSottoscrizioniRowData(testata.getSottoscrizioni())).build();
		} catch (Exception ex) {
			log.error(String.format("Error creating rowData for ws /schedapromo/sottoscrizioni/rowData/%s", promoId), ex);
			return Response.serverError().entity(new Message("Errore recupero rowData sottoscrizioni").asJson()).build();
		}
	}

	@DELETE
	@Path("/schedapromo/{promoId}/sottoscrizioni/{sottoscrizioneId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePromoSottoscrizioni(@PathParam("promoId") Long promoId,
											  @PathParam("sottoscrizioneId") Long sottoscrizioneId,
											  @Context HttpServletRequest request) {
		UsersEntity user = getCurrentUser();
		if (user == null) {
			log.error("Error getting current user");
			return Response.status(Status.PRECONDITION_FAILED)
					.entity(new Message("Utente non autenticato").asJson()).build();
		}
		if (promoId == null || sottoscrizioneId == null) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione o sottoscrizione non trovata").asJson()).build();
		}
		try {
			PromozioneTestataEntity promozione = promoService.findById(promoId);
			SottoscrizioneEntity sottoscrizione = sottoscrizioneService.findById(sottoscrizioneId);
			if (promozione == null || sottoscrizione == null) {
				return Response.status(Response.Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione o sottoscrizione non trovata").asJson()).build();
			}
			if (!promozione.getSottoscrizioni().contains(sottoscrizione)) {
				return Response.status(Response.Status.PRECONDITION_FAILED)
						.entity(new Message("Sottoscrizione '%d' non trovata nella promozione '%d'",
								sottoscrizioneId, promoId).asJson())
						.build();
			}
			promozione.removeSottoscrizione(sottoscrizione);
			promozione = promoService.persist(promozione, user.getName());
			return Response.status(Response.Status.OK)
					.entity(schedaPromoUtil.createSottoscrizioniRowData(promozione.getSottoscrizioni())).build();
		} catch (Exception ex) {
			log.error(String.format("Error deleting promoSottoscrizioni for ws /schedapromo/%d/sottoscrizioni/%d",
					promoId, sottoscrizioneId), ex);
			return Response.serverError()
					.entity(new Message("Errore cancellazione sottoscrizione promo").asJson()).build();
		}
	}

	@GET
	@Path("/schedapromo/reparti/rowData/{promoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataSchedaPromoReparti(@PathParam("promoId") String promoId,
			@Context HttpServletRequest request) {
		
		if ((promoId == null) || promoId.isEmpty()) {
			return null;
		}

		final PromozioneTestataEntity testata = getPromozioneTestataEntity(new Long(promoId));
		if (testata == null) {
			log.error("Errore durante la lettura della testata promozionale con id " + promoId);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		if (!promoSecurity.isAccessible(testata, getApplicationUser(null))) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non abilitata").asJson()).build();
		}

		return Response.status(Response.Status.OK).entity(
				schedaPromoUtil.createRepartiRowData(testata, getApplicationUser(null).getGruppi(), isUserAdmin()))
				.build();
	}

	@GET
	@Path("/schedapromo/tipocassa/rowData/{promoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataSchedaPromoTipoCassa(@PathParam("promoId") String promoId,
			@Context HttpServletRequest request) {
		if ((promoId == null) || promoId.isEmpty()) {
			return null;
		}

		final PromozioneTestataEntity testata = getPromozioneTestataEntity(new Long(promoId));
		if (testata == null) {
			log.error("Errore durante la lettura della testata promozionale con id " + promoId);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		if (!promoSecurity.isAccessible(testata, getApplicationUser(null))) {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Promozione non abilitata").asJson()).build();
		}

		return Response.status(Response.Status.OK).entity(
				schedaPromoUtil.createTipoCassaRowData(testata, getApplicationUser(null).getGruppi(), isUserAdmin()))
				.build();
	}

	@GET
	@Path("/schedapromo/meccaniche/columnDef")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefSchedaPromoMeccaniche(@QueryParam("contesto") String context,
													  @Context HttpServletRequest request) {
		return loadColumnDefFromFile("schedaPromo_meccaniche_columnDef.json",
				"db_schedapromo_meccaniche", context);
	}

	@GET
	@Path("/schedapromo/sottoscrizioni/columnDef")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefSchedaPromoSottoscrizioni(@QueryParam("contesto") String context,
														  @Context HttpServletRequest request) {
		return loadColumnDefFromFile("schedaPromo_sottoscrizioni_columnDef.json",
				"db_schedapromo_sottoscrizioni", context);
	}

	@GET
	@Path("/schedapromo/reparti/columnDef")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefSchedaPromoReparti(@QueryParam("contesto") String context,
												   @Context HttpServletRequest request) {
		return loadColumnDefFromFile("schedaPromo_reparti_columnDef.json",
				"db_schedapromo_meccaniche", context);
	}

	@GET
	@Path("/schedapromo/tipocassa/columnDef")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefSchedaPromoTipoCassa(@QueryParam("contesto") String context,
													 @Context HttpServletRequest request) {
		return loadColumnDefFromFile("schedaPromo_tipocassa_columnDef.json",
				"db_schedapromo_tipocassa", context);
	}

	@DELETE
	@Path("/promoReparto/delete/{promoId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePromoReparto(@PathParam("promoId") String promoId, String jsonEntity,
			@Context HttpServletRequest request) {
		
		final String[] data = jsonEntity.split("=");
		if (data.length != 2) {
			log.error("error in parameter jsonEntity: " + jsonEntity);
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			final long id = Long.parseLong(promoId);
			final long idReparto = Long.parseLong(data[1]);
			final PromozioneTestataEntity testata = getPromozioneTestataEntity(id);
			if (testata == null) {
				log.error("error retrieving promozione with id " + id);
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
			final List<String> gruppi = getApplicationUser(null).getGruppi();
			if (!promoSecurity.isAccessible(testata, getApplicationUser(null))
					&& !ownershipService.hasOwnership(testata, gruppi)) {
				return Response.status(Response.Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione non abilitata").asJson()).build();
			}
			RepartoEntity reparto = testata.getReparti().stream().filter(r -> r.getId().equals(idReparto)).findFirst()
					.orElse(null);
			if (reparto == null) {
				log.error(String.format("error retrieving reparto with id %d for promo %d", idReparto, id));
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
			testata.removeReparto(reparto);
			try {
				promoService.persist(testata, getCurrentUser().getName());
				return Response.status(Response.Status.OK)
						.entity(schedaPromoUtil.createRepartiRowData(testata, gruppi, isUserAdmin())).build();
			} catch (final Exception e) {
				log.error("error saving promozione ", e);
				testata.addReparto(reparto); // put it back...
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}

		} catch (final Exception e) {
			log.error("error deleting reparto for promo " + promoId + " and reparto " + data[1], e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/promoMeccanica/delete/{promoId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePromoMeccanica(@PathParam("promoId") String promoId, String jsonEntity,
			@Context HttpServletRequest request) {
		
		final String[] data = jsonEntity.split("=");
		if (data.length != 2) {
			log.error("error in parameter jsonEntity: " + jsonEntity);
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		try {
			final long id = Long.parseLong(promoId);
			final long idMeccanica = Long.parseLong(data[1]);
			final PromozioneTestataEntity testata = getPromozioneTestataEntity(id);
			if (testata == null) {
				log.error("error retrieving promozione with id " + id);
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
			final List<String> gruppi = getApplicationUser(null).getGruppi();
			if (!promoSecurity.isAccessible(testata, getApplicationUser(null))
					&& !ownershipService.hasOwnership(testata, gruppi)) {
				return Response.status(Response.Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione non abilitata").asJson()).build();
			}

			final PromozioneMeccanicheEntity meccanica = testata.getPromozioneMeccanicheEntities().stream()
					.filter(pme -> pme.getId() == idMeccanica).findFirst().orElse(null);
			if (meccanica == null) {
				log.error("error retrieving meccanica with id " + idMeccanica);
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
			testata.removeMuiPromozioneMeccanich(meccanica);
			try {
				promoService.persist(testata, getCurrentUser().getName());
				return Response.status(Response.Status.OK)
						.entity(schedaPromoUtil.createMeccanicheRowData(testata, gruppi, isUserAdmin())).build();
			} catch (final Exception e) {
				log.error("error saving promozione ", e);
				testata.addMuiPromozioneMeccanich(meccanica); // put it back...
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		} catch (final Exception e) {
			log.error("error deleting meccaniche promo for promo " + promoId + " and meccanica " + data[1], e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/promoTipoCassa/delete/{promoId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePromoTipoCassa(@PathParam("promoId") Long promoId, String jsonBody,
			@Context HttpServletRequest request) {
		
		String msg = String.format("Delete PromozioneTipoCassa - promoId: %d; jsonBody: %s", promoId, jsonBody);
		log.debug(msg);
		final String[] data = jsonBody.split("=");
		if ((promoId == null) || (data.length != 2)) {
			log.error("Error in request body");
			return Response.status(Status.BAD_REQUEST).build();
		}

		try {
			final long idTipoCassa = Long.parseLong(data[1]);
			final PromozioneTestataEntity testataEntity = getPromozioneTestataEntity(promoId);
			if (testataEntity == null) {
				log.error("Error retrieving promo with id: " + promoId);
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}

			final List<String> gruppi = getApplicationUser(null).getGruppi();
			if (!promoSecurity.isAccessible(testataEntity, getApplicationUser(null))
					&& !ownershipService.hasOwnership(testataEntity, gruppi)) {
				return Response.status(Response.Status.PRECONDITION_FAILED)
						.entity(new Message("Promozione non accessibile").asJson()).build();
			}

			final PromozioneTipoTerminaleEntity promozioneTipoTerminale = testataEntity
					.getPromozioneTipiTerminaleCassa().stream()
					.filter(c -> c.getTipoTerminaleCassaEntity().getId() == idTipoCassa).findFirst().orElse(null);
			if ((promozioneTipoTerminale == null) || (promozioneTipoTerminale.getTipoTerminaleCassaEntity() == null)) {
				log.error("Error retrieving tipo cassa with id: " + idTipoCassa);
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}

			testataEntity.removePromozioneTipoTerminale(promozioneTipoTerminale);
			try {
				promoService.persist(testataEntity, getCurrentUser().getName());
				return Response.status(Response.Status.OK)
						.entity(schedaPromoUtil.createTipoCassaRowData(testataEntity, gruppi, isUserAdmin())).build();
			} catch (final Exception ex) {
				log.error("Error saving promo with deleted tipo cassa", ex);
				// If something went wrong, put it back ...
				testataEntity.addPromozioneTipoTerminale(promozioneTipoTerminale);
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
		} catch (final Exception ex) {
			msg = String.format("Something went wrong deleting tipo cassa for promo %d and tipo cassa %s", promoId,
					jsonBody);
			log.error(msg, ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	private boolean isToCovert(String date) {
		try {
			new BigDecimal(date).longValue();
		} catch (final Exception ex) {
			return true;
		}
		return false;
	}

	/**
	 * Ritorna la lista dei canali disponibili, in base al filtro canali
	 * dell'utente: - null, non viene considerato e vengono restituiti tutti i
	 * canali disponibili per il gruppo associato alla promo - lista vuota, l'utente
	 * non ha visibilità sui canali e viene restituita una lista vuota - lista
	 * popolata, viene applicato il filtro sui canali disponibili per il gruppo
	 * associato alla promo
	 *
	 * @param creaPromozioneEntity promozione in creazione
	 * @param canali               filtro canali abilitato per l'utente corrente
	 * @param creaPromoCanali
	 * @return lista canali disponibili per il gruppo associato alla promo
	 */
	private List<CanalePromozioneEntity> availableChannelsForPromo(@NonNull CreaPromozioneEntity creaPromozioneEntity,
			List<CanalePromozioneEntity> canali, List<Long> creaPromoCanali) {
		// Nel caso la promozione in creazione non ha ancora un gruppo assegnato,
		// ritorno una lista vuota di canali;
		// il canale selezionabile dipende dal gruppo promozione selezionato
		if (creaPromozioneEntity.getGruppoPromozioneEntity() == null) {
			return Collections.emptyList();
		}

		if (canali == null) {
			return new ArrayList<>(creaPromozioneEntity.getGruppoPromozioneEntity().getMuiCanalePromoziones());
		}

		if (canali.isEmpty()) {
			return Collections.emptyList();
		}

		return creaPromozioneEntity.getGruppoPromozioneEntity().getMuiCanalePromoziones().stream()
				.filter(c -> creaPromoCanali.contains(c.getCodiceCanale())).collect(Collectors.toList());
	}

	// #3402 - controlli se è consentita la modifica alla testata promozione
	private boolean isTestataEditable(PromozioneTestataEntity testata, PromoUpdate promo) {
		final StatoPromozioneEntity stato = PromoAcl.getStatoCorrente(testata);
		if (stato == null) {
			return false;
		}
		// descrizione modificata su una testata in stato non consentito
		if (!StringUtils.equals(testata.getDescrizione(), promo.getDescrizione())
				&& !PromoAcl.isEditableDescription(stato)) {
			return false;
		}
		// note modificate su una testata in stato non consentito
		if (!StringUtils.equals(testata.getNoteMarketing(), promo.getNoteMarketing())
				&& !PromoAcl.isEditableNotes(stato)) {
			return false;
		}
		// data inizio modificata su una testata in stato non consentito
		if (!testata.getDataInizio().equals(promo.getDataInizio()) && !PromoAcl.isEditableStartDate(stato)) {
			return false;
		}
		// data fine modificata su una testata in stato non consentito
		if (!testata.getDataFine().equals(promo.getDataFine()) && !PromoAcl.isEditableEndDate(stato)) {
			return false;
		}
		return true;
	}

	@POST
	@Path("/sorting/save/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveGridSorting(String sort, @PathParam("id") String codiceGriglia,  @Context HttpServletRequest request) {
		
		UsersEntity user = getCurrentUser();
		if (user == null ){
			log.error("Impossibile recuperare l'utente");
			return Response.status(Status.PRECONDITION_FAILED).build();
		}

		if ( StringUtils.isBlank(sort) ){
			sort = "[]";
		}

		/*
		devo fare il parse dei sorting utente
		 */
		TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
		try {
			Map<String, String> currentSort = JsonUtils.getMapper().readValue(user.getGridSorting(),typeRef);
			currentSort.put(codiceGriglia,sort);
			user.setGridSorting(JsonUtils.getMapper().writeValueAsString(currentSort));
			try {
				user = muiService.persistUser(user);
			} catch (Exception e){
				log.error("Errore durante il salvataggio delle impostazioni utente", e);
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new Message("Errore durante il salvataggio dell'ordinamento").asJson()).build();
			}
		} catch (IOException e) {
			log.error("Impossibile leggere la configurazione utente per " + user.getName(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new Message("Configurazione utente corrotta").asJson()).build();
		}
		return Response.ok().build();
	}

	@GET
	@Path("/sorting/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGridSorting(@PathParam("id") String codiceGriglia, @Context HttpServletRequest request) {
		
		UsersEntity user = getCurrentUser();
		if (user == null ){
			log.error("Impossibile recuperare l'utente");
			return Response.status(Status.PRECONDITION_FAILED).build();
		}
		TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
		try {
			Map<String, String> currentSort = JsonUtils.getMapper().readValue(user.getGridSorting(),typeRef);
			String sort = currentSort.get(codiceGriglia);
			if ( sort == null)
				sort = "[]";
			return Response.ok(sort).build();
		} catch (IOException e) {
			log.error("Impossibile leggere la configurazione utente per " + user.getName(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new Message("Configurazione utente corrotta").asJson()).build();
		}
	}
}
