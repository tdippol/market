package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MuiBottoneDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JpaMuiBottoneDAOImplTest extends AbstractDaoTest {
    @Inject
    private MuiBottoneDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
                    EntityManagerFactoryProducer.class, JpaMuiBottoneDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Before
    public void setUp() throws Exception {
        MuiBottoneEntity btn1 = createMuiBottoneEntity(1L, "BTN-X");
        MuiBottoneEntity btn2 = createMuiBottoneEntity(2L, "BTN-A");
        persist(btn1, btn2);
    }

    @Test
    public void findAll() {
        List<MuiBottoneEntity> result = dao.findAll();
        assertEquals(2, result.size());
        assertEquals(2L, result.get(0).getId().longValue());
        assertEquals("BTN-A", result.get(0).getDescrizione());
        assertEquals(1L, result.get(1).getId().longValue());
        assertEquals("BTN-X", result.get(1).getDescrizione());
    }

    @Test
    public void findById() {
        MuiBottoneEntity result = dao.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId().longValue());
        assertEquals("BTN-X", result.getDescrizione());
    }

    @Test
    public void findById_notExisting() {
        assertNull(dao.findById(3L));
    }

    private MuiBottoneEntity createMuiBottoneEntity(Long id, String description) {
        MuiBottoneEntity entity = new MuiBottoneEntity();
        entity.setId(id);
        entity.setDescrizione(description);
        return entity;
    }
}