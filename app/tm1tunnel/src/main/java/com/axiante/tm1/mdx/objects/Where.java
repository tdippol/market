package com.axiante.tm1.mdx.objects;

import java.util.stream.Collectors;

public class Where extends AbstractDimensionDependentObject{

    public Where copy() {
        final Where w = new Where();

        if (getDimensions() != null ) {
            w.getDimensions().addAll(getDimensions().stream().collect(Collectors.toList()));
        }

        return w;
    }
}
