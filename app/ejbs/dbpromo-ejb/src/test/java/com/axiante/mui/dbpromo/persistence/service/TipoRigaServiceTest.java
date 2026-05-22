package com.axiante.mui.dbpromo.persistence.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazTipoRigaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.TipoRigaServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TipoRigaServiceTest {

    @Mock
    CfgPianificazTipoRigaDAO dao;

    @Spy
    @InjectMocks
    private final TipoRigaService service = new TipoRigaServiceImpl();

    @Test
    public void shouldFindById() {
        CfgPianificazTipoRigaEntity mock = mock(CfgPianificazTipoRigaEntity.class);
        when(mock.getId()).thenReturn(Long.MIN_VALUE);
        when(dao.findById(mock.getId())).thenReturn(mock);
        CfgPianificazTipoRigaEntity entity = service.findById(mock.getId());
        verify(dao, times(1)).findById(mock.getId());
        verify(service, times(1)).findById(mock.getId());
        assertNotNull(entity);
        assertEquals(mock, entity);
    }

    @Test
    public void shouldNotFindById() {
        CfgPianificazTipoRigaEntity mock = mock(CfgPianificazTipoRigaEntity.class);
        when(mock.getId()).thenReturn(Long.MIN_VALUE);
        when(dao.findById(mock.getId())).thenReturn(null);
        CfgPianificazTipoRigaEntity entity = service.findById(mock.getId());
        verify(dao, times(1)).findById(mock.getId());
        verify(service, times(1)).findById(mock.getId());
        assertNull(entity);
    }

    @Test
    public void shouldFindByDescription() {
        CfgPianificazTipoRigaEntity mock = mock(CfgPianificazTipoRigaEntity.class);
        when(mock.getDescrizione()).thenReturn("descrizione");
        when(dao.findByDescription(mock.getDescrizione())).thenReturn(mock);
        CfgPianificazTipoRigaEntity entity = service.findByDescription(mock.getDescrizione());
        verify(dao, times(1)).findByDescription(mock.getDescrizione());
        verify(service, times(1)).findByDescription(mock.getDescrizione());
        assertNotNull(entity);
        assertEquals(mock, entity);
    }

    @Test
    public void shouldNotFindByDescription() {
        CfgPianificazTipoRigaEntity mock = mock(CfgPianificazTipoRigaEntity.class);
        when(mock.getDescrizione()).thenReturn("descrizione");
        CfgPianificazTipoRigaEntity entity = service.findByDescription(mock.getDescrizione());
        verify(dao, times(1)).findByDescription(mock.getDescrizione());
        verify(service, times(1)).findByDescription(mock.getDescrizione());
        assertNull(entity);
    }

    @Test
    public void shouldFindAll() {
        CfgPianificazTipoRigaEntity mock1 = mock(CfgPianificazTipoRigaEntity.class);
        CfgPianificazTipoRigaEntity mock2 = mock(CfgPianificazTipoRigaEntity.class);
        List<CfgPianificazTipoRigaEntity> tipoRighe = Arrays.asList(mock1, mock2);
        when(dao.findAll()).thenReturn(tipoRighe);
        List<CfgPianificazTipoRigaEntity> entities = service.findAll();
        verify(dao, times(1)).findAll();
        verify(service, times(1)).findAll();
        assertNotNull(entities);
        assertTrue(entities.size() == 2);
    }

    @Test
    public void shouldNotFindAll() {
        List<CfgPianificazTipoRigaEntity> entities = service.findAll();
        verify(dao, times(1)).findAll();
        verify(service, times(1)).findAll();
        assertNotNull(entities);
        assertTrue(entities.isEmpty());
    }

    @Test
    public void shouldFindByCodiceTipo() {
        CfgPianificazTipoRigaEntity mock = mock(CfgPianificazTipoRigaEntity.class);
        when(mock.getCodiceTipo()).thenReturn("codiceTipo");
        when(dao.findByCodiceTipo(mock.getCodiceTipo())).thenReturn(mock);
        CfgPianificazTipoRigaEntity entity = service.findByCodiceTipo(mock.getCodiceTipo());
        verify(dao, times(1)).findByCodiceTipo(mock.getCodiceTipo());
        verify(service, times(1)).findByCodiceTipo(mock.getCodiceTipo());
        assertNotNull(entity);
        assertEquals(mock, entity);
    }

    @Test
    public void shouldNotFindByCodiceTipo() {
        CfgPianificazTipoRigaEntity mock = mock(CfgPianificazTipoRigaEntity.class);
        when(mock.getCodiceTipo()).thenReturn("codiceTipo");
        CfgPianificazTipoRigaEntity entity = service.findByCodiceTipo(mock.getCodiceTipo());
        verify(dao, times(1)).findByCodiceTipo(mock.getCodiceTipo());
        verify(service, times(1)).findByCodiceTipo(mock.getCodiceTipo());
        assertNull(entity);
    }

}
