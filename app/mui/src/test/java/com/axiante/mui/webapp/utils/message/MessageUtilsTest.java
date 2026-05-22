package com.axiante.mui.webapp.utils.message;

import com.axiante.mui.webapp.views.content.aggrid.actions.SelectedAction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.faces.application.FacesMessage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MessageUtilsTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void toFacesMessage_shouldThrowWhenActionIsNull() {
        ex.expect(NullPointerException.class);
        MessageUtils.toFacesMessage(null);
    }

    @Test
    public void toFacesMessage_shouldUseDefaultValuesWhenAllCustomValuesAreNull() {
        SelectedAction action = Mockito.mock(SelectedAction.class);
        Mockito.when(action.getCustomMessageLevel()).thenReturn(null);
        Mockito.when(action.getCustomMessageTitle()).thenReturn(null);
        Mockito.when(action.getCustomMessage()).thenReturn(null);
        FacesMessage message = MessageUtils.toFacesMessage(action);
        assertThat(message.getSeverity(), equalTo(FacesMessage.SEVERITY_WARN));
        assertThat(message.getSummary(), equalTo("Errore!"));
        assertThat(message.getDetail(), equalTo("Errore durante l'esecuzione del processo."));
    }

    @Test
    public void toFacesMessage_shouldMapErrorLevelAndUseCustomTitleAndMessage() {
        SelectedAction action = Mockito.mock(SelectedAction.class);
        Mockito.when(action.getCustomMessageLevel()).thenReturn("ERROR");
        Mockito.when(action.getCustomMessageTitle()).thenReturn("Custom title");
        Mockito.when(action.getCustomMessage()).thenReturn("Custom message");
        FacesMessage message = MessageUtils.toFacesMessage(action);
        assertThat(message.getSeverity(), equalTo(FacesMessage.SEVERITY_ERROR));
        assertThat(message.getSummary(), equalTo("Custom title"));
        assertThat(message.getDetail(), equalTo("Custom message"));
    }

    @Test
    public void toFacesMessage_shouldMapFatalLevelCaseInsensitively() {
        SelectedAction action = Mockito.mock(SelectedAction.class);
        Mockito.when(action.getCustomMessageLevel()).thenReturn("fatal");
        Mockito.when(action.getCustomMessageTitle()).thenReturn("Title");
        Mockito.when(action.getCustomMessage()).thenReturn("Message");
        FacesMessage message = MessageUtils.toFacesMessage(action);
        assertThat(message.getSeverity(), equalTo(FacesMessage.SEVERITY_FATAL));
    }

    @Test
    public void toFacesMessage_shouldMapInfoLevelCaseInsensitively() {
        SelectedAction action = Mockito.mock(SelectedAction.class);
        Mockito.when(action.getCustomMessageLevel()).thenReturn("InFo");
        Mockito.when(action.getCustomMessageTitle()).thenReturn("Title");
        Mockito.when(action.getCustomMessage()).thenReturn("Message");
        FacesMessage message = MessageUtils.toFacesMessage(action);
        assertThat(message.getSeverity(), equalTo(FacesMessage.SEVERITY_INFO));
    }

    @Test
    public void toFacesMessage_shouldFallbackToWarnForUnknownLevel() {
        SelectedAction action = Mockito.mock(SelectedAction.class);
        Mockito.when(action.getCustomMessageLevel()).thenReturn("something-else");
        Mockito.when(action.getCustomMessageTitle()).thenReturn("Title");
        Mockito.when(action.getCustomMessage()).thenReturn("Message");
        FacesMessage message = MessageUtils.toFacesMessage(action);
        assertThat(message.getSeverity(), equalTo(FacesMessage.SEVERITY_WARN));
    }

    @Test
    public void toFacesMessage_shouldUseDefaultTitleWhenTitleIsNull() {
        SelectedAction action = Mockito.mock(SelectedAction.class);
        Mockito.when(action.getCustomMessageLevel()).thenReturn("ERROR");
        Mockito.when(action.getCustomMessageTitle()).thenReturn(null);
        Mockito.when(action.getCustomMessage()).thenReturn("Message");
        FacesMessage message = MessageUtils.toFacesMessage(action);
        assertThat(message.getSummary(), equalTo("Errore!"));
        assertThat(message.getDetail(), equalTo("Message"));
    }

    @Test
    public void toFacesMessage_shouldUseDefaultMessageWhenMessageIsNull() {
        SelectedAction action = Mockito.mock(SelectedAction.class);
        Mockito.when(action.getCustomMessageLevel()).thenReturn("ERROR");
        Mockito.when(action.getCustomMessageTitle()).thenReturn("Title");
        Mockito.when(action.getCustomMessage()).thenReturn(null);
        FacesMessage message = MessageUtils.toFacesMessage(action);
        assertThat(message.getSummary(), equalTo("Title"));
        assertThat(message.getDetail(), equalTo("Errore durante l'esecuzione del processo."));
    }
}