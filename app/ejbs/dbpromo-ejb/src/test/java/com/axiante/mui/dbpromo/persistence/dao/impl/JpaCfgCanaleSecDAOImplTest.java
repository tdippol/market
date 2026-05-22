package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleSecDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleSecEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaCfgCanaleSecDAOImplTest extends AbstractDaoTest {
    @Inject
    private CfgCanaleSecDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgCanaleSecDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CanalePromozioneEntity canale;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder().withId(1L).withCodice("G01").build();
        canale = new CanalePromozioneEntityBuilder().withId(1L).withCodice(1L).withGruppo(gruppo).build();
        CanalePromozioneEntity canale2 = new CanalePromozioneEntityBuilder().withId(2L).withCodice(2L).withGruppo(gruppo).build();
        final CfgCanaleSecEntity cfgCanaleSec1 = createCfgCanaleSecEntity(1L, canale);
        final CfgCanaleSecEntity cfgCanaleSec2 = createCfgCanaleSecEntity(2L, canale2);
        persist(gruppo, canale, cfgCanaleSec1, cfgCanaleSec2);
    }

    @Test
    public void findByCanale() {
        final CfgCanaleSecEntity result = dao.findByCanale(canale);
        assertNotNull(result);
        assertEquals(canale, result.getCanale());
    }

    @Test
    public void countByCanale() {
        assertEquals(1, (long) dao.countByCanale(canale));
    }

    @Test
    public void findAll() {
        assertEquals(2, dao.findAll().size());
    }

    private CfgCanaleSecEntity createCfgCanaleSecEntity(Long id, CanalePromozioneEntity canale) {
        final CfgCanaleSecEntity e = new CfgCanaleSecEntity();
        e.setId(id);
        e.setCanale(canale);
        return e;
    }
}