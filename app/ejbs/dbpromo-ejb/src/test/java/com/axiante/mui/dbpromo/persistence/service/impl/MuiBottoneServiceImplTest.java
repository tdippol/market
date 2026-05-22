package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiBottoneDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MuiBottoneServiceImplTest {
    @Mock
    private MuiBottoneDAO dao;

    @Spy
    @InjectMocks
    private MuiBottoneServiceImpl service;

    private List<MuiBottoneEntity> muiBottoneList;

    @Before
    public void setUp() throws Exception {
        MuiBottoneEntity btn1 = mockMuiBottone(1L, "BTN-01");
        MuiBottoneEntity btn2 = mockMuiBottone(2L, "BTN-02");
        muiBottoneList = Arrays.asList(btn1, btn2);
    }

    @Test
    public void findAll() {
        when(dao.findAll()).thenReturn(muiBottoneList);
        final List<MuiBottoneEntity> result = service.findAll();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId().longValue());
        assertEquals("BTN-01", result.get(0).getDescrizione());
        assertEquals(2L, result.get(1).getId().longValue());
        assertEquals("BTN-02", result.get(1).getDescrizione());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findById() {
        when(dao.findById(1L)).thenReturn(muiBottoneList.get(0));
        final MuiBottoneEntity result = service.findById(1L);
        assertEquals(1L, result.getId().longValue());
        assertEquals("BTN-01", result.getDescrizione());
        verify(dao, times(1)).findById(1L);
    }

    private MuiBottoneEntity mockMuiBottone(Long id, String description) {
        MuiBottoneEntity mock = mock(MuiBottoneEntity.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getDescrizione()).thenReturn(description);
        return mock;
    }
}