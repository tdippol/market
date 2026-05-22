package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
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

public class JpaPianoMediaDAOImplTest extends AbstractDaoTest {
    @Inject
    private PianoMediaDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPianoMediaDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private Date dataInizioRif = new GregorianCalendar(2024, Calendar.SEPTEMBER, 15).getTime();
    private Date dataFineRif = new GregorianCalendar(2024, Calendar.NOVEMBER, 15).getTime();
    private PianoMediaEntity pianoMedia1;
    private PianoMediaEntity pianoMedia2;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder(1L).withCodice(1L).withGruppo(gruppo).build();
        PianoMediaStatoEntity pianoMediaStato1 = createPianoMediaStato(1L, "10");
        PianoMediaStatoEntity pianoMediaStato2 = createPianoMediaStato(2L, "20");

        pianoMedia1 = createPianoMedia(1L, new GregorianCalendar(2024, Calendar.OCTOBER, 1).getTime(),
                new GregorianCalendar(2024, Calendar.OCTOBER, 15).getTime(), 2024, canale);
        pianoMedia1.addPianoMediaStato(pianoMediaStato1);
        pianoMedia2 = createPianoMedia(2L, new GregorianCalendar(2024, Calendar.OCTOBER, 16).getTime(),
                new GregorianCalendar(2024, Calendar.OCTOBER, 30).getTime(), 2024, canale);
        pianoMedia2.addPianoMediaStato(pianoMediaStato2);
        persist(gruppo, canale, pianoMediaStato1, pianoMediaStato2, pianoMedia1, pianoMedia2);
    }

    @Test
    public void findByDataInizioAndDataFine() {
        final List<PianoMediaEntity> result = dao.findByDataInizioAndDataFine(dataInizioRif, dataFineRif);
        assertEquals(2, result.size());
        assertTrue(result.contains(pianoMedia1));
        assertTrue(result.contains(pianoMedia2));
    }

    @Test
    public void findByDataInizioAndDataFine_givenNullDataInizio_shouldThrownException() {
        ex.expect(NullPointerException.class);
        dao.findByDataInizioAndDataFine(null, new Date());
    }

    @Test
    public void findByDataInizioAndDataFine_givenNullDataFine_shouldThrownException() {
        ex.expect(NullPointerException.class);
        dao.findByDataInizioAndDataFine(new Date(), null);
    }

    @Test
    public void findNonPubblicatiByDataInizioAndDataFine() {
        final List<PianoMediaEntity> result = dao.findNonPubblicatiByDataInizioAndDataFine(dataInizioRif, dataFineRif);
        assertEquals(1, result.size());
        assertTrue(result.contains(pianoMedia1));
        assertFalse(result.contains(pianoMedia2));
    }

    @Test
    public void findNonPubblicatiByDataInizioAndDataFine_givenNullDataInizio_shouldThrownException() {
        ex.expect(NullPointerException.class);
        dao.findNonPubblicatiByDataInizioAndDataFine(null, new Date());
    }

    @Test
    public void findNonPubblicatiByDataInizioAndDataFine_givenNullDataFine_shouldThrownException() {
        ex.expect(NullPointerException.class);
        dao.findNonPubblicatiByDataInizioAndDataFine(new Date(), null);
    }

    @Test
    public void findOpenPianiMedia() {
        final List<PianoMediaEntity> result = dao.findOpenPianiMedia();
        assertEquals(2, result.size());
        assertTrue(result.contains(pianoMedia1));
        assertTrue(result.contains(pianoMedia2));
    }

    @Test
    public void findNonPubblicatiPianiMedia() {
        final List<PianoMediaEntity> result = dao.findNonPubblicatiPianiMedia();
        assertEquals(1, result.size());
        assertTrue(result.contains(pianoMedia1));
        assertFalse(result.contains(pianoMedia2));
    }

    @Test
    public void findNotCancelled() {
        final List<PianoMediaEntity> result = dao.findNotCancelled();
        assertEquals(2, result.size());
        assertTrue(result.contains(pianoMedia1));
        assertTrue(result.contains(pianoMedia2));
    }

    @Test
    public void findOpenAvailableYears() {
        final List<Integer> result = dao.findOpenAvailableYears();
        assertEquals(1, result.size());
        assertTrue(result.contains(2024));
    }

    @Test
    public void findPubblicatiByDataInizio() {
        final List<PianoMediaEntity> result = dao.findPubblicatiByDataInizio(dataInizioRif);
        assertTrue(result.isEmpty());
    }

    @Test
    public void findPubblicatiByDataInizio_givenNullDataInizio_shouldThrownException() {
        ex.expect(NullPointerException.class);
        dao.findPubblicatiByDataInizio(null);
    }

    @Test
    public void findOpenByDataFine() {
        final List<PianoMediaEntity> result = dao.findOpenByDataFine(dataFineRif);
        assertEquals(2, result.size());
        assertTrue(result.contains(pianoMedia1));
        assertTrue(result.contains(pianoMedia2));
    }

    @Test
    public void findOpenByDataFine_givenNullDataFine_shouldThrownException() {
        ex.expect(NullPointerException.class);
        dao.findOpenByDataFine(null);
    }

    @Test
    public void findPianiMediaAccessibiliInPianificazione() {
        final List<PianoMediaEntity> result = dao.findPianiMediaAccessibiliInPianificazione();
        assertEquals(1, result.size());
        assertFalse(result.contains(pianoMedia1));
        assertTrue(result.contains(pianoMedia2));
    }

    private PianoMediaEntity createPianoMedia(Long id, Date dataInizio, Date dataFine, Integer anno,
                                              CanalePromozioneEntity canale) {
        PianoMediaEntity e = new PianoMediaEntity();
        e.setId(id);
        e.setDataInizio(dataInizio);
        e.setDataFine(dataFine);
        e.setAnno(anno);
        e.setCanale(canale);
        e.setUuid(AxUUID.randomUUID().toString());
        return e;
    }

    private PianoMediaStatoEntity createPianoMediaStato(Long id, String codiceStato) {
        final StatoPromozioneEntity stato = new StatoPromozioneEntity();
        stato.setId(id);
        stato.setCodiceStato(codiceStato);
        stato.setDescrizione("STATO " + codiceStato);
        final PianoMediaStatoEntity e = new PianoMediaStatoEntity();
        e.setId(id);
        e.setStato(stato);
        return e;
    }
}