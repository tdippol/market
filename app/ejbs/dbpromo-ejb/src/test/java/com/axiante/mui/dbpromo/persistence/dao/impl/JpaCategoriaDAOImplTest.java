package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CategoriaDAO;
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
public class JpaCategoriaDAOImplTest extends AbstractDaoTest {


    @Inject
    private CategoriaDAO categoriaDAO;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaCategoriaDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();


    @Before
    public void setUp() throws Exception {
        CategoriaEntity categoriaEntity = createCategoriaEntity("CA1", "categoria 1",
                createRepartoEntity("1A", 999L));
        openTransaction();
        categoriaDAO.persist(categoriaEntity);
        commitTransaction();
    }

    @Test
    public void shouldFindById() {
        final CategoriaEntity categoriaEntity = categoriaDAO.findById(999L);
        assertNotNull(categoriaEntity);
        assertFalse(categoriaEntity.getCodiceCategoria().isEmpty());
        assertEquals(categoriaEntity.getCodiceCategoria(), "CA1");
        assertEquals(categoriaEntity.getDescrizione(), "categoria 1");

        assertNotNull(categoriaEntity.getRepartoEntity());
        assertEquals(categoriaEntity.getRepartoEntity().getCodiceReparto(), "1A");
    }

    @Test
    public void shouldReadAll() {
        List<CategoriaEntity> categoriaList = categoriaDAO.findAll();
        assertNotNull(categoriaList);
        assertFalse(categoriaList.isEmpty());
    }

    @Test
    public void findAllByCodiciCompratore_givenNullCodiciCompratore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        categoriaDAO.findAllByCodiciCompratore(null);
    }

    @Test
    public void findAllByCodiciCompratore_givenEmptyCodiciCompratore_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        categoriaDAO.findAllByCodiciCompratore(Collections.emptyList());
    }

    @Test
    public void findAllByCodiciCompratore_givenExistingCodiciCompratore_shouldReturnNotEmptyList() {
        ResponsabileEntity responsabile = new ResponsabileEntityBuilder().withCodice("R01").build();
        CompratoreEntity compratore1 = new CompratoreEntityBuilder().withCodice("S01").withResponsabile(responsabile).build();
        CompratoreEntity compratore2 = new CompratoreEntityBuilder().withCodice("S02").withResponsabile(responsabile).build();
        CompratoreEntity compratore3 = new CompratoreEntityBuilder().withCodice("S03").withResponsabile(responsabile).build();
        RepartoEntity reparto1 = new RepartoEntityBuilder().withCodice("R1").build();
        CategoriaEntity categoria1 = new CategoriaEntityBuilder().withCodice("C01").withReparto(reparto1).build();
        GrmEntity grm1 = new GrmEntityBuilder().withCodice("G01").withCategoria(categoria1).build();
        SubGrmEntity subGrm1 = new SubGrmEntityBuilder().withCodice("SG1").withGrm(grm1).build();
        RepartoEntity reparto2 = new RepartoEntityBuilder().withCodice("R2").build();
        CategoriaEntity categoria2 = new CategoriaEntityBuilder().withCodice("C02").withReparto(reparto2).build();
        GrmEntity grm2 = new GrmEntityBuilder().withCodice("G02").withCategoria(categoria2).build();
        SubGrmEntity subGrm2 = new SubGrmEntityBuilder().withCodice("SG1").withGrm(grm2).build();
        ItemEntity item1 = new ItemEntityBuilder().withId(1L).withCodice("IT-001").withSubGrm(subGrm1)
                .withCompratore(compratore1).build();
        ItemEntity item2 = new ItemEntityBuilder().withId(2L).withCodice("IT-002").withSubGrm(subGrm2)
                .withCompratore(compratore1).build();
        ItemEntity item3 = new ItemEntityBuilder().withId(3L).withCodice("IT-003").withSubGrm(subGrm2)
                .withCompratore(compratore3).build();
        ItemEntity item4 = new ItemEntityBuilder().withId(4L).withCodice("IT-004").withSubGrm(subGrm1)
                .withCompratore(compratore2).build();
        persist(item1, item2, item3, item4);
        List<CategoriaEntity> categorie = categoriaDAO.findAllByCodiciCompratore(Arrays.asList("S01", "S02"));
        List<String> categorieCodes = categorie.stream().map(CategoriaEntity::getCodiceCategoria).collect(Collectors.toList());
        assertEquals(2, categorieCodes.size());
        assertTrue(categorieCodes.contains("C01"));
        assertTrue(categorieCodes.contains("C02"));
        categorie = categoriaDAO.findAllByCodiciCompratore(Arrays.asList("S10", "S42"));
        assertEquals(0, categorie.size());
        categorie = categoriaDAO.findAllByCodiciCompratore(Arrays.asList("S10", "S03"));
        categorieCodes = categorie.stream().map(CategoriaEntity::getCodiceCategoria).collect(Collectors.toList());
        assertEquals(1, categorieCodes.size());
        assertFalse(categorieCodes.contains("C01"));
        assertTrue(categorieCodes.contains("C02"));
    }

    private CategoriaEntity createCategoriaEntity(String codiceCategoria, String descrizione, RepartoEntity repartoEntity) {
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(999L);
        categoriaEntity.setCodiceCategoria(codiceCategoria);
        categoriaEntity.setDescrizione(descrizione);
        categoriaEntity.setRepartoEntity(repartoEntity);
        return categoriaEntity;
    }

    private RepartoEntity createRepartoEntity(String codiceReparto, Long idReparto) {
        RepartoEntity repartoEntity = new RepartoEntity();
        repartoEntity.setId(idReparto);
        repartoEntity.setCodiceReparto(codiceReparto);
        return repartoEntity;
    }
}
