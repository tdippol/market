package com.axiante.mui.backing;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.connection.AuthType;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mockserver.MockServerTest;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.utility.configuration.Configuration;

@RunWith(MockitoJUnitRunner.class)
public class CellSetCacheTest {
    @Mock
    ApplicationConfiguration applicationProperties;
    @Mock
    MuiToken muiToken;
    @Mock
    MuiToken muiTokenBusy;
    @Mock
    MuiToken muiTokenConnection;
    ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, true);
    Calendar calendar = GregorianCalendar.getInstance();
    @Mock
    Configuration mockedConfiguration;
    @Spy
    @InjectMocks
    CellSetCache cellSetCache;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        when(applicationProperties.getMuiTokenDuration()).thenReturn(5);
        when(applicationProperties.getConnectionTimeout()).thenReturn(5.0);
    }

    @BeforeClass
    public static void startServer() {
        if ( !MockServerTest.isRunning() ) {
            MockServerTest.startServer();
        }
        assertTrue(MockServerTest.isRunning());
    }

    @AfterClass
    public static void stopServer() {
        MockServerTest.stopServer();
        assertFalse(MockServerTest.isRunning());
    }

    @Test
    public void init() {
        cellSetCache.init();
    }

    @Test
    public void testServerBusyTokenPredicate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 1, 1);

        when(muiToken.getUuid()).thenReturn(UUID.randomUUID().toString());
        when(muiTokenBusy.getUuid()).thenReturn(MuiToken.SERVER_BUSY);
        when(muiTokenConnection.getUuid()).thenReturn(MuiToken.CONNECTION_TOKEN);

        assertTrue(cellSetCache.serverBusyToken.test(muiTokenBusy));
        assertFalse(cellSetCache.serverBusyToken.test(muiTokenConnection));
        assertFalse(cellSetCache.serverBusyToken.test(muiToken));
        assertFalse(cellSetCache.serverBusyToken.test(null));
    }

    @Test
    public void testConnectionTokenPredicate() {
        when(muiToken.getUuid()).thenReturn(UUID.randomUUID().toString());
        when(muiTokenBusy.getUuid()).thenReturn(MuiToken.SERVER_BUSY);
        when(muiTokenConnection.getUuid()).thenReturn(MuiToken.CONNECTION_TOKEN);

        assertTrue(cellSetCache.connectionToken.test(muiTokenConnection));
        assertFalse(cellSetCache.connectionToken.test(muiTokenBusy));
        assertFalse(cellSetCache.connectionToken.test(muiToken));
        assertFalse(cellSetCache.connectionToken.test(null));
    }

    @Test
    public void testCloseForTimeoutWhenBusyReturnsFalse() throws Exception {
        cellSetCache.getBusy().set(true);
        assertFalse(cellSetCache.closeForTimeout());

        // now increment busytimes
        for (int i = 1; i < 5; ++i) {
            cellSetCache.closeForTimeout();
        }
        // coverage test alert
        assertFalse(cellSetCache.closeForTimeout());
        // now increment busytimes
        for (int i = 1; i <= 5; ++i) {
            cellSetCache.closeForTimeout();
        }
        // coverage test alert lock
        assertFalse(cellSetCache.closeForTimeout());

        cellSetCache.getBusy().set(false);
    }

    @Test
    public void testCloseForTimeout() throws Exception {

        cellSetCache.getBusy().set(false);
        CellSetCache.liveTokens.clear();

        calendar.set(2017, 1, 1);

        final Cookie cookie = new BasicClientCookie("mock", "mocked");
        when(muiToken.getTimestamp()).thenReturn(calendar.getTimeInMillis());
        when(muiToken.getCookie()).thenReturn(cookie);
        when(muiToken.getUuid()).thenReturn(UUID.randomUUID().toString());
        when(muiToken.getProfile()).thenReturn(mockedProfile);
        when(muiToken.getCellsetId()).thenReturn("1235478");
        when(muiToken.getPassport()).thenReturn("mocked passport");

        when(cellSetCache.internalRegister()).thenReturn(muiToken);
        when(cellSetCache.internalRegisterBusy()).thenReturn(muiTokenBusy);
        when(cellSetCache.internalRegisterConnection()).thenReturn(muiTokenConnection);

        when(muiTokenBusy.getUuid()).thenReturn(MuiToken.SERVER_BUSY);
        when(muiTokenConnection.getUuid()).thenReturn(MuiToken.CONNECTION_TOKEN);

        when(muiTokenConnection.getCookie()).thenReturn(cookie);

        when(applicationProperties.getMuiTokenDuration()).thenReturn(1);

        // put some mocked data
        cellSetCache.register();
        cellSetCache.register();
        cellSetCache.registerBusy();
        cellSetCache.registerConnection();
        assertTrue(cellSetCache.closeForTimeout());

    }

    @Test
    public void testRegister() {
        final Cookie cookie = new BasicClientCookie("mock", "mocked");
        calendar.set(2017, 1, 2);
        when(muiToken.getCookie()).thenReturn(cookie);
        when(muiToken.getUuid()).thenReturn(UUID.randomUUID().toString());
        when(cellSetCache.internalRegister()).thenReturn(muiToken);

        final MuiToken test = cellSetCache.register();
        verify(cellSetCache, times(1)).internalRegister();
        assertThat(test.getUuid(), equalTo(muiToken.getUuid()));
        assertThat(test.getCookie(), equalTo(muiToken.getCookie()));
    }

    @Test
    public void testRegisterBusy() {
        calendar.set(2017, 1, 3);
        when(muiTokenBusy.getUuid()).thenReturn(MuiToken.SERVER_BUSY);
        when(cellSetCache.internalRegisterBusy()).thenReturn(muiTokenBusy);

        final MuiToken test = cellSetCache.registerBusy();
        verify(cellSetCache, times(1)).internalRegisterBusy();
        assertThat(test.getUuid(), equalTo(muiTokenBusy.getUuid()));
        assertThat(test.getCookie(), equalTo(muiTokenBusy.getCookie()));
    }

    @Test
    public void testGet() {
        calendar.set(2017, 1, 2);
        when(muiToken.getUuid()).thenReturn(UUID.randomUUID().toString());
        when(muiToken.matches(ArgumentMatchers.anyString())).thenCallRealMethod();
        when(cellSetCache.internalRegister()).thenReturn(muiToken);

        final MuiToken expected = cellSetCache.register();
        final MuiToken test = cellSetCache.get(expected.getUuid());
        assertThat(expected, equalTo(test));
    }


    @Test
    public void testGetBusy() {
        when(cellSetCache.getBusy()).thenReturn(new AtomicBoolean(true));
        assertNotNull(cellSetCache.getBusy());
    }

}