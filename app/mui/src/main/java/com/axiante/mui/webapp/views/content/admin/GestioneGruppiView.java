package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.GruppoPromozioniService;
import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.dto.GruppoUtenteDto;
import com.axiante.mui.persistence.entity.CanaleEntity;
import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.GrmEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoCanaleEntity;
import com.axiante.mui.persistence.entity.GruppoCompratoreEntity;
import com.axiante.mui.persistence.entity.GruppoGrmEntity;
import com.axiante.mui.persistence.entity.GruppoRepartoEntity;
import com.axiante.mui.persistence.entity.RepartoEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.exporters.GroupsExporter;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.admin.pojos.GruppoCanalePromoPojo;
import com.axiante.mui.webapp.views.content.admin.pojos.GruppoConAssociazioneExport;
import com.axiante.mui.webapp.views.content.admin.pojos.GruppoPromoCanalePojo;
import com.axiante.tm1.mdx.objects.Query;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@MuiViewModel("gestioneGruppi")
@Named("gestioneGruppi")
@SessionScoped
@Slf4j
public class GestioneGruppiView extends AbstractAdminView {

	private static final long serialVersionUID = -7845751138146058766L;

	private static final int TAB_GRUPPI = 0;
	private static final int TAB_UTENTI = 1;
	private static final int TAB_CANALI = 2;
	private static final int TAB_COMPRATORI = 3;
	private static final int TAB_REPARTI = 4;
	private static final int TAB_GRM = 5;

	@Inject
	@Getter
	transient private Instance<ApplicationFilterCatalogProducer> catalogProducerInstance;

	@Inject
	@Getter
	transient private Instance<CatalogReducer> catalogReducerInstance;

	@Inject
	transient private Instance<GruppoPromozioniService> gruppoPromoServiceInstance;

	@Getter
	@Setter
	private Integer activeTab = TAB_GRUPPI;

	@Getter
	@Setter
	private List<CanaleEntity> selectedChannels;

	@Getter
	@Setter
	private List<GruppoPromoCanalePojo> availableChannels;

	@Getter
	private final List<GruppoCanalePromoPojo> channels = new ArrayList<>();

	@Getter
	@Setter
	private CanaleEntity selectedChannel;

	@Inject
	transient private MuiService muiService;

	@Getter
	@Setter
	private GroupEntity selectedGroup;

	@Getter
	private GroupEntity selectedGroupCombo;

	@Getter
	@Setter
	private List<GroupEntity> groupList;

	@Getter
	@Setter
	private List<GroupEntity> filteredGroupList;

	@Getter
	@Setter
	private GroupEntity duplicateGroup;

	@Getter
	private GruppiRepartiBackingBean gruppiRepartiBean;

	@Getter
	private GruppiGrmBackingBean gruppiGrmBean;

	@Getter
	private GruppiCompratoriBackingBean gruppiCompratoriBean;

	@Getter
	private GruppiUtentiBackingBean gruppiUtentiBean;

	@Inject
	transient private Instance<GroupsExporter> groupsExportersInstance;

	String[] securityValues = {"NONE", "READ", "WRITE"}; 
	
	@Override
	public void updateView(final String grid) {
		updateView();
	}

	@Override
	public void updateView() {
        switchTab();
	}

	@PostConstruct
	public void init() {
		initSelectedGroup();
		readGroups();
		gruppiRepartiBean = new GruppiRepartiBackingBean();
		gruppiGrmBean = new GruppiGrmBackingBean();
		gruppiCompratoriBean = new GruppiCompratoriBackingBean();
		gruppiUtentiBean = new GruppiUtentiBackingBean();
	}

	public void tabChanged(TabChangeEvent event) {
		TabView tv = (TabView) event.getComponent();
		this.activeTab = tv.getChildren().indexOf(event.getTab());
		switchTab();
	}

	private void switchTab() {
		switch(activeTab) {
			case TAB_GRUPPI:
				readGroups();
				break;
			case TAB_UTENTI:
				readUsers();
				break;
			case TAB_CANALI:
				readChannels();
				break;
			case TAB_COMPRATORI:
				readCompratori();
				break;
			case TAB_REPARTI:
				readReparti();
				break;
			case TAB_GRM:
				readGrm();
				break;
			default:
				// noop
				break;
		}
	}

	private void readChannels() {
		try {
			channels.clear();
			if (selectedGroupCombo != null) {
				final List<GruppoCanaleEntity> groupChannels = muiService.findAllByGruppo(selectedGroupCombo).stream()
						.sorted(Comparator.comparing(gc -> gc.getCanale().getCodiceCanale()))
						.collect(Collectors.toList());
				if (!groupChannels.isEmpty()) {
					final List<Long> codiciCanale = groupChannels.stream().map(gc -> gc.getCanale().getCodiceCanale())
							.collect(Collectors.toList());
					final List<GruppoPromozioneEntity> groups = gruppoPromoServiceInstance.get()
							.findAllByCodiciCanale(codiciCanale);
					groupChannels.forEach(gc -> {
						final GruppoCanalePromoPojo pojo = new GruppoCanalePromoPojo();
						pojo.setGruppoCanale(gc);
						final GruppoPromozioneEntity group = groups.stream().filter(g -> g.getMuiCanalePromoziones().stream()
								.map(CanalePromozioneEntity::getCodiceCanale)
								.collect(Collectors.toList()).contains(gc.getCanale().getCodiceCanale()))
								.findFirst().orElse(null);
						if (group != null) {
							pojo.setCodiceGruppoPromo(group.getCodiceGruppo());
							pojo.setDescGruppoPromo(group.getDescrizione());
						}
						channels.add(pojo);
					});
				}
			}
		} catch (Exception e) {
			log.error("Error reading channels list", e);
		}
	}

	public void openAddGroupDialog() {
		initSelectedGroup();
		readGroups();
		PrimeFaces.current().executeScript("PF('dlgAddGroupWV').show();");
	}

	public void openDuplicaGroupDialog() {
		duplicateGroup = new GroupEntity();
		String desc = String.format("Copy of %s", selectedGroup.getDescrizione());
		if (desc.length() > GroupEntity.DESCRIZIONE_LENGTH) {
			desc = desc.substring(0, GroupEntity.DESCRIZIONE_LENGTH);
		}
		duplicateGroup.setDescrizione(desc);
		String codice = String.format("Cp %s", selectedGroup.getCodiceGruppo());
		if (codice.length() > GroupEntity.CODE_LENGTH) {
			codice = codice.substring(0, GroupEntity.CODE_LENGTH);
		}
		duplicateGroup.setCodiceGruppo(codice);
		// users non vengono duplicati da un gruppo all'altro, solo i canali
		duplicateGroup.setUsers(null);
		duplicateGroup.setCanali(selectedGroup.getCanali());

		PrimeFaces.current().executeScript("PF('dlgDuplicaGruppoWV').show();");
	}

	private void initSelectedGroup() {
		selectedGroup = new GroupEntity();
	}

