package com.axiante.mui.dbpromo.persistence.entities;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class SubGrmEntityTest {

    @Test
    public void whenAddItemThenEntityInList() {
        SubGrmEntity entity = new SubGrmEntity();
        entity.setItemEntities(new HashSet<ItemEntity>());
        ItemEntity e = new ItemEntity();
        int size = entity.getItemEntities().size();
        entity.addMuiItem(e);
        assertThat(size + 1, CoreMatchers.equalTo(entity.getItemEntities().size()));
        assertThat(e.getSubGrmEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemoveItemThenEntityNotInList() {
        SubGrmEntity entity = new SubGrmEntity();
        entity.setItemEntities(new HashSet<ItemEntity>());
        ItemEntity e = new ItemEntity();
        int size = entity.getItemEntities().size();
        entity.addMuiItem(e);
        assertThat(size + 1, CoreMatchers.equalTo(entity.getItemEntities().size()));
        assertThat(e.getSubGrmEntity(), CoreMatchers.equalTo(entity));

        entity.removeMuiItem(e);
        assertNull(e.getSubGrmEntity());
        assertThat(entity.getItemEntities().size(), CoreMatchers.equalTo(size));
    }
}
