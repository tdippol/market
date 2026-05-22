package com.axiante.tm1.mdx.parser;

import static org.junit.Assert.assertThat;

import com.axiante.tm1.mdx.QueryParser;
import com.axiante.tm1.mdx.objects.Query;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class QueryTest {

    @Test
    public void testMdxGeneration() throws IOException {
        final String jsonString = "{\"MDX\": {\n" + "              \"ROWS\": {\n" + "                  \"NON_EMPTY\": true,\n"
                + "                  \"DIMENSIONS\": {\n"
                + "                      \"Account\": \"{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Account] )}, 0)}, ASC)}}\"\n"
                + "                  }\n" + "              },\n" + "              \"COLS\": {\n"
                + "                  \"NON_EMPTY\": true,\n" + "                  \"DIMENSIONS\": {\n"
                + "                      \"Account Set Up\": \"{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Account Set Up] )}, 0)}, ASC)}}\"\n"
                + "                  }\n" + "              },\n" + "              \"FROM\": \"[Account Set Up]\",\n"
                + "              \"WHERE\": {\n"
                + "                        \"Legal Entity\":\"[Legal Entity].[E134]\"\n" + "                        }\n"
                + "          }}";
        final QueryParser p = new QueryParser();
        final JsonFactory factory = new JsonFactory();
        try (JsonParser parser = factory.createParser(jsonString);) {
            p.read(parser);
            final Query q = p.getQuery();
            final String s = q.generateMDX();
            final String test = "SELECT NON EMPTY {{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Account] )}, 0)}, ASC)}}}} ON ROWS , NON EMPTY {{{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Account Set Up] )}, 0)}, ASC)}}}}} ON COLUMNS FROM [Account Set Up] WHERE ([Legal Entity].[E134])";

            assertThat(s, CoreMatchers.equalTo(test));
        }
    }
}
