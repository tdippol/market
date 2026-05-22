package com.axiante.mui.backing;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

import com.axiante.Tm1Tunnel;
import com.axiante.mui.utils.ApplicationConfiguration;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * this is an application wide cache of token+cellset+timestap
 *
 * @author marco
 *
 */
@Slf4j
@Singleton
public class CellSetCache implements Serializable {
    private static final long serialVersionUID = 694400066882975142L;
    protected static final List<MuiToken> liveTokens = Collections.synchronizedList(new ArrayList<MuiToken>());
    @Getter
    private final AtomicBoolean busy = new AtomicBoolean(false);
    private static AtomicInteger busyTimes = new AtomicInteger(0);
    transient private static final Object semaphore = new Object();

    //	@Inject
    //	@Getter
    //	ApplicationProperties applicationProperties;

    @Inject
    ApplicationConfiguration applicationProperties;

    @PostConstruct
    public void init() {
        log.debug("starting token cache manager");
    }

    protected final Predicate<MuiToken> serverBusyToken = t -> {
        if (t != null) {
            return MuiToken.SERVER_BUSY.equals(t.getUuid());
        }
        return false;
    };

    protected final Predicate<MuiToken> connectionToken = t -> {
        if (t != null) {
            return MuiToken.CONNECTION_TOKEN.equals(t.getUuid());
        }
        return false;
    };

    public boolean closeForTimeout() {
        if (!getBusy().compareAndSet(false, true)) {
            log.debug("clean task busy!");
            if (busyTimes.incrementAndGet() == 5) {
                log.warn("it appears that the cleaning task has been busy for the past 5 minutes");
            } else if (busyTimes.get() == 10) {
                log.warn("THERE SEEMS TO BE A LOKED THREAD IN THE CLEANING TASK !");
            }
            return false;
        }
        synchronized (semaphore) {
            busyTimes.set(0);
            List<MuiToken> expired = null;
            Exception firstFailure = null;
            try {
                log.debug("checking for timedout mui tokens at " + new Date() + "; the list contains "
                        + liveTokens.size() + " element" + (liveTokens.size() == 1 ? "" : "s"));
                final int spawn = applicationProperties.getMuiTokenDuration() * 1000;
                final double connectionTimeout = applicationProperties.getConnectionTimeout();
                final Integer socketTimeout = applicationProperties.getSocketTimeout();
                final Integer connectionManagerTimeout = applicationProperties.getConnectionRequestTimeout();

                log.debug("survival time for tokens is " + (spawn / 1000) + " seconds");
                log.debug("connection timeout is " + connectionTimeout + " seconds");

                final long limit = System.currentTimeMillis();
                removeServerBusyTokens();
                expired = liveTokens.parallelStream().filter(Objects::nonNull).filter(connectionToken.negate())
                        .filter(token -> ((token.getTimestamp() + spawn) < limit)).collect(Collectors.toList());
                if (expired.size() > 0) {
                    log.debug("there are " + expired.size() + " expired elements in cache");
                    try (final Tm1Tunnel iface = new Tm1Tunnel(null);) {
                        final Iterator<MuiToken> iterator = expired.iterator();
                        while (iterator.hasNext()) {
                            final MuiToken token = iterator.next();
                            try {
                                log.debug("attempting to close cellset");
                                if (token.getUuid().equals(MuiToken.SERVER_BUSY)) {
                                    log.warn("found token for busy server: it shouldn't be here");
                                } else {
                                    log.debug("closing muitoken with uuid " + token.getUuid() + " and cookie "
                                            + token.getCookie());
                                    deleteCellSet(token, iface, connectionTimeout, socketTimeout,
                                            connectionManagerTimeout);
                                }
                                log.debug("removing token " + token.getUuid());
                                if (liveTokens.remove(token)) {
                                    log.debug("removed " + token.getUuid() + " from live tokens list");
                                } else {
                                    log.warn("unable to find token " + token.getUuid()
                                    + ": this could lead to a memory leak");
                                }
                                token.removeAllListeners();
                            } catch (URISyntaxException | IOException e) {
                                if (firstFailure == null) {
                                    log.error("Error parsing token " + token.getUuid(), e);
                                    firstFailure = e;
                                }
                            }
                        }
                    }
                } else {
                    log.debug("no expired elements found");
                    if (liveTokens.size() > 0) {
                        log.debug("there are " + liveTokens.size() + " possibilities in the future");
                    }
                }
                expired.clear();

                // now, look for session tokens
                expired = liveTokens.stream().filter(Objects::nonNull).filter(connectionToken)
                        .collect(Collectors.toList());
                List<MuiToken> removeable = null;
                final List<MuiToken> remove = new ArrayList<>();
                // if they have no reference with the opened cellsets, then close them
                for (final MuiToken token : expired) {
                    removeable = liveTokens.parallelStream().filter(Objects::nonNull)
                            .filter(t -> token.getCookie().equals(t.getCookie())).collect(Collectors.toList());
                    if (removeable.size() <= 1) {
                        // mark this token for session close
                        remove.add(token);
                    }
                }
                log.debug("there are " + remove.size() + " connection tokens to be removed");
                // remove all the muiId from the live tokens
                liveTokens.removeAll(remove);

                expired = liveTokens.stream().filter(Objects::nonNull).collect(Collectors.toList());
                liveTokens.clear();
                if ((expired != null) && (expired.size() > 0)) {
                    liveTokens.addAll(expired);
                }
                log.debug("leftovers for next time: " + expired.size());
            } catch (final Throwable t) {
                log.error("unexpected error trying to close cellsets", t);
                return false;
            } finally {
                if (expired != null) {
                    expired.clear();
                    expired = null;
                }
                setBusy(false);
            }
        }
        return true;
    }

