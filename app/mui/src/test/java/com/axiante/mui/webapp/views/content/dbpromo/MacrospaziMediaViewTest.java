package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.dbpromo.persistence.entities.MuiEventoRetailEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiEventoRetailService;
import com.axiante.mui.dbpromo.persistence.service.MuiMacrospazioMediaService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.utils.MacrospaziMediaUtils;
import com.axiante.mui.webapp.views.content.dbpromo.macrospaziMedia.EventiDialog;
import com.axiante.mui.webapp.views.content.dbpromo.macrospaziMedia.MacrospazioDialog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.faces.application.FacesMessage;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MacrospaziMediaViewTest {
    @Mock
    private MuiMacrospazioMediaService macrospazioService;

    @Mock
    private MacrospaziMediaUtils macrospaziUtils;

    @Mock
    private MuiEventoRetailService eventoRetailService;

    @Mock
    private EventiDialog eventiDialog;

    @Spy
    @InjectMocks
    private MacrospaziMediaView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        UsersEntity mockedUser = mock(UsersEntity.class);
        when(mockedUser.getName()).thenReturn("testUser");
        doReturn(mockedUser).when(view).getCurrentUser();
        // Mock addMessage to do nothing
        doNothing().when(view).addMessage(any(), any(FacesMessage.class));
        when(eventiDialog.getBean()).thenReturn(mock(MuiEventoRetailEntity.class));
    }

    @Test
    public void testInitWithNonEmptyMacrospazi() {
        MuiMacrospazioMediaEntity macro = mock(MuiMacrospazioMediaEntity.class);
        when(macrospazioService.findAll()).thenReturn(Collections.singletonList(macro));
        view.init();
        assertNotNull(view.getMacrospazi());
        assertNull(view.getSelectedMacrospazio());
    }

    @Test
    public void testInitWithEmptyMacrospazi() {
        when(macrospazioService.findAll()).thenReturn(Collections.emptyList());
        view.init();
        assertNotNull(view.getMacrospazi());
        assertNull(view.getSelectedMacrospazio());
    }

    @Test
    public void testAddMacrospazioButtonClicked() {
        view.addMacrospazioClicked();
        assertNotNull(view.getMacrospazioDialog());
        assertNotNull(view.getMacrospazioDialog().getBean());
    }

    @Test
    public void testAddMacrospazioSuccess() {
        MacrospazioDialog dialog = mock(MacrospazioDialog.class);
        when(view.getMacrospazioDialog()).thenReturn(dialog);
        MuiMacrospazioMediaEntity macro = mock(MuiMacrospazioMediaEntity.class);
        when(dialog.getBean()).thenReturn(macro);
        when(macrospaziUtils.canAdd(macro)).thenReturn(true);
        when(macrospazioService.persistWithAuditLog(eq(macro), anyString())).thenReturn(macro);
        //noinspection unchecked
        when(view.getMacrospazi()).thenReturn(mock(List.class));
        when(view.getAjax()).thenReturn(mock(org.primefaces.PrimeFaces.Ajax.class));
        view.addMacrospazio();
        verify(view).updateMacrospaziList((any(MuiMacrospazioMediaEntity.class)));
        assertEquals(macro, view.getSelectedMacrospazio());
    }

    @Test
    public void testAddMacrospazioFailure() {
        MacrospazioDialog dialog = mock(MacrospazioDialog.class);
        MuiMacrospazioMediaEntity macro = mock(MuiMacrospazioMediaEntity.class);
        when(dialog.getBean()).thenReturn(macro);
        when(view.getMacrospazioDialog()).thenReturn(dialog);
        when(macrospaziUtils.canAdd(macro)).thenReturn(false);
        when(view.getAjax()).thenReturn(mock(org.primefaces.PrimeFaces.Ajax.class));
        view.addMacrospazio();
        verify(view, never()).addInfoMessage(anyString(), anyString());
        verify(view).addErrorMessage(anyString(), anyString());
    }

    @Test
    public void testAddRetailEventButtonClicked() {
        view.addRetailEventButtonClicked();
        assertNotNull(view.getEventiDialog().getBean());
    }

    @Test
    public void testAddRetailEventSuccess() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        EventiDialog dialog = mock(EventiDialog.class);
        when(view.getEventiDialog()).thenReturn(dialog);
        when(dialog.getBean()).thenReturn(event);
        when(view.getEventiDialog().getBean()).thenReturn(event);
        view.addRetailEvent();
        assertEquals(event, view.getEventiDialog().getBean());
    }

    @Test
    public void testAddRetailEventFailure() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        EventiDialog dialog = mock(EventiDialog.class);
        when(view.getEventiDialog()).thenReturn(dialog);
        when(view.getEventiDialog().getBean()).thenReturn(event);
        view.addRetailEvent();
        verify(view).addErrorMessage(anyString(), anyString());
    }

    @Test
    public void testEditRetailEventSuccess() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        MuiEventoRetailEntity original = mock(MuiEventoRetailEntity.class);
        when(view.getSelectedEvent()).thenReturn(event);
        when(event.getId()).thenReturn(1L);
        when(eventoRetailService.findById(1L)).thenReturn(original);
        EventiDialog dialog = mock(EventiDialog.class);
        when(view.getEventiDialog()).thenReturn(dialog);
        when(dialog.getBean()).thenReturn(event);
        view.editRetailEvent();
        assertEquals(event, view.getSelectedEvent());
    }

    @Test
    public void testEditRetailEventFailure() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        MuiEventoRetailEntity original = mock(MuiEventoRetailEntity.class);
        when(event.getId()).thenReturn(1L);
        when(eventoRetailService.findById(1L)).thenReturn(original);
        EventiDialog dialog = mock(EventiDialog.class);
        when(view.getEventiDialog()).thenReturn(dialog);
        when(dialog.getBean()).thenReturn(event);
        view.editRetailEvent();
        verify(view).addWarningMessage(anyString(), anyString());
    }
}