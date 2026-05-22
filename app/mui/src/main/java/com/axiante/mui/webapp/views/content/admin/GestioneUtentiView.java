package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.ViewType;
import com.axiante.mui.webapp.views.content.admin.pojos.UserWrapper;
import com.axiante.tm1.mdx.objects.Query;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

@MuiViewModel("gestioneUtenti")
@Named("gestioneUtenti")
@SessionScoped
@Slf4j
public class GestioneUtentiView extends AbstractAdminView {

    private static final long serialVersionUID = -7845751138146058766L;

    @Inject
    @Getter
    transient private ApplicationFilterCatalogProducer catalogProducer;

    @Inject
    @Getter
    transient private CatalogReducer catalogReducer;

    @Inject
    transient private MuiService muiService;

    @Getter
    @Setter
    private UserWrapper selectedUser;

    @Getter
    @Setter
    private List<RolesEntity> rolesList;

    @Getter
    @Setter
    private List<UserWrapper> usersList;

    @Getter
    @Setter
    private List<UserWrapper> filteredValues;

    @Getter
    private boolean editAdminFilter;

    @Getter
    @Setter
    private List<GroupEntity> groupList;

    @Getter
    private String dialogTitle;

    @Override
    public void updateView(final String grid) {
        updateView();
    }

    @Override
    public void updateView() {
        prepareJsonFilter();
    }

    @PostConstruct
    public void init() {
        initSelectedUser();
        readUsers();
        readRoles();
        readGroups();
        log.debug("Gestione Utente View initialized");
    }

    public void openAddUserDialog() {
        editAdminFilter = false;
        initSelectedUser();
        readRoles();
        dialogTitle = "Aggiungi Utente";
    }

    public void openEditAdminFilterDialog() {
        editAdminFilter = true;
        dialogTitle = "Modifica Utente";
    }

    private void initSelectedUser() {
        selectedUser = new UserWrapper(new UsersEntity());
        selectedUser.setFilters("{}");
        selectedUser.setHiddenCols("[]");
    }

    private void readRoles() {
        try {
            rolesList = muiService.readRoles();
            rolesList.sort(Comparator.comparing(RolesEntity::getName));
        } catch (final Exception e) {
            log.error("error reading roles", e);
        }
    }

    private void readGroups() {
        try {
            groupList = muiService.readGroups();
            groupList = groupList.stream().filter(Objects::nonNull)
                    .sorted(Comparator.comparing(GroupEntity::getDescrizione)).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("error reading groups", e);
        }
    }

    private void readUsers() {
        try {
            usersList = muiService.readUsers().stream().map(UserWrapper::new)
                    .sorted(Comparator.comparing(UserWrapper::getName)).collect(Collectors.toList());
        } catch (final Exception ex) {
            log.error("Error reading users", ex);
        }
    }

    private void queueNewUserInView(UserWrapper user){
        usersList.add(user);
        usersList.sort(Comparator.comparing(UserWrapper::getName));
    }

    public void reloadUsers() {
        log.warn("called reload on users table");
    }

    public void addUser() {
        final Map<String, String> params = getRequestParameterMap();
        final String json = params.get("jsonFilterd");
        selectedUser.setAdminFilters(json);
        if (selectedUser.getAdminFilters() == null) {
            selectedUser.setAdminFilters("{}");
        }
        if ("[]".equals(selectedUser.getAdminFilters())) {
            selectedUser.setAdminFilters("{}");
        }
        if (selectedUser.getHiddenCols() == null) {
            selectedUser.setHiddenCols("[]");
        }
        if (saveUser(selectedUser)) {
            queueNewUserInView(selectedUser);
            PrimeFaces.current().executeScript("PF('dlgAddUserWV').hide();");
            cleanUserDialog();
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Utente Aggiunto!", ""));
        }
    }

    public void editUser(final RowEditEvent event) {
        setSelectedUser((UserWrapper) event.getObject());
        if (saveUser(getSelectedUser())) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Utente Modificato", ""));
        }
    }

    private boolean saveUser(UserWrapper user) {
        try {
            if (validate(user)) {
                user.updateUser(muiService.persistUser(user.getEntity()));
                muiService.persistGroups(new ArrayList<>(user.getGroupsToUpdate()));
                user.clearGroupUpdateList();
                user.clearRolesUpdateList();
                return true;
            }
        } catch (final Exception e) {
            log.error("Error saving user", e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
        }
        return false;
    }

    public void deleteUser(UserWrapper e) {
        setSelectedUser(e);
        deleteUser();
    }

    public void deleteUser() {
        try {
            if (selectedUser != null) {
                selectedUser.removeCurrentRoles();
                muiService.removeUser(selectedUser.getEntity());
                readUsers();
            }
            initSelectedUser();
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Utente Eliminato!", ""));
        } catch (final Exception e) {
            log.error("Error deleting user", e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
        }
    }

    public void resetUserPrefs() {
        try {
            if (selectedUser != null) {
                selectedUser.setHiddenCols("[]");
                selectedUser.setIngridFilters("{}");
                selectedUser.updateUser(muiService.persistUser(selectedUser.getEntity()));
                readUsers();
            }
            addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Reset delle preferenze Utente eseguito!", ""));
        } catch (final Exception e) {
            log.error("Error resetting user preferences", e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
        }
    }

    private void cleanUserDialog() {
        selectedUser = new UserWrapper(new UsersEntity());
    }

    public boolean validate() throws Exception {
        return validate(selectedUser);
    }

    private boolean validate(UserWrapper user) throws  Exception{
        if (user == null) {
            throw new Exception("Errore di inizializzazione oggetto 'Utente'.");
        }
        if ((user.getName() == null) || user.getName().equals("")) {
            throw new Exception("Campo 'Nome' obbligatorio.");
        }
        if ((user.getName() != null) && (user.getName().length() > 50)) {
            throw new Exception("La lunghezza massima del campo 'Nome' è 50 caratteri.");
        }
        if ((user.getRoles() == null) || user.getRoles().isEmpty()) {
            throw new Exception("Campo 'Ruolo' obbligatorio.");
        }

        return true;
    }
    @Override
    public Query prepareFilteredQuery(final String grid) {
        return null;
    }

    @Override
    public ViewType getViewType() {
        return ViewType.ADMIN;
    }

    // disabling filter button
    @Override
    public boolean isDisableButtonFilters() {
        return true;
    }

    @Override
    public boolean needsFilter(){
        return true;
    }
}
