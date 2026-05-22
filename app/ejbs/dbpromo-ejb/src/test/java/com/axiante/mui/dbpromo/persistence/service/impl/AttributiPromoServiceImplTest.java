package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.AttributiPromoDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AttributiPromoServiceImplTest {
    @Mock
    AttributiPromoDAO dao;

    @InjectMocks
    AttributiPromoServiceImpl service;

    @Test
    public void getDao() {
        assertNotNull(service.getDao());
    }
}