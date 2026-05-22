package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
public class PromozioneMarchioPrivatoEntityTest {
    private PromozioneMarchioPrivatoEntity entity;

    @Before
    public void setUp() throws Exception {
        MarchioPrivatoEntity marchioPrivato = new MarchioPrivatoEntity();
        marchioPrivato.setCodice("CODICE");
        marchioPrivato.setDescrizione("Descrizione");
        entity = new PromozioneMarchioPrivatoEntity();
        entity.setId(42L);
        entity.setMarchioPrivato(marchioPrivato);
    }

    @Test
    public void getKey_shouldReturnCodiceMarchioPrivato() {
        assertEquals("CODICE", entity.getKey());
    }

    @Test
    public void getLabel_shouldReturnDescrizioneMarchioPrivato() {
        assertEquals("Descrizione", entity.getLabel());
    }

    @Test
    public void testEquals_whenNullOther_shouldReturnFalse() {
        assertFalse(entity.equals(null));
    }

    @Test
    public void testEquals_whenOtherDifferentInstance_shouldReturnFalse() {
        MarchioPrivatoEntity marchioPrivato = new MarchioPrivatoEntity();
        assertFalse(entity.equals(marchioPrivato));
    }

    @Test
    public void testEquals_whenDifferentId_shouldReturnFalse() {
        PromozioneMarchioPrivatoEntity other = new PromozioneMarchioPrivatoEntity();
        other.setId(666L);
        assertFalse(entity.equals(other));
    }

    @Test
    public void testEquals_whenSameId_shouldReturnTrue() {
        PromozioneMarchioPrivatoEntity other = new PromozioneMarchioPrivatoEntity();
        other.setId(42L);
        assertTrue(entity.equals(other));
    }
}