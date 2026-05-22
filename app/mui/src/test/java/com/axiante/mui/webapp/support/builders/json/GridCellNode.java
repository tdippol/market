package com.axiante.mui.webapp.support.builders.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@Builder
public class GridCellNode {
    private String name;
    private String value;
    private String type;
    private boolean editable;
    private boolean nullable;
    @Singular
    private Map<String, String> comboBoxValues;

    public ObjectNode asJsonNode() {
        final ObjectMapper mapper = new ObjectMapper();
        final ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("value", value);
        objectNode.put("type", type);
        objectNode.put("editable", editable);
        objectNode.put("nullable", nullable);
        if (!comboBoxValues.isEmpty()) {
            final ArrayNode arrayNode = mapper.createArrayNode();
            for (Map.Entry<String, String> entry : comboBoxValues.entrySet()) {
                final ObjectNode n = mapper.createObjectNode();
                n.put("key", entry.getKey());
                n.put("label", entry.getValue());
                arrayNode.add(n);
            }
            objectNode.set("comboBoxValues", arrayNode);
        }
        return objectNode;
    }
}
