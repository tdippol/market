package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MuiFontStileEntityTest {
    @Test
    public void getLabel() {
        MuiFontStileEntity entity = new MuiFontStileEntity();
        entity.setColore("COLORE");
        entity.setStile("STILE");
        assertEquals("COLORE (STILE)", entity.getLabel());
    }
}