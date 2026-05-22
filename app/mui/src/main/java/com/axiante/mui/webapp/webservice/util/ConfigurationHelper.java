package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.webapp.webservice.util.configuration.CfgConfHeaderEntityHelper;
// import com.axiante.mui.webapp.webservice.util.configuration.CfgPianificazioneEntityHelper;
import com.axiante.mui.webapp.webservice.util.configuration.CfgTipoElementoEntityHelper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import lombok.NonNull;

@Dependent
public class ConfigurationHelper {

    @Inject
    private CfgConfHeaderEntityHelper headerEntityHelper;

    @Inject
    private CfgTipoElementoEntityHelper tipoElementoEntityHelper;

    public boolean updateHeader(@NonNull CfgConfHeaderEntity headerEntity, @NonNull String jsonEntity) throws Exception {
        final ObjectNode jsonNode = (ObjectNode) JsonUtils.getMapper().readTree(jsonEntity);
        final List<String> messages = headerEntityHelper.updateEntity(headerEntity, jsonNode);
        return messages.isEmpty();
    }

    public boolean updateTipoElemento(@NonNull CfgTipoElementoEntity tipoElementoEntity, @NonNull String jsonEntity) throws Exception {
        final ObjectNode jsonNode = (ObjectNode) JsonUtils.getMapper().readTree(jsonEntity);
        final List<String> messages = tipoElementoEntityHelper.updateEntity(tipoElementoEntity, jsonNode);
        return messages.isEmpty();
    }
}
