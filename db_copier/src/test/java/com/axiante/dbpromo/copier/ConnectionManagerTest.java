package com.axiante.dbpromo.copier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConnectionManagerTest {

	@BeforeClass
	public static void checkConnectionIsNotInitialized() {
		assertFalse(ConnectionManager.getDestinationInitialized());
		assertFalse(ConnectionManager.getSourceInitialized());
	}

	@Test
	public void testConnectionManagerConnectsToSource() {
		assertNotNull(ConnectionManager.getInstance().getSource());
	}

	@Test
	public void testConnectionManagerConnectsToDestination() {
		assertNotNull(ConnectionManager.getInstance().getDestination());
	}

	@Test
	public void testConnectionManagerOpensLazyConnections() {
		Connection source = ConnectionManager.getInstance().getSource();
		Connection test = ConnectionManager.getInstance().getSource();
		assertEquals(source, test);
	}

	@Test
	public void testConnectionClosedStaysClosed() throws SQLException {
		Connection con = ConnectionManager.getInstance().getSource();
		con.close();
		Connection closed = ConnectionManager.getInstance().getSource();
		assertNotNull(closed);
		assertTrue(closed.isClosed());
	}

	@AfterClass
	public static void testResetConnection() throws SQLException {
		Connection source = ConnectionManager.getInstance().getSource();
		Connection destination = ConnectionManager.getInstance().getSource();
		assertNotNull(source);
		assertNotNull(destination);

		ConnectionManager.getInstance().resetConnections();
		assertFalse(ConnectionManager.getDestinationInitialized());
		assertFalse(ConnectionManager.getSourceInitialized());

		assertTrue(source.isClosed());
		assertTrue(destination.isClosed());
	}
}