    private void removeServerBusyTokens() {
        List<MuiToken> list = liveTokens.parallelStream().filter(serverBusyToken).collect(Collectors.toList());
        if ((list != null) && (list.size() > 0)) {
            log.debug("removing " + list.size() + " tokens registred after a \"server busy\" event");
            list.parallelStream().forEach(c -> c.removeAllListeners());
            liveTokens.removeAll(list);
            list.clear();
        }
        list = null;
    }

    public MuiToken register() {
        log.debug("registering token .... ");
        synchronized (semaphore) {
            final MuiToken tok = internalRegister();
            busy.set(true);
            liveTokens.add(tok);
            busy.set(false);
            log.debug("done");
            return tok;
        }
    }

    public MuiToken registerBusy() {
        log.debug("registering busy server token .... ");
        synchronized (semaphore) {
            final MuiToken tok = internalRegisterBusy();
            busy.set(true);
            liveTokens.add(tok);
            busy.set(false);
            log.debug("done");
            return tok;
        }
    }

    public MuiToken registerConnection() {
        log.debug("registering connection token .... ");
        synchronized (semaphore) {
            final MuiToken tok = internalRegisterConnection();
            busy.set(true);
            liveTokens.add(tok);
            busy.set(false);
            log.debug("done");
            return tok;
        }
    }

    protected MuiToken internalRegister() {
        return MuiToken.get();
    }

    protected MuiToken internalRegisterBusy() {
        return MuiToken.getBusy();
    }

    protected MuiToken internalRegisterConnection() {
        return MuiToken.getConnection();
    }

    public MuiToken get(final String token) {
        log.debug("looking for token " + token);
        synchronized (semaphore) {
            final MuiToken m = liveTokens.parallelStream().filter(tok -> {
                return tok.matches(token);
            }).findAny().orElse(null);
            log.debug("found " + (m == null ? "none" : " one "));
            return m;
        }
    }

    public void setBusy(final Boolean value) {
        busy.set(value);
    }

    private String deleteCellSet(@NonNull final MuiToken token, @NonNull final Tm1Tunnel iface,
            final Double connectionTimeout, final Integer socketTimeout, final Integer connectionManagerTimeout)
                    throws URISyntaxException, IOException {
        token.fireConnectionClosingEvent();
        if (token.getCellsetId() == null) {
            // it's an empty token
            return null;
        }
        iface.setProfile(token.getProfile());
        iface.setCookie(token.getCookie());
        final String string = iface.verify(token.getPassport(), token.getCookie(), token.getProfile(),
                connectionTimeout, socketTimeout, connectionManagerTimeout);
        log.debug("deleting cellset " + token.getCellsetId());
        iface.executeRequestAsHttpResponse("DELETE", "Cellsets('" + token.getCellsetId() + "')", null, connectionTimeout, token.getCookie());
        return string;

    }
}
