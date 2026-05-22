package com.axiante.mui.webapp.webservice;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("health")
public class HealthService extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> webservices = new HashSet<>();
		webservices.add(HealthResource.class);
		return webservices;
	}
}
