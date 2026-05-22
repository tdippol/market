package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgParametriDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgParametriEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaCfgParametriEntityDAOImplTest extends AbstractDaoTest {
    @Inject
    private CfgParametriDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaCfgParametriDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void shouldFindAll() {
        List<CfgParametriEntity> cfgParametriEntities = dao.findAll();
        assertNotNull(cfgParametriEntities);
        assertTrue(cfgParametriEntities.isEmpty());
    }

    @Test
    public void findEmptyUuid_whenEntityIsUuidEnabledWithUuidSetted_shouldReturnEmptyList() {
        final CfgParametriEntity cfg1 = createCfgParametriEntity("FOO", "FooDesc", "FooValue");
        final CfgParametriEntity cfg2 = createCfgParametriEntity("BAR", "BarDesc", "BarValue");
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
            em.persist(cfg1);
            em.persist(cfg2);
            em.getTransaction().commit();
        }
        assertTrue(dao.findEmptyUuid().isEmpty());
    }

    private CfgParametriEntity createCfgParametriEntity(String codice, String descrizione, String valore) {
        final CfgParametriEntity entity = new CfgParametriEntity();
        entity.setCodiceParametro(codice);
        entity.setDescrizione(descrizione);
        entity.setValore(valore);
        return entity;
    }
}
