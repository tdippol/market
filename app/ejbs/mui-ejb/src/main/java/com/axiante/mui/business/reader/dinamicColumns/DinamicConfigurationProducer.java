package com.axiante.mui.business.reader.dinamicColumns;

import com.axiante.mui.backing.MuiToken;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import com.axiante.tm1.json.objects.Table;
import com.axiante.utility.configuration.ColumnDef;
import com.axiante.utility.configuration.DynamicColumnsSettings;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

@Slf4j
public class DinamicConfigurationProducer {

    private static final Pattern carriageReturn = Pattern.compile("\\\\n");

    public ColumnDef getDinamicConfigurationTree(Table t, DynamicColumnsSettings settings) {
        Row<Cell> headers = t.getHeaders();
        return getDinamicConfigurationTree(headers, settings, t.getActualDataIndex());
    }

    public ColumnDef getDinamicConfigurationTree(MuiToken muiToken, DynamicColumnsSettings settings) {
        return getDinamicConfigurationTree(muiToken.getHeaders(), settings, muiToken.getActualDataIndex());
    }

    protected ColumnDef getDinamicConfigurationTree(Row<Cell> headers, DynamicColumnsSettings settings,
                                                    int actualDataIndex) {

        final ColumnDefFactory factory = new ColumnDefFactory(settings);
        final ColumnDef tree = ColumnDef.createRootNode();
        headers.stream().skip(actualDataIndex).map(c -> factory.fromCell(c)).forEach(c -> tree.insert(c));

        return tree;
    }

    public StringBuilder generateConfiguration(final ColumnDef tree, final Integer parentLevel,
                                               DynamicColumnsSettings settings) {
        final StringBuilder builder = new StringBuilder();
        Map<String, String> headerDefaults = settings.getHeaderdefaults();
        String headerName = null;
        String headerField = null;

        if (tree.getChildren() != null) {
            for (Entry<String, ColumnDef> e : tree.getChildren().entrySet()) {
                if (e.getValue().getChildren() != null && e.getValue().getChildren().size() > 0) {
                    // sono un parent
                    headerName = e.getValue().getCustomHeaders()[parentLevel];
                    headerField = getHeaderName(e.getKey());
                    headerDefaults.put("headerName", headerName);
                    try {
                        if (builder.length() > 0) {
                            builder.append(",");
                        }
                        builder.append(createParentConfiguration(headerDefaults,
                                settings.getHeaderCustomTypes().get(headerField), parentLevel));
                    } catch (IOException e1) {
                        log.error("error creating parent configuration", e1);
                    }
                    builder.append(",\"children\": [");
                    builder.append(generateConfiguration(e.getValue(), parentLevel + 1, settings));
                    builder.append("]}");
                } else {
                    // sono una foglia
                    try {
                        if (builder.length() > 0 && builder.charAt(builder.length() - 1) != '[') {
                            builder.append(",");
                        }
                        builder.append(createLeafConfiguration(e.getValue()));
                    } catch (IOException e1) {
                        log.error("Error building dinamic configuration", e1);
                    }
                }
            }
        } else {
            log.error("Dynamic cols has no children!");
        }
        return builder;
    }

    private StringBuilder createLeafConfiguration(ColumnDef d) throws IOException {
        StringBuilder builder = null;
        if (d.getCustomHeaders() != null) {
            d.setHeaderName(d.getCustomHeaders()[d.getCustomHeaders().length - 1]);
        }
        try (StringWriter writer = new StringWriter()) {
            JsonUtils.getMapper().writeValue(writer, d.fixAgGridNames());
            builder = new StringBuilder(writer.getBuffer());
        }
        return builder;
    }

    private StringBuilder createParentConfiguration(Map<String, String> parentConfig, final ColumnDef d,
                                                    final Integer parentLevel) throws IOException {
        StringBuilder builder = null;
        Map<String, String> appoggio = new HashMap<String, String>();
        appoggio.putAll(parentConfig);
        try (StringWriter writer = new StringWriter()) {
            if (d != null) {
                mergeParentWithCustom(appoggio, d);
            }
            JsonUtils.getMapper().writeValue(writer, appoggio);
//            String parent = writer.getBuffer().toString().replaceAll("\\\\n", "\\n");// TODO improve replaceAll
            String parent = carriageReturn.matcher(writer.getBuffer().toString()).replaceAll("\\n");
            builder = new StringBuilder(parent);
            if (builder.length() > 1) {
                builder.delete(builder.length() - 1, builder.length());// remove last }
            }
        }
        return builder;
    }

    private Map<String, String> mergeParentWithCustom(Map<String, String> parentConfig, final ColumnDef d)
            throws JsonProcessingException {
        if (d.getHeaderClass() != null) {
            parentConfig.put("headerClass", d.getHeaderClass());
        }
        if (d.getOpenByDefault() != null) {
            parentConfig.put("openByDefault", d.getOpenByDefault().toString());
        }

        return parentConfig;
    }

    private String getHeaderName(@NonNull String string) {
        String[] temp = null;
        if (string.contains("$$")) {
            temp = string.split(Pattern.quote("$$"));
            string = temp[temp.length - 1];
        }
        if (string.contains("$")) {
            temp = string.split(Pattern.quote("$"));
            string = temp[temp.length - 1];
        }
        return string;
    }
}
