package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MuiPlanoArticoliDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoArticoliDbPromoId;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.entities.SubGrmEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CategoriaEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CompratoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GrmEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.ItemEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.RepartoEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.ResponsabileEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.SubGrmEntityBuilder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaMuiPlanoArticoliDbPromoDAOImplTest extends AbstractDaoTest {
    @Inject
    private MuiPlanoArticoliDbPromoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaMuiPlanoArticoliDbPromoDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private MuiPlanoArticoliDbPromoEntity planoArticolo1;
    private MuiPlanoArticoliDbPromoEntity planoArticolo2;
    private MuiPlanoArticoliDbPromoEntity planoArticolo3;

    @Before
    public void setUp() throws Exception {
        ResponsabileEntity responsabile = new ResponsabileEntityBuilder(1L).withCodice("RES").build();
        CompratoreEntity compratore = new CompratoreEntityBuilder(1L).withCodice("FOO")
                .withResponsabile(responsabile).build();
        RepartoEntity reparto = new RepartoEntityBuilder(1L).withCodice("R1").build();
        CategoriaEntity categoria = new CategoriaEntityBuilder(1L).withCodice("C01").withReparto(reparto).build();
        GrmEntity grm = new GrmEntityBuilder(1L).withCodice("G01").withCategoria(categoria).build();
        SubGrmEntity subGrm = new SubGrmEntityBuilder(1L).withCodice("SG1").withGrm(grm).build();
        ItemEntity item = new ItemEntityBuilder(1L).withCodice("I001").withSubGrm(subGrm).withCompratore(compratore)
                .build();
        planoArticolo1 = createMuiPlanoArticolo("PL001", 1L, "FOO", item);
        planoArticolo2 = createMuiPlanoArticolo("PL002", 2L, "BAR", item);
        planoArticolo3 = createMuiPlanoArticolo("PL003", 3L, "BAZ", item);
        persist(responsabile, compratore, reparto, categoria, grm, subGrm, item,
                planoArticolo1, planoArticolo2, planoArticolo3);
    }

    @Test
    public void findAll() {
        List<MuiPlanoArticoliDbPromoEntity> result = dao.findAll();
        assertEquals(3, result.size());
        assertTrue(result.contains(planoArticolo1));
        assertTrue(result.contains(planoArticolo2));
        assertTrue(result.contains(planoArticolo3));
    }

    @Test
    public void findAllByIdPlano() {
        final List<MuiPlanoArticoliDbPromoEntity> result = dao.findAllByIdPlano("PL001");
        assertEquals(1, result.size());
        assertTrue(result.contains(planoArticolo1));
        assertFalse(result.contains(planoArticolo2));
        assertFalse(result.contains(planoArticolo3));
    }

    @Test
    public void findAllByIdPlano_givenNullIdPlano_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByIdPlano((String) null);
    }

    @Test
    public void testFindAllByIdPlano() {
        final List<MuiPlanoArticoliDbPromoEntity> result = dao.findAllByIdPlano(Arrays.asList("PL002", "PL003"));
        assertEquals(2, result.size());
        assertFalse(result.contains(planoArticolo1));
        assertTrue(result.contains(planoArticolo2));
        assertTrue(result.contains(planoArticolo3));
    }

    @Test
    public void testFindAllByIdPlano_givenEmptyIdPlano_shouldReturnEmptyList() {
        assertTrue(dao.findAllByIdPlano(Collections.emptyList()).isEmpty());
    }

    @Test
    public void testFindAllByIdPlano_givenNullIdPlano_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByIdPlano((List<String>) null);
    }

    private MuiPlanoArticoliDbPromoEntity createMuiPlanoArticolo(String idPlano, Long codiceItem, String tipoItem,
                                                                 ItemEntity item) {
        final MuiPlanoArticoliDbPromoEntity e = new MuiPlanoArticoliDbPromoEntity();
        e.setId(new MuiPlanoArticoliDbPromoId(idPlano, codiceItem, tipoItem));
        e.setItem(item);
        return e;
    }
}