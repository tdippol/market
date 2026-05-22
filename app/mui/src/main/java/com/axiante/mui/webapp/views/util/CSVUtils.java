package com.axiante.mui.webapp.views.util;

import com.axiante.mui.persistence.entity.AuditLogEntity;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Slf4j
public class CSVUtils {

    //CSV constants
    private static final char CR = (char) 0x0D; //CSV carriage return
    private static final char LF = (char) 0x0A; //CSV line feed
    private static final String CRLF = new StringBuilder().append(CR).append(LF).toString();
    private static final String CSV_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss.SSS";
    public static final int PAGESIZE = 100;
    public static final int RESULTS_MAX_LIMIT = 100000;
    private static final DateFormat CSV_DATE_DATEFORMAT = new SimpleDateFormat(CSV_DATE_FORMAT);
    private static final int CSV_AUDIT_LOG_COLUMNS = 4;
    private static final String AUDIT_LOG_CSV_FILENAME = "listaAuditLog.csv";

    /**
     * Questo metodo crea la lista di righe di un file CSV separate dai caratteri CR(carriage return) e LF(line feed)
     * TODO separatore dei dati
     *
     * @param auditLogEntities
     * @param dataLines
     * @param csvData
     */
    public void composeCsvDataRows(final List<AuditLogEntity> auditLogEntities, List<String[]> dataLines, AtomicReference<String> csvData) {
        auditLogEntities.stream().forEach(log -> {
            List<String> row = new ArrayList<>();
            row.add(CSV_DATE_DATEFORMAT.format(log.getLogDate()));
            row.add(log.getUserName());
            row.add(log.getAction());
            row.add(log.getPath());
            String[] itemsArray = new String[row.size()];
            dataLines.add(row.toArray(itemsArray));
        });

        dataLines.stream()
                .map(this::convertToCSVRow)
                .filter(Objects::nonNull)
                .forEach(row -> csvData.set(new StringBuilder().append(csvData.get()).append(CRLF).append(row).toString()));
    }

    /**
     * Questo metodo converte una string in uno stream di byte e crea un file CSV rappresentato da un oggetto StreamedContent
     *
     * @param csvData
     * @return
     */
    public StreamedContent createExportCsv(final AtomicReference<String> csvData) {
        try {
            final InputStream stream = new ByteArrayInputStream(csvData.get().trim().getBytes("UTF-8"));
            return new DefaultStreamedContent(stream, "text/csv; charset=UTF-8", AUDIT_LOG_CSV_FILENAME);
        } catch (UnsupportedEncodingException e) {
            log.error("Error convert audit log to bytes", e);
        }
        return null;
    }

    /**
     * Questo metodo converte una array di stringhe, contenente le colonne in una riga di un file CSV
     *
     * @param data
     * @return
     */
    private String convertToCSVRow(String[] data) {
        if (data.length != CSV_AUDIT_LOG_COLUMNS) {
            log.warn(String.format("CSV row %s has a wrong number of data. The correct number of data is %s", Arrays.toString(data), CSV_AUDIT_LOG_COLUMNS));
            return null;
        }
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    /**
     * Questo metodo gestisce i caratteri speciali problematici per un file csv.
     *
     * @param data
     * @return
     */
    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
