package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ReportBSDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportBSServiceImplTest {
    @Mock
    ReportBSDAO dao;

    @InjectMocks
    private ReportBSServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private MuiReportBSEntity entity1;

    @Mock
    private MuiReportBSEntity entity2;

    @Mock
    private MuiReportBSEntity entity3;

    private Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    private Date dataInizio = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
            .minusDays(10).atZone(ZoneId.systemDefault()).toInstant());
    private Date dataFine = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
            .plusDays(10).atZone(ZoneId.systemDefault()).toInstant());

    @Before
    public void setUp() throws Exception {
        when(dao.findAllInProgressByPrefissoBS("00001")).thenReturn(Collections.singletonList(entity1));
        when(dao.findAllFuturesByPrefissoBS("00002")).thenReturn(Collections.singletonList(entity2));
        when(dao.findAllCompletedByPrefissoBS("00003")).thenReturn(Collections.singletonList(entity3));
        when(dao.findWithChiaveIn(Collections.singletonList("00001"))).thenReturn(Collections.singletonList(entity1));
        when(dao.findInProgress(today)).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
        when(dao.findAllNotUsedInBetween(Collections.singletonList("00002"), dataInizio, dataFine, today))
                .thenReturn(Stream.of(entity2, entity3).collect(Collectors.toList()));
        when(dao.findAllNotUsedInProgress(Collections.singletonList("00003"), today))
                .thenReturn(Stream.of(entity3).collect(Collectors.toList()));
        when(dao.findById("P01", "BS01")).thenReturn(entity1);
        when(dao.findAll()).thenReturn(Stream.of(entity1, entity2, entity3).collect(Collectors.toList()));
        when(dao.findInBetween(dataInizio, dataFine, today)).thenReturn(Stream.of(entity1, entity3).collect(Collectors.toList()));
    }

    @Test
    public void findAllInProgressByPrefissoBS() {
        final List<MuiReportBSEntity> result = service.findAllInProgressByPrefissoBS("00001");
        assertEquals(1, result.size());
        assertEquals(entity1, result.get(0));
        verify(dao, times(1)).findAllInProgressByPrefissoBS("00001");
        verify(dao, never()).findAllFuturesByPrefissoBS("00002");
        verify(dao, never()).findAllCompletedByPrefissoBS("00003");
    }

    @Test
    public void findAllInProgressByPrefissoBS_whenNullPrefissoBS_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllInProgressByPrefissoBS(null);
    }

    @Test
    public void findAllFuturesByPrefissoBS() {
        final List<MuiReportBSEntity> result = service.findAllFuturesByPrefissoBS("00002");
        assertEquals(1, result.size());
        assertEquals(entity2, result.get(0));
        verify(dao, never()).findAllInProgressByPrefissoBS("00001");
        verify(dao, times(1)).findAllFuturesByPrefissoBS("00002");
        verify(dao, never()).findAllCompletedByPrefissoBS("00003");
    }

    @Test
    public void findAllFuturesByPrefissoBS_whenNullPrefissoBS_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllFuturesByPrefissoBS(null);
    }

    @Test
    public void findAllCompletedByPrefissoBS() {
        final List<MuiReportBSEntity> result = service.findAllCompletedByPrefissoBS("00003");
        assertEquals(1, result.size());
        assertEquals(entity3, result.get(0));
        verify(dao, never()).findAllInProgressByPrefissoBS("00001");
        verify(dao, never()).findAllFuturesByPrefissoBS("00002");
        verify(dao, times(1)).findAllCompletedByPrefissoBS("00003");
    }

    @Test
    public void findAllCompletedByPrefissoBS_whenNullPrefissoBS_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllCompletedByPrefissoBS(null);
    }

    @Test
    public void findAll() {
        final List<MuiReportBSEntity> all = service.findAll();
        assertEquals(3, all.size());
        assertTrue(all.contains(entity1));
        assertTrue(all.contains(entity2));
        assertTrue(all.contains(entity3));
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findById_whenNullCodicePromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findById(null, "BS01");
        verify(dao, never()).findById(anyString(), anyString());
    }

    @Test
    public void findById_whenNullPrefissoBS_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findById("P01", null);
        verify(dao, never()).findById(anyString(), anyString());
    }

    @Test
    public void findById() {
        final MuiReportBSEntity byId = service.findById("P01", "BS01");
        assertEquals(entity1, byId);
        verify(dao, times(1)).findById("P01", "BS01");
    }

    @Test
    public void findAllNotUsedInProgress_whenChiaviNull() {
        final List<MuiReportBSEntity> result = service.findAllNotUsedInProgress(null, today);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, never()).findAllNotUsedInProgress(any(), eq(today));
        verify(dao, times(1)).findInProgress(today);
    }

    @Test
    public void findAllNotUsedInProgress_whenChiaviEmpty() {
        final List<MuiReportBSEntity> result = service.findAllNotUsedInProgress(Collections.emptyList(), today);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, never()).findAllNotUsedInProgress(any(), eq(today));
        verify(dao, times(1)).findInProgress(today);
    }

    @Test
    public void findAllNotUsedInProgress() {
        final List<MuiReportBSEntity> result = service.findAllNotUsedInProgress(Collections.singletonList("00003"), today);
        assertEquals(1, result.size());
        assertTrue(result.contains(entity3));
        verify(dao, times(1)).findAllNotUsedInProgress(Collections.singletonList("00003"), today);
    }

    @Test
    public void findAllNotUsedInBetween_whenChiaviNull() {
        final List<MuiReportBSEntity> result = service.findAllNotUsedInBetween(null, dataInizio, dataFine);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity3));
        verify(dao, never()).findAllNotUsedInBetween(any(), eq(dataInizio), eq(dataFine), eq(today));
        verify(dao, times(1)).findInBetween(dataInizio, dataFine, today);
    }

    @Test
    public void findAllNotUsedInBetween_whenChiaviEmpty() {
        final List<MuiReportBSEntity> result = service.findAllNotUsedInBetween(Collections.emptyList(), dataInizio, dataFine);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity3));
        verify(dao, never()).findAllNotUsedInBetween(any(), eq(dataInizio), eq(dataFine), eq(today));
        verify(dao, times(1)).findInBetween(dataInizio, dataFine, today);
    }

    @Test
    public void findAllNotUsedInBetween() {
        final List<MuiReportBSEntity> result = service.findAllNotUsedInBetween(Collections.singletonList("00002"),
                dataInizio, dataFine);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity2));
        assertTrue(result.contains(entity3));
        verify(dao, times(1)).findAllNotUsedInBetween(Collections.singletonList("00002"),
                dataInizio, dataFine, today);
    }

    @Test
    public void findInProgress() {
        final List<MuiReportBSEntity> result = service.findInProgress(today);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findInProgress(today);
    }

    @Test
    public void findWithChiaveIn() {
        final List<MuiReportBSEntity> result = service.findWithChiaveIn(Collections.singletonList("00001"));
        assertEquals(1, result.size());
        assertTrue(result.contains(entity1));
        verify(dao, times(1)).findWithChiaveIn(Collections.singletonList("00001"));
    }
}