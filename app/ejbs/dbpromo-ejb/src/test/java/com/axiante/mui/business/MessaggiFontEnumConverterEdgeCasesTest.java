package com.axiante.mui.business;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessaggiFontEnumConverterEdgeCasesTest {
    private MessaggiFontEnumConverter converter = new MessaggiFontEnumConverter();

    @Test
    public void convertToDatabaseColumn_whenNullAttribute_shouldReturnNormale() {
        assertEquals(MessaggiFontEnum.NORMALE.getValue(), converter.convertToDatabaseColumn(null));
    }

    @Test
    public void convertToEntityAttribute_whenNullInput_shouldReturnNormale() {
        assertEquals(MessaggiFontEnum.NORMALE, converter.convertToEntityAttribute(null));
    }

    @Test
    public void convertToEntityAttribute_whenInvalidInput_shouldReturnNormale() {
        assertEquals(MessaggiFontEnum.NORMALE, converter.convertToEntityAttribute("INVALID"));
    }
}