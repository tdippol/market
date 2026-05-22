package com.axiante.mui.backing;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;
import java.util.UUID;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.connection.AuthType;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mockserver.MockServerTest;
import com.axiante.mui.common.interfaces.MuiConnectionListener;
import com.axiante.utility.configuration.Configuration;

@RunWith(MockitoJUnitRunner.class)
public class MuiTokenTest {

	@Spy MuiToken muiToken;
	@Rule public ExpectedException ex = ExpectedException.none();
	@Test public void testInstance() {
		assertNotNull(muiToken);
	}

	@Test public void testGet() {
		MuiToken t = MuiToken.get();
		assertNotNull(t);
		assertThat(t.getUuid(), not(equalTo(MuiToken.CONNECTION_TOKEN)));
		assertThat(t.getUuid(), not(equalTo(MuiToken.SERVER_BUSY)));
		assertThat(t.getTimestamp(),is(org.hamcrest.Matchers.greaterThan(0L)));
	}

	@Test public void testBusy() {
		MuiToken busy = MuiToken.getBusy();
		assertNotNull(busy);
		assertThat(busy.getUuid(), equalTo(MuiToken.SERVER_BUSY));
	}

	@Test public void testGetConnection() {
		MuiToken busy = MuiToken.getConnection();
		assertNotNull(busy);
		assertThat(busy.getUuid(), equalTo(MuiToken.CONNECTION_TOKEN));
	}
	@Test public void testGenerate() {
		MuiToken test = new MuiToken();
		assertNull(test.getUuid());
		assertThat(test.getTimestamp(),equalTo(0L));
		test.generate();
		assertNotNull(test.getUuid());
		assertThat(test.getTimestamp(),not(equalTo(0L)));
	}

	@Test public void testMatches() {
		MuiToken t = MuiToken.get();
		assertFalse(t.matches(UUID.randomUUID().toString()));
	}
	@Test public void testMatchesThrowsException() {
		ex.expect(NullPointerException.class);
		muiToken.matches(null);
	}

	@Test public void testIsInUse() {
		MuiToken test = MuiToken.get();
		assertFalse(test.isInUse());
		test.setConfiguration(mock(Configuration.class));
		assertFalse(test.isInUse());
		test.setCellsetId("cellset id");
		assertTrue(test.isInUse());
		test.setConfiguration(null);
		assertFalse(test.isInUse());
	}

	@Test public void testThisInstance() {
		MuiToken token = MuiToken.get();
		MuiToken test = token.thisInstance();

		assertEquals(test, token);
		test = muiToken.thisInstance();
		assertNotEquals(test, token);
	}

	@Test public void testAddListener() {
		MuiToken muiToken = MuiToken.get();
		MuiConnectionListener listener = spy(MuiConnectionListener.class);
		assertNotNull(muiToken.getListeners());
		assertThat(muiToken.getListeners().size(),equalTo(0));
		muiToken.addConnectionClosingListener(listener);
		assertThat(muiToken.getListeners().size(),equalTo(1));
		assertEquals(muiToken.getListeners().get(0), listener);
	}

	@Test public void testRemoveListener() {
		MuiConnectionListener listener = spy(MuiConnectionListener.class);
		MuiConnectionListener notInList = spy(MuiConnectionListener.class);
		MuiToken muiToken = MuiToken.get();
		muiToken.addConnectionClosingListener(listener);
		int size = muiToken.getListeners().size();
		assertTrue(size>0);
		muiToken.removeConnectionClosingListener(notInList);
		assertThat(size, equalTo(muiToken.getListeners().size())); // not removed
		muiToken.removeConnectionClosingListener(listener);
		assertThat(size-1, equalTo(muiToken.getListeners().size())); // removed
	}
	@Test public void testRemoveAllListener() {
		int size = 100;
		final MuiToken muiToken = MuiToken.get();
		new Random().ints(size).forEach(i->{
			muiToken.addConnectionClosingListener(spy(MuiConnectionListener.class));
		});
		assertThat(muiToken.getListeners().size(),is(org.hamcrest.Matchers.greaterThan(0)));
		muiToken.removeAllListeners();
		assertEquals(muiToken.getListeners().size(), 0);
	}

	@Test public void testSetTokenDoesNothing() {
		String test = "some random token";
		assertThat(muiToken.getUuid(), not(equalTo(test)));
		MuiToken testToken = muiToken.thisInstance();
		muiToken.setToken(test);
		verify(muiToken).setToken(ArgumentMatchers.any(String.class));
		assertThat(muiToken, equalTo(testToken));
	}
	@Test public void testGetProfileReturnsDefaultProfile() {
		MuiToken test = MuiToken.get();
		ConnectionProfile mockedProfile = mock(ConnectionProfile.class);
		test.setProfile(mockedProfile);
		ConnectionProfile check = test.getProfile();
		assertEquals(check, mockedProfile);
	}

	@Test public void testGetProfileReturnsNull() {
		MuiToken test = MuiToken.get();
		ConnectionProfile check = test.getProfile();
		assertNull(check);
	}

