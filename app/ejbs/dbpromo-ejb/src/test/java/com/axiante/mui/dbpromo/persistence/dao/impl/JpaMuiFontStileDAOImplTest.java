package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MuiFontStileDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JpaMuiFontStileDAOImplTest extends AbstractDaoTest {
    @Inject
    private MuiFontStileDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaMuiFontStileDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Before
    public void setUp() throws Exception {
        MuiFontStileEntity entity1 = createEntity("F1", "STYLE 1", "WHITE");
        MuiFontStileEntity entity2 = createEntity("F2", "STYLE 2", "BLACK");
        persist(entity1, entity2);
    }

    @Test
    public void findAll() {
        assertEquals(2, dao.findAll().size());
    }

    @Test
    public void findById_existing() {
        assertNotNull(dao.findById("F1"));
    }

    @Test
    public void findById_notExisting() {
        assertNull(dao.findById("F42"));
    }

    private MuiFontStileEntity createEntity(String id, String stile, String colore) {
        final MuiFontStileEntity entity = new MuiFontStileEntity();
        entity.setId(id);
        entity.setStile(stile);
        entity.setColore(colore);
        return entity;
    }
}