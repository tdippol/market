package com.axiante.mui.model.impl;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.hamcrest.CoreMatchers;
import org.junit.Ignore;
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
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.Header;
import org.mockserver.model.HttpError;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import com.axiante.Tm1Tunnel;
import com.axiante.connection.AuthType;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mockserver.MockServerTest;
import com.axiante.mui.backing.CellSetCache;
import com.axiante.mui.backing.CookieRepository;
import com.axiante.mui.backing.MuiToken;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import com.axiante.tm1.json.objects.Table;
import com.axiante.tm1.mdx.Command;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.utility.Constants;
import com.axiante.utility.configuration.ColumnDef;
import com.axiante.utility.configuration.Configuration;
import com.axiante.utility.configuration.DynamicColumnsSettings;
import com.axiante.utility.configuration.StaticColumnDef;

@RunWith(MockitoJUnitRunner.class)
@NotThreadSafe
public class TableProducerTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Mock
	CellSetCache mockedCache;

	@Mock
	ApplicationConfiguration mockedProperties;

	@Mock
	CookieRepository cookieRepository;

	@Mock
	Query mockedQuery;

	@Mock
	Configuration mockedConfiguration;

	@Spy
	@InjectMocks
	TableProducerImpl mockedProducer;

	private static final Object semaphore = new Object();
	private final Cookie mockedCookie = new BasicClientCookie("cookie", "cookie");

	@Test
	public void testProduceTableWhenNullConfigurationThrowsException() {
		this.exception.expect(NullPointerException.class);
		final String string = "astring";
		final Cookie cookie = new BasicClientCookie("mocked", "mocked");
		final Double aDouble = new Random().nextDouble();
		final Boolean aBoolean = new Random().nextBoolean();
		this.mockedProducer.produceTable(null, // Configuration configuration
				mock(Query.class), // Query query
				string, // String cellSetId
				aBoolean, // boolean attributesData
				cookie, // String cookie
				aDouble// Double timeout)
		);
	}

	@Test
	public void testProduceTableWhenNullConnectionProfileThrowsException() {
		this.exception.expect(NullPointerException.class);
		final String string = "astring";
		final Double aDouble = new Random().nextDouble();
		final Boolean aBoolean = new Random().nextBoolean();
		final Cookie cookie = new BasicClientCookie("mocked", "mocked");
		this.mockedProducer.produceTable(mock(Configuration.class), // Configuration configuration
				mock(Query.class), // Query query
				string, // String cellSetId
				aBoolean, // boolean attributesData
				cookie, // String cookie
				aDouble// Double timeout)
		);
	}

	@Test
	public void testProduceTableWhenNullCommandThrowsException() {
		synchronized (semaphore) {
			if (!MockServerTest.isRunning()) {
				MockServerTest.startServer();
			}

			this.exception.expect(NullPointerException.class);
			final String string = "astring";
			final Double aDouble = new Random().nextDouble();
			final Boolean aBoolean = new Random().nextBoolean();

			final Configuration mockedConfiguration = mock(Configuration.class);
			final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
			when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
			final Cookie cookie = new BasicClientCookie("mocked", "mocked");
			this.mockedProducer.produceTable(mockedConfiguration, // Configuration configuration
					mock(Query.class), // Query query
					string, // String cellSetId
					aBoolean, // boolean attributesData
					cookie, // String cookie
					aDouble// Double timeout)
			);
			if (MockServerTest.isRunning()) {
				MockServerTest.stopServer();
			}
		}
	}

	@Test
	public void testProduceTableWithAttributesDataReturnsData() {
		// just for coverage
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final Query mockedQuery = mock(Query.class);
		final String mockedCellsetId = "123456";
		final boolean attributesData = true;
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
		final Double mockedTimeout = 5d;

		assertNotNull(this.mockedProducer.produceTable(mockedConfiguration, // Configuration configuration
				mockedQuery, // Query query
				mockedCellsetId, // String cellSetId
				attributesData, // boolean attributesData
				mockedCookie, // String cookie
				mockedTimeout// Double timeout)
		));
	}

	@Test
	public void testProduceTableWithoutAttributesDataReturnsData() {
		// just for coverage
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final Query mockedQuery = mock(Query.class);
		final String mockedCellsetId = "123456";
		final boolean attributesData = false;
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
		final Double mockedTimeout = 5d;
		assertNotNull(this.mockedProducer.produceTable(mockedConfiguration, // Configuration configuration
				mockedQuery, // Query query
				mockedCellsetId, // String cellSetId
				attributesData, // boolean attributesData
				mockedCookie, // String cookie
				mockedTimeout// Double timeout)
		));

	}

	@Test
	public void testProduceTableWithoutTimeoutReturnsData() {
		// just for coverage
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final Query mockedQuery = mock(Query.class);
		final String mockedCellsetId = "123456";
		final boolean attributesData = false;
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
		final Double mockedTimeout = null;
		assertNotNull(this.mockedProducer.produceTable(mockedConfiguration, // Configuration configuration
				mockedQuery, // Query query
				mockedCellsetId, // String cellSetId
				attributesData, // boolean attributesData
				mockedCookie, // String cookie
				mockedTimeout// Double timeout)
		));

	}

	@Test
	public void testProduceTableWhenServerNotRespondsReturnsErrorTable() {
		synchronized (semaphore) {
			if (MockServerTest.isRunning()) {
				MockServerTest.stopServer();
			}
			final Configuration mockedConfiguration = mock(Configuration.class);
			final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
			when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
			final Query mockedQuery = mock(Query.class);
			final String mockedCellsetId = "123456";
			final boolean attributesData = false;
			final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
			final Double mockedTimeout = 5d;
			final Table test = this.mockedProducer.produceTable(mockedConfiguration, // Configuration configuration
					mockedQuery, // Query query
					mockedCellsetId, // String cellSetId
					attributesData, // boolean attributesData
					mockedCookie, // String cookie
					mockedTimeout// Double timeout)
			);
			assertNotNull(test);
			assertThat(test.isError(), equalTo(true));
			assertThat(test.getErrorMessage(), containsString("Host  unreachable"));
		}
	}

	@Test
	public void testProduceTableWhenQueryErrorReturnsNull() {
		synchronized (semaphore) {
			if (!MockServerTest.isRunning()) {
				MockServerTest.startServer();
			}

			final Configuration mockedConfiguration = mock(Configuration.class);
			final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
			when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
			final Command mockedCommand = mock(Command.class);
			when(mockedCommand.getCube())
					.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
			when(mockedCommand.getAxes())
					.thenReturn(Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND));
			when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
			final Query mockedMDX = mock(Query.class);
			when(mockedConfiguration.getMdx()).thenReturn(mockedMDX);
			when(mockedMDX.generateMDX()).thenReturn("mocked mdx");
			final Query mockedQuery = mock(Query.class);
			final String mockedCellsetId = "123456";
			final boolean attributesData = false;
			final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
			final Double mockedTimeout = 5d;
			final Table test = this.mockedProducer.produceTable(mockedConfiguration, // Configuration configuration
					mockedQuery, // Query query
					mockedCellsetId, // String cellSetId
					attributesData, // boolean attributesData
					mockedCookie, // String cookie
					mockedTimeout// Double timeout)
			);
			assertNull(test);
			if (MockServerTest.isRunning()) {
				MockServerTest.stopServer();
			}
		}
	}

	@Test
	public void testProduceTableWhenJsonDataMalformedThrowsException() {
		synchronized (semaphore) {
			if (!MockServerTest.isRunning()) {
				MockServerTest.startServer();
			}

			final Configuration mockedConfiguration = mock(Configuration.class);
			final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
			when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
			when(mockedConfiguration.getDynamicColumns()).thenReturn(false);
			final Command mockedCommand = mock(Command.class);
			when(mockedCommand.getCube())
					.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
			when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
			final Query mockedMDX = mock(Query.class);
			final boolean attributesData = false;
			final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
			final Double mockedTimeout = 5d;
			this.exception.expect(NullPointerException.class);
			final Table test = this.mockedProducer.produceTable(mockedConfiguration, // Configuration configuration
					mockedMDX, // Query query
					null, // String cellSetId: check that it creates one
					attributesData, // boolean attributesData
					mockedCookie, // String cookie
					mockedTimeout// Double timeout)
			);
			assertNull(test);
			if (MockServerTest.isRunning()) {
				MockServerTest.stopServer();
			}
		}
	}

	@Test
	public void testProduceTableWhenCorrectFlowReturnsData() {
		synchronized (semaphore) {
			if (!MockServerTest.isRunning()) {
				MockServerTest.startServer();
			}

			final Configuration mockedConfiguration = mock(Configuration.class);
			final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
			when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
			when(mockedConfiguration.getDynamicColumns()).thenReturn(false);

			final Command mockedCommand = mock(Command.class);
			final Command mockedCubeCommand = Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND);
			final Command mockedCountCellCommand = Command.createRootContainer()
					.addChildContainer(Constants.AXES_COMMAND);

			final Command mockedAxesCommand = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);
			mockedAxesCommand.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
					.addChildContainer("Name", Constants.OPERATION_SELECT);
			mockedAxesCommand.addChildContainer(Constants.TUPLES_COMMAND)
					.addChildContainer("Members", Constants.OPERATION_EXPAND)
					.addChildContainer("Name", Constants.OPERATION_SELECT);

			final Command mockedCellsCommand = Command.createRootContainer().addChildContainer(Constants.CELLS_COMMAND);
			mockedCellsCommand.addChildContainer("Ordinal,Value", Constants.OPERATION_SELECT);

			when(mockedCommand.getCube()).thenReturn(mockedCubeCommand);
			when(mockedCommand.getAxes(ArgumentMatchers.anyBoolean())).thenReturn(mockedCountCellCommand);
			when(mockedCommand.getAxes()).thenReturn(mockedAxesCommand);
			when(mockedCommand.getCells(ArgumentMatchers.anyBoolean())).thenReturn(mockedCellsCommand);
			when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);

			final Query mockedMDX = mock(Query.class);

			when(this.mockedCache.register()).thenReturn(MuiToken.get());

			final boolean attributesData = false;
			final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
			final Double mockedTimeout = 5d;
			final Table test = this.mockedProducer.produceTable(mockedConfiguration, // Configuration configuration
					mockedMDX, // Query query
					null, // String cellSetId: check that it creates one
					attributesData, // boolean attributesData
					mockedCookie, // String cookie
					mockedTimeout// Double timeout)
			);
			assertNotNull(test);
			assertThat(test.isError(), CoreMatchers.equalTo(false));
			assertThat(test.getColSize(), CoreMatchers.equalTo(388));
			assertThat(test.getActualDataIndex(), CoreMatchers.equalTo(3));
			assertThat(test.getHeaders().get(0).getCaption(), CoreMatchers.equalTo("Versione"));
			assertThat(test.getHeaders().get(1).getCaption(), CoreMatchers.equalTo("Promozione"));
			assertThat(test.getHeaders().get(2).getCaption(), CoreMatchers.equalTo("Reparto"));

			if (MockServerTest.isRunning()) {
				MockServerTest.stopServer();
			}
		}
	}

	@Test
	public void testProduceTableWhenNoFilterQueryFlowReturnsData() {
		synchronized (semaphore) {
			if (!MockServerTest.isRunning()) {
				MockServerTest.startServer();
			}

			final Configuration mockedConfiguration = mock(Configuration.class);
			final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
			when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
			when(mockedConfiguration.getDynamicColumns()).thenReturn(false);

			final Command mockedCommand = mock(Command.class);
			final Command mockedCubeCommand = Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND);
			final Command mockedCountCellCommand = Command.createRootContainer()
					.addChildContainer(Constants.AXES_COMMAND);

			final Command mockedAxesCommand = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);
			mockedAxesCommand.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
					.addChildContainer("Name", Constants.OPERATION_SELECT);
			mockedAxesCommand.addChildContainer(Constants.TUPLES_COMMAND)
					.addChildContainer("Members", Constants.OPERATION_EXPAND)
					.addChildContainer("Name", Constants.OPERATION_SELECT);

			final Command mockedCellsCommand = Command.createRootContainer().addChildContainer(Constants.CELLS_COMMAND);
			mockedCellsCommand.addChildContainer("Ordinal,Value", Constants.OPERATION_SELECT);

			when(mockedCommand.getCube()).thenReturn(mockedCubeCommand);
			when(mockedCommand.getAxes(ArgumentMatchers.anyBoolean())).thenReturn(mockedCountCellCommand);
			when(mockedCommand.getAxes()).thenReturn(mockedAxesCommand);
			when(mockedCommand.getCells(ArgumentMatchers.anyBoolean())).thenReturn(mockedCellsCommand);
			when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);

			final Query mockedMDX = mock(Query.class);
			when(mockedConfiguration.getMdx()).thenReturn(mockedMDX);

			when(this.mockedCache.register()).thenReturn(MuiToken.get());

			final boolean attributesData = false;
			final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
			final Double mockedTimeout = 5d;
			final Table test = this.mockedProducer.produceTable(mockedConfiguration, // Configuration configuration
					null, // Query query
					null, // String cellSetId: check that it creates one
					attributesData, // boolean attributesData
					mockedCookie, // String cookie
					mockedTimeout// Double timeout)
			);
			assertNotNull(test);
			assertThat(test.isError(), CoreMatchers.equalTo(false));
			assertThat(test.getColSize(), CoreMatchers.equalTo(388));
			assertThat(test.getActualDataIndex(), CoreMatchers.equalTo(3));
			assertThat(test.getHeaders().get(0).getCaption(), CoreMatchers.equalTo("Versione"));
			assertThat(test.getHeaders().get(1).getCaption(), CoreMatchers.equalTo("Promozione"));
			assertThat(test.getHeaders().get(2).getCaption(), CoreMatchers.equalTo("Reparto"));

			if (MockServerTest.isRunning()) {
				MockServerTest.stopServer();
			}
		}
	}

	@Test
	public void testProduceTableWhenNoCookieFlowReturnsData() {
		synchronized (semaphore) {
			if (!MockServerTest.isRunning()) {
				MockServerTest.startServer();
			}

			final Configuration mockedConfiguration = mock(Configuration.class);
			final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
			when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
			when(mockedConfiguration.getDynamicColumns()).thenReturn(false);

			final Command mockedCommand = mock(Command.class);
			final Command mockedCubeCommand = Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND);
			final Command mockedCountCellCommand = Command.createRootContainer()
					.addChildContainer(Constants.AXES_COMMAND);

			final Command mockedAxesCommand = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);
			mockedAxesCommand.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
					.addChildContainer("Name", Constants.OPERATION_SELECT);
			mockedAxesCommand.addChildContainer(Constants.TUPLES_COMMAND)
					.addChildContainer("Members", Constants.OPERATION_EXPAND)
					.addChildContainer("Name", Constants.OPERATION_SELECT);

			final Command mockedCellsCommand = Command.createRootContainer().addChildContainer(Constants.CELLS_COMMAND);
			mockedCellsCommand.addChildContainer("Ordinal,Value", Constants.OPERATION_SELECT);

			when(mockedCommand.getCube()).thenReturn(mockedCubeCommand);
			when(mockedCommand.getAxes(ArgumentMatchers.anyBoolean())).thenReturn(mockedCountCellCommand);
			when(mockedCommand.getAxes()).thenReturn(mockedAxesCommand);
			when(mockedCommand.getCells(ArgumentMatchers.anyBoolean())).thenReturn(mockedCellsCommand);
			when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);

			final Query mockedMDX = mock(Query.class);
			when(mockedConfiguration.getMdx()).thenReturn(mockedMDX);

			when(this.mockedCache.register()).thenReturn(MuiToken.get());

			final boolean attributesData = false;
			// String mockedCookie = "mocked cookie";
			final Double mockedTimeout = 5d;
			final Table test = this.mockedProducer.produceTable(mockedConfiguration, // Configuration configuration
					null, // Query query
					null, // String cellSetId: check that it creates one
					attributesData, // boolean attributesData
					null, // String cookie
					mockedTimeout// Double timeout)
			);
			assertNotNull(test);
			assertThat(test.isError(), CoreMatchers.equalTo(false));
			assertThat(test.getColSize(), CoreMatchers.equalTo(388));
			assertThat(test.getActualDataIndex(), CoreMatchers.equalTo(3));
			assertThat(test.getHeaders().get(0).getCaption(), CoreMatchers.equalTo("Versione"));
			assertThat(test.getHeaders().get(1).getCaption(), CoreMatchers.equalTo("Promozione"));
			assertThat(test.getHeaders().get(2).getCaption(), CoreMatchers.equalTo("Reparto"));

			if (MockServerTest.isRunning()) {
				MockServerTest.stopServer();
			}
		}
	}

	@Test
	public void testProduceTableWhenServerBusyReturnsTableWithError() {

		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);

		client.when(new HttpRequest().withPath("/api/v1/Configuration/ProductVersion/$value")).respond(
				new HttpResponse().withStatusCode(200).withHeader(new Header("Set-Cookie", "TM1Cookie pippo")));
		client.when(new HttpRequest().withPath("/api/v1/ExecuteMDX"))
				.respond(new HttpResponse().withStatusCode(401).withDelay(TimeUnit.SECONDS, 10));

		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		when(mockedConfiguration.getDynamicColumns()).thenReturn(false);

		final Command mockedCommand = mock(Command.class);
		final Command mockedCubeCommand = Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND);

		when(mockedCommand.getCube()).thenReturn(mockedCubeCommand);
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);

		final Query mockedMDX = mock(Query.class);

		when(this.mockedCache.registerBusy()).thenReturn(MuiToken.getBusy());

		final boolean attributesData = false;
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
		final Double mockedTimeout = 5d;
		doReturn(1d).when(this.mockedProperties).getConnectionTimeout();
		doReturn(1).when(this.mockedProperties).getSocketTimeout();
		doReturn(1).when(this.mockedProperties).getConnectionRequestTimeout();

		final Table test = this.mockedProducer.produceTable(mockedConfiguration, // Configuration configuration
				mockedMDX, // Query query
				null, // String cellSetId: check that it creates one
				attributesData, // boolean attributesData
				mockedCookie, // String cookie
				mockedTimeout// Double timeout)
		);

		assertNotNull(test);
		assertThat(test.isError(), equalTo(true));
		assertThat(test.getErrorMessage(), containsString("TM1 server did not respond"));

		if (MockServerTest.isRunning()) {
			MockServerTest.stopServer();
		}
	}

	@Test
	public void produceTable_whenClientCannotObtainToken_shouldReturnTableWithError() {
		// Arrange
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		final Command mockedCommand = mock(Command.class);
		final Command mockedCubeCommand = Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND);
		when(mockedCommand.getCube()).thenReturn(mockedCubeCommand);
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		when(mockedConfiguration.getDynamicColumns()).thenReturn(false);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}
		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		client.when(new HttpRequest().withPath("/api/v1/Configuration/ProductVersion/$value"))
				.error(new HttpError().withDropConnection(true));
		// Act
		final Table table = mockedProducer.produceTable(mockedConfiguration, mockedQuery,
				null, false, mockedCookie, 5d);
		// Assert
		assertNotNull(table);
		assertTrue(table.isError());
		assertEquals("There was an error in the request sent to TM1 server", table.getErrorMessage());
		// Cleanup
		if (MockServerTest.isRunning()) {
			MockServerTest.stopServer();
		}
	}

	@Test
	public void testConnectionCloseSetsVariable() {
		this.mockedProducer.connectionClosing();
	}

	@Test
	public void testForceCloseSessionThrowsExceptionWhenCookieIsNull() {
		this.exception.expect(NullPointerException.class);
		this.mockedProducer.forceCloseSession(null, mock(ConnectionProfile.class));
	}

	public void testForceCloseSessionThrowsExceptionWhenProfileIsNull() {
		this.exception.expect(NullPointerException.class);
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");

		this.mockedProducer.forceCloseSession(mockedCookie, null);
	}

	@Test
	public void testForceCloseSessionWhenNoServerReturnsFalse() {
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		final boolean test = this.mockedProducer.forceCloseSession(mockedCookie, mockedProfile);
		assertFalse(test);
	}

	@Test
	public void testForceCloseSessionWhenServerReturnsTrue() {
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		final boolean test = this.mockedProducer.forceCloseSession(mockedCookie, mockedProfile);
		assertTrue(test);

		MockServerTest.stopServer();
	}

	@Test
	public void testProduceTableContentReader() throws Exception {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final String mockedCellSet = "stVqWl4-AYA4AAAg";
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		final Command mockedCellsCommand = Command.createRootContainer().addChildContainer(Constants.CELLS_COMMAND);
		mockedCellsCommand.addChildContainer("Ordinal,Value", Constants.OPERATION_SELECT);
		when(mockedCommand.getCells(ArgumentMatchers.anyBoolean())).thenReturn(mockedCellsCommand);
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		try (CloseableHttpResponse test = this.mockedProducer.produceTableContentReader(mockedConfiguration,
				mockedCellSet, mockedCookie)) {
			assertNotNull(test);
		}
		MockServerTest.stopServer();
	}
	
	@Test
	public void testProduceTableContentReaderWithResponseError() throws Exception {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final String mockedCellSet = "stVqWl4-AYA4AAAg";
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(null);
		final Command mockedCellsCommand = Command.createRootContainer().addChildContainer(Constants.CELLS_COMMAND);
		mockedCellsCommand.addChildContainer("Ordinal,Value", Constants.OPERATION_SELECT);
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		try (CloseableHttpResponse test = this.mockedProducer.produceTableContentReader(mockedConfiguration,
				mockedCellSet, mockedCookie)) {
			assertNull(test);
		}
		MockServerTest.stopServer();
	}
	
	@Test
	public void testProduceTableContentReaderWithResponseNull() throws Exception {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final String mockedCellSet = "cellSetId";
		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		final Command mockedCellsCommand = Command.createRootContainer().addChildContainer(Constants.CELLS_COMMAND);
		mockedCellsCommand.addChildContainer("Ordinal,Value", Constants.OPERATION_SELECT);
		when(mockedCommand.getCells(ArgumentMatchers.anyBoolean())).thenReturn(mockedCellsCommand);
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		try (CloseableHttpResponse test = this.mockedProducer.produceTableContentReader(mockedConfiguration,
				mockedCellSet, null)) {
			assertNull(test);
		}
		
		MockServerTest.stopServer();
	}
	
	@Test
	public void testProduceTableContentReaderWithoutCellSetId() throws Exception {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final String mockedCellSet = null;
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		final Command mockedCellsCommand = Command.createRootContainer().addChildContainer(Constants.CELLS_COMMAND);
		mockedCellsCommand.addChildContainer("Ordinal,Value", Constants.OPERATION_SELECT);
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		try (CloseableHttpResponse test = this.mockedProducer.produceTableContentReader(mockedConfiguration,
				mockedCellSet, mockedCookie)) {
			assertNull(test);
		}
		MockServerTest.stopServer();
	}

	@Test
	public void testProduceTableSkeltonThrowsExceptionWhenTokenIsNull() {
		this.exception.expect(NullPointerException.class);
		this.mockedProducer.produceTableSkelton(null, mock(Query.class), "something", "something");
	}

	@Test
	public void testProduceTableSkeltonReturnsNullWhenNoServer() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);

		final MuiToken mockedToken = mock(MuiToken.class);
		when(mockedToken.getConfiguration()).thenReturn(mockedConfiguration);

		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		when(mockedCommand.getAxes())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND));
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);

		final Query mockedMDX = mock(Query.class);
		when(mockedConfiguration.getMdx()).thenReturn(mockedMDX);
		when(mockedMDX.generateMDX()).thenReturn("mocked mdx");

		assertNull(this.mockedProducer.produceTableSkelton(mockedToken, mock(Query.class), "passport", "cellset"));
	}

	@Test
	public void testProduceTableSkeltonReturnsTableWhenServerFound() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);

		final MuiToken mockedToken = mock(MuiToken.class);
		when(mockedToken.getConfiguration()).thenReturn(mockedConfiguration);

		final Command mockedAxesCommand = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);
		mockedAxesCommand.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);
		mockedAxesCommand.addChildContainer(Constants.TUPLES_COMMAND)
				.addChildContainer("Members", Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);

		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		when(mockedCommand.getAxes()).thenReturn(mockedAxesCommand);
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);

		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}

		final Table test = this.mockedProducer.produceTableSkelton(mockedToken, mock(Query.class), "passport",
				"stVqWl4-AYA4AAAg");
		MockServerTest.stopServer();
		assertNotNull(test);
		assertThat(test.getColSize(), equalTo(388));
		assertThat(test.getActualDataIndex(), equalTo(3));
		assertThat(test.getActualDataSize(), equalTo(385));
	}

	@Test
	public void testProduceTableSkeltonReturnsTableWhenServerFoundAndNoPassport() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);

		final MuiToken mockedToken = mock(MuiToken.class);
		when(mockedToken.getConfiguration()).thenReturn(mockedConfiguration);

		final Command mockedAxesCommand = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);
		mockedAxesCommand.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);
		mockedAxesCommand.addChildContainer(Constants.TUPLES_COMMAND)
				.addChildContainer("Members", Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);

		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		when(mockedCommand.getAxes()).thenReturn(mockedAxesCommand);
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);

		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}

		final Table test = this.mockedProducer.produceTableSkelton(mockedToken, mock(Query.class), null,
				"stVqWl4-AYA4AAAg");
		MockServerTest.stopServer();
		assertNotNull(test);
		assertThat(test.getColSize(), equalTo(388));
		assertThat(test.getActualDataIndex(), equalTo(3));
		assertThat(test.getActualDataSize(), equalTo(385));
	}

	@Test
	public void testProduceTableSkeltonReturnsTableWhenServerFoundAndNoCellsetId() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);

		final MuiToken mockedToken = mock(MuiToken.class);
		when(mockedToken.getConfiguration()).thenReturn(mockedConfiguration);

		final Command mockedAxesCommand = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);
		mockedAxesCommand.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);
		mockedAxesCommand.addChildContainer(Constants.TUPLES_COMMAND)
				.addChildContainer("Members", Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);

		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		when(mockedCommand.getAxes()).thenReturn(mockedAxesCommand);
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);

		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}

		final Table test = this.mockedProducer.produceTableSkelton(mockedToken, mock(Query.class), "a passport", null);
		MockServerTest.stopServer();
		assertNotNull(test);
		assertThat(test.getColSize(), equalTo(388));
		assertThat(test.getActualDataIndex(), equalTo(3));
		assertThat(test.getActualDataSize(), equalTo(385));
	}

	@Test
	public void testGenerateCellSetThrowsExceptionWhenConfigurationIsNull() {
		this.exception.expect(NullPointerException.class);
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");

		this.mockedProducer.generateCellset(null, mock(Query.class), mockedCookie);
	}

	@Test
	public void testGenerateCellSetThrowsExceptionWhenQueryIsNull() {
		this.exception.expect(NullPointerException.class);
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");

		this.mockedProducer.generateCellset(mock(Configuration.class), null, mockedCookie);
	}

	@Test
	public void testGenerateCellSetReturnsNullWhenUnauthorized() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		mockedProfile.setAuthType(AuthType.PASSPORT);
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
		if (MockServerTest.isRunning()) {
			MockServerTest.stopServer();
		}
		assertNull(this.mockedProducer.generateCellset(mockedConfiguration, mock(Query.class), mockedCookie));
	}

	@Test
	public void testGenerateCellSetReturnsNullWhenServerNotFound() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");

		assertNull(this.mockedProducer.generateCellset(mockedConfiguration, mock(Query.class), mockedCookie));
	}

	@Test
	public void testGenerateCellSetReturnsTokenWhenServerFound() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		when(mockedCommand.getAxes(ArgumentMatchers.anyBoolean()))
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND));

		when(this.mockedCache.register()).thenReturn(MuiToken.get());
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");

		final MuiToken test = this.mockedProducer.generateCellset(mockedConfiguration, mock(Query.class), mockedCookie);
		MockServerTest.stopServer();

		assertNotNull(test);
		assertThat(test.getCellsetId(), equalTo("stVqWl4-AYA4AAAg"));
		assertThat(test.getCookie().getName(), equalTo("mocked cookie"));
		assertThat(test.getCookie().getValue(), equalTo("mocked"));

		assertNull(test.getHeaders());
		assertThat(test.getTotalCells(), equalTo(6160));
	}

	@Test
	public void testGenerateCellSetReturnsTokenWhenDynamicConfigAndServerFound() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		when(mockedConfiguration.getDynamicColumns()).thenReturn(true);
		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		when(mockedCommand.getAxes(ArgumentMatchers.anyBoolean()))
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND));

		final Command members = Command.createRootContainer().addChildContainer("Members", Constants.OPERATION_EXPAND)
				.addChild(Command.createCommand("Name", Constants.OPERATION_SELECT),
						Command.createCommand("Level", null), Command.createCommand("Attributes", null),
						Command.createCommand("UniqueName", null));

		final Command tuples = Command.createRootContainer().addChildContainer(Constants.TUPLES_COMMAND, null);
		tuples.addChild(members);

		final Command mockedAxis2Command = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);

		mockedAxis2Command.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);
		mockedAxis2Command.addChild(tuples);

		when(mockedCommand.getAxes()).thenReturn(mockedAxis2Command);
		when(this.mockedCache.register()).thenReturn(MuiToken.get());
		final DynamicColumnsSettings mockedSettings = mock(DynamicColumnsSettings.class);
		final Map<String, String> mockedHeaderDefaults = new HashMap<>();
		mockedHeaderDefaults.put("marryChildren", "true");
		final ColumnDef mockedChildrendefaults = new ColumnDef();
		mockedChildrendefaults.setWidth(110);
		mockedChildrendefaults.setEditable(true);
		mockedChildrendefaults.setColumnGroupShow("open");
		mockedChildrendefaults.setAggFunc("sum");
		mockedChildrendefaults.setType(new String[] { "TM1DataColumnInteger", "numericColumn" });
		mockedChildrendefaults.setHide(false);
		final Map<String, ColumnDef> mockedChildrenCustomTypes = new HashMap<>();
		final ColumnDef mockedVenduto = new ColumnDef();
		mockedVenduto.setColumnGroupShow("always");
		mockedVenduto.setType(new String[] { "TM1DataColumnNumberK", "numericColumn" });
		mockedChildrenCustomTypes.put("VENDUTO_PROMO_NETTO", mockedVenduto);
		final ColumnDef mockedLordo = new ColumnDef();
		mockedLordo.setColumnGroupShow("always");
		mockedLordo.setType(new String[] { "TM1DataColumnNumber", "numericColumn" });
		mockedChildrenCustomTypes.put("MARGINE_LORDO__perc_", mockedLordo);

		when(mockedConfiguration.getDynamicColumnsSettings()).thenReturn(mockedSettings);

		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");

		final MuiToken test = this.mockedProducer.generateCellset(mockedConfiguration, mock(Query.class), mockedCookie);
		MockServerTest.stopServer();

		assertNotNull(test);
		assertThat(test.getCellsetId(), equalTo("stVqWl4-AYA4AAAg"));
		assertThat(test.getCookie().getName(), equalTo("mocked cookie"));
		assertThat(test.getCookie().getValue(), equalTo("mocked"));
		assertNotNull(test.getHeaders());
		assertThat(test.getHeaders().size(), equalTo(385));
	}

	@Test
	public void testGenerateCellSetReturnsTokenWhenDynamicConfigAndServerFoundAndAuthPassport() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.PASSPORT, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		when(mockedConfiguration.getDynamicColumns()).thenReturn(true);
		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		when(mockedCommand.getAxes(ArgumentMatchers.anyBoolean()))
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND));

		final Command members = Command.createRootContainer().addChildContainer("Members", Constants.OPERATION_EXPAND)
				.addChild(Command.createCommand("Name", Constants.OPERATION_SELECT),
						Command.createCommand("Level", null), Command.createCommand("Attributes", null),
						Command.createCommand("UniqueName", null));

		final Command tuples = Command.createRootContainer().addChildContainer(Constants.TUPLES_COMMAND, null);
		tuples.addChild(members);

		final Command mockedAxis2Command = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);

		mockedAxis2Command.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);
		mockedAxis2Command.addChild(tuples);

		when(mockedCommand.getAxes()).thenReturn(mockedAxis2Command);
		when(this.mockedCache.register()).thenReturn(MuiToken.get());
		final DynamicColumnsSettings mockedSettings = mock(DynamicColumnsSettings.class);
		final Map<String, String> mockedHeaderDefaults = new HashMap<>();
		mockedHeaderDefaults.put("marryChildren", "true");
		final ColumnDef mockedChildrendefaults = new ColumnDef();
		mockedChildrendefaults.setWidth(110);
		mockedChildrendefaults.setEditable(true);
		mockedChildrendefaults.setColumnGroupShow("open");
		mockedChildrendefaults.setAggFunc("sum");
		mockedChildrendefaults.setType(new String[] { "TM1DataColumnInteger", "numericColumn" });
		mockedChildrendefaults.setHide(false);

		final Map<String, ColumnDef> mockedChildrenCustomTypes = new HashMap<>();
		final ColumnDef mockedVenduto = new ColumnDef();
		mockedVenduto.setColumnGroupShow("always");
		mockedVenduto.setType(new String[] { "TM1DataColumnNumberK", "numericColumn" });
		mockedChildrenCustomTypes.put("VENDUTO_PROMO_NETTO", mockedVenduto);
		final ColumnDef mockedLordo = new ColumnDef();
		mockedLordo.setColumnGroupShow("always");
		mockedLordo.setType(new String[] { "TM1DataColumnNumber", "numericColumn" });
		mockedChildrenCustomTypes.put("MARGINE_LORDO__perc_", mockedLordo);

		when(mockedConfiguration.getDynamicColumnsSettings()).thenReturn(mockedSettings);
		when(mockedConfiguration.getDynamicColumns()).thenReturn(Boolean.TRUE);

		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");

		final MuiToken test = this.mockedProducer.generateCellset(mockedConfiguration, mock(Query.class), mockedCookie);
		MockServerTest.stopServer();

		assertNotNull(test);
		assertThat(test.getCellsetId(), equalTo("stVqWl4-AYA4AAAg"));
		assertThat(test.getCookie().getName(), equalTo("mocked cookie"));
		assertThat(test.getCookie().getValue(), equalTo("mocked"));

		assertNotNull(test.getHeaders());
		assertThat(test.getHeaders().size(), equalTo(385));
	}

	@Test
	public void testProduceTableHeadersThrowExceptionWhenNullToken() {
		this.exception.expect(NullPointerException.class);
		this.mockedProducer.produceTableHeaders(null, mock(Query.class), "1234");
	}

	@Test
	public void testProduceTableHeadersWhenNoServerReturnsNull() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final MuiToken mockedToken = mock(MuiToken.class);
		when(mockedToken.getConfiguration()).thenReturn(mockedConfiguration);

		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		final Command mockedAxis2Command = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND)
				.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);
		mockedAxis2Command.addChildContainer(Constants.TUPLES_COMMAND, Constants.OPERATION_EXPAND)
				.addChild(Command.createCommand("Members", Constants.OPERATION_EXPAND).addChild(
						Command.createCommand("Name", Constants.OPERATION_SELECT), Command.createCommand("Level", null),
						Command.createCommand("Attributes", null), Command.createCommand("UniqueName", null)));

		when(mockedCommand.getAxes()).thenReturn(mockedAxis2Command);
		final Query mockedQuery = mock(Query.class);
		when(mockedQuery.generateMDX()).thenReturn("mocked mdx");
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		when(mockedConfiguration.getMdx()).thenReturn(mockedQuery);
		assertNull(this.mockedProducer.produceTableHeaders(mockedToken, mock(Query.class), "1234"));
	}

	@Test
	public void testProduceTableHeadersWhenServerReturnsTable() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final MuiToken mockedToken = mock(MuiToken.class);
		when(mockedToken.getConfiguration()).thenReturn(mockedConfiguration);
		when(mockedToken.getCellsetId()).thenReturn("stVqWl4-AYA4AAAg");

		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));

		final Command members = Command.createRootContainer().addChildContainer("Members", Constants.OPERATION_EXPAND)
				.addChild(Command.createCommand("Name", Constants.OPERATION_SELECT),
						Command.createCommand("Level", null), Command.createCommand("Attributes", null),
						Command.createCommand("UniqueName", null));

		final Command tuples = Command.createRootContainer().addChildContainer(Constants.TUPLES_COMMAND, null);
		tuples.addChild(members);

		final Command mockedAxis2Command = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);

		mockedAxis2Command.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);
		mockedAxis2Command.addChild(tuples);

		when(mockedCommand.getAxes()).thenReturn(mockedAxis2Command);
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		final Table test = this.mockedProducer.produceTableHeaders(mockedToken, mock(Query.class),
				mockedToken.getCellsetId());
		assertNotNull(test);
		MockServerTest.stopServer();
	}

	@Test
	public void testProduceTableHeadersWhenServerAndNoPassportReturnsTable() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final MuiToken mockedToken = mock(MuiToken.class);
		when(mockedToken.getConfiguration()).thenReturn(mockedConfiguration);
		when(mockedToken.getCellsetId()).thenReturn("stVqWl4-AYA4AAAg");

		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));

		final Command members = Command.createRootContainer().addChildContainer("Members", Constants.OPERATION_EXPAND)
				.addChild(Command.createCommand("Name", Constants.OPERATION_SELECT),
						Command.createCommand("Level", null), Command.createCommand("Attributes", null),
						Command.createCommand("UniqueName", null));

		final Command tuples = Command.createRootContainer().addChildContainer(Constants.TUPLES_COMMAND, null);
		tuples.addChild(members);

		final Command mockedAxis2Command = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);

		mockedAxis2Command.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);
		mockedAxis2Command.addChild(tuples);

		when(mockedCommand.getAxes()).thenReturn(mockedAxis2Command);
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		final Table test = this.mockedProducer.produceTableHeaders(mockedToken, mock(Query.class),
				mockedToken.getCellsetId());
		assertNotNull(test);
		MockServerTest.stopServer();
	}

	@Test
	public void testProduceTableHeadersWhenServerAndNoPassportAndNoCellsetIdReturnsTable() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		final MuiToken mockedToken = mock(MuiToken.class);
		when(mockedToken.getConfiguration()).thenReturn(mockedConfiguration);

		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));

		final Command members = Command.createRootContainer().addChildContainer("Members", Constants.OPERATION_EXPAND)
				.addChild(Command.createCommand("Name", Constants.OPERATION_SELECT),
						Command.createCommand("Level", null), Command.createCommand("Attributes", null),
						Command.createCommand("UniqueName", null));

		final Command tuples = Command.createRootContainer().addChildContainer(Constants.TUPLES_COMMAND, null);
		tuples.addChild(members);

		final Command mockedAxis2Command = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);

		mockedAxis2Command.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);
		mockedAxis2Command.addChild(tuples);

		when(mockedCommand.getAxes()).thenReturn(mockedAxis2Command);
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		final Table test = this.mockedProducer.produceTableHeaders(mockedToken, mock(Query.class), null);
		assertNotNull(test);
		MockServerTest.stopServer();
	}

	@Test
	public void testMapToCellReturnsDataWhenNullInput() {
		assertNotNull(this.mockedProducer.mapToCell(null));
	}

	@Test
	public void testMapToCellReturnsDataOfSameSizeAsInputWhenInputHasNoChildren() {
		final StaticColumnDef def = mock(StaticColumnDef.class);
		when(def.getChildren()).thenReturn(null);
		final String testName = "mocked field";
		when(def.getField()).thenReturn(testName);
		final List<Cell> test = this.mockedProducer.mapToCell(def);
		assertNotNull(test);
		assertThat(test.size(), equalTo(1));
		final Cell cell = test.get(0);
		assertTrue(cell.getIsHeader());
		assertThat(cell.getName(), equalTo(testName));
		assertThat(cell.getCaption(), equalTo(testName));
		assertTrue(cell.isFromConfiguration());
	}

	@Test
	public void testMapToCellReturnsDataOfSameSizeAsInputChildrenWhenInputHasChildren() {
		final StaticColumnDef def = mock(StaticColumnDef.class);
		final StaticColumnDef child1 = mock(StaticColumnDef.class);
		final StaticColumnDef child2 = mock(StaticColumnDef.class);

		final String testName1 = "mocked field1";
		final String testName2 = "mocked field2";

		when(child1.getField()).thenReturn(testName1);
		when(child2.getField()).thenReturn(testName2);
		when(def.getChildren()).thenReturn(new StaticColumnDef[] { child1, child2 });
		final List<Cell> test = this.mockedProducer.mapToCell(def);
		assertNotNull(test);
		assertThat(test.size(), equalTo(2));
		Cell cell = test.get(0);
		assertTrue(cell.getIsHeader());
		assertThat(cell.getName(), equalTo(testName1));
		assertThat(cell.getCaption(), equalTo(testName1));
		assertTrue(cell.isFromConfiguration());

		cell = test.get(1);
		assertTrue(cell.getIsHeader());
		assertThat(cell.getName(), equalTo(testName2));
		assertThat(cell.getCaption(), equalTo(testName2));
		assertTrue(cell.isFromConfiguration());

	}

	@Test
	public void testConvertToHeadersReturnsNullWhenNullInput() {
		assertNull(this.mockedProducer.convertToHeaders(null));
	}

	@Test
	public void testConvertToHeadersReturnsNullWhenEmptyInput() {
		final Row<Cell> row = this.mockedProducer.convertToHeaders("");
		assertNull(row);
	}

	@Test
	public void testConvertToHeadersReturnsData() {
		String test = "["
				+ "        {'headerName':'Tipo Promozione','field':'TipoPromozione.Descrizione','width':100,'hide':true,'rowGroup': true , 'editable': false,'type':['TM1Element']},"
				+ "        {'headerName':'Promozione','field':'Promozione.Descrizione_plus_Data','width':100,'hide':true,'rowGroup': true , 'editable': false,'type':['TM1Element']},"
				+ "        {'headerName':'Listino'," + "          'marryChildren':true," + "          'children' :"
				+ "          ["
				+ "            {'headerName':'Extra Contratto','field':'MisuraPrestazioni$listino$$Contratto$ExtraContratto','width':100,'hide':false,'rowGroup': false , 'editable': true,'type':['TM1DataColumnInteger']} ,"
				+ "            {'headerName':'Contratto','field':'MisuraPrestazioni$listino$$Contratto$Contratto_1','width':100,'hide':false,'rowGroup': false , 'editable': true,'type':['TM1DataColumnInteger']}"
				+ "          ]" + "        }" + "      ]";

		test = test.replace("\'", "\"");

		final Row<Cell> row = this.mockedProducer.convertToHeaders(test);

		assertNotNull(row);
	}

	@Test
	public void testProduceAxisThrowsExceptionWhenNullConfiguration() {
		this.exception.expect(NullPointerException.class);
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");
		this.mockedProducer.produceAxis(null, mock(Query.class), "something", true, mockedCookie);
	}

	@Test
	public void testProduceAxisThrowsExceptionWhenNullCookie() {
		this.exception.expect(NullPointerException.class);
		this.mockedProducer.produceAxis(mock(Configuration.class), mock(Query.class), "something", true, null);
	}

	@Test
	public void testProduceAxisReturnsNullWhenServerNotAlive() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);

		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));

		final Command members = Command.createRootContainer().addChildContainer("Members", Constants.OPERATION_EXPAND)
				.addChild(Command.createCommand("Name", Constants.OPERATION_SELECT),
						Command.createCommand("Level", null), Command.createCommand("Attributes", null),
						Command.createCommand("UniqueName", null));

		final Command tuples = Command.createRootContainer().addChildContainer(Constants.TUPLES_COMMAND, null);
		tuples.addChild(members);

		final Command mockedAxis2Command = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);

		mockedAxis2Command.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);
		mockedAxis2Command.addChild(tuples);
		when(mockedCommand.getAxes())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND));

		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		final Query mockedQuery = mock(Query.class);
		when(mockedConfiguration.getMdx()).thenReturn(mockedQuery);
		when(mockedQuery.generateMDX()).thenReturn("mocked");
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");

		assertNull(this.mockedProducer.produceAxis(mockedConfiguration, mock(Query.class), "something", true,
				mockedCookie));
	}

	@Test
	public void testProduceAxisReturnsNullWhenServerAliveButNoCellsetId() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);

		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));

		final Command members = Command.createRootContainer().addChildContainer("Members", Constants.OPERATION_EXPAND)
				.addChild(Command.createCommand("Name", Constants.OPERATION_SELECT),
						Command.createCommand("Level", null), Command.createCommand("Attributes", null),
						Command.createCommand("UniqueName", null));

		final Command tuples = Command.createRootContainer().addChildContainer(Constants.TUPLES_COMMAND, null);
		tuples.addChild(members);

		final Command mockedAxis2Command = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);

		mockedAxis2Command.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);
		mockedAxis2Command.addChild(tuples);
		when(mockedCommand.getAxes())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND));

		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		final Query mockedQuery = mock(Query.class);
		when(mockedConfiguration.getMdx()).thenReturn(mockedQuery);
		when(mockedQuery.generateMDX()).thenReturn("mocked");
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");

		assertNull(this.mockedProducer.produceAxis(mockedConfiguration, mock(Query.class), "something", true,
				mockedCookie));
		MockServerTest.stopServer();
	}

	@Test
	public void testProduceAxisReturnsDataWhenServerAliveButNoCellsetId() {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);

		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));

		final Command members = Command.createRootContainer().addChildContainer("Members", Constants.OPERATION_EXPAND)
				.addChild(Command.createCommand("Name", Constants.OPERATION_SELECT),
						Command.createCommand("Level", null), Command.createCommand("Attributes", null),
						Command.createCommand("UniqueName", null));

		final Command tuples = Command.createRootContainer().addChildContainer(Constants.TUPLES_COMMAND, null);
		tuples.addChild(members);

		final Command mockedAxis2Command = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);

		mockedAxis2Command.addChildContainer(Constants.HIERARCHIES_COMMAND, Constants.OPERATION_EXPAND)
				.addChildContainer("Name", Constants.OPERATION_SELECT);
		mockedAxis2Command.addChild(tuples);
		when(mockedCommand.getAxes()).thenReturn(mockedAxis2Command);

		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
		final Query mockedQuery = mock(Query.class);
		when(mockedConfiguration.getMdx()).thenReturn(mockedQuery);
		when(mockedQuery.generateMDX()).thenReturn("mocked");

		final DynamicColumnsSettings mockedSettings = mock(DynamicColumnsSettings.class);
		final Map<String, String> mockedHeaderDefaults = new HashMap<>();
		mockedHeaderDefaults.put("marryChildren", "true");

		final ColumnDef mockedChildrendefaults = new ColumnDef();
		mockedChildrendefaults.setWidth(110);
		mockedChildrendefaults.setEditable(true);
		mockedChildrendefaults.setColumnGroupShow("open");
		mockedChildrendefaults.setAggFunc("sum");
		mockedChildrendefaults.setType(new String[] { "TM1DataColumnInteger", "numericColumn" });
		mockedChildrendefaults.setHide(false);

		final Map<String, ColumnDef> mockedChildrenCustomTypes = new HashMap<>();
		final ColumnDef mockedVenduto = new ColumnDef();
		mockedVenduto.setColumnGroupShow("always");
		mockedVenduto.setType(new String[] { "TM1DataColumnNumberK", "numericColumn" });
		mockedChildrenCustomTypes.put("VENDUTO_PROMO_NETTO", mockedVenduto);
		final ColumnDef mockedLordo = new ColumnDef();
		mockedLordo.setColumnGroupShow("always");
		mockedLordo.setType(new String[] { "TM1DataColumnNumber", "numericColumn" });
		mockedChildrenCustomTypes.put("MARGINE_LORDO__perc_", mockedLordo);

		when(mockedConfiguration.getDynamicColumnsSettings()).thenReturn(mockedSettings);

		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer();
		}
		final Cookie mockedCookie = new BasicClientCookie("mocked cookie", "mocked");

		assertNotNull(this.mockedProducer.produceAxis(mockedConfiguration, null, null, false, mockedCookie));
		MockServerTest.stopServer();
	}

	@Test
	public void testProducePicklistWhenCellHasPicklistReturnsList() {
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		String body = "{\n" + "    '@odata.context': '$metadata#Cellsets(Cells(HasPicklist,PicklistValues))/$entity',\n"
				+ "    'ID': '0WL7YSfZAYAOAAAg',\n" + "    'Cells': [\n" + "        {\n"
				+ "            '@odata.id': 'Cellsets(^0WL7YSfZAYAOAAAg^)/Cells(1)',\n"
				+ "            'HasPicklist': true,\n" + "            'PicklistValues': [\n" + "                'B',\n"
				+ "                'F'\n" + "            ]\n" + "        }\n" + "    ]\n" + "}";
		body = body.replace("\'", "\"").replaceAll("\n", "").replace("^", "\'");
		client.when(new HttpRequest().withPath("/api/v1/ExecuteMDX").withQueryStringParameter(
				"$expand=Cells($select=HasPicklist,PicklistValues),Cells($filter=Ordinal eq  1))"))
				.respond(new HttpResponse().withStatusCode(200).withHeader(new Header("Set-Cookie", "TM1Cookie pippo"))
						.withBody(body));

		final List<String> picklist = this.mockedProducer.producePicklist(mockedConfiguration, mockedQuery, 1, 5d, 5, 5,
				mockedCookie);

		assertNotNull(picklist);
		assertThat(picklist.size(), equalTo(2));
		assertThat(picklist.get(0), equalTo("B"));
		assertThat(picklist.get(1), equalTo("F"));

		if (MockServerTest.isRunning()) {
			MockServerTest.stopServer();
		}
	}

	@Test
	public void testProducePicklistWhenCellHasNoPicklistReturnsEmptyList() {
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		String body = "{\n" + "    '@odata.context': '$metadata#Cellsets(Cells(HasPicklist,PicklistValues))/$entity',\n"
				+ "    'ID': '0WL7YSfZAYAOAAAg',\n" + "    'Cells': [\n" + "        {\n"
				+ "            '@odata.id': 'Cellsets(^0WL7YSfZAYAOAAAg^)/Cells(1)',\n"
				+ "            'HasPicklist': false\n" + "        }\n" + "    ]\n" + "}";
		body = body.replace("\'", "\"").replaceAll("\n", "").replace("^", "\'");
		client.when(new HttpRequest().withPath("/api/v1/ExecuteMDX").withQueryStringParameter(
				"$expand=Cells($select=HasPicklist,PicklistValues),Cells($filter=Ordinal eq  1))"))
				.respond(new HttpResponse().withStatusCode(200).withHeader(new Header("Set-Cookie", "TM1Cookie pippo"))
						.withBody(body));

		final List<String> picklist = this.mockedProducer.producePicklist(mockedConfiguration, mockedQuery, 1, 5d, 5, 5,
				mockedCookie);

		assertNotNull(picklist);
		assertThat(picklist.size(), equalTo(0));

		if (MockServerTest.isRunning()) {
			MockServerTest.stopServer();
		}
	}

	@Test(expected = NullPointerException.class)
	public void producePicklist_givenNullConfiguration_shouldThrowException() {
		mockedProducer.producePicklist(null, mockedQuery, 1, 5d, 5, 5, mockedCookie);
	}

	@Test(expected = NullPointerException.class)
	public void producePicklist_givenNullCellOrdinal_shouldThrowException() {
		mockedProducer.producePicklist(mockedConfiguration, mockedQuery, null, 5d, 5, 5, mockedCookie);
	}

	@Test
	public void producePicklist_givenNullQuery_shouldRetrieveFromConfiguration() {
		// Arrange
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		when(mockedConfiguration.getMdx()).thenReturn(mockedQuery);
		when(mockedConfiguration.getDynamicColumns()).thenReturn(true);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}
		// Act
		final List<String> picklist = mockedProducer.producePicklist(mockedConfiguration, null,
				1, 5d, 5, 5, mockedCookie);
		// Assert
		verify(mockedConfiguration, times(1)).getMdx();
		assertNotNull(picklist);
		// Cleanup
		if (MockServerTest.isRunning()) {
			MockServerTest.stopServer();
		}
	}

	@Test
	public void producePicklist_whenClientThrowException_shouldReturnEmptyList() {
		// Arrange
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		when(mockedConfiguration.getMdx()).thenReturn(mockedQuery);
		when(mockedConfiguration.getDynamicColumns()).thenReturn(true);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}
		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		client.when(new HttpRequest().withPath("/api/v1/ExecuteMDX").withQueryStringParameter(
				"$expand=Cells($select=HasPicklist,PicklistValues),Cells($filter=Ordinal eq  1))"))
				.error(new HttpError().withDropConnection(true));
		// Act
		final List<String> picklist = mockedProducer.producePicklist(mockedConfiguration, null,
				1, 5d, 5, 5, mockedCookie);
		// Assert
		verify(mockedConfiguration, times(1)).getMdx();
		assertNotNull(picklist);
		assertTrue(picklist.isEmpty());
		// Cleanup
		if (MockServerTest.isRunning()) {
			MockServerTest.stopServer();
		}
	}

	@Test
	public void producePicklist_whenTm1ReadMoreThanOneCells_shouldReturnEmptyList() {
		String body = "{\n" + "    '@odata.context': '$metadata#Cellsets(Cells(HasPicklist,PicklistValues))/$entity',\n"
				+ "    'ID': '0WL7YSfZAYAOAAAg',\n"
				+ "    'Cells': [\n"
				+ "        {\n"
				+ "            '@odata.id': 'Cellsets(^0WL7YSfZAYAOAAAg^)/Cells(1)',\n"
				+ "            'HasPicklist': true\n"
				+ "        },\n"
				+ "        {\n"
				+ "            '@odata.id': 'Cellsets(^0WL7YSfZAYAOAAAg^)/Cells(2)',\n"
				+ "            'HasPicklist': true\n"
				+ "        }\n"
				+ "    ]\n" + "}";
		assertInvalidPicklistRetrieved(body);
	}

	@Test
	public void producePicklist_whenTm1ReadInvalidPicklistCells_shouldReturnEmptyList() {
		String body = "{\n" + "    '@odata.context': '$metadata#Cellsets(Cells(HasPicklist,PicklistValues))/$entity',\n"
				+ "    'ID': '0WL7YSfZAYAOAAAg',\n" + "    'Cells': [\n" + "        {\n"
				+ "            '@odata.id': 'Cellsets(^0WL7YSfZAYAOAAAg^)/Cells(1)',\n"
				+ "            'HasPicklist': true\n" + "        }\n" + "    ]\n" + "}";
		assertInvalidPicklistRetrieved(body);
	}

	private void assertInvalidPicklistRetrieved(String body) {
		// Arrange
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		when(mockedConfiguration.getProfile()).thenReturn(mockedProfile);
		when(mockedConfiguration.getMdx()).thenReturn(mockedQuery);
		when(mockedConfiguration.getDynamicColumns()).thenReturn(true);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}
		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		body = body.replace("\'", "\"").replaceAll("\n", "").replace("^", "\'");
		final HttpRequest request = new HttpRequest()
				.withPath("/api/v1/ExecuteMDX")
				.withQueryStringParameter("$expand=Cells($select=HasPicklist,PicklistValues),Cells($filter=Ordinal eq  1))");
		final HttpResponse response = new HttpResponse().withStatusCode(200)
				.withHeader(new Header("Set-Cookie", "TM1Cookie pippo"))
				.withBody(body);
		client.when(request).respond(response);
		// Act
		final List<String> picklist = mockedProducer.producePicklist(mockedConfiguration, null,
				1, 5d, 5, 5, mockedCookie);
		// Assert
		verify(mockedConfiguration, times(1)).getMdx();
		assertNotNull(picklist);
		assertTrue(picklist.isEmpty());
		// Cleanup
		if (MockServerTest.isRunning()) {
			MockServerTest.stopServer();
		}
	}
}
