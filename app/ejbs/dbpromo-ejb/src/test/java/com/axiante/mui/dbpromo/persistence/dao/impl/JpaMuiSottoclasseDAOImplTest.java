package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MuiSottoclasseDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSottoclasseEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JpaMuiSottoclasseDAOImplTest extends AbstractDaoTest {

    @Inject
    private MuiSottoclasseDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaMuiSottoclasseDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private MuiSottoclasseEntity sottoclasse1;
    private MuiSottoclasseEntity sottoclasse2;
    private MuiSottoclasseEntity sottoclasse3;

    @Before
    public void setUp() throws Exception {
        sottoclasse1 = createMuiSottoclasse("FOO");
        sottoclasse2 = createMuiSottoclasse("BAR");
        sottoclasse3 = createMuiSottoclasse("BAZ", true);
        persist(sottoclasse1, sottoclasse2, sottoclasse3);
    }

    @Test
    public void findById() {
        final MuiSottoclasseEntity result = dao.findById("FOO");
        assertEquals(sottoclasse1, result);
    }

    @Test
    public void findById_givenNullId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findById(null);
    }

    @Test
    public void findByCode() {
        final MuiSottoclasseEntity result = dao.findByCode("BAR");
        assertEquals(sottoclasse2, result);
    }

    @Test
    public void findAll() {
        assertEquals(3, dao.findAll().size());
    }

    @Test
    public void findAllAttive() {
        assertEquals(1, dao.findAllAttive().size());
        assertEquals(sottoclasse3, dao.findAllAttive().get(0));
    }

    @Test
    public void findActiveByCode_exists() {
        assertEquals(sottoclasse3, dao.findActiveByCode("BAZ"));
    }

    @Test
    public void findActiveByCode_notExists() {
        assertNull(dao.findActiveByCode("FOO"));
    }

    private MuiSottoclasseEntity createMuiSottoclasse(String sottoClasse) {
        return createMuiSottoclasse(sottoClasse, false);
    }

    private MuiSottoclasseEntity createMuiSottoclasse(String sottoClasse, boolean attiva) {
        final MuiSottoclasseEntity e = new MuiSottoclasseEntity();
        e.setSottoClasse(sottoClasse);
        e.setDescrizione("SOTTOCLASSE " + sottoClasse);
        e.setAttiva(attiva);
        return e;
    }
}