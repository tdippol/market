package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.dto.SottoclasseCountDto;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PianificazioneService extends DbPromoService<PromozionePianificazioneEntity> {
	List<CfgPianificazioneEntity> findAllCfgPianificazioneEntitiesByPromozione(
			PromozioneTestataEntity promozioneTestataEntity);

	CompratoreEntity findCompratoreById(Long compratoreId);

	List<GrmEntity> getAllGrmEntity();

	List<GrmEntity> findAllGrmByCodiciCompratore(List<String> codiciCompratore);

	List<ItemEntity> getAllItemsByCompratore(CompratoreEntity compratoreEntity);

	List<RepartoEntity> getAllReparti();

	List<RepartoEntity> findAllRepartiByCodiciCompratore(List<String> codiciCompratore);

	List<CfgPianificazioneEntity> findAllCfgPianificazioneEntitiesByPromozioneMeccanica(
			PromozioneTestataEntity promozioneTestataEntity, Long meccanicheEntityId);

	PromozionePianificazioneEntity savePromozionePianificazioneEntity(
			@NotNull PromozionePianificazioneEntity promozionePianificazioneEntity) throws Exception;

	@Deprecated
	List<PromozionePianificazioneEntity> findAllMasterPromozionePianificazioneEntityByPromozioneTestata(
			PromozioneTestataEntity promozioneTestataEntity);

	PromozionePianificazioneEntity getPromoPianificazoneById(Long promoPianificazioneId);

	List<PromozionePianificazioneEntity> findAllPianificazioniByIds(List<Long> ids);

	void removePromozionePianificazioneEntity(@NotNull PromozionePianificazioneEntity promozionePianificazioneEntity)
			throws Exception;

	PromozionePianificazioneEntity savePromozionePianificazioneEntity(
			@NonNull PromozionePianificazioneEntity promozionePianificazioneEntity, @NonNull String username);

	List<ItemEntity> findAllItemsByDynamicFilters(Long idCompratore, Long idFornitore, Long idReparto, Long idCategoria,
												  Long idGrm, String codiceMarchioPrivato);

	List<UploadFidayEntity> getUploadFidayEntityByPromozionePianificazioneEntity(
			PromozionePianificazioneEntity promozionePianificazioneEntity);

	void removeUploadFidayEntity(@NotNull UploadFidayEntity uploadFidayEntity) throws Exception;

	List<PromozionePianificazioneEntity> findDuplicatedItems(Long idPromozione, Long codiceElemento);

	List<PromozionePianificazioneEntity> findAllParentPromozionePianificazioneEntityByPromozioneTestata(
			PromozioneTestataEntity testata);

	Map<String, String> getDefaultValues(Long testataId, Long meccanicaId, String codeTipoRiga);

	Map<String, Field> getMappedFields();
	
	List<PromozionePianificazioneEntity> findAllSetByPromozione(PromozioneTestataEntity e);

	PromozionePianificazioneEntity findChildByParent(PromozionePianificazioneEntity pianificazione);

    List<PromozionePianificazioneEntity> findAllBuoniNotUsed(List<Long> ids);

	List<PromozionePianificazioneEntity> findOverlappingByAnnoCanaleMeccanica(PromozionePianificazioneEntity pianificazione,
																			  String anno, CanalePromozioneEntity canale,
																			  MeccanicheEntity meccanica);
	Long countArticoli(PromozioneTestataEntity testata);

	List<Integer> getUsedProgressiveDiscountCodesBuoniPotenziamento(Integer buonoScontoRadice, Date dataInizio, Date dataFine);

	List<SottoclasseCountDto> countSottoclassiUsedInPromoInProgress();
}
