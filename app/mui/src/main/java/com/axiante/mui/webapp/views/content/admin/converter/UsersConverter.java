package com.axiante.mui.webapp.views.content.admin.converter;

import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

@Named("usersConverter")
@Slf4j
public class UsersConverter implements Converter {

    @Inject
    private MuiService muiService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty() || "null".equalsIgnoreCase(value)) {
            return null;
        }
        try {
            final UsersEntity user = muiService.findUser(Integer.parseInt(value));
            return user == null || user.getId() == null ? null : user;
        } catch (Exception ex) {
            log.error("Conversion error", ex);
            throw new ConverterException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid object."));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof UsersEntity) {
            UsersEntity user = (UsersEntity) object;
            return user.getId() == null ? "" : String.valueOf(user.getId());
        } else {
            throw new ConverterException("Value is not a valid instance of GrmEntity.");
        }
    }
}
