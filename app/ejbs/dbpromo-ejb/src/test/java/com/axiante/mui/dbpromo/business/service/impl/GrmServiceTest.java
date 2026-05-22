package com.axiante.mui.dbpromo.business.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.GrmDAO;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.GrmServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GrmServiceTest {
    @Mock
    private GrmDAO dao;
    @Spy
    @InjectMocks
    GrmServiceImpl service;

    @Test
    public void testPersist() throws Exception {
        GrmEntity e = Mockito.mock(GrmEntity.class);

        service.persist(e);
        Mockito.verify(dao).persist(e);
    }

    @Test
    public void findByCode() {
        String code = "some";
        service.findByCode(code);
        Mockito.verify(dao).findByCode(code);
    }
}
