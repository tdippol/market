package com.axiante.mui.filter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;
import com.axiante.mui.persistence.service.MuiService;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationFilterCatalogTestWithMocks {

    @Mock ApplicationFilterCatalogProducer producer;
    @Mock MuiService muiService;


    @Spy
    @InjectMocks
    ConfigurationFilterCatalogImpl configurationFilterCatalog;

    @Rule 
    public ExpectedException exception = ExpectedException.none();
    @Test public void testInitializationHandlesNullConfiguration() throws Exception {
        when(muiService.findConfigurations((String)ArgumentMatchers.notNull(),(ConfigurationTypes)ArgumentMatchers.notNull())).thenReturn(null);
        configurationFilterCatalog.update();
    }
    @Test public void testUpdateHandlesInternalException() throws Exception {
        final ConfigurationEntity entity = mock(ConfigurationEntity.class);
        when(muiService.findConfigurations((String)ArgumentMatchers.notNull(),(ConfigurationTypes)ArgumentMatchers.notNull())).thenReturn(entity);
        configurationFilterCatalog.update();
    }
    @Test public void testUpdateHandlesServiceException() throws Exception {
        when(muiService.findConfigurations((String)ArgumentMatchers.notNull(),(ConfigurationTypes)ArgumentMatchers.notNull())).thenThrow(Exception.class);
        configurationFilterCatalog.update();
    }
    @Test public void testGetCatalogIsEmptyWhenInternalError() throws Exception {
        final ConfigurationEntity entity = mock(ConfigurationEntity.class);
        when(muiService.findConfigurations((String)ArgumentMatchers.notNull(),(ConfigurationTypes)ArgumentMatchers.notNull())).thenReturn(entity);
        assertThat(configurationFilterCatalog.getCatalog().size(), is(0));
        when(muiService.findConfigurations((String)ArgumentMatchers.notNull(),(ConfigurationTypes)ArgumentMatchers.notNull())).thenReturn(null);
        assertThat(configurationFilterCatalog.getCatalog().size(), is(0));
    }

}
