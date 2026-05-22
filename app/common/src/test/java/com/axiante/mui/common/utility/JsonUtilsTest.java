package com.axiante.mui.common.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

@Slf4j
public class JsonUtilsTest {
	static String jsonString = "{" + "'rows': {" + "'Reparto':'REP_01'," + "'Promozione':'2019_401',"
			+ "'Versione':'UFF'" + "}," + "'columns': 'Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO',"
			+ "'oldValue': 123456," + "'newValue': 123" + "}".replace("'", "\"");

	static String jsonString2 = "'array': '[]'".replace("'", "\"");

	static String jsonString3 = "{" + "'array': ['123','456','789']" + "'array2': ['123','456','789']"
			+ "'array3': ['123','456','789']" + "'array4': ['123','456','789']" + "}".replace("'", "\"");
	static String jsonString4 = "{" + "'oldValue': 123456," + "'array': ['123','456','789']" + "'Reparto':'REP_01',"
			+ "}".replace("'", "\"");

	static String jsonString5 = "{" + "}".replace("'", "\"");

	static String row = "{'Reparto':'REP_01','Promozione':'2019_405','Versione':'UFF','testata':null}".replace("'",
			"\"");

	@BeforeClass
	public static final void prepareTest() {
		jsonString = jsonString.replace("'", "\"");
		row = row.replace("'", "\"");
		jsonString2 = jsonString2.replace("'", "\"");
		jsonString3 = jsonString3.replace("'", "\"");
		jsonString4 = jsonString4.replace("'", "\"");
		jsonString5 = jsonString5.replace("'", "\"");
	}

	private static final ObjectMapper rawmapper = new ObjectMapper();

	@Test
	public void testParseData() {
		Map<String, String> data;
		try {
			data = JsonUtils.parseData(jsonString);
			assertNotNull(data);
			assertThat(data.size(), CoreMatchers.equalTo(4));
		} catch (final IOException e) {
			assertTrue(false);
		}
	}

	@Test
	public void testParsingDataJsonParser() {
		JsonFactory factory = new JsonFactory();
		Map<String, String> data;
		Map<String, String> data2;
		try {
			JsonParser parser = factory.createParser(jsonString);
			JsonParser parser2 = factory.createParser(jsonString2);
			assertNotNull(parser);
			assertNotNull(parser2);
			data = JsonUtils.parseData(parser);
			assertNotNull(data);

			try {
				data2 = JsonUtils.parseData(parser2);
				assertNotNull(data2);
			} catch (Exception e) {
				assertNull(JsonUtils.parseData(parser2));
			}
		} catch (final IOException e) {
			assertFalse(false);
		}

	}

	@Test(expected = NullPointerException.class)
	public void throwExceptionParsingDataJsonParser() throws Exception {
		Map<String, String> data = null;
		JsonParser parser3 = null;
		assertNull(parser3);
		data = JsonUtils.parseData(parser3);
		assertNull(data);
	}

	@Test
	public void tryValidation() {
		String validatedString = null;
		String string = "test";
		ObjectMapper validationMapper = new ObjectMapper();
		try {
			validatedString = JsonUtils.validate(jsonString);
			validationMapper.readTree(jsonString);
			assertNotNull(validationMapper.readTree(jsonString));
		} catch (final IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}

		try {
			validatedString = JsonUtils.validate(string);
			assertNotNull(validatedString);
			validationMapper.readTree(string);
			assertNotNull(validationMapper.readTree(string));
		} catch (final IOException e) {
			assertFalse(false);
		}

	}

	@Test(expected = NullPointerException.class)
	public void testNullValidation() throws Exception {
		String json = null;
		assertNull(JsonUtils.validate(json));

	}

	@Test
	public void testParseDataParsesStringWithouQuotes() {
		Map<String, String> data;
		try {
			data = JsonUtils.parseData(row);
			assertNotNull(data);
			assertThat(data.size(), CoreMatchers.equalTo(4));
			assertThat(data.get("Promozione"), CoreMatchers.equalTo("2019_405"));
		} catch (final Exception e) {
			assertTrue(false);
		}
	}

