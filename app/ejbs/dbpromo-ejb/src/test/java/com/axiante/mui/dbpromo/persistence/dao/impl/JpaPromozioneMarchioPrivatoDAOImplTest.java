package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneMarchioPrivatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MarchioPrivatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMarchioPrivatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
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

public class JpaPromozioneMarchioPrivatoDAOImplTest extends AbstractDaoTest {
    @Inject
    private PromozioneMarchioPrivatoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPromozioneMarchioPrivatoDAOImpl.class)
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
        MarchioPrivatoEntity marchioPrivato = createMarchioPrivatoEntity(1L, "42");
        PromozioneMarchioPrivatoEntity promoMarchioPriv1 = createPromoMarchioPrivEntity(1L, testata1, marchioPrivato);
        PromozioneMarchioPrivatoEntity promoMarchioPriv2 = createPromoMarchioPrivEntity(2L, testata2, marchioPrivato);
        persist(gruppo, canale, testata1, testata2, promoMarchioPriv1, promoMarchioPriv2);
    }

    @Test
    public void findByIdPromozione() {
        final List<PromozioneMarchioPrivatoEntity> result = dao.findByIdPromozione(1L);
        assertEquals(1, result.size());
    }

    private PromozioneMarchioPrivatoEntity createPromoMarchioPrivEntity(Long id, PromozioneTestataEntity testata,
                                                                        MarchioPrivatoEntity marchioPrivato) {
        PromozioneMarchioPrivatoEntity e = new PromozioneMarchioPrivatoEntity();
        e.setId(id);
        e.setPromozione(testata);
        e.setMarchioPrivato(marchioPrivato);
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

    private MarchioPrivatoEntity createMarchioPrivatoEntity(Long id, String codice) {
        MarchioPrivatoEntity e = new MarchioPrivatoEntity();
        e.setId(id);
        e.setCodice(codice);
        return e;
    }
}