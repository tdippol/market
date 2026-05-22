package com.axiante.mui.persistence.entity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MuiContextTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    private MuiContext entity;
    private CanaleEntity canale1;
    private CanaleEntity canale2;

    @Before
    public void setUp() throws Exception {
        entity = new MuiContext();
        canale1 = createCanaleEntity(1);
        canale2 = createCanaleEntity(2);
    }

    @Test
    public void addCanale_givenNullCanale_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addCanale(null);
    }

    @Test
    public void addCanale_whenNullCanali() {
        entity.addCanale(canale1);
        assertEquals(1, entity.getCanali().size());
    }

    @Test
    public void addCanale() {
        entity.setCanali(Stream.of(canale1).collect(Collectors.toSet()));
        entity.addCanale(canale2);
        assertEquals(2, entity.getCanali().size());
    }

    @Test
    public void removeCanale_givenNullCanale_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeCanale(null);
    }

    @Test
    public void removeCanale_whenNullCanali_shouldReturnNull() {
        entity.setCanali(null);
        assertNull(entity.removeCanale(canale1));
    }

    @Test
    public void removeCanale_whenCanaleNotInList_shouldReturnNull() {
        entity.setCanali(Stream.of(canale1).collect(Collectors.toSet()));
        assertNull(entity.removeCanale(canale2));
    }

    @Test
    public void removeCanale_whenCanaleInList_shouldReturnIt() {
        entity.setCanali(Stream.of(canale1, canale2).collect(Collectors.toSet()));
        assertEquals(canale2, entity.removeCanale(canale2));
    }

    private CanaleEntity createCanaleEntity(Integer id) {
        CanaleEntity entity = new CanaleEntity();
        entity.setId(id);
        return entity;
    }
}