	@Test(expected = NullPointerException.class)
	public void testParseDataParsesStringWithouQuotesNullException() throws Exception {
		String json = null;
		assertNull(JsonUtils.parseData(json));

	}

	@Test
	public void testParseDataElement() {
		Map<String, String> data;
		Map<String, String> data2;
		try {
			data = JsonUtils.parseData(jsonString, "rows");
			assertNotNull(data);
			//            assertThat(data.keySet().size(), CoreMatchers.equalTo(3));
			data2 = JsonUtils.parseData(jsonString, "test");
			assertNull(data2);
		} catch (final IOException e) {
			assertTrue(false);
		}
	}

	@Test(expected = NullPointerException.class)
	public void testParseDataElementNullException() throws Exception {
		String json = null;
		assertNull(JsonUtils.parseData(json, "rows"));
	}


	@Test
	public void testReadStringList() {
		JsonFactory factory = new JsonFactory();
		ArrayList<String> list = new ArrayList<>();

		try {
			JsonParser parser = factory.createParser(jsonString);
			JsonToken token = parser.nextToken();
			assertNotNull(token);
			assertNotNull(JsonUtils.readStringList(parser));
		} catch (final IOException e) {
			assertTrue(true);
		}
		try {
			JsonParser parser3 = factory.createParser(jsonString3);
			JsonToken token3 = parser3.nextToken();

			if (parser3.nextToken() == JsonToken.START_ARRAY) {
				while (parser3.nextToken() != JsonToken.END_ARRAY) {
					list.add(parser3.getValueAsString());
				}
			}
			assertNotNull(token3);
			assertNotNull(JsonUtils.readStringList(parser3));
		} catch (final IOException e) {
			assertTrue(true);
		}

	}

	@Test(expected = NullPointerException.class)
	public void readStringList_NullJsonParser_shouldThrowException()
			throws JsonParseException, JsonMappingException, IOException {
		JsonParser parser = null;
		JsonUtils.readStringList(parser);
	}

	@Test
	public void testFormat() throws IOException {
		String json = null;
		int json2 = 123;

		assertNotNull(jsonString);
		assertNotNull(JsonUtils.format(jsonString));

		JsonUtils.format(json);

		assertNotNull(json2);
		JsonUtils.format(json2);

	}

	@Test
	public void testWriteValuesAsString() {
		try {
			assertNotNull(jsonString);
			assertNotNull(JsonUtils.writeValueAsString(jsonString));
		} catch (final IOException e) {
			assertTrue(false);
		}
	}

	@Test(expected = NullPointerException.class)
	public void nullPointerExceptionWriteValuesAsString() {
		try {
			String jsonStr = null;
			assertNull(JsonUtils.writeValueAsString(jsonStr));
		} catch (final IOException e) {
			assertTrue(true);
		}
	}

	@Test
	public void tryToReadInt() {
		JsonFactory factory = new JsonFactory();
		try (JsonParser parser = factory.createParser("123")) {
			assertNotNull(parser);
			assertNotNull(JsonUtils.readInt(parser));
		} catch (final IOException e) {
			assertTrue(true);
		}
	}

	@Test(expected = NullPointerException.class)
	public void tryToReadNullInt() {
		try {
			JsonParser parser = null;
			assertNull(JsonUtils.readInt(parser));
		} catch (final IOException e) {
			assertTrue(true);
		}
	}

	@Test
	public void tryToReadLong() {
		JsonFactory factory = new JsonFactory();
		try (JsonParser parser = factory.createParser("123.23")) {
			assertNotNull(parser);
			assertNotNull(JsonUtils.readLong(parser));
		} catch (final IOException e) {
			assertTrue(true);
		}
	}

	@Test(expected = NullPointerException.class)
	public void tryToReadNullLong() {
		try {
			JsonParser parser = null;
			assertNull(JsonUtils.readLong(parser));
		} catch (final IOException e) {
			assertTrue(true);
		}
	}

