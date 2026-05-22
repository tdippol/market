package com.axiante.mui.business;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class MessaggiComponentsEnumConverterEdgeCasesTest {
    private MessaggiComponentsEnumConverter converter = new MessaggiComponentsEnumConverter();

    @Test
    public void convertToDatabaseColumn_whenNullAttribute_shouldReturnnull() {
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    public void convertToEntityAttribute_whenNullOrEmptyInput_shouldReturnNull() {
        assertNull(converter.convertToEntityAttribute(null));
        assertNull(converter.convertToEntityAttribute(" "));
    }

    @Test
    public void convertToEntityAttribute_whenInvalidInput_shouldReturnNull() {
        assertNull(converter.convertToEntityAttribute("INVALID"));
    }
}
