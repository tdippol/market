package com.axiante.mui.webapp.views.content.admin.converter;

import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.service.MuiService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

@Named("groupsConverter")
@Slf4j
public class GroupsConverter implements Converter {

	@Inject
	private MuiService muiService;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if ((value == null) || value.isEmpty() || "null".equals(value)) {
			return null;
		} else {
			try {
				Integer id = Integer.parseInt(value);
				GroupEntity ret = muiService.findGroupById(id);
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
			if (object instanceof GroupEntity) {
				GroupEntity grp = (GroupEntity) object;
				if (grp.getId() == null) {
					return "";
				}
				return String.valueOf(((GroupEntity) object).getId());
			} else {
				throw new ConverterException("Value is not a valid instance of GroupEntity.");
			}
		} else {
			return "";
		}
	}

}
