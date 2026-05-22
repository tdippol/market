package com.axiante.mui.business;

import com.axiante.mui.dbpromo.business.enumeration.ModalitaMarchioPrivato;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ModalitaMarchioPrivatoConverter implements AttributeConverter<ModalitaMarchioPrivato, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ModalitaMarchioPrivato attribute) {
        if ( attribute == null ) return ModalitaMarchioPrivato.NO.getValue();
        return attribute.getValue();
    }

    @Override
    public ModalitaMarchioPrivato convertToEntityAttribute(Integer dbData) {
        if ( dbData == null ) {
            return ModalitaMarchioPrivato.NO;
        }
        return ModalitaMarchioPrivato.fromValue(dbData);
    }

}
