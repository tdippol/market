package com.axiante.mui.webapp.views.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.persistence.entity.AuditLogEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.primefaces.model.StreamedContent;

@RunWith(MockitoJUnitRunner.class)
public class CSVUtilsTest {

    @Spy
    @InjectMocks
    private CSVUtils csvUtils;

    @Test
    public void composeCsvDataRows() {
        final List<AuditLogEntity> logs = new ArrayList<>();
        final Date date1 = new GregorianCalendar(2021, Calendar.AUGUST, 1).getTime();
        final Date date2 = new GregorianCalendar(2021, Calendar.AUGUST, 2).getTime();
        final Date date3 = new GregorianCalendar(2021, Calendar.AUGUST, 3).getTime();
        logs.add(mockAuditLog(date1, "Luke", "foo-action", "foo-path"));
        logs.add(mockAuditLog(date2, "Leia", "bar-action", "bar-path"));
        logs.add(mockAuditLog(date3, "Han", "baz-action", "baz-path"));
        final List<String[]> dataLines = new ArrayList<>();
        final AtomicReference<String> csvData = new AtomicReference<>("");
        csvUtils.composeCsvDataRows(logs, dataLines, csvData);
        assertEquals(3, dataLines.size());
        assertArrayEquals(new String[] { "01/08/2021 00:00:00.000", "Luke", "foo-action", "foo-path" }, dataLines.get(0));
        assertArrayEquals(new String[] { "02/08/2021 00:00:00.000", "Leia", "bar-action", "bar-path" }, dataLines.get(1));
        assertArrayEquals(new String[] { "03/08/2021 00:00:00.000", "Han", "baz-action", "baz-path" }, dataLines.get(2));
        final String expectedCsvData = "\r\n01/08/2021 00:00:00.000,Luke,foo-action,foo-path"
                + "\r\n02/08/2021 00:00:00.000,Leia,bar-action,bar-path"
                + "\r\n03/08/2021 00:00:00.000,Han,baz-action,baz-path";
        assertEquals(expectedCsvData, csvData.get());
    }

    @Test
    public void composeCsvDataRows_givenSpecialChars_shouldReplaceThem() {
        final List<AuditLogEntity> logs = new ArrayList<>();
        final Date date1 = new GregorianCalendar(2021, Calendar.AUGUST, 1).getTime();
        final Date date2 = new GregorianCalendar(2021, Calendar.AUGUST, 2).getTime();
        final Date date3 = new GregorianCalendar(2021, Calendar.AUGUST, 3).getTime();
        logs.add(mockAuditLog(date1, "Luke,Skywalker", "foo-action", "foo-path"));
        logs.add(mockAuditLog(date2, "Leia 'Skywalker'", "bar-action", "bar-path"));
        logs.add(mockAuditLog(date3, "Han \"Solo\"", "baz-action", "baz-path"));
        final List<String[]> dataLines = new ArrayList<>();
        final AtomicReference<String> csvData = new AtomicReference<>("");
        csvUtils.composeCsvDataRows(logs, dataLines, csvData);
        assertEquals(3, dataLines.size());
        assertArrayEquals(new String[] { "01/08/2021 00:00:00.000", "Luke,Skywalker", "foo-action", "foo-path" }, dataLines.get(0));
        assertArrayEquals(new String[] { "02/08/2021 00:00:00.000", "Leia 'Skywalker'", "bar-action", "bar-path" }, dataLines.get(1));
        assertArrayEquals(new String[] { "03/08/2021 00:00:00.000", "Han \"Solo\"", "baz-action", "baz-path" }, dataLines.get(2));
        final String expectedCsvData = "\r\n01/08/2021 00:00:00.000,\"Luke,Skywalker\",foo-action,foo-path"
                + "\r\n02/08/2021 00:00:00.000,\"Leia 'Skywalker'\",bar-action,bar-path"
                + "\r\n03/08/2021 00:00:00.000,\"Han \"\"Solo\"\"\",baz-action,baz-path";
        assertEquals(expectedCsvData, csvData.get());
    }

    @Test
    public void createExportCsv() {
        final AtomicReference<String> csvData = new AtomicReference<>(
                "\r\n01/08/2021 00:00:00,\"Luke,Skywalker\",foo-action,foo-path");
        final StreamedContent sc = csvUtils.createExportCsv(csvData);
        assertNotNull(sc);
        assertEquals("text/csv; charset=UTF-8", sc.getContentType());
        assertEquals("listaAuditLog.csv", sc.getName());
    }

    private AuditLogEntity mockAuditLog(Date date, String username, String action, String path) {
        final AuditLogEntity mock = mock(AuditLogEntity.class);
        when(mock.getLogDate()).thenReturn(date);
        when(mock.getUserName()).thenReturn(username);
        when(mock.getAction()).thenReturn(action);
        when(mock.getPath()).thenReturn(path);
        return mock;
    }
}