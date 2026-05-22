package com.axiante.dbpromo.copier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConnectionManager {
	private Connection source;
	private Connection destination;
	@Getter
	private static final ConnectionManager instance = new ConnectionManager();

	@Getter
	private static Boolean sourceInitialized = Boolean.FALSE;
	@Getter
	private static Boolean destinationInitialized = Boolean.FALSE;

	public Connection getSource() {
		synchronized (sourceInitialized) {
			if (!sourceInitialized) {
				ApplicationProperties p = ApplicationProperties.getInstance();
				try {
					source = createConnection(p.getProperties().getProperty("source_class"),
							p.getProperties().getProperty("source_url"), p.getProperties().getProperty("source_user"),
							p.getProperties().getProperty("source_password"));
				} catch (Exception e) {
					log.error("error connecting to source database", e);
				} finally {
					sourceInitialized = Boolean.TRUE;
				}
			}
		}
		return source;
	}

	public Connection getDestination() {
		synchronized (destinationInitialized) {
			if (!destinationInitialized) {
				ApplicationProperties p = ApplicationProperties.getInstance();
				try {
					destination = createConnection(p.getProperties().getProperty("destination_class"),
							p.getProperties().getProperty("destination_url"),
							p.getProperties().getProperty("destination_user"),
							p.getProperties().getProperty("destination_password"));
				} catch (Exception e) {
					log.error("error connecting to destination database", e);
				} finally {
					destinationInitialized = Boolean.TRUE;
				}
			}
		}
		return destination;
	}

	public void resetConnections() {
		synchronized (destinationInitialized) {
			if (destinationInitialized) {
				try {
					closeConnection(destination);
				} catch (Exception e) {
					log.warn("Error closing destination connection: there might be a resource leak !", e);
				} finally {
					destination = null;
					destinationInitialized = false;
				}
			}
		}
		synchronized (sourceInitialized) {
			if (sourceInitialized) {
				try {
					closeConnection(source);
				} catch (Exception e) {
					log.warn("Error closing source connection: there might be a resource leak !", e);
				} finally {
					source = null;
					sourceInitialized = false;
				}
			}
		}
	}

	private Connection createConnection(@NonNull final String driverName, @NonNull final String url,
			@NonNull final String username, @NonNull final String password) throws Exception {
		Class.forName(driverName);
		return DriverManager.getConnection(url, username, password);
	}

	private Boolean closeConnection(@NonNull final Connection con) throws SQLException {
		if (con.isClosed()) {
			return true;
		}
		con.close();
		return true;
	}
}
