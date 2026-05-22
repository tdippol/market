package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PianoMediaPromoDbpromoEntityTest {
    private PianoMediaPromoDbpromoEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new PianoMediaPromoDbpromoEntity();
        entity.setCodicePromozione("PR-001");
        entity.setDescrizioneEstesa("Descrizione estesa");
    }

    @Test
    public void getId_shouldReturnNull() {
        assertNull(entity.getId());
    }

    @Test
    public void getKey_shouldReturnCodicePromozione() {
        assertEquals("PR-001", entity.getKey());
    }

    @Test
    public void getLabel_shouldReturnDescrizioneEstesa() {
        assertEquals("Descrizione estesa", entity.getLabel());
    }
}