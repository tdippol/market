package com.axiante.tm1.mdx;

import com.axiante.tm1.mdx.objects.Dimension;
import com.axiante.tm1.mdx.objects.Dimension.Type;
import com.axiante.tm1.mdx.objects.DimensionDependentObject;
import com.axiante.tm1.mdx.objects.From;
import com.axiante.tm1.mdx.objects.MdxConstants;
import com.axiante.tm1.mdx.objects.MdxEntry;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.tm1.mdx.objects.Where;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * this class parses mdx. The mdx is stored in a json format
 * The purpose of the facility is to enable injection of filters
 * that come from the end user
 * @author marco
 *
 */
@Slf4j

public class QueryParser {
	@Getter
	private Query query ;

	/**
	 * returns the original query
	 * @return
	 */
	public String getOriginalQuery() {
		return null;
	}

	public Query read(JsonParser parser) throws IOException {
		this.query = Query.builder().build();
		this.query.setWhere(new Where()); // <-- lombok would want me to initialize the whole class
		// but i only need one value initialized ....
		JsonToken current = parser.nextToken();
		if ( current != JsonToken.START_OBJECT) {
			log.error("error reading mdx: expected begin object, found " + current.asString());
		} else { 
			while (current != JsonToken.END_OBJECT) {
				if ( current == JsonToken.FIELD_NAME) {
					switch(parser.getCurrentName()) {
					case MdxConstants.ROWS:
						query.setRows(readEntry(this.query,parser, Dimension.Type.ROWS));
						break;
					case MdxConstants.COLS:
						query.setCols(readEntry(this.query,parser, Dimension.Type.COLUMNS));
						break;
					case MdxConstants.FROM:
						query.setFrom(new From(parser.nextTextValue()));
						break;
					case MdxConstants.WHERE:
						readDimensions(this.query.getWhere(), parser, Dimension.Type.WHERE);
						break;
					}
				}
				current = parser.nextToken();
			}
		}
		return this.query;
	}
	protected  MdxEntry readEntry(final Query query, final JsonParser parser, Dimension.Type type) throws IOException {
		JsonToken token = parser.nextToken();
		MdxEntry entry = new MdxEntry();
		
		if ( token != JsonToken.START_OBJECT) {
			log.error("error reading entry " + type.toString() + ": expected begin object, found " + token.asString());
			return null;
		} else { 
			while(token != JsonToken.END_OBJECT) {
				if( token == JsonToken.FIELD_NAME) {
					switch (parser.getCurrentName()) {
					case MdxConstants.NON_EMPTY:
						entry.setNonEmpty(parser.nextBooleanValue());
						break;
					 case MdxConstants.DIMENSIONS:
						 readDimensions(entry, parser, type);
						 break;
					 default:
					}
				}
				token = parser.nextToken();
			}
		}
		return entry;
	}
	
	protected void readDimensions(@NonNull final DimensionDependentObject entry, @NonNull final JsonParser parser, final Dimension.Type type) throws IOException {
		JsonToken current = parser.nextToken();
		if ( current != JsonToken.START_OBJECT) {
			log.error("error reading dimension " + type.toString() + ": expected start object, found " +current.asString() );
		} else { 
			while(current != JsonToken.END_OBJECT) {
				Dimension dim;
				if ( current == JsonToken.FIELD_NAME) {
					String field = parser.getCurrentName();
					if ( type == Type.WHERE) {
						String datum  = parser.nextTextValue();
						String[] data = datum.split("[.]");
						if ( data.length == 2 ) {
							dim = new Dimension(data[0].replaceAll("\\[", "").replaceAll("\\]",""), data[1], type);	
						} else { 
							dim =new Dimension(field,datum, type);
						}
						
					} else { 
						dim =new Dimension(field,parser.nextTextValue(), type);
					}
					entry.addDimension(dim);
					
				}
				
				current = parser.nextToken();
			}
		}
	}
}
