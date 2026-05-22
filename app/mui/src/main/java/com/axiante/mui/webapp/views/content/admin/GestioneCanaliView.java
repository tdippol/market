package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.common.CanaleSecurityEnum;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleSecEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.CanaleLastProgService;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.CfgAbilitaCheckService;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaleRepartoService;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaleSecService;
import com.axiante.mui.dbpromo.persistence.service.CfgCheckService;
import com.axiante.mui.dbpromo.persistence.service.CfgSovrapposizioneMeccanicheService;
import com.axiante.mui.dbpromo.persistence.service.ConfigurazioneMeccanicheCanaleService;
import com.axiante.mui.dbpromo.persistence.service.MeccanicaService;
import com.axiante.mui.dbpromo.persistence.service.RepartoService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.admin.dto.CfgCanaleRepartoDTO;
import com.axiante.mui.webapp.views.content.admin.pojos.ControlliConfigurati;
import com.axiante.mui.webapp.views.content.admin.pojos.ControlliConfigurati.ConfigurazioneDTO;
import com.axiante.mui.webapp.views.content.admin.pojos.StatiBlocco;
import com.axiante.tm1.mdx.objects.Query;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

@Slf4j
@MuiViewModel("gestioneCanali")
@Named("gestioneCanali")
@SessionScoped
public class GestioneCanaliView extends AbstractAdminView {
	private static final long serialVersionUID = -3999502390082770147L;

	@Inject
	CanalePromozioneService canaleService;

	@Inject
	Instance<ConfigurazioneMeccanicheCanaleService> configurazioneMeccanicaCanaleService;

	@Inject
	ConfigurazioneMeccanicheCanaleService cfgCanaleService;

	@Inject
	CfgCanaleSecService cfgCanaleSecService;

	@Inject
	CanaleLastProgService lastProgService;

	@Inject
	CfgCheckService configurazioniService;

	@Inject
	private Instance<StatoPromoService> statoPromoServiceInstance;

	@Inject
	@Getter
	private SovrapposizioniConfigurateBean sovrapposizioniConfigurateBean;

	@Getter
	@Setter
	List<CanalePromozioneEntity> canali;

	@Getter
	Long idCanaleSelezionato;

	@Getter
	@Setter
	Integer activeTab;

	@Inject
	MeccanicaService meccanicaService;

	@Getter
	List<MeccanicaDTO> meccaniche;

	@Getter
	List<CanaleLastProgEntity> progressivi;

	@Getter
	CanalePromozioneEntity canaleSelezionato;

	@Getter
	CfgCanaleSecEntity sicurezzaCanale;

	@Getter
	final CanaleSecurityEnum[] securityValues = CanaleSecurityEnum.values();

	@Getter
	@Setter
	private List<StatiBlocco> availableStatiBlocco;

	@Getter
	@Setter
	private List<CanalePromozioneEntity> channelsForCopy;

	@Getter
	@Setter
	private Long idCopyChannel;
	
	@Getter
	List<MeccanicaDTO> meccanicheAssociate = null;
	boolean rangeChanged = false;

	@Inject
	@Getter
	ControlliConfigurati controlliConfiguratiBean;

	@Inject
	private CfgAbilitaCheckService abilitaCheckService;

	@Inject
	private CfgSovrapposizioneMeccanicheService sovrapposizioniService;

	@Getter
	private String confirmRemoveMeccFromCanaleMsg;

	@Inject
	Instance<CfgCanaleRepartoService> cfgCanaleRepartoService;
	@Inject
	Instance<RepartoService> repartoServiceInstance;
	@Getter
	List<CfgCanaleRepartoDTO> repartiConfigurati ;

	private CfgAbilitaMeccCanaleEntity meccanicaCanaleSelected;

	@Getter
	private boolean flBidoneApertoDisabled = !getFlMeccanicaSingola();

	@Getter
	boolean limiteTestateAttivo;

	@Getter
	boolean flMarchioPrivato = false;

	@Getter
	boolean flLogoMessaggio = false;

	@Getter
	boolean flCalcolaReparto = false;

	@Getter
	boolean flSicurezzaSet = false;

	@Getter
	boolean flCbMultitransazione = false;

	@Getter
	boolean flDifferenziazioneNegozi = false;

	@Getter
	boolean flListaCondizionale = false;

