package com.axiante.mui.filter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.backing.CellSetCache;
import com.axiante.mui.backing.ConnectionCatalog;
import com.axiante.mui.backing.CookieRepository;
import com.axiante.mui.model.impl.ApplicationFilterCatalogProducerImpl;
import com.axiante.mui.model.impl.TableProducerImpl;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.impl.JpaAclDAO;
import com.axiante.mui.persistence.dao.impl.JpaApplicationPropertiesDAO;
import com.axiante.mui.persistence.dao.impl.JpaConfigurationsDAO;
import com.axiante.mui.persistence.dao.impl.JpaConnectionSetupDAO;
import com.axiante.mui.persistence.dao.impl.JpaMenuDAO;
import com.axiante.mui.persistence.dao.impl.JpaRolesDAO;
import com.axiante.mui.persistence.dao.impl.JpaUsersDAO;
import com.axiante.mui.persistence.service.impl.MuiServiceImpl;

public class ConfigurationFilterCatalogTestNoDb {
	//    @Inject
	//    private EntityManager em;
	@Inject
	private ConfigurationFilterCatalog configurationFilterCatalog;
	@Rule
	public WeldInitiator weld = WeldInitiator.from(
			EntityManagerProducer.class,
			EntityManagerFactoryProducer.class,
			ConfigurationFilterCatalogImpl.class,
			MuiServiceImpl.class,
			JpaRolesDAO.class,
			JpaAclDAO.class,
			JpaMenuDAO.class,
			JpaConfigurationsDAO.class,
			JpaApplicationPropertiesDAO.class,
			JpaConnectionSetupDAO.class,
			JpaUsersDAO.class,
			ApplicationProperties.class,
			TableProducerImpl.class,
			ConnectionCatalog.class,
			CellSetCache.class,
			CookieRepository.class,
			DimensionWatcher.class,
			ApplicationFilterCatalogProducerImpl.class


			).activate(RequestScoped.class)
	.inject(this)
	.build();

	@Test @Ignore
	public void testCatalogContainsData() {
		assertNotNull(configurationFilterCatalog.getCatalog());
		assertThat(configurationFilterCatalog.getCatalog().size(), CoreMatchers.not(CoreMatchers.equalTo(0)));
	}

}
