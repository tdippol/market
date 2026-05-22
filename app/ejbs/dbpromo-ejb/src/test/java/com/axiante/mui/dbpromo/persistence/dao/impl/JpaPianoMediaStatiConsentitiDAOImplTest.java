package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaStatiConsentitiDAO;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

public class JpaPianoMediaStatiConsentitiDAOImplTest {
    @Inject
    private PianoMediaStatiConsentitiDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPianoMediaStatiConsentitiDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Test
    public void findAll() {
        assertTrue(dao.findAll().isEmpty());
    }
}