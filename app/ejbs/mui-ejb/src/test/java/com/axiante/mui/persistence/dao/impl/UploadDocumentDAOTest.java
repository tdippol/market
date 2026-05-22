package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.UploadDocumentDAO;
import com.axiante.mui.persistence.entity.UploadDocumentEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UploadDocumentDAOTest extends DaoTest {

    @Inject
    private UploadDocumentDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaUploadDocumentDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Test
    public void readAll_shouldReturnsAllEntities() {
        assertNotNull(dao.readAll());
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        final int before = dao.readAll().size();
        for (int i = 1; i <= 5; i++) {
            final UploadDocumentEntity entity = createUploadDocumentEntity(String.format("document-%03d.txt", i));
            em.persist(entity);
        }
        em.getTransaction().commit();
        final List<UploadDocumentEntity> documents = dao.readAll();
        assertEquals(5, documents.size() - before);
    }

    @Test
    public void delete_givenValidEntity_shouldRemoveIt() throws Exception {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        final UploadDocumentEntity entity = createUploadDocumentEntity("foo.txt");
        em.persist(entity);
        dao.delete(entity);
        assertTrue(dao.readAll().isEmpty());
        em.getTransaction().commit();
    }

    @Test
    public void persistWithAuditLog_givenValidEntity_shouldSaveIt() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        final UploadDocumentEntity entity = createUploadDocumentEntity("foo.txt");
        final UploadDocumentEntity savedEntity = dao.persistWithAuditLog(entity, "junit");
        assertNotNull(savedEntity);
        assertNotNull(savedEntity.getId());
        assertEquals("junit", savedEntity.getCodiceUtenteInserimento());
        assertNotNull(savedEntity.getDataInserimento());
        assertNull(savedEntity.getCodiceUtenteAggiornamento());
        assertNull(savedEntity.getDataAggiornamento());
        em.getTransaction().commit();
    }

    @Test
    public void persistWithAuditLog_givenExistingEntity_shouldUpdateIt() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        final Date date = new GregorianCalendar(2021, Calendar.JULY, 22).getTime();
        final UploadDocumentEntity entity = createUploadDocumentEntity(1, "foo.txt", "junit", date);
        em.persist(entity);
        final UploadDocumentEntity savedEntity = dao.persistWithAuditLog(entity, "foo-user");
        assertNotNull(savedEntity);
        assertEquals(1, (int) savedEntity.getId());
        assertEquals("junit", savedEntity.getCodiceUtenteInserimento());
        assertEquals("foo-user", savedEntity.getCodiceUtenteAggiornamento());
        assertEquals(date, savedEntity.getDataInserimento());
        assertNotNull(savedEntity.getDataAggiornamento());
        em.getTransaction().commit();
    }

    @Test
    public void findByName_givenExistingName_shouldReturnEntity() throws Exception {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        final UploadDocumentEntity entity = createUploadDocumentEntity("foo1.txt");
        em.persist(entity);
        assertNotNull(dao.findByName("foo1.txt"));
        em.getTransaction().commit();
    }

    @Test
    public void findByName_givenNotExistingName_shouldReturnNull() throws Exception {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        final UploadDocumentEntity entity = createUploadDocumentEntity("foo2.txt");
        em.persist(entity);
        assertNull(dao.findByName("bar.txt"));
        em.getTransaction().commit();
    }

    private UploadDocumentEntity createUploadDocumentEntity(int id, String name, String user, Date date) {
        final UploadDocumentEntity entity = createUploadDocumentEntity(name);
        entity.setId(id);
        entity.setCodiceUtenteInserimento(user);
        entity.setDataInserimento(date);
        return entity;
    }

    private UploadDocumentEntity createUploadDocumentEntity(String name) {
        final UploadDocumentEntity entity = new UploadDocumentEntity();
        entity.setName(name);
        return entity;
    }
}
