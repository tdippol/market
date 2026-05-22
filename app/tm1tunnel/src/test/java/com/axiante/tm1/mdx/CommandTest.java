package com.axiante.tm1.mdx;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.utility.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandTest {

	@Spy
	Command mockedCommand;

	@Test
	public void testParse() {
		final String mdx = "Cube,Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName))),Cells($select=Ordinal,Value,FormattedValue,Consolidated,Updateable,RuleDerived,HasPicklist,PicklistValues)";
		Command command = Command.createRootContainer();
		command.parse(mdx);
		assertThat(command.getName(), equalTo(Constants.ROOT_COMMAND));
		assertThat(command.getChildren().get(0).getName(), equalTo("Cube"));
		assertThat(command.getChildren().get(1).getName(), equalTo("Axes"));
		Command axis = command.getChildren().get(1);
		assertThat(axis.getChildren().size(), equalTo(new Integer(4)));
	}

	@Test
	public void testOperationParse() {
		final String mdx = "$expand=Cube,Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName))),Cells($select=Ordinal,Value,FormattedValue,Consolidated,Updateable,RuleDerived,HasPicklist,PicklistValues)";
		Command command = Command.createRootContainer();
		command.parse(mdx);
		Command copy = command.getCommandWithoudExpand();
		final String test = "Cube,Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName))),Cells($select=Ordinal,Value,FormattedValue,Consolidated,Updateable,RuleDerived,HasPicklist,PicklistValues)";
		StringBuffer buffer = new StringBuffer();
		assertThat(copy.generateRequest(buffer).toString(), equalTo(test));
	}

	@Test
	public void testOperationParseAxis() {
		final String mdx = "$expand=Cube,Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName))),Cells($select=Ordinal,Value,FormattedValue,Consolidated,Updateable,RuleDerived,HasPicklist,PicklistValues)";
		Command command = Command.createRootContainer();
		command.parse(mdx);
		Command copy = command.getAxes();
		assertNotNull(copy);
		final String test = "Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName)))";
		StringBuffer buffer = new StringBuffer();
		assertThat(copy.generateRequest(buffer).toString(), equalTo(test));
	}

	@Test
	public void testOperationParseTuples() {
		final String mdx = "$expand=Cube,Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName))),Cells($select=Ordinal,Value,FormattedValue,Consolidated,Updateable,RuleDerived,HasPicklist,PicklistValues)";
		Command command = Command.createRootContainer();
		command.parse(mdx);
		Command copy = command.getTuples();
		assertNotNull(copy);
		final String test = "Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName))";
		StringBuffer buffer = new StringBuffer();
		assertThat(copy.generateRequest(buffer).toString(), equalTo(test));
	}

	@Test
	public void testOperationParseCells() {
		final String mdx = "$expand=Cube,Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName))),Cells($select=Ordinal,Value,FormattedValue,Consolidated,Updateable,RuleDerived,HasPicklist,PicklistValues)";
		Command command = Command.createRootContainer();
		command.parse(mdx);
		Command copy = command.getCells();
		assertNotNull(copy);
		final String test = "Cells($select=Ordinal,Value,FormattedValue,Consolidated,Updateable,RuleDerived,HasPicklist,PicklistValues)";
		StringBuffer buffer = new StringBuffer();
		assertThat(copy.generateRequest(buffer).toString(), equalTo(test));
	}

	@Test
	public void testOperationHierarchies() {
		final String mdx = "$expand=Cube,Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName))),Cells($select=Ordinal,Value,FormattedValue,Consolidated,Updateable,RuleDerived,HasPicklist,PicklistValues)";
		Command command = Command.createRootContainer();
		command.parse(mdx);
		Command copy = command.getHierarchies();
		assertNotNull(copy);
		final String test = "Hierarchies($select=Name)";
		StringBuffer buffer = new StringBuffer();
		assertThat(copy.generateRequest(buffer).toString(), equalTo(test));
	}

	@Test
	public void testCreateRootContainer() {
		Command c = Command.createRootContainer("");
		assertThat(c.getName(), equalTo(Constants.ROOT_COMMAND));
	}

	@Test
	public void testAddChildContainer() {
		Command c = Command.createRootContainer();
		Command child = c.addChildContainer("CHILD_CONTAINER");
		assertNotNull(c.getChildren());
		assertThat(c.getChildren().size(), equalTo(1));
		assertThat(child.getName(), equalTo("CHILD_CONTAINER"));
	}

	@Test
	public void testIsAxesContainer() {
		Command command = Command.createRootContainer().addChildContainer(Constants.AXES_COMMAND);
		assertThat(command.getName(), equalTo(Constants.AXES_COMMAND));
		assertTrue(command.isAxesContainer());
	}

	@Test
	public void testIsEmptyReturnsTrue() {
		Command command = Command.createRootContainer().addChildContainer(null);
		assertTrue(command.isEmpty());
		command = command.addChildContainer("");
		assertTrue(command.isEmpty());
	}

	@Test
	public void testGetCube() {
		Command cube = Command.createRootContainer().getCube();
		assertNull(cube);
		cube = Command.createRootContainer();
		cube = cube.addChildContainer(Constants.CUBE_COMMAND);
		assertThat(cube.getName(), equalTo(Constants.CUBE_COMMAND));
	}

	@Test
	public void testGenerateRequestForCellSet() {
		Command command = Command.createRootContainer();
		StringBuffer buffer = command.generateRequestForCellset(new StringBuffer(), "test-id");
		assertNotNull(buffer);
		assertTrue(buffer.length() >= 0);
//		assertThat(buffer.toString(), containsString("test-id"));
		assertThat(buffer.toString(), equalTo("Cellsets('test-id')?$expand="));
		final String mdx = "Cube,Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName))),Cells($select=Ordinal,Value,FormattedValue,Consolidated,Updateable,RuleDerived,HasPicklist,PicklistValues)";
		command.parse(mdx);
		buffer = command.generateRequestForCellset(new StringBuffer(), "test-id");
		assertThat(buffer.toString(), equalTo("Cellsets('test-id')?$expand=" + mdx));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddRootThrowsException() {
		Command command = Command.createRootContainer();
		command.addChild(command);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddEmptyChildThrowsException() {
		Command command = Command.createRootContainer();
		Command child = command.addChildContainer(null);
		command.addChild(child);
	}

	@Test
	public void testIsSelect() {
		Command command = Command.createRootContainer().addChildContainer(Constants.CUBE_COMMAND,
				Constants.OPERATION_SELECT);
		assertTrue(command.isSelectCommand());
		command = Command.createRootContainer();
		command.addChildContainer(Constants.CUBE_COMMAND, Constants.OPERATION_EXPAND);
		assertTrue(command.isSelectCommand());
	}

	@Test
	public void testGetAxesReturnsCorrectChildren() {
		final String mdx = "$expand=Cube,Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName))),Cells($select=Ordinal,Value,FormattedValue,Consolidated,Updateable,RuleDerived,HasPicklist,PicklistValues)";
		Command command = Command.createRootContainer();
		command.parse(mdx);
		Command copy = command.getAxes();
		assertThat(copy.getName(), equalTo(Constants.AXES_COMMAND));
		assertNotNull(copy.getChildren());
		assertTrue(copy.getChildren().size() > 0);

		copy = command.getAxes(false);
		assertTrue(copy.getChildren().size() == 0);
	}

	@Test
	public void testGetCellsReturnsCorrectData() {
		final String mdx = "$expand=Cube,Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName))),Cells($select=Ordinal,Value,FormattedValue,Consolidated,Updateable,RuleDerived,HasPicklist,PicklistValues)";
		Command command = Command.createRootContainer();
		command.parse(mdx);
		Command copy = command.getCells();
		assertNotNull(copy);
		assertThat(copy.getName(), equalTo(Constants.CELLS_COMMAND));
		assertTrue(copy.getChildren().size() > 0);
		copy = command.getCells(false);
		assertNotNull(copy);
		assertThat(copy.getName(), equalTo(Constants.CELLS_COMMAND));
		assertTrue(copy.getChildren().size() == 0);
	}

	@Test
	public void testGetCommandForCellsetReturnsCorrectData() {
		final String mdx = "$expand=Cube,Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name,Attributes/Alias,Type,UniqueName))),Cells($select=Ordinal,Value,FormattedValue,Consolidated,Updateable,RuleDerived,HasPicklist,PicklistValues)";
		Command command = Command.createRootContainer();
		command.parse(mdx);

		Command cells = command.getCommandForCellset("test", Constants.AXES_COMMAND, true);
		assertNotNull(cells);
		assertThat(cells.getName(), equalTo(Constants.CELLSET_COMMAND));
		assertTrue(cells.getChildren().size() > 0);
		Command ax = cells.getCommand(Constants.AXES_COMMAND, false);
		assertNull(ax); // check that the command does not contain axes
	}

	@Test(expected = NullPointerException.class)
	public void testAddChildThrowsExceptionWhenNullParameter() {
		Command.createRootContainer().addChild(mock(Command.class), null);
	}

	@Test
	public void testAddChildReturnsCorrectNumerOfChildren() {
		Command one = Command.createCommand("one", Constants.OPERATION_EXPAND);
		Command two = Command.createCommand("two", Constants.OPERATION_SELECT);
		mockedCommand.addChild(one, two);

		verify(mockedCommand).addChild(one);
		verify(mockedCommand).addChild(two);

		assertTrue(mockedCommand.getChildren().size() == 2);
	}

	@Test
	public void testConvertReturnsCorrectValues() {
		Command c = Command.createCommand("test", "mock");
		Command converted = c.convert("mock", "mick");

		assertThat(c, equalTo(c));// c has not been modified
		assertThat(c, not(equalTo(converted)));
		assertThat(converted.getOperation(), equalTo("mick"));
	}

	@Test
	public void testConvertDoesNotChangeCorrectValues() {
		Command c = Command.createCommand("test", "mock");
		Command converted = c.convert("mack", "mick");

		assertThat(c, equalTo(c));// c has not been modified
		assertThat(c, equalTo(converted)); // converted is equal to c
		assertThat(converted.getOperation(), equalTo("mock"));
	}

	@Test
	public void testConvertTraversesChildren() {
		Command c = Command.createCommand("test", "mock");
		c.addChildContainer("test child", "mock");
		c.addChildContainer("test child two", "mick");
		mockedCommand.addChild(c);
		Command test = mockedCommand.convert("mick", "mack");
		assertNotNull(test);
		assertNotNull(test.getChildren());
		assertTrue(test.getChildren().size() == 1);
		assertNotNull(test.getChildren().get(0));
		assertTrue(test.getChildren().get(0).getChildren().size() == 2);
		assertThat(test.getChildren().get(0).getChildren().get(1).getOperation(), equalTo("mack"));
	}

	@Test(expected = NullPointerException.class)
	public void testContainsSelectionThrowsExceptionWhenNoName() {
		mockedCommand.containsSelection("test");
	}

	@Test
	public void testContainsSelectionReturnsTrueWhenSingleNodeAndNameMatches() {
		String expected = "mock";
		when(mockedCommand.getName()).thenReturn(expected);
		assertTrue(mockedCommand.containsSelection(expected));
	}

	@Test
	public void testContainsSelectionReturnsFalseWhenSingleNodeAndNameNotMatches() {
		String expected = "mock";
		when(mockedCommand.getName()).thenReturn(expected);
		assertFalse(mockedCommand.containsSelection(expected + expected));
	}

	@Test
	public void testContainsSelectionReturnsTrueWhenMultipleNodesAndNameMatchesInChildren() {
		String expected = "mock";
		Command test = Command.createCommand("test root", "mack");
		test.addChildContainer("test child", "muck");
		test.addChildContainer(expected, "mick");

		assertTrue(test.containsSelection(expected));
	}

	@Test
	public void testContainsSelectionReturnsFalseWhenMultipleNodesAndNameNotMatchesInChildren() {
		String expected = "mock";
		Command test = Command.createCommand("test root", "mack");
		test.addChildContainer("test child", "muck");
		test.addChildContainer("test child 2", "mock");

		assertFalse(test.containsSelection(expected));
	}

}
