package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

public class RegolaDialogBackingBean {

    @Getter
    @Setter
    private List<CfgPianificazTipoRigaEntity> tipoRighe;

    @Getter
    @Setter
    private List<CfgPianificazioneCampiEntity> campi;

    @Getter
    private Long idTipoRigaSelected;

    @Getter
    private Long idCampoSelected;

    @Getter
    boolean confirmBtnDisabled = true;

    public void setIdTipoRigaSelected(Long idTipoRigaSelected) {
        this.idTipoRigaSelected = idTipoRigaSelected;
        updateConfirmBtn();
    }

    public void setIdCampoSelected(Long idCampoSelected) {
        this.idCampoSelected = idCampoSelected;
        updateConfirmBtn();
    }

    public CfgPianificazTipoRigaEntity getTipoRigaSelected() {
        return tipoRighe.stream().filter(r -> idTipoRigaSelected.equals(r.getId())).findFirst().orElse(null);
    }

    public CfgPianificazioneCampiEntity getCampoSelected() {
        return campi.stream().filter(c -> idCampoSelected.equals(c.getId())).findFirst().orElse(null);
    }

    public boolean isValid() {
        return idTipoRigaSelected != null && idCampoSelected != null;
    }

    public void resetDialog() {
        setIdTipoRigaSelected(null);
        setIdCampoSelected(null);
    }

    public String customDesc(CfgPianificazioneCampiEntity campo) {
        StringBuilder sb = new StringBuilder(campo.getDescrizione());
        if (!StringUtils.isBlank(campo.getCodiceCampo())) {
            sb.append(" [").append(campo.getCodiceCampo()).append("]");
        }
        if (!StringUtils.isBlank(campo.getEntityLookup()) && !StringUtils.isBlank(campo.getEntityAttribute())) {
            sb.append(" (Entity Lookup - ").append(campo.getEntityLookup()).append(")");
        }
        return sb.toString();
    }

    private void updateConfirmBtn() {
        this.confirmBtnDisabled = idTipoRigaSelected == null || idCampoSelected == null;
    }
}
