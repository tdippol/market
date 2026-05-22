package com.axiante.mui.webapp.views.content.dbpromo.data.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class FilterSelectionPojo implements Serializable, Comparable<FilterSelectionPojo> {
    private static final long serialVersionUID = -3761509330362692947L;

    private String key;

    private String label;

    public String getKey() {
        return key;
    }

    public String getLabel() {
        return label == null ? key : String.format("%s - %s", key, label);
    }

    @Override
    public int compareTo(FilterSelectionPojo other) {
        if (other == null) {
            return 1; // this object is considered "greater than" null
        }
        return this.key.compareTo(other.key);
    }
}
