package com.axiante.mui.webapp.webservice;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.CfgSetPianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.CfgTipoElementoService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneCampiService;
import com.axiante.mui.webapp.webservice.util.ConfigurationHelper;
import com.axiante.mui.webapp.webservice.util.ConfigurationRowDataHelper;
import com.axiante.mui.webapp.webservice.util.PianificazioneCampiUtil;
import com.axiante.mui.webapp.webservice.util.configuration.CfgPianificazioneEntityHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import javax.enterprise.context.Dependent;
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
import javax.ws.rs.core.Response.Status;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Path("/configuration")
@Dependent
public class ConfigurationResource extends SessionEnabledResource {

	@Inject
	transient private ConfigurationRowDataHelper configurationRowDataHelper;

	@Inject
	transient private ConfigurationHelper configurationHelper;

	@Inject
	transient private CfgPianificazioneEntityHelper pianificazioneEntityHelper;

	@Inject
	transient CfgSetPianificazioneService cfgSetPianificazioneService;

	@Inject
	transient CfgConfHeaderService cfgConfHeaderService;

	@Inject
	transient CfgTipoElementoService tipoElementoService;

	@Inject
	transient CfgPianificazioneService pianificazioneService;
	
	@Inject
	private PianificazioneCampiService pianificazioneCampiService;
	
	@Inject
	private PianificazioneCampiUtil pianificazioneCampiUtil;

	@GET
	@Path("/columnDef/planningConfigurazione")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefPlanningConfigurazione(@QueryParam("contesto") String context,
													   @Context HttpServletRequest request) {
		return loadColumnDefFromFile("configurazionePlanningConfigurazione.json",
				"db_planning_configurazione", context);
	}

