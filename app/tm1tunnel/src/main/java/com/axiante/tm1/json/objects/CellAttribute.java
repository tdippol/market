package com.axiante.tm1.json.objects;

import java.util.Comparator;
import lombok.Data;

@Data
public class CellAttribute {
	private int index = 0;
	private String value;

	public static final Comparator<CellAttribute>DEFAULT_SORTER = new Comparator<CellAttribute>(){

		@Override
		public int compare(CellAttribute arg0, CellAttribute arg1) {
			return arg0.index-arg1.index;
		}
		
	};
	
	public CellAttribute(String value) {
		this.value=value;
	}

	public CellAttribute(int index, String value) {
		this.value=value;
		this.index = index;
	}
}
