package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

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
        FornitoreEntity fornitoreEntity = createFornitoreEntity("FF999", "fornitore 999");
        openTransaction();
        fornitoreDAO.persist(fornitoreEntity);
        commitTransaction();
    }

    @Test
    public void shouldFindById() {
        final FornitoreEntity fornitoreEntity = fornitoreDAO.findById(999L);
        assertNotNull(fornitoreEntity);
        assertFalse(fornitoreEntity.getCodiceFornitore().isEmpty());
        assertEquals(fornitoreEntity.getCodiceFornitore(), "FF999");
        assertEquals(fornitoreEntity.getDescrizione(), "fornitore 999");
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
        ResponsabileEntity responsabile = new ResponsabileEntityBuilder().withCodice("R01").build();
        CompratoreEntity compratore1 = new CompratoreEntityBuilder().withCodice("S01").withResponsabile(responsabile).build();
        CompratoreEntity compratore2 = new CompratoreEntityBuilder().withCodice("S02").withResponsabile(responsabile).build();
        CompratoreEntity compratore3 = new CompratoreEntityBuilder().withCodice("S03").withResponsabile(responsabile).build();
        RepartoEntity reparto = new RepartoEntityBuilder().withCodice("R1").build();
        CategoriaEntity categoria = new CategoriaEntityBuilder().withCodice("C01").withReparto(reparto).build();
        GrmEntity grm = new GrmEntityBuilder().withCodice("G01").withCategoria(categoria).build();
        SubGrmEntity subGrm = new SubGrmEntityBuilder().withCodice("SG1").withGrm(grm).build();
        FornitoreEntity fornitore1 = new FornitoreEntityBuilder().withCodice("F-01").build();
        FornitoreEntity fornitore2 = new FornitoreEntityBuilder().withCodice("F-02").build();
        FornitoreEntity fornitore3 = new FornitoreEntityBuilder().withCodice("F-03").build();
        ItemEntity item1 = new ItemEntityBuilder().withId(1L).withCodice("IT-001").withSubGrm(subGrm)
                .withCompratore(compratore1).build();
        ItemEntity item2 = new ItemEntityBuilder().withId(2L).withCodice("IT-002").withSubGrm(subGrm)
                .withCompratore(compratore1).build();
        ItemEntity item3 = new ItemEntityBuilder().withId(3L).withCodice("IT-003").withSubGrm(subGrm)
                .withCompratore(compratore3).build();
        ItemEntity item4 = new ItemEntityBuilder().withId(4L).withCodice("IT-004").withSubGrm(subGrm)
                .withCompratore(compratore2).build();
        AssortimentoFornitoreEntity assForn1 = new AssortimentoFornitoreEntityBuilder().withFornitore(fornitore1)
                .withItem(item1).build();
        AssortimentoFornitoreEntity assForn2 = new AssortimentoFornitoreEntityBuilder().withFornitore(fornitore1)
                .withItem(item2).build();
        AssortimentoFornitoreEntity assForn3 = new AssortimentoFornitoreEntityBuilder().withFornitore(fornitore3)
                .withItem(item3).build();
        AssortimentoFornitoreEntity assForn4 = new AssortimentoFornitoreEntityBuilder().withFornitore(fornitore3)
                .withItem(item4).build();
        persist(fornitore2, assForn1, assForn2, assForn3, assForn4);
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

    private FornitoreEntity createFornitoreEntity(String codiceFornitore, String descrizione) {
        FornitoreEntity fornitoreEntity = new FornitoreEntity();
        fornitoreEntity.setId(999L);
        fornitoreEntity.setCodiceFornitore(codiceFornitore);
        fornitoreEntity.setDescrizione(descrizione);
        return fornitoreEntity;
    }
}
