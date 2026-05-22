package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PianificazionePianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JpaPianificazionePianoMediaDAOImplTest extends AbstractDaoTest {

    @Inject
    private PianificazionePianoMediaDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPianificazionePianoMediaDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private PianoMediaEntity pianoMedia;
    private PianificazionePianoMediaEntity ppm1;
    private PianificazionePianoMediaEntity ppm2;
    private PianificazionePianoMediaEntity ppm3;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder(1L).withCodice(1L).withGruppo(gruppo).build();
        SupportoMediaEntity supportoMedia1 = createSupportoMedia(1L, Boolean.TRUE);
        SupportoMediaEntity supportoMedia2 = createSupportoMedia(2L, Boolean.FALSE);
        pianoMedia = createPianoMedia(1L, canale, Calendar.getInstance().get(Calendar.YEAR));
        ppm1 = createPianificazionePiano(1L, supportoMedia1, pianoMedia);
        ppm2 = createPianificazionePiano(2L, supportoMedia2, pianoMedia);
        ppm3 = createPianificazionePiano(3L, supportoMedia1, pianoMedia);
        persist(gruppo, canale, supportoMedia1, supportoMedia2, pianoMedia, ppm1, ppm2, ppm3);
    }

    @Test
    public void remove() {
        Set<PianificazionePianoMediaEntity> toBeRemoved = new HashSet<>();
        toBeRemoved.add(ppm1);
        toBeRemoved.add(ppm2);
        openTransaction();
        dao.remove(toBeRemoved, 1);
        commitTransaction();
        final List<PianificazionePianoMediaEntity> result = dao.findAll();
        assertEquals(1, result.size());
        assertFalse(result.contains(ppm1));
        assertFalse(result.contains(ppm2));
        assertTrue(result.contains(ppm3));
    }

    @Test
    public void remove_default() {
        Set<PianificazionePianoMediaEntity> toBeRemoved = new HashSet<>();
        toBeRemoved.add(ppm1);
        toBeRemoved.add(ppm2);
        openTransaction();
        dao.remove(toBeRemoved);
        commitTransaction();
        final List<PianificazionePianoMediaEntity> result = dao.findAll();
        assertEquals(1, result.size());
        assertFalse(result.contains(ppm1));
        assertFalse(result.contains(ppm2));
        assertTrue(result.contains(ppm3));
    }

    @Test
    public void remove_givenNullSet_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.remove(null, 1);
    }

    @Test
    public void findByPianoMedia_givenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByPianoMedia(null);
    }

    @Test
    public void findByPianoMedia() {
        final List<PianificazionePianoMediaEntity> result = dao.findByPianoMedia(pianoMedia);
        assertEquals(3, result.size());
        assertTrue(result.contains(ppm1));
        assertTrue(result.contains(ppm2));
        assertTrue(result.contains(ppm3));
    }

    @Test
    public void findAttiviByPianoMedia() {
        final List<PianificazionePianoMediaEntity> result = dao.findAttiviByPianoMedia(pianoMedia);
        assertEquals(2, result.size());
        assertTrue(result.contains(ppm1));
        assertFalse(result.contains(ppm2));
        assertTrue(result.contains(ppm3));
    }

    private PianoMediaEntity createPianoMedia(Long id, CanalePromozioneEntity canale, Integer anno) {
        final PianoMediaEntity e = new PianoMediaEntity();
        e.setId(id);
        e.setCanale(canale);
        e.setAnno(anno);
        return e;
    }

    private PianificazionePianoMediaEntity createPianificazionePiano(Long id, SupportoMediaEntity supportoMedia,
                                                                     PianoMediaEntity pianoMedia) {
        final PianificazionePianoMediaEntity e = new PianificazionePianoMediaEntity();
        e.setId(id);
        e.setSupportoMedia(supportoMedia);
        e.setPianoMedia(pianoMedia);
        return e;
    }

    private SupportoMediaEntity createSupportoMedia(Long id, Boolean attivo) {
        final SupportoMediaEntity e = new SupportoMediaEntity();
        e.setId(id);
        e.setAttivo(attivo);
        return e;
    }
}