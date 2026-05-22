package com.axiante.mui.webapp.views.content.admin.dto;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CfgLoghiMessaggiConfigurationDTOTest {

    @Test
    public void noArgsConstructor_shouldSetDefaultValues() {
        CfgLoghiMessaggiConfigurationDTO dto = new CfgLoghiMessaggiConfigurationDTO();
        assertThat(dto.getSezione(), is(equalTo("MESSAGGI")));
        assertThat(dto.getIdMessaggio(), is(equalTo(101)));
        assertNull(dto.getCodiceCanale());
        assertNull(dto.getCodiceMeccanica());
        assertNull(dto.getCodiceDispositivo());
        assertNull(dto.getTesto());
    }

    @Test
    public void constructorFromEntity_shouldMapAllFieldsCorrectly() {
        MuiCfgDefaultCastellettoMessaggiEntity entity = mock(MuiCfgDefaultCastellettoMessaggiEntity.class);
        MuiBottoneEntity bottone = mock(MuiBottoneEntity.class);
        MuiFontStileEntity fontStile = mock(MuiFontStileEntity.class);
        MuiFontEntities fontEntity = mock(MuiFontEntities.class);
        when(entity.getCodiceCanale()).thenReturn(1L);
        when(entity.getCodiceMeccanica()).thenReturn("MECH01");
        when(entity.getCodiceDispositivo()).thenReturn("DEV01");
        when(entity.getSeqStampa()).thenReturn((short) 2);
        when(entity.getSezione()).thenReturn(MessaggiSezioneEnum.MESSAGGI);
        when(entity.getTesto()).thenReturn("Testo di prova");
        when(entity.getTaglioCarta()).thenReturn(true);
        when(entity.getBarcode()).thenReturn(false);
        when(entity.getFont()).thenReturn(MessaggiFontEnum.NORMALE);
        when(entity.getAllineamento()).thenReturn(MessaggiAllineamentoEnum.CENTRO);
        when(entity.getBold()).thenReturn(true);
        when(entity.getLogo()).thenReturn("logo.png");
        when(entity.getBottone()).thenReturn(bottone);
        when(entity.getCodice()).thenReturn(false);
        when(entity.getRegolamento()).thenReturn("Regolamento ABC");
        when(entity.getBarra()).thenReturn(true);
        when(entity.getFontStile()).thenReturn(fontStile);
        when(entity.getIdMessaggio()).thenReturn(202);
        when(entity.getVariabile()).thenReturn("VAR1");
        when(entity.getFontEntity()).thenReturn(fontEntity);
        CfgLoghiMessaggiConfigurationDTO dto = new CfgLoghiMessaggiConfigurationDTO(entity);
        assertThat(dto.getCodiceCanale(), is(1L));
        assertThat(dto.getCodiceMeccanica(), is("MECH01"));
        assertThat(dto.getCodiceDispositivo(), is("DEV01"));
        assertThat(dto.getSeqStampa(), is((short) 2));
        assertThat(dto.getSezione(), is(MessaggiSezioneEnum.MESSAGGI.name()));
        assertThat(dto.getTesto(), is("Testo di prova"));
        assertTrue(dto.getTaglioCarta());
        assertFalse(dto.getBarcode());
        assertThat(dto.getFont(), is(MessaggiFontEnum.NORMALE.getValue()));
        assertThat(dto.getAllineamento(), is(MessaggiAllineamentoEnum.CENTRO.getValue()));
        assertTrue(dto.getBold());
        assertThat(dto.getLogo(), is("logo.png"));
        assertThat(dto.getBottone(), is(bottone));
        assertFalse(dto.getCodice());
        assertThat(dto.getRegolamento(), is("Regolamento ABC"));
        assertTrue(dto.getBarra());
        assertThat(dto.getFontStile(), is(fontStile));
        assertThat(dto.getIdMessaggio(), is(202));
        assertThat(dto.getVariabile(), is("VAR1"));
        assertThat(dto.getFontEntity(), is(fontEntity));
    }

    @Test
    public void constructorFromEntity_shouldHandleNullValues() {
        MuiCfgDefaultCastellettoMessaggiEntity entity = mock(MuiCfgDefaultCastellettoMessaggiEntity.class);
        when(entity.getSezione()).thenReturn(null);
        when(entity.getFont()).thenReturn(null);
        when(entity.getAllineamento()).thenReturn(null);
        CfgLoghiMessaggiConfigurationDTO dto = new CfgLoghiMessaggiConfigurationDTO(entity);
        assertNull(dto.getSezione());
        assertNull(dto.getFont());
        assertNull(dto.getAllineamento());
    }

    @Test
    public void update_shouldSetAllEntityFieldsCorrectly() {
        CfgLoghiMessaggiConfigurationDTO dto = new CfgLoghiMessaggiConfigurationDTO();
        MuiBottoneEntity bottone = mock(MuiBottoneEntity.class);
        MuiFontStileEntity fontStile = mock(MuiFontStileEntity.class);
        MuiFontEntities fontEntity = mock(MuiFontEntities.class);
        dto.setCodiceCanale(1L);
        dto.setCodiceMeccanica("MECH01");
        dto.setCodiceDispositivo("DEV01");
        dto.setSeqStampa((short) 2);
        dto.setSezione(MessaggiSezioneEnum.MESSAGGI.name());
        dto.setTesto("Testo di prova");
        dto.setTaglioCarta(true);
        dto.setBarcode(false);
        dto.setFont(MessaggiFontEnum.NORMALE.getValue());
        dto.setAllineamento(MessaggiAllineamentoEnum.CENTRO.getValue());
        dto.setBold(true);
        dto.setLogo("logo.png");
        dto.setBottone(bottone);
        dto.setCodice(false);
        dto.setRegolamento("Regolamento ABC");
        dto.setBarra(true);
        dto.setFontStile(fontStile);
        dto.setIdMessaggio(202);
        dto.setVariabile("VAR1");
        dto.setFontEntity(fontEntity);
        MuiCfgDefaultCastellettoMessaggiEntity entity = mock(MuiCfgDefaultCastellettoMessaggiEntity.class);
        dto.update(entity);
        verify(entity).setCodiceCanale(1L);
        verify(entity).setCodiceMeccanica("MECH01");
        verify(entity).setCodiceDispositivo("DEV01");
        verify(entity).setSeqStampa((short) 2);
        verify(entity).setSezione(MessaggiSezioneEnum.MESSAGGI);
        verify(entity).setTesto("Testo di prova");
        verify(entity).setTaglioCarta(true);
        verify(entity).setBarcode(false);
        verify(entity).setFont(MessaggiFontEnum.NORMALE);
        verify(entity).setAllineamento(MessaggiAllineamentoEnum.CENTRO);
        verify(entity).setBold(true);
        verify(entity).setLogo("logo.png");
        verify(entity).setBottone(bottone);
        verify(entity).setCodice(false);
        verify(entity).setRegolamento("Regolamento ABC");
        verify(entity).setBarra(true);
        verify(entity).setFontStile(fontStile);
        verify(entity).setIdMessaggio(202);
        verify(entity).setVariabile("VAR1");
        verify(entity).setFontEntity(fontEntity);
    }

    @Test
    public void update_shouldHandleNullValues() {
        CfgLoghiMessaggiConfigurationDTO dto = new CfgLoghiMessaggiConfigurationDTO();
        dto.setSezione(null);
        dto.setFont(null);
        dto.setAllineamento(null);
        MuiCfgDefaultCastellettoMessaggiEntity entity = mock(MuiCfgDefaultCastellettoMessaggiEntity.class);
        dto.update(entity);
        verify(entity).setSezione(null);
        verify(entity).setFont(null);
        verify(entity).setAllineamento(null);
    }

    @Test
    public void equalsAndHashCode_shouldBeBasedOnIncludedFields() {
        CfgLoghiMessaggiConfigurationDTO dto1 = new CfgLoghiMessaggiConfigurationDTO();
        dto1.setCodiceCanale(1L);
        dto1.setCodiceMeccanica("MECH01");
        dto1.setCodiceDispositivo("DEV01");
        dto1.setSeqStampa((short) 2);
        dto1.setTesto("First");
        CfgLoghiMessaggiConfigurationDTO dto2 = new CfgLoghiMessaggiConfigurationDTO();
        dto2.setCodiceCanale(1L);
        dto2.setCodiceMeccanica("MECH01");
        dto2.setCodiceDispositivo("DEV01");
        dto2.setSeqStampa((short) 2);
        dto2.setTesto("Second");
        assertThat(dto1, is(equalTo(dto2)));
        assertThat(dto1.hashCode(), is(equalTo(dto2.hashCode())));
        dto2.setCodiceDispositivo("DEV02");
        assertThat(dto1, is(not(equalTo(dto2))));
        assertThat(dto1.hashCode(), is(not(equalTo(dto2.hashCode()))));
    }
}