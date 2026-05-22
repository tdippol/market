package com.axiante.mui.dbpromo.persistence.entities;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

public class ItemEntityTest {

    private final ItemEntity entity = new ItemEntity();

    @Before
    public void init() {
        entity.setMuiAssortimentoFornitores(new HashSet<AssortimentoFornitoreEntity>());
        entity.setMuiAssortimentoNegozi(new HashSet<AssortimentoNegozioEntity>());
    }

    @Test
    public void whenAddAssortimentoFornitoreEntity() {
        AssortimentoFornitoreEntity e = new AssortimentoFornitoreEntity();
        int size = entity.getMuiAssortimentoFornitores().size();
        entity.addMuiAssortimentoFornitore(e);
        assertThat(entity.getMuiAssortimentoFornitores().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getItemEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemoveAssortimentoFornitoreEntity() {
        AssortimentoFornitoreEntity e = new AssortimentoFornitoreEntity();
        int size = entity.getMuiAssortimentoFornitores().size();
        entity.addMuiAssortimentoFornitore(e);
        assertThat(entity.getMuiAssortimentoFornitores().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getItemEntity(), CoreMatchers.equalTo(entity));

        entity.removeMuiAssortimentoFornitore(e);
        assertThat(entity.getMuiAssortimentoFornitores().size(), CoreMatchers.equalTo(size));
        assertNull(e.getItemEntity());
    }

    @Test
    public void whenAddAssortimentoNegozioEntity() {
        AssortimentoNegozioEntity e = new AssortimentoNegozioEntity();
        int size = entity.getMuiAssortimentoNegozi().size();
        entity.addMuiAssortimentoNegozio(e);
        assertThat(entity.getMuiAssortimentoNegozi().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getItemEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemoveAssortimentoNegozioEntity() {
        AssortimentoNegozioEntity e = new AssortimentoNegozioEntity();
        int size = entity.getMuiAssortimentoNegozi().size();
        entity.addMuiAssortimentoNegozio(e);
        assertThat(entity.getMuiAssortimentoNegozi().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getItemEntity(), CoreMatchers.equalTo(entity));

        entity.removeMuiAssortimentoNegozio(e);
        assertThat(entity.getMuiAssortimentoNegozi().size(), CoreMatchers.equalTo(size));
        assertNull(e.getItemEntity());
    }

}
