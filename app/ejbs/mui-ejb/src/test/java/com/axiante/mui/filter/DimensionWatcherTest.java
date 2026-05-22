package com.axiante.mui.filter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.http.cookie.Cookie;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.utils.ApplicationConfiguration;

@RunWith(MockitoJUnitRunner.class)
public class DimensionWatcherTest {


	@Mock ApplicationConfiguration applicationConfiguration;

	@Spy
	@InjectMocks
	DimensionWatcher w ;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testParseJsonCubeHandlesExceptionAndReturnsNull() {
		assertNull(w.parseJsonCube(new StringBuffer(), "test"));
	}

	@Test
	public void testParseJsonCubeReturnsData() {
		final String json = "{'Cells': [{'Value': 'data update','FormattedValue': 'schema update'}]}".replace("\'", "\"");
		final DimensionWatch expected = new DimensionWatch("test", "schema update", "data update");
		assertThat(w.parseJsonCube(new StringBuffer(json), "test"), equalTo(expected));
	}
	@Test
	public void testParseWrongJsonCubeReturnsEmptyValuesForWatcher() {
		final String json = "{'Cells': [{'nothing': 'data update','correct': 'schema update'}]}".replace("\'", "\"");
		final DimensionWatch expected = w.parseJsonCube(new StringBuffer(json),"test");

		assertNull(expected.getWatch().getLastDataUpdate());
		assertNull(expected.getWatch().getLastSchemaUpdate());
		assertThat(expected.getDimension(), equalTo("test"));
	}


	@Test
	public void testParseJsonDimensionHandlesExceptionAndReturnsNull() {
		assertNull(w.parseJsonDimension(new StringBuffer(), "test"));
	}

	@Test
	public void testParseJsonDimensionReturnsData() {
		final String json = "{'LastDataUpdate': 'data update','LastSchemaUpdate': 'schema update'}".replace("\'", "\"");
		final DimensionWatch expected = new DimensionWatch("test", "schema update", "data update");
		assertThat(w.parseJsonDimension(new StringBuffer(json), "test"), equalTo(expected));
	}
	@Test
	public void testParseWrongJsonDimensionReturnsEmptyValuesForWatcher() {
		final String json = "{'Cells': [{'nothing': 'data update','correct': 'schema update'}]}".replace("\'", "\"");
		final DimensionWatch expected = w.parseJsonDimension(new StringBuffer(json),"test");

		assertNull(expected.getWatch().getLastDataUpdate());
		assertNull(expected.getWatch().getLastSchemaUpdate());
		assertThat(expected.getDimension(), equalTo("test"));
	}

	@Test public void testCheckReturnsFalseWhenNullWatch() {
		Mockito.when(
				w.query(
						ArgumentMatchers.anyString(),
						ArgumentMatchers.any(Cookie.class),
						ArgumentMatchers.any(ConnectionProfile.class)
						)
				).thenReturn(null);
		Cookie cookie = Mockito.mock(Cookie.class);
		assertFalse(w.check("something", cookie, new ConnectionProfile()));
	}
	@Test public void testCheckReturnsFalseWhenWatchAndNotPrevious() {
		DimensionWatch watch = Mockito.mock(DimensionWatch.class);
		Mockito.when(
				w.query(
						ArgumentMatchers.anyString(),
						ArgumentMatchers.any(Cookie.class),
						ArgumentMatchers.any(ConnectionProfile.class)
						)
				).thenReturn(watch);
		Cookie cookie = Mockito.mock(Cookie.class);
		assertFalse(w.check("something", cookie, new ConnectionProfile()));
	}
	@Test public void testCheckAddsWatchWhenWatchAndNotPrevious() {
		final String expected = "mocked dimension";
		DimensionWatch watch = Mockito.mock(DimensionWatch.class);
		Mockito.when(watch.getDimension()).thenReturn(expected);
		Mockito.when(
				w.query(
						ArgumentMatchers.anyString(),
						ArgumentMatchers.any(Cookie.class),
						ArgumentMatchers.any(ConnectionProfile.class)
						)
				).thenReturn(watch);
		Cookie cookie = Mockito.mock(Cookie.class);
		assertFalse(w.check("something", cookie, new ConnectionProfile()));
		assertNotNull(w.getWatches().get(expected));
		assertThat(w.getWatches().get(expected), CoreMatchers.equalTo(watch));
	}
	@Test public void testCheckReturnsTrueWhenWatchFound() {
		DimensionWatch watch = Mockito.mock(DimensionWatch.class);
		Mockito.when(watch.getDimension()).thenReturn("mocked dimension");
		Mockito.when(
				w.query(
						ArgumentMatchers.anyString(),
						ArgumentMatchers.any(Cookie.class),
						ArgumentMatchers.any(ConnectionProfile.class)
						)
				).thenReturn(watch);
		w.getWatches().put(watch.getDimension(), watch);
		Cookie cookie = Mockito.mock(Cookie.class);
		assertTrue(w.check("something", cookie, new ConnectionProfile()));
	}

	@Test public void testCheckVersionReturnsFalseWhenWatchIsNull() {
		Cookie cookie = Mockito.mock(Cookie.class);
		ConnectionProfile connectionProfile = Mockito.mock(ConnectionProfile.class);
		Mockito.when(connectionProfile.getName()).thenReturn("mocked name");
		Mockito.when(
				w.queryVersion(
						cookie,
						connectionProfile
						)
				).thenReturn(null);
		assertFalse(w.checkVersion(cookie, connectionProfile));
	}


	@Test public void testCheckVersionReturnsTrueWhenWatchFound() {
		Cookie cookie = Mockito.mock(Cookie.class);
		ConnectionProfile connectionProfile = Mockito.mock(ConnectionProfile.class);
		Mockito.when(connectionProfile.getName()).thenReturn("mocked name");

		DimensionWatch watch = Mockito.mock(DimensionWatch.class);

		Mockito.when(watch.getDimension()).thenReturn("mocked dimension");

		DimensionWatcher whatcher = Mockito.spy(DimensionWatcher.class);

		Mockito.when(
				whatcher.queryVersion(
						cookie,
						connectionProfile
						)
				).thenReturn(watch);
		whatcher.getWatches().put(watch.getDimension(), watch);

		assertTrue(whatcher.checkVersion(cookie, connectionProfile));
	}

}
