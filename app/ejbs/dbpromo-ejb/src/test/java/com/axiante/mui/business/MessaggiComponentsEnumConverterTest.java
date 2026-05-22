package com.axiante.mui.business;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MessaggiComponentsEnumConverterTest {
    private String dbData;
    private MessaggiComponentsEnum attribute;

    public MessaggiComponentsEnumConverterTest(String dbData, MessaggiComponentsEnum attribute) {
        this.dbData = dbData;
        this.attribute = attribute;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Id Messaggio", MessaggiComponentsEnum.ID_MESSAGGIO},
                {"Taglio carta", MessaggiComponentsEnum.TAGLIO_CARTA},
                {"Barcode", MessaggiComponentsEnum.BARCODE},
                {"Font", MessaggiComponentsEnum.FONT},
                {"Allineamento", MessaggiComponentsEnum.ALLINEAMENTO},
                {"Bold", MessaggiComponentsEnum.BOLD},
                {"Testo", MessaggiComponentsEnum.TESTO},
                {"Logo", MessaggiComponentsEnum.LOGO},
                {"Bottone", MessaggiComponentsEnum.BOTTONE},
                {"Codice", MessaggiComponentsEnum.CODICE},
                {"Regolamento", MessaggiComponentsEnum.REGOLAMENTO},
                {"Seq Stampa", MessaggiComponentsEnum.SEQ_STAMPA},
                {"Barra", MessaggiComponentsEnum.BARRA},
                {"Font Stile", MessaggiComponentsEnum.FONT_STILE},
                {"Variabile", MessaggiComponentsEnum.VARIABILE},
                {"Barcode", MessaggiComponentsEnum.BARCODE},
                {"Font Entity", MessaggiComponentsEnum.FONT_ENTITY}
        });
    }

    @Test
    public void convertToDatabaseColumn() {
        final MessaggiComponentsEnumConverter converter = new MessaggiComponentsEnumConverter();
        final String result = converter.convertToDatabaseColumn(attribute);
        assertEquals(dbData, result);
    }

    @Test
    public void convertToEntityAttribute() {
        final MessaggiComponentsEnumConverter converter = new MessaggiComponentsEnumConverter();
        final MessaggiComponentsEnum result = converter.convertToEntityAttribute(dbData);
        assertEquals(attribute, result);
    }
}