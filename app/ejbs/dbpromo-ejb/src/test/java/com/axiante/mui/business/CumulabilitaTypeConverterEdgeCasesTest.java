package com.axiante.mui.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.axiante.mui.dbpromo.business.enumeration.CumulabilitaType;
import org.junit.Test;

public class CumulabilitaTypeConverterEdgeCasesTest {

    @Test
    public void testConvertToEntityAttribute_NullInput() {
        CumulabilitaTypeConverter converter = new CumulabilitaTypeConverter();
        CumulabilitaType result = converter.convertToEntityAttribute(null);
        assertNull(result);
    }

    @Test
    public void testConvertToDatabaseColumn_NullInput() {
        CumulabilitaTypeConverter converter = new CumulabilitaTypeConverter();
        String result = converter.convertToDatabaseColumn(null);
        assertNull(result);
    }

    @Test
    public void testConvertToDatabaseColumn_ValidInput() {
        CumulabilitaTypeConverter converter = new CumulabilitaTypeConverter();
        String result = converter.convertToDatabaseColumn(CumulabilitaType.CUMULABILE);
        assertEquals("C", result);
        result = converter.convertToDatabaseColumn(CumulabilitaType.ESCUSIVA);
        assertEquals("E", result);
    }
}