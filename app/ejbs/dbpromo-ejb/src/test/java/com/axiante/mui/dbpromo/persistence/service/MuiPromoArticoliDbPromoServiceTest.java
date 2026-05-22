package com.axiante.mui.dbpromo.persistence.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.MuiPromoArticoliDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.service.impl.MuiPromoArticoliDbPromoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MuiPromoArticoliDbPromoServiceTest {

    @Mock
    MuiPromoArticoliDbPromoDAO dao;

    @InjectMocks
    private MuiPromoArticoliDbPromoServiceImpl service;

    @Test
    public void findAll_shouldCallDao() {
        service.findAll();
        verify(dao, times(1)).findAll();
    }

    @Test(expected = NullPointerException.class)
    public void findAllByCodicePromozione_givenNullCodicePromozione_shouldThrowExceptionAndNotCallDao() {
        service.findAllByCodicePromozione(null);
        verify(dao, never()).findAllByCodicePromozione(anyString());
    }

    @Test
    public void findAllByCodicePromozione_shouldCallDao() {
        service.findAllByCodicePromozione("ABC");
        verify(dao, times(1)).findAllByCodicePromozione("ABC");
    }
}