package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaCfgCheckEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaControlliService;
import com.axiante.mui.dbpromo.persistence.service.SupportoMediaService;
import com.axiante.mui.webapp.business.SupportoMediaCheckService;
import com.axiante.mui.webapp.business.supportimedia.SupportoMediaCheckEnum;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.views.content.dbpromo.dtos.ControlloConfiguratoDTO;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class SupportiMediaConfiguratiBean implements Serializable, FacesContextAware {
    private static final long serialVersionUID = 1448917947459399239L;

    @Inject
    private Instance<SupportoMediaService> supportoMediaServiceInstance;

    @Inject
    private Instance<SupportoMediaCheckService> supportoMediaCheckServiceInstance;

    @Getter
    private List<SupportoMediaEntity> supportiMedia;

    @Getter
    private SupportoMediaEntity supportoMediaSelezionato = null;

    @Getter
    private List<ControlloConfiguratoDTO> controlliConfigurati;

    @Inject
    Instance<PianoMediaControlliService> pianoMediaControlliServiceInstance;

    @PostConstruct
    public void init() {
        readSupportiMedia();
        readControlliConfigurati();
        if (supportiMedia != null && supportiMedia.size() > 0) {
            setSupportoMediaSelezionato(supportiMedia.get(0));
        }
    }

    public void setSupportoMediaSelezionato(SupportoMediaEntity supportoMediaSelezionato) {
        this.supportoMediaSelezionato = supportoMediaSelezionato;
        if (supportoMediaSelezionato == null) {
            return;
        }
        clearSelectedChecks();
        updateSelectedChecks();
    }

    public void attivaControllo(ControlloConfiguratoDTO controllo) {
        if (supportoMediaSelezionato == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    String.format("Errore nella selezione del controllo '%s'", controllo.getCheckEnum().getCodice())));
            return;
        }
        if (controllo.isAbilitato()) {
            // Devo aggiungere la relazione
            try {
                if (addControllo(controllo.getCheckEnum().getCodice())) {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                            String.format("Controllo '%s' aggiunto al supporto Media '%s'",
                                    controllo.getCheckEnum().getCodice(), supportoMediaSelezionato.getCodiceMedia())));
                } else {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Errore aggiunta Controllo '%s' al Supporto Media '%s'",
                                controllo.getCheckEnum().getCodice(), supportoMediaSelezionato.getCodiceMedia())));
                }
            } catch (Exception ex) {
                log.error(String.format("Error adding Check '%s' to Supporto Media '%s'",
                        controllo.getCheckEnum().getCodice(), supportoMediaSelezionato.getCodiceMedia()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Errore aggiunta Controllo '%s' al Supporto Media '%s'",
                                controllo.getCheckEnum().getCodice(), supportoMediaSelezionato.getCodiceMedia())));
            }
        } else {
            // Devo eliminare la relazione
            if ( canRemoveControllo(controllo.getCheckEnum().getCodice()) ){
                try {
                    if (removeControllo(controllo.getCheckEnum().getCodice())) {
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                                String.format("Controllo '%s' rimosso dal supporto Media '%s'",
                                        controllo.getCheckEnum().getCodice(), supportoMediaSelezionato.getCodiceMedia())));
                    } else {
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                                String.format("Errore rimozione Controllo '%s' dal Supporto Media '%s'",
                                        controllo.getCheckEnum().getCodice(), supportoMediaSelezionato.getCodiceMedia())));
                    }
                } catch (Exception ex) {
                    log.error(String.format("Error removing Check '%s' from Supporto Media '%s'",
                            controllo.getCheckEnum().getCodice(), supportoMediaSelezionato.getCodiceMedia()), ex);
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                            String.format("Errore rimozione Controllo '%s' dal Supporto Media '%s'",
                                    controllo.getCheckEnum().getCodice(), supportoMediaSelezionato.getCodiceMedia())));
                }
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Il controllo %s non puo' essere rimosso dal supporto media %s perche' in uso",
                                controllo.getCheckEnum().getCodice(), supportoMediaSelezionato.getCodiceMedia())));
            }
        }
        readSupportiMedia();
        readControlliConfigurati();
        clearSelectedChecks();
        updateSelectedChecks();
    }

    private boolean addControllo(String codiceControllo) {
        final ControlloConfiguratoDTO controlloDto = controlliConfigurati.stream()
                .filter(c -> codiceControllo.equals(c.getCheckEnum().getCodice()))
                .findAny()
                .orElse(null);
        if (controlloDto != null) {
            SupportoMediaCfgCheckEntity check = getControlloFromSelectedSupporto(codiceControllo);
            if (check == null) {
                check = new SupportoMediaCfgCheckEntity();
                check.setSupportoMedia(supportoMediaSelezionato);
                check.setCodiceControllo(codiceControllo);
                check.setBlocco(false);
                supportoMediaSelezionato.addCheck(check);
                supportoMediaSelezionato = supportoMediaServiceInstance.get().persist(supportoMediaSelezionato);
                controlloDto.setSupportoMedia(supportoMediaSelezionato);
                controlloDto.setAbilitato(true);
                return true;
            } else {
                log.error(String.format("Controllo '%s' gia' presente per il Supporto Media '%s'",
                        codiceControllo, supportoMediaSelezionato.getCodiceMedia()));
            }
        } else {
            log.error(String.format("Controllo '%s' non presente", codiceControllo));
        }
        return false;
    }

    private boolean canRemoveControllo(String codiceControllo){
        try{
            if ( codiceControllo != null )
                return pianoMediaControlliServiceInstance.get().countByCodiceSupportoConfigurato(codiceControllo) == 0;
        } catch (Exception e ){
            log.error("Error during check ", e);
        }
        return true;

    }

    private boolean removeControllo(String codiceControllo) {
        final ControlloConfiguratoDTO controlloDto = controlliConfigurati.stream()
                .filter(c -> codiceControllo.equals(c.getCheckEnum().getCodice()))
                .findAny()
                .orElse(null);
        if (controlloDto != null) {
            SupportoMediaCfgCheckEntity check = getControlloFromSelectedSupporto(codiceControllo);
            if (check != null) {
                supportoMediaSelezionato.removeCheck(check);
                supportoMediaSelezionato = supportoMediaServiceInstance.get().persist(supportoMediaSelezionato);
                controlloDto.reset();
                return true;
            } else {
                log.error(String.format("Controllo '%s' gia' presente per il Supporto Media '%s'",
                        codiceControllo, supportoMediaSelezionato.getCodiceMedia()));
            }
        } else {
            log.error(String.format("Controllo '%s' non presente", codiceControllo));
        }
        return false;
    }

    private void readSupportiMedia() {
        supportiMedia = supportoMediaServiceInstance.get().findAllAttivi().stream()
                .sorted(Comparator.comparing(SupportoMediaEntity::getCodiceMedia))
                .collect(Collectors.toList());
    }

    private void readControlliConfigurati() {
        controlliConfigurati = supportoMediaCheckServiceInstance.get().readAll().stream()
                .sorted(Comparator.comparing(SupportoMediaCheckEnum::getCodice))
                .map(ControlloConfiguratoDTO::new)
                .collect(Collectors.toList());
    }

    private SupportoMediaCfgCheckEntity getControlloFromSelectedSupporto(String codiceControllo) {
        return supportoMediaSelezionato.getSupportoMediaChecks().stream()
                .filter(c -> codiceControllo.equals(c.getCodiceControllo()))
                .findAny()
                .orElse(null);
    }

    private void clearSelectedChecks() {
        controlliConfigurati.forEach(ControlloConfiguratoDTO::reset);
    }

    private void updateSelectedChecks() {
        controlliConfigurati.forEach(c -> {
            SupportoMediaCfgCheckEntity check = getControlloFromSelectedSupporto(c.getCheckEnum().getCodice());
            if (check != null) {
                c.setSupportoMedia(supportoMediaSelezionato);
                c.setAbilitato(true);
            } else {
                c.reset();
            }
        });
    }
}
