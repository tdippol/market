package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.SupportoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaSupportoMediaDAOImplTest extends AbstractDaoTest {

    @Inject
    private SupportoMediaDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaSupportoMediaDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class)
            .inject(this)
            .build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        SupportoMediaEntity e = new SupportoMediaEntity();
        e.setId(42L);
        e.setCodiceMedia("ABC");
        e.setDescrizione("SUPPORTO ABC");
        openTransaction();
        dao.persist(e);
        commitTransaction();
    }

    @Test
    public void findByCode_givenNullCodice_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByCode(null);
    }

    @Test
    public void findByCode_givenExsistingCodice_shouldReturnEntity() {
        final SupportoMediaEntity entity = dao.findByCode("ABC");
        assertEquals(42L, (long) entity.getId());
        assertEquals("ABC", entity.getCodiceMedia());
        assertEquals("SUPPORTO ABC", entity.getDescrizione());
    }

    @Test
    public void findByCode_givenMoreThanOneEntitiesWithExsistingCodice_shouldThrowException() {
        SupportoMediaEntity e = new SupportoMediaEntity();
        e.setId(23L);
        e.setCodiceMedia("ABC");
        e.setDescrizione("ALTRO SUPPORTO ABC");
        openTransaction();
        dao.persist(e);
        commitTransaction();
        ex.expect(IllegalArgumentException.class);
        dao.findByCode("ABC");
    }

    @Test
    public void findByCode_givenNonExsistingCodice_shouldReturnNull() {
        assertNull(dao.findByCode("XXX"));
    }
}