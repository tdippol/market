package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarchioPrivatoEntityTest {
    private MarchioPrivatoEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new MarchioPrivatoEntity();
        entity.setCodice("CODICE");
        entity.setDescrizione("Descrizione");
    }

    @Test
    public void getKey_shouldReturnCodice() {
        assertEquals("CODICE", entity.getKey());
    }

    @Test
    public void getLabel_shouldReturnDescrizione() {
        assertEquals("Descrizione", entity.getLabel());
    }
}