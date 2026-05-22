package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.entity.AclEntity;
import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.SlowRolesEntity;
import com.axiante.mui.persistence.entity.UploadDocumentEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.persistence.service.RolesService;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.admin.pojos.UploadDocumentPojo;
import com.axiante.tm1.mdx.objects.Query;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

@MuiViewModel("gestioneRuoli")
@Named("gestioneRuoli")
@SessionScoped
@Slf4j
public class GestioneRuoliView extends AbstractAdminView {

	private static final long serialVersionUID = -7845751138146058766L;

	private static final int TAB_GRUPPI = 0;
	private static final int TAB_DOCUMENTI = 1;
	private static final int TAB_UTENTI = 2;

	@Inject
	@Getter
	transient private ApplicationFilterCatalogProducer catalogProducer;

	@Inject
	@Getter
	transient private CatalogReducer catalogReducer;

	@Getter
	@Setter
	private Integer activeTab = TAB_GRUPPI;

	@Getter
	private RolesEntity selectedRoleCombo;

	@Getter
	private RuoliUtentiBackingBean ruoliUtentiBean;

	@Inject
	RolesService rolesService;

	@Override
	public void updateView(final String grid) {
		updateView();
	}

	@Override
	public void updateView() {
		// do nothing
	}

	@PostConstruct
	public void init() {
		gestioneDocumentiBean = new GestioneDocumentiBackingBean();
		ruoliUtentiBean = new RuoliUtentiBackingBean();
		initSelectedRole();
		readRoles();
		loadDocuments();
	}

	@Inject
	transient private MuiService muiService;

	@Inject
	ApplicationProperties applicationProperties;

	@Getter
	@Setter
	private RolesEntity selectedRole;

	@Getter
	@Setter
	private List<RolesEntity> rolesList;

	@Getter
	@Setter
	private RolesEntity duplicateRole;

	@Getter
	private GestioneDocumentiBackingBean gestioneDocumentiBean;

	public void openAddRoleDialog() {
		initSelectedRole();
		readRoles();
		PrimeFaces.current().executeScript("PF('dlgAddRoleWV').show();");
	}

	public void openDuplicaRoleDialog() {
		duplicateRole = new RolesEntity();
		duplicateRole.setName("Copy of " + selectedRole.getName());
		duplicateRole.setDescription("Copy of " + selectedRole.getDescription());
		PrimeFaces.current().executeScript("PF('dlgDuplicaRuoloWV').show();");
	}

	private void initSelectedRole() {
		selectedRole = new RolesEntity();
	}

	private void readRoles() {
		try {
			rolesList = muiService.readRoles();
			rolesList.sort(Comparator.comparing(RolesEntity::getName));
			if (rolesList != null && !rolesList.isEmpty()) {
				setSelectedRoleCombo(rolesList.get(0));
			}
		} catch (final Exception ignored) {
		}
	}

