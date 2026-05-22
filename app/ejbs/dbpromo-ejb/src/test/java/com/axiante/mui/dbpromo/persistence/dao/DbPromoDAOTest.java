package com.axiante.mui.dbpromo.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.impl.JpaItemDAOImpl;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.entities.SubGrmEntity;
import java.math.BigDecimal;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DbPromoDAOTest extends AbstractDaoTest {

    @Inject
    private ItemDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaItemDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Before
    public void setUp() {
        final ResponsabileEntity responsabile = createResponsabileEntity();
        final CompratoreEntity compratore = createCompratoreEntity(responsabile);
        final RepartoEntity reparto = createRepartoEntity();
        final CategoriaEntity categoria = createCategoriaEntity(reparto);
        final GrmEntity grm = createGrmEntity(categoria);
        final SubGrmEntity subGrm = createSubGrmEntity(grm);
        final ItemEntity item = createItem(101L, "IT101");
        item.setCompratoreEntity(compratore);
        item.setSubGrmEntity(subGrm);
        openTransaction();
        dao.persist(item);
        commitTransaction();
    }

    @Test(expected = NullPointerException.class)
    public void findValueByAttribute_givenNullEntityName_shouldThrowException() {
        dao.findValueByAttribute(null, "baz", "foo", "bar");
    }

    @Test(expected = NullPointerException.class)
    public void findValueByAttribute_givenNullEntityAttribute_shouldThrowException() {
        dao.findValueByAttribute("ItemEntity", "baz", null, "bar");
    }

    @Test(expected = NullPointerException.class)
    public void findValueByAttribute_givenNullLookupAttribute_shouldThrowException() {
        dao.findValueByAttribute("ItemEntity", null, "foo", "bar");
    }

    @Test(expected = NullPointerException.class)
    public void findValueByAttribute_givenNullLookupValue_shouldThrowException() {
        dao.findValueByAttribute("ItemEntity", "baz", "foo", null);
    }

    @Test
    public void findValueByAttribute_givenInvalidEntityName_shouldReturnNull() {
        assertNull(dao.findValueByAttribute("FooEntity", "baz", "id", 1));
    }

    @Test
    public void findValueByAttribute_givenInvalidEntityAttribute_shouldReturnNull() {
        assertNull(dao.findValueByAttribute("ItemEntity", "baz", "id", 1));
    }

    @Test
    public void findValueByAttribute_givenInvalidLookupAttribute_shouldReturnNull() {
        assertNull(dao.findValueByAttribute("ItemEntity", "prezzoAttuale", "foo", 1));
    }

    @Test
    public void findValueByAttribute_givenValidEntityNameAndAttribute_shouldReturnValueForThatAttribute() {
        final Object value = dao.findValueByAttribute("ItemEntity", "prezzoAttuale", "id", 101);
        assertNotNull(value);
        assertEquals(new BigDecimal("42.42"), value);
    }

    private ItemEntity createItem(Long id, String code) {
        final ItemEntity entity = new ItemEntity();
        entity.setId(id);
        entity.setCodiceItem(code);
        entity.setPrezzoAttuale(new BigDecimal("42.42"));
        return entity;
    }

    private CompratoreEntity createCompratoreEntity(ResponsabileEntity responsabileEntity) {
        CompratoreEntity entity = new CompratoreEntity();
        entity.setId(100L);
        entity.setCodiceCompratore("001");
        entity.setFlagAttivo(1L);
        entity.setResponsabileEntity(responsabileEntity);
        return entity;
    }

    private ResponsabileEntity createResponsabileEntity() {
        ResponsabileEntity entity = new ResponsabileEntity();
        entity.setId(100L);
        entity.setFlagAttivo(1L);
        entity.setCodiceResponsabile("001");
        return entity;
    }

    private SubGrmEntity createSubGrmEntity(GrmEntity grmEntity) {
        SubGrmEntity entity = new SubGrmEntity();
        entity.setId(1L);
        entity.setCodiceSubgrm("001");
        entity.setGrmEntity(grmEntity);
        return entity;
    }

    private GrmEntity createGrmEntity(CategoriaEntity categoriaEntity) {
        GrmEntity entity = new GrmEntity();
        entity.setId(1L);
        entity.setCodiceGrm("7GG");
        entity.setMuiCategoria(categoriaEntity);
        return entity;
    }

    private CategoriaEntity createCategoriaEntity(RepartoEntity repartoEntity) {
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setId(1L);
        categoria.setCodiceCategoria("111");
        categoria.setRepartoEntity(repartoEntity);
        return categoria;
    }

    private RepartoEntity createRepartoEntity() {
        RepartoEntity reparto = new RepartoEntity();
        reparto.setId(2L);
        reparto.setCodiceReparto("01");
        reparto.setCodiceRepartoPadre("02");
        return reparto;
    }
}
