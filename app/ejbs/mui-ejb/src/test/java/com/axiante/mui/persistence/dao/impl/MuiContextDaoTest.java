package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.MuiContextDAO;
import com.axiante.mui.persistence.entity.CanaleEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.MuiContext;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MuiContextDaoTest extends DaoTest {

    @Inject
    private MuiContextDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JPAMuiContextDAO.class)
            .activate(RequestScoped.class).inject(this).build();

    @Test(expected = NullPointerException.class)
    public void findGroupsByContextCode_givenNullCode_shouldThrowException() {
        dao.findGroupsByContextCode(null);
    }

    @Test
    public void findGroupsByContextCode() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
            final GroupEntity group1 = createGroup("GR1");
            final GroupEntity group2 = createGroup("GR2");
            final GroupEntity group3 = createGroup("GR3");
            persist(group1, group2, group3);
            final CanaleEntity channel1 = createCanale(10L, group1);
            final CanaleEntity channel2 = createCanale(20L, group1, group2);
            final CanaleEntity channel3 = createCanale(30L, group3);
            persist(channel1, channel2, channel3);
            final MuiContext context1 = createMuiContext("FOO", channel1, channel2);
            final MuiContext context2 = createMuiContext("BAR", channel3);
            persist(context1, context2);
            em.getTransaction().commit();
        }
        List<GroupEntity> groups = dao.findGroupsByContextCode("FOO");
        List<String> codes = groups.stream().map(GroupEntity::getCodiceGruppo).collect(Collectors.toList());
        assertEquals(2, codes.size());
        assertTrue(codes.contains("GR1"));
        assertTrue(codes.contains("GR2"));
        assertFalse(codes.contains("GR3"));
        groups = dao.findGroupsByContextCode("BAR");
        codes = groups.stream().map(GroupEntity::getCodiceGruppo).collect(Collectors.toList());
        assertEquals(1, codes.size());
        assertFalse(codes.contains("GR1"));
        assertFalse(codes.contains("GR2"));
        assertTrue(codes.contains("GR3"));
        groups = dao.findGroupsByContextCode("BAZ");
        assertTrue(groups.isEmpty());
    }

    private void persist(Object... entities) {
        for (Object entity : entities) {
            em.persist(entity);
        }
    }

    private GroupEntity createGroup(String code) {
        final GroupEntity entity = new GroupEntity();
        entity.setCodiceGruppo(code);
        return entity;
    }

    private CanaleEntity createCanale(Long code, GroupEntity... groups) {
        final CanaleEntity entity = new CanaleEntity();
        entity.setId(code.intValue());
        entity.setCodiceCanale(code);
        for (GroupEntity group : groups) {
            entity.addGroup(group);
        }
        return entity;
    }

    private MuiContext createMuiContext(String code, CanaleEntity... channels) {
        final MuiContext entity = new MuiContext();
        entity.setCode(code);
        entity.setDescription(String.format("CONTEXT %s", code));
        for (CanaleEntity channel : channels) {
            entity.addCanale(channel);
        }
        return entity;
    }
}
