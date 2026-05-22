package com.axiante.mui.webapp.webservice;

import com.axiante.mui.dbpromo.business.utils.BarcodeUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozionePianificazioneService;
import com.axiante.mui.webapp.utils.security.PromoSecurity;
import com.axiante.mui.webapp.webservice.pojo.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.zxing.WriterException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.enterprise.context.Dependent;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;

@Path("/barcode")
@Dependent
@Slf4j
public class BarcodeServiceResource extends SessionEnabledResource {
	@Inject
	transient PromozionePianificazioneService service;

	@Inject
	transient BarcodeUtils utils;

	@Inject
	transient PromoSecurity promoSecurity;

	@GET
	@Path("/ean13/{id}")
	@Produces({ "image/png", MediaType.APPLICATION_JSON })
	public Response generateEan13(@PathParam("id") String id, @Context HttpServletRequest request)
			throws JsonProcessingException {
		if (id != null) {
			try {
				final PromozionePianificazioneEntity entity = service.findById(Long.parseLong(id));
				if (entity == null) {
					return Response.status(Response.Status.PRECONDITION_FAILED)
							.entity(new Message("Promozione inesistente").asJson()).build();
				}
				if (!promoSecurity.isAccessible(entity.getPromozioneTestataEntity(), getApplicationUser(null))) {
					return Response.status(Response.Status.PRECONDITION_FAILED)
							.entity(new Message("Promozione non accessibile").asJson()).build();
				}
				final BufferedImage image = utils.generateEan13ImageFromEntity(entity);
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "png", baos);
				final byte[] imageData = baos.toByteArray();
				final ResponseBuilder response = Response.ok(new ByteArrayInputStream(imageData));
				response.header("Content-type", "image/png");
				return response.build();
			} catch (final UnsupportedOperationException | IllegalArgumentException e) {
				log.error("Error generating barcode for pianificazione id " + id, e);
				return Response.status(Response.Status.PRECONDITION_FAILED).entity(new Message(e.getMessage()).asJson())
						.header("Content-type", MediaType.APPLICATION_JSON).build();
			} catch (final WriterException | IOException e) {
				log.error("Error generating barcode for pianificazione id " + id, e);
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(new Message(e.getMessage()).asJson()).header("Content-type", MediaType.APPLICATION_JSON)
						.build();
			}
		} else {
			return Response.status(Response.Status.PRECONDITION_FAILED)
					.entity(new Message("Pianificazione mancante").asJson())
					.header("Content-type", MediaType.APPLICATION_JSON).build();
		}
	}
}
