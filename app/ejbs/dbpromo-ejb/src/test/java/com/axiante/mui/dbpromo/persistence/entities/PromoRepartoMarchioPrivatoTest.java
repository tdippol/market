package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PromoRepartoMarchioPrivatoTest {
    private PromoRepartoMarchioPrivato entity;

    @Before
    public void setUp() throws Exception {
        entity = new PromoRepartoMarchioPrivato();
    }

    @Test
    public void getUuid_whenAlreadySetted_thenReturnSameValue() {
        entity.setUuid("UUID");
        assertEquals("UUID", entity.getUuid());
    }

    @Test
    public void getUuid() {
        assertNotNull(entity.getUuid());
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals_whenDifferenteObject_thenReturnFalse() {
        ItemEntity other = new ItemEntity();
        assertFalse(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenDifferentUuid_shouldReturnFalse() {
        PromoRepartoMarchioPrivato other = new PromoRepartoMarchioPrivato();
        other.setUuid("OTHER");
        entity.setUuid("UUID");
        assertFalse(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenSameUuid_shouldReturnTrue() {
        PromoRepartoMarchioPrivato other = new PromoRepartoMarchioPrivato();
        other.setUuid("UUID");
        entity.setUuid("UUID");
        assertTrue(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenOtherNull_shouldReturnFalse() {
        entity.setUuid("UUID");
        assertFalse(entity.equals(null));
    }
}