package com.axiante.mui.dbpromo.persistence.entities;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

public class NegozioEntityTest {

    private final NegozioEntity entity = new NegozioEntity();

    @Before
    public void init() {
        entity.setMuiCfgCanaleNegozis(new HashSet<CfgCanaleNegoziEntity>());
        entity.setPromozioneNegozioEntities(new HashSet<PromozioneNegozioEntity>());
        entity.setMuiAssortimentoNegozi(new HashSet<AssortimentoNegozioEntity>());
    }

    @Test
    public void whenAddCfgCanaleNegoziEntityThenEntityInList() {
        CfgCanaleNegoziEntity e = new CfgCanaleNegoziEntity();
        int size = entity.getMuiCfgCanaleNegozis().size();
        entity.addMuiCfgCanaleNegozi(e);
        assertThat(entity.getMuiCfgCanaleNegozis().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getNegozioEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemoveCfgCanaleNegoziEntityThenEntityNotInList() {
        CfgCanaleNegoziEntity e = new CfgCanaleNegoziEntity();
        int size = entity.getMuiCfgCanaleNegozis().size();
        entity.addMuiCfgCanaleNegozi(e);
        assertThat(entity.getMuiCfgCanaleNegozis().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getNegozioEntity(), CoreMatchers.equalTo(entity));

        entity.removeMuiCfgCanaleNegozi(e);
        assertThat(entity.getMuiCfgCanaleNegozis().size(), CoreMatchers.equalTo(size));
        assertNull(e.getNegozioEntity());

    }

    @Test
    public void whenAddPromozioneNegozioEntityThenEntityInList() {
        PromozioneNegozioEntity e = new PromozioneNegozioEntity();
        int size = entity.getPromozioneNegozioEntities().size();
        entity.addPromozioneNegozioEntity(e);
        assertThat(entity.getPromozioneNegozioEntities().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getNegozioEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemovePromozioneNegozioEntityThenEntityNotInList() {
        PromozioneNegozioEntity e = new PromozioneNegozioEntity();
        int size = entity.getPromozioneNegozioEntities().size();
        entity.addPromozioneNegozioEntity(e);
        assertThat(entity.getPromozioneNegozioEntities().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getNegozioEntity(), CoreMatchers.equalTo(entity));

        entity.removePromozioneNegozioEntity(e);
        assertThat(entity.getPromozioneNegozioEntities().size(), CoreMatchers.equalTo(size));
        assertNull(e.getNegozioEntity());
    }

    @Test
    public void whenAddAssortimentoNegozioEntityThenEntityInList() {
        AssortimentoNegozioEntity e = new AssortimentoNegozioEntity();
        int size = entity.getMuiAssortimentoNegozi().size();
        entity.addMuiAssortimentoNegozio(e);
        assertThat(entity.getMuiAssortimentoNegozi().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getNegozioEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemoveAssortimentoNegozioEntityThenEntityNotInList() {
        AssortimentoNegozioEntity e = new AssortimentoNegozioEntity();
        int size = entity.getMuiAssortimentoNegozi().size();
        entity.addMuiAssortimentoNegozio(e);
        assertThat(entity.getMuiAssortimentoNegozi().size(), CoreMatchers.equalTo(size + 1));
        assertThat(e.getNegozioEntity(), CoreMatchers.equalTo(entity));

        entity.removeMuiAssortimentoNegozio(e);
        assertThat(entity.getMuiAssortimentoNegozi().size(), CoreMatchers.equalTo(size));
        assertNull(e.getNegozioEntity());
    }
}
