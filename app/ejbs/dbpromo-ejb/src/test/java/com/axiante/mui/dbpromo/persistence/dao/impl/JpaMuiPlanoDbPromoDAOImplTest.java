package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MuiPlanoDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoDbPromoEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaMuiPlanoDbPromoDAOImplTest extends AbstractDaoTest {
    @Inject
    private MuiPlanoDbPromoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaMuiPlanoDbPromoDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private MuiPlanoDbPromoEntity planoPromo1;
    private MuiPlanoDbPromoEntity planoPromo2;
    private MuiPlanoDbPromoEntity planoPromo3;

    @Before
    public void setUp() throws Exception {
        planoPromo1 = createPlanoPromo("001");
        planoPromo2 = createPlanoPromo("002");
        planoPromo3 = createPlanoPromo("003");
        persist(planoPromo1, planoPromo2, planoPromo3);
    }

    @Test
    public void findAll() {
        final List<MuiPlanoDbPromoEntity> result = dao.findAll();
        assertEquals(3, result.size());
        assertTrue(result.contains(planoPromo1));
        assertTrue(result.contains(planoPromo2));
        assertTrue(result.contains(planoPromo3));
    }

    @Test
    public void findByIdPlano() {
        final MuiPlanoDbPromoEntity result = dao.findByIdPlano("001");
        assertEquals(planoPromo1, result);
    }

    @Test
    public void findByIdPlano_givenNullIdPlano_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByIdPlano(null);
    }

    @Test
    public void findByIdPlano_whenNoResult_shouldReturnNull() {
        assertNull(dao.findByIdPlano("005"));
    }

    private MuiPlanoDbPromoEntity createPlanoPromo(String idPlano) {
        MuiPlanoDbPromoEntity e = new MuiPlanoDbPromoEntity();
        e.setIdPlano(idPlano);
        return e;
    }
}