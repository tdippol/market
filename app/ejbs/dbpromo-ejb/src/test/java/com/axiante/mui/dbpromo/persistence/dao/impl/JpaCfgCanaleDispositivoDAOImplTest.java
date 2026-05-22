package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleDispositivoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JpaCfgCanaleDispositivoDAOImplTest extends AbstractDaoTest {
    @Inject
    private CfgCanaleDispositivoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgCanaleDispositivoDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        CfgCanaleDispositivoEntity cfgCanaleDispositivo1 = createCfgCanaleDispositivo(1L, "D001");
        CfgCanaleDispositivoEntity cfgCanaleDispositivo2 = createCfgCanaleDispositivo(2L, "D002");
        CfgCanaleDispositivoEntity cfgCanaleDispositivo3 = createCfgCanaleDispositivo(3L, "D003");
        persist(cfgCanaleDispositivo1, cfgCanaleDispositivo2, cfgCanaleDispositivo3);
    }

    @Test
    public void findByCodice_givenNullCodice_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByCodice(null);
    }

    @Test
    public void findByCodice_whenNoResult_shouldThrowException() {
        ex.expect(NoResultException.class);
        dao.findByCodice("D042");
    }

    @Test
    public void findByCodice_whenNonUniqueResult_shouldThrowException() {
        CfgCanaleDispositivoEntity cfgCanaleDispositivo4 = createCfgCanaleDispositivo(4L, "D003");
        persist(cfgCanaleDispositivo4);
        ex.expect(NonUniqueResultException.class);
        dao.findByCodice("D003");
    }

    @Test
    public void findByCodice() {
        final CfgCanaleDispositivoEntity result = dao.findByCodice("D001");
        assertNotNull(result);
        assertEquals(1L, result.getId().longValue());
    }

    private CfgCanaleDispositivoEntity createCfgCanaleDispositivo(Long id, String codice) {
        final CfgCanaleDispositivoEntity entity = new CfgCanaleDispositivoEntity();
        entity.setId(id);
        entity.setCodice(codice);
        return entity;
    }
}