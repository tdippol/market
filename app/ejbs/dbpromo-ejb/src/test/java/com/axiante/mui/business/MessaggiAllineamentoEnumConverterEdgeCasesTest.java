package com.axiante.mui.business;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessaggiAllineamentoEnumConverterEdgeCasesTest {
    private MessaggiAllineamentoEnumConverter converter = new MessaggiAllineamentoEnumConverter();

    @Test
    public void convertToDatabaseColumn_whenNullAttribute_shouldReturnSinistra() {
        assertEquals(MessaggiAllineamentoEnum.SINISTRA.getValue(), converter.convertToDatabaseColumn(null));
    }

    @Test
    public void convertToEntityAttribute_whenNullInput_shouldReturnSinistra() {
        assertEquals(MessaggiAllineamentoEnum.SINISTRA, converter.convertToEntityAttribute(null));
    }

    @Test
    public void convertToEntityAttribute_whenInvalidInput_shouldReturnSinistra() {
        assertEquals(MessaggiAllineamentoEnum.SINISTRA, converter.convertToEntityAttribute("INVALID"));
    }
}
