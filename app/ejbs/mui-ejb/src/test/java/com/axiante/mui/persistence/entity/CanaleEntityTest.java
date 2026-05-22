package com.axiante.mui.persistence.entity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CanaleEntityTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CanaleEntity entity;
    private GroupEntity group1;
    private GroupEntity group2;
    private MuiContext context1;
    private MuiContext context2;

    @Before
    public void setUp() throws Exception {
        entity = new CanaleEntity();
        group1 = new GroupEntity();
        group2 = new GroupEntity();
        context1 = createContextEntity(1L);
        context2 = createContextEntity(2L);
    }

    @Test
    public void addGroup_whenNullGroups() {
        assertEquals(group1, entity.addGroup(group1));
    }

    @Test
    public void addGroup() {
        entity.setGroups(Stream.of(group1).collect(Collectors.toSet()));
        assertEquals(group2, entity.addGroup(group2));
    }

    @Test
    public void removeGroup_whenNullGroups() {
        assertEquals(group1, entity.removeGroup(group1));
    }

    @Test
    public void removeGroup() {
        entity.setGroups(Stream.of(group1).collect(Collectors.toSet()));
        assertEquals(group2, entity.removeGroup(group2));
    }

    @Test
    public void addContext_givenNullContext_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addContext(null);
    }

    @Test
    public void addContext_whenNullContexts() {
        entity.addContext(context1);
        assertEquals(1, entity.getContexts().size());
    }

    @Test
    public void addContext() {
        entity.setContexts(Stream.of(context1).collect(Collectors.toSet()));
        entity.addContext(context2);
        assertEquals(2, entity.getContexts().size());
    }

    @Test
    public void removeContext_givenNullContext_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeContext(null);
    }

    @Test
    public void removeContext_whenNullContexts_shouldReturnNull() {
        assertNull(entity.removeContext(context1));
    }

    @Test
    public void removeContext_withContextToBeRemoved_shouldReturnIt() {
        entity.setContexts(Stream.of(context1).collect(Collectors.toSet()));
        assertEquals(context1, entity.removeContext(context1));
    }

    @Test
    public void removeContext_withoutContextToBeRemoved_shouldReturnNull() {
        entity.setContexts(Stream.of(context1).collect(Collectors.toSet()));
        assertNull(entity.removeContext(context2));
    }

    private MuiContext createContextEntity(Long id) {
        MuiContext context = new MuiContext();
        context.setId(id);
        return context;
    }
}