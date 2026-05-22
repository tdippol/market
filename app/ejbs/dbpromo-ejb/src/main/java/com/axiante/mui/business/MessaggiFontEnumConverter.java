package com.axiante.mui.business;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MessaggiFontEnumConverter implements AttributeConverter<MessaggiFontEnum, String> {
    @Override
    public String convertToDatabaseColumn(MessaggiFontEnum attribute) {
        return attribute==null? MessaggiFontEnum.NORMALE.getValue():attribute.getValue();
    }
    @Override
    public MessaggiFontEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return MessaggiFontEnum.NORMALE;
        }
        try {
            return MessaggiFontEnum.valueOf(dbData.toUpperCase());
        } catch (IllegalArgumentException e) {
            return MessaggiFontEnum.NORMALE;
        }
    }

}
