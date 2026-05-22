package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromoStatiConsentitiDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PromoStatiConsentitiServiceImplTest {
    @Mock
    PromoStatiConsentitiDAO dao;

    @InjectMocks
    PromoStatiConsentitiServiceImpl service;

    @Test
    public void getDao() {
        assertNotNull(service.getDao());
    }
}