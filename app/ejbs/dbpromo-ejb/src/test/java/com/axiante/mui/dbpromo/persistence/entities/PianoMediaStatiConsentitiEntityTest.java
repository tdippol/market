package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PianoMediaStatiConsentitiEntityTest {
    PianoMediaStatiConsentitiEntity entity;

    @Test
    public void getUuid_whenAlreadySetted_thenReturnSameValue() {
        entity = new PianoMediaStatiConsentitiEntity();
        entity.setUuid("UUID");
        assertEquals("UUID", entity.getUuid());
    }

    @Test
    public void getUuid() {
        entity = new PianoMediaStatiConsentitiEntity();
        assertNotNull(entity.getUuid());
    }
}