package com.axiante.mui.common.utility;

import static org.junit.Assert.assertThat;

import java.util.regex.Pattern;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class CellNameMapperTest {

	@Test
	public void testSubst() {
		String name = "test + } . % /";
		String expect = "test_plus__cbrace__dot__perc__slash_";
		String test = name.replaceAll(Pattern.quote(" "), "")
				 .replaceAll(Pattern.quote("+"), "_plus_")
				 .replaceAll(Pattern.quote("}"), "_cbrace_")
				 .replaceAll(Pattern.quote("."), "_dot_")
				 .replaceAll(Pattern.quote("%"), "_perc_")
				 .replaceAll(Pattern.quote("/"), "_slash_");
		assertThat(test, CoreMatchers.equalTo(expect));
	}
	@Test
	public void testSpace() {
		String name = "test name";
		String expect = "testname";
		String test = name.replaceAll(" ", "");
//				 .replaceAll("\\+", "_plus_")
//				 .replaceAll("}", "_cbrace_")
//				 .replaceAll(".", "_dot_")
//				 .replaceAll("%", "_perc_")
//				 .replaceAll("/", "_slash_");
		assertThat(test, CoreMatchers.equalTo(expect));		
	}
	
	@Test
	public void testPlus() {
		String name = "test +";
		String expect = "test_plus_";
		
		String test = name
				.replaceAll(Pattern.quote("+"), "_plus_")
				.replaceAll(" ", "")
				;
		String test2 = "test+";
		assertThat(test, CoreMatchers.equalTo(expect));
		test = CellNameMapper.map2agGrid(name);
		assertThat(test, CoreMatchers.equalTo(expect));
		test = CellNameMapper.agGrid2map(test);
		assertThat(test, CoreMatchers.equalTo(test2));
	}
}
