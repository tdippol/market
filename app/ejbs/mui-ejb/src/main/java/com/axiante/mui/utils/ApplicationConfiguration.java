package com.axiante.mui.utils;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.axiante.mui.backing.ApplicationProperties;

@ApplicationScoped
public class ApplicationConfiguration implements Serializable{

    @Inject
    ApplicationProperties properties;

    public Double getConnectionTimeout() {
        return this.properties.getProperty(ApplicationProperties.CONNECTION_TIMEOUT,
                ApplicationProperties.DEFAULT_CONNECTION_TIMEOUT);
    }

    public Integer getSocketTimeout() {
        return this.properties.getProperty(ApplicationProperties.SOCKET_TIMEOUT,
                ApplicationProperties.DEFAULT_SOCKET_TIMEOUT);
    }

    public Integer getConnectionRequestTimeout() {
        return this.properties.getProperty(ApplicationProperties.CONNECTION_MANAGER_TIMEOUT,
                ApplicationProperties.DEFAULT_CONNECTION_MANAGER_TIMEOUT);
    }

    public Integer getMuiTokenDuration() {
        return this.properties.getProperty(ApplicationProperties.TOKEN_SPAWN, ApplicationProperties.DEFAULT_TOKEN_SPAWN);
    }

    public Integer getGlobalMaxCell() {
        return this.properties.getProperty(ApplicationProperties.MAX_CELLS, ApplicationProperties.DEFAULT_MAX_CELLS);
    }

    public Integer getOverallOperationTimeout() {
        return this.properties.getProperty(ApplicationProperties.TM1_OPERATION_TIMEOUT,
                ApplicationProperties.DEFAULT_TM1_OPERATION_TIMEOUT);
    }

    public Integer getDefaultGridHeight() {
        return this.properties.getProperty(ApplicationProperties.GRID_HEIGHT, ApplicationProperties.DEFAULT_GRID_HEIGHT);
    }

    public Boolean getAdminMode() {
        return this.properties.getProperty(ApplicationProperties.ADMIN_MODE, ApplicationProperties.DEFAULT_ADMIN_MODE);
    }
}
