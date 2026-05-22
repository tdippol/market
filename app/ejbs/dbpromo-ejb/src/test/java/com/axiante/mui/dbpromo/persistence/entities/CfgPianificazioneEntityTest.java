package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CfgPianificazioneEntityTest {
    @Test
    public void setMaxLength() {
        CfgPianificazioneEntity entity = new CfgPianificazioneEntity();
        //noinspection deprecation
        entity.setMaxLength(42);
        assertEquals(42, entity.getAllowZero().intValue());
    }
}
