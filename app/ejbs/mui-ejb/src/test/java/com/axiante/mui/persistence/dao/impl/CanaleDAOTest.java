package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.CanaleDAO;
import com.axiante.mui.persistence.entity.CanaleEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CanaleDAOTest extends DaoTest {

    @Inject
    CanaleDAO dao;

    @Spy
    @InjectMocks
    JpaCanaleDAOImpl canaleDAO;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaCanaleDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Test
    public void persist_givenValidEntity_shouldSaveToDB() {
        final CanaleEntity entity = createEntity(42L, "CH-42");
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
        final CanaleEntity entity = createEntity(42L, "CH-42");
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
            final CanaleEntity entity1 = createEntity(42L, "CH-42");
            final CanaleEntity entity2 = createEntity(69L, "CH-69");
            em.getTransaction().begin();
            em.persist(entity1);
            em.persist(entity2);
            int before = dao.findAll().size();
            dao.remove(entity2);
            int after = dao.findAll().size();
            em.getTransaction().commit();
            assertEquals(before - 1, after);
        }
    }

    @Test
    public void remove_givenNotExistingEntity_shouldNotRemoveIt() {
        if (!em.getTransaction().isActive()) {
            final CanaleEntity entity1 = createEntity(42L, "CH-42");
            final CanaleEntity entity2 = createEntity(69L, "CH-69");
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
    public void persist_givenEntities_shouldSaveThemToDB() {
        final List<CanaleEntity> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(createEntity((long) i, "CH-" + i));
        }
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
            int before = dao.findAll().size();
            dao.persist(list);
            int after = dao.findAll().size();
            em.getTransaction().commit();
            assertEquals(before + 10, after);
        }
    }

    @Test(expected = NullPointerException.class)
    public void persist_givenNullList_shouldThrowException() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
            dao.persist((Collection<CanaleEntity>) null);
            em.getTransaction().commit();
        }
    }

    private void createAndSave(int size) {
        for (int i = 1; i <= size; i++) {
            final CanaleEntity entity = createEntity((long) i, "CH-" + i);
            em.persist(entity);
        }
    }

    private CanaleEntity createEntity(Long code, String desc) {
        final CanaleEntity entity = new CanaleEntity();
        entity.setId(code.intValue());
        entity.setCodiceCanale(code);
        entity.setDescrizione(desc);
        entity.setSigla(String.valueOf(code));
        return entity;
    }
}
