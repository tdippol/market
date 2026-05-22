package com.axiante.tm1.mdx.objects;

import com.axiante.tm1.mdx.filter.Filter;

public class FilterQuery extends Query{
	public FilterQuery(Filter filter) {
		super(null,null,null, new Where(),null, true);
		String adminData = generateAdminCube(filter.getColumn());
		setFrom(new From(adminData));
		setCols(generateCols(adminData));
		setRows(generateRow(filter));
	}
	
	
	private String generateAdminCube(String attribute) {
		return MdxConstants.CURLY_BRAKET_CLOSED + "ElementAttributes_"+ attribute;
	}
	
	private MdxEntry generateCols(String value) {
		MdxEntry entry = new MdxEntry();
		entry.setNonEmpty(false);
		entry.addDimension(new Dimension(value, value, Dimension.Type.COLUMNS));
		return entry;
	}
	private MdxEntry generateRow(Filter f) {
		MdxEntry entry = new MdxEntry();
		entry.setNonEmpty(false);
		entry.addDimension(new Dimension(f.getColumn(), f.getValue(), Dimension.Type.ROWS));
		return entry;
	}
}
