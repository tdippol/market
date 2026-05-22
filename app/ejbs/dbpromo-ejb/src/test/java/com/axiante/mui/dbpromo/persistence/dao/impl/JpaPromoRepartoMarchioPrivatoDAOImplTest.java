package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromoRepartoMarchioPrivatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoRepartoMarchioPrivato;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaPromoRepartoMarchioPrivatoDAOImplTest extends AbstractDaoTest {
    @Inject
    private PromoRepartoMarchioPrivatoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPromoRepartoMarchioPrivatoDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder().withId(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder().withId(1L).withCodice(1L).withGruppo(gruppo)
                .build();
        PromozioneTestataEntity testata1 = createPromozioneTestataEntity(1L, "PROMO 001", canale);
        PromozioneTestataEntity testata2 = createPromozioneTestataEntity(2L, "PROMO 002", canale);
        RepartoEntity reparto = createRepartoEntity(1L, "R1");
        PromoRepartoMarchioPrivato promoReparto1 = createPromoRepartoMarchioPrivatoEntity(1L, testata1, reparto);
        PromoRepartoMarchioPrivato promoReparto2 = createPromoRepartoMarchioPrivatoEntity(2L, testata2, reparto);
        persist(gruppo, canale, testata1, testata2, reparto, promoReparto1, promoReparto2);
    }

    @Test
    public void findByIdPromozione() {
        List<PromoRepartoMarchioPrivato> result = dao.findByIdPromozione(1L);
        assertEquals(1, result.size());
    }

    private PromoRepartoMarchioPrivato createPromoRepartoMarchioPrivatoEntity(Long id, PromozioneTestataEntity testata,
                                                                              RepartoEntity reparto) {
        PromoRepartoMarchioPrivato e = new PromoRepartoMarchioPrivato();
        e.setId(id);
        e.setPromozione(testata);
        e.setReparto(reparto);
        return e;
    }

    private PromozioneTestataEntity createPromozioneTestataEntity(Long id, String codice, CanalePromozioneEntity canale) {
        PromozioneTestataEntity e = new PromozioneTestataEntity();
        e.setId(id);
        e.setCodicePromozione(codice);
        e.setDataInizio(new Date());
        e.setDataFine(new Date());
        e.setMuiCanalePromozione(canale);
        return e;
    }

    private RepartoEntity createRepartoEntity(Long id, String codice) {
        RepartoEntity e = new RepartoEntity();
        e.setId(id);
        e.setCodiceReparto(codice);
        return e;
    }
}