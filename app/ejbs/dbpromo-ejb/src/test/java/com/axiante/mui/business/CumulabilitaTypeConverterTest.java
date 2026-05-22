package com.axiante.mui.business;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.business.enumeration.CumulabilitaType;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CumulabilitaTypeConverterTest {

    private String input;
    private CumulabilitaType expected;

    public CumulabilitaTypeConverterTest(String input, CumulabilitaType expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"C", CumulabilitaType.CUMULABILE},
                {"E", CumulabilitaType.ESCUSIVA},
                {"INVALID", null} // Negative test case
        });
    }

    @Test
    public void testConvertToEntityAttribute() {
        CumulabilitaTypeConverter converter = new CumulabilitaTypeConverter();
        CumulabilitaType result = converter.convertToEntityAttribute(input);
        assertEquals(expected, result);
    }
}