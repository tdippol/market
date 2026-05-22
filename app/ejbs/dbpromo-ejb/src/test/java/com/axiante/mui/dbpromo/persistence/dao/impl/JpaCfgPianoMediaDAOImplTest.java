package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaCfgPianoMediaDAOImplTest extends AbstractDaoTest {
    @Inject
    private CfgPianoMediaDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
                    EntityManagerFactoryProducer.class, JpaCfgPianoMediaDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        final SupportoMediaEntity supportoMedia1 = createSupportoMediaEntity(1L, "S01", Boolean.TRUE);
        final SupportoMediaEntity supportoMedia2 = createSupportoMediaEntity(2L, "S02", Boolean.FALSE);
        final CfgPianoMediaEntity cfgPianoMedia1 = createCfgPianoMediaEntity(1L, supportoMedia1);
        final CfgPianoMediaEntity cfgPianoMedia2 = createCfgPianoMediaEntity(2L, supportoMedia2);
        persist(supportoMedia1, supportoMedia2, cfgPianoMedia1, cfgPianoMedia2);
    }

    @Test
    public void findAllAttivi() {
        final List<CfgPianoMediaEntity> result = dao.findAllAttivi();
        assertEquals(1, result.size());
        assertEquals(1L, (long) result.get(0).getId());
        assertEquals("S01", result.get(0).getSupportoMedia().getCodiceMedia());
    }

    private CfgPianoMediaEntity createCfgPianoMediaEntity(Long id, SupportoMediaEntity supportoMedia) {
        final CfgPianoMediaEntity entity = new CfgPianoMediaEntity();
        entity.setId(id);
        entity.setSupportoMedia(supportoMedia);
        return entity;
    }

    private SupportoMediaEntity createSupportoMediaEntity(Long id, String codice, Boolean attivo) {
        final SupportoMediaEntity entity = new SupportoMediaEntity();
        entity.setId(id);
        entity.setCodiceMedia(codice);
        entity.setAttivo(attivo);
        return entity;
    }
}