package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CfgAzioniEntityTest {
    private CfgAzioniEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new CfgAzioniEntity();
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsWithItself"})
    @Test
    public void testEquals_whenSameObject_shouldReturnTrue() {
        assertTrue(entity.equals(entity));
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals_whenDifferentObject_shouldReturnFalse() {
        ItemEntity item = new ItemEntity();
        assertFalse(entity.equals(item));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenIdNull_shouldReturnFalse() {
        entity.setId(null);
        assertFalse(entity.equals(new CfgAzioniEntity()));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenSameId_shouldReturnTrue() {
        entity.setId(1L);
        CfgAzioniEntity other = new CfgAzioniEntity();
        other.setId(1L);
        assertTrue(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenDifferentId_shouldReturnFalse() {
        entity.setId(1L);
        CfgAzioniEntity other = new CfgAzioniEntity();
        other.setId(2L);
        assertFalse(entity.equals(other));
    }
}