package com.axiante.mui.dbpromo.persistence.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class GruppoPromozioneEntityTest {
    @Test
    public void whenAddCanalePromozioneEntityThenEntityInList() {
        GruppoPromozioneEntity entity = new GruppoPromozioneEntity();
        entity.setMuiCanalePromoziones(new HashSet<CanalePromozioneEntity>());
        int size = entity.getMuiCanalePromoziones().size();
        CanalePromozioneEntity e = new CanalePromozioneEntity();

        e = entity.addMuiCanalePromozione(e);
        assertThat(e.getGruppoPromozioneEntity(), CoreMatchers.equalTo(entity));
        assertThat(entity.getMuiCanalePromoziones().size(), CoreMatchers.equalTo(size + 1));
    }

    @Test
    public void whenRemoveCanalePromozioneEntityThenEntityNotInList() {
        GruppoPromozioneEntity entity = new GruppoPromozioneEntity();
        entity.setMuiCanalePromoziones(new HashSet<CanalePromozioneEntity>());
        int size = entity.getMuiCanalePromoziones().size();
        CanalePromozioneEntity e = new CanalePromozioneEntity();

        e = entity.addMuiCanalePromozione(e);
        assertThat(e.getGruppoPromozioneEntity(), CoreMatchers.equalTo(entity));
        assertThat(entity.getMuiCanalePromoziones().size(), CoreMatchers.equalTo(size + 1));

        e = entity.removeMuiCanalePromozione(e);
        assertNull(e.getGruppoPromozioneEntity());
        assertThat(entity.getMuiCanalePromoziones().size(), CoreMatchers.equalTo(size));
    }

    @Test
    public void testGetKey() {
        GruppoPromozioneEntity entity = new GruppoPromozioneEntity();
        entity.setId(Long.MIN_VALUE);
        String key = entity.getKey();
        assertNotNull(key);
        assertEquals(entity.getId().toString(), key);
    }

    @Test
    public void testGetLabel() {
        GruppoPromozioneEntity entity = new GruppoPromozioneEntity();
        entity.setDescrizione("descrizione");
        String label = entity.getLabel();
        assertNotNull(label);
        assertEquals(entity.getDescrizione(), label);
    }

}
