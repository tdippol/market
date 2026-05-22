package com.axiante.mui.common.utility;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class KeepAsJsonDeserialzier  extends JsonDeserializer<RawPojo> {

    @Override
    public RawPojo deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
    	
        TreeNode tree = jp.getCodec().readTree(jp);
        RawPojo pojo = new RawPojo();
        pojo.setJson(tree.toString());
        return pojo;
    }
}