package com.axiante.mui.webapp.views.content.dbpromo.converter;

import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiBottoneService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.inject.Instance;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named("muiBottoneConverter")
@Slf4j
public class MuiBottoneConverter implements Converter {
    @Inject
    private Instance<MuiBottoneService> muiBottoneServiceInstance;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty() || "SELEZIONA".equalsIgnoreCase(value.trim())) {
            return null;
        }
        try {
            Long id = Long.parseLong(value);
            return muiBottoneServiceInstance.get().findById(id);
        } catch (Exception ex) {
            log.error(String.format("Error converting value %s", value), ex);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        final Long id = ((MuiBottoneEntity) object).getId();
        if (id == null) {
            log.error(String.format("Missing id on object %s", ((MuiBottoneEntity) object).getDescrizione()));
            return null;
        }
        return String.valueOf(id);
    }
}
