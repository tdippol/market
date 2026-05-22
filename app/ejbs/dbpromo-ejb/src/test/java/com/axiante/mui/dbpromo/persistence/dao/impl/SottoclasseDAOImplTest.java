package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.SottoclasseDAO;
import com.axiante.mui.dbpromo.persistence.entities.SottoclasseEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SottoclasseDAOImplTest extends AbstractDaoTest {
    @Inject
    SottoclasseDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    SottoclasseDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        SottoclasseEntity entity1 = createEntity(1L, "SC1", "FOO", true);
        SottoclasseEntity entity2 = createEntity(2L, "SC2", "BAR", false);
        persist(entity1, entity2);
    }

    @Test
    public void findAll() {
        final List<SottoclasseEntity> all = dao.findAll();
        assertEquals(2, all.size());
        assertSottoclasse(all.get(0), 2L, "SC2", "BAR", false);
        assertSottoclasse(all.get(1), 1L, "SC1", "FOO", true);
    }

    @Test
    public void findById_whenNullId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findById(null);
    }

    @Test
    public void findById_whenNotExist_shouldReturnNull() {
        assertNull(dao.findById(3L));
    }

    @Test
    public void findById_whenExist_shouldReturnEntity() {
        final SottoclasseEntity entity = dao.findById(1L);
        assertSottoclasse(entity, 1L, "SC1", "FOO", true);
    }

    @Test
    public void existsByCodeOrDescription_givenNullCode_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.existsByCodeOrDescription(null, "FOO");
    }

    @Test
    public void existsByCodeOrDescription_givenNullDecription_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.existsByCodeOrDescription("FOO", null);
    }

    @Test
    public void existsByCodeOrDescription_givenExistingCode_shouldReturnTrue() {
        assertTrue(dao.existsByCodeOrDescription("SC1", "BAZ"));
    }

    @Test
    public void existsByCodeOrDescription_givenExistingDescription_shouldReturnTrue() {
        assertTrue(dao.existsByCodeOrDescription("SC3", "BAR"));
    }

    @Test
    public void existsByCodeOrDescription_givenNotExistingCodeAndDescription_shouldReturnFalse() {
        assertFalse(dao.existsByCodeOrDescription("SC3", "BAZ"));
    }

    @Test
    public void countSottoclassiNonScaricate() {
        assertEquals(2, dao.countSottoclassiNonScaricate().longValue());
    }

    private void assertSottoclasse(SottoclasseEntity entity, Long id, String codice, String descrizione, boolean attiva) {
        assertEquals(id, entity.getId());
        assertEquals(codice, entity.getCodice());
        assertEquals(descrizione, entity.getDescrizione());
        assertEquals(attiva, entity.getAttiva());
    }

    private SottoclasseEntity createEntity(Long id, String codice, String descrizione, boolean attiva) {
        final SottoclasseEntity entity = new SottoclasseEntity();
        entity.setId(id);
        entity.setCodice(codice);
        entity.setDescrizione(descrizione);
        entity.setAttiva(attiva);
        return entity;
    }
}