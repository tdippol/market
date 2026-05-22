package com.axiante.mui.business;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Converter
@Slf4j
public class MessaggiComponentsEnumConverter implements AttributeConverter<MessaggiComponentsEnum, String> {
    @Override
    public String convertToDatabaseColumn(MessaggiComponentsEnum attribute) {
        return attribute==null?null: attribute.getValue();
    }
    @Override
    public MessaggiComponentsEnum convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return null;
        }
        try {
            return MessaggiComponentsEnum.fromValue(dbData);
        } catch (IllegalArgumentException e) {
            log.error("Invalid value {} for MessaggiComponentsEnum", dbData);
            return null;
        }
    }

}
