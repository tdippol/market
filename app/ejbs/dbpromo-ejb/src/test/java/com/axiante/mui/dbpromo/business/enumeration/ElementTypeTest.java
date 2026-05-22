package com.axiante.mui.dbpromo.business.enumeration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class ElementTypeTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void fromDescription_givenNullDescription_shouldThrowException() {
        ex.expect(NullPointerException.class);
        ElementType.fromDescription(null);
    }

    @Test
    public void fromDescription_caseArticolo() {
        assertSame(ElementType.ARTICOLO, ElementType.fromDescription("articolo"));
    }

    @Test
    public void fromDescription_caseGrm() {
        assertSame(ElementType.GRM, ElementType.fromDescription("grm"));
    }

    @Test
    public void fromDescription_caseReparto() {
        assertSame(ElementType.REPARTO, ElementType.fromDescription("reparto"));
    }

    @Test
    public void fromDescription_caseTotale() {
        assertSame(ElementType.TOTALE, ElementType.fromDescription("totale"));
    }

    @Test
    public void fromDescription_caseAttributo() {
        assertSame(ElementType.ATTRIBUTO, ElementType.fromDescription("attributo"));
    }

    @Test
    public void fromDescription_caseUnknown_shouldReturnNull() {
        assertNull(ElementType.fromDescription("unknown"));
    }
}