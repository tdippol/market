package com.axiante.tm1.json;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import com.axiante.utility.configuration.DynamicColumnsSettings;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * this class produces a single row that contains 
 * the cell(s) with the data ready for the dynamic configuration
 * producer
 * @author marco
 *
 */
@Slf4j
public class Tm1JsonDynaConfReader extends Tm1JsonReader{
	public Row<Cell> getHeaders(@NonNull final InputStream in, @NonNull final DynamicColumnsSettings settings) throws IOException {
		return getHeaders( new JsonFactory().createParser(in), settings);
	}

	public Row<Cell> getHeaders(@NonNull final JsonParser parser, DynamicColumnsSettings settings) throws JsonParseException, IOException{
		log.debug("opening stream for parsing json data");
		
		JsonToken token = parser.nextToken();
		Row<Cell> table = null;
		if ( token != JsonToken.START_OBJECT ) {
			log.error("expected start object");
		} else { 
			while(token != JsonToken.END_OBJECT) {
				switch (token) {
				case FIELD_NAME:
					// read what we have
					String field = parser.getCurrentName();
					switch (field) {
					case "Axes":
						table = readHeaders(parser, settings);
						break;

					default:
						break;
					}
					break;
				default:
					break;
				}
				token = parser.nextToken();
			}
		}

		return table;
	}
	
	private Row<Cell> readHeaders(@NonNull final JsonParser parser, DynamicColumnsSettings settings) throws JsonParseException, IOException{
		Row<Cell> ret = null;
		JsonToken currentToken = parser.nextToken();
		boolean objectStarted = false;
		if (currentToken != JsonToken.START_ARRAY) {
			log.error("expected begin array");
			return null;
		} else { 
			int ordinal = -1;
			int cardinality = -1;
			List<String> hierarchies = null;
			while(currentToken != JsonToken.END_ARRAY && ordinal < 1) {
				currentToken = parser.nextToken();
				if ( objectStarted) {
					if ( currentToken == JsonToken.END_OBJECT) {
						// end of axes
						objectStarted = false;
					} else { 
						// read data
						String field = parser.getCurrentName();
						switch (field) {
						case JsonConstants.Ordinal:
							ordinal = JsonUtils.readInt(parser);
							break;
						case JsonConstants.Cardinality:
							cardinality = JsonUtils.readInt(parser);
							break;
						case JsonConstants.Hierarchies:
							switch(ordinal) {
							case 0:
								hierarchies = readNamesInHierarchy(parser);
								break;
							default:
								break;
							}
							break;
						case JsonConstants.Tuples:
							switch(ordinal) {
							case 0:
								ret = readTuplesForHeaders(parser, cardinality, hierarchies, settings);
								break;
							default:
								log.warn("unexpected Tuples with ordinal " + ordinal);
								break;
							}
							break;
						default:
							break;
						}
					}
				} else { 
					// check that the object starts
					if ( currentToken!=JsonToken.START_OBJECT && currentToken != JsonToken.END_ARRAY ) {
						log.error("expected start object or end array after start array");
						return null;
					}  else { 
						log.debug("###### axes " + (++ordinal) + " starts");
						objectStarted = true;
					}
				}
			}
			
		}
		return ret;
	}
	
	private Row<Cell> readTuplesForHeaders(@NonNull final JsonParser parser, final int cardinality, final List<String> names,DynamicColumnsSettings settings) throws IOException {
		Row<Cell> headers = new Row<Cell>(cardinality);
		JsonToken currentToken = parser.nextToken();
		if( currentToken != JsonToken.START_ARRAY) {
			log.error("expected array of tuples");
		} else { 
			while(currentToken != JsonToken.END_ARRAY) {
				currentToken = parser.nextToken();
				if ( currentToken == JsonToken.FIELD_NAME) {
					String field = parser.getCurrentName();
					switch (field) {
					case JsonConstants.Members:
							headers.addAll(readMembersForHeaders(parser, names,settings));
						break;
					default:
						//something we don't care
						break;
					}
				} 
			}
		}
		return headers;
	}

	
	private List<Cell> readMembersForHeaders(@NonNull final JsonParser parser, @NonNull final List<String> names, DynamicColumnsSettings settings) throws IOException {
		/**
		 * -- passa l'array dei valori letti in hierarchies
		 * per ogni nuovo Ordinal:
		 *   - crea la cella, 
		 *   - metti il contatore a 0
		 *   - il valore dell'header e' hierarchies [contatore]$name
		 *   - salta gli attributi
		 *   - se appendi il valore a header, in header ci prepende $$ 
		 *   
		 *   Es.: Year$2016$$Month$M01
		 *   
		 */
		List<Cell> members = new ArrayList<>();
		int index=0;
		StringBuffer buffer = new StringBuffer();
		Cell dummyCell = new Cell();
		JsonToken currentToken = parser.nextToken();
		if( currentToken != JsonToken.START_ARRAY) {
			log.error("expected array of members");
		} else { 
			int level = -1;
			String attribute = null;
			while(currentToken != JsonToken.END_ARRAY) {
				currentToken = parser.nextToken();
				if ( currentToken == JsonToken.FIELD_NAME) {
					String field = parser.getCurrentName();
					switch (field) {
					case JsonConstants.Name:
						++level;
						if ( buffer.length() > 0 ) {
							buffer.append("$$");
						}
						attribute = JsonUtils.readString(parser);
						buffer.append(names.get(index)).append("$").append(attribute);
						++index;
						break;
					case JsonConstants.Attributes:
						if ( settings.getHeaderconf().length > level ) {
							attribute = settings.getHeaderconf()[level];
						} 
						addAttributesToCell(parser, dummyCell, attribute);
						break;
					default:
						break;
					}
				}
			}
		}
		// done reading the member, push the object in the return variable
		Cell cell = Cell.createHeader(buffer.toString());
		cell.setAttributes(dummyCell.getAttributes());
		
		members.add(cell);
		return members;
	}
	
	private void addAttributesToCell(@NonNull final JsonParser parser, @NonNull final Cell cell, String attribute) throws IOException {
		JsonToken currentToken = parser.nextToken();
		if( currentToken != JsonToken.START_OBJECT) {
			log.error("expected attributes object");
			return;
		}
		while(currentToken != JsonToken.END_OBJECT) {
			currentToken = parser.nextToken();
		
			if (currentToken == JsonToken.FIELD_NAME && attribute.trim().equals(parser.getCurrentName())) {
				cell.addAttribute(attribute, JsonUtils.readString(parser));
			} else { 
				// we don't really care
				if ( currentToken != JsonToken.END_OBJECT )
					parser.nextToken();
			}
		}
	}
}
