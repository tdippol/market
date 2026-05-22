package com.axiante.mui.business;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MessaggiFontEnumConverterTest {
    private String dbData;
    private MessaggiFontEnum attribute;

    public MessaggiFontEnumConverterTest(String dbData, MessaggiFontEnum attribute) {
        this.dbData = dbData;
        this.attribute = attribute;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Normale", MessaggiFontEnum.NORMALE},
                {"Doppio", MessaggiFontEnum.DOPPIO},
                {"Triplo", MessaggiFontEnum.TRIPLO},
        });
    }

    @Test
    public void convertToDatabaseColumn() {
        final MessaggiFontEnumConverter converter = new MessaggiFontEnumConverter();
        final String result = converter.convertToDatabaseColumn(attribute);
        assertEquals(dbData, result);
    }

    @Test
    public void convertToEntityAttribute() {
        final MessaggiFontEnumConverter converter = new MessaggiFontEnumConverter();
        final MessaggiFontEnum result = converter.convertToEntityAttribute(dbData);
        assertEquals(attribute, result);
    }
}