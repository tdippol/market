package com.axiante.mui.webapp.views.content.admin.converter;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

@Named("tipoAccessoDropdownConverter")
@Slf4j
public class TipoAccessoDropdownConverter extends EnumConverter {

	public TipoAccessoDropdownConverter() {
		super(PianificazioneSecurityEnum.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if ( value == null ) {
			return null;
		}
		return Boolean.parseBoolean(value) ? PianificazioneSecurityEnum.WRITE : PianificazioneSecurityEnum.READ;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if ( value == null ) {
			return "NONE";
		}
		return Boolean.valueOf(((PianificazioneSecurityEnum) value).isEditable()).toString();
	}
}
