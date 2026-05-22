package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.common.promo.grid.ComboBoxValues;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
public class ComboBoxFactory implements Serializable {
	private static final long serialVersionUID = 5091466593405752824L;

	public ComboBoxValues from(@NonNull ComboBoxCapable entity) {
		return ComboBoxValues.builder().key(entity.getKey()).label(entity.getLabel()).build();
	}

	public List<ComboBoxValues> from(@NonNull final List<? extends ComboBoxCapable> entities) {
		return from(entities, true);
	}

	public List<ComboBoxValues> from(@NonNull final List<? extends ComboBoxCapable> entities, Boolean withErasure) {
		return from(entities, withErasure, true);
	}

	public List<ComboBoxValues> from(@NonNull final List<? extends ComboBoxCapable> entities,
									 Boolean withErasure, Boolean sorted) {
		List<ComboBoxValues> ret = sorted != null && sorted
				? entities.stream().map(this::from).sorted(Comparator.comparing(ComboBoxValues::getLabel))
					.collect(Collectors.toList())
				: entities.stream().map(this::from).collect(Collectors.toList());
		if (withErasure) {
			ret.add(ComboBoxValues.BLANK);
		}
		return ret;
	}
}
