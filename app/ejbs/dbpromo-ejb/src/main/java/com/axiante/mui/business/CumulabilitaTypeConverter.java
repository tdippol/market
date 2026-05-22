package com.axiante.mui.business;

import com.axiante.mui.dbpromo.business.enumeration.CumulabilitaType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Converter
@Slf4j
public class CumulabilitaTypeConverter implements AttributeConverter<CumulabilitaType, String> {
    @Override
    public String convertToDatabaseColumn(CumulabilitaType attribute) {
        if (log.isDebugEnabled()) {
            log.debug("convertToDatabaseColumn: attribute = {}", attribute);
        }
        if (attribute == null) {
            return null;
        }

        return attribute.getValue();
    }

    @Override
    public CumulabilitaType convertToEntityAttribute(String dbData) {
        if (log.isDebugEnabled()) {
            log.debug("convertToEntityAttribute: dbData = {}", dbData);
        }
        if (dbData == null) {
            return null;
        }
        return CumulabilitaType.fromValue(dbData);
    }
}
