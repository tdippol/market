package com.axiante.mui.backing;

import com.axiante.Tm1Tunnel;
import com.axiante.connection.AuthType;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.utils.ApplicationConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.util.EntityUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@SessionScoped
@Slf4j
public class CookieRepository implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 804898655814639049L;
    private static final String DUMMYCAM = "DUMMY CAM FOR USER THAT SHOULDN'T USE IT";

    @Inject
    @Getter(value = AccessLevel.PROTECTED)
    HttpSession session;

    @Inject
    @Getter(value = AccessLevel.PROTECTED)
    ApplicationConfiguration properties;
    @Getter(value = AccessLevel.PROTECTED)
    final Map<ConnectionProfile, Cookie> cookieMap = new HashMap<>();

    private transient final Object mutex = new Object();

    @Setter
    @Getter(value = AccessLevel.PROTECTED)
    private String CAMPassport;

    private boolean initialized = false;

    @Inject
    KeepAliveScheduler scheduler;

    @PostConstruct
    public void healthCheck() {
        if (getProperties() == null) {
            log.error("properties not injected");
        }
        if (session == null) {
            log.error("Error injecting http session instance");
        }
    }

    public void keepAlive() {
        synchronized (mutex) {
            keepAliveCheck();
        }
    }


    protected boolean keepAliveCheck() {
        boolean result = true;
        try {
            if (session != null && log.isDebugEnabled()) {
                if (session.getAttribute(UsersEntity.USER_ATTRIBUTE) != null) {
                    log.debug("keep alive for user "
                            + getSession().getAttribute(UsersEntity.USER_ATTRIBUTE));
                }
                log.debug("session has been injected and has id " + getSession().getId());
            } else {
                log.warn("http session not injected properly");
            }
            getCookieMap().entrySet().forEach(entry -> {
                CookieRepository.this.test(entry.getKey());
            });
        } catch (IllegalStateException e) {
            log.warn(e.getMessage(), e);
            result = false;
        } catch (final Exception e) {
            log.error("unexpected error while keep-alive runs", e);
            result = false;
        }
        return result;
    }


    public synchronized Cookie getCookie(final ConnectionProfile p) {
        if (p == null) {
            log.error("missing connection profile");
            return null;
        }
        Cookie c = getCookieMap().get(p);
        if (c == null) {
            c = openConnection(p);
            getCookieMap().put(p, c);
        } else {
            test(p);
            c = getCookieMap().get(p);
        }
        return c;
    }

    protected boolean test(final ConnectionProfile profile) {
        boolean ret = false;
        try (Tm1Tunnel iface = getTunnel(profile)) {
            final HttpUtils utils = new HttpUtils();
            final Cookie cookie = getCookieMap().get(profile);
            iface.setCookie(cookie);
            final CloseableHttpResponse response = iface.getVersion(getProperties().getConnectionTimeout(),
                    getProperties().getSocketTimeout(), getProperties().getConnectionRequestTimeout(), cookie);
            final boolean needUpdate = !utils.checkResponse(response);
            EntityUtils.consumeQuietly(response.getEntity());
            response.close();
            if (needUpdate) {
                getCookieMap().put(profile, openConnection(profile));
            }
            ret = true;
        } catch (final Exception e) {
            if ( log.isDebugEnabled()){
                log.error("Error verifying connection cookie", e);
            } else {
                if ( e.getCause() != null && e.getCause().getMessage() != null){
                    log.error("Error verifying connection cookie {}", e.getCause().getMessage());
                } else {
                    log.error("Error verifying connection cookie {}", e.getMessage());
                }
            }

        }
        return ret;
    }

    @PreDestroy
    public void logout() {
        final String errorClosingSession="error closing session: %s";
        final String errorDestroyingCookies = "error while destroying cookies: %s";
        final String errorInvalidatingSession = "error invalidating session: %s";
        try {
            getCookieMap().entrySet().forEach(e -> {
                try (Tm1Tunnel iface = getTunnel(e.getKey());) {
                    iface.getCookieStore().addCookie(e.getValue());
                    iface.getVersion(getProperties().getConnectionTimeout(), getProperties().getSocketTimeout(),
                            getProperties().getConnectionRequestTimeout(), e.getValue());
                } catch (final IOException e1) {
                    String error = String.format(errorClosingSession,e1.getMessage());
                    if ( log.isDebugEnabled()) {
                        log.error(error, e1);
                    } else {
                        log.error(error);
                    }
                }
            });
        } catch (Exception e) {
            String error = String.format(errorDestroyingCookies,e.getMessage());
            if ( log.isDebugEnabled()){
                log.error(error,e);
            } else {
                log.error(error);
            }
        }
        if (scheduler != null) {
            scheduler.removeRepository(this);
        }
        final HttpSession session = getSession();
        if (session != null) {
            try {
                session.invalidate();
            } catch (Exception e) {
                String error = String.format(errorInvalidatingSession, e.getMessage());
                if (log.isDebugEnabled()) {
                    log.error(error, e);
                } else {
                    if ( e.getCause() != null && e.getCause().getMessage() != null) {
                        log.error(String.format(errorInvalidatingSession, e.getCause().getMessage()));
                    } else {
                        log.error(error);
                    }
                }
            }
        }
    }

    // apro una sessione per un utente e un profilo
    private Cookie openConnection(final ConnectionProfile profile) {

        synchronized (mutex) {
            log.debug(String.format("opening connection for profile %s" , profile.getName()));
            try (final Tm1Tunnel iface = new Tm1Tunnel(profile);) {
                try {
                    if (profile.getAuthType().equals(AuthType.BASIC)) {
                        return iface.openConnection(
                                getProperties().getConnectionTimeout(), getProperties().getSocketTimeout(), getProperties().getConnectionRequestTimeout(),
                                Base64.getEncoder().encodeToString(new StringBuffer(profile.getUsername()).append(":")
                                        .append(profile.getPassword()).toString().getBytes()));
                    } else if (profile.getAuthType().equals(AuthType.NAMESPACE)) {
                        return iface.openConnection(
                                getProperties().getConnectionTimeout(), getProperties().getSocketTimeout(), getProperties().getConnectionRequestTimeout(),
                                Base64.getEncoder()
                                        .encodeToString(new StringBuffer(profile.getUsername()).append(":")
                                                .append(profile.getPassword()).append(":").append(profile.getDomain())
                                                .toString().getBytes()));

                    } else {
                        if (getCAMPassport() == null) {
                            return iface.openConnection(
                                    getProperties().getConnectionTimeout(), getProperties().getSocketTimeout(), getProperties().getConnectionRequestTimeout(),
                                    DUMMYCAM);
                        } else {
                            return iface.openConnection(
                                    getProperties().getConnectionTimeout(), getProperties().getSocketTimeout(), getProperties().getConnectionRequestTimeout(),
                                    getCAMPassport());
                        }
                    }
                } catch (final IOException e) {
                    if (log.isDebugEnabled()) {
                        log.error("Error retrieving session cookie", e);
                    } else {
                        log.error("Error retrieving session cookie", e.getMessage());
                    }
                } catch (final Exception e) {
                    if (log.isDebugEnabled()) {
                        log.error("Unforeseen exception ", e);
                    } else {
                        log.error("Unforeseen exception ", e.getMessage());
                    }
                }
            }
            return null;
        }
    }

    // this is a helper method needed for testability of the class;
    protected Tm1Tunnel getTunnel(final ConnectionProfile profile) {
        return new Tm1Tunnel(profile);
    }

    public void initialize(String CAMPassport) {
        log.debug("initializing cookie repository");
        this.setCAMPassport(CAMPassport);
        if (!initialized) {
            scheduler.addRepository(this);
//			keepAlive();
            initialized = true;
        }
    }
}
