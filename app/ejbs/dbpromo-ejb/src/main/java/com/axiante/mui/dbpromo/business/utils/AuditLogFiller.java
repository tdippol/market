package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.dbpromo.persistence.entities.AuditLogInterface;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;

public class AuditLogFiller implements Serializable {
	private static final long serialVersionUID = -8175925308697532871L;

	private transient static final Object semaphore = new Object();

	public static AuditLogInterface fillAuditLogFields(@NonNull final AuditLogInterface entity, @NonNull final String user) {
		synchronized (semaphore) {
			final Date data = new Date(System.currentTimeMillis());
			if (entity.getId() != null) {
				entity.setCodiceUtenteAggiornamento(user);
				entity.setDataAggiornamento(data);
			} else {
				entity.setCodiceUtenteInserimento(user);
				entity.setDataInserimento(data);
			}
			return entity;
		}
	}
}
