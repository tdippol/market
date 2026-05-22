package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.GrmDAO;
import com.axiante.mui.dbpromo.persistence.dao.PromozionePianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.RepartoDAO;
import com.axiante.mui.dbpromo.persistence.dto.SottoclasseCountDto;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianificazioneServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private PromozionePianificazioneDAO dao;

    @Mock
    private CfgPianificazioneDAO pianificazioneDAO;

    @Mock
    private GrmDAO grmDAO;

    @Mock
    private RepartoDAO repartoDAO;

    @Mock
    private GrmEntity grmEntity1;

    @Mock
    private GrmEntity grmEntity2;

    @Mock
    private RepartoEntity repartoEntity1;

    @Mock
    private RepartoEntity repartoEntity2;

    @Mock
    private PromozionePianificazioneEntity pianificazioneEntity1;

    @Mock
    private PromozionePianificazioneEntity pianificazioneEntity2;

    @Mock
    private PromozioneTestataEntity testata;

    @InjectMocks
    private PianificazioneServiceImpl service;

    private List<String> codiciCompratore = Arrays.asList("C01", "C02");
    private List<Long> ids = Arrays.asList(1L, 2L);

    @SuppressWarnings({"unchecked"})
    @Before
    public void setUp() throws Exception {
        when(grmDAO.findAllByCodiciCompratore(codiciCompratore))
                .thenReturn(Stream.of(grmEntity1, grmEntity2).collect(Collectors.toList()));
        when(repartoDAO.findAllByCodiciCompratore(codiciCompratore))
                .thenReturn(Stream.of(repartoEntity1, repartoEntity2).collect(Collectors.toList()));
        when(dao.findAllParentRowsByPromozioneTestata(testata))
                .thenReturn(Stream.of(pianificazioneEntity1, pianificazioneEntity2).collect(Collectors.toList()));
        Map<String, String> defaultValues = new HashMap<>();
        defaultValues.put("FOO", "FOO_VALUE");
        defaultValues.put("BAR", "BAR_VALUE");
        when(pianificazioneDAO.getDefaultValues(1L, 1L, "RIGA")).thenReturn(defaultValues);
        Map<String, Field> mappedFields = mock(Map.class);
        when(dao.getMappedFields()).thenReturn(mappedFields);
        when(dao.findAllByIds(ids)).thenReturn(Stream.of(pianificazioneEntity1, pianificazioneEntity2).collect(Collectors.toList()));
        when(dao.findAllSetByPromozione(testata))
                .thenReturn(Stream.of(pianificazioneEntity1, pianificazioneEntity2).collect(Collectors.toList()));
        when(dao.findFirstChildByParent(pianificazioneEntity1)).thenReturn(pianificazioneEntity2);
        when(dao.countArticoli(testata)).thenReturn(42L);
    }

    @Test
    public void findAllGrmByCodiciCompratore_whenEmptyCodiciCompratore_shouldReturnEmptyList() {
        final List<GrmEntity> result = service.findAllGrmByCodiciCompratore(Collections.emptyList());
        assertTrue(result.isEmpty());
        verifyZeroInteractions(grmDAO);
    }

    @Test
    public void findAllGrmByCodiciCompratore() {
        final List<GrmEntity> result = service.findAllGrmByCodiciCompratore(codiciCompratore);
        assertEquals(2, result.size());
        assertTrue(result.contains(grmEntity1));
        assertTrue(result.contains(grmEntity2));
        verify(grmDAO, times(1)).findAllByCodiciCompratore(codiciCompratore);
    }

    @Test
    public void findAllRepartiByCodiciCompratore_whenEmptyCodiciCompratore_shouldReturnEmptyList() {
        final List<RepartoEntity> result = service.findAllRepartiByCodiciCompratore(Collections.emptyList());
        assertTrue(result.isEmpty());
        verifyZeroInteractions(repartoDAO);
    }

    @Test
    public void findAllRepartiByCodiciCompratore() {
        final List<RepartoEntity> result = service.findAllRepartiByCodiciCompratore(codiciCompratore);
        assertEquals(2, result.size());
        assertTrue(result.contains(repartoEntity1));
        assertTrue(result.contains(repartoEntity2));
        verify(repartoDAO, times(1)).findAllByCodiciCompratore(codiciCompratore);
    }

    @Test
    public void findAllParentPromozionePianificazioneEntityByPromozioneTestata() {
        final List<PromozionePianificazioneEntity> result = service.findAllParentPromozionePianificazioneEntityByPromozioneTestata(testata);
        assertEquals(2, result.size());
        assertTrue(result.contains(pianificazioneEntity1));
        assertTrue(result.contains(pianificazioneEntity2));
        verify(dao, times(1)).findAllParentRowsByPromozioneTestata(testata);
    }

    @Test
    public void getDefaultValues() {
        final Map<String, String> result = service.getDefaultValues(1L, 1L, "RIGA");
        assertEquals(2, result.size());
        assertTrue(result.containsKey("FOO"));
        assertTrue(result.containsKey("BAR"));
        assertEquals("FOO_VALUE", result.get("FOO"));
        assertEquals("BAR_VALUE", result.get("BAR"));
        verify(pianificazioneDAO, times(1)).getDefaultValues(1L, 1L, "RIGA");
    }

    @Test
    public void getMappedFields() {
        final Map<String, Field> result = service.getMappedFields();
        assertNotNull(result);
        verify(dao, times(1)).getMappedFields();
    }

    @Test
    public void findAllPianificazioniByIds_whenNullIds_shouldReturnEmptyList() {
        final List<PromozionePianificazioneEntity> result = service.findAllPianificazioniByIds(null);
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllPianificazioniByIds_whenEmptyIds_shouldReturnEmptyList() {
        final List<PromozionePianificazioneEntity> result = service.findAllPianificazioniByIds(Collections.emptyList());
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllPianificazioniByIds() {
        final List<PromozionePianificazioneEntity> result = service.findAllPianificazioniByIds(ids);
        assertEquals(2, result.size());
        assertTrue(result.contains(pianificazioneEntity1));
        assertTrue(result.contains(pianificazioneEntity2));
        verify(dao, times(1)).findAllByIds(ids);
    }

    @Test
    public void findAllSetByPromozione() {
        final List<PromozionePianificazioneEntity> result = service.findAllSetByPromozione(testata);
        assertEquals(2, result.size());
        assertTrue(result.contains(pianificazioneEntity1));
        assertTrue(result.contains(pianificazioneEntity2));
        verify(dao, times(1)).findAllSetByPromozione(testata);
    }

    @Test
    public void findChildByParent() {
        assertEquals(pianificazioneEntity2, service.findChildByParent(pianificazioneEntity1));
        verify(dao, times(1)).findFirstChildByParent(pianificazioneEntity1);
    }

    @Test
    public void countArticoli_givenNullTestata_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.countArticoli(null);
    }

    @Test
    public void countArticoli() {
        assertEquals(42L, (long) service.countArticoli(testata));
        verify(dao, times(1)).countArticoli(testata);
    }

    @Test
    public void countSottoclassiUsedInPromoInProgress() {
        final List<SottoclasseCountDto> sottoclassi = Stream.of(mock(SottoclasseCountDto.class), mock(SottoclasseCountDto.class))
                .collect(Collectors.toList());
        when(dao.countSottoclassiUsedInPromoInProgress()).thenReturn(sottoclassi);
        assertEquals(2, service.countSottoclassiUsedInPromoInProgress().size());
        verify(dao, times(1)).countSottoclassiUsedInPromoInProgress();
    }
}