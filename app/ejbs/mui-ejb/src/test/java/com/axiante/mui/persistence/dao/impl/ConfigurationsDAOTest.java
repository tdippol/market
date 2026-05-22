package com.axiante.mui.persistence.dao.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.contains;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.ConfigurationsDAO;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;

public class ConfigurationsDAOTest extends DaoTest {

    @Mock
    ConfigurationEntity mockedEntity;
    @Spy
    @InjectMocks
    JpaConfigurationsDAO mockedDao;
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Inject
    ConfigurationsDAO dao;

    private static final Object readWriteSemaphore = new Object();
    @Rule
    public WeldInitiator weld = WeldInitiator
    .from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaConfigurationsDAO.class)
    .activate(RequestScoped.class).inject(this).build();

    @Test
    public void testReadReturnsNonEmptyData() {
        assertNotNull(dao.readAll());
    }

    @Test
    public void testUpsert() throws Exception {
        synchronized (readWriteSemaphore) {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            try {

                ConfigurationEntity entity = createEntity();
                final String path = entity.getPath();

                entity = dao.persist(entity);
                assertNotNull(entity);
                assertThat(entity.getPath(), equalTo(path));

                entity.setPath("test changed");

                entity = dao.persist(entity);
                assertNotNull(entity);
                assertNotEquals(path,entity.getPath());
            } finally {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            }			
        }
    }

    @Test
    public void testRemove() throws Exception {
        synchronized (readWriteSemaphore) {
            final ConfigurationEntity entity = dao.readAll().stream().findAny().orElse(null);
            assertNotNull(entity);
            final List<ConfigurationEntity> list = dao.readAll();
            final int size = list.size();
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            try {
                dao.remove(entity);
                final List<ConfigurationEntity> result = dao.readAll();
                final List<ConfigurationEntity> removed = new ArrayList<>(); 
                for ( final ConfigurationEntity e : list ) {
                    if ( !result.contains(e)) {
                        removed.add(e);
                    }
                }
                assertThat(size, equalTo(result.size() + removed.size()));
                assertThat(removed.size(), equalTo(1));
            } finally {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            }
        }
    }

    @Test public void testFindById() {
        synchronized (readWriteSemaphore) {
            final ConfigurationEntity entity = dao.readAll().stream().findAny().orElse(null);
            assertNotNull(entity);
            final ConfigurationEntity test = dao.findById(entity.getIdConfiguration());
            assertThat(entity, equalTo(test));
        }

    }

    @Test public void testFindByIdReturnsNull() {
        synchronized (readWriteSemaphore) {
            final ConfigurationEntity test = dao.findById(100000000);
            assertNull(test);
        }
    }

    @Test public void testFindByType() {
        final List<ConfigurationTypes> types = dao.readAll().stream().map(ConfigurationEntity::getType).distinct().collect(Collectors.toList());
        assertNotNull(types);
        final ConfigurationTypes test = types.stream().findAny().orElse(null);
        final List<ConfigurationEntity> entityList = dao.findByType(test);
        assertNotNull(entityList);
        assertThat(test, equalTo(
                entityList.stream().map(ConfigurationEntity::getType).distinct().findAny().orElse(null)
                ));

    }
    //    @Test public void testFindByTypeReturnsNothing() {
    //        final ConfigurationTypes test = null;
    //        final List<ConfigurationEntity> entityList = dao.findByType(test);
    //        assertNotNull(entityList);
    //
    //        assertThat(test, not(equalTo(
    //                entityList.stream().map(ConfigurationEntity::getType).distinct().findAny().orElse(null)
    //                )));
    //
    //        assertThat(entityList.size(), equalTo(0));
    //    }

    @Test public void testFindByPath() {
        final List<ConfigurationEntity> types = dao.readAll().stream().distinct().collect(Collectors.toList());
        assertNotNull(types);
        final ConfigurationEntity test = types.stream().findAny().orElse(null);
        assertNotNull(test);
        final ConfigurationEntity entity = dao.findByPath(test.getPath(), test.getType());
        assertNotNull(entity);
        assertThat(test, equalTo(entity));
    }

    @Test public void testFindByPathReturnsNothing() {
        final List<ConfigurationEntity> types = dao.readAll().stream().distinct().collect(Collectors.toList());
        assertNotNull(types);
        final ConfigurationEntity test = types.stream().findAny().orElse(null);
        assertNotNull(test);
        final ConfigurationEntity entity = dao.findByPath(test.getPath(), null);
        assertNull(entity);

    }

    @Test public void testPersistThrowsException() throws Exception {
        final ConfigurationEntity entity = createEntity();
        ex.expect(Exception.class);
        ex.expectMessage(contains("già presente"));
        mockedDao.persist(entity);
    }

    @Test public void testRemoveThrowsException() throws Exception{
        Mockito.doThrow(PersistenceException.class).when(mockedEm).merge(ArgumentMatchers.any());
        final ConfigurationEntity entity = createEntity();
        ex.expect(Exception.class);
        ex.expectMessage(contains("Impossibile rimuovere"));
        mockedDao.remove(entity);
    }

    private ConfigurationEntity createEntity() {
        synchronized (this) {
            final ConfigurationEntity e = new ConfigurationEntity();
            e.setJson("{}");
            e.setPath("/path");
            e.setType(ConfigurationTypes.GLOBAL);

            return e;
        }
    }
}
