package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.AssortimentoFornitoreDAO;
import com.axiante.mui.dbpromo.persistence.entities.AssortimentoFornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.entities.SubGrmEntity;
import com.axiante.mui.dbpromo.persistence.listener.ReadOnlyListenerImpl;
import java.util.List;
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
public class JpaAssortimentoFornitoreDAOImplTest extends AbstractDaoTest {

	@Inject
	private AssortimentoFornitoreDAO dao;

	@Rule
	public WeldInitiator weld = WeldInitiator
			.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
					JpaAssortimentoFornitoreDAOImpl.class, ReadOnlyListenerImpl.class, DbPromo.class)
			.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		ResponsabileEntity responsabileEntity = createResponsabileEntity();
		CompratoreEntity compratoreEntity = createCompratoreEntity("01", 2L, responsabileEntity);
		RepartoEntity repartoEntity = createRepartoEntity("R2", (long) 11);
		CategoriaEntity categoria = createCategoriaEntity("C1", "TEST CATEGORIA", repartoEntity);
		GrmEntity grmEntity = createGrmEntity(categoria);
		SubGrmEntity subGrmEntity = createSubGrmEntity(grmEntity);
		ItemEntity itemEntity = createItemEntity(100, compratoreEntity, subGrmEntity);
		AssortimentoFornitoreEntity assortimentoFornitore = createAssortimentoFornitore(itemEntity);
		openTransaction();
		dao.persist(assortimentoFornitore);
		commitTransaction();
	}

	@Test
	public void shouldFindById() {
		final AssortimentoFornitoreEntity assortimentoFornitore = dao.findById(2L);
		assertNotNull(assortimentoFornitore);
	}

	@Test
	public void shouldReadAll() {
		List<AssortimentoFornitoreEntity> assortimentoFornitore = dao.findAll();
		assertNotNull(assortimentoFornitore);
		assertFalse(assortimentoFornitore.isEmpty());
	}

	private ItemEntity createItemEntity(long id, CompratoreEntity compratoreEntity, SubGrmEntity subGrmEntity) {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setId(id);
		itemEntity.setCompratoreEntity(compratoreEntity);
		itemEntity.setCodiceItem("2A");
		itemEntity.setSubGrmEntity(subGrmEntity);
		return itemEntity;
	}

	private CompratoreEntity createCompratoreEntity(String codiceCompratore, long id,
			ResponsabileEntity responsabileEntity) {
		CompratoreEntity compratoreEntity = new CompratoreEntity();
		compratoreEntity.setId(id);
		compratoreEntity.setCodiceCompratore(codiceCompratore);
		compratoreEntity.setDescrizione("descrizione");
		compratoreEntity.setFlagAttivo(1L);
		compratoreEntity.setResponsabileEntity(responsabileEntity);
		return compratoreEntity;
	}

	private ResponsabileEntity createResponsabileEntity() {
		ResponsabileEntity responsabileEntity = new ResponsabileEntity();
		responsabileEntity.setId(100L);
		responsabileEntity.setFlagAttivo(1L);
		responsabileEntity.setCodiceResponsabile("001");
		return responsabileEntity;
	}

	private AssortimentoFornitoreEntity createAssortimentoFornitore(ItemEntity itemEntity) {
		AssortimentoFornitoreEntity assortimentoFornitore = new AssortimentoFornitoreEntity();
		assortimentoFornitore.setId(2L);
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

	private SubGrmEntity createSubGrmEntity(GrmEntity grmEntity) {
		SubGrmEntity subGrm = new SubGrmEntity();
		subGrm.setId(1L);
		subGrm.setCodiceSubgrm("001");
		subGrm.setGrmEntity(grmEntity);
		return subGrm;
	}

	private GrmEntity createGrmEntity(CategoriaEntity categoria) {
		GrmEntity grm = new GrmEntity();
		grm.setId(1L);
		grm.setCodiceGrm("7GG");
		grm.setMuiCategoria(categoria);
		return grm;
	}

	private CategoriaEntity createCategoriaEntity(String codiceCategoria, String descrizione,
			RepartoEntity repartoEntity) {
		CategoriaEntity categoriaEntity = new CategoriaEntity();
		categoriaEntity.setId(2L);
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
