package com.axiante.tm1.mdx.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;

public class AbstractDimensionDependentObject implements DimensionDependentObject{
    @Getter
    private final List<Dimension> dimensions = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void addDimension(@NonNull final Dimension dimension) {
        dimensions.add(dimension);
    }

    public void addAllDimension(@NonNull final List<Dimension> dimensions) {
        this.dimensions.addAll(dimensions);
    }

}
