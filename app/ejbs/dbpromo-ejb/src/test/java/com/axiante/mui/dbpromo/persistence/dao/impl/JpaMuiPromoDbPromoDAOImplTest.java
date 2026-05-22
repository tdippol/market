package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MuiPromoDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoDbPromoEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaMuiPromoDbPromoDAOImplTest extends AbstractDaoTest {
	@Inject
	private MuiPromoDbPromoDAO dao;

	@Rule
	public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
					JpaMuiPromoDbPromoDAOImpl.class, DbPromo.class)
			.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	private MuiPromoDbPromoEntity promoEntity1;
	private MuiPromoDbPromoEntity promoEntity2;
	private MuiPromoDbPromoEntity promoEntity3;

	@Before
	public void init() {
		Date dataFine1 = new GregorianCalendar(2024, Calendar.OCTOBER, 1).getTime();
		Date dataFine2 = new GregorianCalendar(2024, Calendar.OCTOBER, 10).getTime();
		Date dataFine3 = new GregorianCalendar(2024, Calendar.OCTOBER, 20).getTime();
		promoEntity1 = createPromoEntity("PROMO 01", "CH1", "GR01", dataFine1);
		promoEntity2 = createPromoEntity("PROMO 02", "CH2", "GR02", dataFine2);
		promoEntity3 = createPromoEntity("PROMO 03", "CH3", "GR03", dataFine3);
		persist(promoEntity1, promoEntity2, promoEntity3);
	}

	@Test
	public void testFindAll() {
		List<MuiPromoDbPromoEntity> result = dao.findAll();
		assertEquals(3, result.size());
		assertTrue(result.contains(promoEntity1));
		assertTrue(result.contains(promoEntity2));
		assertTrue(result.contains(promoEntity3));
	}

	private MuiPromoDbPromoEntity createPromoEntity(String codicePromozione, String codiceCanale, String codiceGruppo,
													Date dataFine) {
		MuiPromoDbPromoEntity e = new MuiPromoDbPromoEntity();
		e.setCodicePromozione(codicePromozione);
		e.setCodiceCanale(codiceCanale);
		e.setCodiceGruppo(codiceGruppo);
		e.setDataFine(dataFine);
		return e;
	}

	@Test
	public void findByDataFineGreaterThan() {
		Date date = new GregorianCalendar(2024, Calendar.OCTOBER, 15).getTime();
		final List<MuiPromoDbPromoEntity> result = dao.findByDataFineGreaterThan(new java.sql.Date(date.getTime()));
		assertEquals(1, result.size());
		assertFalse(result.contains(promoEntity1));
		assertFalse(result.contains(promoEntity2));
		assertTrue(result.contains(promoEntity3));
	}

	@Test
	public void findByCodicePromozione() {
		assertEquals(promoEntity1, dao.findByCodicePromozione("PROMO 01"));
	}

	@Test
	public void findByCodicePromozione_givenNullCodicePromozione_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dao.findByCodicePromozione(null);
	}

	@Test
	public void findByCodicePromozione_whenNoResult_shouldReturnNull() {
		assertNull(dao.findByCodicePromozione("PROMO 42"));
	}
}
