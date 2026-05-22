package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.axiante.mui.dbpromo.persistence.dao.MuiFontEntitiesDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MuiFontEntitiesServiceImplTest {

    @Mock
    private MuiFontEntitiesDAO dao;

    @InjectMocks
    MuiFontEntitiesServiceImpl service;

    MuiFontEntities entity1 = new MuiFontEntities();
    MuiFontEntities entity2 = new MuiFontEntities();

    List<MuiFontEntities> entities = Arrays.asList(entity1, entity2);
    @Before
    public void setUp() throws Exception {
        when(dao.findAll()).thenReturn(entities);
        when(dao.findById(anyString())).thenReturn(entity1);
    }

    @Test
    public void testFindByIdCallsDAO(){
        assertEquals(entity1, service.find("test"));
        verify(dao, times(1)).findById("test");
    }

    @Test
    public void testFindAllCallsDAO(){
        assertEquals(entities, service.findAll());
        verify(dao, times(1)).findAll();
    }
}
