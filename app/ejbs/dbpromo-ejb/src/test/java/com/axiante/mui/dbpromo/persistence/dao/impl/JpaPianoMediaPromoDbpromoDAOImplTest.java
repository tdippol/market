package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPromoDbpromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaPianoMediaPromoDbpromoDAOImplTest extends AbstractDaoTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPianoMediaPromoDbpromoDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Inject
    private PianoMediaPromoDbpromoDAO dao;

    private CanalePromozioneEntity canale;

    @Before
    public void setUp() throws Exception {
        final Date date1 = new GregorianCalendar(2023, Calendar.JULY, 31).getTime();
        final Date date2 = new GregorianCalendar(2023, Calendar.JUNE, 30).getTime();
        final Date date3 = new GregorianCalendar(2024, Calendar.OCTOBER, 15).getTime();
        final PianoMediaPromoDbpromoEntity entity1 = createEntity("PR_001", "PROMOZIONE NUMERO 001",
                date1, 42L);
        final PianoMediaPromoDbpromoEntity entity2 = createEntity("PR_002", "PROMOZIONE NUMERO 002",
                date2, 10L);
        final PianoMediaPromoDbpromoEntity entity3 = createEntity("PR_003", "PROMOZIONE NUMERO 003",
                date3, 10L);
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("GR").build();
        canale = new CanalePromozioneEntityBuilder(1L).withCodice(42L).withGruppo(gruppo).build();
        persist(entity1, entity2, entity3, gruppo, canale);
    }

    @Test
    public void findByDataFineGreaterThanAndCanali_givenNullDate_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByDataFineGreaterThanAndCanali(null, Collections.singletonList(canale));
    }


    @Test
    public void findByDataFineGreaterThanAndCanali_givenNullCanali_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        final Date date = new GregorianCalendar(2023, Calendar.JULY, 15).getTime();
        dao.findByDataFineGreaterThanAndCanali(date, null);
    }

    @Test
    public void findByDataFineGreaterThanAndCanali_givenEmptyCanali_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        final Date date = new GregorianCalendar(2023, Calendar.JULY, 15).getTime();
        dao.findByDataFineGreaterThanAndCanali(date, Collections.emptyList());
    }

    @Test
    public void findByDataFineGreaterThanAndCanali() {
        final Date date = new GregorianCalendar(2023, Calendar.JULY, 15).getTime();
        final List<PianoMediaPromoDbpromoEntity> entities = dao.findByDataFineGreaterThanAndCanali(date,
                Collections.singletonList(canale));
        assertEquals(1, entities.size());
        assertEquals("PR_001", entities.get(0).getCodicePromozione());
        assertEquals("PROMOZIONE NUMERO 001", entities.get(0).getDescrizioneEstesa());
    }

    @Test
    public void findByDataFineGreaterThan() {
        final Date date = new GregorianCalendar(2024, Calendar.OCTOBER, 1).getTime();
        final List<PianoMediaPromoDbpromoEntity> result = dao.findByDataFineGreaterThan(date);
        assertEquals(1, result.size());
        assertEquals("PR_003", result.get(0).getCodicePromozione());
        assertEquals("PROMOZIONE NUMERO 003", result.get(0).getDescrizioneEstesa());
    }

    @Test
    public void findByDataFineGreaterThan_givenNullDate_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByDataFineGreaterThan(null);
    }

    @Test
    public void findByCodicePromo_givenNullCodicePromo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByCodicePromo(null);
    }

    @Test
    public void findByCodicePromo() {
        final PianoMediaPromoDbpromoEntity entity = dao.findByCodicePromo("PR_001");
        assertEquals("PR_001", entity.getCodicePromozione());
        assertEquals("PROMOZIONE NUMERO 001", entity.getDescrizioneEstesa());
    }

    @Test
    public void findByCodicePromo_whenNoResult_shouldReturnNull() {
        assertNull(dao.findByCodicePromo("PR_005"));
    }

    @Test
    public void findAllByCodiciPromo_givenNullCodiciPromo_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByCodiciPromo(null);
    }

    @Test
    public void findAllByCodiciPromo_givenEmptyCodiciPromo_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByCodiciPromo(Collections.emptyList());
    }

    @Test
    public void findAllByCodiciPromo() {
        final List<String> codicePromo = Stream.of("PR_001", "PR_003").collect(Collectors.toList());
        final List<PianoMediaPromoDbpromoEntity> result = dao.findAllByCodiciPromo(codicePromo);
        assertEquals(2, result.size());
    }

    private PianoMediaPromoDbpromoEntity createEntity(String codicePromo, String descrizione, Date dataFine,
                                                      Long codiceCanale) {
        final PianoMediaPromoDbpromoEntity e = new PianoMediaPromoDbpromoEntity();
        e.setCodicePromozione(codicePromo);
        e.setDescrizioneEstesa(descrizione);
        e.setDataFine(dataFine);
        e.setCodiceCanale(codiceCanale);
        return e;
    }
}