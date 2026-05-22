package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MuiIniziativaDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiIniziativaEntity;
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

public class JpaMuiIniziativaDAOImplTest extends AbstractDaoTest {
    @Inject
    private MuiIniziativaDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaMuiIniziativaDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private MuiIniziativaEntity iniziativa1;
    private MuiIniziativaEntity iniziativa2;
    private MuiIniziativaEntity iniziativa3;

    @Before
    public void setUp() throws Exception {
        Date dataInizio1 = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 1).getTime();
        Date dataFine1 = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 15).getTime();
        Date dataInizio2 = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 16).getTime();
        Date dataFine2 = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 30).getTime();
        Date dataInizio3 = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 1).getTime();
        Date dataFine3 = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 30).getTime();
        iniziativa1 = createIniziativa("I-01", "INIZIATIVA 01", dataInizio1, dataFine1);
        iniziativa2 = createIniziativa("I-02", "INIZIATIVA 02", dataInizio2, dataFine2);
        iniziativa3 = createIniziativa("I-03", "INIZIATIVA 03", dataInizio3, dataFine3);
        persist(iniziativa1, iniziativa2, iniziativa3);
    }

    @Test
    public void findAllByDataInizioAndDataFine_givenNullDataInizio_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByDataInizioAndDataFine(null, new Date());
    }

    @Test
    public void findAllByDataInizioAndDataFine_givenNullDataFine_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByDataInizioAndDataFine(new Date(), null);
    }

    @Test
    public void findAllByDataInizioAndDataFine() {
        Date dataInizio = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 5).getTime();
        Date dataFine = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 10).getTime();
        List<MuiIniziativaEntity> result = dao.findAllByDataInizioAndDataFine(dataInizio, dataFine);
        assertEquals(2, result.size());
        assertTrue(result.contains(iniziativa1));
        assertFalse(result.contains(iniziativa2));
        assertTrue(result.contains(iniziativa3));
    }

    private MuiIniziativaEntity createIniziativa(String codice, String descrizione, Date dataInizio, Date dataFine) {
        MuiIniziativaEntity e = new MuiIniziativaEntity();
        e.setCodiceIniziativa(codice);
        e.setDescrizione(descrizione);
        e.setDataInizio(dataInizio);
        e.setDataFine(dataFine);
        return e;
    }
}