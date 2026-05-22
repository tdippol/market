package com.axiante.mui.webapp.views.content.admin.dto;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CfgLoghiMessaggiComponentDTOTest {

    @Test
    public void getDefaultComponent_shouldReturnComponentWithCorrectDefaults() {
        MessaggiComponentsEnum componentEnum = MessaggiComponentsEnum.BARCODE;
        CfgLoghiMessaggiComponentDTO dto = CfgLoghiMessaggiComponentDTO.getDefaultComponent(componentEnum);
        assertThat(dto.getName(), is(componentEnum));
        assertFalse(dto.getAttivo());
        assertFalse(dto.getUnicaInRiga());
        assertThat(dto.getComponentText(), is(nullValue()));
    }

    @Test
    public void getTesto_whenComponentTextIsNull_shouldReturnEnumDefaultValue() {
        MessaggiComponentsEnum componentEnum = MessaggiComponentsEnum.BARCODE;
        CfgLoghiMessaggiComponentDTO dto = CfgLoghiMessaggiComponentDTO.getDefaultComponent(componentEnum);
        assertThat(dto.getTesto(), is(componentEnum.getValue()));
    }

    @Test
    public void getTesto_whenComponentTextIsSet_shouldReturnComponentText() {
        CfgLoghiMessaggiComponentDTO dto = new CfgLoghiMessaggiComponentDTO(MessaggiComponentsEnum.BARCODE, false, false, "Custom Text");
        assertThat(dto.getTesto(), is("Custom Text"));
    }

    @Test
    public void setTesto_withValue_shouldUpdateComponentText() {
        CfgLoghiMessaggiComponentDTO dto = CfgLoghiMessaggiComponentDTO.getDefaultComponent(MessaggiComponentsEnum.BARCODE);
        dto.setTesto("New Custom Text");
        assertThat(dto.getComponentText(), is("New Custom Text"));
    }

    @Test
    public void setTesto_withNull_shouldSetComponentTextToNull() {
        CfgLoghiMessaggiComponentDTO dto = new CfgLoghiMessaggiComponentDTO(MessaggiComponentsEnum.BARCODE, false, false, "Initial Text");
        dto.setTesto(null);
        assertThat(dto.getComponentText(), is(nullValue()));
    }

    @Test
    public void setTesto_withEmptyString_shouldSetComponentTextToNull() {
        CfgLoghiMessaggiComponentDTO dto = new CfgLoghiMessaggiComponentDTO(MessaggiComponentsEnum.BARCODE, false, false, "Initial Text");
        dto.setTesto("");
        assertThat(dto.getComponentText(), is(nullValue()));
    }

    @Test
    public void constructorAndSetters_shouldSetAllFields() {
        CfgLoghiMessaggiComponentDTO dto = new CfgLoghiMessaggiComponentDTO(MessaggiComponentsEnum.BARCODE, true, true, "Some Text");
        assertThat(dto.getName(), is(MessaggiComponentsEnum.BARCODE));
        assertTrue(dto.getAttivo());
        assertTrue(dto.getUnicaInRiga());
        assertThat(dto.getComponentText(), is("Some Text"));
        dto.setAttivo(false);
        dto.setUnicaInRiga(false);
        assertFalse(dto.getAttivo());
        assertFalse(dto.getUnicaInRiga());
    }
}