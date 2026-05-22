package com.axiante.tm1;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

@Slf4j
public class HttpTest {

    @Test
    public void testHttp() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
    ClientProtocolException, IOException {
        final CloseableHttpClient client = HttpClients.custom().setHostnameVerifier(new X509HostnameVerifier() {

            @Override
            public boolean verify(final String arg0, final SSLSession arg1) {
                log.info("verify String, SSLSession ... returning true");
                return true;
            }

            @Override
            public void verify(final String host, final String[] cns, final String[] subjectAlts) throws SSLException {
                log.info("verify String, String[] ... doing nothing");

            }

            @Override
            public void verify(final String host, final X509Certificate cert) throws SSLException {
                log.info("verification for host " + host);
                log.info("verify String, X509Certificate ... doing nothing");
            }

            @Override
            public void verify(final String host, final SSLSocket ssl) throws IOException {
                log.info("verification for host " + host);
                log.info("verify String, SSLSocket ... doing nothing");
            }
        }

                ).setSslcontext(new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> {
                    log.info("isTrusted X509Certificate[]m String  ... returning true");
                    return true;
                }).build()).build();

        String uri = "https://MILTMONE1S:15001/api/v1/";
        try {
            InetAddress.getByName(new URL("https://TMONETEST").getHost()).getHostAddress();
        } catch (UnknownHostException | MalformedURLException e) {
            log.error("error determining ip address");
            client.close();
            return;
        }

        uri += TunnelConstants.ExecuteMDX + "$expand=Cube";

        final HttpPost post = new HttpPost(uri);
        post.addHeader("Connection", "keep-alive");
        post.addHeader("Content-Type", "application/json; odata.streaming=true; charset=" + Consts.UTF_8);
        post.addHeader("Accept", "application/json;odata.metadata=minimum,text/plain");
        post.addHeader("Authorization",
                "CAMPassport \"MTsxMDE6NWFlNTFjNjEtOGVjYi1iNjEwLTM4ZmMtZTNkZTc1ODJlZDAxOjMwNzE3MDc0OTA7MDszOzA7\";");
        final String mdx = "SELECT NON EMPTY {[Articolo].[ART_503]} ON ROWS , NON EMPTY {[Compratore].[S17]} ON COLUMNS FROM [Deals]";

        final ObjectMapper mapper = new ObjectMapper();

        post.setEntity(new StringEntity(mapper.writeValueAsString("MDX") + ":" + mapper.writeValueAsString(mdx)));

        try (CloseableHttpResponse response = client.execute(post);) {
            final int code = response.getStatusLine().getStatusCode();
            if ((code != 200) || (code != 201)) {
                final StringBuffer buf = new StringBuffer();
                final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    buf.append(line).append("\n");
                }

                log.error(
                        "\nerror submitting request\nserver response code:" + code + "\nserver response string:" + buf);

            } else {
                assertNotNull(response);
                assertThat(response.getStatusLine().getStatusCode(), CoreMatchers.equalTo(201));
            }
            client.close();
        }
    }

    class JsonMDX {
        String MDX;

        public JsonMDX(final String mdx) {
            MDX = mdx;
        }

    }
}
