package com.axiante.tm1.mdx.objects;

import static org.junit.Assert.assertNotNull;

import com.axiante.tm1.mdx.filter.Filter;
import org.junit.Test;

public class FilterQueryTest {

	private static final String mdx = "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}";
	private static final String column = "Promozione";
	
	@Test
	public void testQueryGeneration() {
		Filter filter = new Filter() {
			
			@Override
			public String getValue() {
				return mdx;
			}
			
			@Override
			public String getColumn() {
				return column;
			}

			@Override
			public String getFilter() {

				return null;
			}
		};
		FilterQuery q = new FilterQuery(filter);
		String query = q.generateMDX();
		assertNotNull(query);
		

	}
}
