package com.axiante.mui.webapp.views.content;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.service.ApplicationPropertiesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class WorkInProgressViewTest {

    @Test
    public void init_shouldKeepDefaultMessageWhenServiceReturnsNull() throws Exception {
        WorkInProgressView underTest = new WorkInProgressView();
        ApplicationPropertiesService service = Mockito.mock(ApplicationPropertiesService.class);
        Mockito.when(service.getPropertiesEntity(ApplicationProperties.ADMIN_MODE_USER_MESSAGE)).thenReturn(null);
        setField(underTest, "service", service);
        underTest.init();
        assertThat(underTest.getMessage(), equalTo("Applicazione in manutenzione"));
    }

    @Test
    public void init_shouldSetCustomMessageWhenServiceReturnsEntity() throws Exception {
        WorkInProgressView underTest = new WorkInProgressView();
        ApplicationPropertiesService service = Mockito.mock(ApplicationPropertiesService.class);
        ApplicationPropertiesEntity entity = Mockito.mock(ApplicationPropertiesEntity.class);
        Mockito.when(entity.getValue()).thenReturn("Custom maintenance message");
        Mockito.when(service.getPropertiesEntity(ApplicationProperties.ADMIN_MODE_USER_MESSAGE)).thenReturn(entity);
        setField(underTest, "service", service);
        underTest.init();
        assertThat(underTest.getMessage(), equalTo("Custom maintenance message"));
    }

    @Test
    public void init_shouldKeepDefaultMessageWhenServiceThrowsException() throws Exception {
        WorkInProgressView underTest = new WorkInProgressView();
        ApplicationPropertiesService service = Mockito.mock(ApplicationPropertiesService.class);
        Mockito.when(service.getPropertiesEntity(ApplicationProperties.ADMIN_MODE_USER_MESSAGE)).thenThrow(new RuntimeException("boom"));
        setField(underTest, "service", service);
        underTest.init();
        assertThat(underTest.getMessage(), equalTo("Applicazione in manutenzione"));
    }

    private static void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}