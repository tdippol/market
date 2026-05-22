package com.axiante.mui.persistence.dao.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DaoTestUtils {

	public static final String DRIVER = "org.h2.Driver";
	public static final String DATABASE_CREATE_URL = "jdbc:h2:mem:test;USER=sa;PASSWORD=sa;DB_CLOSE_DELAY=-1;INIT=CREATE SCHEMA IF NOT EXISTS MUIPROMO;MODE=ORACLE";
	public static final String DATABASE_DROP_URL = "jdbc:h2:mem:test;USER=sa;PASSWORD=sa";

	public static void dropDatabase() {
		try {
			Class.forName(DRIVER);
			try (Connection c = DriverManager.getConnection(DaoTestUtils.DATABASE_DROP_URL); Statement st = c.createStatement()) {
				st.execute("DROP SCHEMA IF EXISTS MUIPROMO");
			}
		} catch (SQLException | ClassNotFoundException e) {
			log.info("Error dropping database", e);
		}
	}

	public static boolean checkCreation() {
		try {
			Class.forName(DRIVER);
			try (Connection c = DriverManager.getConnection(DaoTestUtils.DATABASE_DROP_URL); Statement st = c.createStatement()) {
				st.execute("SELECT * FROM MUIPROMO.ROLES WHERE 1=0");
				return true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			return false;
		}
	}

	public static void createDatabase() {
		try {
			final Path path = Paths.get("src/test/resources/muipromo_db/create_test_schema.sql");

			final StringBuilder query = new StringBuilder();

			for (final String line : Files.readAllLines(path)) {
				query.append("\n").append(line);
			}

			Class.forName(DRIVER);
			try (final Connection c = DriverManager.getConnection(DaoTestUtils.DATABASE_CREATE_URL);
					final Statement stmnt = c.createStatement();) {
				final String[] queries = query.toString().split(";");
				boolean error = false;
				@SuppressWarnings("unused")
				String previousQuery = null;
				for (final String query1 : queries) {
					if (error) {
						break;
					}
					try {
						if (query1.trim().startsWith("--") && (countLines(query1) == 1)) {
							// comment
							log.debug("not submitting commented query \n" + query1);
						} else {
							stmnt.execute(query1);
							previousQuery = query1;
						}
					} catch (final SQLException e) {
						log.error("error executing sql \n" + query1, e);
						error = true;
					}
				}
			}
		} catch (SQLException | ClassNotFoundException | IOException e) {
			log.info("Error creating database", e);
		}
	}

	private static int countLines(final String str) {
		final String[] lines = str.split("\r\n|\r|\n");
		return lines.length;
	}
}
