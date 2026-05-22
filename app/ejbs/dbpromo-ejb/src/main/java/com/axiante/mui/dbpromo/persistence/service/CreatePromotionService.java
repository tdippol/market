package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleNegoziEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiCanaleConsentEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiTransizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CreaPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Date;
import java.util.List;

public interface CreatePromotionService extends DbPromoService<CreaPromozioneEntity> {
	List<CanalePromozioneEntity> findChannelListByGroup(GruppoPromozioneEntity group) throws Exception;

	String findSlotCreaPromoValue() throws Exception;

	CreaPromozioneEntity persist(CreaPromozioneEntity creaPromozioneEntity, String user) throws Exception;

	List<GruppoPromozioneEntity> getAllGroups();

	List<CreaPromozioneEntity> getAllPromoByUserId(String userId) throws Exception;

	CreaPromozioneEntity findByUserIdAndSlotId(String userId, String slotId) throws Exception;

	List<CanalePromozioneEntity> findChannelByDescription(String description) throws Exception;

	// GruppoPromozioneEntity findGroupById(Long id) throws Exception;
	StatoPromozioneEntity findStatoPromozioneEntityByCodiceStato(String codiceStato) throws Exception;

	List<CfgCanaleNegoziEntity> findChannelShopsByChannel(CanalePromozioneEntity canalePromozioneEntity)
			throws Exception;

	List<CfgAbilitaMeccCanaleEntity> findEnabledChannelMechanicByChannel(CanalePromozioneEntity canalePromozioneEntity)
			throws Exception;

	CfgSetPianificazioneEntity findByLockedAndDataInizio(Date dataInizio) throws Exception;

	List<CfgStatiCanaleConsentEntity> findAllPromoAllowedStateByChannel(CanalePromozioneEntity canalePromozioneEntity);

	List<CfgStatiTransizioniEntity> findAllPromoTransitionByChannel(CanalePromozioneEntity canalePromozioneEntity);

	CanaleLastProgEntity persistCanaleLastProgEntity(CanaleLastProgEntity canaleLastProgEntity, String user);

	CanaleLastProgEntity findCanaleLastProgEntityByYearAndChannel(String anno,
			CanalePromozioneEntity canalePromozioneEntity);

	CanalePromozioneEntity findChannelById(Long id) throws Exception;

	boolean runFunctionCountTestate(Long idUser, Long idCanale, Date dataInizio, Date dataFine) ;
	boolean runFunctionCountTestate(Long idCanale, Date dataInizio, Date dataFine, Long maxTestate) ;
}
