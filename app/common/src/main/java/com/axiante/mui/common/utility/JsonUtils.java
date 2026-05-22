package com.axiante.mui.common.utility;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {
	@Getter
	private static final JsonFactory factory = new JsonFactory();
	private static final ObjectMapper rawmapper = new ObjectMapper();
	@Getter
	private static final ObjectMapper validationMapper = new ObjectMapper();
	public static final Pattern jsonStringPatternFix = Pattern.compile("^\"|\"$");
	@Getter
	private static final ObjectMapper mapper = new ObjectMapper();
	@Getter
	private static final ObjectMapper nonClosingStreamMapper = new ObjectMapper();

	static {
		final SimpleModule module = new SimpleModule();
		module.addDeserializer(RawPojo.class, new KeepAsJsonDeserialzier());
		rawmapper.registerModule(module);
		mapper.getFactory().configure(com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
		nonClosingStreamMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
	}

	public static synchronized String prettyPrint(@NonNull final String json) {
		if (json.trim().length() > 0) {
			try {
				return getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(getMapper().readTree(json));
			} catch (IOException e) {
				log.error("error parsing json " + json, e);
				return json;
			}
		}
		return json;
	}

	public static synchronized String uglyPrint(@NonNull final String json) {
		if (json.trim().length() > 0) {
			try {
				return getMapper().writeValueAsString(getMapper().readTree(json));
			} catch (IOException e) {
				log.error("error parsing json " + json, e);
				return json;
			}
		}
		return json;
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

	public static synchronized ArrayList<String> readStringList(@NonNull final JsonParser parser)
			throws JsonParseException, JsonMappingException, IOException {
		final ArrayList<String> list = new ArrayList<>();
		if (parser.nextToken() == JsonToken.START_ARRAY) {
			while (parser.nextToken() != JsonToken.END_ARRAY) {
				list.add(parser.getValueAsString());
			}
		}
		return list;
	}

	public static synchronized String validate(@NonNull final String json) {
		try {
			validationMapper.readTree(json);
		} catch (final IOException e) {
			log.error("Error validating json", e);
			return e.getMessage();
		}
		return null;
	}

	public static synchronized String format(final Object json) {
		if (json != null) {
			final ObjectMapper mapper = JsonUtils.getValidationMapper();
			try {
				final String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
				if (indented != null) {
					return indented;
				}
			} catch (final IOException e) {
				log.error("error formatting json ", e);
			}
		}
		return null;
	}
	public static synchronized String asJson(final Object json) {
		if (json != null) {
			final ObjectMapper mapper = JsonUtils.getValidationMapper();
			try {
				final String indented = mapper.writeValueAsString(json);
				if (indented != null) {
					return indented;
				}
			} catch (final IOException e) {
				log.error("error formatting json ", e);
			}
		}
		return null;
	}

	public static int readInt(@NonNull final JsonParser parser) throws IOException {
		parser.nextToken();
		final int i = parser.getIntValue();
		return i;
	}

	public static long readLong(@NonNull final JsonParser parser) throws IOException {
		parser.nextToken();
		final long i = parser.getLongValue();
		return i;
	}

	public static double readDouble(@NonNull final JsonParser parser) throws IOException {
		parser.nextToken();
		final double i = parser.getDoubleValue();
		return i;
	}

	public static boolean readBoolean(@NonNull final JsonParser parser) throws IOException {
		parser.nextToken();
		final boolean i = parser.getBooleanValue();
		return i;
	}

	public static String readString(@NonNull final JsonParser parser) throws IOException {
		parser.nextToken();
		final String i = "null".equals(parser.getText()) ? "" : parser.getText();
		return i;
	}

	public static String toString(@NonNull final InputStream in) {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
		final StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (final IOException e) {
			log.error("error converting to string ", e);
		} finally {
			try {
				in.close();
			} catch (final IOException e) {
				log.error("error closing input stream", e);
			}
		}
		return sb.toString();
	}

	public static Map<String, String> parseData(@NonNull final String data) throws JsonParseException, IOException {
		try (JsonParser parser = factory.createParser(data)) {
			return parseData(parser);
		}
	}

	public static synchronized Map<String, String> parseData(@NonNull final JsonParser parser)
			throws JsonParseException, IOException {
		final Map<String, String> dataMap = new HashMap<>();
		JsonToken token = parser.nextToken();

		if (token != JsonToken.START_OBJECT) {
			if (token != null) {
				throw new JsonParseException("malformed data input: expected '{' found '" + token.asString() + "'",
						parser.getCurrentLocation());
			} else {
				return null;
			}
		}
		while (token != JsonToken.END_OBJECT) {
			switch (token) {
			case FIELD_NAME:
				dataMap.put(parser.getCurrentName(), readRawObject(parser));
				break;
			default:
				break;
			}
			token = parser.nextToken();
		}
		// make sure to consume the object
		while (token != JsonToken.END_OBJECT) {
			token = parser.nextToken();
		}
		return dataMap;
	}

	public static synchronized ArrayList<Map<String, String>> parseDataMap(@NonNull final String data)
			throws JsonParseException, IOException {
		final ArrayList<Map<String, String>> mapList = new ArrayList<>();
		Map<String, String> dataMap = new HashMap<>();
		try (JsonParser parser = factory.createParser(data)) {
			JsonToken token = parser.nextToken();

			// adesso e' un array di oggetti
			if (token != JsonToken.START_ARRAY) {
				if (token != null) {
					throw new JsonParseException("malformed data input: expected '[' found '" + token.asString() + "'",
							parser.getCurrentLocation());
				} else {
					return null;
				}
			}
			while ((token != null) && (token != JsonToken.END_ARRAY)) {
				while (token != JsonToken.END_OBJECT) {
					switch (token) {
					case FIELD_NAME:
						dataMap.put(parser.getCurrentName(), readRawObject(parser));
						break;
					default:
						break;
					}
					token = parser.nextToken();
				}
				mapList.add(dataMap);
				dataMap = new HashMap<>();
				token = parser.nextToken();
			}

			while ((token != null) && (token != JsonToken.END_OBJECT)) {
				token = parser.nextToken();
			}
		}
		return mapList;
	}

	public static synchronized Map<String, String> parseData(@NonNull final String data, final String name)
			throws JsonParseException, IOException {
		Map<String, String> dataMap = new HashMap<>();
		dataMap = parseData(data);
		if (name != null) {
			final String newData = dataMap.get(name);
			if (newData != null) {
				dataMap = parseData(newData);
			} else {
				// there's an error
				dataMap = null;
			}
		}
		return dataMap;
	}

	public static synchronized String writeValueAsString(@NonNull final Object object) throws JsonProcessingException {
		return getMapper().writeValueAsString(object);
	}


	public static synchronized JsonNode addToNode(JsonNode node, JsonNode addendum, @NonNull final String attriute) {
		if ( node == null ) {
			node = JsonNodeFactory.instance.objectNode();
		}
		if ( addendum == null ) {
			addendum = JsonNodeFactory.instance.objectNode();
		}
		((ObjectNode) node).set(attriute,addendum);
		return node;
	}
}
