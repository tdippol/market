package com.axiante.tm1.mdx;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.tm1.mdx.objects.Dimension;
import com.axiante.tm1.mdx.objects.Dimension.Type;
import com.axiante.tm1.mdx.objects.DimensionDependentObject;
import com.axiante.tm1.mdx.objects.Query;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

public class QueryParserTest {

	@Test
	public void testRead() throws IOException {
		String jsonString = 
				"{\"MDX\": {\n" + 
				"              \"ROWS\": {\n" + 
				"                  \"NON_EMPTY\": true,\n" + 
				"                  \"DIMENSIONS\": {\n" + 
				"                      \"Account\": \"{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Account] )}, 0)}, ASC)}}\"\n" + 
				"                  }\n" + 
				"              },\n" + 
				"              \"COLS\": {\n" + 
				"                  \"NON_EMPTY\": false,\n" + 
				"                  \"DIMENSIONS\": {\n" + 
				"                      \"Account Set Up\": \"{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Account Set Up] )}, 0)}, ASC)}}\"\n" + 
				"                  }\n" + 
				"              },\n" + 
				"              \"FROM\": \"[Account Set Up]\",\n" + 
				"              \"WHERE\": {\n" + 
				"                        \"Legal Entity\":\"@@ElementSelector:LegalEntity=[E134]@@\"\n" + 
				"                        }\n" + 
				"          }}";
		QueryParser p = new QueryParser();
		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(jsonString);
		p.read(parser);
		assertNotNull(p.getQuery());
		assertNotNull(p.getQuery().getRows());
		assertThat(p.getQuery().getRows().isNonEmpty(), CoreMatchers.equalTo(Boolean.TRUE));
		assertThat(p.getQuery().getRows().getDimensions().size(), CoreMatchers.equalTo(1));
		assertThat(p.getQuery().getRows().getDimensions().get(0).getColumn(), CoreMatchers.equalTo("Account"));
		assertThat(p.getQuery().getRows().getDimensions().get(0).getValue(), CoreMatchers.containsString("TM1SUBSETALL"));
		
		parser.close();
		
	}
	
	@Test
	public void testGetOriginalQueryReturnsNull() {
		assertNull(new QueryParser().getOriginalQuery());
	}
	
	@Test public void testReadWrongJsonReturnEmptyQuery() throws JsonParseException, IOException {
		QueryParser p = new QueryParser();
		@SuppressWarnings("resource")
		JsonParser parser = mock(JsonParser.class);
		when(parser.nextToken()).thenReturn(JsonToken.END_ARRAY);
		Query q = p.read(parser);
		assertNotNull(q);
		assertTrue(q.getWhere().getDimensions().size() == 0 );
	}
	
	@Test public void testReadEntryReturnsNull() throws JsonParseException, IOException {
		QueryParser p = new QueryParser();
		@SuppressWarnings("resource")
		JsonParser parser = mock(JsonParser.class);
		when(parser.nextToken()).thenReturn(JsonToken.END_ARRAY);
		
		assertNull(p.readEntry(mock(Query.class), parser, Type.COLUMNS));
	}
	
	@Test public void testReadDimensionsQuitsWhenWrongToken() throws JsonParseException, IOException {
		QueryParser p = new QueryParser();
		@SuppressWarnings("resource")
		JsonParser parser = mock(JsonParser.class);
		when(parser.nextToken()).thenReturn(JsonToken.END_ARRAY);
		DimensionDependentObject test = mock(DimensionDependentObject.class);
		p.readDimensions(test, parser, Type.COLUMNS);
		verify(test, times(0)).addDimension(ArgumentMatchers.any(Dimension.class));
	}
	
	@Test(expected = NullPointerException.class)
	public void testReadDimensionThrowsExceptionWhenNullDimension() throws IOException {
		QueryParser p = new QueryParser();
		p.readDimensions(null, mock(JsonParser.class), Type.COLUMNS);
	}
	@Test(expected = NullPointerException.class)
	public void testReadDimensionThrowsExceptionWhenNullParser() throws IOException {
		QueryParser p = new QueryParser();
		p.readDimensions(mock(DimensionDependentObject.class), null, Type.COLUMNS);
	}	
}

