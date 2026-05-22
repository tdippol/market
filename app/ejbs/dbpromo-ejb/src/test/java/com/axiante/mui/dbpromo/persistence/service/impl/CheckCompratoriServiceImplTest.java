package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CheckCompratoriDAO;
import com.axiante.mui.dbpromo.persistence.entities.CheckCompratoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CheckCompratoriServiceImplTest {
    @Mock
    private CheckCompratoriDAO dao;

    @Mock
    private PromozioneTestataEntity testata;

    @Mock
    private CompratoreEntity compratore;

    @Mock
    private CheckCompratoriEntity entity1;

    @Mock
    private CheckCompratoriEntity entity2;

    @InjectMocks
    private CheckCompratoriServiceImpl service;

    private List<CheckCompratoriEntity> entities = Arrays.asList(entity1, entity2);

    @Before
    public void setUp() throws Exception {
        when(dao.findByPromozione(testata)).thenReturn(entities);
        when(dao.findByCompratore(compratore)).thenReturn(entities);
        when(dao.findByPromozioneAndCompratore(testata, compratore)).thenReturn(entity1);
    }

    @Test
    public void findByPromozione() {
        assertEquals(entities, service.findByPromozione(testata));
        verify(dao, times(1)).findByPromozione(testata);
    }

    @Test
    public void findByCompratore() {
        assertEquals(entities, service.findByCompratore(compratore));
        verify(dao, times(1)).findByCompratore(compratore);
    }

    @Test
    public void findByPromozioneAndCompratore_whenDaoThrowException_shouldReturnNull() {
        when(dao.findByPromozioneAndCompratore(testata, compratore)).thenThrow(new RuntimeException());
        assertNull(service.findByPromozioneAndCompratore(testata, compratore));
        verify(dao, times(1)).findByPromozioneAndCompratore(testata, compratore);
    }

    @Test
    public void findByPromozioneAndCompratore() {
        assertEquals(entity1, service.findByPromozioneAndCompratore(testata, compratore));
        verify(dao, times(1)).findByPromozioneAndCompratore(testata, compratore);
    }
}