package com.axiante.mui.backing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.Dependent;

import org.apache.http.cookie.Cookie;

import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.common.interfaces.MuiConnectionListener;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import com.axiante.utility.configuration.Configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Dependent
@EqualsAndHashCode
public class MuiToken {
    /**
     * this is the unique id of the MuiToken
     */
    @Getter
    private String uuid;
    @Getter
    @Setter
    transient private String cellsetId;
    @Getter
    @Setter
    transient private String passport;
    @Getter
    @Setter
    transient private long timestamp;
    @Getter
    @Setter
    transient private Configuration configuration;
    @Setter
    transient private ConnectionProfile profile;
    @Getter
    @Setter
    transient Row<Cell> headers;
    @Getter
    @Setter
    transient int actualDataIndex;
    @Getter
    @Setter
    transient private int totalCells;
    @Getter
    @Setter
    transient private Cookie cookie;
    @Getter
    @Setter
    transient private List<MuiConnectionListener> listeners = new ArrayList<>();
    @Getter
    @Setter
    transient private String version;

    public static final String SERVER_BUSY = "SERVER_BUSY";
    public static final String CONNECTION_TOKEN = "CONNECTION_TOKEN";
    transient private static final Object mutex = new Object();

    protected MuiToken() {
    }

    public static MuiToken get() {
        synchronized (mutex) {
            final MuiToken tok = new MuiToken();
            tok.generate();
            return tok;
        }
    }

    public static MuiToken getBusy() {
        synchronized (mutex) {
            final MuiToken tok = new MuiToken();
            tok.uuid = SERVER_BUSY;
            tok.timestamp = System.currentTimeMillis();
            return tok;
        }
    }

    public static MuiToken getConnection() {
        synchronized (mutex) {
            final MuiToken tok = new MuiToken();
            tok.uuid = CONNECTION_TOKEN;
            tok.timestamp = System.currentTimeMillis();
            return tok;
        }
    }

    protected void generate() {
        final UUID uid = UUID.randomUUID();
        uuid = uid.toString();
        timestamp = System.currentTimeMillis();
    }

    public boolean matches(@NonNull final String token) {
        return token.equals(getUuid());
    }

    public boolean isInUse() {
        return (configuration != null) && (cellsetId != null);
    }

    public MuiToken thisInstance() {
        return this;
    }

    public void addConnectionClosingListener(final MuiConnectionListener listener) {
        listeners.add(listener);
    }

    public void removeConnectionClosingListener(final MuiConnectionListener listener) {
        listeners.remove(listener);
    }

    public void fireConnectionClosingEvent() {
        listeners.stream().forEach(l -> l.connectionClosing());
    }

    public void removeAllListeners() {
        listeners.clear();
    }

    public void setToken(final String token) {
    }

    public ConnectionProfile getProfile() {
        if (configuration != null) {
            return configuration.getProfile();
        }
        return profile;
    }

    public boolean hasSameOrigin(@NonNull final MuiToken token) {
        return
                (getPassport() != null) &&
                getPassport().equals(token.getPassport()) &&
                (getConfiguration() != null) &&
                (getConfiguration().getProfile() != null) &&
                (token.getConfiguration() != null) &&
                getConfiguration().getProfile().equals(token.getConfiguration().getProfile());
    }
}
