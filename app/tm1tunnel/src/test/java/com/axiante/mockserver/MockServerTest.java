package com.axiante.mockserver;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import com.axiante.connection.AuthType;
import com.axiante.connection.ConnectionProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

@Slf4j
public class MockServerTest {
    private static ClientAndServer mockServer;
    public static final int DEFAULT_PORT=8882;
    public static void createExpectationFromFile(){
        MockServerTest.class.getClassLoader().getResourceAsStream("mockserver/expectations.json");
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final Expectations[] expectations = objectMapper.readValue(MockServerTest.class.getClassLoader().getResourceAsStream("mockserver/expectations.json"),
                    Expectations[].class);
            for(final Expectations expectation:expectations){
                final HttpRequest req = new HttpRequest();
                req.withMethod(expectation.getRequest().getType());
                req.withPath(expectation.getRequest().getPath());
                req.withBody(expectation.getRequest().getBody());
                if((expectation.getRequest().getQuery() != null) && 
                        (expectation.getRequest().getQuery().size() > 0) ) {
                    expectation.getRequest().getQuery().forEach(q->{
                        req.withQueryStringParameter(q.getName(),q.getValue());	
                    });
                }



                final HttpResponse res = new HttpResponse();
                res.withStatusCode(expectation.getResponse().getStatus());
                if ( expectation.getResponse().getHeaders() != null ) {
                    expectation.getResponse().getHeaders().forEach(h->{
                        res.withHeader(h.getName(), h.getValue());
                    });
                }
                if ((expectation.getResponse().getPathToBody() != null) && (expectation.getResponse().getPathToBody().length() > 0) ) {
                    try(
                            InputStream is = MockServerTest.class.getClassLoader().getResourceAsStream("mockserver/"+expectation.getResponse().getPathToBody());
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))
                            ){
                        res.withBody(
                                bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()))
                                );
                    }catch (NullPointerException | IOException ex){
                        log.error("body file missing for "+expectation.getName());
                        return;
                    }
                }
                createExpectation(req,res);
            }
        } catch (final IOException e) {
            log.error("error creating expectations", e);
        }
    }
    public static void startServer(){
        startServer(DEFAULT_PORT);
    }
    public static void startServer(final Integer port){
        startServer(port, true);
    }
    public static void startServer(final Integer port, final boolean withDefaultExpectations){
        mockServer = startClientAndServer(port);
        if ( withDefaultExpectations ) {
            createExpectationFromFile();
        }
    }

    public static void stopServer(){
        mockServer.stop();
    }
    public static boolean isRunning() {
        return (mockServer != null) && mockServer.isRunning();
    }
    public static void createExpectation(final HttpRequest request, final HttpResponse response) {
        new MockServerClient("127.0.0.1", mockServer.getPort())
        .when(
                request
                )
        .respond(
                response
                );
    }

    public static ConnectionProfile createConnectionProfile(final AuthType type, final boolean validateSSL) {
        final ConnectionProfile profile = new ConnectionProfile();
        profile.setUsername("TMoneS04");
        profile.setPassword("waGi5lphAWOyed");
        profile.setHost("http://127.0.0.1");
        profile.setPort(":"+DEFAULT_PORT);
        profile.setPath("/api/v1/");
        profile.setDomain("MIL");
        profile.setAuthType(type);
        profile.setValidatessl(validateSSL);
        return profile;
    }

    public static ConnectionProfile createConnectionProfile(final AuthType type, final String server, final boolean validateSSL) {
        final ConnectionProfile profile = new ConnectionProfile();
        profile.setUsername("TMoneS04");
        profile.setPassword("waGi5lphAWOyed");
        profile.setHost("http://127.0.0.1");
        profile.setPort(":"+DEFAULT_PORT);
        profile.setPath("/api/v1/");
        profile.setDomain("MIL");
        profile.setAuthType(type);
        profile.setValidatessl(validateSSL);
        return profile;
    }

    public static void reset() {
        mockServer.reset();
    }
}
