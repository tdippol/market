package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.GrmDAO;
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
public class JpaGrmDAOTest extends AbstractDaoTest {

    @Inject
    private GrmDAO grmDAO;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaGrmDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private GrmEntity grm;

    @Before
    public void setUp() throws Exception {
        RepartoEntity repartoEntity = createRepartoEntity();
        CategoriaEntity categoriaEntity = createCategoriaEntity(repartoEntity);
        grm = createGrmEntity(categoriaEntity);

        openTransaction();
        grmDAO.persist(grm);
        commitTransaction();
    }

    @Test
    public void shouldFindById() {
        final GrmEntity grm = grmDAO.findById(999L);
        assertNotNull(grm);
        assertFalse(grm.getCodiceGrm().isEmpty());
    }

    @Test
    public void shouldFindByCode() {
        final GrmEntity grm = grmDAO.findByCode("7GG");
        assertNotNull(grm);
        assertFalse(grm.getCodiceGrm().isEmpty());
    }

    @Test
    public void shouldNotFindByWrongCode() {
        final GrmEntity grm = grmDAO.findByCode("NO1");
        assertNull(grm);
    }

    @Test
    public void shouldReadAll() {
        List<GrmEntity> grmList = grmDAO.findAll();
        assertNotNull(grmList);
        assertFalse(grmList.isEmpty());
    }

    @Test
    public void shouldFindAllOrderedBy() {
        List<GrmEntity> grms = grmDAO.findAllOrderedBy();
        assertNotNull(grms);
        assertFalse(grms.isEmpty());
        assertEquals(grms.size(), 1);
        assertNotNull(grms.get(0));
        assertEquals(grm.getId(), grms.get(0).getId());
        assertEquals(grm.getKey(), grms.get(0).getCodiceGrm());
        assertEquals(grm.getLabel(), String.format("%s - %s", grms.get(0).getCodiceGrm(), grms.get(0).getDescrizione()));
        assertEquals(grm.getCodiceGrm(), grms.get(0).getCodiceGrm());
        assertNotNull(grms.get(0).getMuiCategoria());
        CategoriaEntity categoriaEntity = grms.get(0).getMuiCategoria();
        assertEquals(grm.getMuiCategoria().getId(), categoriaEntity.getId());
        assertEquals(grm.getMuiCategoria().getCodiceCategoria(), categoriaEntity.getCodiceCategoria());
        assertNotNull(categoriaEntity.getRepartoEntity());
        RepartoEntity repartoEntity = categoriaEntity.getRepartoEntity();
        assertEquals(grm.getMuiCategoria().getRepartoEntity().getId(), repartoEntity.getId());
        assertEquals(grm.getMuiCategoria().getRepartoEntity().getCodiceReparto(), repartoEntity.getCodiceReparto());
    }

    @Test
    public void findAllByCodiciCompratore_givenNullCodiciCompratore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        grmDAO.findAllByCodiciCompratore(null);
    }

    @Test
    public void findAllByCodiciCompratore_givenEmptyCodiciCompratore_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        grmDAO.findAllByCodiciCompratore(Collections.emptyList());
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
        List<GrmEntity> grms = grmDAO.findAllByCodiciCompratore(Arrays.asList("S01", "S02"));
        List<String> grmsCodes = grms.stream().map(GrmEntity::getCodiceGrm)
                .collect(Collectors.toList());
        assertEquals(2, grmsCodes.size());
        assertTrue(grmsCodes.contains("G01"));
        assertTrue(grmsCodes.contains("G02"));
        grms = grmDAO.findAllByCodiciCompratore(Arrays.asList("S10", "S42"));
        assertEquals(0, grms.size());
        grms = grmDAO.findAllByCodiciCompratore(Arrays.asList("S10", "S03"));
        grmsCodes = grms.stream().map(GrmEntity::getCodiceGrm).collect(Collectors.toList());
        assertEquals(1, grmsCodes.size());
        assertFalse(grmsCodes.contains("G01"));
        assertTrue(grmsCodes.contains("G02"));
    }

    private GrmEntity createGrmEntity(CategoriaEntity categoria) {
        GrmEntity grm = new GrmEntity();
        grm.setId(999L);
        grm.setCodiceGrm("7GG");
        grm.setMuiCategoria(categoria);
        return grm;
    }

    private CategoriaEntity createCategoriaEntity(RepartoEntity reparto) {
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setId(999L);
        categoria.setCodiceCategoria("111");
        categoria.setRepartoEntity(reparto);
        return categoria;
    }

    private RepartoEntity createRepartoEntity() {
        RepartoEntity reparto = new RepartoEntity();
        reparto.setId(999L);
        reparto.setCodiceReparto("01");
        return reparto;
    }

}
