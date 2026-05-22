package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.AttributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneAttributiEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozioneStatoEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozioneTestataEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.StatoPromozioneEntityBuilder;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JpaPromozioneTestataDaoImplTest extends AbstractDaoTest {
    @Inject
    private PromozioneTestataDAO dao;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPromozioneTestataDaoImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    PromozioneTestataEntity promoRif;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01")
                .withDescrizione("GRUPPO 01").build();
        CanalePromozioneEntity canalePromozione = new CanalePromozioneEntityBuilder(1L).withCodice(1L)
                .withGruppo(gruppo).withDescrizione("CANALE 01").build();
        StatoPromozioneEntity statoPromozione = StatoPromozioneEntityBuilder.buildCreata(1L);
        PromozioneStatoEntity promozioneStatoRif = new PromozioneStatoEntityBuilder(666L)
                .withStatoPromozione(statoPromozione).build();
        promoRif = new PromozioneTestataEntityBuilder(666L).withCodice("PROMO RIF")
                .withCanale(canalePromozione).withAnno("2026").withPromozioneStato(promozioneStatoRif)
                .withDataInizio(new GregorianCalendar(2026, Calendar.APRIL, 1).getTime())
                .withDataFine(new GregorianCalendar(2026, Calendar.MAY, 31).getTime())
                .build();
        PromozioneStatoEntity promozioneStato1 = new PromozioneStatoEntityBuilder(1L)
                .withStatoPromozione(statoPromozione).build();
        PromozioneTestataEntity promo1 = new PromozioneTestataEntityBuilder(1L).withCodice("PROMO 01")
                .withCanale(canalePromozione).withAnno("2026").withPromozioneStato(promozioneStato1)
                .withDataInizio(new GregorianCalendar(2026, Calendar.MAY, 1).getTime())
                .withDataFine(new GregorianCalendar(2026, Calendar.MAY, 31).getTime())
                .build();
        PromozioneStatoEntity promozioneStato2 = new PromozioneStatoEntityBuilder(2L)
                .withStatoPromozione(statoPromozione).build();
        PromozioneTestataEntity promo2 = new PromozioneTestataEntityBuilder(2L).withCodice("PROMO 02")
                .withCanale(canalePromozione).withAnno("2026").withPromozioneStato(promozioneStato2)
                .withDataInizio(new GregorianCalendar(2026, Calendar.MAY, 15).getTime())
                .withDataFine(new GregorianCalendar(2026, Calendar.JUNE, 15).getTime())
                .build();
        PromozioneStatoEntity promozioneStato3 = new PromozioneStatoEntityBuilder(3L)
                .withStatoPromozione(statoPromozione).build();
        PromozioneTestataEntity promo3 = new PromozioneTestataEntityBuilder(3L).withCodice("PROMO 03")
                .withCanale(canalePromozione).withAnno("2026").withPromozioneStato(promozioneStato3)
                .withDataInizio(new GregorianCalendar(2026, Calendar.MAY, 15).getTime())
                .withDataFine(new GregorianCalendar(2026, Calendar.MAY, 20).getTime())
                .build();
        promoRif.addPromozioneStatoEntity(promozioneStatoRif);
        promo1.addPromozioneStatoEntity(promozioneStato1);
        promo2.addPromozioneStatoEntity(promozioneStato2);
        promo3.addPromozioneStatoEntity(promozioneStato3);
        AttributiPromoEntity attributo1 = buildAttributo(1L, "FOO");
        AttributiPromoEntity attributo2 = buildAttributo(2L, "BAR");
        PromozioneAttributiEntity promoAttributo1 = buildPromoAttributo(1L, promo1, attributo1, "FOO");
        PromozioneAttributiEntity promoAttributo2 = buildPromoAttributo(2L, promo2, attributo2, "BAR");
        PromozioneAttributiEntity promoAttributo3 = buildPromoAttributo(3L, promo3, attributo1, "BAZ");
        promo1.addPromozioneAttributo(promoAttributo1);
        promo2.addPromozioneAttributo(promoAttributo2);
        promo3.addPromozioneAttributo(promoAttributo3);
        persist(gruppo, canalePromozione, statoPromozione, promozioneStatoRif, promozioneStato1, promozioneStato2, promozioneStato3,
                promoRif, promo1, promo2, promo3, attributo1, attributo2, promoAttributo1, promoAttributo2, promoAttributo3);
    }

    @Test
    public void findOverlappingPromoWithAttributo_givenNullPromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findOverlappingPromoWithAttributo(null, 1L, "FOO");
    }

    @Test
    public void findOverlappingPromoWithAttributo_givenNullIdAttributo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findOverlappingPromoWithAttributo(promoRif, null, "FOO");
    }

    @Test
    public void findOverlappingPromoWithAttributo_givenNullValoreAttributo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findOverlappingPromoWithAttributo(promoRif, 1L, null);
    }

    @Test
    public void findOverlappingPromoWithAttributo() {
        List<PromozioneTestataEntity> result = dao.findOverlappingPromoWithAttributo(promoRif, 1L, "FOO");
        assertEquals(1, result.size());
        assertEquals("PROMO 01", result.get(0).getCodicePromozione());
        assertEquals(1L, (long) result.get(0).getId());
        result = dao.findOverlappingPromoWithAttributo(promoRif, 10L, "BAR");
        assertTrue(result.isEmpty());
    }

    private AttributiPromoEntity buildAttributo(Long id, String parametro) {
        AttributiPromoEntity e = new AttributiPromoEntity();
        e.setId(id);
        e.setParametro(parametro);
        e.setDescrizioneParametro(parametro);
        return e;
    }

    private PromozioneAttributiEntity buildPromoAttributo(Long id, PromozioneTestataEntity promozione,
                                                          AttributiPromoEntity attributo, String valore) {
        PromozioneAttributiEntity e = new PromozioneAttributiEntity();
        e.setId(id);
        e.setPromozioneTestataEntity(promozione);
        e.setAttributo(attributo);
        e.setValore(valore);
        return e;
    }
}