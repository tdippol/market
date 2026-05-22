package com.axiante.mui.webapp.views.content.dbpromo.converter;

import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiBottoneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.enterprise.inject.Instance;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MuiBottoneConverterTest {
    @Mock
    private Instance<MuiBottoneService> muiBottoneServiceInstance;

    @Mock
    private MuiBottoneService muiBottoneService;

    @Before
    public void setUp() throws Exception {
        when(muiBottoneServiceInstance.get()).thenReturn(muiBottoneService);
    }

    @Test
    public void getAsObject_shouldReturnNullWhenValueIsNull() {
        MuiBottoneConverter underTest = new MuiBottoneConverter();
        assertNull(underTest.getAsObject(null, null, null));
    }

    @Test
    public void getAsObject_shouldReturnNullWhenValueIsBlank() {
        MuiBottoneConverter underTest = new MuiBottoneConverter();
        assertNull(underTest.getAsObject(null, null, "   "));
    }

    @Test
    public void getAsObject_shouldReturnNullWhenValueIsSelectOption() {
        MuiBottoneConverter underTest = new MuiBottoneConverter();
        assertNull(underTest.getAsObject(null, null, "SELEZIONA"));
    }

    @Test
    public void getAsObject_shouldDelegateToServiceWhenValueIsValid() {
        MuiBottoneConverter underTest = new MuiBottoneConverter();
        MuiBottoneEntity entity = mock(MuiBottoneEntity.class);
        when(muiBottoneServiceInstance.get()).thenReturn(muiBottoneService);
        when(muiBottoneService.findById(7L)).thenReturn(entity);
        setField(underTest, "muiBottoneServiceInstance", muiBottoneServiceInstance);
        assertThat(underTest.getAsObject(null, null, "7"), equalTo(entity));
        verify(muiBottoneService).findById(7L);
    }

    @Test
    public void getAsObject_shouldReturnNullWhenParsingOrServiceFails() {
        MuiBottoneConverter underTest = new MuiBottoneConverter();
        when(muiBottoneServiceInstance.get()).thenReturn(muiBottoneService);
        when(muiBottoneService.findById(7L)).thenThrow(new RuntimeException("boom"));
        setField(underTest, "muiBottoneServiceInstance", muiBottoneServiceInstance);
        assertNull(underTest.getAsObject(null, null, "7"));
    }

    @Test
    public void getAsString_shouldReturnNullWhenObjectIsNull() {
        MuiBottoneConverter underTest = new MuiBottoneConverter();
        assertNull(underTest.getAsString(null, null, null));
    }

    @Test
    public void getAsString_shouldReturnNullWhenEntityIdIsNull() {
        MuiBottoneConverter underTest = new MuiBottoneConverter();
        MuiBottoneEntity entity = mock(MuiBottoneEntity.class);
        when(entity.getId()).thenReturn(null);
        when(entity.getDescrizione()).thenReturn("Button");
        assertNull(underTest.getAsString(null, null, entity));
    }

    @Test
    public void getAsString_shouldReturnIdAsString() {
        MuiBottoneConverter underTest = new MuiBottoneConverter();
        MuiBottoneEntity entity = mock(MuiBottoneEntity.class);
        when(entity.getId()).thenReturn(15L);
        assertThat(underTest.getAsString(null, null, entity), equalTo("15"));
    }

    private static void setField(Object target, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}