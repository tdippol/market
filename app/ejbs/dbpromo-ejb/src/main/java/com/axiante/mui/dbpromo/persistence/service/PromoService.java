package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.dto.ZonaDto;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoNegozioEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PromoService extends DbPromoService<PromozioneTestataEntity>, Serializable {
	List<PromozioneTestataEntity> read(List<CanalePromozioneEntity> canali) throws Exception;

	PromozioneTestataEntity persist( final PromozioneTestataEntity promozione, String user) throws Exception;

	void remove( PromozioneTestataEntity ui, String codiceUtente) throws Exception;

	List<PromozioneTestataEntity> findAllNotCancelled(List<CanalePromozioneEntity> channels) throws Exception;

	List<TipoNegozioEntity> findAllTipoNegozioEntity() throws Exception;

	TipoNegozioEntity findShopTypeById(Long shopTypeId) throws Exception;

	List<PromozioneTestataEntity> persist( final List<PromozioneTestataEntity> entities, String codiceUtente);

	List<ResponsabileEntity> findAllManager();


	CompratoreEntity findCompratoreEntityById(Long compratoreId) throws Exception;

	List<CompratoreEntity> findAllBuyers();

	List<CompratoreEntity> findAllBuyersByCodes(List<String> codes);

	List<CompratoreEntity> findAllBuyersByIdItems(List<Long> idItems);

	String findTipoElemento(Long promoID);

	List<PromozioneTestataEntity> findOverlappingPromo( final PromozioneTestataEntity promozioneTestataEntity,
			List<CanalePromozioneEntity> canali);

	List<PromozioneTestataEntity> findAllByAnnoAndCanale(String anno, CanalePromozioneEntity canale);

	List<PromozioneTestataEntity> findOverlappingByCodiciMeccanica(PromozioneTestataEntity promozione,
																   List<String> codiciMeccanica);
	List<PromozioneTestataEntity> findAllByAnnoAndMeccanica(PromozioneTestataEntity promozione,
															List<String> codiciMeccanica);

	List<PromozioneTestataEntity> findOverlappingByAnnoAndCodiceMeccanicaWithOffset(PromozioneTestataEntity promozione,
																					List<String> codiciMeccanica);

	List<CategoriaEntity> findAllCategorie();

	List<CategoriaEntity> findAllCategorieByCodiciCompratore(List<String> codiciCompratore);

	List<FornitoreEntity> findAllFornitori();

	List<FornitoreEntity> findAllFornitoriByCodiciCompratore(List<String> codiciCompratore);

	List<FornitoreEntity> findAllFornitoriAttiviByCodiceCompratore(String codiceCompratore);

	boolean runFunctionAccodamentoPubblicazioni(Long idPromozione, String username, Long idNuovoStatoPromozione)
			throws Exception;

	boolean runFunctionCheckStatusTransiction(Long idPromozione, String username, Long idNuovoStatoPromozione)
			throws Exception;

	boolean runFunctionExportPianificazioni(Long idPromozione, String idCompratori, String username, Integer timeout)
		throws Exception;

	boolean runFunctionCalcolaSovrapposizioni(Long idPromozione, String username, Integer timeout) throws Exception;

	List<PromozioneTestataEntity> findAllNotCancelled(Map<String, String> userFilters,
			List<CanalePromozioneEntity> canali) throws Exception;

	List<PromozioneTestataEntity> findAllNotCancelledWithPlannedMecc(Map<String, String> userFilters,
																	 List<CanalePromozioneEntity> canali,
																	 List<Long> idMeccaniche) throws Exception;

	List<String> findAllTipoConsegna();

	List<ZonaDto> findAllDistinctZone();

	List<String> findAllDistinctCedi();

	Long copiaPromozione(String source, String codiceUtente, Long idPromozione, Date dataInizio, Date dataFine);

	List<PromozioneTestataEntity> findOverlappingPromoWithAttributo(final PromozioneTestataEntity promozioneTestataEntity,
																	Long idAttributo, String valoreAttributo);
}