	@Test
	public void tryToReadBoolean() {
		JsonFactory factory = new JsonFactory();
		try (JsonParser parser = factory.createParser("true")) {
			assertNotNull(parser);
			assertNotNull(JsonUtils.readBoolean(parser));
		} catch (final IOException e) {
			assertTrue(true);
		}
	}

	@Test(expected = NullPointerException.class)
	public void tryToReadNullBoolean() {
		try {
			JsonParser parser = null;
			assertNull(JsonUtils.readBoolean(parser));
		} catch (final IOException e) {
			assertTrue(true);
		}
	}

	@Test
	public void tryToReadDouble() {
		JsonFactory factory = new JsonFactory();
		try (JsonParser parser = factory.createParser("123.23")) {
			assertNotNull(parser);
			assertNotNull(JsonUtils.readDouble(parser));
		} catch (final IOException e) {
			assertTrue(true);
		}
	}

	@Test(expected = NullPointerException.class)
	public void tryToReadNullDouble() {
		try {
			JsonParser parser = null;
			assertNull(JsonUtils.readDouble(parser));
		} catch (final IOException e) {
			assertTrue(true);
		}
	}

	@Test
	public void tryToReadString() {
		JsonFactory factory = new JsonFactory();
		try (JsonParser parser = factory.createParser(jsonString)) {
			assertNotNull(parser);
			assertNotNull(JsonUtils.readString(parser));
		} catch (final IOException e) {
			assertTrue(true);
		}
	}

	@Test(expected = NullPointerException.class)
	public void tryToReadNullString() {
		try {
			JsonParser parser = null;
			assertNull(JsonUtils.readString(parser));
		} catch (final IOException e) {
			assertTrue(true);
		}
	}

