package com.axiante.tm1.mdx.objects;

import java.util.List;
import lombok.NonNull;

public interface DimensionDependentObject {
	public void addDimension(@NonNull final Dimension dimension) ;
	public List<Dimension> getDimensions();
}
