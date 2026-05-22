package com.axiante.mui.webapp.views.content.admin.converter;

import com.axiante.mui.persistence.entity.RepartoEntity;
import com.axiante.mui.persistence.service.MuiService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

@Named("repartiConverter")
@Slf4j
public class RepartiConverter implements Converter {

    @Inject
    private MuiService muiService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty() || "null".equalsIgnoreCase(value)) {
            return null;
        }
        try {
            final RepartoEntity reparto = muiService.findRepartoById(Integer.parseInt(value));
            return reparto == null || reparto.getId() == null ? null : reparto;
        } catch (Exception ex) {
            log.error("Conversion error", ex);
            throw new ConverterException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid object."));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if (object == null) {
            return "";
        }
        if (object instanceof RepartoEntity) {
            RepartoEntity reparto = (RepartoEntity) object;
            return reparto.getId() == null ? "" : String.valueOf(reparto.getId());
        } else {
            throw new ConverterException("Value is not a valid instance of RepartoEntity.");
        }
    }
}
