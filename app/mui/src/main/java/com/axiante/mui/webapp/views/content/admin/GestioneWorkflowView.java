package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiCanaleConsentEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiTransizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.CfgAzioniService;
import com.axiante.mui.dbpromo.persistence.service.CfgStatiCanaleConsentitiService;
import com.axiante.mui.dbpromo.persistence.service.CfgStatiTransizioniService;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import com.axiante.mui.dbpromo.persistence.service.GruppoPromozioniService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.admin.pojos.CfgAzioniConsent;
import com.axiante.mui.webapp.views.content.admin.pojos.GruppoCanalePojo;
import com.axiante.mui.webapp.views.content.admin.pojos.StatoCanalePojo;
import com.axiante.tm1.mdx.objects.Query;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

@Slf4j
@MuiViewModel("gestioneWorkflow")
@Named("gestioneWorkflow")
@SessionScoped
public class GestioneWorkflowView extends AbstractAdminView {
	private static final long serialVersionUID = -3999502390082770147L;

	@Inject
	@Getter
	transient private ApplicationFilterCatalogProducer catalogProducer;

	@Inject
	@Getter
	transient private CatalogReducer catalogReducer;

	@Inject
	transient private GruppoPromozioniService groupService;

	@Inject
	transient private StatoPromoService statoPromoService;

	@Inject
	transient private Instance<CanalePromozioneService> canalePromoServiceInstance;

	@Inject
	transient private Instance<CfgStatiCanaleConsentitiService> cfgStatiCanaleConsServiceInstance;

	@Inject
	transient private Instance<CreatePromotionService> createPromoServiceInstance;

	@Inject
	transient private Instance<CfgStatiTransizioniService> cfgStatiTransizioniServiceInstance;

	@Inject
	transient private Instance<CfgAzioniService> azioniServiceInstance;

	@Inject
	private Instance<CfgStatiCanaleConsentitiService> statiCanaleConsentitiServiceInstance;

	@Getter
	@Setter
	private List<GruppoCanalePojo> groupsChannels;

	@Getter
	@Setter
	private GruppoCanalePojo groupChannelSelected;

	@Getter
	private Object idChannelSelected;

	@Getter
	@Setter
	Integer activeTab = 0;

	@Getter
	@Setter
	private List<CfgAzioniConsent> availableActions;
	@Getter
	@Setter
	private List<CfgAzioniConsent> filteredActions;
	@Getter
	StatoCanalePojo selectedStatus;

	@Getter
	@Setter
	private List<StatoCanalePojo> availableStatuses;

	@Getter
	@Setter
	private List<CfgStatiTransizioniEntity> transitions;

	@Getter
	@Setter
	private CfgStatiTransizioniEntity selectedTransition;

	@Getter
	@Setter
	private List<StatoPromozioneEntity> statusesList;

	@Getter
	private Long idFromStatus;

	@Getter
	private Long idToStatus;

	@Getter
	@Setter
	private Long idErrStatus;

	@Getter
	@Setter
	private Boolean flagPublish;

	@Getter
	@Setter
	private Boolean flagControl;

	@Getter
	@Setter
	private Boolean flagAutomatico;

	@Getter
	@Setter
	private boolean confirmTransactionBtnDisabled = true;

	@Getter
	@Setter
	private List<CanalePromozioneEntity> channelsForCopy;

	@Getter
	@Setter
	private Long idCopyChannel;

	@PostConstruct
	public void init() {
		loadGroupsChannels();
		if (!groupsChannels.isEmpty()) {
			groupChannelSelected = groupsChannels.get(0);
			setIdChannelSelected(groupChannelSelected.getChannel().getId());
		}
		
		// alla creazione lo stato selezionato e' il primo della lista
		getSelectedStatuses();
		setFlagAutomatico(false);
	}

	private GruppoCanalePojo getGruppoCanale(@NonNull Long id) {
		return groupsChannels.stream()
				.filter(gc -> gc.getChannel().getId().equals(id))
				.findFirst()
				.orElse(null);
	}

	public void tabChanged(TabChangeEvent event) {
		TabView tv = (TabView) event.getComponent();
		this.activeTab = tv.getChildren().indexOf(event.getTab());
		switchTab();
	}

