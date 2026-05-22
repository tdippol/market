package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class JpaItemDAOTest extends AbstractDaoTest {

    @Inject
    private ItemDAO itemDAO;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaItemDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private ItemEntity item;
    private CompratoreEntity compratore;

    @Before
    public void setUp() throws Exception {
        item = new ItemEntity();
        item.setId(101L);

        ResponsabileEntity responsabile = createResponsabileEntity();
        compratore = createCompratoreEntity(responsabile);
        RepartoEntity repartoEntity = createRepartoEntity();
        CategoriaEntity categoria = createCategoriaEntity(repartoEntity);
        GrmEntity grmEntity = createGrmEntity(categoria);
        SubGrmEntity subGrm = createSubGrmEntity(grmEntity);

        item.setCompratoreEntity(compratore);
        item.setCodiceItem("ART-00");
        item.setSubGrmEntity(subGrm);
        item.setMuiAssortimentoFornitores(createAssortimentoFornitoreSet(item));

        openTransaction();
        itemDAO.persist(item);
        commitTransaction();
    }

    @Test
    public void shouldFindById() {
        final ItemEntity item = itemDAO.findById(101L);
        assertNotNull(item);
        assertFalse(item.getCodiceItem().isEmpty());
    }

    @Test
    public void shouldFindByCode() {
        final ItemEntity item = itemDAO.findByCode("ART-00");
        assertNotNull(item);
        assertFalse(item.getCodiceItem().isEmpty());
    }

    @Test
    public void shouldNotFindByWrongCode() {
        final ItemEntity code = itemDAO.findByCode("ART-NO");
        assertNull(code);
    }

    @Test
    public void shouldReadAll() {
        List<ItemEntity> items = itemDAO.findAll();
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    @Test
    public void shouldFindAllItemsByCompratore() {
        List<ItemEntity> items = itemDAO.findAllByCompratore(null);
        assertNotNull(items);
        assertTrue(items.isEmpty());
        items = itemDAO.findAllByCompratore(compratore);
        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertEquals(1, items.size());
        assertEquals(compratore.getId(), items.get(0).getCompratoreEntity().getId());
        assertEquals(compratore.getCodiceCompratore(),
                items.get(0).getCompratoreEntity().getCodiceCompratore());
        assertEquals(compratore.getFlagAttivo(), items.get(0).getCompratoreEntity().getFlagAttivo());
        assertEquals(compratore.getResponsabileEntity().getId(),
                items.get(0).getCompratoreEntity().getResponsabileEntity().getId());
        assertEquals(compratore.getResponsabileEntity().getFlagAttivo(),
                items.get(0).getCompratoreEntity().getResponsabileEntity().getFlagAttivo());
        assertEquals(compratore.getResponsabileEntity().getCodiceResponsabile(),
                items.get(0).getCompratoreEntity().getResponsabileEntity().getCodiceResponsabile());
    }

    @Test
    public void shoudFindAllByFilter() {
        List<ItemEntity> items = itemDAO.findAllByFilter(item.getCompratoreEntity().getId(),
                item.getMuiAssortimentoFornitores().iterator().next().getFornitoreEntity().getId(),
                item.getSubGrmEntity().getGrmEntity().getMuiCategoria().getRepartoEntity().getId(),
                item.getSubGrmEntity().getGrmEntity().getMuiCategoria().getId(),
                item.getSubGrmEntity().getGrmEntity().getId(), item.getCodiceMarchioPrivato());
        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertEquals(item.getId(), items.get(0).getId());
        assertEquals(item.getKey(), items.get(0).getCodiceItem());
        assertEquals(item.getLabel(), String.format("%s - %s", items.get(0).getCodiceItem(), items.get(0).getDescrizione()));
        assertEquals(item.getCompratoreEntity().getId(), items.get(0).getCompratoreEntity().getId());
        assertEquals(item.getCodiceItem(), items.get(0).getCodiceItem());
        assertEquals(item.getSubGrmEntity().getId(), items.get(0).getSubGrmEntity().getId());
        assertEquals(item.getMuiAssortimentoFornitores().iterator().next().getFornitoreEntity().getId(),
                items.get(0).getMuiAssortimentoFornitores().iterator().next().getFornitoreEntity().getId());
    }

    private SubGrmEntity createSubGrmEntity(GrmEntity grmEntity) {
        SubGrmEntity subGrm = new SubGrmEntity();
        subGrm.setId(1L);
        subGrm.setCodiceSubgrm("001");
        subGrm.setGrmEntity(grmEntity);
        return subGrm;
    }

    private GrmEntity createGrmEntity(CategoriaEntity categoriaEntity) {
        GrmEntity grm = new GrmEntity();
        grm.setId(1L);
        grm.setCodiceGrm("7GG");
        grm.setMuiCategoria(categoriaEntity);
        return grm;
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

    private CompratoreEntity createCompratoreEntity(ResponsabileEntity responsabileEntity) {
        CompratoreEntity compratore = new CompratoreEntity();
        compratore.setId(100L);
        compratore.setCodiceCompratore("001");
        compratore.setFlagAttivo(1L);
        compratore.setResponsabileEntity(responsabileEntity);
        return compratore;
    }

    private ResponsabileEntity createResponsabileEntity() {
        ResponsabileEntity responsabile = new ResponsabileEntity();
        responsabile.setId(100L);
        responsabile.setFlagAttivo(1L);
        responsabile.setCodiceResponsabile("001");
        return responsabile;
    }

    private Set<AssortimentoFornitoreEntity> createAssortimentoFornitoreSet(ItemEntity itemEntity) {
        Set<AssortimentoFornitoreEntity> assortimentoFornitoreSet = new HashSet<>();
        assortimentoFornitoreSet.add(createAssortimentoFornitore(itemEntity));
        return assortimentoFornitoreSet;
    }

    private AssortimentoFornitoreEntity createAssortimentoFornitore(ItemEntity itemEntity) {
        AssortimentoFornitoreEntity assortimentoFornitore = new AssortimentoFornitoreEntity();
        assortimentoFornitore.setId(765L);
        assortimentoFornitore.setFornitorePrincipale(987L);
        assortimentoFornitore.setFornitoreEntity(createFornitore("FF220", "fornitore 2"));
        assortimentoFornitore.setItemEntity(itemEntity);
        return assortimentoFornitore;
    }

    private FornitoreEntity createFornitore(String codiceFornitore, String descrizione) {
        FornitoreEntity fornitore = new FornitoreEntity();
        fornitore.setId(21L);
        fornitore.setCodiceFornitore(codiceFornitore);
        fornitore.setDescrizione(descrizione);
        return fornitore;
    }
}
