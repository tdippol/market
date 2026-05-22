package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.dto.SottoclasseCountDto;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PromozionePianificazioneDAO extends DbPromoDAO<PromozionePianificazioneEntity> {
	List<PromozionePianificazioneEntity> findAllMasterRowsByPromozioneTestata(
			PromozioneTestataEntity promozioneTestataEntity);

	List<PromozionePianificazioneEntity> findAllDetailsByIdPromozione(Long idPromozione);

	List<PromozionePianificazioneEntity> findAllParentRowsByPromozioneTestata(
			PromozioneTestataEntity promozioneTestataEntity);

	List<PromozionePianificazioneEntity> findAllSetByPromozione(PromozioneTestataEntity testata);

	Map<String, Field> getMappedFields();

	PromozionePianificazioneEntity findFirstChildByParent(PromozionePianificazioneEntity pianificazione);

    List<PromozionePianificazioneEntity> findAllByIds(List<Long> ids);

	List<PromozionePianificazioneEntity> findAllBuoni();

	List<PromozionePianificazioneEntity> findAllBuoniNotUsed(List<Long> ids);

	List<PromozionePianificazioneEntity> findOverlappingByAnnoCanaleMeccanica(PromozionePianificazioneEntity pianificazione,
																			  String anno, CanalePromozioneEntity canale,
																			  MeccanicheEntity meccanica);

	Long countArticoli(PromozioneTestataEntity testata);

	List<Integer> getUsedProgressiveDiscountCodesBuoniPotenziamento(Integer buonoScontoRadice, Date dataInizio, Date dataFine);

	List<SottoclasseCountDto> countSottoclassiUsedInPromoInProgress();
}
