package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.ItemDAO;
import com.axiante.mui.dbpromo.persistence.entities.AssortimentoFornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.entities.SubGrmEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.AssortimentoFornitoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CategoriaEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CompratoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.FornitoreEntityBuilder;
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

public class JpaItemDAOImplTest extends AbstractDaoTest {
    @Inject
    private ItemDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaItemDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private ItemEntity item;
    private CompratoreEntity compratore;

    @Before
    public void setUp() throws Exception {
        ResponsabileEntity responsabile = new ResponsabileEntityBuilder(1L).withCodice("RES").build();
        compratore = new CompratoreEntityBuilder(1L).withCodice("C01").withResponsabile(responsabile).build();
        RepartoEntity reparto = new RepartoEntityBuilder().withCodice("R1").build();
        CategoriaEntity categoria = new CategoriaEntityBuilder().withCodice("C01").withReparto(reparto).build();
        GrmEntity grm = new GrmEntityBuilder().withCodice("G01").withCategoria(categoria).build();
        SubGrmEntity subGrm = new SubGrmEntityBuilder().withCodice("SG1").withGrm(grm).build();
        item = new ItemEntityBuilder(1L).withCodice("I001").withCompratore(compratore).withSubGrm(subGrm)
                .withCodiceMarchioPrivato("MP").build();
        FornitoreEntity fornitore = new FornitoreEntityBuilder(1L).withCodice("F01").build();
        AssortimentoFornitoreEntity assortimentoFornitore = new AssortimentoFornitoreEntityBuilder(1L)
                .withFornitore(fornitore).withItem(item).build();
        item.setMuiAssortimentoFornitores(Collections.singleton(assortimentoFornitore));
        persist(responsabile, compratore, reparto, categoria, grm, subGrm, item, fornitore, assortimentoFornitore);
    }

    @Test
    public void findByCode() {
        assertEquals(item, dao.findByCode("I001"));
    }

    @Test
    public void findByCode_whenNoResult_shouldReturnNull() {
        assertNull(dao.findByCode("I666"));
    }

    @Test
    public void findAllByCompratore() {
        final List<ItemEntity> result = dao.findAllByCompratore(compratore);
        assertEquals(1, result.size());
        assertEquals(item, result.get(0));
    }

    @Test
    public void findAllByFilter_withAllFilter() {
        final List<ItemEntity> result = dao.findAllByFilter(1L, 2L, 3L, 4L, 5L, "MP");
        assertTrue(result.isEmpty());
    }

    @Test
    public void findAllByFilter_withNoFilter() {
        final List<ItemEntity> result = dao.findAllByFilter(null, null, null, null, null, null);
        assertEquals(1, result.size());
        assertEquals(item, result.get(0));
    }

    @Test
    public void findCodiceById() {
        assertEquals("I001", dao.findCodiceById(1L));
    }

    @Test
    public void findCodiceById_givenNullId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findCodiceById(null);
    }

    @Test
    public void findByIds() {
        final List<ItemEntity> result = dao.findByIds(Arrays.asList(1L, 2L));
        assertEquals(1, result.size());
        assertEquals(item, result.get(0));
    }

    @Test
    public void findByIds_givenNullIds_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByIds(null);
    }

    @Test
    public void findByIds_givenEmptyIds_shouldReturnEmptyList() {
        assertTrue(dao.findByIds(Collections.emptyList()).isEmpty());
    }

    @Test
    public void findByIdsAndCompratoreAndFornitore() {
        final List<ItemEntity> result = dao.findByIdsAndCompratoreAndFornitore(Arrays.asList(1L, 2L), "C01", "F01");
        assertEquals(1, result.size());
        assertEquals(item, result.get(0));
    }

    @Test
    public void findByIdsAndCompratoreAndFornitore_givenNullIds_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findByIdsAndCompratoreAndFornitore(null, "C01", "F01");
    }

    @Test
    public void findByIdsAndCompratoreAndFornitore_givenEmptyIds_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findByIdsAndCompratoreAndFornitore(Collections.emptyList(), "C01", "F01");
    }

    @Test
    public void findByIdsAndCompratoreAndFornitore_givenNullCodiceCompratore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByIdsAndCompratoreAndFornitore(Arrays.asList(1L, 2L), null, "F01");
    }

    @Test
    public void findByIdsAndCompratoreAndFornitore_givenNullCodiceFornitore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByIdsAndCompratoreAndFornitore(Arrays.asList(1L, 2L), "CO1", null);
    }

    @Test
    public void findByIdsAndCompratoriAndFornitore() {
        final List<ItemEntity> result = dao.findByIdsAndCompratoriAndFornitore(Arrays.asList(1L, 2L),
                Arrays.asList("C01", "C02"), "F01");
        assertEquals(1, result.size());
        assertEquals(item, result.get(0));
    }

    @Test
    public void findByIdsAndCompratoriAndFornitore_givenNullIds_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findByIdsAndCompratoriAndFornitore(null, Arrays.asList("C01", "C02"), "F01");
    }

    @Test
    public void findByIdsAndCompratoriAndFornitore_givenEmptyIds_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findByIdsAndCompratoriAndFornitore(Collections.emptyList(), Arrays.asList("C01", "C02"), "F01");
    }

    @Test
    public void findByIdsAndCompratoriAndFornitore_givenNullCodiciCompratori_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findByIdsAndCompratoriAndFornitore(Arrays.asList(1L, 2L), null, "F01");
    }

    @Test
    public void findByIdsAndCompratoriAndFornitore_givenEmptyCodiciCompratori_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findByIdsAndCompratoriAndFornitore(Arrays.asList(1L, 2L), Collections.emptyList(), "F01");
    }

    @Test
    public void findByIdsAndCompratoriAndFornitore_givenNullFornitore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByIdsAndCompratoriAndFornitore(Arrays.asList(1L, 2L), Arrays.asList("C01", "C02"), null);
    }

    @Test
    public void findCodiceMarchioPrivatoByCompratori() {
        final List<String> result = dao.findCodiceMarchioPrivatoByCompratori(Arrays.asList("C01", "C02"));
        assertEquals(1, result.size());
        assertEquals("MP", result.get(0));
    }

    @Test
    public void findCodiceMarchioPrivatoByCompratori_givenNullCodiciCompratori_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findCodiceMarchioPrivatoByCompratori(null);
    }

    @Test
    public void findCodiceMarchioPrivatoByCompratori_givenEmptyCodiciCompratori_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findCodiceMarchioPrivatoByCompratori(Collections.emptyList());
    }
}