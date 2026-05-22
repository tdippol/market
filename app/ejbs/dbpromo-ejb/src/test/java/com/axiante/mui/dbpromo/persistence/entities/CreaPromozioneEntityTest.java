package com.axiante.mui.dbpromo.persistence.entities;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CreaPromozioneEntityTest {

    private CreaPromozioneEntity entity = new CreaPromozioneEntity();

    @Before
    public void init() {
        entity.setPromozioneTestataEntities(new HashSet<PromozioneTestataEntity>());
    }

    @Test
    public void testGetNoteMarketingReturnsNull() {
        assertNull(entity.getNoteMarketing());
    }

    @Test
    public void whenAddPromozioneTestataEntityThenEntityInList() {
        PromozioneTestataEntity e = new PromozioneTestataEntity();
        int size = entity.getPromozioneTestataEntities().size();
        entity.addPromozioneTestataEntity(e);
        assertThat(entity.getPromozioneTestataEntities().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getCreaPromozioneEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemovePromozioneTestataEntityThenEntityNotInList() {
        PromozioneTestataEntity e = new PromozioneTestataEntity();
        int size = entity.getPromozioneTestataEntities().size();
        entity.addPromozioneTestataEntity(e);
        assertThat(entity.getPromozioneTestataEntities().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getCreaPromozioneEntity(), CoreMatchers.equalTo(entity));

        entity.removePromozioneTestataEntity(e);
        assertThat(entity.getPromozioneTestataEntities().size(), CoreMatchers.equalTo(size));
        assertNull(e.getCreaPromozioneEntity());

    }

    @Test
    public void testGetNewNoteMarketingReturnsNull() {
        assertNull(entity.getNewNoteMarketing());
    }

    @Test
    public void testGetNewDataFineReturnsNull() {
        assertNull(entity.getNewDataFine());
    }

    @Test
    public void testGetNewDataInizioReturnsNull() {
        assertNull(entity.getNewDataInizio());
    }

    @Test
    public void testGetNewDescrizioneReturnsNull() {
        assertNull(entity.getNewDescrizione());
    }

    @Test
    public void getNewOraInizio_shouldReturnNull() {
        assertNull(entity.getNewOraInizio());
    }

    @Test
    public void getNewOraFine_shouldReturnNull() {
        assertNull(entity.getNewOraFine());
    }
}
