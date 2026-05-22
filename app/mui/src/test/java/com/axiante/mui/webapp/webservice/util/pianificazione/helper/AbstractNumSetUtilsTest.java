package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.Set;

public class AbstractNumSetUtilsTest {
    protected PromozionePianificazioneEntity mockPianificazione(String numSet) {
        CfgPianificazTipoRigaEntity tipoRigaMock = mock(CfgPianificazTipoRigaEntity.class);
        lenient().when(tipoRigaMock.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.SET.getTypeCode());
        PromozionePianificazioneEntity mock = mock(PromozionePianificazioneEntity.class);
        when(mock.getNumSet()).thenReturn(numSet);
        lenient().when(mock.getTipoRiga()).thenReturn(tipoRigaMock);
        return mock;
    }

    protected CfgConfHeaderEntity mockCfgConfHeader(Set<CfgPianificazioneEntity> cfgPianificazioni) {
        CfgConfHeaderEntity cfgConfHeader = mock(CfgConfHeaderEntity.class);
        when(cfgConfHeader.getMuiCfgPianificaziones()).thenReturn(cfgPianificazioni);
        return cfgConfHeader;
    }

    protected CfgPianificazioneEntity mockCfgPianificazione(String lista, CfgPianificazioneCampiEntity cfgPianificazioneCampo,
                                                            String mandatory) {
        CfgPianificazioneEntity mock = mock(CfgPianificazioneEntity.class);
        when(mock.getLista()).thenReturn(lista);
        lenient().when(mock.getMuiCfgPianificazioneCampi()).thenReturn(cfgPianificazioneCampo);
        lenient().when(mock.getMandatory()).thenReturn(mandatory);
        return mock;
    }

    protected CfgPianificazioneCampiEntity mockCfgPianificazioneCampo(String campo) {
        CfgPianificazioneCampiEntity mock = mock(CfgPianificazioneCampiEntity.class);
        lenient().when(mock.getCampo()).thenReturn(campo);
        return mock;
    }

    protected PromozioneTestataEntity mockPromozioneTestata(CanalePromozioneEntity canale) {
        return mockPromozioneTestata(canale, null);
    }

    protected PromozioneTestataEntity mockPromozioneTestata(CanalePromozioneEntity canale,
                                                            Set<PromozionePianificazioneEntity> pianificazioni) {
        PromozioneTestataEntity mock = mock(PromozioneTestataEntity.class);
        lenient().when(mock.getMuiCanalePromozione()).thenReturn(canale);
        if (pianificazioni != null) {
            when(mock.getPromozionePianificazioneEntities()).thenReturn(pianificazioni);
        }
        return mock;
    }

    protected CanalePromozioneEntity mockCanalePromozione(boolean flOverlapOffset) {
        CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
        lenient().when(mock.getFlOverlapOffset()).thenReturn(flOverlapOffset);
        return mock;
    }
}
