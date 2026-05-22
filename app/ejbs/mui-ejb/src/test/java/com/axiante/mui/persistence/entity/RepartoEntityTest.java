package com.axiante.mui.persistence.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RepartoEntityTest {
    private RepartoEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new RepartoEntity();
        entity.setCodiceReparto("R01");
        entity.setDescrizione("REPARTO FOO");
    }

    @Test
    public void getLabel_shouldReturnFormattedCodeAndDescription() {
        assertEquals("R01 - REPARTO FOO", entity.getLabel());
    }
}