package com.axiante.mui.business.reader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import com.axiante.mui.filter.CatalogFilter;


public class ViewFilterConfigurationReaderTest {
    Map<String, String> files = new HashMap<>();

    @Before
    public void readFiles() throws IOException, URISyntaxException {
        files.put("test1", ("{\n" + 
                "    'legalentity':['alias','currency','country','location']\n" + 
                "}").replace("\'", "\""));
        files.put("test2", ("{\n" + 
                "    'legalentity':['alias','currency','country','location'],\n" + 
                "    'Account':['alias']\n" + 
                "}").replace("\'", "\""));

    }

    @Test
    public void testReaderRetunsData() {
        assertNotNull(new ViewFilterConfigurationReader().readFilters(files.get("test1"))); 
    }

    @Test(expected = NullPointerException.class)
    public void testReaderNullThrowsException() {
        final String nullFile = null;
        assertNull(new ViewFilterConfigurationReader().readFilters(nullFile)); 
    }

    @Test
    public void testReaderRetunsObject() {
        assertTrue(new ViewFilterConfigurationReader().readFilters(files.get("test1")).size() > 0 ); 
    }
    @Test
    public void testReaderRetunsCorrectObject() {
        final List<CatalogFilter> data = new ViewFilterConfigurationReader().readFilters(files.get("test2"));
        assertTrue(data.size() ==2 );
        final Optional<CatalogFilter> filter = 
                data.stream().filter( e->{
                    return e.getName().equals("legalentity");
                }).findFirst();
        assertTrue(filter.isPresent());
        assertThat(filter.get().getAttributes().size(), CoreMatchers.equalTo(4));
        assertTrue(filter.get().getAttributes().contains("country"));
    }

    @Test(expected = NullPointerException.class)
    public void testReadFiltersThrowsNullPointerException() {
        new ViewFilterConfigurationReader().readFilters(null);
    }


}
