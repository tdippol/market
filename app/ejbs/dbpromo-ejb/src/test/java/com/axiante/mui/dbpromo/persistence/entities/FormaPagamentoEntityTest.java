package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class FormaPagamentoEntityTest {

    @Test
    public void getId_shouldReturnNull() {
        FormaPagamentoEntity entity = new FormaPagamentoEntity();
        assertNull(entity.getId());
    }
}