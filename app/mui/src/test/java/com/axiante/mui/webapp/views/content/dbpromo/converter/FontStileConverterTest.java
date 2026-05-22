package com.axiante.mui.webapp.views.content.dbpromo.converter;

import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiFontStileService;
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
public class FontStileConverterTest {
    @Mock
    private Instance<MuiFontStileService> muiFontStileServiceInstance;

    @Mock
    private MuiFontStileService muiFontStileService;

    @Before
    public void setUp() throws Exception {
        when(muiFontStileServiceInstance.get()).thenReturn(muiFontStileService);
    }

    @Test
    public void getAsObject_shouldReturnNullWhenValueIsNull() {
        FontStileConverter underTest = new FontStileConverter();
        assertNull(underTest.getAsObject(null, null, null));
    }

    @Test
    public void getAsObject_shouldReturnNullWhenValueIsBlank() {
        FontStileConverter underTest = new FontStileConverter();
        assertNull(underTest.getAsObject(null, null, "   "));
    }

    @Test
    public void getAsObject_shouldReturnNullWhenValueIsSelectOption() {
        FontStileConverter underTest = new FontStileConverter();
        assertNull(underTest.getAsObject(null, null, "SELEZIONA"));
    }

    @Test
    public void getAsObject_shouldDelegateToServiceWhenValueIsValid() {
        FontStileConverter underTest = new FontStileConverter();
        MuiFontStileEntity entity = mock(MuiFontStileEntity.class);
        when(muiFontStileServiceInstance.get()).thenReturn(muiFontStileService);
        when(muiFontStileService.findById("12")).thenReturn(entity);
        setField(underTest, "muiFontStileService", muiFontStileServiceInstance);
        assertThat(underTest.getAsObject(null, null, "12"), equalTo(entity));
        verify(muiFontStileService).findById("12");
    }

    @Test
    public void getAsObject_shouldReturnNullWhenServiceThrowsException() {
        FontStileConverter underTest = new FontStileConverter();
        when(muiFontStileServiceInstance.get()).thenReturn(muiFontStileService);
        when(muiFontStileService.findById("12")).thenThrow(new RuntimeException("boom"));
        setField(underTest, "muiFontStileService", muiFontStileServiceInstance);
        assertNull(underTest.getAsObject(null, null, "12"));
    }

    @Test
    public void getAsString_shouldReturnNullWhenObjectIsNull() {
        FontStileConverter underTest = new FontStileConverter();
        assertNull(underTest.getAsString(null, null, null));
    }

    @Test
    public void getAsString_shouldReturnEntityId() {
        FontStileConverter underTest = new FontStileConverter();
        MuiFontStileEntity entity = mock(MuiFontStileEntity.class);
        when(entity.getId()).thenReturn("99");
        assertThat(underTest.getAsString(null, null, entity), equalTo("99"));
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