package com.axiante.mui.webapp.webservice.util.pianificazione;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public abstract class ColumnDefTest {
    protected String readJsonAsString(String filename) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource(filename).getFile());
        return mapper.readTree(file).toString();
    }
}
