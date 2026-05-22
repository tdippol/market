package com.axiante.mui.webapp.filter;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.ApplicationPropertiesService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.views.login.LoginProducer;
import com.axiante.tm1.TunnelConstants;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@WebFilter(urlPatterns = {"/*"})
@Slf4j
public class LoginFilter implements Filter {
    static final String workInProgressPath = "/views/work_in_progress.xhtml";
    static final String sessionExpiredPath = "/views/sessionExpired.xhtml";
    static final String logoutPath = "/views/logout.xhtml";
    static final String indexPath = "/views/index.xhtml";
    static final String healthPath = "/health/status";
    @Inject
    transient LoginProducer loginProducer;

    @Inject
    transient ApplicationProperties applicationProperties;

    @Inject
    ApplicationPropertiesService applicationPropertiesService;

    @Inject
    MuiService muiService;

    private boolean isLoggedIn(final HttpServletRequest req) {
        final Object o = req.getSession().getAttribute(UsersEntity.USER_ATTRIBUTE);
        return o instanceof UsersEntity;
    }

    private UsersEntity getSessionUser(final HttpServletRequest req) {
        final Object o = req.getSession().getAttribute(UsersEntity.USER_ATTRIBUTE);
        if (o instanceof UsersEntity) {
            return (UsersEntity) o;
        }
        return null;
    }

    private boolean isWorkInProgressRequest(final ServletRequest request) {
        return ((HttpServletRequest) request).getRequestURI()
                .equals(request.getServletContext().getContextPath() + workInProgressPath);
    }

    private boolean isSessionExpiredRequest(final ServletRequest request) {
        final String testUri = ((HttpServletRequest) request).getRequestURI();
        return testUri.equals(request.getServletContext().getContextPath() + sessionExpiredPath);
    }

    private boolean isLogoutRequest(final ServletRequest request) {
        final String testUri = ((HttpServletRequest) request).getRequestURI();
        return testUri.equals(request.getServletContext().getContextPath() + logoutPath);
    }

    private void redirectTo(final ServletRequest request, final ServletResponse response, final String page,
                            final UsersEntity user, String cam) throws IOException {
        redirectTo(request, response, page, user, cam, false);
    }

    private boolean isHealthRequest(final ServletRequest request) {
        final String testUri = ((HttpServletRequest) request).getRequestURI();
        return testUri.startsWith(request.getServletContext().getContextPath() + healthPath);
    }

    private void redirectTo(final ServletRequest request, final ServletResponse response, final String page,
                            final UsersEntity user, String cam, boolean invalidate) throws IOException {
        final String landing = request.getServletContext().getContextPath() + page;

        if (invalidate) {
            response.setContentType("text/xml");
            response.getWriter().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                    .printf("<partial-response><redirect url=\"%s\"></redirect></partial-response>", landing);
        } else {
            ((HttpServletRequest) request).getSession().setAttribute(UsersEntity.USER_ATTRIBUTE, user);
            ((HttpServletRequest) request).getSession().setAttribute(TunnelConstants.CAMPASSPORT, cam);
            ((HttpServletResponse) response).sendRedirect(landing);
        }

        return;

    }

    private boolean isTrakerRequest(final ServletRequest request) {
        return ((HttpServletRequest) request).getRequestURL().toString().contains("tracker");
    }

    private String getUsernameFromContext(final ServletRequest request) {
        return ((HttpServletRequest) request).getRemoteUser();
    }

    private boolean isAuthenticationRequest(final ServletRequest request) {
        return (request.getParameter("c_pp") != null)
                || (((HttpServletRequest) request).getSession().getAttribute(TunnelConstants.CAMPASSPORT) != null);
    }

    private boolean extractAuthentication(final HttpServletRequest request, final UsersEntity user) {
        final String cam = request.getParameter("c_pp");
        if (cam != null) {
            request.getSession().setAttribute(TunnelConstants.CAMPASSPORT, cam);
            request.getSession().setAttribute(UsersEntity.USER_ATTRIBUTE, user);
            log.debug(String.format("user %s has been authenticated externally with %s", user.getName(), cam));
            return true;
        }
        return false;
    }