	@GET
	@Path("/rowData/planningConfigurazione/{idHeader}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataPlanningConfigurazione(@PathParam("idHeader") String idHeader,
													 @Context HttpServletRequest request) {
		if (!isUserAdmin()) {
			log.error(String.format("Accesso alla risorsa non consentito per l'utente %s",
					getCurrentUser() != null ? getCurrentUser().getName() : "anonymous"));
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try {
			final CfgConfHeaderEntity confHeader = cfgConfHeaderService.findById(Long.parseLong(idHeader));
			if (confHeader == null) {
				log.error(String.format("CfgConfHeader with id %s not found", idHeader));
				return Response.status(Status.PRECONDITION_FAILED).build();
			}

			final String rowData = configurationRowDataHelper.createRowDataPlanningPianificazione(confHeader);
			log.debug("Loaded rowData: " + rowData);
			return Response.ok(rowData).build();
		} catch (Exception ex) {
			log.error(String.format("Error getting rowData for CfgPianificazione con idHeader %s", idHeader), ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/columnDef/planningHeader")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefPlanningHeader(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
		
		return loadColumnDefFromFile("configurazionePlanningHeader.json", "db_planning_header", context);
	}

	@GET
	@Path("/rowData/planningHeader/{idSetPianificazione}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataPlanningHeader(@PathParam("idSetPianificazione") String idSetPianificazione,
											 @Context HttpServletRequest request) {
		
		if (!isUserAdmin()) {
			log.error(String.format("Accesso alla risorsa non consentito per l'utente %s",
					getCurrentUser() != null ? getCurrentUser().getName() : "anonymous"));
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try {
			final CfgSetPianificazioneEntity cfgSetPianificazione = cfgSetPianificazioneService
					.findById(Long.parseLong(idSetPianificazione));
			if (cfgSetPianificazione == null) {
				log.error(String.format("CfgSetPianificazione with id %s not found", idSetPianificazione));
				return Response.status(Status.PRECONDITION_FAILED).build();
			}

			final String rowData = configurationRowDataHelper.createRowDataPlanningHeader(cfgSetPianificazione);
			log.debug("Loaded rowData: " + rowData);
			return Response.ok(rowData).build();
		} catch (Exception ex) {
			log.error(String.format("Error getting rowData for SetPianificazione %s", idSetPianificazione), ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/columnDef/planningElemento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefPlanningElemento(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
		return loadColumnDefFromFile("configurazionePlanningElemento.json", "db_planning_elemento", context);
	}

	@GET
	@Path("/rowData/planningElemento/{idHeader}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataPlanningElemento(@PathParam("idHeader") String idHeader,
											   @Context HttpServletRequest request) {
		
		if (!isUserAdmin()) {
			log.error(String.format("Accesso alla risorsa non consentito per l'utente %s",
					getCurrentUser() != null ? getCurrentUser().getName() : "anonymous"));
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try {
			final CfgConfHeaderEntity confHeader = cfgConfHeaderService.findById(Long.parseLong(idHeader));
			if (confHeader == null) {
				log.error(String.format("CfgConfHeader with id %s not found", idHeader));
				return Response.status(Status.PRECONDITION_FAILED).build();
			}

			final String rowData = configurationRowDataHelper.createRowDataPlanningTipoElemento(confHeader);
			log.debug("Loaded rowData: " + rowData);
			return Response.ok(rowData).build();
		} catch (Exception ex) {
			log.error(String.format("Error getting rowData for ConfHeader %s", idHeader), ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/planningHeader/{idHeader}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePlanningHeader(@PathParam("idHeader") String idHeader, @Context HttpServletRequest request) {
		
		if (!isUserAdmin()) {
			log.error(String.format("Accesso alla risorsa non consentito per l'utente %s",
					getCurrentUser() != null ? getCurrentUser().getName() : "anonymous"));
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try {
			Long id;
			try {
				id = Long.parseLong(idHeader);
			} catch (NumberFormatException ex) {
				log.error("Error getting ConfHeader id " + idHeader, ex);
				return Response.status(Status.BAD_REQUEST).build();
			}

			final CfgConfHeaderEntity confHeader = cfgConfHeaderService.findById(id);
			if (confHeader == null) {
				log.error(String.format("ConfHeader with id %s not found", idHeader));
				return Response.status(Status.BAD_REQUEST).build();
			}

			CfgSetPianificazioneEntity cfgSetPianificazione = confHeader.getMuiCfgSetPianificazione();
			if (!PromoAcl.isCfgPianificazioneEditable(cfgSetPianificazione)) {
				log.error(String.format("Header %s non è cancellabile", confHeader.getId()));
				return Response.status(Status.PRECONDITION_FAILED).build();
			}
			// Remove header from 'MUI_CFG_SET_PIANIFICAZIONE'
			cfgSetPianificazione.removeMuiCfgConfHeaderEntity(confHeader);
			cfgSetPianificazioneService.persist(cfgSetPianificazione, getCurrentUser().getName());
			// Reload all headers for given cfgSetPianificazione
			String rowData = configurationRowDataHelper.createRowDataPlanningHeader(cfgSetPianificazione);
			final String message = String.format("Header meccanica '%s' e livello '%s' eliminato dal set pianificazione %s",
					confHeader.getMeccanicaEntity().getCodiceMeccanica(),
					confHeader.getLivelloPianificazione().getCodice(),
					cfgSetPianificazione.getId());
			rowData = addSuccessMessage(rowData, message);
			log.debug(message);
			return Response.ok(rowData).build();
		} catch (Exception ex) {
			log.error(String.format("Error deleting ConfHeader %s", idHeader), ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/planningTipoElemento/{idTipoElemento}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePlanningTipoElemento(@PathParam("idTipoElemento") String idTipoElemento,
											   @Context HttpServletRequest request) {
		
		if (!isUserAdmin()) {
			log.error(String.format("Accesso alla risorsa non consentito per l'utente %s",
					getCurrentUser() != null ? getCurrentUser().getName() : "anonymous"));
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try {
			Long id;
			try {
				id = Long.parseLong(idTipoElemento);
			} catch (NumberFormatException ex) {
				log.error("Error getting TipoElemento id " + idTipoElemento, ex);
				return Response.status(Status.BAD_REQUEST).build();
			}

			final CfgTipoElementoEntity tipoElementoEntity = tipoElementoService.findById(id);
			if (tipoElementoEntity == null) {
				log.error(String.format("TipoElemento with id %s not found", idTipoElemento));
				return Response.status(Status.BAD_REQUEST).build();
			}

			CfgConfHeaderEntity confHeader = tipoElementoEntity.getConfHeader();
			if (!PromoAcl.isCfgPianificazioneEditable(confHeader.getMuiCfgSetPianificazione())) {
				log.error(String.format("Il tipo elemento %s associato al header %s non è cancellabile",
						tipoElementoEntity.getId(), confHeader.getId()));
				return Response.status(Status.PRECONDITION_FAILED).build();
			}
			// Remove 'MUI_CFG_TIPO_ELEMENTO' from 'MUI_CFG_CONF_HEADER' and persist
			confHeader.removeTipoElemento(tipoElementoEntity);
			cfgConfHeaderService.persist(confHeader);
			// Reload all tipo elemento for given header
			String rowData = configurationRowDataHelper.createRowDataPlanningTipoElemento(confHeader);
			final String message = String.format("Tipo elemento eliminato da header con meccanica '%s' e livello '%s'",
					confHeader.getMeccanicaEntity().getCodiceMeccanica(),
					confHeader.getLivelloPianificazione().getCodice());
			rowData = addSuccessMessage(rowData, message);
			log.debug(message);
			return Response.ok(rowData).build();
		} catch (Exception ex) {
			log.error(String.format("Error deleting TipoElemento %s", idTipoElemento), ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/planningConfigurazione/{idPianificazione}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePlanningConfigurazione(@PathParam("idPianificazione") String idPianificazione,
												 @Context HttpServletRequest request) {
		
		if (!isUserAdmin()) {
			log.error(String.format("Accesso alla risorsa non consentito per l'utente %s",
					getCurrentUser() != null ? getCurrentUser().getName() : "anonymous"));
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try {
			Long id;
			try {
				id = Long.parseLong(idPianificazione);
			} catch (NumberFormatException ex) {
				log.error("Error getting CfgPianificazione id " + idPianificazione, ex);
				return Response.status(Status.BAD_REQUEST).build();
			}

			final CfgPianificazioneEntity pianificazioneEntity = pianificazioneService.findById(id);
			if (pianificazioneEntity == null) {
				log.error(String.format("CfgPianificazione with id %s not found", idPianificazione));
				return Response.status(Status.BAD_REQUEST).build();
			}

			CfgConfHeaderEntity confHeader = pianificazioneEntity.getMuiCfgConfHeader();
			if (!PromoAcl.isCfgPianificazioneEditable(confHeader.getMuiCfgSetPianificazione())) {
				log.error(String.format("La pianificazione %s associata al header %s non è cancellabile",
						pianificazioneEntity.getId(), confHeader.getId()));
				return Response.status(Status.PRECONDITION_FAILED).build();
			}
			// Remove 'MUI_CFG_PIANIFICAZIONE' from 'MUI_CFG_CONF_HEADER' and persist
			confHeader.removePianificazione(pianificazioneEntity);
			cfgConfHeaderService.persist(confHeader);
			// Reload all cfg pianificazione for given header
			String rowData = configurationRowDataHelper.createRowDataPlanningPianificazione(confHeader);
			final String message = String.format("Regola eliminata da header con meccanica '%s' e livello '%s'",
					confHeader.getMeccanicaEntity().getCodiceMeccanica(),
					confHeader.getLivelloPianificazione().getCodice());
			rowData = addSuccessMessage(rowData, message);
			log.debug(message);
			return Response.ok(rowData).build();
		} catch (Exception ex) {
			log.error(String.format("Error deleting CfgPianificazione %s", idPianificazione), ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/planningHeader/{idHeader}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePlanningHeader(@PathParam("idHeader") String idHeader, String jsonEntity,
										 @Context HttpServletRequest request) {
		
		if (!isUserAdmin()) {
			log.error(String.format("Accesso alla risorsa non consentito per l'utente %s",
					getCurrentUser() != null ? getCurrentUser().getName() : "anonymous"));
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try {
			Long id;
			try {
				id = Long.parseLong(idHeader);
			} catch (NumberFormatException ex) {
				log.error(String.format("Error getting ConfHeader %s", idHeader), ex);
				return Response.status(Status.PRECONDITION_FAILED).build();
			}

			final CfgConfHeaderEntity confHeader = cfgConfHeaderService.findById(id);
			if (confHeader == null) {
				log.error(String.format("ConfHeader with id %s not found", idHeader));
				return Response.status(Status.BAD_REQUEST).build();
			}

			if (!PromoAcl.isCfgPianificazioneEditable(confHeader.getMuiCfgSetPianificazione())) {
				log.error(String.format("Header %s not editable", idHeader));
				return Response.status(Status.PRECONDITION_FAILED).build();
			}

			if (!configurationHelper.updateHeader(confHeader, jsonEntity)) {
				log.error(String.format("Error updating header %s", idHeader));
				return Response.status(Status.PRECONDITION_FAILED).build();
			}

			cfgConfHeaderService.persist(confHeader);
			final String message = String.format("Header meccanica '%s' e livello '%s' aggiornato correttamente",
					confHeader.getMeccanicaEntity().getCodiceMeccanica(),
					confHeader.getLivelloPianificazione().getCodice());
			jsonEntity = addSuccessMessage(jsonEntity, message);
			log.debug(message);
			return Response.ok(jsonEntity).build();
		} catch (Exception ex) {
			log.error(String.format("Error updating header %s", idHeader), ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/planningTipoElemento/{idTipoElemento}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePlanningTipoElemento(@PathParam("idTipoElemento") String idTipoElemento, String jsonEntity,
											   @Context HttpServletRequest request) {
		
		if (!isUserAdmin()) {
			log.error(String.format("Accesso alla risorsa non consentito per l'utente %s",
					getCurrentUser() != null ? getCurrentUser().getName() : "anonymous"));
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try {
			Long id;
			try {
				id = Long.parseLong(idTipoElemento);
			} catch (NumberFormatException ex) {
				log.error(String.format("Error getting TipoElemento %s", idTipoElemento), ex);
				return Response.status(Status.PRECONDITION_FAILED).build();
			}

			final CfgTipoElementoEntity tipoElementoEntity = tipoElementoService.findById(id);
			if (tipoElementoEntity == null) {
				log.error(String.format("TipoElemento with id %s not found", idTipoElemento));
				return Response.status(Status.BAD_REQUEST).build();
			}

			if (!PromoAcl.isCfgPianificazioneEditable(tipoElementoEntity.getConfHeader().getMuiCfgSetPianificazione())) {
				log.error(String.format("TipoElemento %s not editable", idTipoElemento));
				return Response.status(Status.PRECONDITION_FAILED).build();
			}

			if (!configurationHelper.updateTipoElemento(tipoElementoEntity, jsonEntity)) {
				log.error(String.format("Error updating TipoElemento %s", idTipoElemento));
				return Response.status(Status.PRECONDITION_FAILED).build();
			}

			tipoElementoService.persist(tipoElementoEntity);
			final String message = String.format("Tipo elemento associato ad header meccanica '%s' e livello '%s' aggiornato correttamente",
					tipoElementoEntity.getConfHeader().getMeccanicaEntity().getCodiceMeccanica(),
					tipoElementoEntity.getConfHeader().getLivelloPianificazione().getCodice());
			jsonEntity = addSuccessMessage(jsonEntity, message);
			log.debug(message);
			return Response.ok(jsonEntity).build();
		} catch (Exception ex) {
			log.error(String.format("Error updating TipoElemento %s", idTipoElemento), ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/planningConfigurazione/{idPianificazione}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePlanningConfigurazione(@PathParam("idPianificazione") String idPianificazione,
												 String jsonEntity,
												 @Context HttpServletRequest request) {
		
		if (!isUserAdmin()) {
			log.error(String.format("Accesso alla risorsa non consentito per l'utente %s",
					getCurrentUser() != null ? getCurrentUser().getName() : "anonymous"));
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try {
			Long id;
			try {
				id = Long.parseLong(idPianificazione);
			} catch (NumberFormatException ex) {
				log.error("Error getting CfgPianificazione id " + idPianificazione, ex);
				return Response.status(Status.BAD_REQUEST).build();
			}

			final CfgPianificazioneEntity pianificazioneEntity = pianificazioneService.findById(id);
			if (pianificazioneEntity == null) {
				log.error(String.format("CfgPianificazione with id %s not found", idPianificazione));
				return Response.status(Status.BAD_REQUEST).build();
			}

			if (!PromoAcl.isCfgPianificazioneEditable(pianificazioneEntity.getMuiCfgConfHeader().getMuiCfgSetPianificazione())) {
				log.error(String.format("CfgPianificazione %s not editable", idPianificazione));
				return Response.status(Status.PRECONDITION_FAILED).build();
			}

			JsonNode node = prepareNode(jsonEntity);
			if (node.get("columnToBeUpdated") == null || node.get("valueToBeUpdated") == null) {
				final String msg = String.format("Invalid request values for columnToBeUpdated %s and/or valueToBeUpdated %s",
						node.get("columnToBeUpdated"), node.get("valueToBeUpdated"));
				log.error(msg);
				DbPromoAgCellUtils.putValue(node, "callbackMessage", msg, false);
				return Response.status(Status.PRECONDITION_FAILED).entity(node).build();
			}

			final String columnHeaderToBeUpdated = DbPromoAgCellUtils.getValue(node.get("columnHeaderToBeUpdated"));
			final String columnToBeUpdated = DbPromoAgCellUtils.getValue(node.get("columnToBeUpdated"));
			Object valueToBeUpdated = getValueToBeUpdated(node);
			if (!pianificazioneEntityHelper.invokeSetterForField(pianificazioneEntity, columnToBeUpdated, valueToBeUpdated)) {
				// Reset value in node with message
				final String msg = String.format("Errore nella modifica del campo '%s' con il valore '%s'",
						columnHeaderToBeUpdated, valueToBeUpdated);
				log.error(String.format("%s per la pianificazione %s", msg, idPianificazione));
				final Object value = pianificazioneEntityHelper.invokeGetterForField(pianificazioneEntity, columnToBeUpdated);
				DbPromoAgCellUtils.putValue(node, columnToBeUpdated, String.valueOf(value), true);
				return buildResponseWithOriginalRow(node, msg);
			}

			pianificazioneService.persist(pianificazioneEntity);
			final String message = String.format("Regola associata ad header meccanica '%s' e livello '%s' aggiornata correttamente",
					pianificazioneEntity.getMuiCfgConfHeader().getMeccanicaEntity().getCodiceMeccanica(),
					pianificazioneEntity.getMuiCfgConfHeader().getLivelloPianificazione().getCodice());
			if ("FORMULA_ENABLED".equalsIgnoreCase(columnToBeUpdated)) {
				node = configurationRowDataHelper.createRowNode(pianificazioneEntity,
						pianificazioneEntity.getMuiCfgConfHeader().getId());
			}
			DbPromoAgCellUtils.putValue(node, "callbackMessage", message, false);
			log.debug(message);
			return Response.ok(node.toString()).build();
		} catch (Exception ex) {
			log.error(String.format("Error updating CfgPianificazione %s", idPianificazione), ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/columnDef/planningCampi")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnDefPlanningCampi(@QueryParam("contesto") String context, @Context HttpServletRequest request) {
		
		return loadColumnDefFromFile("configurazionePlanningCampi.json", "db_planning_campi", context);
	}

	@GET
	@Path("/rowData/planningCampi")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRowDataPlanningCampi(@Context HttpServletRequest request) {
		
		if (!isUserAdmin()) {
			log.error(String.format("Accesso alla risorsa non consentito per l'utente %s",
					getCurrentUser() != null ? getCurrentUser().getName() : "anonymous"));
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try {
			final String rowData = configurationRowDataHelper.createRowDataPlanningCampi();
			log.debug("Loaded rowData: " + rowData);
			return Response.ok(rowData).build();
		} catch (Exception ex) {
			log.error("Error getting rowData for CfgPianificazioneCampi", ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Path("/update/planningCampi")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePlanningCampi(String jsonEntity, @Context HttpServletRequest request) throws Exception {
		log.debug(jsonEntity);



		Response response = null;
		final JsonNode node = JsonUtils.getMapper().readTree(jsonEntity);
		// Reset messaggi nel nodo
		DbPromoAgCellUtils.putValue(node, "updateMessage", null, false);
		DbPromoAgCellUtils.putValue(node, "errorMessage", null, false);




		
		if (!isUserAdmin()) {
			log.error(String.format("Accesso alla risorsa non consentito per l'utente %s",
					getCurrentUser() != null ? getCurrentUser().getName() : "anonymous"));
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		if (node.get("columnToBeUpdated") != null && node.get("valueToBeUpdated") != null) {
			final String idCfgPianificazione = DbPromoAgCellUtils.getValue(node.get("id"));
			final String columnToBeUpdated = DbPromoAgCellUtils.getValue(node.get("columnToBeUpdated"));
			final String dataType = DbPromoAgCellUtils.getValue(node.get("tipo"));



			Object valueToBeUpdated = DbPromoAgCellUtils.getValue(node.get("valueToBeUpdated"), Boolean.TRUE);

			if (valueToBeUpdated != null && StringUtils.isEmpty(valueToBeUpdated.toString())) {
				valueToBeUpdated = null;
			}

			if (columnToBeUpdated != null && !columnToBeUpdated.isEmpty()) {

				if(idCfgPianificazione !=null) {
					CfgPianificazioneCampiEntity cfgPianificazioneCampiEntity = pianificazioneCampiService.findById(Long.parseLong(idCfgPianificazione));

					boolean valueValidated = false;

					if(cfgPianificazioneCampiEntity !=null) {

						switch (columnToBeUpdated.toUpperCase()) {
						case PianificazioneConstants.DESCRIZIONE:
							valueValidated = true;
								invokeSetterForField(cfgPianificazioneCampiEntity, columnToBeUpdated, node);
							if (!valueValidated) {
								if(valueToBeUpdated !=null) {
								DbPromoAgCellUtils.putValue(node, columnToBeUpdated,valueToBeUpdated.toString(), true);
								}else {
									DbPromoAgCellUtils.putValue(node, columnToBeUpdated,"", true);
								}
							}
							break;
						case PianificazioneConstants.DESCRIZIONE_ESTESA:
							valueValidated = true;
								invokeSetterForField(cfgPianificazioneCampiEntity, columnToBeUpdated, node);
							
							if (!valueValidated) {
								if(valueToBeUpdated !=null) {
								DbPromoAgCellUtils.putValue(node, columnToBeUpdated,valueToBeUpdated.toString(), true);
								}else {
									DbPromoAgCellUtils.putValue(node, columnToBeUpdated,"", true);
								}
							}
							break;
						case PianificazioneConstants.COLUMN_WIDTH:
							valueValidated = true;
								invokeSetterForField(cfgPianificazioneCampiEntity, columnToBeUpdated, node);
							if (!valueValidated) {
								if(valueToBeUpdated !=null) {
								DbPromoAgCellUtils.putValue(node, columnToBeUpdated,valueToBeUpdated.toString(), true);
								}else {
									DbPromoAgCellUtils.putValue(node, columnToBeUpdated,"", true);
								}
							}
							break;
						}						
						if(valueValidated) {
							try {
								pianificazioneCampiService.saveCfgPianificazioneCampiEntity(cfgPianificazioneCampiEntity,
										getCurrentUser().getName());
								DbPromoAgCellUtils.putValue(node, "updateMessage", "Aggiornamento effettuato",
										false);
							}catch (final Exception e) {
								pianificazioneCampiUtil.resetPersistedValue(node, columnToBeUpdated, cfgPianificazioneCampiEntity, valueToBeUpdated);
								log.error(String.format("Errore update tabella MUI_CFG_PIANIFICAZIONE_CAMPI id %s",
										cfgPianificazioneCampiEntity.getId()), e);
								DbPromoAgCellUtils.putValue(node, "errorMessage",
										"Caricamento fallito: contattare l'assistenza", false);
							}
						}else {
							pianificazioneCampiUtil.resetPersistedValue(node, columnToBeUpdated, cfgPianificazioneCampiEntity, valueToBeUpdated);
							log.error(String.format("Errore update tabella MUI_CFG_PIANIFICAZIONE_CAMPI id %s",
									cfgPianificazioneCampiEntity.getId()));
							DbPromoAgCellUtils.putValue(node, "errorMessage",
									"Caricamento fallito: contattare l'assistenza", false);
						}

					}else {
						log.warn(String.format(
								"Not found CfgPianificazioneCampiEntity with id: %s ",
								idCfgPianificazione));
					}
				}else {
					log.warn(String.format(
							"Not found CfgPianificazioneCampiEntity with id null "));
				}
			}


		}
		response = Response.ok(node.toString()).build();
		return response;
	}

	private Object getValueToBeUpdated(@NonNull final JsonNode node) {
		Object valueToBeUpdated = DbPromoAgCellUtils.getValue(node.get("valueToBeUpdated"), Boolean.TRUE);
		if (valueToBeUpdated != null && StringUtils.isEmpty(valueToBeUpdated.toString())) {
			valueToBeUpdated = null;
		}
		return valueToBeUpdated;
	}

	private JsonNode prepareNode(@NonNull String jsonEntity) throws IOException {
		final JsonNode node = JsonUtils.getMapper().readTree(jsonEntity);
		DbPromoAgCellUtils.putValue(node, "updateMessage", null, false);
		DbPromoAgCellUtils.putValue(node, "errorMessage", null, false);
		return node;
	}

	private String addSuccessMessage(String rowData, String message) {
		try {
			final JsonNode jsonNode = JsonUtils.getMapper().readTree(rowData);
			DbPromoAgCellUtils.putValue(jsonNode, "callbackMessage", message, false);
			return jsonNode.toString();
		} catch (IOException ex) {
			log.error("Error adding message to rowData", ex);
			return rowData;
		}
	}

	private Response buildResponseWithOriginalRow(JsonNode node, String message) {
		final ObjectNode responseNode = JsonUtils.getMapper().createObjectNode();
		responseNode.set("originalRow", node);
		responseNode.put("message", message);
		return Response.status(Status.PRECONDITION_FAILED).entity(responseNode.toString()).build();
	}
	
	private boolean invokeSetterForField(CfgPianificazioneCampiEntity entity, String fieldName, JsonNode node) {
        try {
            switch (fieldName) {
                case "descrizione":
                    final String desc = getValueAsString(DbPromoAgCellUtils.getValue(node.get("descrizione")));
                    entity.setDescrizione(desc);
                    return true;
                case "descrizioneEstesa":
                	 final String descEstesa = getValueAsString(DbPromoAgCellUtils.getValue(node.get("descrizioneEstesa")));
                     entity.setDescrizioneEstesa(descEstesa);
                     return true;
                case "columnWidth":
                    final Integer colWidth = getValueAsInteger(DbPromoAgCellUtils.getValue(node.get("columnWidth")));
                    if (colWidth != null && colWidth == -1) {
                        log.error(String.format("Value '%s' for 'COLUMN_WIDTH' is not valid", DbPromoAgCellUtils.getValue(node.get("columnWidth"))));
                        return false;
                    }
                    entity.setColumnWidth(colWidth);
                    return true;
                
                default:
                    log.warn(String.format("Field '%s' not defined in entity CfgPianificazioneCampiEntity", fieldName));
                    return false;
            }
        } catch (NumberFormatException ex) {
            log.error(String.format("The format of value for node '%s' [%s] is not correct", fieldName, node.toString()), ex);
            return false;
        }
    }
	
	private Integer getValueAsInteger(Object value) {
		final String valueAsString = getValueAsString(value);
		return StringUtils.isBlank(valueAsString) || "null".equalsIgnoreCase(valueAsString.trim())
				? null
				: Integer.parseInt(valueAsString);
	}

	private Integer getValueAsBoolean(Object value) {
		String valueAsString = getValueAsString(value);
		if (StringUtils.isBlank(valueAsString)) {
			return null;
		}
		valueAsString = valueAsString.trim();
		return "true".equalsIgnoreCase(valueAsString)
				? 1
				: "false".equalsIgnoreCase(valueAsString)
					? 0
					: -1;
	}

	private String getValueAsString(Object value) {
		return value == null ? null : (String) value;
	}

	private Boolean getAsBoolean(Object value) {
		String valueAsString = getValueAsString(value);
		if (StringUtils.isBlank(valueAsString)) {
			return false;
		}
		valueAsString = valueAsString.trim();
		return "true".equalsIgnoreCase(valueAsString);
	}
}