package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CanalePromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CanalePromozioneServiceImplTest {
    @Mock
    private CanalePromozioneDAO dao;

    @Mock
    private CanalePromozioneEntity entity1;

    @Mock
    private CanalePromozioneEntity entity2;

    @InjectMocks
    private CanalePromozioneServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findByCodiciCanale(Collections.singletonList(1L)))
                .thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
    }

    @Test
    public void findByCodiciCanale_whenCodiciNull_thenEmptyListReturned() {
        assertTrue(service.findByCodiciCanale(null).isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByCodiciCanale_whenCodiciEmpty_thenEmptyListReturned() {
        assertTrue(service.findByCodiciCanale(Collections.emptyList()).isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByCodiciCanale() {
        final List<CanalePromozioneEntity> result = service.findByCodiciCanale(Collections.singletonList(1L));
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findByCodiciCanale(Collections.singletonList(1L));
    }
}