package com.axiante.mui.webapp.views.content.admin.converter;

import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.service.MuiService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

@Named("compratoriConverter")
@Slf4j
public class CompratoriConverter implements Converter {

    @Inject
    private MuiService muiService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty() || "null".equalsIgnoreCase(value)) {
            return null;
        }
        try {
            final CompratoreEntity compratore = muiService.findCompratoreById(Integer.parseInt(value));
            return compratore == null || compratore.getId() == null ? null : compratore;
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
        if (object instanceof CompratoreEntity) {
            CompratoreEntity compratore = (CompratoreEntity) object;
            return compratore.getId() == null ? "" : String.valueOf(compratore.getId());
        } else {
            throw new ConverterException("Value is not a valid instance of GrmEntity.");
        }
    }
}
