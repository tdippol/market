package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import lombok.NonNull;

public interface PianificazioneCampiService extends DbPromoService<CfgPianificazioneCampiEntity> {
	
	public CfgPianificazioneCampiEntity saveCfgPianificazioneCampiEntity(
			@NonNull final CfgPianificazioneCampiEntity cfgPianificazioneCampiEntity,
			@NonNull final String username);
}
