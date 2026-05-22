package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.filter.IngridFilter;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.UsersDAO;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.StringContains;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;

import javax.annotation.concurrent.NotThreadSafe;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.contains;

@Slf4j
@NotThreadSafe
public class UsersDAOIntegrationTest extends DaoTest {

    @Inject
    UsersDAO usersDAO;

    @Spy
    @InjectMocks
    JpaUsersDAO mockedDAO;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaUsersDAO.class)
            .activate(RequestScoped.class).inject(this).build();

    @Test
    public void readAll() {
        try {
            assertNotNull(this.usersDAO.readAll());
        } catch (final Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPersist() throws Exception {
        synchronized (this.em) {
            try {
                if (!this.em.getTransaction().isActive()) {
                    this.em.getTransaction().begin();
                }
                // Add User
                UsersEntity users = new UsersEntity();
                users.setName("USER_NAME");
                final RolesEntity role = this.em.find(RolesEntity.class, 1);


                users.addRole(role);
                users = this.usersDAO.persist(users);

                Assert.assertNotNull(users.getId());
            } catch (final Exception e) {
                e.printStackTrace();
                fail("Error running test " + e.getMessage());
            } finally {
                if (this.em.getTransaction().isActive()) {
                    this.em.getTransaction().rollback();
                }
            }
        }
    }

    @Test
    public void testDelete() throws Exception {
        final List<UsersEntity> users = this.usersDAO.readAll();
        final UsersEntity entity = users.stream().filter(Objects::nonNull).findAny().orElse(null);
        assertNotNull(entity);
        synchronized (this.em) {
            try {
                if (!this.em.getTransaction().isActive()) {
                    this.em.getTransaction().begin();
                }
                this.usersDAO.remove(entity);
                assertThat(users.size(), CoreMatchers.equalTo(this.usersDAO.readAll().size() + 1));
            } finally {
                if (this.em.getTransaction().isActive()) {
                    this.em.getTransaction().rollback();
                }
            }
        }
    }

    @Test
    public void testRemoveThrowsException() throws Exception {
        this.expectedException.expect(Exception.class);
        this.expectedException.expectMessage(StringContains.containsString("in uso"));
        final UsersEntity entity = this.usersDAO.readAll().stream().findAny().orElse(null);
        this.mockedDAO.remove(entity);
    }

    @Test
    public void testPersistThrowsException() throws Exception {
        this.expectedException.expect(Exception.class);
        this.expectedException.expectMessage(contains("già presente"));
        final UsersEntity entity = this.usersDAO.readAll().stream().findAny().orElse(null);
        Mockito.doThrow(Exception.class).when(this.em).merge(ArgumentMatchers.any());
        if (entity == null) {

        } else {
            entity.setId(null);
        }
        this.mockedDAO.persist(entity);
    }

    @Test
    public void testFindByUserNameReturnsData() {
        final UsersEntity entity = this.usersDAO.readAll().stream().findAny().orElse(null);
        if (entity == null) {
            log.warn("empty test database");
        } else {
            final UsersEntity test = this.usersDAO.findByUsername(entity.getName());
            assertEquals(entity, test);
        }
    }

    @Test
    public void testFindByUserNameRetunsNull() {
        final UsersEntity test = this.usersDAO.findByUsername("some name that is unlikely to be in the test database");
        assertNull(test);
    }

    @Test
    public void testFindById() {
        final UsersEntity entity = this.usersDAO.readAll().stream().findAny().orElse(null);
        assertNotNull(entity);
        final UsersEntity test = this.usersDAO.findById(entity.getId());
        assertNotNull(test);
        assertThat(entity, CoreMatchers.equalTo(test));
    }

    @Test
    public void testReadIngridFilter() {
        List<UsersEntity> list = usersDAO.readAll();
        if ( list == null )
            fail("no user in database");
//        final UsersEntity entity = this.usersDAO.readAll().stream().findAny().orElse(null);
        final UsersEntity entity = list.stream().findAny().orElse(null);
        assertNotNull(entity);
        assertNotNull(entity.getIngridFilters());
        assertThat(entity.getIngridFilters(), CoreMatchers.containsString("griglia_1"));
    }

    @Test
    public void testReadIngridFilterAsMap() {
        final UsersEntity entity = this.usersDAO.readAll().stream().findAny().orElse(null);
        assertNotNull(entity);
        final Map<String, List<IngridFilter>> map = entity.getIngridFilterAsMap();
        assertNotNull(map);
        assertNotNull(map.get("griglia_m"));
    }

    @Test
    public void testUpdateIngridFilterMap() throws Exception {
        UsersEntity entity = this.usersDAO.readAll().stream().findAny().orElse(null);
        assertNotNull(entity);
        Map<String, List<IngridFilter>> map = entity.getIngridFilterAsMap();
        assertNotNull(map);
        assertNotNull(map.get("griglia_m"));
        map.remove("griglia_m");
        entity.updateIngridFilters(map);
        synchronized (this.em) {
            try {
                if (!this.em.getTransaction().isActive()) {
                    this.em.getTransaction().begin();
                }
                this.usersDAO.persist(entity);
                entity = this.usersDAO.findById(entity.getId());
                map = entity.getIngridFilterAsMap();
                assertNotNull(map);
                assertNull(map.get("griglia_m"));
                assertNotNull(map.get("griglia_1"));

            } catch (final Exception e) {
                e.printStackTrace();
                fail("Error running test " + e.getMessage());
            } finally {
                if (this.em.getTransaction().isActive()) {
                    this.em.getTransaction().rollback();
                }
            }
        }
    }
}