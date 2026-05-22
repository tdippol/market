package com.axiante.dbpromo.copier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Anonimizer {

	public void anonimize(Connection con) throws SQLException {
		System.out.println("####  scrambling   ####");

		for (String table : getTablesToAnonimize()) {
			System.out.println("anonimize table " + table);
			try (Statement st = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s", table))) {
				new Operation(new Configuration(table)).anonimize(rs);
			}
		}
	}

	List<String> getTablesToAnonimize() {
		Object source = ApplicationProperties.getInstance().getProperties().get("anonimize");
		if (source == null) {
			return null;
		}
		return new ArrayList<String>(Arrays.asList(source.toString().split(",")));
	}

	class Configuration {
		private static final String FIELD = "anonimize.%s.field";
		private static final String TYPE = "anonimize.%s.type";
		private static final String SOURCE = "anonimize.%s.source";

		@Getter
		private String field;
		@Getter
		private String type;
		@Getter
		private String source;

		Configuration(@NonNull String table) {
			field = ApplicationProperties.getInstance().getProperties()
					.getProperty(String.format(FIELD, table.toLowerCase())).toString();
			type = ApplicationProperties.getInstance().getProperties()
					.getProperty(String.format(TYPE, table.toLowerCase())).toString();
			source = ApplicationProperties.getInstance().getProperties()
					.getProperty(String.format(SOURCE, table.toLowerCase())).toString();
		}

	}

	class Operation {
		Configuration conf;
		boolean isField = false;
		String radix = null;
		Map<Integer, List<String>> externalData;

		int min = -1;
		int max = -1;

		Operation(Configuration configuration) {
			this.conf = configuration;
			if (conf.getType().equals("external")) {
				loadExternalData();
			} else if (conf.getType().equals("auto")) {
				if (!conf.getSource().startsWith("\"")) {
					// e' un campo
					isField = true;
				}
				radix = conf.getSource().replaceAll("\"", "");
			}
		}

		void loadExternalData() {
			// anonimize.mui_compratore.source=cognomi.txt, nomi.txt
			String[] sources = conf.getSource().split(",");
			Integer index = -1;
			for (String source : sources) {
				load(source.trim(), ++index);
			}
			min = externalData.keySet().stream().min(Comparator.comparingInt(Integer::intValue)).orElse(-1);
			max = externalData.keySet().stream().max(Comparator.comparingInt(Integer::intValue)).orElse(-1);
		}

		void load(@NonNull String source, @NonNull Integer column) {
			if (externalData == null) {
				externalData = new HashMap<Integer, List<String>>();
			}
			try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(source);
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {
				Integer index = 1;
				List<String> data = null;
				while (reader.ready()) {
					data = pad(externalData.get(index), column);
					if (data == null) {
						data = new ArrayList<String>();
					}
					data.add(reader.readLine());
					externalData.put(index, data);
					++index;
				}
			} catch (IOException e) {
				log.error("errore durante la lettura della sorgente dati " + source, e);
			}
		}

		List<String> pad(List<String> data, Integer columns) {
			if (data == null) {
				data = new ArrayList<String>();
			}
			if (data.size() < columns) {
				for (Integer col = data.size(); col < columns - 1; ++col) {
					data.add("");
				}
			}
			return data;
		}

		void anonimize(ResultSet rs) throws SQLException {
			int precision = getFieldMaxLength(rs);
			while (rs.next()) {
				rs.updateString(conf.getField(), externalData == null ? scrambleFromColumn(rs, radix, precision)
						: scrambleFromExternal(precision));
				rs.updateRow();
			}
		}

		int getFieldMaxLength(ResultSet rs) throws SQLException {
			ResultSetMetaData meta = rs.getMetaData();
			for (int i = 1; i <= meta.getColumnCount(); ++i) {
				if (meta.getColumnName(i).equalsIgnoreCase(conf.getField())) {
					return meta.getPrecision(i);
				}
			}
			throw new SQLException("Unresolved column precision for " + conf.getField());
		}

		String scrambleFromColumn(ResultSet rs, String column, int precision) throws SQLException {
			// we expect the first columm to be the id
			if (isField) {
				return truncate(rs.getString(column) + "_" + rs.getInt(1), precision);
			} else {
				return truncate(column + "_" + rs.getInt(1), precision);
			}
		}

		String scrambleFromExternal(int precision) {
			List<String> data = externalData.get(getRandomNumber());
			if (data == null) {
				throw new RuntimeException("Corrupted data");
			}
			return truncate(data.stream().filter(Objects::nonNull).collect(Collectors.joining(" ")), precision);
		}

		String truncate(@NonNull String source, int size) {
			if (source.length() >= size) {
				return source.substring(size - 1);
			}
			return source;
		}

		int getRandomNumber() {
			return (int) ((Math.random() * (max - min)) + min);
		}
	}

}
