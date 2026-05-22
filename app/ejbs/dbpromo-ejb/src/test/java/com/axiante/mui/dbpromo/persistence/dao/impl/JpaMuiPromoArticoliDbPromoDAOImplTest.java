package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MuiPromoArticoliDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoArticoliDbPromoId;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.entities.SubGrmEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class JpaMuiPromoArticoliDbPromoDAOImplTest extends AbstractDaoTest {

    @Inject
    private MuiPromoArticoliDbPromoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
                    EntityManagerFactoryProducer.class, JpaMuiPromoArticoliDbPromoDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class)
            .inject(this).build();

    @Before
    public void setUp() throws Exception {
        final CompratoreEntity compratore = createCompratore(1L);
        final ItemEntity item1 = createItem(1L, compratore);
        final ItemEntity item2 = createItem(2L, compratore);
        final ItemEntity item3 = createItem(3L, compratore);
        final ItemEntity item4 = createItem(4L, compratore);
        persist(item1, item2, item3, item4);
        List<MuiPromoArticoliDbPromoEntity> entities = new ArrayList<>();
        entities.add(createEntity("ABC", "A", "C01", item1));
        entities.add(createEntity("ABC", "A", "C01", item2));
        entities.add(createEntity("ABC", "A", "C02", item3));
        entities.add(createEntity("XYZ", "A", "C01", item1));
        entities.add(createEntity("XYZ", "A", "C03", item4));
        persist(entities);
    }

    @Test
    public void findAll_shouldReturnAllEntities() {
        final List<MuiPromoArticoliDbPromoEntity> entities = dao.findAll();
        assertEquals(5, entities.size());
        final List<String> codiciPromozione = entities.stream().map(e -> e.getId().getCodicePromozione())
                .distinct().collect(Collectors.toList());
        assertEquals(2, codiciPromozione.size());
        assertEquals("ABC", codiciPromozione.get(0));
        assertEquals("XYZ", codiciPromozione.get(1));
    }

    @Test(expected = NullPointerException.class)
    public void findAllByCodicePromozione_givenNullCodicePromozione_shouldThrowException() {
        dao.findAllByCodicePromozione(null);
    }

    @Test
    public void findAllByCodicePromozione_shouldReturnAllFilteredEntities() {
        List<MuiPromoArticoliDbPromoEntity> entities = dao.findAllByCodicePromozione("ABC");
        assertEquals(3, entities.size());
        final List<String> codiciPromozione = entities.stream().map(e -> e.getId().getCodicePromozione())
                .distinct().collect(Collectors.toList());
        assertEquals(1, codiciPromozione.size());
        assertEquals("ABC", codiciPromozione.get(0));
        entities = dao.findAllByCodicePromozione("AAA");
        assertTrue(entities.isEmpty());
    }

    private MuiPromoArticoliDbPromoEntity createEntity(String codicePromozione, String tipoItem, String codiceCompratore,
                                                       ItemEntity itemEntity) {
        final MuiPromoArticoliDbPromoId id = new MuiPromoArticoliDbPromoId(codicePromozione, itemEntity.getId(), tipoItem);
        final MuiPromoArticoliDbPromoEntity entity = new MuiPromoArticoliDbPromoEntity();
        entity.setId(id);
        entity.setCodiceCompratore(codiceCompratore);
        entity.setItem(itemEntity);
        return entity;
    }

    private ItemEntity createItem(Long id, CompratoreEntity compratore) {
        final RepartoEntity reparto = new RepartoEntity();
        reparto.setCodiceReparto(String.valueOf(id));
        final CategoriaEntity categoria = new CategoriaEntity();
        categoria.setCodiceCategoria(String.valueOf(id));
        categoria.setRepartoEntity(reparto);
        final GrmEntity grm = new GrmEntity();
        grm.setCodiceGrm(String.valueOf(id));
        grm.setMuiCategoria(categoria);
        final SubGrmEntity subGrm = new SubGrmEntity();
        subGrm.setCodiceSubgrm(String.valueOf(id));
        subGrm.setGrmEntity(grm);
        final ItemEntity item = new ItemEntity();
        item.setId(id);
        item.setCodiceItem(String.valueOf(id));
        item.setDescrizione(String.format("ITEM %05d", id));
        item.setCompratoreEntity(compratore);
        item.setSubGrmEntity(subGrm);
        return item;
    }

    private CompratoreEntity createCompratore(Long id) {
        final ResponsabileEntity responsabile = new ResponsabileEntity();
        responsabile.setId(id);
        responsabile.setCodiceResponsabile(String.format("R%02d", id));
        responsabile.setFlagAttivo(1L);
        final CompratoreEntity compratore = new CompratoreEntity();
        compratore.setId(id);
        compratore.setCodiceCompratore(String.format("C%02d", id));
        compratore.setFlagAttivo(1L);
        compratore.setResponsabileEntity(responsabile);
        return compratore;
    }

    private void persist(List<MuiPromoArticoliDbPromoEntity> entities) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
            for (MuiPromoArticoliDbPromoEntity e : entities) {
                e = em.merge(e);
                em.persist(e);
            }
            em.getTransaction().commit();
        }
    }
}