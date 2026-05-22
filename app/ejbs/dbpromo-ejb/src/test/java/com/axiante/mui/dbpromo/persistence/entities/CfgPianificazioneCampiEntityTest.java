package com.axiante.mui.dbpromo.persistence.entities;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import org.junit.Test;

public class CfgPianificazioneCampiEntityTest {
    @Test
    public void whenAddCfgPianificazioneEntityThenEntityInList() {
        CfgPianificazioneCampiEntity entity = new CfgPianificazioneCampiEntity();
        entity.setMuiCfgPianificaziones(new HashSet<CfgPianificazioneEntity>());
        CfgPianificazioneEntity e = new CfgPianificazioneEntity();
        entity.addMuiCfgPianificazione(e);
        assertTrue(entity.getMuiCfgPianificaziones().size() == 1);
        assertTrue(e.getMuiCfgPianificazioneCampi().equals(entity));
    }

    @Test
    public void whenRemoveCfgPianificazioneEntityThenEntityNotInList() {
        CfgPianificazioneCampiEntity entity = new CfgPianificazioneCampiEntity();
        entity.setMuiCfgPianificaziones(new HashSet<CfgPianificazioneEntity>());
        CfgPianificazioneEntity e = new CfgPianificazioneEntity();
        entity.addMuiCfgPianificazione(e);
        assertTrue(entity.getMuiCfgPianificaziones().size() == 1);
        assertTrue(e.getMuiCfgPianificazioneCampi().equals(entity));

        entity.removeMuiCfgPianificazione(e);
        assertTrue(entity.getMuiCfgPianificaziones().size() == 0);
        assertNull(e.getMuiCfgPianificazioneCampi());
    }
}