	@Test(expected = NullPointerException.class)
	public void testToString() throws IOException {
		String stringInput = "test";
		int intInput = 1234;
		InputStream inStr = new ByteArrayInputStream(stringInput.getBytes());
		InputStream inInt = new ByteArrayInputStream(String.valueOf(intInput).getBytes());
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStr));
		StringBuilder sb = new StringBuilder();

		String line = null;

		assertNotNull(JsonUtils.toString(inStr));

		assertNotNull(JsonUtils.toString(inInt));

		assertNull(JsonUtils.toString(null));

		// assertEquals(String.valueOf(intInput), JsonUtils.toString(inInt));
		// assertEquals(stringInput, JsonUtils.toString(inStr));

		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (final IOException e) {
			assertFalse(true);
			;
		}

	}

	@Test
	public void tryToParseDataMap() {
		JsonToken token = null;
		ArrayList<Map<String, String>> mapDati;
		// Map<String, String> dataMap = new HashMap<>();
		JsonFactory factory = new JsonFactory();
		try (JsonParser parser = factory.createParser(jsonString)) {
			token = parser.nextToken();
			assertNotNull(token);
			mapDati = JsonUtils.parseDataMap(jsonString);
			assertNotNull(mapDati);
		} catch (final IOException e) {
			assertTrue(true);
		}
		try (JsonParser parser = factory.createParser(jsonString2)) {
			token = parser.nextToken();
			if (token != JsonToken.START_ARRAY) {
				if (token != null) {
					throw new JsonParseException("malformed data input: expected '[' found '" + token.asString() + "'",
							parser.getCurrentLocation());
				}
			}
			assertNotNull(token);
			mapDati = JsonUtils.parseDataMap(jsonString2);
			assertNotNull(mapDati);
		} catch (final IOException e) {
			assertTrue(true);
		}
		try (JsonParser parser = factory.createParser(jsonString3)) {
			token = parser.nextToken();

			if (token != JsonToken.START_ARRAY) {
				if (token != null) {
					throw new JsonParseException("malformed data input: expected '[' found '" + token.asString() + "'",
							parser.getCurrentLocation());
				}
			}
			assertNotNull(token);
			mapDati = JsonUtils.parseDataMap(jsonString3);
			assertNotNull(mapDati);
		} catch (final IOException e) {
			assertTrue(true);
		}

	}

	@Test(expected = NullPointerException.class)
	public void tryToParseDataMapNullToken() {
		ArrayList<Map<String, String>> mapList;
		String jsonStr = null;
		JsonFactory factory = new JsonFactory();
		try {
			mapList = JsonUtils.parseDataMap(jsonStr);
			assertNull(mapList);
			JsonParser parser = factory.createParser(jsonStr);
			JsonToken token = parser.nextToken();
			assertNull(token);
		} catch (final IOException e) {
			assertTrue(true);
		}

	}

	@Test(expected = NullPointerException.class)
	public void testReadRawDataObject() throws JsonParseException, JsonMappingException, IOException {
		JsonFactory factory = new JsonFactory();
		try {
			JsonParser parser = factory.createParser(row);
			JsonUtils.readRawObject(parser);
		} catch (IOException e) {
			// this shouldn't happen, log the error
			log.error("Error parsing file with initialized parser", e);
		}
		try {
			JsonParser parser = null;
			JsonUtils.readRawObject(parser);
		} catch (IOException e) {
			// this shouldn't happen, log the error
			log.error("Error parsing with initial null parser", e);
		}

	}
	@Test(expected = NullPointerException.class)
	public void testAddToNodeThrowsExceptionWhenNullAttribute() {
		JsonUtils.addToNode(JsonNodeFactory.instance.objectNode(), JsonNodeFactory.instance.objectNode(), null);
	}
	@Test
	public void testAddToNodeReturnsNodeWhenAllNulls() {
		assertNotNull(JsonUtils.addToNode(null, null, "attribute"));
	}
	@Test
	public void testAddToNodeAddsEmptyNodeWhenAddendumIsNull() {
		JsonNode node = JsonUtils.addToNode(null, null, "attribute");
		assertTrue(node.get("attribute").isObject());
		assertTrue(node.get("attribute").toString().equals("{}"));
	}
	@Test
	public void testAddToNodeAddsCorrectNodeWhenAddendumIsNotNull() {
		JsonNode addendum = JsonNodeFactory.instance.objectNode();
		((ObjectNode)addendum).put("value", "test");
		JsonNode result = JsonUtils.addToNode(null, addendum, "attribute");
		assertNotNull(result);
		assertNotNull(result.get("attribute"));
		assertTrue(result.get("attribute").isObject());
		assertNotNull(result.get("attribute").get("value"));
		assertTrue(result.get("attribute").get("value").isTextual());
		assertTrue(result.get("attribute").get("value").asText().equals("test"));
	}
	@Test
	public void testAddToNodeOverwritesCorrectNodeWhenAddendumIsNotNull() {
		JsonNode addendum = JsonNodeFactory.instance.objectNode();
		((ObjectNode)addendum).put("value", "test");
		JsonNode result = JsonNodeFactory.instance.objectNode();
		((ObjectNode)result).put("attribute", "test");
		// check prerequisites
		assertNotNull(result.get("attribute"));
		assertFalse(result.get("attribute").isObject());

		result = JsonUtils.addToNode(null, addendum, "attribute");
		assertNotNull(result);
		assertNotNull(result.get("attribute"));
		assertTrue(result.get("attribute").isObject());
		assertNotNull(result.get("attribute").get("value"));
		assertTrue(result.get("attribute").get("value").isTextual());
		assertTrue(result.get("attribute").get("value").asText().equals("test"));
	}
	public static synchronized String readRawObject(@NonNull final JsonParser parser)
			throws JsonParseException, JsonMappingException, IOException {
		if (parser.getCurrentToken() != JsonToken.START_OBJECT) {
			// try to move to the next token
			parser.nextToken();
		}
		if (parser.getCurrentToken() != JsonToken.VALUE_NULL) {
			parser.setCodec(rawmapper);
			return rawmapper.readValue(parser, RawPojo.class).getJson();
		} else {
			return "";
		}
	}


}
