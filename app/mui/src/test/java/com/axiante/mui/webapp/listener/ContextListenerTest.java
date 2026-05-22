package com.axiante.mui.webapp.listener;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.dbpromo.persistence.service.UuiUtilityService;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.service.MuiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContextListenerTest {

    @InjectMocks
    private ContextListener underTest;

    @Mock
    private ApplicationProperties props;
    @Mock
    private MuiService muiService;
    @Mock
    private UuiUtilityService dbpromoService;

    @Before
    public void setUp() throws Exception {
        // Since workInProgress is static, we must reset it before each test
        // to ensure test isolation.
        Field field = ContextListener.class.getDeclaredField("workInProgress");
        field.setAccessible(true);
        ((AtomicBoolean) field.get(null)).set(false);
    }

    @Test
    public void contextInitialized_whenUpdateIsFalse_shouldDoNothing() throws Exception {
        when(props.getProperty(ApplicationProperties.UPDATE_UUID, (Boolean) null)).thenReturn(false);
        underTest.contextInitialized(null);
        verify(muiService, never()).readMenus();
        verify(dbpromoService, never()).ensureAllNonEmptyUuid();
    }

    @Test
    public void contextInitialized_whenPropsIsNull_shouldDoNothing() throws Exception {
        // Manually set the field to null to override @InjectMocks
        setField(underTest, "props", null);
        underTest.contextInitialized(null);
        verify(muiService, never()).findApplicationProperty(anyString());
    }

    @Test
    public void contextInitialized_whenAlreadyInProgress_shouldDoNothing() throws Exception {
        // Manually set the static flag to true
        Field field = ContextListener.class.getDeclaredField("workInProgress");
        field.setAccessible(true);
        ((AtomicBoolean) field.get(null)).set(true);
        underTest.contextInitialized(null);
        verify(props, never()).getProperty(anyString(), (String) any());
    }

    @Test
    public void contextInitialized_whenUpdateIsTrue_shouldRunAllUpdateTasks() throws Exception {
        when(props.getProperty(ApplicationProperties.UPDATE_UUID, (Boolean) null)).thenReturn(true);
        when(muiService.findApplicationProperty(ApplicationProperties.UPDATE_UUID)).thenReturn(mock(ApplicationPropertiesEntity.class));
        // Mock service read calls to return empty lists to avoid NullPointerExceptions
        when(muiService.readMenus()).thenReturn(Collections.emptyList());
        when(muiService.readACLs()).thenReturn(Collections.emptyList());
        when(muiService.readApplicationProperties()).thenReturn(Collections.emptyList());
        when(muiService.readConfigurations()).thenReturn(Collections.emptyList());
        when(muiService.readRoles()).thenReturn(Collections.emptyList());
        when(muiService.readUsers()).thenReturn(Collections.emptyList());
        underTest.contextInitialized(null);
        verify(muiService).persistApplicationProperty(any(ApplicationPropertiesEntity.class));
        verify(muiService).readMenus();
        verify(muiService).readACLs();
        verify(muiService).readApplicationProperties();
        verify(muiService).readConfigurations();
        verify(muiService).readRoles();
        verify(muiService).readUsers();
        verify(dbpromoService).ensureAllNonEmptyUuid();
    }

    @Test
    public void contextInitialized_whenUpdateIsNullAndPropertyIsMissing_shouldCreatePropertyAndRunUpdates() throws Exception {
        when(props.getProperty(ApplicationProperties.UPDATE_UUID, (Boolean) null)).thenReturn(null);
        when(muiService.findApplicationProperty(ApplicationProperties.UPDATE_UUID)).thenReturn(null);
        // Mock service read calls to return empty lists
        when(muiService.readMenus()).thenReturn(Collections.emptyList());
        when(muiService.readACLs()).thenReturn(Collections.emptyList());
        when(muiService.readApplicationProperties()).thenReturn(Collections.emptyList());
        when(muiService.readConfigurations()).thenReturn(Collections.emptyList());
        when(muiService.readRoles()).thenReturn(Collections.emptyList());
        when(muiService.readUsers()).thenReturn(Collections.emptyList());
        underTest.contextInitialized(null);
        // Should persist the property twice: once to create it, once inside the final update block
        ArgumentCaptor<ApplicationPropertiesEntity> captor = ArgumentCaptor.forClass(ApplicationPropertiesEntity.class);
        verify(muiService, times(2)).persistApplicationProperty(captor.capture());
        verify(muiService).readMenus();
        verify(dbpromoService).ensureAllNonEmptyUuid();
    }

    @Test
    public void contextInitialized_whenUpdateIsNullAndPropertyExists_shouldUpdatePropertyAndRunUpdates() throws Exception {
        when(props.getProperty(ApplicationProperties.UPDATE_UUID, (Boolean) null)).thenReturn(null);
        ApplicationPropertiesEntity existingProperty = mock(ApplicationPropertiesEntity.class);
        when(muiService.findApplicationProperty(ApplicationProperties.UPDATE_UUID)).thenReturn(existingProperty);
        // Mock service read calls to return empty lists
        when(muiService.readMenus()).thenReturn(Collections.emptyList());
        when(muiService.readACLs()).thenReturn(Collections.emptyList());
        when(muiService.readApplicationProperties()).thenReturn(Collections.emptyList());
        when(muiService.readConfigurations()).thenReturn(Collections.emptyList());
        when(muiService.readRoles()).thenReturn(Collections.emptyList());
        when(muiService.readUsers()).thenReturn(Collections.emptyList());
        underTest.contextInitialized(null);
        // Verify it was called twice, once in each `if` block that deals with the property
        verify(muiService, times(2)).persistApplicationProperty(existingProperty);
        verify(existingProperty, never()).setKey(anyString()); // Should not set key on existing property
        verify(muiService).readMenus();
        verify(dbpromoService).ensureAllNonEmptyUuid();
    }

    @Test
    public void updateTasks_shouldContinueEvenIfOneFails() throws Exception {
        when(props.getProperty(ApplicationProperties.UPDATE_UUID, (Boolean) null)).thenReturn(true);
        when(muiService.findApplicationProperty(ApplicationProperties.UPDATE_UUID)).thenReturn(mock(ApplicationPropertiesEntity.class));
        // Simulate a failure in one of the update methods
        when(muiService.readMenus()).thenThrow(new RuntimeException("Database connection failed!"));
        // The other methods should still be called
        when(muiService.readACLs()).thenReturn(Collections.emptyList());
        when(muiService.readUsers()).thenReturn(Collections.emptyList());
        underTest.contextInitialized(null);
        verify(muiService).readMenus(); // This one is called and throws
        verify(muiService).readACLs(); // This one should still be called
        verify(muiService).readUsers(); // This one should still be called
        verify(dbpromoService).ensureAllNonEmptyUuid(); // This one should definitely be called
    }

    @Test
    public void contextDestroyed_shouldDoNothing() {
        // This test simply ensures the method can be called without throwing an exception.
        underTest.contextDestroyed(null);
    }

    private static void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}