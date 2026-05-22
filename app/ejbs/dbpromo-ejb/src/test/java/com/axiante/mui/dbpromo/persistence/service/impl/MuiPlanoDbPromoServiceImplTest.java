package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiPlanoDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
public class MuiPlanoDbPromoServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private MuiPlanoDbPromoDAO dao;

    @Mock
    private MuiPlanoDbPromoEntity entity1;

    @Mock
    private MuiPlanoDbPromoEntity entity2;

    @Mock
    private PromozioneTestataEntity promo;

    @InjectMocks
    private MuiPlanoDbPromoServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findAll()).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
        when(dao.findByIdPlano("FOO")).thenReturn(entity1);
        when(entity1.getIdPlano()).thenReturn("A");
        when(entity2.getIdPlano()).thenReturn("B");
    }

    @Test
    public void findAll() {
        final List<MuiPlanoDbPromoEntity> all = service.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(entity1));
        assertTrue(all.contains(entity2));
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findByIdPlano_whenNullIdPlano_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByIdPlano(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByIdPlano() {
        final MuiPlanoDbPromoEntity result = service.findByIdPlano("FOO");
        assertEquals(entity1, result);
        verify(dao, times(1)).findByIdPlano("FOO");
    }

    @Test
    public void findByPromo_whenNullPromo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByPromo(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByPromo_whenPromoWithoutPlanogrammi_shouldReturnEmptyList() {
        when(promo.getPlanogrammi()).thenReturn(null);
        final List<MuiPlanoDbPromoEntity> result = service.findByPromo(promo);
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByPromo_whenPromoWithPlanogrammi_shouldReturnThem() {
        when(promo.getPlanogrammi()).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toSet()));
        final List<MuiPlanoDbPromoEntity> result = service.findByPromo(promo);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verifyZeroInteractions(dao);
    }

    @Test
    public void findIdPlanoByPromo_whenNullPromo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findIdPlanoByPromo(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findIdPlanoByPromo() {
        when(promo.getPlanogrammi()).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toSet()));
        final List<String> result = service.findIdPlanoByPromo(promo);
        assertEquals(2, result.size());
        assertTrue(result.contains("A"));
        assertTrue(result.contains("B"));
        verifyZeroInteractions(dao);
    }
}