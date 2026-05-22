package com.axiante.tm1.mdx.objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.tm1.mdx.filter.Filter;
import com.axiante.tm1.mdx.objects.Dimension.Type;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QueryTest {
    @Spy
    Query query = Query.builder().build();

    @Mock
    MdxEntry mockedRows;
    @Mock
    MdxEntry mockedCols;
    @Mock
    From mockedFrom;
    @Mock
    Where mockedWhere;

    @Test(expected = NullPointerException.class)
    public void testGenerateMDXThrowsException() {
        spy(Query.builder().build()).generateMDX();
    }

    @Before public void initTest() {
        query.setRows(mockedRows);
        query.setCols(mockedCols);
        query.setFrom(mockedFrom);
        query.setWhere(mockedWhere);
    }

    @Test
    public void testGenerateMDXWithoutParameters() {
        final Query test = mock(Query.class);
        test.setRows(mock(MdxEntry.class));
        test.setCols(mock(MdxEntry.class));
        test.setFrom(mock(From.class));
        doCallRealMethod().when(test).generateMDX();
        test.generateMDX();
        verify(test, times(1)).generateMDX(false);
    }

    @Test
    public void testGenerateMDXWhenNonEmptyRowsParameter() {
        when(mockedRows.isNonEmpty()).thenReturn(true);
        doReturn(false).when(query).isCartesianColumns();

        String test = query.generateMDX(false);
        String expected = "SELECT NON EMPTY {{}} ON ROWS , {} ON COLUMNS FROM null"; // everything is mocked
        assertNotNull(test);
        assertThat(test, equalTo(expected));

        expected = "SELECT NON EMPTY {{}} ON ROWS , {{{}}} ON COLUMNS FROM null";
        when(mockedRows.isNonEmpty()).thenReturn(true);
        doReturn(true).when(query).isCartesianColumns();
        test = query.generateMDX(false);
        assertNotNull(test);
        assertThat(test, equalTo(expected));

        when(mockedRows.isNonEmpty()).thenReturn(false);
        doReturn(false).when(query).isCartesianColumns();
        expected = "SELECT {{}} ON ROWS , {} ON COLUMNS FROM null"; // everything is mocked
        test = query.generateMDX(false);
        assertNotNull(test);
        assertThat(test, equalTo(expected));
        expected = "SELECT {{}} ON ROWS , {{{}}} ON COLUMNS FROM null"; // everything is mocked
        when(mockedRows.isNonEmpty()).thenReturn(false);
        doReturn(true).when(query).isCartesianColumns();
        test = query.generateMDX(false);
        assertNotNull(test);
        assertThat(test, equalTo(expected));
    }

    @Test
    public void testGenerateMDXWhenNonEmptyColsParameter() {
        when(mockedCols.isNonEmpty()).thenReturn(true);
        doReturn(true).when(query).isCartesianColumns();
        String test = query.generateMDX(false);
        String expected = "SELECT {{}} ON ROWS , NON EMPTY {{{}}} ON COLUMNS FROM null"; // everything is mocked
        assertNotNull(test);
        assertThat(test, equalTo(expected));

        when(mockedCols.isNonEmpty()).thenReturn(true);
        expected = "SELECT {{}} ON ROWS , NON EMPTY {{{}}} ON COLUMNS FROM null"; // everything is mocked
        assertNotNull(test);
        assertThat(test, equalTo(expected));

        when(mockedCols.isNonEmpty()).thenReturn(false);
        doReturn(true).when(query).isCartesianColumns();
        expected = "SELECT {{}} ON ROWS , {{{}}} ON COLUMNS FROM null"; // everything is mocked

        test = query.generateMDX(false);
        assertNotNull(test);
        assertThat(test, equalTo(expected));

        expected = "SELECT {{}} ON ROWS , {} ON COLUMNS FROM null"; // everything is mocked
        when(mockedCols.isNonEmpty()).thenReturn(false);
        doReturn(false).when(query).isCartesianColumns();
        test = query.generateMDX(false);
        assertNotNull(test);
        assertThat(test, equalTo(expected));

    }

    @Test
    public void testGenerateMDXWithFromParameter() {
        when(mockedFrom.getValue()).thenReturn("mockedCube");

        final String test = query.generateMDX(false);
        final String expected = "SELECT {{}} ON ROWS , {{{}}} ON COLUMNS FROM mockedCube"; // everything is mocked
        assertNotNull(test);
        assertThat(test, equalTo(expected));
        verify(query, times(2)).getFilteredDimension(ArgumentMatchers.any(MdxEntry.class));
    }

    @Test
    public void testGenerateMDXWithWhereParameter() {
        final Dimension mockedDimension = mock(Dimension.class);
        when(mockedDimension.getColumn()).thenReturn("mocked column");
        when(mockedDimension.getValue()).thenReturn("mocked_value");
        final List<Dimension> mockedDimensions = Arrays.asList(new Dimension[] { mockedDimension });
        when(mockedWhere.getDimensions()).thenReturn(mockedDimensions);

        final String test = query.generateMDX(false);
        final String expected = "SELECT {{}} ON ROWS , {{{}}} ON COLUMNS FROM null WHERE ([mocked column].mocked_value)"; // everything
        // is
        // mocked
        assertNotNull(test);
        assertThat(test, equalTo(expected));
        verify(query,times(2)).getFilteredDimension(ArgumentMatchers.any(MdxEntry.class));
    }

    @Test
    public void testGenerateMDXWithDynamicColumnsParameter() {
        final Dimension mockedDimension = mock(Dimension.class);
        when(mockedDimension.getValue()).thenReturn("mocked_value");
        when (mockedDimension.getType()).thenReturn(Type.ROWS);
        final List<Dimension> mockedDimensions = Arrays.asList(new Dimension[] { mockedDimension });
        when(mockedCols.getDimensions()).thenReturn(mockedDimensions);

        final String test = query.generateMDX(true);
        final String expected = "SELECT {{}} ON ROWS , {{{mocked_value}}} ON COLUMNS FROM null"; // everything
        // is
        // mocked
        assertNotNull(test);
        assertThat(test, equalTo(expected));
    }

    @Test public void testCopy() {
        final Query test = query.copy();
        assertNotNull(test);
        //        assertThat(test, equalTo(query)); // questo non puo'funzionare perche'i dati sono moccati ...
    }

    @Test(expected = NullPointerException.class) 
    public void testGetFilteredDimensionThrowsExceptionWhenNullDimension() {
        query.getFilteredDimension(null);
    }

    @Test 
    public void testGetFilteredDimensionReturnsDataWhenNoFiltersAvailable() {
        final Dimension mockedDimension = mock(Dimension.class);
        when(mockedDimension.getValue()).thenReturn("mocked_value");
        when(mockedDimension.getType()).thenReturn(Type.COLUMNS);
        final List<Dimension> mockedDimensions = Arrays.asList(new Dimension[] { mockedDimension });
        when(mockedCols.getDimensions()).thenReturn(mockedDimensions);

        final StringBuilder test = query.getFilteredDimension(mockedCols);
        final String expected = "{{mocked_value}}";
        assertNotNull(test);
        assertThat(test.toString(), equalTo(expected));
    }

    @Test 
    public void testGetFilteredDimensionReturnsDataWhenFiltersAvailable() {
        final Dimension mockedDimension = mock(Dimension.class);
        when(mockedDimension.getColumn()).thenReturn("mocked column");
        when(mockedDimension.getValue()).thenReturn("mocked value");
        when(mockedDimension.getType()).thenReturn(Type.COLUMNS);
        final List<Dimension> mockedDimensions = Arrays.asList(new Dimension[] { mockedDimension });
        when(mockedCols.getDimensions()).thenReturn(mockedDimensions);

        final Filter mockedFilter = mock(Filter.class);
        when(mockedFilter.getColumn()).thenReturn("mocked column");
        when(mockedFilter.getFilter()).thenReturn("mocked filter");

        final List<? extends Filter> list = Arrays.asList(new Filter[] {mockedFilter});
        query.setFilters(list);

        final StringBuilder test = query.getFilteredDimension(mockedCols);
        final String expected = "{{FILTER ({mocked value},(mocked filter))}}";
        assertNotNull(test);
        assertThat(test.toString(), equalTo(expected));
    }
}
