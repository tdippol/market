package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ClusterClienteEntityTest {
    private ClusterClienteEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new ClusterClienteEntity();
        entity.setIdCluster("IdCluster");
        entity.setDescrizione("Descrizione");
    }

    @Test
    public void getId_shouldReturnNull() {
        assertNull(entity.getId());
    }

    @Test
    public void getKey_shouldReturnIdCluster() {
        assertEquals("IdCluster", entity.getKey());
    }

    @Test
    public void getLabel_shouldReturnDescrizione() {
        assertEquals("Descrizione", entity.getLabel());
    }
}