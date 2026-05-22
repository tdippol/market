package com.axiante.mui.webapp.webservice.util.pianificazione;

import com.axiante.mui.webapp.webservice.util.ColumnDefUtils;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public abstract class AbstractColumnDef {
    protected String loadColumnDefFromFile(final String jsonFile, final String hiddenColumns, final String gridName,
                                           final String contesto, final boolean contestoRequired) {
        String json = "";
        try {
            if (!StringUtils.isBlank(contesto)) {
                // Contesto presente, tenta di caricare il file specifico del contesto
                json = loadColumnDef(String.format("%s%s%s", contesto, File.separator, jsonFile), gridName, hiddenColumns);
                if (json == null) {
                    if (contestoRequired) {
                        log.error(String.format("Unable to load columnDef JSON file '%s' from contesto '%s'; using default",
                                jsonFile, contesto));
                    }
                    json = loadColumnDef(jsonFile, gridName, hiddenColumns);
                    if (StringUtils.isBlank(json)) {
                        log.error(String.format("Unable to load default columnDef JSON file '%s'", jsonFile));
                    }
                }
            } else {
                // Contesto non presente, carica il file di default
                json = loadColumnDef(jsonFile, gridName, hiddenColumns);
                if (StringUtils.isBlank(json)) {
                    log.error(String.format("Unable to load default columnDef JSON file '%s'", jsonFile));
                }
            }
        } catch (Exception ex) {
            log.error(String.format("Error loading columnDef JSON file '%s' from contesto '%s'", jsonFile, contesto), ex);
        }
        return json;
    }

    private String loadColumnDef(String jsonFile, String gridName, String hiddenColumns) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFile);
        if (inputStream == null) {
            return null;
        }
        JsonNode columnDef = new ColumnDefUtils().applyHiddenColumns(inputStream, hiddenColumns, gridName);
        if (columnDef == null) {
            log.warn(String.format("Error applying hidden columns for grid '%s'", gridName));
            // Reload stream
            inputStream = getClass().getClassLoader().getResourceAsStream(jsonFile);
            if (inputStream == null) {
                log.error(String.format("Cannot load columnDef JSON file '%s'", jsonFile));
                return null;
            }
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
        }
        return columnDef.toString();
    }

    protected String getNullableValue(String value) {
        return value != null ? value : "";
    }

    protected String getFullDescription(String codice, String descrizione) {
        return codice != null && descrizione != null ? String.format("%s - %s", codice, descrizione) : "";
    }
}
