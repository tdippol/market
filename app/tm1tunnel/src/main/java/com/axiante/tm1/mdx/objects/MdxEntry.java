package com.axiante.tm1.mdx.objects;

import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

public class MdxEntry extends AbstractDimensionDependentObject{
    @Setter
    @Getter
    private boolean nonEmpty = true;


    public MdxEntry copy() {
        final MdxEntry m = new MdxEntry();

        m.setNonEmpty(isNonEmpty());

        if(getDimensions() != null ) {
            m.getDimensions().addAll(getDimensions().stream().map(Dimension::copy).collect(Collectors.toList()));
        }
        return m;
    }
}
