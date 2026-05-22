package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
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
public class PromoServiceImplTest {
    @Mock
    private PromozioneTestataDAO dao;

    @Mock
    private PromozioneTestataEntity testata;

    @Mock
    private PromozioneTestataEntity entity1;

    @Mock
    private PromozioneTestataEntity entity2;

    @InjectMocks
    private PromoServiceImpl service;

    private List<String> codiciMeccanica = Stream.of("M01", "M02").collect(Collectors.toList());

    @Before
    public void setUp() throws Exception {
        when(dao.findAllByAnnoAndMeccanica(testata, codiciMeccanica))
                .thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
    }

    @Test
    public void findAllByAnnoAndMeccanica_whenCodiciMeccanicaNull_shouldReturnEmptyList() {
        final List<PromozioneTestataEntity> result = service.findAllByAnnoAndMeccanica(testata, null);
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByAnnoAndMeccanica_whenCodiciMeccanicaEmpty_shouldReturnEmptyList() {
        final List<PromozioneTestataEntity> result = service.findAllByAnnoAndMeccanica(testata, Collections.emptyList());
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByAnnoAndMeccanica() {
        final List<PromozioneTestataEntity> result = service.findAllByAnnoAndMeccanica(testata, codiciMeccanica);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findAllByAnnoAndMeccanica(testata, codiciMeccanica);
    }
}