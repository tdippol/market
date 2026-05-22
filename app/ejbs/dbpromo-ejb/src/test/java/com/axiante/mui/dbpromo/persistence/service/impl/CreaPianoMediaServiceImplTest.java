package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CreaPianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreaPianoMediaServiceImplTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private CreaPianoMediaDAO dao;

    @InjectMocks
    private CreaPianoMediaServiceImpl service;

    @Test
    public void findByUserId_givenNullUserId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByUserId(null);
        verify(dao, never()).findByUserId(any());
    }

    @Test
    public void findByUserId() {
        List<CreaPianoMediaEntity> entities = new ArrayList<>();
        entities.add(mockCreaPianoMedia("junit", "001"));
        when(dao.findByUserId("junit")).thenReturn(entities);
        final List<CreaPianoMediaEntity> result = service.findByUserId("junit");
        assertEquals(1, result.size());
        final CreaPianoMediaEntity pianoMedia = result.get(0);
        assertEquals("junit", pianoMedia.getUserId());
        assertEquals("001", pianoMedia.getSlotId());
        verify(dao, times(1)).findByUserId("junit");
    }

    @Test
    public void findByUserIdAndSlotId_givenNullUserId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByUserIdAndSlotId(null, "001");
        verify(dao, never()).findByUserIdAndSlotId(any(), any());
    }

    @Test
    public void findByUserIdAndSlotId_givenNullSlotId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByUserIdAndSlotId("junit", null);
        verify(dao, never()).findByUserIdAndSlotId(any(), any());
    }

    @Test
    public void findByUserIdAndSlotId() {
        final CreaPianoMediaEntity entity = mockCreaPianoMedia("junit", "001");
        when(dao.findByUserIdAndSlotId("junit", "001")).thenReturn(entity);
        final CreaPianoMediaEntity pianoMedia = service.findByUserIdAndSlotId("junit", "001");
        assertEquals("junit", pianoMedia.getUserId());
        assertEquals("001", pianoMedia.getSlotId());
        verify(dao, times(1)).findByUserIdAndSlotId("junit", "001");
    }

    private CreaPianoMediaEntity mockCreaPianoMedia(String userId, String slotId) {
        final CreaPianoMediaEntity mock = mock(CreaPianoMediaEntity.class);
        when(mock.getUserId()).thenReturn(userId);
        when(mock.getSlotId()).thenReturn(slotId);
        return mock;
    }
}