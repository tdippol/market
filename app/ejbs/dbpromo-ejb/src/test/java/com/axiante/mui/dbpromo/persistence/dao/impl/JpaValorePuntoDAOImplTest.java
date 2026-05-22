package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.ValorePuntoDAO;
import com.axiante.mui.dbpromo.persistence.entities.ValorePuntoEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
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

public class JpaValorePuntoDAOImplTest extends AbstractDaoTest {

    @Inject
    private ValorePuntoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaValorePuntoDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        List<ValorePuntoEntity> entities = new ArrayList<>();
        ValorePuntoEntity e = new ValorePuntoEntity();
        e.setCodicePromozione("PROMO-01");
        e.setValorePunto(new BigDecimal("0.1"));
        e.setDataInizio(new GregorianCalendar(2023, Calendar.APRIL, 1).getTime());
        e.setDataFine(new GregorianCalendar(2023, Calendar.APRIL, 10).getTime());
        entities.add(e);
        e = new ValorePuntoEntity();
        e.setCodicePromozione("PROMO-02");
        e.setValorePunto(new BigDecimal("0.2"));
        e.setDataInizio(new GregorianCalendar(2023, Calendar.APRIL, 11).getTime());
        e.setDataFine(new GregorianCalendar(2023, Calendar.APRIL, 20).getTime());
        entities.add(e);
        e = new ValorePuntoEntity();
        e.setCodicePromozione("PROMO-03");
        e.setValorePunto(new BigDecimal("0.3"));
        e.setDataInizio(new GregorianCalendar(2023, Calendar.APRIL, 21).getTime());
        e.setDataFine(new GregorianCalendar(2023, Calendar.APRIL, 30).getTime());
        entities.add(e);
        openTransaction();
        dao.persist(entities);
        commitTransaction();
    }

    @Test
    public void findWhereDate_givenNullDate_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findWhereDate(null);
    }

    @Test
    public void findWhereDate_givenExistingDate_shouldReturnNotEmptyList() {
        Date date = new GregorianCalendar(2023, Calendar.APRIL, 28).getTime();
        final List<ValorePuntoEntity> result = dao.findWhereDate(date);
        assertEquals(1, result.size());
        final ValorePuntoEntity e = result.get(0);
        assertEquals("PROMO-03", e.getCodicePromozione());
        assertEquals(new BigDecimal("0.3"), e.getValorePunto());
        assertEquals(new GregorianCalendar(2023, Calendar.APRIL, 21).getTime(), e.getDataInizio());
        assertEquals(new GregorianCalendar(2023, Calendar.APRIL, 30).getTime(), e.getDataFine());
    }

    @Test
    public void findWhereDate_givenNotExistingDate_shouldReturnEmptyList() {
        Date date = new GregorianCalendar(2023, Calendar.JANUARY, 1).getTime();
        assertTrue(dao.findWhereDate(date).isEmpty());
    }
}