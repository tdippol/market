package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.dao.MuiPromoDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.service.impl.MuiPromoDbPromoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MuiPromoDbPromoServiceTest {

	@Mock
	MuiPromoDbPromoDAO dao;

	@Spy
	@InjectMocks
	MuiPromoDbPromoServiceImpl service;

	@Test
	public void testFindAll() {
		service.findAll();
		Mockito.verify(dao).findAll();
	}
}
