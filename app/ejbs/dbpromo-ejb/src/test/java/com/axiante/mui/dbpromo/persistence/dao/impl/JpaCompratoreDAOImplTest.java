package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CompratoreDAO;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
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

public class JpaCompratoreDAOImplTest extends AbstractDaoTest {

    @Inject
    private CompratoreDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaCompratoreDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private final ResponsabileEntity responsabile = new ResponsabileEntityBuilder(1L).withCodice("001")
            .withFlagAttivo(1L).build();
    private final CompratoreEntity compratore1 = new CompratoreEntityBuilder(1L).withCodice("01")
            .withDescrizione("Compratore 01").withResponsabile(responsabile).withFlagAttivo(1L).build();
    private final CompratoreEntity compratore2 = new CompratoreEntityBuilder(2L).withCodice("00")
            .withDescrizione("Compratore 00").withResponsabile(responsabile).withFlagAttivo(1L).build();
    private RepartoEntity reparto = new RepartoEntityBuilder().withCodice("R1").build();
    private CategoriaEntity categoria = new CategoriaEntityBuilder().withCodice("C01").withReparto(reparto).build();
    private GrmEntity grm = new GrmEntityBuilder().withCodice("G01").withCategoria(categoria).build();
    private SubGrmEntity subGrm = new SubGrmEntityBuilder().withCodice("SG1").withGrm(grm).build();
    private ItemEntity item1 = new ItemEntityBuilder(1L).withCodice("I001").withCompratore(compratore1).withSubGrm(subGrm)
            .build();
    private ItemEntity item2 = new ItemEntityBuilder(2L).withCodice("I002").withCompratore(compratore2).withSubGrm(subGrm)
            .build();

    @Before
    public void setUp() throws Exception {
        persist(responsabile, compratore1, compratore2, reparto, categoria, grm, subGrm, item1, item2);
    }

    @Test
    public void shouldFindAllOrderByCodiceCompratore() {
        List<CompratoreEntity> compratoreEntities = dao.findAllOrderedBy();
        assertNotNull(compratoreEntities);
        assertFalse(compratoreEntities.isEmpty());
        assertEquals(2, compratoreEntities.size());
        assertEquals(compratore2.getCodiceCompratore(), compratoreEntities.get(0).getCodiceCompratore());
        assertEquals(compratore2.getId(), compratoreEntities.get(0).getId());
        assertEquals(compratore2.getDescrizione(), compratoreEntities.get(0).getDescrizione());
        assertEquals(compratore2.getFlagAttivo(), compratoreEntities.get(0).getFlagAttivo());
        assertEquals(compratore2.getItemEntities(), compratoreEntities.get(0).getItemEntities());
        assertEquals(compratore2.getResponsabileEntity().getId(),
                compratoreEntities.get(0).getResponsabileEntity().getId());
        assertEquals(compratore2.getResponsabileEntity().getFlagAttivo(),
                compratoreEntities.get(0).getResponsabileEntity().getFlagAttivo());
        assertEquals(compratore2.getResponsabileEntity().getDescrizione(),
                compratoreEntities.get(0).getResponsabileEntity().getDescrizione());
        assertEquals(compratore2.getResponsabileEntity().getCodiceResponsabile(),
                compratoreEntities.get(0).getResponsabileEntity().getCodiceResponsabile());
        assertEquals(compratore2.getResponsabileEntity().getCompratoreEntities(),
                compratoreEntities.get(0).getResponsabileEntity().getCompratoreEntities());
        assertEquals(compratore1.getCodiceCompratore(), compratoreEntities.get(1).getCodiceCompratore());
        assertEquals(compratore1.getId(), compratoreEntities.get(1).getId());
        assertEquals(compratore1.getDescrizione(), compratoreEntities.get(1).getDescrizione());
        assertEquals(compratore1.getFlagAttivo(), compratoreEntities.get(1).getFlagAttivo());
        assertEquals(compratore1.getItemEntities(), compratoreEntities.get(1).getItemEntities());
        assertEquals(compratore1.getResponsabileEntity().getId(),
                compratoreEntities.get(1).getResponsabileEntity().getId());
        assertEquals(compratore1.getResponsabileEntity().getFlagAttivo(),
                compratoreEntities.get(1).getResponsabileEntity().getFlagAttivo());
        assertEquals(compratore1.getResponsabileEntity().getDescrizione(),
                compratoreEntities.get(1).getResponsabileEntity().getDescrizione());
        assertEquals(compratore1.getResponsabileEntity().getCodiceResponsabile(),
                compratoreEntities.get(1).getResponsabileEntity().getCodiceResponsabile());
        assertEquals(compratore1.getResponsabileEntity().getCompratoreEntities(),
                compratoreEntities.get(1).getResponsabileEntity().getCompratoreEntities());
    }

    @Test
    public void findAllByCodes_givenNullCodesList_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByCodes(null);
    }

    @Test
	public void findAllByCodes_givenEmptyList_shouldReturnThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByCodes(Collections.emptyList());
    }

    @Test
    public void findAllByCodes_givenList_shouldReturnListIfBuyersExistWithGivenCodes() {
        final List<CompratoreEntity> entities = dao.findAllByCodes(Collections.singletonList("01"));
        assertEquals(1, entities.size());
        assertEquals("Compratore 01", entities.get(0).getDescrizione());
    }

    @Test
    public void findAllByIdItems() {
        final List<CompratoreEntity> result = dao.findAllByIdItems(Arrays.asList(1L, 2L));
        assertEquals(2, result.size());
    }

    @Test
    public void findAllByIdItems_givenNullIdItems_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByIdItems(null);
    }

    @Test
    public void findAllByIdItems_givenEmptyIdItems_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByIdItems(Collections.emptyList());
    }
}
