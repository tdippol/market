package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.dao.CategoriaDAO;
import com.axiante.mui.dbpromo.persistence.dao.CompratoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.FornitoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.NegozioDAO;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.dao.ResponsabileDAO;
import com.axiante.mui.dbpromo.persistence.dao.TipoNegozioDAO;
import com.axiante.mui.dbpromo.persistence.dto.ZonaDto;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoNegozioEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Dependent
@Transactional
@Slf4j
public class PromoServiceImpl extends AbstractDbPromoService<PromozioneTestataEntity> implements PromoService {
	private static final long serialVersionUID = 8194909035394479742L;

	@Inject
	@Getter
	private PromozioneTestataDAO dao;

	@Inject
	private TipoNegozioDAO tipoNegozioDAO;

	@Inject
	private ResponsabileDAO responsabileDAO;

	@Inject
	private CompratoreDAO compratoreDAO;

	@Inject
	private CategoriaDAO categoriaDAO;

	@Inject
	private FornitoreDAO fornitoreDAO;
	
	@Inject
	private NegozioDAO negozioDAO;

	@Override
	public List<PromozioneTestataEntity> read(List<CanalePromozioneEntity> canali) throws Exception {
		return getDao().findAllSecured(canali);
	}

	@Override
	public PromozioneTestataEntity persist(@NonNull final PromozioneTestataEntity promozione, String user)
			throws Exception {
		return (PromozioneTestataEntity) getDao().persistWithAuditLog(promozione, user);
	}

	@Override
	public List<PromozioneTestataEntity> persist(@NonNull final List<PromozioneTestataEntity> entities, String user) {
		for (int i = 0; i < entities.size(); ++i) {
			entities.set(i, (PromozioneTestataEntity) AuditLogFiller.fillAuditLogFields(entities.get(i), user));
		}
		getDao().persist(entities);
		return entities;
	}

	@Override
	public List<ResponsabileEntity> findAllManager() {
		return responsabileDAO.findAll();
	}


	@Override
	public CompratoreEntity findCompratoreEntityById(Long compratoreId) throws Exception {
		return compratoreDAO.findById(compratoreId);
	}

	@Override
	public List<CompratoreEntity> findAllBuyers() {
		return compratoreDAO.findAllOrderedBy();
	}

