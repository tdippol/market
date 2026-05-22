package com.axiante.mui.webapp.views.content.dbpromo.converter;

import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiFontStileService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.inject.Instance;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "fontStileConverter")
@Slf4j
public class FontStileConverter implements Converter {
    @Inject
    private Instance<MuiFontStileService> muiFontStileService;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty() || "SELEZIONA".equalsIgnoreCase(value.trim())) {
            return null;
        }
        try {
            return muiFontStileService.get().findById(value);
        } catch (Exception ex) {
            log.error(String.format("Error converting value %s", value), ex);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent component, Object object) {
        return object != null
                ? ((MuiFontStileEntity) object).getId()
                : null;
    }
}
