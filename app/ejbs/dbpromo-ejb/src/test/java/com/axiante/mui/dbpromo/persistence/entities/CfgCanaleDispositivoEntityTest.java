package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CfgCanaleDispositivoEntityTest {
    private CfgCanaleDispositivoEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new CfgCanaleDispositivoEntity();
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsWithItself"})
    @Test
    public void testEquals_whenSameObject_shouldReturnTrue() {
        assertTrue(entity.equals(entity));
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals_whenDifferentObjectType_shouldReturnFalse() {
        ItemEntity item = mock(ItemEntity.class);
        assertFalse(entity.equals(item));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenAnyNullIds_shouldReturnFalse() {
        CfgCanaleDispositivoEntity other = mock(CfgCanaleDispositivoEntity.class);
        lenient().when(other.getId()).thenReturn(1L);
        entity.setId(null);
        assertFalse(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenDifferentIds_shouldReturnFalse() {
        CfgCanaleDispositivoEntity other = mock(CfgCanaleDispositivoEntity.class);
        when(other.getId()).thenReturn(1L);
        entity.setId(2L);
        assertFalse(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenSameIds_shouldReturnTrue() {
        CfgCanaleDispositivoEntity other = mock(CfgCanaleDispositivoEntity.class);
        when(other.getId()).thenReturn(1L);
        entity.setId(1L);
        assertTrue(entity.equals(other));
    }

    @Test
    public void setFtpUser_whenNull() {
        entity.setFtpUser(null);
        assertNull(entity.getFtpUser());
    }

    @Test
    public void setFtpUser() {
        entity.setFtpUser("user");
        assertNotNull(entity.getFtpUser());
    }

    @Test
    public void getFtpUser_whenNull_shouldReturnNull() {
        entity.setFtpUser(null);
        assertNull(entity.getFtpUser());
    }

    @Test
    public void getFtpUser() {
        entity.setFtpUser("user");
        assertNotNull(entity.getFtpUser());
    }

    @Test
    public void setFtpPassword_whenNull() {
        entity.setFtpPassword(null);
        assertNull(entity.getFtpPassword());
    }

    @Test
    public void setFtpPassword() {
        entity.setFtpPassword("password");
        assertNotNull(entity.getFtpPassword());
    }

    @Test
    public void getFtpPassword_whenNull_shouldReturnNull() {
        entity.setFtpPassword(null);
        assertNull(entity.getFtpPassword());
    }

    @Test
    public void getFtpPassword() {
        entity.setFtpPassword("password");
        assertNotNull(entity.getFtpPassword());
    }
}