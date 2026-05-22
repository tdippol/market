package com.axiante.mui.webapp.views.content.admin.converter;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

@Named("tipoAccessoConverter")
@Slf4j
public class TipoAccessoConverter extends EnumConverter {

	public TipoAccessoConverter() {
		super(PianificazioneSecurityEnum.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return Boolean.parseBoolean(value) ? PianificazioneSecurityEnum.WRITE : PianificazioneSecurityEnum.READ;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return Boolean.valueOf(((PianificazioneSecurityEnum) value).isEditable()).toString();
	}
}
