package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.CumulabilitaType;
import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CumulabilitaEsclusivitaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JpaCumulabilitaEsclusivitaDAOImplTest extends AbstractDaoTest {
    @Inject
    private CumulabilitaEsclusivitaDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCumulabilitaEsclusivitaDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Before
    public void setUp() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date dataInizio1 = new GregorianCalendar(2025, Calendar.JULY, 1).getTime();
        Date dataInizio2 = new GregorianCalendar(2025, Calendar.JULY, 10).getTime();
        Date dataInizio3 = new GregorianCalendar(2025, Calendar.JULY, 20).getTime();
        Date dataFine1 = new GregorianCalendar(2025, Calendar.JULY, 20).getTime();
        CumulabilitaEsclusivitaEntity entity1 = buildEntity(CumulabilitaType.CUMULABILE, dataInizio1, dataFine1, "PROMO-01");
        CumulabilitaEsclusivitaEntity entity2 = buildEntity(CumulabilitaType.ESCUSIVA, dataInizio2, cal.getTime(), "PROMO-02");
        CumulabilitaEsclusivitaEntity entity3 = buildEntity(CumulabilitaType.ESCUSIVA, dataInizio3, cal.getTime(), "PROMO-03");
        persist(entity1, entity2, entity3);
    }

    @Test
    public void findByType() {
        assertEquals(1, dao.findByType(CumulabilitaType.CUMULABILE).size());
    }

    @Test
    public void findOpenByType() {
        assertEquals(2, dao.findOpenByType(CumulabilitaType.ESCUSIVA).size());
    }

    @Test
    public void findOverlapping() {
        Date dataInizio = new GregorianCalendar(2025, Calendar.JUNE, 1).getTime();
        Date dataFine = new GregorianCalendar(2025, Calendar.JUNE, 30).getTime();
        CumulabilitaEsclusivitaEntity entity = buildEntity(CumulabilitaType.CUMULABILE, dataInizio, dataFine, "PROMO-00");
        assertTrue(dao.findOverlapping(entity).isEmpty());
    }

    @Test
    public void exportCumulabilita() {
        JpaCumulabilitaEsclusivitaDAOImpl mockDao = mock(JpaCumulabilitaEsclusivitaDAOImpl.class);
        when(mockDao.exportCumulabilita(1L, "user")).thenReturn(true);
        assertTrue(mockDao.exportCumulabilita(1L, "user"));
        verify(mockDao, times(1)).exportCumulabilita(1L, "user");
    }

    private CumulabilitaEsclusivitaEntity buildEntity(CumulabilitaType tipo, Date dataInizio, Date dataFine,
                                                      String codicePromozione) {
        CumulabilitaEsclusivitaEntity entity = new CumulabilitaEsclusivitaEntity();
        entity.setTipo(tipo);
        entity.setDataInizio(dataInizio);
        entity.setDataFine(dataFine);
        entity.setCodicePromozione(codicePromozione);
        entity.setDescrizione(codicePromozione);
        entity.setPrefissoBS("BS");
        entity.setCodiceUtenteInserimento("user");
        entity.setDataInserimento(new Date(System.currentTimeMillis()));
        return entity;
    }
}