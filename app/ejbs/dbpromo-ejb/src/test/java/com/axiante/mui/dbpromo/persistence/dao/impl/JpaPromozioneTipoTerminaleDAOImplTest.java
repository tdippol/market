package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertNotNull;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneTipoTerminaleDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTipoTerminaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoTerminaleCassaEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaPromozioneTipoTerminaleDAOImplTest extends AbstractDaoTest {
    @Inject
    private PromozioneTipoTerminaleDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPromozioneTipoTerminaleDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private PromozioneTestataEntity testata;
    private TipoTerminaleCassaEntity cassa;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder().withId(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder().withId(1L).withCodice(1L).withGruppo(gruppo)
                .build();
        testata = createPromozioneTestataEntity(1L, "PROMO 001", canale);
        PromozioneTestataEntity testata2 = createPromozioneTestataEntity(2L, "PROMO 002", canale);
        cassa = createTipoTerminaleCassaEntity(1L, 42);
        PromozioneTipoTerminaleEntity promoTipoTerminale1 = createPromoTipoTerminaleEntity(testata, cassa);
        PromozioneTipoTerminaleEntity promoTipoTerminale2 = createPromoTipoTerminaleEntity(testata2, cassa);
        persist(gruppo, canale, testata, cassa, promoTipoTerminale1, promoTipoTerminale2);
    }

    @Test
    public void findByTestataAndCassa() {
        final PromozioneTipoTerminaleEntity result = dao.findByTestataAndCassa(testata, cassa);
        assertNotNull(result);
    }

    private PromozioneTipoTerminaleEntity createPromoTipoTerminaleEntity(PromozioneTestataEntity testata,
                                                                         TipoTerminaleCassaEntity cassa) {
        PromozioneTipoTerminaleEntity e = new PromozioneTipoTerminaleEntity();
        e.setPromozioneTestataEntity(testata);
        e.setTipoTerminaleCassaEntity(cassa);
        e.setDataAggiornamento(new Date());
        e.setDataInserimento(new Date());
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

    private TipoTerminaleCassaEntity createTipoTerminaleCassaEntity(Long id, Integer tipoTerminale) {
        TipoTerminaleCassaEntity e = new TipoTerminaleCassaEntity();
        e.setId(id);
        e.setTipoTerminale(tipoTerminale);
        return e;
    }
}