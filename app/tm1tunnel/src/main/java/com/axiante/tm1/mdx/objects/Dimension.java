package com.axiante.tm1.mdx.objects;

import com.axiante.tm1.mdx.filter.Filter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dimension implements Filter{
	private String column;
	private String value;
	private Type type;
	
	
	
	public enum Type{
		ROWS,
		COLUMNS,
		WHERE,
		SEPARATOR
	}

	
	public static final Dimension DEFAULT_SEPARATOR= new Dimension(""," * ", Type.SEPARATOR);
	
	
	@Override
	public String getFilter() {
		return "FILTER({})";
	}

	public String getCurlyValue() {
		return MdxConstants.CURLY_BRAKET_OPEN + getValue() + MdxConstants.CURLY_BRAKET_CLOSED;
	}
	
	
	public Dimension copy() {
	    return new Dimension(getColumn(), getValue(), getType());
	}
}
