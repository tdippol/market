package com.axiante.mui.business.reader;

//import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.axiante.mui.filter.CatalogFilter;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ViewFilterConfigurationReader {
	JsonFactory factory = new JsonFactory();
	public List<CatalogFilter> readFilters(String json){
		List<CatalogFilter> data = null;
		
		try(JsonParser parser = factory.createParser(json)){
			data = readFilters(parser);
		} catch (IOException e) {
			log.error("Error reading from input stream ", e);
		}
		return data;
	}
	
	private List<CatalogFilter> readFilters(@NonNull JsonParser parser){
		List<CatalogFilter> data = null;
		try {
			data = new ArrayList<>();
			JsonToken token=parser.getCurrentToken();
			if ( token == null ) {
				token = parser.nextToken();
			}
			CatalogFilter filter = new CatalogFilter();
			while(token != null && token != JsonToken.END_OBJECT) {
				if (JsonToken.FIELD_NAME == token ) {
					filter = new CatalogFilter();
					filter.setName(parser.getCurrentName());
				} else if ( JsonToken.START_ARRAY == token ) {
					while(JsonToken.END_ARRAY != token) {
						token = parser.nextToken();
						if ( JsonToken.END_ARRAY != token )
							filter.getAttributes().add(parser.getText());
						else
							data.add(filter);
					}
				}
				token = parser.nextToken();
			}
		} catch (Exception e) {
			log.error("error parsing json file",e);
		}
		if (data.size() == 0 ) data = null;
		
		return data;
	}
	
}
