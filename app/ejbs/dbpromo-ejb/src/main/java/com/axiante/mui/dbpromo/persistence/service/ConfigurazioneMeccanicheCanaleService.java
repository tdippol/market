package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import java.util.List;

public interface ConfigurazioneMeccanicheCanaleService extends DbPromoService<CfgAbilitaMeccCanaleEntity> {
	List<CfgAbilitaMeccCanaleEntity> findMeccanicaCanaleByPromozione(CanalePromozioneEntity canalePromozioneEntity);
	CfgAbilitaMeccCanaleEntity findByMeccanicaAndCanale(Long idMeccanica, Long idCanale);

}