	@Test public void testConfigurationProfileOverridesDefaultProfile() {
		ConnectionProfile defaultProfile = mock(ConnectionProfile.class);
		ConnectionProfile connectionProfile = mock(ConnectionProfile.class);
		Configuration mock = mock(Configuration.class);
		when(mock.getProfile()).thenReturn(connectionProfile);

		MuiToken test = MuiToken.get();
		assertNull(test.getProfile());
		test.setProfile(defaultProfile);
		assertNull(test.getConfiguration());
		assertEquals(defaultProfile, test.getProfile());
		test.setConfiguration(mock);
		assertNotNull(test.getConfiguration());
		assertThat(defaultProfile, not(equalTo(test.getProfile())));
		assertThat(connectionProfile, equalTo(test.getProfile()));
	}

	@Test public void testFireConnectionClosingEventRunsForEachListener() {
		Random random = new Random();
		int listeners = random.nextInt(10-1) + 1;
		final MuiToken muiToken = MuiToken.get();
		MuiConnectionListener listener = spy(MuiConnectionListener.class);
		random.ints(listeners).forEach(r->{
			muiToken.addConnectionClosingListener(listener);
		});
		muiToken.fireConnectionClosingEvent();
		verify(listener,times(listeners)).connectionClosing();
	}

	@Test(expected = NullPointerException.class)
	public void test_hasSameOriginThrowsExceptionWhenNullToken(){
		muiToken.hasSameOrigin(null);
	}
	@Test
	public void test_hasSameOriginReturnsFalseWhenPassportIsNull(){
		MuiToken another = Mockito.mock(MuiToken.class);
		when(muiToken.getPassport()).thenReturn(null);
		assertThat(muiToken.hasSameOrigin(another),CoreMatchers.equalTo(false));
	}
	@Test
	public void test_hasSameOriginReturnsFalseWhenDifferentPassports(){
		MuiToken another = Mockito.mock(MuiToken.class);
		final String passport = "passport";
		when(another.getPassport()).thenReturn(passport);
		when(muiToken.getPassport()).thenReturn(passport+"1");
		assertThat(muiToken.hasSameOrigin(another),CoreMatchers.equalTo(false));
	}
	@Test
	public void test_hasSameOriginReturnsFalseWhenNullConfiguration(){
		MuiToken another = Mockito.mock(MuiToken.class);
		final String passport = "passport";
		when(another.getPassport()).thenReturn(passport);
		when(muiToken.getPassport()).thenReturn(passport);
		when(muiToken.getConfiguration()).thenReturn(null);
		assertThat(muiToken.hasSameOrigin(another),CoreMatchers.equalTo(false));
	}
	@Test
	public void test_hasSameOriginReturnsFalseWhenDifferentConfiguration(){
		MuiToken another = Mockito.mock(MuiToken.class);
		final String passport = "passport";
		when(another.getPassport()).thenReturn(passport);
		when(muiToken.getPassport()).thenReturn(passport);
		Configuration configuration = mock(Configuration.class);
		when(muiToken.getConfiguration()).thenReturn(configuration);
		assertThat(muiToken.hasSameOrigin(another),CoreMatchers.equalTo(false));
	}
	@Test
	public void test_hasSameOriginReturnsFalseWhenNullProfile(){
		MuiToken another = Mockito.mock(MuiToken.class);
		final String passport = "passport";
		when(another.getPassport()).thenReturn(passport);
		when(muiToken.getPassport()).thenReturn(passport);
		Configuration configuration = mock(Configuration.class);
		when(configuration.getProfile()).thenReturn(null);
		when(muiToken.getConfiguration()).thenReturn(configuration);
		assertThat(muiToken.hasSameOrigin(another),CoreMatchers.equalTo(false));
	}
	@Test
	public void test_hasSameOriginReturnsFalseWhenNullTokenProfile(){
		MuiToken another = Mockito.mock(MuiToken.class);
		final String passport = "passport";
		when(another.getPassport()).thenReturn(passport);
		when(muiToken.getPassport()).thenReturn(passport);
		Configuration configuration = mock(Configuration.class);
		Configuration configuration2 = mock(Configuration.class);
		ConnectionProfile profile = mock(ConnectionProfile.class);
		when(configuration.getProfile()).thenReturn(profile);
		when(muiToken.getConfiguration()).thenReturn(configuration);
		when(another.getConfiguration()).thenReturn(configuration2);
		assertThat(muiToken.hasSameOrigin(another),CoreMatchers.equalTo(false));
	}
	@Test
	public void test_hasSameOriginReturnsTrueWhenAllMatch(){
		MuiToken another = Mockito.mock(MuiToken.class);
		when(another.getPassport()).thenReturn("passport");
		when(muiToken.getPassport()).thenReturn("passport");
		Configuration configuration = mock(Configuration.class);
		when(another.getConfiguration()).thenReturn(configuration);
		when(muiToken.getConfiguration()).thenReturn(configuration);
		ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.BASIC, false);
		when(configuration.getProfile()).thenReturn(profile);
		assertThat(muiToken.hasSameOrigin(another),CoreMatchers.equalTo(true));
	}
	//    (getPassport() != null) &&
	//    getPassport().equals(token.getPassport()) &&
	//    (getConfiguration() != null) &&
	//    (getConfiguration().getProfile() != null) &&
	//    (token.getConfiguration() != null) &&
	//    getConfiguration().getProfile().equals(token.getConfiguration().getProfile());

}

