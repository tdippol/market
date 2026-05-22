package com.axiante.mui.webapp.views.login;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginProducerTest {

    @Mock
    private MuiService muiService;

    @Mock
    private HttpServletRequest request;

    @Spy
    @InjectMocks
    private LoginProducer loginProducer;

    @Test
    public void executeUserLogin_givenExistingName_shouldReturnUserEntity() throws Exception {
        final UsersEntity user = mock(UsersEntity.class);
        when(muiService.findUser("foo")).thenReturn(user);
        when(muiService.persistUser(any(UsersEntity.class))).thenReturn(user);
        assertNotNull(loginProducer.executeUserLogin("foo"));
        verify(muiService, times(1)).persistUser(any(UsersEntity.class));
        verify(user, times(1)).setLastAccess(any(Date.class));
    }

    @Test
    public void executeUserLogin_givenNotExistingName_shouldReturnNull() throws Exception {
        when(muiService.findUser("bar")).thenReturn(null);
        assertNull(loginProducer.executeUserLogin("bar"));
        verify(muiService, never()).persistUser(any(UsersEntity.class));
    }

    @Test
    public void executeUserLogin_whenSomethingWentWrong_shouldReturnNull() throws Exception {
        when(muiService.findUser("baz")).thenThrow(Exception.class);
        assertNull(loginProducer.executeUserLogin("baz"));
        verify(muiService, never()).persistUser(any(UsersEntity.class));
    }

    @Test
    public void getTm1AuthString_givenValidRequest_shouldReturnConnectionString() {
        when(request.getRequestURI()).thenReturn("request/basePath");
        when(request.getRequestURL()).thenReturn(new StringBuffer("request/basePath/subPath"));
        final String expectedString = "biServer"
                + "?b_action=xts.run&m=portal/bridge.xts&c_env=portal/variables_TM1.xml&c_cmd=../tm1/web/tm1web.html"
                + "&ps=request/basePath"
                + "&pg=cam.html&host=localhost";
        final String actualString = loginProducer.getTm1AuthString(request, "biServer");
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getTm1AuthString_whenSomethingWentWrong_shouldReturnLocalhost() {
        when(request.getRequestURI()).thenThrow(RuntimeException.class);
        assertEquals("localhost", loginProducer.getTm1AuthString(request, "biServer"));
    }
}