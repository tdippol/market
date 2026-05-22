package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.dao.CanaleLastProgEntityDAO;
import com.axiante.mui.dbpromo.persistence.dao.CanalePromozioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgAbilitaMeccCanaleDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleNegoziDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgParametriDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgSetPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgStatiCanaleConsentDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgStatiTransizioniDAO;
import com.axiante.mui.dbpromo.persistence.dao.CreaPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.GruppoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.StatoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleNegoziEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgParametriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiCanaleConsentEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiTransizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CreaPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Dependent
@Transactional
@Slf4j
public class CreatePromotionServiceImpl extends AbstractDbPromoService<CreaPromozioneEntity>
		implements CreatePromotionService {
	private static final long serialVersionUID = 36910195658069128L;

	@Inject
	@Getter
	CreaPromozioneDAO dao;

	@Inject
	private CanalePromozioneDAO canalePromozioneDAO;

	@Inject
	private CfgParametriDAO cfgParametriEntityDAO;

	@Inject
	private CreaPromozioneDAO creaPromozioneEntityDAO;

	@Inject
	private GruppoPromozioneDAO gruppoPromozioneDAO;

	@Inject
	private StatoPromozioneDAO statoPromozioneDAO;

	@Inject
	private CfgCanaleNegoziDAO cfgCanaleNegoziDAO;

	@Inject
	private CfgAbilitaMeccCanaleDAO cfgAbilitaMeccCanaleDAO;

	@Inject
	private CfgSetPianificazioneDAO cfgSetPianificazioneDAO;

	@Inject
	private CfgStatiCanaleConsentDAO cfgStatiCanaleConsentDAO;

	@Inject
	private CfgStatiTransizioniDAO cfgStatiTransizioniDAO;

	@Inject
	private CanaleLastProgEntityDAO canaleLastProgEntityDAO;

	@Override
	public List<CanalePromozioneEntity> findChannelListByGroup(final GruppoPromozioneEntity group) throws Exception {
		return canalePromozioneDAO.findAllByGroup(group);
	}

	@Override
	public String findSlotCreaPromoValue() throws Exception {
		List<CfgParametriEntity> cfgParametriEntities = cfgParametriEntityDAO.findAll();
		CfgParametriEntity cfgParametriEntity = null;
		if (cfgParametriEntities != null) {
			cfgParametriEntity = cfgParametriEntities.stream()
					.filter(par -> Constants.CREA_PROMOZIONE_SLOT_MINIMUM.equals(par.getCodiceParametro())).findFirst()
					.orElse(null);
		}
		return cfgParametriEntity != null ? cfgParametriEntity.getValore() : "";
	}

	@Override
	public CreaPromozioneEntity persist(@NonNull final CreaPromozioneEntity creaPromozioneEntity,
			@NonNull final String user) throws Exception {
		return (CreaPromozioneEntity) creaPromozioneEntityDAO.persistWithAuditLog(creaPromozioneEntity, user);
	}

	@Override
	public List<GruppoPromozioneEntity> getAllGroups() {
		return gruppoPromozioneDAO.findAll();
	}

	@Override
	public List<CreaPromozioneEntity> getAllPromoByUserId(String userId) throws Exception {
		return creaPromozioneEntityDAO.findAllByUserId(userId);
	}

	@Override
	public CreaPromozioneEntity findByUserIdAndSlotId(String userId, String slotId) throws Exception {
		return creaPromozioneEntityDAO.findByUserIdAndSlotId(userId, slotId);
	}

	@Override
	public List<CanalePromozioneEntity> findChannelByDescription(String description) throws Exception {
		return canalePromozioneDAO.findByDescription(description);
	}

	@Override
	public CanalePromozioneEntity findChannelById(Long id) throws Exception {
		return canalePromozioneDAO.findById(id);
	}

	@Override
	public StatoPromozioneEntity findStatoPromozioneEntityByCodiceStato(String codiceStato) throws Exception {
		return statoPromozioneDAO.findByCode(codiceStato);
	}

	@Override
	public List<CfgCanaleNegoziEntity> findChannelShopsByChannel(CanalePromozioneEntity canalePromozioneEntity)
			throws Exception {
		return cfgCanaleNegoziDAO.findAllByIdCanale(canalePromozioneEntity);
	}

	@Override
	public List<CfgAbilitaMeccCanaleEntity> findEnabledChannelMechanicByChannel(
			CanalePromozioneEntity canalePromozioneEntity) throws Exception {
		return cfgAbilitaMeccCanaleDAO.findAllByIdCanale(canalePromozioneEntity);
	}

	@Override
	public CfgSetPianificazioneEntity findByLockedAndDataInizio(Date dataInizio) throws Exception {
		return cfgSetPianificazioneDAO.findByLockedAndDataInizio(dataInizio);
	}

	@Override
	public List<CfgStatiCanaleConsentEntity> findAllPromoAllowedStateByChannel(
			CanalePromozioneEntity canalePromozioneEntity) {
		return cfgStatiCanaleConsentDAO.findAllPromoAllowedStateByChannel(canalePromozioneEntity);
	}

	@Override
	public List<CfgStatiTransizioniEntity> findAllPromoTransitionByChannel(
			CanalePromozioneEntity canalePromozioneEntity) {
		return cfgStatiTransizioniDAO.findAllPromoTransitionByChannel(canalePromozioneEntity);
	}

	@Override
	public CanaleLastProgEntity persistCanaleLastProgEntity(CanaleLastProgEntity canaleLastProgEntity,
			@NonNull String user) {
		return (CanaleLastProgEntity) canaleLastProgEntityDAO.persistWithAuditLog(canaleLastProgEntity, user);
	}

	@Override
	public CanaleLastProgEntity findCanaleLastProgEntityByYearAndChannel(String anno,
			CanalePromozioneEntity canalePromozioneEntity) {
		return canaleLastProgEntityDAO.getByYearAndChannel(anno, canalePromozioneEntity);
	}

	@Override
	public boolean runFunctionCountTestate(Long idUser, Long idCanale, Date dataInizio, Date dataFine)  {
		try{
			return getDao().runFunctionCountTestate(idUser,idCanale,dataInizio, dataFine);
		} catch (Exception e){
			log.warn("Errore durante l'esecuzione della funzione P_MUI_COUNT_PROMOZIONI", e);
		}
		return false;
	}
	@Override
	public boolean runFunctionCountTestate(Long idCanale, Date dataInizio, Date dataFine, Long maxTestate)  {
		if ( maxTestate != null && maxTestate > -1) {
			try {
				return getDao().runFunctionCountTestate(idCanale, dataInizio, dataFine, maxTestate);
			} catch (Exception e) {
				log.warn("Errore durante l'esecuzione della funzione P_MUI_COUNT_PROMOZIONI", e);
			}
			return false;
		} else {
			return true;
		}
	}
}
