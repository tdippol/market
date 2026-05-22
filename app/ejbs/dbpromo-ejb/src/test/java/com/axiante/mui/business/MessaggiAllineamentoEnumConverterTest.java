package com.axiante.mui.business;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MessaggiAllineamentoEnumConverterTest {
    private String dbData;
    private MessaggiAllineamentoEnum attribute;

    public MessaggiAllineamentoEnumConverterTest(String dbData, MessaggiAllineamentoEnum attribute) {
        this.dbData = dbData;
        this.attribute = attribute;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Sinistra", MessaggiAllineamentoEnum.SINISTRA},
                {"Centro", MessaggiAllineamentoEnum.CENTRO},
                {"Destra", MessaggiAllineamentoEnum.DESTRA},
        });
    }

    @Test
    public void convertToDatabaseColumn() {
        final MessaggiAllineamentoEnumConverter converter = new MessaggiAllineamentoEnumConverter();
        final String result = converter.convertToDatabaseColumn(attribute);
        assertEquals(dbData, result);
    }

    @Test
    public void convertToEntityAttribute() {
        final MessaggiAllineamentoEnumConverter converter = new MessaggiAllineamentoEnumConverter();
        final MessaggiAllineamentoEnum result = converter.convertToEntityAttribute(dbData);
        assertEquals(attribute, result);
    }
}