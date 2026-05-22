package com.axiante.mui.utils;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.mui.backing.ApplicationProperties;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationConfigurationTest {
    @Mock ApplicationProperties properties;
    @Spy @InjectMocks ApplicationConfiguration applicationConfiguration; 

    @Test public void testGetConnectionTimeout() {
        applicationConfiguration.getConnectionTimeout();
        verify(properties).getProperty(ApplicationProperties.CONNECTION_TIMEOUT,
                ApplicationProperties.DEFAULT_CONNECTION_TIMEOUT);
    }

    @Test public void testGetSocketTimeout() {
        applicationConfiguration.getSocketTimeout();
        verify(properties).getProperty(ApplicationProperties.SOCKET_TIMEOUT,
                ApplicationProperties.DEFAULT_SOCKET_TIMEOUT);
    }

    @Test public void  getConnectionRequestTimeout() {
        applicationConfiguration.getConnectionRequestTimeout();
        verify(properties).getProperty(ApplicationProperties.CONNECTION_MANAGER_TIMEOUT,
                ApplicationProperties.DEFAULT_CONNECTION_MANAGER_TIMEOUT);
    }

    @Test public void testGetMuiTokenDuration() {
        applicationConfiguration.getMuiTokenDuration();
        verify(properties).getProperty(ApplicationProperties.TOKEN_SPAWN, ApplicationProperties.DEFAULT_TOKEN_SPAWN);
    }

    @Test public void testGetGlobalMaxCell() {
        applicationConfiguration.getGlobalMaxCell();
        verify(properties).getProperty(ApplicationProperties.MAX_CELLS, ApplicationProperties.DEFAULT_MAX_CELLS);
    }

    @Test public void testGetOverallOperationTimeout() {
        applicationConfiguration.getOverallOperationTimeout();
        verify(properties).getProperty(ApplicationProperties.TM1_OPERATION_TIMEOUT,
                ApplicationProperties.DEFAULT_TM1_OPERATION_TIMEOUT);
    }

    @Test public void testGetDefaultGridHeight() {
        applicationConfiguration.getDefaultGridHeight();
        verify(properties).getProperty(ApplicationProperties.GRID_HEIGHT, ApplicationProperties.DEFAULT_GRID_HEIGHT);
    }
}
