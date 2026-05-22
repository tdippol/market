package com.axiante.mui.webapp.views.content.admin.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CfgDescrizioneMessaggiDTOTest {

    @Test
    public void initialState_shouldHaveNullsAndEditFalse() {
        CfgDescrizioneMessaggiDTO dto = new CfgDescrizioneMessaggiDTO();
        assertThat(dto.getIdMessaggio(), is(nullValue()));
        assertThat(dto.getDescrizione(), is(nullValue()));
        assertFalse(dto.isEdit());
    }

    @Test
    public void gettersAndSetters_shouldWorkCorrectly() {
        CfgDescrizioneMessaggiDTO dto = new CfgDescrizioneMessaggiDTO();
        dto.setIdMessaggio(202);
        dto.setDescrizione("Another description");
        dto.setEdit(true);
        assertThat(dto.getIdMessaggio(), is(202));
        assertThat(dto.getDescrizione(), is("Another description"));
        assertTrue(dto.isEdit());
    }

    @Test
    public void clear_shouldResetAllFieldsToInitialState() {
        CfgDescrizioneMessaggiDTO dto = new CfgDescrizioneMessaggiDTO();
        dto.setIdMessaggio(101);
        dto.setDescrizione("Some description");
        dto.setEdit(true);
        dto.clear();
        assertThat(dto.getIdMessaggio(), is(nullValue()));
        assertThat(dto.getDescrizione(), is(nullValue()));
        assertFalse(dto.isEdit());
    }

    @Test
    public void validate_shouldReturnTrueWhenBothFieldsAreSet() {
        CfgDescrizioneMessaggiDTO dto = new CfgDescrizioneMessaggiDTO();
        dto.setIdMessaggio(101);
        dto.setDescrizione("Some description");
        assertTrue(dto.validate());
    }

    @Test
    public void validate_shouldReturnFalseIfAnyFieldIsMissing() {
        CfgDescrizioneMessaggiDTO dtoWithIdOnly = new CfgDescrizioneMessaggiDTO();
        dtoWithIdOnly.setIdMessaggio(101);
        CfgDescrizioneMessaggiDTO dtoWithDescOnly = new CfgDescrizioneMessaggiDTO();
        dtoWithDescOnly.setDescrizione("Some description");
        CfgDescrizioneMessaggiDTO emptyDto = new CfgDescrizioneMessaggiDTO();
        assertFalse("Should be invalid when description is null", dtoWithIdOnly.validate());
        assertFalse("Should be invalid when id is null", dtoWithDescOnly.validate());
        assertFalse("Should be invalid when both are null", emptyDto.validate());
    }
}