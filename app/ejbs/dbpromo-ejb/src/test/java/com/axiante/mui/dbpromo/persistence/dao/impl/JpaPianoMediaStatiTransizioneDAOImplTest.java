package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaStatiTransizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaPianoMediaStatiTransizioneDAOImplTest extends AbstractDaoTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, DbPromo.class,
                    JpaPianoMediaStatiTransizioneDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Inject
    private PianoMediaStatiTransizioneDAO dao;

    private PianoMediaEntity pianoMedia1;
    private PianoMediaEntity pianoMedia2;
    private PianoMediaEntity pianoMedia3;
    private StatoPromozioneEntity statoPromozione1;
    private StatoPromozioneEntity statoPromozione2;
    private StatoPromozioneEntity statoPromozione3;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppoPromo = createGruppoPromo("G-01", "GRUPPO 01");
        CanalePromozioneEntity canalePromo = createCanalePromo(42L, "CANALE 42", gruppoPromo);
        statoPromozione1 = createStatoPromozione(1L, "10");
        statoPromozione2 = createStatoPromozione(2L, "20");
        statoPromozione3 = createStatoPromozione(3L, "30");
        pianoMedia1 = createPianoMedia(1L, 2024, canalePromo);
        pianoMedia2 = createPianoMedia(2L, 2024, canalePromo);
        pianoMedia3 = createPianoMedia(3L, 2024, canalePromo);
        PianoMediaStatiTransizioneEntity pianoMediaStato1 = createPianoMediaStato(1L, pianoMedia1,
                statoPromozione1, statoPromozione2);
        PianoMediaStatiTransizioneEntity pianoMediaStato2 = createPianoMediaStato(2L, pianoMedia2,
                statoPromozione1, statoPromozione3, Boolean.TRUE);
        PianoMediaStatiTransizioneEntity pianoMediaStato3 = createPianoMediaStato(3L, pianoMedia3,
                statoPromozione1, statoPromozione3, Boolean.TRUE);
        PianoMediaStatiTransizioneEntity pianoMediaStato4 = createPianoMediaStato(4L, pianoMedia3,
                statoPromozione1, statoPromozione3, Boolean.TRUE);
        openTransaction();
        getEm().persist(gruppoPromo);
        getEm().persist(canalePromo);
        getEm().persist(statoPromozione1);
        getEm().persist(statoPromozione2);
        getEm().persist(statoPromozione3);
        getEm().persist(pianoMedia1);
        getEm().persist(pianoMedia2);
        getEm().persist(pianoMedia3);
        getEm().persist(pianoMediaStato1);
        getEm().persist(pianoMediaStato2);
        getEm().persist(pianoMediaStato3);
        getEm().persist(pianoMediaStato4);
        commitTransaction();
    }

    @Test
    public void findByIdAndStatus() {
        List<PianoMediaStatiTransizioneEntity> result = dao.findByIdAndStatus(1L, 1L);
        assertEquals(1, result.size());
        PianoMediaStatiTransizioneEntity entity = result.get(0);
        assertEquals("10", entity.getStatoPromozione().getCodiceStato());
        assertEquals("20", entity.getStatoTransizione().getCodiceStato());
    }

    @Test
    public void findAllNotAutomaticByIdAndStatus() {
        List<PianoMediaStatiTransizioneEntity> result = dao.findAllNotAutomaticByIdAndStatus(1L, 1L);
        assertEquals(1, result.size());
        PianoMediaStatiTransizioneEntity entity = result.get(0);
        assertEquals("10", entity.getStatoPromozione().getCodiceStato());
        assertEquals("20", entity.getStatoTransizione().getCodiceStato());
        result = dao.findAllNotAutomaticByIdAndStatus(2L, 1L);
        assertTrue(result.isEmpty());
    }

    @Test
    public void findAllAutomaticByIdAndStatus() {
        List<PianoMediaStatiTransizioneEntity> result = dao.findAllAutomaticByIdAndStatus(1L, 1L);
        assertTrue(result.isEmpty());
        result = dao.findAllAutomaticByIdAndStatus(2L, 1L);
        assertEquals(1, result.size());
        PianoMediaStatiTransizioneEntity entity = result.get(0);
        assertEquals("10", entity.getStatoPromozione().getCodiceStato());
        assertEquals("30", entity.getStatoTransizione().getCodiceStato());
    }

    @Test
    public void findByPianoAndStatuses_givenNullPianoMedia_shouldThrownException() {
        ex.expect(NullPointerException.class);
        dao.findByPianoAndStatuses(null, statoPromozione1, statoPromozione2);
    }

    @Test
    public void findByPianoAndStatuses_givenNullFromStato_shouldThrownException() {
        ex.expect(NullPointerException.class);
        dao.findByPianoAndStatuses(pianoMedia1, null, statoPromozione3);
    }

    @Test
    public void findByPianoAndStatuses_givenNullToStato_shouldThrownException() {
        ex.expect(NullPointerException.class);
        dao.findByPianoAndStatuses(pianoMedia2, statoPromozione1, null);
    }

    @Test
    public void findByPianoAndStatuses_whenCountMoreThanOne_shouldReturnNull() {
        PianoMediaStatiTransizioneEntity result = dao.findByPianoAndStatuses(pianoMedia3, statoPromozione1, statoPromozione3);
        assertNull(result);
    }

    @Test
    public void findByPianoAndStatuses() {
        PianoMediaStatiTransizioneEntity result = dao.findByPianoAndStatuses(pianoMedia1, statoPromozione1, statoPromozione2);
        assertEquals(1L, (long) result.getId());
        result = dao.findByPianoAndStatuses(pianoMedia1, statoPromozione1, statoPromozione3);
        assertNull(result);
    }

    @Test
    public void findByPianoAndStatusesAndFlagAutomatico_whenCountMoreThanOne_shouldReturnNull() {
        PianoMediaStatiTransizioneEntity result = dao.findByPianoAndStatusesAndFlagAutomatico(pianoMedia3,
                statoPromozione1, statoPromozione3, Boolean.TRUE);
        assertNull(result);
    }

    @Test
    public void findByPianoAndStatusesAndFlagAutomatico() {
        PianoMediaStatiTransizioneEntity result = dao.findByPianoAndStatusesAndFlagAutomatico(pianoMedia1,
                statoPromozione1, statoPromozione2, Boolean.FALSE);
        assertEquals(1L, (long) result.getId());
        result = dao.findByPianoAndStatusesAndFlagAutomatico(pianoMedia1, statoPromozione1, statoPromozione3,
                Boolean.FALSE);
        assertNull(result);
    }

    private PianoMediaStatiTransizioneEntity createPianoMediaStato(Long id, PianoMediaEntity pianoMedia,
                                                                   StatoPromozioneEntity statoPromozione,
                                                                   StatoPromozioneEntity statoTransizione) {
        return createPianoMediaStato(id, pianoMedia, statoPromozione, statoTransizione, Boolean.FALSE);
    }

    private PianoMediaStatiTransizioneEntity createPianoMediaStato(Long id, PianoMediaEntity pianoMedia,
                                                                   StatoPromozioneEntity statoPromozione,
                                                                   StatoPromozioneEntity statoTransizione,
                                                                   Boolean flagAutomatico) {
        PianoMediaStatiTransizioneEntity e = new PianoMediaStatiTransizioneEntity();
        e.setId(id);
        e.setPianoMediaEntity(pianoMedia);
        e.setStatoPromozione(statoPromozione);
        e.setStatoTransizione(statoTransizione);
        e.setStatoErrore(statoTransizione);
        e.setFlagAutomatico(flagAutomatico);
        return e;
    }

    private PianoMediaEntity createPianoMedia(Long id, Integer anno, CanalePromozioneEntity canale) {
        PianoMediaEntity e = new PianoMediaEntity();
        e.setId(id);
        e.setAnno(anno);
        e.setCanale(canale);
        return e;
    }

    private StatoPromozioneEntity createStatoPromozione(Long id, String codice) {
        StatoPromozioneEntity e = new StatoPromozioneEntity();
        e.setId(id);
        e.setCodiceStato(codice);
        e.setDescrizione(String.format("STATO %s", codice));
        return e;
    }

    private CanalePromozioneEntity createCanalePromo(Long codice, String descrizione, GruppoPromozioneEntity gruppo) {
        CanalePromozioneEntity e = new CanalePromozioneEntity();
        e.setCodiceCanale(codice);
        e.setDescrizione(descrizione);
        e.setGruppoPromozioneEntity(gruppo);
        return e;
    }

    private GruppoPromozioneEntity createGruppoPromo(String codice, String descrizione) {
        final GruppoPromozioneEntity e = new GruppoPromozioneEntity();
        e.setCodiceGruppo(codice);
        e.setDescrizione(descrizione);
        return e;
    }
}