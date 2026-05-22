package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.dbpromo.business.enumeration.PianoMediaTipoData;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgPianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.SupportoMediaService;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.data.PianoMediaOperazioniMassiveBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.SupportiMediaConfiguratiBean;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.menu.MenuItem;

@MuiViewModel("pianoMediaAmministrazione")
@Dependent
@Slf4j
public class PianoMediaAmministrazioneView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = -4206172901246234394L;

    private int TAB_ANAGRAFICHE = 0;
    private int TAB_CONTROLLI = 1;

    @Inject
    private Instance<SupportoMediaService> supportoMediaServiceInstance;

    @Inject
    private Instance<CfgPianoMediaService> cfgPianoMediaServiceInstance;

    @Getter
    @Setter
    private Integer activeTab = TAB_ANAGRAFICHE;

    @Getter
    private String codiceSupporto;

    @Getter
    private String descrizioneSupporto;

    @Getter
    private boolean btnConfirmAddInizializzazioneDisabled = true;

    private SupportoMediaEntity selectedMedia;

    @Inject
    @Getter
    private SupportiMediaConfiguratiBean supportiMediaConfiguratiBean;

    @Inject
    @Getter
    private PianoMediaOperazioniMassiveBean operazioniMassiveBean;

    public static final int ANAGRAFICHE = 0;
    public static final int CONTROLLI = 1;
    public static final int CONFIGURAZIONE = 2;
    public static final int OPERAZIONI = 3;

    @Getter
    boolean admin = false;
    public void init() {
        // NOOP
        this.admin = getUserDto().isAdmin();
    }

    public void onTabChange(TabChangeEvent event) {
        TabView tv = (TabView) event.getComponent();
        this.activeTab = tv.getChildren().indexOf(event.getTab());
        if (this.activeTab == CONTROLLI && supportiMediaConfiguratiBean != null){
            supportiMediaConfiguratiBean.init();
        }
    }

    public void prepareDialogAddSupporto() {
        resetDialogAddSupporto();
    }

    public void prepareDialogAddInizializzazione(){
        resetDialogAddInizializzazione();
    }

    public void confirmDialogAddSupporto() {
        if (validate()) {
            try {
                final SupportoMediaEntity entity = (SupportoMediaEntity) AuditLogFiller
                        .fillAuditLogFields(new SupportoMediaEntity(), getUserDto().getUsermame());
                entity.setCodiceMedia(codiceSupporto.trim().toUpperCase());
                entity.setDescrizione(descrizioneSupporto.trim().toUpperCase());
                supportoMediaServiceInstance.get().persist(entity);
                executeScript("PF('dlgAddSupporto').hide()");
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        String.format("Creato nuovo Supporto Media con codice '%s'", codiceSupporto)));
                resetDialogAddSupporto();
            } catch (Exception ex) {
                log.error(String.format("Error adding 'SupportoMedia' with codice '%s'", codiceSupporto), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Errore creazione nuovo Supporto Media con codice '%s'; contattare l'assistenza", codiceSupporto)));
            }
        }
    }

    public void resetDialogAddSupporto() {
        this.codiceSupporto = null;
        this.descrizioneSupporto = null;
    }

    public void setCodiceSupporto(String codiceSupporto) {
        this.codiceSupporto = codiceSupporto != null ? codiceSupporto.toUpperCase() : null;
    }

    public void setDescrizioneSupporto(String descrizioneSupporto) {
        this.descrizioneSupporto = descrizioneSupporto != null ? descrizioneSupporto.toUpperCase() : null;
    }

    private boolean validate() {
        return validateCodice() && validateDescrizione();
    }

    private boolean validateDescrizione() {
        if (StringUtils.isBlank(descrizioneSupporto)) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Specificare una descrizione per il supporto media"));
            return false;
        }
        return true;
    }

    private boolean validateCodice() {
        if (StringUtils.isBlank(codiceSupporto)) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Specificare un codice per il supporto media"));
            return false;
        }
        if (supportoMediaServiceInstance.get().isCodeUsed(codiceSupporto)) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    String.format("Il codice '%s' è già in uso.", codiceSupporto)));
            return false;
        }
        return true;
    }

    @Override
    public void setNode(MenuItem node) {
        super.setNode(node);
        applyDefaultRules();
        init();
    }

    @Override
    public void applyRules() {
        // NOOP
    }

    @Override
    public void applyDefaultRules() {
        // NOOP
    }

    public List<SupportoMediaEntity> getMediaList(){
        List<SupportoMediaEntity> list = supportoMediaServiceInstance.get().findAllAttivi();
        List<CfgPianoMediaEntity> used = cfgPianoMediaServiceInstance.get().findAll();
        used.forEach(cfg -> list.remove(cfg.getSupportoMedia()));
        return list;
    }

    public void confirmDialogAddInizializzazione(){
        if (selectedMedia != null){
            try {
                final CfgPianoMediaEntity cfg = new CfgPianoMediaEntity();
                cfg.setSupportoMedia(selectedMedia);
                cfg.setTipoDataInizio(PianoMediaTipoData.CAMPAGNA);
                cfg.setTipoDataFine(PianoMediaTipoData.CAMPAGNA);
                cfgPianoMediaServiceInstance.get().persist(cfg);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        String.format("Configurazione Media aggiunta con supporto '%s'", selectedMedia.getCodiceMedia())));
                executeScript("PF('dlgAddInizializzazione').hide()");

                resetDialogAddInizializzazione();
            } catch (Exception ex) {
                log.error(String.format("Error adding 'PianoMedia' with supporto '%s'", selectedMedia.getCodiceMedia()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Errore aggiunta configurazione per supporto '%s'; contattare l'assistenza", selectedMedia.getCodiceMedia())));
            }
        }
    }

    public void resetDialogAddInizializzazione(){
        this.selectedMedia = null;
    }

    public void setSelectedMedia(long media){
        if ( media == -1){
            this.selectedMedia = null;
        }
        getMediaList().stream().filter(m -> m.getId().equals(media)).findFirst().ifPresent(m -> this.selectedMedia = m);
        this.btnConfirmAddInizializzazioneDisabled = (this.selectedMedia == null);
    }

    public long getSelectedMedia(){
        return this.selectedMedia != null ? this.selectedMedia.getId() : -1;
    }

    public boolean setOperaSuTuttiIPiani(){
        return true;
    }
}
