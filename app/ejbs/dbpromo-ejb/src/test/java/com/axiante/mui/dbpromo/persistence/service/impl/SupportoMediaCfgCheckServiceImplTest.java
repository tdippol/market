package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.SupportoMediaCfgCheckDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class SupportoMediaCfgCheckServiceImplTest {
    @Mock
    SupportoMediaCfgCheckDAO dao;

    @InjectMocks
    SupportoMediaCfgCheckServiceImpl service;

    @Test
    public void getDao() {
        assertNotNull(service.getDao());
    }
}