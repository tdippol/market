package com.axiante.mui.dbpromo.business.enumeration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertSame;

public class MessaggiFontEnumTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void fromValue_whenPiccolo() {
        assertSame(MessaggiFontEnum.PICCOLO, MessaggiFontEnum.fromValue("Piccolo"));
    }

    @Test
    public void fromValue_whenNormale() {
        assertSame(MessaggiFontEnum.NORMALE, MessaggiFontEnum.fromValue("Normale"));
    }

    @Test
    public void fromValue_whenDoppio() {
        assertSame(MessaggiFontEnum.DOPPIO, MessaggiFontEnum.fromValue("Doppio"));
    }

    @Test
    public void fromValue_whenTriplo() {
        assertSame(MessaggiFontEnum.TRIPLO, MessaggiFontEnum.fromValue("Triplo"));
    }

    @Test
    public void fromValue_givenUnknownValue_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        MessaggiFontEnum.fromValue("unknown");
    }
}