	private void readGroups() {
		try {
			groupList = muiService.readGroups();
			groupList.sort(Comparator.comparing(GroupEntity::getDescrizione));
			if ((groupList != null) && !groupList.isEmpty()) {
				setSelectedGroupCombo(groupList.get(0));
			}
		} catch (final Exception ignored) {
		}
	}

	public void copyGroup() {
		try {
			if (validate()) {
				// in selected ho i valori nuovi di name e description ....
				final GroupEntity e = new GroupEntity();
				e.setDescrizione(duplicateGroup.getDescrizione());
				e.setCodiceGruppo(duplicateGroup.getCodiceGruppo());
				e.setUsers(duplicateGroup.getUsers());
				final GroupEntity savedGroup = muiService.persistGroup(e);
				duplicateGroup.getCanali().forEach(c -> c.addGroup(savedGroup));
				muiService.persistCanali(duplicateGroup.getCanali());
				PrimeFaces.current().executeScript("PF('dlgDuplicaGruppoWV').hide();");
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gruppo Aggiunto!", ""));
				cleanGroupDialog();
				readGroups();
			}
		} catch (final Exception e) {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Non e' stato possibile aggiungere il gruppo"));
			log.error("Error copying group", e);
		}
	}

	public void addGroup() {
		try {
			if (validate()) {
				muiService.persistGroup(selectedGroup);
				readGroups();
				PrimeFaces.current().executeScript("PF('dlgAddGroupWV').hide();");
				cleanGroupDialog();
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gruppo Aggiunto!", ""));
			}
		} catch (final Exception e) {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
			log.error("Error adding group", e);
		}
	}

	public void editGroup(final RowEditEvent event) {
		try {
			setSelectedGroup((GroupEntity) event.getObject());
			if (validate()) {
				final GroupEntity group = selectedGroup;
				muiService.persistGroup(group);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gruppo Modificato!", ""));
			}
		} catch (final Exception e) {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
			log.error("Error editing group", e);
		}
	}

