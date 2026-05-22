package com.axiante.mui.business;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.axiante.mui.common.PianificazioneSecurityEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
public class SecurityEnumConverter  implements AttributeConverter<PianificazioneSecurityEnum, String>{
	@Override
	public String convertToDatabaseColumn(PianificazioneSecurityEnum value) {
		if ( value == null ) return null;
		return value.getSecurity();
	}
	@Override
	public PianificazioneSecurityEnum convertToEntityAttribute(String dbData) {
		if ( dbData == null ) return null;
		PianificazioneSecurityEnum s = PianificazioneSecurityEnum.fromString(dbData);
		if ( s == null ) {
			log.warn(String.format("Valore non valido per sicurezza in database. Trovato %s aspettato R/W", dbData));
			s = PianificazioneSecurityEnum.READ;
		}
		return s;

	}

}
