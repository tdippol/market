package com.axiante.tm1.json;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import com.axiante.tm1.json.objects.Table;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

@Slf4j
public class Tm1JsonReaderTest {

	@Test
	public void testRead() throws IOException, IllegalAccessException {
		log.info("reading stream");
		long time = System.currentTimeMillis();
		Tm1JsonReader reader = new Tm1JsonReader();
		Table result = null;
		try(InputStream in = getClass().getClassLoader().getResourceAsStream("axes.json");){
		assertNotNull(in);
		result = reader.readAxes(in);
		assertThat(result.getRowSize(), CoreMatchers.equalTo(reader.getExpectedRows())); // number of rows + headers
		}
		try(InputStream in = getClass().getClassLoader().getResourceAsStream("cells.json")){
		reader.readCellSetData(result, in);
		// get a random cell at col 10
		Random r = new Random();
		Cell c = result.get(10 ,10);
		assertNotNull(c);
		Row<Cell> headers = result.getHeaders();
		// get a random row index and add 1 so that we're sure we don't get headers
		int rowIndex = r.nextInt((result.getRowSize() -0)  + 1) + 1; 
		if ( rowIndex == result.getRowSize() )
			--rowIndex; //<--in case we got the last index make sure we don't get an illegalaccessexception 
		Row<Cell> testRow = result.getRowByRowIndex(rowIndex);
		assertNotNull (testRow);
		assertThat(
				headers.stream().map(Cell::getName).collect(Collectors.joining()),
				CoreMatchers.equalTo(
						testRow.stream().map(Cell::getName).collect(Collectors.joining())
						)
				);
		List<List<String>> data = result.generateSimpleTable();
		time = System.currentTimeMillis() - time;
		log.info("done");
		log.info("generated final string table of " + data.size() + " x " + data.get(0).size() + " in " + time + " millis");
		}
	}

	@Test
	public void testUniqueNameSplit() {
		String uniqueName = "[Year].[2017]";
		String data = uniqueName.split("\\]\\.\\[")[0].replaceAll("\\[", "");
		assertThat("Year", CoreMatchers.equalTo(data));
	}

	@Test
	public void testMatcherRemovesQuotes() {
		final String test = "\"this is quoted text ";
		final String string = "with some funny' inside\"";
		final String compare = "this is quoted text ";
		final String _string = "with some funny' inside";
		final Pattern jsonStringPatternFix = Pattern.compile("^\"|\"$");
		Matcher matcher = jsonStringPatternFix.matcher("");
		String testString = null;
		String compareString = null;
		String check = null;
		for (int i = 0; i < 100; ++i) {
			testString = test + i + string;
			compareString = compare + i + _string;
			check = matcher.reset(testString).replaceAll("");
			assertThat(check, CoreMatchers.equalTo(compareString));
		}
	}

}