    private void doChainFiler(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        if (chain != null) {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        boolean printMessage = true;
        if (isHealthRequest(request)) {
            log.debug("redirect to health page");
            doChainFiler(request, response, chain);
            printMessage = false;
        }
        if (isWorkInProgressRequest(request)) {
            log.debug("redirect to work in progress page");
            doChainFiler(request, response, chain);
            invalidateSession(request);
            printMessage = false;
        } else if (isSessionExpiredRequest(request)) {
            ((HttpServletRequest) request).getSession().invalidate();
            log.debug("redirect to sessionExpired page");
            doChainFiler(request, response, chain);
            invalidateSession(request);
            printMessage = false;
        } else if (isLogoutRequest(request)) {
            log.debug("redirect to sessionExpired page");
            doChainFiler(request, response, chain);
            invalidateSession(request);
            printMessage = false;
        } else {
            if (printMessage) {
                printRequest((HttpServletRequest) request);
            }
            final HttpServletRequest httpRequest = (HttpServletRequest) request;
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            UsersEntity user = null;
            final boolean adminMode = applicationPropertiesService.calculateAdminMode();
            if (isLoggedIn(httpRequest)) {
                // average joe is already logged in
                user = getSessionUser(httpRequest);
                if (user == null) {
                    log.error("Unable to get user from session");
                    redirectTo(request, response, workInProgressPath, null, null);
                    return;
                }
                try {
                    user = muiService.findUser(user.getId());
                } catch (Exception ex) {
                    log.error("Unable to refresh user from DB", ex);
                    redirectTo(request, response, workInProgressPath, user, null);
                    return;
                }
                if (isTrakerRequest(request) && !user.isAdmin()) {
                    ((HttpServletResponse) response).sendError(401, "Insufficient privileges.");
                } else {
                    if (adminMode && !applicationPropertiesService.hasAccessAsAdmin(user.getRoles())) {
                        redirectTo(request, response, workInProgressPath, user, null, true);
                        return;
                    }

                    log.debug("user is ok, chaining other filters");
                    // fine, go on
                    doChainFiler(request, response, chain);
                }
            } else {
                // get username from WLS
                final String username = getUsernameFromContext(request);
                if (username == null) {
                    log.debug("Error retrieving user from browser.");
                    httpResponse.sendError(500, "Error retrieving user from browser.");
                } else {
                    // we have the username, now let's get the user and move on
                    user = this.loginProducer.executeUserLogin(username);
                    if (user == null) {
                        log.debug("Error retrieving user from database.");
                        httpResponse.sendError(500, "Error retrieving user " + username + " from database.");
                    } else // now, if we're in admin mode and we're not admins ...
                        if (adminMode && !applicationPropertiesService.hasAccessAsAdmin(user.getRoles())) {
                            // user needs to get to the work in progress page
                            redirectTo(request, response, workInProgressPath, user, null);
                            return;
                        } else {
                            // here, we can be admins or regular joe without admin mode... we need to verify
                            // if the user needs to do a camlogin
                            if (user.getCamLogin()) {
                                // the user is configured to authenticate against a CAM
                                if (!isAuthenticationRequest(request)) {
                                    // this request is not an auth request, we send the user to the CAM
                                    final String biServer = this.applicationProperties
                                            .getProperty(ApplicationProperties.AUTH_SERVER);
                                    if (biServer == null) {
                                        log.error("Error in the configuration of BI Server");
                                        httpResponse.sendError(503, "Error in the configuration of BI Server");
                                    }
                                    log.info("Utente " + user.getName() + " inviato a SSO su " + biServer);
                                    httpResponse.sendRedirect(this.loginProducer.getTm1AuthString(httpRequest, biServer));
                                    return; // we're out of the filter here
                                }
                                if (extractAuthentication(httpRequest, user)) {
                                    log.info("Utente " + user.getName() + " verificato da SSO");
                                    // everything went fine and we can let joe play with the application
                                    httpResponse.sendRedirect(
                                            request.getServletContext().getContextPath() + "/views/index.xhtml");
                                    return;
                                }
                                // there's a problem with joe
                                log.warn("BI returned null CAM passport");
                                httpResponse.sendError(401);
                                return;
                            }
                            // joe is a user, authenticated by the server and present in the database, let's
                            // move on
                            log.debug("user can enter without sso");
                            redirectTo(request, response, indexPath, user, null);
                        }
                }
            }
        }
    }

    private void printRequest(HttpServletRequest request) {
        final Object o = request.getSession().getAttribute(UsersEntity.USER_ATTRIBUTE);
        if (o != null) {
            log.debug(new StringBuffer().append("User ").append(((UsersEntity) o).getName())
                    .append(" is asking permission to go to :" + request.getRequestURI()).toString());
        } else {
            log.warn(new StringBuffer().append("Anonymous ")
                    .append(" is asking permission to go to :" + request.getRequestURI()).toString());
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("initializing login filter");
    }

    @Override
    public void destroy() {
        log.debug("destroying login filter");
    }

    private void invalidateSession(ServletRequest request) {
        final HttpSession session = ((HttpServletRequest) request).getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

}
