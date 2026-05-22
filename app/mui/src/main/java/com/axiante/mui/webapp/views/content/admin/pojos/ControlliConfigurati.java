package com.axiante.mui.webapp.views.content.admin.pojos;

import com.axiante.mui.dbpromo.persistence.entities.*;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.CfgAbilitaCheckService;
import com.axiante.mui.dbpromo.persistence.service.CfgCheckService;
import com.axiante.mui.dbpromo.persistence.service.ConfigurazioneMeccanicheCanaleService;
import com.axiante.mui.webapp.views.FacesContextAware;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class ControlliConfigurati implements Serializable, FacesContextAware {
	private static final long serialVersionUID = -210508160539251655L;
	@Getter
	private MeccanicheEntity meccanicaSelezionata = null;
	@Getter
	private List<ConfigurazioneDTO> configurazioni;
	@Getter
	private List<MeccanicheEntity> meccaniche;
	@Getter
	private List<String> configurazioniPresenti;
	private CanalePromozioneEntity canale;
	@Inject
	private CfgCheckService configurazioniService;
	@Inject
	private CfgAbilitaCheckService abilitaCheckService;
	@Inject
	private ConfigurazioneMeccanicheCanaleService meccanicaCanaleService;
	private CfgAbilitaMeccCanaleEntity meccanicaCanaleAbilitata = null;
	@Inject
	CanalePromozioneService canaleService;

	@Getter
	@Setter
	private Integer idCopyChannel;
	@Getter
	@Setter
	private Long idMeccanicaCopy;
	
	@Getter
	@Setter
	private List<CanalePromozioneEntity> channelsForCopy;

	@PostConstruct
	public void initialize() {
		readConfigurazioni();
	}

	/**
	 * Legge le configurazioni disponibili e le mette in una struttura che tiene
	 * conto della selezione
	 */
	private void readConfigurazioni() {
		configurazioni = configurazioniService.findAll().stream()
				.sorted(Comparator.comparing(CfgCheckEntity::getCodice)).map(ConfigurazioneDTO::new)
				.collect(Collectors.toList());
	}

	/**
	 * setta il canale corrente e aggiorna le meccaniche associate
	 * 
	 * @param canale
	 */
	public void setCanale(@NonNull CanalePromozioneEntity canale) {
		this.canale = canale;
		readMeccanicheAssociate(canale);
	}
	private void readMeccanicheAssociate(@NonNull CanalePromozioneEntity canale) {
		meccaniche = new ArrayList<>();
		if (canale != null) {
			meccaniche.addAll(canale.getMuiCfgAbilitaMeccCanales().stream().map(a -> a.getMeccanicheEntity()).distinct()
					.sorted(Comparator.comparing(MeccanicheEntity::getCodiceMeccanica)).collect(Collectors.toList()));
		}
		;
		if (meccaniche.size() > 0) {
			if ( meccanicaSelezionata == null ) {
				setMeccanicaSelezionata(meccaniche.get(0));
			} else { 
				// la meccanica era gia selezionata.


				if ( !meccaniche.stream().filter(m-> m.getCodiceMeccanica().equals(meccanicaSelezionata.getCodiceMeccanica())).findAny().isPresent() ) {
					// la meccanica selezionata non esiste nel nuovo canale, setta la prima
					setMeccanicaSelezionata(meccaniche.get(0));
				} else {
					// la meccanica esiste: aggiorna la configurazione
					setMeccanicaSelezionata(meccanicaSelezionata);
				}
			}
		} else {
			// se non ci sono meccaniche selezionabili allora
			// fai il reset delle opzioni
			clearSelectedConfigurations();
		}
	}

	/**
	 * Alla selezione della meccanica aggiorna la lista dei controlli configurati
	 * 
	 * @param meccanica
	 */
	public void setMeccanicaSelezionata(MeccanicheEntity meccanica) {
		if (meccanica == null)
			return;
		this.meccanicaSelezionata = meccanica;
		meccanicaCanaleAbilitata = getCfgAbilitaMeccanicaCanale(meccanica);
		readConfigurazioniPresenti();
		clearSelectedConfigurations();
		updateSelectedConfigurations();
	}

	private void readConfigurazioniPresenti() {
		configurazioniPresenti = this.canale.getMuiCfgAbilitaMeccCanales().stream() // di CfgAbilitaMeccCanaleEntity
				.filter(a -> a.getMeccanicheEntity().getCodiceMeccanica()
						.equals(this.meccanicaSelezionata.getCodiceMeccanica())) // solo quelli relativi alla meccanica
				.map(c -> c.getControlliConfigurati()) // mappo in CfgCheckEntity in due passaggi
				.flatMap(Set::stream).map(c -> c.getCheck())// CfgCheckEntity
				.map(c -> c.getCodice())// solo i codici di configurazione
				.distinct() // univoci
				.collect(Collectors.toList());
	}

	/**
	 * per ogni possibile configurazione,se il codice della configurazione e'
	 * presente a partire dal canale, setta il boolean selected a true
	 */
	private void updateSelectedConfigurations() {
		// estraggo i codici configurazione gia' presenti nell
		configurazioni.stream().filter(c -> configurazioniPresenti.contains(c.getCodice())).forEach(c -> {
			CfgAbilitaCheckEntity check = getCfgAbilitaConfigurazione(c.getConfigurazione());
			if (check != null) {
				c.setAbilita(check);
				c.selected = true;
			}
		});
	}

	/**
	 * rimuove tutte le selezioni dalle configurazioni 
	 */
	private void clearSelectedConfigurations() {
		configurazioni.forEach(ConfigurazioneDTO::reset);
	}
	/**
	 * Ritorna un elemento cfgAbilitaMeccanicaCanale associato alla meccanica
	 * 
	 * @param meccanica
	 */
	private CfgAbilitaMeccCanaleEntity getCfgAbilitaMeccanicaCanale(@NonNull MeccanicheEntity meccanica) {
		return canale.getMuiCfgAbilitaMeccCanales().stream()
				.filter(c -> c.getMeccanicheEntity().getId().equals(meccanica.getId())).findAny().orElse(null);
	}

	/**
	 * Ritorna l'elemento di configurazione (molti a molti) a cui
	 * aggiungere/togliere la configurazione
	 * 
	 * @param controllo
	 * @return
	 */
	private CfgAbilitaCheckEntity getCfgAbilitaConfigurazione(CfgCheckEntity controllo) {
		return meccanicaCanaleAbilitata.getControlliConfigurati().stream()
				.filter(c -> c.getCheck().getCodice().equals(controllo.getCodice())).findAny().orElse(null);
	}

	/**
	 * data la configurazione, questa viene aggiunta alla lista delle configurazioni
	 * attive per la coppia canale/meccanica
	 * 
	 * @param codiceConfigurazione
	 * @param severita
	 */
	public boolean addConfiguration(@NonNull String codiceConfigurazione, @NonNull String severita) throws Exception {
		final ConfigurazioneDTO configurazione = configurazioni.stream()
				.filter(c -> c.getCodice().equals(codiceConfigurazione)).findAny().orElse(null);
		if (configurazione != null) {
			CfgAbilitaCheckEntity check = getCfgAbilitaConfigurazione(configurazione.getConfigurazione());
			if (check == null) {
				// creo la relazione
				check = new CfgAbilitaCheckEntity();
				check.setCheck(configurazione.getConfigurazione());
				check.setSeverita(severita);
				// la aggiungo a quelle disponibili a db
				check = meccanicaCanaleAbilitata.addControllo(check);
				// la salvo sul db
				meccanicaCanaleAbilitata = meccanicaCanaleService.persist(meccanicaCanaleAbilitata);
				// aggiorno la lista dietro alla tabella
				configurazione.setAbilita(check);
				return true;
			} else {
				log.error(String.format("Configurazione %s gia' presente per la meccanica %s ed il canale %s",
						codiceConfigurazione, meccanicaSelezionata.getCodiceMeccanica(), canale.getCodiceCanale()));
			}
		} else {
			log.error(String.format("Codice configurazione %s non trovato", codiceConfigurazione));
		}
		return false;
	}

	/**
	 * aggiorna il livello di severita' per una configurazione presente
	 * 
	 * @param codiceConfigurazione
	 * @param severita
	 * @return
	 */
	public boolean updateSeverita(@NonNull String codiceConfigurazione, @NonNull String severita) {
		final ConfigurazioneDTO configurazione = configurazioni.stream()
				.filter(c -> c.getCodice().equals(codiceConfigurazione)).findAny().orElse(null);
		if (configurazione != null) {
			CfgAbilitaCheckEntity check = getCfgAbilitaConfigurazione(configurazione.getConfigurazione());
			if (check != null) {
				check.setSeverita(severita);
				check = meccanicaCanaleAbilitata.addControllo(check);
				// la salvo sul db
				abilitaCheckService.persist(check);
				// aggiorno la lista dietro alla tabella
				configurazione.setAbilita(check);
				return true;
			} else {
				log.error(String.format("Configurazione %s non presente per la meccanica %s ed il canale %s",
						codiceConfigurazione, meccanicaSelezionata.getCodiceMeccanica(), canale.getCodiceCanale()));
			}
		} else {
			log.error(String.format("Codice configurazione %s non trovato", codiceConfigurazione));
		}
		return false;
	}

	/**
	 * Disabilita la configurazione per la coppia canale/meccanica corrente
	 * 
	 * @param codiceConfigurazione
	 * @return
	 */
	public boolean removeConfigurazione(@NonNull String codiceConfigurazione) throws Exception {
		final ConfigurazioneDTO configurazione = configurazioni.stream()
				.filter(c -> c.getCodice().equals(codiceConfigurazione)).findAny().orElse(null);
		if (configurazione != null) {
			final CfgAbilitaCheckEntity check = getCfgAbilitaConfigurazione(configurazione.getConfigurazione());
			if (check != null) {
				// rimuovo la relazione
				meccanicaCanaleAbilitata.removeControllo(check);
				// la salvo sul db
				meccanicaCanaleService.persist(meccanicaCanaleAbilitata);
				configurazione.reset();
				return true;
			} else {
				log.error(String.format("Configurazione %s non presente per la meccanica %s ed il canale %s",
						codiceConfigurazione, meccanicaSelezionata.getCodiceMeccanica(), canale.getCodiceCanale()));
			}
		} else {
			log.error(String.format("Codice configurazione %s non trovato", codiceConfigurazione));
		}
		return false;
	}

	public void abilitaConfigurazione(ConfigurazioneDTO configurazione) {
		if (configurazione.isSelected()) {
			try {
				if (addConfiguration(configurazione.getCodice(), configurazione.getSeverita())) {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							String.format("Configurazione %s associata", configurazione.getCodice())));
				} else {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Errore durante il salvataggio dei dati"));
				}
			} catch (Exception e) {
				log.error("Error saving data", e);
			}
		} else {
			try {
				if (removeConfigurazione(configurazione.getCodice())) {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							String.format("Configurazione %s disassociata", configurazione.getCodice())));
				} else {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Errore durante il salvataggio dei dati"));
				}
			} catch (Exception e) {
				log.error("Error saving data", e);
			}
		}
	}

	public void openCopyFromChannelDialog() {
		resetCopyFromChannelDialog();
		channelsForCopy = new ArrayList<>();
		if (this.canale != null) {
			final Comparator<CanalePromozioneEntity> byGruppo = Comparator
					.comparing(c -> c.getGruppoPromozioneEntity().getDescrizione());
			final Comparator<CanalePromozioneEntity> byCanale = Comparator
					.comparing(CanalePromozioneEntity::getDescrizione);
			channelsForCopy.addAll(
					canaleService.findAll().stream()//tutti i canali disponibili
					.filter(c -> !c.getId().equals(this.canale.getId())) //rimuovo il canale corrente
					.sorted(byGruppo.thenComparing(byCanale)) //ordino
					.collect(Collectors.toList())
					);
		} 
	}
	public void resetCopyFromChannelDialog() {
		setIdCopyChannel(null);
	}
	
	public void confirmCopyFromChannel() {
		if (idCopyChannel != null) {
			try {
				final CanalePromozioneEntity canaleSelezionato = canaleService.findById(idCopyChannel.longValue());
				// dal canale selezionato prendo tutte le meccaniche che ho nel canale corrente
				if ( canaleSelezionato == null ) {
					addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", "Errore di copia delle configurazioni"));
					log.error(String.format("Canale con id %d non presente a db", idCopyChannel));
					return;

				}
				List<MeccanicheEntity> meccanicheDisponibili = canaleSelezionato.getMuiCfgAbilitaMeccCanales().stream().map(CfgAbilitaMeccCanaleEntity::getMeccanicheEntity).filter(m-> meccaniche.contains(m)).collect(Collectors.toList());
				if ( meccanicheDisponibili.size() == 0 ) {
					addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Nessuna meccanica disponibile",
									String.format("Nessuna meccanica associata al canale %s e' associata al canale %s", canaleSelezionato.getCodiceCanale(), canale.getCodiceCanale())
									));
					return;
				}
				// ho delle meccaniche da copiare : 
				// per ogni meccanica devo cancellare le relazioni delle configurazioni e crearne di 
				// nuove copiandole dalla sorgente
				//rimuovi 

				meccanicheDisponibili.forEach(meccanica->{
					final CfgAbilitaMeccCanaleEntity meccanicaCanaleDaModificare = getCfgAbilitaMeccanicaCanale(meccanica);
					final List<CfgAbilitaCheckEntity> configurazioniDaCopiare = copiaConfigurazioneDaMeccanica(canaleSelezionato, meccanica);
					List<CfgAbilitaCheckEntity> configurazioniDaCancellare = abilitaCheckService.findByCanaleMeccanica(meccanicaCanaleDaModificare);
					configurazioniDaCancellare.forEach(c->{
						meccanicaCanaleDaModificare.removeControllo(c);
					});
					meccanicaCanaleService.persist(meccanicaCanaleDaModificare); //rimuovi l'associazione con meccanica/canale
					configurazioniDaCopiare.forEach(c->meccanicaCanaleDaModificare.addControllo(c));
					meccanicaCanaleService.persist(meccanicaCanaleDaModificare);
				});
				// adesso devo rileggere le configurazioni
				clearSelectedConfigurations();
				readMeccanicheAssociate(canale);
				addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Configurazioni copiate"));
				
			} catch (Exception ex) {
				addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", "Errore durante il salvataggio delle configurazioni"));
				log.error("Errore durante la copia da canale", ex);
			}
		}
	}

	private List<CfgAbilitaCheckEntity> copiaConfigurazioneDaMeccanica(CanalePromozioneEntity canaleSelezionato, MeccanicheEntity meccanicaEntity) {
		return canaleSelezionato.getMuiCfgAbilitaMeccCanales().stream()
			.filter(a->a.getMeccanicheEntity().getId().equals(meccanicaEntity.getId()))
			.flatMap(c->c.getControlliConfigurati().stream())
			.map(c->{
				//creo un nuovo controllo
				CfgAbilitaCheckEntity e = new CfgAbilitaCheckEntity();
				e.setSeverita(c.getSeverita());
				e.setCheck(c.getCheck());
				return e;
			})
			.collect(Collectors.toList());
			
	}
	
	public List<MeccanicheEntity> getMeccanicheForCopy(){
		return meccaniche.stream().filter(c-> !c.equals(meccanicaSelezionata)).collect(Collectors.toList());
	}
	public void resetCopyFromMeccanicaDialog() {
		setIdMeccanicaCopy(null);
	}
	public void confirmCopyFromMeccania	() {
		if ( idMeccanicaCopy != null) {
			MeccanicheEntity meccanica = meccaniche.stream().filter(c->c.getId().equals(idMeccanicaCopy)).findFirst().orElse(null);
			if ( meccanica == null ) {
				addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", "Errore di copia delle configurazioni"));
				log.error(String.format("Meccanica con id %d non presente a db per il canale %d", idMeccanicaCopy, canale.getCodiceCanale()));
				
				return;
			}
			try {
				final CfgAbilitaMeccCanaleEntity meccanicaCanaleDaModificare = getCfgAbilitaMeccanicaCanale(meccanicaSelezionata);
				final List<CfgAbilitaCheckEntity> configurazioniDaCopiare = copiaConfigurazioneDaMeccanica(canale, meccanica);
				if ( configurazioniDaCopiare.size() == 0) {
					addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Nessuna configurazione", "non ci sono configurazioni compatibili"));
					return;
				}
				List<CfgAbilitaCheckEntity> configurazioniDaCancellare = abilitaCheckService.findByCanaleMeccanica(meccanicaCanaleDaModificare);
				configurazioniDaCancellare.forEach(c->{
					meccanicaCanaleDaModificare.removeControllo(c);
				});
				meccanicaCanaleService.persist(meccanicaCanaleDaModificare); //rimuovi l'associazione con meccanica/canale
				configurazioniDaCopiare.forEach(c->meccanicaCanaleDaModificare.addControllo(c));
				meccanicaCanaleService.persist(meccanicaCanaleDaModificare);
				
				// adesso devo rileggere le configurazioni
				clearSelectedConfigurations();
				readMeccanicheAssociate(canale);
				addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Configurazioni copiate"));
			} catch (Exception e ) {
				addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", "Errore durante il salvataggio delle configurazioni"));
				log.error("Errore durante la copia da meccanica", e);

			}

		}
	}
	
	
	public class ConfigurazioneDTO {
		@Getter
		private CfgAbilitaCheckEntity abilitato;
		@Getter
		private CfgCheckEntity configurazione;
		@Getter
		private CfgCheckEntity configurazioneBase;
		@Getter
		@Setter
		boolean selected = false;

		public ConfigurazioneDTO(@NonNull CfgCheckEntity configurazione) {
			this.configurazioneBase = configurazione;
			this.configurazione = configurazione;
		}

		public void setAbilita(CfgAbilitaCheckEntity abilitato) {
			this.abilitato = abilitato;
			if (abilitato == null) {
				this.configurazione = configurazioneBase;
				this.selected = false;
			} else {
				this.configurazione = abilitato.getCheck();
				this.selected = true;
			}

		}

		public Long getId() {
			return getConfigurazione().getId();
		}

		public String getCodice() {
			return getConfigurazione().getCodice();
		}

		public String getDescrizione() {
			return getConfigurazione().getDescrizione();
		}

		public String getMessaggioErrore() {
			return getConfigurazione().getMessaggioErrore();
		}

		public String getAmbito() {
			return getConfigurazione().getAmbito();
		}

		public String getTipo() {
			return getConfigurazione().getTipoControllo();
		}

		public String getSeverita() {
			if (getAbilitato() == null) {
				return "Errore";
			}
			return getAbilitato().getSeverita();
		}

		Comparator<ConfigurazioneDTO> getComparator() {
			return (o1, o2) -> o1.getId().compareTo(o2.getId());
		}

		public void reset() {
			setAbilita(null);
		}
		
		public void setSeverita(String severita) {
			if ( getAbilitato() != null) {
				getAbilitato().setSeverita(severita);
			}
		}
	}

}
