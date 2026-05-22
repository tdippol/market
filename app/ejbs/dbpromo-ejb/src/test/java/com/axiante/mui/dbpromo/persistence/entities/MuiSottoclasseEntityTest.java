package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class MuiSottoclasseEntityTest {
    private MuiSottoclasseEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new MuiSottoclasseEntity();
        entity.setDescrizione("DESCRIZIONE");
        entity.setSottoClasse("SOTTOCLASSE");
    }

    @Test
    public void getKey_shouldReturnSottoclasse() {
        assertEquals("SOTTOCLASSE", entity.getKey());
    }

    @Test
    public void getLabel_shouldRetunDescrizione() {
        assertEquals("DESCRIZIONE", entity.getLabel());
    }

    @Test
    public void getId_shouldReturnNull() {
        assertNull(entity.getId());
    }
}