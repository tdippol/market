package com.axiante.mui.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResourcesTest {
    // @Mock
    // CacheConsuelo consuelo ;
    @InjectMocks
    Resources resources = new Resources();

    @Test
    public void testResourcesIsInitialized() {
        assertNotNull(resources);
    }
}
