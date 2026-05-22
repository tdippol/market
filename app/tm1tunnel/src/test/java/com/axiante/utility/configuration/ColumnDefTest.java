package com.axiante.utility.configuration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedHashMap;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ColumnDefTest {

	@Spy ColumnDef columnDef;
	
	@Test public void testGetGenealogyReturnsNullWhenNothingSet() {
		assertNull(columnDef.getGenealogy());
	}
	
	@Test public void testGetGenealogyReturnsSingleFiledWhenSingleColumnPresent() {
		when(columnDef.getField()).thenReturn("mocked");
		String[] test = columnDef.getGenealogy();
		assertNotNull(test);
		assertThat(test.length,equalTo(1));
		assertThat(test[0], equalTo("mocked"));
	}
	
	@Test public void testGetGenealogyReturnsSameNumberOfFiledAsNumberOfColumnPresent() {
		when(columnDef.getField()).thenReturn("mock1$$mock2");
		String[] test = columnDef.getGenealogy();
		assertNotNull(test);
		assertThat(test.length,equalTo(2));
		assertThat(test[0], equalTo("mock1"));
		assertThat(test[1], equalTo("mock2"));
	}
	
	@Test public void testCloneWithoutHeaders() {
		assertNotNull(columnDef.clone());
		Random rand = new Random();
		ColumnDef expected = new ColumnDef();
		expected.headerName        = "mock";
		expected.field             = "mock";
		expected.width             = rand.nextInt();
		expected.editable          = rand.nextBoolean();
		expected.columnGroupShow   = "mock";
		expected.aggFunc           = "mock";
		expected.aggFuncParam      = "mock";
		expected.type              = new String[] {"mock"};
		expected.hide			  = rand.nextBoolean();
		expected.rowGroup 		  = rand.nextBoolean();
		expected.cellClass 		  = "mock";

		ColumnDef test = expected.clone();
		assertNotNull(test);
		assertThat(test,equalTo(expected));
	}
	
	@Test public void testCloneWithHeaders() {
		assertNotNull(columnDef.clone(true));
		ColumnDef expected = new ColumnDef();
		expected.headerClass       = "mock"     ;
		expected.openByDefault     = new Random().nextBoolean() ;
		
		ColumnDef test = expected.clone(true);
		assertNotNull(test);
		assertThat(test,equalTo(expected));
		

	}

	@Test public void testCreateRootNode() {
		ColumnDef rootNode = ColumnDef.createRootNode();
		assertTrue(rootNode.isRoot());
	}
	
	@Test public void testInsertReturnsNullWhenNotRootNode() {
		ColumnDef test = mock(ColumnDef.class);
		assertNull(columnDef.insert(test));
	}

	@Test public void testInsertReturnsSuccessWhenRootNode() {
		when(columnDef.isRoot()).thenReturn(true);
		ColumnDef insert = mock(ColumnDef.class);
		when(insert.getGenealogy()).thenReturn(new String[] {"only child"});
		ColumnDef test = columnDef.insert(insert);
		assertNotNull(test);
		assertThat(test, equalTo(insert));
		assertTrue(columnDef.getChildren().values().stream().anyMatch(d->d.equals(insert)));
	}
	@Test public void testInsertReturnsSuccessWhenRootNodeAndInsertingWithChild() {
		when(columnDef.isRoot()).thenReturn(true);
		LinkedHashMap<String, ColumnDef> childrenOfRoot = new LinkedHashMap<String, ColumnDef>();
		childrenOfRoot.put("child of root",mock(ColumnDef.class));
		when(columnDef.getChildren()).thenReturn(childrenOfRoot);
		ColumnDef insert = mock(ColumnDef.class);
		when(insert.getGenealogy()).thenReturn(new String[] {"only child"});
		ColumnDef test = columnDef.insert(insert);
		assertNotNull(test);
		assertThat(test, equalTo(insert));
		assertTrue(columnDef.getChildren().values().stream().anyMatch(d->d.equals(insert)));
	}

	@Test public void testGetInsertionNodeReturnsNullWhenNotRoot() {
		assertNull(columnDef.getInsertionNode(null));
		assertNull(columnDef.getInsertionNode(mock(ColumnDef.class)));
	}
	
	@Test public void testGetInsertionNodeReturnsItselfWhenInsertingOnlyChild() {
		when(columnDef.isRoot()).thenReturn(true);
		ColumnDef insert = mock(ColumnDef.class);
		when(insert.getGenealogy()).thenReturn(new String[] {"only child"});
		ColumnDef test = columnDef.getInsertionNode(insert);
		assertNotNull(test);
		assertThat(test, equalTo(columnDef));
	}

	@Test public void testGetInsertionNodeReturnsItselfWhenInsertingChildren() {
		when(columnDef.isRoot()).thenReturn(true);
		ColumnDef insert = mock(ColumnDef.class);
		when(insert.getGenealogy()).thenReturn(new String[] {"child one", "child two"});
		ColumnDef test = columnDef.getInsertionNode(insert);
		assertNotNull(test);
		ColumnDef expected = new ColumnDef();
		expected.setField("child one");
		assertThat(test, equalTo(expected));
	}
	
	@Test (expected = NullPointerException.class) 
	public void testMergeWithNullThrowsException() {
		columnDef.merge(null);
	}

	@Test (expected = NullPointerException.class) 
	public void testMergeHeadersWithNullThrowsException() {
		columnDef.merge(null, true);
	}

	@Test public void testMergeEmptyWithSomethingReturnsSomethingsData() {
		Random rand = new Random();
		ColumnDef expected = new ColumnDef();
		expected.headerName        = "mock";
		expected.field             = "mock";
		expected.width             = rand.nextInt();
		expected.editable          = rand.nextBoolean();
		expected.columnGroupShow   = "mock";
		expected.aggFunc           = "mock";
		expected.aggFuncParam      = "mock";
		expected.type              = new String[] {"mock"};
		expected.hide			  = rand.nextBoolean();
		expected.rowGroup 		  = rand.nextBoolean();
		expected.cellClass 		  = "mock";

		columnDef.merge(expected);
		assertThat(columnDef.getHeaderName()     ,equalTo(expected.getHeaderName()     ));
		assertThat(columnDef.getField()          ,equalTo(expected.getField()          ));
		assertThat(columnDef.getWidth()          ,equalTo(expected.getWidth()          ));
		assertThat(columnDef.getEditable()       ,equalTo(expected.getEditable()       ));
		assertThat(columnDef.getColumnGroupShow(),equalTo(expected.getColumnGroupShow()));
		assertThat(columnDef.getAggFunc()        ,equalTo(expected.getAggFunc()        ));
		assertThat(columnDef.getAggFuncParam()   ,equalTo(expected.getAggFuncParam()   ));
		assertThat(columnDef.getType()           ,equalTo(expected.getType()           ));
		assertThat(columnDef.getHide()		      ,equalTo(expected.getHide()		   ));
		assertThat(columnDef.getRowGroup()       ,equalTo(expected.getRowGroup()       ));
		assertThat(columnDef.getCellClass()      ,equalTo(expected.getCellClass()      ));
	}
	@Test public void testMergeHeadersEmptyWithSomethingReturnsSomethingsData() {
		ColumnDef expected = new ColumnDef();
		expected.headerClass       = "mock"     ;
		expected.openByDefault     = new Random().nextBoolean() ;

		columnDef.merge(expected, true);
		assertThat(columnDef.getHeaderClass()  ,equalTo(expected.getHeaderClass()  ));
		assertThat(columnDef.getOpenByDefault(),equalTo(expected.getOpenByDefault()));
	}

}
