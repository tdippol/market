package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CreaPianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaCreaPianoMediaDAOImplTest extends AbstractDaoTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaCreaPianoMediaDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Inject
    private CreaPianoMediaDAO dao;

    @Before
    public void setUp() throws Exception {
        final CreaPianoMediaEntity pianoMedia1 = creaPianoMedia("junit", "001");
        final CreaPianoMediaEntity pianoMedia2 = creaPianoMedia("junit", "002");
        openTransaction();
        getEm().persist(pianoMedia1);
        getEm().persist(pianoMedia2);
        commitTransaction();
    }


    @Test
    public void findByUserId_givenNullUserId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByUserId(null);
    }

    @Test
    public void findByUserId() {
        final List<CreaPianoMediaEntity> entities = dao.findByUserId("junit");
        assertEquals(2, entities.size());
        CreaPianoMediaEntity entity = entities.get(0);
        assertEquals("junit", entity.getUserId());
        assertNotNull(entity.getId());
        entity = entities.get(1);
        assertEquals("junit", entity.getUserId());
        assertNotNull(entity.getId());
    }

    @Test
    public void findByUserIdAndSlotId_givenNullUserId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByUserIdAndSlotId(null, "001");
    }

    @Test
    public void findByUserIdAndSlotId_givenNullSlotId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByUserIdAndSlotId("junit", null);
    }

    @Test
    public void findByUserIdAndSlotId() {
        final CreaPianoMediaEntity entity = dao.findByUserIdAndSlotId("junit", "001");
        assertEquals("junit", entity.getUserId());
        assertEquals("001", entity.getSlotId());
        assertNotNull(entity.getId());
    }

    @Test
    public void findByUserIdAndSlotId_whenNoEntitiesFound_shouldReturnNull() {
        assertNull(dao.findByUserIdAndSlotId("junit", "010"));
    }

    @Test
    public void findByUserIdAndSlotId_whenManyEntitiesFound_shouldReturnNull() {
        CreaPianoMediaEntity pm = creaPianoMedia("junit", "001");
        persist(pm);
        assertNull(dao.findByUserIdAndSlotId("junit", "001"));
    }

    private CreaPianoMediaEntity creaPianoMedia(String userId, String slotId) {
        final CreaPianoMediaEntity e = new CreaPianoMediaEntity();
        e.setUserId(userId);
        e.setSlotId(slotId);
        return e;
    }
}