	public void handleFileUpload(final FileUploadEvent event) {
		if ((event != null) && (event.getFile() != null)) {
			SlowRolesEntity role = rolesService.findById(selectedRole.getId());
			role.setHelpData(event.getFile().getContents());
			role.setHelpFilename(event.getFile().getFileName());
			try {
				if (validate()) {
					role = rolesService.persist(role);
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manuale Caricato!", ""));
					PrimeFaces.current().executeScript("PF('dlgLoadManualWV').hide();");
					selectedRole=muiService.findRole(selectedRole.getId());
					readRoles();
				}
			} catch (final Exception e) {
				log.error(String.format("Error upload user manual for role '%s'", selectedRole.getName()), e);
				addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento", e.getMessage()));
			}
		} else {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento",
					"Non e' stato possibile caricare il file"));
		}
	}

	public void copyRole() {
		try {
			if (validate()) {
				// in selected ho i valori nuovi di name e description ....
				RolesEntity e = new RolesEntity();
				e.setName(duplicateRole.getName());
				e.setDescription(duplicateRole.getDescription());
				e = muiService.persistRole(e);
				if ( e.getId() != null ) {
					// duplichiamo le acl
					for (final AclEntity acl : selectedRole.getAcls()) {
						e.addAcl(copy(acl));
					}
					e = muiService.persistRole(e);
					//associamo i menu
					for (final MenuEntity menu : selectedRole.getMenus()) {
						menu.addRole(e);
						muiService.persistMenu(menu);
					}
					e = muiService.persistRole(e);
					readRoles();
					PrimeFaces.current().executeScript("PF('dlgDuplicaRuoloWV').hide();");
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ruolo Aggiunto!", ""));
				} else {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
							"Non e' stato possibile aggiungere il ruolo"));
				}
				cleanRoleDialog();
			}
		} catch (final Exception e) {
			log.error("Error copying role", e);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
		}
	}

	private AclEntity copy(AclEntity a) {
		final AclEntity e = new AclEntity();
		e.setComponent(a.getComponent());
		e.setComponentClass(a.getComponentClass());
		e.setEditable(a.getEditable());
		e.setEnabled(a.getEnabled());
		e.setVisible(a.getVisible());
		return e;
	}

	public void addRole() {
		try {
			if (validate()) {
				muiService.persistRole(selectedRole);
				readRoles();
				PrimeFaces.current().executeScript("PF('dlgAddRoleWV').hide();");
				cleanRoleDialog();
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ruolo Aggiunto!", ""));
			}
		} catch (final Exception e) {
			log.error("Error adding role", e);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
		}
	}

	public void editRole(final RowEditEvent event) {
		try {
			setSelectedRole((RolesEntity) event.getObject());
			if (validate()) {
				final RolesEntity role = selectedRole;
				muiService.persistRole(role);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ruolo Modificato!", ""));
			}
		} catch (final Exception e) {
			log.error("Error edigint role", e);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
		}
	}

	public void deleteRole() {
		try {
			if (selectedRole != null) {
				if ((selectedRole.getUsers() != null) && (selectedRole.getUsers().size() > 0)) {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Impossibile rimuovere il ruolo",
							"Ci sono utenti ancora collegati"));
				} else {
					muiService.removeRole(selectedRole);
					readRoles();
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ruolo Eliminato!", ""));
				}
			}
			selectedRole = null;
		} catch (final Exception e) {
			log.error("Error deleting role", e);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
		}
	}

	private void cleanRoleDialog() {
		selectedRole = new RolesEntity();
		duplicateRole = new RolesEntity();
	}

	public boolean validate() throws Exception {
		return validate(getSelectedRole());
	}

	private boolean validate(RolesEntity role) throws Exception {
		if (role == null) {
			throw new Exception("Errore di inizializzazione oggetto 'Ruolo'.");
		}
		if ((role.getName() == null) || role.getName().equals("")) {
			throw new Exception("Campo 'Nome' obbligatorio.");
		}
		if ((role.getName() != null) && (role.getName().length() > 50)) {
			throw new Exception("La lunghezza massima del campo 'Nome' è 50 caratteri.");
		}
		if ((role.getDescription() != null) && (role.getDescription().length() > 250)) {
			throw new Exception("La lunghezza massima del campo 'Descrizione' è 250 caratteri.");
		}
		return true;
	}

	@Override
	public Query prepareFilteredQuery(final String grid) {
		return null;
	}

	public void onRowSelected(SelectEvent event) {
		loadDocuments();
		gestioneDocumentiBean.setBtnSelectAllDocsDisabled(false);
		gestioneDocumentiBean.setBtnDeselectAllDocsDisabled(false);
		gestioneDocumentiBean.setSelectManyDisabled(false);
	}

	public void selectAllDocs() {
		if (gestioneDocumentiBean.getSelectedRole() == null) {
			final String msg = "Nessun ruolo selezionato: impossibile associare documenti";
			log.error(msg);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", msg));
		} else {
			try {
				gestioneDocumentiBean.getSelectedRole().getDocuments().clear();
				gestioneDocumentiBean.getDocuments()
				.forEach(pojo -> gestioneDocumentiBean.getSelectedRole().addDocument(pojo.getEntity()));
				muiService.persistRole(gestioneDocumentiBean.getSelectedRole());
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Documento associato",
						String.format("Tutti i documenti disponibili sono stati associati al ruolo '%s'",
								gestioneDocumentiBean.getSelectedRole().getName())));
			} catch (Exception ex) {
				log.error("Error adding all documents to role " + gestioneDocumentiBean.getSelectedRole().getName(), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						String.format("Errore associazione documenti al ruolo '%s'",
								gestioneDocumentiBean.getSelectedRole().getName())));
			}
		}
		loadDocuments();
	}

	public void deselectAllDocs() {
		if (gestioneDocumentiBean.getSelectedRole() == null) {
			final String msg = "Nessun ruolo selezionato: impossibile disassociare documenti";
			log.error(msg);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", msg));
		} else {
			try {
				gestioneDocumentiBean.getSelectedRole().getDocuments().clear();
				muiService.persistRole(gestioneDocumentiBean.getSelectedRole());
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Documenti disassociati",
						String.format("Tutti i documenti disponibili sono stati disassociati dal ruolo '%s'",
								gestioneDocumentiBean.getSelectedRole().getName())));
			} catch (Exception ex) {
				log.error("Error removing all documents from role " + gestioneDocumentiBean.getSelectedRole().getName(), ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						String.format("Errore disassociazione documenti dal ruolo '%s'",
								gestioneDocumentiBean.getSelectedRole().getName())));
			}
		}
		loadDocuments();
	}

	public void toggleDocumentForRole(UploadDocumentPojo pojo) {
		if (gestioneDocumentiBean.getSelectedRole() == null) {
			final String msg = "Nessun ruolo selezionato: impossibile associare / disassociare documento";
			log.error(msg);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", msg));
		} else {
			if (pojo.isAssociated()) {
				gestioneDocumentiBean.getSelectedRole().addDocument(pojo.getEntity());
			} else {
				gestioneDocumentiBean.getSelectedRole().removeDocument(pojo.getEntity());
			}
			try {
				muiService.persistRole(gestioneDocumentiBean.getSelectedRole());
				final String msg = String.format("Il documento '%s' e' stato %s ruolo '%s'",
						pojo.getEntity().getName(),
						pojo.isAssociated() ? "associato al" : "disassociato dal",
								gestioneDocumentiBean.getSelectedRole().getName());
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Documento " + (pojo.isAssociated() ? "associato" : "disassociato"), msg));
			} catch (Exception ex) {
				final String logMsg = String.format("Error %s document '%s' from role '%s'",
						pojo.isAssociated() ? "adding" : "removing",
								pojo.getEntity().getName(),
								gestioneDocumentiBean.getSelectedRole().getName());
				log.error(logMsg, ex);
				final String msg = String.format("Errore %s documento '%s' dal ruolo '%s'",
						pojo.isAssociated() ? "associazione" : "disassociazione",
								pojo.getEntity().getName(),
								gestioneDocumentiBean.getSelectedRole().getName());
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", msg));
			}
		}
	}

	public void deleteDocument() {
		try {
			if (gestioneDocumentiBean.getSelectedDoc() != null) {
				deleteFile(gestioneDocumentiBean.getSelectedDoc().getName());
				muiService.removeDocument(gestioneDocumentiBean.getSelectedDoc());
				loadDocuments();
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Documento eliminato",
						String.format("Il documento '%s' e' stato eliminato correttamente",
								gestioneDocumentiBean.getSelectedDoc().getName())));
			}
			gestioneDocumentiBean.setSelectedDoc(null);
		} catch (Exception ex) {
			log.error("Error deleting document " + gestioneDocumentiBean.getSelectedDoc().getName()
					+ " [id=" + gestioneDocumentiBean.getSelectedDoc().getId() + "]", ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
					"Errore eliminazione documento"));
		}
	}

	public void dialogConfirmUpload() {
		uploadFile(true);
	}

	public void dialogCancelUpload() {
		if (gestioneDocumentiBean.getTmpUploadedFilename() != null) {
			try {
				FileUtils.forceDelete(new File(applicationProperties.getProperty(
						ApplicationProperties.DOCUMENT_DATA_PATH, ApplicationProperties.DOCUMENT_DATA_PATH_DEFAULT)
						+ File.separator
						+ gestioneDocumentiBean.getTmpUploadedFilename()));
			} catch (IOException ex) {
				log.warn("Error deleting tmp file " + gestioneDocumentiBean.getTmpUploadedFilename(), ex);
			}
		}
	}

	public void handleDocumentFileUpload(final FileUploadEvent event) {
		if ((event != null) && (event.getFile() != null)) {
			try {
				gestioneDocumentiBean.setUploadedFile(event.getFile());
				final boolean fileExist = muiService
						.findDocumentByName(gestioneDocumentiBean.getUploadedFile().getFileName()) != null;
				if (fileExist) {
					final String filename = gestioneDocumentiBean.getUploadedFile().getFileName()
							+ "."
							+ System.currentTimeMillis()
							+ ".tmp";
					gestioneDocumentiBean.setTmpUploadedFilename(filename);
					writeByte(gestioneDocumentiBean.getUploadedFile().getContents(), filename);
					executeScript("PF('dialogOverwriteFile').show()");
				} else {
					uploadFile(false);
				}
			} catch (Exception ex) {
				final String msg = "Non e' stato possibile caricare il file";
				log.error(msg, ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento", msg));
			}
		} else {
			final String msg = "Non e' stato possibile caricare il file";
			log.error(msg);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento", msg));
		}
	}

	public void tabChanged(TabChangeEvent event) {
		TabView tv = (TabView) event.getComponent();
		this.activeTab = tv.getChildren().indexOf(event.getTab());
		switchTab();
	}

	private void switchTab() {
		switch (activeTab) {
			case TAB_GRUPPI:
				readRoles();
				break;
			case TAB_DOCUMENTI:
				loadDocuments();
				break;
			case TAB_UTENTI:
				readUsers();
				break;
			default:
				// noop
				break;
		}
	}

	private void readUsers() {
		if (selectedRoleCombo != null) {
			try {
				ruoliUtentiBean.setUtenti(selectedRoleCombo.getUsers().stream()
						.sorted(Comparator.comparing(UsersEntity::getName)).collect(Collectors.toList()));
			} catch (Exception ex) {
				log.error(String.format("Error reading users from role %s", selectedRoleCombo.getName()), ex);
			}
		}
	}


	public void setSelectedRoleCombo(RolesEntity selectedRoleCombo) {
		this.selectedRoleCombo = selectedRoleCombo;
		if (activeTab.equals(TAB_UTENTI)) {
			readUsers();
		}
	}

	public void openAddUtentiToRoleDialog() {
		try {
			ruoliUtentiBean.setAvailableUsers(muiService.readUsers().stream()
					.filter(u -> !ruoliUtentiBean.getUtenti().contains(u))
					.sorted(Comparator.comparing(UsersEntity::getName))
					.collect(Collectors.toList()));
		} catch (Exception ex) {
			log.error("Error reading available users", ex);
			ruoliUtentiBean.setAvailableUsers(new ArrayList<>());
		}
		ruoliUtentiBean.setSelectedUsers(null);
		executeScript("PF('dlgAddUtentiToRole').show()");
	}

	public void addUsersToRole() {
		if (selectedRoleCombo != null && ruoliUtentiBean.getSelectedUsers().length > 0) {
			final AtomicInteger count = new AtomicInteger(0);
			Arrays.stream(ruoliUtentiBean.getSelectedUsers()).forEach(u -> {
				try {
					u.addRole(selectedRoleCombo);
					muiService.persistUser(u);
					count.incrementAndGet();
				} catch (Exception ex) {
					log.error(String.format("Error adding role '%s' to user '%s'",
							selectedRoleCombo.getName(), u.getName()), ex);
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
							String.format("Impossibile aggiungere ruolo '%s' a utente '%s'; contattare l'assistenza tecnica.",
									selectedRoleCombo.getName(), u.getName())));
				}
			});
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					String.format("%d %s al gruppo '%s'", count.intValue(),
							ruoliUtentiBean.getSelectedUsers().length == 1 ? "utente aggiunto" : "utenti aggiunti",
							selectedRoleCombo.getName()),
					""));
			readUsers();
			ruoliUtentiBean.setSelectedUsers(null);
		} else {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Nessun utente aggiunto al gruppo", ""));
		}
	}

	public void removeUserFromRole() {
		if (selectedRoleCombo !=  null && ruoliUtentiBean.getSelectedUser() != null) {
			try {
				selectedRoleCombo.removeUser(ruoliUtentiBean.getSelectedUser());
				ruoliUtentiBean.getSelectedUser().removeRole(selectedRoleCombo);
				muiService.persistUser(ruoliUtentiBean.getSelectedUser());
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						String.format("Utente '%s' rimosso dal ruolo '%s'", ruoliUtentiBean.getSelectedUser().getName(),
								selectedRoleCombo.getName()), ""));
				readUsers();
				ruoliUtentiBean.setSelectedUser(null);
			} catch (Exception ex) {
				log.error("Error removing user from role", ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						String.format("Impossibile rimuovere utente dal ruolo '%s'; contattare l'assistenza tecnica.",
								selectedRoleCombo.getName())));
			}
		} else {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Nessun utente rimosso dal ruolo", ""));
		}
	}

	private void loadDocuments() {
		gestioneDocumentiBean.setDocuments(muiService.readDocuments().stream()
				.sorted(Comparator.comparing(UploadDocumentEntity::getName))
				.map(e -> new UploadDocumentPojo(e, e.getRoles().contains(gestioneDocumentiBean.getSelectedRole())))
				.collect(Collectors.toList()));
	}

	private void deleteFile(String filename) {
		final String dataPath = applicationProperties.getProperty(ApplicationProperties.DOCUMENT_DATA_PATH);
		if (dataPath == null) {
			log.error("Missing configuration property " + ApplicationProperties.DOCUMENT_DATA_PATH);
			return;
		}
		String fullFilename = dataPath + File.separator + filename;
		File file = new File(fullFilename);
		if (!file.exists() || !file.canWrite()) {
			log.error("File " + filename + " not exist or there is an issue with directory permissions");
			return;
		}
		try {
			file.delete();
			log.info("File " + fullFilename + " deleted successfully");
		} catch (Exception ex) {
			log.error("Error deleting file " + fullFilename, ex);
		}
	}

	private void uploadFile(final boolean overwrite) {
		try {
			UploadDocumentEntity entity;
			if (overwrite) {
				entity = muiService.findDocumentByName(gestioneDocumentiBean.getUploadedFile().getFileName());
				overwriteUploadedFile();
			} else {
				entity = new UploadDocumentEntity();
				entity.setName(gestioneDocumentiBean.getUploadedFile().getFileName());
				writeByte(gestioneDocumentiBean.getUploadedFile().getContents(),
						gestioneDocumentiBean.getUploadedFile().getFileName());
			}
			muiService.persistDocument(entity, getCurrentUser().getName());
			loadDocuments();
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Documento caricato",
					String.format("Il documento '%s' e' stato caricato correttamente",
							gestioneDocumentiBean.getUploadedFile().getFileName())));
		} catch (Exception ex) {
			log.error("Error writing file", ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento",
					"Non e' stato possibile caricare il file"));
		}
	}

	private void overwriteUploadedFile() throws Exception {
		File srcFile = new File(applicationProperties.getProperty(
				ApplicationProperties.DOCUMENT_DATA_PATH, ApplicationProperties.DOCUMENT_DATA_PATH_DEFAULT)
				+ File.separator
				+ gestioneDocumentiBean.getTmpUploadedFilename());
		File destFile = new File(applicationProperties.getProperty(
				ApplicationProperties.DOCUMENT_DATA_PATH, ApplicationProperties.DOCUMENT_DATA_PATH_DEFAULT)
				+ File.separator
				+ gestioneDocumentiBean.getUploadedFile().getFileName());
		if (destFile.exists()) {
			destFile.delete();
		}
		FileUtils.moveFile(srcFile, destFile);
	}

	private void writeByte(byte[] bytes, String filename) throws Exception {
		final String dataPath = applicationProperties.getProperty(ApplicationProperties.DOCUMENT_DATA_PATH,
				ApplicationProperties.DOCUMENT_DATA_PATH_DEFAULT);
		final File file = new File(dataPath + File.separator + filename);
		final OutputStream os = new FileOutputStream(file);
		os.write(bytes);
		os.flush();
		os.close();
	}
}
