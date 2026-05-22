package com.axiante.mui.webapp.views.content.admin.pojos;

import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;
import lombok.Getter;
import lombok.Setter;

public class CfgAzioniConsent {
	@Getter
	private CfgAzioniEntity entity;

	@Getter
	@Setter
	private boolean available = false;

	public CfgAzioniConsent(CfgAzioniEntity entity) {
		this.entity = entity;
	}
}
