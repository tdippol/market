package com.axiante.mui.webapp.views.login;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.utility.SessionParams;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.template.TemplateView;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginViewTest {

    @Mock
    private LoginProducer loginProducer;

    @Mock
    private TemplateView template;

    @Mock
    private ExternalContext externalContext;

    @Spy
    @InjectMocks
    private LoginView view;

    @Before
    public void setUp() throws IOException {
        when(view.getExternalContext()).thenReturn(externalContext);
        when(view.getRequestContextPath()).thenReturn("foo");
        doNothing().when(externalContext).redirect(anyString());
    }

    @Test
    public void init_shouldAddMessage() {
        view.init();
        verify(view, times(1)).addMessage(eq(null), any(FacesMessage.class));
    }

    @Test
    public void login_givenValidUser_shouldPutUserInSession() throws Exception {
        view.setUsername("junit");
        final UsersEntity user = mock(UsersEntity.class);
        final Map<String, Object> sessionMap = new HashMap<>();
        when(loginProducer.executeUserLogin("junit")).thenReturn(user);
        doNothing().when(template).reloadMenu(user);
        when(view.getSessionMap()).thenReturn(sessionMap);
        view.login();
        verify(template, times(1)).reloadMenu(user);
        verify(externalContext, times(1)).redirect("foo/views/index.xhtml");
        assertEquals(user, sessionMap.get(SessionParams.USER_ATTRIBUTE));
    }

    @Test
    public void login_givenInvalidUser_shouldAddMessage() throws Exception {
        view.setUsername("junit");
        when(loginProducer.executeUserLogin("junit")).thenReturn(null);
        view.login();
        verify(view, times(1)).addMessage(eq(null), any(FacesMessage.class));
    }

    @Test
    public void logout_shouldInvalidateSession() throws IOException {
        doNothing().when(externalContext).invalidateSession();
        view.logout();
        verify(externalContext, times(1)).invalidateSession();
        verify(externalContext, times(1)).redirect("foo/views/index.xhtml");
    }
}