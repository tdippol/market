package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CanalePromozioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.IniziativaDAO;
import com.axiante.mui.dbpromo.persistence.entities.AuditLogInterface;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AbstractDbPromoServiceTest {
    @Mock
    private CanalePromozioneDAO dao;

    @Mock
    private CanalePromozioneEntity entity1;

    @Mock
    private CanalePromozioneEntity entity2;

    @Mock
    private CanalePromozioneEntity entity3;

    @InjectMocks
    @Spy
    private CanalePromozioneServiceImpl service;

    @InjectMocks
    @Spy
    private IniziativeServiceImpl iniziativeService;

    private List<CanalePromozioneEntity> all = Stream.of(entity1, entity2, entity3).collect(Collectors.toList());
    List<AuditLogInterface> auditingEntities = all.stream().map(e -> (AuditLogInterface) e)
            .collect(Collectors.toList());
    private int batchSize = 10;

    @Before
    public void setUp() throws Exception {
        when(service.getDao()).thenReturn(dao);
        doNothing().when(dao).persist(all, batchSize);
        doNothing().when(dao).persistWithAuditLog(auditingEntities, batchSize, "USER");
        lenient().when(dao.persistInTransaction(all)).thenReturn(all);
        when(dao.findValueByAttribute("FOO", "BAR", "BAZ", "BAZ"))
                .thenReturn("FOOBAR");
        doNothing().when(dao).flush();
    }

    @Test
    public void persist() {
        service.persist(all, batchSize);
        verify(dao, times(1)).persist(all, batchSize);
    }

    @Test
    public void persistWithAuditLog() {
        service.persistWithAuditLog(auditingEntities, batchSize, "USER");
        verify(dao, times(1)).persistWithAuditLog(auditingEntities, batchSize, "USER");
    }

    @Test
    public void ensureAllUuidAreFilled_whenListNull() {
        when(dao.findEmptyUuid()).thenReturn(null);
        service.ensureAllUuidAreFilled();
        verify(dao, never()).persistInTransaction(anyList());
    }

    @Test
    public void ensureAllUuidAreFilled_whenListEmpty() {
        when(dao.findEmptyUuid()).thenReturn(Collections.emptyList());
        service.ensureAllUuidAreFilled();
        verify(dao, never()).persistInTransaction(anyList());
    }

    @Test
    public void ensureAllUuidAreFilled() {
        IniziativaDAO iniziativaDAO = mock(IniziativaDAO.class);
        IniziativaEntity iniziativa1 = mock(IniziativaEntity.class);
        IniziativaEntity iniziativa2 = mock(IniziativaEntity.class);
        List<IniziativaEntity> emptyUuids = Stream.of(iniziativa1, iniziativa2).collect(Collectors.toList());
        when(iniziativeService.getDao()).thenReturn(iniziativaDAO);
        lenient().when(iniziativa1.getUuid()).thenReturn("UUID-1");
        lenient().when(iniziativa2.getUuid()).thenReturn("UUID-2");
        lenient().when(iniziativaDAO.findEmptyUuid()).thenReturn(emptyUuids);
        iniziativeService.ensureAllUuidAreFilled();
    }

    @Test
    public void findValueByAttribute() {
        assertEquals("FOOBAR",
                service.findValueByAttribute("FOO", "BAR", "BAZ", "BAZ"));
        verify(dao, times(1))
                .findValueByAttribute("FOO", "BAR", "BAZ", "BAZ");
    }

    @Test
    public void flush() {
        service.flush();
        verify(dao, times(1)).flush();
    }
}