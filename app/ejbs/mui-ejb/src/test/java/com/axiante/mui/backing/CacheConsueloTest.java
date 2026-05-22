package com.axiante.mui.backing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class CacheConsueloTest {
    @Spy
    CacheConsuelo cacheConsuelo;
    @Mock
    CellSetCache cellCache;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(cacheConsuelo.getCellCache()).thenReturn(cellCache);
    }

    @Test
    public void run() {
        when(cellCache.closeForTimeout()).thenReturn(false);
        cacheConsuelo.run();
        when(cellCache.closeForTimeout()).thenReturn(true);
        cacheConsuelo.run();
    }

    @Test
    public void getCellCache() {
        assertNotNull(cacheConsuelo.getCellCache());
        assertEquals(cellCache,cacheConsuelo.getCellCache());

    }

}