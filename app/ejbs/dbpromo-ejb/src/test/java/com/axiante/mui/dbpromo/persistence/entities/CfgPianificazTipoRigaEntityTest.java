package com.axiante.mui.dbpromo.persistence.entities;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class CfgPianificazTipoRigaEntityTest {
    @Test
    public void whenAddCfgPianificazioneEntityThenEntityInList() {
        CfgPianificazTipoRigaEntity entity = new CfgPianificazTipoRigaEntity();
        entity.setMuiCfgPianificaziones(new HashSet<CfgPianificazioneEntity>());
        int size = entity.getMuiCfgPianificaziones().size();
        CfgPianificazioneEntity e = new CfgPianificazioneEntity();

        e = entity.addMuiCfgPianificazione(e);
        assertThat(e.getMuiCfgPianificazTipoRiga(), CoreMatchers.equalTo(entity));
        assertThat(entity.getMuiCfgPianificaziones().size(), CoreMatchers.equalTo(size + 1));
    }

    @Test
    public void whenRemoveCfgPianificazioneEntityThenEntityNotInList() {
        CfgPianificazTipoRigaEntity entity = new CfgPianificazTipoRigaEntity();
        entity.setMuiCfgPianificaziones(new HashSet<CfgPianificazioneEntity>());
        int size = entity.getMuiCfgPianificaziones().size();
        CfgPianificazioneEntity e = new CfgPianificazioneEntity();

        e = entity.addMuiCfgPianificazione(e);
        assertThat(e.getMuiCfgPianificazTipoRiga(), CoreMatchers.equalTo(entity));
        assertThat(entity.getMuiCfgPianificaziones().size(), CoreMatchers.equalTo(size + 1));

        e = entity.removeMuiCfgPianificazione(e);
        assertNull(e.getMuiCfgPianificazTipoRiga());
        assertThat(entity.getMuiCfgPianificaziones().size(), CoreMatchers.equalTo(size));
    }
    
    @Test
    public void whenAddPianificazioneEntityThenEntityInList() {
    	PromozionePianificazioneEntity entity = new PromozionePianificazioneEntity();
    	entity.setChildren(new HashSet<PromozionePianificazioneEntity>());
        int size = entity.getChildren().size();
        PromozionePianificazioneEntity e = new PromozionePianificazioneEntity();

        e = entity.addDetail(e);
        assertThat(entity.getChildren().size(), CoreMatchers.equalTo(size + 1));
    }

    @Test
    public void whenRemovePianificazioneEntityThenEntityNotInList() {
    	PromozionePianificazioneEntity entity = new PromozionePianificazioneEntity();
        entity.setChildren(new HashSet<PromozionePianificazioneEntity>());
        int size = entity.getChildren().size();
        PromozionePianificazioneEntity e = new PromozionePianificazioneEntity();
        
        e = entity.addDetail(e);
        assertThat(entity.getChildren().size(), CoreMatchers.equalTo(size + 1));

        e = entity.removeDetail(e);
        assertNull(e.getChildren());
        assertThat(entity.getChildren().size(), CoreMatchers.equalTo(size));
    }
    
    @Test
    public void whenAddPianificazioneThenEntityInList() {
    	CfgPianificazTipoRigaEntity entity = new CfgPianificazTipoRigaEntity();
        entity.setPianificazioni(new HashSet<PromozionePianificazioneEntity>());
        int size = entity.getPianificazioni().size();
        PromozionePianificazioneEntity e = new PromozionePianificazioneEntity();

        e = entity.addPianificazione(e);
        assertThat(e.getTipoRiga(), CoreMatchers.equalTo(entity));
        assertThat(entity.getPianificazioni().size(), CoreMatchers.equalTo(size + 1));
    }

    @Test
    public void whenRemovePianificazioneThenEntityNotInList() {
        CfgPianificazTipoRigaEntity entity = new CfgPianificazTipoRigaEntity();
        entity.setPianificazioni(new HashSet<PromozionePianificazioneEntity>());
        int size = entity.getPianificazioni().size();
        PromozionePianificazioneEntity e = new PromozionePianificazioneEntity();
        
        e = entity.addPianificazione(e);
        assertThat(e.getTipoRiga(), CoreMatchers.equalTo(entity));
        assertThat(entity.getPianificazioni().size(), CoreMatchers.equalTo(size + 1));

        e = entity.removePianificazione(e);
        assertNull(e.getTipoRiga());
        assertThat(entity.getPianificazioni().size(), CoreMatchers.equalTo(size));
    }

}
