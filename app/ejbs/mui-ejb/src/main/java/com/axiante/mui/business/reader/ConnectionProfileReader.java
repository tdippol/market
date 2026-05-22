package com.axiante.mui.business.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.common.utility.JsonUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NonNull;

public class ConnectionProfileReader {
	@Getter
	private final Map<String, ConnectionProfile> configurations = new HashMap<String, ConnectionProfile>();

	public ConnectionProfileReader read(@NonNull final InputStream in) throws IOException {
		configurations.clear();
		try (JsonParser parser = JsonUtils.getFactory().createParser(in);) {
			return read(parser);
		}
	}

	public ConnectionProfileReader read(@NonNull final String in) throws IOException {
		configurations.clear();
		try (JsonParser parser = JsonUtils.getFactory().createParser(in);) {
			return read(parser);
		}
	}

	private ConnectionProfileReader read(@NonNull final JsonParser parser) throws IOException {
		ConnectionProfile profile = null;
		ObjectMapper mapper = new ObjectMapper();
		JsonToken current = null;
		while ((current = parser.nextToken()) != JsonToken.START_OBJECT) {
			// posizionati sul primo oggetto
		}
		// from now on we have just configurations
		current = parser.nextToken();
		String field = null;
		while (current != JsonToken.END_ARRAY && current != JsonToken.END_OBJECT) {
			if (current == JsonToken.FIELD_NAME) {
				field = parser.getCurrentName();
			}
			while (current != JsonToken.END_ARRAY) {
				// a connection starts here
				while (current == JsonToken.FIELD_NAME) {
					field = parser.getCurrentName();
					current = parser.nextToken();
					profile = mapper.readValue(parser, ConnectionProfile.class);
					profile.setName(field);
					configurations.put(field, profile);
				}
				current = parser.nextToken();
			}
		}

		return this;
	}

}
