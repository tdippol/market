package com.axiante.tm1.mdx.objects;

import com.axiante.tm1.mdx.filter.Filter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class FilterImpl implements Filter {
    @Getter
    @Setter
    @NonNull
    List<String> values;
    @Getter
    @Setter
    @NonNull
    String dimension;
    @Getter
    @Setter
    @NonNull
    String attribute;

    @Override
    public String getColumn() {
        return dimension;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public String getFilter() {
        final String column = MdxConstants.SQUARE_BRAKET_OPEN + dimension + MdxConstants.SQUARE_BRAKET_CLOSED + "."
                + MdxConstants.SQUARE_BRAKET_OPEN + attribute + MdxConstants.SQUARE_BRAKET_CLOSED + " = ";

        final StringBuilder builder = new StringBuilder("(").append(column)
                .append(values.stream().map(v->toSafeValue(v)).collect(Collectors.joining(" OR " + column))).append(")");
        return builder.toString();
    }


    private String toSafeValue(final String value) {
        // if value contains " all occurrences of " become an occurrence of ""
        return  "\"" + value.replaceAll("\"", "\"\"") + "\"";
    }
}
