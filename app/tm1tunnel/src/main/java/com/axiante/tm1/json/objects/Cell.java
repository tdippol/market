package com.axiante.tm1.json.objects;

import com.axiante.mui.common.utility.CellNameMapper;
import com.axiante.mui.common.utility.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Cell {
    private String value;
    private Boolean consolidated = Boolean.FALSE;
    private Boolean editable = Boolean.FALSE;
    private List<String> pickList;
    private Integer ordinal;
    private Boolean isHeader = Boolean.FALSE;
    private String name;
    private String caption;
    private Boolean hasPickList = Boolean.FALSE;
    private Integer columPosition;
    private Map<String, LinkedList<CellAttribute>> attributes = new LinkedHashMap<String, LinkedList<CellAttribute>>();
    private boolean fromConfiguration = false;
    private AtomicInteger attributeIndex = new AtomicInteger(-1);
    // this is needed to get the picklist on demand from the front-end
    private Integer gridColumn;

    public static synchronized Cell createHeader(String value) {
        final Cell cell = new Cell();
        cell.setIsHeader(true);
        cell.setName(value);
        cell.setCaption(value);
        return cell;
    }

    public static synchronized Cell createHeaderFromConfiguration(String value) {
        final Cell cell = new Cell();
        cell.setIsHeader(true);
        cell.setName(value);
        cell.setCaption(value);
        cell.setFromConfiguration(true);
        return cell;
    }

    /**
     * the second bit of the number contains 1 if editable thus
     * 
     * <pre>
     * number & 2
     * </pre>
     * 
     * returns 2 if editable. Eg. </br>
     * 
     * <pre>
     *  
     * 	123456 = 11110001001000000
     *  2 = 10
     *  123456 & 2 = 11110001001000000 & 10 = 0
     *  11110001001000000
     *  00000000000000010
     *  -----------------
     *  00000000000000000
     *  
     *  123458 = 11110001001000010
     *  2 = 10
     *  123456 & 2 = 11110001001000010 & 10 = 00000000000000010 = 2
     *  11110001001000010
     *  00000000000000010
     *  -----------------
     *  00000000000000010
     * </pre>
     * 
     * @param update
     */
    public void calculateUpdate(Long update) {
        // the second bit contains 1 if editable, thus the
        this.editable = ((update & 2) == 2);
        log.debug("calculating (" + update + " & 2) == 2 ... " + this.editable);
    }

    public void addPickElement(String element) {
        synchronized (this.isHeader) { // <-- lazy bastard ....
            if (this.pickList == null) {
                this.pickList = Collections.synchronizedList(new ArrayList<String>());
            }
        }
        if (element == null) {
            this.pickList.add("null");
        } else {
            this.pickList.add(element);
        }
    }

    public void addAttribute(String key, String value) {
        LinkedList<CellAttribute> values = attributes.get(key);
        if (values == null)
            values = new LinkedList<CellAttribute>();
        CellAttribute attribute = new CellAttribute(attributeIndex.incrementAndGet(), value);
        values.add(attribute);
        attributes.put(key, values);
    }

    public List<CellAttribute> getAttribute(String key) {
        return attributes.get(key).stream().collect(Collectors.toList());
    }

    @Deprecated
    public String getAgGridCell(final String cellSetName, final int actualDataIndex) {
        return getAgGridCell();
    }

    public String getAgGridCell() {
        /*
         * Updateable PicklistValues HasPicklist Value Consolidated
         */

        /*
         * per tutto quello che arriva da 0 a actualDataIndex
         * 
         * nomeColonna:{attr/valore}
         * 
         * per tutto quello da actualDataIndex
         * nomeHierarchiAxes0$nomeColonna:{att/valore}
         */
        StringBuilder builder = new StringBuilder();
        try {
            StringBuilder attributes = new StringBuilder();
            if (getName() == null) {
                builder.append(
                        JsonUtils.getMapper().writeValueAsString("cella " + getOrdinal() + " / " + getColumPosition()));
            } else {
                builder.append(JsonUtils.getMapper().writeValueAsString(CellNameMapper.map2agGrid(getName())));
            }
            builder.append(":").append("{");

            List<String> appoggio = getAttributes().keySet().stream().filter(e -> {
                return e != null && e.trim().length() > 0;
            }).collect(Collectors.toList());
            List<String> attributesList = new ArrayList<String>();
            for (String key : appoggio) {
                for (CellAttribute attribute : getAttribute(key)) {
                    attributesList.add(JsonUtils.getMapper().writeValueAsString(CellNameMapper.map2agGrid(key)) + ":"
                            + JsonUtils.getMapper().writeValueAsString(attribute.getValue()));
                }
            }
            appoggio = null;
            attributes.append(attributesList.stream().collect(Collectors.joining(",")));
            attributesList = null;
            if (attributes.length() > 0) {
                builder.append(attributes).append(",");
            }
            attributes = null;
            builder.append(JsonUtils.getMapper().writeValueAsString("Updateable")).append(":").append(getEditable())
                    .append(",").append(JsonUtils.getMapper().writeValueAsString("HasPicklist")).append(":")
                    .append(getHasPickList()).append(",").append(JsonUtils.getMapper().writeValueAsString("Value"))
                    .append(":").append(JsonUtils.getMapper().writeValueAsString(getValue())).append(",")
                    .append(JsonUtils.getMapper().writeValueAsString("Name")).append(":")
                    .append(JsonUtils.getMapper().writeValueAsString(getValue())).append(",")
                    .append(JsonUtils.getMapper().writeValueAsString("Consolidated")).append(":")
                    .append(getConsolidated());
            if (getHasPickList()) {
                builder.append(",").append(JsonUtils.getMapper().writeValueAsString("GridColumn")).append(":")
                        .append(JsonUtils.getMapper().writeValueAsString(getGridColumn()));
                if (pickList != null) {
                    builder.append(",").append(JsonUtils.getMapper().writeValueAsString("PicklistValues")).append(":[");
                    pickList.forEach(e -> {
                        try {
                            builder.append(JsonUtils.getMapper().writeValueAsString(e)).append(",");
                        } catch (JsonProcessingException e1) {
                            log.error("error processing picklist to json", e1);
                        }
                    });
                    builder.deleteCharAt(builder.length() - 1);
                    builder.append("]");
                }
            }
            builder.append("}");
        } catch (JsonProcessingException e) {
            log.error("Error processing object to json", e);
        }
        return builder.toString();
    }

    public void setName(@NonNull String nameToSet) {
        this.name = nameToSet;
    }

}
