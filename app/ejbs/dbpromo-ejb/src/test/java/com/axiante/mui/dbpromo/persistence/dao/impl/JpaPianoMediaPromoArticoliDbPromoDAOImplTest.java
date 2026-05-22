package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPromoArticoliDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDbPromoEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaPianoMediaPromoArticoliDbPromoDAOImplTest extends AbstractDaoTest {
    @Inject
    private PianoMediaPromoArticoliDbPromoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPianoMediaPromoArticoliDbPromoDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private PianoMediaPromoArticoliDbPromoEntity promoArticolo1;
    private PianoMediaPromoArticoliDbPromoEntity promoArticolo2;
    private PianoMediaPromoArticoliDbPromoEntity promoArticolo3;

    @Before
    public void setUp() throws Exception {
        promoArticolo1 = createPromoArticolo("PROMO 001", "I001", "FOO");
        promoArticolo2 = createPromoArticolo("PROMO 002", "I002", "BAR");
        promoArticolo3 = createPromoArticolo("PROMO 003", "I003", "BAZ");
        persist(promoArticolo1, promoArticolo2, promoArticolo3);
    }

    @Test
    public void findByCodicePromo() {
        final List<PianoMediaPromoArticoliDbPromoEntity> result = dao.findByCodicePromo("PROMO 001");
        assertEquals(1, result.size());
        assertTrue(result.contains(promoArticolo1));
        assertFalse(result.contains(promoArticolo2));
        assertFalse(result.contains(promoArticolo3));
    }

    @Test
    public void findByCodiceItemAndCodicePromoAndTipoItem() {
        assertEquals(promoArticolo1,
                dao.findByCodiceItemAndCodicePromoAndTipoItem("I001", "PROMO 001", "FOO"));
    }

    @Test
    public void findByCodiceItemAndCodicePromoAndTipoItem_whenNoResult_shouldReturnNull() {
        assertNull(dao.findByCodiceItemAndCodicePromoAndTipoItem("I001", "PROMO 003", "FOOBAR"));
    }

    private PianoMediaPromoArticoliDbPromoEntity createPromoArticolo(String codicePromo, String codiceItem, String tipoItem) {
        final PianoMediaPromoArticoliDbPromoEntity e = new PianoMediaPromoArticoliDbPromoEntity();
        e.setCodicePromozione(codicePromo);
        e.setCodiceItem(codiceItem);
        e.setTipoItem(tipoItem);
        return e;
    }

}