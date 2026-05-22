package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SupportoMediaEntityTest {
    @Mock
    private SupportoMediaCfgCheckEntity check;

    private SupportoMediaEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new SupportoMediaEntity();
    }

    @Test
    public void addCheck_whenChecksNull() {
        entity.setSupportoMediaChecks(null);
        doNothing().when(check).setSupportoMedia(entity);
        assertEquals(check, entity.addCheck(check));
        verify(check, times(1)).setSupportoMedia(entity);
    }

    @Test
    public void addCheck() {
        entity.setSupportoMediaChecks(new HashSet<>());
        doNothing().when(check).setSupportoMedia(entity);
        assertEquals(check, entity.addCheck(check));
        verify(check, times(1)).setSupportoMedia(entity);
    }

    @Test
    public void removeCheck_whenChecksNull() {
        entity.setSupportoMediaChecks(null);
        assertEquals(check, entity.removeCheck(check));
        verify(check, times(1)).setSupportoMedia(null);
    }

    @Test
    public void removeCheck() {
        entity.setSupportoMediaChecks(new HashSet<>());
        assertEquals(check, entity.removeCheck(check));
        verify(check, times(1)).setSupportoMedia(null);
    }
}