package com.axiante.mui.dbpromo.persistence.entities;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

public class CategoriaEntityTest {
    private CategoriaEntity entity = new CategoriaEntity();

    @Before
    public void init() {
        entity.setGrmEntities(new HashSet<GrmEntity>());
    }

    @Test
    public void wheAddMuiGrmThenEntityInList() {
        GrmEntity e = new GrmEntity();
        int size = entity.getGrmEntities().size();
        entity.addMuiGrm(e);
        assertThat(entity.getGrmEntities().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getMuiCategoria(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemoveMuiGrmThenEntityNotInList() {
        GrmEntity e = new GrmEntity();
        int size = entity.getGrmEntities().size();
        entity.addMuiGrm(e);
        assertThat(entity.getGrmEntities().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getMuiCategoria(), CoreMatchers.equalTo(entity));

        entity.removeMuiGrm(e);
        assertThat(entity.getGrmEntities().size(), CoreMatchers.equalTo(size));
        assertNull(e.getMuiCategoria());
    }

}
