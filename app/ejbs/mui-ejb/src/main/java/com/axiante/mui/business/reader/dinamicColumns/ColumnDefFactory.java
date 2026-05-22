package com.axiante.mui.business.reader.dinamicColumns;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.CellAttribute;
import com.axiante.utility.configuration.ColumnDef;
import com.axiante.utility.configuration.DynamicColumnsSettings;

import lombok.NonNull;

public class ColumnDefFactory {
	private DynamicColumnsSettings settings;
	
	public ColumnDefFactory(@NonNull final DynamicColumnsSettings settings) {
		this.settings = settings;
	}
	public ColumnDef fromCell(Cell c) {
		ColumnDef d = settings.getChildrendefaults().clone();
		d.setField(c.getName());
		d.setCustomHeaders(generateHeaderNames(c));
		// se il nome e' in customtypes
		String realName = getRealName(c);
		ColumnDef customTypes = settings.getChildrenCustomTypes().get(realName);
		if ( customTypes != null ) {
			d.merge(customTypes);
		}
		return d;
	}
	
	private String getRealName(Cell c) {
		if ( c.getName() != null && c.getName().indexOf("$") > -1 ) { 
			String[] d = c.getName().split(Pattern.quote("$$"));
			d = d[d.length-1].split(Pattern.quote("$"));
			return d[d.length-1];
		} else { 
			return c.getName();
		}
	}
	protected String[] generateHeaderNames(Cell c) {
		List<String> appoggio = c.getAttributes().keySet().stream().filter(
				e->{
					return e!=null && e.trim().length()>0;}
				).collect(Collectors.toList());
		List<CellAttribute> ret  = new ArrayList<CellAttribute>();
		for (String key: appoggio) {
			ret.addAll(c.getAttribute(key));
		}

		if ( ret != null && ret.size() > 0) {
			List<String> result = ret.stream().sorted(CellAttribute.DEFAULT_SORTER).map(CellAttribute::getValue).collect(Collectors.toList());
			return result.toArray(new String[result.size()]);
		}
		return null;
	}
	
}
