package com.axiante.mui.webapp.filter;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.ApplicationPropertiesService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.views.login.LoginProducer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;

    @Mock
    private HttpSession session;

    @Mock
    private UsersEntity user;

    @Mock
    private RolesEntity role;

    @Mock
    private ApplicationPropertiesService propertiesService;

    @Mock
    private ApplicationProperties appProperties;

    @Mock
    private MuiService muiService;

    @Mock
    private LoginProducer loginProducer;

    @Spy
    @InjectMocks
    private LoginFilter filter;

    @Before
    public void setUp() {
        final ServletContext context = mock(ServletContext.class);
        when(session.getAttribute(anyString())).thenReturn(user);
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("foo");
    }

    @Test
    public void doFilter_givenHealthPath_shouldChainFilter() throws Exception {
        when(request.getRequestURI()).thenReturn("foo/health/bar");
        when(request.getRequestURL()).thenReturn(new StringBuffer("foo/views/bar.xhtml"));
        when(muiService.findUser(anyInt())).thenReturn(user);
        filter.doFilter(request, response, chain);
        verify(chain, atLeastOnce()).doFilter(request, response);
    }

    @Test
    public void doFilter_givenWorkInProgressPath_whenThereIsSession_shouldInvalidateSession()
            throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("foo/views/work_in_progress.xhtml");
        when(request.getSession(false)).thenReturn(session);
        filter.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(request, response);
        verify(session, times(1)).invalidate();
    }

    @Test
    public void doFilter_givenWorkInProgressPath_whenThereIsNoSession_shouldNotCallInvalidateSession()
            throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("foo/views/work_in_progress.xhtml");
        when(request.getSession(false)).thenReturn(null);
        filter.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(request, response);
        verify(session, never()).invalidate();
    }

    @Test
    public void doFilter_givenSessionExpiredPath_shouldInvalidateSession() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("foo/views/sessionExpired.xhtml");
        filter.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(request, response);
        verify(session, times(1)).invalidate();
    }

    @Test
    public void doFilter_givenLogoutPath_shouldChainFilter() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("foo/views/logout.xhtml");
        filter.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(request, response);
    }

    @Test
    public void doFilter_whenLoggedInAndExceptionOccurs_shouldRedirectToWorkInProgress() throws Exception {
        when(request.getRequestURI()).thenReturn("foo/views/bar.xhtml");
        when(propertiesService.calculateAdminMode()).thenReturn(false);
        when(muiService.findUser(anyInt())).thenThrow(Exception.class);
        filter.doFilter(request, response, chain);
        verify(response, times(1)).sendRedirect("foo/views/work_in_progress.xhtml");
        verify(chain, never()).doFilter(request, response);
    }

    @Test
    public void doFilter_whenLoggedInNotAdmin_givenTrackerPath_shouldSendUnauthorizedResponse() throws Exception {
        when(request.getRequestURI()).thenReturn("foo/views/tracker");
        when(request.getRequestURL()).thenReturn(new StringBuffer("foo/views/tracker"));
        when(propertiesService.calculateAdminMode()).thenReturn(false);
        when(muiService.findUser(anyInt())).thenReturn(user);
        filter.doFilter(request, response, chain);
        verify(response, times(1)).sendError(eq(401), anyString());
        verify(chain, never()).doFilter(request, response);
    }

    @Test
    public void doFilter_whenLoggedInNotAdmin_givenAdminModeEnabled_shouldRedirectToWorkInProgressAsJsfResponse()
            throws Exception {
        final PrintWriter pw = mock(PrintWriter.class);
        when(request.getRequestURI()).thenReturn("foo/views/bar.xhtml");
        when(request.getRequestURL()).thenReturn(new StringBuffer("foo/views/bar.xhtml"));
        when(propertiesService.calculateAdminMode()).thenReturn(true);
        when(muiService.findUser(anyInt())).thenReturn(user);
        when(response.getWriter()).thenReturn(pw);
        when(pw.append(anyString())).thenReturn(pw);
        when(user.getRoles()).thenReturn(new HashSet<>(Collections.singletonList(role)));
        filter.doFilter(request, response, chain);
        verify(response, never()).sendRedirect("foo/views/work_in_progress.xhtml");
        verify(chain, never()).doFilter(request, response);
        verify(pw, times(1)).append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        verify(pw, times(1)).printf("<partial-response><redirect url=\"%s\"></redirect></partial-response>",
                "foo/views/work_in_progress.xhtml");
    }

    @Test
    public void doFilter_whenLoggedIn_givenAdminModeDisabled_shouldChainFilter() throws Exception {
        when(request.getRequestURI()).thenReturn("foo/views/bar.xhtml");
        when(request.getRequestURL()).thenReturn(new StringBuffer("foo/views/bar.xhtml"));
        when(propertiesService.calculateAdminMode()).thenReturn(false);
        when(muiService.findUser(anyInt())).thenReturn(user);
        filter.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(request, response);
    }

    @Test
    public void doFilter_whenNotLoggedInAndNotUsernameFromContext_shouldSendInternalServerErrorResponse()
            throws Exception {
        when(request.getRequestURI()).thenReturn("foo/views/bar.xhtml");
        when(session.getAttribute(anyString())).thenReturn(null);
        when(request.getRemoteUser()).thenReturn(null);
        filter.doFilter(request, response, chain);
        verify(response, times(1)).sendError(500, "Error retrieving user from browser.");
    }

    @Test
    public void doFilter_whenNotLoggedIn_givenFailedLogin_shuldSendInternalServerErroreResponse() throws Exception {
        when(request.getRequestURI()).thenReturn("foo/views/bar.xhtml");
        when(session.getAttribute(anyString())).thenReturn(null);
        when(request.getRemoteUser()).thenReturn("junit");
        when(loginProducer.executeUserLogin("junit")).thenReturn(null);
        filter.doFilter(request, response, chain);
        verify(response, times(1)).sendError(500, "Error retrieving user junit from database.");
    }

    @Test
    public void doFilter_whenNotLoggedIn_givenSuccessLogin_shouldRedirectToIndexPage() throws Exception {
        when(request.getRequestURI()).thenReturn("foo/views/bar.xhtml");
        when(session.getAttribute(anyString())).thenReturn(null);
        when(request.getRemoteUser()).thenReturn("junit");
        when(loginProducer.executeUserLogin("junit")).thenReturn(user);
        filter.doFilter(request, response, chain);
        verify(response, times(1)).sendRedirect("foo/views/index.xhtml");
        verify(session, times(1)).setAttribute("ax_user", user);
    }

    @Test
    public void doFilter_whenNotLoggedIn_givenSuccessLoginAndAdminModeEnabledAndUserNotAdmin_shouldRedirectToWorkInProgressPage()
            throws Exception {
        when(request.getRequestURI()).thenReturn("foo/views/bar.xhtml");
        when(session.getAttribute(anyString())).thenReturn(null);
        when(request.getRemoteUser()).thenReturn("junit");
        when(loginProducer.executeUserLogin("junit")).thenReturn(user);
        when(propertiesService.calculateAdminMode()).thenReturn(true);
        when(user.getRoles()).thenReturn(new HashSet<>(Collections.singletonList(role)));
        filter.doFilter(request, response, chain);
        verify(session, times(1)).setAttribute("ax_user", user);
    }

    @Test
    public void doFilter_whenNotLoggedIn_givenSuccessLoginAndAdminModeEnabledAndUserAsAdmin_shouldRedirectToIndexPage()
            throws Exception {
        when(request.getRequestURI()).thenReturn("foo/views/bar.xhtml");
        when(session.getAttribute(anyString())).thenReturn(null);
        when(request.getRemoteUser()).thenReturn("junit");
        when(loginProducer.executeUserLogin("junit")).thenReturn(user);
        when(propertiesService.calculateAdminMode()).thenReturn(true);
        HashSet<RolesEntity> roles = new HashSet<>(Collections.singletonList(role));
        when(user.getRoles()).thenReturn(roles);
        when(propertiesService.hasAccessAsAdmin(roles)).thenReturn(true);
        filter.doFilter(request, response, chain);
        verify(response, times(1)).sendRedirect("foo/views/index.xhtml");
        verify(session, times(1)).setAttribute("ax_user", user);
    }

    @Test
    public void doFilter_whenNotLoggedIn_givenSuccessLoginAndCamLoginAndAuthRequest_shouldRedirectToIndexPage()
            throws Exception {
        when(request.getRequestURI()).thenReturn("foo/views/bar.xhtml");
        when(session.getAttribute(anyString())).thenReturn(null);
        when(request.getRemoteUser()).thenReturn("junit");
        when(loginProducer.executeUserLogin("junit")).thenReturn(user);
        when(user.getCamLogin()).thenReturn(true);
        when(request.getParameter("c_pp")).thenReturn("ok");
        filter.doFilter(request, response, chain);
        verify(response, times(1)).sendRedirect("foo/views/index.xhtml");
        verify(session, times(1)).setAttribute("ax_user", user);
        verify(session, times(1)).setAttribute("CAMPassPort", "ok");
    }

    @Test
    public void doFilter_whenNotLoggedIn_givenSuccessLoginAndFailedCamLogin_shouldSendUnauthorizedResponse()
            throws Exception {
        when(request.getRequestURI()).thenReturn("foo/views/bar.xhtml");
        when(session.getAttribute(anyString())).thenReturn(null);
        when(request.getRemoteUser()).thenReturn("junit");
        when(loginProducer.executeUserLogin("junit")).thenReturn(user);
        when(user.getCamLogin()).thenReturn(true);
        when(request.getParameter("c_pp")).thenReturn(null);
        when(session.getAttribute("CAMPassPort")).thenReturn("bar");
        filter.doFilter(request, response, chain);
        verify(response, times(1)).sendError(401);
    }

    @Test
    public void doFilter_whenNotLoggedIn_givenSuccessLoginAndCamLoginAndNotAuthRequestAndNotAuthServer_shouldSendServiceUnavailableResponse()
            throws Exception {
        when(request.getRequestURI()).thenReturn("foo/views/bar.xhtml");
        when(session.getAttribute(anyString())).thenReturn(null);
        when(request.getRemoteUser()).thenReturn("junit");
        when(loginProducer.executeUserLogin("junit")).thenReturn(user);
        when(user.getCamLogin()).thenReturn(true);
        when(request.getParameter("c_pp")).thenReturn(null);
        when(session.getAttribute("CAMPassPort")).thenReturn(null);
        when(appProperties.getProperty("AUTH_SERVER")).thenReturn(null);
        filter.doFilter(request, response, chain);
        verify(response, times(1)).sendError(503, "Error in the configuration of BI Server");
    }

    @Test
    public void doFilter_whenNotLoggedIn_givenSuccessLoginAndCamLoginAndNotAuthRequestAndValidAuthServer_shouldRedirectToAuthServer()
            throws Exception {
        when(request.getRequestURI()).thenReturn("foo/views/bar.xhtml");
        when(session.getAttribute(anyString())).thenReturn(null);
        when(request.getRemoteUser()).thenReturn("junit");
        when(loginProducer.executeUserLogin("junit")).thenReturn(user);
        when(user.getCamLogin()).thenReturn(true);
        when(request.getParameter("c_pp")).thenReturn(null);
        when(session.getAttribute("CAMPassPort")).thenReturn(null);
        when(appProperties.getProperty("AUTH_SERVER")).thenReturn("authServer");
        when(loginProducer.getTm1AuthString(request, "authServer")).thenReturn("authServerUrl");
        filter.doFilter(request, response, chain);
        verify(response, times(1)).sendRedirect("authServerUrl");
    }
}