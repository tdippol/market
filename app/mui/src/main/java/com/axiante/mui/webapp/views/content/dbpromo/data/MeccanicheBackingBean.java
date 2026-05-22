package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.ConfigurazioneMeccanicheCanaleService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
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
public class MeccanicheBackingBean implements FacesContextAware {
	@Getter
	PromozioneTestataEntity promozioneCorrente;

	@Getter
	public boolean btnAggiungiDisabled;

	@Getter
	@Setter
	Object nuovaMeccanica;

	@Getter
	List<MeccanicheEntity> meccanichePossibili;

	PromoService promoservice;

	ConfigurazioneMeccanicheCanaleService cfgMeccanicheCanaleService;

	@Getter(value = AccessLevel.PROTECTED)
	private String user;

	public MeccanicheBackingBean(PromoService promoservice,
			ConfigurazioneMeccanicheCanaleService cfgMeccanicheCanaleService, @NonNull final String user) {
		this.promoservice = promoservice;
		this.cfgMeccanicheCanaleService = cfgMeccanicheCanaleService;
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
				result = stato == 10 || stato == 30 || stato == 311;
			}

			if (result) {
				result = !this.filterMeccaniche().isEmpty() && !isMeccanicaSingolaAssociata();
			}
		}

		this.btnAggiungiDisabled = !result;
	}

	private boolean isMeccanicaSingolaAssociata() {
		return promozioneCorrente.getMuiCanalePromozione().getFlMeccanicaSingola()
				&& !promozioneCorrente.getPromozioneMeccanicheEntities().isEmpty();
	}

	public void prepareDialog() {
		updateMeccanichePossibili();
		this.nuovaMeccanica = null;
	}

	void updateMeccanichePossibili() {
		this.meccanichePossibili = filterMeccaniche();
	}

	public void aggiungiMeccanica() {
		if (nuovaMeccanica != null && promoservice != null) {
			if (isMeccanicaSingolaAssociata()) {
				log.error("impossibile associare la meccanica con id " + nuovaMeccanica
						+ " alla promozione corrente; il canale è configurato per permettere una sola meccanica");
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore",
						"Impossibile aggiungere la meccanica selezionata"));
				return;
			}
			PromozioneMeccanicheEntity meccanica = (PromozioneMeccanicheEntity) AuditLogFiller
					.fillAuditLogFields(new PromozioneMeccanicheEntity(), user);
			long idMeccanicaDaInserire = Long.parseLong(nuovaMeccanica.toString());
			MeccanicheEntity meccanicaDaInserire = meccanichePossibili.stream()
					.filter(m -> m.getId() == idMeccanicaDaInserire).findAny().orElse(null);
			if (meccanicaDaInserire != null) {
				meccanica.setMeccanicheEntity(meccanicaDaInserire);
				promozioneCorrente.addMuiPromozioneMeccanich(meccanica);
				try {
					promozioneCorrente = promoservice.persist(promozioneCorrente, user);
					executeScript("updateRowData(" + promozioneCorrente.getId() + ");");
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
							"Meccanica inserita correttamente"));

				} catch (Exception e) {
					log.error("Error adding meccanica", e);
					promozioneCorrente.removeMuiPromozioneMeccanich(meccanica);
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore",
							"Impossibile aggiungere la meccanica selezionata"));
				}
			} else {
				log.error("impossibile recuperare la meccanica con id " + nuovaMeccanica
						+ " dalla liste delle meccaniche possibili");
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore",
						"Impossibile aggiungere la meccanica selezionata"));
			}
		} else {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore",
					"Errore di inizializzazione: si prega di ri-eseguire il login"));

		}
		updateButtonStatus();
		executeScript("updateCurrentPromo()");
		executeScript("updateAddButton()");
	}

	private List<MeccanicheEntity> filterMeccaniche() {
		final Set<MeccanicheEntity> meccanichePresenti = promozioneCorrente.getPromozioneMeccanicheEntities().stream()
				.map(PromozioneMeccanicheEntity::getMeccanicheEntity).collect(Collectors.toSet());

		/*
		 * Le uniche operazioni consentite sono la rimozione e l’aggiunta di una
		 * meccanica ( sempre compatibilmente con quanto previsto in
		 * MUI_CFG_ABILITA_MECC_CANALE filtrata sul campo ID_CANALE con il canale della
		 * testata promozionale
		 */

		final List<CfgAbilitaMeccCanaleEntity> result = cfgMeccanicheCanaleService
				.findMeccanicaCanaleByPromozione(promozioneCorrente.getCanalePromozioneEntity());
		final List<MeccanicheEntity> filtered = result.stream().filter(c -> {
			// (Flag Abilita = 1 o Flag Default = 1 )
			return c.getFlagAbilita() == 1l || c.getFlagDefault() == 1l;
		}).map(CfgAbilitaMeccCanaleEntity::getMeccanicheEntity)
				// rimuovi le meccaniche gia' presenti
				.filter(meccanica -> meccanichePresenti == null ? true : !meccanichePresenti.contains(meccanica))
			    .sorted(Comparator.comparing(MeccanicheEntity::getCodiceMeccanica))
				.collect(Collectors.toList());

		return filtered;
	}

	public void refresh() throws Exception {
		if (promozioneCorrente != null) {
			promozioneCorrente = promoservice.findById(promozioneCorrente.getId());
		}
	}
}
