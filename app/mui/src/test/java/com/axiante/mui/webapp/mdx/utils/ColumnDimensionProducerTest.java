package com.axiante.mui.webapp.mdx.utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import com.axiante.mui.webapp.utils.mdx.ColumnDimensionProducer;
import com.axiante.tm1.mdx.objects.Dimension;
import com.axiante.tm1.mdx.objects.Dimension.Type;
import java.util.List;
import org.junit.Test;

public class ColumnDimensionProducerTest {

	@Test
	public void testProduceFromNullReturnsNull() {
		ColumnDimensionProducer e = new ColumnDimensionProducer();
		assertNull(e.getFromStringConfiguration(null));
	}
	
	@Test
	public void testProduceFromWrongStringReturnsNull() {
		ColumnDimensionProducer e = new ColumnDimensionProducer();
		assertNull(e.getFromStringConfiguration("some string"));
	}
	
	@Test
	public void testProduceFromOneSeparatorStringReturnsOneObjects() {
		ColumnDimensionProducer e = new ColumnDimensionProducer();
		assertThat(e.getFromStringConfiguration("some$string").size(), equalTo(1));
	}
	
	@Test
	public void testProduceFromDoubleSeparatorStringReturnsOneObjects() {
		ColumnDimensionProducer e = new ColumnDimensionProducer();
		assertThat(e.getFromStringConfiguration("test$string$$some$string").size(), equalTo(1));
	}

	@Test
	public void testProduceFromInvalidFormatReturnsNull() {
		ColumnDimensionProducer e = new ColumnDimensionProducer();
		assertNull(e.getFromStringConfiguration("test$string$$some$string$string"));
	}
	@Test
	public void testProduceFromEarlyInvalidFormatReturnsNull() {
		ColumnDimensionProducer e = new ColumnDimensionProducer();
		assertNull(e.getFromStringConfiguration("test$string$string$$some$string"));
	}
	
	@Test
	public void testProduceFromOneSeparatorStringReturnsOneCorrectDimension() {
		ColumnDimensionProducer e = new ColumnDimensionProducer();
		List<Dimension> list = e.getFromStringConfiguration("test$string"); 
		assertThat(list.size(), equalTo(1));
		assertThat(list.get(0).getType(), equalTo(Type.COLUMNS));
		assertThat(list.get(0).getColumn(), equalTo("([test].[string])"));

		list = e.getFromStringConfiguration("test$string$$another$string"); 
		assertThat(list.size(), equalTo(1));
		assertThat(list.get(0).getType(), equalTo(Type.COLUMNS));
		assertThat(list.get(0).getColumn(), equalTo("([test].[string],[another].[string])"));

	}
	

}
