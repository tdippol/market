package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPianificazioneDettaglioDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaPianoMediaPianificazioneDettaglioDAOImplTest extends AbstractDaoTest {
    @Inject
    private PianoMediaPianificazioneDettaglioDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPianoMediaPianificazioneDettaglioDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private PianoMediaEntity pianoMedia;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder().withId(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder().withId(1L).withCodice(1L).withGruppo(gruppo)
                .build();
        pianoMedia = createPianoMediaEntity(1L, canale, 2024);
        final PianoMediaEntity pianoMedia2 = createPianoMediaEntity(2L, canale, 2024);
        PianoMediaPianificazioneDettaglioEntity dettaglio1 = createPianoMediaDettaglioEntity(pianoMedia,
                "PROMO 001", "I001", "AA");
        PianoMediaPianificazioneDettaglioEntity dettaglio2 = createPianoMediaDettaglioEntity(pianoMedia2,
                "PROMO 001", "I002", "BB");
        persist(gruppo, canale, pianoMedia, dettaglio1, dettaglio2);
    }

    @Test
    public void findByPianoMedia_givenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByPianoMedia(null);
    }

    @Test
    public void findByPianoMedia() {
        List<PianoMediaPianificazioneDettaglioEntity> result = dao.findByPianoMedia(pianoMedia);
        assertEquals(1, result.size());
    }

    private PianoMediaPianificazioneDettaglioEntity createPianoMediaDettaglioEntity(PianoMediaEntity pianoMedia,
                                                                                    String codicePromo,
                                                                                    String codiceItem,
                                                                                    String tipoItem) {
        PianoMediaPianificazioneDettaglioEntity e = new PianoMediaPianificazioneDettaglioEntity();
        e.setPianoMedia(pianoMedia);
        e.setCodicePromozione(codicePromo);
        e.setCodiceItem(codiceItem);
        e.setTipoItem(tipoItem);
        return e;
    }

    private PianoMediaEntity createPianoMediaEntity(Long id, CanalePromozioneEntity canale, Integer anno) {
        PianoMediaEntity e = new PianoMediaEntity();
        e.setId(id);
        e.setCanale(canale);
        e.setAnno(anno);
        return e;
    }
}