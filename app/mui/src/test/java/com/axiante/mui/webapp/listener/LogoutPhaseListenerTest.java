package com.axiante.mui.webapp.listener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogoutPhaseListenerTest {

    @InjectMocks
    private LogoutPhaseListener underTest;

    @Mock
    private PhaseEvent mockEvent;
    @Mock
    private FacesContext mockFacesContext;
    @Mock
    private ExternalContext mockExternalContext;
    @Mock
    private HttpServletResponse mockResponse;
    @Mock
    private HttpSession mockSession;

    @Before
    public void setUp() {
        when(mockEvent.getFacesContext()).thenReturn(mockFacesContext);
        when(mockFacesContext.getExternalContext()).thenReturn(mockExternalContext);
    }

    @Test
    public void getPhaseId_shouldReturnRenderResponse() {
        assertThat(underTest.getPhaseId(), is(PhaseId.RENDER_RESPONSE));
    }

    @Test
    public void afterPhase_shouldDoNothing() {
        // This test just ensures no exception is thrown for the empty method.
        underTest.afterPhase(mockEvent);
    }

    @Test
    public void beforePhase_whenPathIsLogoutAndSessionExists_shouldInvalidateAndAddHeaders() {
        when(mockExternalContext.getRequestServletPath()).thenReturn("/views/logout.xhtml");
        when(mockExternalContext.getResponse()).thenReturn(mockResponse);
        when(mockExternalContext.getSession(false)).thenReturn(mockSession);
        underTest.beforePhase(mockEvent);
        verify(mockResponse).addHeader("Pragma", "no-cache");
        verify(mockResponse, times(1)).addHeader("Cache-Control", "no-cache");
        verify(mockResponse).addHeader("Cache-Control", "no-store");
        verify(mockResponse).addHeader("Cache-Control", "must-revalidate");
        verify(mockResponse).addHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");
        verify(mockSession).invalidate();
    }

    @Test
    public void beforePhase_whenPathIsLogoutAndSessionIsNull_shouldOnlyAddHeaders() {
        when(mockExternalContext.getRequestServletPath()).thenReturn("/views/logout.xhtml");
        when(mockExternalContext.getResponse()).thenReturn(mockResponse);
        when(mockExternalContext.getSession(false)).thenReturn(null);
        underTest.beforePhase(mockEvent);
        verify(mockResponse).addHeader("Pragma", "no-cache");
        verify(mockSession, never()).invalidate();
    }

    @Test
    public void beforePhase_whenPathIsNotLogout_shouldDoNothing() {
        when(mockExternalContext.getRequestServletPath()).thenReturn("/views/some-other-page.xhtml");
        underTest.beforePhase(mockEvent);
        verify(mockExternalContext, never()).getResponse();
        verify(mockExternalContext, never()).getSession(false);
        verify(mockSession, never()).invalidate();
    }
}