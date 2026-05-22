package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MuiPromoDbPromoEntityTest {
    private MuiPromoDbPromoEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new MuiPromoDbPromoEntity();
    }

    @Test
    public void getStato_withCodice() {
        entity.setCodiceStato("42");
        assertEquals("42", entity.getStato());
    }

    @Test
    public void getStato_withDescrizione() {
        entity.setDescrizioneStato("Descrizione");
        assertEquals("Descrizione", entity.getStato());
    }

    @Test
    public void getStato_withBothCodiceAndDescrizione() {
        entity.setCodiceStato("42");
        entity.setDescrizioneStato("Descrizione");
        assertEquals("42-Descrizione", entity.getStato());
    }
}