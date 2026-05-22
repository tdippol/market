package com.axiante.mui.dbpromo.business.enumeration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertSame;

public class MessaggiAllineamentoEnumTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void fromValue_whenSinistra() {
        assertSame(MessaggiAllineamentoEnum.SINISTRA, MessaggiAllineamentoEnum.fromValue("Sinistra"));
    }

    @Test
    public void fromValue_whenCentro() {
        assertSame(MessaggiAllineamentoEnum.CENTRO, MessaggiAllineamentoEnum.fromValue("Centro"));
    }

    @Test
    public void fromValue_whenDestra() {
        assertSame(MessaggiAllineamentoEnum.DESTRA, MessaggiAllineamentoEnum.fromValue("Destra"));
    }

    @Test
    public void fromValue_givenUnknownValue_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        MessaggiAllineamentoEnum.fromValue("unknown");
    }
}