package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.webapp.views.FacesContextAware;
import java.util.List;
import javax.faces.application.FacesMessage;
import lombok.Getter;
import lombok.Setter;

public class HeaderDialogBackingBean implements FacesContextAware {

    @Getter
    @Setter
    List<MeccanicheEntity> meccaniche;

    @Getter
    @Setter
    List<CfgLivelloPianificazioneEntity> livelli;

    @Getter
    Long idMeccanicaSelected;

    @Getter
    Long idLivelloSelected;

    @Getter
    Integer minSet;

    @Getter
    Integer maxSet;

    @Getter
    Integer minRaggr;

    @Getter
    Integer maxRaggr;

    @Getter
    @Setter
    boolean unicaInPromo;

    @Getter
    boolean confirmBtnDisabled = true;

    public void resetDialog() {
        setIdMeccanicaSelected(null);
        setIdLivelloSelected(null);
        setMinSet(null);
        setMaxSet(null);
        setMinRaggr(null);
        setMaxRaggr(null);
        setUnicaInPromo(false);
        confirmBtnDisabled = true;
    }

    public void setIdMeccanicaSelected(Long idMeccanicaSelected) {
        this.idMeccanicaSelected = idMeccanicaSelected;
        updateConfirmBtn();
    }

    public void setIdLivelloSelected(Long idLivelloSelected) {
        this.idLivelloSelected = idLivelloSelected;
        updateConfirmBtn();
    }

    public void setMinSet(Integer minSet) {
        this.minSet = minSet;
        updateConfirmBtn();
    }

    public void setMaxSet(Integer maxSet) {
        this.maxSet = maxSet;
        updateConfirmBtn();
    }

    public void setMinRaggr(Integer minRaggr) {
        this.minRaggr = minRaggr;
        updateConfirmBtn();
    }

    public void setMaxRaggr(Integer maxRaggr) {
        this.maxRaggr = maxRaggr;
        updateConfirmBtn();
    }

    public MeccanicheEntity getMeccanicaSelected() {
        return meccaniche.stream().filter(m -> idMeccanicaSelected.equals(m.getId())).findFirst().orElse(null);
    }

    public CfgLivelloPianificazioneEntity getLivelloSelected() {
        return livelli.stream().filter(l -> idLivelloSelected.equals(l.getId())).findFirst().orElse(null);
    }

    public boolean isValid() {
        return idMeccanicaSelected != null
                && idLivelloSelected != null
                && validateMinMaxSet()
                && validateMinMaxRaggr();
    }

    private void updateConfirmBtn() {
        this.confirmBtnDisabled = !isValid();
    }

    private boolean validateMinMaxSet() {
        final boolean isValid = minSet == null || maxSet == null || minSet <= maxSet;
        if (!isValid) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Il valore per 'Min Set' supera quello per 'Max Set'"));
        }
        return isValid;
    }

    private boolean validateMinMaxRaggr() {
        final boolean isValid = minRaggr == null || maxRaggr == null || minRaggr <= maxRaggr;
        if (!isValid) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Il valore per 'Min Raggruppamento' supera quello per 'Max Raggruppamento'"));
        }
        return isValid;
    }
}