	@Override
	public List<CompratoreEntity> findAllBuyersByCodes(@NonNull List<String> codes) {
		if (codes.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return compratoreDAO.findAllByCodes(codes);
	}

	@Override
	public List<CompratoreEntity> findAllBuyersByIdItems(@NonNull List<Long> idItems) {
		if (idItems.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return compratoreDAO.findAllByIdItems(idItems);
	}

	@Override
	public String findTipoElemento(@NonNull Long promoID) {
		final PromozioneTestataEntity promozioneTestata = getDao().findById(promoID);
		if (promozioneTestata == null) {
			return "";
		}

		final CfgSetPianificazioneEntity muiCfgSetPianificazione = promozioneTestata.getMuiCfgSetPianificazione();
		if (muiCfgSetPianificazione == null) {
			return "";
		}
		if (muiCfgSetPianificazione.getMuiCfgConfHeaders() == null) {
			return "";
		}
		final CfgPianificazioneEntity cfgPianificazioneEntity = muiCfgSetPianificazione.getMuiCfgConfHeaders().stream()
				.flatMap(h -> h.getMuiCfgPianificaziones().stream()).findFirst().orElse(null);
		return cfgPianificazioneEntity != null ? cfgPianificazioneEntity.getLista() : "";
	}

	@Override
	public List<PromozioneTestataEntity> findOverlappingPromo(PromozioneTestataEntity promozioneTestataEntity,
			List<CanalePromozioneEntity> canali) {
		return getDao().findOverlappingPromo(promozioneTestataEntity, canali);
	}

	@Override
	public List<PromozioneTestataEntity> findAllByAnnoAndCanale(String anno, CanalePromozioneEntity canale) {
		return getDao().findAllByAnnoAndCanale(anno, canale);
	}

	@Override
	public List<PromozioneTestataEntity> findOverlappingByCodiciMeccanica(@NonNull PromozioneTestataEntity promozione,
																		  List<String> codiciMeccanica) {
		if (codiciMeccanica.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return dao.findOverlappingByCodiciMeccanica(promozione, codiciMeccanica);
	}
	public List<PromozioneTestataEntity> findAllByAnnoAndMeccanica(PromozioneTestataEntity promozione,
															List<String> codiciMeccanica){

		if (codiciMeccanica == null || codiciMeccanica.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return dao.findAllByAnnoAndMeccanica(promozione, codiciMeccanica);
	}

	@Override
	public List<PromozioneTestataEntity> findOverlappingByAnnoAndCodiceMeccanicaWithOffset(@NonNull PromozioneTestataEntity promozione,
																						   List<String> codiciMeccanica) {
		if (codiciMeccanica == null || codiciMeccanica.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return dao.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(promozione, codiciMeccanica,
				promozione.getCanalePromozioneEntity().getOverlapOffsetStart(),
				promozione.getCanalePromozioneEntity().getOverlapOffsetEnd());
	}

	@Override
	public void remove(@NonNull final PromozioneTestataEntity promozione, @NonNull final String user) throws Exception {
		throw new RuntimeException("operation not permitted: PromozioneTestataEntity cannot be phisically deleted");
	}

	@Override
	public void remove(PromozioneTestataEntity entity) {
		throw new RuntimeException("operation not permitted: PromozioneTestataEntity cannot be phisically deleted");
	}

	@Override
	public List<PromozioneTestataEntity> findAllNotCancelled(List<CanalePromozioneEntity> canali) throws Exception {
		return getDao().findAllNotCancelled(canali);
	}

	@Override
	public List<TipoNegozioEntity> findAllTipoNegozioEntity() throws Exception {
		return tipoNegozioDAO.findAll();
	}

	@Override
	public TipoNegozioEntity findShopTypeById(Long shopTypeId) throws Exception {
		return tipoNegozioDAO.findById(shopTypeId);
	}

	@Override
	public List<CategoriaEntity> findAllCategorie() {
		return categoriaDAO.findAll();
	}

	@Override
	public List<CategoriaEntity> findAllCategorieByCodiciCompratore(@NonNull List<String> codiciCompratore) {
		if (codiciCompratore.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return categoriaDAO.findAllByCodiciCompratore(codiciCompratore);
	}

	@Override
	public List<FornitoreEntity> findAllFornitori() {
		return fornitoreDAO.findAll();
	}

	@Override
	public List<FornitoreEntity> findAllFornitoriByCodiciCompratore(@NonNull List<String> codiciCompratore) {
		if (codiciCompratore.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return fornitoreDAO.findAllByCodiciCompratore(codiciCompratore);
	}

	@Override
	public List<FornitoreEntity> findAllFornitoriAttiviByCodiceCompratore(@NonNull String codiceCompratore) {
		return fornitoreDAO.findAllFornitoriAttiviByCodiceCompratore(codiceCompratore);
	}

	@Override
	public boolean runFunctionAccodamentoPubblicazioni(Long idPromozione, String username, Long idNuovoStatoPromozione)
			throws Exception {
		return getDao().runFunctionAccodamentoPubblicazioni(idPromozione, username, idNuovoStatoPromozione);
	}

    @Override
    public boolean runFunctionCheckStatusTransiction(Long idPromozione, String username, Long idNuovoStatoPromozione)
			throws Exception {
        return getDao().runFunctionCheckStatusTransiction(idPromozione, username, idNuovoStatoPromozione);
    }

	@Override
	public boolean runFunctionExportPianificazioni(Long idPromozione, String idCompratori, String username, Integer timeout)
			throws Exception {
		return getDao().runFunctionExportPianificazioni(idPromozione, idCompratori, username, timeout);
	}

	@Override
	public boolean runFunctionCalcolaSovrapposizioni(Long idPromozione, String username, Integer timeout) throws Exception {
		return getDao().runFunctionCalcolaSovrapposizioni(idPromozione, username, timeout);
	}

	@Override
	public List<PromozioneTestataEntity> findAllNotCancelled(Map<String, String> userFilters,
			List<CanalePromozioneEntity> canali) throws Exception {
		return userFilters.isEmpty() ? getDao().findAllNotCancelled(canali)
				: getDao().findAllNotCancelled(userFilters, canali);
	}

	@Override
	public List<PromozioneTestataEntity> findAllNotCancelledWithPlannedMecc(Map<String, String> userFilters,
																			List<CanalePromozioneEntity> canali,
																			List<Long> idMeccaniche) throws Exception {
		return userFilters == null || userFilters.isEmpty()
				? getDao().findAllNotCancelledWithPlannedMecc(canali, idMeccaniche)
				: getDao().findAllNotCancelledWithPlannedMecc(userFilters, canali, idMeccaniche);
	}

	@Override
	public List<String> findAllTipoConsegna() {
		return negozioDAO.findAllTipoConsegna();
	}

	@Override
	public List<ZonaDto> findAllDistinctZone() {
		return negozioDAO.findAllDistinctZone();
	}

	@Override
	public List<String> findAllDistinctCedi() {
		return negozioDAO.findAllDistinctCedi();
	}

	@Override
	public Long copiaPromozione(String source, String codiceUtente, Long idPromozione, Date dataInizio, Date dataFine) {
		return dao.copiaPromozione(source, codiceUtente, idPromozione, dataInizio, dataFine);
	}
}
