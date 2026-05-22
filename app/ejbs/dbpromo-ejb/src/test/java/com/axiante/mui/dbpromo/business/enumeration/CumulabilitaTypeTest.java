package com.axiante.mui.dbpromo.business.enumeration;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CumulabilitaTypeTest {
    private CumulabilitaType cumulabile;
    private CumulabilitaType esclusiva;

    @Before
    public void setUp() throws Exception {
        cumulabile = CumulabilitaType.CUMULABILE;
        esclusiva = CumulabilitaType.ESCUSIVA;
    }

    @Test
    public void testToString() {
        assertEquals("CumulabilitaType{value='C'}", cumulabile.toString());
    }

    @Test
    public void getTitle_whenCumulabile_shouldReturnCumulabilita() {
        assertEquals("Cumulabilità", cumulabile.getTitle());
    }

    @Test
    public void getTitle_whenEsclusiva_shouldReturnEsclusivita() {
        assertEquals("Esclusività", esclusiva.getTitle());
    }
}