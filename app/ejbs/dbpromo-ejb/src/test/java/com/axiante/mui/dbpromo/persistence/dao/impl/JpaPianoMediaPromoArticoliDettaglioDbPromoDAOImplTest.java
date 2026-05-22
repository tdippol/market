package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPromoArticoliDettaglioDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDettaglioDbPromoEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaPianoMediaPromoArticoliDettaglioDbPromoDAOImplTest extends AbstractDaoTest {
    @Inject
    private PianoMediaPromoArticoliDettaglioDbPromoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPianoMediaPromoArticoliDettaglioDbPromoDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        PianoMediaPromoArticoliDettaglioDbPromoEntity dettaglio1 = createDettaglioEntity("PROMO 001", "I001", "FOO");
        PianoMediaPromoArticoliDettaglioDbPromoEntity dettaglio2 = createDettaglioEntity("PROMO 001", "I001", "BAR");
        persist(dettaglio1, dettaglio2);
    }

    @Test
    public void findByCodicePromoAndItemAndTipoItem() {
        List<PianoMediaPromoArticoliDettaglioDbPromoEntity> result = dao
                .findByCodicePromoAndItemAndTipoItem("PROMO 001", "I001", "FOO");
        assertEquals(1, result.size());
    }

    private PianoMediaPromoArticoliDettaglioDbPromoEntity createDettaglioEntity(String codicePromo, String codiceItem,
                                                                                String tipoItem) {
        PianoMediaPromoArticoliDettaglioDbPromoEntity e = new PianoMediaPromoArticoliDettaglioDbPromoEntity();
        e.setCodicePromozione(codicePromo);
        e.setCodiceItem(codiceItem);
        e.setTipoItem(tipoItem);
        e.setCodiceSocieta("S1");
        e.setCodiceMeccanica("M1");
        e.setCodiceCondizione("C1");
        e.setCodiceZona("Z1");
        return e;
    }
}