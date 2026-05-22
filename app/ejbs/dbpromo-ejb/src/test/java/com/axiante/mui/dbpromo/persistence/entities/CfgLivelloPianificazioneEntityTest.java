package com.axiante.mui.dbpromo.persistence.entities;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CfgLivelloPianificazioneEntityTest {
	static final CfgLivelloPianificazioneEntity entity= new CfgLivelloPianificazioneEntity();

	@Before
	public void init() {
		entity.setId((long) 1);
        entity.setMuiCfgConfHeaders(new HashSet<>());
        entity.setId(42L);
        entity.setDescrizione("FOO LEVEL");
	}
	
	@Test
    public void addMuiCfgConfHeaderEntityTest() {
		CfgConfHeaderEntity e = new CfgConfHeaderEntity();
        int size = entity.getMuiCfgConfHeaders().size();
        entity.addMuiCfgConfHeaderEntity(e);
        size = entity.getMuiCfgConfHeaders().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getLivelloPianificazione(), CoreMatchers.equalTo(entity));
    }
	
	@Test
    public void removeTipoElemento() {
		CfgConfHeaderEntity e = new CfgConfHeaderEntity();
		int size = entity.getMuiCfgConfHeaders().size();
        e = entity.addMuiCfgConfHeaderEntity(e);
        size = entity.getMuiCfgConfHeaders().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getLivelloPianificazione(), CoreMatchers.equalTo(entity));

        entity.removeMuiCfgConfHeaderEntity(e);
        assertThat(entity.getMuiCfgConfHeaders().size(), CoreMatchers.equalTo(size - 1));
        assertNull(e.getLivelloPianificazione());
    }

    @Test
    public void getKey() {
        assertEquals("42", entity.getKey());
    }

    @Test
    public void getLabel() {
        assertEquals("FOO LEVEL", entity.getLabel());
    }
}
