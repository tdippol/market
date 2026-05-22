package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTipoTerminaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoTerminaleCassaEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.TipoTerminaleCassaService;
import com.axiante.mui.webapp.views.FacesContextAware;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TipoCassaBackingBean implements FacesContextAware {
	@Getter
	PromozioneTestataEntity promozioneCorrente;

	@Getter
	public boolean btnAggiungiDisabled;

	@Getter
	@Setter
	public Object nuovoTipoCassa;

	@Getter
	public List<TipoTerminaleCassaEntity> tipoCassaAvailable;

	PromoService promoService;

	TipoTerminaleCassaService tipoCassaService;

	@Getter(value = AccessLevel.PROTECTED)
	private String user;

	public TipoCassaBackingBean(PromoService promoService, TipoTerminaleCassaService tipoCassaService, @NonNull final String user) {
		this.promoService = promoService;
		this.tipoCassaService = tipoCassaService;
		this.user = user;
	}

	public void setPromozioneCorrente(PromozioneTestataEntity promozioneCorrente) {
		this.promozioneCorrente = promozioneCorrente;
		updateButtonStatus();
	}

	public void prepareDialog() {
		updateTipoCassaAvailable();
		nuovoTipoCassa = null;
	}

	public void aggiungiTipoCassa() {
		if ((nuovoTipoCassa != null) && (promoService != null)) {
			long idTipoCassa = Long.parseLong(nuovoTipoCassa.toString());
			final TipoTerminaleCassaEntity cassaEntity = tipoCassaAvailable.stream()
					.filter(e -> idTipoCassa == e.getId())
					.findFirst()
					.orElse(null);
			if (cassaEntity != null) {
				PromozioneTipoTerminaleEntity promoTipoCassa = new PromozioneTipoTerminaleEntity();
				promoTipoCassa = (PromozioneTipoTerminaleEntity) AuditLogFiller
						.fillAuditLogFields(new PromozioneTipoTerminaleEntity(), user);
				promoTipoCassa.setTipoTerminaleCassaEntity(cassaEntity);
				promozioneCorrente.addPromozioneTipoTerminale(promoTipoCassa);
				try {
					setPromozioneCorrente(promoService.persist(promozioneCorrente, user));
					executeScript("updateRowData(" + promozioneCorrente.getId() + ");");
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
							"Tipo Cassa inserita correttamente"));
				} catch (Exception ex) {
					// Something went wrong; remove tipo cassa from testata
					log.error(String.format("Errore aggiunta tipo cassa id=%d alla promozione corrente id=%d",
							idTipoCassa, promozioneCorrente.getId()), ex);
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore",
							"Impossibile aggiungere il tipo cassa selezionato"));
					promozioneCorrente.removePromozioneTipoTerminale(promoTipoCassa);
				}
			} else {
				String msg = String.format("Impossibile recuperare il tipo cassa con id=%d dalla lista dei tipo cassa disponibili", idTipoCassa);
				log.error(msg);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore",
						"Impossibile aggiungere il tipo cassa selezionato"));
			}
		} else {
			log.error("Impossibile recuperare un tipo cassa valido");
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore",
					"Errore di inizializzazione: si prega di ri-eseguire il login"));
		}
		updateButtonStatus();
		executeScript("updateAddButton()");
	}

	public void updateButtonStatus() {
		final StatoPromozioneEntity lastStatus = PromoAcl.getStatoCorrente(promozioneCorrente);
		final List<String> validStates = Arrays.asList(
				PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey(),
				PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey(),
				PromoStatusEnum.SBLOCCO_PIANIFICAZIONE_PER_CORREZIONI.getKey(),
				PromoStatusEnum.SBLOCCO_PROMO_IN_ESECUZIONE_PER_CORREZIONI.getKey());
		btnAggiungiDisabled = (promozioneCorrente == null)
				|| filterTipoCassa().isEmpty()
				|| ((lastStatus != null) && !validStates.contains(lastStatus.getCodiceStato()));
	}

	private void updateTipoCassaAvailable() {
		tipoCassaAvailable = filterTipoCassa();
	}

	/**
	 * Filter TipoCassa: all except those already used
	 * @return list of available TipoCassa
	 */
	private List<TipoTerminaleCassaEntity> filterTipoCassa() {
		final List<Long> tipoCassaUsedIds = promozioneCorrente.getPromozioneTipiTerminaleCassa().stream()
				.map(e -> e.getTipoTerminaleCassaEntity().getId())
				.collect(Collectors.toList());
		return tipoCassaService.findAll().stream()
				.filter(c -> !tipoCassaUsedIds.contains(c.getId()))
				.sorted(Comparator.comparing(TipoTerminaleCassaEntity::getDescrizione))
				.collect(Collectors.toList());
	}

	public void updateButtonAggiungiTipoCassa() {
		final StatoPromozioneEntity lastStatus = PromoAcl.getStatoCorrente(promozioneCorrente);
		final List<String> validStates = Arrays.asList(
				PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey(),
				PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey(),
				PromoStatusEnum.SBLOCCO_PIANIFICAZIONE_PER_CORREZIONI.getKey(),
				PromoStatusEnum.SBLOCCO_PROMO_IN_ESECUZIONE_PER_CORREZIONI.getKey());
		btnAggiungiDisabled = (promozioneCorrente == null)
				|| filterTipoCassaAfterDelete().isEmpty()
				|| ((lastStatus != null) && !validStates.contains(lastStatus.getCodiceStato()));
	}

	private List<TipoTerminaleCassaEntity> filterTipoCassaAfterDelete() {
		try {
			setPromozioneCorrente(promoService.findById(promozioneCorrente.getId()));
		} catch (Exception e1) {
			log.error("Error filtering Tipo Cassa", e1);
		}
		final List<Long> tipoCassaUsedIds = promozioneCorrente.getPromozioneTipiTerminaleCassa().stream()
				.map(e -> e.getTipoTerminaleCassaEntity().getId())
				.collect(Collectors.toList());
		return tipoCassaService.findAll().stream()
				.filter(c -> !tipoCassaUsedIds.contains(c.getId()))
				.sorted(Comparator.comparing(TipoTerminaleCassaEntity::getDescrizione))
				.collect(Collectors.toList());
	}
}
