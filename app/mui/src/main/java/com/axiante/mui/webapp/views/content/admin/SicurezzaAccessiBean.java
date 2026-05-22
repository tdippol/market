package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.views.FacesContextAware;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class SicurezzaAccessiBean implements Serializable, FacesContextAware {
    private static final long serialVersionUID = -8142728995037556445L;

    @Inject
    private MuiService muiService;

    @Getter
    @Setter
    private GroupEntity gruppoSelected;

    @Getter
    @Setter
    private List<GroupEntity> groupList;

    @Getter
    @Setter
    private PianificazioneSecurityEnum accessoScheda;

    @Getter
    @Setter
    private PianificazioneSecurityEnum accessoPianificazione;

    @Getter
    @Setter
    private Boolean pianificazioneCompratore;

    @Getter
    @Setter
    private Boolean filtroArticoli;

    public void resetDialog() {
        setGruppoSelected(null);
        setAccessoScheda(PianificazioneSecurityEnum.READ);
        setAccessoPianificazione(PianificazioneSecurityEnum.READ);
        setPianificazioneCompratore(Boolean.FALSE);
        setFiltroArticoli(Boolean.FALSE);
    }

    public void loadGroups(List<GroupEntity> usedGroups) {
        try {
            // Escludo i gruppi gia' utilizzati e configurati con un qualche livello di security
            groupList = muiService.readGroups().stream()
                    .filter(g -> !usedGroups.contains(g))
                    .sorted(Comparator.comparing(GroupEntity::getCodiceGruppo))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error reading groups", ex);
            groupList = new ArrayList<>();
        }
    }

    public boolean validate() {
        return gruppoSelected != null && accessoScheda != null && accessoPianificazione != null
                && pianificazioneCompratore != null && filtroArticoli != null;
    }
}
