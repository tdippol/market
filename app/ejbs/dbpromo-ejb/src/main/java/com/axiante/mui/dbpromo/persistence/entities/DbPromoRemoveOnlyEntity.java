package com.axiante.mui.dbpromo.persistence.entities;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DbPromoRemoveOnlyEntity implements DbPromoReadOnlyEntityInterface {
	private static boolean override = false;
	static {
		String sysprop = System.getProperty(OVERRIDE_READ_ONLY);
		override = (sysprop != null && "true".equals(sysprop.trim().toLowerCase()));
	}

	@Override
	@PrePersist
	@PreUpdate
	public void preventUpdate(Object o) {
		if (override) {
			log.info("preventUpdate called");
		} else {
			throw new RuntimeException("Entity of type " + (o == null ? "null" : o.getClass())
					+ " cannot be updated from the application");
		}
	}
}
