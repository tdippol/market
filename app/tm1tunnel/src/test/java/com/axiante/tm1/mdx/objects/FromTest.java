package com.axiante.tm1.mdx.objects;


import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class FromTest {
	
	@Test public void testGetCubeNameReturnsValue(){
		String expectedCube = "mocked cube";
		From from = new From(expectedCube);
		assertThat(from.getCubeName(), CoreMatchers.equalTo(expectedCube));
	}

	@Test public void testGetCubeNameReturnsValueWhenCubeInBrakets(){
		String expectedCube = "mocked cube";
		From from = new From("["+expectedCube+"]");
		assertThat(from.getCubeName(), CoreMatchers.equalTo(expectedCube));
	}
	
	@Test public void testGetCubeNameReturnsWholeValueWhenCubeInHalfBrakets(){
		String expectedCube = "mocked cube]";
		From from = new From(expectedCube);
		assertThat(from.getCubeName(), CoreMatchers.equalTo(expectedCube));
		expectedCube = "[mocked cube";
		from = new From(expectedCube);
		assertThat(from.getCubeName(), CoreMatchers.equalTo(expectedCube));
	}

}
