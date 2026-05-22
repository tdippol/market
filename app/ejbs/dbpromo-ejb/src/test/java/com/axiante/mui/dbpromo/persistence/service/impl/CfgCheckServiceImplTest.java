package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCheckDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class CfgCheckServiceImplTest {
    @Mock
    CfgCheckDAO dao;

    @InjectMocks
    CfgCheckServiceImpl service;

    @Test
    public void getDao() {
        assertNotNull(service.getDao());
    }
}