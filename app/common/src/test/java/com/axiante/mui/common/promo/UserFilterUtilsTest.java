package com.axiante.mui.common.promo;

import static org.junit.Assert.assertNotNull;

import com.axiante.mui.common.utility.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

public class UserFilterUtilsTest {

	private static final UserFilterUtils userFilterUtils = new UserFilterUtils();
	private static final String dbFiltersJson = "{ " + "\"glossary\": { " + "\"title\": \"example glossary\", "
			+ "\"GlossDiv\": { " + "\"title\": \"S\", " + "\"GlossList\": { " + "\"selectedValues\": { "
			+ "\"ID\": \"SGML\", " + "\"SortAs\": \"SGML\", "
			+ "\"GlossTerm\": \"Standard Generalized Markup Language\", " + "\"Acronym\": \"SGML\", "
			+ "\"Abbrev\": \"ISO 8879:1986\", " + "\"Attribute\": { "
			+ " \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\", "
			+ "\"GlossSeeAlso\": [\"GML\", \"XML\"] " + "}, " + "\"GlossSee\": \"markup\" " + "} " + "} " + "} " + "} "
			+ "} ";

	private static final String dumbJson = "{ " + "\"glossary\": { " + "\"title\": \"example glossary\", "
			+ "\"GlossDiv\": { " + "\"title\": \"S\", " + "\"GlossList\": { " + "\"selectedValues\": { "
			+ "\"ID\": \"SGML\", " + "\"SortAs\": \"SGML\", "
			+ "\"GlossTerm\": \"Standard Generalized Markup Language\", " + "\"Acronym\": \"SGML\" " + "} " + "} ";

	@Test
	public void testCreateUserFiltersMapToQueryString() {
		Map<String, String> userFiltersMap = new TreeMap<>();

		// try {
		// JsonNode root = JsonUtils.getMapper().readTree(dbFiltersJson);
		// }catch (final IOException e) {
		// e.printStackTrace();
		// }

		userFiltersMap = userFilterUtils.createUserFiltersMapToQueryString(dbFiltersJson);
		assertNotNull(userFiltersMap);

	}

	@Test
	public void testCreateUserFiltersMapToQueryString2() {
		Map<String, String> userFiltersMap = new TreeMap<>();

		try {
			JsonNode dumbRoot = JsonUtils.getMapper().readTree(dumbJson);
			assertNotNull(dumbRoot);
			userFiltersMap = userFilterUtils.createUserFiltersMapToQueryString(dumbJson);
			assertNotNull(userFiltersMap);
		} catch (final IOException e) {
			Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
		}

	}
}
