package com.axiante.mui.business.reader.dinamicColumns;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.Tm1Tunnel;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.backing.ConnectionCatalog;
import com.axiante.mui.business.reader.ConfigurationReader;
import com.axiante.tm1.json.Tm1JsonReader;
import com.axiante.tm1.json.objects.Table;
import com.axiante.tm1.mdx.Command;
import com.axiante.utility.configuration.ColumnDef;
import com.axiante.utility.configuration.Configuration;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class DinamicConfigurationProducerTest2 {
    //    private static final int PORT = 1080;
    //    private static final String server = "127.0.0.1";

    @DataPoint
    public static ConnectionProfile esselungaProfile = Mockito.mock(ConnectionProfile.class);
    @DataPoint
    public static ConnectionProfile hqProfile = Mockito.mock(ConnectionProfile.class);
    @DataPoint
    public static ConnectionProfile localProfile = Mockito.mock(ConnectionProfile.class);

    @Mock
    ConnectionCatalog catalog;

    @InjectMocks
    ConfigurationReader creader ;

    @Before
    public void setupMocks() throws JsonParseException, JsonMappingException, IOException {

    }

    Configuration c = null;

    private Table getTestData() {

        if (getAddress(localProfile) == null) {
            final Table t = new Table();
            t.setError(true);
            return t;
        }

        try (FileInputStream in = new FileInputStream(setDinamicConfig())) {
            //			creader = creader.read(in);
            final StringBuilder resultStringBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }
            creader = creader.read(resultStringBuilder.toString());
        } catch (final Exception e) {
            fail("error reading configuration");
        }
        if (creader == null) {
            return null;
        }
        c = creader.getConfiguration("local_vm_dinamic");

        Table table = null;
        final Command command = c.getCommand();
        final String MDX_QUERY = c.getMdx().generateMDX();
        CloseableHttpResponse response = null;
        String ID = null;
        JsonParser parser = null;
        try (final Tm1Tunnel iface = new Tm1Tunnel(localProfile)) {
            Command _command = command.getCube();
            final StringBuffer uriRequest = new StringBuffer();
            String commandString = _command.generateRequest(uriRequest).toString();
            response = iface.executeMDXasHttpResponse(commandString, MDX_QUERY, false, null, null, null, null);

            final JsonFactory factory = new JsonFactory();
            parser = factory.createParser(response.getEntity().getContent());
            JsonToken jsonToken = parser.getCurrentToken();
            while (jsonToken != JsonToken.END_OBJECT) {
                if ((JsonToken.FIELD_NAME == jsonToken) && "ID".equals(parser.getCurrentName())) {
                    log.debug("found id");
                    parser.nextToken();
                    ID = parser.getText();
                }
                jsonToken = parser.nextToken();
            }

            assertNotNull(ID);
            if (response != null) {
                response.close();
            }
            // now we have the cube, read the axes
            _command = command.getAxes();
            assertNotNull(_command);
            uriRequest.setLength(0);
            commandString = _command.generateRequestForCellset(uriRequest, ID).toString();
            log.debug(commandString);
            response.close();
            response = iface.executeRequestAsHttpResponse("GET", commandString, null);
            final Tm1JsonReader reader = new Tm1JsonReader();
            table = reader.readAxes(response.getEntity().getContent());

            if ((ID != null) ) {
                log.debug("deleting named query " + ID);
                response.close();
                response = iface.executeRequestAsHttpResponse("DELETE", "Cellsets('" + ID + "')", null);
            }

        } catch (final Exception e) {
            log.error("error ", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (final IOException e) {
                    log.error("Error closing response");
                }
            }
            if (parser != null) {
                try {
                    parser.close();
                } catch (final IOException e) {
                    log.error("Error closing parser");
                }
            }
        }
        return table;
    }

    private String getAddress(final ConnectionProfile profile) {
        try {
            if (InetAddress.getByName(new URL(profile.getHost()).getHost()).isReachable(3000)) {
                return "hello!";
            }
        } catch (final IOException e) {
            log.debug("destination host unreachable");
        }
        return null;
    }

    private File setDinamicConfig() throws IOException, URISyntaxException {
        final URL url = getClass().getClassLoader().getResource("mui_config");
        File f = null;
        if (url == null) {
            log.error("could not find " + "config");
        } else {
            try (Stream<Path> paths = Files.walk(Paths.get(url.toURI()))) {
                final Path p = paths.filter(Files::isRegularFile).filter(s -> s.toString().endsWith(".json"))
                        .filter(s -> s.getFileName().toString().startsWith("dinamic")).findAny().orElse(null);
                if (p != null) {
                    f = p.toFile();
                }
            }
        }
        return f;
    }

    @Test
    public void test() {

        final Table testData = getTestData();
        if (testData == null) {
            Assert.fail("unable to test with empty data set ");
        }
        if (testData.isError()) {
            Assert.assertTrue("Unable to reliably run test as the server is not responding", true);
            return;
        }
        assertNotNull(testData.getHeaders());
        final DinamicConfigurationProducer producer = new DinamicConfigurationProducer();
        final ColumnDef tree = producer.getDinamicConfigurationTree(testData, c.getDynamicColumnsSettings());
        assertNotNull(tree);
        assertThat(tree.getChildren().size(), CoreMatchers.equalTo(2));
        assertNotNull(tree.getChildren().get("Countries_Currency$China"));
        assertThat(tree.getChildren().get("Countries_Currency$China").getChildren().size(), CoreMatchers.equalTo(12));
        producer.generateConfiguration(tree, 0, c.getDynamicColumnsSettings());
    }

}
