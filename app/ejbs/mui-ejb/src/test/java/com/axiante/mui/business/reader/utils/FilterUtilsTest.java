package com.axiante.mui.business.reader.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.axiante.mui.filter.utils.FilterUtils;
import com.axiante.mui.filter.utils.FilterUtilsImpl;
import com.axiante.tm1.mdx.objects.Dimension;
import com.axiante.tm1.mdx.objects.Dimension.Type;

public class FilterUtilsTest {

	@InjectMocks
	FilterUtils filterUtils = new FilterUtilsImpl();

	@Test
	public void testUnpackReturnsData() {
		List<String> data = null;
		String row = "{\"Reparto\":\"REP_01\",\"Promozione\":\"2019_405\",\"Versione\":\"UFF\"}";
		String column = "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO";
		try {
			data = filterUtils.unpack(row, column);
			assertNotNull(data);
			assertThat(data.get(0),CoreMatchers.equalTo("Dimensions('Scenario')/Hierarchies('Scenario')/Elements('TGT_MKT')"));
			assertThat(data.get(1), CoreMatchers.equalTo("Dimensions('MisuraTimone.')/Hierarchies('MisuraTimone.')/Elements('N_ART_PROMO')"));
			assertThat(data.get(2),CoreMatchers.equalTo("Dimensions('Promozione')/Hierarchies('Promozione')/Elements('2019_405')"));
			assertThat(data.get(3),CoreMatchers.equalTo("Dimensions('Versione')/Hierarchies('Versione')/Elements('UFF')"));
			assertThat(data.get(4),CoreMatchers.equalTo("Dimensions('Reparto')/Hierarchies('Reparto')/Elements('REP_01')"));
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	@Test
	public void testUnpackWrongColsReturnsNull() {
		String row = "{\"Reparto\":\"REP_01\",\"Promozione\":\"2019_405\",\"Versione\":\"UFF\"}";
		String column = "";
		try {
			assertNull(filterUtils.unpack(row, column));
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	@Test
	public void testUnpackWrongRowsReturnsNull() {
		String row = "";
		String column = "";
		try {
			assertNull(filterUtils.unpack(row, column));
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	@Test
	public void testUnpackNullRowsReturnsNull() {
		String row = null;
		String column = "";
		try {
			assertNull(filterUtils.unpack(row, column));
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	@Test
	public void testUnpackNullColsReturnsNull() {
		String row = "";
		String column = null;
		try {
			assertNull(filterUtils.unpack(row, column));
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	@Test
	public void testUnpackNullReturnsNull() {
		String row = null;
		String column = null;
		try {
			assertNull(filterUtils.unpack(row, column));
		} catch (Exception e) {
			assertTrue(false);
		}
	}


	@Test
	public void testUnpackRowsToDimensionReturnsData() {
		List<Dimension> data = null;
		String row = "{\"Reparto\":\"REP_01\",\"Promozione\":\"2019_405\",\"Versione\":\"UFF\"}";
		try {
			data = filterUtils.unpackRowsToDimension(row);
			assertNotNull(data);
			assertThat(data.size(), CoreMatchers.equalTo(3));
			for(Dimension d : data) {
				assertThat(d.getType(),CoreMatchers.equalTo(Type.ROWS));
			}
			assertThat(1, CoreMatchers.equalTo(
					(int)data.parallelStream()
					.map(Dimension::getValue)
					.filter("{[Promozione].[2019_405]}"::equals)
					.count()
					)
					);
			assertThat(1, CoreMatchers.equalTo(
					(int)data.parallelStream()
					.map(Dimension::getValue)
					.filter("{[Reparto].[REP_01]}"::equals)
					.count()
					)
					);
			assertThat(1, CoreMatchers.equalTo(
					(int)data.parallelStream()
					.map(Dimension::getValue)
					.filter("{[Versione].[UFF]}"::equals)
					.count()
					)
					);

		} catch (Exception e) {
			assertTrue(false);
		}
	}


	//	@Test public void testConvertJsonFilterReturnsNullWhenInIsNull() {
	//		assertNull(filterUtils.convertJsonFilter(null));
	//	}
	//
	//	@Test public void testConvertJsonFilterReturnsInputWhenNotParseable() {
	//		String test = "test";
	//		assertThat(filterUtils.convertJsonFilter(test), equalTo(test));
	//	}
	//	@Test public void testConvertJsonFilterReturnsNewFormatWhenOldFormatIsPassed() {
	//		String test="{'a_dimension':['attribute_a','attribute_b']}".replace("\'", "\"");;
	//		String expected="{'dimensions':{'a_dimension':['attribute_a','attribute_b']},'defaults':[]}".replace("\'", "\"");
	//		assertThat(filterUtils.convertJsonFilter(test, true),equalTo(expected));
	//	}
	//
	//	@Test public void testConvertJsonFilterAddsDefaultsWhenMissing() {
	//		String test="{'dimensions':{'a_dimension':['attribute_a','attribute_b']}}".replace("\'", "\"");
	//		String expected="{'dimensions':{'a_dimension':['attribute_a','attribute_b']},'defaults':[]}".replace("\'", "\"");
	//		assertThat(filterUtils.convertJsonFilter(test, true),equalTo(expected));
	//	}
	//	@Test public void testConvertJsonFilterReturnsEmptyFilterWhenMissingDimensionsButHasDefaults() {
	//		String test="{'defaults':[]}".replace("\'", "\"");
	//		String expected="{'dimensions':{},'defaults':[]}".replace("\'", "\"");
	//		String result = filterUtils.convertJsonFilter(test);
	//		assertEquals(expected, result);
	//	}

}
