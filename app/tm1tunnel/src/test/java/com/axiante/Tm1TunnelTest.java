package com.axiante;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.connection.AuthType;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mockserver.MockServerTest;
import com.axiante.tm1.TunnelConstants;
import com.axiante.tm1.mdx.Command;
import com.axiante.utility.Constants;
import com.axiante.utility.configuration.ColumnDef;
import com.axiante.utility.configuration.Configuration;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.NotThreadSafe;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

@NotThreadSafe
@RunWith(MockitoJUnitRunner.class)
public class Tm1TunnelTest {
	public static final ConnectionProfile mockServerProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE,
			false);

	@Test
	public void testConstructorWithNullProfile() {
		try (Tm1Tunnel t = new Tm1Tunnel(null);) {
			assertNotNull(t);
		}
	}

	@Test
	public void testConstructorWithProfile() {
		final ConnectionProfile profile = mock(ConnectionProfile.class);
		try (Tm1Tunnel t = new Tm1Tunnel(profile);) {
			assertNotNull(t);
		}
	}

	@Test
	public void getVersion() throws ClientProtocolException, IOException {
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}
		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		client.when(new HttpRequest().withMethod("GET").withPath("/api/v1/Configuration/ProductVersion/$value"))
				.respond(new HttpResponse().withStatusCode(200).withBody("'10.2.20606.115'".replace("\'", "\""))
						.withCookie(org.mockserver.model.Cookie.cookie("cookie", "cookie")));

		try (Tm1Tunnel iface = new Tm1Tunnel(mockServerProfile)) {
			assertEquals(200, iface.getVersion(null, null, null, null).getStatusLine().getStatusCode());
		} finally {
			MockServerTest.stopServer();
		}
	}

	@Test
	public void testExecuteMdxAsHttpResponse() throws ClientProtocolException, IOException {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.PASSPORT, false);
		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);
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

		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}
		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		client.when(new HttpRequest().withMethod("POST").withPath("/api/v1/" + TunnelConstants.ExecuteMDX)
				.withQueryStringParameter("$expand", "Cube"))

				.respond(new HttpResponse().withStatusCode(200)
						.withBody("{'@odata.context': '$metadata#Cellsets(Cube)/$entity'," + "'ID': 'stVqWl4-AYA4AAAg'"
								+ "}".replace("\'", "\""), Charset.forName("UTF-8")));

		try (Tm1Tunnel iface = new Tm1Tunnel(mockedProfile)) {
			final Command command = mockedConfiguration.getCommand();
			final Command _command = command.getCube();
			final StringBuffer uriRequest = new StringBuffer();
			final String commandString = _command.generateRequest(uriRequest).toString();
			final Cookie cookie = new BasicClientCookie("cookie", "cookie");
			assertNotNull(iface.executeMDXasHttpResponse(commandString, "SOME MOCKED MDX", false, 0d, 0, 0, cookie));
		}

		MockServerTest.stopServer();
	}

	@Test
	public void testExecuteMdx() throws ClientProtocolException, IOException {
		final Configuration mockedConfiguration = mock(Configuration.class);
		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.PASSPORT, false);
		final Command mockedCommand = mock(Command.class);
		when(mockedCommand.getCube())
				.thenReturn(Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND));
		when(mockedConfiguration.getCommand()).thenReturn(mockedCommand);

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

		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}
		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		client.when(new HttpRequest().withMethod("POST").withPath("/api/v1/" + TunnelConstants.ExecuteMDX)
				.withQueryStringParameter("$expand", "Cube"))

				.respond(new HttpResponse().withStatusCode(200)
						.withBody("{'@odata.context': '$metadata#Cellsets(Cube)/$entity'," + "'ID': 'stVqWl4-AYA4AAAg'"
								+ "}".replace("\'", "\""), Charset.forName("UTF-8")));

		try (Tm1Tunnel iface = new Tm1Tunnel(mockedProfile)) {
			final Command command = mockedConfiguration.getCommand();
			final Command _command = command.getCube();
			final StringBuffer uriRequest = new StringBuffer();
			final String commandString = _command.generateRequest(uriRequest).toString();
			final Cookie cookie = new BasicClientCookie("cookie", "cookie");
			assertNotNull(iface.executeMDX(commandString, "SOME MOCKED MDX", "", 0d, null, null, cookie));

		}

		MockServerTest.stopServer();
	}

	@Test
	public void testDeleteById() throws ClientProtocolException, URISyntaxException, IOException {
		final String deleteCommand = "mock_collection('123')";
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}
		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		client.when(new HttpRequest().withMethod("DELETE").withPath("/api/v1/" + deleteCommand))
				.respond(new HttpResponse().withStatusCode(200));
		try (Tm1Tunnel iface = new Tm1Tunnel(MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false))) {
			final Cookie cookie = new BasicClientCookie("cookie", "cookie");
			assertThat(iface.deleteById("mock_collection", "123", "", 0d, cookie), equalTo(TunnelConstants.SUCCESS));
		}

		MockServerTest.stopServer();
	}

	@Test
	public void testCellPut() {
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		final String cube = "mocked_cube";
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}
		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		client.when(new HttpRequest().withMethod("POST").withPath("/api/v1/Cubes('" + cube + "')/tm1.Update")
				.withBody("{'Cells':[{'Tuple@odata.bind':['tuple1']}],'Value':'value1'}".replace("\'", "\"")))

				.respond(new HttpResponse().withStatusCode(200)
						.withBody("{'@odata.context': '$metadata#Cellsets(Cube)/$entity'," + "'ID': 'stVqWl4-AYA4AAAg'"
								+ "}".replace("\'", "\""), Charset.forName("UTF-8")));

		try (Tm1Tunnel iface = new Tm1Tunnel(profile)) {
			final Cookie cookie = new BasicClientCookie("cookie", "cookie");
			assertNotNull(iface.cellPut(cube, Arrays.asList(new String[] { "tuple1" }), "value1", 0d, 0, 0, cookie));
		}

		MockServerTest.stopServer();

	}

	@Test
	public void testExecuteProcess() {
		final Map<String, String> parameters = new HashMap<>();
		parameters.put("mock_key", "mock_value");
		final String processName = "mocked_process";
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		client.when(new HttpRequest().withMethod("POST")
				.withPath("/api/v1/Processes('" + processName + "')/tm1.Execute")
				.withBody("{'Parameters' :[{'Name' : 'mock_key', 'Value' : 'mock_value'}]}".replace("\'", "\"")))

				.respond(new HttpResponse().withStatusCode(200)
						.withBody("{'@odata.context': '$metadata#Cellsets(Cube)/$entity'," + "'ID': 'stVqWl4-AYA4AAAg'"
								+ "}".replace("\'", "\""), Charset.forName("UTF-8")));
		try (Tm1Tunnel iface = new Tm1Tunnel(profile)) {
			final Cookie cookie = new BasicClientCookie("cookie", "cookie");
			assertNotNull(iface.executeProcess(processName, parameters, 0d, 0, 0, cookie));
		}
		MockServerTest.stopServer();

	}

	@Test
	public void testExecuteProcessWithResponseReturnsResponse() {
		final Map<String, String> parameters = new HashMap<>();
		parameters.put("mock_key", "mock_value");
		final String processName = "mocked_process";
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);

		client.when(new HttpRequest().withMethod("GET").withPath("/api/v1/Configuration/ProductVersion/$value"))

				.respond(new HttpResponse().withStatusCode(200).withBody("11.7.00000.42"));

		client.when(new HttpRequest().withMethod("POST")
				.withPath("/api/v1/Processes('" + processName + "')/tm1.ExecuteWithReturn")
				.withBody("{'Parameters' :[{'Name' : 'mock_key', 'Value' : 'mock_value'}]}".replace("\'", "\"")))

				.respond(new HttpResponse().withStatusCode(201).withBody(
						"{'@odata.context':'../$metadata#ibm.tm1.api.v1.ProcessExecuteResult','ProcessExecuteStatusCode':'CompletedSuccessfully'}"
								.replace("\'", "\""),
						Charset.forName("UTF-8")));
		try (Tm1Tunnel iface = new Tm1Tunnel(profile)) {
			final Cookie cookie = new BasicClientCookie("cookie", "cookie");
			assertNotNull(iface.executeProcess(processName, parameters, 0d, 0, 0, cookie));
		}
		MockServerTest.stopServer();

	}

	@Test
	public void testExecuteProcessWithResponseReturnsSuccessWhenServerRepondsCompletedSuccessfully()
			throws IOException {
		final Map<String, String> parameters = new HashMap<>();
		parameters.put("mock_key", "mock_value");
		final String processName = "mocked_process";
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);

		client.when(new HttpRequest().withMethod("GET").withPath("/api/v1/Configuration/ProductVersion/$value"))

				.respond(new HttpResponse().withStatusCode(200).withBody("11.7.00000.42"));

		client.when(new HttpRequest().withMethod("POST")
				.withPath("/api/v1/Processes('" + processName + "')/tm1.ExecuteWithReturn")
				.withBody("{'Parameters' :[{'Name' : 'mock_key', 'Value' : 'mock_value'}]}".replace("\'", "\"")))

				.respond(new HttpResponse().withStatusCode(201).withBody(
						"{'@odata.context':'../$metadata#ibm.tm1.api.v1.ProcessExecuteResult','ProcessExecuteStatusCode':'CompletedSuccessfully'}"
								.replace("\'", "\""),
						Charset.forName("UTF-8")));
		try (Tm1Tunnel iface = new Tm1Tunnel(profile)) {
			final Cookie cookie = new BasicClientCookie("cookie", "cookie");
			try (final CloseableHttpResponse response = iface.executeProcess(processName, parameters, 0d, 0, 0,
					cookie);) {
				assertNotNull(response);
				assertThat(response.getStatusLine().getStatusCode(), equalTo(201));
			}
		}
		MockServerTest.stopServer();
	}

	@Test
	public void testExecuteProcessWithResponseReturnsFailWhenServerNotRepondsCompletedSuccessfully()
			throws IOException {
		final Map<String, String> parameters = new HashMap<>();
		parameters.put("mock_key", "mock_value");
		final String processName = "mocked_process";
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);

		client.when(new HttpRequest().withMethod("GET").withPath("/api/v1/Configuration/ProductVersion/$value"))

				.respond(new HttpResponse().withStatusCode(200).withBody("11.7.00000.42"));

		final String[] failingStatuse = { "Aborted", "QuitCalled", "CompletedWithMessages", "AnythingElse" };
		for (final String failStatus : failingStatuse) {
			client.when(new HttpRequest().withMethod("POST")
					.withPath("/api/v1/Processes('" + processName + "')/tm1.ExecuteWithReturn")
					.withBody("{'Parameters' :[{'Name' : 'mock_key', 'Value' : 'mock_value'}]}".replace("\'", "\"")))

					.respond(new HttpResponse().withStatusCode(201).withBody(
							"{'@odata.context':'../$metadata#ibm.tm1.api.v1.ProcessExecuteResult','ProcessExecuteStatusCode':'"
									+ failStatus + "'}".replace("\'", "\""),
							Charset.forName("UTF-8")));
			try (Tm1Tunnel iface = new Tm1Tunnel(profile)) {
				final Cookie cookie = new BasicClientCookie("cookie", "cookie");
				try (final CloseableHttpResponse response = iface.executeProcess(processName, parameters, 0d, 0, 0,
						cookie);) {
					assertNotNull(response);
					assertThat(
							"failing for status code " + failStatus + ": expected 500 found "
									+ response.getStatusLine().getStatusCode(),
							response.getStatusLine().getStatusCode(), equalTo(500));
				}
			}

		}
		MockServerTest.stopServer();
	}

	@Test
	public void testVerify() throws ClientProtocolException, IOException {
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}
		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		client.when(new HttpRequest().withMethod("GET").withPath("/api/v1/Configuration/ProductVersion/$value"))
				.respond(new HttpResponse().withStatusCode(200).withBody("'10.2.20606.115'".replace("\'", "\""))
						.withCookie(org.mockserver.model.Cookie.cookie("cookie", "cookie")));
		try (Tm1Tunnel iface = new Tm1Tunnel(mockServerProfile)) {
			final Cookie cookie = new BasicClientCookie("cookie", "cookie");
			assertNotNull(iface.verify("mocked_passport", cookie, mockServerProfile, 0d, 0, 0));
		}
		MockServerTest.stopServer();
	}
}
