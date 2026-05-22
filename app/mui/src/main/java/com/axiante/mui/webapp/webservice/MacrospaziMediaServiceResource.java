package com.axiante.mui.webapp.webservice;

import com.axiante.mui.dbpromo.persistence.dto.MacrospazioWithEventsDTO;
import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiMacrospazioMediaService;
import com.axiante.mui.webapp.utils.MacrospaziMediaUtils;
import com.axiante.mui.webapp.webservice.util.MacrospaziMediaRowdataUtils;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/macrospazimedia")
@Slf4j
@Dependent
public class MacrospaziMediaServiceResource extends SessionEnabledResource {
    @Inject
    Instance<MuiMacrospazioMediaService> serviceInstance;

    @Inject
    MacrospaziMediaUtils utils;

    @Inject
    MacrospaziMediaRowdataUtils rowdataUtils;

    @GET
    @Path("/columnDef")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMacrospaziMediaColumnDef(@QueryParam("contesto") String contesto) {
        return loadColumnDefFromFile(
                "columnDefMacrospaziMedia.json", "db_grid_macrospazi", contesto);
    }

    @GET
    @Path("/rowData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMacrospaziMediaRowdata(@QueryParam("contesto") String contesto) {
        List<MacrospazioWithEventsDTO> macrospazi = serviceInstance.get().findAllWithHasEvents();
        return Response.ok().entity(rowdataUtils.createRowData(macrospazi)).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMacrospazioMedia(@PathParam("id") Long id) {
        if (id != null) {
            MuiMacrospazioMediaEntity entity = serviceInstance.get().findById(id);
            if (entity != null) {
                if (utils.canDelete(entity)) {
                    log.debug("deleting macrospazio media with id {}", id);
                    serviceInstance.get().remove(entity);
                    return Response.ok().build();
                }
                // cannot delete
                log.warn("macrospazio media with id {} has events and cannot be deleted", id);
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }
            // not present
            log.error("macrospazio media with id {} not found", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        // id cannot be null
        log.error("id should not be null");
        return Response.status(Response.Status.PRECONDITION_FAILED).build();
    }
}
