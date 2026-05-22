package com.axiante.tm1.mdx;

import com.axiante.utility.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
public class Command implements Serializable {
	String name; // Cube
	List<Command> children = Collections.synchronizedList(new ArrayList<Command>());
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Command parent;

	private String operation;

	public static final List<String> HEADERONLYLIST = Arrays.asList("Axes", "Hierarchies", "Name", "Tuples",
			"UniqueName", "Members");

	private Command() {

	}

	private Command(String name, String operation) {
		// cannot be instantiated
		this.name = name;
		this.operation = operation;
	}

	public static Command createRootContainer(@NonNull final String request) {
		return new Command(Constants.ROOT_COMMAND, null).parse(request);
	}

	public static Command createRootContainer() {
		return new Command(Constants.ROOT_COMMAND, null);
	}

	public synchronized Command addChildContainer(String name) {
		return addChildContainer(name, null);
	}

	public synchronized Command addChildContainer(String name, String operation) {
		Command container = new Command(name, operation);
		this.children.add(container);
		container.parent = this;
		return container;
	}

	public void addChild(@NonNull final Command container) {
		synchronized (container) {
			if (container.isEmpty()) {
				throw new IllegalArgumentException("cannot insert an empty command");
			}
			if (container.isRootContainer()) {
				throw new IllegalArgumentException("cannot insert a ROOT Container");
			}
			this.children.add(container);
		}
	}

	public Command addChild(@NonNull final Command... children) {
		synchronized (children) {
			for (Command c : children) {
				addChild(c);
			}
		}
		return this;
	}

	public synchronized Command parse(@NonNull String commandString) {
		StringBuffer buf = new StringBuffer();
		String comando = null;
		Command currentContainer = this;
		Command child = null;
		for (char c : commandString.toCharArray()) {
			switch (c) {
			case ',':
				child = currentContainer.addChildContainer(buf.toString(), comando);
				comando = null;
				buf.delete(0, buf.length());
				break;
			case '(':
				// cominciano i figli
				child = currentContainer.addChildContainer(buf.toString(), comando);
				currentContainer = child;
				comando = null;
				buf.delete(0, buf.length());
				break;
			case ')':
				// finiscono i figli
				child = currentContainer.addChildContainer(buf.toString(), comando);
				comando = null;
				buf.delete(0, buf.length());
				currentContainer = currentContainer.getParent();
				break;
			case '$':
				// comincia un comando
				buf.append(c);
				break;
			case '=':
				// finisce un comando
				comando = buf.toString();
				buf.delete(0, buf.length());
				break;
			default:
				// accumula
				buf.append(c);
				break;
			}
		}
		;
		return currentContainer;
	}

	public StringBuffer generateRequestForCellset(@NonNull final StringBuffer buffer, @NonNull final String cellsetId) {
		return generateRequestForCellset(buffer, cellsetId, null);
	}

	public StringBuffer generateRequestForCellset(@NonNull final StringBuffer buffer, @NonNull final String cellsetId,
			List<String> include) {
		boolean includeMatch = true;
		if (buffer.length() == 0) {
			buffer.append("Cellsets('").append(cellsetId).append("')?$expand=");
		}
		if (Constants.ROOT_COMMAND.equalsIgnoreCase(name)) {
			// parse children
			for (Command container : children) {
				if (buffer.length() > 0 && buffer.charAt(buffer.length() - 1) != ','
						&& buffer.charAt(buffer.length() - 1) != '=') {
					buffer.append(",");
				}
				container.generateRequest(buffer);
			}
			if (buffer.length() > 0 && buffer.charAt(0) == ',') {
				buffer.delete(0, 1); // remove first occurrence of ","
			}
		} else {
			if (include != null && include.contains(name)) {
				includeMatch = true;
			} else {
				includeMatch = (include == null);
			}
			if (includeMatch) {
				if (operation != null) {
					buffer.append(operation).append("=");
				}
				buffer.append(name);
				if (children.size() > 0) {
					buffer.append("(");
					for (Command container : children) {
						container.generateRequest(buffer, include);
						if (buffer.charAt(buffer.length() - 1) != ',') {
							buffer.append(",");
						}
					}
					buffer.delete(buffer.length() - 1, buffer.length());
					buffer.append(")");
				}
			}
		}
		return buffer;
	}

	public StringBuffer generateRequest(@NonNull final StringBuffer buffer) {
		return generateRequest(buffer, null);
	}

	public StringBuffer generateRequest(@NonNull final StringBuffer buffer, final List<String> include) {
		if (Constants.ROOT_COMMAND.equalsIgnoreCase(name)) {
			// parse children
			for (Command container : children) {
				if (buffer.length() > 0 && buffer.charAt(buffer.length() - 1) != ',') {
					buffer.append(",");
				}
				container.generateRequest(buffer);
			}
			if (buffer.length() > 0 && buffer.charAt(0) == ',') {
				buffer.delete(0, 1); // remove first occurrence of ","
			}
		} else {
			boolean includeMatch = true;
			if (include != null && include.contains(name)) {
				includeMatch = true;
			} else {
				includeMatch = (include == null);
			}
			if (includeMatch) {
				if (operation != null) {
					buffer.append(operation).append("=");
				}
				buffer.append(name);
				if (children.size() > 0) {
					buffer.append("(");
					for (Command container : children) {
						container.generateRequest(buffer, include);
						if (buffer.charAt(buffer.length() - 1) != ',') {
							buffer.append(",");
						}
					}
					buffer.delete(buffer.length() - 1, buffer.length());
					buffer.append(")");
				}

			}
		}
		return buffer;
	}

