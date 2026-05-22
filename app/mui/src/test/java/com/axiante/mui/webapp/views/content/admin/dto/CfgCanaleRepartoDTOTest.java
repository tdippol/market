package com.axiante.mui.webapp.views.content.admin.dto;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleReparto;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CfgCanaleRepartoDTOTest {

    private CanalePromozioneEntity canale;
    private RepartoEntity reparto;
    private CfgCanaleReparto config;

    @Before
    public void setUp() {
        canale = mock(CanalePromozioneEntity.class);
        reparto = mock(RepartoEntity.class);
        config = mock(CfgCanaleReparto.class);
        when(config.getCanale()).thenReturn(canale);
        when(config.getReparto()).thenReturn(reparto);
        when(config.getMaxTestate()).thenReturn(10);
    }

    @Test
    public void constructor_withNonNullConfig_shouldInitializeAndBeSelected() {
        CfgCanaleRepartoDTO dto = new CfgCanaleRepartoDTO(config);
        assertThat(dto.getCanale(), is(canale));
        assertThat(dto.getReparto(), is(reparto));
        assertThat(dto.getMaxTestate(), is(10));
        assertTrue(dto.isSelected());
        assertThat(dto.getConfig(), is(config));
    }

    @Test
    public void constructor_withNullConfig_shouldNotBeSelectedAndHaveDefaults() {
        CfgCanaleRepartoDTO dto = new CfgCanaleRepartoDTO(null);
        assertThat(dto.getMaxTestate(), is(0));
        assertFalse(dto.isSelected());
        assertThat(dto.getConfig(), is(nullValue()));
        assertThat(dto.getCanale(), is(nullValue()));
        assertThat(dto.getReparto(), is(nullValue()));
    }

    @Test
    public void setSelected_toTrueWhenUnconfigured_shouldCreateConfig() {
        CfgCanaleRepartoDTO dto = new CfgCanaleRepartoDTO(null);
        dto.setCanale(canale);
        dto.setReparto(reparto);
        dto.setSelected(true);
        assertThat(dto.getConfig(), is(notNullValue()));
        assertThat(dto.getConfig().getCanale(), is(canale));
        assertThat(dto.getConfig().getReparto(), is(reparto));
    }

    @Test
    public void setSelected_toTrueWhenConfigured_shouldNotChangeConfig() {
        CfgCanaleRepartoDTO dto = new CfgCanaleRepartoDTO(config);
        dto.setSelected(true);
        assertThat(dto.getConfig(), is(config));
    }

    @Test
    public void setSelected_toFalse_shouldNotChangeConfig() {
        CfgCanaleRepartoDTO dto = new CfgCanaleRepartoDTO(config);
        dto.setSelected(false);
        assertThat(dto.getConfig(), is(config));
    }

    @Test
    public void setMaxTestate_whenConfigured_shouldUpdateBoth() {
        doCallRealMethod().when(config).setMaxTestate(25);
        doCallRealMethod().when(config).getMaxTestate();
        CfgCanaleRepartoDTO dto = new CfgCanaleRepartoDTO(config);
        dto.setMaxTestate(25);
        assertThat(dto.getMaxTestate(), is(25));
        assertThat(dto.getConfig().getMaxTestate(), is(25));
    }

    @Test
    public void setMaxTestate_whenUnconfigured_shouldCreateAndPopulateConfig() {
        CfgCanaleRepartoDTO dto = new CfgCanaleRepartoDTO(null);
        dto.setCanale(canale);
        dto.setReparto(reparto);
        dto.setMaxTestate(25);
        assertThat(dto.getConfig(), is(notNullValue()));
        assertThat(dto.getConfig().getCanale(), is(canale));
        assertThat(dto.getConfig().getReparto(), is(reparto));
        assertThat(dto.getConfig().getMaxTestate(), is(25));
    }

    @Test(expected = IllegalStateException.class)
    public void getKey_whenRepartoIsNull_shouldThrowException() {
        CfgCanaleRepartoDTO dto = new CfgCanaleRepartoDTO(null);
        dto.getKey();
    }

    @Test
    public void getKey_whenRepartoIsValid_shouldReturnRepartoKey() {
        when(reparto.getKey()).thenReturn("REP_KEY");
        CfgCanaleRepartoDTO dto = new CfgCanaleRepartoDTO(config);
        assertThat(dto.getKey(), is(equalTo("REP_KEY")));
    }

    @Test
    public void updateConfig_withNewConfig_shouldUpdateFieldsAndBeSelected() {
        CfgCanaleRepartoDTO dto = new CfgCanaleRepartoDTO(null);
        dto.updateConfig(config);
        assertThat(dto.getCanale(), is(canale));
        assertThat(dto.getReparto(), is(reparto));
        assertThat(dto.getMaxTestate(), is(10));
        assertTrue(dto.isSelected());
        assertThat(dto.getConfig(), is(config));
    }

    @Test
    public void updateConfig_withNull_shouldBecomeNotSelected() {
        CfgCanaleRepartoDTO dto = new CfgCanaleRepartoDTO(config);
        dto.updateConfig(null);
        assertFalse(dto.isSelected());
    }
}