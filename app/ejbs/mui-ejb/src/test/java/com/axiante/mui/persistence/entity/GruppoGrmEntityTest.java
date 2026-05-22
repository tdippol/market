package com.axiante.mui.persistence.entity;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GruppoGrmEntityTest {
    private GruppoGrmEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new GruppoGrmEntity();
    }

    @Test
    public void setTipoAccesso_givenNullTipoAccesso_shouldSetAsRead() {
        entity.setTipoAccesso(null);
        assertEquals(PianificazioneSecurityEnum.READ, entity.getTipoAccesso());
    }

    @Test
    public void setTipoAccesso() {
        entity.setTipoAccesso(PianificazioneSecurityEnum.READ);
        assertEquals(PianificazioneSecurityEnum.READ, entity.getTipoAccesso());
        entity.setTipoAccesso(PianificazioneSecurityEnum.WRITE);
        assertEquals(PianificazioneSecurityEnum.WRITE, entity.getTipoAccesso());
    }

    @Test
    public void getTipoAccesso() {
        entity.setTipoAccesso(PianificazioneSecurityEnum.READ);
        assertEquals(PianificazioneSecurityEnum.READ, entity.getTipoAccesso());
        entity.setTipoAccesso(PianificazioneSecurityEnum.WRITE);
        assertEquals(PianificazioneSecurityEnum.WRITE, entity.getTipoAccesso());
    }
}