	public void deleteGroup() {
		try {
			if (selectedGroup != null) {
				if ((selectedGroup.getUsers() != null) && (!selectedGroup.getUsers().isEmpty())) {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Impossibile rimuovere il gruppo",
							"Ci sono utenti ancora collegati"));
				} else {
					muiService.removeGroup(selectedGroup);
					readGroups();
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gruppo eliminato!", ""));
				}
			}
			selectedGroup = null;
		} catch (final Exception e) {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
			log.error("Error deleting group", e);
		}
	}

	private void cleanGroupDialog() {
		selectedGroup = new GroupEntity();
		duplicateGroup = new GroupEntity();
	}

	public boolean validate() throws Exception {
		return validate(getSelectedGroup());
	}

	private boolean validate(GroupEntity group) throws Exception {
		if (group == null) {
			throw new Exception("Errore di inizializzazione oggetto 'Gruppo'.");
		}
		if ((group.getCodiceGruppo() == null) || group.getCodiceGruppo().trim().isEmpty()) {
			throw new Exception("Campo 'Codice Gruppo' obbligatorio.");
		}
		if(group.getCodiceGruppo().length()> GroupEntity.CODE_LENGTH) {
			throw new Exception("'Codice Gruppo' non puo avere piu di " + GroupEntity.CODE_LENGTH + " caratteri.");
		}
		if ((group.getDescrizione() == null) || group.getDescrizione().trim().isEmpty()) {
			throw new Exception("Campo 'Descrizione' obbligatorio.");
		}
		if(group.getDescrizione().length()> GroupEntity.DESCRIZIONE_LENGTH) {
			throw new Exception("'Descrizione Gruppo' non puo avere piu di " + GroupEntity.DESCRIZIONE_LENGTH + " caratteri.");
		}
		return true;
	}

	@Override
	public Query prepareFilteredQuery(final String grid) {
		return null;
	}

	public void toggleFlagGruppoCanale(GruppoCanalePromoPojo pojo, String flag) {
		try {
			muiService.persistGruppoCanale(pojo.getGruppoCanale());
			boolean value = false;
			switch (flag){
				case "OWNER":
					value = pojo.getGruppoCanale().getOwner();
					break;
				case "PROPAGA OWNER":
					value = pojo.getGruppoCanale().getPropagaOwner();
					break;
				case "CREATE":
				default:
					value = pojo.getGruppoCanale().getCreate();
					break;
			}
			final String msg = String.format("Flag '%s' %s su canale '%s'",
					flag, value ? "abilitato" : "disabilitato",
					pojo.getGruppoCanale().getCanale().getDescrizione());
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Canale modificato", msg));
		} catch (Exception ex) {
			final String channelDesc = pojo.getGruppoCanale().getCanale().getDescrizione();
			log.error(String.format("Error saving canale '%s'", channelDesc), ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					String.format("Errore modifica flag '%s' su canale '%s'", flag, channelDesc)));
		}
	}

	public void removeChannelFromGroup() {
		if ((selectedChannel != null) && (selectedGroupCombo != null)) {
			final String channel = String.format("%s-%s", selectedChannel.getCodiceCanale(),
					selectedChannel.getDescrizione());
			final String group = selectedGroupCombo.getDescrizione();
			try {
				selectedChannel.removeGroup(selectedGroupCombo);
				muiService.persistCanale(selectedChannel);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Canale rimosso",
						String.format("Il canale '%s' è stato rimosso dal gruppo '%s'", channel, group)));
				selectedChannel = null;
				readChannels();
			} catch (Exception ex) {
				log.error(String.format("Errore removing channel '%s' from group '%s'", channel, group), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						String.format("Errore rimozione canale '%s' dal gruppo '%s; contattare l'assistenza tecnica.",
								channel, group)));
			}
		} else {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Impossibile eliminare canale dal gruppo; contattare l'assistenza tecnica."));
		}
	}

	public void openAddChannelsToGroupDialog() {
		if (availableChannels == null) {
			availableChannels = new ArrayList<>();
		}
		availableChannels.clear();
		try {
			// Load available channes, the ones that are not in selected group, with associated gruppo promo
			final List<CanaleEntity> canali = muiService.readCanali().stream()
					.filter(c -> !selectedGroupCombo.getCanali().contains(c))
					.collect(Collectors.toList());
			final List<GruppoPromozioneEntity> gruppiPromo = gruppoPromoServiceInstance.get()
					.findAllByCodiciCanale(canali.stream().map(CanaleEntity::getCodiceCanale).collect(Collectors.toList()));
			canali.forEach(c -> {
				final GruppoPromoCanalePojo pojo = new GruppoPromoCanalePojo();
				pojo.setCanale(c);
				final GruppoPromozioneEntity group = gruppiPromo.stream().filter(g -> g.getMuiCanalePromoziones().stream()
								.map(CanalePromozioneEntity::getCodiceCanale)
								.collect(Collectors.toList()).contains(c.getCodiceCanale()))
						.findFirst().orElse(null);
				pojo.setGruppoPromo(group != null ? group.getDescrizione() : "");
				availableChannels.add(pojo);
			});
			// Sort by gruppo promo then canale
			availableChannels.sort(Comparator.comparing(GruppoPromoCanalePojo::getGruppoPromo, Comparator.nullsLast(String::compareToIgnoreCase))
					.thenComparing(x -> x.getCanale().getDescrizione(), Comparator.nullsLast(String::compareToIgnoreCase)));
		} catch (Exception ex) {
			log.error("Error reading available channels", ex);
			availableChannels = new ArrayList<>();
		}
		selectedChannels = null;
		PrimeFaces.current().executeScript("PF('dlgAddChannelsToGroup').show();");
	}

	public void addChannelToGroup() {
		try {
			if ((selectedGroupCombo != null) && !selectedChannels.isEmpty()) {
				selectedChannels.forEach(c -> selectedGroupCombo.addCanale(c));
				muiService.persistCanali(selectedChannels);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						String.format("%s al gruppo", selectedChannels.size() == 1 ? "Canale aggiunto" : "Canali aggiunti"),
						""));
				readChannels();
				selectedChannels = null;
			} else {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nessun canale aggiunto al gruppo",
						""));
			}
		} catch (Exception ex) {
			log.error("Error adding channels to group", ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Impossibile aggiungere canali al gruppo; contattare l'assistenza tecnica."));
		}
	}

	public void selectAllChannels() {
		selectedChannels = availableChannels.stream().map(GruppoPromoCanalePojo::getCanale).collect(Collectors.toList());
	}

	public void deselectAllChannels() {
		selectedChannels = null;
	}

	public void setSelectedGroupCombo(GroupEntity selectedGroupCombo) {
		this.selectedGroupCombo = selectedGroupCombo;
		switch (activeTab) {
			case TAB_UTENTI:
				readUsers();
				break;
			case TAB_CANALI:
				readChannels();
				break;
			case TAB_COMPRATORI:
				readCompratori();
				break;
			case TAB_REPARTI:
				readReparti();
				break;
			case TAB_GRM:
				readGrm();
				break;
		}
	}

	public int getMaxCodeLength() {
		return GroupEntity.CODE_LENGTH;
	}

	public int getMaxDescrizioneLength() {
		return GroupEntity.DESCRIZIONE_LENGTH;
	}

	// Gestione Tab "Reparto"
	private void readReparti() {
		try {
			if (selectedGroupCombo != null) {
				gruppiRepartiBean.setReparti(muiService.findAllGruppoRepartoByGruppo(selectedGroupCombo)
						.stream()
						.sorted(Comparator.comparing(gr -> gr.getReparto().getCodiceReparto()))
						.collect(Collectors.toList()));
			}
		} catch (Exception ex) {
			log.error("Error reading reparti", ex);
		}
	}

	public void toggleSecurity(AjaxBehaviorEvent event) {
		try {
			final GruppoRepartoEntity entity = (GruppoRepartoEntity) event.getComponent().getAttributes().get("toggleEntity");
			if (entity != null) {
				muiService.saveGruppoReparto(entity);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modifica effettuata",
						String.format("Modificato tipo accesso in '%s' per il reparto '%s' sul gruppo '%s'",
								entity.getTipoAccesso().getSecurity(), entity.getReparto().getDescrizione(),
								entity.getGruppo().getDescrizione())));
			} else {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						"Errore modifica tipoAccesso; contattare l'assistenza tecnica."));
			}
		} catch (Exception ex) {
			log.error("Error toggle tipoAccesso", ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Errore modifica tipoAccesso; contattare l'assistenza tecnica."));
		}
	}

	public void removeRepartoFromGroup() {
		if (gruppiRepartiBean.getSelectedGruppoReparto() != null) {
			final String descReparto = gruppiRepartiBean.getSelectedGruppoReparto().getReparto().getDescrizione();
			final String descGruppo = gruppiRepartiBean.getSelectedGruppoReparto().getGruppo().getDescrizione();
			try {
				muiService.removeGruppoReparto(gruppiRepartiBean.getSelectedGruppoReparto());
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reparto rimosso",
						String.format("Il reparto '%s' è stato rimosso dal gruppo '%s'", descReparto, descGruppo)));
				gruppiRepartiBean.setSelectedGruppoReparto(null);
				readReparti();
			} catch (Exception ex) {
				log.error(String.format("Error removing Reparto %s from Gruppo %s", descReparto, descGruppo), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						String.format("Errore rimozione reparto '%s' dal gruppo '%s; contattare l'assistenza tecnica.",
								descReparto, descGruppo)));
			}
		}
	}

	public void openAddRepartiToGroupDialog() {
		try {
			final List<RepartoEntity> associatedReparti = gruppiRepartiBean.getReparti().stream()
					.map(GruppoRepartoEntity::getReparto).collect(Collectors.toList());
			gruppiRepartiBean.setAvailableReparti(muiService.findAllReparti().stream()
					.filter(r -> !associatedReparti.contains(r))
					.sorted(Comparator.comparing(RepartoEntity::getCodiceReparto))
					.collect(Collectors.toList()));
		} catch (Exception ex) {
			log.error("Error reading available reparti", ex);
			gruppiRepartiBean.setAvailableReparti(new ArrayList<>());
		}
		gruppiRepartiBean.setSelectedReparti(null);
		gruppiRepartiBean.setTipoAccesso(PianificazioneSecurityEnum.READ);
		executeScript("PF('dlgAddRepartiToGroup').show();");
	}

	public void selectAllReparti() {
		gruppiRepartiBean.setSelectedReparti(gruppiRepartiBean.getAvailableReparti());
	}

	public void deselectAllReparti() {
		gruppiRepartiBean.setSelectedReparti(null);
	}

	public void addRepartoToGroup() {
		try {
			if (selectedGroupCombo != null && gruppiRepartiBean.getSelectedReparti() != null
					&& !gruppiRepartiBean.getSelectedReparti().isEmpty()) {
				final List<GruppoRepartoEntity> list = new ArrayList<>();
				gruppiRepartiBean.getSelectedReparti().forEach(reparto -> {
					final GruppoRepartoEntity entity = new GruppoRepartoEntity();
					entity.setGruppo(selectedGroupCombo);
					entity.setReparto(reparto);
					entity.setTipoAccesso(gruppiRepartiBean.getTipoAccesso());
					list.add(entity);
				});
				muiService.saveGruppoReparti(list);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						String.format("%d %s al gruppo", gruppiRepartiBean.getSelectedReparti().size(),
								gruppiRepartiBean.getSelectedReparti().size() == 1 ? "reparto aggiunto" : "reparti aggiunti"),
						""));
				readReparti();
				gruppiRepartiBean.setSelectedReparti(null);
			} else {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Nessun reparto aggiunto al gruppo", ""));
			}
		} catch (Exception ex) {
			log.error("Error adding reparti to group", ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Impossibile aggiungere reparti al gruppo; contattare l'assistenza tecnica."));
		}
	}

	public void changeRepartiAllRead() {
		updateTipoAccessoReparti(PianificazioneSecurityEnum.READ);
	}

	public void changeRepartiAllWrite() {
		updateTipoAccessoReparti(PianificazioneSecurityEnum.WRITE);
	}

	private void updateTipoAccessoReparti(PianificazioneSecurityEnum tipoAccesso) {
		try {
			gruppiRepartiBean.getReparti().forEach(gr -> gr.setTipoAccesso(tipoAccesso));
			muiService.saveGruppoReparti(gruppiRepartiBean.getReparti());
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo accesso modificato",
					String.format("Tipo accesso modificato in %s per tutti i reparti", tipoAccesso.toString())));
		} catch (Exception ex) {
			log.error(String.format("Error setting tipo accesso %s for all departments", tipoAccesso), ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Impossibile modificare massivamente tipo accesso; contattare l'assistenza tecnica."));
		}
	}

	public void removeAllReparti() {
		if (selectedGroupCombo != null) {
			try {
				muiService.removeAllGruppoRepartoByGruppo(selectedGroupCombo);
				gruppiRepartiBean.getReparti().clear();
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reparti eliminati",
						String.format("Rimossi tutti i reparti dal gruppo %s", selectedGroupCombo.getDescrizione())));
			} catch (Exception ex) {
				log.error(String.format("Error removing all departments from group %s - %s",
						selectedGroupCombo.getCodiceGruppo(), selectedGroupCombo.getDescrizione()), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						String.format("Impossibile rimuovere reparti dal gruppo %s; contattare l'assistenza tecnica.",
								selectedGroupCombo.getDescrizione())));
			}
		} else {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Nessun gruppo selezionato; impossibile rimuovere reparti."));
		}
	}

	public void openCopyFromGroupDialog() {
		resetCopyFromGroupDialog();
		if (selectedGroupCombo != null) {
			gruppiRepartiBean.setGroupsForCopy(groupList.stream()
					.filter(g -> !selectedGroupCombo.getId().equals(g.getId()))
					.collect(Collectors.toList()));
		} else {
			gruppiRepartiBean.setGroupsForCopy(new ArrayList<>());
		}
	}

	public void preConfirmCopyFromGroup() {
		if (gruppiRepartiBean.getIdCopyGroup() != null) {
			try {
				final GroupEntity srcGroup = getSrcGroup(gruppiRepartiBean.getIdCopyGroup());
				if (srcGroup != null && selectedGroupCombo != null) {
					if (muiService.findAllGruppoRepartoByGruppo(srcGroup).isEmpty()) {
						executeScript("PF('dlgGroupNoReparti').show()");
					} else {
						copyRepartiFromGroup(srcGroup);
						executeScript("PF('dlgCopyFromGroup').hide()");
					}
				} else {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
							"Errore copia reparti"));
				}
			} catch (Exception ex) {
				log.error(String.format("Error copying reparti from group id %d to group  id %d",
						gruppiRepartiBean.getIdCopyGroup(), selectedGroupCombo.getId()), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						"Errore copia reparti"));
			}
		}
	}

	public void confirmCopyFromGroup() {
		if (gruppiRepartiBean.getIdCopyGroup() != null) {
			try {
				final GroupEntity srcGroup = getSrcGroup(gruppiRepartiBean.getIdCopyGroup());
				if (srcGroup != null && selectedGroupCombo != null) {
					copyRepartiFromGroup(srcGroup);
				} else {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
							"Errore copia reparti"));
				}
			} catch (Exception ex) {
				log.error(String.format("Error copying reparti from group id %d to group  id %d",
						gruppiRepartiBean.getIdCopyGroup(), selectedGroupCombo.getId()), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						"Errore copia reparti"));
			}
		}
	}

	private GroupEntity getSrcGroup(Integer id) {
		return groupList.stream()
				.filter(g -> g.getId().equals(id))
				.findFirst().orElse(null);
	}

	private void copyRepartiFromGroup(GroupEntity srcGroup) {
		final List<GruppoRepartoEntity> list = new ArrayList<>();
		// Elimino associazioni esistenti
		muiService.removeAllGruppoRepartoByGruppo(selectedGroupCombo);
		// Creo le nuove associazioni
		muiService.findAllGruppoRepartoByGruppo(srcGroup).forEach(gr -> {
			final GruppoRepartoEntity entity = new GruppoRepartoEntity();
			entity.setGruppo(selectedGroupCombo);
			entity.setReparto(gr.getReparto());
			entity.setTipoAccesso(gr.getTipoAccesso());
			list.add(entity);
		});
		muiService.saveGruppoReparti(list);
		readReparti();
		addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reparti copiati",
				String.format("%d %s dal gruppo '%s' al gruppo '%s'", list.size(),
						list.size() == 1 ? "reparto copiato" : "reparti copiati",
						srcGroup.getCodiceGruppo(), selectedGroupCombo.getCodiceGruppo())));
	}

	public void resetCopyFromGroupDialog() {
		gruppiRepartiBean.setIdCopyGroup(null);
	}

	// Gestione Tab "GRM"
	private void readGrm() {
		try {
			if (selectedGroupCombo != null) {
				gruppiGrmBean.setGrm(muiService.findGruppoGrmByGroup(selectedGroupCombo).stream()
						.sorted(Comparator.comparing(gg -> gg.getGrm().getCodice()))
						.collect(Collectors.toList()));
			}
		} catch (Exception ex) {
			log.error("Error reading grm", ex);
		}
	}

	public void toggleGrmSecurity(AjaxBehaviorEvent event) {
		try {
			final GruppoGrmEntity entity = (GruppoGrmEntity) event.getComponent().getAttributes().get("toggleEntity");
			if (entity != null) {
				muiService.saveGruppoGrm(entity);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modifica effettuata",
						String.format("Modificato tipo accesso in '%s' per il grm '%s' sul gruppo '%s'",
								entity.getTipoAccesso().getSecurity(), entity.getGrm().getDescrizione(),
								entity.getGruppo().getDescrizione())));
			} else {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						"Errore modifica tipoAccesso; contattare l'assistenza tecnica."));
			}
		} catch (Exception ex) {
			log.error("Error toggle tipoAccesso for GruppoGrm", ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Errore modifica tipoAccesso; contattare l'assistenza tecnica."));
		}
	}

	public void removeGrmFromGroup() {
		if (gruppiGrmBean.getSelectedGruppoGrm() != null) {
			final String descGrm = gruppiGrmBean.getSelectedGruppoGrm().getGrm().getDescrizione();
			final String descGruppo = gruppiGrmBean.getSelectedGruppoGrm().getGrm().getDescrizione();
			try {
				muiService.removeGruppoGrm(gruppiGrmBean.getSelectedGruppoGrm());
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Grm rimosso",
						String.format("Il grm '%s' è stato rimosso dal gruppo '%s'", descGrm, descGruppo)));
				gruppiGrmBean.setSelectedGruppoGrm(null);
				readGrm();
			} catch (Exception ex) {
				log.error(String.format("Error removing Grm %s from Gruppo %s", descGrm, descGruppo), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						String.format("Errore rimozione grm '%s' dal gruppo '%s; contattare l'assistenza tecnica.",
								descGrm, descGruppo)));
			}
		}
	}

	public void openAddGrmToGroupDialog() {
		try {
			final List<GrmEntity> associatedGrm = gruppiGrmBean.getGrm().stream().map(GruppoGrmEntity::getGrm)
					.collect(Collectors.toList());
			gruppiGrmBean.setAvailableGrm(muiService.findAllGrm().stream()
					.filter(g -> !associatedGrm.contains(g))
					.sorted(Comparator.comparing(GrmEntity::getCodice))
					.collect(Collectors.toList()));
		} catch (Exception ex) {
			log.error("Error reading available grm", ex);
			gruppiGrmBean.setAvailableGrm(new ArrayList<>());
		}
		gruppiGrmBean.setSelectedGrm(null);
		gruppiGrmBean.setTipoAccesso(PianificazioneSecurityEnum.READ);
		executeScript("PF('dlgAddGrmToGroup').show();");
	}

	public void selectAllGrm() {
		gruppiGrmBean.setSelectedGrm(gruppiGrmBean.getAvailableGrm());
	}

	public void deselectAllGrm() {
		gruppiGrmBean.setSelectedGrm(null);
	}

	public void addGrmToGroup() {
		try {
			if (selectedGroupCombo != null && gruppiGrmBean.getSelectedGrm() != null
					&& !gruppiGrmBean.getSelectedGrm().isEmpty()) {
				final List<GruppoGrmEntity> list = new ArrayList<>();
				gruppiGrmBean.getSelectedGrm().forEach(grm -> {
					final GruppoGrmEntity entity = new GruppoGrmEntity();
					entity.setGruppo(selectedGroupCombo);
					entity.setGrm(grm);
					entity.setTipoAccesso(gruppiGrmBean.getTipoAccesso());
					list.add(entity);
				});
				muiService.saveGruppoGrm(list);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						String.format("%d grm %s al gruppo", list.size(), list.size() == 1 ? "aggiunto" : "aggiunti"),
						""));
				readGrm();
				gruppiGrmBean.setSelectedGrm(null);
			} else {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Nessun grm aggiunto al gruppo", ""));
			}
		} catch (Exception ex) {
			log.error("Error adding grm to group", ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Impossibile aggiungere grm al gruppo; contattare l'assistenza tecnica."));
		}
	}

	public void changeGrmAllRead() {
		updateTipoAccessoGrm(PianificazioneSecurityEnum.READ);
	}

	public void changeGrmAllWrite() {
		updateTipoAccessoGrm(PianificazioneSecurityEnum.WRITE);
	}

	private void updateTipoAccessoGrm(PianificazioneSecurityEnum tipoAccesso) {
		try {
			gruppiGrmBean.getGrm().forEach(gg -> gg.setTipoAccesso(tipoAccesso));
			muiService.saveGruppoGrm(gruppiGrmBean.getGrm());
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo accesso modificato",
					String.format("Tipo accesso modificato in %s per tutti i grm", tipoAccesso.toString())));
		} catch (Exception ex) {
			log.error(String.format("Error setting tipo accesso %s for all grm", tipoAccesso), ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Impossibile modificare massivamente tipo accesso; contattare l'assistenza tecnica."));
		}
	}

	public void removeAllGrm() {
		if (selectedGroupCombo != null) {
			try {
				muiService.removeAllGruppoGrmByGruppo(selectedGroupCombo);
				gruppiGrmBean.getGrm().clear();
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Grm eliminati",
						String.format("Rimossi tutti i grm dal gruppo %s", selectedGroupCombo.getDescrizione())));
			} catch (Exception ex) {
				log.error(String.format("Error removing all grm from group %s - %s",
						selectedGroupCombo.getCodiceGruppo(), selectedGroupCombo.getDescrizione()), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						String.format("Impossibile rimuovere grm dal gruppo %s; contattare l'assistenza tecnica.",
								selectedGroupCombo.getDescrizione())));
			}
		} else {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Nessun gruppo selezionato; impossibile rimuovere grm."));
		}
	}

	public void openCopyGrmFromGroupDialog() {
		resetCopyGrmFromGroupDialog();
		if (selectedGroupCombo != null) {
			gruppiGrmBean.setGroupsForCopy(groupList.stream()
					.filter(g -> !selectedGroupCombo.getId().equals(g.getId()))
					.collect(Collectors.toList()));
		} else {
			gruppiGrmBean.setGroupsForCopy(new ArrayList<>());
		}
	}

	public void preConfirmCopyGrmFromGroup() {
		if (gruppiGrmBean.getIdCopyGroup() != null) {
			try {
				final GroupEntity srcGroup = getSrcGroup(gruppiGrmBean.getIdCopyGroup());
				if (srcGroup != null && selectedGroupCombo != null) {
					if (muiService.findGruppoGrmByGroup(srcGroup).isEmpty()) {
						executeScript("PF('dlgGroupNoGrm').show()");
					} else {
						copyGrmFromGroup(srcGroup);
						executeScript("PF('dlgCopyGrmFromGroup').hide()");
					}
				} else {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
							"Errore copia grm"));
				}
			} catch (Exception ex) {
				log.error(String.format("Error copying grm from group id %d to group  id %d",
						gruppiGrmBean.getIdCopyGroup(), selectedGroupCombo.getId()), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						"Errore copia grm"));
			}
		}
	}

	public void confirmCopyGrmFromGroup() {
		if (gruppiGrmBean.getIdCopyGroup() != null) {
			try {
				final GroupEntity srcGroup = getSrcGroup(gruppiGrmBean.getIdCopyGroup());
				if (srcGroup != null && selectedGroupCombo != null) {
					copyGrmFromGroup(srcGroup);
				} else {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
							"Errore copia grm"));
				}
			} catch (Exception ex) {
				log.error(String.format("Error copying grm from group id %d to group  id %d",
						gruppiGrmBean.getIdCopyGroup(), selectedGroupCombo.getId()), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						"Errore copia grm"));
			}
		}
	}

	public void resetCopyGrmFromGroupDialog() {
		gruppiGrmBean.setIdCopyGroup(null);
	}

	private void copyGrmFromGroup(GroupEntity group) {
		final List<GruppoGrmEntity> list = new ArrayList<>();
		// Elimino associazioni esistenti
		muiService.removeAllGruppoGrmByGruppo(selectedGroupCombo);
		// Creo le nuove associazioni
		muiService.findGruppoGrmByGroup(group).forEach(gg -> {
			final GruppoGrmEntity entity = new GruppoGrmEntity();
			entity.setGruppo(selectedGroupCombo);
			entity.setGrm(gg.getGrm());
			entity.setTipoAccesso(gg.getTipoAccesso());
			list.add(entity);
		});
		muiService.saveGruppoGrm(list);
		readGrm();
		addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Grm copiati",
				String.format("%d grm %s dal gruppo '%s' al gruppo '%s'", list.size(),
						list.size() == 1 ? "copiato" : "copiati",
						group.getCodiceGruppo(), selectedGroupCombo.getCodiceGruppo())));
	}

	// Gestione Tab "Compratori"
	private void readCompratori() {
		try {
			if (selectedGroupCombo != null) {
				gruppiCompratoriBean.setCompratori(muiService.findAllGruppoCompratoreByGroup(selectedGroupCombo).stream()
						.filter(gc -> gc.getCompratore() != null)
						.sorted(Comparator.comparing(gc -> gc.getCompratore().getCodiceCompratore()))
						.collect(Collectors.toList()));
			}
		} catch (Exception ex) {
			log.error("Error reading compratori", ex);
		}
	}

	public void toggleCompratoreSecurity(AjaxBehaviorEvent event) {
		try {
			final GruppoCompratoreEntity entity = (GruppoCompratoreEntity) event.getComponent().getAttributes()
					.get("toggleEntity");
			if (entity != null) {
				muiService.saveGruppoCompratore(entity);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modifica effettuata",
						String.format("Modificato tipo accesso in '%s' per il compratore '%s' sul gruppo '%s'",
								entity.getTipoAccesso().getSecurity(), entity.getCompratore().getCodiceCompratore(),
								entity.getGruppo().getDescrizione())));
			} else {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						"Errore modifica tipoAccesso; contattare l'assistenza tecnica."));
			}
		} catch (Exception ex) {
			log.error("Error toggle tipoAccesso for GruppoCompratore", ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Errore modifica tipoAccesso; contattare l'assistenza tecnica."));
		}
	}

	public void removeCompratoreFromGroup() {
		if (gruppiCompratoriBean.getSelectedGruppoCompratore() != null) {
			final String descCompratore = gruppiCompratoriBean.getSelectedGruppoCompratore().getCompratore().getDescrizione();
			final String descGruppo = gruppiCompratoriBean.getSelectedGruppoCompratore().getGruppo().getDescrizione();
			try {
				muiService.removeGruppoCompratore(gruppiCompratoriBean.getSelectedGruppoCompratore());
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compratore rimosso",
						String.format("Il compratore '%s' è stato rimosso dal gruppo '%s'", descCompratore, descGruppo)));
				gruppiCompratoriBean.setSelectedGruppoCompratore(null);
				readCompratori();
			} catch (Exception ex) {
				log.error(String.format("Error removing Compratore %s from Gruppo %s", descCompratore, descGruppo), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						String.format("Errore rimozione compratore '%s' dal gruppo '%s; contattare l'assistenza tecnica.",
								descCompratore, descGruppo)));
			}
		}
	}

	public void openAddCompratoriToGroupDialog() {
		try {
			final List<CompratoreEntity> associatedCompratori = gruppiCompratoriBean.getCompratori().stream()
					.map(GruppoCompratoreEntity::getCompratore).collect(Collectors.toList());
			gruppiCompratoriBean.setAvailableCompratori(muiService.findAllCompratori().stream()
					.filter(c -> !associatedCompratori.contains(c))
					.sorted(Comparator.comparing(CompratoreEntity::getCodiceCompratore))
					.collect(Collectors.toList()));
		} catch (Exception ex) {
			log.error("Error reading available compratori", ex);
			gruppiCompratoriBean.setAvailableCompratori(new ArrayList<>());
		}
		gruppiCompratoriBean.setSelectedCompratori(null);
		gruppiCompratoriBean.setTipoAccesso(PianificazioneSecurityEnum.READ);
		executeScript("PF('dlgAddCompratoriToGroup').show();");
	}

	public void selectAllCompratori() {
		gruppiCompratoriBean.setSelectedCompratori(gruppiCompratoriBean.getAvailableCompratori());
	}

	public void deselectAllCompratori() {
		gruppiCompratoriBean.setSelectedCompratori(null);
	}

	public void addCompratoriToGroup() {
		try {
			if (selectedGroupCombo != null && gruppiCompratoriBean.getSelectedCompratori() != null
					&& !gruppiCompratoriBean.getSelectedCompratori().isEmpty()) {
				final List<GruppoCompratoreEntity> list = new ArrayList<>();
				gruppiCompratoriBean.getSelectedCompratori().forEach(c -> {
					final GruppoCompratoreEntity entity = new GruppoCompratoreEntity();
					entity.setGruppo(selectedGroupCombo);
					entity.setCompratore(c);
					entity.setTipoAccesso(gruppiCompratoriBean.getTipoAccesso());
					list.add(entity);
				});
				muiService.saveGruppoCompratore(list);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						String.format("%d %s al gruppo", list.size(),
								list.size() == 1 ? "compratore aggiunto" : "compratori aggiunti"),
						""));
				readCompratori();
				gruppiCompratoriBean.setSelectedCompratori(null);
			} else {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Nessun compratore aggiunto al gruppo", ""));
			}
		} catch (Exception ex) {
			log.error("Error adding compratore to group", ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Impossibile aggiungere grm al gruppo; contattare l'assistenza tecnica."));
		}
	}

	public void changeCompratoriAllRead() {
		updateTipoAccessoCompratori(PianificazioneSecurityEnum.READ);
	}

	public void changeCompratoriAllWrite() {
		updateTipoAccessoCompratori(PianificazioneSecurityEnum.WRITE);
	}

	private void updateTipoAccessoCompratori(PianificazioneSecurityEnum tipoAccesso) {
		try {
			gruppiCompratoriBean.getCompratori().forEach(gc -> gc.setTipoAccesso(tipoAccesso));
			muiService.saveGruppoCompratore(gruppiCompratoriBean.getCompratori());
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo accesso modificato",
					String.format("Tipo accesso modificato in %s per tutti i compratori", tipoAccesso.toString())));
		} catch (Exception ex) {
			log.error(String.format("Error setting tipo accesso %s for all buyers", tipoAccesso), ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Impossibile modificare massivamente tipo accesso; contattare l'assistenza tecnica."));
		}
	}

	public void removeAllCompratori() {
		if (selectedGroupCombo != null) {
			try {
				muiService.removeAllGruppoCompratoreByGruppo(selectedGroupCombo);
				gruppiCompratoriBean.getCompratori().clear();
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compratori eliminati",
						String.format("Rimossi tutti i compratori dal gruppo %s", selectedGroupCombo.getDescrizione())));
			} catch (Exception ex) {
				log.error(String.format("Error removing all buyer from group %s - %s",
						selectedGroupCombo.getCodiceGruppo(), selectedGroupCombo.getDescrizione()), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						String.format("Impossibile rimuovere compratori dal gruppo %s; contattare l'assistenza tecnica.",
								selectedGroupCombo.getDescrizione())));
			}
		} else {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Nessun gruppo selezionato; impossibile rimuovere compratori."));
		}
	}

	public void openCopyCompratoriFromGroupDialog() {
		resetCopyCompratoriFromGroupDialog();
		if (selectedGroupCombo != null) {
			gruppiCompratoriBean.setGroupsForCopy(groupList.stream()
					.filter(g -> !selectedGroupCombo.getId().equals(g.getId()))
					.collect(Collectors.toList()));
		} else {
			gruppiCompratoriBean.setGroupsForCopy(new ArrayList<>());
		}
	}

	public void resetCopyCompratoriFromGroupDialog() {
		gruppiCompratoriBean.setIdCopyGroup(null);
	}

	public void preConfirmCopyCompratoriFromGroup() {
		if (gruppiCompratoriBean.getIdCopyGroup() != null) {
			try {
				final GroupEntity srcGroup = getSrcGroup(gruppiCompratoriBean.getIdCopyGroup());
				if (srcGroup != null && selectedGroupCombo != null) {
					if (muiService.findAllGruppoCompratoreByGroup(srcGroup).isEmpty()) {
						executeScript("PF('dlgGroupNoCompratori').show()");
					} else {
						copyCompratoriFromGroup(srcGroup);
						executeScript("PF('dlgCopyCompratoriFromGroup').hide()");
					}
				} else {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
							"Errore copia compratori"));
				}
			} catch (Exception ex) {
				log.error(String.format("Error copying compratori from group id %d to group  id %d",
						gruppiCompratoriBean.getIdCopyGroup(), selectedGroupCombo.getId()), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						"Errore copia compratori"));
			}
		}
	}

	public void confirmCopyCompratoriFromGroup() {
		if (gruppiCompratoriBean.getIdCopyGroup() != null) {
			try {
				final GroupEntity srcGroup = getSrcGroup(gruppiCompratoriBean.getIdCopyGroup());
				if (srcGroup != null && selectedGroupCombo != null) {
					copyCompratoriFromGroup(srcGroup);
				} else {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
							"Errore copia compratori"));
				}
			} catch (Exception ex) {
				log.error(String.format("Error copying compratori from group id %d to group  id %d",
						gruppiCompratoriBean.getIdCopyGroup(), selectedGroupCombo.getId()), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						"Errore copia compratori"));
			}
		}
	}

	private void copyCompratoriFromGroup(GroupEntity group) {
		final List<GruppoCompratoreEntity> list = new ArrayList<>();
		// Elimino associazioni esistenti
		muiService.removeAllGruppoCompratoreByGruppo(selectedGroupCombo);
		// Creo le nuove associazioni
		muiService.findAllGruppoCompratoreByGroup(group).forEach(gc -> {
			final GruppoCompratoreEntity entity = new GruppoCompratoreEntity();
			entity.setGruppo(selectedGroupCombo);
			entity.setCompratore(gc.getCompratore());
			entity.setTipoAccesso(gc.getTipoAccesso());
			list.add(entity);
		});
		muiService.saveGruppoCompratore(list);
		readCompratori();
		addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compratori copiati",
				String.format("%d %s dal gruppo '%s' al gruppo '%s'", list.size(),
						list.size() == 1 ? "compratore copiato" : "compratori copiati",
						group.getCodiceGruppo(), selectedGroupCombo.getCodiceGruppo())));
	}

	// Gestione Tab "Utenti"
	public void readUsers() {
		if (selectedGroupCombo != null) {
			try {
				// reload group by id to get fresh users
				selectedGroupCombo = muiService.findGroupById(selectedGroupCombo.getId());
				gruppiUtentiBean.setUtenti(selectedGroupCombo.getUsers().stream()
						.sorted(Comparator.comparing(UsersEntity::getName)).collect(Collectors.toList()));
			} catch (Exception ex) {
				log.error(String.format("Error reading users from group %s", selectedGroupCombo.getCodiceGruppo()), ex);
			}
		}
	}

	public void removeUserFromGroup() {
		if (selectedGroupCombo != null && gruppiUtentiBean.getSelectedUser() != null) {
			try {
				selectedGroupCombo.removeUser(gruppiUtentiBean.getSelectedUser());
				gruppiUtentiBean.getSelectedUser().removeGruppo(selectedGroupCombo);
				muiService.persistUser(gruppiUtentiBean.getSelectedUser());
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						String.format("Utente %s rimosso dal gruppo '%s'", gruppiUtentiBean.getSelectedUser().getName(),
								selectedGroupCombo.getCodiceGruppo()),
						""));
				readUsers();
				gruppiUtentiBean.setSelectedUser(null);
			} catch (Exception ex) {
				log.error("Error removing user from group", ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						String.format("Impossibile rimuovere utente dal gruppo '%s'; contattare l'assistenza tecnica.",
								selectedGroupCombo.getCodiceGruppo())));
			}
		} else {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Nessun utente rimosso dal gruppo", ""));		}
	}

	public void openAddUtentiToGroupDialog() {
		try {
			final List<UsersEntity> users = muiService.readUsers().stream()
					.filter(u -> !gruppiUtentiBean.getUtenti().contains(u))
					.sorted(Comparator.comparing(UsersEntity::getName))
					.collect(Collectors.toList());
			gruppiUtentiBean.setAvailableUsers(users);
		} catch (Exception ex) {
			log.error("Error reading available users", ex);
			gruppiUtentiBean.setAvailableUsers(new ArrayList<>());
		}
		gruppiUtentiBean.setSelectedUsers(null);
		executeScript("PF('dlgAddUtentiToGroup').show()");
	}

	public void addUsersToGroup() {
		if (selectedGroupCombo != null && gruppiUtentiBean.getSelectedUsers().length > 0) {
			try {
				Arrays.stream(gruppiUtentiBean.getSelectedUsers()).forEach(u -> selectedGroupCombo.addUser(u));
				muiService.persistGroup(selectedGroupCombo);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						String.format("%d %s al gruppo '%s'", gruppiUtentiBean.getSelectedUsers().length,
								gruppiUtentiBean.getSelectedUsers().length == 1 ? "utente aggiunto" : "utenti aggiunti",
								selectedGroupCombo.getCodiceGruppo()),
						""));
				readUsers();
				gruppiUtentiBean.setSelectedUsers(null);
			} catch (Exception ex) {
				log.error("Error adding users to group", ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						String.format("Impossibile aggiungere utenti al gruppo '%s'; contattare l'assistenza tecnica.",
								selectedGroupCombo.getCodiceGruppo())));
			}
		} else {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Nessun utente aggiunto al gruppo", ""));
		}
	}

	// Export groups
	public StreamedContent getExportGroups() {
		try {
			final List<GroupEntity> groups = groupList.stream()
					.sorted(Comparator.comparing(GroupEntity::getCodiceGruppo)).collect(Collectors.toList());
			final List<GruppoUtenteDto> users = muiService.findAllGroupsWithUsers();
			final InputStream stream = groupsExportersInstance.get().produce(groups, getGruppoCanale(), users,
					getCompratori(groups), getReparti(groups), getGrm(groups));
			return new DefaultStreamedContent(stream,
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "gruppi.xlsx");
		} catch (Exception ex) {
			log.warn("Error exporting data", ex);
		}
		return null;
	}

	private List<GruppoConAssociazioneExport> getCompratori(List<GroupEntity> groups) {
		List<GruppoConAssociazioneExport> list = new ArrayList<>();
		groups.forEach(g -> {
			final List<GruppoCompratoreEntity> compratori = muiService.findAllGruppoCompratoreByGroup(g);
			if (compratori.isEmpty()) {
				list.add(new GruppoConAssociazioneExport(g.getCodiceGruppo(), g.getDescrizione()));
			} else {
				compratori.stream().sorted(byCodiceCompratore).forEach(c -> list.add(
						new GruppoConAssociazioneExport(g.getCodiceGruppo(), g.getDescrizione(),
								c.getCompratore().getCodiceCompratore(), c.getCompratore().getDescrizione(),
								c.getTipoAccesso().getSecurity())));
			}
		});
		return list;
	}

	private List<GruppoConAssociazioneExport> getReparti(List<GroupEntity> groups) {
		List<GruppoConAssociazioneExport> list = new ArrayList<>();
		groups.forEach(g -> {
			final List<GruppoRepartoEntity> reparti = muiService.findAllGruppoRepartoByGruppo(g);
			if (reparti.isEmpty()) {
				list.add(new GruppoConAssociazioneExport(g.getCodiceGruppo(), g.getDescrizione()));
			} else {
				reparti.stream().sorted(byCodiceReparto).forEach(r -> list.add(
						new GruppoConAssociazioneExport(g.getCodiceGruppo(), g.getDescrizione(),
								r.getReparto().getCodiceReparto(), r.getReparto().getDescrizione(),
								r.getTipoAccesso().getSecurity())));
			}
		});
		return list;
	}

	private List<GruppoConAssociazioneExport> getGrm(List<GroupEntity> groups) {
		List<GruppoConAssociazioneExport> list = new ArrayList<>();
		groups.forEach(g -> {
			final List<GruppoGrmEntity> grm = muiService.findGruppoGrmByGroup(g);
			if (grm.isEmpty()) {
				list.add(new GruppoConAssociazioneExport(g.getCodiceGruppo(), g.getDescrizione()));
			} else {
				grm.stream().sorted(byCodiceGrm).forEach(e -> list.add(
						new GruppoConAssociazioneExport(g.getCodiceGruppo(), g.getDescrizione(),
								e.getGrm().getCodice(), e.getGrm().getDescrizione(),
								e.getTipoAccesso().getSecurity())));
			}
		});
		return list;
	}

	private List<GruppoCanalePromoPojo> getGruppoCanale() {
		final List<GruppoCanalePromoPojo> list = new ArrayList<>();
		final List<GruppoCanaleEntity> groupChannels = muiService.findAllGruppoCanale();
		if (!groupChannels.isEmpty()) {
			final List<Long> codiciCanale = groupChannels.stream().map(gc -> gc.getCanale().getCodiceCanale())
					.collect(Collectors.toList());
			final List<GruppoPromozioneEntity> groups = gruppoPromoServiceInstance.get()
					.findAllByCodiciCanale(codiciCanale);
			groupChannels.forEach(gc -> {
				final GruppoCanalePromoPojo pojo = new GruppoCanalePromoPojo();
				pojo.setGruppoCanale(gc);
				final GruppoPromozioneEntity group = groups.stream().filter(g -> g.getMuiCanalePromoziones().stream()
							.map(CanalePromozioneEntity::getCodiceCanale)
							.collect(Collectors.toList()).contains(gc.getCanale().getCodiceCanale()))
						.findFirst().orElse(null);
				if (group != null) {
					pojo.setCodiceGruppoPromo(group.getCodiceGruppo());
					pojo.setDescGruppoPromo(group.getDescrizione());
				}
				list.add(pojo);
			});
		}
		list.sort(byCodiceGruppo.thenComparing(byCodiceGruppoPromo).thenComparing(byCodiceCanale));
		return list;
	}

	Comparator<GruppoCanalePromoPojo> byCodiceGruppo = (o1, o2) -> {
		try {
			return o1.getGruppoCanale().getGruppo().getCodiceGruppo()
					.compareTo(o2.getGruppoCanale().getGruppo().getCodiceGruppo());
		} catch (Exception ex) {
			log.error("Error comparing", ex);
		}
		return 0;
	};

	Comparator<GruppoCanalePromoPojo> byCodiceGruppoPromo = (o1, o2) -> {
		try {
			return o1.getCodiceGruppoPromo().compareTo(o2.getCodiceGruppoPromo());
		} catch (Exception ex) {
			log.error("Error comparing", ex);
		}
		return 0;
	};

	Comparator<GruppoCanalePromoPojo> byCodiceCanale = (o1, o2) -> {
		try {
			return o1.getGruppoCanale().getCanale().getCodiceCanale()
					.compareTo(o2.getGruppoCanale().getCanale().getCodiceCanale());
		} catch (Exception ex) {
			log.error("Error comparing", ex);
		}
		return 0;
	};

	Comparator<GruppoCompratoreEntity> byCodiceCompratore = (o1, o2) -> {
		try {
			return o1.getCompratore().getCodiceCompratore().compareTo(o2.getCompratore().getCodiceCompratore());
		} catch (Exception ex) {
			log.error("Error comparing", ex);
		}
		return 0;
	};

	Comparator<GruppoRepartoEntity> byCodiceReparto = (o1, o2) -> {
		try {
			return o1.getReparto().getCodiceReparto().compareTo(o2.getReparto().getCodiceReparto());
		} catch (Exception ex) {
			log.error("Error comparing", ex);
		}
		return 0;
	};

	Comparator<GruppoGrmEntity> byCodiceGrm = (o1, o2) -> {
		try {
			return o1.getGrm().getCodice().compareTo(o2.getGrm().getCodice());
		} catch (Exception ex) {
			log.error("Error comparing", ex);
		}

		return 0;
	};
	
	public void cambioAccesso(GroupEntity gruppo) {
		try {
			muiService.persistGroup(gruppo);
		} catch (Exception e) {
			log.error(String.format("Error persisting group %s", gruppo.getCodiceGruppo()), e);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",
					String.format("Errore durante il cambio di accesso per il gruppo %s:\n%s",
							gruppo.getCodiceGruppo(), e.getMessage())));
		}
	}

	public CatalogReducer getCatalogReducer(){
		return catalogReducerInstance.get();
	}

	public ApplicationFilterCatalogProducer getCatalogProducer() {
		return catalogProducerInstance.get();
	}
}
