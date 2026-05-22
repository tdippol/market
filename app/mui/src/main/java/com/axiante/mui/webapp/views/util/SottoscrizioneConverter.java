package com.axiante.mui.webapp.views.util;

import com.axiante.mui.dbpromo.persistence.entities.SottoscrizioneEntity;
import com.axiante.mui.dbpromo.persistence.service.SottoscrizioneService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@Named("sottoscrizioneConverter")
@Slf4j
public class SottoscrizioneConverter implements Converter {
    @Inject
    private Instance<SottoscrizioneService> sottoscrizioneService;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.isEmpty() || "null".equalsIgnoreCase(value.trim())) {
            return null;
        }
        try {
            Long id = Long.parseLong(value);
            final SottoscrizioneEntity entity = sottoscrizioneService.get().findById(id);
            if (entity == null || entity.getId() == null) {
                return null;
            }
            return entity;
        } catch (Exception ex) {
            log.error(String.format("Error during conversion of Sottoscrizione with value %s", value), ex);
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Conversion Error", "Not a valid object."));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object == null) {
            return "";
        }
        if (object instanceof SottoscrizioneEntity) {
            SottoscrizioneEntity entity = (SottoscrizioneEntity) object;
            if (entity.getId() == null) {
                return "";
            }
            return String.valueOf(entity.getId());
        } else {
            throw new ConverterException("Object is not a valid instance of SottoscrizioneEntity.");
        }
    }
}
