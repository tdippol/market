package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CumulabilitaBuoniEntityTest {
    private CumulabilitaBuoniEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new CumulabilitaBuoniEntity();
        entity.setCodicePromozione("PR-001");
        entity.setPrefissoBS("BS");
    }

    @Test
    public void getChiave() {
        assertEquals("PR-001|BS", entity.getChiave());
    }
}