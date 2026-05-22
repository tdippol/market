package com.axiante.tm1.mdx.objects;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AbstractDimensionDependentObjectTest {
	@Spy AbstractDimensionDependentObject test;

	@Mock Dimension mockedDimension;
	
	@Test(expected = NullPointerException.class) 
	public void testAddDimensionThrowsException() {
		test.addDimension(null);
	}
	
	@Test(expected = NullPointerException.class) 
	public void testAddAllDimensionsThrowsException() {
		test.addAllDimension(null);
	}
	
	
	@Test public void testAddDimensionReturnsValue() {
		test.addDimension(mockedDimension);
		assertThat(test.getDimensions().size(),CoreMatchers.equalTo(1));
		assertTrue(test.getDimensions().contains(mockedDimension));
	}
	
	@Test public void testAddAllDimensionsReturnsValue() {
		List<Dimension> list = new ArrayList<Dimension>();
		list.add(mockedDimension);
		list.add(mockedDimension);
		test.addAllDimension(list);
		assertThat(test.getDimensions().size(),CoreMatchers.equalTo(2));
		assertTrue(test.getDimensions().contains(mockedDimension));
		assertThat(test.getDimensions().get(0), CoreMatchers.equalTo(mockedDimension));
		assertThat(test.getDimensions().get(1), CoreMatchers.equalTo(mockedDimension));
	}

}
