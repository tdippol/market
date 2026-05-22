package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.FornitoreDAO;
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
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class JpaFornitoreDAOImplTest extends AbstractDaoTest {
    @Inject
    private FornitoreDAO fornitoreDAO;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaFornitoreDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        FornitoreEntity fornitoreEntity = createFornitoreEntity(999L, "FF999", "fornitore 999");
        ResponsabileEntity responsabile = new ResponsabileEntityBuilder().withCodice("R01").build();
        CompratoreEntity compratore1 = new CompratoreEntityBuilder().withCodice("S01").withResponsabile(responsabile).build();
        CompratoreEntity compratore2 = new CompratoreEntityBuilder().withCodice("S02").withResponsabile(responsabile).build();
        CompratoreEntity compratore3 = new CompratoreEntityBuilder().withCodice("S03").withResponsabile(responsabile).build();
        RepartoEntity reparto = new RepartoEntityBuilder().withCodice("R1").build();
        CategoriaEntity categoria = new CategoriaEntityBuilder().withCodice("C01").withReparto(reparto).build();
        GrmEntity grm = new GrmEntityBuilder().withCodice("G01").withCategoria(categoria).build();
        SubGrmEntity subGrm = new SubGrmEntityBuilder().withCodice("SG1").withGrm(grm).build();
        FornitoreEntity fornitore1 = new FornitoreEntityBuilder(1L).withCodice("F-01").build();
        FornitoreEntity fornitore2 = new FornitoreEntityBuilder(2L).withCodice("F-02").build();
        FornitoreEntity fornitore3 = new FornitoreEntityBuilder(3L).withCodice("F-03").build();
        ItemEntity item1 = new ItemEntityBuilder().withId(1L).withCodice("IT-001").withSubGrm(subGrm)
                .withCompratore(compratore1).build();
        ItemEntity item2 = new ItemEntityBuilder().withId(2L).withCodice("IT-002").withSubGrm(subGrm)
                .withCompratore(compratore1).build();
        ItemEntity item3 = new ItemEntityBuilder().withId(3L).withCodice("IT-003").withSubGrm(subGrm)
                .withCompratore(compratore3).build();
        ItemEntity item4 = new ItemEntityBuilder().withId(4L).withCodice("IT-004").withSubGrm(subGrm)
                .withCompratore(compratore2).build();
        AssortimentoFornitoreEntity assForn1 = new AssortimentoFornitoreEntityBuilder(1L).withFornitore(fornitore1)
                .withItem(item1).build();
        AssortimentoFornitoreEntity assForn2 = new AssortimentoFornitoreEntityBuilder(2L).withFornitore(fornitore1)
                .withItem(item2).build();
        AssortimentoFornitoreEntity assForn3 = new AssortimentoFornitoreEntityBuilder(3L).withFornitore(fornitore3)
                .withItem(item3).build();
        AssortimentoFornitoreEntity assForn4 = new AssortimentoFornitoreEntityBuilder(4L).withFornitore(fornitore3)
                .withItem(item4).build();
        AssortimentoFornitoreEntity assForn5 = new AssortimentoFornitoreEntityBuilder(5L).withFornitore(fornitore3)
                .withItem(item1).withDataEliminazione(new Date()).build();
        fornitore1.addMuiAssortimentoFornitore(assForn1);
        fornitore1.addMuiAssortimentoFornitore(assForn2);
        fornitore3.addMuiAssortimentoFornitore(assForn3);
        fornitore3.addMuiAssortimentoFornitore(assForn4);
        fornitore3.addMuiAssortimentoFornitore(assForn5);
        persist(fornitoreEntity, fornitore1, fornitore2, fornitore3, responsabile, compratore1, compratore2, compratore3,
                reparto, categoria, grm, subGrm, item1, item2, item3, item4, assForn1,
                assForn2, assForn3, assForn4, assForn5);
    }

    @Test
    public void shouldFindById() {
        final FornitoreEntity fornitoreEntity = fornitoreDAO.findById(999L);
        assertNotNull(fornitoreEntity);
        assertFalse(fornitoreEntity.getCodiceFornitore().isEmpty());
        assertEquals("FF999", fornitoreEntity.getCodiceFornitore());
        assertEquals("fornitore 999", fornitoreEntity.getDescrizione());
    }

    @Test
    public void shouldReadAll() {
        List<FornitoreEntity> fornitoreList = fornitoreDAO.findAll();
        assertNotNull(fornitoreList);
        assertFalse(fornitoreList.isEmpty());
    }

    @Test
    public void findAllByCodiciCompratore_givenNullCodiciCompratore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        fornitoreDAO.findAllByCodiciCompratore(null);
    }

    @Test
    public void findAllByCodiciCompratore_givenEmptyCodiciCompratore_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        fornitoreDAO.findAllByCodiciCompratore(Collections.emptyList());
    }

    @Test
    public void findAllByCodiciCompratore_givenExistingCodiciCompratore_shouldReturnNotEmptyList() {
        List<FornitoreEntity> fornitori = fornitoreDAO.findAllByCodiciCompratore(Arrays.asList("S01", "S02"));
        List<String> fornitoriCodes = fornitori.stream().map(FornitoreEntity::getCodiceFornitore)
                .collect(Collectors.toList());
        assertEquals(2, fornitoriCodes.size());
        assertTrue(fornitoriCodes.contains("F-01"));
        assertTrue(fornitoriCodes.contains("F-03"));
        assertFalse(fornitoriCodes.contains("F-02"));
        fornitori = fornitoreDAO.findAllByCodiciCompratore(Arrays.asList("S10", "S42"));
        assertEquals(0, fornitori.size());
    }

    @Test
    public void findAllFornitoriAttiviByCodiceCompratore_givenNullCodiceCompratore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        fornitoreDAO.findAllFornitoriAttiviByCodiceCompratore(null);
    }

    @Test
    public void findAllFornitoriAttiviByCodiceCompratore() {
        List<FornitoreEntity> fornitori = fornitoreDAO.findAllFornitoriAttiviByCodiceCompratore("S01");
        assertEquals(1, fornitori.size());
        assertEquals("F-01", fornitori.get(0).getCodiceFornitore());
    }

    private FornitoreEntity createFornitoreEntity(Long id, String codiceFornitore, String descrizione) {
        FornitoreEntity fornitoreEntity = new FornitoreEntity();
        fornitoreEntity.setId(id);
        fornitoreEntity.setCodiceFornitore(codiceFornitore);
        fornitoreEntity.setDescrizione(descrizione);
        return fornitoreEntity;
    }
}
