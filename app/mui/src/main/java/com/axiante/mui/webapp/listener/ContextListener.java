package com.axiante.mui.webapp.listener;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.dbpromo.persistence.service.UuiUtilityService;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.service.ApplicationPropertiesService;
import com.axiante.mui.persistence.service.MuiService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class ContextListener implements ServletContextListener {
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	@Inject
	ApplicationProperties props;
	@Inject
	ApplicationPropertiesService appService;

	@Inject
	MuiService muiService;

	@Inject
	UuiUtilityService dbpromoService;

	private static final AtomicBoolean workInProgress = new AtomicBoolean(false);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.info("Application started on " + sdf.format(new Date(System.currentTimeMillis())));
		if (workInProgress.get()) {
			log.info("another process is working on the updates");
			return;
		}
		workInProgress.set(true);
		if (props != null) {
			Boolean update = props.getProperty(ApplicationProperties.UPDATE_UUID, (Boolean) null);
			if (update == null) {
				try {
					// add the property and set it to false: we're doing the job
					update = true;
					ApplicationPropertiesEntity p = muiService
							.findApplicationProperty(ApplicationProperties.UPDATE_UUID);
					if (p == null) {
						p = new ApplicationPropertiesEntity();
						p.setKey(ApplicationProperties.UPDATE_UUID);
					}
					p.getUuid();
					p.setValue(Boolean.FALSE.toString());
					muiService.persistApplicationProperty(p);
				} catch (Exception e) {
					log.error("error updating application property " + ApplicationProperties.UPDATE_UUID, e);
				}
			}
			if (update) {
				// do the updates
				try {
					// add the property and set it to false: we're doing the job
					update = true;
					ApplicationPropertiesEntity p = muiService
							.findApplicationProperty(ApplicationProperties.UPDATE_UUID);
					if (p == null) {
						p = new ApplicationPropertiesEntity();
						p.setKey(ApplicationProperties.UPDATE_UUID);
					}
					p.getUuid();
					p.setValue(Boolean.FALSE.toString());
					muiService.persistApplicationProperty(p);
				} catch (Exception e) {
					log.error("error updating application property " + ApplicationProperties.UPDATE_UUID, e);
				} finally {
					update();
				}
			}
		} else {
			log.error("error initializing context");
		}
		workInProgress.set(false);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	private void update() {
		// mui stuff
		updateMenu();
		updateAcl();
		updateApplicationProperties();
		updateConfigurations();
		updateRoles();
		updateUsers();
		// dbpromo stuff
		// TODO: metodo univoco che lavora con un nuovo service
		updateDbPromo();
	}

	private void updateMenu() {
		try {
			muiService.readMenus().forEach(e -> {
				e.getUuid();
				try {
					muiService.persistMenu(e);
				} catch (Exception ex) {
					log.error("error fixing menu id " + e.getIdMenu(), ex);
				}
			});
		} catch (Exception e) {
			log.error("Error ensuring uuid for menus", e);
		}
	}

	private void updateAcl() {
		try {
			muiService.readACLs().forEach(e -> {
				e.getUuid();
				try {
					muiService.persistACL(e);
				} catch (Exception ex) {
					log.error("error fixing acl id " + e.getId(), ex);
				}
			});
		} catch (Exception e) {
			log.error("Error ensuring uuid for acl", e);
		}
	}

	private void updateApplicationProperties() {
		try {
			muiService.readApplicationProperties().forEach(e -> {
				e.getUuid();
				try {
					muiService.persistApplicationProperty(e);
				} catch (Exception ex) {
					log.error("error fixing acl id " + e.getIdApplicationProperties(), ex);
				}
			});
		} catch (Exception e) {
			log.error("Error ensuring uuid for application property", e);
		}
	}

	private void updateConfigurations() {
		try {
			muiService.readConfigurations().forEach(e -> {
				e.getUuid();
				try {
					muiService.persistConfigurations(e);
				} catch (Exception ex) {
					log.error("error fixing menu id " + e.getIdConfiguration(), ex);
				}
			});
		} catch (Exception e) {
			log.error("Error ensuring uuid for configuration", e);
		}
	}

	private void updateRoles() {
		try {
			muiService.readRoles().forEach(e -> {
				e.getUuid();
				try {
					muiService.persistRole(e);
				} catch (Exception ex) {
					log.error("error fixing role id " + e.getId(), ex);
				}
			});
		} catch (Exception e) {
			log.error("Error ensuring uuid for role", e);
		}
	}

	private void updateUsers() {
		try {
			muiService.readUsers().forEach(e -> {
				e.getUuid();
				try {
					muiService.persistUser(e);
				} catch (Exception ex) {
					log.error("error fixing user id " + e.getId(), ex);
				}
			});
		} catch (Exception e) {
			log.error("Error ensuring uuid for user", e);
		}
	}

	private void updateDbPromo() {
		dbpromoService.ensureAllNonEmptyUuid();
	}

}