	public boolean isRootContainer() {
		return Constants.ROOT_COMMAND.equals(name);
	}

	public boolean isAxesContainer() {
		return "Axes".equals(name);
	}

	public boolean isOperationContainer() {
		if (isRootContainer()) {
			return false;
		}
		return operation != null;
	}

	public boolean isEmpty() {
		if (this.name != null) {
			return "".equals(this.name.trim());
		} else {
			return true;
		}
	}

	public boolean isExpandCommand() {
		if (isRootContainer() && children.size() > 0) {
			// check first child
			return children.get(0).isExpandCommand();
		} else {
			return isOperationContainer() && Constants.OPERATION_EXPAND.equals(operation);
		}
	}

	public boolean isSelectCommand() {
		if (isRootContainer() && children.size() > 0) {
			// check first child
			return children.get(0).isExpandCommand();
		} else {
			return isOperationContainer() && Constants.OPERATION_SELECT.equals(operation);
		}
	}

	/**
	 * copies the current command
	 * 
	 * @return
	 */
	public Command getCommandWithoudExpand() {
		Command copy = copy();
		if (copy.isExpandCommand()) {
			if (copy.isRootContainer()) {
				copy.getChildren().get(0).setOperation(null);
			} else {
				copy.setOperation(null);
			}
		}
		return copy;
	}

	public Command copy() {
		Command copy = Command.createRootContainer();
		if (isRootContainer()) {
			// ok
		} else {
			copy.setName(name);
			copy.setOperation(operation);
		}
		copyChildrenOn(copy);
		return copy;
	}

	private Command copyChildrenOn(final @NonNull Command copy) {
		for (Command command : children) {
			if (!command.isEmpty()) {
				copy.addChild(command.copy());
			}
		}
		return copy;
	}

	public Command getCube() {
		return getCommand(Constants.CUBE_COMMAND);
	}

	public Command getAxes(boolean withChildren) {
		Command command = getCommand(Constants.AXES_COMMAND);
		if (withChildren == false && command.getChildren() != null) {
			command.getChildren().clear();
		}
		return command;
	}

	public Command getAxes() {
		return getAxes(true);
	}

	public Command getTuples() {
		return getCommand(Constants.TUPLES_COMMAND);
	}

	public Command getCells() {
		return getCommand(Constants.CELLS_COMMAND);
	}

	public Command getCells(boolean withChildren) {
		Command command = getCommand(Constants.CELLS_COMMAND);
		if (withChildren == false) {
			command.getChildren().clear();
		}
		return command;
	}

	public Command getHierarchies() {
		return getCommand(Constants.HIERARCHIES_COMMAND);
	}

	/**
	 * returns a copy of the command specified in parameter
	 * 
	 * @param command
	 * @return
	 */
	private Command getCommand(@NonNull final String command) {
		return getCommand(command, true);
	}

	/**
	 * returns the command specified in
	 * 
	 * <pre>
	 * command
	 * </pre>
	 * 
	 * </br>
	 * if the value of
	 * 
	 * <pre>
	 * copy
	 * </pre>
	 * 
	 * is
	 * 
	 * <pre>
	 * true
	 * </pre>
	 * 
	 * then the command is copied in a new object, otherwise the command itself is
	 * returned
	 * 
	 * @param command
	 * @param copy
	 * @return
	 */
	public Command getCommand(@NonNull final String command, boolean copy) {
		if (command.trim().equals(name.trim())) {
			if (copy) {
				return getCommandWithoudExpand();
			} else {
				return this;
			}
		} else {
			Command ret = null;
			for (Command _command : children) {
				ret = _command.getCommand(command, copy);
				if (ret != null) {
					if (copy) {
						ret = ret.getCommandWithoudExpand();
					}
					break;
				}
			}
			return ret;
		}
	}

	public Command getCommandForCellset(String id, String command, boolean withChildren) {
		Command ret = null;
		if (id != null) {
			ret = Command.createRootContainer();
			ret.setName(Constants.CELLSET_COMMAND);
			ret.addChildContainer("'" + id + "'", null);

			Command children = getCommand(command);
			if (children != null) {
				for (Command child : children.getChildren()) {
					ret.addChild(child);
				}
			}
		}

		return ret;
	}

	public Command convert(String operation, String into) {
		Command copy = this.copy();
		copy = convert(copy, operation, into);
		return copy;
	}

	private Command convert(Command node, String operation, String into) {
		if (operation.equals(node.getOperation())) {
			node.setOperation(into);
		}
		for (Command child : node.getChildren()) {
			convert(child, operation, into);
		}
		return node;
	}

	public boolean containsSelection(final String getCurrentName) {
		return containsSelection(this, getCurrentName);
	}

	private boolean containsSelection(final Command command, final String getCurrentName) {
		boolean ret = false;
		if (command.getName().equalsIgnoreCase(getCurrentName)) {
			ret = true;
		} else {
			ret = command.getChildren().stream().filter(child -> {
				return containsSelection(child, getCurrentName);
			}).findAny().isPresent();
		}
		return ret;
	}

	public static Command createCommand(String name, String operation) {
		return new Command(name, operation);
	}
}
