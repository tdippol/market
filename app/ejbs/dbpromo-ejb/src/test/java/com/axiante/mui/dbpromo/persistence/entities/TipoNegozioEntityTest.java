package com.axiante.mui.dbpromo.persistence.entities;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class TipoNegozioEntityTest {
    @Test
    public void testWhenAddNegoziotThenEntityInList() {
        TipoNegozioEntity entity = new TipoNegozioEntity();
        entity.setNegozioEntities(new HashSet<NegozioEntity>());
        NegozioEntity e = new NegozioEntity();
        int size = entity.getNegozioEntities().size();
        entity.addMuiNegozio(e);

        assertThat(entity.getNegozioEntities().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getTipoNegozioEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void testWhenRemoveNegoziotThenEntityNotInList() {
        TipoNegozioEntity entity = new TipoNegozioEntity();
        entity.setNegozioEntities(new HashSet<NegozioEntity>());
        NegozioEntity e = new NegozioEntity();
        int size = entity.getNegozioEntities().size();
        entity.addMuiNegozio(e);

        assertThat(entity.getNegozioEntities().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getTipoNegozioEntity(), CoreMatchers.equalTo(entity));

        entity.removeMuiNegozio(e);
        assertNull(e.getTipoNegozioEntity());
        assertThat(entity.getNegozioEntities().size(), CoreMatchers.equalTo(size));
    }
}
