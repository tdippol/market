package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MuiFlagDominioEntityTest {
    private MuiFlagDominioEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new MuiFlagDominioEntity();
        entity.setId(42L);
        entity.setValore("VALORE");
    }

    @Test
    public void getKey_shouldReturnId() {
        assertEquals("42", entity.getKey());
    }

    @Test
    public void getLabel() {
        assertEquals("VALORE", entity.getLabel());
    }
}