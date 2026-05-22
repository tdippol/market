package com.axiante.mui.dbpromo.persistence.entities;

import javax.resource.spi.IllegalStateException;

public interface DbPromoReadOnlyEntityInterface {
	public static final String OVERRIDE_READ_ONLY = "OVERRIDE_READ_ONLY";

	void preventUpdate(Object o) throws IllegalStateException;
}