	private void switchTab() {
		switch(activeTab) {
		case 0:
			loadAvailableStatuses();
			break;
		case 1:
			loadTransitions();
			break;
		case 2 :
			loadAvailableActions();
			break;
		default:
		}
	}

	private void loadAvailableActions() {
		try {
			availableActions = azioniServiceInstance.get().findAll().stream()
					.sorted(Comparator.comparing(CfgAzioniEntity::getCodice))
					.map(CfgAzioniConsent::new).collect(Collectors.toList());
		} catch (Exception e ) {
			log.error("error", e);
		}
	}

	public void toggleStatusVisible(StatoCanalePojo status) {
		if (groupChannelSelected != null) {
			CanalePromozioneEntity channel = groupChannelSelected.getChannel();
			try {
				if (status.isAvailable()) {
					// Add status to channel
					CfgStatiCanaleConsentEntity statoCanaleConsentito = (CfgStatiCanaleConsentEntity) AuditLogFiller
							.fillAuditLogFields(new CfgStatiCanaleConsentEntity(), getCurrentUser().getName());
					statoCanaleConsentito.setMuiCanalePromozione(channel);
					statoCanaleConsentito.setStatoPromozioneEntity(status.getStato());
					channel.addMuiCfgStatiCanaleConsent(statoCanaleConsentito);
					channel = canalePromoServiceInstance.get().persist(channel);
				} else {
					// Remove status from channel
					CfgStatiCanaleConsentEntity statoCanaleConsentito = cfgStatiCanaleConsServiceInstance.get()
							.findByCanaleAndStato(channel, status.getStato());
					if (statoCanaleConsentito != null) {
						cfgStatiCanaleConsServiceInstance.get().remove(statoCanaleConsentito);
						channel.removeMuiCfgStatiCanaleConsent(statoCanaleConsentito);
					} else {
						final String msg = String.format("Errore rimozione stato '%s' da canale '%s'",
								status.getStato().getLabel(), channel.getDescrizione());
						log.error(msg);
						addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione", msg));
					}
				}
				groupChannelSelected.setChannel(channel);
				final String msg = String.format("Stato '%s' %s canale '%s'", status.getStato().getLabel(),
						status.isAvailable() ? "aggiunto a" : "rimosso da", channel.getDescrizione());
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
			} catch (Exception ex) {
				final String msg = String.format("Errore %s canale '%s'",
						status.isAvailable()
						? "aggiunta stato '" + status.getStato().getLabel() + "' a "
								: "rimozione stato '" + status.getStato().getLabel() + "' da ",
								channel.getDescrizione());
				log.error(msg, ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione", msg));
			}
			loadGroupsChannels();
			loadAvailableStatuses();
		}
	}

	public void setIdChannelSelected(Object idChannelSelected) {
		this.idChannelSelected = idChannelSelected;
		if (idChannelSelected != null) {
			final GruppoCanalePojo gruppoCanalePojo = getGruppoCanale(new Long(idChannelSelected.toString()));
			if (gruppoCanalePojo != null) {
				groupChannelSelected = gruppoCanalePojo;
				loadAvailableStatuses();
				loadTransitions();
				loadAvailableActions();
			} else {
				log.warn("Error getting GroupChannel with channelId: " + idChannelSelected);
			}
		}
	}

	public void deleteTransition() {
		if (selectedTransition != null) {
			try {
				cfgStatiTransizioniServiceInstance.get().remove(selectedTransition);
				final String msg = String.format("Transizione da stato '%s - %s' a stato '%s - %s' eliminata con successo",
						selectedTransition.getStatoTransizione().getCodiceStato(),
						selectedTransition.getStatoTransizione().getLabel(),
						selectedTransition.getStatoPromozione().getCodiceStato(),
						selectedTransition.getStatoPromozione().getLabel());
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Transizione eliminata", msg));
				loadTransitions();
				selectedTransition = null;
			} catch (Exception ex) {
				final String msg = String.format("Errore eliminazione transizione da stato '%s - %s' a stato '%s - %s'",
						selectedTransition.getStatoTransizione().getCodiceStato(),
						selectedTransition.getStatoTransizione().getLabel(),
						selectedTransition.getStatoPromozione().getCodiceStato(),
						selectedTransition.getStatoPromozione().getLabel());
				log.error(msg, ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", msg));
			}
		}
	}

	public void setIdFromStatus(Long idFromStatus) {
		this.idFromStatus = idFromStatus;
		handleDialogConfirmBtn();
	}

	public void setIdToStatus(Long idToStatus) {
		this.idToStatus = idToStatus;
		handleDialogConfirmBtn();
	}

	public void openAddTransitionDialog() {
		resetAddTransitionDialog();
		statusesList = availableStatuses.stream()
				.filter(StatoCanalePojo::isAvailable)
				.map(StatoCanalePojo::getStato)
				.collect(Collectors.toList());
	}

	public void openCopyFromChannelDialog() {
		resetCopyFromChannelDialog();
		if (idChannelSelected != null) {
			try {
				channelsForCopy = groupsChannels.stream()
						.filter(gc -> !gc.getChannel().getId().equals(new Long(idChannelSelected.toString())))
						.map(GruppoCanalePojo::getChannel).collect(Collectors.toList());
			} catch (Exception ex) {
				log.warn("Error getting available channes for copy", ex);
				channelsForCopy = new ArrayList<>();
			}
		} else {
			channelsForCopy = new ArrayList<>();
		}
	}

	public void resetCopyFromChannelDialog() {
		setIdCopyChannel(null);
	}

	public void confirmCopyFromChannel() {
		if (idCopyChannel != null) {
			try {
				final CanalePromozioneEntity channel = groupChannelSelected.getChannel();
				final CanalePromozioneEntity channelSrc = channelsForCopy.stream()
						.filter(c -> idCopyChannel.equals(c.getId())).findFirst().orElse(null);
				if ((channelSrc == null) || (channel == null)) {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
							"Errore copia azioni consentite"));
					return;
				}
				channel.getMuiCfgStatiCanaleConsents().forEach(sc -> copyActionsFromSource(sc, channelSrc));
				canalePromoServiceInstance.get().persist(channel);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Azioni copiate",
						String.format("Azioni copiate dal canale '%s' al canale '%s'",
								channelSrc.getDescrizione(), channel.getDescrizione())));
			} catch (Exception ex) {
				log.error(String.format("Error copying action from channel %s to channel %s",
						idCopyChannel, idChannelSelected), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						"Errore copia azioni consentite"));
			}
		}
	}

	private void copyActionsFromSource(CfgStatiCanaleConsentEntity statoConsentitoDest, CanalePromozioneEntity canaleSrc) {
		final CfgStatiCanaleConsentEntity statoConsentitoSrc = canaleSrc.getMuiCfgStatiCanaleConsents().stream()
				.filter(sc -> sc.getStatoPromozioneEntity().getId().equals(statoConsentitoDest.getStatoPromozioneEntity().getId()))
				.findFirst().orElse(null);
		if (statoConsentitoSrc != null) {
			statoConsentitoDest.getAzioni().clear();
			statoConsentitoDest.setAzioni(new HashSet<>(statoConsentitoSrc.getAzioni()));
		}
	}

	public void confirmTransaction() {
		try {
			if ((idFromStatus == null) || (idToStatus == null) || (groupChannelSelected == null)) {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
						"Errore salvataggio transizione; contattare l'assistenza tecnica"));
				return;
			}
			StatoPromozioneEntity fromStatus = getStatoPromozioneById(idFromStatus);
			StatoPromozioneEntity toStatus = getStatoPromozioneById(idToStatus);
			if ((fromStatus == null) || (toStatus == null)) {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
						"Errore salvataggio transizione; contattare l'assistenza tecnica"));
				return;
			}
			if (flagAutomatico && automaticTransactionExists(groupChannelSelected.getChannel(), fromStatus)) {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
						String.format("La transizione automatica dallo stato '%s - %s' esiste gia'",
								fromStatus.getCodiceStato(), fromStatus.getLabel())));
				return;
			}
			if (isTransitionExists(groupChannelSelected.getChannel(), fromStatus, toStatus, flagAutomatico)) {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
						String.format("La transizione dallo stato '%s - %s' allo stato '%s - %s' esiste gia'",
								fromStatus.getCodiceStato(), fromStatus.getLabel(),
								toStatus.getCodiceStato(), toStatus.getLabel())));
				return;
			}
			CfgStatiTransizioniEntity transition = new CfgStatiTransizioniEntity();
			transition.setMuiCanalePromozione(groupChannelSelected.getChannel());
			transition.setStatoTransizione(toStatus);
			transition.setStatoPromozione(fromStatus);
			transition.setStatoErrore(idErrStatus != null
					? getStatoPromozioneById(idErrStatus)
							: fromStatus);
			if (flagPublish != null) {
				transition.setFlagPubblica(flagPublish);
			}
			if (flagControl != null) {
				transition.setFlagControlli(flagControl);
			}
			if (flagAutomatico != null) {
				transition.setFlagAutomatico(flagAutomatico);
			}
			cfgStatiTransizioniServiceInstance.get().persistWithAuditLog(transition, getCurrentUser().getName());
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Transizione creata",
					String.format("Transizione dalla stato '%s - %s' allo stato '%s - %s' creata correttamente (modalità: %s)",
							fromStatus.getCodiceStato(), fromStatus.getLabel(),
							toStatus.getCodiceStato(), toStatus.getLabel(),
							transition.getTipo())));
			loadTransitions();
		} catch (Exception ex) {
			log.error("Error saving transition", ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
					"Errore salvataggio transizione; contattare l'assistenza tecnica"));
		}
	}

	private StatoPromozioneEntity getStatoPromozioneById(@NonNull Long id) {
		return statusesList.stream().filter(s -> id.equals(s.getId())).findFirst().orElse(null);
	}

	public void resetAddTransitionDialog() {
		setIdFromStatus(null);
		setIdToStatus(null);
		setIdErrStatus(null);
		setFlagPublish(null);
		setFlagControl(null);
		setFlagAutomatico(false);
		setConfirmTransactionBtnDisabled(true);
	}

	private void loadGroupsChannels() {
		groupsChannels = new ArrayList<>();
		try {
			groupService.findAll().stream()
			.sorted(Comparator.comparing(GruppoPromozioneEntity::getDescrizione))
			.forEach(g -> g.getMuiCanalePromoziones().stream()
					.sorted(Comparator.comparing(CanalePromozioneEntity::getDescrizione))
					.forEach(c -> groupsChannels.add(new GruppoCanalePojo(g, c))));
		} catch (Exception ex) {
			log.warn("Error loading groups and channels", ex);
		}
	}

	private void loadAvailableStatuses() {
		availableStatuses = new ArrayList<>();
		if (groupChannelSelected != null) {
			try {
				statoPromoService.findAll().stream().sorted(Comparator.comparing(StatoPromozioneEntity::getCodiceStato))
						.forEach(s -> availableStatuses.add(
						new StatoCanalePojo(s, isStatusAvailableForChannel(s, groupChannelSelected.getChannel()))));
			} catch (Exception ex) {
				log.warn("Error loading available statuses", ex);
			}
		}
	}

	private void loadTransitions() {
		if (groupChannelSelected != null) {
			Comparator<CfgStatiTransizioniEntity> byStatusFrom = Comparator
					.comparing(t -> t.getStatoPromozione().getCodiceStato());
			Comparator<CfgStatiTransizioniEntity> byStatusTo = Comparator
					.comparing(t -> t.getStatoTransizione().getCodiceStato());
			transitions = createPromoServiceInstance.get()
					.findAllPromoTransitionByChannel(groupChannelSelected.getChannel())
					.stream()
					.sorted(byStatusFrom.thenComparing(byStatusTo))
					.collect(Collectors.toList());
		} else {
			transitions = new ArrayList<>();
		}
	}

	private boolean isStatusAvailableForChannel(final StatoPromozioneEntity stato, final CanalePromozioneEntity canale) {
		return canale.getMuiCfgStatiCanaleConsents().stream()
				.anyMatch(sc -> sc.getStatoPromozioneEntity().getCodiceStato().equals(stato.getCodiceStato()));
	}

	private void handleDialogConfirmBtn() {
		setConfirmTransactionBtnDisabled((idFromStatus == null) || (idToStatus == null));
	}

	private boolean isTransitionExists(final CanalePromozioneEntity channel, final StatoPromozioneEntity fromStatus,
									   final StatoPromozioneEntity toStatus, Boolean flagAutomatico) {
		final List<CfgStatiTransizioniEntity> transizioni = cfgStatiTransizioniServiceInstance.get()
				.findAllByCanaleAndStatusFromAndStatusTo(channel, fromStatus, toStatus);
		return transizioni != null && transizioni.stream().anyMatch(t -> flagAutomatico.equals(t.getFlagAutomatico()));
	}

	private boolean automaticTransactionExists(CanalePromozioneEntity channel, StatoPromozioneEntity fromStatus) {
		return cfgStatiTransizioniServiceInstance.get().existAutomaticByCanaleAndFromStatus(channel, fromStatus);
	}

	@Override
	public Query prepareFilteredQuery(final String grid) {
		return null;
	}

	@Override
	public void updateView() {
		// Do nothing
	}

	@Override
	public void updateView(final String grid) {
		updateView();
	}

	public List<StatoCanalePojo> getSelectedStatuses(){
		List<StatoCanalePojo> list = new ArrayList<>();
		if ( getAvailableStatuses() != null ) {
			list = getAvailableStatuses().stream().filter(StatoCanalePojo::isAvailable).collect(Collectors.toList());
			if ( (list.size() > 0) && (selectedStatus == null)) {
				setSelectedStatus(list.get(0));
			}
		}
		return list;
	}

	public void setSelectedStatus(StatoCanalePojo status) {
		this.selectedStatus = status;
		updateAzioniConsent();
		keepSelectedStatusFocus();
	}

	private void updateAzioniConsent() {
		if ( selectedStatus != null ) {
			try {
				List<CfgAzioniEntity> azioniConsent = statiCanaleConsentitiServiceInstance.get()
						.findAzioniConsentByCanaleAndStato(groupChannelSelected.getChannel(), selectedStatus.getStato());
				availableActions.forEach(a -> a.setAvailable(azioneAvaliable(azioniConsent, a.getEntity())));
			} catch ( Exception e) {
				log.error("Error updating actions list", e);
			}
		} else {
			availableActions.forEach(a -> a.setAvailable(false));
		}
	}

	public void onStatusSelect(SelectEvent event) {
		StatoCanalePojo ojb = (StatoCanalePojo)event.getObject();
		setSelectedStatus(ojb);
	}

	boolean azioneAvaliable(@NonNull List<CfgAzioniEntity> azioniConsent, @NonNull CfgAzioniEntity azione) {
		// debug code
		for(CfgAzioniEntity a : azioniConsent) {
			log.info(String.format("Azione %s match = %s", a.getId().equals(azione.getId()), a.getCodice()));
		}
		return azioniConsent.stream().anyMatch(a -> a.getId().equals(azione.getId()));
	}

	public void actionToggled(CfgAzioniConsent action) {
		CfgStatiCanaleConsentitiService service = statiCanaleConsentitiServiceInstance.get();
		CfgStatiCanaleConsentEntity statoConsent = service.findByCanaleAndStato(groupChannelSelected.getChannel(),
				selectedStatus.getStato());
		if ( action.isAvailable()) {
			statoConsent.addAzione(action.getEntity());
		} else {
			statoConsent.removeAzione(action.getEntity());
		}
		service.persist(statoConsent);
		loadGroupsChannels();
		keepSelectedStatusFocus();
	}

	public void toggleStatoTransizioneFlag(CfgStatiTransizioniEntity transition) {
		try {
			cfgStatiTransizioniServiceInstance.get().persistWithAuditLog(transition, getCurrentUser().getName());
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
					"Stato Transizione aggiornato correttamente"));
		} catch (Exception ex) {
			log.error(String.format("Error updating 'Stato Transizione' with id: %d", transition.getId()), ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione",
					"Errore aggiornamento Stato Transizione"));
		}
	}

	private void keepSelectedStatusFocus() {
		// Ri-seleziona visivamente la riga sulla datatable degli stati (keep focus)
		final int index = getSelectedStatuses().indexOf(selectedStatus);
		if (index != -1) {
			executeScript("PF('statiSelezionatiTable').selectRow(" + index + ", true)");
		}
	}
}
