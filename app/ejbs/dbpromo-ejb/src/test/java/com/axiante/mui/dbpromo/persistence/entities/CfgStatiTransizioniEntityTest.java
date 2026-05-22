package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CfgStatiTransizioniEntityTest {
    private CfgStatiTransizioniEntity entity;

    @Test
    public void getTipo_whenFlagAutomatico_shuldReturnAutomatico() {
        entity = new CfgStatiTransizioniEntity();
        entity.setFlagAutomatico(true);
        assertEquals("Automatico", entity.getTipo());
    }

    @Test
    public void getTipo_whenNotFlagAutomatico_shuldReturnManuale() {
        entity = new CfgStatiTransizioniEntity();
        entity.setFlagAutomatico(false);
        assertEquals("Manuale", entity.getTipo());
    }
}