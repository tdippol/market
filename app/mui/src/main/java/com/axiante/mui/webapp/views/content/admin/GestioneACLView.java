package com.axiante.mui.webapp.views.content.admin;


import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.entity.AclEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.tm1.mdx.objects.Query;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;

@Slf4j
@MuiViewModel("gestioneACL")
@Named("gestioneACL")
@SessionScoped
public class GestioneACLView extends AbstractAdminView{

	private static final long serialVersionUID = -7845751138146058766L;
	@Inject
	@Getter
	transient private ApplicationFilterCatalogProducer catalogProducer;
	@Inject
	@Getter
	transient private CatalogReducer catalogReducer ;
	@Inject
	transient private MuiService muiService;
	@Getter
	@Setter
	private List<AclEntity> aclList;
	@Getter
	@Setter
	private List<RolesEntity> rolesList;
	@Getter
	@Setter
	private List<String> componentGroupList;
	@Getter
	@Setter
	private AclEntity selectedACL;

	@Override
	public void updateView() {
		initSelectedAcl();
		readComponentGroups();
		refresh();
	}
	public void refresh() {
		readACLs();
		readRoles();
	}
	@Override
	public void updateView(final String grid){
		updateView();
	}


	@PostConstruct
	public void init() {
		updateView();
	}

	public void openAddACLDialog(){
		initSelectedAcl();
		readRoles();
		PrimeFaces.current().executeScript("PF('dlgAddACLWV').show();");
	}

	private void initSelectedAcl(){
		selectedACL=new AclEntity();
	}

	private void readACLs() {
		try {
			aclList = muiService.readACLs();

		} catch (final Exception e) {
			log.error("Error reading ACLs", e);
		}
	}

	private void readRoles() {
		try {
			rolesList = muiService.readRoles();
			rolesList.sort(Comparator.comparing(RolesEntity::getName));
		} catch (final Exception e) {
			log.error("Error reading Roles", e);
		}
	}

	private void readComponentGroups() {
		try {
			componentGroupList = muiService.readComponentGroups();
		} catch (final Exception e) {
			log.error("Error reading Component Groups", e);
		}
	}

	public void addACL(){
		try {
			if(validate()) {
				muiService.persistACL(selectedACL);
				readACLs();
				PrimeFaces.current().executeScript("PF('dlgAddACLWV').hide();");
				cleanACLDialog();
				addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "Regola ACL Aggiunta!", "")
						);
			}
		} catch (final Exception e) {
			log.error("Error adding ACL", e);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",  e.getMessage()));
		}
	}
	public void editACL(final AclEntity acl){
		try {
			setSelectedACL(acl);
			if(validate()) {
				muiService.persistACL(selectedACL);
				readACLs();
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Regola ACL Modificata!", ""));
			}
		} catch (final Exception e) {
			log.error("Error editing ACL", e);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
	}
	public void deleteACL(){
		try {
			muiService.removeACL(selectedACL);
			readACLs();
			cleanACLDialog();
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Regola ACL Eliminata!", ""));
		} catch (final Exception e) {
			log.error("Error removing ACL", e);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
		}
	}

	private void cleanACLDialog(){
		selectedACL=new AclEntity();
	}

	public boolean validate() throws Exception{
		if(selectedACL==null) {
			throw new Exception("Errore di inizializzazione oggetto 'Regola ACL'.");
		}
		if(selectedACL.getRolesByRoleId()==null) {
			throw new Exception("Campo 'Ruolo' obbligatorio.");
		}
		if((selectedACL.getComponentClass()==null) || selectedACL.getComponentClass().equals("")) {
			throw new Exception("Campo 'Classe Componente' obbligatorio.");
		}
		if((selectedACL.getComponent()==null) || selectedACL.getComponent().equals("")) {
			throw new Exception("Campo 'Componente' obbligatorio.");
		}
		if(selectedACL.getComponent().length()>50) {
			throw new Exception("La lunghezza massima del campo 'Componente' è 50 caratteri.");
		}
		if(selectedACL.getVisible()==null) {
			throw new Exception("Campo 'Visibile' obbligatorio.");
		}
		if(selectedACL.getEnabled()==null) {
			throw new Exception("Campo 'Enabled' obbligatorio.");
		}
		if(selectedACL.getEditable()==null) {
			throw new Exception("Campo 'Editable' obbligatorio.");
		}
		return true;
	}
	@Override
	public Query prepareFilteredQuery(final String grid) {
		return null;
	}


}
