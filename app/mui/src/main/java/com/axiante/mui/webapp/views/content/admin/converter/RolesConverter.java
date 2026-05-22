package com.axiante.mui.webapp.views.content.admin.converter;

import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.service.MuiService;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

@Named(value = "rolesConverter")
@Slf4j
public class RolesConverter implements Converter {

	@Inject
	private Instance<MuiService> muiService;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if ((value != null) && (value.trim().length() > 0)) {
			try {
				try {
					RolesEntity role = muiService.get().findRole(Integer.parseInt(value));
					if (role != null) {
						return role;
					}
				} catch (Exception e) {
					log.error("Error converting value " + value + " to role ", e);
				}
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid object."));
			}
			return null;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((RolesEntity) object).getId());
		} else {
			return null;
		}
	}

}
