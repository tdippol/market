package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleRepartoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleReparto;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.RepartoEntityBuilder;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaCfgCanaleRepartoDAOImplTest extends AbstractDaoTest {

    @Inject
    private CfgCanaleRepartoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgCanaleRepartoDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CanalePromozioneEntity canale;
    private RepartoEntity reparto;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder().withId(1L).withCodice("G01").build();
        canale = new CanalePromozioneEntityBuilder().withId(1L).withCodice(1L).withGruppo(gruppo).build();
        CanalePromozioneEntity canale2 = new CanalePromozioneEntityBuilder().withId(2L).withCodice(2L).withGruppo(gruppo).build();
        reparto = new RepartoEntityBuilder().withId(1L).withCodice("R1").build();
        RepartoEntity reparto2 = new RepartoEntityBuilder().withId(2L).withCodice("R2").build();
        CfgCanaleReparto canaleReparto1 = createCanaleReparto(1L, canale, reparto);
        CfgCanaleReparto canaleReparto2 = createCanaleReparto(2L, canale2, reparto2);
        persist(gruppo, canale, canale2, reparto, reparto2, canaleReparto1, canaleReparto2);
    }

    @Test
    public void findByCanale() {
        final List<CfgCanaleReparto> result = dao.findByCanale(canale);
        assertEquals(1, result.size());
        assertEquals(1L, (long) result.get(0).getId());
        assertEquals(canale, result.get(0).getCanale());
        assertEquals(reparto, result.get(0).getReparto());
    }

    @Test
    public void findByCanaleAndReparto() {
        final CfgCanaleReparto result = dao.findByCanaleAndReparto(canale, reparto);
        assertNotNull(result);
        assertEquals(1L, (long) result.getId());
        assertEquals(canale, result.getCanale());
        assertEquals(reparto, result.getReparto());
    }

    private CfgCanaleReparto createCanaleReparto(Long id, CanalePromozioneEntity canale, RepartoEntity reparto) {
        final CfgCanaleReparto e = new CfgCanaleReparto();
        e.setId(id);
        e.setCanale(canale);
        e.setReparto(reparto);
        return e;
    }
}