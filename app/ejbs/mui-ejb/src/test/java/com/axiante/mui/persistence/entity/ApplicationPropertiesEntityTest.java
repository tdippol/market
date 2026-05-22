package com.axiante.mui.persistence.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationPropertiesEntityTest {

    @Test
    public void getUuid_shouldReturnNotNull() {
        final ApplicationPropertiesEntity entity1 = new ApplicationPropertiesEntity();
        assertNotNull(entity1.getUuid());
        final ApplicationPropertiesEntity entity2 = new ApplicationPropertiesEntity();
        entity2.setUuid("foo-uuid");
        assertEquals("foo-uuid", entity2.getUuid());
    }
}