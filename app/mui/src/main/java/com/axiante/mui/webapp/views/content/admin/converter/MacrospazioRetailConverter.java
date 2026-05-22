package com.axiante.mui.webapp.views.content.admin.converter;

import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiMacrospazioMediaService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named("macrospazioRetailConverter")
@Slf4j
public class MacrospazioRetailConverter implements Converter {

    @Inject
    private MuiMacrospazioMediaService service;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if ((value == null) || value.isEmpty() || "null".equals(value)) {
            return null;
        } else {
            try {
                MuiMacrospazioMediaEntity ret = service.findById(Long.valueOf(value));
                if ((ret == null) || (ret.getId() == null)) {
                    return null;
                }
                return ret;
            } catch (Exception e) {
                log.error("Conversion error ", e);
                throw new ConverterException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid object."));
            }
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            if (object instanceof MuiMacrospazioMediaEntity) {
                MuiMacrospazioMediaEntity grp = (MuiMacrospazioMediaEntity) object;
                if (grp.getId() == null) {
                    return "";
                }
                return String.valueOf(((MuiMacrospazioMediaEntity) object).getId());
            } else {
                throw new ConverterException("Value is not a valid instance of MuiMacrospazioMediaEntity.");
            }
        } else {
            return "";
        }
    }

}
