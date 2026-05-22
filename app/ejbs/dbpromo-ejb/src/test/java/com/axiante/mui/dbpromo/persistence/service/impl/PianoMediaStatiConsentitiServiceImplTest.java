package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaStatiConsentitiDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaStatiConsentitiServiceImplTest {
    @Mock
    PianoMediaStatiConsentitiDAO dao;

    @InjectMocks
    PianoMediaStatiConsentitiServiceImpl service;

    @Test
    public void getDao() {
        assertNotNull(service.getDao());
    }
}