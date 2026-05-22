package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgAzioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaCfgAzioniDAOImplTest extends AbstractDaoTest {
    @Inject
    private CfgAzioniDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgAzioniDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        CfgAzioniEntity azione1 = createAzione(1L, "A001");
        CfgAzioniEntity azione2 = createAzione(2L, "A002");
        CfgAzioniEntity azione3 = createAzione(3L, "A002");
        persist(azione1, azione2, azione3);
    }

    @Test
    public void findByCodice() {
        final CfgAzioniEntity result = dao.findByCodice("A001");
        assertNotNull(result);
        assertEquals(1, (long) result.getId());
    }

    @Test
    public void findByCodice_givenNullCodice_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByCodice(null);
    }

    @Test
    public void findByCodice_whenNoResult_shouldThrowException() {
        ex.expect(NoResultException.class);
        dao.findByCodice("A003");
    }

    @Test
    public void findByCodice_whenManyResult_shouldThrowException() {
        ex.expect(NonUniqueResultException.class);
        dao.findByCodice("A002");
    }

    private CfgAzioniEntity createAzione(Long id, String codice) {
        CfgAzioniEntity e = new CfgAzioniEntity();
        e.setId(id);
        e.setCodice(codice);
        return e;
    }
}