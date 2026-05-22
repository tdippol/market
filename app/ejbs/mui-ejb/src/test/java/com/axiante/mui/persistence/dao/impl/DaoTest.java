package com.axiante.mui.persistence.dao.impl;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import lombok.Getter;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class DaoTest {
	@Inject
	@Getter
	EntityManager em;

	@Mock
	EntityManager mockedEm;

	// #3779: creazione e caricamento dati iniziali vengono fatti direttamente dal persistence.xml
	//	@BeforeClass
	//	public static void createDatabase() {
	//		DaoTestUtils.createDatabase();
	//	}

	//	@AfterClass
	//	public static void dropDatabase() {
	//		DaoTestUtils.dropDatabase();
	//	}

	@Before
	public void initTest() {
		Mockito.lenient().doThrow(EntityExistsException.class).when(mockedEm).merge(ArgumentMatchers.any());
	}
}
