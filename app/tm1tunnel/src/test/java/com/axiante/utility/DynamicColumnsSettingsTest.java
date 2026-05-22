package com.axiante.utility;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.utility.configuration.DynamicColumnsSettings;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class DynamicColumnsSettingsTest {

	String s = "{ 'DynamicColumnsSettings': {'headerconf': ['Caption_Default','Monate_DE'	],'headerdefaults': {'marryChildren': true},'childrendefaults': {'width': 100,'editable': true,'columnGroupShow': 'always','type': ['TM1DataColumnNumber','numericColumn']},'childrenCustomTypes': {'Mrz': {'type': ['TM1DataColumnText','numericColumn']},'Dez': {'type': ['TM1DataColumnText','numericColumn']}}} } ";

	@Test
	public void testRead() throws JsonParseException, IOException {
		String testString = s.replaceAll("'", "\"");
		try (JsonParser parser = JsonUtils.getFactory().createParser(testString);) {
			parser.nextToken(); // object
			parser.nextToken(); // name
			parser.nextToken(); // object
			DynamicColumnsSettings set = JsonUtils.getMapper().readValue(parser, DynamicColumnsSettings.class);
			assertNotNull(set);
			assertThat(set.getHeaderconf().length, CoreMatchers.equalTo(2));
			assertThat(set.getHeaderconf()[0], CoreMatchers.equalTo("Caption_Default"));
			assertThat(set.getHeaderdefaults().size(), CoreMatchers.equalTo(1));
			assertTrue(Boolean.valueOf(set.getHeaderdefaults().get("marryChildren")));
			assertThat(set.getChildrendefaults().getWidth(), CoreMatchers.equalTo(100));
		}
	}
}
