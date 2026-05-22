package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PianificazioneAnagraficaPianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneAnagraficaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaPianificazioneAnagraficaPianoMediaDAOImplTest extends AbstractDaoTest {
    @Inject
    private PianificazioneAnagraficaPianoMediaDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPianificazioneAnagraficaPianoMediaDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private PianoMediaPianificazioneDettaglioEntity dettaglio1;
    private PianoMediaPianificazioneDettaglioEntity dettaglio2;
    private PianificazionePianoMediaEntity pianificazione1;
    private PianificazionePianoMediaEntity pianificazione2;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder().withId(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder().withId(1L).withCodice(1L).withGruppo(gruppo)
                .build();
        PianoMediaEntity pianoMedia = createPianoMedia(1L, canale, 2024);
        dettaglio1 = createPianificazioneDettaglio(1L, pianoMedia, "PROMO 001", "I001", "AA");
        dettaglio2 = createPianificazioneDettaglio(2L, pianoMedia,
                "PROMO 001", "I001", "BB");
        pianificazione1 = createPianificazioneMedia(1L);
        pianificazione2 = createPianificazioneMedia(2L);
        PianificazioneAnagraficaPianoMediaEntity anagrafica1 = createAnagraficaPianoMediaEntity(1L, dettaglio1, pianificazione1);
        PianificazioneAnagraficaPianoMediaEntity anagrafica2 = createAnagraficaPianoMediaEntity(2L, dettaglio2, pianificazione1);
        PianificazioneAnagraficaPianoMediaEntity anagrafica3 = createAnagraficaPianoMediaEntity(3L, dettaglio2, pianificazione2);
        PianificazioneAnagraficaPianoMediaEntity anagrafica4 = createAnagraficaPianoMediaEntity(4L, dettaglio2, pianificazione2);
        persist(gruppo, canale, pianoMedia, dettaglio1, dettaglio2, pianificazione1, pianificazione2, anagrafica1, anagrafica2,
                anagrafica3, anagrafica4);
    }

    @Test
    public void findByPianificazioneDettaglioAndPianificazioneMedia() {
        final PianificazioneAnagraficaPianoMediaEntity result = dao.findByPianificazioneDettaglioAndPianificazioneMedia(dettaglio1, pianificazione1);
        assertEquals(1L, (long) result.getId());
    }

    @Test
    public void findByPianificazioneDettaglioAndPianificazioneMedia_whenEntitiesFound_shouldThrowException() {
        ex.expect(IllegalStateException.class);
        dao.findByPianificazioneDettaglioAndPianificazioneMedia(dettaglio2, pianificazione2);
    }

    @Test
    public void findByPianificazioneDettaglioAndPianificazioneMedia_whenNoResult_shouldThrowException() {
        ex.expect(IllegalStateException.class);
        dao.findByPianificazioneDettaglioAndPianificazioneMedia(dettaglio1, pianificazione2);
    }

    private PianificazioneAnagraficaPianoMediaEntity createAnagraficaPianoMediaEntity(Long id, PianoMediaPianificazioneDettaglioEntity dettaglio,
                                                                                      PianificazionePianoMediaEntity pianificazione) {
        PianificazioneAnagraficaPianoMediaEntity e = new PianificazioneAnagraficaPianoMediaEntity();
        e.setId(id);
        e.setPianificazioneDettaglio(dettaglio);
        e.setPianificazioneMedia(pianificazione);
        return e;
    }

    private PianoMediaPianificazioneDettaglioEntity createPianificazioneDettaglio(Long id, PianoMediaEntity pianoMedia,
                                                                                  String codicePromo,
                                                                                  String codiceItem,
                                                                                  String tipoItem) {
        PianoMediaPianificazioneDettaglioEntity e = new PianoMediaPianificazioneDettaglioEntity();
        e.setId(id);
        e.setPianoMedia(pianoMedia);
        e.setCodicePromozione(codicePromo);
        e.setCodiceItem(codiceItem);
        e.setTipoItem(tipoItem);
        return e;
    }

    private PianificazionePianoMediaEntity createPianificazioneMedia(Long id) {
        PianificazionePianoMediaEntity e = new PianificazionePianoMediaEntity();
        e.setId(id);
        return e;
    }

    private PianoMediaEntity createPianoMedia(Long id, CanalePromozioneEntity canale, Integer anno) {
        PianoMediaEntity e = new PianoMediaEntity();
        e.setId(id);
        e.setCanale(canale);
        e.setAnno(anno);
        return e;
    }
}