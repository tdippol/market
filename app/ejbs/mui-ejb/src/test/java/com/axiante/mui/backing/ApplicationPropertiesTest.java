package com.axiante.mui.backing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
//import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.service.MuiService;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationPropertiesTest {

	@Mock
	MuiService muiService;
	@Spy
	@InjectMocks
    ApplicationProperties applicationProperties;
	static final String AUTH_SERVER = "http://mytestserver.mok.something.com";
	static final Integer TOKEN_SPAWN = 300;
	static final Double CONNECTION_TIMEOUT = 10.125;
    @Before
    public void setUp() throws Exception {
        List<ApplicationPropertiesEntity> applicationPropertiesEntityList = new ArrayList<>();
        applicationPropertiesEntityList.add(createApplicationPropertiesEntity(ApplicationProperties.AUTH_SERVER,AUTH_SERVER));
        applicationPropertiesEntityList.add(createApplicationPropertiesEntity(ApplicationProperties.TOKEN_SPAWN,TOKEN_SPAWN.toString()));
        applicationPropertiesEntityList.add(createApplicationPropertiesEntity(ApplicationProperties.CONNECTION_TIMEOUT,CONNECTION_TIMEOUT.toString()));
        
        applicationPropertiesEntityList.add(createApplicationPropertiesEntity("EXCEPTION","not a number"));

        when(muiService.readApplicationProperties()).thenReturn(applicationPropertiesEntityList);

    	applicationProperties.init();
    	verify(applicationProperties, times(1)).update();
    }

    private ApplicationPropertiesEntity createApplicationPropertiesEntity(String key,String value){
        ApplicationPropertiesEntity applicationPropertiesEntity = new ApplicationPropertiesEntity();
        applicationPropertiesEntity.setKey(key);
        applicationPropertiesEntity.setValue(value);
        return applicationPropertiesEntity;
    }

    @Test
    public void testUpdate() {
        boolean result = applicationProperties.update();
        verify(applicationProperties, times(2)).update(); // the first time is in the @before
        assertTrue(result);
    }
    @Test
    public void testUpdateReturnsFalse() throws Exception {
    	doThrow(Exception.class).when(muiService).readApplicationProperties();
        boolean result = applicationProperties.update();
        verify(applicationProperties, times(2)).update(); // the first time is in the @before
        assertFalse(result);
    }

    @Test
    public void getPropertyReturnsString() {
        String connectionTimeout = applicationProperties.getProperty(ApplicationProperties.AUTH_SERVER,"failure");
        assertEquals(AUTH_SERVER,connectionTimeout);
    }
    
    @Test public void getPropertyReturnsDefaultString() {
        String connectionTimeout = applicationProperties.getProperty("not a property","success");
        assertEquals("success",connectionTimeout);
    }

    @Test
    public void getPropertyReturnsDouble() {
        Double connectionTimeout = applicationProperties.getProperty(ApplicationProperties.CONNECTION_TIMEOUT,ApplicationProperties.DEFAULT_CONNECTION_TIMEOUT);
        assertEquals(CONNECTION_TIMEOUT,connectionTimeout);
    }

    @Test
    public void getPropertyReturnsDefaultDouble() {
        Double connectionTimeout = applicationProperties.getProperty("wrongKey",ApplicationProperties.DEFAULT_CONNECTION_TIMEOUT);
        assertEquals(ApplicationProperties.DEFAULT_CONNECTION_TIMEOUT,connectionTimeout);
    }
    @Test public void getPropertyDoubleReturnsDefaultWhenNotANumber() {
    	assertThat(1d, CoreMatchers.equalTo(applicationProperties.getProperty("EXCEPTION", 1d)));
    }

    @Test
    public void getPropertyReturnsInteger() {
        Integer res = applicationProperties.getProperty(ApplicationProperties.TOKEN_SPAWN, ApplicationProperties.DEFAULT_TOKEN_SPAWN);
        assertEquals(TOKEN_SPAWN,res);
    }
    @Test
    public void getPropertyReturnsDefaultInteger() {
    	Integer test = 123456;
        Integer res = applicationProperties.getProperty("not a property", test);
        assertEquals(test,res);
    }
    @Test public void getPropertyIntegerReturnsDefaultWhenNotANumber() {
    	Integer test = 1254;
    	assertThat(test, CoreMatchers.equalTo(applicationProperties.getProperty("EXCEPTION", test)));
    }

    @Test
    public void getMuiService() {
        assertNotNull(applicationProperties.getMuiService());
    }
    
    @Test public void testGetPropertyReturnsValue() {
    	assertNotNull(applicationProperties.getProperty(ApplicationProperties.AUTH_SERVER));
    }
    @Test public void testGetPropertyReturnsNull() {
    	applicationProperties.update();
    	assertNull(applicationProperties.getProperty("not a property"));
    }

}