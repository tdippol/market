package com.axiante.mui.dbpromo.persistence.entities;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class ResponsabileEntityTest {

    @Test
    public void whenAddCompratoreEntityThenEntityInList() {
        ResponsabileEntity entity = new ResponsabileEntity();
        entity.setCompratoreEntities(new HashSet<CompratoreEntity>());
        int size = entity.getCompratoreEntities().size();
        CompratoreEntity e = new CompratoreEntity();

        e = entity.addMuiCompratore(e);
        assertThat(e.getResponsabileEntity(), CoreMatchers.equalTo(entity));
        assertThat(entity.getCompratoreEntities().size(), CoreMatchers.equalTo(size + 1));
    }

    @Test
    public void whenRemoveCompratoreEntityThenEntityNotInList() {
        ResponsabileEntity entity = new ResponsabileEntity();
        entity.setCompratoreEntities(new HashSet<CompratoreEntity>());
        int size = entity.getCompratoreEntities().size();
        CompratoreEntity e = new CompratoreEntity();

        e = entity.addMuiCompratore(e);
        assertThat(e.getResponsabileEntity(), CoreMatchers.equalTo(entity));
        assertThat(entity.getCompratoreEntities().size(), CoreMatchers.equalTo(size + 1));

        e = entity.removeMuiCompratore(e);
        assertNull(e.getResponsabileEntity());
        assertThat(entity.getCompratoreEntities().size(), CoreMatchers.equalTo(size));
    }
}
