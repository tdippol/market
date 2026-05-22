package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MuiIniziativaEntityTest {
    private MuiIniziativaEntity entity;

    @Test
    public void getId_whenWrongCodiceIniziativa_thenReturnNull() {
        entity = new MuiIniziativaEntity();
        entity.setCodiceIniziativa("ABC");
        assertNull(entity.getId());
    }

    @Test
    public void getId() {
        entity = new MuiIniziativaEntity();
        entity.setCodiceIniziativa("42");
        assertEquals(42L, entity.getId().longValue());
    }

    @Test
    public void getKey() {
        entity = new MuiIniziativaEntity();
        entity.setCodiceIniziativa("42");
        assertEquals("42", entity.getKey());
    }

    @Test
    public void getLabel() {
        entity = new MuiIniziativaEntity();
        entity.setCodiceIniziativa("42");
        entity.setDescrizione("Descrizione");
        assertEquals("42 - Descrizione", entity.getLabel());
    }
}