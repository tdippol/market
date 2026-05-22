package com.axiante.mui.webapp.webservice;

import com.axiante.mui.webapp.webservice.pojo.HealthInventory;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HealthResource {
	@GET
	@Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatus() {
		MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
		long memUsed = memBean.getHeapMemoryUsage().getUsed();
		long memMax = memBean.getHeapMemoryUsage().getMax();

		HealthInventory inventory = new HealthInventory().withData("memory used", memUsed)
				.withData("memory max", memMax).status(memUsed < memMax * 0.9);

		if (inventory.isStatus()) {
			return Response.ok().entity(inventory).build();
		} else {
			log.error("Memory usage is too high: {}/{}", memUsed, memMax);
			return Response.serverError().entity(inventory).build();
		}
	}
}
