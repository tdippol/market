package com.axiante.mui.webapp.webservice.util.pianificazione.util;

import static org.junit.Assert.*;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import org.junit.Test;

public class PianificazioneSecurityEnumTest {

    @Test(expected = NullPointerException.class)
    public void fromString_givenNullString_shouldThrowException() {
        PianificazioneSecurityEnum.fromString(null);
    }

    @Test
    public void fromString_givenValidString_shouldReturnEnum() {
        assertEquals(PianificazioneSecurityEnum.READ, PianificazioneSecurityEnum.fromString("R"));
        assertEquals(PianificazioneSecurityEnum.READ, PianificazioneSecurityEnum.fromString("r"));
        assertEquals(PianificazioneSecurityEnum.WRITE, PianificazioneSecurityEnum.fromString("W"));
        assertEquals(PianificazioneSecurityEnum.WRITE, PianificazioneSecurityEnum.fromString("w"));
    }

    @Test
    public void fromString_givenInvalidString_shouldReturnNull() {
        assertNull(PianificazioneSecurityEnum.fromString("WRONG"));
    }
}