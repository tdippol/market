package com.axiante.mui.filter;

import com.axiante.mui.backing.*;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.model.impl.ApplicationFilterCatalogProducerImpl;
import com.axiante.mui.model.impl.TableProducerImpl;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.impl.*;
import com.axiante.mui.persistence.entity.ConfigurationTypes;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.persistence.service.impl.MuiServiceImpl;
import com.axiante.mui.utils.ApplicationConfiguration;
import org.hamcrest.CoreMatchers;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationFilterCatalogTest {

    @Mock
    ApplicationFilterCatalogProducer mockedProducer;
    @Mock
    MuiService mockedService;

    @Spy
    @InjectMocks
    private ConfigurationFilterCatalogImpl mokedConfigurationFilterCatalog;

    @Inject
    private ConfigurationFilterCatalog configurationFilterCatalog;

    /*
    
     */
    @Rule
    public WeldInitiator weld = WeldInitiator.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class,
            ConfigurationFilterCatalogImpl.class, MuiServiceImpl.class, JpaRolesDAO.class, JpaAclDAO.class,
            JpaMenuDAO.class, JpaMenuRoleDAO.class, JpaConfigurationsDAO.class, JpaApplicationPropertiesDAO.class,
            JpaConnectionSetupDAO.class, JpaCanaleDAOImpl.class, JpaGroupDAOImpl.class, JpaUsersDAO.class,
            JpaUploadDocumentDAOImpl.class, ApplicationConfiguration.class, ApplicationProperties.class,
            TableProducerImpl.class, ConnectionCatalog.class, CellSetCache.class, CookieRepository.class,
            ApplicationFilterCatalogProducerImpl.class, DimensionWatcher.class
            , KeepAliveScheduler.class

    ).activate(RequestScoped.class, SessionScoped.class).inject(this).build();

    @Test
    public void testCatalogContainsData() {
        assertNotNull(configurationFilterCatalog.getCatalog());
        assertTrue(configurationFilterCatalog.getCatalog().size() > 0);
    }

    @Test
    public void testCatalogUpdateReturnsSameData() {
        final List<ConfigurationElement> elements = configurationFilterCatalog.getCatalog();
        assertNotNull(elements);
        configurationFilterCatalog.update();
        assertThat(configurationFilterCatalog.getCatalog(), CoreMatchers.equalTo(elements));
    }

    @Test
    public void testCatalogUpdateReturnsEmptyWhenServiceThrowsException() throws Exception {
        when(mockedService.findConfigurations(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(ConfigurationTypes.class))).thenThrow(new Exception());
        assertThat(mokedConfigurationFilterCatalog.getCatalog(), empty());
    }

    @Test
    public void testCatalogUpdateReturnsEmptyWhenServiceReturnsNull() throws Exception {
        when(mockedService.findConfigurations(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(ConfigurationTypes.class))).thenReturn(null);
        assertThat(mokedConfigurationFilterCatalog.getCatalog(), empty());
    }

    @Test
    public void testFindByCodeAndAttributeReturnsDataWhenCorrectParams() {
        assertThat(configurationFilterCatalog.getCatalog(), not(empty()));
        assertNotNull(configurationFilterCatalog.findByCodeAndAttribute("promozione", "anno"));
    }

    @Test
    public void testFindByCodeAndAttributeReturnsNullWhenCodeIsWrongParams() {
        assertThat(configurationFilterCatalog.getCatalog(), not(empty()));
        assertNull(configurationFilterCatalog.findByCodeAndAttribute("not in list", "anno"));
    }

    @Test
    public void testFindByCodeAndAttributeReturnsNullWhenAttributeIsWrong() {
        assertThat(configurationFilterCatalog.getCatalog(), not(empty()));
        assertNull(configurationFilterCatalog.findByCodeAndAttribute("promozione", "not in list"));
    }

    @BeforeClass
    public static void createEmbeddedDerby() {
        DaoTestUtils.createDatabase();
    }

    @AfterClass
    public static void dropEmbeddedDerby() {
        DaoTestUtils.dropDatabase();
    }

}