	private static final int TAB_MECCANICHE = 0;
	private static final int TAB_CODICI = 1;
	private static final int TAB_SICUREZZA = 2;
	private static final int TAB_STATI_BLOCCO = 3;
	private static final int TAB_CONTROLLI = 4;
	//	#5148
	//private static final int TAB_SOVRAPPOSIZIONI = 5;


	@PostConstruct
	public void init() {
		readCanali();
		readMeccaniche();
		readAvailableStatiBlocco();
		if (!getCanali().isEmpty()) {
			canaleSelezionato = getCanali().get(0);
			setIdCanaleSelezionato(canaleSelezionato.getId());
			readProgressivi(canaleSelezionato);
			controlliConfiguratiBean.setCanale(canaleSelezionato);
			sovrapposizioniConfigurateBean.setCanale(canaleSelezionato);
		}
		calcolaRepartiConfigurati();
	}

	private void readSicurezza() {
		try {
			sicurezzaCanale = cfgCanaleSecService.findByCanale(canaleSelezionato);
			if (sicurezzaCanale == null) {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "FATAL",
						"Impossibile configurare la sicurezza per il canale selezionato, fare logout"));
			}
		} catch (Exception e) {
			log.error("Error reading security configuration", e);
		}
	}

	private void readCanali() {
		canali = canaleService.findAll();
		final Comparator<CanalePromozioneEntity> byGruppo = Comparator
				.comparing(c -> c.getGruppoPromozioneEntity().getDescrizione());
		final Comparator<CanalePromozioneEntity> byCanale = Comparator
				.comparing(CanalePromozioneEntity::getDescrizione);
		canali.sort(byGruppo.thenComparing(byCanale));
		canali.get(0).getMuiCfgAbilitaMeccCanales().forEach(CfgAbilitaMeccCanaleEntity::getMeccanicheEntity);
	}

	private void calcolaRepartiConfigurati(){
		repartiConfigurati = new ArrayList<>();
		repartoServiceInstance.get().findAll().stream().sorted(Comparator.comparing(RepartoEntity::getDescrizione)).forEach(reparto->{
			try {
				CfgCanaleRepartoDTO conf = new CfgCanaleRepartoDTO(cfgCanaleRepartoService.get().findByCanaleAndReparto(getCanaleSelezionato(), reparto));
				if (!conf.isSelected()) {
					conf.setCanale(getCanaleSelezionato());
					conf.setReparto(reparto);
					conf.setMaxTestate(0);
				}
				repartiConfigurati.add(conf);
			}catch (Exception e){
				log.error(String.format("Errore durante la configurazione del reparto %s per il canale %s",reparto.getCodiceReparto(), getCanaleSelezionato().getCodiceCanale()),e);
			}
		});
	}
	
	private void readMeccaniche() {
		meccaniche = meccanicaService.findAll().stream().map(MeccanicaDTO::new)
				.sorted(Comparator.comparing(MeccanicaDTO::getCodiceMeccanica)).collect(Collectors.toList());
	}

	private void readProgressivi(@NonNull CanalePromozioneEntity canale) {
		progressivi = lastProgService.findByCanale(canale);
		progressivi.sort(Comparator.comparing(CanaleLastProgEntity::getAnno));
	}

	public void setCodeRangeMin(String range) {
		Long l = convertRange(range);
		if ((l != null) && !l.equals(canaleSelezionato.getCodeRangeMin())) {
			canaleSelezionato.setCodeRangeMin(l);
			canaleService.persist(canaleSelezionato);
		}
	}

	public String getCodeRangeMin() {
		if ((canaleSelezionato != null) && (canaleSelezionato.getCodeRangeMin() != null)) {
			return canaleSelezionato.getCodeRangeMin().toString();
		}
		return null;
	}

	public void setCodeRangeMax(String range) {
		Long l = convertRange(range);
		if ((l != null) && !l.equals(canaleSelezionato.getCodeRangeMax())) {
			canaleSelezionato.setCodeRangeMax(l);
			canaleService.persist(canaleSelezionato);
		}
	}

	public String getCodeRangeMax() {
		if ((canaleSelezionato != null) && (canaleSelezionato.getCodeRangeMax() != null)) {
			return canaleSelezionato.getCodeRangeMax().toString();
		}
		return null;
	}

	public String getMaxProgressivo(){
		if ( canaleSelezionato != null && canaleSelezionato.getMaxProgressivo() != null ){
			return canaleSelezionato.getMaxProgressivo().toString();
		} else {
			return "0";
		}
	}

	public void setMaxProgressivo(String range) {
		Long l = convertRange(range);
		if ((l != null) && !l.equals(canaleSelezionato.getCodeRangeMin())) {
			canaleSelezionato.setMaxProgressivo(l);
			canaleService.persist(canaleSelezionato);
		}
	}
	private Integer convertUpload(String range) {
		try {
			return Integer.parseInt(range);
		} catch (Exception e) {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Solo valori numerici interi"));
			log.error("Value not permitted", e);
		}
		return null;
	}
	private Long convertRange(String range) {
		try {
			return Long.parseLong(range);
		} catch (Exception e) {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Solo valori numerici"));
			log.error("Value not permitted", e);
		}
		return null;
	}

	public void setIdCanaleSelezionato(Long idCanaleSelezionato) {
		this.idCanaleSelezionato = idCanaleSelezionato;
		// adesso setta il flag di selezione della meccanica
		boolean success = false;
		if ( idCanaleSelezionato != null){
			CanalePromozioneEntity c = canaleService.findById(idCanaleSelezionato);
			if ( c != null ){
				canaleSelezionato = c;
				meccaniche.forEach(m -> m.setSelected(false));
				canaleSelezionato.getMuiCfgAbilitaMeccCanales().stream()
						.map(CfgAbilitaMeccCanaleEntity::getMeccanicheEntity).forEach(this::activateMeccanica);
				readSicurezza();
				updateStatiBlocco();
				controlliConfiguratiBean.setCanale(canaleSelezionato);
				sovrapposizioniConfigurateBean.setCanale(canaleSelezionato);
				success = true;
				flBidoneApertoDisabled = !getFlMeccanicaSingola();
				limiteTestateAttivo = (c.getMaxPromo() != null && c.getMaxPromo() > -1);
				flMarchioPrivato = c.getFlMarchioPrivato();
				flLogoMessaggio = c.getFlLogoMessaggio();
				flCalcolaReparto = c.getFlCalcolaReparto();
				flSicurezzaSet = c.getFlSicurezzaSet();
				flCbMultitransazione = c.getFlTotalizzatori();
				flDifferenziazioneNegozi = c.getFlDifferenziazioneNegozi();
				flListaCondizionale = c.getFlListaCondizionale();
				calcolaRepartiConfigurati();
			}
		}
		if ( !success ){
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "FATAL",
					"Impossibile selezionare il canale, fare logout"));
		}
	}
	private void activateMeccanica(MeccanicheEntity meccanica) {
		Optional<MeccanicaDTO> mec = meccaniche.stream().filter(m -> m.getId().equals(meccanica.getId())).findFirst();
		if (mec.isPresent()) {
			mec.get().setSelected(true);
			log.info(String.format("Setting selected to meccanica [%s]", mec.get().getDescrizione()));
		}
	}

	// perform the toggle action
	public void toggleCheckBox(MeccanicaDTO meccanica) {
		boolean failure = false;
		if (canaleSelezionato != null) {
			String message = null;
			FacesMessage fmessage = null;
			if (meccanica.isSelected()) {
				// non c'era devo aggiungerla
				CfgAbilitaMeccCanaleEntity cfg = new CfgAbilitaMeccCanaleEntity();
				cfg = (CfgAbilitaMeccCanaleEntity) AuditLogFiller.fillAuditLogFields(cfg, getCurrentUser().getName());
				cfg.setFlagAbilita(1L);
				cfg.setFlagDefault(1L);
				cfg.setMeccanicheEntity(meccanica.getMeccanica());
				canaleSelezionato.addMuiCfgAbilitaMeccCanale(cfg);
			} else {
				// c'era: devo toglierla
				Optional<CfgAbilitaMeccCanaleEntity> optcfg = findCfgAbilitaMeccanicaCanaleByMeccanica(
						meccanica.getMeccanica());
				if (optcfg.isPresent()) {
					final CfgAbilitaMeccCanaleEntity cfg = optcfg.get();
					// #4013: se sono presenti controlli o sovrapposizioni associate, mostro dialog di conferma
					if (isDeletable(cfg)) {
						canaleSelezionato.removeMuiCfgAbilitaMeccCanale(cfg);
					} else {
						// Show confirm dialog
						confirmRemoveMeccFromCanaleMsg = String.format(
								"Ci sono controlli associati alla meccanica '%s' sul canale '%d - %s'; sei sicuro di voler confermare?",
								cfg.getMeccanicheEntity().getCodiceMeccanica(), canaleSelezionato.getCodiceCanale(),
								canaleSelezionato.getDescrizione());
						meccanicaCanaleSelected = cfg;
						executeScript("PF('confirmRemoveMeccFromCanale').show()");
						return;
					}
				} else {
					message = String.format("Impossibile rimuovere la meccanica %s dal canale %s",
							meccanica.getCodiceMeccanica(), canaleSelezionato.getDescrizione());
				}
			}
			if (message == null) {
				try {
					canaleSelezionato = canaleService.persist(canaleSelezionato);
					// canaleService.flush();
					message = String.format("Associazione meccanica %s canale %s", meccanica.getCodiceMeccanica(),
							canaleSelezionato.getDescrizione());
					fmessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", message);
				} catch (Exception e) {
					log.error("Error saving data", e);
					message = String.format("Errore di salvataggio meccanica %s canale %s",
							meccanica.getCodiceMeccanica(), canaleSelezionato.getDescrizione());
					fmessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failure", message);
					failure = true;
				}
			} else {
				log.error(message);
				fmessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failure", message);
				failure = true;
			}
			if (failure) {
				// rollback a video
				meccanica.setSelected(!meccanica.isSelected());
			}
			addMessage(null, fmessage);
		}
	}

	/**
	 * E' possibile eliminare l'associazione meccanica / canale sono se non ha controlli e sovrapposizioni associate
	 * @param cfg
	 * @return
	 */
	private boolean isDeletable(CfgAbilitaMeccCanaleEntity cfg) {
		return (cfg.getControlliConfigurati() == null
					|| (cfg.getControlliConfigurati() != null && cfg.getControlliConfigurati().isEmpty()))
				&& (cfg.getSovrapposizioniConfigurate() == null
					|| (cfg.getSovrapposizioniConfigurate() != null && cfg.getSovrapposizioniConfigurate().isEmpty()));
	}

	public void removeMeccanicaFromCanale() {
		if (meccanicaCanaleSelected != null) {
			// rimuovi controlli e sovrapposizioni
			abilitaCheckService.findByCanaleMeccanica(meccanicaCanaleSelected)
					.forEach(c -> meccanicaCanaleSelected.removeControllo(c));
			sovrapposizioniService.findByCanaleMeccanica(meccanicaCanaleSelected)
					.forEach(s -> meccanicaCanaleSelected.removeSovrapposizione(s));
			canaleSelezionato.removeMuiCfgAbilitaMeccCanale(meccanicaCanaleSelected);
			try {
				canaleService.persist(canaleSelezionato);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
						String.format("Meccanica '%s' rimossa dal canale '%d - %s'",
								meccanicaCanaleSelected.getMeccanicheEntity().getCodiceMeccanica(),
								canaleSelezionato.getCodiceCanale(), canaleSelezionato.getDescrizione())));
			} catch (Exception e) {
				log.error("Error saving data", e);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failure",
						String.format("Errore rimozione meccanica '%s' dal canale '%d - %s'",
								meccanicaCanaleSelected.getMeccanicheEntity().getCodiceMeccanica(),
								canaleSelezionato.getCodiceCanale(), canaleSelezionato.getDescrizione())));
			}
		}
	}

	public void resetMeccanicaFromCanale() {
		canaleSelezionato.getMuiCfgAbilitaMeccCanales().stream()
				.map(CfgAbilitaMeccCanaleEntity::getMeccanicheEntity).forEach(this::activateMeccanica);
	}

	private Optional<CfgAbilitaMeccCanaleEntity> findCfgAbilitaMeccanicaCanaleByMeccanica(MeccanicheEntity meccanica) {
		return canaleSelezionato.getMuiCfgAbilitaMeccCanales().stream()
				.filter(c -> c.getMeccanicheEntity().getId().equals(meccanica.getId())).findFirst();
	}

	public void tabChanged(TabChangeEvent event) {
		TabView tv = (TabView) event.getComponent();
		this.activeTab = tv.getChildren().indexOf(event.getTab());
		switchTab();
	}

	private void switchTab() {
		switch (activeTab) {
		case TAB_MECCANICHE:
			break;
		case TAB_CODICI:
			if (canaleSelezionato != null) {
				readProgressivi(canaleSelezionato);
			} else {
				progressivi = new ArrayList<>();
			}
			break;
		case TAB_STATI_BLOCCO:
			readAvailableStatiBlocco();
			updateStatiBlocco();
			break;
		case TAB_CONTROLLI:
			controlliConfiguratiBean.setCanale(canaleSelezionato);
			break;
//			#5148
//		case TAB_SOVRAPPOSIZIONI:
//			sovrapposizioniConfigurateBean.setCanale(canaleSelezionato);
//			break;
		default:
			// noop
			break;
		}
	}

	private void readAvailableStatiBlocco() {
		try {
			availableStatiBlocco = statoPromoServiceInstance.get().findAllSorted().stream().map(StatiBlocco::new)
					.collect(Collectors.toList());
		} catch (Exception ex) {
			log.error("Error reading available 'Stati Blocco'", ex);
		}
	}

	private void updateStatiBlocco() {
		if (idCanaleSelezionato != null) {
			final CanalePromozioneEntity canale = getCanaleSelectedFromId(idCanaleSelezionato);
			if (canale != null) {
				availableStatiBlocco.forEach(b -> b.setAvailable(canale.getStatiBlocco().contains(b.getEntity())));
			} else {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "FATAL",
						"Impossibile selezionare il canale, fare logout"));
			}
		} else {
			availableStatiBlocco.forEach(b -> b.setAvailable(false));
		}
	}

	public void openCopyFromChannelDialog() {
		resetCopyFromChannelDialog();
		if (idCanaleSelezionato != null) {
			channelsForCopy = canali.stream().filter(c -> !c.getId().equals(idCanaleSelezionato))
					.collect(Collectors.toList());
		} else {
			channelsForCopy = new ArrayList<>();
		}
	}

	public void confirmCopyFromChannel() {
		if (idCopyChannel != null) {
			try {
				final CanalePromozioneEntity canale = getCanaleSelectedFromId(idCanaleSelezionato);
				final CanalePromozioneEntity canaleSrc = getCanaleSelectedFromId(idCopyChannel);
				if (canale == null || canaleSrc == null) {
					addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", "Errore copia 'Stati Blocco'"));
					return;
				}
				// Elimino gli StatiBlocco dal canale corrente
				canale.getStatiBlocco().clear();
				// Aggiungo gli stati presenti nel canaleSrc
				canaleSrc.getStatiBlocco().forEach(canale::addStatoBlocco);
				// Persisto canale corrente
				canaleService.persist(canale);
				addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Stati Blocco copiati",
								String.format("'Stati Blocco' copiati dal canale '%d - %s' al canale '%d - %s'",
										canaleSrc.getCodiceCanale(), canaleSrc.getDescrizione(),
										canale.getCodiceCanale(), canale.getDescrizione())));
				// Reload table
				readAvailableStatiBlocco();
				updateStatiBlocco();
			} catch (Exception ex) {
				log.error(String.format("Error copying 'Stati Blocco' from channel %d to channel %d", idCopyChannel,
						idCanaleSelezionato), ex);
				addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", "Errore copia 'Stati Blocco'"));
			}
		}
	}

	public void resetCopyFromChannelDialog() {
		setIdCopyChannel(null);
	}
	public void repartoToggled(CfgCanaleRepartoDTO config){
		// sto facendo il toggle quindi arriva col valore precedente
		if (!config.isSelected()){
			// sto selezionando
			config.updateConfig(cfgCanaleRepartoService.get().persist(config.getConfig()));
		} else {
			// sto deselezionando
			cfgCanaleRepartoService.get().remove(config.getConfig());
			config.updateConfig(null);
		}
	}
	public void totaleChanged(CfgCanaleRepartoDTO config){
		if (config.getConfig() != null){
			config.updateConfig(cfgCanaleRepartoService.get().persist(config.getConfig()));
		}
	}
	public void statoBloccoToggled(StatiBlocco statoBlocco) {
		try {
			if (idCanaleSelezionato != null) {
				final CanalePromozioneEntity canale = getCanaleSelectedFromId(idCanaleSelezionato);
				if (canale != null) {
					String msg;
					if (statoBlocco.isAvailable()) {
						// StatoBlocco aggiunto al canale
						msg = String.format("Aggiunto lo stato '%s - %s' agli stati bloccati per il canale '%d - %s'",
								statoBlocco.getEntity().getCodiceStato(), statoBlocco.getEntity().getDescrizione(),
								canale.getCodiceCanale(), canale.getDescrizione());
						canale.addStatoBlocco(statoBlocco.getEntity());
					} else {
						// StatoBlocco rimosso dal canale
						msg = String.format("Rimosso lo stato '%s - %s' dagli stati bloccati per il canale '%d - %s'",
								statoBlocco.getEntity().getCodiceStato(), statoBlocco.getEntity().getDescrizione(),
								canale.getCodiceCanale(), canale.getDescrizione());
						canale.removeStatoBlocco(statoBlocco.getEntity());
					}
					canaleService.persist(canale);
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", msg));
				}
			}
		} catch (Exception ex) {
			log.error(String.format("Error updating 'Stato Blocco' for channel with id: %d", idCanaleSelezionato), ex);
			addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione", "Errore aggiornamento Stato Blocco"));
			readCanali();
			updateStatiBlocco();
		}
	}

	private CanalePromozioneEntity getCanaleSelectedFromId(Long id) {
		return canali.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
	}

	@Override
	public void updateView() {
		// noop
	}

	@Override
	public void updateView(String grid) {
		// noop
	}

	@Override
	public Query prepareFilteredQuery(String grid) {
		return null;
	}

	@Override
	protected ApplicationFilterCatalogProducer getCatalogProducer() {
		return null;
	}

	@Override
	protected CatalogReducer getCatalogReducer() {
		return null;
	}

	public void handleSecurityChangeOWW(ValueChangeEvent event) {
		sicurezzaCanale.setOwnerWrite((CanaleSecurityEnum) event.getNewValue());
		cfgCanaleSecService.persist(sicurezzaCanale);
	}

	public void handleSecurityChangeOWR(ValueChangeEvent event) {
		sicurezzaCanale.setOwnerRead((CanaleSecurityEnum) event.getNewValue());
		cfgCanaleSecService.persist(sicurezzaCanale);
	}

	public void handleSecurityChangeOTR(ValueChangeEvent event) {
		sicurezzaCanale.setOtherRead((CanaleSecurityEnum) event.getNewValue());
		cfgCanaleSecService.persist(sicurezzaCanale);
	}

	public void handleSecurityChangeOTW(ValueChangeEvent event) {
		sicurezzaCanale.setOtherWrite((CanaleSecurityEnum) event.getNewValue());
		cfgCanaleSecService.persist(sicurezzaCanale);
	}
	
	public List<ConfigurazioneDTO> getConfigurazioni() {
		return controlliConfiguratiBean.getConfigurazioni();
	}

	public void setDuplicaArticolo(boolean duplicaArticolo){
		if ( canaleSelezionato != null){
			canaleSelezionato.setDuplicaArticolo(duplicaArticolo);
			canaleService.persist(canaleSelezionato);
		}
	}
	public boolean getDuplicaArticolo(){
		if ( canaleSelezionato != null){
			return canaleSelezionato.getDuplicaArticolo();
		} else {
			return true;
		}
	}

	public void setDuplicaTotale(boolean duplicaTotale){
		if ( canaleSelezionato != null){
			canaleSelezionato.setDuplicaTotale(duplicaTotale);
			canaleService.persist(canaleSelezionato);
		}
	}
	public boolean getDuplicaTotale(){
		if ( canaleSelezionato != null){
			return canaleSelezionato.getDuplicaTotale();
		} else {
			return true;
		}
	}

	public boolean getModalitaUploadEstesa(){
		if ( canaleSelezionato != null ){
			return !canaleSelezionato.isLegacyUploadNamingConvention();
		} else {
			return false;
		}
	}
	public void setModalitaUploadEstesa(boolean modalita){
		if ( canaleSelezionato != null ){
			canaleSelezionato.setLegacyUploadNamingConvention(!modalita);
			canaleService.persist(canaleSelezionato);
		}
	}
	public void setDuplicaReparto(boolean duplicaReparto){
		if ( canaleSelezionato != null){
			canaleSelezionato.setDuplicaReparto(duplicaReparto);
			canaleService.persist(canaleSelezionato);
		}
	}

	public String getAbilitaUpload() {
		return canaleSelezionato != null ?
				canaleSelezionato.getAbilitaUpload().toString():
				"0";
	}
	public void setAbilitaUpload(String abilitaUpload) {
		if ( canaleSelezionato != null ){
			Integer integer = convertUpload(abilitaUpload);
			if ( integer != null && ! integer.equals(canaleSelezionato.getAbilitaUpload())){
				canaleSelezionato.setAbilitaUpload(integer);
				canaleService.persist(canaleSelezionato);
			}
		} else {
			log.error("nessun canale selezionato");
		}
	}

	public boolean getDuplicaReparto(){
		if ( canaleSelezionato != null){
			return canaleSelezionato.getDuplicaReparto();
		} else {
			return true;
		}
	}


	public void setDuplicaGrm(boolean duplicaGrm){
		if ( canaleSelezionato != null){
			canaleSelezionato.setDuplicaGrm(duplicaGrm);
			canaleService.persist(canaleSelezionato);
		}
	}
	public boolean getDuplicaGrm(){
		if ( canaleSelezionato != null){
			return canaleSelezionato.getDuplicaGrm();
		} else {
			return true;
		}
	}

	public boolean getFlOverridePianificazione() {
		if (canaleSelezionato != null) {
			return canaleSelezionato.getFlOverridePianificazioneInizio();
		} else {
			return true;
		}
	}

	public void setFlOverridePianificazione(boolean override) {
		if (canaleSelezionato != null) {
			canaleSelezionato.setFlOverridePianificazioneInizio(override);
			canaleService.persist(canaleSelezionato);
		}
	}

	public boolean getFlValorePuntoFragola() {
		if (canaleSelezionato != null) {
			return canaleSelezionato.getFlValorePuntoFragola();
		} else {
			return true;
		}
	}

	public void setFlValorePuntoFragola(boolean flag) {
		if (canaleSelezionato != null) {
			canaleSelezionato.setFlValorePuntoFragola(flag);
			canaleService.persist(canaleSelezionato);
		}
	}

	public boolean getFlMeccanicaSingola() {
		return canaleSelezionato != null ? canaleSelezionato.getFlMeccanicaSingola() : true;
	}

	public void setFlMeccanicaSingola(boolean flag) {
		if (canaleSelezionato != null) {
			canaleSelezionato.setFlMeccanicaSingola(flag);
			if (!flag) {
				canaleSelezionato.setFlBidoneAperto(false);
			}
			canaleService.persist(canaleSelezionato);
			flBidoneApertoDisabled = !flag;
		}
	}

	public boolean getFlBidoneAperto() {
		return canaleSelezionato != null ? canaleSelezionato.getFlBidoneAperto() : true;
	}

	public void setFlBidoneAperto(boolean flag) {
		if (canaleSelezionato != null) {
			canaleSelezionato.setFlBidoneAperto(flag);
			canaleService.persist(canaleSelezionato);
		}
	}

	public boolean getFlCheckDate() {
		if (canaleSelezionato != null) {
			return canaleSelezionato.getFlCheckDate();
		} else {
			return true;
		}
	}

	public void setFlCheckDate(boolean checkDate) {
		if (canaleSelezionato != null) {
			canaleSelezionato.setFlCheckDate(checkDate);
			canaleService.persist(canaleSelezionato);
		}
	}

	public boolean getFlDeleteEnabled() {
		if (canaleSelezionato != null) {
			return canaleSelezionato.getFlCheckDateOverrideDelete();
		} else {
			return true;
		}
	}

	public void setFlDeleteEnabled(boolean override) {
		if (canaleSelezionato != null) {
			canaleSelezionato.setFlCheckDateOverrideDelete(override);
			canaleService.persist(canaleSelezionato);
		}
	}

	public Integer getTolleranzaDataInizio() {
		if (canaleSelezionato != null) {
			return canaleSelezionato.getTolleranzaDataInizio();
		} else {
			return 0;
		}
	}

	public void setTolleranzaDataInizio(Integer inizio){
		if (canaleSelezionato != null ){
			canaleSelezionato.setTolleranzaDataInizio(inizio);
			canaleService.persist(canaleSelezionato);
		}
	}

	public Integer getTolleranzaDataFine(){
		if (canaleSelezionato != null ){
			return canaleSelezionato.getTolleranzaDataFine();
		} else {
			return 0;
		}
	}

	public void setTolleranzaDataFine(Integer fine){
		if (canaleSelezionato != null ){
			canaleSelezionato.setTolleranzaDataFine(fine);
			canaleService.persist(canaleSelezionato);
		}
	}
	public Integer getGiorniDopoDataFine(){
		if (canaleSelezionato != null ){
			return canaleSelezionato.getDeleteActiveDaysAfterDataFine();
		} else {
			return 0;
		}
	}

	public void setGiorniDopoDataFine(Integer fine){
		if (canaleSelezionato != null ){
			canaleSelezionato.setDeleteActiveDaysAfterDataFine(fine);
			canaleService.persist(canaleSelezionato);
		}
	}

	public void setLimiteTestateAttivo(boolean attivo){
		if ( getCanaleSelezionato() != null  ){
			if ( attivo ){
				getCanaleSelezionato().setMaxPromo(0L);
			} else {
				getCanaleSelezionato().setMaxPromo(null);
			}
			this.limiteTestateAttivo = attivo;
			canaleSelezionato = canaleService.persist(getCanaleSelezionato());
		}
	}

	public void limiteTestateChanged() {
		if (getCanaleSelezionato() != null) {
			canaleSelezionato = canaleService.persist(getCanaleSelezionato());
		}
	}

	public void setFlMarchioPrivato(boolean flMarchioPrivato) {
		if (getCanaleSelezionato() != null) {
			getCanaleSelezionato().setFlMarchioPrivato(flMarchioPrivato);
			canaleSelezionato = canaleService.persist(getCanaleSelezionato());
		}
	}

	public void setFlLogoMessaggio(boolean flLogoMessaggio) {
		if (getCanaleSelezionato() != null) {
			getCanaleSelezionato().setFlLogoMessaggio(flLogoMessaggio);
			canaleSelezionato = canaleService.persist(getCanaleSelezionato());
		}
	}

	public void setFlDifferenziazioneNegozi(boolean flDifferenziazioneNegozi) {
		if (getCanaleSelezionato() != null) {
			getCanaleSelezionato().setFlDifferenziazioneNegozi(flDifferenziazioneNegozi);
			canaleSelezionato = canaleService.persist(getCanaleSelezionato());
		}
	}

	public void setFlListaCondizionale(boolean flListaCondizionale) {
		if (getCanaleSelezionato() != null) {
			getCanaleSelezionato().setFlListaCondizionale(flListaCondizionale);
			canaleSelezionato = canaleService.persist(getCanaleSelezionato());
		}
	}

	public void setFlCalcolaReparto(boolean flCalcolaReparto) {
		if (getCanaleSelezionato() != null) {
			getCanaleSelezionato().setFlCalcolaReparto(flCalcolaReparto);
			canaleSelezionato = canaleService.persist(getCanaleSelezionato());
		}
	}

	public void setFlSicurezzaSet(boolean flSicurezzaSet){
		if (getCanaleSelezionato() != null) {
			getCanaleSelezionato().setFlSicurezzaSet(flSicurezzaSet);
			canaleSelezionato = canaleService.persist(getCanaleSelezionato());
		}
	}

	public void setFlCbMultitransazione(boolean flCbMultitransazione){
		if (getCanaleSelezionato() != null) {
			getCanaleSelezionato().setFlTotalizzatori(flCbMultitransazione);
			canaleSelezionato = canaleService.persist(getCanaleSelezionato());
		}
	}

	public boolean getFlOverlapOffet() {
		return canaleSelezionato != null ? canaleSelezionato.getFlOverlapOffset() : false;
	}

	public void setFlOverlapOffet(boolean flOverlapOffet) {
		if (canaleSelezionato != null) {
			canaleSelezionato.setFlOverlapOffset(flOverlapOffet);
			canaleService.persist(canaleSelezionato);
		}
	}

	public Integer getOverlapOffsetStart() {
		return canaleSelezionato != null ? canaleSelezionato.getOverlapOffsetStart() : 0;
	}

	public void setOverlapOffsetStart(Integer start) {
		if (canaleSelezionato != null) {
			canaleSelezionato.setOverlapOffsetStart(start);
			canaleService.persist(canaleSelezionato);
		}
	}

	public Integer getOverlapOffsetEnd() {
		return canaleSelezionato != null ? canaleSelezionato.getOverlapOffsetEnd() : 0;
	}

	public void setOverlapOffsetEnd(Integer end) {
		if (canaleSelezionato != null) {
			canaleSelezionato.setOverlapOffsetEnd(end);
			canaleService.persist(canaleSelezionato);
		}
	}

	public class MeccanicaDTO {
		@Getter
		MeccanicheEntity meccanica;
		@Getter
		@Setter
		boolean selected = false;

		public MeccanicaDTO(@NonNull MeccanicheEntity meccanica) {
			this.meccanica = meccanica;
		}

		public Long getId() {
			return getMeccanica().getId();
		}

		public String getCodiceMeccanica() {
			return getMeccanica().getCodiceMeccanica();
		}

		public String getDescrizione() {
			return getMeccanica().getDescrizione();
		}

		Comparator<MeccanicaDTO> getComparator() {
			return (o1, o2) -> o1.getId().compareTo(o2.getId());
		}
	}
}
