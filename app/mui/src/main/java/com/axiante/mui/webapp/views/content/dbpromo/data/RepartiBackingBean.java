package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.RepartoService;
import com.axiante.mui.webapp.views.FacesContextAware;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepartiBackingBean implements FacesContextAware {
	@Getter
	PromozioneTestataEntity promozioneCorrente;

	@Getter
	public boolean btnAggiungiDisabled;

	@Getter
	@Setter
	Object nuovoReparto;

	@Getter
	List<RepartoEntity> repartiPossibili;

	PromoService promoservice;

	RepartoService repartoService;

	@Getter(value = AccessLevel.PROTECTED)
	private String user;

	public RepartiBackingBean(PromoService promoservice,
			RepartoService repartoService, @NonNull final String user) {
		this.promoservice = promoservice;
		this.repartoService = repartoService;
		this.user = user;
	}

	public void setPromozioneCorrente(PromozioneTestataEntity promozioneCorrente) {
		this.promozioneCorrente = promozioneCorrente;
		updateButtonStatus();
	}

	private void updateButtonStatus() {
		boolean result = false;
		if (this.promozioneCorrente != null) {
			final PromozioneStatoEntity entity = promozioneCorrente.getPromozioneStatoEntities().stream()
					.filter(p -> p.getDataFineStato() == null).findFirst().orElse(null);
			if (entity != null) {
				long stato = Long.parseLong(entity.getStatoPromozioneEntity().getCodiceStato());
				result = (stato == 10) || (stato == 30) || (stato == 311);
			}

			if (result) {
				result = !this.filterReparti().isEmpty();
			}
		}

		this.btnAggiungiDisabled = !result;
	}

	public void prepareDialog() {
		updateRepartiPossibili();
		this.nuovoReparto = null;
	}

	void updateRepartiPossibili() {
		this.repartiPossibili = filterReparti();
	}

	public void aggiungiReparto() {
		if ((nuovoReparto != null) && (promoservice != null)) {
			long idRepartoDaInserire = Long.parseLong(nuovoReparto.toString());

			RepartoEntity repartoDaInserire = repartiPossibili.stream()
					.filter(m -> m.getId() == idRepartoDaInserire).findAny().orElse(null);
			if (repartoDaInserire != null) {
				promozioneCorrente.addReparto(repartoDaInserire);
				try {
					promoservice.persist(promozioneCorrente, user);
					executeScript("updateRowDataReparto(" + promozioneCorrente.getId() + ");");
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
							"Reparto inserita correttamente"));

				} catch (Exception e) {
					log.error("Error adding reparto", e);
					promozioneCorrente.removeReparto(repartoDaInserire);
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore",
							"Impossibile aggiungere il reparto selezionata"));
				}
			} else {
				log.error("impossibile recuperare il reparto con id " + nuovoReparto
						+ " dalla liste dei reparti possibili");
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore",
						"Impossibile aggiungere il reparto selezionata"));
			}
		} else {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore",
					"Errore di inizializzazione: si prega di ri-eseguire il login"));

		}
		updateButtonStatus();
		executeScript("updateAddButtonReparti()");
	}

	private List<RepartoEntity> filterReparti() {
		final Set<RepartoEntity> repartiPresenti = promozioneCorrente.getReparti().stream().collect(Collectors.toSet());
		final List<RepartoEntity> result = repartoService.findAll();
		final List<RepartoEntity> filtered = result.stream()
				// rimuovi le meccaniche gia' presenti
				.filter(reparto -> repartiPresenti == null ? true : !repartiPresenti.contains(reparto))
				.sorted(Comparator.comparing(RepartoEntity::getCodiceReparto))
				.collect(Collectors.toList());
		return filtered;
	}

	public void refresh() throws Exception {
		if (promozioneCorrente != null) {
			promozioneCorrente = promoservice.findById(promozioneCorrente.getId());
		}
	}
}
