package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PromozioneAttributiEntityTest {
    PromozioneAttributiEntity entity;

    @Test
    public void getUuid_whenAlreadySetted_thenReturnSameValue() {
        entity = new PromozioneAttributiEntity();
        entity.setUuid("UUID");
        assertEquals("UUID", entity.getUuid());
    }

    @Test
    public void getUuid() {
        entity = new PromozioneAttributiEntity();
        assertNotNull(entity.getUuid());
    }
}