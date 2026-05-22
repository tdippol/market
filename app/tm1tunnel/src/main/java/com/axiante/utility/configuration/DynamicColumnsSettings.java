package com.axiante.utility.configuration;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class DynamicColumnsSettings implements Serializable{
    String[] headerconf;
    Map<String, String> headerdefaults;
    ColumnDef childrendefaults;
    Map<String, ColumnDef> childrenCustomTypes;
    Map<String, ColumnDef> headerCustomTypes;

    public DynamicColumnsSettings copy() {
        DynamicColumnsSettings s = new DynamicColumnsSettings();
        if (getHeaderdefaults() != null) {
            s.setHeaderdefaults(getHeaderdefaults().entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }

        if (getChildrenCustomTypes() != null) {
            s.setChildrenCustomTypes(getChildrenCustomTypes().entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }

        if (getHeaderCustomTypes() != null) {
            s.setHeaderCustomTypes(getHeaderCustomTypes().entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }

        if (getHeaderconf() != null) {
            s.setHeaderconf(new String[getHeaderconf().length]);
            for (int i = 0; i < getHeaderconf().length; ++i) {
                s.getHeaderconf()[i] = getHeaderconf()[i];
            }
        }
        return s;
    }
}
