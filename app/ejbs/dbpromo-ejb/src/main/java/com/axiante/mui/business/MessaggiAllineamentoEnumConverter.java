package com.axiante.mui.business;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MessaggiAllineamentoEnumConverter implements AttributeConverter<MessaggiAllineamentoEnum, String> {
    @Override
    public String convertToDatabaseColumn(MessaggiAllineamentoEnum attribute) {
        return attribute==null? MessaggiAllineamentoEnum.SINISTRA.getValue():attribute.getValue();
    }
    @Override
    public MessaggiAllineamentoEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return MessaggiAllineamentoEnum.SINISTRA;
        }
        try {
            return MessaggiAllineamentoEnum.valueOf(dbData.toUpperCase());
        } catch (IllegalArgumentException e) {
            return MessaggiAllineamentoEnum.SINISTRA;
        }
    }

}
