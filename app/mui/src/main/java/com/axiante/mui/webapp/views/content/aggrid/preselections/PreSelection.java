package com.axiante.mui.webapp.views.content.aggrid.preselections;

import com.axiante.connection.ConnectionProfile;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class PreSelection implements Serializable {
    private static final long serialVersionUID = -8894752281166102084L;
    String id;
    String dimension;
    String dimCode;
    String dimColumnName;
    String attribute;
    String attrCode;
    String attrColumnName;
    String paramName;
    String selectedValue;
    String process;
    List<String> refresh;
    ConnectionProfile profile;
    Boolean visible;
}
