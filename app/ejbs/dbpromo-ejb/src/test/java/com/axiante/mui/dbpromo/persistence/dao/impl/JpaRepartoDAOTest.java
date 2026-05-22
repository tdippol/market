package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.RepartoDAO;
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
public class JpaRepartoDAOTest extends AbstractDaoTest {

    @Inject
    private RepartoDAO repartoDAO;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaRepartoDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private RepartoEntity reparto1 = createRepartoEntity("01", 999L);
    private RepartoEntity reparto2 = createRepartoEntity("00", 666L);

    @Before
    public void setUp() throws Exception {
        openTransaction();
        repartoDAO.persist(reparto1);
        repartoDAO.persist(reparto2);
        commitTransaction();
    }

    @Test
    public void shouldFindById() {
        final RepartoEntity reparto = repartoDAO.findById(999L);
        assertNotNull(reparto);
        assertFalse(reparto.getCodiceReparto().isEmpty());
        assertEquals(reparto.getCodiceReparto(), "01");
    }

    @Test
    public void shouldFindByCode() {
        final RepartoEntity reparto = repartoDAO.findByCode("01");
        assertNotNull(reparto);
        assertFalse(reparto.getCodiceReparto().isEmpty());
        assertEquals(reparto.getCodiceReparto(), "01");
    }

    @Test
    public void shouldNotFindByWrongCode() {
        final RepartoEntity reparto = repartoDAO.findByCode("08");
        assertNull(reparto);
    }

    @Test
    public void shouldReadAll() {
        List<RepartoEntity> repartoList = repartoDAO.findAll();
        assertNotNull(repartoList);
        assertFalse(repartoList.isEmpty());
    }

    @Test
    public void shouldFindAllOrderByCodiceReparto() {
        List<RepartoEntity> reparti = repartoDAO.findAllOrderedBy();
        assertNotNull(reparti);
        assertFalse(reparti.isEmpty());
        assertEquals(2, reparti.size());
        assertEquals(reparto2.getCodiceReparto(), reparti.get(0).getCodiceReparto());
        assertEquals(reparto2.getKey(), reparti.get(0).getCodiceReparto());
        assertEquals(reparto2.getId(), reparti.get(0).getId());
        assertEquals(reparto2.getLabel(), String.format("%s - %s", reparti.get(0).getCodiceReparto(), reparti.get(0).getDescrizione()));
        assertEquals(reparto1.getCodiceReparto(), reparti.get(1).getCodiceReparto());
        assertEquals(reparto1.getKey(), reparti.get(1).getCodiceReparto());
        assertEquals(reparto1.getId(), reparti.get(1).getId());
        assertEquals(reparto1.getLabel(), String.format("%s - %s", reparti.get(1).getCodiceReparto(), reparti.get(1).getDescrizione()));
    }

    @Test
    public void findAllByCodiciCompratore_givenNullCodiciCompratore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        repartoDAO.findAllByCodiciCompratore(null);
    }

    @Test
    public void findAllByCodiciCompratore_givenEmptyCodiciCompratore_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        repartoDAO.findAllByCodiciCompratore(Collections.emptyList());
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
        List<RepartoEntity> reparti = repartoDAO.findAllByCodiciCompratore(Arrays.asList("S01", "S02"));
        List<String> repartiCodes = reparti.stream().map(RepartoEntity::getCodiceReparto).collect(Collectors.toList());
        assertEquals(2, repartiCodes.size());
        assertTrue(repartiCodes.contains("R1"));
        assertTrue(repartiCodes.contains("R2"));
        reparti = repartoDAO.findAllByCodiciCompratore(Arrays.asList("S10", "S42"));
        assertEquals(0, reparti.size());
        reparti = repartoDAO.findAllByCodiciCompratore(Arrays.asList("S10", "S03"));
        repartiCodes = reparti.stream().map(RepartoEntity::getCodiceReparto).collect(Collectors.toList());
        assertEquals(1, repartiCodes.size());
        assertFalse(repartiCodes.contains("R1"));
        assertTrue(repartiCodes.contains("R2"));
    }

    private RepartoEntity createRepartoEntity(String codiceReparto, Long idReparto) {
        RepartoEntity reparto = new RepartoEntity();
        reparto.setId(idReparto);
        reparto.setCodiceReparto(codiceReparto);
        reparto.setDescrizione("descrizione");
        return reparto;
    }

}
