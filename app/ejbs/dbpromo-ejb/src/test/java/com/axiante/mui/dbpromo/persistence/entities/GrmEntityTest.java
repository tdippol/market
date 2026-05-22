package com.axiante.mui.dbpromo.persistence.entities;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class GrmEntityTest {

    @Test
    public void whenAddSubGrmEntityThenEntityInList() {
        GrmEntity entity = new GrmEntity();
        entity.setSubGrmEntities(new HashSet<SubGrmEntity>());
        int size = entity.getSubGrmEntities().size();
        SubGrmEntity e = new SubGrmEntity();

        e = entity.addSubGrmEntity(e);
        assertThat(e.getGrmEntity(), CoreMatchers.equalTo(entity));
        assertThat(entity.getSubGrmEntities().size(), CoreMatchers.equalTo(size + 1));
    }

    @Test
    public void whenRemoveSubGrmEntityThenEntityNotInList() {
        GrmEntity entity = new GrmEntity();
        entity.setSubGrmEntities(new HashSet<SubGrmEntity>());
        int size = entity.getSubGrmEntities().size();
        SubGrmEntity e = new SubGrmEntity();

        e = entity.addSubGrmEntity(e);
        assertThat(e.getGrmEntity(), CoreMatchers.equalTo(entity));
        assertThat(entity.getSubGrmEntities().size(), CoreMatchers.equalTo(size + 1));

        e = entity.removeSubGrmEntity(e);
        assertNull(e.getGrmEntity());
        assertThat(entity.getSubGrmEntities().size(), CoreMatchers.equalTo(size));
    }

}
