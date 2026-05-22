package com.axiante.dbpromo.copier;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Migrator {

    private static final int DEFAULT_COMMITSIZE = 50000;
    private int COMMITSIZE = DEFAULT_COMMITSIZE;

    private String[] elements = {"viste", "anagrafica", "configurazione", "dati"};

    private String sourceSchema = null;
    private String destinationSchema = null;

    private String sequenceIncrement = null;

    private String compress = null;
    private String prepare = null;

    private boolean blobAsBinary = false;
    public static void main(String[] args) {
        new Migrator();
    }

    public Migrator() {
        Properties props = ApplicationProperties.getInstance().getProperties();
        sourceSchema = props.getProperty("source_use_schema", null);
        destinationSchema = props.getProperty("destination_use_schema", null);
        sequenceIncrement = props.getProperty("sequence_increment_script");
        compress = props.getProperty("compress_database");
        prepare = props.getProperty("prepare");
        String bb  = props.getProperty("blob2binary");
        String commitSize = props.getProperty("commit_size");
        if (compress != null && compress.toLowerCase().trim().equals("n")) {
            compress = null;
        }
        if (prepare != null && "false".equalsIgnoreCase(prepare)) {
            prepare = null;
        }
        if ( bb != null && "true".equalsIgnoreCase(bb)){
            blobAsBinary = true;
        }
        if ( commitSize != null ){
            try{
                Integer.valueOf(commitSize);
                COMMITSIZE = Integer.valueOf(commitSize);
            } catch (Exception e){
                COMMITSIZE = DEFAULT_COMMITSIZE;
            }
        } else {
            COMMITSIZE = DEFAULT_COMMITSIZE;
        }
        try (Connection source = ConnectionManager.getInstance().getSource();
             Connection destination = ConnectionManager.getInstance().getDestination();) {
            if ((source != null) && (destination != null)) {
                try {
                    if (prepare != null)
                        preapareDestination(destination);

                    for (String element : elements) {
                        System.out.println("####  " + element + "   ####");
                        Map<String, String> migrationElements = getTableMap(element);
                        List<String> tables = getTables(element, !"viste".equals(element));
                        for (String table : tables) {
                            if (table.trim().length() > 0) {
                                System.out.print("\t migrating " + table);
                                try {
                                    migrate(table, migrationElements.get(table), source, destination);
                                } catch (Exception te) {
                                    log.error("Error migrating table " + table, te);
                                }
                            }
                        }
                    }
                    if (ApplicationProperties.getInstance().getProperties().get("anonimize") != null) {
                        new Anonimizer().anonimize(destination);
                    }
                    if (sequenceIncrement != null) {
                        fixSequences(destination);
                    } else {
                        System.out.println("================================");
                        System.out.println("REMEMBER TO FIX SEQUENCES !!!!!!!");
                        System.out.println("================================");
                    }
                    compress(destination, getAllTables());
                } catch (Exception e) {
                    log.error("Error migrating database ", e);
                }
            }
        } catch (SQLException e1) {
            log.error("Error connecting to source or destination", e1);
        }
        System.out.println("done");
    }

    public boolean migrate(String sourceTable, String table, Connection source, Connection destination) throws Exception {
        if ("MUI_PROMOZIONE_PIANIFICAZIONE".equalsIgnoreCase(table)) {
            return migrateParentChild(sourceTable, table, "ID_PROMOZIONE_PIANIFICAZIONE", source, destination);
//        } else if ("ROLES".equalsIgnoreCase(table)) {
//            return migrateSingleAutocommit(sourceTable, table, source, destination);
        } else {
            return migrateSingle(sourceTable, table, source, destination);
        }
    }

    boolean migrateSingleAutocommit(String sourceTable, String table, Connection source, Connection destination) throws Exception {
        try (Statement originSt = source.createStatement();) {
            ResultSet origin = originSt.executeQuery(createSelect(sourceTable));
            ResultSetMetaData meta = origin.getMetaData();
            final boolean autocommit = destination.getAutoCommit();
            if (!autocommit) {
                destination.setAutoCommit(true);
            }
            long lines = countLines(source, sourceSchema == null ? sourceTable : sourceSchema + "." + sourceTable);
            System.out.print(" [ " + lines + " rows ]");
            try (PreparedStatement dest = destination.prepareStatement(createPreparedStatement(meta, table))) {
                int inserted = 0;
                while (origin.next()) {

                    for (int i = 1; i <= meta.getColumnCount(); ++i) {
                        Object o = origin.getObject(i);
                        if (o == null) {
                            switch (meta.getColumnType(i)){
                                case Types.BLOB:
                                    dest.setNull(i, Types.BINARY);
                                    break;
                                default:
                                    dest.setNull(i, meta.getColumnType(i));
                                    break;
                            }

                        } else {
                            switch (meta.getColumnType(i)) {
                                case Types.DECIMAL:
                                    dest.setBigDecimal(i, origin.getBigDecimal(i));
                                    break;
                                case Types.BIGINT:
                                case Types.INTEGER:
                                case Types.TINYINT:
                                case Types.SMALLINT:
                                    dest.setInt(i, origin.getInt(i));
                                    break;
                                case Types.NUMERIC:
                                    dest.setDouble(i, origin.getDouble(i));
                                    break;
                                case Types.BOOLEAN:
                                    dest.setBoolean(i, origin.getBoolean(i));
                                    break;
                                case Types.CHAR:
                                case Types.VARCHAR:
                                    dest.setString(i, origin.getString(i));
                                    break;
                                case Types.CLOB:
                                    java.sql.Clob aclob = (Clob) o;
                                    java.io.InputStream ip = aclob.getAsciiStream();
                                    String text = new BufferedReader(new InputStreamReader(ip, StandardCharsets.UTF_8)).lines()
                                            .collect(Collectors.joining("\n"));
                                    dest.setString(i, text);
                                    break;
                                case Types.BLOB:
                                    java.sql.Blob bclob = (Blob) o;
                                    java.io.InputStream bs = bclob.getBinaryStream();
                                    String tempFile = "temp" + System.currentTimeMillis();
                                    File file = File.createTempFile(tempFile, "tmp");
                                    OutputStream outputStream = new FileOutputStream(file);
                                    int byteRead = -1;
                                    while ((byteRead = bs.read()) != -1) {
                                        outputStream.write(byteRead);
                                    }
                                    outputStream.flush();
                                    outputStream.close();
                                    bs.close();
                                    FileInputStream fis = new FileInputStream(file);
                                    dest.setBinaryStream(i, fis, (int) file.length());
                                    break;
                                case Types.TIME:
                                case Types.TIME_WITH_TIMEZONE:
                                    dest.setTime(i, origin.getTime(i));
                                    break;
                                case Types.TIMESTAMP_WITH_TIMEZONE:
                                case Types.TIMESTAMP:
                                    dest.setTimestamp(i, origin.getTimestamp(i));
                                    break;
                                case Types.DATE:
                                    dest.setDate(i, origin.getDate(i));
                                    break;
                                default:
                                    log.error("Unhandled type " + meta.getColumnClassName(i));
                                    throw new IllegalArgumentException("Unhandled column type " + meta.getColumnType(i)
                                            + " mapped as java class " + meta.getColumnClassName(i));
                            }
                        }
                    }
                    dest.executeUpdate();
                }
            }
            System.out.println(" done!");
            destination.setAutoCommit(autocommit);
        }
        return true;
    }

    boolean migrateSingle(String sourceTable, String table, Connection source, Connection destination) throws Exception {

        try (Statement originSt = source.createStatement();) {
            ResultSet origin = originSt.executeQuery(createSelect(sourceTable));
            ResultSetMetaData meta = origin.getMetaData();
            final boolean autocommit = destination.getAutoCommit();
            if (autocommit) {
                destination.setAutoCommit(false);
            }
            int batches = 0;
            long lines = countLines(source, sourceSchema == null ? sourceTable : sourceSchema + "." + sourceTable);
            System.out.print(" [ " + lines + " rows ]");
            try (PreparedStatement dest = destination.prepareStatement(createPreparedStatement(meta, table))) {
                int inserted = 0;
                while (origin.next()) {
                    prepare(dest, origin, meta);
                    if (((++inserted) % COMMITSIZE) == 0) {
                        dest.executeBatch();
                        ++batches;
                        destination.commit();
                        inserted = 0;
                        System.out.print(".");
                        if ((batches % 50) == 0) {
                            double perc = (batches * COMMITSIZE * 100) / lines;
                            System.out.printf("%.2f%%%n", perc);
                            System.out.print("\t\t");
                        }
                    }
                }
                if (inserted > 0) {
                    dest.executeBatch();
                    ++batches;
                    destination.commit();
                }
            }
            System.out.println(" done!");
            destination.setAutoCommit(autocommit);
        }
        return true;
    }

    boolean migrateParentChild(String sourceTable, String table, String column, Connection source, Connection destination)
            throws Exception {

        // parents
        try (Statement originSt = source.createStatement();) {
            ResultSet origin = originSt.executeQuery(
                    createParentSelect(sourceSchema == null ? sourceTable : sourceSchema + "." + sourceTable, column));
            ResultSetMetaData meta = origin.getMetaData();
            final boolean autocommit = destination.getAutoCommit();
            if (autocommit) {
                destination.setAutoCommit(false);
            }
            int batches = 0;
            long lines = countLines(source, sourceSchema == null ? table : sourceSchema + "." + table);
            System.out.println("");
            System.out.print("\tparent lines [ " + lines + " rows ]");
            try (PreparedStatement dest = destination.prepareStatement(createPreparedStatement(meta,
                    destinationSchema == null ? table : destinationSchema + "." + table))) {
                int inserted = 0;
                while (origin.next()) {
                    prepare(dest, origin, meta);
                    if (((++inserted) % COMMITSIZE) == 0) {
                        dest.executeBatch();
                        ++batches;
                        destination.commit();
                        inserted = 0;
                        System.out.print(".");
                        if ((batches % 50) == 0) {
                            double perc = (batches * COMMITSIZE * 100) / lines;
                            System.out.printf("%.2f%%%n", perc);
                            System.out.print("\t\t");
                        }
                    }
                }
                if (inserted > 0) {
                    dest.executeBatch();
                    ++batches;
                    destination.commit();
                }
            }
            destination.commit();
            destination.setAutoCommit(autocommit);
        }
        // children
        try (Statement originSt = source.createStatement();) {
            ResultSet origin = originSt
                    .executeQuery(createChildSelect(sourceSchema == null ? table : sourceSchema + "." + table, column));
            ResultSetMetaData meta = origin.getMetaData();
            final boolean autocommit = destination.getAutoCommit();
            if (autocommit) {
                destination.setAutoCommit(false);
            }
            int batches = 0;
            long lines = countLines(source, sourceSchema == null ? table : sourceSchema + "." + table);
            System.out.println("");
            System.out.print("\tchild lines [ " + lines + " rows ]");
            try (PreparedStatement dest = destination.prepareStatement(createPreparedStatement(meta,
                    destinationSchema == null ? table : destinationSchema + "." + table))) {
                int inserted = 0;
                while (origin.next()) {
                    prepare(dest, origin, meta);
                    if (((++inserted) % COMMITSIZE) == 0) {
                        dest.executeBatch();
                        ++batches;
                        destination.commit();
                        inserted = 0;
                        System.out.print(".");
                        if ((batches % 50) == 0) {
                            double perc = (batches * COMMITSIZE * 100) / lines;
                            System.out.printf("%.2f%%%n", perc);
                            System.out.print("\t\t");
                        }
                    }
                }
                if (inserted > 0) {
                    dest.executeBatch();
                    ++batches;
                    destination.commit();
                }
            }
            System.out.println(" done!");
            destination.setAutoCommit(autocommit);
        }
        return true;
    }

    long countLines(Connection connection, String table) throws Exception {
        long lines = 0L;
        try (final Statement st = connection.createStatement();
             final ResultSet rs = st.executeQuery("Select count(*) from " + table);) {
            if (rs.next()) {
                lines = rs.getLong(1);
            }
        }
        return lines;
    }

    String createSelect(String table) {
        return "Select * from " + table;
    }

    String createParentSelect(String table, String column) {
        return "Select * from " + table + " WHERE " + column + " is null";
    }

    String createChildSelect(String table, String column) {
        return "Select * from " + table + " WHERE " + column + " in (select id from " + table + " where " + column
                + " is null)";
    }

    String createPreparedStatement(@NonNull final ResultSetMetaData data, @NonNull final String tablename)
            throws Exception {
        final StringBuffer buffer = new StringBuffer("Insert into ");
        final StringBuffer values = new StringBuffer(" values (");
        buffer.append(tablename).append(" (");
        for (int i = 1; i <= data.getColumnCount(); ++i) {
            buffer.append(data.getColumnName(i));
            values.append("?");
            if (i < data.getColumnCount()) {
                buffer.append(",");
                values.append(",");
            }
        }
        buffer.append(")").append(values).append(")");

        return buffer.toString();
    }

    PreparedStatement prepare(@NonNull final PreparedStatement st, @NonNull final ResultSet rs,
                              @NonNull final ResultSetMetaData data) throws Exception {
        for (int i = 1; i <= data.getColumnCount(); ++i) {
            Object o = rs.getObject(i);
            if (o == null) {
                if (blobAsBinary){
                    switch (data.getColumnType(i)) {
                        case Types.BLOB:
                            st.setNull(i, Types.BINARY);
                            break;
                        default:
                            st.setNull(i, data.getColumnType(i));
                            break;
                    }
                } else {
                    st.setNull(i, data.getColumnType(i));
                }
            } else {
                switch (data.getColumnType(i)) {
                    case Types.DECIMAL:
                        st.setBigDecimal(i, rs.getBigDecimal(i));
                        break;
                    case Types.BIGINT:
                    case Types.INTEGER:
                    case Types.TINYINT:
                    case Types.SMALLINT:
                        st.setInt(i, rs.getInt(i));
                        break;
                    case Types.NUMERIC:
                        st.setDouble(i, rs.getDouble(i));
                        break;
                    case Types.BOOLEAN:
                        st.setBoolean(i, rs.getBoolean(i));
                        break;
                    case Types.CHAR:
                    case Types.VARCHAR:
                        st.setString(i, rs.getString(i));
                        break;
                    case Types.CLOB:
                        java.sql.Clob aclob = (Clob) o;
                        java.io.InputStream ip = aclob.getAsciiStream();
                        String text = new BufferedReader(new InputStreamReader(ip, StandardCharsets.UTF_8)).lines()
                                .collect(Collectors.joining("\n"));
                        st.setString(i, text);
                        break;
                    case Types.BLOB:
                        java.sql.Blob bclob = (Blob) o;
                        java.io.InputStream bs = bclob.getBinaryStream();
                        String tempFile = "temp" + System.currentTimeMillis();
                        File file = File.createTempFile(tempFile, "tmp");
                        OutputStream outputStream = new FileOutputStream(file);
                        int byteRead = -1;
                        while ((byteRead = bs.read()) != -1) {
                            outputStream.write(byteRead);
                        }
                        FileInputStream fis = new FileInputStream(file);
                        st.setBinaryStream(i, fis, (int) file.length());
//					st.setBinaryStream(i, bs);
                        break;
                    case Types.TIME:
                    case Types.TIME_WITH_TIMEZONE:
                        st.setTime(i, rs.getTime(i));
                        break;
                    case Types.TIMESTAMP_WITH_TIMEZONE:
                    case Types.TIMESTAMP:
                        st.setTimestamp(i, rs.getTimestamp(i));
                        break;
                    case Types.DATE:
                        st.setDate(i, rs.getDate(i));
                        break;
                    default:
                        log.error("Unhandled type " + data.getColumnClassName(i));
                        throw new IllegalArgumentException("Unhandled column type " + data.getColumnType(i)
                                + " mapped as java class " + data.getColumnClassName(i));
                }
            }
        }
        st.addBatch();
        return st;
    }

    List<String> getTables(String type) {
        return getTables(type, true);
    }

    List<String> getTables(String type, boolean resolve) {
        Object s = ApplicationProperties.getInstance().getProperties().get(type);
        if (s != null) {
            if ("viste".equals(type) && resolve) {
                return Arrays.asList(s.toString().split(",")).stream().map(s1 -> ApplicationProperties.getInstance().getProperties().getProperty(s1)).filter(Objects::nonNull).collect(Collectors.toList());
            } else {
                return new ArrayList<>(Arrays.asList(s.toString().split(",")));
            }
        }
        return new ArrayList<>();
    }

    Map<String, String> getTableMap(String type) {
        Object s = ApplicationProperties.getInstance().getProperties().get(type);
        Map<String, String> ret = new HashMap<>();
        if (s != null) {
            if ("viste".equals(type)) {
                Arrays.asList(s.toString().split(",")).stream().forEach(s1 -> {
                    if (ApplicationProperties.getInstance().getProperties().getProperty(s1) != null)
                        ret.put(s1, ApplicationProperties.getInstance().getProperties().getProperty(s1));
                });
                return ret;
            } else {
                return Arrays.asList(s.toString().split(",")).stream().collect(Collectors.toMap(Function.identity(), Function.identity()));
            }
        }
        return null;
    }

    List<String> getDeleteSequence() {
        List<String> ret = new ArrayList<>();
        for (int i = elements.length - 1; i >= 0; --i) {
            final List<String> tables = getTables(elements[i]);
            ret.addAll(tables.stream().filter(s -> s.trim().length() > 0).sorted(Collections.reverseOrder()).collect(Collectors.toList()));
        }
        return ret;
    }

    boolean preapareDestination(Connection con) throws Exception {
        System.out.println("Preparing destination database ...");
        boolean ret = false;
        final String deleteNoSchema = "DELETE FROM %s";
        String delete = "DELETE FROM %s";
        if (destinationSchema != null) {
            delete = "DELETE FROM " + destinationSchema + ".%s";
        }
        try (Statement st = con.createStatement()) {
            for (String table : getDeleteSequence()) {
                System.out.print("\tcleaning " + table + "... ");
                log.debug("cleaning " + table);
                if (!truncate(con, table)) {
                    System.out.print("[using delete because of constraints]");
                    if (table.contains(".")) {
                        st.executeUpdate(String.format(deleteNoSchema, table));
                    } else {
                        st.executeUpdate(String.format(delete, table));
                    }
                }
                System.out.println("done!");
            }
            ret = true;
        }
        return ret;
    }

    private boolean truncate(Connection con, String table) {
        try (Statement st = con.createStatement()) {
            st.executeUpdate("TRUNCATE TABLE " + table);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    List<String> getAllTables() {
        return getDeleteSequence().stream().filter(Objects::nonNull).filter(s -> s.trim().length() > 0)
                .collect(Collectors.toList());
    }

    private boolean compress(Connection connection, List<String> tables) throws Exception {
        if (compress == null) {
            return true;
        }
        System.out.println("####  compressing   ####");
        String schema = "APP";
        if (destinationSchema != null) {
            schema = destinationSchema;
        }
        try (CallableStatement st = connection.prepareCall("call SYSCS_UTIL.SYSCS_COMPRESS_TABLE(?,?,0)")) {
            ;
            for (String table : tables) {
                st.setString(1, schema.trim());
                st.setString(2, table.trim());
                st.executeUpdate();
            }
        }
        return true;
    }

    void fixSequences(Connection destination) {
        System.out.println("####  aligning sequences   ####");
        List<String> tables = Arrays.asList(
                ApplicationProperties.getInstance().getProperties().getProperty("tables.id.list", "").split(","));
        List<String> simpleId = Arrays.asList(
                ApplicationProperties.getInstance().getProperties().getProperty("tables.id.simple", "").split(","));
        String id = null;
        String schema = destinationSchema == null ? "APP" : destinationSchema;
        String sequence = null;
        for (String table : tables) {
            if (simpleId.contains(table)) {
                id = "ID";
            } else {
                id = "ID_" + table.toUpperCase();
            }
            sequence = ApplicationProperties.getInstance().getProperties().getProperty(table, null);
            if (sequence != null) {
                long value = getMax(destination, id, table, schema);
                if (value > 0) {
                    System.out.println(String.format("SETTING %s TO %d", sequence, value));
                    updateSequence(destination, sequence, schema, value);
                }
            }

        }

    }

    long getMax(Connection con, String id, String table, String schema) {
        String query = String.format("SELECT MAX(%s) FROM %s.%s", id, schema, table);

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(String.format(query, id, schema, table));) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    void updateSequence(Connection con, String sequence, String schema, long value) {
        // String seq = schema + "." + sequence;
        long result = 0;
        String query = String.format(sequenceIncrement, schema, sequence);
        try (PreparedStatement pst = con.prepareStatement(query)) {
            while (result <= value) {
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getLong(1);
                } else {
                    return;
                }
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
