package com.axiante.mui.webapp.support.builders.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.List;

public class JsonBuilder {
    private Long id;
    private List<GridCellNode> nodes;

    public JsonBuilder(Long id) {
        this.id = id;
    }

    public JsonBuilder withNode(GridCellNode node) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        nodes.add(node);
        return this;
    }

    public ObjectNode build() {
        final ObjectMapper mapper = new ObjectMapper();
        final ObjectNode objectNode = mapper.createObjectNode();
        for (GridCellNode cell : nodes) {
            objectNode.set(cell.getName(), cell.asJsonNode());
        }
        final ObjectNode idNode = mapper.createObjectNode();
        idNode.put("value", id);
        idNode.put("type", "string");
        idNode.put("editable", false);
        objectNode.set("id", idNode);
        return objectNode;
    }
}
