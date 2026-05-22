package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.PianoMediaSecurityDAO;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.PianoMediaSecurityEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JpaPianoMediaSecurityDAOTest extends DaoTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaPianoMediaSecurityDAO.class)
            .activate(RequestScoped.class).inject(this).build();

    @Inject
    private PianoMediaSecurityDAO dao;

    private UsersEntity user;
    private PianoMediaSecurityEntity security1;
    private PianoMediaSecurityEntity security2;

    @Before
    public void setUp() throws Exception {
        // Create a bunch of entities
        final GroupEntity group1 = createGroup(101, "FOO");
        final GroupEntity group2 = createGroup(102, "BAR");
        final GroupEntity group3 = createGroup(103, "BAZ");
        user = createUser(101, "JUNIT", Stream.of(group1, group3).collect(Collectors.toSet()));
        security1 = createSecurity(101, group1);
        security2 = createSecurity(102, group2);
        getEm().getTransaction().begin();
        getEm().persist(group1);
        getEm().persist(group2);
        getEm().persist(group3);
        getEm().persist(user);
        getEm().persist(security1);
        getEm().persist(security2);
        getEm().getTransaction().commit();
    }

    @Test
    public void findByUser() throws Exception {
        final List<PianoMediaSecurityEntity> result = dao.findByUser(user);
        assertEquals(1, result.size());
        assertTrue(result.contains(security1));
        assertFalse(result.contains(security2));
    }

    private PianoMediaSecurityEntity createSecurity(Integer id, GroupEntity group) {
        final PianoMediaSecurityEntity e = new PianoMediaSecurityEntity();
        e.setId(id);
        e.setGroup(group);
        return e;
    }

    private GroupEntity createGroup(Integer id, String code) {
        final GroupEntity e = new GroupEntity();
        e.setId(id);
        e.setCodiceGruppo(code);
        return e;
    }

    private UsersEntity createUser(Integer id, String name, Set<GroupEntity> groups) {
        final UsersEntity e = new UsersEntity();
        e.setId(id);
        e.setName(name);
        e.setGruppi(groups);
        return e;
    }
}