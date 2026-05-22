package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.GroupDAO;
import com.axiante.mui.persistence.entity.GroupEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GroupDAOTest extends DaoTest {
	@Inject
	GroupDAO dao;

//	@Spy
//	@InjectMocks
//	JpaGroupDAOImpl groupDAO;
//
	@Rule
	public WeldInitiator weld  = WeldInitiator
		.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaGroupDAOImpl.class)
		.activate(RequestScoped.class).inject(this).build();

	@Test
	public void persist_givenValidEntity_shouldSaveToDB() {
		final GroupEntity entity = createEntity("GR-1", "GRUPPO 1");
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			int before = dao.findAll().size();
			dao.persist(entity);
			int after = dao.findAll().size();
			em.getTransaction().commit();
			assertEquals(before + 1, after);
		}
	}

	@Test
	public void persist_givenExistingEntity_shouldNotSaveToDB() {
		final GroupEntity entity = createEntity("GR-1", "GRUPPO 1");
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.persist(entity);
			int before = dao.findAll().size();
			dao.persist(entity);
			int after = dao.findAll().size();
			em.getTransaction().commit();
			assertEquals(before, after);
		}
	}

	@Test
	public void findAll_shouldReturnAllEntitiesInDB() {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			int before = dao.findAll().size();
			createAndSave(10);
			int after = dao.findAll().size();
			em.getTransaction().commit();
			assertEquals(before + 10, after);
		}
	}

	@Test
	public void remove_givenExistingEntity_shouldRemoveIt() {
		if (!em.getTransaction().isActive()) {
			final GroupEntity entity1 = createEntity("GR-1", "GRUPPO 1");
			final GroupEntity entity2 = createEntity("GR-2", "GRUPPO 2");
			em.getTransaction().begin();
			em.persist(entity1);
			em.persist(entity2);
			int before = dao.findAll().size();
			dao.remove(entity1);
			int after = dao.findAll().size();
			em.getTransaction().commit();
			assertEquals(before - 1, after);
		}
	}

	@Test
	public void remove_givenNotExistingEntity_shouldNotRemoveIt() {
		if (!em.getTransaction().isActive()) {
			final GroupEntity entity1 = createEntity("GR-1", "GRUPPO 1");
			final GroupEntity entity2 = createEntity("GR-2", "GRUPPO 2");
			em.getTransaction().begin();
			em.persist(entity1);
			int before = dao.findAll().size();
			dao.remove(entity2);
			int after = dao.findAll().size();
			em.getTransaction().commit();
			assertEquals(before, after);
		}
	}

	@Test
	public void findById_givenExistingId_shouldReturnAssociatedEntity() {
		if (!em.getTransaction().isActive()) {
			final GroupEntity entity1 = createEntity("GR-1", "GRUPPO 1");
			final GroupEntity entity2 = createEntity("GR-2", "GRUPPO 2");
			em.getTransaction().begin();
			em.persist(entity1);
			em.persist(entity2);
			final GroupEntity byId = dao.findById(entity1.getId());
			em.getTransaction().commit();
			assertNotNull(byId);
			assertEquals("GR-1", byId.getCodiceGruppo());
			assertEquals("GRUPPO 1", byId.getDescrizione());
		}
	}

	@Test
	public void findById_givenNotExistingId_shouldReturnNull() {
		if (!em.getTransaction().isActive()) {
			final GroupEntity entity1 = createEntity("GR-1", "GRUPPO 1");
			final GroupEntity entity2 = createEntity("GR-2", "GRUPPO 2");
			em.getTransaction().begin();
			em.persist(entity1);
			em.persist(entity2);
			final GroupEntity byId = dao.findById(999);
			em.getTransaction().commit();
			assertNull(byId);
		}
	}

	@Test(expected = NullPointerException.class)
	public void countAccessTotaleByCodiciGruppoAndTipoNotNull_givenNullCodiciGruppo_shouldThrowException() {
		dao.countAccessTotaleByCodiciGruppoAndTipoNotNull(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void countAccessTotaleByCodiciGruppoAndTipoNotNull_givenEmptyCodiciGruppo_shouldThrowException() {
		dao.countAccessTotaleByCodiciGruppoAndTipoNotNull(Collections.emptyList());
	}

	@Test
	@Ignore("When tipo accesso is null, is saved at DB as READ; see SecurityEnumConverter")
	public void countAccessTotaleByCodiciGruppoAndTipoNotNull_givenCodiciGruppoAndSecurity_shouldReturnCountTotale() {
		final GroupEntity entity1 = createEntity("GR-1", "GRUPPO 1", PianificazioneSecurityEnum.READ);
		final GroupEntity entity2 = createEntity("GR-2", "GRUPPO 2", PianificazioneSecurityEnum.WRITE);
		final GroupEntity entity3 = createEntity("GR-X", "GRUPPO X");
		final GroupEntity entity4 = createEntity("GR-Y", "GRUPPO Y");
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.persist(entity1);
			em.persist(entity2);
			em.persist(entity3);
			em.persist(entity4);
			em.getTransaction().commit();
		}
		assertEquals(1, (long) dao.countAccessTotaleByCodiciGruppoAndTipoNotNull(Arrays.asList("GR-1", "GR-X")));
		assertEquals(0, (long) dao.countAccessTotaleByCodiciGruppoAndTipoNotNull(Arrays.asList("GR-X", "GR-Y")));
	}

	@Test(expected = NullPointerException.class)
	public void countWriteableAccessTotaleByCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
		dao.countWriteableAccessTotaleByCodiciGruppo(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void countWriteableAccessTotaleByCodiciGruppo_givenEmptyCodiciGruppo_shouldThrowException() {
		dao.countWriteableAccessTotaleByCodiciGruppo(Collections.emptyList());
	}

	@Test
	public void countWriteableAccessTotaleByCodiciGruppo_givenCodiciGruppoAndSecurity_shouldReturnCountTotale() {
		final GroupEntity entity1 = createEntity("GR-1", "GRUPPO 1", PianificazioneSecurityEnum.READ);
		final GroupEntity entity2 = createEntity("GR-2", "GRUPPO 2", PianificazioneSecurityEnum.WRITE);
		final GroupEntity entity3 = createEntity("GR-X", "GRUPPO X");
		final GroupEntity entity4 = createEntity("GR-Y", "GRUPPO Y");
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.persist(entity1);
			em.persist(entity2);
			em.persist(entity3);
			em.persist(entity4);
			em.getTransaction().commit();
		}
		assertEquals(0, (long) dao.countWriteableAccessTotaleByCodiciGruppo(Arrays.asList("GR-1", "GR-X")));
		assertEquals(1, (long) dao.countWriteableAccessTotaleByCodiciGruppo(Arrays.asList("GR-1", "GR-2")));
		assertEquals(0, (long) dao.countWriteableAccessTotaleByCodiciGruppo(Arrays.asList("GR-X", "GR-Y")));
	}

	private void createAndSave(int size) {
		for (int i = 1; i <= size; i++) {
			final GroupEntity entity = createEntity("GR-" + i, "GRUPPO " + i);
			em.persist(entity);
		}
	}

	private GroupEntity createEntity(String code, String desc) {
		final GroupEntity entity = new GroupEntity();
		entity.setCodiceGruppo(code);
		entity.setDescrizione(desc);
		return entity;
	}

	private GroupEntity createEntity(String code, String desc, PianificazioneSecurityEnum security) {
		final GroupEntity entity = createEntity(code, desc);
		entity.setAccessoTotale(security);
		return entity;
	}
}
