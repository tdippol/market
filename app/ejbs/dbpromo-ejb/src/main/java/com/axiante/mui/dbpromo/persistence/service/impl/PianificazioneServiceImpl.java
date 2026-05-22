package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgConfHeaderDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.CompratoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.GrmDAO;
import com.axiante.mui.dbpromo.persistence.dao.ItemDAO;
import com.axiante.mui.dbpromo.persistence.dao.PromozionePianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.RepartoDAO;
import com.axiante.mui.dbpromo.persistence.dao.UploadFidatyDAO;
import com.axiante.mui.dbpromo.persistence.dto.SottoclasseCountDto;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Dependent
@Transactional
@Slf4j
public class PianificazioneServiceImpl extends AbstractDbPromoService<PromozionePianificazioneEntity>
		implements PianificazioneService {
	private static final long serialVersionUID = -9016707188517449461L;

	@Inject
	@Getter
	PromozionePianificazioneDAO dao;

	@Inject
	private CfgPianificazioneDAO pianificazioneDAO;

	@Inject
	private CompratoreDAO compratoreDAO;

	@Inject
	private GrmDAO grmDAO;

	@Inject
	private ItemDAO itemDAO;

	@Inject
	private RepartoDAO repartoDAO;

	@Inject
	private UploadFidatyDAO uploadFidatyDAO;

	@Inject
	CfgConfHeaderDAO confHeaderDAO;

	@Override
	public List<CfgPianificazioneEntity> findAllCfgPianificazioneEntitiesByPromozione(
			PromozioneTestataEntity promozioneTestataEntity) {

		final CfgSetPianificazioneEntity set = promozioneTestataEntity.getMuiCfgSetPianificazione();
		final List<MeccanicheEntity> meccaniche = promozioneTestataEntity.getPromozionePianificazioneEntities().stream()
				.map(PromozionePianificazioneEntity::getMeccanicaEntity).distinct().collect(Collectors.toList());

		return set.getMuiCfgConfHeaders().stream().filter(h -> meccaniche.contains(h.getMeccanicaEntity()))
				.flatMap(h -> h.getMuiCfgPianificaziones().stream()).distinct().collect(Collectors.toList());

	}

	@Override
	public CompratoreEntity findCompratoreById(Long compratoreId) {
		return compratoreDAO.findById(compratoreId);
	}

	@Override
	public List<GrmEntity> getAllGrmEntity() {
		return grmDAO.findAllOrderedBy();
	}

    @Override
    public List<GrmEntity> findAllGrmByCodiciCompratore(List<String> codiciCompratore) {
		if (codiciCompratore.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
        return grmDAO.findAllByCodiciCompratore(codiciCompratore);
    }

    @Override
	public List<ItemEntity> getAllItemsByCompratore(CompratoreEntity compratoreEntity) {
		return itemDAO.findAllByCompratore(compratoreEntity);
	}

	@Override
	public List<RepartoEntity> getAllReparti() {
		return repartoDAO.findAllOrderedBy();
	}

    @Override
    public List<RepartoEntity> findAllRepartiByCodiciCompratore(List<String> codiciCompratore) {
		if (codiciCompratore.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return repartoDAO.findAllByCodiciCompratore(codiciCompratore);
    }

    @Override
	public List<CfgPianificazioneEntity> findAllCfgPianificazioneEntitiesByPromozioneMeccanica(
			PromozioneTestataEntity promozioneTestataEntity, Long meccanicheEntityId) {
		return pianificazioneDAO.findAllByPromoAndMeccanicaId(promozioneTestataEntity,
				meccanicheEntityId);
	}

	@Override
	public PromozionePianificazioneEntity savePromozionePianificazioneEntity(
			@NonNull final PromozionePianificazioneEntity promozionePianificazioneEntity) {
		return dao.persist(promozionePianificazioneEntity);
	}

	@Override
	public PromozionePianificazioneEntity savePromozionePianificazioneEntity(
			@NonNull final PromozionePianificazioneEntity promozionePianificazioneEntity,
			@NonNull final String username) {
		return (PromozionePianificazioneEntity) dao.persistWithAuditLog(promozionePianificazioneEntity, username);
	}

	@Override
	public List<PromozionePianificazioneEntity> findAllMasterPromozionePianificazioneEntityByPromozioneTestata(
			PromozioneTestataEntity promozioneTestataEntity) {
		return dao.findAllMasterRowsByPromozioneTestata(promozioneTestataEntity);
	}

	@Override
	public List<PromozionePianificazioneEntity> findAllParentPromozionePianificazioneEntityByPromozioneTestata(
			PromozioneTestataEntity promozioneTestataEntity) {
		return dao.findAllParentRowsByPromozioneTestata(promozioneTestataEntity);
	}

	@Override
	public Map<String, String> getDefaultValues(Long testataId, Long meccanicaId, String codeTipoRiga) {
		return pianificazioneDAO.getDefaultValues(testataId, meccanicaId, codeTipoRiga);
	}

	@Override
	public Map<String, Field> getMappedFields() {
		return dao.getMappedFields();
	}

	@Override
	public PromozionePianificazioneEntity getPromoPianificazoneById(Long promoPianificazioneId) {
		return dao.findById(promoPianificazioneId);
	}

	@Override
	public List<PromozionePianificazioneEntity> findAllPianificazioniByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return dao.findAllByIds(ids);
	}

	@Override
	public void removePromozionePianificazioneEntity(
			@NonNull final PromozionePianificazioneEntity promozionePianificazioneEntity) {
		dao.remove(promozionePianificazioneEntity);
	}

	@Override
	public List<ItemEntity> findAllItemsByDynamicFilters(Long idCompratore, Long idFornitore, Long idReparto,
														 Long idCategoria, Long idGrm, String codiceMarchioPrivato) {
		return itemDAO.findAllByFilter(idCompratore, idFornitore, idReparto, idCategoria, idGrm, codiceMarchioPrivato);
	}

	@Override
	public List<UploadFidayEntity> getUploadFidayEntityByPromozionePianificazioneEntity(
			@NonNull final PromozionePianificazioneEntity promozionePianificazioneEntity) {
		return uploadFidatyDAO.findByPianificazione(promozionePianificazioneEntity.getId());
	}

	@Override
	public void removeUploadFidayEntity(@NonNull final UploadFidayEntity uploadFidayEntity) {
		uploadFidatyDAO.remove(uploadFidayEntity);
	}

	@Override
	public List<PromozionePianificazioneEntity> findDuplicatedItems(Long idPromozione, Long codiceElemento) {
		return dao.findAllDetailsByIdPromozione(idPromozione).stream()
				.filter(p -> p.getCodiceElemento() != null
							&& p.getCodiceElemento().trim().equals("" + codiceElemento.intValue()))
				.collect(Collectors.toList());
	}
	
	public List<PromozionePianificazioneEntity> findAllSetByPromozione(PromozioneTestataEntity e){
		return dao.findAllSetByPromozione(e);
	}
	
	@Override
	public PromozionePianificazioneEntity findChildByParent(PromozionePianificazioneEntity pianificazione) {
		return dao.findFirstChildByParent(pianificazione);
	}

    @Override
    public List<PromozionePianificazioneEntity> findAllBuoniNotUsed(List<Long> ids) {
		return ids == null || ids.isEmpty()
				? dao.findAllBuoni()
				: dao.findAllBuoniNotUsed(ids);
    }

	@Override
	public List<PromozionePianificazioneEntity> findOverlappingByAnnoCanaleMeccanica(@NonNull PromozionePianificazioneEntity pianificazione,
																					 @NonNull String anno,
																					 @NonNull CanalePromozioneEntity canale,
																					 @NonNull MeccanicheEntity meccanica) {
		return dao.findOverlappingByAnnoCanaleMeccanica(pianificazione, anno, canale, meccanica);
	}

	@Override
	public Long countArticoli(@NonNull PromozioneTestataEntity testata){
		return dao.countArticoli(testata);
	}

	@Override
	public List<Integer> getUsedProgressiveDiscountCodesBuoniPotenziamento(Integer buonoScontoRadice, Date dataInizio, Date dataFine) {
		return dao.getUsedProgressiveDiscountCodesBuoniPotenziamento(buonoScontoRadice, dataInizio, dataFine);
	}

	@Override
	public List<SottoclasseCountDto> countSottoclassiUsedInPromoInProgress() {
		return dao.countSottoclassiUsedInPromoInProgress();
	}
}
