package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MuiFlagDominioDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFlagDominioEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFlagEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaMuiFlagDominioDAOImplTest extends AbstractDaoTest {

    @Inject
    private MuiFlagDominioDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaMuiFlagDominioDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        MuiFlagEntity flag = createFlag(1L);
        MuiFlagEntity flag2 = createFlag(2L);
        MuiFlagDominioEntity flagDominio1 = createFlagDominio(1L, flag, Boolean.TRUE, Boolean.FALSE);
        MuiFlagDominioEntity flagDominio2 = createFlagDominio(2L, flag2, Boolean.TRUE, Boolean.TRUE);
        MuiFlagDominioEntity flagDominio3 = createFlagDominio(3L, flag, Boolean.FALSE, Boolean.FALSE);
        MuiFlagDominioEntity flagDominio4 = createFlagDominio(4L, flag2, Boolean.FALSE, Boolean.TRUE);
        persist(flag, flag2, flagDominio1, flagDominio2, flagDominio3, flagDominio4);
    }

    @Test
    public void findAllAttiviByFlag() {
        final List<MuiFlagDominioEntity> result = dao.findAllAttiviByFlag(1L);
        assertEquals(1, result.size());
    }

    @Test
    public void findAllAttiviByFlag_givenNullIdFlag_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllAttiviByFlag(null);
    }

    @Test
    public void findAllAttiviAndDefaultByFlag() {
        final List<MuiFlagDominioEntity> result = dao.findAllAttiviAndDefaultByFlag(2L);
        assertEquals(1, result.size());
    }

    @Test
    public void findAllAttiviAndDefaultByFlag_givenNullIdFlag_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllAttiviAndDefaultByFlag(null);
    }

    private MuiFlagDominioEntity createFlagDominio(Long id, MuiFlagEntity flag, Boolean attivo, Boolean defaultFlag) {
        final MuiFlagDominioEntity e = new MuiFlagDominioEntity();
        e.setId(id);
        e.setFlag(flag);
        e.setAttivo(attivo);
        e.setFlDefault(defaultFlag);
        return e;
    }

    private MuiFlagEntity createFlag(Long id) {
        final MuiFlagEntity e = new MuiFlagEntity();
        e.setId(id);
        return e;
    }
}