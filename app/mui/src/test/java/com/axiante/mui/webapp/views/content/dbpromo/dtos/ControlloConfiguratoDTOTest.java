package com.axiante.mui.webapp.views.content.dbpromo.dtos;

import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.webapp.business.supportimedia.SupportoMediaCheckEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ControlloConfiguratoDTOTest {

    @Test
    public void constructor_shouldSetCheckEnum() {
        ControlloConfiguratoDTO underTest = new ControlloConfiguratoDTO(SupportoMediaCheckEnum.values()[0]);
        assertThat(underTest.getCheckEnum(), equalTo(SupportoMediaCheckEnum.values()[0]));
    }

    @Test
    public void defaultState_shouldBeDisabledAndWithoutSupportoMedia() {
        ControlloConfiguratoDTO underTest = new ControlloConfiguratoDTO(SupportoMediaCheckEnum.values()[0]);
        assertThat(underTest.isAbilitato(), equalTo(false));
        assertNull(underTest.getSupportoMedia());
    }

    @Test
    public void setters_shouldUpdateFields() {
        ControlloConfiguratoDTO underTest = new ControlloConfiguratoDTO(SupportoMediaCheckEnum.values()[0]);
        SupportoMediaEntity supportoMedia = new SupportoMediaEntity();
        underTest.setSupportoMedia(supportoMedia);
        underTest.setAbilitato(true);
        assertThat(underTest.getSupportoMedia(), equalTo(supportoMedia));
        assertThat(underTest.isAbilitato(), equalTo(true));
    }

    @Test
    public void reset_shouldClearSupportoMediaAndDisableFlag() {
        ControlloConfiguratoDTO underTest = new ControlloConfiguratoDTO(SupportoMediaCheckEnum.values()[0]);
        underTest.setSupportoMedia(new SupportoMediaEntity());
        underTest.setAbilitato(true);
        underTest.reset();
        assertNull(underTest.getSupportoMedia());
        assertThat(underTest.isAbilitato(), equalTo(false));
    }
}