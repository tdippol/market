package com.axiante.mui.webapp.views.content.admin.converter;

import com.axiante.mui.persistence.entity.CanaleEntity;
import com.axiante.mui.persistence.service.MuiService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

@Named("canaliConverter")
@Slf4j
public class CanaliConverter implements Converter {

	@Inject
	private MuiService muiService;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if ((value != null) && (value.trim().length() > 0)) {
			try {
				try {
					for (CanaleEntity canale : muiService.readCanali()) {
						if (canale.getId() == Integer.parseInt(value)) {
							return canale;
						}
					}
				} catch (Exception e) {
					log.error("Error converting " + value + " to CanaleEntity", e);
				}
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid object."));
			}
			return null;
		} else

		{
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((CanaleEntity) object).getId());
		} else {
			return null;
		}
	}

}
