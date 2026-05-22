package com.axiante.mui.persistence.service;

import com.axiante.mui.persistence.dao.AuditLogDAO;
import com.axiante.mui.persistence.entity.AuditLogEntity;
import com.axiante.mui.persistence.service.impl.AuditLogServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuditLogServiceTest {

    @Mock
    private AuditLogEntity mockedEntity;

    @Mock
    private AuditLogDAO dao;

    @Spy
    @InjectMocks
    private AuditLogService service = new AuditLogServiceImpl();

    @Test
    public void findAllLogs_shouldCallDao() {
        final List<AuditLogEntity> entities = Collections.singletonList(mockedEntity);
        when(dao.readAll()).thenReturn(entities);
        assertEquals(1, service.findAllLogs().size());
        verify(dao, times(1)).readAll();
    }

    @Test
    public void saveLog_shouldCallDao() throws Exception {
        when(dao.persist(mockedEntity)).thenReturn(mockedEntity);
        assertNotNull(service.saveLog(mockedEntity));
        verify(dao, times(1)).persist(mockedEntity);
    }

    @Test
    public void removeLog_shouldCallDao() throws Exception {
        doNothing().when(dao).remove(mockedEntity);
        service.removeLog(mockedEntity);
        verify(dao, times(1)).remove(mockedEntity);
    }

    @Test
    public void findAllPaginationSortedLogs_shouldCallDao() {
        final int currentPage = 0;
        final int pageSize = 10;
        final Map<String, String> sortFilters = new HashMap<>();
        sortFilters.put("foo", "bar");
        final Map<String, Object> filters = new HashMap<>();
        filters.put("baz", 42);
        final List<AuditLogEntity> entities = Collections.singletonList(mockedEntity);
        when(dao.findAllPaginationSortedLogs(currentPage, pageSize, sortFilters, filters, false))
                .thenReturn(entities);
        assertEquals(1,
                service.findAllPaginationSortedLogs(currentPage, pageSize, sortFilters, filters, false).size());
        verify(dao, times(1))
                .findAllPaginationSortedLogs(currentPage, pageSize, sortFilters, filters, false);
    }

    @Test
    public void deleteAllLogs_shouldCallDao() throws Exception {
        doNothing().when(dao).deleteAll();
        service.deleteAllLogs();
        verify(dao, times(1)).deleteAll();
    }

    @Test
    public void deleteAllLogsFiltered_shouldCallDao() throws Exception {
        final Date date = mock(Date.class);
        doNothing().when(dao).deleteAllFiltered(date, true);
        service.deleteAllLogsFiltered(date, true);
        verify(dao, times(1)).deleteAllFiltered(date, true);
    }

    @Test
    public void findAllPaginationFilteredLogs_shouldCallDao() {
        final int currentPage = 0;
        final int pageSize = 10;
        final Date date1 = mock(Date.class);
        final Date date2 = mock(Date.class);
        final Map<String, Object> filters = new HashMap<>();
        filters.put("baz", 42);
        final List<AuditLogEntity> entities = Collections.singletonList(mockedEntity);
        when(dao.findAllPaginationFilteredLogs(currentPage, pageSize, date1, date2, filters))
                .thenReturn(entities);
        assertEquals(1,
                service.findAllPaginationFilteredLogs(currentPage, pageSize, date1, date2, filters).size());
        verify(dao, times(1))
                .findAllPaginationFilteredLogs(currentPage, pageSize, date1, date2, filters);
    }

    @Test(expected = NullPointerException.class)
    public void logAction_givenNullAction_shouldThrowException() {
        service.logAction(null, "path", "junit");
    }

    @Test(expected = NullPointerException.class)
    public void logAction_givenNullPath_shouldThrowException() {
        service.logAction("action", null, "junit");
    }

    @Test(expected = NullPointerException.class)
    public void logAction_givenNullUsername_shouldThrowException() {
        service.logAction("action", "path", null);
    }

    @Test
    public void logAction_givenValidParams_shouldReturnTrue() throws Exception {
        when(dao.persist(any(AuditLogEntity.class))).thenReturn(mockedEntity);
        assertTrue(service.logAction("action", "path", "junit"));
        verify(dao, times(1)).persist(any(AuditLogEntity.class));
    }

    @Test
    public void logAction_whenException_shouldReturnFalse() throws Exception {
        when(dao.persist(any(AuditLogEntity.class))).thenThrow(Exception.class);
        assertFalse(service.logAction("action", "path", "junit"));
        verify(dao, times(1)).persist(any(AuditLogEntity.class));
    }
}
