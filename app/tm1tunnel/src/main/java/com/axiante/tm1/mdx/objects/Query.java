package com.axiante.tm1.mdx.objects;

import com.axiante.tm1.mdx.filter.Filter;
import com.axiante.tm1.mdx.objects.Dimension.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class Query {
    MdxEntry rows;
    MdxEntry cols;
    From from;
    @Builder.Default
    Where where = new Where();
    List<? extends Filter> filters;
    @Builder.Default
    boolean cartesianColumns = true;

    public String generateMDX() {
        return generateMDX(false);
    }

    public String generateMDX(boolean dinamcColumns) {
        StringBuilder builder = new StringBuilder("SELECT").append(MdxConstants.SPACE);
        if (getRows().isNonEmpty()) {
            builder.append(MdxConstants.NON_EMPTY_MDX).append(MdxConstants.SPACE);
        }

        builder.append(getFilteredDimension(getRows()));

        builder.append(" ON ROWS ");
        builder.append(", ");
        if (getCols().isNonEmpty() && isCartesianColumns()) {
            builder.append(MdxConstants.NON_EMPTY_MDX).append(MdxConstants.SPACE);
        }
        builder.append(MdxConstants.CURLY_BRAKET_OPEN);
        if (isCartesianColumns()) {
            if (dinamcColumns)
                builder.append(getFilteredDimension(getCols()));
            else
                builder.append(getFilteredDimension(getCols()));
        } else {
            // columns are to be taken as a list of columns
            builder.append(
                    getCols().getDimensions().stream().map(Dimension::getColumn).collect(Collectors.joining(",")));

        }

        builder.append(MdxConstants.CURLY_BRAKET_CLOSED);
        builder.append(" ON COLUMNS");

        // from
        builder.append(MdxConstants.SPACE).append(MdxConstants.FROM).append(MdxConstants.SPACE)
                .append(getFrom().getValue());
        if (getWhere() != null && getWhere().getDimensions().size() > 0) {
            // where
            builder.append(MdxConstants.SPACE).append(MdxConstants.WHERE).append(MdxConstants.SPACE)
                    .append(MdxConstants.PARENTHESIS_OPENED);
            builder.append(getWhere().getDimensions().stream().map(dim -> {
                return ("[" + dim.getColumn() + "]." + dim.getValue());
            }

            ).collect(Collectors.joining(",")));
            builder.append(MdxConstants.PARENTHESIS_CLOSED);
        }

        return builder.toString();
    }

    protected Dimension extractSeparator(@NonNull final List<Dimension> list) {
        Dimension separator = list.stream().filter(d -> {
            return d.getType().equals(Dimension.Type.SEPARATOR);
        }).findAny().orElse(null);
        if (separator != null) {
            list.remove(separator);
        } else {
            separator = new Dimension("", " * ", Type.SEPARATOR);
        }
        return separator;
    }

    protected StringBuilder getFilteredDimension(MdxEntry dimension) {
        StringBuilder builder = new StringBuilder();
        Dimension separator = extractSeparator(dimension.getDimensions());
        if (getFilters() != null && getFilters().size() > 0) {
            // ho i filtri
            final Map<String, List<Filter>> filterMap = filters.stream().filter(Objects::nonNull)
                    .collect(Collectors.groupingBy(Filter::getColumn));

            for (Dimension row : dimension.getDimensions()) {
                if (filterMap.containsKey(row.getColumn())) {
                    // dimensione filtrata
                    builder.append("{FILTER ({").append(row.getValue()).append("}").append(",").append("(")
                            .append(filterMap.get(row.getColumn()).stream().map(Filter::getFilter)
                                    .collect(Collectors.joining(" AND ")))
                            .append(")").append(")}");
                } else {
                    // dimensione non filtrata
                    builder.append("{").append(row.getValue()).append("}");
                }
                builder.append(separator.getValue());
            }
            builder.delete(builder.length() - " * ".length(), builder.length()); // tolgo ultimo "*"
        } else {
            // non ho filtri
            builder.append("{");
            builder.append(
                    dimension.getDimensions().stream().map(Dimension::getValue).collect(Collectors.joining("*")));
            builder.append("}");
        }
        if (builder.lastIndexOf("*") > builder.lastIndexOf("}")) {
            builder.delete(builder.lastIndexOf("*"), builder.length() - 1);
        }

        if (separator != Dimension.DEFAULT_SEPARATOR) {
            builder.insert(0, "{");
            builder.append("}");
        }
        return builder;
    }

    public Query copy() {
        Query q = Query.builder().build();
        q.setRows(getRows().copy());
        q.setCols(getCols().copy());
        q.setFrom(new From(getFrom().getValue()));
        q.setWhere(getWhere().copy());

        if (getFilters() != null)
            q.setFilters(getFilters().stream().collect(Collectors.toList()));

        setCartesianColumns(isCartesianColumns());

//		return this.toBuilder().build();
        return q;
    }

}
