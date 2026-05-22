package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CfgAbilitaCheckEntityTest {
    private CfgAbilitaCheckEntity entity;

    @Before
    public void setUp() throws Exception {
        CfgAbilitaMeccCanaleEntity meccanicaCanale = new CfgAbilitaMeccCanaleEntity();
        meccanicaCanale.setId(24L);
        CfgCheckEntity check = new CfgCheckEntity();
        check.setId(23L);
        entity = new CfgAbilitaCheckEntity();
        entity.setId(42L);
        entity.setSeverita("HIGH");
        entity.setCheck(check);
        entity.setMeccanicaCanaleAbilitata(meccanicaCanale);
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsWithItself"})
    @Test
    public void testEquals_sameObject_shouldReturnTrue() {
        assertTrue(entity.equals(entity));
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals_differentObject_shouldReturnFalse() {
        assertFalse(entity.equals(new ItemEntity()));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenCheckNull_shouldReturnFalse() {
        entity.setCheck(null);
        assertFalse(entity.equals(new CfgAbilitaCheckEntity()));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenDifferentCheck_shouldReturnFalse() {
        CfgAbilitaCheckEntity other = new CfgAbilitaCheckEntity();
        other.setCheck(new CfgCheckEntity());
        assertFalse(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenMeccanicaCanaleNull_shouldReturnFalse() {
        entity.setMeccanicaCanaleAbilitata(null);
        assertFalse(entity.equals(new CfgAbilitaCheckEntity()));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenDifferentMeccanicaCanale_shouldReturnFalse() {
        CfgAbilitaCheckEntity other = new CfgAbilitaCheckEntity();
        other.setMeccanicaCanaleAbilitata(new CfgAbilitaMeccCanaleEntity());
        assertFalse(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals() {
        CfgAbilitaCheckEntity other = new CfgAbilitaCheckEntity();
        other.setCheck(entity.getCheck());
        other.setMeccanicaCanaleAbilitata(entity.getMeccanicaCanaleAbilitata());
        assertTrue(entity.equals(other));
    }

    @SuppressWarnings("ObviousNullCheck")
    @Test
    public void testHashCode() {
        assertNotNull(entity.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "CfgAbilitaCheckEntity{id=42, check=23, meccanicaCanaleAbilitata=24, severita='HIGH'}";
        assertEquals(expected, entity.toString());
        entity.setCheck(null);
        expected = "CfgAbilitaCheckEntity{id=42, check=null, meccanicaCanaleAbilitata=24, severita='HIGH'}";
        assertEquals(expected, entity.toString());
        entity.setMeccanicaCanaleAbilitata(null);
        expected = "CfgAbilitaCheckEntity{id=42, check=null, meccanicaCanaleAbilitata=null, severita='HIGH'}";
        assertEquals(expected, entity.toString());
    }
}